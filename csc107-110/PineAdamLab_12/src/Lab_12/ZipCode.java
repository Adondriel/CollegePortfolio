package Lab_12;

/**
 * @author Adam Pine The Zip Code Class
 * 
 *         NEEDS TO BE RECODED TO MAKE THE 0's BE ADDED IN THE CONSTRUCTION SO
 *         THAT THE ZEROS ARE PLACED INTO THE ARRAY!!!!!
 */
public class ZipCode
{
	private int[] zipDigits;
	private static final int AMOUNT_OF_DIGITS = 5;
	private static final int AMOUNT_OF_LETTERS = 32;
	private static final String[] CODE =
	{ "||'''",// 0
			"'''||",// 1
			"''|'|",// 2
			"''||'",// 3
			"'|''|",// 4
			"'|'|'",// 5
			"'||''",// 6
			"|'''|",// 7
			"|''|'",// 8
			"|'|''" };// 9

	/**
	 * @param zip
	 *            Construct the Zip code. (digit version)
	 */
	public ZipCode(int zip) throws ZipCodeException
	{
		if (zip >= Math.pow(10, AMOUNT_OF_DIGITS))
		{
			throw new ZipCodeException("The zip code is too large.");
		}
		if (zip <= 0)
		{
			throw new ZipCodeException("The zip code is too small(negative)!");
		}
		String finalString = "" + zip;
		if (finalString.length() < AMOUNT_OF_DIGITS)
		{
			int numOfZeros = AMOUNT_OF_DIGITS - finalString.length();
			for (int i = 0; i < numOfZeros; i++)
			{
				finalString = "0" + finalString;
			}
		}
		zipDigits = new int[finalString.length()];
		for (int i = 0; i < finalString.length(); i++)
		{
			String tempCharToString = "" + finalString.charAt(i);
			zipDigits[i] = Integer.parseInt(tempCharToString);
		}
	}
	/**
	 * Constructor for string input.
	 * @param zipString
	 * @throws ZipCodeException
	 */
	public ZipCode(String zipString) throws ZipCodeException
	{
		// Check to make sure it is long enough
		if (zipString.length() != AMOUNT_OF_LETTERS)
		{
			throw new ZipCodeException(
					"Zipcode is either too long to too short!");
		}
		// Check framing bits
		if ((zipString.charAt(0) != '|')
				|| (zipString.charAt(zipString.length() - 1) != '|'))
		{
			throw new ZipCodeException("Zipcode has incorrect framing bits!");
		}

		String[] stringArray = new String[AMOUNT_OF_DIGITS];
		zipDigits = new int[AMOUNT_OF_DIGITS];

		for (int i = 0; i < AMOUNT_OF_DIGITS; i++)
		{
			stringArray[i] = zipString.substring(1 + (AMOUNT_OF_DIGITS * i),
					6 + (AMOUNT_OF_DIGITS * i));
		}
		//Check parity
		for (int i = 0; i < stringArray.length; i++)
		{
			for (int j = 0; j < CODE.length - 1; j++)
			{
				if (!stringArray[i].equals(CODE[j]))
				{
					if (j > 9)
					{
						throw new ZipCodeException("BAD PARITY. YOU ARE NOT WEIRD AL");
					}
				}
			}
		}

		for (int i = 0; i < AMOUNT_OF_DIGITS; i++)
		{
			for (int j = 0; j < CODE.length; j++)
			{
				if (stringArray[i].equals(CODE[j]))
				{
					zipDigits[i] = j;
				}
			}
		}
		
		
		//Check to make sure the decoded check digit actually 
		int sum = 0;
		for (int i = 0; i < zipDigits.length; i++)
		{
			sum = sum + zipDigits[i];
		}
		int CheckDigitDecode = 0;
		String checkDigitStringCode = zipString.substring(zipString.length()-6, zipString.length()-1);
		for (int i = 0; i < CODE.length; i++)
		{
			if (checkDigitStringCode.equals(CODE[i]))
			{
				CheckDigitDecode = i;
			}
		}
		if ((sum + CheckDigitDecode)%10 != 0)
		{
			throw new ZipCodeException("Check Digit is incorrect");
		}		
	}

	/**
	 * @return the check digit - all numbers added together, then the number of
	 *         integers needed to make the number divisible by 10.
	 */
	public int getCheckDigit()
	{
		int sum = 0;
		int checkDigit = 0;

		for (int i = 0; i < zipDigits.length; i++)
		{
			sum = sum + zipDigits[i];
		}
		while (sum % 10 != 0)
		{
			checkDigit++;
			sum++;
		}
		return checkDigit;
	}

	/**
	 * @return Returns the barcode as a string.
	 */
	public String getBarCode()
	{
		String barCode = "";
		for (int i = 0; i < zipDigits.length; i++)
		{
			barCode = barCode + CODE[zipDigits[i]];
		}
		barCode = "|" + barCode + CODE[getCheckDigit()] + "|";
		return barCode;
	}

	/**
	 * Turns the zipcode into a string.
	 */
	public String toString()
	{
		String finalString = "";
		for (int i = 0; i < zipDigits.length; i++)
		{
			finalString = finalString + zipDigits[i];
		}
		return finalString;
	}
}
