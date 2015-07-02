/****************************************************
* Lab 10                                            *
* Programmer: Adam Pine     *
* 4/21                                              *
*                                                   *
* Modifies a singly linked list ADT to work and     *
* become a doubly linked list ADT                   *
****************************************************/
// Linked list implementation using pointers
// Singly linked
// Holds ints exclusively
// Zero-based indexing

#include <stdio.h>
#include <stdlib.h>
#include "boolean.h"
//in terms of free, 1 = taken, 0 = open.
typedef struct node{
  int free;
  int nodeSize;
  struct node *prev;
  struct node *next;
}node;

typedef struct {
  boolean isFull;
  boolean isEmpty;
  int size;
  node *toFirst;
  node *toLast;
}list;

int MAX;

void ramCreate (list *);
void ramIsEmpty (list *);
void ramIsFull (list *);
boolean ramHasRoom(int, list);
int ramSize (list);
int nodeAlloc (int, list *);
int nodeDealloc(int, list *);
node *getLastNode(list);
node *getNodeAtIndex(int, list);

//int listPred (int, list);
//int listSucc (int, list);
//int listRemoveKey (int, list *);
//int listIndexOf (int, list);
////int listNextIndexOf (int, int, list);
//int listElementAt (int, list);
//void listPrint (list);
//struct node *getNodeAtIndex(int, list);
//void listPrintReverse (list);

