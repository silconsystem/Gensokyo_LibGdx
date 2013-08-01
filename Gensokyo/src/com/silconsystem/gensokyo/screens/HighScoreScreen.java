package com.silconsystem.gensokyo.screens;

// import gdx packs
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

//import my packs
import com.silconsystem.gensokyo.GensokyoGame;
import com.silconsystem.gensokyo.actors.Profile;
import com.silconsystem.gensokyo.managers.AudioFxManager.GensokyoEffects;
import com.silconsystem.gensokyo.utils.DefaultActorListener;

/**
 * Simple HighScore Screen
 * @author silconsys
 *
 */

public class HighScoreScreen extends AbstractScreen 
{
	public HighScoreScreen(GensokyoGame gensokyogame)
	{
		super(gensokyogame);
	}
	
	@Override
	public void show()
	{
		super.show();
		Profile profile = gensokyogame.getProfileManager().retrieveProfile();
		
		// retrieve the default table actor
		Table table = super.getTable();
		table.defaults().spaceBottom(30);
		table.add("High Scores").colspan(2);
		
		// espisode one HighScore
		String level1Highscore = String.valueOf(profile.getHighScore(0));
		Label espisode1Highscore = new Label(level1Highscore, getSkin());
		table.row();
		table.add("Espisode One");
		table.add(espisode1Highscore);
		
		String level2Highscore = String.valueOf(profile.getHighScore(1));
		Label espisode2Highscore = new Label(level2Highscore, getSkin());
		table.row();
		table.add("Espisode Two").center();
		table.add(espisode2Highscore);
		
		String level3Highscore = String.valueOf(profile.getHighScore(2));
		Label espisode3Highscore = new Label(level3Highscore, getSkin());
		table.row();
		table.add("Espisode Three");
		table.add(espisode3Highscore);
		
		// register back button
		TextButton backButton = new TextButton("Back to main menu",getSkin());
		
		backButton.addListener(new DefaultActorListener()
		{
			@Override
			public void touchUp(
					InputEvent event,
					float x,
					float y,
					int pointer,
					int button )
			{
				super.touchUp(event, x, y, pointer, button);
				gensokyogame.getAudioFxManager().play(GensokyoEffects.OK);
				gensokyogame.setScreen(new MenuScreen(gensokyogame));
			}
		});
		
		TextButton exitButton = new TextButton("Exit Game",getSkin());
		
		exitButton.addListener(new DefaultActorListener()
		{
			@Override
			public void touchUp(
					InputEvent event,
					float x,
					float y,
					int pointer,
					int button )
			{
				super.touchUp(event, x, y, pointer, button);
				gensokyogame.getAudioFxManager().play(GensokyoEffects.OK);
				gensokyogame.dispose();
				Gdx.app.exit();
			}
		});
		
		table.row();
		table.add(backButton).size(250, 40).colspan(2);
		
		table.row();
		table.add(exitButton).size(250, 40).colspan(2);
		
	}
}
