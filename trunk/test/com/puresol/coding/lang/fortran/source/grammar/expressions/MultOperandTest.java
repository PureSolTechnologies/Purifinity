package com.puresol.coding.lang.fortran.source.grammar.expressions;

import org.junit.Test;

import com.puresol.coding.lang.fortran.source.grammar.FortranGrammarTester;

import junit.framework.TestCase;

public class MultOperandTest extends TestCase {

	@Test
	public void test() {
		assertTrue(FortranGrammarTester.valid("a**b", MultOperand.class));
	}

}
