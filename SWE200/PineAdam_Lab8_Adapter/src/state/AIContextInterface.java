package state;

import gameplay.TimerObserver;
/**
 * @author Adam Pine
 */
public interface AIContextInterface extends TimerObserver {
	/**
	 * Each interface needs to have an execute() method.
	 */
	public void execute();
}
