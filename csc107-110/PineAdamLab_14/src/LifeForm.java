import java.util.Random;

/**
 * @author Adam Pine
 * the LifeForm class, for all of our amazing humans and zombies.
 */
public class LifeForm
{

	protected int health;
	protected int currentWeaponStrength = 0;
	protected int numberOfHitsTaken = 0;

	/**
	 * @return the number of hits taken by this lifeform.
	 */
	public int getNumberOfHitsTaken()
	{
		return numberOfHitsTaken;
	}

	/**
	 * @return health of the lifeform
	 */
	public int getLifePoints()
	{
		// TODO Auto-generated method stub
		return health;
	}

	/**
	 * causes the lifeform to take damage. can't go below 0
	 * @param weaponStr
	 */
	public void takeHit(int weaponStr)
	{
		Random rand = new Random();
		int dmgAmt = rand.nextInt(weaponStr + 1);
		health = health - dmgAmt;
		if (health <= 0)
		{
			health = 0;
		}
		numberOfHitsTaken++;
	}

	/**
	 * fairly self explanitory, but yea returns the weaponStrength of the item the lifeform is holding.
	 * @return currentWeaponStrength
	 */
	public int getCurrentWeaponStrength()
	{
		return currentWeaponStrength;
	}

	/**
	 * Pick up a weapon, of strength weaponStr.
	 * 
	 * @param weaponStr
	 */
	public void pickUpWeapon(int weaponStr)
	{
		if (weaponStr > currentWeaponStrength)
		{
			currentWeaponStrength = weaponStr;
		}
	}

	/**
	 * The shoot method, shoots the victim, if the weapon has power.
	 * @param victim
	 */
	public void shoot(LifeForm victim)
	{
		if (currentWeaponStrength > 0)
		{
			victim.takeHit(currentWeaponStrength);
			currentWeaponStrength--;
			
		}
	}

}
