package weapon;
import static org.junit.Assert.*;

import org.junit.Test;

import weapon.Attachment;
import weapon.Pistol;
import weapon.Stabilizer;


/**
 * @author Benjamin Uleau
 *
 */
public class TestStabilizer
{
	/**
	 * Test weapons with stabilizer as the first attachment
	 */
	@Test
	public void test()
	{
		Pistol p=new Pistol();
		Attachment a=new Stabilizer(p);
		assertEquals(10, p.calcDmg(5));
		assertEquals(12, a.calcDmg(5));
	}

}
