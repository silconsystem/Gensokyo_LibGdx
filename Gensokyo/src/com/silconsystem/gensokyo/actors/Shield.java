package com.silconsystem.gensokyo.actors;

// import java utils
import java.util.Locale;

// import my utils
import com.silconsystem.gensokyo.utils.TextUtils;

public enum Shield implements PlayerItem
{
	SPHERE_SHIELD	("Sphere-Shield", 100, 1),
	SQUARE_SHIELD	("Square-Shield", 250, 2),
	YINYANG_SHIELD	("YinYang-Shield", 500, 3),
	WING_SHIELD	("Wing-Shield", 1000, 4),
	SLASH_SHIELD	("Slash-Shield", 2000, 5);
	
	private final String name;
	private final int price;
	private final int armor;
	
	private Shield(String name, int price, int armor)
	{
		this.name = name;
		this.price = price;
		this.armor = armor;
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
	 * 		retrieves the armor level
	 * 		1 equals 10% less damge received
	 */
	public int getArmor()
	{
		return armor;
	}
	
	@Override
	public String toString()
	{
		return String.format(Locale.US, "%s (%s) - Armor: %d", name, getPriceAsText(), armor);
	}
}
