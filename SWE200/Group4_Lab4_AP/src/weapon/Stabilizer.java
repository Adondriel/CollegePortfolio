package weapon;

/**
 * @author Benjamin Uleau
 *
 */
public class Stabilizer extends Attachment
{
	/**
	 * @param w the weapon
	 */
	public Stabilizer(GenericWeapon w)
	{
		super(w);
	}

	public int calcDmg(int distance)
	{
		double base=w.calcDmg(5);
		double newDmg=base*1.25;
		System.out.println(newDmg);
		if(w.getCurrentAmmo()==0){
			w.reload();
		}
		return (int)newDmg;
	}
}
