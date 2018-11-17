import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DamagePlantTest.class, EntityTest.class, GameTest.class, PlantTest.class,
		SunPlantTest.class, ZombieTest.class })
public class AllTests {

}
