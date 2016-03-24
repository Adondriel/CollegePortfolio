/**
 * @author Adam Pine
 * The test cases for the Cell class
 */
package environment;
import static org.junit.Assert.*;

import org.junit.Test;

import environment.Cell;
import lifeform.LifeForm;
import lifeform.MockLifeForm;

public class TestCell {
/**
 * at initialization, the cell should be empty and not contain a 
 * lifeform.
 */
	@Test 
	public void testInitialization() 
	{ 
		Cell cell= new Cell(); 
		assertNull(cell.getLifeForm()); 
	}

/**
 * Checks to see if we can change the LifeForm held by the Cell that
 * getLifeForm properly responds to this change
 */
	@Test
	public void testSetLifeForm()
	{
		LifeForm bob = new MockLifeForm("Bob", 40);
		LifeForm fred = new MockLifeForm("Fred",40);
		Cell cell = new Cell();
		//the cell is empty so this should work.
		boolean success = cell.addLifeForm(bob);
		assertTrue(success);
		assertEquals(bob, cell.getLifeForm());
		//the cell is not empty so this should fail
		success = cell.addLifeForm(fred);
		assertFalse(success);
		assertEquals(bob, cell.getLifeForm());
	}
	
	/**
	 * Check the removal of lifeform from a cell.
	 */
	@Test
	public void testRemoveLifeForm()
	{
		LifeForm bob = new MockLifeForm("Bob",40);
		LifeForm steve = new MockLifeForm("Steve",40);
		Cell cell = new Cell();
		cell.addLifeForm(bob);
		assertEquals(bob, cell.removeLifeForm());
		cell.addLifeForm(steve);
		assertEquals(steve, cell.getLifeForm());
		Cell nullcell = new Cell();
		assertNull(nullcell.removeLifeForm());
	}

}
