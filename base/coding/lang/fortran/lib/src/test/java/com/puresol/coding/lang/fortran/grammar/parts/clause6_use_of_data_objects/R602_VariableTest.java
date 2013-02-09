package com.puresol.coding.lang.fortran.grammar.parts.clause6_use_of_data_objects;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R602_VariableTest {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("variable", "IF"));
    }

    @Test
    public void test2() throws Exception {
	assertTrue(FortranGrammarPartTester.test("variable", "DO"));
    }

    @Test
    public void test3() throws Exception {
	assertTrue(FortranGrammarPartTester.test("variable", "WHILE"));
    }

    @Test
    public void test4() throws Exception {
	assertTrue(FortranGrammarPartTester.test("variable", "THEN"));
    }

    @Test
    public void test5() throws Exception {
	assertTrue(FortranGrammarPartTester.test("variable", "ELSE"));
    }
}
