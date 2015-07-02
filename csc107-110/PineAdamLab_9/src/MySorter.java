import static org.junit.Assert.assertTrue;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author Adam Pine
 *         The different sorter methods are contained in this class.
 */
public class MySorter
{

	/**
	 * Sorts the array using the bubble sort method.
	 * 
	 * @param array
	 */
	public void bubbleSort(int[] array)
	{
		for (int i = 0; i < array.length - 1; i++)
		{
			for (int j = 0; j < array.length - 1; j++)
			{
				if (array[j] > array[j + 1])
				{
					swap(array, j, j + 1);
				}
			}
		}

	}

	/**
	 * Swaps the values of the position i, with j, in the array of x.
	 * 
	 * @param x
	 * @param i
	 * @param j
	 */
	private void swap(int[] x, int i, int j)
	{
		int t = x[i];
		x[i] = x[j];
		x[j] = t;
	}

	/**
	 * @param array
	 * @param endPoint
	 * @return the position of the highest value in the array, from 0 to the
	 *         endPoint.
	 */
	public int maxPosition(int[] array, int endPoint)
	{
		int maxVal = array[0];
		int maxPos = 0;

		for (int i = 0; i <= endPoint; i++)
		{
			if (array[i] > maxVal)
			{
				maxVal = array[i];
				maxPos = i;
			}
		}
		return maxPos;
	}

	/**
	 * Sorts the array using the selection sort method.
	 * 
	 * @param array
	 */
	public void selectionSort(int[] array)
	{
		int unsortedSize = array.length - 1;
		while (unsortedSize != 0)
		{
			for (int i = 0; i < unsortedSize; i++)
			{
				int maxPos = maxPosition(array, unsortedSize);
				swap(array, maxPos, unsortedSize);
			}
			unsortedSize--;
		}

	}

	/**
	 * Sorts the array using the insertionSort method.
	 * 
	 * @param array
	 */
	public void insertionSort(int[] array)
	{
		for (int i = 0; i < array.length-1; i++)
		{
			for (int j = array.length-1; j > 0; j--)
			{
				while (array[j-1] > array[j])
				{
					swap(array, j, j-1);
				}
			}
		}
	}

}
