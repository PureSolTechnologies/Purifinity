package com.puresol.coding.lang.fortran.grammar.parts.clause8_execution_control;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class R832_IfConstructTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("if-construct",
				"      IF (A > 0.0) THEN          \n"
						+ "          B = SQRT (A) \n"
						+ "          C = LOG (A)  \n"
						+ "      END IF           \n"));
	}

	@Test
	public void test2() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("if-construct",
				"      IF (M.LT.0) THEN             \n"
						+ "          INFO = 9       \n"
						+ "      END IF             \n"));
	}

	@Test
	public void test3() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("if-construct",
				"      IF (M.LT.0) THEN                \n"
						+ "          INFO = 1          \n"
						+ "      ELSE IF (N.LT.0) THEN \n"
						+ "          INFO = 9          \n"
						+ "      END IF                \n"));
	}

	@Test
	public void test4() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("if-construct",
				"      IF (M.LT.0) THEN                        \n"
						+ "          INFO = 1                  \n"
						+ "      ELSE IF (N.LT.0) THEN         \n"
						+ "          INFO = 2                  \n"
						+ "      ELSE IF (INCX.EQ.0) THEN      \n"
						+ "          INFO = 5                  \n"
						+ "      ELSE IF (INCY.EQ.0) THEN      \n"
						+ "          INFO = 7                  \n"
						+ "      ELSE IF (LDA.LT.MAX(1,M)) THEN\n"
						+ "          INFO = 9                  \n"
						+ "      ELSE                          \n"
						+ "          INFO = 10                 \n"
						+ "      END IF                        \n"));
	}
}
