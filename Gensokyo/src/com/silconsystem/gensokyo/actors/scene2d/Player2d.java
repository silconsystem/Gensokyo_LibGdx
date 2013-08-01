package com.silconsystem.gensokyo.actors.scene2d;

// import java utils
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// import gdx packs
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

// import my game packs
import com.silconsystem.gensokyo.actors.PlayerCharacter;
import com.silconsystem.gensokyo.utils.VectorUtils;

public class Player2d extends Image
{
	private static final float MAX_SPEED = 240;
	private static final float MAX_ACCELERATION = 10;
	private static final float MAX_DECELERATION = MAX_ACCELERATION / 2;
	
	private final Vector2 position;
	private final Vector2 velocity;
	private final Vector2 acceleration;
	
	private final Animation	girlsBankAnimation;
	
	private float bankAnimationStateTime;
	private Map<TextureRegion, Drawable> bankAnimationDrawables;
	
	public Player2d(PlayerCharacter playerCharacter, Array<AtlasRegion> girlAnimationFrames)
	{
		// call the constructor
		super(girlAnimationFrames.get(0));
		super.setTouchable(null);
		
		// set Vector2 values
		this.position = new Vector2();
		this.velocity = new Vector2();
		this.acceleration = new Vector2();
		
		this.girlsBankAnimation = new Animation(0.15f, girlAnimationFrames);
		
		// acces the banking animation from the animation frames
		this.bankAnimationDrawables = new HashMap<TextureRegion, Drawable>();
		for (AtlasRegion region : girlAnimationFrames)
		{
			bankAnimationDrawables.put(region, new TextureRegionDrawable(region));
		}
	}
	
	// create link to Player2d {@link Player2d}
	public static Player2d create(PlayerCharacter playerCharacter, TextureAtlas textureAtlas)
	{
		// load regions from the texture atlas
		Array<AtlasRegion> regions = textureAtlas.findRegions(playerCharacter.getPlayerModel().getSimpleName());
		Iterator<AtlasRegion> regionIterator = regions.iterator();
		while (regionIterator.hasNext())
		{
			if (regionIterator.next().index < 0)
			{
				regionIterator.remove();
			}
		}
		
		// return the player
		return new Player2d(playerCharacter, regions);
	}

	// set ship start position
	public void playerStartPosition(float x, float y)
	{
		position.set(x, y);
	}
	
	@Override
	public void act(float delta)
	{
		super.act(delta);
		movePlayer(delta);
		bankPlayer(delta);
	}
	
	/**
	 * 		Move the player around
	 */
	private void movePlayer(float delta)
	{
		// check input and calc acceleration
		if (Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer))
		{
			/*
			 * set accel based on accelerometer input
			 * axis is inverted -> game is in landscape mode
			 */
			acceleration.set(Gdx.input.getAccelerometerY(), Gdx.input.getAccelerometerX());
			
			// set accel bounds
			VectorUtils.adjustByRange(acceleration, -2, 2);
			
			// set input deadzone
			if (!VectorUtils.adjustDeadZone(acceleration, 1f, 0f))
			{
				// if out of deadzone, adjust accel
				// 2 equals 100% of max accel
				acceleration.x = (acceleration.x / 2 * MAX_ACCELERATION);
				acceleration.y = (-acceleration.y / 2 * MAX_ACCELERATION);
			}
		} else {
			// if no keys pressed, no accel, no velocity + or -
			acceleration.x = (Gdx.input.isKeyPressed(Input.Keys.LEFT) ? - MAX_ACCELERATION :
				(Gdx.input.isKeyPressed(Input.Keys.RIGHT) ? MAX_ACCELERATION : 0));
			acceleration.y = (Gdx.input.isKeyPressed(Input.Keys.UP) ? MAX_ACCELERATION :
				(Gdx.input.isKeyPressed(Input.Keys.DOWN) ? - MAX_ACCELERATION : 0));
		}
		/*
		 * if no accel && player = moving, calc decel
		 */
		if (acceleration.len() == 0f && velocity.len() > 0f)
		{
			// horizontal decel
			if (velocity.x > 0)
			{
				acceleration.x = -MAX_DECELERATION;
				if (velocity.x - acceleration.x < 0)
				{
					acceleration.x = -(acceleration.x - velocity.x);
				}
			}
			else if (velocity.x < 0)
			{				
				acceleration.x = MAX_DECELERATION;
				if (velocity.x + acceleration.x > 0)
				{
					acceleration.x = (acceleration.x - velocity.x);
				}
			}
				
			// vertical accel
			if (velocity.y > 0)
			{
				acceleration.y = -MAX_DECELERATION;
				if (velocity.y - acceleration.y < 0)
				{
					acceleration.y = -(acceleration.y - velocity.y);
				}
			}
			else if (velocity.y < 0)
			{
				acceleration.y = MAX_DECELERATION;
				if (velocity.y + acceleration.y > 0)
				{
					acceleration.y = (acceleration.y - velocity.y);
				}
			}
		}
			
		// modify and check player velocity
		velocity.add(acceleration);
		VectorUtils.adjustByRange(velocity, -MAX_SPEED, MAX_SPEED);
		
		// modify and check player position with delta param
		position.add(velocity.x * delta, velocity.y * delta);
		
		// check if player is within screen bounds
		if (VectorUtils.adjustByRangeX(position, 0, (getStage().getWidth() - getWidth())))
				velocity.x = 0;
		if (VectorUtils.adjustByRangeY(position, 0, (getStage().getHeight() - getHeight())))
				velocity.y = 0;
		
		// update players actual position
		setX(position.x);
		setY(position.y);
	}
		
	/**
	 * 		find appropriate animations for banking
	 */
	private void bankPlayer(float delta)
	{
		TextureRegion frame;
		
		if (velocity.x < 0)
		{
			frame = girlsBankAnimation.getKeyFrame(bankAnimationStateTime += delta, false);
			if (frame.isFlipX()) frame.flip(true, false);
		}
		else if (velocity.x > 0)
		{
			frame = girlsBankAnimation.getKeyFrame(bankAnimationStateTime += delta, false);
			if (!frame.isFlipX()) frame.flip(true, false);
		} else {
			bankAnimationStateTime = 0;
			frame = girlsBankAnimation.getKeyFrame(0, false);
		}
		setDrawable(bankAnimationDrawables.get(frame));
	}
	static final String TAG = Player2d.class.getSimpleName();
}
