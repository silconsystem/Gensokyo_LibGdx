package com.silconsystem.gensokyo.actors;

public enum Reward
{
	ITEM	(25),
	POWER	(75),
	SPECIAL	(100);
	
	private final int credits;
	
	private Reward(int score)
	{
		this.credits = score;
	}
	
	// retrieve credits earned when item is captured
	public int getCredits()
	{
		return credits;
	}
}
