import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


/**
 * @author Adam Pine
 * Tests the SentenceCounter Class
 *
 */
public class TestSentenceCounter
{
	private static final String SENTENCE1 = "This is my sentence.";
	private static final String SENTENCE2 = "These words make another sentence that is longer";
	private static final String SENTENCE3 = " These words make another sentence that is longer ";
	private SentenceCounter sc1;
	private SentenceCounter sc2;
	private SentenceCounter sc3;
	/**
	 * Setup our sentences
	 */
	@Before
	public void setup()
	{
		sc1 = new SentenceCounter(SENTENCE1);
		sc2 = new SentenceCounter(SENTENCE2);
		sc3 = new SentenceCounter(SENTENCE3);
	}

	/**
	 * Tests that our constructor actually constructs things!
	 */
	@Test
	public void testConstructor()
	{
		assertEquals(SENTENCE1, sc1.getSentence());
		assertEquals(SENTENCE2, sc2.getSentence());
	}
	
	/**
	 * Test the countBlanks method to count the number of spaces.
	 */
	@Test
	public void testCountBlanks()
	{
		assertEquals(3, sc1.countBlanks());
		assertEquals(7, sc2.countBlanks());
		assertEquals(9, sc3.countBlanks());		
	}
	
	
	/**
	 * Test the countLowerCase() function to see if it accurately counts the lower case letters.
	 */
	@Test
	public void testCountLower()
	{
		assertEquals(15, sc1.countLowerCase());
		assertEquals(40, sc2.countLowerCase());
	}
	
	/**
	 * Test the bordercase of the lowercase counter.
	 */
	@Test
	public void testCountLowerBorder()
	{
		SentenceCounter borderCounter = new SentenceCounter((char)96 + "az}");
		assertEquals(2, borderCounter.countLowerCase());
	}
	
	/**
	 * Test the firstblackpostion method to ensure it accurately finds the correct first space.
	 */
	@Test
	public void testFirstBlankPosition()
	{
		assertEquals(4, sc1.firstBlankPosition());
		assertEquals(5, sc2.firstBlankPosition());
	}
	
	/**
	 * Tests to see if the method will crash and die if we don't give it any spaces
	 */
	@Test
	public void testFirstBlankPositionNoBlanks()
	{
		SentenceCounter sc = new SentenceCounter("NoBlanksInThisSentence");
		assertEquals(-1, sc.firstBlankPosition());
	}
	
	/**
	 * Test the blankPosition function to see if it returns the correct index of the nthSpace that we pass it.
	 */
	@Test
	public void testBlankPosition()
	{
		assertEquals(10, sc1.blankPosition(3));
		assertEquals(24, sc2.blankPosition(4));
	}
	
	/**
	 * Test to make sure we don't crash if we request a blank that doesn't exist
	 */
	@Test
	public void testBlankPositionTooFar()
	{
		assertEquals(-1, sc1.blankPosition(4));
	}
	
	/**
	 * Test our contains function to see if it determines what each sentence contains.
	 */
	@Test
	public void testContains()
	{
		assertTrue(sc1.contains('T'));
		assertTrue(sc1.contains('y'));
		assertFalse(sc1.contains('z'));
		assertFalse(sc1.contains('Y'));
	}
	
	/**
	 * Test the count occurrences method
	 */
	@Test
	public void testCountOccurrences()
	{
		assertEquals(1, sc1.countOccurrences("ence"));
		assertEquals(2, sc1.countOccurrences("en"));
		assertEquals(1, sc2.countOccurrences("that"));
		assertEquals(0, sc2.countOccurrences("This"));
	}
	
	/**
	 * Tests the WildCardMatches method
	 */
	@Test
	public void testMatchesWildCard()
	{
		assertEquals(1, sc1.countWildCardMatches(".e..e..e"));
		assertEquals(2, sc2.countWildCardMatches("o..e"));
	}

}
