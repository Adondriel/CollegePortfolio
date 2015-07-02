/****************************************************
* Lab 3 *
* Programmer: Adam Pine *
* 2/10 *
* quick sort in c with pointers*
****************************************************/

/*********************************************************
 * From C PROGRAMMING: A MODERN APPROACH, Second Edition *
 * By K. N. King                                         *
 * Copyright (c) 2008, 1996 W. W. Norton & Company, Inc. *
 * All rights reserved.                                  *
 * This program may be freely distributed for class use, *
 * provided that this copyright notice is retained.      *
 *********************************************************/

/* qsort.c (Chapter 9, page 207) */
/* Sorts an array of integers using Quicksort algorithm */

#include <stdio.h>

#define N 10

void quicksort(int a[], int low, int high);
int split(int a[], int low, int high);

int main(void)
{
  int a[N], i;
  int key;

  printf("Enter %d numbers to be sorted: \n", N);
  for (i = 0; i < N; i++)
    scanf("%d", &a[i]);
  printf("Enter the value to search for\n");
	scanf("%d", &key);

  quicksort(&a[0], 0, N - 1);

  printf("In sorted order: ");
  for (i = 0; i < N; i++)
    printf("%d ", a[i]);
  printf("\n");

  if ((binarySearch(&a[0], key, a[0], a[N-1]) + 1) == key)
  {
  	printf("Key value found\n");
  }

  return 0;
}

void quicksort(int *p, int low, int high)
{
  int middle;

  if (low >= high) return;
  middle = split(p, low, high);
  quicksort(p, low, middle - 1);
  quicksort(p, middle + 1, high);
}

int split(int *q, int low, int high)
{
  int part_element = q[low];

  for (;;) {
    while (low < high && part_element <= q[high])
      high--;
    if (low >= high) break;
    q[low++] = q[high];

    while (low < high && q[low] <= part_element)
      low++;
    if (low >= high) break;
    q[high--] = q[low];
  }

  q[high] = part_element;
  return high;
}

int binarySearch (int *p, int key, int low, int high)
{
	if (high < low)
	{
		return -1;
	}
	int mid = (low+high)/2;
	if (p[mid] > key)
	{
		return binarySearch(p, key, low, mid-1);
	}	
	else if (p[mid] < key)
	{
		return binarySearch(p, key, mid+1, high);
	}
	else
	{
		return mid;
	}
	
}
