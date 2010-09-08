package com.puresol.coding.lang.fortran.grammar.parts;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class DerivedTypeDefTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("derived-type-def", "TYPE PERSON\n"
				+ "INTEGER AGE\n" + "CHARACTER (LEN = 50) NAME\n"
				+ "END TYPE PERSON"));
	}

}
