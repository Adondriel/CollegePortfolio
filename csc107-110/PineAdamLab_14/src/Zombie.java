

/**
 * @author Adam
 * The Zombie class! 
 */
public class Zombie extends LifeForm
{
	private int origHP;

	/**
	 * Construct us a zombie! *evil scientist laugh*
	 * @param hp
	 */
	public Zombie(int hp) 
	{
		origHP = hp;
		health = hp;		
	}

	/**
	 * Regain 10% of the hitpoints that are currently missing.
	 */
	public void recover()
	{
		health = (int) (health + ((origHP-health)*0.10));
	}

}
