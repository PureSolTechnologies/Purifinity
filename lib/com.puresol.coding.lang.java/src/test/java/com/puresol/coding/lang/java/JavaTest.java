/*
 * This is a copyright message... 
 */
package com.puresol.coding.lang.java;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class JavaTest {

    @Test
    public void testSingleton() {
	Java java = Java.getInstance();
	assertNotNull(java);
	assertSame(java, Java.getInstance());
    }

}
