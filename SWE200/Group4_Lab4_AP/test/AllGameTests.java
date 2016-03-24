/**
 * @author Adam Pine
 * Runs all of the tests in this project
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import environment.TestCell;
import environment.TestEnvironment;
import gameplay.TestSimpleTimer;
import lifeform.TestAlien;
import lifeform.TestHuman;
import lifeform.TestLifeForm;
import recovery.TestRecoveryFractional;
import recovery.TestRecoveryLinear;
import recovery.TestRecoveryNone;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestCell.class,
	TestEnvironment.class,
	TestLifeForm.class,
	TestHuman.class,
	TestAlien.class,
	TestRecoveryFractional.class,
	TestRecoveryLinear.class,
	TestRecoveryNone.class,
	TestSimpleTimer.class
})
public class AllGameTests 
{
}
