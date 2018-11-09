package code;

import edu.buffalo.fractal.WorkerResult;
import code.Fractal;

public class Worker {
		
	//makes array of fractals and assigns the parts theyre supposed to generate
		public Fractal[] whatFractal(Fractal currentFractal, int threads) {
			Fractal[] retVal = new Fractal[threads];
			
			Fractal[] f = duplicate(currentFractal, threads);
			WorkerResult[] r = new WorkerResult[threads];
			int currentPosition = 0;
			for (int i = 0; i < threads; i++) {
				Fractal current = f[i];
				int increment = currentPosition + (2048 / threads);
				int last = currentPosition + (2048 / threads) - 1;
				System.out.println(i +". " +increment + ", Last " + last);
				if (2048 % threads == 0) {
				current.setSF(currentPosition, currentPosition + (2048 / threads) - 1);
				currentPosition = currentPosition + (2048 / threads);
				}
				
				else {
					if (i != threads - 1) {
					 current.setSF(currentPosition, currentPosition + (2048 / threads) - 1);
					}
					else {
						current.setSF(currentPosition, currentPosition + (2048 / threads) + (2048 % threads));
					}
					currentPosition = currentPosition + (2048 / threads);
				}
			}
			return f;
		}
		
		// makes exact duplicate of fractal passed in
		public Fractal[] duplicate(Fractal currentFractal, int numOfDupes) {
			Fractal[] retVal = new Fractal[numOfDupes];
			Class c = currentFractal.getClass();
			for (int i = 0; i < numOfDupes; i++) {
				try {
					Fractal f = (Fractal) c.newInstance();
					f.setEscapeDistance(currentFractal.getEscapeDistance());
					f.setEscapeTime(currentFractal.getEscapeTime());
					f.setThreads(currentFractal.getThreads());
					double[] lims = currentFractal.getLims();
					f.setLims(lims[0], lims[1], lims[2], lims[3]);
					retVal[i] = f;
					
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return retVal;
		}
		
	}
	
	
	
