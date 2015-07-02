
/**
 * @author Adam Pine
 * The class that contains all of our very useful methods to calculate different types of taxes.
 */
public class TaxCalculator
{
	static final int DEPENDENT_WORTH = 1000;
	static final int DEPENDENT_WORTH_TWO = 1100;
	/**
	 * Simple calculate a 5% tax on the income.
	 * @param income
	 * @return a 5% tax
	 */
	public double simpleTax(double income)
	{
		
		return 0.05 * income;
	}


	 /** 
	 * @param income
	 * @return tax amount for a 2 level tax bracket.
	 */
	public double twoLevelTax(double income)
	{
		if (income >= 30000)
		{
			return income * 0.05;
		}else
		{
			return 0;
		}
	}

	/**
	 * @param income
	 * @return tax amount for a 3 level tax bracket.
	 */
	public double threeLevelTax(double income)
	{
		if (income < 30000)
		{
			return 0.0;
		}
		if ((income >= 30000) && (income < 50000))
		{
			return income * 0.05;
		}else
		{
			return income * 0.07;
		}

	}


	/**
	 * @param income
	 * @param numberOfKids
	 * @return The three level tax of the modified income based on number of kids.
	 */
	public double simpleDependentTax(double income, int numberOfKids)
	{
		double modifiedIncome = income - (numberOfKids * 1000);
		return threeLevelTax(modifiedIncome);
	}


	/**
	 * @param income
	 * @param numberOfKids
	 * @return the amount of tax the person has to pay based on the income, with different discounts depending on number of kids.
	 */
	public double twoLevelDeductions(double income, int numberOfKids)
	{
		double modifiedIncome;
		if (numberOfKids < 4 && numberOfKids > 0)
		{
			modifiedIncome = income - (numberOfKids * DEPENDENT_WORTH);
		}else
		{
			modifiedIncome = income - (numberOfKids * DEPENDENT_WORTH_TWO);
		}
		
		if (modifiedIncome < 20000)
		{
			return modifiedIncome * 0.05;
		}else
		{
			return modifiedIncome * 0.15;
		}
	}


	/**
	 * @param incomeOne
	 * @param incomeTwo
	 * @return the amount of tax the couple has to pay on their combined income.
	 */
	public double spouseSimple(double incomeOne, double incomeTwo)
	{
		return (incomeOne + incomeTwo) * 0.05;
	}


	/**
	 * @param incomeOne
	 * @param incomeTwo
	 * @return the amount of tax a couple has to pay, based on the difference between their two incomes.
	 */
	public double spouseDistance(double incomeOne, double incomeTwo)
	{
		double maxIncome = Math.max(incomeOne, incomeTwo);
		double minIncome = Math.min(incomeOne, incomeTwo);
		double incomeDistance = maxIncome - minIncome;
		if(incomeDistance > 10000)
		{
			return (maxIncome * 0.05) + (minIncome * 0.03);
		}else
		{
			return spouseSimple(minIncome, maxIncome);
		}		
	}


	/**
	 * @param income
	 * @param dependents
	 * @return the amount of tax that a person owes, depending on the amount of deductions and amount of income.
	 */
	public double alternateMinimum(double income, int dependents)
	{
		double modifiedIncome = income - (dependents * DEPENDENT_WORTH);
		if ((modifiedIncome >= 30000) && (modifiedIncome < 80000))
		{
			return modifiedIncome * 0.05;
		}
		if ((income*0.1) < (dependents * DEPENDENT_WORTH))
		{
			return income * 0.10;
		}
		if (modifiedIncome >= 80000)
		{
			return modifiedIncome * 0.10;
		}
		return 0;
	}


	/**
	 * @param income
	 * @param dependents
	 * @param mortgageInterestPaid
	 * @return The amount of tax a person needs to pay 
	 */
	public double specialTaxLaws(double income, int dependents, double mortgageInterestPaid)
	{
		double modifiedIncome = income - (dependents * DEPENDENT_WORTH);
		if ((modifiedIncome >= 20000) && (modifiedIncome < 40000))
		{
			if (mortgageInterestPaid > (modifiedIncome*0.02))
			{
				return (modifiedIncome - mortgageInterestPaid) * 0.05;
			}
			return modifiedIncome*0.05;
		}
		if (modifiedIncome >= 40000)
		{
			if ((income*0.1) < (dependents * DEPENDENT_WORTH))
			{
				return income * 0.10;
			}
			return modifiedIncome * 0.10;
		}
		return 0;
	}
}
