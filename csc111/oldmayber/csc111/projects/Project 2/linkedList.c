
/****************************************************
* Lab 10                                            *
* Programmer: Dr. mooney, modified by Adam Pine     *
* 4/21                                              *
*                                                   *
* Modifies a singly linked list ADT to work and     *
* become a doubly linked list ADT                   *
****************************************************/

#include <stdio.h>
#include "linkedList.h"

//struct node *findPtrToPred (int, list);

/****************************************************
* listCreate *
****************************************************/
//Inputs: pointer to list
//Outputs: none
//Purpose: Initializes the list
void ramCreate (list *l)
{
  node *temp = malloc(sizeof(struct node));
  (*temp).free = 0;
  (*temp).nodeSize = MAX;
  (*temp).prev = NULL;
  (*temp).next = NULL;
  (*l).toFirst = temp;
  (*l).toLast = temp;
  (*l).isEmpty = TRUE;
  (*l).isFull = FALSE;
  (*l).size = 1;
}
/****************************************************
* listIsEmpty *
****************************************************/
//Inputs: list
//Outputs: int
//Purpose: returns TRUE or FALSE (1 or 0) 
void ramIsEmpty (list *l)
{
  //empty: When the only thing in the list is a node
  // that is unallocated.
  //CHECKS THE CONDITIONS SO THAT WE DONT HAVE TO DO IT ELSEWHERE.
  int p = (*l).size;
  if (p == 1)
  {
    (*l).isEmpty = TRUE;
  }
  else
  {
    (*l).isEmpty = FALSE;
  }
}
/****************************************************
* listIsFull *
****************************************************/
//Inputs: list
//Outputs: int
//Purpose: returns TRUE or FALSE (1 or 0) 
void ramIsFull (list *l)
{  
  node *p = (*l).toFirst;
  if (size <= p.nodeSize)
  {
    (*l).isFull = FALSE;
  }
  else
  {
    (*l).isFull = TRUE;
  }
  
}
/****************************************************
* listSize *
****************************************************/
//Inputs: list
//Outputs: int
//Purpose: returns the amount of nodes allocated.
int ramSize (list l)
{
  return l.size;
}
/****************************************************
* listAdd *
****************************************************/
//Inputs: int, pointer to list
//Outputs: int
//Purpose: Add a node to the list. returns -1 for
//         failure, 0 otherwise. 
int nodeAlloc (int s, list *l)
{
  //s is the size to be allocated
  if (!ramHasRoom(s))
  {
    return -1;
  }
  else
  {
    node *temp = malloc(sizeof(node));
    (*temp).free = 1;
    (*temp).nodeSize = s;
    (*temp).next = NULL;
    (*temp).prev = (*l).toLast;
    (*l).toLast = temp;
    (*l).size++;
    ramIsEmpty(*l);
    ramIsFull(*l);
  }
}

int nodeDealloc(int index, list *l)
{
if (index == 0)
{
  printf("You cannot delete the HEAP!");
  return -1;
}
else
{
  node *p = getNodeAtIndex(index, *l);
  node *temp = p;
  (*p).prev->next = (*p).next;
  if ((*p).next != NULL)
  {
    (*p).next->prev = (*p).prev;
  }
  else
  {
    (*l).toLast = (*p).prev;
  }
  free(temp);
  (*l).size--;
  ramIsEmpty(*l);
  ramIsFull(*l);
  return 0;
}



}
node *getNodeAtIndex(int index, list l)
{
  int i = 0;
  node *p;

  if ((index < 1) || (index > l.size - 1))
    return NULL;
  else {
    p = l.toFirst;
    while (i < index) {
      i++;
      p = p -> next;
    }
    return p;
  }
}
///****************************************************
//* listPrint *
//****************************************************/
////Inputs: list
////Outputs: none
////Purpose: prints the list.
void listPrint (list l)
{
  struct node *p;
  int i=0;
  p = l.toFirst;
  while (p) {
    printf("Node#: %d Free: %d NodeSize: %d\n", i, (*p).free, (*p).nodeSize);
    p = p -> next;
    i++
  }
  printf("\n");
}
///****************************************************
//* listPrintReverse *
//****************************************************/
////Inputs: list
////Outputs: none
////Purpose: prints the list in reverse utilizing the
////         doubly linked lists prev pointer. 
//void listPrintReverse (list l)
//{
//  struct node *p;
//
//  p = getNodeAtIndex(l.size-1, l);
//  while (p) 
//  {
//    printf("%d ", p -> nodeSize);
//    p = (*p).prev;
//  }
//  printf("\n");
//}