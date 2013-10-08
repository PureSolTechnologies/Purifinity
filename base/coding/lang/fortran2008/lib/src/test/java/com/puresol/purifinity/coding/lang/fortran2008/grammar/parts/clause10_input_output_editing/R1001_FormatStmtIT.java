package com.puresol.purifinity.coding.lang.fortran2008.grammar.parts.clause10_input_output_editing;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.purifinity.coding.lang.fortran2008.grammar.FortranGrammarPartTester;

public class R1001_FormatStmtIT {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("format-stmt", "FORMAT()\n"));
    }

    public void test2() throws Exception {
	assertTrue(FortranGrammarPartTester.test("format-stmt",
		"FORMAT (1PE12.4, I10)\n"));
    }

    public void test3() throws Exception {
	assertTrue(FortranGrammarPartTester.test("format-stmt",
		"FORMAT (I12, /, ' Dates: ', 2 (2I3, I5))\n"));
    }

    public void test4() throws Exception {
	assertTrue(FortranGrammarPartTester.test("format-stmt",
		"FORMAT (1X, I1, 1X, 'ISN''T', 1X, I1)\n"));
    }
}
