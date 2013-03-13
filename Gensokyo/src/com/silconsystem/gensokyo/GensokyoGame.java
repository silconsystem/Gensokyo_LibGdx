package com.silconsystem.gensokyo;

// import my game packs
import com.silconsystem.gensokyo.screens.SplashScreen;
import com.silconsystem.gensokyo.screens.MenuScreen;
import com.silconsystem.gensokyo.managers.PreferencesManager;
import com.silconsystem.gensokyo.managers.ProfileManager;
import com.silconsystem.gensokyo.managers.LevelManager;
import com.silconsystem.gensokyo.managers.AudioManager;
import com.silconsystem.gensokyo.managers.AudioFxManager;

// import gdx packs
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.Texture;

public class GensokyoGame extends Game
{
	public static final String LOG = GensokyoGame.class.getSimpleName();
	
	public static final boolean DEV = true;
	
	private FPSLogger fpslogger;
	
	// managers
	private PreferencesManager preferencesManager;
	private ProfileManager profileManager;
	private LevelManager levelManager;
	private AudioManager audioManager;
	private AudioFxManager audioEffectsManager;
	
	public GensokyoGame()
	{
		// --
	}
	
	// manager getters
	public PreferencesManager getPreferencesManager()
	{
		return preferencesManager;
	}
	
	public ProfileManager getProfileManager()
	{
		return profileManager;
	}
	
	public LevelManager getLevelManager()
	{
		return levelManager;
	}
	
	public AudioManager getAudioManager()
	{
		return audioManager;
	}
	
	public AudioFxManager getAudioFxManager()
	{
		return audioEffectsManager;
	}
	
	// screen helper methods
	public SplashScreen getSplashScreen()
	{
		return new SplashScreen(this);
	}
	
	public MenuScreen getMenuScreen()
	{
		return new MenuScreen(this);
	}
	
	@Override
	public void create()
	{							
		Gdx.app.log(GensokyoGame.LOG, "Creating the game");
		fpslogger = new FPSLogger();
		
		// create the preferences manager
		preferencesManager = new PreferencesManager();
		
		// create music manager
		audioManager = new AudioManager();
		audioManager.setVolume(preferencesManager.getVolume());
		audioManager.setEnabled(preferencesManager.isMusicEnabled());
		
		// create the sound effects manager
		audioEffectsManager = new AudioFxManager();
		audioEffectsManager.setVolume(preferencesManager.getVolume());
		audioEffectsManager.setEnabled(preferencesManager.isSoundEnabled());
		
		// create the profile manager
		profileManager = new ProfileManager();
		profileManager.retrieveProfile();
		
		// create the level manager
		levelManager = new LevelManager();
	}
	
	@Override
	public void resize(int width, int height)
	{
		super.resize(width, height);
		Gdx.app.log(GensokyoGame.LOG, "Resizing to: " + width + " x " + height);
		
		// on first resize show splash screen
		if(getScreen() == null)
		{
			setScreen(getSplashScreen());
		}
	}
	
	@Override
	public void render()
	{
		super.render();		
		if (DEV) fpslogger.log();
		Texture.setEnforcePotImages(false);
	}
	
	@Override
	public void pause()
	{
		super.pause();		
		Gdx.app.log(GensokyoGame.LOG, "Pausing Game");
	}
	
	@Override
	public void resume()
	{
		super.resume();		
		Gdx.app.log(GensokyoGame.LOG, "Resume Game");
	}
	
	@Override
	public void setScreen(Screen screen)
	{
		super.setScreen(screen);
		Gdx.app.log(GensokyoGame.LOG, "Setting Screen: " + screen.getClass().getSimpleName());
	}
	
	@Override
	public void dispose()
	{
		super.dispose();
		Gdx.app.log(GensokyoGame.LOG, "Disposing Game");
		
		// dispose sound services
		audioManager.dispose();
		audioEffectsManager.dispose();
	}
		
}
