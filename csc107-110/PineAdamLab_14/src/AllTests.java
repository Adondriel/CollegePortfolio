import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Adam
 * Our test suite class that lets us test all of our classes at once.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({TestZombie.class, TestHuman.class})

public class AllTests
{	
}
