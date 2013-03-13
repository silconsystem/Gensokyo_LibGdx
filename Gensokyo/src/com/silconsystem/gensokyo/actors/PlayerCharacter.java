package com.silconsystem.gensokyo.actors;

// import gdx packs
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

// import my game packs
import com.silconsystem.gensokyo.GensokyoGame;

public class PlayerCharacter implements Serializable
{
	private PlayerModel playerModel;
	private SpellType spellType;
	private Shield shield;
	
	public PlayerCharacter()
	{
		// ---
	}
	
	public PlayerModel getPlayerModel()
	{
		return playerModel;
	}
	
	public SpellType getSpellType()
	{
		return spellType;
	}
	
	public Shield getShield()
	{
		return shield;
	}
	
	/***
	 * 		Check for configuration playerItems
	 */
	public boolean contains(PlayerItem playerItem)
	{
		if (playerItem == null) return false;
		return (playerItem.equals(playerModel) || playerItem.equals(spellType) || playerItem.equals(shield));
	}
	
	/***
	 * 		install configured playerItems 
	 */
	public void install(PlayerItem playerItem)
	{
		// log the item install
		Gdx.app.log(GensokyoGame.LOG, "installing player item: " + playerItem);
		if (playerItem instanceof PlayerModel)
		{
			playerModel = (PlayerModel) playerItem;
		}
		else if (playerItem instanceof SpellType)
		{
			spellType = (SpellType) playerItem;
		}
		else if (playerItem instanceof Shield)
		{
			shield = (Shield) playerItem;
		}
		else {
			throw new IllegalArgumentException ("Unknown item: " + playerItem);
		}
	}
	
	/***
	 * 		Implement Json Serializable utility
	 */
	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData)
	{
		playerModel = PlayerModel.valueOf (json.readValue("playerModel", String.class, jsonData));
		spellType = SpellType.valueOf (json.readValue("spellType", String.class, jsonData));
		shield = Shield.valueOf (json.readValue("shield", String.class, jsonData));
	}
	
	@Override
	public void write(Json json)
	{
		json.writeValue("playerModel", playerModel.name());
		json.writeValue("spellType", spellType.name());
		json.writeValue("shield", shield.name());
	}
}
