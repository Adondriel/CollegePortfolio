package weapon;

/**
 * @author Benjamin Uleau
 *
 */
public abstract class Attachment extends GenericWeapon implements Weapon 
{
	GenericWeapon w;
	
	/**
	 * @param w the weapon
	 */
	public Attachment(GenericWeapon w){
		this.w=w;
	}
}
