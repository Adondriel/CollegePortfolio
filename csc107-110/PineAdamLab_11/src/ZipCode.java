
/**
 * @author Adam Pine
 * The Zip Code Class
 * 
 * NEEDS TO BE RECODED TO MAKE THE 0's BE ADDED IN THE CONSTRUCTION SO THAT THE ZEROS ARE PLACED INTO THE ARRAY!!!!!
 */
public class ZipCode
{
	private int[] zipDigits;
	private static final int AMOUNT_OF_DIGITS = 5;
	private static final String[] CODE =
		{"||'''",//0
		"'''||",//1
		"''|'|",//2
		"''||'",//3
		"'|''|",//4
		"'|'|'",//5
		"'||''",//6
		"|'''|",//7
		"|''|'",//8
		"|'|''"};//9
	
	/**
	 * @param zip
	 * Construct the Zip code.
	 */
	public ZipCode(int zip)
	{
		
		String finalString = ""+zip;
		if (finalString.length() < AMOUNT_OF_DIGITS)
		{
			int numOfZeros = AMOUNT_OF_DIGITS - finalString.length();
			for (int i = 0; i<numOfZeros;i++)
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
	 * @return the check digit - all numbers added together, then the number of integers needed to make the number divisible by 10.
	 */
	public int getCheckDigit()
	{
		int sum = 0;
		int checkDigit = 0;
		
		for (int i = 0; i < zipDigits.length; i++)
		{
			sum = sum + zipDigits[i];
		}		
		while (sum%10 != 0)
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
