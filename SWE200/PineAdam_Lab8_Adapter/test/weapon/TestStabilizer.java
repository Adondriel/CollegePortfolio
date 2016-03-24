/**
 * 
 */
package weapon;

import static org.junit.Assert.*;
import exceptions.AttachmentException;
import exceptions.WeaponException;
import gameplay.SimpleTimer;

import org.junit.Test;

/**
 * @author Dr. Alice Armstrong
 *
 */
public class TestStabilizer {



	/**
	 * tests wrapping a Stabilizer around a PlasmaCannon
	 * @throws WeaponException 
	 */
	@Test
	public void testStabilizerPlasmaCannon() throws AttachmentException, WeaponException {
		Stabilizer stabilizerPlasma = new Stabilizer(new PlasmaCannon()); 
		SimpleTimer timer = new SimpleTimer(); 
		timer.addTimeObserver(stabilizerPlasma);
		timer.timeChanged();
		
		//check that the number of attachments was updated
		assertEquals(1, stabilizerPlasma.getNumAttachments()); 
		
		//check toString()
		assertEquals("PlasmaCannon +Stabilizer", stabilizerPlasma.toString()); 
		
		//fire the PlasmaCannon once at something in range (15)
		//power weakens with each shot
		//damage should be 50*100% = 50
		//with the stabilizer, damage should be 50*(1.25) = 62
		assertEquals(62, stabilizerPlasma.fire(15)); 
		timer.timeChanged();
		
		//test at base.maxRange (20)
		//PlasmaCannon damage is 50*75% = 37
		//with Stabilizer: 37*1.25 = 46
		assertEquals(46, stabilizerPlasma.fire(20)); 
		timer.timeChanged();
		
		//test out of base.maxRange (45)
		//PlasmaCannon damage is 0
		//with stabilizer boost = 0*1.25 = 0
		assertEquals(0, stabilizerPlasma.fire(45)); 
		timer.timeChanged();
		
		//test at base.maxRange (20)
		//PlasmaCannon damage is 50*25% = 12
		//with Stabilizer: 12*1.25 = 15
		assertEquals(15, stabilizerPlasma.fire(20)); 
		timer.timeChanged();
		
		//check that the PlasmaCannon automatically reloaded
		assertEquals(4, stabilizerPlasma.getCurrentAmmo()); 
		
		//test out of scope range (70)
		assertEquals(0, stabilizerPlasma.fire(70)); 
		timer.timeChanged();
	}
	
	/**
	 * tests wrapping a Stabilizer around a Scope around a Plasma Cannon
	 * @throws AttachmentException
	 * @throws WeaponException
	 */
	@Test
	public void testStabilizerScopePlasmaCannon() throws AttachmentException, WeaponException
	{
		Stabilizer stabilizerScopePlasma = new Stabilizer(new Scope(new PlasmaCannon())); 
		SimpleTimer timer = new SimpleTimer(); 
		timer.addTimeObserver(stabilizerScopePlasma);
		timer.timeChanged();
		
		//check that the number of attachments was updated
		assertEquals(2, stabilizerScopePlasma.getNumAttachments()); 
		
		//check toString()
		assertEquals("PlasmaCannon +Scope +Stabilizer", stabilizerScopePlasma.toString()); 
		
		//fire the PlasmaCannon once at something in range (15)
		//power weakens with each shot
		//damage should be 50*100% = 50
		//with the scope, damage should be 50*(1+((30-15)/30) = 75
		//with the stabilizer, damage should be 75*(1.25) = 93
		assertEquals(93, stabilizerScopePlasma.fire(15)); 
		timer.timeChanged();
		
		//test at base.maxRange (20)
		//PlasmaCannon damage is 50*75% = 37
		//with the scope, damage should be 37*(1+((30-20)/30) = 49
		//with Stabilizer: 49*1.25 = 61
		assertEquals(61, stabilizerScopePlasma.fire(20)); 
		timer.timeChanged();
		
		//test out of base.maxRange (25), but within Scope range
		//PlasmaCannon damage is 0
		//with the scope, damage should be 5+ 50*50% = 30
		//with stabilizer boost = 30*1.25 = 37
		assertEquals(37, stabilizerScopePlasma.fire(25)); 
		timer.timeChanged();
		
		//test at base.maxRange (20)
		//PlasmaCannon damage is 50*25% = 12
		//with the scope, damage should be 12*(1+((30-20)/30) = 15
		//with Stabilizer: 15*1.25 = 20
		assertEquals(20, stabilizerScopePlasma.fire(20)); 
		timer.timeChanged();
		
		//check that the PlasmaCannon automatically reloaded
		assertEquals(4, stabilizerScopePlasma.getCurrentAmmo()); 
		
		//test out of scope range (70)
		assertEquals(0, stabilizerScopePlasma.fire(70)); 
		timer.timeChanged();
	}
	
