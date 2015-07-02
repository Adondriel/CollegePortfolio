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
int main (int argc, char* argv[])
{
	int code, shares;
	float prevCPS, currCPS,prevCPSVal, currCPSVal, percentChange;

	scanf("%d %d %f %f", &code, &shares, &prevCPS, &currCPS);
	prevCPSVal = prevCPS*shares;
	currCPSVal = currCPS*shares;
	if (prevCPSVal > 999)
	{
		double prevFloor = floor(prevCPS);
		double prevRem = remainder (prevCPSVal,1000);
	}
	
	//What percent of 12000, is 11930?
	//11930/12000 = .994166667
	//1-.994166667 = .005833 which is almost .6%
	//equation, (1-(prevworth/currworth))
	percentChange = ((currCPSVal-prevCPSVal)/prevCPSVal)*10;
	printf("%s", "Code   Shares Prev CPS Curr CPS  Prev Worth  Curr Worth Change(%)\n");
	printf("%s", "-----------------------------------------------------------------\n");
	printf("%d  %d   $%.2f   $%.2f    $%.2f   $%.2f  %.1f\n", code, shares, prevCPS, currCPS, prevCPSVal, currCPSVal, percentChange*10);

	return 0;
}

