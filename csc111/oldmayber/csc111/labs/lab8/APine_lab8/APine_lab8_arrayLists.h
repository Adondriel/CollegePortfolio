#ifndef arrayLists_h
	#define arrayLists_h
	#define MAXSIZE 10
	#define TRUE 1
	#define FALSE 0
		
	#include <stdio.h>
	typedef int boolean;
	typedef int arrayList[MAXSIZE + 1];

	void arrayListCreate (arrayList);
	int arrayListIsEmpty (arrayList);
	int arrayListIsFull (arrayList);
	int arrayListSize (arrayList);
	int arrayListAdd (int, arrayList);
	int arrayListPred (int, arrayList);
	int arrayListSucc (int, arrayList);
	int arrayListRemoveKey (int, arrayList);
	int arrayListIndexOf (int, arrayList);
	int arrayListNextIndexOf (int, int, arrayList);
	int arrayListElementAt (int, arrayList);
	void arrayListPrint (arrayList);
#endif
