/**************************************************
* <Lab 11>                                        *
* Programmer: <Adam Pine>                         *
* Feb 03 2015                                     *
*                                                 *
* To create a program that takes investment firm  *
* info, and displays it. With stream I/O          *
**************************************************/
#include "APine_lab11.h"
/****************************************************
* initReport*
****************************************************/
//Inputs: report *
//Outputs: none
//Purpose: Sets up a report for use.
void initReport(report *r)
{
	(*r).index = 0;
	(*r).timesRealloced = 0;
	(*r).maxSize = MAXSIZE;
	return;
}
/****************************************************
* addEntry*
****************************************************/
//Inputs: entry, report *
//Outputs: none
//Purpose: adds entry to the report
void addEntry(entry e, report *r)
{
	if (reportIsFull(*r) == 1)
	{
		(*r).e = (entry *)realloc((*r).e, sizeof(entry)*MAXSIZE*2);
		MAXSIZE = MAXSIZE*2;
	}
	(*r).e[(*r).index] = e;
	(*r).index++;
	return;
}
/****************************************************
* exportReport*
****************************************************/
//Inputs: report r, FILE *
//Outputs: none
//Purpose: Prints the report into the FILE.
void exportReport(report r, FILE *outFile)
{
	int i=0;
	fprintf(outFile, "%s", "Code   Shares Prev CPS Curr CPS  Prev Worth  Curr Worth Change(%)\n");
	fprintf(outFile, "%s", "-----------------------------------------------------------------\n");
	for (i = 0; i < r.index; i++)
	{
		float prevCPSVal, currCPSVal, percentChange;
		int thiscode, thisshares;
		float previouscps, currentcps;
		thiscode = r.e[i].code;
		thisshares = r.e[i].shares;
		previouscps = r.e[i].prevcps;
		currentcps = r.e[i].currcps;
		prevCPSVal = previouscps*thisshares;
		currCPSVal = currentcps*thisshares;
		percentChange = ((currCPSVal-prevCPSVal)/prevCPSVal)*100;
		if (thiscode < 0)
		{
			fprintf(outFile, "%s", "-----------------------------------------------------------------\n");
			return;
		}else
		{
			fprintf(outFile, "%05d %7d $%7.2f $%7.2f $%'10.2f $%'10.2f %8.1f\n", thiscode, thisshares, previouscps, currentcps, prevCPSVal, currCPSVal, percentChange);
		}
	}
	fprintf(outFile, "%s", "-----------------------------------------------------------------\n");
	printf("Successfully exported the reportData.dat.\n");
}
/****************************************************
* reportIsFull*
****************************************************/
//Inputs: report
//Outputs: int
//Purpose: returns 1, if the report is full otherwise, returns 0.
int reportIsFull(report r)
{
	if (r.index == r.maxSize)
	{
		return 1;
	}
	else
	{
		return 0;
	}
}