/**
 * 
 */
package gameplay;

import lifeform.Alien;
import lifeform.Human;
import recovery.RecoveryBehavior;
import recovery.RecoveryFractional;
import recovery.RecoveryLinear;
import recovery.RecoveryNone;
import state.AIContext;
import state.AIContextAdapter;
import state.AIContextInterface;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.PowerBooster;
import weapon.Scope;
import weapon.Stabilizer;
import weapon.Weapon;
import command.Invoker;
import command.InvokerBuilder;
import environment.CellInfo;
import environment.Environment;
import exceptions.AttachmentException;
import exceptions.RecoveryRateException;

/**
 * @author Dr. Alice Armstrong
 * Edited by Adam Pine
 *
 */
public class Simulator implements TimerObserver{
	
	private Environment e; 
	private AIContextInterface[] aliens; 
	private AIContextInterface[] humans; 
	private int numHumans; 
	private int numAliens; 
	private int numWeapons; 
	private SimpleTimer timer; 
	private InvokerBuilder builder;
	private Invoker inv;
	
	public Simulator(Environment e, SimpleTimer timer, int numHumans, int numAliens)
	{
		this.e = e; 
		this.timer = timer; 
		this.numHumans = numHumans; 
		this.numAliens = numAliens; 
		numWeapons = numHumans+numAliens; 
		
		aliens = new AIContext[numAliens]; 
		humans = new AIContextAdapter[numHumans]; 
		
		builder = new InvokerBuilder(); 
		inv = builder.loadCommands(); 
		
		createWeapons();
		createHumans(); 
		createAliens(); 
		
	}
	
	
	/**
	 * creates and places randomly generated weapons
	 * 
	 */
	private void createWeapons()
	{
		//add Weapons to the Environment
		CellInfo info; 
		Weapon temp; 
		int randW; 
		int randA, randB;
		
		try {
			//add Weapons
			for (int w = 0; w < numWeapons; w++)
			{
				//pick a random location
				do
				{
					info = e.getRandomCell(); 
				}
				//check to make sure we could put another weapon there
				while (e.getWeapons(info.getRow(), info.getCol())[0] != null && e.getWeapons(info.getRow(), info.getCol())[1] != null);
				
						
				//pick a random kind of weapon
				randW = (int)(Math.random()*3); 
				if (randW == 0)
				{
					temp = new Pistol(); 
				}
				else if(randW == 1)
				{
					temp = new PlasmaCannon(); 
				}
				else
				{
					temp = new ChainGun(); 
				}
				
			
				//try to add a first attachment
				randA = (int)(Math.random()*4); 
				if (randA ==0)
				{
					//add a scope as the first attachment
					temp = new Scope(temp); 
				}
				else if (randA == 1)
				{
					//add a stabilizer as the first attachment
					temp = new Stabilizer(temp); 
				}
				else if (randA == 2)
				{
					//add a power booster as the first attachment
					temp = new PowerBooster(temp); 
				}
				else
				{
					//no attachments at all 
				}
				
				//check for a second attachment
				if (randA != 3)
				{
					//try to add a first attachment
					randB = (int)(Math.random()*4); 
					if (randB ==0)
					{
						//add a scope as the second attachment
						temp = new Scope(temp);
					}
					else if (randB == 1)
					{
						//add a stabilizer as the second attachment
						temp = new Stabilizer(temp); 
					}
					else if (randB == 2)
					{
						//add a power booster as the second attachment
						temp = new PowerBooster(temp);
					}
					else
					{
						//no second attachment
					}
				}
				
				//make temp an observer
				timer.addTimeObserver(temp);
				e.addWeapon(temp, info.getRow(), info.getCol());
				e.updateCell(info.getRow(), info.getCol());
				
			}
		}
		catch (AttachmentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * creates and places randomly generated Humans
	 * lifepoint range: 15 - 100
	 * armor range: 5 - 50
	 */
	private void createHumans()
	{
		String name; 
		int lifePoints; 
		int minLP = 15; //minimum possible lifepoints
		int maxLP = 100; //maximum possible lifepoints
		int armor;
		int minArmor = 5; //minimum possible armor
		int maxArmor = 50; //maximum possible armor
		Human tempHuman;
		int direction; 
		
		for (int i = 0; i< numHumans; i++)
		{
			//generate a name
			name = "Human_"+i; 
			//generate a random lifepoints
			lifePoints = (int)(Math.random()*maxLP); 
			if (lifePoints < minLP)
			{
				lifePoints = minLP; 
			}
			
			//generate random armor
			armor = (int)(Math.random()*maxArmor); 
			if (armor < minArmor)
			{
				armor = minArmor; 
			}
			
			tempHuman = new Human(name, lifePoints, armor); 
			
			
			//pick a random direction to face 
			direction = (int)(Math.random()*4); 
			if (direction == 0)
			{
				//North, do nothing
			}
			else if (direction == 1)
			{
				tempHuman.setDirection("South"); 
			}
			else if (direction == 2)
			{
				tempHuman.setDirection("East"); 
			}
			else
			{
				tempHuman.setDirection("West"); 
			}
			
			boolean placed = false;
			CellInfo info; 
			//add humans to random locations
			do
			{
				info = e.getRandomCell();
				placed = e.addLifeForm(tempHuman, info.getRow(), info.getCol());
			}while(!placed);
			
			humans[i] = new AIContextAdapter(tempHuman, e, inv); 
			timer.addTimeObserver(humans[i]);
			e.updateCell(info.getRow(), info.getCol());
		}
	}
	
	/**
	 * creates and places randomly generated Aliens
	 * lifepoint range: 10 - 125
	 * RecoveryBahviors: chosen at random
	 */
	private void createAliens()
	{
		String name; 
		int lifePoints; 
		int minLP = 10; //minimum possible lifepoints
		int maxLP = 125; //maximum possible lifepoints
		
		Alien tempAlien;
		RecoveryBehavior recovery; 
		int direction; 
		int r;
		int recoveryRate; 
		
		for (int i = 0; i< numAliens; i++)
		{
			//generate a name
			name = "Alien_"+i; 
			//generate a random lifepoints
			lifePoints = (int)(Math.random()*maxLP); 
			if (lifePoints < minLP)
			{
				lifePoints = minLP; 
			}
			
			//generate a random RecoveryBehavior
			r = (int)(Math.random()*3); 
			if (r == 0)
			{
				recovery = new RecoveryNone(); 
			}
			else if(r == 1)
			{
				//recover between 5 - 10 points each round
				int amount = (int)(Math.random()*5) +5; 
				recovery = new RecoveryLinear(amount); 
			}
			else
			{
				//recover between 20% - 70% of current life each time
				recovery = new RecoveryFractional(Math.random()*0.5+0.2); 
			}
			
			try {
				//recovery 1 - 4 rounds
				recoveryRate = (int)(Math.random()*4)+1; 
				
				tempAlien = new Alien(name, lifePoints, recovery, recoveryRate);
				timer.addTimeObserver(tempAlien); 
				
				//pick a random direction to face 
				direction = (int)(Math.random()*4); 
				if (direction == 0)
				{
					//North, do nothing
				}
				else if (direction == 1)
				{
					tempAlien.setDirection("South"); 
				}
				else if (direction == 2)
				{
					tempAlien.setDirection("East"); 
				}
				else
				{
					tempAlien.setDirection("West"); 
				}
				
				boolean placed = false;
				CellInfo info; 
				//add humans to random locations
				do
				{
					info = e.getRandomCell();
					placed = e.addLifeForm(tempAlien, info.getRow(), info.getCol());
				}while(!placed);
				
				aliens[i] = new AIContext(tempAlien, e); 
				e.updateCell(info.getRow(), info.getCol());
				timer.addTimeObserver(aliens[i]);
			} catch (RecoveryRateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
		}
	}


	/* (non-Javadoc)
	 * @see gameplay.TimerObserver#updateTime(int)
	 */
	@Override
	public void updateTime(int time) {	
		//Not needed, Human execute method does nothing!
//		for (int i = 0; i < numHumans; i++)
//		{
//			humans[i].execute();
//		}
		//all aliens second
		for (int i = 0; i < numAliens; i++)
		{
			aliens[i].execute();
		}
		

		
	}
	

}
