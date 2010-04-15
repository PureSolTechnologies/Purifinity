package com.puresol.utils;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class DependencyInjectionTest extends TestCase {

	@Test
	public void testInjection() {
		DITestClass testClass = new DITestClass();
		Assert.assertEquals(null, testClass.getI());
		DependencyInjection.inject(testClass, new Integer(3));
		Assert.assertEquals(Integer.valueOf(3), testClass.getI());
	}

}
