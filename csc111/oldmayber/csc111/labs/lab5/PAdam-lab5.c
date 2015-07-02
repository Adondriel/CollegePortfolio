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
void scanInfix(void);
void infixToPostfix(stack stk);


char infix[STACK_SIZE], postfix[STACK_SIZE];
int infixSize=0;
int postfixSize=0;

int main (int argc, char* argv[])
{
	stack myStack;
	stack evalStack;
	createStack(myStack);
	createStack(evalStack);

	printf("Please enter an infix expression");
	scanInfix();
	infixToPostfix(myStack);
	printf("%d", evaluate(evalStack));
}

void scanInfix()
{
	int i = 0;
	while (i < STACK_SIZE)
	{
		char c = getchar();
		if (c != ' ')
		{
			if (c == '\n')
			{
				infix[i] = '#';
				break;
			}
			infix[i] = c;
			infixSize++;
			printf("Scanned in: %c \n", infix[i]); 
		}
		i++;
	}
}

void infixToPostfix(stack stk)
{
	int i = 0;
	for (i = 0; i < infixSize; i++)
	{
		if ((infix[i] >= '0') && (infix[i] <= '9'))
		{
			postfix[postfixSize] = infix[i];
			postfixSize++;
		}else
		{
			if ((infix[i] == '+') || (infix[i] == '-') || (infix[i] == '*') || (infix[i] == '/'))
			{
				if (stk[0] == 0)
				{
					push(infix[i], stk);
				}else if ((infix[i] == '+') || (infix[i] == '-'))
				{
					int j;
					for (j = stk[0]; j > 0; j--)
					{
						postfix[postfixSize] = pop(stk);
						postfixSize++;
					}
					push(infix[i], stk);
				}else if ((infix[i] == '*') || (infix[i] == '/'))
				{
					int j;
					for (j = stk[0]; j > 0; j--)
					{
						if ((peek(stk) == '*') || (peek(stk) == '/'))
						{
							postfix[postfixSize] = pop(stk);
							postfixSize++;
						}
					}
					push(infix[i], stk);
				}
			}		
		}
	}

	for (i = stk[0]; i > 0; i--)
	{
		postfix[postfixSize] = pop(stk);
		postfixSize++;
	}
	printf("Postfix: \n");
	for (i = 0; i < postfixSize+2; i++)
	{
		printf("%c", postfix[i]); 
	}	
	printf("\ninfix: \n");
	for (i = 0; i < infixSize; i++)
	{
		printf("%c", infix[i]); 
	}
	printf("\nstack: \n");
	for (i = 1; i < stk[0]; i++)
	{
		printf("%c", stk[i]); 
	}
}

int evaluate(stack stk)
{	
	int i;
	for (i = 0; i < postfixSize; i++)
	{
		if ((postfix[i] >= '0') && (postfix[i] <= '9'))
		{
			push(postfix[i], stk);
		}else
		{
			int a = (int)pop(stk);
			int b = (int)pop(stk);
			if (a == '9')
			{
				a = 9;
			}
			if (a == '8')
			{
				a = 8;
			}
			if (a == '7')
			{
				a = 7;
			}
			if (a == '6')
			{
				a = 6;
			}
			if (a == '5')
			{
				a = 5;
			}
			if (a == '4')
			{
				a = 4;
			}
			if (a == '3')
			{
				a = 3;
			}
			if (a == '2')
			{
				a = 2;
			}
			if (a == '1')
			{
				a = 1;
			}
			if (a == '0')
			{
				a = 0;
			}
			if (b == '9')
			{
				b = 9;
			}
			if (b == '8')
			{
				b = 8;
			}
			if (b == '7')
			{
				b = 7;
			}
			if (b == '6')
			{
				b = 6;
			}
			if (b == '5')
			{
				b = 5;
			}
			if (b == '4')
			{
				b = 4;
			}
			if (b == '3')
			{
				b = 3;
			}
			if (b == '2')
			{
				b = 2;
			}
			if (b == '1')
			{
				b = 1;
			}
			if (b == '0')
			{
				b = 0;
			}
			if (postfix[i] == '+')
			{
				push(a+b, stk);
			}else if (postfix[i] == '-')
			{
				push(a-b, stk);
			}else if (postfix[i] == '*')
			{
				push(a*b, stk);
			}else if (postfix[i] == '/')
			{
				push(a/b, stk);
			}
		}
		
	}
	return pop(stk);
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
    printf("Pushed: %c\n", i);
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
    printf("Popped: %c\n", top);
    return top;
  }
}

int peek (stack stk)
{
	int top;
  
	if (stackEmpty(stk)) 
	{
		stackUnderflow();
		return -1;
 	}else 
 	{
		return top;
  	}
}





