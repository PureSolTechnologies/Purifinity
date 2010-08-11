package com.puresol.coding.lang.fortran.source.grammar.programunits;

import org.junit.Test;

import com.puresol.coding.lang.fortran.source.grammar.FortranGrammarTester;

import junit.framework.TestCase;

public class ProgramStmtTest extends TestCase {
	@Test
	public void test() {
		assertTrue(FortranGrammarTester
				.valid("PROGRAM Test", ProgramStmt.class));
		assertTrue(FortranGrammarTester.invalid("PROGRAM", ProgramStmt.class));

	}
}
