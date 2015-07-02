package Lab_12;

/**
 * @author Adam Pine
 * Zipcode exception class
 */
public class ZipCodeException extends Exception
{
	private String message;
	
	/**
	 * @param string The error message
	 */
	public ZipCodeException(String string)
	{
		this.message = string;
	}
	
	/**
	 * Turns it into a string
	 */
	public String toString()
	{
		return message;
	}
}
