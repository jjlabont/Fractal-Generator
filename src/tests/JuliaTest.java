package tests;

/**
 * Class which tests the Julia class.
 *
 * @author Josh Labonte
 * @author Yizhou Sun (Joey)
 * @author Ravi Patel
 * @author Jon Zeglen
 */
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import code.Julia;
import code.Mandel;

public class JuliaTest {
	
	/** Test for translating a pixel's row to the associated x-coordinate in the fractal */
	@Test
	public void rowtoX() {
		Julia juli = new Julia();
		assertEquals(-1.7 + 2 * (1.7 + 1.7) / 2048, juli.rowToX(2), 0.01);
	}

	/** Test for translating a pixel's column to the associated y-coordinate in the fractal */
	@Test
	public void columntoY() {
		Julia juli = new Julia();
		assertEquals((-1.0 + 2 * (1.0 + 1.0) / 2048), juli.columnToY(2), 0.01);
	}
	
	/** Test for calculating the escape time for a coordinate whose distance from the origin exceeds the escape distance after a single loop pass */
	@Test
	public void singleLoop() {
		Julia juli = new Julia();
		assertEquals(1, juli.calcEscapeTime(1.6933593749999853, 0.9765625), 0.01);
	}
	
	/** Test for calculating the escape time for a coordinate whose distance from the origin never exceeds the escape distance */
	@Test
	public void neverEcapes() {
		Julia juli = new Julia();
		assertEquals(255, juli.calcEscapeTime(1.0492187499999897, -0.234375), 0.01);
	}
	
	//checks with escape lim of 3 for coord
	@Test
	public void checkThree() {
		Julia juli = new Julia();
		juli.setEscapeDistance(3);
		assertEquals(10, juli.calcEscapeTime(1.4538160469667272, -0.13502935420743645), 0.01);
		
	}
	
	//checks escape lim 2, escape time 135
			@Test
			public void checkFour() {
				
				Julia juli = new Julia();
				juli.setEscapeDistance(2);
				juli.setEscapeTime(135);
				assertEquals(135, juli.calcEscapeTime(1.0492187499999897, -0.234375), 0.01);
				
			}
	 
	/** Test to calculate that the fractal returns a 2-d array with 2048 rows and 2048 columns */
	@Test
	public void dimension()
	{
		Julia juli = new Julia();
		juli.generateFractal();
		int[][] array = juli.getArray();
		
		int rows = array.length;
		int columns = array[0].length;
		assertEquals(2048, rows);
		assertEquals(2048, columns);
		
	}

}
