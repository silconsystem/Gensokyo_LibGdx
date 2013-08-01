package com.silconsystem.gensokyo.actors;

// import java utils
import java.util.HashMap;
import java.util.Map;

// import gdx packs
import android.annotation.SuppressLint;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

// import my game packs
import com.silconsystem.gensokyo.GensokyoGame;
import com.silconsystem.gensokyo.utils.TextUtils;

@SuppressLint("UseSparseArrays")
public class Profile implements Serializable
{
	private int currentLevelId;
	private int credits;
	private Map<Integer, Integer> highScores;
	private PlayerCharacter playerCharacter;
	
	public Profile()
	{
		if (GensokyoGame.DEV)
		{
			credits = 1000000;
		} else {
			credits = 10000;
		}
		highScores = new HashMap<Integer, Integer>();
		playerCharacter = new PlayerCharacter();
		playerCharacter.install(PlayerModel.MARISA_KIRISAME);
		playerCharacter.install(SpellType.SUPER_SHORTWAVE);
		playerCharacter.install(Shield.SPHERE_SHIELD);
	}
	
	// retrieve the current level ID
	public int getCurrentLevelId()
	{
		return currentLevelId;
	}

	// retrieve the highscores for each level (levelID -> highScores)
	public Map<Integer, Integer> getHighScores()
	{
		return highScores;
	}
	
	// get scores for current level
	public int getHighScore(int levelId)
	{
		if (highScores == null) return 0;
		Integer highScore = highScores.get(levelId);
		return (highScore == null ? 0 : highScore);
	}
	
	// notify score on current level, return true if it is new highscore
	public boolean notifyScore(int levelId, int score)
	{
		if (score > getHighScore(levelId))
		{
			highScores.put(levelId, score);
			return true;
		}
		return false;
	}
	
	// retrieve player credit
	public int getCredits()
	{
		return credits;
	}
	
	// retrieve the credit amount as text
	public String getCreditsAsText()
	{
		return TextUtils.creditStyle(credits);
	}
	
	// retrieve current player config
	public PlayerCharacter getPlayer()
	{
		return playerCharacter;
	}
	
	// checks if items can be bought
	public boolean canBuy(PlayerItem playerItem)
	{
		if (playerCharacter.contains(playerItem))
		{
			return false;
		}
		if (playerItem.getPrice() > credits)
		{
			return false;
		}
		return true;
	}
	
	//  get the player item
	public boolean buy(PlayerItem playerItem)
	{
		if (canBuy(playerItem))
		{
			Gdx.app.log(GensokyoGame.LOG, "Buying item: " + playerItem);
			playerCharacter.install(playerItem);
			credits -= playerItem.getPrice();
			Gdx.app.log(GensokyoGame.LOG, "Credits Available: " + credits);
			return true;
		} else {
			Gdx.app.log(GensokyoGame.LOG, "Not enough credits to buy item: " + playerItem);
			return false;
		}
	}
	
	/***
	 * 		Implement Json Serializable
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData)
	{
		// read basic properties
		currentLevelId = json.readValue("currentLevelId", Integer.class, jsonData);
		credits = json.readValue("credits", Integer.class, jsonData);
		
		// change Json Hashmap default formatted strings to integers (levelId)
		Map<String, Integer> highScores = json.readValue("highScores", HashMap.class, Integer.class, jsonData);
		for (String levelIdAsString : highScores.keySet())
		{
			int levelId = Integer.valueOf(levelIdAsString);
			Integer highScore = highScores.get(levelIdAsString);
			this.highScores.put(levelId, highScore);
		}
		
		// read the playerCharacter
		playerCharacter = json.readValue("playerCharacter", PlayerCharacter.class, jsonData);
		
	}

	@Override
	public void write(Json json)
	{
		json.writeValue("currentLevelId", currentLevelId);
		json.writeValue("credits", credits);
		json.writeValue("highScores", highScores);
		json.writeValue("playerCharacter", playerCharacter);
		
	}
}
