/**
*Stores all the archive hash objects for three categories: usernames, hostnames, problems
*Creates a new hash table if there is a value with the same key in a hash table
*@author Ben Blease
*@since 12/14/15
*I pledge my honor that I have abided by the Stevens honor system.
**/
import java.util.*;
public class HashList
{
	private ArchiveHash[] theList;
	private int capacity;
	private int hashCapacity;

	public HashList(int lstCapacity, int hashCapacity)
	{
		capacity = lstCapacity; 
		this.hashCapacity = hashCapacity;
		theList = new ArchiveHash[capacity]; 
	}

	/**
	*Searches all hash tables for a particular key
	*@param key: searches all hash tables for key
	*@return out: A list of all values paired with key throughout all hash tables
	**/
	public Integer[] searchHashes(String key)
	{
		Integer[] out = new Integer[capacity];
		int c = 0;
		for(int i = 0; i < capacity; i++)
		{
			if (theList[i] != null && theList[i].get(key)!=null)
			{
				out[c] = theList[i].get(key);
				c++;
			}
		}
		return out;
	}

	/**
	*Adds key and value to hash table. If the key is already in the table, add to next table without key
	*@param key: key to add to table
	*@param val: val tied to key
	**/
	public void addToHash(String key, int val)
	{		
		int i = 0;
		while (i < capacity && theList[i] != null && theList[i].get(key) != null)
		{
			i++;
		}
		if (i == capacity-1)
		{
			ArchiveHash[] temp = new ArchiveHash[capacity*2];
			System.arraycopy(theList, 0, temp, 0, capacity);
			capacity *= 2;
			theList = temp; 
		}
		else
		{
			if (theList[i] == null)
			{
				theList[i] = new ArchiveHash(hashCapacity);
			}
			theList[i].insert(key, val);
		}
	}

	/**
	*Checks if a key is found in any hash table
	*@return true if key is found, false otherwise
	**/
	public boolean contains(String key)
	{
		return (searchHashes(key)[0] != null) ? true : false;
	}

	/**
	*Shows the each hash table's contents
	**/
	public void show()
	{
		for (ArchiveHash curr : theList)
		{
			if (curr != null)
			{
				curr.output();
			}
		}
	}
}