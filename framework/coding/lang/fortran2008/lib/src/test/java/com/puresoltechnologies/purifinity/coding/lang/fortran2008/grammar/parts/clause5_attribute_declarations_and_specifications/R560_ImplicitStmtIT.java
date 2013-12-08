package com.puresoltechnologies.purifinity.coding.lang.fortran2008.grammar.parts.clause5_attribute_declarations_and_specifications;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.coding.lang.fortran2008.grammar.FortranGrammarPartTester;

public class R560_ImplicitStmtIT {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("implicit-stmt",
		"      implicit none\n"));
    }

    @Test
    public void test2() throws Exception {
	assertTrue(FortranGrammarPartTester.test("implicit-stmt",
		"      implicit REAL(A-Z)\n"));
    }

}
