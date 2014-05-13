/*
 * This is a copyright message... 
 */
package com.puresoltechnologies.purifinity.framework.lang.java7;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.puresoltechnologies.purifinity.server.metrics.spi.LanguageDependedSLOCMetric;
import com.puresoltechnologies.purifinity.server.plugin.java7.Java;
import com.puresoltechnologies.purifinity.server.plugin.java7.metrics.SLOCMetricImpl;

public class JavaTest {

	@Test
	public void testSingleton() {
		Java java = Java.getInstance();
		assertNotNull(java);
		assertSame(java, Java.getInstance());
	}

	@Test
	public void checkServices() {
		Java java = Java.getInstance();
		LanguageDependedSLOCMetric implementation = java
				.getImplementation(LanguageDependedSLOCMetric.class);
		assertNotNull(implementation);
		assertEquals(SLOCMetricImpl.class, implementation.getClass());
	}
}
