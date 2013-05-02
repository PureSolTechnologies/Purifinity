package com.puresol.coding.lang.java.grammar.parts.chapter14_BlocksAndStatements;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class IfThenElseStatementNoShortIfIT {

    @Test
    public void test1() throws Exception {
	assertTrue(JavaGrammarPartTester.test("IfThenElseStatementNoShortIf",
		"if (true)\n" + "System.err.println(\"\");\n" + "else\n"
			+ "System.err.println(\"\");\n"));
    }

}
