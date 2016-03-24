/**
 * @author Adam Pine
 * Tests the functionality of the LifeForm class.
 */
package lifeform;
import static org.junit.Assert.*;
import org.junit.Test;
import lifeform.LifeForm;

public class TestLifeForm {
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
