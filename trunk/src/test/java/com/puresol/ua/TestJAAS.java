package com.puresol.ua;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This is a test class for UA testing.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TestJAAS extends JAAS {

	public TestJAAS() {
		super(TestJAAS.class.getResource("/config/JAASTest.conf").toString());
	}

	@Test
	public void testInstance() {
		assertNotNull(new TestJAAS());
	}

	@Test
	public void testResourcePresence() {
		assertNotNull(TestJAAS.class.getResource("/config/JAASTest.conf"));
	}
}
