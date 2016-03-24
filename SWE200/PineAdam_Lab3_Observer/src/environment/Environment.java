/**
 * @author Adam Pine
 * The environment class, used to hold and manage a 2-D array of cells.
 */
package environment;
import lifeform.LifeForm;
public class Environment 
{
	private int maxRow = 5;
	private int maxCol = 1;
	private Cell[][] theCells = new Cell[maxRow][maxCol];
	
	public Environment()
	{
		
	}
	/**
	 * Will add a LifeForm to the 2-D array of Cells if it does not have a LifeForm in said location.
	 * @param row
	 * @param col
	 * @param entity
	 * @return true if added successfully, and false if not added.
	 */
	public boolean addLifeForm(int row, int col, LifeForm entity)
	{
		if ((row>=maxRow)||(col>=maxCol))
		{
			return false;
		}
		if (theCells[row][col] == null)
		{
			theCells[row][col] = new Cell();
			theCells[row][col].addLifeForm(entity);
			return true;
		}else
		{
			return false;	
		}		
	}
	/**
	 * Removes a lifeform from the environment if it exists
	 * @param row
	 * @param col
	 * @return the removed lifeform if successful, null if unsuccessful. 
	 */
	public LifeForm removeLifeForm(int row, int col)
	{
		if (theCells[row][col] != null)
		{
			LifeForm rCell = theCells[row][col].getLifeForm();
			theCells[row][col].removeLifeForm();
			return rCell;
		}
		else
		{
			return null;
		}
	}
	
	public LifeForm getLifeForm(int row, int col)
	{
		if (theCells[row][col] == null)
		{
			return null;
		}
		else
		{
			return theCells[row][col].getLifeForm();
		}
		
	}
}
