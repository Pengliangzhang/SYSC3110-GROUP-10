import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EntityTest {

	private Entity entity;
	@Before
	public void setUp() throws Exception {
		entity = new Entity(3,3);
	}

	@Test
	public void getXTest() {
		assertEquals("test the function getX in Entity class", 3, entity.getX());
	}
	
	@Test
	public void getYTest() {
		assertEquals("test the function getY in Entity class", 3, entity.getY());
	}
	
	@Test
	public void setXTest() {
		entity.setX(10);
		assertEquals("test the function setX in Entity class", 10, entity.getX());
	}
	
	@Test
	public void setYTest() {
		entity.setY(10);
		assertEquals("test the function setY in Entity class", 10, entity.getY());
	}



}
