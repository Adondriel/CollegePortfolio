package Lab_12;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;


/**
 * @author Adam Pine
 * The test cases for the ZipCode class. ZipCode class needs to be rewritten.
 */
public class ZipCodeTest
{

	/**
	 * Test the initialization of the zip code.
	 */
	@Test
	public void testInitSimple() throws ZipCodeException
	{
		ZipCode z1 = new ZipCode(17257);
		assertEquals("17257", z1.toString());
	}
	
	/**
	 * Test the borders of the toString method.
	 */
	@Test
	public void testZipBorders() throws ZipCodeException
	{
		ZipCode z1 = new ZipCode(12340);
		assertEquals("12340", z1.toString());
		ZipCode z2 = new ZipCode(1234);
		assertEquals("01234", z2.toString());
		ZipCode z3 = new ZipCode(123);
		assertEquals("00123", z3.toString());
		ZipCode z4 = new ZipCode(12);
		assertEquals("00012", z4.toString());
		ZipCode z5 = new ZipCode(1);
		assertEquals("00001", z5.toString());
	}

	/**
	 * Test the getCheckDigit method, also test it's border cases of 0, and 9.
	 */
	@Test
	public void testCheckDigit() throws ZipCodeException
	{
		ZipCode z1 = new ZipCode(1234);
		assertEquals(0, z1.getCheckDigit());	
		ZipCode z2 = new ZipCode(11234);
		assertEquals(9, z2.getCheckDigit());	
		ZipCode z3 = new ZipCode(11);
		assertEquals(8, z3.getCheckDigit());
		ZipCode z4 = new ZipCode(11111);
		assertEquals(5, z4.getCheckDigit());
		ZipCode z5 = new ZipCode(17257);
		assertEquals(8, z5.getCheckDigit());
	}
	
	/**
	 * Test the getBarCode method, makes sure it can also support zeros.
	 */
	@Test
	public void testGetBarCode() throws ZipCodeException
	{
		ZipCode z1 = new ZipCode(17257);
		assertEquals("|'''|||'''|''|'|'|'|'|'''||''|'|", z1.getBarCode());
		ZipCode z2 = new ZipCode(123);
		assertEquals("|||'''||''''''||''|'|''||''|''||", z2.getBarCode());	
	}
	
	/**
	 * Start of lab 12
	 * @throws ZipCodeException 
	 */
	@Test
	public void testDecodeSingleDigit() throws ZipCodeException
	{
		ZipCode z = new ZipCode("|'''||||'''||'''||'''||'''|'|''|");
		assertEquals("10000", z.toString());
	}
	
	/**
	 * Test the decoding process of the string zipcode.
	 * @throws ZipCodeException
	 */
	@Test
	public void testDecode() throws ZipCodeException
	{
		ZipCode z1 = new ZipCode("|'''|||'''|''|'|'|'|'|'''||''|'|");
		assertEquals("17257", z1.toString());
		// Leading zeros
		ZipCode z2 = new ZipCode("|||'''|'''|''|'|'|'|'|'''||'|''|");
		assertEquals("07257", z2.toString());
		//we need numbers, 3, 4, 6, 8, 9
		ZipCode z3 = new ZipCode("|''||''|''|'||''|''|'|'|''||'''|");
		assertEquals("34689", z3.toString());
	}
	
	/**
	 * Test to make sure it errors when we give it too short of a zipcode
	 * @throws ZipCodeException
	 */
	@Test(expected = ZipCodeException.class) 
	public void testTooShort() throws ZipCodeException
	{
		ZipCode z = new ZipCode("|'''|||'''|''|'||'|'|'''||''|'|");
	}
	
	/**
	 * Test to see if it can detect the first framing bit as short
	 * @throws ZipCodeException
	 */
	@Test(expected = ZipCodeException.class) 
	public void testFirstShort() throws ZipCodeException
	{
		ZipCode z = new ZipCode("''''|||'''|''|'||'|'|'''||''|'|");
	}
	
	/**
	 * Test to see if it can detect the lastframing bit as being short.
	 * @throws ZipCodeException
	 */
	@Test(expected = ZipCodeException.class) 
	public void testLastShort() throws ZipCodeException
	{
		ZipCode z = new ZipCode("|'''|||'''|''|'||'|'|'''||''|''");
	}
	
	/**
	 * test to make sure it can check the parity of the barcode.
	 * @throws ZipCodeException
	 */
	@Test(expected = ZipCodeException.class) 
	public void testParity() throws ZipCodeException
	{
		ZipCode z = new ZipCode("|'''|||||||''|'||'|'||||||''|''");
		ZipCode z2 = new ZipCode("|'''|||'''|''|'||'|'|'''||''|''");
	}
}
