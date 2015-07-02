/****************************************************
* Lab 9 *
* Programmer: Adam Pine *
* 4/14 *
* lab 9 Structs*
****************************************************/
#include "APine_lab9.h"
#include <stdio.h>
void createQueue(queue q)
{	
	q.size = 0;
	return;
}
int getQueueSize(queue q)
{
	return q.size;
}
void addToQueue(process p, queue q)
{
	if (q.size < MAXSIZE)
	{	
		q.items[q.size] = p;
		q.size++;
	}
	return;
}
void scheduleProcess(queue q)
{
	//Has to decide which item to schedule, then it has to schedule it. set timestamp
	int i;
	int pos, posVal = 0;
	for (i = 0; i < getQueueSize(q); i++)
	{
		if (q.items[i].priority > posVal)
		{
			//almost tried to use .getPriority, hah, thought i was back in java.
			posVal = q.items[i].priority;
			pos = i;
		} 
	}
	for (i = getQueueSize(q); i >= 0; i--)
	{
		if (q.items[i].priority > posVal)
		{
			//almost tried to use .getPriority, hah, thought i was back in java.
			posVal = q.items[i].priority;
			pos = i;
		}
	}
	//this should set up q, then copy it over to s, then "delete" q's entry.
	q.items[pos].timeStamp = ticks;
	return;
}
void getUserInput(queue q)
{
	//prompts for input, then adds it to the queue, initialize timestamp to -1.
	printf("Please enter the process you would like to schedule in the format id,duration,priority\n");
	process temp;
	scanf("%d,%d,%d", temp.id, temp.duration, temp.priority);
	if (temp.id < 0)
	{
		loop = 1;
	}
	if ((temp.timeStamp < 0) || (temp.duration < 0) || (temp.priority < 0) || (temp.priority > 10))
	{
		printf("You have made an error while entering your information, please reenter it:\n");
		getUserInput(q);
	}
	addToQueue(temp, q);
	return;
}
void printQueue(queue q)
{
	int i;
	for (i = 0; i < getQueueSize(q); i++)
	{
		printf("%d", q.items[i].id);
		printf("%d", q.items[i].timeStamp);
		printf("%d", q.items[i].duration);
		printf("%d", q.items[i].priority);
	}
	return;
}
