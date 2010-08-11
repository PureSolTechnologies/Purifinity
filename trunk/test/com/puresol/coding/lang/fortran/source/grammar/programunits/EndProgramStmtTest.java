package com.puresol.coding.lang.fortran.source.grammar.programunits;

import org.junit.Test;

import com.puresol.coding.lang.fortran.source.grammar.FortranGrammarTester;

import junit.framework.TestCase;

public class EndProgramStmtTest extends TestCase {

	@Test
	public void test() {
		assertTrue(FortranGrammarTester.valid("END", EndProgramStmt.class));
		assertTrue(FortranGrammarTester.valid("END PROGRAM",
				EndProgramStmt.class));
		assertTrue(FortranGrammarTester.valid("END PROGRAM Test",
				EndProgramStmt.class));
	}

}
