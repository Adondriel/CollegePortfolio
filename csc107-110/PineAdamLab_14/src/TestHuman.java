import static org.junit.Assert.*;

import org.junit.Test;


/**
 * @author Adam Pine
 * This is the Test human class that tests the functions of the human
 */
public class TestHuman extends TestLifeForm
{
	/**
	 * The abstract createLifeForm specifically for the human.
	 * @return a Human with the health of HP
	 * @param hp - hitpoints to give the lifeform on creation. 
	 */
	@Override
	public LifeForm createLifeForm(int hp)
	{
		return new Human(hp);
	}
	
	/**
	 * Tests the creation of the human.
	 */
	@Test
	public void testCreation()
	{
		Human wellington = new Human(100);
		assertEquals(100, wellington.getLifePoints());
	}
	
	/**
	 * Tests that override the pickupweakerweapon of the testlifeform because humans are smarter than zombies.
	 */
	@Test
	public void pickUpWeakerWeapon()
	{
		Human wellington = new Human(100);
		assertEquals(0, wellington.numberOfWeaponsInBackPack());
		wellington.pickUpWeapon(15);
		wellington.pickUpWeapon(14);
		assertEquals(15, wellington.getCurrentWeaponStrength());
		assertEquals(1, wellington.numberOfWeaponsInBackPack());
		assertTrue(wellington.isCarryingWeapon(14));


	}

	/**
	 * Tests to make sure the humans can hold more than 1 weapon in backpack.
	 */
	@Test
	public void pickUpTwoWeakerWeapon()
	{
		Human wellington = new Human(100);
		assertEquals(0, wellington.numberOfWeaponsInBackPack());
		wellington.pickUpWeapon(15);
		wellington.pickUpWeapon(14);
		wellington.pickUpWeapon(12);
		assertEquals(15, wellington.getCurrentWeaponStrength());
		assertEquals(2, wellington.numberOfWeaponsInBackPack());
		assertTrue(wellington.isCarryingWeapon(14));
		assertTrue(wellington.isCarryingWeapon(12));
	}
	
	/**
	 * tests the limits of the backpack.
	 */
	@Test
	public void testTenWeapons()
	{
		Human wellington = new Human(100);
		assertEquals(0, wellington.numberOfWeaponsInBackPack());
		wellington.pickUpWeapon(15);
		wellington.pickUpWeapon(14);
		wellington.pickUpWeapon(13);
		wellington.pickUpWeapon(12);
		wellington.pickUpWeapon(11);
		wellington.pickUpWeapon(10);
		wellington.pickUpWeapon(9);
		wellington.pickUpWeapon(8);
		wellington.pickUpWeapon(7);
		wellington.pickUpWeapon(6);
		wellington.pickUpWeapon(5);
		wellington.pickUpWeapon(4);
		assertEquals(15, wellington.getCurrentWeaponStrength());
		assertEquals(10, wellington.numberOfWeaponsInBackPack());
		assertTrue(wellington.isCarryingWeapon(14));
		assertTrue(wellington.isCarryingWeapon(13));
		assertTrue(wellington.isCarryingWeapon(12));
		assertTrue(wellington.isCarryingWeapon(11));
		assertTrue(wellington.isCarryingWeapon(10));
		assertTrue(wellington.isCarryingWeapon(9));
		assertTrue(wellington.isCarryingWeapon(8));
		assertTrue(wellington.isCarryingWeapon(7));
		assertTrue(wellington.isCarryingWeapon(6));
		assertTrue(wellington.isCarryingWeapon(5));
		assertFalse(wellington.isCarryingWeapon(4));
		wellington.pickUpWeapon(20);
		assertEquals(20, wellington.getCurrentWeaponStrength());
		assertEquals(10, wellington.numberOfWeaponsInBackPack());

	}
	
	/**
	 * tests to make sure the human is swapping to the right weapon power level.
	 */
	@Test
	public void testSwapWeapons()
	{
		Human wellington = new Human(100);
		Zombie deadFred = new Zombie(50);
		assertEquals(0, wellington.numberOfWeaponsInBackPack());
		wellington.pickUpWeapon(20);
		wellington.pickUpWeapon(15);
		wellington.pickUpWeapon(8);
		wellington.pickUpWeapon(13);
		wellington.pickUpWeapon(12);
		wellington.pickUpWeapon(11);
		wellington.pickUpWeapon(10);
		wellington.shoot(deadFred);
		wellington.shoot(deadFred);
		wellington.shoot(deadFred);
		wellington.shoot(deadFred);
		wellington.shoot(deadFred);
		wellington.shoot(deadFred);
		assertEquals(14, wellington.getCurrentWeaponStrength());
		wellington.swapBestWeapon();
		assertEquals(15, wellington.getCurrentWeaponStrength());
		assertTrue(wellington.isCarryingWeapon(14));
	}
	
	
}
