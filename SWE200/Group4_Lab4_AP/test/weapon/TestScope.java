package weapon;
import static org.junit.Assert.*;

import org.junit.Test;

import weapon.Attachment;
import weapon.GenericWeapon;
import weapon.Pistol;
import weapon.Scope;
import weapon.Weapon;


/**
 * @author Benjamin Uleau
 *
 */
public class TestScope
{

	/**
	 * Test the functionality of all weapons that have the scope as the first attachment
	 */
	@Test
	public void test()
	{
		//The weapon used for testing
		Pistol p=new Pistol();
		assertEquals(10, p.calcDmg(5));
		
		//The attachment that wraps the pistol
		Attachment s=new Scope(p);
		assertEquals(18, s.calcDmg(5));
		
		Pistol p2=new Pistol();
		assertEquals(2, p2.calcDmg(25));
		Attachment s2=new Scope(p2);
		assertEquals(7, s2.calcDmg(27));
	}

}
