package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import code.Fractal;
import code.Mandel;
import code.Worker;

public class WorkTests {
	
	//test to make sure duplicate method works
	@Test
	public void dupTest() {
		Mandel m = new Mandel();
		Worker w = new Worker();
		m.setEscapeDistance(24);
		m.setEscapeTime(32);
		m.setThreads(7);
		
		Fractal[] f = w.duplicate(m, 2);
		assertEquals(7, f[0].getThreads());
		assertEquals(32, f[0].getEscapeTime());
		assertEquals(24, f[0].getEscapeDistance());
		
		assertEquals(7, f[1].getThreads());
		assertEquals(32, f[1].getEscapeTime());
		assertEquals(24, f[1].getEscapeDistance());
		
		
		
	}

}
