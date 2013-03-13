package com.silconsystem.gensokyo.actors;

// import java utils
import java.util.Locale;

// import game uitls
import android.annotation.SuppressLint;
import com.silconsystem.gensokyo.utils.TextUtils;

public enum PlayerModel implements PlayerItem
{
	MARISA_KIRISAME	("Marisa-Kirisame", 6000, 1),
	REIMU_HAKUREI	("Reimu-Hakurei", 12000, 2),
	SAKUYA_IZAYOI	("Sakukya-Izayoi", 16000, 3),
	REMILIA_SCARLET	("Remilia-Scarlet", 18000, 4),
	YOUMU_KONPAKU	("Youmu-Konpaku", 20000, 5);
	
	private final String name;
	private final int price;
	private final int firingCapacity;
	
	private PlayerModel(String name, int price, int firingCapacity)
	{
		this.name = name;
		this.price = price;
		this.firingCapacity = firingCapacity;
	}
	
	public String getName()
	{
		return name;
	}
	
	@SuppressLint("DefaultLocale")
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
	 * 		return firing capacity for each player
	 * 		1 eqauls 1 shot each 1/4 sec
	 */
	public int getFiringCapacity()
	{
		return firingCapacity;
	}
	
	@Override
	public String toString()
	{
		return String.format(Locale.US, "%s (%s) - Firing: %d", name, getPriceAsText(), firingCapacity);
	}
	
}
