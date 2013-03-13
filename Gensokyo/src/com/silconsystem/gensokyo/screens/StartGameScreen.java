package com.silconsystem.gensokyo.screens;

// import gdx packs
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

// import my game packs
import com.silconsystem.gensokyo.GensokyoGame;
import com.silconsystem.gensokyo.actors.SpellType;
import com.silconsystem.gensokyo.actors.PlayerItem;
import com.silconsystem.gensokyo.actors.Profile;
import com.silconsystem.gensokyo.actors.Shield;
import com.silconsystem.gensokyo.actors.PlayerCharacter;
import com.silconsystem.gensokyo.actors.PlayerModel;
import com.silconsystem.gensokyo.managers.AudioFxManager.GensokyoEffects;
import com.silconsystem.gensokyo.managers.AudioManager.GensokyoMusic;
import com.silconsystem.gensokyo.utils.DefaultActorListener;

public class StartGameScreen extends AbstractScreen
{
	private Profile profile;
	private PlayerCharacter playerCharacter;
	
	private TextButton espisode1Button;
	private TextButton espisode2Button;
	private TextButton espisode3Button;
	private SelectBox playerModelSelectBox;
	private SelectBox spellTypeSelectBox;
	private SelectBox shieldSelectBox;
	private Label creditsLabel;
	
	private Image playerModelImage;
	private Image spellTypeImage;
	private Image shieldImage;
	
	private levelClickListener levelClickListener;
	private ItemSelectionListener itemSelectionListener;
	
	public StartGameScreen(GensokyoGame gensokyogame)
	{
		super(gensokyogame);
		
		levelClickListener = new levelClickListener();
		itemSelectionListener = new ItemSelectionListener();
	}
	
	@Override
	public void show()
	{
		super.show();
		
		// play menu music
		gensokyogame.getAudioManager().play(GensokyoMusic.MENU_MUSIC);
		
		// retrieve default table actor
		Table table = super.getTable();
		table.defaults().spaceBottom(20);
		table.columnDefaults(0).padRight(20);
		table.columnDefaults(4).padLeft(10);
		table.add("Start Game").colspan(5);
		
		// retrieve table layout
		profile = gensokyogame.getProfileManager().retrieveProfile();
		playerCharacter = profile.getPlayer();
		
		// create level buttons
		table.row();
		table.add("Espisodes");
		
		espisode1Button = new TextButton("Espisode One", getSkin());
		espisode1Button.addListener(levelClickListener);
		table.add(espisode1Button).fillX().padRight(10);
		
		espisode2Button = new TextButton("Espisode Two", getSkin());
		espisode2Button.addListener(levelClickListener);
		table.add(espisode2Button).fillX().padRight(10);
		
		espisode3Button = new TextButton("Espisode Three", getSkin());
		espisode3Button.addListener(levelClickListener);
		table.add(espisode3Button).fillX();
		
		// create item select boxes
		//		--- player select
		playerModelSelectBox = new SelectBox(PlayerModel.values(), getSkin());
		playerModelSelectBox.addListener(itemSelectionListener);
		playerModelImage = new Image();
		
		table.row();
		table.add("Player");
		table.add(playerModelSelectBox).fillX().colspan(3);
		table.add(playerModelImage);
		
		//		--- spell select
		spellTypeSelectBox = new SelectBox(SpellType.values(), getSkin());
		spellTypeSelectBox.addListener(itemSelectionListener);
		spellTypeImage = new Image();
		
		table.row();
		table.add("Spell");
		table.add(spellTypeSelectBox).fillX().colspan(3);
		table.add(spellTypeImage);
		
		//		--- shield select
		shieldSelectBox = new SelectBox(Shield.values(), getSkin());
		shieldSelectBox.addListener(itemSelectionListener);
		shieldImage = new Image();
		
		table.row();
		table.add("Shield");
		table.add(shieldSelectBox).fillX().colspan(3);
		table.add(shieldImage);
		
		// create credits label
		creditsLabel = new Label(profile.getCreditsAsText(), getSkin());
		table.row();
		table.add("Credits");
		table.add(creditsLabel).left().colspan(4);
		
		// register back button
		TextButton backButton = new TextButton("Back to main menu", getSkin());
		backButton.addListener(new DefaultActorListener()
		{
			public void touchUp(
					InputEvent event,
					float x,
					float y,
					int pointer,
					int button)
					{
						gensokyogame.getAudioFxManager().play(GensokyoEffects.SELECT);
						gensokyogame.setScreen(new MenuScreen(gensokyogame));
					}
		});
		table.row();
		table.add(backButton).size(250, 60).colspan(5);
		
		// set select box inititial values
		updateValues();
	}
	
	private void updateValues()
	{
		// select boxes
		playerModelSelectBox.setSelection(playerCharacter.getPlayerModel().ordinal());
		spellTypeSelectBox.setSelection(playerCharacter.getSpellType().ordinal());
		shieldSelectBox.setSelection(playerCharacter.getShield().ordinal());
		
		// drawables
		TextureRegion playerModel = getAtlasTwo().findRegion(playerCharacter.getPlayerModel().getSimpleName());
		TextureRegion spellType = getAtlasTwo().findRegion(playerCharacter.getSpellType().getSimpleName());
		TextureRegion shield = getAtlasTwo().findRegion(playerCharacter.getShield().getSimpleName());
		
		// images
		playerModelImage.setDrawable(new TextureRegionDrawable(playerModel));
		spellTypeImage.setDrawable(new TextureRegionDrawable(spellType));
		shieldImage.setDrawable(new TextureRegionDrawable(shield));
	}
	
	/*
	 * 		level button listener	
	 */
	public class levelClickListener extends DefaultActorListener
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
					
					// find target level id
					int targetLevelId = - 1;
					Actor actor = event.getListenerActor();
					if (actor == espisode1Button)
					{
						targetLevelId = 0;
					}
					else if (actor == espisode2Button)
					{
						targetLevelId = 1;
					}
					else if (actor == espisode3Button)
					{
						targetLevelId = 2;
					}
					else {
						return;
					}
					
					// check for level id current level
					if (profile.getCurrentLevelId() >= targetLevelId)
					{
						Gdx.app.log(GensokyoGame.LOG, "Starting level: " + targetLevelId);
						gensokyogame.setScreen(new LevelScreen(gensokyogame, targetLevelId));
					} else {
						Gdx.app.log(GensokyoGame.LOG, "Unable to start level: " + targetLevelId);
					}
				}
	}
	
	/*
	 * 		item selectbox listener
	 */
	private class ItemSelectionListener extends ChangeListener
	{
		@Override
		public void changed(ChangeEvent event, Actor actor)
		{
			// find selected items
			PlayerItem selectedItem = null;
			int selectedIndex = ((SelectBox) actor).getSelectionIndex();
			if (actor == playerModelSelectBox)
			{
				selectedItem = PlayerModel.values()[selectedIndex];
			}
			else if (actor == spellTypeSelectBox)
			{
				selectedItem = SpellType.values()[selectedIndex];
			}
			else if (actor == shieldSelectBox)
			{
				selectedItem = Shield.values()[selectedIndex];
			} else {
				return;
			}
			
			// if player already equipped the item, cant buy it
			if (playerCharacter.contains(selectedItem)) return;
			
			// try n buy
			if (profile.buy(selectedItem))
			{
				creditsLabel.setText(profile.getCreditsAsText());
			}
			
			// update widgets
			updateValues();
		}
	}
}
