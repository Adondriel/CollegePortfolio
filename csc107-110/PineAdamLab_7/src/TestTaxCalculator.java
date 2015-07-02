import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Pine
 * A class that tests all of our TaxCalculator methods!
 */
public class TestTaxCalculator
{
	TaxCalculator tc;

	/**
	 * Set up the tc variable for use in later tests.
	 */
	@Before
	public void setup()
	{
		tc = new TaxCalculator();
	}

	/**
	 * Test really simple taxes
	 */
	@Test
	public void testSimpleTax()
	{
		assertEquals(0.05 * 10000, tc.simpleTax(10000.00), 0.001);
		assertEquals(0.05 * 50000, tc.simpleTax(50000.00), 0.001);
	}

	/**
	 * In two level taxes, people who make less than $30,000 pay no tax and,
	 * everyone else pays 5%. Test the border cases where the behavior changes
	 * and a few other values.
	 */
	@Test
	public void testTwoLevelTax()
	{
		assertEquals(0, tc.twoLevelTax(29999.99), 0.001);
		assertEquals(30000 * 0.05, tc.twoLevelTax(30000), 0.001);

		assertEquals(0, tc.twoLevelTax(10000), 0.001);
		assertEquals(34567.89 * 0.05, tc.twoLevelTax(34567.89), 0.001);
	}

	/**
	 * In three level taxes, people who make less than $30,000 pay no tax and,
	 * people making at least $30,000 but not $50,00 pay 5%, and everyone else
	 * pays 7%.
	 */
	@Test
	public void testThreeLevelTax()
	{
		assertEquals(0, tc.threeLevelTax(29999.99), 0.001);
		assertEquals(30000 * 0.05, tc.threeLevelTax(30000), 0.001);
		assertEquals(49999.99 * 0.05, tc.threeLevelTax(49999.99), 0.001);
		assertEquals(50000 * 0.07, tc.threeLevelTax(50000), 0.001);
	}

	/**
	 * This tax gives you a break for every child you have. Your adjusted income
	 * is $1000 less than your actual income for every child you have. Then your
	 * taxes are computed with the same three ranges as the three range tax.
	 */
	@Test
	public void testSimpleDependants()
	{
		assertEquals(0, tc.simpleDependentTax(32999.99, 3), 0.001);
		assertEquals(30000 * 0.05, tc.simpleDependentTax(34000.00, 4), 0.001);
		assertEquals(49999.99 * 0.05, tc.simpleDependentTax(51999.99, 2), 0.001);
		assertEquals(50000 * 0.07, tc.simpleDependentTax(53000, 3), 0.001);
	}

	/**
	 * In this tax, the deduction for your dependents depends on the number of
	 * children you have. The first three children result in deductions of $1000
	 * each, but if you have four or more children, each results in a deduction
	 * of $1100. Your tax is computed from your adjusted income as follows:
	 * 0-19,999.99: 5% 
	 * 20,000 - and up = 15%
	 */
	@Test
	public void testTwoLevelDeductions()
	{
		assertEquals(19999.99 * 0.05, tc.twoLevelDeductions(22999.99, 3), 0.001);
		assertEquals(19999.99 * 0.05, tc.twoLevelDeductions(19999.99 + 4400, 4), 0.001);
		assertEquals(20000 * 0.15, tc.twoLevelDeductions(23000, 3), 0.001);
		assertEquals(20000 * 0.15, tc.twoLevelDeductions(24400, 4), 0.001);
	}

	/**
	 * This tax is based on the income of two spouses. They pay 5% of the total
	 * of their incomes.
	 */
	@Test
	public void testSpouseSimple()
	{
		assertEquals(40000.02 * 0.05, tc.spouseSimple(20000.01, 20000.01), 0.001);
		assertEquals(30000 * 0.05, tc.spouseSimple(20000, 10000), 0.001);
	}

