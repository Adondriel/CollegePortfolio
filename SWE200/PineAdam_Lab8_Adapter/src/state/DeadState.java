/**
 * 
 */
package state;

import environment.CellInfo;
import environment.Environment;
import weapon.Weapon;
import lifeform.LifeForm;

/**
 * @author Dr. Alice Armstrong
 *
 */
public class DeadState extends ActionState {

	public DeadState(AIContext context)
	{
		super(context); 
	}
	
	/**
	 * If the LifeForm had a weapon then remove that weapon and place it in a random Cell in the Environment (that has space).  
	 * Put LifeForm back to full life points.  
	 * Place LifeForm in a random unoccupied Cell. 
	 */
	@Override
	public void executeAction() {
		CellInfo info; 
		int oldRow = lifeform.getRow(); 
		int oldCol = lifeform.getCol(); 
		//remove the lifeform's weapon
		Weapon tempWeapon = lifeform.dropWeapon(); 
		
		//place it in a new random Cell that has space for one
		do
		{
			info = e.getRandomCell(); 
		}
		while((info.hasWeapon1() && info.hasWeapon2()) || (info.getRow() == oldRow && info.getCol() == oldCol));
		e.addWeapon(tempWeapon, info.getRow(), info.getCol()); 
		
		//remove the dead lifeform 
		e.removeLifeForm(lifeform.getRow(), lifeform.getCol());
		e.updateCell(oldRow, oldCol);
		
		//reload to full points
		lifeform.revive(); 
		
		//place unarmed lifeform in a new randon cell
		do
		{
			info = e.getRandomCell(); 
		}
		while(info.hasLife() || (info.getRow() == oldRow || info.getCol() == oldCol));
		e.addLifeForm(lifeform, info.getRow(), info.getCol());
		e.updateCell(lifeform.getRow(), lifeform.getCol());
		
		//move to the NoWeaponState
		context.setCurrentState(context.getNoWeaponState());
	}

}
