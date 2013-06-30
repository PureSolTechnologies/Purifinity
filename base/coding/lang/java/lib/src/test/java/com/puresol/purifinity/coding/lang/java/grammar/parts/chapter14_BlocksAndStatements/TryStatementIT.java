package com.puresol.purifinity.coding.lang.java.grammar.parts.chapter14_BlocksAndStatements;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.purifinity.coding.lang.java.grammar.JavaGrammarPartTester;

public class TryStatementIT {

    @Test
    public void test() throws Exception {
	assertTrue(JavaGrammarPartTester.test("TryStatement", "try {\n"
		+ "} catch(Exception e) {\n" + "}\n"));
    }

    @Test
    public void test2() throws Exception {
	assertTrue(JavaGrammarPartTester.test("TryStatement", "try {\n"
		+ "test();\n" + "} catch (Exception e) {\n" + "test();\n"
		+ "}\n"));
    }

}
