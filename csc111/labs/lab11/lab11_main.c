#include "lab11.h"
int main (int argc, char* argv[])
{
	MAXSIZE = 10;
	FILE *inFile, *outFile;
	inFile = fopen("stockData.dat", "r");
	if (!inFile)
	{
		printf("Please create a file named \"stockData.dat\"");
		exit(1);
	}
	outFile = fopen("stockRpt.dat", "w");
	if (!outFile)
	{
		printf("There was an error opening or creating \"stockRpt.dat\"");
		exit(1);
	}
	report *r;
	entry en;
	r = (report *)malloc(sizeof(report));
	r->e = malloc(sizeof(entry)*MAXSIZE);

	initReport(r);
	

	int x;
	while (!feof(inFile))
	{
		x = fscanf(inFile, "%d %d %f %f", &en.code, &en.shares, &en.prevcps, &en.currcps);
		if (x ==4)
		{
			addEntry(en, r);
		}
	} 
	exportReport(*r, outFile);
	close(inFile);
	close(outFile);
	return 0;
	
}