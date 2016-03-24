package state;

import static org.junit.Assert.*;

import javax.swing.JOptionPane;

import org.junit.Test;

import exceptions.RecoveryRateException;
import gui.GUI;

/**
 * @author Adam Pine
 */
public class TestAIContextAdapter {
	/**
	 * Test the direction commands to ensure that they actually turn the humans
	 * @throws RecoveryRateException
	 * @throws InterruptedException
	 */
	@Test
	public void testDirectionCommands() throws RecoveryRateException, InterruptedException {
		GUI.main(null);
		Thread.sleep(2500);
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Are the humans facing north? \nPlease click the east button next."));
		Thread.sleep(2500);
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Are the humans facing east? \nPlease click the south button next."));
		Thread.sleep(2500);
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Are the humans facing south? \nPlease click the west button next."));
		Thread.sleep(2500);
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Are the humans facing west?"));
	}
	/**
	 * Test that the human can pickup a weapon
	 * @throws InterruptedException
	 */
	@Test
	public void testPickupWeapon() throws InterruptedException{
		GUI.main(null);
		Thread.sleep(30000);
		//very difficult to test, cause you have to get a human to a cell that contains a weapon.
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Did the human pickup the weapon in the cell?"));
	}
	
	/**
	 * Test that the Humans under your control can drop weapons.
	 * @throws InterruptedException
	 */
	@Test
	public void testDropWeapon() throws InterruptedException{
		GUI.main(null);
		Thread.sleep(30000);
		//very difficult to test, cause you have to get a human to a cell that contains a weapon.
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Did the human drop the weapon in the cell?"));
	}
	
	/**
	 * Test that the human can fire a weapon.
	 * @throws InterruptedException
	 */
	@Test
	public void testFireWeapon() throws InterruptedException{
		GUI.main(null);
		Thread.sleep(30000);
		//very difficult to test, cause you have to get a human to a cell that contains a weapon.
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Did the human fire the weapon?"));
	}
	/**
	 * Test that it will only execute the most recently clicked command item.
	 * @throws InterruptedException
	 */
	@Test
	public void testMultipleClicks() throws InterruptedException{
		GUI.main(null);
		Thread.sleep(30000);
		//very difficult to test, cause you have to get a human to a cell that contains a weapon.
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Did the human only execute one action for it's turn?"));
	}
	/**
	 * Test that not clicking just makes the humans not do anything. You have to actually play the game in order to win!
	 * @throws InterruptedException
	 */
	@Test
	public void testNoClicks() throws InterruptedException{
		GUI.main(null);
		Thread.sleep(30000);
		//very difficult to test, cause you have to get a human to a cell that contains a weapon.
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Did the human not execute any commands other than turning north?"));
	}
	
	/**
	 * Extra Test
	 * Test that the alien's do not respawn after killing them.
	 * @throws InterruptedException
	 */
	@Test
	public void testNoRespawn() throws InterruptedException{
		GUI.main(null);
		Thread.sleep(100000);
		//very difficult to test, cause you have to get a human to a cell that contains a weapon.
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "After killing an alien did he respawn?"));
	}
	

}
