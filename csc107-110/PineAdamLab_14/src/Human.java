import java.util.ArrayList;

/**
 * @author Adam Pine
 * The human class, constructs and creates and does things with humans.
 */
public class Human extends LifeForm
{
	protected int numberOfWeaponsInBackPack;
	protected ArrayList<Integer> weaponsInBackPack = new ArrayList<Integer>();

	/**
	 * @param hp construct the human!!!
	 */
	public Human(int hp)
	{
		health = hp;
	}

	/**
	 * @return the number of weapons in the humans backpack
	 */
	public int numberOfWeaponsInBackPack()
	{
		return numberOfWeaponsInBackPack;
	}

	/**
	 * @param weaponStrength
	 * @return whether or not the backpack contains a weapon of that strength
	 */
	public boolean isCarryingWeapon(int weaponStrength)
	{
		if (weaponsInBackPack.contains(weaponStrength))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * The special pickUpWeapon method for humans.
	 */
	public void pickUpWeapon(int weaponStr)
	{
		if (weaponStr > currentWeaponStrength)
		{
			currentWeaponStrength = weaponStr;
		}
		else if (weaponsInBackPack.size() < 10)
		{
			weaponsInBackPack.add(weaponStr);
			numberOfWeaponsInBackPack++;
		}
	}
	
	/**
	 * Tells the human to debate what weapon is better in their inventory, they then decide if they should swap their weapons out.
	 */
	public void swapBestWeapon()
	{
		for (int i = 0; i < weaponsInBackPack.size()-1; i++)
		{
			if (currentWeaponStrength < weaponsInBackPack.get(i))
			{
				int temp = currentWeaponStrength;
				currentWeaponStrength = weaponsInBackPack.get(i);
				weaponsInBackPack.set(i, temp);
			}
		}
	}

}
