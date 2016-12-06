/**
*A Hash Table implementation storing key value pairs. 
*Stores ticket ID Numbers
*@author Ben Blease
*@since 12/14/15
*I pledge my honor that I have abided by the Stevens honor system.
**/

import java.util.*;
public class ArchiveHash
{
	private HashEntry[] theTable;
	private int capacity; 
	private int size;
	private static final float LOAD_FACTOR = 0.5f;
	
	/**
	*A hash entry, storing key and value
	**/
	private class HashEntry
	{
		public String key; //username/hostname/problem
		public int val; //id of ticket

		public HashEntry(String key, int val)
		{
			this.key = key;
			this.val = val; 
		}
	}
	
	public ArchiveHash(int capacity)
	{
		this.capacity = capacity;
		theTable = new HashEntry[capacity];
		size = 0;
	}
	
	/**
	*Inserts key and value into hash table
	*Uses open addressing and linear probing
	*@param key: key to insert
	*@param val: val to pair with key in table
	**/
	public void insert(String key, int val)
	{
		rehash();
		HashEntry newEntry = new HashEntry(key, val);
		int index = (int) (hashFunction(key) % capacity);
		if (theTable[index] == null)
		{
			theTable[index] = newEntry;
		}
		else
		{
			while (theTable[index] != null)
			{
				index = (index+1)%capacity;
			}
			theTable[index] = newEntry;
		}
		size++;
	}
	
	/**
	*Returns integer value of key
	*@param key, key of needed values
	*@return out, list of all values with same key
	**/
	public Integer get(String key)
	{
		int index = (int) (hashFunction(key) % capacity);
		if (theTable[index] != null && theTable[index].key.equals(key))
		{
			return theTable[index].val;
		}
		while (theTable[index] != null)
		{
			//index restarts from 0 when capacity is reached
			if (index == capacity-1 && !theTable[index].key.equals(key))
			{
				index = 0;
			}
			else if(theTable[index].key.equals(key))
			{
				
				return theTable[index].val;
			}
			index++;
		}
		return null;
	}

	/**
	*Prints all keys with their index
	**/
	public void output()
	{
		String out = "";
		int i = 0;
		while (i < capacity)
		{
			if (theTable[i] != null)
			{
				out += "[" + theTable[i].val + " , " + theTable[i].key + "]";
			}
			i++;
		}
		System.out.println(out);
	}

	/**
	*Computes hash function based from string
	*@param key: String to compute hash function from
	**/	
	private long hashFunction(String key)
	{
		long out = 0;
		for (int i = 0; i < key.length()-1; i++)
		{
			out += Math.pow(23, key.length()-1-i) * (int) key.charAt(i);
		}
		return out;
	}
	
	/**
	*Creates a new hash table of odd length
	*Rehashes keys to new indeces
	**/
	private void rehash()
	{
		if ((int) (LOAD_FACTOR*capacity) == size)
		{
			System.out.print("REHASHING");
			HashEntry[] temp = new HashEntry[capacity];
			System.arraycopy(theTable, 0, temp, 0, capacity);
			theTable = new HashEntry[(capacity*2)+1];
			for (HashEntry curr : temp)
			{
				if (curr != null)
				{
					insert(curr.key, curr.val);
				}
			}			
			capacity = (capacity*2)+1;
		}
	}
}
