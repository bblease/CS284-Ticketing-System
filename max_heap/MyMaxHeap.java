/**
*A Max Heap Implementation
*@author Ben Blease
*@since 11/19/15
*I pledge my honor that I have abided by the Stevens honor system.
**/

import java.util.*;

public class MyMaxHeap
{

	private CreditOffer[] theHeap;
	private int size;
	private int capacity;
	private double monthlyExpenses;

	/**
	*Creates a heap by inserting the elements of the input array arr
	*@param arr: array of credit offers collected from input
	*@param n: capacity of the heap
	*@param monthlyExpenses: planned monthly expense
	**/
	public MyMaxHeap(CreditOffer[] arr, int n, double monthlyExpenses)
	{
		this.size = 0;
		this.capacity = n;
		theHeap = new CreditOffer[capacity+1];
		this.monthlyExpenses = monthlyExpenses;

		for (int j = 0; j < capacity; j++)
		{
			add(arr[j]);
		}
	}

	/**
	*Creates an empty heap with capacity = 5
	*@param monthlyExpenses: planned monthly expense
	**/
	public MyMaxHeap(double monthlyExpenses)
	{
		this.size = 0;
		this.capacity = 5;
		this.monthlyExpenses = monthlyExpenses;
		theHeap = new CreditOffer[capacity+1];
	}

	/**
	*returns size of the heap size smaller than capacity
	*@return size of the heap
	**/ 
	public int size()
	{
		return size;
	}

	/**
	*Adds element and reheaps
	*Will add element and reheap, discarding an element to keep the same capacity if size > capacity
	*@param job: CreditOffer to be added
	*@return true if successfully added
	*@return false if unsuccessful
	**/ 
	public boolean add(CreditOffer job)
	{
		if (size == capacity)
		{
			CreditOffer[] temp = new CreditOffer[capacity*2+1];			
			System.arraycopy(theHeap, 0, temp, 0, capacity+1);
			capacity *= 2;
			theHeap = temp;
		}
		//adds element as the last leaf on the tree
		theHeap[size+1] = job;
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
	*Removes the node with the highest score and reheaps
	*@return returns removed CreditOffer
	**/
	public CreditOffer removeMax()
	{
		CreditOffer out = null;

		if (size > 0)
		{
			out = theHeap[1];
			theHeap[1] = theHeap[size];
			theHeap[size] = null;
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
	*returns the node with the highest score without removing
	*@return the node with the highest score
	**/ 
	public CreditOffer getFirst()
	{
		if (size > 0)
		{
			return theHeap[1];
		}

		else
		{
			return null;
		}
	}

	/**
	*Prints all elements in the order they are stored
	**/
	public void showList()
	{
	
		if (size > 0)
		{
			String out = "";
			
			for (int i = 1; i < capacity+1; i++)
			{
				if (theHeap[i] != null)
				{
					double scorei = (monthlyExpenses*theHeap[i].cbr) - theHeap[i].membershipFee - (monthlyExpenses*12*0.1*theHeap[i].apr);
					out += "[";
					out += "SCORE : " + scorei + " , ";
					out += "Membership Fee : " + theHeap[i].membershipFee + " , ";
					out += "Cash Back Rate : " + theHeap[i].cbr + " , ";
					out += "APR : " + theHeap[i].apr;
					out += "]" + "\n";
				}
			}

			System.out.println(out);
		}
	}

	/**
	*Swaps the CreditOffers at indeces i and j
	*@param i: index i
	*@param j: index j
	**/
	private void swap(int i, int j)
	{
		if (i <= capacity+1 && j <= capacity+1)
		{
			CreditOffer temp;
			temp = theHeap[i];
			theHeap[i] = theHeap[j];
			theHeap[j] = temp;
		}
	}

	/**
	*Compares the scores of i and j
	*@param i: index of element i
	*@param j: index of element j
	*@return differences of the scores of i and j
	**/
	private double compare(int i, int j)
	{
		//assuming compare compares the scores associated with the creditoffers, monthlyExpenses must be a class variable, contrary to the UML provided. 
		double scorei = (monthlyExpenses*theHeap[i].cbr) - theHeap[i].membershipFee - (monthlyExpenses*12*0.1*theHeap[i].apr);
		double scorej = (monthlyExpenses*theHeap[j].cbr) - theHeap[j].membershipFee - (monthlyExpenses*12*0.1*theHeap[j].apr);
		return scorei - scorej;
	}

	/**
	*Reorders the left and right child so left child is less than right child
	**/
	private void order()
	{
		for (int n = 1; n < capacity + 1; n++)
		{
			if (n*2 < capacity && theHeap[(n*2)+1] != null && n*2 < capacity && (n*2)+1 < capacity)
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