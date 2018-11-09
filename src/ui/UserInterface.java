package ui;

import java.awt.Dimension;

/**
 * A class that implements the user interface for phase 2.
 *
 * @author Josh Labonte
 * @author Yizhou Sun (Joey)
 * @author Ravi Patel
 * @author Jon Zeglen
 * 
 */

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import code.BurningShips;
import code.Fractal;
import code.Julia;
import code.Mandel;
import code.Multibrot;
import code.Worker;
import edu.buffalo.fractal.ColorModelFactory;
import edu.buffalo.fractal.ComputePool;
import edu.buffalo.fractal.FractalPanel;

  
	public class UserInterface implements ActionListener {
	
			
	public JFrame _frame;      // sets up JFrame for fractal 
	public JMenuBar _menuBar;  // adds Jmenues and Menu items
	       JMenu _file;
	       JMenu _jmFrac;
	       JMenu _jmColor;
	       JMenuItem _jmExit;
	       JMenuItem _jmblue;
	       JMenuItem _jmred;
	       JMenuItem _Purple;
	       JMenuItem _Gray;
	       FractalPanel _fracPanel;
	       Point _firstPosition;
	       JTextField _text;
	       
	       Worker _work = new Worker();
	       ComputePool _pool = new ComputePool();
	       
	   public  Fractal _currentFractal = new Mandel();
	       int _currentEscape;
	       int _currentEscapeTime;
	       int _currentThreads = 4;
	       
		public UserInterface()  {           // creates user interface with instance variables in JFrame
	     _frame = new JFrame("Fractal");
	    _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame
	    _fracPanel = new FractalPanel();
	     _menuBar = new JMenuBar();
	     _file = new JMenu("File");
	     _jmExit = new JMenuItem("Exit");
	     _currentEscape = _currentFractal.getEscapeDistance();
	     _fracPanel.setSize(2048, 2048);
	     _fracPanel.setPreferredSize(new Dimension(1024, 1024));
	     
	     //_fracPanel.updateImage(_currentFractal.getArray());
	     
	     _text = new JTextField();
	     
	     _pool.clearPool();
	     _pool.changePanel(_fracPanel);
	     _pool.generateFractal(2048, _work.whatFractal(_currentFractal, _currentThreads));
	     
	     // adds mouse listener and motion listener
	     _fracPanel.addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent e) {
				String s = _firstPosition.x + ", " + _firstPosition.y + " to " + e.getX() + ", " + e.getY();
				_text.setText(s);
				_text.setVisible(true);
				_menuBar.add(_text);
				_frame.pack();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	 
	     });
	     _fracPanel.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generate method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			

			//figures out coordinates for zoom
			@Override
			public void mousePressed(MouseEvent e) {
				_firstPosition = _fracPanel.getMousePosition();
				JMenu m = new JMenu(_firstPosition.x + ", " + _firstPosition.y + " to ");
				_frame.pack();
			}

			//figures out coordinates for zoom
			@Override
			public void mouseReleased(MouseEvent e) {
				Point second = _fracPanel.getMousePosition();
				_currentFractal.zoom(_firstPosition, second);
				System.out.println("Zoom should be done");
				_pool.clearPool();
				//_pool.generateFractal(2048, _work.whatFractal(_currentFractal, _currentThreads));
				updateUI();
				System.out.println("Zoom should BE done");
			}
	    	 
	     });
	     
	     
	     // creates set escape distance button.
	     JMenuItem escape = new JMenuItem("Set Escape Distance");
	     escape.addActionListener(new ActionListener(){

	    	 //takes input for new escape distance
			@Override
			public void actionPerformed(ActionEvent e) {
				int num;
				// error message
				String error = "Please enter a valid input. \n Valid inputs are integers greater than or equal to 1";
				
				String test = JOptionPane.showInputDialog("Enter a new escape distance value");
				try
				{
					
					// checks if  num is legal
					num = Integer.parseInt(test);
					if (num >= 1)
					{
						_currentEscape = num;
						_currentFractal.setEscapeDistance(num);
						updateUI();
					}
					else
					{
						JOptionPane.showMessageDialog(_frame, error);
					}
				}
				catch (NumberFormatException idk)
				{
					JOptionPane.showMessageDialog(_frame, error);
				}
				
			}
	    	 
	     });
	     
	  // creates set escape time button.
	     JMenuItem escapeTime = new JMenuItem("Set Escape Time");
	     escapeTime.addActionListener(new ActionListener(){

	    	 //takes input for new escape distance
			@Override
			public void actionPerformed(ActionEvent e) {
				int num;
				// error message
				String error = "Please enter a valid input. \n Valid inputs are integers greater than \n or equal to 1 and less than or equal to 255";
				
				String test = JOptionPane.showInputDialog("Enter a new escape time value");
				try
				{
					
					// checks if  num is legal
					num = Integer.parseInt(test);
					if (num >= 1 && num <= 255)
					{
						_currentEscapeTime = num;
						_currentFractal.setEscapeTime(num);
						updateUI();
					}
					else
					{
						JOptionPane.showMessageDialog(_frame, error);
					}
				}
				catch (NumberFormatException idk)
				{
					JOptionPane.showMessageDialog(_frame, error);
				}
				
			}
	    	 
	     });
	     
	     // creates set threads.
	     JMenuItem threads = new JMenuItem("Set Number of Threads");
	     threads.addActionListener(new ActionListener(){

	    	 //takes input for new escape distance
			@Override
			public void actionPerformed(ActionEvent e) {
				int num;
				// error message
				String error = "Please enter a valid input. \n Valid inputs are integers greater than or equal to 1 and less than or equal to 128";
				
				String test = JOptionPane.showInputDialog("Enter number of threads");
				try
				{
					
					// checks if  num is legal
					num = Integer.parseInt(test);
					if (num >= 1)
					{
						_currentThreads = num;
						_currentFractal.setThreads(num);
						updateUI();
					}
					else
					{
						JOptionPane.showMessageDialog(_frame, error);
					}
				}
				catch (NumberFormatException idk)
				{
					JOptionPane.showMessageDialog(_frame, error);
				}
				
			}
	    	 
	     });
	     
	     //add zoom button
	     JMenuItem resetZoomButton = new JMenuItem("Reset Zoom");
	     resetZoomButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				_currentFractal.resetLimits();
				updateUI();
			}
	    	 
	     });
		  
	     
	     //adds buttons to menu
	     _file.add(resetZoomButton);
	     _file.add(escape);
	     _file.add(escapeTime);
	     _file.add(threads);
	     _file.add(_jmExit);
	   
	    
	     _jmExit.addActionListener(new ActionListener(){
	    	 @Override
	 	    public void actionPerformed(ActionEvent x) {
	    		 System.exit(0);
	    	 }
	    	 
	    });  
	    	
	    
	     
	     
	    _menuBar.add(_file);

	    _jmFrac = new JMenu("Fractal");
	     
	    // creates buttons for switching fractals
	   createFractalButton(new BurningShips() , "BurningShip");
	   createFractalButton(new Julia(), "Julia");
	   createFractalButton(new Mandel(), "Mandelbrot");
	   createFractalButton(new Multibrot(), "Multibrot");
	   
	    _menuBar.add(_jmFrac);
