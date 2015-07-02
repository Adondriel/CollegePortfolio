/**************************************************
* <Lab 2>                                         *
* Programmer: <Adam Pine>                         *
* Feb 03 2015                                     *
*                                                 *
* To create a program that takes investment firm  *
* info, and displays it.                          *
**************************************************/
#include <stdio.h>
#include <math.h>
#include <string.h>
#include <locale.h>
int main (int argc, char* argv[])
{
	setlocale(LC_ALL,"");
	int code, shares;
	float prevCPS, currCPS,prevCPSVal, currCPSVal, percentChange;
	printf("Please input the information: (Enter a negative value for code to exit)\n");
	while (1)
	{
		scanf("%d %d %f %f", &code, &shares, &prevCPS, &currCPS);
		if (code < 0)
		{
			return 0;
		}
		else
		{
			prevCPSVal = prevCPS*shares;
			currCPSVal = currCPS*shares;
	
			//What percent of 12000, is 11930?
			//11930/12000 = .994166667
			//1-.994166667 = .005833 which is almost .6%
			//equation, ((currCPSVal-prevCPSVal)/prevCPSVal)*10
			percentChange = ((currCPSVal-prevCPSVal)/prevCPSVal)*10;
			printf("%s", "Code   Shares Prev CPS Curr CPS  Prev Worth  Curr Worth Change(%)\n");
			printf("%s", "-----------------------------------------------------------------\n");
			printf("%05d %7d $%7.2f $%7.2f $%'10.2f $%'10.2f %8.1f\n", code, shares, prevCPS, currCPS, prevCPSVal, currCPSVal, percentChange*10);
		}
	}
//	return 0; 
}




