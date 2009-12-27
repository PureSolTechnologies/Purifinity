// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g 2009-12-27 22:51:02

package com.puresol.coding.fortran.antlr.output;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class FortranLexer extends Lexer {
    public static final int DOLLAR=26;
    public static final int LOR=39;
    public static final int EXPONENT=59;
    public static final int LT=47;
    public static final int STAR=36;
    public static final int OCTAL_ESC=68;
    public static final int ID=55;
    public static final int EOF=-1;
    public static final int FLOAT_CONST=60;
    public static final int LPAREN=28;
    public static final int CHAR_CONST=66;
    public static final int LINEFEED=61;
    public static final int IF=20;
    public static final int RPAREN=29;
    public static final int LNOT=37;
    public static final int ESC_SEQ=64;
    public static final int THEN=22;
    public static final int COMMA=27;
    public static final int HEX_CONST=58;
    public static final int STRING_CONST=65;
    public static final int EOR=46;
    public static final int PLUS=33;
    public static final int EQ=52;
    public static final int COMMENT=62;
    public static final int NE=51;
    public static final int INTEGER=15;
    public static final int D=23;
    public static final int E=12;
    public static final int GE=50;
    public static final int F=19;
    public static final int G=14;
    public static final int XOR=45;
    public static final int A=16;
    public static final int B=6;
    public static final int C=69;
    public static final int L=17;
    public static final int M=72;
    public static final int N=11;
    public static final int O=8;
    public static final int H=21;
    public static final int UNICODE_ESC=67;
    public static final int I=10;
    public static final int J=70;
    public static final int K=71;
    public static final int U=5;
    public static final int INT_CONST=56;
    public static final int T=9;
    public static final int W=74;
    public static final int HEX_DIGIT=57;
    public static final int POWER=35;
    public static final int V=41;
    public static final int NEQV=43;
    public static final int Q=40;
    public static final int P=73;
    public static final int S=4;
    public static final int R=7;
    public static final int MINUS=32;
    public static final int TRUE=53;
    public static final int Y=75;
    public static final int NOTNL=63;
    public static final int X=44;
    public static final int Z=76;
    public static final int COLON=30;
    public static final int REAL=18;
    public static final int WS=24;
    public static final int SUBROUTINE=13;
    public static final int ASSIGN=31;
    public static final int END_IF=25;
    public static final int GT=49;
    public static final int EQV=42;
    public static final int DIV=34;
    public static final int FALSE=54;
    public static final int LAND=38;
    public static final int LE=48;

    // delegates
    // delegators

    public FortranLexer() {;} 
    public FortranLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public FortranLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g"; }

    // $ANTLR start "SUBROUTINE"
    public final void mSUBROUTINE() throws RecognitionException {
        try {
            int _type = SUBROUTINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:14:2: ( S U B R O U T I N E )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:14:4: S U B R O U T I N E
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
        }
        finally {
        }
    }
    // $ANTLR end "SUBROUTINE"

    // $ANTLR start "INTEGER"
    public final void mINTEGER() throws RecognitionException {
        try {
            int _type = INTEGER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:17:2: ( I N T E G E R )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:17:4: I N T E G E R
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
        }
        finally {
        }
    }
    // $ANTLR end "INTEGER"

    // $ANTLR start "REAL"
    public final void mREAL() throws RecognitionException {
        try {
            int _type = REAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:19:2: ( R E A L )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:19:4: R E A L
            {
            mR(); 
            mE(); 
            mA(); 
            mL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "REAL"

    // $ANTLR start "IF"
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:22:2: ( I F )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:22:4: I F
            {
            mI(); 
            mF(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IF"

    // $ANTLR start "THEN"
    public final void mTHEN() throws RecognitionException {
        try {
            int _type = THEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:24:2: ( T H E N )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:24:4: T H E N
            {
            mT(); 
            mH(); 
            mE(); 
            mN(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "THEN"

    // $ANTLR start "END_IF"
    public final void mEND_IF() throws RecognitionException {
        try {
            int _type = END_IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:26:2: ( E N D ( WS )+ I F )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:26:4: E N D ( WS )+ I F
            {
            mE(); 
            mN(); 
            mD(); 
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:26:10: ( WS )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='\t' && LA1_0<='\n')||LA1_0=='\r'||LA1_0==' ') ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:26:10: WS
            	    {
            	    mWS(); 

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);

            mI(); 
            mF(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "END_IF"

    // $ANTLR start "DOLLAR"
    public final void mDOLLAR() throws RecognitionException {
        try {
            int _type = DOLLAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:31:3: ( '$' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:32:3: '$'
            {
            match('$'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOLLAR"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:36:3: ( ',' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:37:3: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:41:3: ( '(' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:42:3: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:46:3: ( ')' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:47:3: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:51:3: ( ':' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:52:3: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "ASSIGN"
    public final void mASSIGN() throws RecognitionException {
        try {
            int _type = ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:57:3: ( '=' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:58:3: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ASSIGN"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:62:3: ( '-' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:63:3: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:67:3: ( '+' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:68:3: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "DIV"
    public final void mDIV() throws RecognitionException {
        try {
            int _type = DIV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:72:3: ( '/' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:73:3: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIV"

    // $ANTLR start "POWER"
    public final void mPOWER() throws RecognitionException {
        try {
            int _type = POWER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:77:3: ({...}? => '**' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:78:3: {...}? => '**'
            {
            if ( !((getCharPositionInLine()>0)) ) {
                throw new FailedPredicateException(input, "POWER", "getCharPositionInLine()>0");
            }
            match("**"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "POWER"

    // $ANTLR start "STAR"
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:82:3: ({...}? => '*' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:83:3: {...}? => '*'
            {
            if ( !((getCharPositionInLine()>0)) ) {
                throw new FailedPredicateException(input, "STAR", "getCharPositionInLine()>0");
            }
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STAR"

    // $ANTLR start "LNOT"
    public final void mLNOT() throws RecognitionException {
        try {
            int _type = LNOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:87:3: ( '.' N O T '.' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:88:3: '.' N O T '.'
            {
            match('.'); 
            mN(); 
            mO(); 
            mT(); 
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LNOT"

    // $ANTLR start "LAND"
    public final void mLAND() throws RecognitionException {
        try {
            int _type = LAND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:92:3: ( '.' A N D '.' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:93:3: '.' A N D '.'
            {
            match('.'); 
            mA(); 
            mN(); 
            mD(); 
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LAND"

    // $ANTLR start "LOR"
    public final void mLOR() throws RecognitionException {
        try {
            int _type = LOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:97:3: ( '.' O R '.' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:98:3: '.' O R '.'
            {
            match('.'); 
            mO(); 
            mR(); 
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LOR"

    // $ANTLR start "EQV"
    public final void mEQV() throws RecognitionException {
        try {
            int _type = EQV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:102:3: ( '.' E Q V '.' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:103:3: '.' E Q V '.'
            {
            match('.'); 
            mE(); 
            mQ(); 
            mV(); 
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQV"

    // $ANTLR start "NEQV"
    public final void mNEQV() throws RecognitionException {
        try {
            int _type = NEQV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:107:3: ( '.' N E Q V '.' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:108:3: '.' N E Q V '.'
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
        }
        finally {
        }
    }
    // $ANTLR end "NEQV"

    // $ANTLR start "XOR"
    public final void mXOR() throws RecognitionException {
        try {
            int _type = XOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:112:3: ( '.' X O R '.' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:113:3: '.' X O R '.'
            {
            match('.'); 
            mX(); 
            mO(); 
            mR(); 
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "XOR"

    // $ANTLR start "EOR"
    public final void mEOR() throws RecognitionException {
        try {
            int _type = EOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:117:3: ( '.' E O R '.' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:118:3: '.' E O R '.'
            {
            match('.'); 
            mE(); 
            mO(); 
            mR(); 
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EOR"

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:122:3: ( '.' L T '.' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:123:3: '.' L T '.'
            {
            match('.'); 
            mL(); 
            mT(); 
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LT"

    // $ANTLR start "LE"
    public final void mLE() throws RecognitionException {
        try {
            int _type = LE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:127:3: ( '.' L E '.' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:128:3: '.' L E '.'
            {
            match('.'); 
            mL(); 
            mE(); 
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LE"

    // $ANTLR start "GT"
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:132:3: ( '.' G T '.' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:133:3: '.' G T '.'
            {
            match('.'); 
            mG(); 
            mT(); 
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GT"

    // $ANTLR start "GE"
    public final void mGE() throws RecognitionException {
        try {
            int _type = GE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:137:3: ( '.' G E '.' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:138:3: '.' G E '.'
            {
            match('.'); 
            mG(); 
            mE(); 
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GE"

    // $ANTLR start "NE"
    public final void mNE() throws RecognitionException {
        try {
            int _type = NE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:142:3: ( '.' N E '.' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:143:3: '.' N E '.'
            {
            match('.'); 
            mN(); 
            mE(); 
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NE"

    // $ANTLR start "EQ"
    public final void mEQ() throws RecognitionException {
        try {
            int _type = EQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:147:3: ( '.' E Q '.' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:148:3: '.' E Q '.'
            {
            match('.'); 
            mE(); 
            mQ(); 
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQ"

    // $ANTLR start "TRUE"
    public final void mTRUE() throws RecognitionException {
        try {
            int _type = TRUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:152:3: ( '.' T R U E '.' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:153:3: '.' T R U E '.'
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
        }
        finally {
        }
    }
    // $ANTLR end "TRUE"

    // $ANTLR start "FALSE"
    public final void mFALSE() throws RecognitionException {
        try {
            int _type = FALSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:157:3: ( '.' F A L S E '.' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:158:3: '.' F A L S E '.'
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
        }
        finally {
        }
    }
    // $ANTLR end "FALSE"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:162:3: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:163:3: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:168:3: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')||(LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "INT_CONST"
    public final void mINT_CONST() throws RecognitionException {
        try {
            int _type = INT_CONST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:177:3: ( ( '0' .. '9' )+ )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:178:3: ( '0' .. '9' )+
            {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:178:3: ( '0' .. '9' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:178:3: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INT_CONST"

    // $ANTLR start "HEX_CONST"
    public final void mHEX_CONST() throws RecognitionException {
        try {
            int _type = HEX_CONST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:182:3: ( '0x' ( HEX_DIGIT )+ )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:183:3: '0x' ( HEX_DIGIT )+
            {
            match("0x"); 

            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:183:8: ( HEX_DIGIT )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')||(LA4_0>='A' && LA4_0<='F')||(LA4_0>='a' && LA4_0<='f')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:183:8: HEX_DIGIT
            	    {
            	    mHEX_DIGIT(); 

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "HEX_CONST"

    // $ANTLR start "FLOAT_CONST"
    public final void mFLOAT_CONST() throws RecognitionException {
        try {
            int _type = FLOAT_CONST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:187:3: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )+ ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT )
            int alt11=3;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:188:3: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )?
                    {
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:188:3: ( '0' .. '9' )+
                    int cnt5=0;
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:188:4: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt5 >= 1 ) break loop5;
                                EarlyExitException eee =
                                    new EarlyExitException(5, input);
                                throw eee;
                        }
                        cnt5++;
                    } while (true);

                    match('.'); 
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:188:19: ( '0' .. '9' )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0>='0' && LA6_0<='9')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:188:20: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:188:31: ( EXPONENT )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0=='E'||LA7_0=='e') ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:188:31: EXPONENT
                            {
                            mEXPONENT(); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:189:5: '.' ( '0' .. '9' )+ ( EXPONENT )?
                    {
                    match('.'); 
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:189:9: ( '0' .. '9' )+
                    int cnt8=0;
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( ((LA8_0>='0' && LA8_0<='9')) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:189:10: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt8 >= 1 ) break loop8;
                                EarlyExitException eee =
                                    new EarlyExitException(8, input);
                                throw eee;
                        }
                        cnt8++;
                    } while (true);

                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:189:21: ( EXPONENT )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0=='E'||LA9_0=='e') ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:189:21: EXPONENT
                            {
                            mEXPONENT(); 

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:190:5: ( '0' .. '9' )+ EXPONENT
                    {
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:190:5: ( '0' .. '9' )+
                    int cnt10=0;
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( ((LA10_0>='0' && LA10_0<='9')) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:190:6: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt10 >= 1 ) break loop10;
                                EarlyExitException eee =
                                    new EarlyExitException(10, input);
                                throw eee;
                        }
                        cnt10++;
                    } while (true);

                    mEXPONENT(); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FLOAT_CONST"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:194:3: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? LINEFEED | '/*' ( options {greedy=false; } : ( LINEFEED | ~ ( '\\n' ) ) )* '*/' | {...}? => ( 'c' | '*' ) (~ ( '\\n' | '\\r' ) )* ( '\\r' )? LINEFEED )
            int alt18=3;
            int LA18_0 = input.LA(1);

            if ( (LA18_0=='/') ) {
                int LA18_1 = input.LA(2);

                if ( (LA18_1=='/') ) {
                    alt18=1;
                }
                else if ( (LA18_1=='*') ) {
                    alt18=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 18, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA18_0=='*'||LA18_0=='c') && ((getCharPositionInLine()==0))) {
                alt18=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:195:3: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? LINEFEED
                    {
                    match("//"); 

                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:196:3: (~ ( '\\n' | '\\r' ) )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( ((LA12_0>='\u0000' && LA12_0<='\t')||(LA12_0>='\u000B' && LA12_0<='\f')||(LA12_0>='\u000E' && LA12_0<='\uFFFF')) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:196:3: ~ ( '\\n' | '\\r' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);

                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:200:3: ( '\\r' )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0=='\r') ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:200:3: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    mLINEFEED(); 
                    _channel=HIDDEN;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:201:5: '/*' ( options {greedy=false; } : ( LINEFEED | ~ ( '\\n' ) ) )* '*/'
                    {
                    match("/*"); 

                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:202:3: ( options {greedy=false; } : ( LINEFEED | ~ ( '\\n' ) ) )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0=='*') ) {
                            int LA15_1 = input.LA(2);

                            if ( (LA15_1=='/') ) {
                                alt15=2;
                            }
                            else if ( ((LA15_1>='\u0000' && LA15_1<='.')||(LA15_1>='0' && LA15_1<='\uFFFF')) ) {
                                alt15=1;
                            }


                        }
                        else if ( ((LA15_0>='\u0000' && LA15_0<=')')||(LA15_0>='+' && LA15_0<='\uFFFF')) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:203:5: ( LINEFEED | ~ ( '\\n' ) )
                    	    {
                    	    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:203:5: ( LINEFEED | ~ ( '\\n' ) )
                    	    int alt14=2;
                    	    int LA14_0 = input.LA(1);

                    	    if ( (LA14_0=='\n') ) {
                    	        alt14=1;
                    	    }
                    	    else if ( ((LA14_0>='\u0000' && LA14_0<='\t')||(LA14_0>='\u000B' && LA14_0<='\uFFFF')) ) {
                    	        alt14=2;
                    	    }
                    	    else {
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 14, 0, input);

                    	        throw nvae;
                    	    }
                    	    switch (alt14) {
                    	        case 1 :
                    	            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:204:7: LINEFEED
                    	            {
                    	            mLINEFEED(); 

                    	            }
                    	            break;
                    	        case 2 :
                    	            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:205:9: ~ ( '\\n' )
                    	            {
                    	            if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\uFFFF') ) {
                    	                input.consume();

                    	            }
                    	            else {
                    	                MismatchedSetException mse = new MismatchedSetException(null,input);
                    	                recover(mse);
                    	                throw mse;}


                    	            }
                    	            break;

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);

                    match("*/"); 

                    _channel=HIDDEN;

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:210:3: {...}? => ( 'c' | '*' ) (~ ( '\\n' | '\\r' ) )* ( '\\r' )? LINEFEED
                    {
                    if ( !((getCharPositionInLine()==0)) ) {
                        throw new FailedPredicateException(input, "COMMENT", "getCharPositionInLine()==0");
                    }
                    if ( input.LA(1)=='*'||input.LA(1)=='c' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:215:3: (~ ( '\\n' | '\\r' ) )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( ((LA16_0>='\u0000' && LA16_0<='\t')||(LA16_0>='\u000B' && LA16_0<='\f')||(LA16_0>='\u000E' && LA16_0<='\uFFFF')) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:215:3: ~ ( '\\n' | '\\r' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);

                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:219:3: ( '\\r' )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0=='\r') ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:219:3: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    mLINEFEED(); 
                    _channel=HIDDEN;

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:223:3: ( ( ' ' | '\\t' | '\\r' | LINEFEED ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:224:3: ( ' ' | '\\t' | '\\r' | LINEFEED )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "LINEFEED"
    public final void mLINEFEED() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:235:3: ( '\\n' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:236:3: '\\n'
            {
            match('\n'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "LINEFEED"

    // $ANTLR start "NOTNL"
    public final void mNOTNL() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:241:3: (~ ( '\\r' | '\\n' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:241:5: ~ ( '\\r' | '\\n' )
            {
            if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "NOTNL"

    // $ANTLR start "STRING_CONST"
    public final void mSTRING_CONST() throws RecognitionException {
        try {
            int _type = STRING_CONST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:245:3: ( '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:246:3: '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"'
            {
            match('\"'); 
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:247:3: ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )*
            loop19:
            do {
                int alt19=3;
                int LA19_0 = input.LA(1);

                if ( (LA19_0=='\\') ) {
                    alt19=1;
                }
                else if ( ((LA19_0>='\u0000' && LA19_0<='!')||(LA19_0>='#' && LA19_0<='[')||(LA19_0>=']' && LA19_0<='\uFFFF')) ) {
                    alt19=2;
                }


                switch (alt19) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:248:5: ESC_SEQ
            	    {
            	    mESC_SEQ(); 

            	    }
            	    break;
            	case 2 :
            	    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:250:5: ~ ( '\\\\' | '\"' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRING_CONST"

    // $ANTLR start "CHAR_CONST"
    public final void mCHAR_CONST() throws RecognitionException {
        try {
            int _type = CHAR_CONST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:259:3: ( '\\'' ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) ) '\\'' )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:260:3: '\\'' ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) ) '\\''
            {
            match('\''); 
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:261:3: ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0=='\\') ) {
                alt20=1;
            }
            else if ( ((LA20_0>='\u0000' && LA20_0<='&')||(LA20_0>='(' && LA20_0<='[')||(LA20_0>=']' && LA20_0<='\uFFFF')) ) {
                alt20=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:262:5: ESC_SEQ
                    {
                    mESC_SEQ(); 

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:264:5: ~ ( '\\'' | '\\\\' )
                    {
                    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CHAR_CONST"

    // $ANTLR start "EXPONENT"
    public final void mEXPONENT() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:274:3: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:275:3: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:279:3: ( '+' | '-' )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0=='+'||LA21_0=='-') ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:283:3: ( '0' .. '9' )+
            int cnt22=0;
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0>='0' && LA22_0<='9')) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:283:4: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt22 >= 1 ) break loop22;
                        EarlyExitException eee =
                            new EarlyExitException(22, input);
                        throw eee;
                }
                cnt22++;
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "EXPONENT"

    // $ANTLR start "HEX_DIGIT"
    public final void mHEX_DIGIT() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:288:3: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:289:3: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
            {
            if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "HEX_DIGIT"

    // $ANTLR start "ESC_SEQ"
    public final void mESC_SEQ() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:298:3: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | UNICODE_ESC | OCTAL_ESC )
            int alt23=3;
            int LA23_0 = input.LA(1);

            if ( (LA23_0=='\\') ) {
                switch ( input.LA(2) ) {
                case '\"':
                case '\'':
                case '\\':
                case 'b':
                case 'f':
                case 'n':
                case 'r':
                case 't':
                    {
                    alt23=1;
                    }
                    break;
                case 'u':
                    {
                    alt23=2;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                    {
                    alt23=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }
            switch (alt23) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:299:3: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
                    {
                    match('\\'); 
                    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:310:5: UNICODE_ESC
                    {
                    mUNICODE_ESC(); 

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:311:5: OCTAL_ESC
                    {
                    mOCTAL_ESC(); 

                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "ESC_SEQ"

    // $ANTLR start "OCTAL_ESC"
    public final void mOCTAL_ESC() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:316:3: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
            int alt24=3;
            int LA24_0 = input.LA(1);

            if ( (LA24_0=='\\') ) {
                int LA24_1 = input.LA(2);

                if ( ((LA24_1>='0' && LA24_1<='3')) ) {
                    int LA24_2 = input.LA(3);

                    if ( ((LA24_2>='0' && LA24_2<='7')) ) {
                        int LA24_4 = input.LA(4);

                        if ( ((LA24_4>='0' && LA24_4<='7')) ) {
                            alt24=1;
                        }
                        else {
                            alt24=2;}
                    }
                    else {
                        alt24=3;}
                }
                else if ( ((LA24_1>='4' && LA24_1<='7')) ) {
                    int LA24_3 = input.LA(3);

                    if ( ((LA24_3>='0' && LA24_3<='7')) ) {
                        alt24=2;
                    }
                    else {
                        alt24=3;}
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 24, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }
            switch (alt24) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:317:3: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:317:8: ( '0' .. '3' )
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:317:9: '0' .. '3'
                    {
                    matchRange('0','3'); 

                    }

                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:317:19: ( '0' .. '7' )
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:317:20: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:317:30: ( '0' .. '7' )
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:317:31: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:318:5: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:318:10: ( '0' .. '7' )
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:318:11: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:318:21: ( '0' .. '7' )
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:318:22: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:319:5: '\\\\' ( '0' .. '7' )
                    {
                    match('\\'); 
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:319:10: ( '0' .. '7' )
                    // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:319:11: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "OCTAL_ESC"

    // $ANTLR start "UNICODE_ESC"
    public final void mUNICODE_ESC() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:324:3: ( '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:325:3: '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
            {
            match('\\'); 
            match('u'); 
            mHEX_DIGIT(); 
            mHEX_DIGIT(); 
            mHEX_DIGIT(); 
            mHEX_DIGIT(); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "UNICODE_ESC"

    // $ANTLR start "A"
    public final void mA() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:328:11: ( ( 'a' | 'A' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:328:12: ( 'a' | 'A' )
            {
            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "A"

    // $ANTLR start "B"
    public final void mB() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:329:11: ( ( 'b' | 'B' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:329:12: ( 'b' | 'B' )
            {
            if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "B"

    // $ANTLR start "C"
    public final void mC() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:330:11: ( ( 'c' | 'C' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:330:12: ( 'c' | 'C' )
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "C"

    // $ANTLR start "D"
    public final void mD() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:331:11: ( ( 'd' | 'D' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:331:12: ( 'd' | 'D' )
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "D"

    // $ANTLR start "E"
    public final void mE() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:332:11: ( ( 'e' | 'E' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:332:12: ( 'e' | 'E' )
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "E"

    // $ANTLR start "F"
    public final void mF() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:333:11: ( ( 'f' | 'F' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:333:12: ( 'f' | 'F' )
            {
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "F"

    // $ANTLR start "G"
    public final void mG() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:334:11: ( ( 'g' | 'G' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:334:12: ( 'g' | 'G' )
            {
            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "G"

    // $ANTLR start "H"
    public final void mH() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:335:11: ( ( 'h' | 'H' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:335:12: ( 'h' | 'H' )
            {
            if ( input.LA(1)=='H'||input.LA(1)=='h' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "H"

    // $ANTLR start "I"
    public final void mI() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:336:11: ( ( 'i' | 'I' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:336:12: ( 'i' | 'I' )
            {
            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "I"

    // $ANTLR start "J"
    public final void mJ() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:337:11: ( ( 'j' | 'J' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:337:12: ( 'j' | 'J' )
            {
            if ( input.LA(1)=='J'||input.LA(1)=='j' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "J"

    // $ANTLR start "K"
    public final void mK() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:338:11: ( ( 'k' | 'K' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:338:12: ( 'k' | 'K' )
            {
            if ( input.LA(1)=='K'||input.LA(1)=='k' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "K"

    // $ANTLR start "L"
    public final void mL() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:339:11: ( ( 'l' | 'L' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:339:12: ( 'l' | 'L' )
            {
            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "L"

    // $ANTLR start "M"
    public final void mM() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:340:11: ( ( 'm' | 'M' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:340:12: ( 'm' | 'M' )
            {
            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "M"

    // $ANTLR start "N"
    public final void mN() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:341:11: ( ( 'n' | 'N' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:341:12: ( 'n' | 'N' )
            {
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "N"

    // $ANTLR start "O"
    public final void mO() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:342:11: ( ( 'o' | 'O' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:342:12: ( 'o' | 'O' )
            {
            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "O"

    // $ANTLR start "P"
    public final void mP() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:343:11: ( ( 'p' | 'P' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:343:12: ( 'p' | 'P' )
            {
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "P"

    // $ANTLR start "Q"
    public final void mQ() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:344:11: ( ( 'q' | 'Q' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:344:12: ( 'q' | 'Q' )
            {
            if ( input.LA(1)=='Q'||input.LA(1)=='q' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "Q"

    // $ANTLR start "R"
    public final void mR() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:345:11: ( ( 'r' | 'R' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:345:12: ( 'r' | 'R' )
            {
            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "R"

    // $ANTLR start "S"
    public final void mS() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:346:11: ( ( 's' | 'S' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:346:12: ( 's' | 'S' )
            {
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "S"

    // $ANTLR start "T"
    public final void mT() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:347:11: ( ( 't' | 'T' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:347:12: ( 't' | 'T' )
            {
            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "T"

    // $ANTLR start "U"
    public final void mU() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:348:11: ( ( 'u' | 'U' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:348:12: ( 'u' | 'U' )
            {
            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "U"

    // $ANTLR start "V"
    public final void mV() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:349:11: ( ( 'v' | 'V' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:349:12: ( 'v' | 'V' )
            {
            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "V"

    // $ANTLR start "W"
    public final void mW() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:350:11: ( ( 'w' | 'W' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:350:12: ( 'w' | 'W' )
            {
            if ( input.LA(1)=='W'||input.LA(1)=='w' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "W"

    // $ANTLR start "X"
    public final void mX() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:351:11: ( ( 'x' | 'X' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:351:12: ( 'x' | 'X' )
            {
            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "X"

    // $ANTLR start "Y"
    public final void mY() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:352:11: ( ( 'y' | 'Y' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:352:12: ( 'y' | 'Y' )
            {
            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "Y"

    // $ANTLR start "Z"
    public final void mZ() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:353:11: ( ( 'z' | 'Z' ) )
            // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:353:12: ( 'z' | 'Z' )
            {
            if ( input.LA(1)=='Z'||input.LA(1)=='z' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "Z"

    public void mTokens() throws RecognitionException {
        // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:8: ( SUBROUTINE | INTEGER | REAL | IF | THEN | END_IF | DOLLAR | COMMA | LPAREN | RPAREN | COLON | ASSIGN | MINUS | PLUS | DIV | POWER | STAR | LNOT | LAND | LOR | EQV | NEQV | XOR | EOR | LT | LE | GT | GE | NE | EQ | TRUE | FALSE | ID | INT_CONST | HEX_CONST | FLOAT_CONST | COMMENT | WS | STRING_CONST | CHAR_CONST )
        int alt25=40;
        alt25 = dfa25.predict(input);
        switch (alt25) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:10: SUBROUTINE
                {
                mSUBROUTINE(); 

                }
                break;
            case 2 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:21: INTEGER
                {
                mINTEGER(); 

                }
                break;
            case 3 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:29: REAL
                {
                mREAL(); 

                }
                break;
            case 4 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:34: IF
                {
                mIF(); 

                }
                break;
            case 5 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:37: THEN
                {
                mTHEN(); 

                }
                break;
            case 6 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:42: END_IF
                {
                mEND_IF(); 

                }
                break;
            case 7 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:49: DOLLAR
                {
                mDOLLAR(); 

                }
                break;
            case 8 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:56: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 9 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:62: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 10 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:69: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 11 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:76: COLON
                {
                mCOLON(); 

                }
                break;
            case 12 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:82: ASSIGN
                {
                mASSIGN(); 

                }
                break;
            case 13 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:89: MINUS
                {
                mMINUS(); 

                }
                break;
            case 14 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:95: PLUS
                {
                mPLUS(); 

                }
                break;
            case 15 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:100: DIV
                {
                mDIV(); 

                }
                break;
            case 16 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:104: POWER
                {
                mPOWER(); 

                }
                break;
            case 17 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:110: STAR
                {
                mSTAR(); 

                }
                break;
            case 18 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:115: LNOT
                {
                mLNOT(); 

                }
                break;
            case 19 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:120: LAND
                {
                mLAND(); 

                }
                break;
            case 20 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:125: LOR
                {
                mLOR(); 

                }
                break;
            case 21 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:129: EQV
                {
                mEQV(); 

                }
                break;
            case 22 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:133: NEQV
                {
                mNEQV(); 

                }
                break;
            case 23 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:138: XOR
                {
                mXOR(); 

                }
                break;
            case 24 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:142: EOR
                {
                mEOR(); 

                }
                break;
            case 25 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:146: LT
                {
                mLT(); 

                }
                break;
            case 26 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:149: LE
                {
                mLE(); 

                }
                break;
            case 27 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:152: GT
                {
                mGT(); 

                }
                break;
            case 28 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:155: GE
                {
                mGE(); 

                }
                break;
            case 29 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:158: NE
                {
                mNE(); 

                }
                break;
            case 30 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:161: EQ
                {
                mEQ(); 

                }
                break;
            case 31 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:164: TRUE
                {
                mTRUE(); 

                }
                break;
            case 32 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:169: FALSE
                {
                mFALSE(); 

                }
                break;
            case 33 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:175: ID
                {
                mID(); 

                }
                break;
            case 34 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:178: INT_CONST
                {
                mINT_CONST(); 

                }
                break;
            case 35 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:188: HEX_CONST
                {
                mHEX_CONST(); 

                }
                break;
            case 36 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:198: FLOAT_CONST
                {
                mFLOAT_CONST(); 

                }
                break;
            case 37 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:210: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 38 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:218: WS
                {
                mWS(); 

                }
                break;
            case 39 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:221: STRING_CONST
                {
                mSTRING_CONST(); 

                }
                break;
            case 40 :
                // /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranLexer.g:1:234: CHAR_CONST
                {
                mCHAR_CONST(); 

                }
                break;

        }

    }


    protected DFA11 dfa11 = new DFA11(this);
    protected DFA25 dfa25 = new DFA25(this);
    static final String DFA11_eotS =
        "\5\uffff";
    static final String DFA11_eofS =
        "\5\uffff";
    static final String DFA11_minS =
        "\2\56\3\uffff";
    static final String DFA11_maxS =
        "\1\71\1\145\3\uffff";
    static final String DFA11_acceptS =
        "\2\uffff\1\2\1\3\1\1";
    static final String DFA11_specialS =
        "\5\uffff}>";
    static final String[] DFA11_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\4\1\uffff\12\1\13\uffff\1\3\37\uffff\1\3",
            "",
            "",
            ""
    };

    static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
    static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
    static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
    static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
    static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
    static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
    static final short[][] DFA11_transition;

    static {
        int numStates = DFA11_transitionS.length;
        DFA11_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = DFA11_eot;
            this.eof = DFA11_eof;
            this.min = DFA11_min;
            this.max = DFA11_max;
            this.accept = DFA11_accept;
            this.special = DFA11_special;
            this.transition = DFA11_transition;
        }
        public String getDescription() {
            return "186:1: FLOAT_CONST : ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )+ ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT );";
        }
    }
    static final String DFA25_eotS =
        "\1\uffff\5\24\10\uffff\1\37\1\41\1\uffff\1\24\2\57\4\uffff\1\24"+
        "\1\61\4\24\2\uffff\1\66\14\uffff\1\24\2\uffff\1\24\1\uffff\4\24"+
        "\11\uffff\2\24\1\112\1\113\5\uffff\2\24\2\uffff\3\24\1\121\1\24"+
        "\1\uffff\1\24\1\124\1\uffff";
    static final String DFA25_eofS =
        "\125\uffff";
    static final String DFA25_minS =
        "\1\11\1\125\1\106\1\105\1\110\1\116\10\uffff\1\52\1\0\1\60\1\0\2"+
        "\56\4\uffff\1\102\1\60\1\124\1\101\1\105\1\104\2\uffff\1\0\2\uffff"+
        "\1\105\2\uffff\1\117\1\105\1\uffff\1\105\3\uffff\1\0\2\uffff\1\122"+
        "\1\uffff\1\105\1\114\1\116\1\11\4\uffff\1\56\3\uffff\1\56\1\117"+
        "\1\107\2\60\5\uffff\1\125\1\105\2\uffff\1\124\1\122\1\111\1\60\1"+
        "\116\1\uffff\1\105\1\60\1\uffff";
    static final String DFA25_maxS =
        "\1\172\1\165\1\156\1\145\1\150\1\156\10\uffff\1\57\1\uffff\1\170"+
        "\1\uffff\1\170\1\145\4\uffff\1\142\1\172\1\164\1\141\1\145\1\144"+
        "\2\uffff\1\uffff\2\uffff\1\164\2\uffff\1\161\1\164\1\uffff\1\157"+
        "\3\uffff\1\uffff\2\uffff\1\162\1\uffff\1\145\1\154\1\156\1\40\4"+
        "\uffff\1\166\3\uffff\1\161\1\157\1\147\2\172\5\uffff\1\165\1\145"+
        "\2\uffff\1\164\1\162\1\151\1\172\1\156\1\uffff\1\145\1\172\1\uffff";
    static final String DFA25_acceptS =
        "\6\uffff\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16\6\uffff\1\41\1\46"+
        "\1\47\1\50\6\uffff\1\45\1\17\1\uffff\1\21\1\45\1\uffff\1\44\1\23"+
        "\2\uffff\1\27\1\uffff\1\37\1\24\1\40\1\uffff\1\43\1\42\1\uffff\1"+
        "\4\4\uffff\1\20\1\31\1\32\1\30\1\uffff\1\34\1\33\1\22\5\uffff\1"+
        "\6\1\25\1\36\1\35\1\26\2\uffff\1\3\1\5\5\uffff\1\2\2\uffff\1\1";
    static final String DFA25_specialS =
        "\1\4\16\uffff\1\2\1\uffff\1\3\16\uffff\1\0\14\uffff\1\1\47\uffff}>";
    static final String[] DFA25_transitionS = {
            "\2\25\2\uffff\1\25\22\uffff\1\25\1\uffff\1\26\1\uffff\1\6\2"+
            "\uffff\1\27\1\10\1\11\1\17\1\15\1\7\1\14\1\20\1\16\1\22\11\23"+
            "\1\12\2\uffff\1\13\3\uffff\4\24\1\5\3\24\1\2\10\24\1\3\1\1\1"+
            "\4\6\24\4\uffff\1\24\1\uffff\2\24\1\21\1\24\1\5\3\24\1\2\10"+
            "\24\1\3\1\1\1\4\6\24",
            "\1\30\37\uffff\1\30",
            "\1\31\7\uffff\1\32\27\uffff\1\31\7\uffff\1\32",
            "\1\33\37\uffff\1\33",
            "\1\34\37\uffff\1\34",
            "\1\35\37\uffff\1\35",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\36\4\uffff\1\36",
            "\52\42\1\40\uffd5\42",
            "\12\44\7\uffff\1\45\3\uffff\1\46\1\54\1\47\4\uffff\1\43\1\uffff"+
            "\1\51\1\53\4\uffff\1\52\3\uffff\1\50\10\uffff\1\45\3\uffff\1"+
            "\46\1\54\1\47\4\uffff\1\43\1\uffff\1\51\1\53\4\uffff\1\52\3"+
            "\uffff\1\50",
            "\60\42\12\55\7\42\32\55\4\42\1\55\1\42\32\55\uff85\42",
            "\1\44\1\uffff\12\23\13\uffff\1\44\37\uffff\1\44\22\uffff\1"+
            "\56",
            "\1\44\1\uffff\12\23\13\uffff\1\44\37\uffff\1\44",
            "",
            "",
            "",
            "",
            "\1\60\37\uffff\1\60",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\62\37\uffff\1\62",
            "\1\63\37\uffff\1\63",
            "\1\64\37\uffff\1\64",
            "\1\65\37\uffff\1\65",
            "",
            "",
            "\0\42",
            "",
            "",
            "\1\70\16\uffff\1\67\20\uffff\1\70\16\uffff\1\67",
            "",
            "",
            "\1\71\1\uffff\1\72\35\uffff\1\71\1\uffff\1\72",
            "\1\73\16\uffff\1\74\20\uffff\1\73\16\uffff\1\74",
            "",
            "\1\76\11\uffff\1\75\25\uffff\1\76\11\uffff\1\75",
            "",
            "",
            "",
            "\60\42\12\55\7\42\32\55\4\42\1\55\1\42\32\55\uff85\42",
            "",
            "",
            "\1\77\37\uffff\1\77",
            "",
            "\1\100\37\uffff\1\100",
            "\1\101\37\uffff\1\101",
            "\1\102\37\uffff\1\102",
            "\2\103\2\uffff\1\103\22\uffff\1\103",
            "",
            "",
            "",
            "",
            "\1\105\47\uffff\1\104\37\uffff\1\104",
            "",
            "",
            "",
            "\1\106\42\uffff\1\107\37\uffff\1\107",
            "\1\110\37\uffff\1\110",
            "\1\111\37\uffff\1\111",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "",
            "",
            "",
            "",
            "",
            "\1\114\37\uffff\1\114",
            "\1\115\37\uffff\1\115",
            "",
            "",
            "\1\116\37\uffff\1\116",
            "\1\117\37\uffff\1\117",
            "\1\120\37\uffff\1\120",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\122\37\uffff\1\122",
            "",
            "\1\123\37\uffff\1\123",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            ""
    };

    static final short[] DFA25_eot = DFA.unpackEncodedString(DFA25_eotS);
    static final short[] DFA25_eof = DFA.unpackEncodedString(DFA25_eofS);
    static final char[] DFA25_min = DFA.unpackEncodedStringToUnsignedChars(DFA25_minS);
    static final char[] DFA25_max = DFA.unpackEncodedStringToUnsignedChars(DFA25_maxS);
    static final short[] DFA25_accept = DFA.unpackEncodedString(DFA25_acceptS);
    static final short[] DFA25_special = DFA.unpackEncodedString(DFA25_specialS);
    static final short[][] DFA25_transition;

    static {
        int numStates = DFA25_transitionS.length;
        DFA25_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA25_transition[i] = DFA.unpackEncodedString(DFA25_transitionS[i]);
        }
    }

    class DFA25 extends DFA {

        public DFA25(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 25;
            this.eot = DFA25_eot;
            this.eof = DFA25_eof;
            this.min = DFA25_min;
            this.max = DFA25_max;
            this.accept = DFA25_accept;
            this.special = DFA25_special;
            this.transition = DFA25_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( SUBROUTINE | INTEGER | REAL | IF | THEN | END_IF | DOLLAR | COMMA | LPAREN | RPAREN | COLON | ASSIGN | MINUS | PLUS | DIV | POWER | STAR | LNOT | LAND | LOR | EQV | NEQV | XOR | EOR | LT | LE | GT | GE | NE | EQ | TRUE | FALSE | ID | INT_CONST | HEX_CONST | FLOAT_CONST | COMMENT | WS | STRING_CONST | CHAR_CONST );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA25_32 = input.LA(1);

                         
                        int index25_32 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA25_32>='\u0000' && LA25_32<='\uFFFF')) && ((getCharPositionInLine()==0))) {s = 34;}

                        else s = 54;

                         
                        input.seek(index25_32);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA25_45 = input.LA(1);

                         
                        int index25_45 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA25_45>='\u0000' && LA25_45<='/')||(LA25_45>=':' && LA25_45<='@')||(LA25_45>='[' && LA25_45<='^')||LA25_45=='`'||(LA25_45>='{' && LA25_45<='\uFFFF')) && ((getCharPositionInLine()==0))) {s = 34;}

                        else if ( ((LA25_45>='0' && LA25_45<='9')||(LA25_45>='A' && LA25_45<='Z')||LA25_45=='_'||(LA25_45>='a' && LA25_45<='z')) ) {s = 45;}

                        else s = 20;

                         
                        input.seek(index25_45);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA25_15 = input.LA(1);

                         
                        int index25_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA25_15=='*') && (((getCharPositionInLine()>0)||(getCharPositionInLine()==0)))) {s = 32;}

                        else if ( ((LA25_15>='\u0000' && LA25_15<=')')||(LA25_15>='+' && LA25_15<='\uFFFF')) && ((getCharPositionInLine()==0))) {s = 34;}

                        else s = 33;

                         
                        input.seek(index25_15);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA25_17 = input.LA(1);

                         
                        int index25_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA25_17>='0' && LA25_17<='9')||(LA25_17>='A' && LA25_17<='Z')||LA25_17=='_'||(LA25_17>='a' && LA25_17<='z')) ) {s = 45;}

                        else if ( ((LA25_17>='\u0000' && LA25_17<='/')||(LA25_17>=':' && LA25_17<='@')||(LA25_17>='[' && LA25_17<='^')||LA25_17=='`'||(LA25_17>='{' && LA25_17<='\uFFFF')) && ((getCharPositionInLine()==0))) {s = 34;}

                        else s = 20;

                         
                        input.seek(index25_17);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA25_0 = input.LA(1);

                         
                        int index25_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA25_0=='S'||LA25_0=='s') ) {s = 1;}

                        else if ( (LA25_0=='I'||LA25_0=='i') ) {s = 2;}

                        else if ( (LA25_0=='R'||LA25_0=='r') ) {s = 3;}

                        else if ( (LA25_0=='T'||LA25_0=='t') ) {s = 4;}

                        else if ( (LA25_0=='E'||LA25_0=='e') ) {s = 5;}

                        else if ( (LA25_0=='$') ) {s = 6;}

                        else if ( (LA25_0==',') ) {s = 7;}

                        else if ( (LA25_0=='(') ) {s = 8;}

                        else if ( (LA25_0==')') ) {s = 9;}

                        else if ( (LA25_0==':') ) {s = 10;}

                        else if ( (LA25_0=='=') ) {s = 11;}

                        else if ( (LA25_0=='-') ) {s = 12;}

                        else if ( (LA25_0=='+') ) {s = 13;}

                        else if ( (LA25_0=='/') ) {s = 14;}

                        else if ( (LA25_0=='*') && (((getCharPositionInLine()>0)||(getCharPositionInLine()==0)))) {s = 15;}

                        else if ( (LA25_0=='.') ) {s = 16;}

                        else if ( (LA25_0=='c') ) {s = 17;}

                        else if ( (LA25_0=='0') ) {s = 18;}

                        else if ( ((LA25_0>='1' && LA25_0<='9')) ) {s = 19;}

                        else if ( ((LA25_0>='A' && LA25_0<='D')||(LA25_0>='F' && LA25_0<='H')||(LA25_0>='J' && LA25_0<='Q')||(LA25_0>='U' && LA25_0<='Z')||LA25_0=='_'||(LA25_0>='a' && LA25_0<='b')||LA25_0=='d'||(LA25_0>='f' && LA25_0<='h')||(LA25_0>='j' && LA25_0<='q')||(LA25_0>='u' && LA25_0<='z')) ) {s = 20;}

                        else if ( ((LA25_0>='\t' && LA25_0<='\n')||LA25_0=='\r'||LA25_0==' ') ) {s = 21;}

                        else if ( (LA25_0=='\"') ) {s = 22;}

                        else if ( (LA25_0=='\'') ) {s = 23;}

                         
                        input.seek(index25_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 25, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}