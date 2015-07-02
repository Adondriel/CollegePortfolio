/****************************************************
* Lab 9 *
* Programmer: Adam Pine *
* 4/14 *
* lab 9 Structs*
****************************************************/
#include "APine_lab9.h"
#include <stdio.h>
void createQueue(queue *q)
{	
	int i;
	(*q).size = 0;
	(*q).scheduledProc.id = -1;
//	for (i = 0; i <= MAXSIZE; i++)
//	{
//		q.items[].timeStamp = -1;
//	}
	printf("Queue Size: %d\n", (*q).size);
	return;
}
int getQueueSize(queue q)
{
	return q.size;
}
void addToQueue(process p, queue *q)
{
	printf("Process p: %d\n", p.id);
	printf("Process p: %d\n", p.duration);
	printf("Process p: %d\n", p.timeStamp);
	printf("Process p: %d\n", p.priority);	
	printf("queue of %d ID: %d\n", (*q).size, (*q).items[(*q).size].id);
	printf("queue of %d duration: %d\n", (*q).size, (*q).items[(*q).size].duration);
	printf("queue of %d timestamp: %d\n", (*q).size, (*q).items[(*q).size].timeStamp);
	printf("queue of %d Priority: %d\n", (*q).size, (*q).items[(*q).size].priority);
	if ((*q).size < MAXSIZE)
	{
		(*q).items[(*q).size].id = p.id;
		(*q).items[(*q).size].duration = p.duration;
		(*q).items[(*q).size].priority = p.priority;
		(*q).size++;
		printf("queue of %d ID: %d\n", (*q).size-1, (*q).items[(*q).size-1].id);
		printf("queue of %d duration: %d\n", (*q).size-1, (*q).items[(*q).size-1].duration);
		printf("queue of %d timestamp: %d\n", (*q).size-1, (*q).items[(*q).size-1].timeStamp);
		printf("queue of %d Priority: %d\n", (*q).size-1, (*q).items[(*q).size-1].priority);
	}
	return;
}
void scheduleProcess(queue *q)
{
	//Has to decide which item to schedule, then it has to schedule it. set timestamp
	int i;
	int pos, posVal = 0;
	for (i = 0; i <= (*q).size; i++)
	{
		if ((*q).items[i].priority > posVal)
		{
			posVal = (*q).items[i].priority;
			pos = i;
		} 
	}
	for (i = (*q).size; i >= 0; i--)
	{
		if ((*q).items[i].priority > posVal)
		{
			posVal = (*q).items[i].priority;
			pos = i;
		}
	}
	//this should set up q
	printf("Scheduled a process\n");
	(*q).scheduledProc = (*q).items[pos];
	queueRemoveKey(pos, q);
	return;
}
void getUserInput(queue *q)
{	
	process temp = {-1, -1, -1, -1};
	int tempid,dur,pri,yorn;
	yorn = -2;	
	
	printf("Would you like to schedule a new process? Enter 0 for no or 1 for yes. enter -1 to exit program.\n");
	scanf("%d", &yorn);
	if (yorn == -1)
	{
		loop = -1;
	}
	if (yorn == 0)
	{
		return;
	}
	else
	{
		//prompts for input, then adds it to the queue.
		printf("Please enter the process you would like to schedule in the format id,duration,priority\n");
		printf("ID: %d, DUR: %d, PRI: %d\n", temp.id, temp.duration, temp.priority); 
		scanf("%d,%d,%d", &tempid, &dur, &pri);
		temp.id = tempid;
		temp.duration = dur;
		temp.priority = pri;
		printf("ID: %d, DUR: %d, PRI: %d\n", temp.id, temp.duration, temp.priority); 
		if (temp.id < 0)
		{
			loop = 1;
		}
		addToQueue(temp, q);
	}
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

int queueRemoveKey (int index, queue *q)
{
  int i;
  if ((i < 1) || (i > (*q).size))
    return -1;
  for (i = index; i < (*q).size; i++)
    (*q).items[i] = (*q).items[i + 1];
    (*q).size--;
  return 1;
}

void checkProcessing(queue *q)
{
	if (ticks - (*q).scheduledProc.timeStamp >= ticks - (*q).scheduledProc.duration)
	{
		(*q).scheduledProc.id = -1;
	}
}
