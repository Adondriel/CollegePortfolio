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

int main (void)
{
  list ll;
  int x;

  listCreate(&ll);
  x = listSize(ll);
  printf("Size: %d\n", x);
  listAdd(2, &ll);
  x = listSize(ll);
  printf("Size: %d\n", x);
  listAdd(4, &ll);
  listAdd(6, &ll);
  listAdd(8, &ll);
  listAdd(10, &ll);
  listAdd(4, &ll);
  listAdd(12, &ll);
  listAdd(4, &ll);
  listAdd(14, &ll);
  listPrint(ll);
  listPrintReverse(ll);
  x = listSize(ll);
  printf("Size: %d\n", x);
  x = listIsFull(ll);
  printf("Is full: %d\n", x);
  x = listIsEmpty(ll);
  printf("Is empty: %d\n", x);
  x = listElementAt(1, ll);
  printf("Element 1: %d\n", x);
  x = listSize(ll);
  x = listElementAt(x, ll);
  printf("Element n: %d\n", x);
  x = listIndexOf(6, ll);
  printf("Index of 6: %d\n", x);
  x = listPred(x, ll);
  printf("Pred of 6: %d\n", x);
  x = listIndexOf(6, ll);
  x = listSucc(x, ll);
  printf("Succ of 6: %d\n", x);
  x = listIndexOf(2, ll);
  x = listPred(x, ll);
  printf("Pred of 2: %d\n", x);
  x = listIndexOf(14, ll);
  x = listSucc(x, ll);
  printf("Succ of 14: %d\n", x);
  x = listIndexOf(4, ll);
  printf("Index of 4: %d\n", x);
  //x = listNextIndexOf(x + 1, 4, ll);
  //printf("Next index of 4: %d\n", x);
  //x = listNextIndexOf(x + 1, 4, ll);
  //printf("Next index of 4: %d\n", x);
  x = listIndexOf(6, ll);
  listRemoveKey(x, &ll);
  printf("6 removed\n");
  listPrint(ll);
  x = listIndexOf(2, ll);
  listRemoveKey(x, &ll);
  printf("2 removed\n");
  listPrint(ll);
  x = listIndexOf(14, ll);
  listRemoveKey(x, &ll);
  printf("14 removed\n");
  listPrint(ll);
  x = listIndexOf(4, ll);
  listRemoveKey(x, &ll);
  printf("4 removed\n");
  listPrint(ll);
  x = listIndexOf(10, ll);
  listRemoveKey(x, &ll);
  printf("10 removed\n");
  listPrint(ll);
  x = listIndexOf(4, ll);
  listRemoveKey(x, &ll);
  printf("4 removed\n");
  listPrint(ll);
  x = listIndexOf(8, ll);
  listRemoveKey(x, &ll);
  printf("8 removed\n");
  listPrint(ll);
  x = listIndexOf(4, ll);
  listRemoveKey(x, &ll);
  printf("4 removed\n");
  listPrint(ll);
  x = listIndexOf(12, ll);
  listRemoveKey(x, &ll);
  printf("12 removed\n");
  listPrint(ll);

  return 0;
}
