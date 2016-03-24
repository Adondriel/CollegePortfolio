/**
 * 
 */
package state;

import weapon.Weapon;
import environment.Environment;
import lifeform.LifeForm;

/**
 * @author Dr. Alice Armstrong
 *
 */
public class NoWeaponState extends ActionState {
	
	public NoWeaponState(AIContext context)
	{
		super(context); 
	}

	/**
	 * Evaluation: Check if I'm dead first. If weapon in cell call acquireWeapon method.  If no weapon call search method.
	 * Acquire Weapon: Pickup weapon and change to Has Weapon state.
	 * Dead: Move to Dead state
	 * Search: Turn random direction and move to new Cell.
	 */
	@Override
	public void executeAction() 
	{
		//check if dead
		if (lifeform.getCurrentLifePoints() == 0)
		{
			//move to the dead state
			context.setCurrentState(context.getDeadState());
			e.updateCell(lifeform.getRow(), lifeform.getCol());
		}
		else
		{
			//check to see if there is a weapon available in this cell
			Weapon[] weapons = e.getWeapons(lifeform.getRow(), lifeform.getCol()); 
			
			//pick up weapon1 first, if available
			if(weapons[0] != null)
			{
				lifeform.pickUpWeapon(weapons[0]); 
				e.removeWeapon(weapons[0], lifeform.getRow(), lifeform.getCol());
				e.updateCell(lifeform.getRow(), lifeform.getCol());
				
				//move to HasWeapon state
				context.setCurrentState(context.getHasWeaponState());
			}
			//try weapon2, if no weapon1
			else if(weapons[1] != null)
			{
				lifeform.pickUpWeapon(weapons[1]); 
				e.removeWeapon(weapons[1], lifeform.getRow(), lifeform.getCol());
				e.updateCell(lifeform.getRow(), lifeform.getCol());
				//move to HasWeapon state
				context.setCurrentState(context.getHasWeaponState());
			}
			else
			{
				//there are no weapons in this cell, pick a random location and move there
				int direction = (int)(Math.random()*4); 
				if (direction == 0)
				{
					lifeform.setDirection("North"); 
					e.updateCell(lifeform.getRow(), lifeform.getCol());
					
				}
				else if (direction == 1)
				{
					lifeform.setDirection("South"); 
					e.updateCell(lifeform.getRow(), lifeform.getCol());
				}
				else if (direction == 2)
				{
					lifeform.setDirection("East");
					e.updateCell(lifeform.getRow(), lifeform.getCol());
				}
				else
				{
					 lifeform.setDirection("West"); 
					 e.updateCell(lifeform.getRow(), lifeform.getCol());
				}
				int oldRow, oldCol; 
				oldRow = lifeform.getRow(); 
				oldCol = lifeform.getCol(); 
				e.move(lifeform);
				e.updateCell(oldRow, oldCol); 
				e.updateCell(lifeform.getRow(), lifeform.getCol()); 
				
				//do not change state because we still don't have a weapon
			}
		}
		
	}

}
