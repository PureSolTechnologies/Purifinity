package com.puresol.coding.lang.fortran.grammar.parts.clause10_input_output_editing;

import static org.junit.Assert.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R1001_FormatStmtTest {

	@Test
	public void testEmptyMainProgram() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("format-stmt", "FORMAT()\n"));
		assertTrue(FortranGrammarPartTester.test("format-stmt",
				"FORMAT (1PE12.4, I10)\n"));
		assertTrue(FortranGrammarPartTester.test("format-stmt",
				"FORMAT (I12, /, ' Dates: ', 2 (2I3, I5))\n"));
		assertTrue(FortranGrammarPartTester.test("format-stmt",
				"FORMAT (1X, I1, 1X, 'ISN''T', 1X, I1)\n"));
	}
}
