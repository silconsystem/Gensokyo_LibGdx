package com.silconsystem.gensokyo.screens;

// import gdx packs
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

// import game packs
import com.silconsystem.gensokyo.managers.AudioFxManager.GensokyoEffects;
import com.silconsystem.gensokyo.managers.AudioManager.GensokyoMusic;
import com.silconsystem.gensokyo.utils.DefaultActorListener;
import com.silconsystem.gensokyo.GensokyoGame;

// import java util pack
import java.util.Locale;

public class SettingScreen extends AbstractScreen
{	
	private Label volumeValue;
	
	// main settingscreen constructor
	public SettingScreen(GensokyoGame gensokyogame) {
			
			super(gensokyogame);
			
	}
		
	@Override
	public void show()
	{
		super.show();
			
		Table table = super.getTable();
		table.defaults().spaceBottom(30);
		table.columnDefaults(0).padRight(20);
		table.add("Options").colspan(3);
		
		// create labels widget
		/* ---		sound effects checkbox		--- */
		final CheckBox soundEffectsCheckBox = new CheckBox("", getSkin());
		soundEffectsCheckBox.setChecked(gensokyogame.getPreferencesManager().isSoundEnabled());
		soundEffectsCheckBox.addListener(new ChangeListener()
		{
			@Override
			public void changed(
					ChangeEvent event,
					Actor actor)
					{
						boolean enabled = soundEffectsCheckBox.isChecked();
						gensokyogame.getPreferencesManager().setSoundEnabled(enabled);
						gensokyogame.getAudioFxManager().setEnabled(enabled);
						gensokyogame.getAudioFxManager().play(GensokyoEffects.SELECT);
					}
		});
		table.row();
		table.add("Sound Effects");
		table.add(soundEffectsCheckBox).colspan(2).left();
		
		/* ---		music checkbox		--- */
		final CheckBox musicCheckBox = new CheckBox("", getSkin());
		musicCheckBox.setChecked(gensokyogame.getPreferencesManager().isMusicEnabled());
		musicCheckBox.addListener(new ChangeListener()
		{
			@Override
			public void changed(
					ChangeEvent event,
					Actor actor)
					{
						boolean enabled = musicCheckBox.isChecked();
						gensokyogame.getPreferencesManager().setMusicEnabled(enabled);
						gensokyogame.getAudioManager().setEnabled(enabled);
						gensokyogame.getAudioFxManager().play(GensokyoEffects.SELECT);
						
						// if music was disabled, start playing menu theme
						if (enabled) gensokyogame.getAudioManager().play(GensokyoMusic.MENU_MUSIC);
					}
		});
		table.row();
		table.add("Music");
		table.add(musicCheckBox).colspan(2).left();
		
		/* ---		volume slider
		 * 
		 * 		range [0,0] - [0,1]
		 * 		step 0.1f
		 */
		Slider volumeSlider = new Slider(0f, 1f, 0.1f, false, getSkin());
		volumeSlider.setValue(gensokyogame.getPreferencesManager().getVolume());
		volumeSlider.addListener(new ChangeListener()
		{
			@Override
			public void changed(
					ChangeEvent event,
					Actor actor)
					{
						float value = ((Slider) actor).getValue();
						gensokyogame.getPreferencesManager().setVolume(value);
						gensokyogame.getAudioManager().setVolume(value);
						gensokyogame.getAudioFxManager().setVolume(value);
						updateVolumeLabel();
					}
		});
		
		// create volume label
		volumeValue = new Label("", getSkin());
		updateVolumeLabel();
		
		// volume row
		table.row();
		table.add("Volume");
		table.add(volumeSlider);
		table.add(volumeValue).width(40);
		
		// register 'back' button
		TextButton backButton = new TextButton("Back to main menu", getSkin());
		backButton.addListener(new DefaultActorListener()
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
						gensokyogame.getAudioFxManager().play(GensokyoEffects.SELECT);
						gensokyogame.setScreen(new MenuScreen(gensokyogame));
					}
		});
		table.row();
		table.add(backButton).size(250, 60).colspan(3);
		
	}
	
	/***
	 * 		updater for the volume label next to the slider widget
	 ***/
	private void updateVolumeLabel()
	{
		float volume = (gensokyogame.getPreferencesManager().getVolume() * 100);
		volumeValue.setText(String.format(Locale.US, "%1.0f%%", volume));
	}
}
