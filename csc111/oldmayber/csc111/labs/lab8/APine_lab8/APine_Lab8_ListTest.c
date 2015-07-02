/****************************************************
* Lab 8 *
* Programmer: Adam Pine *
* 4/7 *
* lab 8 ADTs and Headerfiles, and Make files*
****************************************************/
#include "APine_lab8_arrayLists.h"

int main (int argc, char* argv[])
{
	arrayList a;
	int x;
	
	arrayListCreate(a);
  	x = arrayListSize(a);
	printf("Size of a: %d\n", x);
	arrayListAdd(1, a);
  	arrayListAdd(2, a);
  	arrayListAdd(3, a);
  	arrayListAdd(4, a);
  	arrayListAdd(5, a);
  	arrayListAdd(6, a);
  	arrayListAdd(7, a);
  	arrayListAdd(8, a);
  	arrayListAdd(9, a);
	arrayListPrint(a);
	x = arrayListSize(a);
	printf("Size of a: %d\n", x);
	x = arrayListIsFull(a);
  	printf("List is full: %d\n", x);
  	x = arrayListIsEmpty(a);
  	printf("List is empty: %d\n", x);
  	x = arrayListElementAt(1, a);
  	printf("Element at 1: %d\n", x);
 	x = arrayListSize(a);
 	x = arrayListElementAt(x, a);
  	printf("Element n: %d\n", x);
  	x = arrayListIndexOf(6, a);
  	printf("Index of 6: %d\n", x);
  	x = arrayListPred(x, a);
 	printf("Pred of 6: %d\n", x);
  	x = arrayListIndexOf(6, a);
  	x = arrayListSucc(x, a);
  	printf("Succ of 6: %d\n", x);
  	x = arrayListIndexOf(2, a);
  	x = arrayListPred(x, a);
  	printf("Pred of 2: %d\n", x);
  	x = arrayListIndexOf(4, a);
  	x = arrayListSucc(x, a);
  	printf("Succ of 4: %d\n", x);
  	x = arrayListIndexOf(4, a);
  	printf("Index of 4: %d\n", x);
  	x = arrayListNextIndexOf(x + 1, 6, a);
  	printf("Next index of 6: %d\n", x);
  	x = arrayListNextIndexOf(x + 1, 8, a);
  	printf("Next index of 8: %d\n", x);
  	x = arrayListIndexOf(6, a);
  	arrayListRemoveKey(x, a);
  	arrayListPrint(a);

}
