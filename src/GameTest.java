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
		 assertEquals("test if the zombie number start at 0", 0, game.getAllZombies().size());
	}
	
	@Test
	public void initialPlantsTest() {
		 assertEquals("test if the plant number start at 0", 0, game.getAllPlants().size());
	}
	
	@Test
	public void getSunTest() {
		 assertEquals("test if the sun number start at 0", 0, game.getSun());
	}

	@Test
	public void plantAPlantZeroSun() {
		 game.plantAPlant(3,3,"sunflower");
		 assertEquals("set plant failed due to 0 sun", 0, game.getAllPlants().size());
	}
	
	@Test
	public void plantAPlantTwentySun() {
		 game.setSun(25);
		 game.plantAPlant(3,3,"sunflower");
		 assertEquals("set sunflower successfully ", 1, game.getAllPlants().size());     
	}
	
	@Test
	public void plantAPlantFiftySun() {
		 game.setSun(50);
		 game.plantAPlant(3,3,"peashooter");
		 assertEquals("set sunflower successfully ", 1, game.getAllPlants().size());     
	}

	
	@Test
	public void getCurrTest() {
		 assertEquals("check if getcurr working properly as defalut", null, game.getCurr());     
	}
	
	@Test
	public void getCurrTestAfternewGame() {
		game.newGame();
		 assertEquals("check if getcurr working properly after new game", 50, game.getCurr().getSun());
		 assertEquals("check if getcurr working properly after new game", 0, game.getCurr().getAllZombies().size());
		 assertEquals("check if getcurr working properly after new game", 0, game.getCurr().getAllPlants().size());
		 assertEquals("check if getcurr working properly after new game", 0, game.getCurr().getTickCount());
		 assertEquals("check if getcurr working properly after new game", 10, game.getCurr().getTotalZombies());
		 assertEquals("check if getcurr working properly after new game", 10, game.getCurr().getRemainingZombies());
	}
	
	@Test
	public void undoTestone() {
		 assertFalse("check if undo works one", game.undo());     
	}
	
	@Test
	public void undoTesttwo() {
	    game.newGame();
		game.setPre(game);
		 assertTrue("check if undo works two",  game.undo());     
	}
	
	@Test
	public void redoTestone() {
		 assertFalse("check if redo works two",  game.redo());     
	}
	
	@Test
	public void redoTesttwo() {
	    game.newGame();
		game.setNext(game);
		 assertTrue("check if redo works two",  game.redo());     
	}
	
	@Test
	public void getNextTest() {
		 assertEquals("check if getnext working properly as defalut", null, game.getNext());     
	}
	
	@Test
	public void getPreTest() {
		 assertEquals("check if getpre working properly as defalut", null, game.getPre());     
	}
}
