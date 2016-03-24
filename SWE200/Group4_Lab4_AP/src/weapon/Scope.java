package weapon;

/**
 * @author Benjamin Uleau
 *
 */
public class Scope extends Attachment
{
	int ampedMaxRange=w.getMaxRange()+10;
	double maxRangeDamage=super.calcDmg(5);
	/**
	 * @param w the weapon
	 */
	public Scope(GenericWeapon w)
	{
		super(w);
	}

	public int calcDmg(int distance)
	{
		if(w.getMaxRange()<distance && distance<=ampedMaxRange){
			System.out.println("Within new range");
			return calcDmg(maxRange)+5;
		}
		else if(w.getMaxRange()>=distance){
			System.out.println("Within original range");
			double doubleDamage=w.calcDmg(distance);
			double numerator=ampedMaxRange-distance;
			double denominator=ampedMaxRange;
			double rangeFraction=numerator/denominator;
			double inRangeDamage=doubleDamage*(1+rangeFraction);
			return (int)inRangeDamage;
		}
		else{
			System.out.println("Out of range");
			return 0;
		}
	}
}
