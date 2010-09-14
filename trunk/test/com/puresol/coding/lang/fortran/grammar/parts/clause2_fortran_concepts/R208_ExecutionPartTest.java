package com.puresol.coding.lang.fortran.grammar.parts.clause2_fortran_concepts;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class R208_ExecutionPartTest extends TestCase {

	@Test
	public void testSubroutineSubprogram() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("execution-part",
				"IF( N.GT.0 ) THEN\n"
						+ "CALL ATL_F77WRAP_SCOPY( N, X, INCX, Y, INCY )\n"
						+ "END IF\n" + "RETURN\n"
						+ "write (*,*) 'This isn''t a Test!'\n"));
	}

	@Test
	public void testSubroutineSubprogram2() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("execution-part", "INFO = 0\n"
				+ "IF (M.LT.0) THEN\n" + "    INFO = 1\n"
				+ "ELSE IF (N.LT.0) THEN\n" + "    INFO = 2\n"
				+ "ELSE IF (INCX.EQ.0) THEN\n" + "    INFO = 5\n"
				+ "ELSE IF (INCY.EQ.0) THEN\n" + "    INFO = 7\n"
				+ "ELSE IF (LDA.LT.MAX(1,M)) THEN\n" + "    INFO = 9\n"
				+ "END IF\n"));
	}

	@Test
	public void testSubroutineSubprogram3() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("execution-part", "INFO = 0\n"
				+ "IF (M.LT.0) THEN\n" + "    INFO = 1\n"
				+ "ELSE IF (N.LT.0) THEN\n" + "    INFO = 2\n"
				+ "ELSE IF (INCX.EQ.0) THEN\n" + "    INFO = 5\n"
				+ "ELSE IF (INCY.EQ.0) THEN\n" + "    INFO = 7\n"
				+ "ELSE IF (LDA.LT.MAX(1,M)) THEN\n" + "    INFO = 9\n"
				+ "END IF\n" + "IF (INFO.NE.0) THEN\n"
				+ "    CALL XERBLA('ZGERC ',INFO)\n" + "    RETURN\n"
				+ "END IF\n"
				+ "IF ((M.EQ.0) .OR. (N.EQ.0) .OR. (ALPHA.EQ.ZERO)) RETURN\n"
				+ "IF (INCY.GT.0) THEN\n" + "    JY = 1\n" + "ELSE\n"
				+ "    JY = 1 - (N-1)*INCY\n" + "END IF\n"
				+ "IF (INCX.EQ.1) THEN\n" + "    DO 20 J = 1,N\n"
				+ "        IF (Y(JY).NE.ZERO) THEN\n"
				+ "            TEMP = ALPHA*DCONJG(Y(JY))\n"
				+ "            DO 10 I = 1,M\n"
				+ "                A(I,J) = A(I,J) + X(I)*TEMP\n"
				+ "            CONTINUE\n" + "        END IF\n"
				+ "        JY = JY + INCY\n" + "    CONTINUE\n" + "ELSE\n"
				+ "    IF (INCX.GT.0) THEN\n" + "        KX = 1\n"
				+ "    ELSE\n" + "        KX = 1 - (M-1)*INCX\n"
				+ "    END IF\n" + "    DO 40 J = 1,N\n"
				+ "        IF (Y(JY).NE.ZERO) THEN\n"
				+ "            TEMP = ALPHA*DCONJG(Y(JY))\n"
				+ "            IX = KX\n" + "            DO 30 I = 1,M\n"
				+ "                A(I,J) = A(I,J) + X(IX)*TEMP\n"
				+ "                IX = IX + INCX\n" + "            CONTINUE\n"
				+ "        END IF\n" + "        JY = JY + INCY\n"
				+ "    CONTINUE\n" + "END IF\n" + "RETURN\n"));
	}
}
