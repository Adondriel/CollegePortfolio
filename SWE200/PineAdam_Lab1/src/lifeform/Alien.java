/**
 * @author Adam Pine
 * The alien class, subclass to the LifeForm. Holds information and functions for an alien.
 */
package lifeform;

import recovery.RecoveryBehavior;
import recovery.RecoveryNone;

public class Alien extends LifeForm {
	private RecoveryBehavior recoveryBehavior;
	private int maxLifePoints = 30;
	/**
	 * Constructor that will default to a recoveryNone RB.
	 * @param name string, name of the alien
	 * @param points int, amount of hp the alien will have.
	 */
	public Alien(String name, int points) {
		this(name, points, new RecoveryNone());
		setAttackDmg(10);
	}
	/**
	 * Constructor, with a RecoveryBehavior.
	 * @param name string, holds the alien's name
	 * @param points how much HP it should have.
	 * @param rb The recovery behavior.
	 */
	public Alien(String name, int points, RecoveryBehavior rb){
		super(name, points);
		recoveryBehavior = rb;
	}
	/**
	 * The recover function, that can be called to have the alien recover health.
	 */
	public void recover(){
		setCurrentLifePoints(recoveryBehavior.calculateRecovery(currentLifePoints, maxLifePoints));
	}
	/**
	 * Set's the current HP value.
	 * @param life what the life should be set to.
	 */
	public void setCurrentLifePoints(int life){
		currentLifePoints = life;
	}


}
