/**************************************************
* <Lab 11>                                        *
* Programmer: <Adam Pine>                         *
* Feb 03 2015                                     *
*                                                 *
* To create a program that takes investment firm  *
* info, and displays it. With stream I/O          *
**************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <locale.h>
int MAXSIZE;
typedef struct {
	int code;
	int shares;
	float prevcps;
	float currcps;
}entry;

typedef struct {
	int index;
	int timesRealloced;
	int maxSize;
	entry *e;
}report;
void initReport(report *);
void addEntry(entry, report *);
void exportReport(report, FILE *);
int reportIsFull(report);

