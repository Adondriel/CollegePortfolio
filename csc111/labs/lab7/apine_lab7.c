/****************************************************
* Lab 3 *
* Programmer: Adam Pine *
* 2/10 *
* lab 7 Strings*
****************************************************/
#include <stdio.h>
#include <string.h>
void strToUpper (const char *s, char *t);
void printStringArray(const char *s);
void strStrip (const char *s, char c, char *t);
int strIsSubstring (const char *s, const char *b);
void strSubstitute (const char *s, char k, char c, char *t);
void strReverse (const char *s, char *t);
int strPalindrome (const char *s);
void strSplit (const char *s, char c, int n, char *h, char *t);
int numberOfCharacters(const char *s, char c);

int main (int argc, char* argv[])
{
	char str[] = {"Every good boy does fine."};
	char str2[30] = {""};
	char str3[30] = {""};

	//Test Word Count
	int wordCount = strWordCount(str);
	printf("Word count: %d\n", wordCount);
	//end test word count
	//
	//Test str to upper.
	strToUpper(str, str2);
	printStringArray(str);
	printStringArray(str2);
	//end test str to upper.
	//
	//Test str Strip
	char c = 'o';
	strStrip(str, c, str2);
	printStringArray(str);
	printStringArray(str2);
	//end test str Strip
	//
	//Test str Substring
	char d[] = {"boy"};
	printf("Where does the word boy start? %d", strIsSubstring(str, d));
	//end test str is Substring
	//
	//Test strSubstitute
	char e, g;
	e = 'g';
	g = 'o';
	strSubstitute(str, e, g, str2);
	printStringArray(str);
	printStringArray(str2);
	//end test strSubstitute
	//
	//Test strReverse
	strReverse(str, str2);
	printStringArray(str);
	printStringArray(str2);
	//Test Palindrome
	char tacocat[] = {"tacocat"};
	printf("Is tacocat a Palindrome? %d", strPalindrome(tacocat));
	//end palindrome test
	//
	//Test strSplit
	strSplit (str, 'o', 3, str3, str2);
	printf("The starting str\n");
	printStringArray(str);
	printf("The second half of str\n");
	printStringArray(str2);
	printf("The first half of str\n");
	printStringArray(str3);
	
}

/****************************************************
* strWordCount *
****************************************************/
//Inputs: String, with spaces
//Outputs: integer
//Purpose: find number of words in a string, separated by space.
int strWordCount (const char *s)
{
	int i, n = 0;
	int size = strlen(s);
	for (i = 0; i < size; i++)
	{
		//printf("s of %d: %d\n", i, s[i]);
		if (s[i] == ' ')
		{
			n++;
			//printf("value of n at %d: %d\n", i, n);
		}
	} 	
	return n+1;
}


/****************************************************
* strToUpper *
****************************************************/
//Inputs: char string, char string.
//Outputs: none
//Purpose: Returns in t string s with every character in s converted to upper case.
void strToUpper (const char *s, char *t)
{
	//printf("a: %d A: %d\n", 'a', 'A');
	//printf("b: %d B: %d\n", 'b', 'B');
	//printf("z: %d Z: %d\n", 'z', 'Z');
	int i = 0;
	int size = strlen(s);
	for (i = 0; i < size; i++)
	{
		if ((s[i] <= 122) && (s[i] >= 97))
		{ 
			//printf("%d\n", s[i]);
			t[i] = s[i] - 32;
			//printf("%d\n", t[i]);
		}
	}
	
	return;
}

/****************************************************
* strStrip *
****************************************************/
//Inputs: char string, single char, char string
//Outputs: none
//Purpose: Returns in t string s with every instance of c in s removed.

void strStrip (const char *s, char c, char *t)
{
	int i, j = 0;
	int size = strlen(s);
	for (i = 0; i < size; i++)
	{
		t[i] = ' ';
	}
	for (i = 0; i < size; i++)
	{
		if (s[i] != c)
		{
			t[j] = s[i];
			j++; 
		}
	}
	return;
}

/****************************************************
* strIsSubstring *
****************************************************/
//Inputs: char string, char string
//Outputs: int
//Purpose: If b is a substring of s, then the position of the first occurence of b in s is returned; otherwise, -1 is returned.

