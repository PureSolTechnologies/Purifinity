package com.puresol.coding.lang.fortran.grammar.parts;

import junit.framework.TestCase;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class PrimaryTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("primary", "VARIABLE"));
		assertTrue(GrammarPartTester.test("primary", "x"));

		assertTrue(GrammarPartTester.test("primary", "1.0")); // constant
		assertTrue(GrammarPartTester.test("primary",
				"'ABCDEFGHIJKLMNOPQRSTUVWXYZ' (I:I)")); // designator
		assertTrue(GrammarPartTester.test("primary", "[ 1.0, 2.0 ]")); // array-constructor
		assertTrue(GrammarPartTester.test("primary", "PERSON (12, 'Jones')")); // structure-constructor
		assertTrue(GrammarPartTester.test("primary", "F (X, Y)")); // function-reference
		assertTrue(GrammarPartTester.test("primary", "X%KIND")); // type-param-inquiry
		assertTrue(GrammarPartTester.test("primary", "KIND")); // type-param-name
		assertTrue(GrammarPartTester.test("primary", "(S + T)")); // (expr)
	}
}
