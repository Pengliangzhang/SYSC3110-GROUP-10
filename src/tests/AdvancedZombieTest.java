package tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entities.AdvancedZombie;

public class AdvancedZombieTest {
	AdvancedZombie a;
	@Before
	public void setUp() throws Exception {
		  a = new AdvancedZombie(5);
	}

	@Test
	public void rowTest() {
		assertEquals("test row", 5, a.getX());
	}
	
	@Test
	public void healthTest() {
		assertEquals("test health", 150, a.getHealth());
	}
	
	@Test
	public void dmgTest() {
		assertEquals("test damage", 50, a.getDamage());
	}

	@Test
	public void moveSpeedTest() {
		assertEquals("test move speed", 1, a.getMoveSpeed());
	}
	
	@Test
	public void attackSpeedTest() {
		assertEquals("test attack speed", 2, a.getAttackSpeed());
	}
}
