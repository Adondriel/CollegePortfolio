#include "stdio.h"
#include "lab9.h"


int main()
{
   queue q;
   q.size = 17;
   doSomethingWithQueue(q);
}

int doSomethingWithQueue(queue q)
{
  printf("%d", q.size);
}
