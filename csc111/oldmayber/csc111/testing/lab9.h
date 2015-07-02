#ifndef lab9_h
#define lab9_h

typedef struct process 
{
  int id;
 int someothervar;
 char name[10]; 
} process;

typedef struct queue
{
 int size;
 process list[25];
} queue;

int doSomethingWithQueue(queue);

#endif
