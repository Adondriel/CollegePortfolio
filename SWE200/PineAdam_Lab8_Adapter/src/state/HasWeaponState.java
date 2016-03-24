/**
 * 
 */
package state;

import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import environment.Environment;

/**
 * @author Dr. Alice Armstrong
 *
 */
public class HasWeaponState extends ActionState {
	
	public HasWeaponState(AIContext context)
	{
		super(context); 
	}

	/**
	 * Evaluation: If valid target in range call attackTarget.  If no target call search.
	 * Attack Target: Fire the weapon. 
	 * Search: Turn random direction and 50% of time move to new Cell.
	 */
	@Override
	public void executeAction() 
	{
		//check if dead
		if (context.getLifeForm().getCurrentLifePoints() == 0)
		{
			//move to dead state
			context.setCurrentState(context.getDeadState());
		}
		else
		{
			int range = lifeform.getAttackRange()/5; 
			int nextCell = 1; 
			boolean attacked = false;
			
			//look for a valid opponent
			if (lifeform.getDirection().equals("North"))
			{
				//make sure we stay on the board
				while(range - nextCell >0 && lifeform.getRow() - nextCell >=0)
				{
					LifeForm opponent = e.getLifeForm(lifeform.getRow() - nextCell, lifeform.getCol()); 
					if (opponent != null)
					{
						//look for a member of the opposite team
						if ((lifeform instanceof Human && opponent instanceof Alien) || (lifeform instanceof Alien && opponent instanceof Human))
						{
							//attack the opponent
							lifeform.attack(opponent, nextCell*5);
							attacked = true; 
							e.updateCell(lifeform.getRow(), lifeform.getCol());
							e.updateCell(opponent.getRow(), opponent.getCol());
						}
						else
						{
							//can't attack because a team member is in the way
							break; 
						}
					}
					nextCell++; 
				}
			}
			else if (lifeform.getDirection().equals("South"))
			{
				//make sure we stay on the board
				while(range - nextCell >= 0 && lifeform.getRow()+nextCell < e.getNumRows())
				{
					LifeForm opponent = e.getLifeForm(lifeform.getRow() + nextCell, lifeform.getCol()); 
					if (opponent != null)
					{
						//look for a member of the opposite team
						if ((lifeform instanceof Human && opponent instanceof Alien) || (lifeform instanceof Alien && opponent instanceof Human))
						{
							//attack the opponent
							lifeform.attack(opponent, nextCell*5);
							attacked = true; 
							e.updateCell(lifeform.getRow(), lifeform.getCol());
							e.updateCell(opponent.getRow(), opponent.getCol());
						}
						else
						{
							//can't attack because a team member is in the way
							break; 
						}
					}
					nextCell++; 
				}
			}
			else if (lifeform.getDirection().equals("East"))
			{
				//make sure we stay on the board
				while(range - nextCell >= 0 && lifeform.getCol()+nextCell < e.getNumCols())
				{
					LifeForm opponent = e.getLifeForm(lifeform.getRow(), lifeform.getCol()+nextCell); 
					if (opponent != null)
					{
						//look for a member of the opposite team
						if ((lifeform instanceof Human && opponent instanceof Alien) || (lifeform instanceof Alien && opponent instanceof Human))
						{
							//attack the opponent
							lifeform.attack(opponent, nextCell*5);
							attacked = true;
							e.updateCell(lifeform.getRow(), lifeform.getCol());
							e.updateCell(opponent.getRow(), opponent.getCol());
						}
						else
						{
							//can't attack because a team member is in the way
							break; 
						}
					}
					nextCell++; 
				}
			}
			else 
			{
				//make sure we stay on the board
				while(range - nextCell >= 0 && lifeform.getCol()-nextCell  >= 0)
				{
					LifeForm opponent = e.getLifeForm(lifeform.getRow(), lifeform.getCol()-nextCell); 
					if (opponent != null)
					{
						//look for a member of the opposite team
						if ((lifeform instanceof Human && opponent instanceof Alien) || (lifeform instanceof Alien && opponent instanceof Human))
						{
							//attack the opponent
							lifeform.attack(opponent, nextCell*5);
							attacked = true; 
							e.updateCell(lifeform.getRow(), lifeform.getCol());
							e.updateCell(opponent.getRow(), opponent.getCol());
						}
						else
						{
							//can't attack because a team member is in the way
							break; 
						}
					}
					nextCell++; 
				}
			}
			
			if (lifeform.getWeapon().getCurrentAmmo() == 0)
			{
				//move to the no ammo state
				context.setCurrentState(context.getOutOfAmmoState());
			}
			
			//no valid opponent found in range
			if (!attacked)
			{
				String dir; 
				//get a new direction and change the lifeform's direction
				do
				{
					dir = newDirection(); 
				}
				while(dir.equals(lifeform.getDirection())); 
				lifeform.setDirection(dir);
				e.updateCell(lifeform.getRow(), lifeform.getCol());
				
				//50% of the time, move to a new location
				int move = (int)(Math.random()*2);
				if(move == 0)
				{
					int oldRow, oldCol; 
					oldRow = lifeform.getRow(); 
					oldCol = lifeform.getCol(); 
					e.move(lifeform);
					e.updateCell(oldRow, oldCol); 
					e.updateCell(lifeform.getRow(), lifeform.getCol());
				}
			}	
		}
		
	}
	
	private String newDirection()
	{
		int direction = (int)(Math.random()*4); 
		if(direction == 0)
		{
			return "North"; 
		}
		else if(direction ==1)
		{
			return "South"; 
		}
		else if (direction ==2)
		{
			return "East"; 
		}
		else
		{
			return "West"; 
		}
	}

}
