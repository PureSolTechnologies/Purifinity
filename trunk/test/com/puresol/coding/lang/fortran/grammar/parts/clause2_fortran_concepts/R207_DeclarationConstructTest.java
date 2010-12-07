package com.puresol.coding.lang.fortran.grammar.parts.clause2_fortran_concepts;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R207_DeclarationConstructTest extends TestCase {

	@Test
	public void test1() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("declaration-construct",
				"PARAMETER(INOM2 =  2 * INOM)\n"));
	}

	@Test
	public void test2() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("declaration-construct",
				"REAL(8) CROSR\n"));
	}
}
