package com.puresol.coding.lang.java.grammar.parts.chapter14_BlocksAndStatements;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class StatementNoShortIfTest {

    @Test
    public void test1() throws Exception {
	assertTrue(JavaGrammarPartTester.test("StatementNoShortIf",
		"System.err.println(\"\");\n"));
    }

    @Test
    public void test2() throws Exception {
	assertTrue(JavaGrammarPartTester.test("StatementNoShortIf",
		"if (true)\n" + "System.err.println(\"\");\n" + "else\n"
			+ "System.err.println(\"\");\n"));
    }

}