package com.puresol.utils;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class InstancesTest extends TestCase {

	@Test
	public void testCreateDefault() {
		try {
			TestClass4Instance test = Instances
					.createInstance(TestClass4Instance.class);
			Assert.assertNotNull(test);
			Assert.assertEquals("default", test.getText());
		} catch (ClassInstantiationException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

	@Test
	public void testException() {
		try {
			Instances.createInstance(TestClass4Instance.class, "TEXT");
			Assert.fail("A ClassInstantiationException was expected!");
		} catch (ClassInstantiationException e) {
			// nothing to catch, this was expected!
		}
	}

	@Test
	public void testRunStaticReturnValueMethod() {
		try {
			int i = Instances.runStaticMethod(TestClass4Instance.class,
					"returnValuedMethod", Integer.class, 4);
			Assert.assertEquals(16, i);
		} catch (MethodInvokationException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

	@Test
	public void testRunStaticVoidMethod() {
		try {
			Instances.runStaticMethod(TestClass4Instance.class, "voidMethod",
					void.class);
		} catch (MethodInvokationException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

	@Test
	public void testInvokeMethodReturnTypeException() {
		try {
			Instances.runStaticMethod(TestClass4Instance.class, "voidMethod",
					Integer.class);
			Assert.fail("Exception was expected!");
		} catch (MethodInvokationException e) {
			// nothing to catch, this was expected!
		}
	}

	@Test
	public void testInvokeMethodTypeException() {
		try {
			Instances.runStaticMethod(TestClass4Instance.class,
					"returnValuedMethod", Integer.class, "Test");
			Assert.fail("Exception was expected!");
		} catch (MethodInvokationException e) {
			// nothing to catch, this was expected!
		}
	}

	@Test
	public void testInvokeInvalidMethodName() {
		try {
			Instances.runStaticMethod(TestClass4Instance.class,
					"invalidMethod", Integer.class, "Test");
			Assert.fail("Exception was expected!");
		} catch (MethodInvokationException e) {
			// nothing to catch, this was expected!
		}
	}
}
