package com.puresol.coding.lang.fortran.source.grammar.programunits;

import org.junit.Test;

import com.puresol.coding.lang.fortran.source.grammar.FortranGrammarTester;

import junit.framework.TestCase;

public class ModuleStmtTest extends TestCase {
	@Test
	public void test() {
		assertTrue(FortranGrammarTester.valid("MODULE Test", ModuleStmt.class));
		assertTrue(FortranGrammarTester.invalid("MODULE", ModuleStmt.class));
	}
}
