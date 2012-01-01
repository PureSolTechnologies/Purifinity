package com.puresol.coding.lang.fortran.grammar.parts.clause12_procedures;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R1222_ActualArgSpecListTest extends TestCase {

	@Test
	public void test3() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("actual-arg-spec-list", "A"));
	}

	@Test
	public void test4() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("actual-arg-spec-list", "A, B"));
	}

	@Test
	public void test5() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("actual-arg-spec-list", "A, B, C"));
	}

	@Test
	public void test6() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("actual-arg-spec-list",
				"N, X, INCX, Y, INCY"));
	}
}
