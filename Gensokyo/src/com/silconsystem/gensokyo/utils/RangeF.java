package com.silconsystem.gensokyo.utils;

// import java packs
import java.io.Serializable;

public class RangeF implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1942868025399700239L;
	private final float start;
	private final float end;
	
	public RangeF(float start, float end)
	{
		if (! isValid(start, end))
		{
			throw new IllegalArgumentException("Invalid range: [" + start + "," + end +"]");
		}
		this.start = start;
		this.end = end;
	}
	
	public float getStart()
	{
		return start;
	}
	
	public float getEnd()
	{
		return end;
	}
	
	public float getMean()
	{
		return ((start + end) / 2);
	}
	
	public boolean isInside(float value)
	{
		return (value >= start && value <= end);
	}
	
	public boolean isInside(RangeF range)
	{
		if (range == null) return false;
		return (start >= range.start && end <= range.end);
	}
	
	public boolean isOutside(float value)
	{
		return !isInside(value);
	}
	
	public boolean isOutside(RangeF range)
	{
		return !isInside(range);
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(end);
		result = prime * result + Float.floatToIntBits(start);
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj ==  null) return false;
		if (getClass() != obj.getClass()) return false;
		RangeF other = (RangeF) obj;
		if (Float.floatToIntBits(end) != Float.floatToIntBits(other.end)) return false;
		if (Float.floatToIntBits(start) != Float.floatToIntBits(other.start)) return false;
		return true;
	}
	
	@Override
	public String toString()
	{
		return (start + "-" + end);
	}
	
	// static members
	public static boolean isValid(float start, float end)
	{
		return (start >= 0 && start < end);
	}
}
