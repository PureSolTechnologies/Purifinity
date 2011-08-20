package com.puresol.coding.lang.fortran.grammar.parts.clause6_use_of_data_objects;

import junit.framework.TestCase;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R602_VariableTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("variable", "IF"));
		assertTrue(FortranGrammarPartTester.test("variable", "DO"));
		assertTrue(FortranGrammarPartTester.test("variable", "WHILE"));
		assertTrue(FortranGrammarPartTester.test("variable", "THEN"));
		assertTrue(FortranGrammarPartTester.test("variable", "ELSE"));
	}
}
