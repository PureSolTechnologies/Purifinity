package com.puresol.coding.lang.fortran.grammar.parts.clause5_attribute_declarations_and_specifications;

import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R562_LetterSpecTest extends TestCase {

	@Test
	public void test() {
		assertTrue(FortranGrammarPartTester.test("letter-spec", "A-Z"));
	}

}
