package com.silconsystem.gensokyo.managers;

// import gdx packs
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PreferencesManager
{
	// game constants
	private static final String	PREF_VOLUME = "volume";
	private static final String	PREF_MUSIC_ENABLED = "music.enabled";
	private static final String	PREF_EFFECT_ENABLED = "sound.enabled";
	private static final String	PREFS_NAME = "gensokyo";
	
	public PreferencesManager()
	{
		// ---
	}
	
	protected Preferences getPrefs()
	{
		return Gdx.app.getPreferences(PREFS_NAME);
	}
	
	public boolean isSoundEnabled()
	{
		return getPrefs().getBoolean(PREF_EFFECT_ENABLED, true);
	}
	
	public void setSoundEnabled(boolean soundEffectsEnabled)
	{
		getPrefs().putBoolean(PREF_EFFECT_ENABLED, soundEffectsEnabled);
		getPrefs().flush();
	}
	
	public boolean isMusicEnabled()
	{
		return getPrefs().getBoolean(PREF_MUSIC_ENABLED, true);
	}
	
	public void setMusicEnabled(boolean musicEnabled)
	{
		getPrefs().putBoolean(PREF_MUSIC_ENABLED, musicEnabled);
		getPrefs().flush();
	}
	
	public float getVolume()
	{
		return getPrefs().getFloat(PREF_VOLUME, 0.5f);
	}
	
	public void setVolume(float volume)
	{
		getPrefs().putFloat(PREF_VOLUME, volume);
		getPrefs().flush();
	}
	
}
