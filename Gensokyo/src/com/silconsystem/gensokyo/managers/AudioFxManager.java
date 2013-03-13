package com.silconsystem.gensokyo.managers;

// import gdx packs
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;

// import my game packs
import com.silconsystem.gensokyo.GensokyoGame;
import com.silconsystem.gensokyo.managers.AudioFxManager.GensokyoEffects;
import com.silconsystem.gensokyo.utils.LRUCache;
import com.silconsystem.gensokyo.utils.LRUCache.CacheEntryRemovedListener;

public class AudioFxManager implements CacheEntryRemovedListener<GensokyoEffects, Sound>, Disposable
{
	/***
	 * 		Available sound files
	 ***/
	public enum GensokyoEffects
	{
		SELECT("audio/effects/se_select00.mp3"),
		CANCEL("audio/effects/se_cancel00.mp3"),
		OK("audio/effects/se_ok00.mp3");
		
		private final String filename;
		
		private GensokyoEffects(String filename)
		{
			this.filename = filename;
		}
		
		public String getFileName()
		{
			return filename;
		}
	}
	
	// default sound volume variable
	private float volume = 1f;
	
	// sound enabled bool, true by default
	private boolean enabled = true;
	
	// sound cache
	private final LRUCache<GensokyoEffects, Sound> soundCache;
	
	/***
	 * 		Create sound effects manager
	 ***/
	public AudioFxManager()
	{
		soundCache = new LRUCache<AudioFxManager.GensokyoEffects, Sound>(10);
		soundCache.setEntryRemovedListener(this);
	}
	
	/***
	 * 		Play specified sound
	 ***/
	public void play(GensokyoEffects sound)
	{
		// check if sound is enabled
		if (!enabled) return;
		
		Sound soundToPlay = soundCache.get(sound);
		if (soundToPlay == null)
		{
			FileHandle soundFile = Gdx.files.internal(sound.getFileName());
			soundToPlay = Gdx.audio.newSound(soundFile);
			soundCache.add(sound, soundToPlay);
		}
		
		// play the sound
		Gdx.app.log(GensokyoGame.LOG, "Playing sound: " + sound.name());
		soundToPlay.play(volume);
	}
	
	/***
	 * 		Set the sound effects volume
	 * 		the volume must be in the range [0,1]
	 ***/
	public void setVolume(float volume)
	{
		// log changing the effects volume
		Gdx.app.log(GensokyoGame.LOG, "Changing effect volume to: " + volume);
		
		// check and set the new volume
		if(volume < 0 || volume > 1f)
		{
			throw new IllegalArgumentException("The volume must be set inside the range: [0,1]");
		}
		this.volume = volume;
	}
	
	/***
	 * 		Enable or disable the sound
	 ***/
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}
	
	/***
	 * 		Implement EntryRemovedListener
	 ***/
	@Override
	public void notifyEntryRemoved(GensokyoEffects key, Sound value)
	{
		Gdx.app.log(GensokyoGame.LOG, "Disposing sound: " + key.name());
		value.dispose();
	}

	@Override
	public void dispose() {
		Gdx.app.log(GensokyoGame.LOG, "Disposing AudioFxManager");
		for (Sound sound : soundCache.retrieveAll())
		{
			sound.stop();
			sound.dispose();
		}
	}	
}
