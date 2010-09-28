package com.puresol.uhura.grammar.production;

import static org.junit.Assert.*;

import org.junit.Test;

public class FinishConstructionTest {

	@Test
	public void testSingleton() {
		Construction finish = FinishTerminal.getInstance();
		assertNotNull(finish);
		assertSame(finish, FinishTerminal.getInstance());
	}

}
