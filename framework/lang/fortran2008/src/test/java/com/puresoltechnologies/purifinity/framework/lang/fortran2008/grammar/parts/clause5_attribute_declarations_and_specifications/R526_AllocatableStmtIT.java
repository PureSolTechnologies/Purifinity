package com.puresoltechnologies.purifinity.framework.lang.fortran2008.grammar.parts.clause5_attribute_declarations_and_specifications;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.fortran2008.grammar.FortranGrammarPartTester;

public class R526_AllocatableStmtIT {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("allocatable-stmt",
		"      ALLOCATABLE :: A (:, :), B, SCALAR\n"));
    }

}
