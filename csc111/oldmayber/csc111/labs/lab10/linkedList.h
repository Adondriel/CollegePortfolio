/****************************************************
* Lab 10                                            *
* Programmer: Dr. mooney, modified by Adam Pine     *
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

struct node {
  struct node *prev;
  int element;
  struct node *next;
};

typedef struct {
  boolean isFull;
  boolean isEmpty;
  int size;
  struct node *toFirst;
}list;

void listCreate (list *);
int listIsEmpty (list);
int listIsFull (list);
int listSize (list);
int listAdd (int, list *);
int listPred (int, list);
int listSucc (int, list);
int listRemoveKey (int, list *);
int listIndexOf (int, list);
//int listNextIndexOf (int, int, list);
int listElementAt (int, list);
void listPrint (list);
struct node *getNodeAtIndex(int, list);
void listPrintReverse (list);

