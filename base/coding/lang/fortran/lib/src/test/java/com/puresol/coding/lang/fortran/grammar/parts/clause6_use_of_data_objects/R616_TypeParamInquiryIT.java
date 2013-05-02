package com.puresol.coding.lang.fortran.grammar.parts.clause6_use_of_data_objects;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R616_TypeParamInquiryIT {

    @Test
    public void test() throws Exception {
	assertTrue(FortranGrammarPartTester.test("primary", "X%TypeParamName")); // type-param-inquiry
    }
}
