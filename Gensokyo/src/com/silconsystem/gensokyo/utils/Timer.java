package com.silconsystem.gensokyo.utils;

/***
 * 
 * @author silconsys
 *
 *	initialize timer to a certain time period, then in update(delta) method call Timer.update(delta)
 *	on all Timers.
 *	Then check if any of timers have elapsed by calling:
 *	Timer.hasElapsed()
 *
 *	this handy script I found on stackoverflow by user1542257
 *
 */
public class Timer {
	protected float remaining;
	protected float interval;
	
	public Timer(float interval)
	{
		this.interval = interval;
		this.remaining = interval;
	}
	
	public boolean hasTimeElapsed()
	{
		return (remaining < 0.0F);
	}
	
	public void reset()
	{
		remaining = interval;
	}
	
	public void reset(float interval)
	{
		this.interval = interval;
		this.remaining = interval;
	}
	
	public void update(float delta)
	{
		remaining -= delta;
	}
}
