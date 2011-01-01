package com.puresol.coding.lang.fortran.grammar.parts.clause7_expressions_and_assignment;

import junit.framework.TestCase;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R701_PrimaryTest extends TestCase {

	@Test
	public void testSimple() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("primary", "a(33)"));
	}

	// @Test
	// public void test() {
	// Logger.getRootLogger().setLevel(Level.TRACE);
	// assertTrue(FortranGrammarPartTester.test("primary", "VARIABLE"));
	// assertTrue(FortranGrammarPartTester.test("primary", "(x)"));
	// assertTrue(FortranGrammarPartTester.test("primary", "x"));
	//
	// assertTrue(FortranGrammarPartTester.test("primary", "1.0")); // constant
	// assertTrue(FortranGrammarPartTester.test("primary",
	// "'ABCDEFGHIJKLMNOPQRSTUVWXYZ' (I:I)")); // designator
	// assertTrue(FortranGrammarPartTester.test("primary", "[ 1.0, 2.0 ]")); //
	// array-constructor
	// assertTrue(FortranGrammarPartTester.test("primary",
	// "PERSON (12, 'Jones')")); // structure-constructor
	// assertTrue(FortranGrammarPartTester.test("primary", "F (X, Y)")); //
	// function-reference
	// assertTrue(FortranGrammarPartTester.test("primary", "X%TypeParamName"));
	// // type-param-inquiry
	// assertTrue(FortranGrammarPartTester.test("primary", "TypeParamName")); //
	// type-param-name
	// assertTrue(FortranGrammarPartTester.test("primary", "(S + T)")); //
	// (expr)
	// }
}
