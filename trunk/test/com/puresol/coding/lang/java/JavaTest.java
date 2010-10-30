package com.puresol.coding.lang.java;

import static org.junit.Assert.*;

import org.junit.Test;

public class JavaTest {

	@Test
	public void testSingleton() {
		Java java = Java.getInstance();
		assertNotNull(java);
		assertSame(java, Java.getInstance());
	}

}
