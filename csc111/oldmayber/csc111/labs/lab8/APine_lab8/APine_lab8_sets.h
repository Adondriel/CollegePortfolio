
#ifndef sets_h
	#define sets_h
	#include "APine_lab8_arrayLists.h"


	void listSetCreate(arrayList);
	int listSetSize (arrayList);
	boolean listSetIsEmpty(arrayList);
	boolean listSetIsFull(arrayList);
	int listSetInsert(int, arrayList);
	void listSetDeleteElement(int, arrayList);
	void listSetPrint (arrayList);
	boolean listSetIsSet(arrayList);
	void listSetUnion(arrayList, arrayList, arrayList l3);
	void listSetIntersect(arrayList, arrayList, arrayList l3);
	void listSetDifference(arrayList, arrayList, arrayList l3);
#endif












