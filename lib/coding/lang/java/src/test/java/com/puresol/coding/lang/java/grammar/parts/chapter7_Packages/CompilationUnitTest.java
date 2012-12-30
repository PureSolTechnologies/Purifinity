package com.puresol.coding.lang.java.grammar.parts.chapter7_Packages;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class CompilationUnitTest {

    @Test
    public void testEmptyCompilationUnit() throws Exception {
	assertTrue(JavaGrammarPartTester.test("CompilationUnit", ""));
    }

    @Test
    public void testSingleSemicolon() throws Exception {
	assertTrue(JavaGrammarPartTester.test("CompilationUnit", ";"));
    }

    @Test
    public void testSimpleCompilationUnit() throws Exception {
	assertTrue(JavaGrammarPartTester.test("CompilationUnit",
		"package pkg;\n" + "import imp.imp2;\n"
			+ "public class Class { }\n"));
    }
}
