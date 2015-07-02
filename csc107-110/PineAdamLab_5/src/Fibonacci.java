/**
 * A class that gives us the Fibonacci sequence.
 * @author Adam Pine
 *
 */
public class Fibonacci
{
	private long oneBack = 1;
	private long twoBack = 0;
	
	/**
	 * @return the next value in the sequence
	 */
	public long findNext()
	{
		long result = oneBack + twoBack;
		if (result < oneBack)
		{
			oneBack = 0;
			twoBack = 1;
			result = 0;
		} else
		{
			twoBack = oneBack;
			oneBack = result;
		}
		return result;
	}

	/**
	 * @return the most recent value we generated
	 */
	public long getOneBack()
	{
		return oneBack;
	}

	/**
	 * @return the previous value we generated
	 */
	public long getTwoBack()
	{
		return twoBack;
	}
}
