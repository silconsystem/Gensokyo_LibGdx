package com.silconsystem.gensokyo.managers;

// import java utils\
import java.util.ArrayList;
import java.util.List;

// import my game packs
import com.silconsystem.gensokyo.actors.Level;

public class LevelManager
{
	private final List<Level> levels;
	
	public LevelManager()
	{
		// create level 2
		Level level2 = new Level(2);
		level2.setName("Espisode Three");
		
		// create level 1
		Level level1 = new Level(1);
		level1.setName("Espisode Two");
		level1.setNextLevel(level2);
		
		// create level 0 (start level)
		Level level0 = new Level(0);
		level0.setName("espisode One");
		level0.setNextLevel(level1);
		
		// register the levels
		levels = new ArrayList<Level>(3);
		levels.add(level0);
		levels.add(level1);
		levels.add(level2);
	}
	
	// retrieve available levels
	public List<Level> getLevel()
	{
		return levels;
	}
	
	// retrieve level with given id or return null if no level exists
	public Level findLevelById(int id)
	{
		if (id < 0 || id >= levels.size())
		{
			return null;
		}
		return levels.get(id);
	}
}
