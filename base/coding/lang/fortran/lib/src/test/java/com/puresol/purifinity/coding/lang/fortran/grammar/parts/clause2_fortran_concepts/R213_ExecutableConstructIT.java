package com.puresol.purifinity.coding.lang.fortran.grammar.parts.clause2_fortran_concepts;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.purifinity.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R213_ExecutableConstructIT {

    @Test
    public void testProgram4() throws Exception {
	assertTrue(FortranGrammarPartTester
		.test("executable-construct",
			"        IF ((M .EQ. 0) .OR. (N .EQ. 0) .OR. (ALPHA .EQ. ZERO)) RETURN\n"));
    }
}