// creates button for color options
	     _jmColor = new JMenu("Color");
	     _jmred = new JMenuItem("Red");
	    _jmColor.add(_jmred);	    
	     _jmblue = new JMenuItem("Blue");
	    _jmColor.add(_jmblue);
	     _Gray = new JMenuItem("Gray");
	    _jmColor.add(_Gray);
	     _Purple = new JMenuItem("Purple");
	    _jmColor.add(_Purple);
	    _menuBar.add(_jmColor);
	   
	    
		_jmred.addActionListener(new ActionListener() {
  // calls action event
			@Override
			public void actionPerformed(ActionEvent arg0) {

				final ColorModelFactory g = new ColorModelFactory();
				_fracPanel.setIndexColorModel(g.createRainbowColorModel(128));
				updateUI();
			}
		});

		_jmblue.addActionListener(new ActionListener() {
			 // calls color and updates image for each color
			@Override
			public void actionPerformed(ActionEvent arg0) {

				final ColorModelFactory g = new ColorModelFactory();  
				_fracPanel.setIndexColorModel(g.createBluesColorModel(128));
				updateUI();
			}

		});
		
		_Gray.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				final ColorModelFactory g = new ColorModelFactory();
				_fracPanel.setIndexColorModel(g.createGrayColorModel(128));
				updateUI();
			}

		});
		
		_Purple.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				 final ColorModelFactory g = new ColorModelFactory();
				_fracPanel.setIndexColorModel(g.createSomeColorModel(128));
				updateUI();
			}

		});
	
	    _frame.add(_fracPanel);
	    
	    _jmExit.addActionListener(this);
	    _jmred.addActionListener(this);
	    _frame.setJMenuBar(_menuBar);
	    _frame.setVisible(true);
	    _frame.pack();
	  }
		
		  /** Creates menu item on the ui.
		   * @param JMenu The type of menu being used
		   * @param String The menu's name
		   *  */
		public void createMenuItem(JMenu menu, String name) {
	    	menu.add(new JMenuItem(name));
	    	
	    }
		
		// tells what button is selected 
	  public void actionPerformed(ActionEvent ae) {
	    String comStr = ae.getActionCommand();
	    System.out.println(comStr + " Selected");
	  }
	  
	    public void actionExit(ActionEvent e) {
	        //DO SOMETHING /
	        System.exit(0);
	    }
	  
	  
	  
	  /** Creates a menu item for the fractal menu
	   * @param fractalType The type of fractal being used
	   * @param buttonName What you're going to name the button
	   *  */
	    
	  public void createFractalButton(final Fractal fractalType, String buttonName) {
		  JMenuItem item = new JMenuItem(buttonName);
		  
		  item.addActionListener(new ActionListener() {

			  // calls and generates fractal to the ui
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				_currentFractal = fractalType;
				_currentFractal.setEscapeDistance(_currentEscape);
				_currentFractal.setEscapeTime(_currentEscapeTime);
				_currentFractal.setThreads(_currentThreads);
				_currentFractal.resetLimits();
				_pool.clearPool();
				//_pool.generateFractal(2048, _work.whatFractal(_currentFractal, _currentThreads));
				//_currentFractal.generateFractal();
				updateUI();
			}
			  
		  });
		  
		  _jmFrac.add(item);
		  
	  }
	  
	  
	  /** Updates ui with fractal
	   *  */
	  public void updateUI() {
		  //_fracPanel.updateImage(_currentFractal.getArray());
		  _pool.clearPool();
		  _pool.generateFractal(2048, _work.whatFractal(_currentFractal, _currentThreads));
		  _text.setVisible(false);
		  _menuBar.remove(_text);
		  _frame.pack();
	  }
	  
	}
	 