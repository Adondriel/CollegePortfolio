/**
 * 
 */
package command;

import lifeform.LifeForm;
import environment.Environment;

/**
 * @author Dr. Alice Armstrong
 *
 */
public class TurnNorthCmd implements Command {

	private Environment e; 
	
	public TurnNorthCmd()
	{
		//This is sloppy, but there is no way a call to a command constructor should be the first call to getEnvironment()
		e = Environment.getEnvironment(1, 1);  
	}
	
	/**
	 * if there is a lifeform in cell (row, col), turns it to face North
	 * @see command.Command#execute(int, int)
	 */
	@Override
	public void execute(int row, int col) {
		LifeForm life = e.getLifeForm(row, col); 
		
		if (life != null)
		{
			life.setDirection("North"); 
			
			//update the environment observers
			e.updateCell(row, col);
		}

	}

}
