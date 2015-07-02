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
	int entries;
	float prevCPSVal, currCPSVal, percentChange;
	float floatTable[10][2];
	int intTable[10][2];


	printf("Please input the information: (Enter a negative value for code to print the table)\n");
	int i;	
	for (i=0; i<10; i++)
	{
//                                       Code             Shares               PrevCPS            CurrCPS
		scanf("%d %d %f %f", &intTable[i][0], &intTable[i][1], &floatTable[i][0], &floatTable[i][1]);
		entries++;
		if (intTable[i][0] < 0 || i >=9 )
		{	
			//What percent of 12000, is 11930?
			//11930/12000 = .994166667
			//1-.994166667 = .005833 which is almost .6%
			//equation, ((currCPSVal-prevCPSVal)/prevCPSVal)*10

			printf("%s", "Code   Shares Prev CPS Curr CPS  Prev Worth  Curr Worth Change(%)\n");
			printf("%s", "-----------------------------------------------------------------\n");
			for (i=0; i<entries; i++)
			{
				prevCPSVal = floatTable[i][0]*intTable[i][1];
				currCPSVal = floatTable[i][1]*intTable[i][1];
				percentChange = ((currCPSVal-prevCPSVal)/prevCPSVal)*10;
				if (intTable[i][0] < 0)
				{
					printf("%s", "-----------------------------------------------------------------\n");
					return 0;
				}else
				{
					printf("%05d %7d $%7.2f $%7.2f $%'10.2f $%'10.2f %8.1f\n", intTable[i][0], intTable[i][1], floatTable[i][0], floatTable[i][1], prevCPSVal, currCPSVal, percentChange*10);
				}				
			}
			printf("%s", "-----------------------------------------------------------------\n");
		}    
	}
	return 0;	
}




