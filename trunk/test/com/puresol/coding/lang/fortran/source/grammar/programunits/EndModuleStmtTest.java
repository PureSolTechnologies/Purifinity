package com.puresol.coding.lang.fortran.source.grammar.programunits;

import org.junit.Test;

import com.puresol.coding.lang.fortran.source.grammar.FortranGrammarTester;

import junit.framework.TestCase;

public class EndModuleStmtTest extends TestCase {

	@Test
	public void test() {
		assertTrue(FortranGrammarTester.valid("END", EndModuleStmt.class));
		assertTrue(FortranGrammarTester
				.valid("END MODULE", EndModuleStmt.class));
		assertTrue(FortranGrammarTester.valid("END MODULE Test",
				EndModuleStmt.class));
	}

}
