package com.puresoltechnologies.purifinity.framework.lang.fortran2008.grammar.parts.clause4_types;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.lang.fortran2008.grammar.FortranGrammarPartTester;

public class R419_ImagPartIT {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("imag-part", "+1"));
    }

    @Test
    public void test2() throws Exception {
	assertTrue(FortranGrammarPartTester.test("imag-part", "0"));
    }

    @Test
    public void test3() throws Exception {
	assertTrue(FortranGrammarPartTester.test("imag-part", "-1"));
    }

    @Test
    public void test4() throws Exception {
	assertTrue(FortranGrammarPartTester.test("imag-part", "+1.0"));
    }

    @Test
    public void test5() throws Exception {
	assertTrue(FortranGrammarPartTester.test("imag-part", "0.0"));
    }

    @Test
    public void test6() throws Exception {
	assertTrue(FortranGrammarPartTester.test("imag-part", "-1.0"));
    }

    @Test
    public void test7() throws Exception {
	assertTrue(FortranGrammarPartTester.test("imag-part", "variable"));
    }

}
