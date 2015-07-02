/****************************************************
* Lab 9 *
* Programmer: Adam Pine *
* 4/14 *
* lab 9 Structs*
****************************************************/
#include "APine_lab9.h"
#include <stdio.h>
/****************************************************
* createQueue *
****************************************************/
//Inputs: A pointer to a queue
//Outputs: none
//Purpose: Initiallizes the values of the queue.
void createQueue(queue *q)
{	
	int i;
	(*q).size = 0;
	(*q).scheduledProc.id = -1;
	(*q).scheduledProc.duration = -1;
	(*q).scheduledProc.timeStamp = -1;
	(*q).scheduledProc.priority = -1;
        (*q).scheduledProc.complete = 0;
	
/*	for (i = 0; i < MAXSIZE; i++)
	{
		(*q).items[i].id = -1;
		(*q).items[i].duration = -1;
		(*q).items[i].timeStamp = -1;
		(*q).items[i].priority = -1;
	}*/
	printf("Queue Size: %d\n", (*q).size);
	return;
}
/****************************************************
* getQueueSize *
****************************************************/
//Inputs: Queue
//Outputs: Int
//Purpose: Returns the size of the queue.
int getQueueSize(queue q)
{
	return q.size;
}
/****************************************************
* addToQueue *
****************************************************/
//Inputs: process, queue
//Outputs: none
//Purpose: Adds the process to the queue
void addToQueue(process p, queue *q)
{
	//printf("Process p: %d\n", p.id);
	//printf("Process p: %d\n", p.duration);
	//printf("Process p: %d\n", p.timeStamp);
	//printf("Process p: %d\n", p.priority);	
	//printf("queue of %d ID: %d\n", (*q).size, (*q).items[(*q).size].id);
	//printf("queue of %d duration: %d\n", (*q).size, (*q).items[(*q).size].duration);
	//printf("queue of %d timestamp: %d\n", (*q).size, (*q).items[(*q).size].timeStamp);
	//printf("queue of %d Priority: %d\n", (*q).size, (*q).items[(*q).size].priority);
	if ((*q).size < MAXSIZE)
	{
		(*q).items[(*q).size].id = p.id;
		(*q).items[(*q).size].duration = p.duration;
		(*q).items[(*q).size].priority = p.priority;
		(*q).items[(*q).size].complete = p.complete;
		(*q).items[(*q).size].timeStamp = 32768;

		(*q).size++;
		//printf("queue of %d ID: %d\n", (*q).size-1, (*q).items[(*q).size-1].id);
		//printf("queue of %d duration: %d\n", (*q).size-1, (*q).items[(*q).size-1].duration);
		//printf("queue of %d timestamp: %d\n", (*q).size-1, (*q).items[(*q).size-1].timeStamp);
		//printf("queue of %d Priority: %d\n", (*q).size-1, (*q).items[(*q).size-1].priority);
	}
	return;
}
/****************************************************
* scheduleProcess *
****************************************************/
//Inputs: queue
//Outputs: none
//Purpose: determines which process to schedule, and then it schedules it.
void scheduleProcess(queue *q)
{
	//Has to decide which item to schedule, then it has to schedule it. set timestamp
	int i;
	int pos, posVal = 0;

	for (i = 0; i < (*q).size; i++)
	{
		if ((*q).items[i].priority > posVal)
		{
			posVal = (*q).items[i].priority;
			pos = i;
		} 
	}
        //printf("Pos is: %d",pos);
	for (i = (*q).size-1; i >= 0; i--)
	{
		if ((*q).items[i].priority > posVal)
		{
			posVal = (*q).items[i].priority;
			pos = i;
		}
	}
        //printf("Pos is: %d",pos);
	//this should set up q
	printf("Scheduled a process: %d \n",pos);
        if (pos < (*q).size)
        {
	  (*q).scheduledProc.id = (*q).items[pos].id;
	  (*q).scheduledProc.duration = (*q).items[pos].duration;
	  (*q).scheduledProc.timeStamp = ticks;
	  (*q).scheduledProc.priority = (*q).items[pos].priority;
          (*q).scheduledProc.complete = (*q).items[pos].complete;
	  queueRemoveKey(pos, q);
        }
	return;
}
/****************************************************
* getUserInput *
****************************************************/
//Inputs: queue
//Outputs: none
//Purpose: prompts the user for all of the inputs, can call addToQueue.
void getUserInput(queue *q)
{	
	process temp;
        temp.id=-1;
        temp.duration = -1;
        temp.priority = -1;
  
	int tempid = 0;
        int dur = 0;
        int pri = 0;
        int yorn = 0;
	
	printf("Would you like to schedule a new process? Enter 0 for no or 1 for yes. enter -1 to exit program.\n");           
	scanf("%d", &yorn);
         
        if (yorn == -1)
	{		
		loop = -1;
		return;
	}
	else if (yorn == 0)
	{
		return;
	}
	else
	{
		//prompts for input, then adds it to the queue.
		printf("Please enter the process you would like to schedule in the format id,duration,priority\n");
		//printf("ID: %d, DUR: %d, PRI: %d\n", temp.id, temp.duration, temp.priority); 
		scanf("%d,%d,%d", &tempid, &dur, &pri);
		temp.id = tempid;
		temp.duration = dur;
		temp.priority = pri;
                temp.timeStamp = ticks;
                temp.complete = 0;
		//printf("ID: %d, DUR: %d, PRI: %d\n", temp.id, temp.duration, temp.priority); 
		addToQueue(temp, q);
		//printf("queue of %d ID: %d\n", (*q).size-1, (*q).items[(*q).size-1].id);
		//printf("queue of %d duration: %d\n", (*q).size-1, (*q).items[(*q).size-1].duration);
		//printf("queue of %d timestamp: %d\n", (*q).size-1, (*q).items[(*q).size-1].timeStamp);
		//printf("queue of %d Priority: %d\n", (*q).size-1, (*q).items[(*q).size-1].priority);
	}
	return;
}
/****************************************************
* printQueue*
****************************************************/
//Inputs: queue
//Outputs: none
//Purpose: Prints the queue
void printQueue(queue q)
{
	int i;
	for (i = 0; i < getQueueSize(q); i++)
	{
                printf("Queue Entry %d",i);
		printf(" id: %d", q.items[i].id);
		printf(" timeStamp: %d", q.items[i].timeStamp);
		printf(" duration: %d", q.items[i].duration);
		printf(" priority: %d\n", q.items[i].priority);
	}
	return;
}
/****************************************************
* queueRemoveKey *
****************************************************/
//Inputs: index/the position to remove, the queue to remove it from
//Outputs: int 1 if successful, -1 if failed
//Purpose: To remove the specified position from the queue.
int queueRemoveKey (int index, queue *q)
{
  printf("Remove Index %d\n",index);
  int i;
  if ((index < 0) || (index > (*q).size))
  {
    return -1;
  }
  for (i = index; i < (*q).size; i++)
  {
    printf("in loop");
    (*q).items[i] = (*q).items[i + 1];
  }
  (*q).size--;
  
  return 1;
}
/****************************************************
* checkProcessing *
****************************************************/
//Inputs: queue
//Outputs: none
//Purpose: Checks whether or not the currently scheduled process is complete or not.
void checkProcessing(queue *q)
{
	if ((ticks - (*q).scheduledProc.timeStamp) >= ((*q).scheduledProc.duration))
	{
		(*q).scheduledProc.complete = 1;
	}
	return;
}





