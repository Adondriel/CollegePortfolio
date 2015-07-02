/****************************************************
* Lab 9 *
* Programmer: Adam Pine *
* 4/14 *
* lab 9 Structs*
****************************************************/

#ifndef MAXSIZE
	#define MAXSIZE 25

	typedef struct process
	{
		int id;
		int timeStamp;
		int duration;
		int priority;
                int complete;
	} process;
	
	typedef struct queue
	{
		int size;
		process scheduledProc;
		process items[25];
	} queue;

	int ticks;
	int loop;

	void createQueue(queue *);
	int getQueueSize(queue);
	void addToQueue(process, queue *);
	void scheduleProcess(queue *);
	void getUserInput(queue *);
	void printQueue(queue);
	void checkProcessing(queue *);	
#endif
