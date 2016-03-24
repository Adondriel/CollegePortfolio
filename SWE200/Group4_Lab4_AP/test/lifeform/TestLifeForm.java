/**
 * @author Alex Fennen
 * Tests the functionality of the LifeForm class.
 */
package lifeform;
import static org.junit.Assert.*;

import org.junit.Test;

import environment.Range;
import gameplay.SimpleTimer;
import lifeform.LifeForm;
import weapon.*;

public class TestLifeForm {
	//!!!!
	//DECORATOR PATTERN TEST START HERE.	
	//!!!!
	/**
	 * Tests that a lifeform can equip a weapon
	 */
	@Test
	public void testPickupWeapon(){
		LifeForm entity = new MockLifeForm("Bob", 40);
		GenericWeapon w = new Pistol();
		entity.pickupWeapon(w);
		assertEquals(w, entity.weapon);
	}
	/**
	 * Tests that a lifeform can drop a weapon
	 */
	@Test
	public void testdropWeapon(){
		LifeForm entity = new MockLifeForm("Bob", 40);
		GenericWeapon w = new Pistol();
		entity.pickupWeapon(w);
		assertEquals(w, entity.weapon);
		entity.dropWeapon();
		assertEquals(null, entity.weapon);
	}
	/**
	 * Tests that a lifeform can't equip a weapon if it already has one
	 */
	@Test
	public void testCantOverrideWeapon(){
		LifeForm entity = new MockLifeForm("Bob", 40);
		GenericWeapon w = new Pistol();
		entity.pickupWeapon(w);
		assertEquals(w, entity.weapon);
		GenericWeapon c = new ChainGun();
		entity.pickupWeapon(c);
		assertEquals(w, entity.weapon);
	}
	/**
	 * Tests that a lifeform can attack correctly with different weapons from different ranges
	 */
	@Test
	public void testAttackingWithWeapon(){
		LifeForm entity = new MockLifeForm("Bob", 40);
		LifeForm entity2 = new MockLifeForm("Fred", 40);
		GenericWeapon p = new Pistol();
		GenericWeapon c = new ChainGun();
		entity.pickupWeapon(p);
		entity2.pickupWeapon(c);
		Range.distance = 5;
		entity.attackLF(entity2);
		assertEquals(30, entity2.getCurrentLifePoints());
		entity2.attackLF(entity);
		assertEquals(38, entity.getCurrentLifePoints());
		Range.distance = 15;
		entity.attackLF(entity2);
		assertEquals(24, entity2.getCurrentLifePoints());
		entity2.attackLF(entity);
		assertEquals(31, entity.getCurrentLifePoints());
	}
	/**
	 * Tests that a lifeform whose weapon is ouot of ammo attacks using its base attack
	 */
	@Test
	public void testAttackingWithoutAmmo(){
		LifeForm entity = new MockLifeForm("Bob", 40);
		LifeForm entity2 = new MockLifeForm("Fred", 40);
		GenericWeapon p = new Pistol();
		entity.pickupWeapon(p);
		p.setCurrentAmmo(0);
		Range.distance = 1;
		entity.setAttackDmg(5);
		entity.attackLF(entity2);
		assertEquals(35, entity2.getCurrentLifePoints());
	}
	/**
	 * Tests that a lifeform without ammo and outside of the melee damage range does not
	 * deal damage
	 */
	@Test
	public void testNoAmmoAndPastRange(){
		LifeForm entity = new MockLifeForm("Bob", 40);
		LifeForm entity2 = new MockLifeForm("Fred", 40);
		GenericWeapon p = new Pistol();
		entity.pickupWeapon(p);
		p.setCurrentAmmo(0);
		Range.distance = 15;
		entity.setAttackDmg(5);
		entity.attackLF(entity2);
		assertEquals(40, entity2.getCurrentLifePoints());
	}
	//!!!!
	//OBSERVATION PATTERN TEST START HERE.	
	//!!!!
	/**
	 * Tests if the lifeform can set attackStr.
	 */
	@Test
	public void testSetAttack(){
		LifeForm e = new MockLifeForm("Bob", 30);
		assertEquals(0,e.getAttackDmg());
		e.setAttackDmg(25);
		assertEquals(25, e.getAttackDmg());
	}
	/**
	 * Tests to ensure that a lifeform can attack 
	 * another lifeform and the other lifeform takes damage correctly.
	 */
	@Test
	public void testAttackMethod(){
		Range.distance = 1;
		LifeForm e = new MockLifeForm("bob",30);
		LifeForm e2 = new MockLifeForm("Steve", 30);
		e.setAttackDmg(10);
		e.attackLF(e2);
		assertEquals(20, e2.getCurrentLifePoints());
	}
	/**
	 * Test that the dead are not able to attack.
	 */
	@Test
	public void testDeadCannotAttack(){
		LifeForm e = new MockLifeForm("bob",0);
		LifeForm e2 = new MockLifeForm("Steve", 30);
		e.setAttackDmg(10);
		e.attackLF(e2);
		assertEquals(30, e2.getCurrentLifePoints());
	}
	/**
	 * Tests that the lifeform can track the passage of time.
	 * @throws InterruptedException
	 */
	@Test
	public void testCanTrackPassageOfTime() throws InterruptedException {
		SimpleTimer st = new SimpleTimer(1000);
		LifeForm e = new MockLifeForm("bob",20);
		st.addTimeObserver(e);
		st.start();
		Thread.sleep(250); //1/4 a second diff from the simpletimer sleep.
		for (int x=0; x < 5; x++){
			assertEquals(x, e.myTime);
			Thread.sleep(1000);
		}
	}
	
	//!!!!
	//STRATEGY PATTERN TEST START HERE.	
	//!!!!
	/**
	 * When a LifeForm is created, it should know it's name and how
	 * many life points it has.
	 */
	/**
	 * Tests the storing of the values for the lifeforms.
	 */
	@Test
	public void testInitialization() {
		LifeForm entity = new MockLifeForm("Bob", 40);
		assertEquals("Bob", entity.getName());
		assertEquals(40, entity.getCurrentLifePoints());
	}
	/**
	 * Tests getting hit.
	 */
	@Test
	public void testTakeHit(){
		LifeForm entity = new MockLifeForm("Bob", 30);
		entity.takeHit(10);
		assertEquals(20, entity.getCurrentLifePoints());
	}
	/**
	 * Test getting hit into death.
	 */
	@Test
	public void testTakeHitToDeath(){
		LifeForm e2 = new MockLifeForm("Steve", 10);
		e2.takeHit(12);
		assertEquals(0, e2.getCurrentLifePoints());
	}
	
	@Test
	public void testTakeMultiHit(){
		LifeForm e2 = new MockLifeForm("Bob", 30);
		e2.takeHit(5);
		assertEquals(25, e2.getCurrentLifePoints());
		e2.takeHit(5);
		assertEquals(20, e2.getCurrentLifePoints());
	}

}
