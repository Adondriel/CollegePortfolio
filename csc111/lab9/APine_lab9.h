

#ifndef MAXSIZE
	#define MAXSIZE 10

	typedef struct process
	{
		int id;
		int timeStamp;
		int duration;
		int priority;
	} process;
	
	typedef struct queue
	{
		int size;
		process items[25];
	} queue;

	int ticks;
	int loop;

	void createQueue(queue);
	int getQueueSize(queue);
	void addToQueue(process, queue);
	void scheduleProcess(queue);
	void getUserInput(queue);
	void printQueue(queue);
#endif
