import java.util.ArrayList;

/**
 * @author Adam Pine
 * Out class that define all of our methods
 */
public class SentenceCounter
{
	private String sentence;

	/**
	 * @param sentence
	 * Constructs our instance variable
	 */
	public SentenceCounter(String sentence)
	{
		this.sentence = sentence;
	}

	/**
	 * @return sentence
	 * Returns our instance variable
	 */
	public String getSentence()
	{
		return sentence;
	}

	/**
	 * @return number of spaces in the sentence
	 */
	public int countBlanks()
	{
		int count = 0;
		for (int i = 0; i < sentence.length(); i++)
		{
			if (sentence.charAt(i) == ' ')
			{
				count++;
			}
		}
		return count;
	}

	/**
	 * @return number of lower case letters in our sentence
	 */
	public int countLowerCase()
	{
		int count = 0;
		for (int i = 0; i < sentence.length(); i++)
		{
			if ((sentence.charAt(i) >= 97) && (sentence.charAt(i) <= 122))
			{
				count++;
			}
		}
		return count;
	}

	/**
	 * @return the index of the first blank position in our sentence.
	 */
	public int firstBlankPosition()
	{
		int i;
		for (i = 0; i < sentence.length(); i++)
		{
			if (sentence.charAt(i) == ' ')
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * @param nthSpace
	 * @return the index of the nthSpace
	 */
	public int blankPosition(int nthSpace)
	{
		ArrayList<Integer> spaces = new ArrayList<Integer>();
		if (nthSpace > countBlanks())
		{
			return -1;
		}
		for (int i = 0; i < sentence.length(); i++)
		{
			if (sentence.charAt(i) == ' ')
			{
				spaces.add(i);
			}
		}
		return spaces.get(nthSpace - 1);
	}

	/**
	 * @param charInput
	 * @return true or false if that sentence contains the param.
	 */
	public boolean contains(char charInput)
	{
		if (sentence.contains(Character.toString(charInput)))
		{
			return true;
		}
		return false;
	}

	/**
	 * @param stringInput
	 * @return the number of times a string occurs in the sentence
	 */
	public int countOccurrences(String stringInput)
	{
		String testSubstring;
		int stringLength = stringInput.length();
		int count = 0;
		for (int i = 0; i < sentence.length(); i++)
		{
			if (sentence.charAt(i) == stringInput.charAt(0))
			{
				if ((i + stringLength) < sentence.length())
				{
					testSubstring = sentence.substring(i, i + stringLength);
					if (testSubstring.equals(stringInput))
					{
						count++;
					}
				}
			}

		}
		return count;
	}

	/**
	 * @param input
	 * @return the number of matches that occur in the string, using '.' as the wildcard.
	 */
	public int countWildCardMatches(String input)
	{
		int coorectInARowCount = 0;
		ArrayList<Character> notWildCardVal = new ArrayList<Character>();
		ArrayList<Integer> notWildCardPos = new ArrayList<Integer>();
		for (int i = 0; i < input.length(); i++)
		{
			if (input.charAt(i) != '.')
			{
				notWildCardVal.add(input.charAt(i));
				notWildCardPos.add(i);
			}
		}
		System.out.println(notWildCardVal.toString());
		System.out.println(notWildCardPos.toString());
		for (int i = 0; i < sentence.length(); i++)
		{
			if (sentence.charAt(i) == notWildCardVal.get(0))
			{
				for (int j = 1; j < notWildCardVal.size(); j++)
				{
					if (i + (notWildCardPos.get(j) - notWildCardPos.get(j - 1)) < sentence.length())
					{
						if (sentence.charAt(i + (notWildCardPos.get(j) - notWildCardPos.get(j - 1))) == notWildCardVal.get(j))
						{
							coorectInARowCount++;
						}
					}
				}
			}
		}
		if (coorectInARowCount == notWildCardVal.size())
		{
			return coorectInARowCount;
		}
		return 1;

	}

}
