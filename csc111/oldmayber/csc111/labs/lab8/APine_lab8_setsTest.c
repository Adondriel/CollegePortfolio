/****************************************************
* Lab 8 *
* Programmer: Adam Pine *
* 4/7 *
* lab 8 ADTs and Headerfiles, and Make files*
****************************************************/

#include "APine_lab8_sets.h"

int main (int argc, char* argv[])
{
	arrayList list1, list2, list3, list4, list5;
	int x, y, z;
	
	listSetCreate(list1);
	listSetCreate(list2);
	listSetCreate(list3);
	listSetCreate(list4);
	listSetCreate(list5);
	printf("Size of list1: %d\n", listSetSize(list1));
	printf("Size of list2: %d\n", listSetSize(list2));
	printf("Size of list3: %d\n", listSetSize(list3));
	printf("Is list1 empty?: %d\n", listSetIsEmpty(list1));
	printf("Is list2 empty?: %d\n", listSetIsEmpty(list2));
	printf("Is list3 empty?: %d\n", listSetIsEmpty(list3));
	printf("Is list1 full?: %d\n", listSetIsFull(list1));
	printf("Is list2 full?: %d\n", listSetIsFull(list2));
	printf("Is list3 full?: %d\n", listSetIsFull(list3));
	x = listSetInsert(1, list1);
	listSetInsert(2, list1);
	listSetInsert(3, list1);
	listSetInsert(4, list1);
	listSetInsert(6, list1);
	listSetInsert(5, list1);
	listSetPrint(list1);
	printf("Size of list1: %d\n", listSetSize(list1));
	printf("Is list1 empty?: %d\n", listSetIsEmpty(list1));
	printf("Is list1 full?: %d\n", listSetIsFull(list1));
	listSetDeleteElement(6, list1);
	listSetPrint(list1);
	printf("Is list1 a set?: %d\n", listSetIsSet(list1));
	listSetInsert(4, list2);
	listSetInsert(5, list2);
	listSetInsert(6, list2);
	listSetInsert(7, list2);
	listSetInsert(8, list2);
	listSetUnion(list1, list2, list3);
	listSetPrint(list3);
	listSetIntersect(list1, list2, list4);
	listSetPrint(list4);
	listSetDifference(list1, list2, list5);
	listSetPrint(list5);
	
}
