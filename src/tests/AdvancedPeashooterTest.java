package tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entities.AdvancedPeashooter;

public class AdvancedPeashooterTest {

	AdvancedPeashooter a;
	@Before
	public void setUp() throws Exception {
		 a = new AdvancedPeashooter(3,5);
	}

	@Test
	public void xValueTest() {
		assertEquals("Test the x value", 3, a.getX());
	}
	

	@Test
	public void yValueTest() {
		assertEquals("Test the y value", 5, a.getY());
	}
	
	@Test
	public void healthTest() {
		assertEquals("Test the health", 150, a.getHealth());
	}
	
	@Test
	public void dmgTest() {
		assertEquals("Test the damage", 50, a.getDamage());
	}
	

	@Test
	public void rangeTest() {
		assertEquals("Test the range", 10, a.getRange());
	}


	@Test
	public void damageTickTest() {
		assertEquals("Test the damage tick", 2, a.getDamageTick());
	}
	

	@Test
	public void sunCostTest() {
		assertEquals("Test the sun cost", 75, a.getSunCost());
	}
}
