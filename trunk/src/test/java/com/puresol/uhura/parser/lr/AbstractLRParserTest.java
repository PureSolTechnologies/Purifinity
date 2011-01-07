package com.puresol.uhura.parser.lr;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbstractLRParserTest {

	@Test
	public void testCloningPreconditions() {
		try {
			AbstractLRParser.class.getDeclaredField("backtrackEnabled");
			AbstractLRParser.class.getDeclaredField("backtrackDepth");
			AbstractLRParser.class.getDeclaredField("timeout");
			AbstractLRParser.class.getDeclaredField("excludeFailsEnabled");
			AbstractLRParser.class.getDeclaredField("parserTable");
			AbstractLRParser.class.getDeclaredField("backtrackStack");
			AbstractLRParser.class.getDeclaredField("failedActions");
			AbstractLRParser.class.getDeclaredField("stateStack");
			AbstractLRParser.class.getDeclaredField("actionStack");
			AbstractLRParser.class.getDeclaredField("parserErrors");
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}
}
