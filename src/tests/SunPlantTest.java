package tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entities.SunPlant;

public class SunPlantTest {

	private SunPlant plant;
	@Before
	public void setUp() throws Exception {
		plant = new SunPlant(3,3,50,20,10,20);
	}

	@Test
	public void getSunTest() {
		assertEquals("test the getSun function in SunPlant", 20,plant.getSun());
	}


	@Test
	public void getSunTickTest() {
		assertEquals("test the getSunTick function in SunPlant", 1,plant.getCurrentTick());
	}


	@Test
	public void getCurrentTickTest() {
		 
		assertEquals("test the getCurrentTick function in SunPlant", 1,plant.getCurrentTick());
	}

	@Test
	public void setTickTest() {
		 plant.setCurrentTick(5);
		assertEquals("test the getCurrentTick function in SunPlant", 5,plant.getCurrentTick());
	}
	
	@Test
	public void generateSunTest() {
		 plant.setCurrentTick(10);
		assertEquals("test the generateSun function in SunPlant", 20,plant.getSun());
	}

}