	/**
	 * This tax is for married couples, and the tax depends on how far apart
	 * their salaries are. If the salaries are within $10,000 of each other, the
	 * tax is 5% of the total. If they are more than $10,000 apart, the tax is
	 * 5% of the larger income plus 3% of the smaller one.
	 */
	@Test
	public void testSpouceDistance()
	{
		// The border case here is when the difference between the
		// salaries is 10,000 and the value changes at 10,000.01.
		// Since the order of the parameters shouldn't matter,
		// do both cases with both parameter orders.
		assertEquals(40000 * 0.05, tc.spouseDistance(25000, 15000), 0.001);
		assertEquals(40000 * 0.05, tc.spouseDistance(15000, 25000), 0.001);
		assertEquals(30000.01 * 0.05 + 20000 * 0.03, tc.spouseDistance(20000.00, 30000.01), 0.001);
		assertEquals(30000.01 * 0.05 + 20000 * 0.03, tc.spouseDistance(30000.01, 20000.00), 0.001);
	}

	/**
	 * This tax includes an alternate minimum tax for people in the top tax
	 * bracket. incomes are adjusted by $1000 for each dependent and
	 * the tax levels are:
	 * 0-29,999,99: 0%
	 * 30,000 - 79,999.99: 5%
	 * 80,000 and up: 10%
	 * However, if you are in the top tax bracket and your deductions are more
	 * than 10% of your income, you pay taxes on your entire income.
	 */
	@Test
	public void testAlternateMinimumTax()
	{
		assertEquals(0, tc.alternateMinimum(32999.93, 3), 0.001);
		assertEquals(30000 * 0.05, tc.alternateMinimum(34000, 4), 0.001);
		assertEquals(79999.99 * 0.05, tc.alternateMinimum(81999.99, 2), 0.001);
		assertEquals(80000 * 0.10, tc.alternateMinimum(83000, 3), 0.001);
		assertEquals((100000 - 10000) * 0.10, tc.alternateMinimum(100000, 10), 0.001);
		assertEquals(99999.99 * 0.10, tc.alternateMinimum(99999.99, 10), 0.001);
	}

	/**
	 * This is a single income tax with three tax levels: 
	 * 0 - 19,999.99: 0%
	 * 20,000 - 39,999.99: 5% 
	 * 40,000 and up: 10% 
	 * Everyone gets a $1000 deduction
	 * per dependent, However it also includes these special instructions:
	 *  - for the middle tax group, if their mortgage interest is more than 2% of their
	 * adjusted income, they can deduct it from that adjusted income.
	 * - for the top tax bracket, if their total deductions are more than 10% of their
	 * income, they pay taxes on their entire income.
	 * 
	 * The parameters to specialTaxLaws are:
	 *  - income
	 *  - number of dependents
	 *  - mortgage interest paid
	 */
	@Test
	public void testSpecialTaxLaws()
	{
		// test the border between the first two brackets
		assertEquals(0, tc.specialTaxLaws(22999.99, 3, 0.0), 0.001);
		assertEquals(20000 * 0.05, tc.specialTaxLaws(24000.00, 4, 0.0), 0.001);
		
		// test the border between the top two brackets
		assertEquals(39999.99 * 0.05, tc.specialTaxLaws(41999.99, 2, 0.0), 0.001);
		assertEquals(40000.00 * 0.10, tc.specialTaxLaws(43000.00, 3, 0.0), 0.001);
		
		// In the middle bracket we also need to test the border for mortgage interest
		assertEquals(30000.00 * 0.05, tc.specialTaxLaws(30000.00, 0, 30000 * 0.02), 0.001);
		assertEquals((30000 - (30000 * 0.02 + 0.01)) * 0.05, tc.specialTaxLaws(30000, 0, 30000 * 0.02 + 0.01), 0.001);
		
		// Also do one one that has dependents to make sure it is comparing the adjusted income.
		assertEquals((30000 - (30000 * 0.02 + 0.01)) * 0.05, tc.specialTaxLaws(31000, 1, 30000 * 0.02 + 0.01), 0.001);
		
		// in the top bracket, we also need to test the border of the alternate minimum tax.
		assertEquals((100000 - 10000) * 0.10, tc.specialTaxLaws(100000, 10, 0.0), 0.001);
		assertEquals(99999.99 * 0.10, tc.specialTaxLaws(99999.99, 10, 0.0), 0.001);
	}
}
