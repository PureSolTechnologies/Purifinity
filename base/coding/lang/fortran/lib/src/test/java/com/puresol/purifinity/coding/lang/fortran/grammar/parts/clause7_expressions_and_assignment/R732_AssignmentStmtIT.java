package com.puresol.purifinity.coding.lang.fortran.grammar.parts.clause7_expressions_and_assignment;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.purifinity.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R732_AssignmentStmtIT {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("assignment-stmt",
		"      IF=1\n"));
    }

    @Test
    public void test2() throws Exception {
	assertTrue(FortranGrammarPartTester.test("assignment-stmt",
		"      A = 3.5 + X * Y\n"));
    }

    @Test
    public void test3() throws Exception {
	assertTrue(FortranGrammarPartTester.test("assignment-stmt",
		"      I = INT (A)\n"));
    }

    @Test
    public void test4() throws Exception {
	assertTrue(FortranGrammarPartTester.test("assignment-stmt",
		"      NAME = 'Dr. '//FIRST_NAME//' '//SURNAME\n"));
    }

    @Test
    public void test5() throws Exception {
	assertTrue(FortranGrammarPartTester.test("assignment-stmt",
		"      NAME(:) = 'Dr. '//FIRST_NAME//' '//SURNAME\n"));
    }

    @Test
    public void test6() throws Exception {
	assertTrue(FortranGrammarPartTester.test("assignment-stmt",
		"      A(I, J) = REAL (I + J - 2)\n"));
    }

    @Test
    public void test7() throws Exception {
	assertTrue(FortranGrammarPartTester.test("assignment-stmt",
		"      B(I, J) = A(I, J) + B(I, J) * REAL (I * J)\n"));
    }

    @Test
    public void test8() throws Exception {
	assertTrue(FortranGrammarPartTester.test("assignment-stmt",
		"      XX=(2.-EVA12)/EVA12\n"));
    }

    @Test
    public void test9() throws Exception {
	assertTrue(FortranGrammarPartTester.test("assignment-stmt",
		"      A = 1 * 2 + 3 * 4 + 5 * 6\n"));
    }

    @Test
    public void test10() throws Exception {
	assertTrue(FortranGrammarPartTester.test("assignment-stmt",
		"      HS = RH01 * SA0(I) + RH01 * SA0(I) + RH01 * SA0(I)\n"));
    }

    @Test
    public void test11() throws Exception {
	assertTrue(FortranGrammarPartTester.test("assignment-stmt",
		"      HS = RHO1 * SA0(I) + RHO2 * QA0(I) + RHO3 * QN0(I)\n"));
    }
}
