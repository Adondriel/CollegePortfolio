/****************************************************
* Lab 5 *
* Programmer: Adam Pine *
* 2/24 *
* *
* Stacks *
****************************************************/
#include <stdio.h>
#define STACK_SIZE 20
#define TRUE 1
#define FALSE 0

typedef int Boolean;
typedef int stack[STACK_SIZE];

void createStack (stack stk);
Boolean stackEmpty (stack stk);
Boolean stackFull (stack stk);
void stackOverflow(void);
void stackUnderflow(void);
void push (int i, stack stk);
int pop (stack stk);
void scanInfix(stack stk);


char infix[STACK_SIZE], postfix[STACK_SIZE];

int main (int argc, char* argv[])
{
	stack myStack;
	createStack(myStack);

	printf("Please enter an infix expression");
	scanInfix(myStack);
}

void scanInfix(stack stk)
{
	int i;
    char tempInfix[STACK_SIZE];
	int tempInfixSize;
	int infixSize=0;
	for (i = 0; i < STACK_SIZE; i++)
	{
		tempInfix[i] = getchar();
		tempInfixSize++;
		printf("Scanned in: %c.\n", tempInfix[i]); 
		if (tempInfix[i] == '\n')
		{
			break;
		}
	}
	for (i = 0; i < tempInfixSize; i++)
	{
		if (tempInfix[i] != ' ')
		{
			infix[infixSize] = tempInfix[i];
			infixSize++;
		}	 
	}	
	for (i = 0; i < infixSize; i++)
	{
		printf("%c", infix[i]);
	}
}

void createStack (stack stk)
{
  stk[0] = 0;
}

Boolean stackEmpty (stack stk)
{
  return stk[0] == 0;
}

Boolean stackFull (stack stk)
{
  return stk[0] == STACK_SIZE - 1;
}

void stackOverflow(void)
{
  printf("***Invalid push - stack full***\n");
}

void stackUnderflow(void)
{
  printf("***Invalid pop - stack empty***\n");
}

void push (int i, stack stk)
{
  if (stackFull(stk))
    stackOverflow();
  else {
    stk[0]++;
    stk[stk[0]] = i;
  }
}

int pop (stack stk)
{
  int top;
  
  if (stackEmpty(stk)) {
    stackUnderflow();
    return -1;
  }
  else {
    top = stk[stk[0]];
    stk[0]--;
    return top;
  }
}





