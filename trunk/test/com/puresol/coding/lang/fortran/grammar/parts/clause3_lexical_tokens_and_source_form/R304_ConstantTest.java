package com.puresol.coding.lang.fortran.grammar.parts.clause3_lexical_tokens_and_source_form;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R304_ConstantTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("constant", "1.0"));
		assertTrue(FortranGrammarPartTester.test("constant", "0"));
		assertTrue(FortranGrammarPartTester.test("constant", "1.23"));
		assertTrue(FortranGrammarPartTester.test("constant", "(A,B)"));
		assertTrue(FortranGrammarPartTester.test("constant", "( 1.2 , 3.4 )"));
		assertTrue(FortranGrammarPartTester.test("constant", "'HALLO'"));
		assertTrue(FortranGrammarPartTester.test("constant", ".TRUE."));
		assertTrue(FortranGrammarPartTester.test("constant", ".FALSE."));
	}

}
