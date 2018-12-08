package tests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AdvancedPeashooterTest.class, AdvancedZombieTest.class, DamagePlantTest.class, EntityTest.class,
		GameTest.class, PlantTest.class, SunPlantTest.class, ZombieTest.class })
public class AllTests {

}
