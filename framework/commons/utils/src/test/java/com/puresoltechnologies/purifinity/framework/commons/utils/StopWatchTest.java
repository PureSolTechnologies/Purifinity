package com.puresoltechnologies.purifinity.framework.commons.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
		assertTrue(stopWatch.getMilliseconds() >= 100);
		assertTrue(stopWatch.getSeconds() >= 0.1);
		assertTrue(stopWatch.getStopTime().getTime() >= stopWatch
				.getStartTime().getTime() + 100);
	}

}
