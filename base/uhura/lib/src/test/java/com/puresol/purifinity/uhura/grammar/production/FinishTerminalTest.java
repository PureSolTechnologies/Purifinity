package com.puresol.purifinity.uhura.grammar.production;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.purifinity.uhura.grammar.production.Construction;
import com.puresol.purifinity.uhura.grammar.production.FinishTerminal;

public class FinishTerminalTest {

	@Test
	public void testSingleton() {
		Construction finish = FinishTerminal.getInstance();
		assertNotNull(finish);
		assertSame(finish, FinishTerminal.getInstance());
		assertEquals("_FINISH_", finish.getName());
	}

}
