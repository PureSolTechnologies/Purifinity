package com.puresol.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class InstancesTest {

	@Test
	public void testCreateDefault() {
		try {
			TestClass4Instance test = Instances
					.createInstance(TestClass4Instance.class);
			assertNotNull(test);
			assertEquals("default", test.getText());
		} catch (ClassInstantiationException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testException() {
		try {
			Instances.createInstance(TestClass4Instance.class, "TEXT");
			fail("A ClassInstantiationException was expected!");
		} catch (ClassInstantiationException e) {
			// nothing to catch, this was expected!
		}
	}

	@Test
	public void testRunStaticReturnValueMethod() {
		try {
			int i = Instances.runStaticMethod(TestClass4Instance.class,
					"returnValuedMethod", Integer.class, 4);
			assertEquals(16, i);
		} catch (MethodInvokationException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testRunStaticVoidMethod() {
		try {
			Instances.runStaticMethod(TestClass4Instance.class, "voidMethod",
					void.class);
		} catch (MethodInvokationException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testInvokeMethodReturnTypeException() {
		try {
			Instances.runStaticMethod(TestClass4Instance.class, "voidMethod",
					Integer.class);
			fail("Exception was expected!");
		} catch (MethodInvokationException e) {
			// nothing to catch, this was expected!
		}
	}

	@Test
	public void testInvokeMethodTypeException() {
		try {
			Instances.runStaticMethod(TestClass4Instance.class,
					"returnValuedMethod", Integer.class, "Test");
			fail("Exception was expected!");
		} catch (MethodInvokationException e) {
			// nothing to catch, this was expected!
		}
	}

	@Test
	public void testInvokeInvalidMethodName() {
		try {
			Instances.runStaticMethod(TestClass4Instance.class,
					"invalidMethod", Integer.class, "Test");
			fail("Exception was expected!");
		} catch (MethodInvokationException e) {
			// nothing to catch, this was expected!
		}
	}
}
