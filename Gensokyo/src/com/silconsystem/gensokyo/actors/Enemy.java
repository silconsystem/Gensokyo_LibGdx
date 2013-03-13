package com.silconsystem.gensokyo.actors;

public enum Enemy
{
	BLUE_FAIRY	(null, 50),
	RED_FAIRY	(null, 100),
	GREEN_FAIRY	(null, 150);
	
	private final Shot shot;
	private final int itemValue;
	
	private Enemy(Shot shot, int score)
	{
		this.shot = shot;
		this.itemValue = score;
	}
	
	// retrieve shot fired by enemy
	public Shot getShot()
	{
		return shot;
	}
	
	// retrieve score gained by kills
	public int getItemValue()
	{
		return itemValue;
	}	
}
