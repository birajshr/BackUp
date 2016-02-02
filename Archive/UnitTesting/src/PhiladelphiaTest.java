import static org.junit.Assert.*;

import org.junit.Test;


public class PhiladelphiaTest {

	@Test
	public void testIsItSunny() {
		assertTrue(Philadelphia.isItSunny());
	}
	
	@Test
	public void testIsItNotSunny() {
		assertFalse(Philadelphia.isItSunny());
	}
	
	@Test
	public void sameObject() {
		assertSame(Philadelphia.isItSunny(),Philadelphia.isItSunny());
	}
	
	@Test
	public void throwException() {
		//throw new IllegalArgumentException("Error");
		int a = 2/0;
	}

}
