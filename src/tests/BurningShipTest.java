package tests;

/**
 * Class which tests the BurningShip class.
 *
 * @author Josh Labonte
 * @author Yizhou Sun (Joey)
 * @author Ravi Patel
 * @author Jon Zeglen
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import code.BurningShips;
import code.Mandel;


public class BurningShipTest {

	
	
	/** Test for translating a pixel's row to the associated x-coordinate in the fractal */
	@Test
	public void rowtoX() {
		BurningShips ship = new BurningShips();
		assertEquals(-1.8 + 2 * ((-1.7 - -1.8) / 2048), ship.rowToX(2), 0.01);
	}

	/** Test for translating a pixel's column to the associated y-coordinate in the fractal */
	@Test
	public void columntoY() {
		BurningShips ship = new BurningShips();
		assertEquals(-0.08 + 2 * ((0.08 + 0.025) / 2048), ship.columnToY(2), 0.01);
	}

	/** Test for calculating the escape time for a coordinate whose distance from the origin never exceeds the escape distance */
	@Test
	public void neverEscapes() {
		BurningShips ship = new BurningShips();
		assertEquals(255, ship.calcEscapeTime(-1.7443359374999874, -0.017451171875000338), 0.01);
	}
	
	/** Test to calculate the fractal that returns a 2-d array with 2048 rows and 2048 columns */
	@Test
	public void dimension() {
		BurningShips ship = new BurningShips();
		ship.generateFractal();
		int[][] array = ship.getArray();
		
		int rows = array.length;
		int columns = array[0].length;
		assertEquals(2048, rows);
		assertEquals(2048, columns);
		
	}

	
	/** Test to calculate that none of the pixels in the Burning Ship set have an escape time of 0 or 1 */
	@Test
	public void checkZeroOne() {
		BurningShips ship = new BurningShips();
		ship.generateFractal();
		int[][] checks = ship.getArray();
		for (int x = 0; x < 2048; x++) 
		{
			for (int y = 0; y < 2048; y++) 
			{
				int val = checks[x][y];
				if (val == 1 || val == 0)
				{
					fail("Value was " + val + " at " + x + "," + y);
				}

			}
		}
	}
	
	//checks with escape lim of 3 for coord
	@Test
	public void checkThree() {
		BurningShips ship = new BurningShips();
		ship.setEscapeDistance(3);
		assertEquals(10, ship.calcEscapeTime(-1.6999999999999802, 0.0030136986301371603), 0.01);
		
	}
	
	//checks escape lim 2, escape time 135
			@Test
			public void checkFour() {
				
				BurningShips ship = new BurningShips();
				ship.setEscapeDistance(2);
				ship.setEscapeTime(135);
				assertEquals(135, ship.calcEscapeTime(-1.7443359374999874, -0.017451171875000338), 0.01);
				
			}
	
	
	
	
}