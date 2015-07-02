import java.math.BigInteger;

/**
 * A class that supports arbitrarily large Fibonacci numbers
 * @author Adam Pine
 */
public class BigFibonacci
{
	private BigInteger oneBack = new BigInteger("1");
	private BigInteger twoBack = new BigInteger("0");
	
	/**
	 * @return the next value in the sequence
	 */
	public BigInteger findNext()
	{
		BigInteger result = oneBack.add(twoBack);
		twoBack = oneBack;
		oneBack = result;
		return result;
	}

	/**
	 * @return the most recent value we generated
	 */
	public BigInteger getOneBack()
	{
		return oneBack;
	}

	/**
	 * @return the previous value we generated
	 */
	public BigInteger getTwoBack()
	{
		return twoBack;
	}

}
