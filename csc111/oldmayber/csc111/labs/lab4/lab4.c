/****************************************************
* Lab 4 *
* Programmer: Adam Pine *
* 2/24 *
* *
* Functions with lists *
****************************************************/
#include <stdio.h>
#define MAXSIZE 5
typedef int list[MAXSIZE + 1];
int main (int argc, char* argv[])
{
	list l;
	create(l);
	printf("%d\n", isEmpty(l));
	printf("%d\n", isFull(l));
	printf("%d\n", listSize(l));
	add(5,l);
	add(20,l);
	add(8,l);
	printList(l);
	printf("%d\n", isEmpty(l));
	printf("%d\n", isFull(l));
	printf("%d\n", listSize(l));
	addAt(3,2,l);
	printList(l);
	removeKey(1,l);
	printList(l);
	printf("%d\n", indexOf(20,l));
	printf("%d\n", indexOf(10,l));
 	printf("%d\n", pred(2,l));
	printf("%d\n", succ(2,l));
}

/****************************************************
* create *
****************************************************/
//Inputs: an list
//Outputs: none
//Purpose: Set the 0th pos to 0 because 0 elements in list

int create(list t)
{
	t[0] = 0;
	return 0;
}

/****************************************************
* listSize *
****************************************************/
//Inputs: a list
//Outputs: Integer: the size of the list.
//Purpose: return the size of the list

int listSize(list t)
{
	return t[0];
}

/****************************************************
* isEmpty *
****************************************************/
//Inputs: an list
//Outputs: Integer: 1, for true 0 for false.
//Purpose: Check if the list is empty

int isEmpty(list t)
{
	if (t[0] == 0)
	{
		return 1;
	}else
	{
		return 0;
	}	
}

/****************************************************
* isFull *
****************************************************/
//Inputs: an list
//Outputs: Integer: 1, for true 0 for false.
//Purpose: Check if the list is full

int isFull(list t)
{
	if (t[0] == MAXSIZE)
	{
		return 1;
	}else
	{
		return 0;
	}	
}

/****************************************************
* indexOf *
****************************************************/
//Inputs: the element search for(element), an list to look in(t).
//Outputs: Integer: the position of element
//Purpose: return the position of the element

int indexOf (int element, list t)
{
	int i;
	for (i=1;i<=listSize(t);i++)
	{
		if (t[i] == element)
		{
			return i;
		}
	}
	return -1;	
}

/****************************************************
* pred *
****************************************************/
//Inputs: the current index, the list to use.
//Outputs: Integer: The value at index-1
//Purpose: return the value at the previous index.

int pred (int index, list t)
{
	return t[index-1];
}

/****************************************************
* succ *
****************************************************/
//Inputs: the current index, the list to use.
//Outputs: Integer: The value at index-1
//Purpose: return the value at the next index.

int succ (int index, list t)
{
	return t[index+1];
}

/****************************************************
* add *
****************************************************/
//Inputs: The element to add, the list to use.
//Outputs: integer: -1 if list is full, 0 otherwise
//Purpose: return the value at the next index.

int add(int element, list t)
{
	if (t[0] != MAXSIZE)
	{
		t[t[0]+1] = element;
		t[0]++;
		return 0;
	}
	return -1;
}

/****************************************************
* addAt *
****************************************************/
//Inputs: The element to add, The index or where to add it, the list to use.
//			1 <= index <= listsize. (cannot insert into 0 pos, that's a no no)
//Outputs: integer: -1 if list is full, 0 if successful
//Purpose: add the element to the array at the index, in the list. 
//         must increment the 0th
int addAt(int element, int index, list t)
{
	if (listSize(t) >= MAXSIZE)
	{
		return -1;
	}else
	{	
		int i;
		for (i=listSize(t);i>=index;i--)
		{
			t[i+1] = t[i];
		}
		t[index] = element;
		t[0]++;
		return 0;
	}	
}

/****************************************************
* removeKey *
****************************************************/
//Inputs: The index or where to remove, the list to use.
//Outputs: integer: -1 if list is full, 0 otherwise
//Purpose: remove the value at the index
// 1 <= index <= list_size

int removeKey(int index, list t)
{	
	if ((1 <= index) || (index <= listSize(t)))
	{
		int i;
		for (i=index;i<listSize(t);i++)
		{
			t[i] = t[i+1];
		}
		t[0]--;
		return 0;
	}
	return -1;
}

/****************************************************
* printList *
****************************************************/
//Inputs: the list to print.
//Outputs: The list
//Purpose: print out the list, not including pos 0
// 1 <= index <= list_size

int printList(list t)
{
	int i;
	printf("List printed out:\n");
	for (i=listSize(t);i>0;i--)
	{
		printf("%d\n", t[i]);
	}
	printf("-----\n");
	return 0;
}




