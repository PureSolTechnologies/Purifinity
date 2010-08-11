package com.puresol.coding.lang.fortran.source.grammar.dataobjects;

import org.junit.Test;

import com.puresol.coding.lang.fortran.source.grammar.FortranGrammarTester;

import junit.framework.TestCase;

public class DesignatorTest extends TestCase {

	@Test
	public void test() {
		assertTrue(FortranGrammarTester.valid("VariableName", Designator.class));
		assertTrue(FortranGrammarTester.valid("Array(1)", Designator.class));
	}

}
