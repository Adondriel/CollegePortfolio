/**
 * 
 */
package state;

import static org.junit.Assert.*;
import lifeform.Alien;
import lifeform.Human;
import lifeform.MockLifeForm;

import org.junit.Before;
import org.junit.Test;

import weapon.Pistol;
import weapon.Weapon;
import environment.CellInfo;
import environment.Environment;
import exceptions.RecoveryRateException;
import exceptions.WeaponException;

/**
 * @author Dr. Alice Armstrong
 *
 */
public class TestStatePattern {

	Environment e;
	MockLifeForm bob; 
	Pistol p; 
	Alien sheryl; 
	Human dan;
	Alien alienBob; 
	
	@Before
	public void clear() throws RecoveryRateException
	{
		e= Environment.getEnvironment(12, 12); 
		e.clearBoard();
		bob = new MockLifeForm("bob", 40, 5);
		p = new Pistol();
		alienBob = new Alien("bob", 40);
		sheryl = new Alien("sheryl", 50); 
		dan = new Human("dan", 50, 2); 
	}

	
	/**
	 * test the dead state
	 */
	@Test
	public void testDeadState()
	{
		bob.takeHit(100);
		bob.pickUpWeapon(p); 
		e.addLifeForm(bob, 1, 1); 
		
		AIContext context = new AIContext(bob, e); 
		
		//the current AI is in the dead state
		assertTrue(context.getCurrentState() instanceof DeadState); 
	
		//run the dead state action
		context.execute();
		
		//bob is no longer dead
		assertEquals(40, bob.getCurrentLifePoints()); 
		// (1, 1) is empty
		CellInfo info = e.getCellInfo(1, 1); 
		assertFalse(info.hasLife()); 
		assertFalse(info.hasWeapon1()); 
		assertFalse(info.hasWeapon2()); 
		
		//find where bob went
		if (bob.getRow() == 1)
		{
			assertNotEquals(1, bob.getCol()); 
		}
		else if (bob.getCol() == 1)
		{
			assertNotEquals(1, bob.getRow()); 
		}
		assertNull(e.getLifeForm(1, 1));
		assertNotNull(e.getLifeForm(bob.getRow(), bob.getCol()));
		
		//find the other weapon
		boolean found = false; 
		for (int i = 0; i < e.getNumRows(); i++)
		{
			for (int j = 0; j< e.getNumCols(); j++)
			{
				info = e.getCellInfo(i, j); 
				if (info.hasWeapon1() || info.hasWeapon2())
				{
					if (info.getRow() != 1 || info.getCol() != 1)
					{
						found = true; 
					}
				}
			}
		}
		assertTrue(found); 
		
		//AI is in the NoWeapon state
		assertTrue(context.getCurrentState() instanceof NoWeaponState);
	}
	
	/**
	 * tests the NoWeapon state, AIContext will deal with the case that the lifeform is dead
	 * This assumes a live lifeform. 
	 * 
	 * Acquire Weapon: Pickup weapon, if there is one in the cell
	 * Search: If there is no weapon available, Turn random direction and move to new Cell.
	 */
	@Test
	public void testNoWeaponState()
	{
		
		e.addLifeForm(bob, 1, 1); 
		
		AIContext context = new AIContext(bob, e); 
		
		//AI is in NoWeapon state
		assertTrue(context.getCurrentState() instanceof NoWeaponState); 
		
		//kill bob
		bob.takeHit(100);
		context.execute();
		//AI is in Dead state
		assertTrue(context.getCurrentState() instanceof DeadState); 
		context.execute();
		
		//AI is in NoWeapon state
		assertTrue(context.getCurrentState() instanceof NoWeaponState); 
		context.execute();
		
		//there is no weapon in bob's cell, so bob should be in a new location
		boolean newSpot = false; 
		if(bob.getRow() != 1 || bob.getCol() != 1)
		{
			newSpot = true; 
		}
		assertTrue(newSpot); 
		
		//AI is STILL in NoWeapon state
		assertTrue(context.getCurrentState() instanceof NoWeaponState); 
		
		//add a weapon to bob's new location
		int row = bob.getRow(); 
		int col = bob.getCol(); 
		e.addWeapon(p, row, col); 
		context.execute();
		//AI is in HasWeapon state
		assertTrue(context.getCurrentState() instanceof HasWeaponState); 
		
		//now bob should be in the same place and have a weapon
		assertEquals(row, bob.getRow()); 
		assertEquals(col, bob.getCol());
		assertTrue(bob.hasWeapon()); 
		
		//make sure the weapon is no longer in the cell
		Weapon[] weapons = e.getWeapons(row, col); 
		assertNull(weapons[0]); 
		assertNull(weapons[1]); 
	}
	
