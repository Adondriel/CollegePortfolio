package state;

import command.Invoker;
import environment.Environment;
import lifeform.Human;
import lifeform.LifeForm;
/**
 * @author Adam Pine
 */
public class AIContextAdapter implements AIContextInterface {
	//The invoker that will be used.
	Invoker invoker;
	//The environment that isn't used...
	Environment e;
	//The lifeform that will be used.
	LifeForm lf;
	
	/**
	 * Contructor, pass in the info that we need.
	 * @param tempHuman
	 * @param e
	 * @param inv
	 */
	public AIContextAdapter(Human tempHuman, Environment e, Invoker inv) {
		lf = tempHuman;
		this.e = e;
		invoker = inv;
	}

	@Override
	public void updateTime(int time) {
		invoker.getMostRecentCommand().execute(lf.getRow(), lf.getCol());
	}

	@Override
	public void execute() {
		//do nothing.
	}

}
