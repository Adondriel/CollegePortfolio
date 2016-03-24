/**
 * @author Adam Pine
 * Keeps Track of the information associated with a simple life form.
 * Also provides the functionality related to the life form.
 */
package lifeform;

import gameplay.TimeObserver;
import weapon.*;
import environment.Range;
public abstract class LifeForm implements TimeObserver
{
	private String name;
	protected int currentLifePoints;
	private int attackDmg;
	protected int myTime;
	protected GenericWeapon weapon;
	
	/**
	 * @param name The name of the life form
	 * @param points The current starting life points of the life form
	 */
	public LifeForm(String name, int points)
	{
		this.name = name;
		currentLifePoints = points;
		setAttackDmg(0);
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
	
	
	public void attackLF(LifeForm lf){
		if ( currentLifePoints > 0){
			if(weapon != null){
				if(weapon.getCurrentAmmo() > 0){
					lf.takeHit(weapon.calcDmg(Range.distance));
				}
				else{
					if (Range.distance <= 10){
						lf.takeHit(attackDmg);
					}
				}
			}
			else{
				if (Range.distance <= 10){
					lf.takeHit(attackDmg);
				}	
			}	
		}
	}
	
	public int getAttackDmg() {
		return attackDmg;
	}

	protected void setAttackDmg(int attackDmg) {
		this.attackDmg = attackDmg;
	}
	
	public void updateTime(int time){
		myTime = time;
	}
	public int getMyTime(){
		return myTime;
	}
	
	public void pickupWeapon(GenericWeapon w){
		if(weapon == null)
			weapon = w;
	}
	
	public void dropWeapon(){
		weapon = null;
	}
	
}
