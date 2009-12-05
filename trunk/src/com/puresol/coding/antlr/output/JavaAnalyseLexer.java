// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g 2009-12-05 19:11:56

package com.puresol.coding.antlr.output;

import com.puresol.coding.antlr.ANTLRJavaHelper;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class JavaAnalyseLexer extends Lexer {
    public static final int CLOSE_BRACKER=19;
    public static final int EXPONENT=21;
    public static final int STAR=9;
    public static final int VISIBILITY=5;
    public static final int UNICODE_ESC=30;
    public static final int OCTAL_ESC=31;
    public static final int CHAR=28;
    public static final int HEX_DIGIT=29;
    public static final int FLOAT=22;
    public static final int INT=20;
    public static final int SEMICOLON=4;
    public static final int MINUS=13;
    public static final int ID=7;
    public static final int EOF=-1;
    public static final int CLOSE_RECT_BRACKET=17;
    public static final int MODIFIER=6;
    public static final int LINEFEED=23;
    public static final int OPEN_BRACKET=18;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int WS=25;
    public static final int ESC_SEQ=26;
    public static final int COMMA=14;
    public static final int EQUAL=15;
    public static final int BLOCK_END=11;
    public static final int PLUS=12;
    public static final int OPEN_RECT_BRACKET=16;
    public static final int DOT=8;
    public static final int COMMENT=24;
    public static final int BLOCK_BEGIN=10;
    public static final int STRING=27;

    private ANTLRJavaHelper helper = null;

    public JavaAnalyseLexer(CharStream stream, ANTLRJavaHelper helper)
    {
    	this(stream);
    	this.helper = helper;
    }


    // delegates
    // delegators

    public JavaAnalyseLexer() {;} 
    public JavaAnalyseLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public JavaAnalyseLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g"; }

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:22:7: ( 'package' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:22:9: 'package'
            {
            match("package"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:23:7: ( 'import' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:23:9: 'import'
            {
            match("import"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "MODIFIER"
    public final void mMODIFIER() throws RecognitionException {
        try {
            int _type = MODIFIER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:49:2: ( VISIBILITY | 'final' | 'static' | 'transient' )
            int alt1=4;
            switch ( input.LA(1) ) {
            case 'p':
                {
                alt1=1;
                }
                break;
            case 'f':
                {
                alt1=2;
                }
                break;
            case 's':
                {
                alt1=3;
                }
                break;
            case 't':
                {
                alt1=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:49:4: VISIBILITY
                    {
                    mVISIBILITY(); 

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:50:4: 'final'
                    {
                    match("final"); 


                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:51:4: 'static'
                    {
                    match("static"); 


                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:52:4: 'transient'
                    {
                    match("transient"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MODIFIER"

    // $ANTLR start "VISIBILITY"
    public final void mVISIBILITY() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:57:2: ( 'public' | 'private' | 'protected' )
            int alt2=3;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='p') ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1=='u') ) {
                    alt2=1;
                }
                else if ( (LA2_1=='r') ) {
                    int LA2_3 = input.LA(3);

                    if ( (LA2_3=='i') ) {
                        alt2=2;
                    }
                    else if ( (LA2_3=='o') ) {
                        alt2=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 2, 3, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:57:4: 'public'
                    {
                    match("public"); 


                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:58:4: 'private'
                    {
                    match("private"); 


                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:59:4: 'protected'
                    {
                    match("protected"); 


                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "VISIBILITY"

    // $ANTLR start "BLOCK_BEGIN"
    public final void mBLOCK_BEGIN() throws RecognitionException {
        try {
            int _type = BLOCK_BEGIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:71:2: ( '{' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:71:4: '{'
            {
            match('{'); 
            helper.addBlockBegin();

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BLOCK_BEGIN"

    // $ANTLR start "BLOCK_END"
    public final void mBLOCK_END() throws RecognitionException {
        try {
            int _type = BLOCK_END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:75:2: ( '}' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:75:4: '}'
            {
            match('}'); 
            helper.addBlockEnd();

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BLOCK_END"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:78:6: ( '+' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:78:8: '+'
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

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:79:7: ( '-' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:79:9: '-'
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

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:80:5: ( '.' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:80:7: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "STAR"
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:81:6: ( '*' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:81:8: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STAR"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:82:7: ( ',' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:82:9: ','
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

    // $ANTLR start "EQUAL"
    public final void mEQUAL() throws RecognitionException {
        try {
            int _type = EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:83:7: ( '=' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:83:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQUAL"

    // $ANTLR start "OPEN_RECT_BRACKET"
    public final void mOPEN_RECT_BRACKET() throws RecognitionException {
        try {
            int _type = OPEN_RECT_BRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:86:2: ( '[' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:86:4: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OPEN_RECT_BRACKET"

    // $ANTLR start "CLOSE_RECT_BRACKET"
    public final void mCLOSE_RECT_BRACKET() throws RecognitionException {
        try {
            int _type = CLOSE_RECT_BRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:90:2: ( ']' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:90:4: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CLOSE_RECT_BRACKET"

    // $ANTLR start "OPEN_BRACKET"
    public final void mOPEN_BRACKET() throws RecognitionException {
        try {
            int _type = OPEN_BRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:94:2: ( '(' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:94:4: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OPEN_BRACKET"

    // $ANTLR start "CLOSE_BRACKER"
    public final void mCLOSE_BRACKER() throws RecognitionException {
        try {
            int _type = CLOSE_BRACKER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:98:2: ( ')' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:98:4: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CLOSE_BRACKER"

    // $ANTLR start "SEMICOLON"
    public final void mSEMICOLON() throws RecognitionException {
        try {
            int _type = SEMICOLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:102:2: ( ';' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:102:4: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SEMICOLON"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:104:5: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:104:7: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:104:31: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')||(LA3_0>='A' && LA3_0<='Z')||LA3_0=='_'||(LA3_0>='a' && LA3_0<='z')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:
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
            	    break loop3;
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

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:107:5: ( ( '0' .. '9' )+ )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:107:7: ( '0' .. '9' )+
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:107:7: ( '0' .. '9' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:107:7: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

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
    // $ANTLR end "INT"

    // $ANTLR start "FLOAT"
    public final void mFLOAT() throws RecognitionException {
        try {
            int _type = FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:111:5: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )+ ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT )
            int alt11=3;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:111:9: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )?
                    {
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:111:9: ( '0' .. '9' )+
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
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:111:10: '0' .. '9'
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
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:111:25: ( '0' .. '9' )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0>='0' && LA6_0<='9')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:111:26: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:111:37: ( EXPONENT )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0=='E'||LA7_0=='e') ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:111:37: EXPONENT
                            {
                            mEXPONENT(); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:112:9: '.' ( '0' .. '9' )+ ( EXPONENT )?
                    {
                    match('.'); 
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:112:13: ( '0' .. '9' )+
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
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:112:14: '0' .. '9'
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

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:112:25: ( EXPONENT )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0=='E'||LA9_0=='e') ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:112:25: EXPONENT
                            {
                            mEXPONENT(); 

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:113:9: ( '0' .. '9' )+ EXPONENT
                    {
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:113:9: ( '0' .. '9' )+
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
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:113:10: '0' .. '9'
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
    // $ANTLR end "FLOAT"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:117:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? LINEFEED | '/*' ( options {greedy=false; } : ( LINEFEED | ~ ( '\\n' ) ) )* '*/' )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0=='/') ) {
                int LA16_1 = input.LA(2);

                if ( (LA16_1=='/') ) {
                    alt16=1;
                }
                else if ( (LA16_1=='*') ) {
                    alt16=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 16, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:117:9: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? LINEFEED
                    {
                    match("//"); 

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:117:14: (~ ( '\\n' | '\\r' ) )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( ((LA12_0>='\u0000' && LA12_0<='\t')||(LA12_0>='\u000B' && LA12_0<='\f')||(LA12_0>='\u000E' && LA12_0<='\uFFFF')) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:117:14: ~ ( '\\n' | '\\r' )
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

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:117:28: ( '\\r' )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0=='\r') ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:117:28: '\\r'
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
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:118:9: '/*' ( options {greedy=false; } : ( LINEFEED | ~ ( '\\n' ) ) )* '*/'
                    {
                    match("/*"); 

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:118:14: ( options {greedy=false; } : ( LINEFEED | ~ ( '\\n' ) ) )*
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
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:118:42: ( LINEFEED | ~ ( '\\n' ) )
                    	    {
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:118:42: ( LINEFEED | ~ ( '\\n' ) )
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
                    	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:118:43: LINEFEED
                    	            {
                    	            mLINEFEED(); 

                    	            }
                    	            break;
                    	        case 2 :
                    	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:118:52: ~ ( '\\n' )
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:121:5: ( ( ' ' | '\\t' | '\\r' | LINEFEED ) )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:121:9: ( ' ' | '\\t' | '\\r' | LINEFEED )
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:121:9: ( ' ' | '\\t' | '\\r' | LINEFEED )
            int alt17=4;
            switch ( input.LA(1) ) {
            case ' ':
                {
                alt17=1;
                }
                break;
            case '\t':
                {
                alt17=2;
                }
                break;
            case '\r':
                {
                alt17=3;
                }
                break;
            case '\n':
                {
                alt17=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:121:11: ' '
                    {
                    match(' '); 

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:122:11: '\\t'
                    {
                    match('\t'); 

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:123:11: '\\r'
                    {
                    match('\r'); 

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:124:11: LINEFEED
                    {
                    mLINEFEED(); 

                    }
                    break;

            }

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:129:9: ( '\\n' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:129:11: '\\n'
            {
            match('\n'); 
            helper.incSlocCount();

            }

        }
        finally {
        }
    }
    // $ANTLR end "LINEFEED"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:132:5: ( '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:132:8: '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"'
            {
            match('\"'); 
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:132:12: ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )*
            loop18:
            do {
                int alt18=3;
                int LA18_0 = input.LA(1);

                if ( (LA18_0=='\\') ) {
                    alt18=1;
                }
                else if ( ((LA18_0>='\u0000' && LA18_0<='!')||(LA18_0>='#' && LA18_0<='[')||(LA18_0>=']' && LA18_0<='\uFFFF')) ) {
                    alt18=2;
                }


                switch (alt18) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:132:14: ESC_SEQ
            	    {
            	    mESC_SEQ(); 

            	    }
            	    break;
            	case 2 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:132:24: ~ ( '\\\\' | '\"' )
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
            	    break loop18;
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
    // $ANTLR end "STRING"

    // $ANTLR start "CHAR"
    public final void mCHAR() throws RecognitionException {
        try {
            int _type = CHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:135:5: ( '\\'' ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) ) '\\'' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:135:8: '\\'' ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) ) '\\''
            {
            match('\''); 
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:135:13: ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0=='\\') ) {
                alt19=1;
            }
            else if ( ((LA19_0>='\u0000' && LA19_0<='&')||(LA19_0>='(' && LA19_0<='[')||(LA19_0>=']' && LA19_0<='\uFFFF')) ) {
                alt19=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:135:15: ESC_SEQ
                    {
                    mESC_SEQ(); 

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:135:25: ~ ( '\\'' | '\\\\' )
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
    // $ANTLR end "CHAR"

    // $ANTLR start "EXPONENT"
    public final void mEXPONENT() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:139:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:139:12: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:139:22: ( '+' | '-' )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0=='+'||LA20_0=='-') ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:
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

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:139:33: ( '0' .. '9' )+
            int cnt21=0;
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( ((LA21_0>='0' && LA21_0<='9')) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:139:34: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt21 >= 1 ) break loop21;
                        EarlyExitException eee =
                            new EarlyExitException(21, input);
                        throw eee;
                }
                cnt21++;
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:142:11: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:142:13: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:146:5: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | UNICODE_ESC | OCTAL_ESC )
            int alt22=3;
            int LA22_0 = input.LA(1);

            if ( (LA22_0=='\\') ) {
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
                    alt22=1;
                    }
                    break;
                case 'u':
                    {
                    alt22=2;
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
                    alt22=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:146:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
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
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:147:9: UNICODE_ESC
                    {
                    mUNICODE_ESC(); 

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:148:9: OCTAL_ESC
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:153:5: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
            int alt23=3;
            int LA23_0 = input.LA(1);

            if ( (LA23_0=='\\') ) {
                int LA23_1 = input.LA(2);

                if ( ((LA23_1>='0' && LA23_1<='3')) ) {
                    int LA23_2 = input.LA(3);

                    if ( ((LA23_2>='0' && LA23_2<='7')) ) {
                        int LA23_4 = input.LA(4);

                        if ( ((LA23_4>='0' && LA23_4<='7')) ) {
                            alt23=1;
                        }
                        else {
                            alt23=2;}
                    }
                    else {
                        alt23=3;}
                }
                else if ( ((LA23_1>='4' && LA23_1<='7')) ) {
                    int LA23_3 = input.LA(3);

                    if ( ((LA23_3>='0' && LA23_3<='7')) ) {
                        alt23=2;
                    }
                    else {
                        alt23=3;}
                }
                else {
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
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:153:9: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:153:14: ( '0' .. '3' )
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:153:15: '0' .. '3'
                    {
                    matchRange('0','3'); 

                    }

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:153:25: ( '0' .. '7' )
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:153:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:153:36: ( '0' .. '7' )
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:153:37: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:154:9: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:154:14: ( '0' .. '7' )
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:154:15: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:154:25: ( '0' .. '7' )
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:154:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:155:9: '\\\\' ( '0' .. '7' )
                    {
                    match('\\'); 
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:155:14: ( '0' .. '7' )
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:155:15: '0' .. '7'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:160:5: ( '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:160:9: '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
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

    public void mTokens() throws RecognitionException {
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:1:8: ( T__32 | T__33 | MODIFIER | BLOCK_BEGIN | BLOCK_END | PLUS | MINUS | DOT | STAR | COMMA | EQUAL | OPEN_RECT_BRACKET | CLOSE_RECT_BRACKET | OPEN_BRACKET | CLOSE_BRACKER | SEMICOLON | ID | INT | FLOAT | COMMENT | WS | STRING | CHAR )
        int alt24=23;
        alt24 = dfa24.predict(input);
        switch (alt24) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:1:10: T__32
                {
                mT__32(); 

                }
                break;
            case 2 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:1:16: T__33
                {
                mT__33(); 

                }
                break;
            case 3 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:1:22: MODIFIER
                {
                mMODIFIER(); 

                }
                break;
            case 4 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:1:31: BLOCK_BEGIN
                {
                mBLOCK_BEGIN(); 

                }
                break;
            case 5 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:1:43: BLOCK_END
                {
                mBLOCK_END(); 

                }
                break;
            case 6 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:1:53: PLUS
                {
                mPLUS(); 

                }
                break;
            case 7 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:1:58: MINUS
                {
                mMINUS(); 

                }
                break;
            case 8 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:1:64: DOT
                {
                mDOT(); 

                }
                break;
            case 9 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:1:68: STAR
                {
                mSTAR(); 

                }
                break;
            case 10 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:1:73: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 11 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:1:79: EQUAL
                {
                mEQUAL(); 

                }
                break;
            case 12 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:1:85: OPEN_RECT_BRACKET
                {
                mOPEN_RECT_BRACKET(); 

                }
                break;
            case 13 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:1:103: CLOSE_RECT_BRACKET
                {
                mCLOSE_RECT_BRACKET(); 

                }
                break;
            case 14 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:1:122: OPEN_BRACKET
                {
                mOPEN_BRACKET(); 

                }
                break;
            case 15 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:1:135: CLOSE_BRACKER
                {
                mCLOSE_BRACKER(); 

                }
                break;
            case 16 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:1:149: SEMICOLON
                {
                mSEMICOLON(); 

                }
                break;
            case 17 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:1:159: ID
                {
                mID(); 

                }
                break;
            case 18 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:1:162: INT
                {
                mINT(); 

                }
                break;
            case 19 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:1:166: FLOAT
                {
                mFLOAT(); 

                }
                break;
            case 20 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:1:172: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 21 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:1:180: WS
                {
                mWS(); 

                }
                break;
            case 22 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:1:183: STRING
                {
                mSTRING(); 

                }
                break;
            case 23 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:1:190: CHAR
                {
                mCHAR(); 

                }
                break;

        }

    }


    protected DFA11 dfa11 = new DFA11(this);
    protected DFA24 dfa24 = new DFA24(this);
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
            return "110:1: FLOAT : ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )+ ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT );";
        }
    }
    static final String DFA24_eotS =
        "\1\uffff\5\23\4\uffff\1\40\11\uffff\1\42\4\uffff\7\23\3\uffff\25"+
        "\23\1\100\3\23\1\100\2\23\1\106\1\uffff\1\100\1\23\1\110\1\100\1"+
        "\23\1\uffff\1\23\1\uffff\2\23\2\100";
    static final String DFA24_eofS =
        "\115\uffff";
    static final String DFA24_minS =
        "\1\11\1\141\1\155\1\151\1\164\1\162\4\uffff\1\60\11\uffff\1\56\4"+
        "\uffff\1\143\1\142\1\151\1\160\1\156\2\141\3\uffff\1\153\1\154\1"+
        "\166\1\164\1\157\1\141\1\164\1\156\1\141\1\151\1\141\1\145\1\162"+
        "\1\154\1\151\1\163\1\147\1\143\1\164\1\143\1\164\1\60\1\143\1\151"+
        "\1\145\1\60\1\145\1\164\1\60\1\uffff\1\60\1\145\2\60\1\145\1\uffff"+
        "\1\156\1\uffff\1\144\1\164\2\60";
    static final String DFA24_maxS =
        "\1\175\1\165\1\155\1\151\1\164\1\162\4\uffff\1\71\11\uffff\1\145"+
        "\4\uffff\1\143\1\142\1\157\1\160\1\156\2\141\3\uffff\1\153\1\154"+
        "\1\166\1\164\1\157\1\141\1\164\1\156\1\141\1\151\1\141\1\145\1\162"+
        "\1\154\1\151\1\163\1\147\1\143\1\164\1\143\1\164\1\172\1\143\1\151"+
        "\1\145\1\172\1\145\1\164\1\172\1\uffff\1\172\1\145\2\172\1\145\1"+
        "\uffff\1\156\1\uffff\1\144\1\164\2\172";
    static final String DFA24_acceptS =
        "\6\uffff\1\4\1\5\1\6\1\7\1\uffff\1\11\1\12\1\13\1\14\1\15\1\16\1"+
        "\17\1\20\1\21\1\uffff\1\24\1\25\1\26\1\27\7\uffff\1\10\1\23\1\22"+
        "\35\uffff\1\3\5\uffff\1\2\1\uffff\1\1\4\uffff";
    static final String DFA24_specialS =
        "\115\uffff}>";
    static final String[] DFA24_transitionS = {
            "\2\26\2\uffff\1\26\22\uffff\1\26\1\uffff\1\27\4\uffff\1\30\1"+
            "\20\1\21\1\13\1\10\1\14\1\11\1\12\1\25\12\24\1\uffff\1\22\1"+
            "\uffff\1\15\3\uffff\32\23\1\16\1\uffff\1\17\1\uffff\1\23\1\uffff"+
            "\5\23\1\3\2\23\1\2\6\23\1\1\2\23\1\4\1\5\6\23\1\6\1\uffff\1"+
            "\7",
            "\1\31\20\uffff\1\33\2\uffff\1\32",
            "\1\34",
            "\1\35",
            "\1\36",
            "\1\37",
            "",
            "",
            "",
            "",
            "\12\41",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\41\1\uffff\12\24\13\uffff\1\41\37\uffff\1\41",
            "",
            "",
            "",
            "",
            "\1\43",
            "\1\44",
            "\1\45\5\uffff\1\46",
            "\1\47",
            "\1\50",
            "\1\51",
            "\1\52",
            "",
            "",
            "",
            "\1\53",
            "\1\54",
            "\1\55",
            "\1\56",
            "\1\57",
            "\1\60",
            "\1\61",
            "\1\62",
            "\1\63",
            "\1\64",
            "\1\65",
            "\1\66",
            "\1\67",
            "\1\70",
            "\1\71",
            "\1\72",
            "\1\73",
            "\1\74",
            "\1\75",
            "\1\76",
            "\1\77",
            "\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
            "\1\101",
            "\1\102",
            "\1\103",
            "\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
            "\1\104",
            "\1\105",
            "\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
            "",
            "\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
            "\1\107",
            "\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
            "\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
            "\1\111",
            "",
            "\1\112",
            "",
            "\1\113",
            "\1\114",
            "\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
            "\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23"
    };

    static final short[] DFA24_eot = DFA.unpackEncodedString(DFA24_eotS);
    static final short[] DFA24_eof = DFA.unpackEncodedString(DFA24_eofS);
    static final char[] DFA24_min = DFA.unpackEncodedStringToUnsignedChars(DFA24_minS);
    static final char[] DFA24_max = DFA.unpackEncodedStringToUnsignedChars(DFA24_maxS);
    static final short[] DFA24_accept = DFA.unpackEncodedString(DFA24_acceptS);
    static final short[] DFA24_special = DFA.unpackEncodedString(DFA24_specialS);
    static final short[][] DFA24_transition;

    static {
        int numStates = DFA24_transitionS.length;
        DFA24_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA24_transition[i] = DFA.unpackEncodedString(DFA24_transitionS[i]);
        }
    }

    class DFA24 extends DFA {

        public DFA24(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 24;
            this.eot = DFA24_eot;
            this.eof = DFA24_eof;
            this.min = DFA24_min;
            this.max = DFA24_max;
            this.accept = DFA24_accept;
            this.special = DFA24_special;
            this.transition = DFA24_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__32 | T__33 | MODIFIER | BLOCK_BEGIN | BLOCK_END | PLUS | MINUS | DOT | STAR | COMMA | EQUAL | OPEN_RECT_BRACKET | CLOSE_RECT_BRACKET | OPEN_BRACKET | CLOSE_BRACKER | SEMICOLON | ID | INT | FLOAT | COMMENT | WS | STRING | CHAR );";
        }
    }
 

}