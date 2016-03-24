/**
 * 
 * @author Adam Pine
 * Keeps Track of the information associated with a simple life form.
 * Also provides the functionality related to the life form.
 *
 */
public class LifeForm 
{
	private String name;
	private int currentLifePoints;
	
	/**
	 * 
	 * @param name The name of the life form
	 * @param points The current starting life points of the life form
	 */
	public LifeForm(String name, int points)
	{
		this.name = name;
		currentLifePoints = points;
	}
	
	/**
	 * @return the name of the lifeform
	 */
	public String getName()
	{
		return name;
	}
	
	/** 
	 * @return the amount of current life points the life form has.
	 */			
	public int getCurrentLifePoints()
	{
		return currentLifePoints;
	}
}
