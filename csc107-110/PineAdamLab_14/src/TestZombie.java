import static org.junit.Assert.*;

import org.junit.Test;


/**
 * @author Adam Pine
 *	The test Zombie class that tests the functions of the zombie!
 */
public class TestZombie extends TestLifeForm {
	
	/**
	 * The abstract createLifeForm specifically for the zombie.
	 * @return a Zombie with the health of HP
	 * @param hp - hitpoints to give the lifeform on creation. 
	 */
	public LifeForm createLifeForm(int hp)
	{
		return new Zombie(hp);
	}
	
	
	/**
	 * tests to make sure a zombie is made correctly.
	 */
	@Test
	public void testCreation() 
	{
		LifeForm deadFred = new Zombie(50);
		assertEquals(50, deadFred.getLifePoints());
	}	

	/**
	 * tests that the health of thezombie cannot go lower than zero... which would be dead.
	 */
	@Test
	public void testHealthSubtractionLimit() 
	{
		LifeForm deadFred = new Zombie(50);
		assertEquals(50, deadFred.getLifePoints());
		for (int i = 0; i < 20; i++)
		{
			deadFred.takeHit(30);
		}
		assertEquals(0, deadFred.getLifePoints());
	}	

	/**
	 * Tests the recover method of the zombie.
	 */
	@Test
	public void testRecover() 
	{
		Zombie deadFred = new Zombie(50);
		assertEquals(50, deadFred.getLifePoints());
		deadFred.takeHit(30);
		int dmgTaken = 50 - deadFred.getLifePoints();
		int dmgdLife = deadFred.getLifePoints();
		deadFred.recover();
		assertEquals((int)(dmgTaken*0.1), deadFred.getLifePoints()-dmgdLife);
		//bordercases
		deadFred.takeHit(30);
		dmgTaken = 50 - deadFred.getLifePoints();
		dmgdLife = deadFred.getLifePoints();
		deadFred.recover();
		assertEquals((int)(dmgTaken*0.1), deadFred.getLifePoints()-dmgdLife);
		System.out.println(deadFred.getLifePoints());
		for (int i = 0; i < 100; i++)
		{
			deadFred.recover();
		}
		//where it gets really low into the decimals that it just sits at 41.
		assertEquals(41, deadFred.getLifePoints());
	}
	
	
	
	
	

}
