package tests;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import mvc.Game;

public class GameTest {

	private Game game;
	
	@Before
	public void setUp() throws Exception {
		game = new Game();
	}

	@Test
	public void levelTest() {
		assertEquals("test if getlevel function works",1, game.getlevel());
	}
	
	@Test
	public void setlevelTest() {
		game.setLevel(2);
		assertEquals("test if setlevel function works", 2,game.getlevel());
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
	public void loadAndSave() {
		game.newGame();
		game.saveGame(game);
		Game agame = game.loadGame();
		assertEquals("data test", game.getSun(),agame.getSun());
		assertEquals("data test", game.getlevel(),agame.getlevel());
		assertEquals("data test", game.getAllZombies(),agame.getAllZombies());
		assertEquals("data test", game.getTickCount(),agame.getTickCount());
		assertEquals("data test", game.getAllPlants(), agame.getAllPlants());
	}
	
	
	@Test
	public void readLevelFileTestLevel2() {
		game.readLevelFile(2);
		assertEquals("test is the function reading the level", 2, game.getlevel());
	}
	
	@Test
	public void readSAXTestLevel3() throws Exception {
		File file = new File("level.xml");
		game.readSAX(file, 3);
		assertEquals("test is the function readSAX works", 3 , game.getlevel());
	}

//	@Test
//	public void undoTestone() {
//	    game.newGame();
//		assertFalse("check if undo works one", game.undo());     
//	}
//	
//	@Test
//	public void undoTesttwo() {
//	    game.newGame();
//		game.takeTurn();
//		 assertTrue("check if undo works two",  game.undo());     
//	}
//	
//	@Test
//	public void redoTestone() {
//		game.newGame();
//		assertFalse("check if redo works two",  game.redo());     
//	}
//	
//	@Test
//	public void redoTesttwo() {
//	    game.newGame();
//	    game.takeTurn();
//	    game.undo();
//		 assertTrue("check if redo works two",  game.redo());     
//	}
	
	
	
}
