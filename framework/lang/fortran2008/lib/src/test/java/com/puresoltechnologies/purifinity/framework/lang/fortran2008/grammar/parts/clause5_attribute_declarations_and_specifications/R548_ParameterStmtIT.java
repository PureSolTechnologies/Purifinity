package com.puresoltechnologies.purifinity.framework.lang.fortran2008.grammar.parts.clause5_attribute_declarations_and_specifications;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.fortran2008.grammar.FortranGrammarPartTester;

public class R548_ParameterStmtIT {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("parameter-stmt",
		"PARAMETER(INOM2 =  2*INOM)\n"));
    }

}
