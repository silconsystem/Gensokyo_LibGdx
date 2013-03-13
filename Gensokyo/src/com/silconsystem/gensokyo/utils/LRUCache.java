package com.silconsystem.gensokyo.utils;

// import java packs
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/***
 * 
 * @author silconsys
 *
 * @param <K>
 * @param <V>
 * 
 * 		Simple LRU cache implementation
 * 		taken from StackOverFlow question:
 * 		"http://stackoverflow.com/questions/224868/easy-simple-to-use-lru-cache-in-java"
 */
public class LRUCache<K, V>
{
	/*** called when cached element is about to be removed
	 * 
	 */
	public interface CacheEntryRemovedListener<K, V>
	{
		void notifyEntryRemoved(K key, V value);
	}
	
	private Map<K, V> cache;
	private CacheEntryRemovedListener<K, V> entryRemovedListener;
	
	/*** creates the cache with the specified max enties
	 * 
	 */
	public LRUCache(final int maxEntries)
	{
		cache = new LinkedHashMap<K, V>(maxEntries + 1, .75F, true)
		{
			/**
			 * 
			 */
			private static final long	serialVersionUID	= 1L;

			public boolean removeEldestEntry(Map.Entry<K, V> eldest)
			{
				if (size() > maxEntries)
				{
					if (entryRemovedListener != null)
					{
						entryRemovedListener.notifyEntryRemoved(eldest.getKey(), eldest.getValue());
					}
					return true;
				}
				return false;
			}
		};
	}
	
	public void add(K key, V value)
	{
		cache.put(key, value);
	}
	
	public V get(K key)
	{
		return cache.get(key);
	}
	
	public Collection<V> retrieveAll()
	{
		return cache.values();
	}
	
	public void setEntryRemovedListener(CacheEntryRemovedListener<K, V> entryRemovedListener)
	{
		this.entryRemovedListener = entryRemovedListener;
	}
}
