package com.silconsystem.gensokyo.screens;

// import gdx packs
import com.badlogic.gdx.Gdx;

//import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

// import my game packs
import com.silconsystem.gensokyo.GensokyoGame;
import com.silconsystem.gensokyo.actors.Level;
import com.silconsystem.gensokyo.actors.Profile;
import com.silconsystem.gensokyo.actors.scene2d.ParallaxBackground2d;
import com.silconsystem.gensokyo.actors.scene2d.ParallaxLayer2d;
import com.silconsystem.gensokyo.actors.scene2d.Player2d;
import com.silconsystem.gensokyo.managers.AudioManager.GensokyoMusic;

public class LevelScreen extends AbstractScreen
{
	private final Profile profile;
	private final Level level;
	
	private Player2d player2d;
	
	String mapname;
	private ParallaxBackground2d parallaxBg;
	//private TextureAtlas atlas;
	//private ParticleEffect particle;
	
	public LevelScreen(GensokyoGame gensokyogame, int targetLevelId)
	{
		super(gensokyogame);
		
		profile = gensokyogame.getProfileManager().retrieveProfile();
		level = gensokyogame.getLevelManager().findLevelById(targetLevelId);
		
		// handle unknownn levelId
		if (level.getId() >= 3)
		{
			Gdx.app.log(GensokyoGame.LOG, "Cant find selected levelId, returning to menu");
			gensokyogame.setScreen(new MenuScreen(gensokyogame));
		}
	}
	
	@Override
	protected boolean isGameScreen()
	{
		return true;
	}
	
	@Override
	public void show()
	{
		super.show();
		
		// play level music
		gensokyogame.getAudioManager().play(GensokyoMusic.LEVEL_00_MUSIC);	
		
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("screens/gamescreen/atlas/backgrounds.pack"));
		
		parallaxBg = new ParallaxBackground2d(new ParallaxLayer2d[]
				{
					new ParallaxLayer2d(atlas.findRegion("bg1"), new Vector2(), new Vector2(0, 0), new Vector2(0, 0)),
					new ParallaxLayer2d(atlas.findRegion("bg3"), new Vector2(1.0f, 0.1f), new Vector2(0, MENU_VIEWPORT_HEIGHT - 200), new Vector2(0, 0))
				}, MENU_VIEWPORT_WIDTH, MENU_VIEWPORT_HEIGHT, new Vector2(0, 70));
		
		// create player entity and add to stage
		player2d = Player2d.create(profile.getPlayer(), getGameAtlas());
		player2d.playerStartPosition((stage.getWidth() / 2 - player2d.getWidth() / 2), player2d.getHeight());
		stage.addActor(player2d);
		
		// fade in effect
		stage.getRoot().getColor().a = 0f;
		stage.getRoot().addAction(Actions.fadeIn(0.5f));
	}
	
	
	@Override
	public void render(float delta)
	{
		super.render(delta);
		
		parallaxBg.render(delta);
		stage.draw();
	}
}
