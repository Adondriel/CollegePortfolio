/**
 * @author Adam Pine
 * Keeps Track of the information associated with a simple life form.
 * Also provides the functionality related to the life form.
 */
package lifeform;
public abstract class LifeForm 
{
	private String name;
	protected int currentLifePoints;
	
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
	/**
	 * Makes the lifeform take damage.
	 * @param dmg Amount of dmg to subtract.
	 */
	public void takeHit(int dmg){
		int math = currentLifePoints - dmg;
		if (math < 0){
			math = 0;
		}
		currentLifePoints = math;
	}
}
