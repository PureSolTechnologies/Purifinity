/*
 * This is a copyright message... 
 */
package com.puresol.coding.lang.java;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.metrics.sloc.LanguageDependedSLOCMetric;

public class JavaTest {

	@Test
	public void testSingleton() {
		Java java = Java.getInstance();
		assertNotNull(java);
		assertSame(java, Java.getInstance());
	}

	@Test
	public void testGetImplementation() {
		Java java = Java.getInstance();
		assertNotNull(java.getImplementation(LanguageDependedSLOCMetric.class));
	}
}
