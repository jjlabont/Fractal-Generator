package code;

import edu.buffalo.fractal.WorkerResult;

/**
 * Class which implements the Burning Ships Set.
 *
 * @author Josh Labonte
 * @author Yizhou Sun (Joey)
 * @author Ravi  Patel
 * @author Jon Zeglen 
 */

public class BurningShips extends Fractal{
	
	/** Lower limit to find X coordinate. */
	private final static double X_LOWER_LIM = -1.8;
	
	/** Upper limit to find X coordinate. */
	private final static double X_UPPER_LIM = -1.7;
	
	/** Lower limit to find Y coordinate. */
	private final static double Y_LOWER_LIM = -0.08;
	
	/** Upper limit to find Y coordinate. */
	private final static double Y_UPPER_LIM = 0.025;
	
	/** 2-D array of type int. */
	private int[][] _shipEscape;

//	/** This mandel method assigns a 2-D array of type int to something in the fractal superclass */
	public BurningShips() {
		super(X_LOWER_LIM, X_UPPER_LIM, Y_LOWER_LIM, Y_UPPER_LIM);
		// _burningShipsEscape = super.something; (DO WE NEED THIS?)
	}
	
	 /** With this formulaX method, we can calculate the X coordinate value.
	   *  @param xCalc      current point's x-coordinate
	   *  @param yCalc      current point's y-coordinate
	   *  @param currentX   current point's x-coordinate
	   *  @return xPrime
	   * */
	
	//formula implementation
	public double formulaX(double xCalc, double yCalc, double currentX)
	{
		double xPrime = (xCalc * xCalc) - (yCalc * yCalc) + currentX;
		
		return xPrime;
	}
	
	
	 /** With this formulaY method, we can calculate the Y coordinate value.
	   *  @param xCalc      current point's x-coordinate
	   *  @param yCalc      current point's y-coordinate
	   *  @param currentY   current point's y-coordinate
	   *  @return yPrime
	   * */
	
	//formula implementation
	public double formulaY(double xCalc, double yCalc, double currentY)
	{
		double yPrime = Math.abs(2 * xCalc * yCalc) + currentY;
		
		return yPrime;
	}

	
	
}