	@Test
	public void testStabilizerStabilizerPlasmaCannon() throws AttachmentException, WeaponException
	{
		Stabilizer stabilizerStabilizerPlasma = new Stabilizer(new Stabilizer(new PlasmaCannon())); 
		SimpleTimer timer = new SimpleTimer(); 
		timer.addTimeObserver(stabilizerStabilizerPlasma);
		timer.timeChanged();
		
		//check that the number of attachments was updated
		assertEquals(2, stabilizerStabilizerPlasma.getNumAttachments()); 
		
		//check toString()
		assertEquals("PlasmaCannon +Stabilizer +Stabilizer", stabilizerStabilizerPlasma.toString()); 
		
		//fire the PlasmaCannon once at something in range (15)
		//power weakens with each shot
		//damage should be 50*100% = 50
		//with the stabilizer, damage should be 50*(1.25) = 62
		//with the stabilizer, damage should be 62*(1.25) = 77
		assertEquals(77, stabilizerStabilizerPlasma.fire(15)); 
		timer.timeChanged();
		
		//test at base.maxRange (20)
		//PlasmaCannon damage is 50*75% = 37
		//with Stabilizer: 37*1.25 = 46
		//with Stabilizer: 46*1.25 = 57
		assertEquals(57, stabilizerStabilizerPlasma.fire(20)); 
		timer.timeChanged();
		
		//test out of base.maxRange (45)
		//PlasmaCannon damage is 0
		//with stabilizer boost = 0*1.25 = 0
		assertEquals(0, stabilizerStabilizerPlasma.fire(45)); 
		timer.timeChanged();
		
		//test at base.maxRange (20)
		//PlasmaCannon damage is 50*25% = 12
		//with Stabilizer: 12*1.25 = 15
		//with Stabilizer: 15*1.25 = 18
		assertEquals(18, stabilizerStabilizerPlasma.fire(20)); 
		timer.timeChanged();
		
		//check that the PlasmaCannon automatically reloaded
		assertEquals(4, stabilizerStabilizerPlasma.getCurrentAmmo()); 
		
		//test out of scope range (70)
		assertEquals(0, stabilizerStabilizerPlasma.fire(70)); 
		timer.timeChanged();
	}
	
	@Test
	public void testStabilizerPowerBoostPlasmaCannon() throws AttachmentException, WeaponException
	{
		Stabilizer stabilizerPowerBoostPlasma = new Stabilizer(new PowerBooster(new PlasmaCannon())); 
		SimpleTimer timer = new SimpleTimer(); 
		timer.addTimeObserver(stabilizerPowerBoostPlasma);
		timer.timeChanged();
		
		//check that the number of attachments was updated
		assertEquals(2, stabilizerPowerBoostPlasma.getNumAttachments()); 
		
		//check toString()
		assertEquals("PlasmaCannon +PowerBooster +Stabilizer", stabilizerPowerBoostPlasma.toString()); 
		
		//fire the PlasmaCannon once at something in range (215)
		//power weakens with each shot
		//damage should be 50*100% = 50
		//with the powerBooster, damage should be 50*(1+4/4) = 100
		//with the stabilizer, damage should be 100*(1.25) = 125
		assertEquals(125, stabilizerPowerBoostPlasma.fire(15)); 
		timer.timeChanged();
		
		//test at base.maxRange (20)
		//PlasmaCannon damage is 50*75% = 37
		//with PowerBooster: 37*(1+3/4) = 64
		//with Stabilizer: 64*1.25 = 80
		assertEquals(80, stabilizerPowerBoostPlasma.fire(20)); 
		timer.timeChanged();
		
		//test out of base.maxRange (45)
		//PlasmaCannon damage is 0
		//with stabilizer boost = 0*1.25 = 0
		assertEquals(0, stabilizerPowerBoostPlasma.fire(45)); 
		timer.timeChanged();
		
		//test at base.maxRange (20)
		//PlasmaCannon damage is 50*25% = 12
		//with PowerBooster: 12*1.25 = 15
		//with Stabilizer: 15*1.25 = 18
		assertEquals(18, stabilizerPowerBoostPlasma.fire(20)); 
		timer.timeChanged();
		
		//check that the PlasmaCannon automatically reloaded
		assertEquals(4, stabilizerPowerBoostPlasma.getCurrentAmmo()); 
		
		//test out of scope range (70)
		assertEquals(0, stabilizerPowerBoostPlasma.fire(70)); 
		timer.timeChanged();
	}
	
}
