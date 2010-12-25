package com.puresol.di;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.utils.ClassInstantiationException;

public class DIClassBuilderTest {

	@Test
	public void testClassBuilder() {
		DIClassBuilder builder = DIClassBuilder.forInjections(Injection
				.unnamed(Integer.valueOf(42)));
		try {
			DITestClass testClass = builder.createInstance(DITestClass.class);
			assertEquals(Integer.valueOf(42), testClass.getI());
		} catch (ClassInstantiationException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

}
