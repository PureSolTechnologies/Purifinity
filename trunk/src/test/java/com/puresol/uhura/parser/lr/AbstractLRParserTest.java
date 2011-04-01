package com.puresol.uhura.parser.lr;

import org.junit.Test;

public class AbstractLRParserTest {

	@Test
	public void testCloningPreconditions() throws Throwable {
		AbstractLRParser.class.getDeclaredField("backtrackEnabled");
		AbstractLRParser.class.getDeclaredField("backtrackDepth");
		AbstractLRParser.class.getDeclaredField("timeout");
		AbstractLRParser.class.getDeclaredField("memoization");
		AbstractLRParser.class.getDeclaredField("parserTable");
		AbstractLRParser.class.getDeclaredField("backtrackStack");
		AbstractLRParser.class.getDeclaredField("memoizationBuffer");
		AbstractLRParser.class.getDeclaredField("stateStack");
		AbstractLRParser.class.getDeclaredField("actionStack");
		AbstractLRParser.class.getDeclaredField("parserErrors");
	}
}
