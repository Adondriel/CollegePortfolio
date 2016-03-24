/**
 * @author Adam Pine
 * Tests for the human, tests initialization and armor setting/getting along with armor value validation.
 */
package lifeform;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestHuman {

	@Test
	public void testHumanInit() {
		//Test init.
		Human h = new Human("Bob", 30, 20);
		assertEquals(20, h.getArmorPoints());
	}
	@Test
	public void testHumanSetArmor(){
		Human h = new Human("Steve", 30, -50);
		//test the setting of armor, even though the init does this for us.
		h.setArmorPoints(20);
		assertEquals(20, h.getArmorPoints());
	}
	@Test
	public void testHumanArmorLessThanZero(){
		//Test armor <0
		Human h = new Human("Steve", 30, -50);
		assertEquals(0,h.getArmorPoints());
	}
	@Test
	public void testHumanArmorEqualToZero(){
		//Test armor =0
		Human h = new Human("Steve", 30, 0);
		assertEquals(0,h.getArmorPoints());
	}
	

}
