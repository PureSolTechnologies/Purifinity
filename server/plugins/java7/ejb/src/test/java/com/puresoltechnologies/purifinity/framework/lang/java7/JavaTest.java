/*
 * This is a copyright message... 
 */
package com.puresoltechnologies.purifinity.framework.lang.java7;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.puresoltechnologies.purifinity.server.plugin.java7.Java;

public class JavaTest {

	@Test
	public void testSingleton() {
		Java java = Java.getInstance();
		assertNotNull(java);
		assertSame(java, Java.getInstance());
	}
}
