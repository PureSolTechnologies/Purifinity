package com.puresol.coding.lang.fortran.grammar.parts.clause5_attribute_declarations_and_specifications;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class R526_AllocatableStmtTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("allocatable-stmt",
				"ALLOCATABLE :: A (:, :), B, SCALAR"));
	}

}
