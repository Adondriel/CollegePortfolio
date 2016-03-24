/**
 * 
 */
package state;

import lifeform.LifeForm;
import environment.Environment;
import exceptions.WeaponException;

/**
 * @author Dr. Alice Armstrong
 *
 */
public class OutOfAmmoState extends ActionState {

	public OutOfAmmoState(AIContext context)
	{
		super(context); 
	}
	
	/**
	 * Evaluation: If  not dead, call reload method. Otherwise call dead method. 
	 * Dead: Move to Dead state
	 * Reload: Reload the weapon, change to Has Weapon state.
	 * @throws WeaponException 
	 */
	@Override
	public void executeAction() {
		//check if dead
		if(lifeform.getCurrentLifePoints() == 0)
		{
			//move to dead state
			context.setCurrentState(context.getDeadState());
			e.updateCell(lifeform.getRow(), lifeform.getCol());
		}
		else
		{
			lifeform.getWeapon().reload();
			//move to HasWeapon state
			context.setCurrentState(context.getHasWeaponState());
			e.updateCell(lifeform.getRow(), lifeform.getCol());
		}
	}

}
