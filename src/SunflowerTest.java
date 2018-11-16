import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SunflowerTest {

	private Sunflower sushi;
	@Before
	public void setUp() throws Exception {
		sushi = new Sunflower(3,3);
	}

	@Test
	public void toStringtest() {
		assertEquals("test the toString function in Sunflower", "SF", sushi.toString());
	}

}
