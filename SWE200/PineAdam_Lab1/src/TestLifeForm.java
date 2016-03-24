import static org.junit.Assert.*;

import org.junit.Test;
/**
 * @author Adam Pine
 * Tests the functionality provided by the book class
 *
 */
public class TestLifeForm {
/**
 * When a LifeForm is created, it should know it's name and how
 * many life points it has.
 */
	@Test
	public void testInitialization() {
		LifeForm entity = new LifeForm("Bob", 40);
		assertEquals("Bob", entity.getName());
		assertEquals(40, entity.getCurrentLifePoints());
	}

}
