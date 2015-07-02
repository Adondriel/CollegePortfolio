#include "APine_lab8_arrayLists.h"
/****************************************************
* listSetCreate *
****************************************************/
//Inputs: arrayList 
//Outputs: nothing
//Purpose: create set
void listSetCreate(arrayList l)
{
	arrayListCreate(l);
	return;	
}
/****************************************************
* ListSetSize *
****************************************************/
//Inputs: arrayList
//Outputs: Int
//Purpose: Return size

int listSetSize (arrayList l)
{
	int x = arrayListSize(l);
	return x;
}
/****************************************************
* listSetIsEmpty *
****************************************************/
//Inputs: arrayList
//Outputs: boolean
//Purpose: return if a set is empty

boolean listSetIsEmpty(arrayList l)
{
	if (arrayListIsEmpty(l))
	{
		return TRUE;
	}
	return FALSE;
}
/****************************************************
* ListSetIsFull *
****************************************************/
//Inputs: arrayList
//Outputs: boolean
//Purpose: return if a set is full or not

boolean listSetIsFull(arrayList l)
{
	if (arrayListIsFull(l))
	{
		return TRUE;
	}
	return FALSE;
}
/****************************************************
* listSetInsert *
****************************************************/
//Inputs: int, arrayList
//Outputs: int, -1 if error.
//Purpose: to add a value to a set.

int listSetInsert(int element, arrayList l)
{
	if (listSetIsFull(l))
	{
		printf("%d", l[1]);
		return -1;
	}
	if (arrayListNextIndexOf(1, element, l) == -1)
	{
		arrayListAdd(element, l);
		return 0;
	}
	return -1;
}
/****************************************************
* listSetDeleteElement *
****************************************************/
//Inputs: int, arrayList
//Outputs: edits the given arraylist
//Purpose: to delete an element from an set

void listSetDeleteElement(int element, arrayList l)
{	
	int x = arrayListNextIndexOf(1, element, l);
	if (x != -1)
	{
		arrayListRemoveKey(x, l);
	}
	return;
}
/****************************************************
* listSetPrint *
****************************************************/
//Inputs: arrayList
//Outputs: the arraylist
//Purpose: to print the set

void listSetPrint (arrayList l)
{
	arrayListPrint(l);
	return;
}
/****************************************************
* listSetIsSet *
****************************************************/
//Inputs: arrayList
//Outputs: boolean
//Purpose: Returns whether or not the arraylist is a set or not

boolean listSetIsSet(arrayList l)
{	
	int size = l[0];
	int i, j;
	int temp[size];
	listSetCreate(temp);
	for (i = 1; i < size; i++)
	{
		if (arrayListNextIndexOf(1, l[i], temp) == -1)
		{
			listSetInsert(l[i], temp);
		}
		else
		{
			return FALSE;
		}
	}
	return TRUE;

/*
	for (i = 1; i < size; i++)
	{
		for (j = i; j < size - i; j++)
		{
			if (l[i] == l[j])
			{
				return FALSE;
			}
		}
	}
	return TRUE;
*/
}
/****************************************************
* listSetUnion *
****************************************************/
//Inputs: arrayList, arrayList, arrayList
//Outputs: none
//Purpose: makes a union, between l1 and l2 inside of l3

void listSetUnion(arrayList l1, arrayList l2, arrayList l3)
{
	//take list 1 copy everything to list 3
	//loop through l2, if l3 does not have it add it.
	int l1Size = l1[0];
	int l2Size = l2[0];
	int i, j;
	for (i = 1; i <= l1Size; i++)
	{
		listSetInsert(l1[i], l3);
	}
	for (j = 1; j <= l2Size; j++)
	{
		if (arrayListNextIndexOf(1, l2[j], l3) == -1)
		{
			listSetInsert(l2[j], l3);
		}
	}
}
/****************************************************
* listSetIntersect *
****************************************************/
//Inputs: arrayList, arrayList, arrayList
//Outputs: the intersection of l1 and l2 into l3
//Purpose: to make an intersection of l1 and l2 into l3

void listSetIntersect(arrayList l1, arrayList l2, arrayList l3)
{
	int size = MAXSIZE;
	int l1Size = l1[0];
	int l2Size = l2[0];
	int i, j, k = 1;
	for (i = 1; i <= l1Size; i++)
	{
		for (j = 1; j <= l2Size; j++)
		{
			if (l1[i] == l2[j])
			{
				listSetInsert(l1[i], l3);
			}
		}
	}
}
/****************************************************
* listSetDifference *
****************************************************/
//Inputs: arrayList, arrayList, arrayList
//Outputs: none
//Purpose: to place the difference between l1, and l2 into l3.

void listSetDifference(arrayList l1, arrayList l2, arrayList l3)
{
	int l1Size = l1[0];
	int l2Size = l2[0];
	int i,j,k;
	for (i = 1; i <= l1Size; i++)
	{
		if (arrayListNextIndexOf(1, l1[i], l2) == -1)
		{
			listSetInsert(l1[i], l3);
		}
	}
}













