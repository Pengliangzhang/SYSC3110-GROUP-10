package tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entities.DamagePlant;

public class DamagePlantTest {

	private DamagePlant shooter;
	@Before
	public void setUp() throws Exception {
		shooter = new DamagePlant(3,3,50,10,5,5,20);
	}

	@Test
	public void getRangeTest() {
		assertEquals("test the getRange function in DamagePlant",10,shooter.getRange());
	}
	
	@Test
	public void getDamageTest() {
		assertEquals("test the getDamage function in DamagePlant",5,shooter.getDamage());
	}
	
	@Test
	public void getDamageTickTest() {
		assertEquals("test the getDamageTick function in DamagePlant",5,shooter.getDamageTick());
	}

}
