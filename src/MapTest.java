import junit.framework.TestCase;

import org.newdawn.slick.Color;

/**
 * A test class
 */
public class MapTest extends TestCase {
	
	/**
	 * Sets up the test fixture. Called before every test case method.
	 */
	@Override
	protected void setUp() {
	}
	
	/**
	 * Tears down the test fixture. Called after every test case method.
	 */
	@Override
	protected void tearDown() {
	}
	
    public void testCompareColor() {
		Color c1 = null, c2 = null;
		assertFalse(Map.compareColor(c1,c2));
		c1 = new Color(1,2,3);
		assertFalse(Map.compareColor(c1,c2));
		c2 = new Color(1,2,3);
		assertTrue(Map.compareColor(c1,c2));
		c2 = new Color(3,2,1);
		assertFalse(Map.compareColor(c1,c2));
    }
    
}
