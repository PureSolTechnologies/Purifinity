package com.puresol.utils.di;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class DependencyInjectionTest extends TestCase {

	@Test
	public void testInjection() {
		DITestClass testClass = new DITestClass();
		Assert.assertEquals(null, testClass.getI());
		DependencyInjection.inject(testClass, Injection.unnamed(Integer
				.valueOf(3)));
		Assert.assertEquals(Integer.valueOf(3), testClass.getI());
	}

	@Test
	public void testInjection2() {
		DITestClass2 testClass = new DITestClass2();
		Assert.assertEquals(null, testClass.getI());
		Assert.assertEquals(null, testClass.getJ());
		DependencyInjection.inject(testClass, Injection.unnamed(Integer
				.valueOf(3)), Injection.unnamed(Double.valueOf(3.1415926)));
		Assert.assertEquals(Integer.valueOf(3), testClass.getI());
		Assert.assertEquals(Double.valueOf(3.1415926), testClass.getJ());
	}

	@Test
	public void testInjection3() {
		DITestClass3 testClass = new DITestClass3();
		Assert.assertEquals(null, testClass.getI());
		Assert.assertEquals(null, testClass.getJ());
		Assert.assertEquals(null, testClass.getJ2());
		DependencyInjection.inject(testClass, Injection.unnamed(Integer
				.valueOf(3)), Injection.unnamed(Double.valueOf(3.1415926)),
				Injection.named("TestName", 1.23456789));
		Assert.assertEquals(Integer.valueOf(3), testClass.getI());
		Assert.assertEquals(Double.valueOf(3.1415926), testClass.getJ());
		Assert.assertEquals(Double.valueOf(1.23456789), testClass.getJ2());
	}

}
