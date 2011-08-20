package com.puresol.uhura.grammar.production;

import static org.junit.Assert.*;

import org.junit.Test;

public class DummyTerminalTest {

	@Test
	public void testSingleton() {
		Construction empty = DummyTerminal.getInstance();
		assertNotNull(empty);
		assertSame(empty, DummyTerminal.getInstance());
		assertEquals("_DUMMY_", empty.getName());
	}

}
