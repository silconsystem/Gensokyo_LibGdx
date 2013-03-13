package com.silconsystem.gensokyo.managers;

// import gdx packs
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;

// import my game packs
import com.silconsystem.gensokyo.GensokyoGame;
import com.silconsystem.gensokyo.actors.Profile;

public class ProfileManager
{	
	// location of the json profile file
	private static final String PROFILE_DATA_FILE = "data/profile-v1.json";
	
	// loaded profile (can be null)
	private Profile profile;
	
	public ProfileManager()
	{
		// ---
	}
	
	// retrieve the profile, create if null
	public Profile retrieveProfile()
	{
		// profile data handler
		FileHandle profileDataFile = Gdx.files.local(PROFILE_DATA_FILE);
		Gdx.app.log(GensokyoGame.LOG, "retrieving profile from: " + profileDataFile.path());
		
		// if already loaded, return it
		if (profile != null)
		{
			return profile;
		}
		
		// create the json utility
		Json json = new Json();
		
		// check if the data file exists
		if (profileDataFile.exists())
		{
			try {
				String profileAsText = profileDataFile.readString().trim();
				
				// decode the file
				if (profileAsText.matches("^[A-Za-z0-9/+=]+$"))
				{
					Gdx.app.log(GensokyoGame.LOG, "Profile id base64 encoded");
					profileAsText = Base64Coder.decodeString(profileAsText);
				}
				
				// restore state
				profile = json.fromJson(Profile.class, profileAsText);
			} catch (Exception e) {
				Gdx.app.log(GensokyoGame.LOG, "Unable to parse existing profile data file", e);
				
				// automatic create new profile
				profile = new Profile();
				persist(profile);
			}
		} else {
			// create new prifile data file
			profile = new Profile();
			persist(profile);
		}
		return profile;
	}
	
	// persist the current profile
	protected void persist(Profile profile)
	{
		// create the handle for profile data file
		FileHandle profileDataFile = Gdx.files.local(PROFILE_DATA_FILE);
		Gdx.app.log(GensokyoGame.LOG, "Persisting profile in: " + profileDataFile.path());
		
		Json json = new Json();
		
		// convert profile to text
		String profileAsText = json.toJson(profile);
		
		// encode the text
		if (!GensokyoGame.DEV)
		{
			profileAsText = Base64Coder.encodeString(profileAsText);
		}
		
		// write the profile data file
		profileDataFile.writeString(profileAsText, false);
	}
	
	// persist the players profile
	public void persist()
	{
		if (profile != null)
		{
			persist(profile);
		}
	}
}
