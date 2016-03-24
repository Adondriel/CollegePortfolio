/**
 * 
 * @author Adam Pine
 * A Cell that can hold a lifeform
 */
public class Cell 
{
	private LifeForm entity = null;
	/**
	 * 
	 * @return the LifeForm in this Cell.
	 * 
	 */
	public LifeForm getLifeForm()
	{
		return entity;
	}
	
	/**
	 * Tries to add the lifeform to the Cell. Will not add if a LifeForm is already present
	 * @param entity
	 * @return true if the LifeForm was added to the Cell, false otherwise.
	 */
	public boolean addLifeForm(LifeForm entity)
	{
		if (this.entity == null)
		{
			this.entity = entity;
			return true;
		}
		else
		{
			return false;
		}		
	}
	
	/**
	 * Removes the lifeform from the Cell.
	 * @return the removed lifeform if successful, null if not.
	 */
	public LifeForm removeLifeForm()
	{
		if (entity != null)
		{
			LifeForm returnLF = entity;
			entity = null;
			return returnLF;
		}
		else
		{
			return null;
		}
	}
	
}
