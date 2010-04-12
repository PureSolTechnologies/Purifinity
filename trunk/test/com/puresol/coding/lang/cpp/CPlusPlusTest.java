package com.puresol.coding.lang.cpp;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CPlusPlusTest extends TestCase {

    @Test
    public void testSingleton() {
	CPlusPlus cpp1 = CPlusPlus.getInstance();
	Assert.assertNotNull(cpp1);
	CPlusPlus cpp2 = CPlusPlus.getInstance();
	Assert.assertNotNull(cpp2);
	Assert.assertSame(cpp1, cpp2);
    }

    @Test
    public void testGetName() {
	Assert.assertEquals("C++", CPlusPlus.getInstance().getName());
    }

    @Test
    public void testIsObjectOriented() {
	Assert.assertEquals(true, CPlusPlus.getInstance().isObjectOriented());
    }

}
