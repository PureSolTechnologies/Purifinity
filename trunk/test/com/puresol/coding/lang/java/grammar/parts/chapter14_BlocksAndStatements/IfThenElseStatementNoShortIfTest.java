package com.puresol.coding.lang.java.grammar.parts.chapter14_BlocksAndStatements;

import static org.junit.Assert.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.java.grammar.GrammarPartTester;

public class IfThenElseStatementNoShortIfTest {

	@Test
	public void test1() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("IfThenElseStatementNoShortIf",
				"if (true)\n" + "System.err.println(\"\");\n" + "else\n"
						+ "System.err.println(\"\");\n"));
	}

}
