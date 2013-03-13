package com.silconsystem.gensokyo.utils;

// import java packs
import java.io.Serializable;

public class Range implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5785667458373529828L;
	private final int start;
	private final int end;
	
	public Range(int start, int end)
	{
		if (!isValid(start, end))
		{
			throw new IllegalArgumentException("Invalid range: [" + start + "," + end + "]");
		}
		this.start = start;
		this.end = end;
	}
	
	public int getStart()
	{
		return start;
	}
	
	public int getEnd()
	{
		return end;
	}
	
	public int getMean()
	{
		return ((start + end) / 2);
	}
	
	public boolean isInside(int value)
	{
		return (value >= start && value <= end);
	}
	
	public boolean isInside(Range range)
	{
		if (range == null) return false;
		return (start >= range.start && end <= range.end);
	}
	
	public boolean isOutside(int value)
	{
		return ! isInside(value);
	}
	
	public boolean isOutside(Range range)
	{
		return ! isInside(range);
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + end;
		result = prime * result + start;
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Range other = (Range) obj;
		if (end != other.end) return false;
		if (start != other.start) return false;
		return true;
	}
	
	@Override
	public String toString()
	{
		return (start + "-" + end);
	}
	
	// static members
	public static boolean isValid(int start, int end)
	{
		return (start >= 0 && start < end);
	}
}
