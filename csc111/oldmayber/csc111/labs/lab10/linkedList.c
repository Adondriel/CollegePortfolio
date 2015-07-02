
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

struct node *findPtrToPred (int, list);

/****************************************************
* listCreate *
****************************************************/
//Inputs: pointer to list
//Outputs: none
//Purpose: Initializes the list
void listCreate (list *l)
{
  (*l).toFirst = NULL;
  (*l).isEmpty = TRUE;
  (*l).isFull = FALSE;
  (*l).size = 0;
}
/****************************************************
* listIsEmpty *
****************************************************/
//Inputs: list
//Outputs: int
//Purpose: returns TRUE or FALSE (1 or 0) 
int listIsEmpty (list l)
{
  return l.isEmpty;
}
/****************************************************
* listIsFull *
****************************************************/
//Inputs: list
//Outputs: int
//Purpose: returns TRUE or FALSE (1 or 0) 
int listIsFull (list l)
{
  return l.isFull;
}
/****************************************************
* listSize *
****************************************************/
//Inputs: list
//Outputs: int
//Purpose: returns the size of the list
int listSize (list l)
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
int listAdd (int el, list *l)
{
  struct node *temp;

  if ((*l).isFull)
  {
    return -1;
  }
  else 
  {
    temp = malloc(sizeof(struct node));
    if (!temp)
    {
      (*l).isFull = TRUE;
      return -1;
    }
    (*l).size++;
    if ((*l).toFirst != NULL)
    {
      (*l).toFirst -> prev = temp;
    }
    (*temp).prev = NULL;
    (*temp).element = el;
    (*temp).next = (*l).toFirst;
    (*l).toFirst = temp;
    if ((*l).isEmpty)
    {
      (*l).isEmpty = FALSE;
    }
    return 0;
  }
}
/****************************************************
* findPtrToPred *
****************************************************/
//Inputs: int, list
//Outputs: struct node
//Purpose: Returns the node that points to the specified index. 
struct node *findPtrToPred (int index, list l)
{
  int i = 0;
  struct node *p;

  if ((index < 1) || (index > l.size - 1))
    return NULL;
  else {
    p = l.toFirst;
    while (i < index - 1) {
      i++;
      p = p -> next;
    }
    return p;
  }
}

//NOTE: Error checking done in findPtrToPred
/****************************************************
* listPred *
****************************************************/
//Inputs: int, list
//Outputs: int
//Purpose: returns the value of the previous node.
int listPred (int index, list l)
{
  int el;
  struct node *p;

  p = findPtrToPred(index, l);
  if (p)
    return p -> element;
  else
    return -1;
}
/****************************************************
* listSucc *
****************************************************/
//Inputs: index, list
//Outputs: int
//Purpose: returns the value of the next node.
int listSucc (int index, list l)
{
  int i = 0;
  struct node *p;

  if ((index < 0) || (index >= l.size - 2))
    return -1;
  else {
    p = l.toFirst;
    while (i <= index) {
      i++;
      p = p -> next;
    }
    return p -> element;
  }
}

//NOTE: Error checking done in findPtrToPred
/****************************************************
* listRemoveKey *
****************************************************/
//Inputs: index, list
//Outputs: int
//Purpose: removes the node at the specified index from
//         the specified list.
int listRemoveKey (int index, list *l)
{
  struct node *p;
  struct node *temp;

  if (index == 0) { //Special case deleting 1st element
    temp = ((*l).toFirst) -> next;
    free((*l).toFirst);
    (*l).toFirst = temp;
    (*l).size--;
    if ((*l).size == 0)
    {
      (*l).isEmpty = TRUE;
    }
  }
  else { //Set predecessor's ptr to ptr of indexed element
    p = getNodeAtIndex(index, *l);
    temp = p;
    (*p).prev -> next = (*p).next;
    if ((*p).next != NULL)
    {
      (*p).next -> prev = (*p).prev;
    }
    free (temp);
    (*l).size--;
    if ((*l).isFull)
    {
      (*l).isFull = FALSE;
    }
  }
  return 0;
}
/****************************************************
* listIndexOf *
****************************************************/
//Inputs: int, list
//Outputs: int
//Purpose: returns the index of the specified value.
int listIndexOf (int key, list l)
{
  struct node *p;
  int i = 0;

  p = l.toFirst;
  while (p && (p -> element != key)) {
    p = p -> next;
    i++;
  }
  if (p)
    return i;
  return -1;
}

//New function
//Starts looking for an element starting at position start
//Effectively replaces the original listIndexOf by
//setting start to 1
/*
int listNextIndexOf (int start, int element, list l)
{
int i;

if ((start < 1) || (start > l[0]))
return -1;
for (i = start; i < l[0] + 1; i++)
if (l[i] == element)
return i;
return -1;
}
*/

//New function
//Returns the element at the specified index
/****************************************************
* listElementAt *
****************************************************/
//Inputs: int, list
//Outputs: int
//Purpose: returns the value of the node at index.
int listElementAt (int index, list l)
{
  struct node *p;
  int i;

  if ((index < 1) || (index > l.size))
    return -1;
  for (i = 1, p = l.toFirst; i < index; i++, p = p -> next)
    ;
  return p -> element;
}
/****************************************************
* listPrint *
****************************************************/
//Inputs: list
//Outputs: none
//Purpose: prints the list.
void listPrint (list l)
{
  struct node *p;

  p = l.toFirst;
  while (p) {
    printf("%d ", p -> element);
    p = p -> next;
  }
  printf("\n");
}
/****************************************************
* getNodeAtIndex *
****************************************************/
//Inputs: int, list
//Outputs: node
//Purpose: Returns the node at the given index. 
struct node *getNodeAtIndex(int index, list l)
{
  int i = 0;
  struct node *p;

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
/****************************************************
* listPrintReverse *
****************************************************/
//Inputs: list
//Outputs: none
//Purpose: prints the list in reverse utilizing the
//         doubly linked lists prev pointer. 
void listPrintReverse (list l)
{
  struct node *p;

  p = getNodeAtIndex(l.size-1, l);
  while (p) 
  {
    printf("%d ", p -> element);
    p = (*p).prev;
  }
  printf("\n");
}