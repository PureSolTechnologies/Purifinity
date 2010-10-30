package com.puresol.coding.lang.java.grammar.parts.chapter7_Packages;

import static org.junit.Assert.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.java.grammar.GrammarPartTester;

public class CompilationUnitTest {

	@Test
	public void testEmptyCompilationUnit() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("CompilationUnit", ""));
	}

	@Test
	public void testSingleSemicolon() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("CompilationUnit", ";"));
	}

	@Test
	public void testSimpleCompilationUnit() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("CompilationUnit", "package pkg;\n"
				+ "import imp.imp2;\n" + "public class Class { }\n"));
	}
}
