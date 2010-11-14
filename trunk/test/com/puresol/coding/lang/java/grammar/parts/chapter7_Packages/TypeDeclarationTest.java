package com.puresol.coding.lang.java.grammar.parts.chapter7_Packages;

import static org.junit.Assert.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class TypeDeclarationTest {

	@Test
	public void testSingleSemicolon() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(JavaGrammarPartTester.test("TypeDeclaration", ";"));
	}
}
