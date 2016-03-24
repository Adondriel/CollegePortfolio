/**
 * 
 */
package environment;

import static org.junit.Assert.*;
import lifeform.Human;

import org.junit.Test;

/**
 * @author Dr. Alice Armstrong
 *
 */
public class TestCellInfo {

	/************ LAB 7 (State) TESTS START HERE ******************/
	@Test
	public void testInitialization() {
		CellInfo info = new CellInfo(5, 4, new Human("Bob", 15, 2), true, false); 
		assertEquals(5, info.getRow()); 
		assertEquals(4, info.getCol()); 
		assertTrue(info.hasLife()); 
		assertTrue(info.hasWeapon1()); 
		assertFalse(info.hasWeapon2());
	}

}
