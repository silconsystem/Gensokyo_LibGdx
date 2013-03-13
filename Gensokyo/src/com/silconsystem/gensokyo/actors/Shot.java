package com.silconsystem.gensokyo.actors;

public enum Shot
{
	SHOT_00	(1),
	SHOT_01 (2),
	SHOT_02 (3),
	SHOT_03 (4),
	SHOT_04 (5);
	
	private final int damage;
	
	private Shot(int damage)
	{
		this.damage = damage;
	}
	
	// retrieve caused damage
	public int getDamage()
	{
		return damage;
	}
}
