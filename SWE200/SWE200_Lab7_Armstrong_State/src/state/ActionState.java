/**
 * 
 */
package state;

import lifeform.LifeForm;
import environment.Environment;

/**
 * @author Dr. Alice Armstrong
 *
 */
public abstract class ActionState {
	protected Environment e; 
	protected LifeForm lifeform;
	protected AIContext context; 
	
	public ActionState(AIContext context)
	{
		this.context = context; 
		lifeform = context.getLifeForm(); 
		e = context.getEnvironment(); 
	}
	abstract public void executeAction(); 
}
