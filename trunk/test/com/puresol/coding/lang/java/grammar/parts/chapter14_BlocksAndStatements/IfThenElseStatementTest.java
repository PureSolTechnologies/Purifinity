package com.puresol.coding.lang.java.grammar.parts.chapter14_BlocksAndStatements;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.GrammarPartTester;

public class IfThenElseStatementTest {

	@Test
	public void test1() {
		assertTrue(GrammarPartTester.test("IfThenElseStatement", "if (true)\n"

		+ "System.err.println(\"\");\n" + "else\n"
				+ "System.err.println(\"\");"));
	}

	@Test
	public void test2() {
		assertTrue(GrammarPartTester.test("IfThenElseStatement", "if (true)\n"
				+ "if (true)\n" + "System.err.println(\"\");\n" + "else\n"
				+ "System.err.println(\"\");\n" + "else\n"
				+ "System.err.println(\"\");"));
	}

}
