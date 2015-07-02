
/**
 * @author Adam Pine
 * A class that represents a book in our library.
 */
public class Book
{
	/**
	 * Define our instance variables
	 */
	private String title;
	private boolean checkedOut;
	private int numberOfCheckOuts;

	/**
	 * Create the book and give it a title, tell it that it is not 
	 * checked out because we just put it in and it has not yet been
	 * checked out before.
	 * @param t
	 */
	public Book(String t)
	{
		title = t;
		checkedOut = false;
		numberOfCheckOuts = 0;
	}
	
	/**
	 * When we check out a book, we need to tell it that it is checked out,
	 * and that it has been checked out one more time than before.
	 */
	public void checkOut()
	{
		checkedOut = true;
		numberOfCheckOuts++;
	}
	
	/**
	 * @return The title of this book
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * @return Whether or not this book is checked out.
	 */
	public boolean isCheckedOut()
	{
		return checkedOut;
	}

	/**
	 * When we check in a book, make sure it knows that by setting the instance variable to false.
	 */
	public void checkin()
	{
		checkedOut = false;		
	}

	/**
	 * @return The number of times this book has been checked out.
	 */
	public int getNumberOfCheckOuts()
	{
		return numberOfCheckOuts;
	}
}
