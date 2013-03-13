package com.silconsystem.gensokyo.screens;

// static scene2d packs
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

// import gdx packs
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

// import my game packs
import com.silconsystem.gensokyo.GensokyoGame;
import com.silconsystem.gensokyo.managers.AudioManager.GensokyoMusic;

public class SplashScreen extends AbstractScreen
{	
	private Image splashImage;
	String welcomeMessage = "2013 ,R.van Ardenne for LucidVision MultiVerse";
	
	// splashscreen constructor
	public SplashScreen(GensokyoGame gensokyogame)
	{		
		super(gensokyogame);
	}
	
	@Override
	public void show()
	{
		super.show();
		
		// play the menu music theme
		gensokyogame.getAudioManager().play(GensokyoMusic.MENU_MUSIC);
		
		AtlasRegion splashregion = getAtlas().findRegion("splashbackground");
		TextureRegionDrawable splashDrawable = new TextureRegionDrawable(splashregion);
		
		splashImage = new Image(splashDrawable, Scaling.stretch);
		splashImage.setFillParent(true);
		splashImage.getColor().a = 0f;
				
		// configure fade effect
		splashImage.addAction(sequence(fadeIn(0.75f), delay(2.75f), fadeOut(0.75f),
			new Action()
			{
				@Override
				public boolean act(float delta)
				{
					gensokyogame.setScreen(new MenuScreen(gensokyogame));
					return true;
				}
			}));
		stage.addActor(splashImage);
		
		// draw a message
		Table table = super.getTable();
		table.add(welcomeMessage).spaceBottom(GAME_VIEWPORT_HEIGHT / 2);
	}
}
