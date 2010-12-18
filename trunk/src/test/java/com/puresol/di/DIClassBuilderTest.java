package com.puresol.di;

import org.junit.Test;

import com.puresol.utils.ClassInstantiationException;

import junit.framework.Assert;
import junit.framework.TestCase;

public class DIClassBuilderTest extends TestCase {

    @Test
    public void testClassBuilder() {
	DIClassBuilder builder = DIClassBuilder.forInjections(Injection
		.unnamed(Integer.valueOf(42)));
	try {
	    DITestClass testClass = builder.createInstance(DITestClass.class);
	    Assert.assertEquals(Integer.valueOf(42), testClass.getI());
	} catch (ClassInstantiationException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	}
    }

}
