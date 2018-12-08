package tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entities.Zombie;

public class ZombieTest {

private Zombie kris;
	
	
	@Before
	public void setUp() throws Exception {
		kris = new Zombie(2,3,10,50,6,6);	
	}

	@Test
	public void damageTest() {
 
		assertEquals("test zombies getDamage function", 10, kris.getDamage());
	}
	
	@Test
	public void HealthTest() {
 
		assertEquals("test zombies getHealth function", 50, kris.getHealth());
	}
	
	@Test
	public void moveSpeedTest() {
 
		assertEquals("test zombies getMoveSpeed function", 6, kris.getMoveSpeed());
	}

	
	@Test
	public void attackSpeedTest() {
 
		assertEquals("test zombies getAttackSpeed function", 6, kris.getAttackSpeed());
	}
	
	@Test
	public void takeDamageTest() {
		kris.takeDamage(30);
		assertEquals("test zombies takeDamage function", 20, kris.getHealth() );
	}
}
