package com.puresol.coding.lang.fortran.grammar.parts;

import junit.framework.TestCase;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class TypeParamInquiryTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("type-param-inquiry", "X%TypeParamName")); // type-param-inquiry
	}
}
