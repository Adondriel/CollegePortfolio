import static org.junit.Assert.*;

import org.junit.Test;


/**
 * @author Adam Pine
 * The class containing the tests for our MySorter class.
 */
public class TestMySorter
{

	/**
	 * Tests Bubble sort on a small, randomly numbered array.
	 */
	@Test
	public void testBubbleSmall()
	{
		int[] x = {5, 3, 8, 1};
		MySorter s = new MySorter();
		s.bubbleSort(x);
		for(int i = 0; i < x.length-1; i++)
		{
			assertTrue("Out of order at " + i, x[i] < x[i+1]);
		}
	}
	
	/**
	 * Test max position for an element in the middle of the unsorted portion
	 */
	@Test
	public void testMaxPositionMiddle()
	{
		int[] x = {1, 5, 3, 7, 8, 9};
		MySorter s = new MySorter();
		assertEquals(1, s.maxPosition(x,2));
	}
	
	/**
	 * Test max position for an element in the beginning of the unsorted portion
	 */
	@Test
	public void testMaxPositionFirst()
	{
		int[] x = {5, 1, 3, 2, 8, 9};
		MySorter s = new MySorter();
		assertEquals(0, s.maxPosition(x, 3));
	}
	
	/**
	 * Test max position for an element at the end of the unsorted portion
	 */
	@Test
	public void testMaxPositionLast()
	{
		int[] x = {1, 5, 3, 7, 8, 9};
		MySorter s = new MySorter();
		assertEquals(3, s.maxPosition(x,3));
	}
	
	/**
	 * maxPosition must work correctly even if there is only one 
	 * element in the portion of the array it is looking at
	 */
	@Test
	public void testMaxPositionOneElement()
	{
		int[] x = {1, 5, 3, 7, 8, 9};
		MySorter s = new MySorter();
		assertEquals(0, s.maxPosition(x,0));
	}
	
	/**
	 * Make sure that we can find the biggest thing
	 * even if it's negative.
	 */
	@Test
	public void testMaxPositionNegative()
	{
		int[] x = {-7, -6, -8, -3, -1};
		MySorter s = new MySorter();
		assertEquals(1, s.maxPosition(x, 2));
	}
	
	/**
	 * Test Selection Sort, on a small randomly ordered array.
	 */
	@Test
	public void testSelectionSmall()
	{
		int[] x = {5, 3, 8, 1};
		MySorter s = new MySorter();
		s.selectionSort(x);
		for(int i = 0; i < x.length-1; i++)
		{
			assertTrue("Out of order at " + i, x[i] < x[i+1]);
		}
	}
	
	/**
	 * Test Insertion Sort on a small, randomly ordered array.
	 */
	@Test
	public void testInsertionSmall()
	{
		int[] x = {5, 3, 8, 1};
		MySorter s = new MySorter();
		s.insertionSort(x);
		for(int i = 0; i < x.length-1; i++)
		{
			assertTrue("Out of order at " + i, x[i] < x[i+1]);
		}
	}

}
