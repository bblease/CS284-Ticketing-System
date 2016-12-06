/**
* Final project for CS284 F2015 semester
* Professor Igor Fayneburg
* A Ticket System using hash table archives and max heap for ticket priority
* MAIN CLASS for this project
* @author Ben Blease
* @since 12/14/15
* I pledge my honor that I have abided by the Stevens honor system.
**/

import java.util.*;
import java.io.*;

public class TicketSystem
{
	private Ticket[] archive; 
	private TicketQueue queue;
	private UserList userHistory;
	private HashList usernames; 
	private HashList hosts;  
	private HashList problems;

	/*hash lists contains all hashtables with a specific type of key.
	If a username is already in an existing hash table, the hashlist creates a new table.
	Hashlists provide interface between the ticketsystem class and the hash tables.*/
	private int capacity = 20;

	public TicketSystem()
	{
		queue = new TicketQueue(capacity);
		userHistory = new UserList(capacity);
		archive = new Ticket[capacity];
		usernames = new HashList(capacity, 133);
		hosts = new HashList(capacity, 133);
		problems = new HashList(capacity, 133);
	}
	/**
	* Gathers data and adds to the Priority Queue
	* Handles incorrect input based on requirements outlined in assignment
	**/
	public void gatherData()
	{
		//read messages.txt. Some code reused from my submission for HW4
		//uses implementation of map in UserList.java
		try 
		{
			File targetFile = new File("messages.txt");
			FileReader reader = new FileReader(targetFile);
			BufferedReader input = new BufferedReader(reader);
			String inputLine;
			int id = 0; //current id number

			while ((inputLine = input.readLine()) != null)
			{
				String[] split = inputLine.split("\\|");
				if (split.length == 4 && !inputLine.equals("break"))
				{
					String username = split[0];
					String hostname = split[1];
					String problem = split[2];
					int level = Integer.parseInt(split[3]);
					
					if (level < 1 || level > 5)
					{
						System.out.println("ERROR: Priority level is out of bounds [1, 5], ignoring.");
					}
					else if (username.equals("break"))
					{
						System.out.println("ERROR: 'break' may not be a username, ignoring.");
					}
					else
					{
						//add user to directory
						userHistory.insert(username, level);
						//determine current priority
						Double priority = level-0.05*userHistory.getCount(username);
						//add Ticket to queue based on priority
						Ticket newTicket = new Ticket(username, hostname, problem, level, priority, 0);
						queue.addHeap(newTicket);
						System.out.println("READ " + inputLine);
					}
				}
				//handle tickets after batch is finished
				else if (inputLine.equals("break"))
				{
					System.out.println("---------------FINISHED BATCH---------------");
					System.out.println("---------------SERVING BATCH---------------");
					while (!queue.isEmpty())
					{
						handleTicket(id);
						id++;
					}
					System.out.println("---------------FINISHED SERVING BATCH---------------");
				}
			}
			input.close();
		}

		catch (IOException e)
		{
			System.out.println("File not found!");
			System.exit(1);
		}
		System.out.println("Finished reading file");
	}
	/**
	* Handles Tickets in the priority queue
	* Checks for similar tickets previously handled
	* Adds Tickets to archive hashes
	**/ 
	public void handleTicket(int idNum)
	{
		//processed ticket
		Ticket procTicket = queue.remove();
		//check for previous issues and discard based on policy outlined in assignment
		for (Ticket curr : search(procTicket.username, usernames))
		{
			if (curr != null &&curr.hostname.equals(procTicket.hostname) 
				&& curr.problem.equals(procTicket.problem))
			{
				System.out.println("SYSTEM has handled ticket before, ignoring ticket" + procTicket.toString());
				return;
			}
		}

		for (Ticket curr : search(procTicket.hostname, hosts))
		{
			if (curr != null && curr.problem.equals(procTicket.problem)
				&& !curr.username.equals(procTicket.username))
			{
				System.out.println("SYSTEM has handled similar ticket to ticket : " + procTicket.toString() + " , problem is an easy fix.");
			}
		}

		for (Ticket curr : search(procTicket.problem, problems))
		{
			if (curr != null && curr.problem.equals(procTicket.problem)
				&& !curr.username.equals(procTicket.username)
				&& !curr.hostname.equals(procTicket.hostname))
			{
				System.out.println("SYSTEM has handled similar problem to ticket : " + procTicket.toString() + " solution may differ.");
			}
		}
		if (idNum == capacity)
		{
			Ticket[] temp = new Ticket[capacity*2];			
			System.arraycopy(archive, 0, temp, 0, capacity);
			capacity *= 2;
			archive = temp;
		}
		//add ticket attributes to archive hash tables and assigns id number
		System.out.println("SERVING " + procTicket.toString());
		archive[idNum] = procTicket;
		procTicket.idNum = idNum;
		usernames.addToHash(procTicket.username, idNum);
		hosts.addToHash(procTicket.hostname, idNum);
		problems.addToHash(procTicket.problem, idNum);

	}

	/**
	* Allows users to search the archive for messages previously processesed.
	**/
	public void userSearch()
	{
		String yn; //yes or no input
		Scanner newScanner = new Scanner(System.in);
		while(true)
		{
			System.out.println("---------------FINISHED---------------" + "\n");
			System.out.println("Would you like to search for a ticket? Y/N");
			if (newScanner.nextLine().equals("y"))
			{
				System.out.println("Leave blank to skip category.");
				System.out.println("Please enter username : ");
				String name = newScanner.nextLine();
				int i = 1;
				if (name != "") {System.out.println("The following tickets were submitted by user " + name + " : ");}
				//print list of all tickets under this username
				for (Ticket ticket : search(name, usernames))
				{
					
					if (ticket!= null) 
					{
						System.out.println(i + " " + ticket.toString());
						i++;
					}		
				}
				i = 1;
				System.out.println("Please enter hostname : ");
				String host = newScanner.nextLine();
				if (host != "") {System.out.println("The following tickets were submitted for host " + host + " : ");}
				//print list of all tickets under this host
				for (Ticket ticket : search(host, hosts))
				{
					if (ticket!= null) 
					{
						System.out.println(ticket.toString());
						i++;
					}
				}
				i = 1;
				System.out.println("Please enter problem : ");
				String problem = newScanner.nextLine();
				if (problem != "") {System.out.println("The following tickets have the problem " + problem + " : ");}
				//print list of all tickets under this problem
				i = 1;
				for (Ticket ticket : search(problem, problems))
				{
					if (ticket!= null) 
					{
						System.out.println(ticket.toString());
						i++;
					}
				}
			}
			else
			{
				System.out.println("Thank you for using this ticketing system.");
				break;
			}
		}	
	}

	/**
	* Searches hash table for specific key
	* @param input: key to search archive for
	* @param table: hash table to search
	* @return out: returns list of tickets found in archive
	**/
	private Ticket[] search(String key, HashList table)
	{
		Integer[] ids = table.searchHashes(key);
		Ticket[] out = new Ticket[ids.length];
		for (int i = 0; i < ids.length; i++)
		{
			if (ids[i] != null)
			{
				out[i] = archive[ids[i]];
			}
		}
		return out;
	}

	public static void main(String[] args)
	{
		TicketSystem focus = new TicketSystem();
		focus.gatherData(); 
		focus.userSearch();
	}
}