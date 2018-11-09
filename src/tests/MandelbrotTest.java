package tests;

/**
 * Class which tests the MandelBrot class.
 *
 * @author Josh Labonte
 * @author Yizhou Sun (Joey)
 * @author Ravi Patel
 * @author Jon Zeglen
 */
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import code.Mandel;
import code.Multibrot;

public class MandelbrotTest {
	
	
	/** Test for translating a pixel's row to the associated x-coordinate in the fractal */
	@Test
	public void rowtoX() {
		Mandel mandel = new Mandel();
		assertEquals(mandel.rowToX(2), -2.15 + 2 * (0.6 + 2.15) / 2048, 0.01);
	}

	/** Test for translating a pixel's column to the associated y-coordinate in the fractal */
	@Test
	public void columntoY() {
		Mandel mandel = new Mandel();
		assertEquals(mandel.columnToY(2), (-1.3 + 2 * (1.3 + 1.3) / 2048), 0.01);
	}

	/** Test for calculating the escape time for a coordinate whose distance from the origin never exceeds the escape distance */
	@Test
	public void neverEscapes() {
		Mandel mandel = new Mandel();
		assertEquals(255.0, mandel.calcEscapeTime(0.3207031250000001, -0.07109374999999386), 0.01);
		
	}
	
	/** Test for calculating the escape time for a coordinate whose distance from the origin exceeds the escape distance after a single loop pass */
	@Test
	public void singleLoop() {
		Mandel mandel = new Mandel();
		assertEquals(1.0, mandel.calcEscapeTime(0.5946289062500001, 1.2949218750000122), 0.01);
		
	}
	
	//checks with escape lim of 3 for coord
	@Test
	public void checkThree() {
		Mandel mandel = new Mandel();
		mandel.setEscapeDistance(3);
		assertEquals(10, mandel.calcEscapeTime(0.46007827788650374, -0.3383561643835661), 0.01);
		
	}
	
	//checks escape lim 2, escape time 135
		@Test
		public void checkFour() {
			
			Mandel mandel = new Mandel();
			mandel.setEscapeDistance(2);
			mandel.setEscapeTime(135);
			assertEquals(135, mandel.calcEscapeTime(0.3207031250000001, -0.07109374999999386), 0.01);
			
		}

	/** Test to calculate that the fractal returns a 2-d array with 2048 rows and 2048 columns */
	@Test
	public void dimension()
	{
		Mandel mandel = new Mandel();
		mandel.generateFractal();
		int[][] array = mandel.getArray();
		
		int rows = array.length;
		int columns = array[0].length;
		assertEquals(2048, rows);
		assertEquals(2048, columns);
		
	}
	
}
