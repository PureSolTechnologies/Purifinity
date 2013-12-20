package com.puresoltechnologies.purifinity.framework.lang.fortran2008.grammar.parts.clause12_procedures;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.fortran2008.grammar.FortranGrammarPartTester;

public class R1220_CallStmtIT {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("call-stmt",
		"      CALL ATL_F77WRAP_SCOPY\n"));
    }

    @Test
    public void test2() throws Exception {
	assertTrue(FortranGrammarPartTester.test("call-stmt",
		"      CALL ATL_F77WRAP_SCOPY()\n"));
    }

    @Test
    public void test3() throws Exception {
	assertTrue(FortranGrammarPartTester.test("call-stmt",
		"      CALL ATL_F77WRAP_SCOPY( A )\n"));
    }

    @Test
    public void test4() throws Exception {
	assertTrue(FortranGrammarPartTester.test("call-stmt",
		"      CALL ATL_F77WRAP_SCOPY( A, B )\n"));
    }

    @Test
    public void test5() throws Exception {
	assertTrue(FortranGrammarPartTester.test("call-stmt",
		"      CALL ATL_F77WRAP_SCOPY( A, B, C )\n"));
    }

    @Test
    public void test6() throws Exception {
	assertTrue(FortranGrammarPartTester.test("call-stmt",
		"      CALL ATL_F77WRAP_SCOPY( N, X, INCX, Y, INCY )\n"));
    }
}
