package com.puresoltechnologies.purifinity.server.plugin.fortran2008.grammar.parts.clause7_expressions_and_assignment;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.server.plugin.fortran2008.grammar.FortranGrammarPartTester;

public class R717_Level5ExprIT {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("level-5-expr", "A.EQV.B"));
    }

    @Test
    public void test2() throws Exception {
	assertTrue(FortranGrammarPartTester.test("level-5-expr", "A.NEQV.B"));
    }

}
