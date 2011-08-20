package com.puresol.di;

import static org.junit.Assert.*;

import org.junit.Test;

public class DependencyInjectionTest {

	@Test
	public void testInjection() {
		DITestClass testClass = new DITestClass();
		assertEquals(null, testClass.getI());
		DependencyInjection.inject(testClass,
				Injection.unnamed(Integer.valueOf(3)));
		assertEquals(Integer.valueOf(3), testClass.getI());
	}

	@Test
	public void testInjection2() {
		DITestClass2 testClass = new DITestClass2();
		assertEquals(null, testClass.getI());
		assertEquals(null, testClass.getJ());
		DependencyInjection.inject(testClass,
				Injection.unnamed(Integer.valueOf(3)),
				Injection.unnamed(Double.valueOf(3.1415926)));
		assertEquals(Integer.valueOf(3), testClass.getI());
		assertEquals(Double.valueOf(3.1415926), testClass.getJ());
	}

	@Test
	public void testInjection3() {
		DITestClass3 testClass = new DITestClass3();
		assertEquals(null, testClass.getI());
		assertEquals(null, testClass.getJ());
		assertEquals(null, testClass.getJ2());
		DependencyInjection.inject(testClass,
				Injection.unnamed(Integer.valueOf(3)),
				Injection.unnamed(Double.valueOf(3.1415926)),
				Injection.named("TestName", 1.23456789));
		assertEquals(Integer.valueOf(3), testClass.getI());
		assertEquals(Double.valueOf(3.1415926), testClass.getJ());
		assertEquals(Double.valueOf(1.23456789), testClass.getJ2());
	}

}
