package weapon;
/**
 * @author Adam Pine
 */
import gameplay.TimeObserver;

public interface Weapon extends TimeObserver {
	public abstract int calcDmg(int distance);
}