	/**
	 * Evaluation: If  not dead, call reload method. Otherwise call dead method. 
	 * Dead: Move to Dead state
	 * Reload: Reload the weapon, change to Has Weapon state.
	 * @throws WeaponException 
	 */
	@Test
	public void testOutOfAmmoDead() throws WeaponException
	{
		bob.pickUpWeapon(p); 
		e.addLifeForm(bob, 1, 1); 
		
		//empty the clip
		while(p.getCurrentAmmo() >0)
		{
			p.fire(10);
			p.updateTime(1);
		}
		
		AIContext context = new AIContext(bob, e); 
		//AI is in OutOfAmmo state
		assertTrue(context.getCurrentState() instanceof OutOfAmmoState); 

		
		//kill bob
		bob.takeHit(100);
		context.execute();
		//AI is in Dead state
		assertTrue(context.getCurrentState() instanceof DeadState); 
		context.execute();
		
		//AI is in NoWeapon state
		assertTrue(context.getCurrentState() instanceof NoWeaponState); 
	}
	
	/**
	 * Evaluation: If  not dead, call reload method. Otherwise call dead method. 
	 * Dead: Move to Dead state
	 * Reload: Reload the weapon, change to Has Weapon state.
	 * @throws WeaponException 
	 */
	@Test
	public void testOutOfAmmoLife() throws WeaponException
	{
		bob.pickUpWeapon(p);
		e.addLifeForm(bob, 1, 1);

		//empty the clip
		while(p.getCurrentAmmo() >0)
		{
			p.fire(10);
			p.updateTime(1);
		}
		
		AIContext context = new AIContext(bob, e); 
		
		//AI is in OutOfAmmo state
		assertTrue(context.getCurrentState() instanceof OutOfAmmoState); 
		context.execute();
		assertEquals(10, p.getCurrentAmmo()); 
		//AI is in HasWeapon state
		assertTrue(context.getCurrentState() instanceof HasWeaponState); 
	}
	
	/**
	 * Evaluation: If target call attackTarget.  If no target call search.
	 * Attack Target: Fire the weapon.  If out of ammo move to Out of Ammo state.
	 * Dead: Move to Dead state
	 * Search: Turn random direction and 50% of time move to new Cell.
	 * @throws RecoveryRateException 
	 */
	@Test
	public void testHasWeaponStateNoOpponents() throws RecoveryRateException
	{
		
		int row = 1; 
		int col = 1; 
		String direction = alienBob.getDirection(); 
		int moves = 0; 
		
		Pistol p = new Pistol();
		alienBob.pickUpWeapon(p); 
		
		//add alienBob all by himself with no possible targets
		e.addLifeForm(alienBob, 1, 1); 
		
		AIContext context = new AIContext(alienBob, e); 
		
		//there are no possible targets, so alienBob be facing a different direction
		//half of the time alienBob should move
		for (int i = 0; i < 50; i++)
		{
			context.execute();
			assertNotEquals(direction, alienBob.getDirection()); 
			assertTrue(context.getCurrentState() instanceof HasWeaponState);
			direction = alienBob.getDirection(); 
			if (row != alienBob.getRow() || col != alienBob.getCol())
			{
				moves++; 
				row = alienBob.getRow(); 
				col = alienBob.getCol(); 
			}
		}
		
		assertTrue(moves >= 15 && moves <=40); 	
	}

	/**
	 * Evaluation: If target call attackTarget.  If no target call search.
	 * Attack Target: Fire the weapon.  If out of ammo move to Out of Ammo state.
	 * Dead: Move to Dead state
	 * Search: Turn random direction and 50% of time move to new Cell.
	 * @throws RecoveryRateException 
	 */
	@Test
	public void testHasWeaponStateOpponentsDead() throws RecoveryRateException
	{
		
		int row = 1; 
		int col = 1; 
		String direction = alienBob.getDirection(); 
		int moves = 0; 
		
		p = new Pistol();
		alienBob.pickUpWeapon(p); 
		
		//add alienBob all by himself with no possible targets
		e.addLifeForm(alienBob, 1, 1); 
		
		AIContext context = new AIContext(alienBob, e); 
		
		//kill bob
		alienBob.takeHit(100);
		context.execute(); 
		
		assertTrue(context.getCurrentState() instanceof DeadState); 
	}
	
