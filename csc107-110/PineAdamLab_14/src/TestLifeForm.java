import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Adam Pine The LifeForm test class for both types of life forms.
 */
public abstract class TestLifeForm
{
	/**
	 * the abstract method that allows each of the humans and the zombies to
	 * create their own instance, depending on the lifeform that is calling it.
	 * 
	 * @param hp
	 * @return 
	 */
	public abstract LifeForm createLifeForm(int hp);

	/**
	 * Test to see if the spread of damage being done is spread out well enough.
	 */
	@Test
	public void testHitsHurtRandomly()
	{
		int originalHealth = 50;
		int weaponStrength = 5;
		int[] damage = new int[weaponStrength + 1];
		int numberOfHits = 10000;
		for (int i = 0; i < numberOfHits; i++)
		{
			LifeForm deadFred = createLifeForm(originalHealth);
			deadFred.takeHit(weaponStrength);
			damage[originalHealth - deadFred.getLifePoints()]++;
		}
		for (int i = 0; i < weaponStrength + 1; i++)
		{
			System.out.println(damage[i]);
		}
		int hitsPerDamage = numberOfHits / (weaponStrength + 1);
		int epsilon = (int) (hitsPerDamage * 0.15);
		for (int i = 0; i < damage.length; i++)
		{
			assertTrue("not enough examples of dmg = " + i,
					(hitsPerDamage - epsilon) < damage[i]);
			assertTrue("Too many examples of dmg = " + i,
					(hitsPerDamage + epsilon) > damage[i]);
		}
	}

	/**
	 * test the get weapon strength method.
	 */
	@Test
	public void testWeaponStrength()
	{
		LifeForm it = createLifeForm(50);
		assertEquals(0, it.getCurrentWeaponStrength());
		it.pickUpWeapon(15);
		assertEquals(15, it.getCurrentWeaponStrength());
	}

	/**
	 * Test to make to make sure it does not pick up a weaker weapon than you
	 * are currently using.
	 */
	@Test
	public void testPickUpWeakerWeapon()
	{
		LifeForm it = createLifeForm(50);
		assertEquals(0, it.getCurrentWeaponStrength());
		it.pickUpWeapon(15);
		it.pickUpWeapon(14);
		assertEquals(15, it.getCurrentWeaponStrength());
	}

	/**
	 * tests the shooting method
	 */
	@Test
	public void testShooting()
	{
		LifeForm it = createLifeForm(50);
		LifeForm victim = createLifeForm(30);
		it.pickUpWeapon(12);
		it.shoot(victim);
		assertEquals(11, it.getCurrentWeaponStrength());
		assertEquals(1, victim.getNumberOfHitsTaken());
	}

	/**
	 * tests the hittaken method to ensure that we can keep track of how many
	 * times someone or something has been hit.
	 */
	@Test
	public void testHitCount()
	{
		LifeForm it = createLifeForm(30);
		assertEquals(0, it.getNumberOfHitsTaken());
		it.takeHit(5);
		assertEquals(1, it.getNumberOfHitsTaken());
		it.takeHit(5);
		assertEquals(2, it.getNumberOfHitsTaken());
	}

	/**
	 * Test the shooting method to make sure you cannot shoot without a weapon.
	 */
	@Test
	public void testShootingWithNoWeapon()
	{
		LifeForm it = createLifeForm(50);
		LifeForm victim = createLifeForm(30);
		it.shoot(victim);
		assertEquals(0, it.getCurrentWeaponStrength());
		assertEquals(0, victim.getNumberOfHitsTaken());
		assertEquals(30, victim.getLifePoints());
	}

}
