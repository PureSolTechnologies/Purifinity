package com.puresol.coding.lang.java.grammar.parts.chapter14_BlocksAndStatements;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class TryStatementTest {

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
