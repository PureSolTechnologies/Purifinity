package com.puresol.coding.lang.fortran.grammar.parts.clause2_fortran_concepts;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R201_ProgramIT {

    @Test
    public void testProgram() throws Exception {
	assertTrue(FortranGrammarPartTester.test("program", "end program\n"));
    }

    @Test
    public void testProgram2() throws Exception {
	assertTrue(FortranGrammarPartTester.test("program",//
		"PROGRAM TEST\n",//
		"*      COMMENT\n",//
		"END PROGRAM\n"));
    }

    @Test
    public void testProgram3() throws Exception {
	assertTrue(FortranGrammarPartTester.test("program", //
		"PROGRAM TEST\n",//
		"*      COMMENT\n",//
		"      IMPLICIT NONE\n",//
		"END PROGRAM\n"));
    }

    @Test
    public void testProgram4() throws Exception {
	assertTrue(FortranGrammarPartTester
		.test("program",//
			"PROGRAM TEST\n",//
			"*     COMMENT\n",//
			"      IMPLICIT NONE\n",//
			"      DOUBLE COMPLEX ALPHA\n",//
			"      INTEGER INCX,INCY,LDA,M,N\n",//
			"      DOUBLE COMPLEX A(LDA,*),X(*),Y(*)\n",//
			"      DOUBLE COMPLEX ZERO\n",//
			"      PARAMETER (ZERO= (0.0D+0,0.0D+0))\n",//
			"      DOUBLE COMPLEX TEMP\n",//
			"      INTEGER I,INFO,IX,J,JY,KX\n",//
			"      EXTERNAL XERBLA\n",//
			"      INTRINSIC DCONJG,MAX\n",//
			"      INFO = 0\n",//
			"      IF (M.LT.0) THEN\n",//
			"          INFO = 1\n",//
			"      ELSE IF (N.LT.0) THEN\n",//
			"          INFO = 2\n",//
			"      ELSE IF (INCX.EQ.0) THEN\n",//
			"          INFO = 5\n",//
			"      ELSE IF (INCY.EQ.0) THEN\n",//
			"          INFO = 7\n",//
			"      ELSE IF (LDA.LT.MAX(1,M)) THEN\n",//
			"          INFO = 9\n",//
			"      END IF\n",//
			"      IF (INFO.NE.0) THEN\n",//
			"          CALL XERBLA('ZGERC ',INFO)\n",//
			"          RETURN\n",//
			"      END IF\n",//
			"        IF ((M .EQ. 0) .OR. (N .EQ. 0) .OR. (ALPHA .EQ. ZERO)) RETURN\n",//
			"      IF (INCY.GT.0) THEN\n",//
			"          JY = 1\n",//
			"      ELSE\n",//
			"         JY = 1 - (N-1)*INCY\n",//
			"      END IF\n",//
			"      IF (INCX.EQ.1) THEN\n",//
			"          DO 20 J = 1,N\n",//
			"            IF (Y(JY).NE.ZERO) THEN\n",//
			"              TEMP = ALPHA*DCONJG(Y(JY))\n",//
			"              DO 10 I = 1,M\n",//
			"                A(I,J) = A(I,J) + X(I)*TEMP\n",//
			" 10           CONTINUE\n",//
			"            END IF\n",//
			"            JY = JY + INCY\n",//
			" 20     CONTINUE\n",//
			"      ELSE\n",//
			"      IF (INCX.GT.0) THEN\n",//
			"      KX = 1\n",//
			"      ELSE\n",//
			"      KX = 1 - (M-1)*INCX\n",//
			"      END IF\n",//
			"      DO 40 J = 1,N\n",//
			"      IF (Y(JY).NE.ZERO) THEN\n",// ,//
			"      TEMP = ALPHA*DCONJG(Y(JY))\n",//
			"      IX = KX\n",//
			"      DO 30 I = 1,M\n",//
			"      A(I,J) = A(I,J) + X(IX)*TEMP\n",//
			"      IX = IX + INCX\n",//
			" 30             CONTINUE\n",//
			"      END IF\n",//
			"      JY = JY + INCY\n",//
			" 40     CONTINUE\n",//
			"      END IF\n",//
			"      RETURN\n",//
			"      END\n",//
			"      END PROGRAM\n"));
    }

    @Test
    public void testSubroutine() throws Exception {
	assertTrue(FortranGrammarPartTester.test("program",//
		"SUBROUTINE ZGERC(M,N,ALPHA,X,INCX,Y,INCY,A,LDA)\n",//
		"END SUBROUTINE\n"));
    }

    @Test
    public void testSubroutine2() throws Exception {
	assertTrue(FortranGrammarPartTester.test("program",
		"SUBROUTINE ZGERC(M,N,ALPHA,X,INCX,Y,INCY,A,LDA)\n",//
		"DOUBLE COMPLEX DC\n",//
		"END SUBROUTINE\n"));
    }
}
