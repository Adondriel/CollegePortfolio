/****************************************************
* Lab 3 *
* Programmer: Adam Pine *
* 2/10 *
* *
* Insertion sort in c *
****************************************************/
#include <stdio.h>
int main (int argc, char* argv[])
{
	int intArray[10];
	int input;
	int i;
	int j;
	for (i=0; i<10; i++)
	{
		printf("%s %d%s", "Please enter the", i, "th value:\n");
		scanf("%d", &input);
		intArray[i] = input;
	}
	for (i=0; i<10; i++)
	{
		printf("%d\n", intArray[i]);
	}

	for (i=1; i<10; i++)
	{
		for (j=i; j>0; j--)
		{
			if (intArray[j] < intArray[j-1])
			{
				//SWAP!
				int temp = intArray[j-1];
				intArray[j-1] = intArray[j];
				intArray[j] = temp;	
			}
		}	
	}
	for (i=0; i<10; i++)
	{
		printf("%d: %d\n", i, intArray[i]);
	}
	return 0;
}