package com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts.chapter14_BlocksAndStatements;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.java7.grammar.JavaGrammarPartTester;

public class IfThenElseStatementIT {

	@Test
	public void test1() throws Exception {
		assertTrue(JavaGrammarPartTester.test("IfThenElseStatement",
				"if (true)\n"

				+ "System.err.println(\"\");\n" + "else\n"
						+ "System.err.println(\"\");"));
	}

	@Test
	public void test2() throws Exception {
		assertTrue(JavaGrammarPartTester.test("IfThenElseStatement",
				"if (true)\n" + "if (true)\n" + "System.err.println(\"\");\n"
						+ "else\n" + "System.err.println(\"\");\n" + "else\n"
						+ "System.err.println(\"\");"));
	}

}
