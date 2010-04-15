package com.puresol.utils;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class DIClassBuilderTest extends TestCase {

	@Test
	public void testClassBuilder() {
		DIClassBuilder<DITestClass> builder = new DIClassBuilder<DITestClass>(
				DITestClass.class);
		builder.setInjection(Integer.valueOf(42));
		try {
			DITestClass testClass = builder.createInstance();
			Assert.assertEquals(Integer.valueOf(42), testClass.getI());
		} catch (ClassInstantiationException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

}
