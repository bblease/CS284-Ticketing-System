/**
*A Map implementation for Ticket System
*Provides a list of all users that have submitted a ticket and their total levels
*@author Ben Blease
*@since 12/14/15
*I pledge my honor that I have abided by the Stevens honor system.
**/

public class UserList
{
	private String[] users;
	private int[] count;
	private int size;
	private int capacity;

	public UserList(int capacity)
	{
		this.capacity = capacity;
		users = new String[capacity];
		count = new int[capacity];
	}

	/**
	*Inserts name and number of tickets added
	*@param name: Name to add
	*@param score: Number of tickets
	**/
	public void insert(String name, int score)
	{
		if (size == capacity)
		{
			String[] usersTmp = new String[capacity*=2];
			int[] countTmp = new int[capacity]; 

			System.arraycopy(users, 0, usersTmp, 0, users.length);
			System.arraycopy(count, 0, countTmp, 0, count.length);
			users = usersTmp;
			count = countTmp;
		}

		for (int i = 0; i < users.length; i++)
		{
			if (users[i] == null)
			{
				users[i] = name;
				count[i] = score;
				size++;
				break;
			}

			else if (users[i] != null && users[i].equals(name))
			{
				count[i] += score; 
				break;
			}
		}
	}

	/**
	*Gets the number of tickets submitted by a user at any given time
	*@param name: user that submitted number of tickets
	*@return count: number of tickets user name has submitted
	**/
	public int getCount(String name)
	{
		for (int i = 0; i < users.length; i++)
		{
			if (users[i] != null && users[i].equals(name))
			{
				return count[i];
			}
		}
		return 0;
	}
}