package com.silconsystem.gensokyo.screens;

// import gdx packs
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

// import my game packs
import com.silconsystem.gensokyo.GensokyoGame;
import com.silconsystem.gensokyo.actors.Level;
import com.silconsystem.gensokyo.actors.Profile;
import com.silconsystem.gensokyo.actors.scene2d.Player2d;
import com.silconsystem.gensokyo.managers.AudioManager.GensokyoMusic;

public class LevelScreen extends AbstractScreen
{
	private final Profile profile;
	private final Level level;
	
	private Player2d player2d;
	
	String mapname;
	
	public LevelScreen(GensokyoGame gensokyogame, int targetLevelId)
	{
		super(gensokyogame);
		
		profile = gensokyogame.getProfileManager().retrieveProfile();
		level = gensokyogame.getLevelManager().findLevelById(targetLevelId);
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
		
		// create player entity and add to stage
		player2d = Player2d.create(profile.getPlayer(), getGameAtlas());
		player2d.playerStartPosition((stage.getWidth() / 2 - player2d.getWidth() / 2), player2d.getHeight());
		stage.addActor(player2d);
		
		if (level.getId() == 0)
		{
			// set level by id
			Gdx.app.log(GensokyoGame.LOG, "Setting espisode one, levelid: " + level.getId());
		}
		else if (level.getId() == 1)
		{
			// set level by id
			Gdx.app.log(GensokyoGame.LOG, "Setting espisode two, levelid: " + level.getId());
		}
		else if (level.getId() == 2)
		{
			// set level by id
			Gdx.app.log(GensokyoGame.LOG, "Setting espisode two, levelid: " + level.getId());
		} else {
			Gdx.app.log(GensokyoGame.LOG, "Cant find selected levelId, returning to menu");
			gensokyogame.setScreen(new MenuScreen(gensokyogame));
		}
		
		// fade in effect
		stage.getRoot().getColor().a = 0f;
		stage.getRoot().addAction(Actions.fadeIn(0.5f));
	}
}
