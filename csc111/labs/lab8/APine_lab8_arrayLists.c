
#include <stdio.h>

#define MAXSIZE 10
#define TRUE 1
#define FALSE 0

typedef int boolean;
typedef int arrayList[MAXSIZE + 1] ;

void arrayListCreate (arrayList l)
{
  l[0] = 0;
}

int arrayListIsEmpty (arrayList l)
{
  if (l[0] == 0)
    return TRUE;
   else
    return FALSE;
}

int arrayListIsFull (arrayList l)
{
  if (l[0] == MAXSIZE)
    return TRUE;
   else
    return FALSE;
}

int arrayListSize (arrayList l)
{
  return l[0];
}

int arrayListAdd (int el, arrayList l)
{
  if (arrayListIsFull(l))
    return -1;
  else {
    l[0]++;
    l[l[0]] = el;
    return 0;
  }
}

int arrayListPred (int index, arrayList l)
{
  if ((index < 2) || (index > l[0]))
    return -1;
  return l[index - 1];
}

int arrayListSucc (int index, arrayList l)
{
  if ((index < 1) || (index > l[0] - 1))
    return -1;
  return l[index + 1];
}

int arrayListRemoveKey (int index, arrayList l)
{
  int i;

  if ((i < 1) || (i > l[0]))
    return -1;
  for (i = index; i < l[0]; i++)
    l[i] = l[i + 1];
  l[0]--;
  return 1;
}

int arrayListIndexOf (int element, arrayList l)
{
  int i;

  for (i = 1; i < l[0] + 1; i++)
    if (l[i] == element)
      return i;
  return -1;
}

//New function
//Starts looking for an element starting at position start
//Effectively replaces the original arrayListIndexOf by
//setting start to 1
int arrayListNextIndexOf (int start, int element, arrayList l)
{
  int i;

  if ((start < 1) || (start > l[0]))
    return -1;
  for (i = start; i < l[0] + 1; i++)
    if (l[i] == element)
      return i;
  return -1;
}

//New function
//Returns the element at the specified index
int arrayListElementAt (int index, arrayList l)
{

  if ((index < 1) || (index > l[0]))
    return -1;
  return l[index];
}

void arrayListPrint (arrayList l)
{
  int i;

  for (i = 1; i <= l[0]; i++)
    printf("%d ", l[i]);
  printf("\n");
}
