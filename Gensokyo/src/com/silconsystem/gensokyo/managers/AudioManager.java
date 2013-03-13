package com.silconsystem.gensokyo.managers;

// import gdx packs
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;
import com.silconsystem.gensokyo.GensokyoGame;

public class AudioManager implements Disposable
{
	public enum GensokyoMusic
	{
		MENU_MUSIC("audio/music/survival.mp3"),
		LEVEL_00_MUSIC("audio/music/holocaust.mp3"),
		LEVEL_01_MUSIC("audio/music/hesitation.mp3");
		
		private String filename;
		private Music musicresource;
		
		private GensokyoMusic(String filename)
		{
			this.filename = filename;
		}
		
		public String getFileName()
		{
			return filename;
		}
		
		public Music getMusicResource()
		{
			return musicresource;
		}
		
		public void setMusicResource(Music musicNowPlaying)
		{
			this.musicresource = musicNowPlaying;
		}
	}
	
	// variable to hold the playing background music
	private GensokyoMusic musicNowPlaying;
	
	// default music volume variable
	private float volume = 1f;
	
	// music enabled bool, true by default
	private boolean enabled = true;
	
	// music manager constructor
	public AudioManager()
	{
		// ---
	}
	
	/*** 
	 * 		Audio player methods
	 * 		music already playing should stop automaticly
	 ***/
	public void play(GensokyoMusic music)
	{
		// check enabled bool
		if (!enabled) return;
		
		// check if the music is playing
		if (musicNowPlaying == music) return;
		
		// log the current song
		Gdx.app.log(GensokyoGame.LOG, "Playing Music: " + music.name());
		
		// stop any playing music
		stop();
		
		// start the stream for the audiofiles
		FileHandle musicfile = Gdx.files.internal(music.getFileName());
		Music musicresource = Gdx.audio.newMusic(musicfile);
		musicresource.setVolume(volume);
		musicresource.setLooping(true);
		musicresource.play();
		
		// set the music streaming
		musicNowPlaying = music;
		musicNowPlaying.setMusicResource(musicresource);
	}
	
	/*** 
	 * 		Stop the music currently playing
	 * 		if there is any playing at the moment 
	 ***/
	public void stop()
	{
		if(musicNowPlaying != null)
		{
			Gdx.app.log(GensokyoGame.LOG, "Stopping currently playing music");
			Music musicresource = musicNowPlaying.getMusicResource();
			musicresource.stop();
			musicresource.dispose();
			musicNowPlaying = null;
		}
	}
	
	/*** 		
	 * 		Set the volume level
	 * 		the level should be inside [0,1] range
	 * 		otherwise throw an exception  
	 ***/
	public void setVolume(float volume)
	{
		// log the volume level change
		Gdx.app.log(GensokyoGame.LOG, "Adjusting music volume to: " + volume);
		
		// check the volume and set level
		if (volume < 0 || volume > 1f)
		{
			throw new IllegalArgumentException("The volume must be set inside the [0,1] range");
		}
		this.volume = volume;
		
		// if music is playing, adjust the volume
		if (musicNowPlaying != null)
		{
			musicNowPlaying.getMusicResource().setVolume(volume);
		}
	}
	
	/*** 
	 * 		Enable or disable the music 
	 ***/
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
		
		// if music is disabled, stop any playing music
		if (!enabled)
		{
			stop();
		}
	}
	
	/*** 
	 * 		Dispose of the music manager 
	 ***/
	public void dispose() {
		Gdx.app.log(GensokyoGame.LOG, "Disposing music manager");
		stop();
	}

}
