package com.silconsystem.gensokyo.actors;

import java.util.Locale;

import android.annotation.SuppressLint;

public enum EnemyType implements Enemy
{
	GREEN_FAIRY	("Green-Fairy", 1, null, false),
	RED_FAIRY	("Red-Fairy", 1, null, false),
	BLUE_FAIRY	("Blue-Fairy", 1, null, false),
	BIG_RED_FAIRY	("Big-Red-Fairy", 2, null, false),
	BIG_GREEN_FAIRY	("Big-Green_Fairy", 2, null, false),
	SANEA_KOTIYA	("Sanea-Kotiya", 3, null, true),
	NAZRIN		("Nazrin", 3, null, true);
	
	private String name;
	private int level;
	private String attackType;
	private boolean isSubBoss;
	
	private EnemyType(String name, int level, String attackType, boolean isBoss)
	{
		// enemy constructor
		this.name = name;
		this.level = level;
		this.attackType = attackType;
		this.isSubBoss = isBoss;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getAttackType()
	{
		return attackType;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public boolean isSubBoss()
	{
		return isSubBoss;
	}
	
	@SuppressLint("DefaultLocale")
	@Override
	public String getSimpleName()
	{
		return name().replaceAll("_", "-").toLowerCase(Locale.US);
	}
}
