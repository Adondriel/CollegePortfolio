#include "APine_lab9.h"
#include <stdio.h>
int main (int argc, char* argv[])
{
	//create an array of processes called queue.
	ticks = 0;
	loop = 0;
	queue q;
	createQueue(q);
	while (loop == 0)
	{
		ticks++;
		printf("%d",ticks);
		int i;
		for (i = 1; i < getQueueSize(q); i++)
		{
			if (ticks - q.items[i].timeStamp > ticks)
			{
				if (getQueueSize(q) > 0)
				{
					scheduleProcess(q);
				}
			}	
		}
		getUserInput(q);
		if (ticks % 10 == 0)
		{
			for (i = 1; i < getQueueSize(q); i++)
			{
				q.items[i].priority = q.items[i].priority + 1;
			}			
		}
		
	}
}
