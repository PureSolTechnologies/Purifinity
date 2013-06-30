package com.puresol.purifinity.uhura.grammar.production;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.purifinity.uhura.grammar.production.Construction;
import com.puresol.purifinity.uhura.grammar.production.EmptyTerminal;

public class EmptyTerminalTest {

	@Test
	public void testSingleton() {
		Construction empty = EmptyTerminal.getInstance();
		assertNotNull(empty);
		assertSame(empty, EmptyTerminal.getInstance());
		assertEquals("_EMPTY_", empty.getName());
	}

}
