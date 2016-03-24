/**
 * @author Adam Pine
 * The tests for the alien class. Tests all of their recovery methods, and initialization.
 */
package lifeform;

import static org.junit.Assert.*;

import org.junit.Test;

import recovery.RecoveryFractional;
import recovery.RecoveryLinear;

public class TestAlien {

	/**
	 * Tests the Aliens default recovery, along with that it also tests the recoverynone.
	 */
	@Test
	public void testAlienDefaultRecovNone() {
		//Test creation and the default recovery, also tests the recoverynone alien behavior.
		Alien a = new Alien("ET", 30);
		int startHP= a.getCurrentLifePoints();
		a.recover();
		int endHP=a.getCurrentLifePoints();
		assertEquals(startHP,endHP);		
	}
	/**
	 * Tests the linear recovery type, the alien recovers exactly 3 hp, when the method is called.
	 */
	@Test
	public void testAlienRecovLinear(){
		Alien a = new Alien("ET", 30, new RecoveryLinear(3));
		a.takeHit(10);
		a.recover();
		assertEquals(23, a.getCurrentLifePoints());		
	}
	/**
	 * Tests the fractional alien recovery, it will recover 50% of it's hp once the recover function is called.
	 */
	@Test
	public void testAlienRecovFract(){
		Alien a = new Alien("ET", 30, new RecoveryFractional(1.5));
		a.takeHit(10);
		a.recover();
		assertEquals((int)(20*1.5), a.getCurrentLifePoints(), 0);
	}
	/**
	 * Test double recovery
	 */
	@Test
	public void testAlienDoubleRecovery(){
		Alien a = new Alien("ET", 30, new RecoveryLinear(3));
		a.takeHit(10);
		a.recover();
		assertEquals(23, a.getCurrentLifePoints());
		a.recover();
		assertEquals(26, a.getCurrentLifePoints());
	}

}
