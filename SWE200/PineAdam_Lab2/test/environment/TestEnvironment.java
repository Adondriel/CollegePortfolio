/**
 * @author Adam Pine
 * Used to test the Environment Class.
 */
package environment;
import static org.junit.Assert.*;

import org.junit.Test;

import environment.Environment;
import lifeform.LifeForm;
import lifeform.MockLifeForm;

public class TestEnvironment 
{	
	/**
	 * Tests the init of the environment.
	 */
	@Test
	public void testInit()
	{
		Environment e = new Environment();
		assertNull(e.getLifeForm(0, 0));		
	}
	/**
	 * Tests the addLifeForm function of the Environment class.
	 */
	@Test
	public void testAddLifeForm() 
	{
		LifeForm bob = new MockLifeForm("Bob",40);
		LifeForm steve = new MockLifeForm("Steve",40);
		Environment e = new Environment();
		assertTrue(e.addLifeForm(0, 0, bob));
		assertTrue(e.addLifeForm(1, 0, steve));
		assertFalse(e.addLifeForm(0, 0, steve));
		assertFalse(e.addLifeForm(10, 0, bob));
		assertFalse(e.addLifeForm(3, 5, bob));
	}
	
	/**
	 * Tests the removeLifeForm function of the environment class.
	 */
	@Test
	public void testRemoveLifeForm()
	{
		LifeForm bob = new MockLifeForm("Bob",40);
		LifeForm steve = new MockLifeForm("Steve",40);
		Environment e = new Environment();
		e.addLifeForm(0, 0, bob);
		e.addLifeForm(1, 0, steve);
		assertEquals(bob,e.removeLifeForm(0, 0));
		assertNull(e.removeLifeForm(3, 0));
	}
	
	/**
	 * Tests the getLifeForm function of the environment class.
	 */
	@Test
	public void testGetLifeForm()
	{
		LifeForm bob = new MockLifeForm("Bob",40);
		LifeForm steve = new MockLifeForm("Steve",40);
		Environment e = new Environment();
		e.addLifeForm(0, 0, bob);
		e.addLifeForm(1, 0, steve);
		assertEquals(bob, e.getLifeForm(0, 0));
		assertEquals(steve,e.getLifeForm(1, 0));
		
	}

}
