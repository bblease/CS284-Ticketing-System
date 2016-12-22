# CS284-Ticketing-System
A Java implementation of an IT ticketing system for CS284 - Data Structures at Stevens Institute of Technology

#Assignment

This repository contains my implementation of the CS284 Final Project

Prof. Igor Fayneburg

I pledge my honor that I have abided by the Stevens honor system.

CS284 curriculum has since changed and no longer includes this assignment.

#From the Assignment

#Requirements
#Input:
Your program should read from a file named “messages.txt” that contains the information provided by
the users. Each line has the following format:
Username|Hostname|Problem|Level
(For example, a line may look like that:
alecsmart|arts|RAM|4
(To parse the above, you may re-use the string-parsing examples in the book.)
Your code should also check for the break line that should trigger the processing of messages in the
priority queue.
#Data Structures
You should implement a priority queue for inserting the messages once they have been read from the
input file. The message with the highest priority should be returned first.
There should also be an array that serves as the archive of all messages that have not been ignored.
Finally, there should be hash tables, implemented using open addressing and linear probing, which enable
fast retrieval of messages based on username, hostname or issue type. The hash table should store the ID
number of each message only. Given the ID number, gathering all relevant data from the archive should
be easy.
#Message Priority
The internal priority of a message should be determined by the level set by the user and adjusted by the
user’s complaint history as follows:
3
𝑃𝑟𝑖𝑜𝑟𝑖𝑡𝑦𝑃𝑄 = 𝑃𝑟𝑖𝑜𝑟𝑖𝑡𝑦 − 0.05 × 𝑇𝑜𝑡𝑎𝑙𝐿𝑒𝑣𝑒𝑙𝑠𝑢𝑠𝑒𝑟,
where TotalLevelsuser is the sum of the levels of all messages filed by the user who issued the current
message and can be found in the archive. For example, if a user has issued three messages whose level
values were 2, 4 and 4, then the value of TotalLevelsuser is 10.
#Hash Function
You should implement the following hash function (which is a variation on the built-in Java hash function
for Strings):
ℎ(𝑛𝑎𝑚𝑒) = 23𝑛−1 × 𝑛𝑎𝑚𝑒[0] + 23𝑛−2 × 𝑛𝑎𝑚𝑒[1] + ⋯ + 𝑛𝑎𝑚𝑒[𝑛 − 1] = ∑23𝑛−1−𝑖𝑛𝑎𝑚𝑒[𝑖],
𝑛−1
𝑖=0
where n is the number of characters in the string.
1. This project is allocated a month for a reason, so start early!
2. Uppercase and lowercase characters should be treated as distinct.
3. Make sure your code can handle a name of an issue that contains spaces.
4. You can assume, however, that our input files will not be corrupt.
5. Use long instead of int variables for the hash codes.
6. Keep in mind that the hash table may require reallocation. You do not need to worry about
7. having the length of the new table be a prime number during reallocation, but ensure that the
number is odd.

