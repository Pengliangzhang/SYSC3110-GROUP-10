import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlantTest {

	private Plant masaka;
	@Before
	public void setUp() throws Exception {
		masaka = new Plant(3,3,50,20);
	}

	@Test
	public void healthTest() {
		assertEquals("test function getHealth in plant", 50, masaka.getHealth());
	}
	
	@Test
	public void takeDamageTest() {
		masaka.takeDamage(30);
		assertEquals("test function takeDamage in plant", 20, masaka.getHealth());
	}

	@Test
	public void sunCostTest() {
		assertEquals("test function getSunCost in plant", 20, masaka.getSunCost());
	}
}
