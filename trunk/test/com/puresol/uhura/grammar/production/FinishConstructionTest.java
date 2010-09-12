package com.puresol.uhura.grammar.production;

import junit.framework.TestCase;

import org.junit.Test;

public class FinishConstructionTest extends TestCase {

	@Test
	public void testSingleton() {
		Construction finish = FinishTerminal.getInstance();
		assertNotNull(finish);
		assertSame(finish, FinishTerminal.getInstance());
	}

}
