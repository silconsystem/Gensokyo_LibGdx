package com.silconsystem.gensokyo.actors;

public class Level
{
	private final int id;
	private String name;
	private boolean completed;
	private Level nextLevel;
	
	public Level(int id)
	{
		this.id = id;
	}
	
	// retrieve object ID
	public int getId()
	{
		return id;
	}
	
	// retrieve level name
	public String getName()
	{
		return name;
	}
	
	// set level name
	public void setName(String name)
	{
		this.name = name;
	}
	
	// retrieve if the level is complete
	public boolean isCompleted()
	{
		return completed;
	}
	
	// set the level status
	public void setCompleted(boolean completed)
	{
		this.completed = completed;
	}
	
	// retrieve the next level
	public Level nextlevel()
	{
		return nextLevel;
	}
	
	// set the next level
	public void setNextLevel(Level nextLevel)
	{
		this.nextLevel = nextLevel;
	}
	
	// check if there is a new level
	public boolean hasNextLevel()
	{
		return (nextLevel != null );
	}
}
