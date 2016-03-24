/**
 * @author Adam Pine
 * The Human class, holds information and functions for the human.
 */
package lifeform;

public class Human extends LifeForm {
	private int armorPoints;
	/**
	 * Contructor for human, initializes the human
	 * @param name
	 * @param points
	 * @param armor
	 */
	public Human(String name, int points, int armor) {
		super(name, points);
		setArmorPoints(armor);
	}	
	/**
	 * @return int, armorPoints for the instantiated human.
	 */
	public int getArmorPoints(){
		return armorPoints;
	}	
	/**
	 * Sets the armor value for the instantiated human.
	 * @param armor Int
	 */
	public void setArmorPoints(int armor){
		if (armor < 0){
			armor = 0;
		}
		armorPoints = armor;		
	}
	
	@Override
	public void takeHit(int dmg){
		if (armorPoints > dmg){
			int math = currentLifePoints - (dmg - armorPoints);
			if (math < 0){
				math = 0;
			}
			currentLifePoints = math;
		}
	}

}
