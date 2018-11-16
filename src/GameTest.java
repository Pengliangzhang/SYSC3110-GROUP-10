import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

	private Game game;
	
	@Before
	public void setUp() throws Exception {
		game = new Game();
	}

	@Test
	public void initialZombieTest() {
		 assertEquals("test if the zombie number start at 0", 0, game.getZombieSize());
	}
	
	@Test
	public void initialPlantsTest() {
		 assertEquals("test if the plant number start at 0", 0, game.getPlantSize());
	}
	
	@Test
	public void getSunTest() {
		 assertEquals("test if the sun number start at 0", 0, game.getSun());
	}

	@Test
	public void plantAPlantZeroSun() {
		 game.plantAPlant(3,3,"sunflower");
		 assertEquals("set plant failed due to 0 sun", 0, game.getPlantSize());
	}
	
	@Test
	public void plantAPlantTwentySun() {
		 game.setSun(25);
		 game.plantAPlant(3,3,"sunflower");
		 assertEquals("set sunflower successfully ", 1, game.getPlantSize());     
	}
	
	@Test
	public void plantAPlantFiftySun() {
		 game.setSun(50);
		 game.plantAPlant(3,3,"peashooter");
		 assertEquals("set sunflower successfully ", 1, game.getPlantSize());     
	}

}
