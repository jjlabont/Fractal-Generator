package tests;

/**
 * Class which tests the Multibrot class.
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

public class MultibrotTest {
	
	
	
	/** Test for translating a pixel's row to the associated x-coordinate in the fractal */
	@Test
	public void rowtoX() {
		Multibrot multi = new Multibrot();
		
		assertEquals(-1.0 + 2 * (1.0 + 1.0) / 2048, multi.rowToX(2), 0.01);
	}

	/** Test for translating a pixel's column to the associated y-coordinate in the fractal */
	@Test
	public void columntoY() {
		Multibrot multi = new Multibrot();
		assertEquals((-1.3 + 2 * (1.3 + 1.3) / 2048), multi.columnToY(2), 0.01);
	}
	
	/** Test for calculating the escape time for a coordinate whose distance from the origin exceeds the escape distance after a single loop pass */
	@Test
	public void singleLoop() {
		Multibrot multi = new Multibrot();
		assertEquals(1, multi.calcEscapeTime(0.9921875, 1.05625), 0.01);
	}
	
	/** Test for calculating the escape time for a coordinate whose distance from the origin never exceeds the escape distance */
	@Test
	public void neverEscapes() {
		Multibrot multi = new Multibrot();
		assertEquals(255, multi.calcEscapeTime(0.5859375, 0.24375000000000108), 0.01);	
	}
	
	//checks with escape lim of 3 for coord
	@Test
	public void checkThree() {
		Multibrot multi = new Multibrot();
		multi.setEscapeDistance(3);
		assertEquals(10, multi.calcEscapeTime(0.7025440313111545, -0.5520547945205528), 0.01);
		
	}
	
	//checks escape lim 2, escape time 135
	@Test
	public void checkFour() {
		
		Multibrot multi = new Multibrot();
		multi.setEscapeDistance(2);
		multi.setEscapeTime(135);
		assertEquals(135, multi.calcEscapeTime(0.5859375, 0.24375000000000108), 0.01);
		
	}
	
	/** Test to calculate that the fractal returns a 2-d array with 2048 rows and 2048 columns */
	@Test
	public void dimension()
	{
		Multibrot multi = new Multibrot();
		multi.generateFractal();
		int[][] array = multi.getArray();
		
		int rows = array.length;
		int columns = array[0].length;
		assertEquals(2048, rows);
		assertEquals(2048, columns);
		
	}
	 

}


