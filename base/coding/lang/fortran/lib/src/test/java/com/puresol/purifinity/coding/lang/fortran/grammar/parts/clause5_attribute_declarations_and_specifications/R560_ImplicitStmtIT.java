package com.puresol.purifinity.coding.lang.fortran.grammar.parts.clause5_attribute_declarations_and_specifications;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.purifinity.coding.lang.fortran.grammar.FortranGrammarPartTester;

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