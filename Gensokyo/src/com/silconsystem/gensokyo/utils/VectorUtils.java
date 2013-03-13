package com.silconsystem.gensokyo.utils;

// import math utils
import com.badlogic.gdx.math.Vector2;

public class VectorUtils
{
	/**
	 * 	perform check if vector X coord is inside range
	 * 	return true, false otherwise
	 */
	public static boolean adjustByRangeX(Vector2 vector, float min, float max)
	{
		if (vector.x < min)
		{
			vector.x = min;
			return true;
		}
		else if (vector.x > max)
		{
			vector.x = max;
			return true;
		}
		return false;
	}
	
	/**
	 * 	perform check if vector Y coord is inside range
	 * 	return true, false otherwise
	 */
	public static boolean adjustByRangeY(Vector2 vector, float min, float max)
	{
		if (vector.y < min)
		{
			vector.y = min;
			return true;
		}
		else if (vector.y > max)
		{
			vector.y = max;
			return true;
		}
		return false;
	}
	
	/**
	 * 	check if coords are within [xMin, xMax] range
	 * 	adjusting them if needed
	 * 	returns true if atleast one value was changed
	 * 	false otherwise
	 */
	public static boolean adjustByRange(
			Vector2 vector,
			float xMin,
			float xMax,
			float yMin,
			float yMax)
			{
				boolean modified = false;
				if (adjustByRangeX(vector, xMin, xMax)) modified = true;
				if (adjustByRangeY(vector, yMin, yMax)) modified = true;
				return modified;
			}
	
	/**
	 * 	check if coords are within [min, max] range
	 * 	adjusting them if needed
	 * 	returns true if atleast one value was changed
	 * 	false otherwise
	 */
	public static boolean adjustByRange(
			Vector2 vector,
			float min,
			float max)
			{
				return adjustByRange(vector, min,max, min, max);
			}
	
	/**
	 * 	uses given value when vector coords are <= specified radius value
	 */
	public static boolean adjustDeadZone(
			Vector2 vector,
			float radius,
			float adjustedValue)
			{
				if (vector.len() <= radius)
				{
					vector.x = adjustedValue;
					vector.y = adjustedValue;
					return true;
				}
				return false;
			}
}
