package com.silconsystem.gensokyo.actors;

public interface PlayerItem
{
	// retrieve the player item name
	String getName();
	
	// retrieve simple name representation
	String getSimpleName();
	
	// retrieves price
	int getPrice();
	
	// retrieves price as text
	String getPriceAsText();
}
