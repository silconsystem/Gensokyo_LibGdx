package com.silconsystem.gensokyo.actors;

// import java utils
import java.util.Locale;

// import my utils
import com.silconsystem.gensokyo.utils.TextUtils;

public enum SpellType implements PlayerItem
{
	SUPER_SHORTWAVE	("Super-Shortwave", 500, Shot.SHOT_00),
	HOMING_AMULET	("Homing-Amulet", 1000, Shot.SHOT_01),
	KITCHEN_KNIVES	("Kitchen-Knives", 1200, Shot.SHOT_02),
	SWORD_SLASH	("Sword-Slash", 1400, Shot.SHOT_03),
	VAMPIRE_FANGS	("Vampire-Fangs", 1600, Shot.SHOT_04);
	
	private final String name;
	private final int price;
	private final Shot shot;
	
	private SpellType(String name, int price, Shot shot)
	{
		this.name = name;
		this.price = price;
		this.shot = shot;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public String getSimpleName()
	{
		return name().replaceAll("_", "-").toLowerCase(Locale.US);
	}

	public int getPrice()
	{
		return price;
	}

	@Override
	public String getPriceAsText()
	{
		return TextUtils.creditStyle(price);
	}
	
	/*
	 * 		retrieve shot fired by weapon type
	 */
	public Shot getShot()
	{
		return shot;
	}
	
	@Override
	public String toString()
	{
		return String.format(Locale.US, "%s (%s) - Damage: %s", name, getPriceAsText(), shot.getDamage());
	}
}
