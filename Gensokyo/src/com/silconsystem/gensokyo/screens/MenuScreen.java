package com.silconsystem.gensokyo.screens;

//import gdx packs
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.silconsystem.gensokyo.utils.DefaultActorListener;

// import my game packs
import com.silconsystem.gensokyo.GensokyoGame;
import com.silconsystem.gensokyo.managers.AudioFxManager.GensokyoEffects;

public class MenuScreen extends AbstractScreen
{
	// menuscreen constructor
	public MenuScreen(GensokyoGame gensokyogame)
	{			
		super(gensokyogame);			
	}
	
	@Override
	public void show()
	{
		super.show();
		
		// get table actor
		Table table = super.getTable();
		table.add("Welcome to Gensokyo's Night Time").spaceBottom(50);
		table.row();
		
		// register the button to start the game
		TextButton startGameButton = new TextButton("Start Game", getSkin());
		startGameButton.addListener(new DefaultActorListener()
		{
			@Override
			public void touchUp(
					InputEvent event,
					float x,
					float y,
					int pointer,
					int button)
					{
						super.touchUp(event, x, y, pointer, button);
						gensokyogame.getAudioFxManager().play(GensokyoEffects.OK);
						gensokyogame.setScreen(new StartGameScreen(gensokyogame));
					}
		});
		table.add(startGameButton).size(300, 60).uniform().spaceBottom(10);
		table.row();
		
		// register the options button
		TextButton settingsButton = new TextButton("Settings", getSkin());
		settingsButton.addListener(new DefaultActorListener()
		{
			@Override
			public void touchUp(
					InputEvent event,
					float x,
					float y,
					int pointer,
					int button)
					{
						super.touchUp(event, x, y, pointer, button);
						gensokyogame.getAudioFxManager().play(GensokyoEffects.OK);
						gensokyogame.setScreen(new SettingScreen(gensokyogame));
					}
		});
		table.add(settingsButton).uniform().fill().spaceBottom(10);
		table.row();
		
		// register the highscores button
		TextButton highScoreButton = new TextButton("HighScores", getSkin());
		highScoreButton.addListener(new DefaultActorListener()
		{
			@Override
			public void touchUp(
					InputEvent event,
					float x,
					float y,
					int pointer,
					int button)
					{
						super.touchUp(event, x, y, pointer, button);
						gensokyogame.getAudioFxManager().play(GensokyoEffects.OK);
						gensokyogame.setScreen(new HighScores(gensokyogame));
					}
		});
		table.add(highScoreButton).uniform().fill();
	}
}
