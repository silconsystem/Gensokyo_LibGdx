package com.silconsystem.gensokyo.actors;

import java.util.Locale;

import android.annotation.SuppressLint;

public enum EnemyAttackType implements Enemy
{
	HOMING_TYPE	("Homing-Type", 10),
	CIRCLE_TYPE	("Circle-Type", 10),
	THREE_WAY	("Three-Way", 10);
	
	private String name;
	private int damage;
	
	private EnemyAttackType(String name, int damage)
	{
		this.name = name;
		this.damage = damage;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	@SuppressLint("DefaultLocale")
	@Override
	public String getSimpleName()
	{
		return name().replaceAll("_", "-").toLowerCase(Locale.US);
	}
}
