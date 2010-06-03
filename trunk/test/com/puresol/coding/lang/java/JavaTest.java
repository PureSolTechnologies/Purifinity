package com.puresol.coding.lang.java;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class JavaTest extends TestCase {

    @Test
    public void testSingleton() {
	Java java1 = Java.getInstance();
	Assert.assertNotNull(java1);
	Java java2 = Java.getInstance();
	Assert.assertNotNull(java2);
	Assert.assertSame(java1, java2);
    }

    @Test
    public void testGetName() {
	Assert.assertEquals("Java", Java.getInstance().getName());
    }

    @Test
    public void testIsObjectOriented() {
	Assert.assertEquals(true, Java.getInstance().isObjectOriented());
    }

}
