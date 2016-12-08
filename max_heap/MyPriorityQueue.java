/**
*A Priority Queue Implementation
*@author Ben Blease
*@since 11/19/15
*I pledge my honor that I have abided by the Stevens honor system.
**/

import java.io.*;
import java.util.*;

public class MyPriorityQueue
{
	private MyMaxHeap theQueue;

	/**
	*Creates a heap by inserting the elements of the input array arr
	*@param arr: array of credit offers collected from input
	*@param n: capacity of the heap
	*@param monthlyExpenses: planned monthly expense
	**/
	public MyPriorityQueue(CreditOffer[] arr, int n, double monthlyExpenses)
	{
		theQueue = new MyMaxHeap(arr, n, monthlyExpenses);
	}	

	/**
	*Creates an empty heap with capacity = 5
	*@param monthlyExpenses: planned monthly expense
	**/
	public MyPriorityQueue(double monthlyExpenses)
	{
		theQueue = new MyMaxHeap(monthlyExpenses);
	}

	/**
	*Adds a new element to the queue and returns true
	*@param co: CreditOffer to be added to the queue
	**/
	public boolean offer(CreditOffer co)
	{
		return theQueue.add(co);
	}

	/**
	*Returns the node with the highest score and removes.
	*@return returns removed CreditOffer
	**/
	public CreditOffer remove()
	{
		return theQueue.removeMax();
	}

	/**
	*returns the node with the highest score without removing
	*@return the node with the highest score
	**/ 
	public CreditOffer peek()
	{
		return theQueue.getFirst();
	}

	/**
	*returns true if the list is empty
	*@return true if list is empty
	**/
	public boolean isEmpty()
	{
		if (theQueue.size() == 0)
		{
			return true;
		}

		else
		{
			return false;
		}
	}

	/**
	*Prints all elements in the order they are stored
	**/
	public void showList()
	{
		theQueue.showList();
	}

	public static void main(String[] args)
	{
		//read inputs from offers.txt
		List<CreditOffer> arrList = new ArrayList<CreditOffer>();

		try 
		{
			double membershipFee;
			double CBR;
			double APR;

			File targetFile = new File("offers.txt");
			FileReader reader = new FileReader(targetFile);
			BufferedReader input = new BufferedReader(reader);
			String inputLine;

			while ((inputLine = input.readLine()) != null)
			{
				String[] split = inputLine.split(" ");
				System.out.println("READ " + inputLine);

				if (split[0].length() != 0)
				{
					membershipFee = Double.parseDouble(split[0]);
					CBR = Double.parseDouble(split[1]);
					APR = Double.parseDouble(split[2]);

					arrList.add(new CreditOffer(membershipFee, CBR, APR));
				}
			}

			input.close();
		}

		catch (IOException e)
		{
			System.out.println("File not found!");
			System.exit(1);
		}
		//convert to array
		CreditOffer[] arr = arrList.toArray(new CreditOffer[arrList.size()]);

		//UNCOMMENT to read from file
		//MyPriorityQueue newQueue = new MyPriorityQueue(arr, arrList.size(), 40.0);

		//UNCOMMENT to add to empty heap
		MyPriorityQueue newQueue = new MyPriorityQueue(40);
		System.out.println(newQueue.isEmpty());
		newQueue.offer(new CreditOffer(42, .62, .01));
		newQueue.showList();
		newQueue.offer(new CreditOffer(12, .21, .02));
		newQueue.showList();
		newQueue.offer(new CreditOffer(32, .04, .03));
		newQueue.showList();
		newQueue.offer(new CreditOffer(23, .07, .06));
		newQueue.showList();	
		newQueue.offer(new CreditOffer(43, .03, .021));
		newQueue.showList();
		newQueue.remove();
		newQueue.showList();
		newQueue.remove();
		newQueue.showList();
		newQueue.remove();
		newQueue.showList();
	}
}