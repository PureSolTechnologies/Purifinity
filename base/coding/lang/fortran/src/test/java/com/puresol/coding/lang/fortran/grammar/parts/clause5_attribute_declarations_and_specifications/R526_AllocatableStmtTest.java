package com.puresol.coding.lang.fortran.grammar.parts.clause5_attribute_declarations_and_specifications;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R526_AllocatableStmtTest {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("allocatable-stmt",
		"      ALLOCATABLE :: A (:, :), B, SCALAR\n"));
    }

}
