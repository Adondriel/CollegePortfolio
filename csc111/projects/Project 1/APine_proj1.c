/****************************************************
* Project 1 *
* Programmer: Adam Pine *
* 3/31 *
* Project 1 Tic Tac Toe*
****************************************************/
#include <stdio.h>
#define TABLESIZE 3

char table[TABLESIZE][TABLESIZE] = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
void printTable();
void doUserInput(char *s);
void doComputerTurn();
void checkWin();

int main (int argc, char* argv[])
{
	printTable();
	int i, j = 0;	
	printf("Please enter where you want to go: in the format (x, y)\n");
	while ( i == 0)
	{
		printf("Please enter where you want to go: in the format (x, y)\n");
		char input[6];
		gets(input);
		//printf("Please enter where you want to go: in the format (x, y)\n");
		doUserInput(input);
		printTable();
		doComputerTurn();
		checkWin();
	}
}

void printTable()
{
	int i, j = 0;
	for (i = 0; i < TABLESIZE; i++)
	{
		for (j = 0; j<TABLESIZE; j++)
		{
			printf("[%c]", table[i][j]);
		}
		printf("\n");
	}
	printf("\n");
	return;
}

void doUserInput(char *s)
{
	int i, x, y = 0;
	x = *(s+1) - 48;
	y = *(s+4) - 48;
	printf("%d ", x);
	printf("%d ", y);
	printf("%d \n", table[x][y]);
	if (x > 2 || y > 2)
	{
		printf("Error, must be between 0, and 2.");
		doUserInput(s);
	}
	if (table[x][y] != ' ')
	{
		printf("You cannot place that there!\n");
		return;
	}else
	{
		table[x][y] = 'x';
		printTable(table);
		printf("\n");
	}			
	return;
}

void doComputerTurn()
{
	int r1 = rand() % 3;
	int r2 = rand() % 3;
	if (table[r1][r2] != ' ')
	{
		doComputerTurn();
	}else
	{
		table[r1][r2] = 'o';
		printTable(table);
		printf("\n");
	}			
	return;
}

void checkWin()
{
	//X's
	int i, j, k, l = 0;
	int xes, oes = 0;
	for (i = 0; i < 3; i++)
	{
		for (j = 0; j < 3; j++)
		{
			if (table[i][j] == 'x')
			{
				xes++;
			}
		}
	}
	if (xes == 3)
	{
		printf("Player wins!");
		return;
	}
	xes = 0;


	for (i = 0; i < 3; i++)
	{
		for (j = 0; j < 3; j++)
		{
			if (table[j][i] == 'x')
			{
				xes++;
			}
		}
	}
	if (xes == 3)
	{
		printf("Player wins!");
		return;
	}
	xes = 0;

	if (((table[0][0] == 'x') && (table[1][1] == 'x') && (table[2][2] == 'x') )|| ((table[2][0] == 'x') && (table[1][1] == 'x') && (table[0][2] == 'x')))
	{
		printf("Player wins!");
		return;
	}
	//End X's


	//O's
	for (i = 0; i < 3; i++)
	{
		for (j = 0; j < 3; j++)
		{
			if (table[i][j] == 'o')
			{
				oes++;
			}
		}
	}
	if (oes == 3)
	{
		printf("Computer wins! 1");
		return;
	}


	oes = 0;
	for (i = 0; i < 3; i++)
	{
		for (j = 0; j < 3; j++)
		{
			if (table[j][i] == 'o')
			{
				oes++;
			}
		}
	}
	if (oes == 3)
	{
		printf("Computer wins! 2");
		return;
	}
	oes = 0;

	if ( ((table[0][0] == 'o') && (table[1][1] == 'o') && (table[2][2] == 'o')) || ((table[2][0] == 'o') && (table[1][1] == 'o') && (table[0][2] == 'o')))
	{
		printf("Computer wins! 3");
		return;
	}



return;
}