int strIsSubstring (const char *s, const char *b)
{
	int i, j, k, l = 0;
	int sizeB = strlen(b)+1;
	int sizeS = strlen(s);
	for (i=0; i < (sizeS-sizeB); i++)
	{
		if (s[i] == b[0])
		{
			for (j=1; j<sizeB; j++)
			{
				k = i;
				if (s[k] == b[j])
				{
					k++;
					l++;
				}
				if (j == sizeB-1)
				{
					//printf("s of %d: %c", i, s[i]);
					return i;
				}
			}
		}
	}
	return -1;
}

/****************************************************
* strSubstitute *
****************************************************/
//Inputs: char string, single char, single char, char string.
//Outputs: int
//Purpose: Returns in t the result of substituting c for k in s. 

void strSubstitute (const char *s, char k, char c, char *t)
{
	int i, j = 0;
	int size = strlen(s);
	for (i = 0; i < size; i++)
	{
		if (s[i] == c)
		{
			t[i] = k;
		}else
		{
			t[i] = s[i];
		}		
	}
	return;
}

/****************************************************
* strReverse *
****************************************************/
//Inputs: char string, char string
//Outputs: none
//Purpose: Returns in t the reverse of string s.

void strReverse (const char *s, char *t)
{
	int i, j = 0;
	int size = strlen(s);

	for (i = size-1; i >= 0; i--)
	{	
		t[j] = s[i];
		j++;
	}
	return;
}

/****************************************************
* strPalindrome *
****************************************************/
//Inputs: char string
//Outputs: int
//Purpose: Returns 1 if s is a palindrome, 0 otherwise

int strPalindrome (const char *s)
{
	int i, j = 0;
	int size = strlen(s);
	char d[40] = {""};
	for (i = size-1; i >= 0; i--)
	{	
		d[j] = s[i];
		j++;
	}
	printStringArray(d);
	printStringArray(s);
	j = 0;
	for (i = 0; i < size; i++)
	{
		if (s[i] == d[i]);
		{
			j++;
		}
	}
	printf("value of j: %d", j);
	if (j == size)
	{
		return 1;
	}else
	{
		return 0;
	}
}

/****************************************************
* strSplit *
****************************************************/
//Inputs: char string, single char, int, char string, char string.
//Outputs: none
//Purpose: Splits s into two substrings. The split occurs at the nth (counting from zero) occurence of c (with c in the second of the two parts). The first half is returned in h, the second in t . If n = 0,  s is returned in t and h is the empty string. If there are fewer than n occurrences of c in s, then h gets s and t is the empty string.
void strSplit (const char *s, char c, int n, char *h, char *t)
{

	int i, j, k, l, m = 0;
	int globalI;
	int size = strlen(s);
	int sizeT = strlen(t);
	int sizeH = strlen(h);
	int numOfC = numberOfCharacters(s, c);
	printf("Numb of %c: %d", c, numOfC);


	for (i = 0; i < sizeT; i++)
	{
		t[i] = ' ';
		h[i] = ' ';
	}
	if (n == 0)
	{	
		for (i = 0; i < size; i++)
		{
			t[i] = s[i];
		}
	}
	if (numOfC < n)
	{
		for (i = 0; i < size; i++)
		{
			h[i] = s[i];
		}
	}
	for (i = 0; i < size; i++)
	{
		if (s[i] == c)
		{
			j++;
		}
		if (j == n)
		{
			globalI = i;
			break;
		}
	}
	printf("glboalI is: %d", globalI);
	for (k = 0; k < globalI; k++)
	{
		h[k] = s[k];
	}
	for (l = globalI; l < size; l++)
	{
		t[m] = s[l];
		m++;
	}
	return;
}

void printStringArray(const char *s)
{
	int size = strlen(s);
	int i;
	for (i = 0; i < size; i++)
	{
		printf("%c", s[i]);
	}
	printf("\n");	
	return;
}

int numberOfCharacters(const char *s, char c)
{
	int i, j = 0;
	int size = strlen(s);
	for (i = 0; i < size; i++)
	{
		if (s[i] == c)
		{
			j++;
		}
	}
	return j;
}



















