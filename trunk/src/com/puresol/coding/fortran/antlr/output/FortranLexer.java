/***************************************************************************
 *
 *   FortranLexer.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.fortran.antlr.output;

import org.antlr.runtime.*;

public class FortranLexer extends Lexer {
    public static final int FUNCTION = 21;
    public static final int LOR = 64;
    public static final int EXTERNAL = 49;
    public static final int LT = 71;
    public static final int EXPONENT = 83;
    public static final int STAR = 61;
    public static final int WHILE = 42;
    public static final int ALLOCATE = 35;
    public static final int OCTAL_ESC = 91;
    public static final int DO = 40;
    public static final int COMPLEX = 29;
    public static final int EOF = -1;
    public static final int CHARACTER = 31;
    public static final int RPAREN = 54;
    public static final int LNOT = 62;
    public static final int HEX_CONST = 82;
    public static final int STRING_CONST = 89;
    public static final int PARAMETER = 36;
    public static final int EOR = 70;
    public static final int RETURN = 47;
    public static final int GOTO = 46;
    public static final int EQ = 76;
    public static final int COMMENT = 86;
    public static final int NE = 75;
    public static final int D = 25;
    public static final int E = 17;
    public static final int F = 19;
    public static final int GE = 74;
    public static final int G = 7;
    public static final int A = 8;
    public static final int B = 13;
    public static final int C = 20;
    public static final int L = 23;
    public static final int M = 9;
    public static final int N = 16;
    public static final int O = 6;
    public static final int H = 30;
    public static final int I = 15;
    public static final int J = 92;
    public static final int ELSE = 39;
    public static final int K = 93;
    public static final int U = 12;
    public static final int T = 14;
    public static final int W = 41;
    public static final int POWER = 60;
    public static final int V = 66;
    public static final int NEQV = 68;
    public static final int Q = 65;
    public static final int P = 4;
    public static final int S = 11;
    public static final int R = 5;
    public static final int Y = 94;
    public static final int X = 28;
    public static final int Z = 95;
    public static final int REAL = 24;
    public static final int WS = 26;
    public static final int NONE = 34;
    public static final int SUBROUTINE = 18;
    public static final int GT = 73;
    public static final int INTRINSIC = 50;
    public static final int CALL = 45;
    public static final int END = 44;
    public static final int FALSE = 78;
    public static final int LAND = 63;
    public static final int DOLLAR = 51;
    public static final int ID = 79;
    public static final int LPAREN = 53;
    public static final int FLOAT_CONST = 84;
    public static final int IF = 37;
    public static final int LINEFEED = 85;
    public static final int ESC_SEQ = 88;
    public static final int THEN = 38;
    public static final int CONTINUE = 48;
    public static final int COMMA = 52;
    public static final int DOUBLE_PRECISION = 27;
    public static final int PLUS = 58;
    public static final int ENDDO = 43;
    public static final int INTEGER = 22;
    public static final int XOR = 69;
    public static final int IMPLICIT = 33;
    public static final int UNICODE_ESC = 90;
    public static final int INT_CONST = 80;
    public static final int HEX_DIGIT = 81;
    public static final int MINUS = 57;
    public static final int TRUE = 77;
    public static final int NOTNL = 87;
    public static final int COLON = 55;
    public static final int ASSIGN = 56;
    public static final int PROGRAM = 10;
    public static final int EQV = 67;
    public static final int LOGICAL = 32;
    public static final int DIV = 59;
    public static final int LE = 72;

    // delegates
    // delegators

    public FortranLexer() {
	;
    }

    public FortranLexer(CharStream input) {
	this(input, new RecognizerSharedState());
    }

    public FortranLexer(CharStream input, RecognizerSharedState state) {
	super(input, state);

    }

    public String getGrammarFileName() {
	return "/home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g";
    }

    // $ANTLR start "PROGRAM"
    public final void mPROGRAM() throws RecognitionException {
	try {
	    int _type = PROGRAM;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:11:9:
	    // ( P R O G R A M )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:11:11:
	    // P R O G R A M
	    {
		mP();
		mR();
		mO();
		mG();
		mR();
		mA();
		mM();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "PROGRAM"

    // $ANTLR start "SUBROUTINE"
    public final void mSUBROUTINE() throws RecognitionException {
	try {
	    int _type = SUBROUTINE;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:13:2:
	    // ( S U B R O U T I N E )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:13:4:
	    // S U B R O U T I N E
	    {
		mS();
		mU();
		mB();
		mR();
		mO();
		mU();
		mT();
		mI();
		mN();
		mE();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "SUBROUTINE"

    // $ANTLR start "FUNCTION"
    public final void mFUNCTION() throws RecognitionException {
	try {
	    int _type = FUNCTION;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:14:9:
	    // ( F U N C T I O N )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:14:11:
	    // F U N C T I O N
	    {
		mF();
		mU();
		mN();
		mC();
		mT();
		mI();
		mO();
		mN();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "FUNCTION"

    // $ANTLR start "INTEGER"
    public final void mINTEGER() throws RecognitionException {
	try {
	    int _type = INTEGER;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:16:2:
	    // ( I N T E G E R )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:16:4:
	    // I N T E G E R
	    {
		mI();
		mN();
		mT();
		mE();
		mG();
		mE();
		mR();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "INTEGER"

    // $ANTLR start "REAL"
    public final void mREAL() throws RecognitionException {
	try {
	    int _type = REAL;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:18:2:
	    // ( R E A L )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:18:4:
	    // R E A L
	    {
		mR();
		mE();
		mA();
		mL();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "REAL"

    // $ANTLR start "DOUBLE_PRECISION"
    public final void mDOUBLE_PRECISION() throws RecognitionException {
	try {
	    int _type = DOUBLE_PRECISION;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:20:2:
	    // ( D O U B L E ( WS )+ P R E C I S I O N )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:20:4:
	    // D O U B L E ( WS )+ P R E C I S I O N
	    {
		mD();
		mO();
		mU();
		mB();
		mL();
		mE();
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:20:16:
		// ( WS )+
		int cnt1 = 0;
		loop1: do {
		    int alt1 = 2;
		    int LA1_0 = input.LA(1);

		    if (((LA1_0 >= '\t' && LA1_0 <= '\n') || LA1_0 == '\r' || LA1_0 == ' ')) {
			alt1 = 1;
		    }

		    switch (alt1) {
		    case 1:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:20:16:
			// WS
		    {
			mWS();

		    }
			break;

		    default:
			if (cnt1 >= 1)
			    break loop1;
			EarlyExitException eee =
				new EarlyExitException(1, input);
			throw eee;
		    }
		    cnt1++;
		} while (true);

		mP();
		mR();
		mE();
		mC();
		mI();
		mS();
		mI();
		mO();
		mN();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "DOUBLE_PRECISION"

    // $ANTLR start "COMPLEX"
    public final void mCOMPLEX() throws RecognitionException {
	try {
	    int _type = COMPLEX;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:21:9:
	    // ( C O M P L E X )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:21:11:
	    // C O M P L E X
	    {
		mC();
		mO();
		mM();
		mP();
		mL();
		mE();
		mX();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "COMPLEX"

    // $ANTLR start "CHARACTER"
    public final void mCHARACTER() throws RecognitionException {
	try {
	    int _type = CHARACTER;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:23:2:
	    // ( C H A R A C T E R )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:23:4:
	    // C H A R A C T E R
	    {
		mC();
		mH();
		mA();
		mR();
		mA();
		mC();
		mT();
		mE();
		mR();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "CHARACTER"

    // $ANTLR start "LOGICAL"
    public final void mLOGICAL() throws RecognitionException {
	try {
	    int _type = LOGICAL;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:24:9:
	    // ( L O G I C A L )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:24:11:
	    // L O G I C A L
	    {
		mL();
		mO();
		mG();
		mI();
		mC();
		mA();
		mL();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "LOGICAL"

    // $ANTLR start "IMPLICIT"
    public final void mIMPLICIT() throws RecognitionException {
	try {
	    int _type = IMPLICIT;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:25:9:
	    // ( I M P L I C I T )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:25:11:
	    // I M P L I C I T
	    {
		mI();
		mM();
		mP();
		mL();
		mI();
		mC();
		mI();
		mT();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "IMPLICIT"

    // $ANTLR start "NONE"
    public final void mNONE() throws RecognitionException {
	try {
	    int _type = NONE;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:26:6:
	    // ( N O N E )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:26:8:
	    // N O N E
	    {
		mN();
		mO();
		mN();
		mE();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "NONE"

    // $ANTLR start "ALLOCATE"
    public final void mALLOCATE() throws RecognitionException {
	try {
	    int _type = ALLOCATE;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:27:9:
	    // ( A L L O C A T E )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:27:11:
	    // A L L O C A T E
	    {
		mA();
		mL();
		mL();
		mO();
		mC();
		mA();
		mT();
		mE();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "ALLOCATE"

    // $ANTLR start "PARAMETER"
    public final void mPARAMETER() throws RecognitionException {
	try {
	    int _type = PARAMETER;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:29:2:
	    // ( P A R A M E T E R )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:29:4:
	    // P A R A M E T E R
	    {
		mP();
		mA();
		mR();
		mA();
		mM();
		mE();
		mT();
		mE();
		mR();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "PARAMETER"

    // $ANTLR start "IF"
    public final void mIF() throws RecognitionException {
	try {
	    int _type = IF;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:31:2:
	    // ( I F )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:31:4:
	    // I F
	    {
		mI();
		mF();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "IF"

    // $ANTLR start "THEN"
    public final void mTHEN() throws RecognitionException {
	try {
	    int _type = THEN;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:33:2:
	    // ( T H E N )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:33:4:
	    // T H E N
	    {
		mT();
		mH();
		mE();
		mN();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "THEN"

    // $ANTLR start "ELSE"
    public final void mELSE() throws RecognitionException {
	try {
	    int _type = ELSE;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:34:6:
	    // ( E L S E )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:34:8:
	    // E L S E
	    {
		mE();
		mL();
		mS();
		mE();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "ELSE"

    // $ANTLR start "DO"
    public final void mDO() throws RecognitionException {
	try {
	    int _type = DO;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:35:4:
	    // ( D O )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:35:6:
	    // D O
	    {
		mD();
		mO();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "DO"

    // $ANTLR start "WHILE"
    public final void mWHILE() throws RecognitionException {
	try {
	    int _type = WHILE;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:36:7:
	    // ( W H I L E )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:36:9:
	    // W H I L E
	    {
		mW();
		mH();
		mI();
		mL();
		mE();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "WHILE"

    // $ANTLR start "ENDDO"
    public final void mENDDO() throws RecognitionException {
	try {
	    int _type = ENDDO;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:37:7:
	    // ( E N D D O )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:37:9:
	    // E N D D O
	    {
		mE();
		mN();
		mD();
		mD();
		mO();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "ENDDO"

    // $ANTLR start "END"
    public final void mEND() throws RecognitionException {
	try {
	    int _type = END;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:38:6:
	    // ( E N D )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:38:8:
	    // E N D
	    {
		mE();
		mN();
		mD();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "END"

    // $ANTLR start "CALL"
    public final void mCALL() throws RecognitionException {
	try {
	    int _type = CALL;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:39:7:
	    // ( C A L L )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:39:9:
	    // C A L L
	    {
		mC();
		mA();
		mL();
		mL();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "CALL"

    // $ANTLR start "GOTO"
    public final void mGOTO() throws RecognitionException {
	try {
	    int _type = GOTO;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:40:6:
	    // ( G O ( WS )+ T O )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:40:8:
	    // G O ( WS )+ T O
	    {
		mG();
		mO();
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:40:12:
		// ( WS )+
		int cnt2 = 0;
		loop2: do {
		    int alt2 = 2;
		    int LA2_0 = input.LA(1);

		    if (((LA2_0 >= '\t' && LA2_0 <= '\n') || LA2_0 == '\r' || LA2_0 == ' ')) {
			alt2 = 1;
		    }

		    switch (alt2) {
		    case 1:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:40:12:
			// WS
		    {
			mWS();

		    }
			break;

		    default:
			if (cnt2 >= 1)
			    break loop2;
			EarlyExitException eee =
				new EarlyExitException(2, input);
			throw eee;
		    }
		    cnt2++;
		} while (true);

		mT();
		mO();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "GOTO"

    // $ANTLR start "RETURN"
    public final void mRETURN() throws RecognitionException {
	try {
	    int _type = RETURN;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:41:8:
	    // ( R E T U R N )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:41:10:
	    // R E T U R N
	    {
		mR();
		mE();
		mT();
		mU();
		mR();
		mN();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "RETURN"

    // $ANTLR start "CONTINUE"
    public final void mCONTINUE() throws RecognitionException {
	try {
	    int _type = CONTINUE;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:42:9:
	    // ( C O N T I N U E )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:42:11:
	    // C O N T I N U E
	    {
		mC();
		mO();
		mN();
		mT();
		mI();
		mN();
		mU();
		mE();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "CONTINUE"

    // $ANTLR start "EXTERNAL"
    public final void mEXTERNAL() throws RecognitionException {
	try {
	    int _type = EXTERNAL;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:43:9:
	    // ( E X T E R N A L )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:43:11:
	    // E X T E R N A L
	    {
		mE();
		mX();
		mT();
		mE();
		mR();
		mN();
		mA();
		mL();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "EXTERNAL"

    // $ANTLR start "INTRINSIC"
    public final void mINTRINSIC() throws RecognitionException {
	try {
	    int _type = INTRINSIC;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:45:2:
	    // ( I N T R I N S I C )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:45:4:
	    // I N T R I N S I C
	    {
		mI();
		mN();
		mT();
		mR();
		mI();
		mN();
		mS();
		mI();
		mC();

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "INTRINSIC"

    // $ANTLR start "DOLLAR"
    public final void mDOLLAR() throws RecognitionException {
	try {
	    int _type = DOLLAR;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:50:3:
	    // ( '$' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:51:3:
	    // '$'
	    {
		match('$');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "DOLLAR"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
	try {
	    int _type = COMMA;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:55:3:
	    // ( ',' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:56:3:
	    // ','
	    {
		match(',');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "COMMA"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
	try {
	    int _type = LPAREN;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:60:3:
	    // ( '(' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:61:3:
	    // '('
	    {
		match('(');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "LPAREN"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
	try {
	    int _type = RPAREN;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:65:3:
	    // ( ')' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:66:3:
	    // ')'
	    {
		match(')');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "RPAREN"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
	try {
	    int _type = COLON;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:70:3:
	    // ( ':' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:71:3:
	    // ':'
	    {
		match(':');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "COLON"

    // $ANTLR start "ASSIGN"
    public final void mASSIGN() throws RecognitionException {
	try {
	    int _type = ASSIGN;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:76:3:
	    // ( '=' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:77:3:
	    // '='
	    {
		match('=');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "ASSIGN"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
	try {
	    int _type = MINUS;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:81:3:
	    // ( '-' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:82:3:
	    // '-'
	    {
		match('-');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "MINUS"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
	try {
	    int _type = PLUS;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:86:3:
	    // ( '+' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:87:3:
	    // '+'
	    {
		match('+');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "PLUS"

    // $ANTLR start "DIV"
    public final void mDIV() throws RecognitionException {
	try {
	    int _type = DIV;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:91:3:
	    // ( '/' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:92:3:
	    // '/'
	    {
		match('/');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "DIV"

    // $ANTLR start "POWER"
    public final void mPOWER() throws RecognitionException {
	try {
	    int _type = POWER;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:96:3:
	    // ({...}? => '**' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:97:3:
	    // {...}? => '**'
	    {
		if (!((getCharPositionInLine() > 0))) {
		    throw new FailedPredicateException(input, "POWER",
			    "getCharPositionInLine()>0");
		}
		match("**");

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "POWER"

    // $ANTLR start "STAR"
    public final void mSTAR() throws RecognitionException {
	try {
	    int _type = STAR;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:101:3:
	    // ({...}? => '*' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:102:3:
	    // {...}? => '*'
	    {
		if (!((getCharPositionInLine() > 0))) {
		    throw new FailedPredicateException(input, "STAR",
			    "getCharPositionInLine()>0");
		}
		match('*');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "STAR"

    // $ANTLR start "LNOT"
    public final void mLNOT() throws RecognitionException {
	try {
	    int _type = LNOT;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:106:3:
	    // ( '.' N O T '.' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:107:3:
	    // '.' N O T '.'
	    {
		match('.');
		mN();
		mO();
		mT();
		match('.');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "LNOT"

    // $ANTLR start "LAND"
    public final void mLAND() throws RecognitionException {
	try {
	    int _type = LAND;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:111:3:
	    // ( '.' A N D '.' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:112:3:
	    // '.' A N D '.'
	    {
		match('.');
		mA();
		mN();
		mD();
		match('.');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "LAND"

    // $ANTLR start "LOR"
    public final void mLOR() throws RecognitionException {
	try {
	    int _type = LOR;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:116:3:
	    // ( '.' O R '.' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:117:3:
	    // '.' O R '.'
	    {
		match('.');
		mO();
		mR();
		match('.');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "LOR"

    // $ANTLR start "EQV"
    public final void mEQV() throws RecognitionException {
	try {
	    int _type = EQV;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:121:3:
	    // ( '.' E Q V '.' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:122:3:
	    // '.' E Q V '.'
	    {
		match('.');
		mE();
		mQ();
		mV();
		match('.');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "EQV"

    // $ANTLR start "NEQV"
    public final void mNEQV() throws RecognitionException {
	try {
	    int _type = NEQV;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:126:3:
	    // ( '.' N E Q V '.' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:127:3:
	    // '.' N E Q V '.'
	    {
		match('.');
		mN();
		mE();
		mQ();
		mV();
		match('.');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "NEQV"

    // $ANTLR start "XOR"
    public final void mXOR() throws RecognitionException {
	try {
	    int _type = XOR;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:131:3:
	    // ( '.' X O R '.' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:132:3:
	    // '.' X O R '.'
	    {
		match('.');
		mX();
		mO();
		mR();
		match('.');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "XOR"

    // $ANTLR start "EOR"
    public final void mEOR() throws RecognitionException {
	try {
	    int _type = EOR;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:136:3:
	    // ( '.' E O R '.' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:137:3:
	    // '.' E O R '.'
	    {
		match('.');
		mE();
		mO();
		mR();
		match('.');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "EOR"

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
	try {
	    int _type = LT;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:141:3:
	    // ( '.' L T '.' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:142:3:
	    // '.' L T '.'
	    {
		match('.');
		mL();
		mT();
		match('.');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "LT"

    // $ANTLR start "LE"
    public final void mLE() throws RecognitionException {
	try {
	    int _type = LE;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:146:3:
	    // ( '.' L E '.' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:147:3:
	    // '.' L E '.'
	    {
		match('.');
		mL();
		mE();
		match('.');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "LE"

    // $ANTLR start "GT"
    public final void mGT() throws RecognitionException {
	try {
	    int _type = GT;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:151:3:
	    // ( '.' G T '.' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:152:3:
	    // '.' G T '.'
	    {
		match('.');
		mG();
		mT();
		match('.');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "GT"

    // $ANTLR start "GE"
    public final void mGE() throws RecognitionException {
	try {
	    int _type = GE;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:156:3:
	    // ( '.' G E '.' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:157:3:
	    // '.' G E '.'
	    {
		match('.');
		mG();
		mE();
		match('.');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "GE"

    // $ANTLR start "NE"
    public final void mNE() throws RecognitionException {
	try {
	    int _type = NE;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:161:3:
	    // ( '.' N E '.' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:162:3:
	    // '.' N E '.'
	    {
		match('.');
		mN();
		mE();
		match('.');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "NE"

    // $ANTLR start "EQ"
    public final void mEQ() throws RecognitionException {
	try {
	    int _type = EQ;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:166:3:
	    // ( '.' E Q '.' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:167:3:
	    // '.' E Q '.'
	    {
		match('.');
		mE();
		mQ();
		match('.');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "EQ"

    // $ANTLR start "TRUE"
    public final void mTRUE() throws RecognitionException {
	try {
	    int _type = TRUE;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:171:3:
	    // ( '.' T R U E '.' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:172:3:
	    // '.' T R U E '.'
	    {
		match('.');
		mT();
		mR();
		mU();
		mE();
		match('.');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "TRUE"

    // $ANTLR start "FALSE"
    public final void mFALSE() throws RecognitionException {
	try {
	    int _type = FALSE;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:176:3:
	    // ( '.' F A L S E '.' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:177:3:
	    // '.' F A L S E '.'
	    {
		match('.');
		mF();
		mA();
		mL();
		mS();
		mE();
		match('.');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "FALSE"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
	try {
	    int _type = ID;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:181:3:
	    // ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' ..
	    // 'Z' | '0' .. '9' | '_' )* )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:182:3:
	    // ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z'
	    // | '0' .. '9' | '_' )*
	    {
		if ((input.LA(1) >= 'A' && input.LA(1) <= 'Z')
			|| input.LA(1) == '_'
			|| (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:187:3:
		// ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
		loop3: do {
		    int alt3 = 2;
		    int LA3_0 = input.LA(1);

		    if (((LA3_0 >= '0' && LA3_0 <= '9')
			    || (LA3_0 >= 'A' && LA3_0 <= 'Z')
			    || LA3_0 == '_' || (LA3_0 >= 'a' && LA3_0 <= 'z'))) {
			alt3 = 1;
		    }

		    switch (alt3) {
		    case 1:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:
		    {
			if ((input.LA(1) >= '0' && input.LA(1) <= '9')
				|| (input.LA(1) >= 'A' && input.LA(1) <= 'Z')
				|| input.LA(1) == '_'
				|| (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
			    input.consume();

			} else {
			    MismatchedSetException mse =
				    new MismatchedSetException(null, input);
			    recover(mse);
			    throw mse;
			}

		    }
			break;

		    default:
			break loop3;
		    }
		} while (true);

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "ID"

    // $ANTLR start "INT_CONST"
    public final void mINT_CONST() throws RecognitionException {
	try {
	    int _type = INT_CONST;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:196:3:
	    // ( ( '0' .. '9' )+ )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:197:3:
	    // ( '0' .. '9' )+
	    {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:197:3:
		// ( '0' .. '9' )+
		int cnt4 = 0;
		loop4: do {
		    int alt4 = 2;
		    int LA4_0 = input.LA(1);

		    if (((LA4_0 >= '0' && LA4_0 <= '9'))) {
			alt4 = 1;
		    }

		    switch (alt4) {
		    case 1:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:197:3:
			// '0' .. '9'
		    {
			matchRange('0', '9');

		    }
			break;

		    default:
			if (cnt4 >= 1)
			    break loop4;
			EarlyExitException eee =
				new EarlyExitException(4, input);
			throw eee;
		    }
		    cnt4++;
		} while (true);

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "INT_CONST"

    // $ANTLR start "HEX_CONST"
    public final void mHEX_CONST() throws RecognitionException {
	try {
	    int _type = HEX_CONST;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:201:3:
	    // ( '0x' ( HEX_DIGIT )+ )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:202:3:
	    // '0x' ( HEX_DIGIT )+
	    {
		match("0x");

		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:202:8:
		// ( HEX_DIGIT )+
		int cnt5 = 0;
		loop5: do {
		    int alt5 = 2;
		    int LA5_0 = input.LA(1);

		    if (((LA5_0 >= '0' && LA5_0 <= '9')
			    || (LA5_0 >= 'A' && LA5_0 <= 'F') || (LA5_0 >= 'a' && LA5_0 <= 'f'))) {
			alt5 = 1;
		    }

		    switch (alt5) {
		    case 1:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:202:8:
			// HEX_DIGIT
		    {
			mHEX_DIGIT();

		    }
			break;

		    default:
			if (cnt5 >= 1)
			    break loop5;
			EarlyExitException eee =
				new EarlyExitException(5, input);
			throw eee;
		    }
		    cnt5++;
		} while (true);

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "HEX_CONST"

    // $ANTLR start "FLOAT_CONST"
    public final void mFLOAT_CONST() throws RecognitionException {
	try {
	    int _type = FLOAT_CONST;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:206:3:
	    // ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' (
	    // '0' .. '9' )+ ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT )
	    int alt12 = 3;
	    alt12 = dfa12.predict(input);
	    switch (alt12) {
	    case 1:
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:207:3:
		// ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )?
	    {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:207:3:
		// ( '0' .. '9' )+
		int cnt6 = 0;
		loop6: do {
		    int alt6 = 2;
		    int LA6_0 = input.LA(1);

		    if (((LA6_0 >= '0' && LA6_0 <= '9'))) {
			alt6 = 1;
		    }

		    switch (alt6) {
		    case 1:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:207:4:
			// '0' .. '9'
		    {
			matchRange('0', '9');

		    }
			break;

		    default:
			if (cnt6 >= 1)
			    break loop6;
			EarlyExitException eee =
				new EarlyExitException(6, input);
			throw eee;
		    }
		    cnt6++;
		} while (true);

		match('.');
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:207:19:
		// ( '0' .. '9' )*
		loop7: do {
		    int alt7 = 2;
		    int LA7_0 = input.LA(1);

		    if (((LA7_0 >= '0' && LA7_0 <= '9'))) {
			alt7 = 1;
		    }

		    switch (alt7) {
		    case 1:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:207:20:
			// '0' .. '9'
		    {
			matchRange('0', '9');

		    }
			break;

		    default:
			break loop7;
		    }
		} while (true);

		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:207:31:
		// ( EXPONENT )?
		int alt8 = 2;
		int LA8_0 = input.LA(1);

		if (((LA8_0 >= 'D' && LA8_0 <= 'E') || (LA8_0 >= 'd' && LA8_0 <= 'e'))) {
		    alt8 = 1;
		}
		switch (alt8) {
		case 1:
		    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:207:31:
		    // EXPONENT
		{
		    mEXPONENT();

		}
		    break;

		}

	    }
		break;
	    case 2:
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:208:5:
		// '.' ( '0' .. '9' )+ ( EXPONENT )?
	    {
		match('.');
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:208:9:
		// ( '0' .. '9' )+
		int cnt9 = 0;
		loop9: do {
		    int alt9 = 2;
		    int LA9_0 = input.LA(1);

		    if (((LA9_0 >= '0' && LA9_0 <= '9'))) {
			alt9 = 1;
		    }

		    switch (alt9) {
		    case 1:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:208:10:
			// '0' .. '9'
		    {
			matchRange('0', '9');

		    }
			break;

		    default:
			if (cnt9 >= 1)
			    break loop9;
			EarlyExitException eee =
				new EarlyExitException(9, input);
			throw eee;
		    }
		    cnt9++;
		} while (true);

		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:208:21:
		// ( EXPONENT )?
		int alt10 = 2;
		int LA10_0 = input.LA(1);

		if (((LA10_0 >= 'D' && LA10_0 <= 'E') || (LA10_0 >= 'd' && LA10_0 <= 'e'))) {
		    alt10 = 1;
		}
		switch (alt10) {
		case 1:
		    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:208:21:
		    // EXPONENT
		{
		    mEXPONENT();

		}
		    break;

		}

	    }
		break;
	    case 3:
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:209:5:
		// ( '0' .. '9' )+ EXPONENT
	    {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:209:5:
		// ( '0' .. '9' )+
		int cnt11 = 0;
		loop11: do {
		    int alt11 = 2;
		    int LA11_0 = input.LA(1);

		    if (((LA11_0 >= '0' && LA11_0 <= '9'))) {
			alt11 = 1;
		    }

		    switch (alt11) {
		    case 1:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:209:6:
			// '0' .. '9'
		    {
			matchRange('0', '9');

		    }
			break;

		    default:
			if (cnt11 >= 1)
			    break loop11;
			EarlyExitException eee =
				new EarlyExitException(11, input);
			throw eee;
		    }
		    cnt11++;
		} while (true);

		mEXPONENT();

	    }
		break;

	    }
	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "FLOAT_CONST"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
	try {
	    int _type = COMMENT;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:213:3:
	    // ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? LINEFEED | '/*' (
	    // options {greedy=false; } : ( LINEFEED | ~ ( '\\n' ) ) )*
	    // '*/' | {...}? => ( 'c' | '*' ) (~ ( '\\n' | '\\r' ) )* (
	    // '\\r' )? LINEFEED | '!' (~ ( '\\n' | '\\r' ) )* ( '\\r' )?
	    // LINEFEED )
	    int alt21 = 4;
	    int LA21_0 = input.LA(1);

	    if ((LA21_0 == '/')) {
		int LA21_1 = input.LA(2);

		if ((LA21_1 == '/')) {
		    alt21 = 1;
		} else if ((LA21_1 == '*')) {
		    alt21 = 2;
		} else {
		    NoViableAltException nvae =
			    new NoViableAltException("", 21, 1, input);

		    throw nvae;
		}
	    } else if ((LA21_0 == '*' || LA21_0 == 'c')
		    && ((getCharPositionInLine() == 0))) {
		alt21 = 3;
	    } else if ((LA21_0 == '!')) {
		alt21 = 4;
	    } else {
		NoViableAltException nvae =
			new NoViableAltException("", 21, 0, input);

		throw nvae;
	    }
	    switch (alt21) {
	    case 1:
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:214:3:
		// '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? LINEFEED
	    {
		match("//");

		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:215:3:
		// (~ ( '\\n' | '\\r' ) )*
		loop13: do {
		    int alt13 = 2;
		    int LA13_0 = input.LA(1);

		    if (((LA13_0 >= '\u0000' && LA13_0 <= '\t')
			    || (LA13_0 >= '\u000B' && LA13_0 <= '\f') || (LA13_0 >= '\u000E' && LA13_0 <= '\uFFFF'))) {
			alt13 = 1;
		    }

		    switch (alt13) {
		    case 1:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:215:3:
			// ~ ( '\\n' | '\\r' )
		    {
			if ((input.LA(1) >= '\u0000' && input.LA(1) <= '\t')
				|| (input.LA(1) >= '\u000B' && input.LA(1) <= '\f')
				|| (input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF')) {
			    input.consume();

			} else {
			    MismatchedSetException mse =
				    new MismatchedSetException(null, input);
			    recover(mse);
			    throw mse;
			}

		    }
			break;

		    default:
			break loop13;
		    }
		} while (true);

		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:219:3:
		// ( '\\r' )?
		int alt14 = 2;
		int LA14_0 = input.LA(1);

		if ((LA14_0 == '\r')) {
		    alt14 = 1;
		}
		switch (alt14) {
		case 1:
		    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:219:3:
		    // '\\r'
		{
		    match('\r');

		}
		    break;

		}

		mLINEFEED();
		_channel = HIDDEN;

	    }
		break;
	    case 2:
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:220:5:
		// '/*' ( options {greedy=false; } : ( LINEFEED | ~ ( '\\n'
		// ) ) )* '*/'
	    {
		match("/*");

		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:221:3:
		// ( options {greedy=false; } : ( LINEFEED | ~ ( '\\n' ) )
		// )*
		loop16: do {
		    int alt16 = 2;
		    int LA16_0 = input.LA(1);

		    if ((LA16_0 == '*')) {
			int LA16_1 = input.LA(2);

			if ((LA16_1 == '/')) {
			    alt16 = 2;
			} else if (((LA16_1 >= '\u0000' && LA16_1 <= '.') || (LA16_1 >= '0' && LA16_1 <= '\uFFFF'))) {
			    alt16 = 1;
			}

		    } else if (((LA16_0 >= '\u0000' && LA16_0 <= ')') || (LA16_0 >= '+' && LA16_0 <= '\uFFFF'))) {
			alt16 = 1;
		    }

		    switch (alt16) {
		    case 1:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:222:5:
			// ( LINEFEED | ~ ( '\\n' ) )
		    {
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:222:5:
			// ( LINEFEED | ~ ( '\\n' ) )
			int alt15 = 2;
			int LA15_0 = input.LA(1);

			if ((LA15_0 == '\n')) {
			    alt15 = 1;
			} else if (((LA15_0 >= '\u0000' && LA15_0 <= '\t') || (LA15_0 >= '\u000B' && LA15_0 <= '\uFFFF'))) {
			    alt15 = 2;
			} else {
			    NoViableAltException nvae =
				    new NoViableAltException("", 15, 0,
					    input);

			    throw nvae;
			}
			switch (alt15) {
			case 1:
			    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:223:7:
			    // LINEFEED
			{
			    mLINEFEED();

			}
			    break;
			case 2:
			    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:224:9:
			    // ~ ( '\\n' )
			{
			    if ((input.LA(1) >= '\u0000' && input.LA(1) <= '\t')
				    || (input.LA(1) >= '\u000B' && input
					    .LA(1) <= '\uFFFF')) {
				input.consume();

			    } else {
				MismatchedSetException mse =
					new MismatchedSetException(null,
						input);
				recover(mse);
				throw mse;
			    }

			}
			    break;

			}

		    }
			break;

		    default:
			break loop16;
		    }
		} while (true);

		match("*/");

		_channel = HIDDEN;

	    }
		break;
	    case 3:
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:229:3:
		// {...}? => ( 'c' | '*' ) (~ ( '\\n' | '\\r' ) )* ( '\\r'
		// )? LINEFEED
	    {
		if (!((getCharPositionInLine() == 0))) {
		    throw new FailedPredicateException(input, "COMMENT",
			    "getCharPositionInLine()==0");
		}
		if (input.LA(1) == '*' || input.LA(1) == 'c') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:234:3:
		// (~ ( '\\n' | '\\r' ) )*
		loop17: do {
		    int alt17 = 2;
		    int LA17_0 = input.LA(1);

		    if (((LA17_0 >= '\u0000' && LA17_0 <= '\t')
			    || (LA17_0 >= '\u000B' && LA17_0 <= '\f') || (LA17_0 >= '\u000E' && LA17_0 <= '\uFFFF'))) {
			alt17 = 1;
		    }

		    switch (alt17) {
		    case 1:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:234:3:
			// ~ ( '\\n' | '\\r' )
		    {
			if ((input.LA(1) >= '\u0000' && input.LA(1) <= '\t')
				|| (input.LA(1) >= '\u000B' && input.LA(1) <= '\f')
				|| (input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF')) {
			    input.consume();

			} else {
			    MismatchedSetException mse =
				    new MismatchedSetException(null, input);
			    recover(mse);
			    throw mse;
			}

		    }
			break;

		    default:
			break loop17;
		    }
		} while (true);

		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:238:3:
		// ( '\\r' )?
		int alt18 = 2;
		int LA18_0 = input.LA(1);

		if ((LA18_0 == '\r')) {
		    alt18 = 1;
		}
		switch (alt18) {
		case 1:
		    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:238:3:
		    // '\\r'
		{
		    match('\r');

		}
		    break;

		}

		mLINEFEED();
		_channel = HIDDEN;

	    }
		break;
	    case 4:
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:240:3:
		// '!' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? LINEFEED
	    {
		match('!');
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:241:3:
		// (~ ( '\\n' | '\\r' ) )*
		loop19: do {
		    int alt19 = 2;
		    int LA19_0 = input.LA(1);

		    if (((LA19_0 >= '\u0000' && LA19_0 <= '\t')
			    || (LA19_0 >= '\u000B' && LA19_0 <= '\f') || (LA19_0 >= '\u000E' && LA19_0 <= '\uFFFF'))) {
			alt19 = 1;
		    }

		    switch (alt19) {
		    case 1:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:241:3:
			// ~ ( '\\n' | '\\r' )
		    {
			if ((input.LA(1) >= '\u0000' && input.LA(1) <= '\t')
				|| (input.LA(1) >= '\u000B' && input.LA(1) <= '\f')
				|| (input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF')) {
			    input.consume();

			} else {
			    MismatchedSetException mse =
				    new MismatchedSetException(null, input);
			    recover(mse);
			    throw mse;
			}

		    }
			break;

		    default:
			break loop19;
		    }
		} while (true);

		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:245:3:
		// ( '\\r' )?
		int alt20 = 2;
		int LA20_0 = input.LA(1);

		if ((LA20_0 == '\r')) {
		    alt20 = 1;
		}
		switch (alt20) {
		case 1:
		    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:245:3:
		    // '\\r'
		{
		    match('\r');

		}
		    break;

		}

		mLINEFEED();
		_channel = HIDDEN;

	    }
		break;

	    }
	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "COMMENT"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
	try {
	    int _type = WS;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:249:3:
	    // ( ( ' ' | '\\t' | '\\r' | LINEFEED ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:250:3:
	    // ( ' ' | '\\t' | '\\r' | LINEFEED )
	    {
		if ((input.LA(1) >= '\t' && input.LA(1) <= '\n')
			|| input.LA(1) == '\r' || input.LA(1) == ' ') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

		_channel = HIDDEN;

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "WS"

    // $ANTLR start "LINEFEED"
    public final void mLINEFEED() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:261:3:
	    // ( '\\n' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:262:3:
	    // '\\n'
	    {
		match('\n');

	    }

	} finally {
	}
    }

    // $ANTLR end "LINEFEED"

    // $ANTLR start "NOTNL"
    public final void mNOTNL() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:267:3:
	    // (~ ( '\\r' | '\\n' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:267:5:
	    // ~ ( '\\r' | '\\n' )
	    {
		if ((input.LA(1) >= '\u0000' && input.LA(1) <= '\t')
			|| (input.LA(1) >= '\u000B' && input.LA(1) <= '\f')
			|| (input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF')) {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "NOTNL"

    // $ANTLR start "STRING_CONST"
    public final void mSTRING_CONST() throws RecognitionException {
	try {
	    int _type = STRING_CONST;
	    int _channel = DEFAULT_TOKEN_CHANNEL;
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:271:3:
	    // ( '\\'' ( '\\'' '\\'' | ESC_SEQ | ~ ( '\\\\' | '\\'' ) )*
	    // '\\'' )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:272:3:
	    // '\\'' ( '\\'' '\\'' | ESC_SEQ | ~ ( '\\\\' | '\\'' ) )*
	    // '\\''
	    {
		match('\'');
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:273:3:
		// ( '\\'' '\\'' | ESC_SEQ | ~ ( '\\\\' | '\\'' ) )*
		loop22: do {
		    int alt22 = 4;
		    int LA22_0 = input.LA(1);

		    if ((LA22_0 == '\'')) {
			int LA22_1 = input.LA(2);

			if ((LA22_1 == '\'')) {
			    alt22 = 1;
			}

		    } else if ((LA22_0 == '\\')) {
			alt22 = 2;
		    } else if (((LA22_0 >= '\u0000' && LA22_0 <= '&')
			    || (LA22_0 >= '(' && LA22_0 <= '[') || (LA22_0 >= ']' && LA22_0 <= '\uFFFF'))) {
			alt22 = 3;
		    }

		    switch (alt22) {
		    case 1:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:274:5:
			// '\\'' '\\''
		    {
			match('\'');
			match('\'');

		    }
			break;
		    case 2:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:276:5:
			// ESC_SEQ
		    {
			mESC_SEQ();

		    }
			break;
		    case 3:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:278:5:
			// ~ ( '\\\\' | '\\'' )
		    {
			if ((input.LA(1) >= '\u0000' && input.LA(1) <= '&')
				|| (input.LA(1) >= '(' && input.LA(1) <= '[')
				|| (input.LA(1) >= ']' && input.LA(1) <= '\uFFFF')) {
			    input.consume();

			} else {
			    MismatchedSetException mse =
				    new MismatchedSetException(null, input);
			    recover(mse);
			    throw mse;
			}

		    }
			break;

		    default:
			break loop22;
		    }
		} while (true);

		match('\'');

	    }

	    state.type = _type;
	    state.channel = _channel;
	} finally {
	}
    }

    // $ANTLR end "STRING_CONST"

    // $ANTLR start "EXPONENT"
    public final void mEXPONENT() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:288:3:
	    // ( ( 'e' | 'E' | 'd' | 'D' ) ( '+' | '-' )? ( '0' .. '9' )+ )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:289:3:
	    // ( 'e' | 'E' | 'd' | 'D' ) ( '+' | '-' )? ( '0' .. '9' )+
	    {
		if ((input.LA(1) >= 'D' && input.LA(1) <= 'E')
			|| (input.LA(1) >= 'd' && input.LA(1) <= 'e')) {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:295:3:
		// ( '+' | '-' )?
		int alt23 = 2;
		int LA23_0 = input.LA(1);

		if ((LA23_0 == '+' || LA23_0 == '-')) {
		    alt23 = 1;
		}
		switch (alt23) {
		case 1:
		    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:
		{
		    if (input.LA(1) == '+' || input.LA(1) == '-') {
			input.consume();

		    } else {
			MismatchedSetException mse =
				new MismatchedSetException(null, input);
			recover(mse);
			throw mse;
		    }

		}
		    break;

		}

		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:299:3:
		// ( '0' .. '9' )+
		int cnt24 = 0;
		loop24: do {
		    int alt24 = 2;
		    int LA24_0 = input.LA(1);

		    if (((LA24_0 >= '0' && LA24_0 <= '9'))) {
			alt24 = 1;
		    }

		    switch (alt24) {
		    case 1:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:299:4:
			// '0' .. '9'
		    {
			matchRange('0', '9');

		    }
			break;

		    default:
			if (cnt24 >= 1)
			    break loop24;
			EarlyExitException eee =
				new EarlyExitException(24, input);
			throw eee;
		    }
		    cnt24++;
		} while (true);

	    }

	} finally {
	}
    }

    // $ANTLR end "EXPONENT"

    // $ANTLR start "HEX_DIGIT"
    public final void mHEX_DIGIT() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:304:3:
	    // ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:305:3:
	    // ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
	    {
		if ((input.LA(1) >= '0' && input.LA(1) <= '9')
			|| (input.LA(1) >= 'A' && input.LA(1) <= 'F')
			|| (input.LA(1) >= 'a' && input.LA(1) <= 'f')) {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "HEX_DIGIT"

    // $ANTLR start "ESC_SEQ"
    public final void mESC_SEQ() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:314:3:
	    // ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' |
	    // '\\\\' ) | UNICODE_ESC | OCTAL_ESC )
	    int alt25 = 3;
	    int LA25_0 = input.LA(1);

	    if ((LA25_0 == '\\')) {
		switch (input.LA(2)) {
		case '\"':
		case '\'':
		case '\\':
		case 'b':
		case 'f':
		case 'n':
		case 'r':
		case 't': {
		    alt25 = 1;
		}
		    break;
		case 'u': {
		    alt25 = 2;
		}
		    break;
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7': {
		    alt25 = 3;
		}
		    break;
		default:
		    NoViableAltException nvae =
			    new NoViableAltException("", 25, 1, input);

		    throw nvae;
		}

	    } else {
		NoViableAltException nvae =
			new NoViableAltException("", 25, 0, input);

		throw nvae;
	    }
	    switch (alt25) {
	    case 1:
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:315:3:
		// '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' |
		// '\\\\' )
	    {
		match('\\');
		if (input.LA(1) == '\"' || input.LA(1) == '\''
			|| input.LA(1) == '\\' || input.LA(1) == 'b'
			|| input.LA(1) == 'f' || input.LA(1) == 'n'
			|| input.LA(1) == 'r' || input.LA(1) == 't') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }
		break;
	    case 2:
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:326:5:
		// UNICODE_ESC
	    {
		mUNICODE_ESC();

	    }
		break;
	    case 3:
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:327:5:
		// OCTAL_ESC
	    {
		mOCTAL_ESC();

	    }
		break;

	    }
	} finally {
	}
    }

    // $ANTLR end "ESC_SEQ"

    // $ANTLR start "OCTAL_ESC"
    public final void mOCTAL_ESC() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:332:3:
	    // ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) |
	    // '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' )
	    // )
	    int alt26 = 3;
	    int LA26_0 = input.LA(1);

	    if ((LA26_0 == '\\')) {
		int LA26_1 = input.LA(2);

		if (((LA26_1 >= '0' && LA26_1 <= '3'))) {
		    int LA26_2 = input.LA(3);

		    if (((LA26_2 >= '0' && LA26_2 <= '7'))) {
			int LA26_4 = input.LA(4);

			if (((LA26_4 >= '0' && LA26_4 <= '7'))) {
			    alt26 = 1;
			} else {
			    alt26 = 2;
			}
		    } else {
			alt26 = 3;
		    }
		} else if (((LA26_1 >= '4' && LA26_1 <= '7'))) {
		    int LA26_3 = input.LA(3);

		    if (((LA26_3 >= '0' && LA26_3 <= '7'))) {
			alt26 = 2;
		    } else {
			alt26 = 3;
		    }
		} else {
		    NoViableAltException nvae =
			    new NoViableAltException("", 26, 1, input);

		    throw nvae;
		}
	    } else {
		NoViableAltException nvae =
			new NoViableAltException("", 26, 0, input);

		throw nvae;
	    }
	    switch (alt26) {
	    case 1:
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:333:3:
		// '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
	    {
		match('\\');
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:333:8:
		// ( '0' .. '3' )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:333:9:
		// '0' .. '3'
		{
		    matchRange('0', '3');

		}

		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:333:19:
		// ( '0' .. '7' )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:333:20:
		// '0' .. '7'
		{
		    matchRange('0', '7');

		}

		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:333:30:
		// ( '0' .. '7' )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:333:31:
		// '0' .. '7'
		{
		    matchRange('0', '7');

		}

	    }
		break;
	    case 2:
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:334:5:
		// '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
	    {
		match('\\');
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:334:10:
		// ( '0' .. '7' )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:334:11:
		// '0' .. '7'
		{
		    matchRange('0', '7');

		}

		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:334:21:
		// ( '0' .. '7' )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:334:22:
		// '0' .. '7'
		{
		    matchRange('0', '7');

		}

	    }
		break;
	    case 3:
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:335:5:
		// '\\\\' ( '0' .. '7' )
	    {
		match('\\');
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:335:10:
		// ( '0' .. '7' )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:335:11:
		// '0' .. '7'
		{
		    matchRange('0', '7');

		}

	    }
		break;

	    }
	} finally {
	}
    }

    // $ANTLR end "OCTAL_ESC"

    // $ANTLR start "UNICODE_ESC"
    public final void mUNICODE_ESC() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:340:3:
	    // ( '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:341:3:
	    // '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
	    {
		match('\\');
		match('u');
		mHEX_DIGIT();
		mHEX_DIGIT();
		mHEX_DIGIT();
		mHEX_DIGIT();

	    }

	} finally {
	}
    }

    // $ANTLR end "UNICODE_ESC"

    // $ANTLR start "A"
    public final void mA() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:344:11:
	    // ( ( 'a' | 'A' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:344:12:
	    // ( 'a' | 'A' )
	    {
		if (input.LA(1) == 'A' || input.LA(1) == 'a') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "A"

    // $ANTLR start "B"
    public final void mB() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:345:11:
	    // ( ( 'b' | 'B' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:345:12:
	    // ( 'b' | 'B' )
	    {
		if (input.LA(1) == 'B' || input.LA(1) == 'b') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "B"

    // $ANTLR start "C"
    public final void mC() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:346:11:
	    // ( ( 'c' | 'C' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:346:12:
	    // ( 'c' | 'C' )
	    {
		if (input.LA(1) == 'C' || input.LA(1) == 'c') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "C"

    // $ANTLR start "D"
    public final void mD() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:347:11:
	    // ( ( 'd' | 'D' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:347:12:
	    // ( 'd' | 'D' )
	    {
		if (input.LA(1) == 'D' || input.LA(1) == 'd') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "D"

    // $ANTLR start "E"
    public final void mE() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:348:11:
	    // ( ( 'e' | 'E' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:348:12:
	    // ( 'e' | 'E' )
	    {
		if (input.LA(1) == 'E' || input.LA(1) == 'e') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "E"

    // $ANTLR start "F"
    public final void mF() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:349:11:
	    // ( ( 'f' | 'F' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:349:12:
	    // ( 'f' | 'F' )
	    {
		if (input.LA(1) == 'F' || input.LA(1) == 'f') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "F"

    // $ANTLR start "G"
    public final void mG() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:350:11:
	    // ( ( 'g' | 'G' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:350:12:
	    // ( 'g' | 'G' )
	    {
		if (input.LA(1) == 'G' || input.LA(1) == 'g') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "G"

    // $ANTLR start "H"
    public final void mH() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:351:11:
	    // ( ( 'h' | 'H' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:351:12:
	    // ( 'h' | 'H' )
	    {
		if (input.LA(1) == 'H' || input.LA(1) == 'h') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "H"

    // $ANTLR start "I"
    public final void mI() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:352:11:
	    // ( ( 'i' | 'I' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:352:12:
	    // ( 'i' | 'I' )
	    {
		if (input.LA(1) == 'I' || input.LA(1) == 'i') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "I"

    // $ANTLR start "J"
    public final void mJ() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:353:11:
	    // ( ( 'j' | 'J' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:353:12:
	    // ( 'j' | 'J' )
	    {
		if (input.LA(1) == 'J' || input.LA(1) == 'j') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "J"

    // $ANTLR start "K"
    public final void mK() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:354:11:
	    // ( ( 'k' | 'K' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:354:12:
	    // ( 'k' | 'K' )
	    {
		if (input.LA(1) == 'K' || input.LA(1) == 'k') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "K"

    // $ANTLR start "L"
    public final void mL() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:355:11:
	    // ( ( 'l' | 'L' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:355:12:
	    // ( 'l' | 'L' )
	    {
		if (input.LA(1) == 'L' || input.LA(1) == 'l') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "L"

    // $ANTLR start "M"
    public final void mM() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:356:11:
	    // ( ( 'm' | 'M' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:356:12:
	    // ( 'm' | 'M' )
	    {
		if (input.LA(1) == 'M' || input.LA(1) == 'm') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "M"

    // $ANTLR start "N"
    public final void mN() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:357:11:
	    // ( ( 'n' | 'N' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:357:12:
	    // ( 'n' | 'N' )
	    {
		if (input.LA(1) == 'N' || input.LA(1) == 'n') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "N"

    // $ANTLR start "O"
    public final void mO() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:358:11:
	    // ( ( 'o' | 'O' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:358:12:
	    // ( 'o' | 'O' )
	    {
		if (input.LA(1) == 'O' || input.LA(1) == 'o') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "O"

    // $ANTLR start "P"
    public final void mP() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:359:11:
	    // ( ( 'p' | 'P' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:359:12:
	    // ( 'p' | 'P' )
	    {
		if (input.LA(1) == 'P' || input.LA(1) == 'p') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "P"

    // $ANTLR start "Q"
    public final void mQ() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:360:11:
	    // ( ( 'q' | 'Q' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:360:12:
	    // ( 'q' | 'Q' )
	    {
		if (input.LA(1) == 'Q' || input.LA(1) == 'q') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "Q"

    // $ANTLR start "R"
    public final void mR() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:361:11:
	    // ( ( 'r' | 'R' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:361:12:
	    // ( 'r' | 'R' )
	    {
		if (input.LA(1) == 'R' || input.LA(1) == 'r') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "R"

    // $ANTLR start "S"
    public final void mS() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:362:11:
	    // ( ( 's' | 'S' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:362:12:
	    // ( 's' | 'S' )
	    {
		if (input.LA(1) == 'S' || input.LA(1) == 's') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "S"

    // $ANTLR start "T"
    public final void mT() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:363:11:
	    // ( ( 't' | 'T' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:363:12:
	    // ( 't' | 'T' )
	    {
		if (input.LA(1) == 'T' || input.LA(1) == 't') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "T"

    // $ANTLR start "U"
    public final void mU() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:364:11:
	    // ( ( 'u' | 'U' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:364:12:
	    // ( 'u' | 'U' )
	    {
		if (input.LA(1) == 'U' || input.LA(1) == 'u') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "U"

    // $ANTLR start "V"
    public final void mV() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:365:11:
	    // ( ( 'v' | 'V' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:365:12:
	    // ( 'v' | 'V' )
	    {
		if (input.LA(1) == 'V' || input.LA(1) == 'v') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "V"

    // $ANTLR start "W"
    public final void mW() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:366:11:
	    // ( ( 'w' | 'W' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:366:12:
	    // ( 'w' | 'W' )
	    {
		if (input.LA(1) == 'W' || input.LA(1) == 'w') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "W"

    // $ANTLR start "X"
    public final void mX() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:367:11:
	    // ( ( 'x' | 'X' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:367:12:
	    // ( 'x' | 'X' )
	    {
		if (input.LA(1) == 'X' || input.LA(1) == 'x') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "X"

    // $ANTLR start "Y"
    public final void mY() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:368:11:
	    // ( ( 'y' | 'Y' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:368:12:
	    // ( 'y' | 'Y' )
	    {
		if (input.LA(1) == 'Y' || input.LA(1) == 'y') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "Y"

    // $ANTLR start "Z"
    public final void mZ() throws RecognitionException {
	try {
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:369:11:
	    // ( ( 'z' | 'Z' ) )
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:369:12:
	    // ( 'z' | 'Z' )
	    {
		if (input.LA(1) == 'Z' || input.LA(1) == 'z') {
		    input.consume();

		} else {
		    MismatchedSetException mse =
			    new MismatchedSetException(null, input);
		    recover(mse);
		    throw mse;
		}

	    }

	} finally {
	}
    }

    // $ANTLR end "Z"

    public void mTokens() throws RecognitionException {
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:8:
	// ( PROGRAM | SUBROUTINE | FUNCTION | INTEGER | REAL |
	// DOUBLE_PRECISION | COMPLEX | CHARACTER | LOGICAL | IMPLICIT |
	// NONE | ALLOCATE | PARAMETER | IF | THEN | ELSE | DO | WHILE |
	// ENDDO | END | CALL | GOTO | RETURN | CONTINUE | EXTERNAL |
	// INTRINSIC | DOLLAR | COMMA | LPAREN | RPAREN | COLON | ASSIGN |
	// MINUS | PLUS | DIV | POWER | STAR | LNOT | LAND | LOR | EQV |
	// NEQV | XOR | EOR | LT | LE | GT | GE | NE | EQ | TRUE | FALSE |
	// ID | INT_CONST | HEX_CONST | FLOAT_CONST | COMMENT | WS |
	// STRING_CONST )
	int alt27 = 59;
	alt27 = dfa27.predict(input);
	switch (alt27) {
	case 1:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:10:
	    // PROGRAM
	{
	    mPROGRAM();

	}
	    break;
	case 2:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:18:
	    // SUBROUTINE
	{
	    mSUBROUTINE();

	}
	    break;
	case 3:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:29:
	    // FUNCTION
	{
	    mFUNCTION();

	}
	    break;
	case 4:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:38:
	    // INTEGER
	{
	    mINTEGER();

	}
	    break;
	case 5:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:46:
	    // REAL
	{
	    mREAL();

	}
	    break;
	case 6:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:51:
	    // DOUBLE_PRECISION
	{
	    mDOUBLE_PRECISION();

	}
	    break;
	case 7:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:68:
	    // COMPLEX
	{
	    mCOMPLEX();

	}
	    break;
	case 8:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:76:
	    // CHARACTER
	{
	    mCHARACTER();

	}
	    break;
	case 9:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:86:
	    // LOGICAL
	{
	    mLOGICAL();

	}
	    break;
	case 10:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:94:
	    // IMPLICIT
	{
	    mIMPLICIT();

	}
	    break;
	case 11:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:103:
	    // NONE
	{
	    mNONE();

	}
	    break;
	case 12:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:108:
	    // ALLOCATE
	{
	    mALLOCATE();

	}
	    break;
	case 13:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:117:
	    // PARAMETER
	{
	    mPARAMETER();

	}
	    break;
	case 14:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:127:
	    // IF
	{
	    mIF();

	}
	    break;
	case 15:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:130:
	    // THEN
	{
	    mTHEN();

	}
	    break;
	case 16:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:135:
	    // ELSE
	{
	    mELSE();

	}
	    break;
	case 17:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:140:
	    // DO
	{
	    mDO();

	}
	    break;
	case 18:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:143:
	    // WHILE
	{
	    mWHILE();

	}
	    break;
	case 19:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:149:
	    // ENDDO
	{
	    mENDDO();

	}
	    break;
	case 20:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:155:
	    // END
	{
	    mEND();

	}
	    break;
	case 21:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:159:
	    // CALL
	{
	    mCALL();

	}
	    break;
	case 22:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:164:
	    // GOTO
	{
	    mGOTO();

	}
	    break;
	case 23:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:169:
	    // RETURN
	{
	    mRETURN();

	}
	    break;
	case 24:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:176:
	    // CONTINUE
	{
	    mCONTINUE();

	}
	    break;
	case 25:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:185:
	    // EXTERNAL
	{
	    mEXTERNAL();

	}
	    break;
	case 26:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:194:
	    // INTRINSIC
	{
	    mINTRINSIC();

	}
	    break;
	case 27:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:204:
	    // DOLLAR
	{
	    mDOLLAR();

	}
	    break;
	case 28:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:211:
	    // COMMA
	{
	    mCOMMA();

	}
	    break;
	case 29:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:217:
	    // LPAREN
	{
	    mLPAREN();

	}
	    break;
	case 30:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:224:
	    // RPAREN
	{
	    mRPAREN();

	}
	    break;
	case 31:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:231:
	    // COLON
	{
	    mCOLON();

	}
	    break;
	case 32:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:237:
	    // ASSIGN
	{
	    mASSIGN();

	}
	    break;
	case 33:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:244:
	    // MINUS
	{
	    mMINUS();

	}
	    break;
	case 34:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:250:
	    // PLUS
	{
	    mPLUS();

	}
	    break;
	case 35:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:255:
	    // DIV
	{
	    mDIV();

	}
	    break;
	case 36:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:259:
	    // POWER
	{
	    mPOWER();

	}
	    break;
	case 37:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:265:
	    // STAR
	{
	    mSTAR();

	}
	    break;
	case 38:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:270:
	    // LNOT
	{
	    mLNOT();

	}
	    break;
	case 39:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:275:
	    // LAND
	{
	    mLAND();

	}
	    break;
	case 40:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:280:
	    // LOR
	{
	    mLOR();

	}
	    break;
	case 41:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:284:
	    // EQV
	{
	    mEQV();

	}
	    break;
	case 42:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:288:
	    // NEQV
	{
	    mNEQV();

	}
	    break;
	case 43:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:293:
	    // XOR
	{
	    mXOR();

	}
	    break;
	case 44:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:297:
	    // EOR
	{
	    mEOR();

	}
	    break;
	case 45:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:301:
	    // LT
	{
	    mLT();

	}
	    break;
	case 46:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:304:
	    // LE
	{
	    mLE();

	}
	    break;
	case 47:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:307:
	    // GT
	{
	    mGT();

	}
	    break;
	case 48:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:310:
	    // GE
	{
	    mGE();

	}
	    break;
	case 49:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:313:
	    // NE
	{
	    mNE();

	}
	    break;
	case 50:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:316:
	    // EQ
	{
	    mEQ();

	}
	    break;
	case 51:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:319:
	    // TRUE
	{
	    mTRUE();

	}
	    break;
	case 52:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:324:
	    // FALSE
	{
	    mFALSE();

	}
	    break;
	case 53:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:330:
	    // ID
	{
	    mID();

	}
	    break;
	case 54:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:333:
	    // INT_CONST
	{
	    mINT_CONST();

	}
	    break;
	case 55:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:343:
	    // HEX_CONST
	{
	    mHEX_CONST();

	}
	    break;
	case 56:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:353:
	    // FLOAT_CONST
	{
	    mFLOAT_CONST();

	}
	    break;
	case 57:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:365:
	    // COMMENT
	{
	    mCOMMENT();

	}
	    break;
	case 58:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:373:
	    // WS
	{
	    mWS();

	}
	    break;
	case 59:
	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:376:
	    // STRING_CONST
	{
	    mSTRING_CONST();

	}
	    break;

	}

    }

    protected DFA12 dfa12 = new DFA12(this);
    protected DFA27 dfa27 = new DFA27(this);
    static final String DFA12_eotS = "\5\uffff";
    static final String DFA12_eofS = "\5\uffff";
    static final String DFA12_minS = "\2\56\3\uffff";
    static final String DFA12_maxS = "\1\71\1\145\3\uffff";
    static final String DFA12_acceptS = "\2\uffff\1\2\1\1\1\3";
    static final String DFA12_specialS = "\5\uffff}>";
    static final String[] DFA12_transitionS =
	    { "\1\2\1\uffff\12\1",
		    "\1\3\1\uffff\12\1\12\uffff\2\4\36\uffff\2\4", "", "",
		    "" };

    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    static final char[] DFA12_min =
	    DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    static final char[] DFA12_max =
	    DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    static final short[] DFA12_accept =
	    DFA.unpackEncodedString(DFA12_acceptS);
    static final short[] DFA12_special =
	    DFA.unpackEncodedString(DFA12_specialS);
    static final short[][] DFA12_transition;

    static {
	int numStates = DFA12_transitionS.length;
	DFA12_transition = new short[numStates][];
	for (int i = 0; i < numStates; i++) {
	    DFA12_transition[i] =
		    DFA.unpackEncodedString(DFA12_transitionS[i]);
	}
    }

    class DFA12 extends DFA {

	public DFA12(BaseRecognizer recognizer) {
	    this.recognizer = recognizer;
	    this.decisionNumber = 12;
	    this.eot = DFA12_eot;
	    this.eof = DFA12_eof;
	    this.min = DFA12_min;
	    this.max = DFA12_max;
	    this.accept = DFA12_accept;
	    this.special = DFA12_special;
	    this.transition = DFA12_transition;
	}

	public String getDescription() {
	    return "205:1: FLOAT_CONST : ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )+ ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT );";
	}
    }

    static final String DFA27_eotS =
	    "\1\uffff\16\32\10\uffff\1\71\1\74\2\uffff\2\110\1\32\3\uffff\6\32"
		    + "\1\122\1\32\1\125\1\32\1\uffff\14\32\2\uffff\1\144\16\uffff\11\32"
		    + "\1\uffff\2\32\1\uffff\12\32\1\u0084\2\32\12\uffff\13\32\1\u0097"
		    + "\4\32\1\u009c\2\32\1\u009f\1\32\1\u00a1\1\32\1\uffff\1\32\1\u00a4"
		    + "\1\32\4\uffff\2\32\1\u009c\10\32\1\uffff\4\32\1\uffff\2\32\1\uffff"
		    + "\1\32\1\uffff\1\32\1\u00b8\1\uffff\1\u00b9\12\32\1\u00c4\7\32\2"
		    + "\uffff\3\32\1\u00cf\3\32\1\u00d3\2\32\2\uffff\1\u00d6\2\32\1\u00d9"
		    + "\2\32\1\u00d6\2\32\1\uffff\2\32\1\u00e0\1\uffff\1\32\1\u00e2\1\uffff"
		    + "\1\u00e3\1\32\1\uffff\1\u00e5\1\u00e6\1\u00e3\1\32\1\u00e8\1\32"
		    + "\1\uffff\1\u00ea\2\uffff\1\u00eb\2\uffff\1\u00eb\1\uffff\1\u00ec"
		    + "\3\uffff";
    static final String DFA27_eofS = "\u00ed\uffff";
    static final String DFA27_minS =
	    "\1\11\1\101\2\125\1\106\1\105\1\117\1\0\2\117\1\114\1\110\1\114"
		    + "\1\110\1\117\10\uffff\1\52\1\0\1\60\1\uffff\2\56\1\101\3\uffff\1"
		    + "\117\1\122\1\102\1\116\1\124\1\120\1\60\1\101\1\60\1\0\1\uffff\3"
		    + "\0\1\107\1\116\1\114\1\105\1\124\1\104\1\123\1\111\1\11\2\uffff"
		    + "\1\0\3\uffff\2\105\1\117\3\uffff\1\105\4\uffff\1\115\1\114\1\101"
		    + "\1\107\1\101\1\122\1\103\1\105\1\114\1\uffff\1\114\1\125\1\uffff"
		    + "\1\102\4\0\1\111\1\105\1\117\1\116\1\105\1\60\1\105\1\114\4\uffff"
		    + "\1\56\2\uffff\1\56\2\uffff\1\120\1\124\1\114\2\122\1\115\1\117\1"
		    + "\124\1\107\2\111\1\60\1\122\1\114\4\0\1\103\1\60\1\103\1\60\1\122"
		    + "\1\uffff\1\117\1\60\1\105\4\uffff\1\114\1\111\1\60\2\101\1\105\1"
		    + "\125\1\111\1\105\1\116\1\103\1\uffff\1\116\1\105\2\0\1\uffff\1\0"
		    + "\1\101\1\uffff\1\101\1\uffff\1\116\1\60\1\uffff\1\60\1\105\1\116"
		    + "\1\103\1\115\2\124\1\117\1\122\1\123\1\111\1\60\1\11\3\0\1\114\1"
		    + "\124\1\101\2\uffff\1\130\1\125\1\124\1\60\1\105\1\111\1\116\1\60"
		    + "\1\111\1\124\2\uffff\3\0\1\60\1\105\1\114\1\60\2\105\1\uffff\1\122"
		    + "\1\116\1\60\1\uffff\1\103\1\60\1\uffff\2\0\1\uffff\3\60\1\122\1"
		    + "\60\1\105\1\uffff\1\60\2\uffff\1\0\2\uffff\1\60\1\uffff\1\60\3\uffff";
    static final String DFA27_maxS =
	    "\1\172\1\162\2\165\1\156\1\145\1\157\1\uffff\2\157\1\154\1\150\1"
		    + "\170\1\150\1\157\10\uffff\1\57\1\uffff\1\170\1\uffff\1\170\1\145"
		    + "\1\157\3\uffff\1\157\1\162\1\142\1\156\1\164\1\160\1\172\1\164\1"
		    + "\172\1\uffff\1\uffff\3\uffff\1\147\1\156\1\154\1\145\1\164\1\144"
		    + "\1\163\1\151\1\40\2\uffff\1\uffff\3\uffff\1\164\1\157\1\161\3\uffff"
		    + "\1\164\4\uffff\1\156\1\154\1\141\1\147\1\141\1\162\1\143\1\162\1"
		    + "\154\1\uffff\1\154\1\165\1\uffff\1\142\4\uffff\1\151\1\145\1\157"
		    + "\1\156\1\145\1\172\1\145\1\154\4\uffff\1\161\2\uffff\1\166\2\uffff"
		    + "\1\160\1\164\1\154\2\162\1\155\1\157\1\164\1\147\2\151\1\172\1\162"
		    + "\1\154\4\uffff\1\143\1\172\1\143\1\172\1\162\1\uffff\1\157\1\172"
		    + "\1\145\4\uffff\1\154\1\151\1\172\2\141\1\145\1\165\1\151\1\145\1"
		    + "\156\1\143\1\uffff\1\156\1\145\2\uffff\1\uffff\1\uffff\1\141\1\uffff"
		    + "\1\141\1\uffff\1\156\1\172\1\uffff\1\172\1\145\1\156\1\143\1\155"
		    + "\2\164\1\157\1\162\1\163\1\151\1\172\1\40\3\uffff\1\154\1\164\1"
		    + "\141\2\uffff\1\170\1\165\1\164\1\172\1\145\1\151\1\156\1\172\1\151"
		    + "\1\164\2\uffff\3\uffff\1\172\1\145\1\154\1\172\2\145\1\uffff\1\162"
		    + "\1\156\1\172\1\uffff\1\143\1\172\1\uffff\2\uffff\1\uffff\3\172\1"
		    + "\162\1\172\1\145\1\uffff\1\172\2\uffff\1\uffff\2\uffff\1\172\1\uffff"
		    + "\1\172\3\uffff";
    static final String DFA27_acceptS =
	    "\17\uffff\1\33\1\34\1\35\1\36\1\37\1\40\1\41\1\42\3\uffff\1\65\3"
		    + "\uffff\1\71\1\72\1\73\12\uffff\1\71\14\uffff\1\71\1\43\1\uffff\1"
		    + "\71\1\45\1\50\3\uffff\1\64\1\70\1\47\1\uffff\1\53\1\63\1\67\1\66"
		    + "\11\uffff\1\16\2\uffff\1\21\15\uffff\1\26\1\44\1\60\1\57\1\uffff"
		    + "\1\46\1\54\1\uffff\1\55\1\56\27\uffff\1\24\3\uffff\1\52\1\61\1\62"
		    + "\1\51\13\uffff\1\5\4\uffff\1\25\2\uffff\1\13\1\uffff\1\17\2\uffff"
		    + "\1\20\23\uffff\1\23\1\22\12\uffff\1\27\1\6\11\uffff\1\1\3\uffff"
		    + "\1\4\2\uffff\1\7\2\uffff\1\11\6\uffff\1\3\1\uffff\1\12\1\30\1\uffff"
		    + "\1\14\1\31\1\uffff\1\15\1\uffff\1\32\1\10\1\2";
    static final String DFA27_specialS =
	    "\1\21\6\uffff\1\7\20\uffff\1\27\21\uffff\1\23\1\uffff\1\13\1\31"
		    + "\1\11\13\uffff\1\17\34\uffff\1\3\1\16\1\12\1\5\40\uffff\1\4\1\25"
		    + "\1\1\1\33\33\uffff\1\26\1\22\1\uffff\1\30\24\uffff\1\6\1\20\1\10"
		    + "\21\uffff\1\15\1\24\1\32\16\uffff\1\0\1\2\13\uffff\1\14\10\uffff}>";
    static final String[] DFA27_transitionS =
	    {
		    "\2\37\2\uffff\1\37\22\uffff\1\37\1\36\2\uffff\1\17\2\uffff\1"
			    + "\40\1\21\1\22\1\30\1\26\1\20\1\25\1\31\1\27\1\33\11\34\1\23"
			    + "\2\uffff\1\24\3\uffff\1\12\1\32\1\35\1\6\1\14\1\3\1\16\1\32"
			    + "\1\4\2\32\1\10\1\32\1\11\1\32\1\1\1\32\1\5\1\2\1\13\2\32\1\15"
			    + "\3\32\4\uffff\1\32\1\uffff\1\12\1\32\1\7\1\6\1\14\1\3\1\16\1"
			    + "\32\1\4\2\32\1\10\1\32\1\11\1\32\1\1\1\32\1\5\1\2\1\13\2\32"
			    + "\1\15\3\32",
		    "\1\42\20\uffff\1\41\16\uffff\1\42\20\uffff\1\41",
		    "\1\43\37\uffff\1\43",
		    "\1\44\37\uffff\1\44",
		    "\1\47\6\uffff\1\46\1\45\27\uffff\1\47\6\uffff\1\46\1\45",
		    "\1\50\37\uffff\1\50",
		    "\1\51\37\uffff\1\51",
		    "\60\53\12\56\7\53\1\54\6\56\1\55\6\56\1\52\13\56\4\53\1\56"
			    + "\1\53\1\54\6\56\1\55\6\56\1\52\13\56\uff85\53",
		    "\1\57\37\uffff\1\57",
		    "\1\60\37\uffff\1\60",
		    "\1\61\37\uffff\1\61",
		    "\1\62\37\uffff\1\62",
		    "\1\65\1\uffff\1\64\11\uffff\1\63\23\uffff\1\65\1\uffff\1\64"
			    + "\11\uffff\1\63",
		    "\1\66\37\uffff\1\66",
		    "\1\67\37\uffff\1\67",
		    "",
		    "",
		    "",
		    "",
		    "",
		    "",
		    "",
		    "",
		    "\1\70\4\uffff\1\70",
		    "\15\73\1\53\34\73\1\72\uffd5\73",
		    "\12\102\7\uffff\1\103\3\uffff\1\100\1\101\1\76\4\uffff\1\104"
			    + "\1\uffff\1\77\1\75\4\uffff\1\106\3\uffff\1\105\10\uffff\1\103"
			    + "\3\uffff\1\100\1\101\1\76\4\uffff\1\104\1\uffff\1\77\1\75\4"
			    + "\uffff\1\106\3\uffff\1\105",
		    "",
		    "\1\102\1\uffff\12\34\12\uffff\2\102\36\uffff\2\102\22\uffff"
			    + "\1\107",
		    "\1\102\1\uffff\12\34\12\uffff\2\102\36\uffff\2\102",
		    "\1\112\6\uffff\1\113\6\uffff\1\111\21\uffff\1\112\6\uffff\1"
			    + "\113\6\uffff\1\111",
		    "",
		    "",
		    "",
		    "\1\114\37\uffff\1\114",
		    "\1\115\37\uffff\1\115",
		    "\1\116\37\uffff\1\116",
		    "\1\117\37\uffff\1\117",
		    "\1\120\37\uffff\1\120",
		    "\1\121\37\uffff\1\121",
		    "\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32",
		    "\1\123\22\uffff\1\124\14\uffff\1\123\22\uffff\1\124",
		    "\12\32\7\uffff\24\32\1\126\5\32\4\uffff\1\32\1\uffff\24\32"
			    + "\1\126\5\32",
		    "\15\73\1\53\42\73\12\56\7\73\14\56\1\127\1\130\14\56\4\73\1"
			    + "\56\1\73\14\56\1\127\1\130\14\56\uff85\73",
		    "",
		    "\15\73\1\53\42\73\12\56\7\73\13\56\1\131\16\56\4\73\1\56\1"
			    + "\73\13\56\1\131\16\56\uff85\73",
		    "\15\73\1\53\42\73\12\56\7\73\1\132\31\56\4\73\1\56\1\73\1\132"
			    + "\31\56\uff85\73",
		    "\15\73\1\53\42\73\12\56\7\73\32\56\4\73\1\56\1\73\32\56\uff85"
			    + "\73",
		    "\1\133\37\uffff\1\133",
		    "\1\134\37\uffff\1\134",
		    "\1\135\37\uffff\1\135",
		    "\1\136\37\uffff\1\136",
		    "\1\137\37\uffff\1\137",
		    "\1\140\37\uffff\1\140",
		    "\1\141\37\uffff\1\141",
		    "\1\142\37\uffff\1\142",
		    "\2\143\2\uffff\1\143\22\uffff\1\143",
		    "",
		    "",
		    "\15\73\1\53\ufff2\73",
		    "",
		    "",
		    "",
		    "\1\145\16\uffff\1\146\20\uffff\1\145\16\uffff\1\146",
		    "\1\147\11\uffff\1\150\25\uffff\1\147\11\uffff\1\150",
		    "\1\151\1\uffff\1\152\35\uffff\1\151\1\uffff\1\152",
		    "",
		    "",
		    "",
		    "\1\154\16\uffff\1\153\20\uffff\1\154\16\uffff\1\153",
		    "",
		    "",
		    "",
		    "",
		    "\1\155\1\156\36\uffff\1\155\1\156",
		    "\1\157\37\uffff\1\157",
		    "\1\160\37\uffff\1\160",
		    "\1\161\37\uffff\1\161",
		    "\1\162\37\uffff\1\162",
		    "\1\163\37\uffff\1\163",
		    "\1\164\37\uffff\1\164",
		    "\1\165\14\uffff\1\166\22\uffff\1\165\14\uffff\1\166",
		    "\1\167\37\uffff\1\167",
		    "",
		    "\1\170\37\uffff\1\170",
		    "\1\171\37\uffff\1\171",
		    "",
		    "\1\172\37\uffff\1\172",
		    "\15\73\1\53\42\73\12\56\7\73\17\56\1\173\12\56\4\73\1\56\1"
			    + "\73\17\56\1\173\12\56\uff85\73",
		    "\15\73\1\53\42\73\12\56\7\73\23\56\1\174\6\56\4\73\1\56\1\73"
			    + "\23\56\1\174\6\56\uff85\73",
		    "\15\73\1\53\42\73\12\56\7\73\13\56\1\175\16\56\4\73\1\56\1"
			    + "\73\13\56\1\175\16\56\uff85\73",
		    "\15\73\1\53\42\73\12\56\7\73\21\56\1\176\10\56\4\73\1\56\1"
			    + "\73\21\56\1\176\10\56\uff85\73",
		    "\1\177\37\uffff\1\177",
		    "\1\u0080\37\uffff\1\u0080",
		    "\1\u0081\37\uffff\1\u0081",
		    "\1\u0082\37\uffff\1\u0082",
		    "\1\u0083\37\uffff\1\u0083",
		    "\12\32\7\uffff\3\32\1\u0085\26\32\4\uffff\1\32\1\uffff\3\32"
			    + "\1\u0085\26\32",
		    "\1\u0086\37\uffff\1\u0086",
		    "\1\u0087\37\uffff\1\u0087",
		    "",
		    "",
		    "",
		    "",
		    "\1\u0089\42\uffff\1\u0088\37\uffff\1\u0088",
		    "",
		    "",
		    "\1\u008a\47\uffff\1\u008b\37\uffff\1\u008b",
		    "",
		    "",
		    "\1\u008c\37\uffff\1\u008c",
		    "\1\u008d\37\uffff\1\u008d",
		    "\1\u008e\37\uffff\1\u008e",
		    "\1\u008f\37\uffff\1\u008f",
		    "\1\u0090\37\uffff\1\u0090",
		    "\1\u0091\37\uffff\1\u0091",
		    "\1\u0092\37\uffff\1\u0092",
		    "\1\u0093\37\uffff\1\u0093",
		    "\1\u0094\37\uffff\1\u0094",
		    "\1\u0095\37\uffff\1\u0095",
		    "\1\u0096\37\uffff\1\u0096",
		    "\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32",
		    "\1\u0098\37\uffff\1\u0098",
		    "\1\u0099\37\uffff\1\u0099",
		    "\15\73\1\53\42\73\12\56\7\73\13\56\1\u009a\16\56\4\73\1\56"
			    + "\1\73\13\56\1\u009a\16\56\uff85\73",
		    "\15\73\1\53\42\73\12\56\7\73\10\56\1\u009b\21\56\4\73\1\56"
			    + "\1\73\10\56\1\u009b\21\56\uff85\73",
		    "\15\73\1\53\42\73\12\56\7\73\32\56\4\73\1\56\1\73\32\56\uff85"
			    + "\73",
		    "\15\73\1\53\42\73\12\56\7\73\1\u009d\31\56\4\73\1\56\1\73\1"
			    + "\u009d\31\56\uff85\73",
		    "\1\u009e\37\uffff\1\u009e",
		    "\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32",
		    "\1\u00a0\37\uffff\1\u00a0",
		    "\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32",
		    "\1\u00a2\37\uffff\1\u00a2",
		    "",
		    "\1\u00a3\37\uffff\1\u00a3",
		    "\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32",
		    "\1\u00a5\37\uffff\1\u00a5",
		    "",
		    "",
		    "",
		    "",
		    "\1\u00a6\37\uffff\1\u00a6",
		    "\1\u00a7\37\uffff\1\u00a7",
		    "\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32",
		    "\1\u00a8\37\uffff\1\u00a8",
		    "\1\u00a9\37\uffff\1\u00a9",
		    "\1\u00aa\37\uffff\1\u00aa",
		    "\1\u00ab\37\uffff\1\u00ab",
		    "\1\u00ac\37\uffff\1\u00ac",
		    "\1\u00ad\37\uffff\1\u00ad",
		    "\1\u00ae\37\uffff\1\u00ae",
		    "\1\u00af\37\uffff\1\u00af",
		    "",
		    "\1\u00b0\37\uffff\1\u00b0",
		    "\1\u00b1\37\uffff\1\u00b1",
		    "\15\73\1\53\42\73\12\56\7\73\4\56\1\u00b2\25\56\4\73\1\56\1"
			    + "\73\4\56\1\u00b2\25\56\uff85\73",
		    "\15\73\1\53\42\73\12\56\7\73\15\56\1\u00b3\14\56\4\73\1\56"
			    + "\1\73\15\56\1\u00b3\14\56\uff85\73",
		    "",
		    "\15\73\1\53\42\73\12\56\7\73\2\56\1\u00b4\27\56\4\73\1\56\1"
			    + "\73\2\56\1\u00b4\27\56\uff85\73",
		    "\1\u00b5\37\uffff\1\u00b5",
		    "",
		    "\1\u00b6\37\uffff\1\u00b6",
		    "",
		    "\1\u00b7\37\uffff\1\u00b7",
		    "\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32",
		    "",
		    "\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32",
		    "\1\u00ba\37\uffff\1\u00ba",
		    "\1\u00bb\37\uffff\1\u00bb",
		    "\1\u00bc\37\uffff\1\u00bc",
		    "\1\u00bd\37\uffff\1\u00bd",
		    "\1\u00be\37\uffff\1\u00be",
		    "\1\u00bf\37\uffff\1\u00bf",
		    "\1\u00c0\37\uffff\1\u00c0",
		    "\1\u00c1\37\uffff\1\u00c1",
		    "\1\u00c2\37\uffff\1\u00c2",
		    "\1\u00c3\37\uffff\1\u00c3",
		    "\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32",
		    "\2\u00c5\2\uffff\1\u00c5\22\uffff\1\u00c5",
		    "\15\73\1\53\42\73\12\56\7\73\27\56\1\u00c6\2\56\4\73\1\56\1"
			    + "\73\27\56\1\u00c6\2\56\uff85\73",
		    "\15\73\1\53\42\73\12\56\7\73\24\56\1\u00c7\5\56\4\73\1\56\1"
			    + "\73\24\56\1\u00c7\5\56\uff85\73",
		    "\15\73\1\53\42\73\12\56\7\73\23\56\1\u00c8\6\56\4\73\1\56\1"
			    + "\73\23\56\1\u00c8\6\56\uff85\73",
		    "\1\u00c9\37\uffff\1\u00c9",
		    "\1\u00ca\37\uffff\1\u00ca",
		    "\1\u00cb\37\uffff\1\u00cb",
		    "",
		    "",
		    "\1\u00cc\37\uffff\1\u00cc",
		    "\1\u00cd\37\uffff\1\u00cd",
		    "\1\u00ce\37\uffff\1\u00ce",
		    "\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32",
		    "\1\u00d0\37\uffff\1\u00d0",
		    "\1\u00d1\37\uffff\1\u00d1",
		    "\1\u00d2\37\uffff\1\u00d2",
		    "\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32",
		    "\1\u00d4\37\uffff\1\u00d4",
		    "\1\u00d5\37\uffff\1\u00d5",
		    "",
		    "",
		    "\15\73\1\53\42\73\12\56\7\73\32\56\4\73\1\56\1\73\32\56\uff85"
			    + "\73",
		    "\15\73\1\53\42\73\12\56\7\73\4\56\1\u00d7\25\56\4\73\1\56\1"
			    + "\73\4\56\1\u00d7\25\56\uff85\73",
		    "\15\73\1\53\42\73\12\56\7\73\4\56\1\u00d8\25\56\4\73\1\56\1"
			    + "\73\4\56\1\u00d8\25\56\uff85\73",
		    "\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32",
		    "\1\u00da\37\uffff\1\u00da",
		    "\1\u00db\37\uffff\1\u00db",
		    "\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32",
		    "\1\u00dc\37\uffff\1\u00dc",
		    "\1\u00dd\37\uffff\1\u00dd",
		    "",
		    "\1\u00de\37\uffff\1\u00de",
		    "\1\u00df\37\uffff\1\u00df",
		    "\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32",
		    "",
		    "\1\u00e1\37\uffff\1\u00e1",
		    "\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32",
		    "",
		    "\15\73\1\53\42\73\12\56\7\73\32\56\4\73\1\56\1\73\32\56\uff85"
			    + "\73",
		    "\15\73\1\53\42\73\12\56\7\73\21\56\1\u00e4\10\56\4\73\1\56"
			    + "\1\73\21\56\1\u00e4\10\56\uff85\73",
		    "",
		    "\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32",
		    "\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32",
		    "\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32",
		    "\1\u00e7\37\uffff\1\u00e7",
		    "\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32",
		    "\1\u00e9\37\uffff\1\u00e9",
		    "",
		    "\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32",
		    "",
		    "",
		    "\15\73\1\53\42\73\12\56\7\73\32\56\4\73\1\56\1\73\32\56\uff85"
			    + "\73", "", "",
		    "\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32", "",
		    "\12\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32", "",
		    "", "" };

    static final short[] DFA27_eot = DFA.unpackEncodedString(DFA27_eotS);
    static final short[] DFA27_eof = DFA.unpackEncodedString(DFA27_eofS);
    static final char[] DFA27_min =
	    DFA.unpackEncodedStringToUnsignedChars(DFA27_minS);
    static final char[] DFA27_max =
	    DFA.unpackEncodedStringToUnsignedChars(DFA27_maxS);
    static final short[] DFA27_accept =
	    DFA.unpackEncodedString(DFA27_acceptS);
    static final short[] DFA27_special =
	    DFA.unpackEncodedString(DFA27_specialS);
    static final short[][] DFA27_transition;

    static {
	int numStates = DFA27_transitionS.length;
	DFA27_transition = new short[numStates][];
	for (int i = 0; i < numStates; i++) {
	    DFA27_transition[i] =
		    DFA.unpackEncodedString(DFA27_transitionS[i]);
	}
    }

    class DFA27 extends DFA {

	public DFA27(BaseRecognizer recognizer) {
	    this.recognizer = recognizer;
	    this.decisionNumber = 27;
	    this.eot = DFA27_eot;
	    this.eof = DFA27_eof;
	    this.min = DFA27_min;
	    this.max = DFA27_max;
	    this.accept = DFA27_accept;
	    this.special = DFA27_special;
	    this.transition = DFA27_transition;
	}

	public String getDescription() {
	    return "1:1: Tokens : ( PROGRAM | SUBROUTINE | FUNCTION | INTEGER | REAL | DOUBLE_PRECISION | COMPLEX | CHARACTER | LOGICAL | IMPLICIT | NONE | ALLOCATE | PARAMETER | IF | THEN | ELSE | DO | WHILE | ENDDO | END | CALL | GOTO | RETURN | CONTINUE | EXTERNAL | INTRINSIC | DOLLAR | COMMA | LPAREN | RPAREN | COLON | ASSIGN | MINUS | PLUS | DIV | POWER | STAR | LNOT | LAND | LOR | EQV | NEQV | XOR | EOR | LT | LE | GT | GE | NE | EQ | TRUE | FALSE | ID | INT_CONST | HEX_CONST | FLOAT_CONST | COMMENT | WS | STRING_CONST );";
	}

	public int specialStateTransition(int s, IntStream _input)
		throws NoViableAltException {
	    IntStream input = _input;
	    int _s = s;
	    switch (s) {
	    case 0:
		int LA27_215 = input.LA(1);

		int index27_215 = input.index();
		input.rewind();
		s = -1;
		if (((LA27_215 >= '0' && LA27_215 <= '9')
			|| (LA27_215 >= 'A' && LA27_215 <= 'Z')
			|| LA27_215 == '_' || (LA27_215 >= 'a' && LA27_215 <= 'z'))) {
		    s = 46;
		}

		else if ((LA27_215 == '\r')
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if (((LA27_215 >= '\u0000' && LA27_215 <= '\f')
			|| (LA27_215 >= '\u000E' && LA27_215 <= '/')
			|| (LA27_215 >= ':' && LA27_215 <= '@')
			|| (LA27_215 >= '[' && LA27_215 <= '^')
			|| LA27_215 == '`' || (LA27_215 >= '{' && LA27_215 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else
		    s = 227;

		input.seek(index27_215);
		if (s >= 0)
		    return s;
		break;
	    case 1:
		int LA27_125 = input.LA(1);

		int index27_125 = input.index();
		input.rewind();
		s = -1;
		if (((LA27_125 >= '0' && LA27_125 <= '9')
			|| (LA27_125 >= 'A' && LA27_125 <= 'Z')
			|| LA27_125 == '_' || (LA27_125 >= 'a' && LA27_125 <= 'z'))) {
		    s = 46;
		}

		else if ((LA27_125 == '\r')
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if (((LA27_125 >= '\u0000' && LA27_125 <= '\f')
			|| (LA27_125 >= '\u000E' && LA27_125 <= '/')
			|| (LA27_125 >= ':' && LA27_125 <= '@')
			|| (LA27_125 >= '[' && LA27_125 <= '^')
			|| LA27_125 == '`' || (LA27_125 >= '{' && LA27_125 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else
		    s = 156;

		input.seek(index27_125);
		if (s >= 0)
		    return s;
		break;
	    case 2:
		int LA27_216 = input.LA(1);

		int index27_216 = input.index();
		input.rewind();
		s = -1;
		if ((LA27_216 == 'R' || LA27_216 == 'r')) {
		    s = 228;
		}

		else if (((LA27_216 >= '0' && LA27_216 <= '9')
			|| (LA27_216 >= 'A' && LA27_216 <= 'Q')
			|| (LA27_216 >= 'S' && LA27_216 <= 'Z')
			|| LA27_216 == '_'
			|| (LA27_216 >= 'a' && LA27_216 <= 'q') || (LA27_216 >= 's' && LA27_216 <= 'z'))) {
		    s = 46;
		}

		else if ((LA27_216 == '\r')
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if (((LA27_216 >= '\u0000' && LA27_216 <= '\f')
			|| (LA27_216 >= '\u000E' && LA27_216 <= '/')
			|| (LA27_216 >= ':' && LA27_216 <= '@')
			|| (LA27_216 >= '[' && LA27_216 <= '^')
			|| LA27_216 == '`' || (LA27_216 >= '{' && LA27_216 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else
		    s = 26;

		input.seek(index27_216);
		if (s >= 0)
		    return s;
		break;
	    case 3:
		int LA27_87 = input.LA(1);

		int index27_87 = input.index();
		input.rewind();
		s = -1;
		if ((LA27_87 == 'P' || LA27_87 == 'p')) {
		    s = 123;
		}

		else if ((LA27_87 == '\r')
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if (((LA27_87 >= '\u0000' && LA27_87 <= '\f')
			|| (LA27_87 >= '\u000E' && LA27_87 <= '/')
			|| (LA27_87 >= ':' && LA27_87 <= '@')
			|| (LA27_87 >= '[' && LA27_87 <= '^')
			|| LA27_87 == '`' || (LA27_87 >= '{' && LA27_87 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else if (((LA27_87 >= '0' && LA27_87 <= '9')
			|| (LA27_87 >= 'A' && LA27_87 <= 'O')
			|| (LA27_87 >= 'Q' && LA27_87 <= 'Z')
			|| LA27_87 == '_'
			|| (LA27_87 >= 'a' && LA27_87 <= 'o') || (LA27_87 >= 'q' && LA27_87 <= 'z'))) {
		    s = 46;
		}

		else
		    s = 26;

		input.seek(index27_87);
		if (s >= 0)
		    return s;
		break;
	    case 4:
		int LA27_123 = input.LA(1);

		int index27_123 = input.index();
		input.rewind();
		s = -1;
		if ((LA27_123 == 'L' || LA27_123 == 'l')) {
		    s = 154;
		}

		else if (((LA27_123 >= '0' && LA27_123 <= '9')
			|| (LA27_123 >= 'A' && LA27_123 <= 'K')
			|| (LA27_123 >= 'M' && LA27_123 <= 'Z')
			|| LA27_123 == '_'
			|| (LA27_123 >= 'a' && LA27_123 <= 'k') || (LA27_123 >= 'm' && LA27_123 <= 'z'))) {
		    s = 46;
		}

		else if ((LA27_123 == '\r')
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if (((LA27_123 >= '\u0000' && LA27_123 <= '\f')
			|| (LA27_123 >= '\u000E' && LA27_123 <= '/')
			|| (LA27_123 >= ':' && LA27_123 <= '@')
			|| (LA27_123 >= '[' && LA27_123 <= '^')
			|| LA27_123 == '`' || (LA27_123 >= '{' && LA27_123 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else
		    s = 26;

		input.seek(index27_123);
		if (s >= 0)
		    return s;
		break;
	    case 5:
		int LA27_90 = input.LA(1);

		int index27_90 = input.index();
		input.rewind();
		s = -1;
		if ((LA27_90 == 'R' || LA27_90 == 'r')) {
		    s = 126;
		}

		else if (((LA27_90 >= '0' && LA27_90 <= '9')
			|| (LA27_90 >= 'A' && LA27_90 <= 'Q')
			|| (LA27_90 >= 'S' && LA27_90 <= 'Z')
			|| LA27_90 == '_'
			|| (LA27_90 >= 'a' && LA27_90 <= 'q') || (LA27_90 >= 's' && LA27_90 <= 'z'))) {
		    s = 46;
		}

		else if ((LA27_90 == '\r')
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if (((LA27_90 >= '\u0000' && LA27_90 <= '\f')
			|| (LA27_90 >= '\u000E' && LA27_90 <= '/')
			|| (LA27_90 >= ':' && LA27_90 <= '@')
			|| (LA27_90 >= '[' && LA27_90 <= '^')
			|| LA27_90 == '`' || (LA27_90 >= '{' && LA27_90 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else
		    s = 26;

		input.seek(index27_90);
		if (s >= 0)
		    return s;
		break;
	    case 6:
		int LA27_178 = input.LA(1);

		int index27_178 = input.index();
		input.rewind();
		s = -1;
		if ((LA27_178 == 'X' || LA27_178 == 'x')) {
		    s = 198;
		}

		else if (((LA27_178 >= '0' && LA27_178 <= '9')
			|| (LA27_178 >= 'A' && LA27_178 <= 'W')
			|| (LA27_178 >= 'Y' && LA27_178 <= 'Z')
			|| LA27_178 == '_'
			|| (LA27_178 >= 'a' && LA27_178 <= 'w') || (LA27_178 >= 'y' && LA27_178 <= 'z'))) {
		    s = 46;
		}

		else if ((LA27_178 == '\r')
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if (((LA27_178 >= '\u0000' && LA27_178 <= '\f')
			|| (LA27_178 >= '\u000E' && LA27_178 <= '/')
			|| (LA27_178 >= ':' && LA27_178 <= '@')
			|| (LA27_178 >= '[' && LA27_178 <= '^')
			|| LA27_178 == '`' || (LA27_178 >= '{' && LA27_178 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else
		    s = 26;

		input.seek(index27_178);
		if (s >= 0)
		    return s;
		break;
	    case 7:
		int LA27_7 = input.LA(1);

		int index27_7 = input.index();
		input.rewind();
		s = -1;
		if ((LA27_7 == 'O' || LA27_7 == 'o')) {
		    s = 42;
		}

		else if (((LA27_7 >= '\u0000' && LA27_7 <= '/')
			|| (LA27_7 >= ':' && LA27_7 <= '@')
			|| (LA27_7 >= '[' && LA27_7 <= '^')
			|| LA27_7 == '`' || (LA27_7 >= '{' && LA27_7 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if ((LA27_7 == 'A' || LA27_7 == 'a')) {
		    s = 44;
		}

		else if ((LA27_7 == 'H' || LA27_7 == 'h')) {
		    s = 45;
		}

		else if (((LA27_7 >= '0' && LA27_7 <= '9')
			|| (LA27_7 >= 'B' && LA27_7 <= 'G')
			|| (LA27_7 >= 'I' && LA27_7 <= 'N')
			|| (LA27_7 >= 'P' && LA27_7 <= 'Z')
			|| LA27_7 == '_'
			|| (LA27_7 >= 'b' && LA27_7 <= 'g')
			|| (LA27_7 >= 'i' && LA27_7 <= 'n') || (LA27_7 >= 'p' && LA27_7 <= 'z'))) {
		    s = 46;
		}

		else
		    s = 26;

		input.seek(index27_7);
		if (s >= 0)
		    return s;
		break;
	    case 8:
		int LA27_180 = input.LA(1);

		int index27_180 = input.index();
		input.rewind();
		s = -1;
		if ((LA27_180 == 'T' || LA27_180 == 't')) {
		    s = 200;
		}

		else if (((LA27_180 >= '0' && LA27_180 <= '9')
			|| (LA27_180 >= 'A' && LA27_180 <= 'S')
			|| (LA27_180 >= 'U' && LA27_180 <= 'Z')
			|| LA27_180 == '_'
			|| (LA27_180 >= 'a' && LA27_180 <= 's') || (LA27_180 >= 'u' && LA27_180 <= 'z'))) {
		    s = 46;
		}

		else if ((LA27_180 == '\r')
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if (((LA27_180 >= '\u0000' && LA27_180 <= '\f')
			|| (LA27_180 >= '\u000E' && LA27_180 <= '/')
			|| (LA27_180 >= ':' && LA27_180 <= '@')
			|| (LA27_180 >= '[' && LA27_180 <= '^')
			|| LA27_180 == '`' || (LA27_180 >= '{' && LA27_180 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else
		    s = 26;

		input.seek(index27_180);
		if (s >= 0)
		    return s;
		break;
	    case 9:
		int LA27_46 = input.LA(1);

		int index27_46 = input.index();
		input.rewind();
		s = -1;
		if (((LA27_46 >= '0' && LA27_46 <= '9')
			|| (LA27_46 >= 'A' && LA27_46 <= 'Z')
			|| LA27_46 == '_' || (LA27_46 >= 'a' && LA27_46 <= 'z'))) {
		    s = 46;
		}

		else if ((LA27_46 == '\r')
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if (((LA27_46 >= '\u0000' && LA27_46 <= '\f')
			|| (LA27_46 >= '\u000E' && LA27_46 <= '/')
			|| (LA27_46 >= ':' && LA27_46 <= '@')
			|| (LA27_46 >= '[' && LA27_46 <= '^')
			|| LA27_46 == '`' || (LA27_46 >= '{' && LA27_46 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else
		    s = 26;

		input.seek(index27_46);
		if (s >= 0)
		    return s;
		break;
	    case 10:
		int LA27_89 = input.LA(1);

		int index27_89 = input.index();
		input.rewind();
		s = -1;
		if ((LA27_89 == 'L' || LA27_89 == 'l')) {
		    s = 125;
		}

		else if (((LA27_89 >= '0' && LA27_89 <= '9')
			|| (LA27_89 >= 'A' && LA27_89 <= 'K')
			|| (LA27_89 >= 'M' && LA27_89 <= 'Z')
			|| LA27_89 == '_'
			|| (LA27_89 >= 'a' && LA27_89 <= 'k') || (LA27_89 >= 'm' && LA27_89 <= 'z'))) {
		    s = 46;
		}

		else if ((LA27_89 == '\r')
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if (((LA27_89 >= '\u0000' && LA27_89 <= '\f')
			|| (LA27_89 >= '\u000E' && LA27_89 <= '/')
			|| (LA27_89 >= ':' && LA27_89 <= '@')
			|| (LA27_89 >= '[' && LA27_89 <= '^')
			|| LA27_89 == '`' || (LA27_89 >= '{' && LA27_89 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else
		    s = 26;

		input.seek(index27_89);
		if (s >= 0)
		    return s;
		break;
	    case 11:
		int LA27_44 = input.LA(1);

		int index27_44 = input.index();
		input.rewind();
		s = -1;
		if ((LA27_44 == 'L' || LA27_44 == 'l')) {
		    s = 89;
		}

		else if (((LA27_44 >= '0' && LA27_44 <= '9')
			|| (LA27_44 >= 'A' && LA27_44 <= 'K')
			|| (LA27_44 >= 'M' && LA27_44 <= 'Z')
			|| LA27_44 == '_'
			|| (LA27_44 >= 'a' && LA27_44 <= 'k') || (LA27_44 >= 'm' && LA27_44 <= 'z'))) {
		    s = 46;
		}

		else if ((LA27_44 == '\r')
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if (((LA27_44 >= '\u0000' && LA27_44 <= '\f')
			|| (LA27_44 >= '\u000E' && LA27_44 <= '/')
			|| (LA27_44 >= ':' && LA27_44 <= '@')
			|| (LA27_44 >= '[' && LA27_44 <= '^')
			|| LA27_44 == '`' || (LA27_44 >= '{' && LA27_44 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else
		    s = 26;

		input.seek(index27_44);
		if (s >= 0)
		    return s;
		break;
	    case 12:
		int LA27_228 = input.LA(1);

		int index27_228 = input.index();
		input.rewind();
		s = -1;
		if (((LA27_228 >= '0' && LA27_228 <= '9')
			|| (LA27_228 >= 'A' && LA27_228 <= 'Z')
			|| LA27_228 == '_' || (LA27_228 >= 'a' && LA27_228 <= 'z'))) {
		    s = 46;
		}

		else if ((LA27_228 == '\r')
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if (((LA27_228 >= '\u0000' && LA27_228 <= '\f')
			|| (LA27_228 >= '\u000E' && LA27_228 <= '/')
			|| (LA27_228 >= ':' && LA27_228 <= '@')
			|| (LA27_228 >= '[' && LA27_228 <= '^')
			|| LA27_228 == '`' || (LA27_228 >= '{' && LA27_228 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else
		    s = 235;

		input.seek(index27_228);
		if (s >= 0)
		    return s;
		break;
	    case 13:
		int LA27_198 = input.LA(1);

		int index27_198 = input.index();
		input.rewind();
		s = -1;
		if (((LA27_198 >= '0' && LA27_198 <= '9')
			|| (LA27_198 >= 'A' && LA27_198 <= 'Z')
			|| LA27_198 == '_' || (LA27_198 >= 'a' && LA27_198 <= 'z'))) {
		    s = 46;
		}

		else if ((LA27_198 == '\r')
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if (((LA27_198 >= '\u0000' && LA27_198 <= '\f')
			|| (LA27_198 >= '\u000E' && LA27_198 <= '/')
			|| (LA27_198 >= ':' && LA27_198 <= '@')
			|| (LA27_198 >= '[' && LA27_198 <= '^')
			|| LA27_198 == '`' || (LA27_198 >= '{' && LA27_198 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else
		    s = 214;

		input.seek(index27_198);
		if (s >= 0)
		    return s;
		break;
	    case 14:
		int LA27_88 = input.LA(1);

		int index27_88 = input.index();
		input.rewind();
		s = -1;
		if ((LA27_88 == 'T' || LA27_88 == 't')) {
		    s = 124;
		}

		else if (((LA27_88 >= '0' && LA27_88 <= '9')
			|| (LA27_88 >= 'A' && LA27_88 <= 'S')
			|| (LA27_88 >= 'U' && LA27_88 <= 'Z')
			|| LA27_88 == '_'
			|| (LA27_88 >= 'a' && LA27_88 <= 's') || (LA27_88 >= 'u' && LA27_88 <= 'z'))) {
		    s = 46;
		}

		else if ((LA27_88 == '\r')
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if (((LA27_88 >= '\u0000' && LA27_88 <= '\f')
			|| (LA27_88 >= '\u000E' && LA27_88 <= '/')
			|| (LA27_88 >= ':' && LA27_88 <= '@')
			|| (LA27_88 >= '[' && LA27_88 <= '^')
			|| LA27_88 == '`' || (LA27_88 >= '{' && LA27_88 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else
		    s = 26;

		input.seek(index27_88);
		if (s >= 0)
		    return s;
		break;
	    case 15:
		int LA27_58 = input.LA(1);

		int index27_58 = input.index();
		input.rewind();
		s = -1;
		if ((LA27_58 == '\r') && ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if (((LA27_58 >= '\u0000' && LA27_58 <= '\f') || (LA27_58 >= '\u000E' && LA27_58 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else
		    s = 100;

		input.seek(index27_58);
		if (s >= 0)
		    return s;
		break;
	    case 16:
		int LA27_179 = input.LA(1);

		int index27_179 = input.index();
		input.rewind();
		s = -1;
		if ((LA27_179 == 'U' || LA27_179 == 'u')) {
		    s = 199;
		}

		else if (((LA27_179 >= '0' && LA27_179 <= '9')
			|| (LA27_179 >= 'A' && LA27_179 <= 'T')
			|| (LA27_179 >= 'V' && LA27_179 <= 'Z')
			|| LA27_179 == '_'
			|| (LA27_179 >= 'a' && LA27_179 <= 't') || (LA27_179 >= 'v' && LA27_179 <= 'z'))) {
		    s = 46;
		}

		else if ((LA27_179 == '\r')
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if (((LA27_179 >= '\u0000' && LA27_179 <= '\f')
			|| (LA27_179 >= '\u000E' && LA27_179 <= '/')
			|| (LA27_179 >= ':' && LA27_179 <= '@')
			|| (LA27_179 >= '[' && LA27_179 <= '^')
			|| LA27_179 == '`' || (LA27_179 >= '{' && LA27_179 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else
		    s = 26;

		input.seek(index27_179);
		if (s >= 0)
		    return s;
		break;
	    case 17:
		int LA27_0 = input.LA(1);

		int index27_0 = input.index();
		input.rewind();
		s = -1;
		if ((LA27_0 == 'P' || LA27_0 == 'p')) {
		    s = 1;
		}

		else if ((LA27_0 == 'S' || LA27_0 == 's')) {
		    s = 2;
		}

		else if ((LA27_0 == 'F' || LA27_0 == 'f')) {
		    s = 3;
		}

		else if ((LA27_0 == 'I' || LA27_0 == 'i')) {
		    s = 4;
		}

		else if ((LA27_0 == 'R' || LA27_0 == 'r')) {
		    s = 5;
		}

		else if ((LA27_0 == 'D' || LA27_0 == 'd')) {
		    s = 6;
		}

		else if ((LA27_0 == 'c')) {
		    s = 7;
		}

		else if ((LA27_0 == 'L' || LA27_0 == 'l')) {
		    s = 8;
		}

		else if ((LA27_0 == 'N' || LA27_0 == 'n')) {
		    s = 9;
		}

		else if ((LA27_0 == 'A' || LA27_0 == 'a')) {
		    s = 10;
		}

		else if ((LA27_0 == 'T' || LA27_0 == 't')) {
		    s = 11;
		}

		else if ((LA27_0 == 'E' || LA27_0 == 'e')) {
		    s = 12;
		}

		else if ((LA27_0 == 'W' || LA27_0 == 'w')) {
		    s = 13;
		}

		else if ((LA27_0 == 'G' || LA27_0 == 'g')) {
		    s = 14;
		}

		else if ((LA27_0 == '$')) {
		    s = 15;
		}

		else if ((LA27_0 == ',')) {
		    s = 16;
		}

		else if ((LA27_0 == '(')) {
		    s = 17;
		}

		else if ((LA27_0 == ')')) {
		    s = 18;
		}

		else if ((LA27_0 == ':')) {
		    s = 19;
		}

		else if ((LA27_0 == '=')) {
		    s = 20;
		}

		else if ((LA27_0 == '-')) {
		    s = 21;
		}

		else if ((LA27_0 == '+')) {
		    s = 22;
		}

		else if ((LA27_0 == '/')) {
		    s = 23;
		}

		else if ((LA27_0 == '*')
			&& (((getCharPositionInLine() > 0) || (getCharPositionInLine() == 0)))) {
		    s = 24;
		}

		else if ((LA27_0 == '.')) {
		    s = 25;
		}

		else if ((LA27_0 == 'B' || LA27_0 == 'H'
			|| (LA27_0 >= 'J' && LA27_0 <= 'K')
			|| LA27_0 == 'M' || LA27_0 == 'O' || LA27_0 == 'Q'
			|| (LA27_0 >= 'U' && LA27_0 <= 'V')
			|| (LA27_0 >= 'X' && LA27_0 <= 'Z')
			|| LA27_0 == '_' || LA27_0 == 'b' || LA27_0 == 'h'
			|| (LA27_0 >= 'j' && LA27_0 <= 'k')
			|| LA27_0 == 'm' || LA27_0 == 'o' || LA27_0 == 'q'
			|| (LA27_0 >= 'u' && LA27_0 <= 'v') || (LA27_0 >= 'x' && LA27_0 <= 'z'))) {
		    s = 26;
		}

		else if ((LA27_0 == '0')) {
		    s = 27;
		}

		else if (((LA27_0 >= '1' && LA27_0 <= '9'))) {
		    s = 28;
		}

		else if ((LA27_0 == 'C')) {
		    s = 29;
		}

		else if ((LA27_0 == '!')) {
		    s = 30;
		}

		else if (((LA27_0 >= '\t' && LA27_0 <= '\n')
			|| LA27_0 == '\r' || LA27_0 == ' ')) {
		    s = 31;
		}

		else if ((LA27_0 == '\'')) {
		    s = 32;
		}

		input.seek(index27_0);
		if (s >= 0)
		    return s;
		break;
	    case 18:
		int LA27_155 = input.LA(1);

		int index27_155 = input.index();
		input.rewind();
		s = -1;
		if ((LA27_155 == 'N' || LA27_155 == 'n')) {
		    s = 179;
		}

		else if (((LA27_155 >= '0' && LA27_155 <= '9')
			|| (LA27_155 >= 'A' && LA27_155 <= 'M')
			|| (LA27_155 >= 'O' && LA27_155 <= 'Z')
			|| LA27_155 == '_'
			|| (LA27_155 >= 'a' && LA27_155 <= 'm') || (LA27_155 >= 'o' && LA27_155 <= 'z'))) {
		    s = 46;
		}

		else if ((LA27_155 == '\r')
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if (((LA27_155 >= '\u0000' && LA27_155 <= '\f')
			|| (LA27_155 >= '\u000E' && LA27_155 <= '/')
			|| (LA27_155 >= ':' && LA27_155 <= '@')
			|| (LA27_155 >= '[' && LA27_155 <= '^')
			|| LA27_155 == '`' || (LA27_155 >= '{' && LA27_155 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else
		    s = 26;

		input.seek(index27_155);
		if (s >= 0)
		    return s;
		break;
	    case 19:
		int LA27_42 = input.LA(1);

		int index27_42 = input.index();
		input.rewind();
		s = -1;
		if ((LA27_42 == 'M' || LA27_42 == 'm')) {
		    s = 87;
		}

		else if ((LA27_42 == 'N' || LA27_42 == 'n')) {
		    s = 88;
		}

		else if (((LA27_42 >= '0' && LA27_42 <= '9')
			|| (LA27_42 >= 'A' && LA27_42 <= 'L')
			|| (LA27_42 >= 'O' && LA27_42 <= 'Z')
			|| LA27_42 == '_'
			|| (LA27_42 >= 'a' && LA27_42 <= 'l') || (LA27_42 >= 'o' && LA27_42 <= 'z'))) {
		    s = 46;
		}

		else if ((LA27_42 == '\r')
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if (((LA27_42 >= '\u0000' && LA27_42 <= '\f')
			|| (LA27_42 >= '\u000E' && LA27_42 <= '/')
			|| (LA27_42 >= ':' && LA27_42 <= '@')
			|| (LA27_42 >= '[' && LA27_42 <= '^')
			|| LA27_42 == '`' || (LA27_42 >= '{' && LA27_42 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else
		    s = 26;

		input.seek(index27_42);
		if (s >= 0)
		    return s;
		break;
	    case 20:
		int LA27_199 = input.LA(1);

		int index27_199 = input.index();
		input.rewind();
		s = -1;
		if ((LA27_199 == 'E' || LA27_199 == 'e')) {
		    s = 215;
		}

		else if ((LA27_199 == '\r')
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if (((LA27_199 >= '\u0000' && LA27_199 <= '\f')
			|| (LA27_199 >= '\u000E' && LA27_199 <= '/')
			|| (LA27_199 >= ':' && LA27_199 <= '@')
			|| (LA27_199 >= '[' && LA27_199 <= '^')
			|| LA27_199 == '`' || (LA27_199 >= '{' && LA27_199 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else if (((LA27_199 >= '0' && LA27_199 <= '9')
			|| (LA27_199 >= 'A' && LA27_199 <= 'D')
			|| (LA27_199 >= 'F' && LA27_199 <= 'Z')
			|| LA27_199 == '_'
			|| (LA27_199 >= 'a' && LA27_199 <= 'd') || (LA27_199 >= 'f' && LA27_199 <= 'z'))) {
		    s = 46;
		}

		else
		    s = 26;

		input.seek(index27_199);
		if (s >= 0)
		    return s;
		break;
	    case 21:
		int LA27_124 = input.LA(1);

		int index27_124 = input.index();
		input.rewind();
		s = -1;
		if ((LA27_124 == 'I' || LA27_124 == 'i')) {
		    s = 155;
		}

		else if (((LA27_124 >= '0' && LA27_124 <= '9')
			|| (LA27_124 >= 'A' && LA27_124 <= 'H')
			|| (LA27_124 >= 'J' && LA27_124 <= 'Z')
			|| LA27_124 == '_'
			|| (LA27_124 >= 'a' && LA27_124 <= 'h') || (LA27_124 >= 'j' && LA27_124 <= 'z'))) {
		    s = 46;
		}

		else if ((LA27_124 == '\r')
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if (((LA27_124 >= '\u0000' && LA27_124 <= '\f')
			|| (LA27_124 >= '\u000E' && LA27_124 <= '/')
			|| (LA27_124 >= ':' && LA27_124 <= '@')
			|| (LA27_124 >= '[' && LA27_124 <= '^')
			|| LA27_124 == '`' || (LA27_124 >= '{' && LA27_124 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else
		    s = 26;

		input.seek(index27_124);
		if (s >= 0)
		    return s;
		break;
	    case 22:
		int LA27_154 = input.LA(1);

		int index27_154 = input.index();
		input.rewind();
		s = -1;
		if ((LA27_154 == 'E' || LA27_154 == 'e')) {
		    s = 178;
		}

		else if ((LA27_154 == '\r')
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if (((LA27_154 >= '\u0000' && LA27_154 <= '\f')
			|| (LA27_154 >= '\u000E' && LA27_154 <= '/')
			|| (LA27_154 >= ':' && LA27_154 <= '@')
			|| (LA27_154 >= '[' && LA27_154 <= '^')
			|| LA27_154 == '`' || (LA27_154 >= '{' && LA27_154 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else if (((LA27_154 >= '0' && LA27_154 <= '9')
			|| (LA27_154 >= 'A' && LA27_154 <= 'D')
			|| (LA27_154 >= 'F' && LA27_154 <= 'Z')
			|| LA27_154 == '_'
			|| (LA27_154 >= 'a' && LA27_154 <= 'd') || (LA27_154 >= 'f' && LA27_154 <= 'z'))) {
		    s = 46;
		}

		else
		    s = 26;

		input.seek(index27_154);
		if (s >= 0)
		    return s;
		break;
	    case 23:
		int LA27_24 = input.LA(1);

		int index27_24 = input.index();
		input.rewind();
		s = -1;
		if ((LA27_24 == '*')
			&& (((getCharPositionInLine() > 0) || (getCharPositionInLine() == 0)))) {
		    s = 58;
		}

		else if (((LA27_24 >= '\u0000' && LA27_24 <= '\f')
			|| (LA27_24 >= '\u000E' && LA27_24 <= ')') || (LA27_24 >= '+' && LA27_24 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else if ((LA27_24 == '\r')
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else
		    s = 60;

		input.seek(index27_24);
		if (s >= 0)
		    return s;
		break;
	    case 24:
		int LA27_157 = input.LA(1);

		int index27_157 = input.index();
		input.rewind();
		s = -1;
		if ((LA27_157 == 'C' || LA27_157 == 'c')) {
		    s = 180;
		}

		else if (((LA27_157 >= '0' && LA27_157 <= '9')
			|| (LA27_157 >= 'A' && LA27_157 <= 'B')
			|| (LA27_157 >= 'D' && LA27_157 <= 'Z')
			|| LA27_157 == '_'
			|| (LA27_157 >= 'a' && LA27_157 <= 'b') || (LA27_157 >= 'd' && LA27_157 <= 'z'))) {
		    s = 46;
		}

		else if ((LA27_157 == '\r')
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if (((LA27_157 >= '\u0000' && LA27_157 <= '\f')
			|| (LA27_157 >= '\u000E' && LA27_157 <= '/')
			|| (LA27_157 >= ':' && LA27_157 <= '@')
			|| (LA27_157 >= '[' && LA27_157 <= '^')
			|| LA27_157 == '`' || (LA27_157 >= '{' && LA27_157 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else
		    s = 26;

		input.seek(index27_157);
		if (s >= 0)
		    return s;
		break;
	    case 25:
		int LA27_45 = input.LA(1);

		int index27_45 = input.index();
		input.rewind();
		s = -1;
		if ((LA27_45 == 'A' || LA27_45 == 'a')) {
		    s = 90;
		}

		else if ((LA27_45 == '\r')
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if (((LA27_45 >= '\u0000' && LA27_45 <= '\f')
			|| (LA27_45 >= '\u000E' && LA27_45 <= '/')
			|| (LA27_45 >= ':' && LA27_45 <= '@')
			|| (LA27_45 >= '[' && LA27_45 <= '^')
			|| LA27_45 == '`' || (LA27_45 >= '{' && LA27_45 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else if (((LA27_45 >= '0' && LA27_45 <= '9')
			|| (LA27_45 >= 'B' && LA27_45 <= 'Z')
			|| LA27_45 == '_' || (LA27_45 >= 'b' && LA27_45 <= 'z'))) {
		    s = 46;
		}

		else
		    s = 26;

		input.seek(index27_45);
		if (s >= 0)
		    return s;
		break;
	    case 26:
		int LA27_200 = input.LA(1);

		int index27_200 = input.index();
		input.rewind();
		s = -1;
		if ((LA27_200 == 'E' || LA27_200 == 'e')) {
		    s = 216;
		}

		else if (((LA27_200 >= '0' && LA27_200 <= '9')
			|| (LA27_200 >= 'A' && LA27_200 <= 'D')
			|| (LA27_200 >= 'F' && LA27_200 <= 'Z')
			|| LA27_200 == '_'
			|| (LA27_200 >= 'a' && LA27_200 <= 'd') || (LA27_200 >= 'f' && LA27_200 <= 'z'))) {
		    s = 46;
		}

		else if ((LA27_200 == '\r')
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if (((LA27_200 >= '\u0000' && LA27_200 <= '\f')
			|| (LA27_200 >= '\u000E' && LA27_200 <= '/')
			|| (LA27_200 >= ':' && LA27_200 <= '@')
			|| (LA27_200 >= '[' && LA27_200 <= '^')
			|| LA27_200 == '`' || (LA27_200 >= '{' && LA27_200 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else
		    s = 26;

		input.seek(index27_200);
		if (s >= 0)
		    return s;
		break;
	    case 27:
		int LA27_126 = input.LA(1);

		int index27_126 = input.index();
		input.rewind();
		s = -1;
		if ((LA27_126 == 'A' || LA27_126 == 'a')) {
		    s = 157;
		}

		else if ((LA27_126 == '\r')
			&& ((getCharPositionInLine() == 0))) {
		    s = 43;
		}

		else if (((LA27_126 >= '\u0000' && LA27_126 <= '\f')
			|| (LA27_126 >= '\u000E' && LA27_126 <= '/')
			|| (LA27_126 >= ':' && LA27_126 <= '@')
			|| (LA27_126 >= '[' && LA27_126 <= '^')
			|| LA27_126 == '`' || (LA27_126 >= '{' && LA27_126 <= '\uFFFF'))
			&& ((getCharPositionInLine() == 0))) {
		    s = 59;
		}

		else if (((LA27_126 >= '0' && LA27_126 <= '9')
			|| (LA27_126 >= 'B' && LA27_126 <= 'Z')
			|| LA27_126 == '_' || (LA27_126 >= 'b' && LA27_126 <= 'z'))) {
		    s = 46;
		}

		else
		    s = 26;

		input.seek(index27_126);
		if (s >= 0)
		    return s;
		break;
	    }
	    NoViableAltException nvae =
		    new NoViableAltException(getDescription(), 27, _s,
			    input);
	    error(nvae);
	    throw nvae;
	}
    }

}
