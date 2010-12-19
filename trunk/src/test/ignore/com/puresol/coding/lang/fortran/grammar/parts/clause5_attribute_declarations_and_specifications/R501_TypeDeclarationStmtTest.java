package com.puresol.coding.lang.fortran.grammar.parts.clause5_attribute_declarations_and_specifications;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R501_TypeDeclarationStmtTest extends TestCase {

	@Test
	public void testDoubleComplex() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("type-declaration-stmt",
				"      DOUBLE COMPLEX DC\n"));
	}

	@Test
	public void testInteger() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("type-declaration-stmt",
				"      INTEGER(4) i, j\n"));
	}

	@Test
	public void testInteger2() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("type-declaration-stmt",
				"INTEGER(4) :: NODOGR\n"));
	}

}
