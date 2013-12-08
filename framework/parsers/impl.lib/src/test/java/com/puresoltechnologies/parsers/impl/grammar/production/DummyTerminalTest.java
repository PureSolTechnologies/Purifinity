package com.puresoltechnologies.parsers.impl.grammar.production;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresoltechnologies.parsers.impl.grammar.production.Construction;
import com.puresoltechnologies.parsers.impl.grammar.production.DummyTerminal;

public class DummyTerminalTest {

	@Test
	public void testSingleton() {
		Construction empty = DummyTerminal.getInstance();
		assertNotNull(empty);
		assertSame(empty, DummyTerminal.getInstance());
		assertEquals("_DUMMY_", empty.getName());
	}

}
