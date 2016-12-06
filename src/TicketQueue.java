/**
*A Priority Queue Implementation for Ticket System
*Stores Ticket objects based on priorities
*@author Ben Blease
*@since 12/14/15
*I pledge my honor that I have abided by the Stevens honor system.
**/

import java.util.*;

public class TicketQueue
{
	private Ticket[] theQueue;
	private int capacity;
	private int size;

	public TicketQueue(int capacity)
	{
		//capacity is updated when batch of data is read from messages.txt
		this.capacity = capacity;
		//capacity for user archive is arbitrary
		theQueue = new Ticket[capacity+1];
	}
	
	/**
	* Adds Ticket to max heap.
	* @param job: ticket to be added to the heap
	* @return true if successfully added
	**/
	public boolean addHeap(Ticket job)
	{
		if (size == capacity)
		{
			System.out.println("REHEAPING");
			Ticket[] temp = new Ticket[capacity*2+1];			
			System.arraycopy(theQueue, 0, temp, 0, capacity+1);
			capacity *= 2;
			theQueue = temp;
		}
		//adds element as the last leaf on the tree
		theQueue[size+1] = job;
		int k = size+1;
		//shift leaf up
		while (k > 1 && compare(k/2, k) < 0.0)
		{
			swap(k, k/2);
			k = k/2;
		}
		order();
		size++;
		return true;
	}
	/**
	* Includes code from my own implementation of a Max Heap from CS 284 HW4 - Extra Credit
	* Removes the ticket with the highest priority
	* Heap structure kept intact
	* @return out: Ticket with highest priority
	**/
	public Ticket remove()
	{
		Ticket out = null;

		if (size > 0)
		{
			out = theQueue[1];
			theQueue[1] = theQueue[size];
			theQueue[size] = null;
			size--;

			int k = 1;

			while(2*k+1 < size && compare(k, 2*k+1) < 0.0)
			{
				swap(k, 2*k+1);
				k = 2*k+1;
			}
			order();
		}
		return out;
	}

	/**
	* @return true if heap is empty, false otherwise
	**/	
	public boolean isEmpty()
	{
		return (size == 0) ? true : false;
	}

	public void out()
	{
		System.out.println(Arrays.toString(theQueue));
	}

	/**
	* Swaps the Ticket objects at indeces i and j
	* @param i: index i
	* @param j: index j
	**/
	private void swap(int i, int j)
	{
		if (i <= capacity+1 && j <= capacity+1)
		{
			Ticket tmp;
			tmp = theQueue[i];
			theQueue[i] = theQueue[j];
			theQueue[j] = tmp;
		}
	}

	/**
	* Compares the priorities of i and j
	* @param i: index of element i
	* @param j: index of element j
	* @return differences of the priorities of i and j
	**/
	private Double compare(int i, int j)
	{
		if (theQueue[i] != null && theQueue[j] != null)
		{
			return theQueue[i].priority - theQueue[j].priority;
		}
		else {return null;}
	}

	/**
	* Reorders the left and right child so left child is less than right child
	**/
	private void order()
	{
		for (int n = 1; n < capacity + 1; n++)
		{
			if (n*2 < capacity && theQueue[(n*2)+1] != null && n*2 < capacity && (n*2)+1 < capacity)
			{
				int m = n*2;
				if (compare(m, m+1) > 0.0)
				{
					swap(m,m+1);
				}
			}
		}
	}
}