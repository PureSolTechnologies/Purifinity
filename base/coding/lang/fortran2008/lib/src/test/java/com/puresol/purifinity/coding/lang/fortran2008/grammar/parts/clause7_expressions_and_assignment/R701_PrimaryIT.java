package com.puresol.purifinity.coding.lang.fortran2008.grammar.parts.clause7_expressions_and_assignment;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.purifinity.coding.lang.fortran2008.grammar.FortranGrammarPartTester;

public class R701_PrimaryIT {

    @Test
    public void testSimple() throws Exception {
	assertTrue(FortranGrammarPartTester.test("primary", "a(33)"));
    }

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("primary", "VARIABLE"));
    }

    @Test
    public void test2() throws Exception {
	assertTrue(FortranGrammarPartTester.test("primary", "(x)"));
    }

    @Test
    public void test3() throws Exception {
	assertTrue(FortranGrammarPartTester.test("primary", "x"));
    }

    @Test
    public void test4() throws Exception {
	assertTrue(FortranGrammarPartTester.test("primary", "1.0")); // constant
    }

    @Test
    public void test5() throws Exception {
	assertTrue(FortranGrammarPartTester.test("primary",
		"'ABCDEFGHIJKLMNOPQRSTUVWXYZ' (I:I)")); // designator
    }

    @Test
    public void test6() throws Exception {
	assertTrue(FortranGrammarPartTester.test("primary", "[ 1.0, 2.0 ]")); // array-constructor
    }

    @Test
    public void test7() throws Exception {
	assertTrue(FortranGrammarPartTester.test("primary",
		"PERSON (12, 'Jones')")); // structure-constructor
    }

    @Test
    public void test8() throws Exception {
	assertTrue(FortranGrammarPartTester.test("primary", "F (X, Y)")); // function-reference
    }

    @Test
    public void test9() throws Exception {
	assertTrue(FortranGrammarPartTester.test("primary", "X%TypeParamName")); // type-param-inquiry
    }

    @Test
    public void test10() throws Exception {
	assertTrue(FortranGrammarPartTester.test("primary", "TypeParamName")); // type-param-name
    }

    @Test
    public void test11() throws Exception {
	assertTrue(FortranGrammarPartTester.test("primary", "(S + T)")); // (expr)
    }
}
