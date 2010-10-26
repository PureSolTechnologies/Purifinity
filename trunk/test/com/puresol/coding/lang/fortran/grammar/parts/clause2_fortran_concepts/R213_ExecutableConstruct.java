package com.puresol.coding.lang.fortran.grammar.parts.clause2_fortran_concepts;

import static org.junit.Assert.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class R213_ExecutableConstruct {

	@Test
	public void testProgram4() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester
				.test("executable-construct",
						"        IF ((M .EQ. 0) .OR. (N .EQ. 0) .OR. (ALPHA .EQ. ZERO)) RETURN\n"));
	}
}
