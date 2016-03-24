/**
 * @author Adam Pine
 * Tests the functionality of the LifeForm class.
 */
package lifeform;
import static org.junit.Assert.*;
import org.junit.Test;

import gameplay.SimpleTimer;
import lifeform.LifeForm;

public class TestLifeForm {
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
