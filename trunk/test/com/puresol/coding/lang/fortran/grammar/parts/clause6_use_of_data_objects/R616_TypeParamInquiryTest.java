package com.puresol.coding.lang.fortran.grammar.parts.clause6_use_of_data_objects;

import junit.framework.TestCase;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R616_TypeParamInquiryTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("type-param-inquiry", "X%TypeParamName")); // type-param-inquiry
	}
}
