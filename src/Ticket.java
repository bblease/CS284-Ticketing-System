/**
*A Ticket Object to be added to the TicketQueue priority queue 
*Stores all relevant attributes to the ticket
*@author Ben Blease
*@since 12/14/15
*I pledge my honor that I have abided by the Stevens honor system.
**/

public class Ticket
{
	public String username;
	public String hostname;
	public String problem; 
	public int level; 
	public Double priority; //priority is assumed an attribute of Ticket
	public int idNum;

	public Ticket(String username, String hostname, String problem, int level, Double priority, int idNum)
	{
		this.username = username;
		this.hostname = hostname;
		this.problem = problem;
		this.level = level;
		this.priority = priority;
		this.idNum = idNum;
	}

	/**
	*Creates string of all relevant data to be printed
	*@return out: Output string with all relevant data
	**/
	public String toString()
	{
		String out = "";
		out += "[" + username + " , " + hostname + " , " + problem + " , " + level + ", ID : " + idNum + "]";
		return out;
	}
}