package code;

import edu.buffalo.fractal.WorkerResult;

/**
 * Class which implements the Multibrot Set.
 *
 * @author Josh Labonte
 * @author Yizhou Sun (Joey)
 * @author Ravi Patel
 * @author Jon Zeglen
 */

public class Multibrot extends Fractal{
	
	/** Lower limit to find X coordinate. */
	private final static double X_LOWER_LIM = -1.0;
	
	/** Upper limit to find X coordinate. */
	private final static double X_UPPER_LIM = 1.0;
	
	/** Lower limit to find Y coordinate. */
	private final static double Y_LOWER_LIM = -1.3;
	
	/** Upper limit to find Y coordinate. */
	private final static double Y_UPPER_LIM = 1.3;
	

	/** This mandel method assigns a 2-D array of type int to the value of passes in the fractal superclass */
	public Multibrot() {
		super(X_LOWER_LIM, X_UPPER_LIM, Y_LOWER_LIM, Y_UPPER_LIM);
	}
	

	  /** With this formulaX method, we can calculate the X coordinate value.
	   *  @param xCalc      current point's x-coordinate
	   *  @param yCalc      current point's y-coordinate
	   *  @param currentX   current point's x-coordinate
	   *  @return xPrime
	   * */
	//formula override
	public double formulaX(double xCalc, double yCalc, double currentX)
	{
		double xPrime = (xCalc * xCalc * xCalc) - (3 * xCalc * (yCalc * yCalc)) + currentX;
		
		return xPrime;
	}
	
	 /** With this formulaY method, we can calculate the Y coordinate value.
	   *  @param xCalc      current point's x-coordinate
	   *  @param yCalc      current point's y-coordinate
	   *  @param currentY   current point's y-coordinate
	   *  @return yPrime
	   * */
	
	//formula override
	public double formulaY(double xCalc, double yCalc, double currentY)
	{
		double yPrime = (3 * (xCalc * xCalc) * yCalc) - (yCalc * yCalc * yCalc) + currentY;
		
		return yPrime;
	}


	
	
}
