import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


/**
 * @author Adam Pine
 * Tests the functionality provided by the Book class
 */
public class TestBook
{
	/**
	 * We need to define the different variables that we will be using in the test methods.
	 */	
	private static String bookTitle = "Catcher in The Rye";
	private Book book1;	
	
	/**
	 * We need to create a book that the other tests can use, so that we don't have a ton of duplicated code.
	 */
	@Before
	public void setUp()
	{		
		book1 = new Book(bookTitle);
	}

	/**
	 * When a book is created it should know its title and that it hasn't been checked out yet, cause it is brand new!
	 */
	@Test
	public void testInitialization()
	{
		assertEquals(bookTitle, book1.getTitle());
		assertEquals(0, book1.getNumberOfCheckOuts());
	}
	
	/**
	 * When a book is checked out it needs to know that it was actually checked out,
	 * and the number of checkouts that have occurred.
	 */
	@Test
	public void testCheckOut()
	{
		book1.checkOut();
		assertTrue(book1.isCheckedOut());		
		assertEquals(1, book1.getNumberOfCheckOuts());
	}
	
	/**
	 * When we check out the book more than once it needs to be sure that it is actually counting.
	 */
	@Test
	public void testCheckOutTwice()
	{
		book1.checkOut();
		assertTrue(book1.isCheckedOut());
		book1.checkOut();
		assertEquals(2, book1.getNumberOfCheckOuts());
	}
	
	/**
	 * When a book is checked in, it needs to know that it was checked in.
	 * So, we check out the book, then we check it back in, then we verify.
	 */
	@Test
	public void testCheckIn()
	{
		book1.checkOut();
		book1.checkin();
		assertFalse(book1.isCheckedOut());
		
	}

}
