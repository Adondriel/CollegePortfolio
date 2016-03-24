/**
 * 
 */
package state;

import static org.junit.Assert.*;
import lifeform.Alien;

import org.junit.Before;
import org.junit.Test;

import environment.Environment;
import exceptions.RecoveryRateException;

/**
 * @author Dr. Alice Armstrong
 *
 */
public class TestAIContext {

	Alien a; 
	Environment e; 
	@Before
	public void setUp() throws RecoveryRateException
	{
		a = new Alien("Bob", 50); 
		e= Environment.getEnvironment(12, 12); 
		e.clearBoard();
	}
	
	/**
	 * check that initialization & all getters work
	 */
	@Test
	public void testInitialization() {
		AIContext context = new AIContext(a, e); 
	
		assertTrue(context.getCurrentState() instanceof NoWeaponState); 
		
		assertTrue(context.getDeadState() instanceof DeadState); 
		assertTrue(context.getHasWeaponState() instanceof HasWeaponState); 
		assertTrue(context.getNoWeaponState() instanceof NoWeaponState); 
		assertTrue(context.getOutOfAmmoState() instanceof OutOfAmmoState); 
		
		assertEquals(context.getLifeForm(), a); 
		assertEquals(context.getEnvironment(), e); 
	}
	
	/**
	 * make sure that the AIContext can change it's current state
	 */
	@Test
	public void testSetters()
	{
		AIContext context = new AIContext(a, e); 
		assertTrue(context.getCurrentState() instanceof NoWeaponState); 
		
		context.setCurrentState(new DeadState(context));
		assertTrue(context.getCurrentState() instanceof DeadState);
		
		context.setCurrentState(new HasWeaponState(context));
		assertTrue(context.getCurrentState() instanceof HasWeaponState);
		
		context.setCurrentState(new OutOfAmmoState(context));
		assertTrue(context.getCurrentState() instanceof OutOfAmmoState);
		
		context.setCurrentState(new NoWeaponState(context));
		assertTrue(context.getCurrentState() instanceof NoWeaponState);
	}

}
