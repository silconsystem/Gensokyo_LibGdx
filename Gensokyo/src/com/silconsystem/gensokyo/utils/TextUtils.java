package com.silconsystem.gensokyo.utils;

// import java packs
import java.util.Locale;
import java.text.NumberFormat;

public class TextUtils
{
	private static final NumberFormat FORMATTER;
	
	static
	{
		FORMATTER = NumberFormat.getCurrencyInstance(Locale.US);
		FORMATTER.setMaximumFractionDigits(0);
		FORMATTER.setMaximumFractionDigits(0);
	}
	
	private TextUtils()
	{
		// ---
	}
	
	public static String creditStyle(int credits)
	{
		return FORMATTER.format(credits);
	}
}
