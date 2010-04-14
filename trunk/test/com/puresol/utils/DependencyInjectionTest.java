package com.puresol.utils;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class DependencyInjectionTest extends TestCase {

	public static class TestClass {

		@Inject(Integer.class)
		private Integer i = null;

		public Integer getI() {
			return i;
		}
	}

	@Test
	public void testInjection() {
		TestClass testClass = new TestClass();
		Assert.assertEquals(null, testClass.getI());
		DependencyInjection.inject(testClass, new Integer(3));
		Assert.assertEquals(Integer.valueOf(3), testClass.getI());
	}

	@Test
	public void testClassBuilder() {
		DependencyInjection.ClassBuilder<TestClass> builder = new DependencyInjection.ClassBuilder<TestClass>(
				TestClass.class);
		builder.setInjection(Integer.valueOf(42));
		try {
			TestClass testClass = builder.createInstance();
			Assert.assertEquals(Integer.valueOf(42), testClass.getI());
		} catch (ClassInstantiationException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}
}
