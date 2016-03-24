package weapon;

/**
 * @author Benjamin Uleau
 *
 */
public class PowerBooster extends Attachment
{
	/**
	 * @param w the weaopn
	 */
	public PowerBooster(GenericWeapon w)
	{
		super(w);
	}

	public int calcDmg(int distance)
	{
		double base=w.calcDmg(distance);
		double newDmg=base*(1+w.getCurrentAmmo()/w.getMaxAmmo());
		return (int)newDmg;
	}
}
