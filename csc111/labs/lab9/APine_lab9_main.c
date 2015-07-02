#include "APine_lab9.h"
#include <stdio.h>
int main (int argc, char* argv[])
{
	//create an array of processes called queue.
	ticks = 0;
	loop = 0;
	queue q;
	createQueue(&q);
	while (loop == 0)
	{
		ticks++;
		int i;
		if (q.scheduledProc.id == -1)
		{
			scheduleProcess(&q);
		}
		getUserInput(&q);
		if (ticks % 10 == 0)
		{
			for (i = 1; i < getQueueSize(q); i++)
			{
				q.items[i].priority = q.items[i].priority + 1;
			}			
		}
		printf("Ticks: %d\n",ticks);
		if (q.size > 0)
		{
			printf("Current Scheduled Process info:\n");
			printf("ID: %d\n", q.scheduledProc.id);
			printf("Duration: %d\n", q.scheduledProc.duration);
			printf("Timestamp: %d\n", q.scheduledProc.timeStamp);
			printf("Priority: %d\n", q.scheduledProc.priority);
		}
	}
}
