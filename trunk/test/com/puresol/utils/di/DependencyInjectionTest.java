package com.puresol.utils.di;

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

	@Test
	public void testInjection2() {
		DITestClass2 testClass = new DITestClass2();
		Assert.assertEquals(null, testClass.getI());
		DependencyInjection.inject(testClass, Integer.valueOf(3), Double
				.valueOf(3.1415926));
		Assert.assertEquals(Double.valueOf(3.1415926), testClass.getJ());
		Assert.assertEquals(Integer.valueOf(3), testClass.getI());
	}

}
