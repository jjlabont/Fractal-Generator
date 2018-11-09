package code;

import java.awt.Point;

import javax.swing.SwingWorker;

import edu.buffalo.fractal.WorkerResult;

/**
 * A superclass that controls all the sets.
 *
 * @author Josh Labonte
 * @author Yizhou Sun (Joey)
 * @author Ravi Patel
 * @author Jon Zeglen
 * 
 */

abstract public class Fractal extends SwingWorker<WorkerResult, Void> {
	
	private double _xLowLim;
	private double _xUpLim;
	private double _yLowLim;
	private double _yUpLim;
	private double _defaultXLow;
	private double _defaultXUp;
	private double _defaultYLow;
	private double _defaultYUp;
	private int[][] _escapeArray;
	private int _start;
	private int _finish;
	private int  _xLength = 2048;
	private int _yLength = 2048;
	
	//defaults  escape limit to 2
	private int _escapeLim = 2;
	//defaults escape time to 255
	private int _escapeTime = 255;
	//defaults threads to 4
	public int _threads = 4;
	
	
	//Enter the bounds for x and y coordinates
	public Fractal(double xLowerLim, double xUpperLim, double yLowerLim, double yUpperLim)
	{
		//sets instance variables up
		_xLowLim = xLowerLim;
		_xUpLim = xUpperLim;
		_yLowLim = yLowerLim;
		_yUpLim = yUpperLim;
		
		//remembers defaults
		_defaultXLow = xLowerLim;
		_defaultXUp = xUpperLim;
		_defaultYLow = yLowerLim;
		_defaultYUp= yUpperLim;
		
		
		//creates array of escape times and fills it
		
		//generateFractal();
		
		
	}
	// returns an array of the limits
	public double[] getLims() {
		double[] lims = new double[4];
		lims[0] = _xLowLim;
		lims[1] = _xUpLim;
		lims[2] = _yLowLim;
		lims[3] = _yUpLim;
		return lims;
		
	}
	
	//sets the limits for fractal
	public void setLims(double xLowerLim, double xUpperLim, double yLowerLim, double yUpperLim)
	{
		//sets instance variables up
		_xLowLim = xLowerLim;
		_xUpLim = xUpperLim;
		_yLowLim = yLowerLim;
		_yUpLim = yUpperLim;
	}
	
	//generate fractal piece that this instance is supposed to generate
	protected WorkerResult doInBackground() {
		double tempL = rowToX(_start);
		double tempU = rowToX(_finish);
		_xUpLim = tempU;
		_xLowLim = tempL;

		_xLength = _finish - _start + 1;
		System.out.println(_xLength + " " + _start + " " + _finish);
		System.out.println("xLow " + _xLowLim + ", xUp " + _xUpLim);
		generateFractal();
		WorkerResult r = new WorkerResult(_start, _escapeArray);
		return r;
	}
	
	//sets the start and finish for fractal generation
	public void setSF(int startRow, int finishRow) {
		_start = startRow;
		_finish = finishRow;
		
		
	}
	
	//creates fractal
	public void generateFractal()
	{
		_escapeArray = new int[_xLength][_yLength];
		for (int x = 0; x < _xLength; x++)
		{
			for (int y = 0; y < _yLength; y++)
			{
				//calculates current xCoord
				double currentX = rowToX(x);
				//calculates current yCoord
				double currentY = columnToY(y);
				//sets escapetime to passes needed to escape
				int escapetime = calcEscapeTime(currentX, currentY);
				//sets escape to number of passes
				_escapeArray[x][y] = escapetime;
			}
			
		}
	}
	
	
	
	//returns an array of the escape times
	public int[][] getArray()
	{
		return _escapeArray;
	}
	
	//returns the current escape lim
	public int getEscapeDistance() {
		
		return _escapeLim;
		
	}
	
	//sets escape distance of greater than or equal to 1
	public void setEscapeDistance(int newDistance)
	{
		if (newDistance >= 1) {
			_escapeLim = newDistance;
			
		}
		generateFractal();
	}
	
	// sets escape time
	public void setEscapeTime (int newEscapeTime) {
		
		if (newEscapeTime > 0 && newEscapeTime <= 255) {
		_escapeTime = newEscapeTime;
		}
		generateFractal();
		
	}
	
	//gets escape time
	public int getEscapeTime() {
		
		return _escapeTime;
	}
	
	//gets threads
	public int getThreads() {
		return _threads;
	}
	
	//sets threads
	public void setThreads(int numThreads) {
		_threads = numThreads;
	}
	
	//converts row to x coordinate
	public double rowToX(int row)
	{
		double position = _xLowLim + (row * ((_xUpLim - _xLowLim) / _xLength));
		return position;
	}
	//converts column to y coordinate
	public double columnToY(int column)
	{
		double position = _yLowLim + (column * ((_yUpLim - _yLowLim) / _yLength));
		return position;
	}
	
	//calculates passes need to escape
	public int calcEscapeTime(double xCoord, double yCoord)
	{
		//sets current x and y values
		double currentX = xCoord;
		double currentY = yCoord;
		double distanceToZero = Math.sqrt((xCoord * xCoord) + (yCoord * yCoord));
		//sets passes to 0
		int passes = 0;
		//calculates passes needed for escape
		while (distanceToZero <= _escapeLim && passes < _escapeTime)
		{
			//xPrime and yPrime are temporary storages for the current calc
			//set xPrime and yPrime equal to their formulas
			//These formulas should be implemented in subclass to use specific formula
			double xPrime = formulaX(xCoord, yCoord, currentX);
			double yPrime = formulaY(xCoord, yCoord, currentY);
			//sets xCalc and yCalc after calculations have occurred
			xCoord = xPrime;
			yCoord = yPrime;
			//updates distanceToZero
			distanceToZero = Math.sqrt((xCoord * xCoord) + (yCoord * yCoord));
			//increase passes
			passes = passes + 1;
		}
		return passes;
	}
	
	//zooms in on fractals
	public void zoom(Point first, Point second) {
		
		if (rowToX(first.x) <= rowToX(second.x)) {
			double xLow = rowToX(2 * first.x);
			_xUpLim = rowToX(2 * second.x);
			_xLowLim = xLow;
		}
		else {
			double xLow = rowToX(2 * second.x);
			_xUpLim = rowToX(2 * first.x);
			_xLowLim = xLow;
		}
		if (columnToY(first.y) <= columnToY(second.y)) {
			double yLow = columnToY(2 * first.y);
			_yUpLim = columnToY(2 * second.y);
			_yLowLim = yLow;
		}
		else {
			double yLow = columnToY(2 * second.y);
			_yUpLim = columnToY(2 * first.y);
			_yLowLim = yLow;
		}
		//generateFractal();
	}
	
	//resets zoom to default lims
	public void resetLimits() {
		_xLowLim = _defaultXLow;
		_xUpLim = _defaultXUp;
		_yLowLim = _defaultYLow;
		_yUpLim = _defaultYUp;
		//generateFractal();
	}
	
	
	//implement abstract in subclass
	abstract public double formulaX(double xCalc, double yCalc, double currentX);
	abstract public double formulaY(double xCalc, double yCalc, double currentY);
	
}
