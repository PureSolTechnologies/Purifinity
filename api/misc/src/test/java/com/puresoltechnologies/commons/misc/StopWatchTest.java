package com.puresoltechnologies.commons.misc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.commons.misc.StopWatch;

public class StopWatchTest {

	@Test
	public void testInitialValues() {
		StopWatch stopWatch = new StopWatch();
		assertNull(stopWatch.getStartTime());
		assertNull(stopWatch.getStopTime());
		assertEquals(0, stopWatch.getMilliseconds());
		assertEquals(0.0, stopWatch.getSeconds(), 1e-10);
	}

	@Test
	public void testMeasurement() throws InterruptedException {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Thread.sleep(100);
		stopWatch.stop();
		assertNotNull(stopWatch.getStartTime());
		assertNotNull(stopWatch.getStopTime());
		long milliseconds = stopWatch.getMilliseconds();
		assertTrue("Expected to have at least 100ms, but got: " + milliseconds
				+ "ms", milliseconds >= 90);
		assertEquals(stopWatch.getSeconds(), milliseconds / 1000.0, 0.01);
		assertTrue(stopWatch.getStopTime().getTime() >= stopWatch
				.getStartTime().getTime() + 100);
	}

}
