package com.silconsystem.gensokyo.actors.scene2d;

//import java utils
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//import gdx packs
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
import com.silconsystem.gensokyo.actors.EnemyType;

public class Enemy2d extends Image
{
	private static final float 	MAX_SPEED = 230;
	private static final float	MAX_ACCELERATION = 10;
	private static final float	MAX_DECELERATION = MAX_ACCELERATION / 2;
	
	private final Vector2 position;
	private final Vector2 velocity;
	private final Vector2 acceleration;
	
	private final Animation enemyBankAnimation;
	
	private float bankAnimationStateTime;
	private Map<TextureRegion, Drawable> bankAnimationDrawables;
	
	private Enemy2d(EnemyType enemyType, Array<AtlasRegion> enemyAnimationFrames)
	{
		super(enemyAnimationFrames.get(0));
		
		this.position = new Vector2();
		this.velocity = new Vector2();
		this.acceleration = new Vector2();
		
		this.enemyBankAnimation = new Animation(0.15f, enemyAnimationFrames);
		
		// acces the banking animation from the animation frames
		this.bankAnimationDrawables = new HashMap<TextureRegion, Drawable>();
		for (AtlasRegion region : enemyAnimationFrames)
		{
			bankAnimationDrawables.put(region, new TextureRegionDrawable(region));
		}
	}
	
	public static Enemy2d create(EnemyType enemyType, TextureAtlas textureAtlas)
	{
		// load regions from texture atlas
		Array<AtlasRegion> regions = textureAtlas.findRegions(enemyType.getSimpleName());
		Iterator<AtlasRegion> regionIterator = regions.iterator();
		while (regionIterator.hasNext())
		{
			if (regionIterator.next().index < 0)
			{
				regionIterator.remove();
			}
		}
		
		return new Enemy2d(enemyType, regions);
	}
}
