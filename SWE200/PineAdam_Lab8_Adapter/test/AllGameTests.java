import org.junit.runner.RunWith; 
import org.junit.runners.Suite; 

import command.TestCommand;
import recovery.TestRecovery;
import state.TestStatePattern;
import weapon.TestPowerBooster;
import weapon.TestScope;
import weapon.TestStabilizer;
import weapon.TestWeapons;
import environment.TestCellInfo;
import environment.TestEnvironment;
import environment.TestCell;
import gameplay.TestSimpleTimer;
import lifeform.TestAlien;
import lifeform.TestLifeForm;
import lifeform.TestHuman; 

/** 
 * Runs all of the tests in this project 
 * @author Dr. Dudley Girard -- first author
 * @author Dr. Alice Armstrong -- revisions
 */ 
@RunWith(Suite.class) 
@Suite.SuiteClasses( 
{  
 TestLifeForm.class,  
 TestCell.class,
 TestEnvironment.class,
 TestHuman.class,
 TestAlien.class, 
 TestRecovery.class,
 TestWeapons.class, 
 TestPowerBooster.class,
 TestScope.class, 
 TestStabilizer.class, 
 TestSimpleTimer.class, 
 TestCommand.class,
 TestCellInfo.class,
 TestStatePattern.class
 
}) 
 
public class AllGameTests 
{ 
} 