	/**
	 * Evaluation: If target call attackTarget.  If no target call search.
	 * Attack Target: Fire the weapon.  If out of ammo move to Out of Ammo state.
	 * Dead: Move to Dead state
	 * Search: Turn random direction and 50% of time move to new Cell.
	 * @throws RecoveryRateException 
	 */
	@Test
	public void testHasWeaponStateOpponentsInRange() throws RecoveryRateException
	{	
		alienBob.pickUpWeapon(p); 
		
		//add alienBob facing South
		e.addLifeForm(alienBob, 1, 1); 
		
		AIContext context = new AIContext(alienBob, e); 
		alienBob.setDirection("South"); 
		assertTrue(context.getCurrentState() instanceof HasWeaponState); 
		
		//add dan to the south
		e.addLifeForm(dan, 4, 1); 
		context.execute();
		assertTrue(context.getCurrentState() instanceof HasWeaponState);
		
		//dan should take the hit
		assertTrue(dan.getCurrentLifePoints() < 50);
		
		//remove, revive and replace to test attacking North
		e.removeLifeForm(1, 1); 
		e.removeLifeForm(4, 1); 
		alienBob.revive();
		dan.revive();
		p.updateTime(1);
		e.addLifeForm(alienBob, 4, 1); 
		alienBob.setDirection("North"); 
		e.addLifeForm(dan, 1, 1); 
		
		//shoot dan again
		assertTrue(context.getCurrentState() instanceof HasWeaponState);
		context.execute();//dan should take the hit
		assertTrue(dan.getCurrentLifePoints() < 50);
		assertTrue(context.getCurrentState() instanceof HasWeaponState);
		
		//remove, revive and replace to test attacking East
		e.removeLifeForm(1, 1); 
		e.removeLifeForm(4, 1); 
		alienBob.revive();
		dan.revive();
		p.updateTime(1);
		e.addLifeForm(alienBob, 1, 1); 
		alienBob.setDirection("East"); 
		e.addLifeForm(dan, 1, 5); 
		
		//shoot dan again
		assertTrue(context.getCurrentState() instanceof HasWeaponState);
		context.execute();//dan should take the hit
		assertTrue(dan.getCurrentLifePoints() < 50);
		assertTrue(context.getCurrentState() instanceof HasWeaponState);
		
		//remove, revive and replace to test attacking West
		e.removeLifeForm(1, 1); 
		e.removeLifeForm(1, 5); 
		alienBob.revive();
		dan.revive();
		p.updateTime(1);
		e.addLifeForm(alienBob, 3, 5); 
		alienBob.setDirection("West"); 
		e.addLifeForm(dan, 3, 2); 
		
		//shoot dan again
		assertTrue(context.getCurrentState() instanceof HasWeaponState);
		context.execute();//dan should take the hit
		assertTrue(dan.getCurrentLifePoints() < 50);
		assertTrue(context.getCurrentState() instanceof HasWeaponState);
	}
	
	/**
	 * Evaluation: If target call attackTarget.  If no target call search.
	 * Attack Target: Fire the weapon.  If out of ammo move to Out of Ammo state.
	 * Dead: Move to Dead state
	 * Search: Turn random direction and 50% of time move to new Cell.
	 * @throws RecoveryRateException 
	 */
	@Test
	public void testHasWeaponStateOpponentsOutOfRange() throws RecoveryRateException
	{	
		alienBob.pickUpWeapon(p); 
		
		//add alienBob facing South
		e.addLifeForm(alienBob, 1, 1); 
		
		AIContext context = new AIContext(alienBob, e); 
		alienBob.setDirection("South"); 
		assertTrue(context.getCurrentState() instanceof HasWeaponState); 
		
		//add dan to the south
		e.addLifeForm(dan, 7, 1); 
		context.execute();
		assertTrue(context.getCurrentState() instanceof HasWeaponState);
		
		//dan should take the hit
		assertTrue(dan.getCurrentLifePoints() == 50);
		
		//remove, revive and replace to test attacking North
		e.removeLifeForm(1, 1); 
		e.removeLifeForm(7, 1); 
		alienBob.revive();
		dan.revive();
		p.updateTime(1);
		e.addLifeForm(alienBob, 8, 1); 
		alienBob.setDirection("North"); 
		e.addLifeForm(dan, 1, 1); 
		
		//shoot dan again
		assertTrue(context.getCurrentState() instanceof HasWeaponState);
		context.execute();//dan should take the hit
		assertTrue(dan.getCurrentLifePoints() == 50);
		assertTrue(context.getCurrentState() instanceof HasWeaponState);
		
		//remove, revive and replace to test attacking East
		e.removeLifeForm(1, 1); 
		e.removeLifeForm(8, 1); 
		alienBob.revive();
		dan.revive();
		p.updateTime(1);
		e.addLifeForm(alienBob, 1, 1); 
		alienBob.setDirection("East"); 
		e.addLifeForm(dan, 1, 8); 
		
		//shoot dan again
		assertTrue(context.getCurrentState() instanceof HasWeaponState);
		context.execute();//dan should take the hit
		assertTrue(dan.getCurrentLifePoints() == 50);
		assertTrue(context.getCurrentState() instanceof HasWeaponState);
		
		//remove, revive and replace to test attacking West
		e.removeLifeForm(1, 1); 
		e.removeLifeForm(1, 8); 
		alienBob.revive();
		dan.revive();
		p.updateTime(1);
		e.addLifeForm(alienBob, 3, 5); 
		alienBob.setDirection("West"); 
		e.addLifeForm(dan, 3, 0); 
		
		//shoot dan again
		assertTrue(context.getCurrentState() instanceof HasWeaponState);
		context.execute();//dan should take the hit
		assertTrue(dan.getCurrentLifePoints() == 50);
		assertTrue(context.getCurrentState() instanceof HasWeaponState);
	}
	
