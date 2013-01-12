package com.puresol.coding.lang.java.grammar.parts.chapter14_BlocksAndStatements;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class IfThenElseStatementTest {

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