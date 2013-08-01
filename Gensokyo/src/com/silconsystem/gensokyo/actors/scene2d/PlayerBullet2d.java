package com.silconsystem.gensokyo.actors.scene2d;

// import gdx packs
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

// import my game packs
import com.silconsystem.gensokyo.actors.PlayerCharacter;
import com.silconsystem.gensokyo.actors.Shot;
import com.silconsystem.gensokyo.actors.SpellType;
import com.silconsystem.gensokyo.screens.LevelScreen;

public class PlayerBullet2d extends Image
{
	private static final float BULLET_SPEED = 10f;
	
	private final Vector2 position;
	private final Vector2 velocity;
	
	private float width;
	private float height;
	
	private boolean alive;
	
	private TextureRegion bulletImage;	
	
	private Shot shot;
	private Player2d player2d;
	private TextureAtlas atlas;
	private PlayerCharacter playerCharacter;
	private SpellType spellType;
	private LevelScreen levelScreen;
	
	private PlayerBullet2d(SpellType spellType, TextureRegion bulletImage, boolean alive)
	{
		// playerbullet2d constructor
		super(bulletImage);
		super.setTouchable(null);
		
		this.player2d = new Player2d(playerCharacter, null);
		this.width = player2d.getImageWidth();
		this.height = player2d.getImageHeight();
		
		this.atlas = new TextureAtlas(Gdx.files.internal("screens/gamescreen/atlas/bullets.pack"));
		this.alive = false;
		
		this.bulletImage = new TextureRegion();
		
		this.position = new Vector2();
		this.velocity = new Vector2();
	}
	
	public PlayerBullet2d create(SpellType spellType, TextureRegion bulletImage, boolean alive)
	{
		SpellType spell = playerCharacter.getSpellType();
		TextureRegion image = atlas.findRegion(playerCharacter.getSpellType().getSimpleName() + "-shot0");
		alive = false;
		
		return new PlayerBullet2d(spell, image, alive);
	}
	
	public void startPosition(float x, float y)
	{
		x = player2d.getX();
		y = player2d.getY();
		position.set(x, y);
	}
	
	@Override
	public void act(float delta)
	{
		super.act(delta);
		bulletMovement(delta);
	}
	
	private void bulletMovement(float delta)
	{
		if (alive == true)
		{
			velocity.y += BULLET_SPEED * delta;
		}
	}
}