	/**
	 * Evaluation: If target call attackTarget.  If no target call search.
	 * Attack Target: Fire the weapon.  If out of ammo move to Out of Ammo state.
	 * Dead: Move to Dead state
	 * Search: Turn random direction and 50% of time move to new Cell.
	 * @throws RecoveryRateException 
	 */
	@Test
	public void testHasWeaponStateOpponentsInRangeSameTeam() throws RecoveryRateException
	{	
		alienBob.pickUpWeapon(p); 
		
		//add alienBob facing South
		e.addLifeForm(alienBob, 1, 1); 
		
		AIContext context = new AIContext(alienBob, e); 
		alienBob.setDirection("South"); 
		assertTrue(context.getCurrentState() instanceof HasWeaponState); 
		
		//add dan to the south
		e.addLifeForm(sheryl, 4, 1); 
		context.execute();
		assertTrue(context.getCurrentState() instanceof HasWeaponState);
		
		//dan should take the hit
		assertTrue(sheryl.getCurrentLifePoints() == 50);
		
		//remove, revive and replace to test attacking North
		e.removeLifeForm(1, 1); 
		e.removeLifeForm(4, 1); 
		alienBob.revive();
		dan.revive();
		p.updateTime(1);
		e.addLifeForm(alienBob, 4, 1); 
		alienBob.setDirection("North"); 
		e.addLifeForm(sheryl, 1, 1); 
		
		//shoot dan again
		assertTrue(context.getCurrentState() instanceof HasWeaponState);
		context.execute();//dan should take the hit
		assertTrue(sheryl.getCurrentLifePoints() == 50);
		assertTrue(context.getCurrentState() instanceof HasWeaponState);
		
		//remove, revive and replace to test attacking East
		e.removeLifeForm(1, 1); 
		e.removeLifeForm(4, 1); 
		alienBob.revive();
		dan.revive();
		p.updateTime(1);
		e.addLifeForm(alienBob, 1, 1); 
		alienBob.setDirection("East"); 
		e.addLifeForm(sheryl, 1, 5); 
		
		//shoot dan again
		assertTrue(context.getCurrentState() instanceof HasWeaponState);
		context.execute();//dan should take the hit
		assertTrue(sheryl.getCurrentLifePoints() == 50);
		assertTrue(context.getCurrentState() instanceof HasWeaponState);
		
		//remove, revive and replace to test attacking West
		e.removeLifeForm(1, 1); 
		e.removeLifeForm(1, 5); 
		alienBob.revive();
		dan.revive();
		p.updateTime(1);
		e.addLifeForm(alienBob, 3, 5); 
		alienBob.setDirection("West"); 
		e.addLifeForm(sheryl, 3, 2); 
		
		//shoot dan again
		assertTrue(context.getCurrentState() instanceof HasWeaponState);
		context.execute();//dan should take the hit
		assertTrue(sheryl.getCurrentLifePoints() == 50);
		assertTrue(context.getCurrentState() instanceof HasWeaponState);
	}
	
	/**
	 * Evaluation: If target call attackTarget.  If no target call search.
	 * Attack Target: Fire the weapon.  If out of ammo move to Out of Ammo state.
	 * Dead: Move to Dead state
	 * Search: Turn random direction and 50% of time move to new Cell.
	 * @throws RecoveryRateException 
	 * @throws WeaponException 
	 */
	@Test
	public void testHasWeaponStateLastBullet() throws RecoveryRateException, WeaponException
	{	
		//empty p's clip down to 1 bullet
		while(p.getCurrentAmmo() > 1)
		{
			p.fire(10); 
			p.updateTime(1);
		}
		
		alienBob.pickUpWeapon(p); 
		
		//add alienBob facing South
		e.addLifeForm(alienBob, 1, 1); 
		
		AIContext context = new AIContext(alienBob, e); 
		alienBob.setDirection("South"); 
		assertTrue(context.getCurrentState() instanceof HasWeaponState); 
		
		//add dan to the south
		e.addLifeForm(dan, 4, 1); 
		context.execute();
		assertTrue(context.getCurrentState() instanceof OutOfAmmoState);
		
		//dan should take the hit
		assertTrue(dan.getCurrentLifePoints() < 50);
		
	}
}
