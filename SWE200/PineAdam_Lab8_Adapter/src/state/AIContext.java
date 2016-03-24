/**
 * 
 */
package state;

import lifeform.LifeForm;
import environment.Environment;
import gameplay.SimpleTimer;
import gameplay.TimerObserver;

/**
 * @author Dr. Alice Armstrong
 * Edited by Adam Pine
 */
public class AIContext implements TimerObserver, AIContextInterface {
	private Environment e; 
	private LifeForm lifeform; 
	private ActionState currentState; 
	
	private HasWeaponState hasWeapon; 
	private NoWeaponState noWeapon; 
	private OutOfAmmoState noAmmo; 
	private DeadState dead; 
	
	public AIContext(LifeForm lifeform, Environment e){
		this.lifeform = lifeform;
		this.e = e; 
		
		hasWeapon = new HasWeaponState(this); 
		noWeapon = new NoWeaponState(this); 
		noAmmo = new OutOfAmmoState(this); 
		dead = new DeadState(this); 
		
		if(lifeform.getCurrentLifePoints() == 0)
		{
			currentState = dead; 
		}
		else if (lifeform.hasWeapon())
		{
			if(lifeform.getWeapon().getCurrentAmmo() == 0)
			{
				currentState = noAmmo; 
			}
			else
			{
				currentState = hasWeapon; 
			}
		}
		else
		{
			currentState = noWeapon; 
		}
	}
	
	/* (non-Javadoc)
	 * @see gameplay.TimerObserver#updateTime(int)
	 */
	@Override
	public void updateTime(int time) {
		execute(); 
		System.out.println(lifeform+ "executing..."); 
	}
	
	/**
	 * execute the current state's action
	 */
	public void execute()
	{
		currentState.executeAction();
	}
	
	/**
	 * 
	 * @return this context's OutOfAmmoState
	 */
	public OutOfAmmoState getOutOfAmmoState()
	{
		return noAmmo; 
	}
	
	/**
	 * 
	 * @return this context's HasWeaponState
	 */
	public HasWeaponState getHasWeaponState()
	{
		return hasWeapon; 
	}
	
	/**
	 * 
	 * @return this context's NoWeaponState
	 */
	public NoWeaponState getNoWeaponState()
	{
		return noWeapon; 
	}
	
	/**
	 * 
	 * @return this context's DeadState
	 */
	public DeadState getDeadState()
	{
		return dead; 
	}
	
	/**
	 * Change this context's state
	 * @param newState
	 */
	public void setCurrentState(ActionState newState)
	{
		currentState = newState; 
	}
	
	/**
	 * 
	 * @return the current ActionState
	 */
	public ActionState getCurrentState()
	{
		return currentState; 
	}

	/**
	 * 
	 * @return the lifeform controlled by this context
	 */
	public LifeForm getLifeForm()
	{
		return lifeform; 
	}

	/**
	 * 
	 * @return the environment for the context
	 */
	public Environment getEnvironment()
	{
		return e; 
	}
	
	
}
