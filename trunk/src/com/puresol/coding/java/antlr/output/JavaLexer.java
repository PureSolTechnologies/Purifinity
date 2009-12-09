// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g 2009-12-09 13:36:09

package com.puresol.coding.java.antlr.output;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class JavaLexer extends Lexer {
    public static final int PACKAGE=4;
    public static final int PROTECTED=27;
    public static final int DEC=41;
    public static final int EXPONENT=62;
    public static final int LT=49;
    public static final int CLASS=6;
    public static final int STAR=36;
    public static final int BIT_AND=45;
    public static final int WHILE=17;
    public static final int OCTAL_ESC=72;
    public static final int CASE=21;
    public static final int CHAR=69;
    public static final int NEW=10;
    public static final int DO=16;
    public static final int FOR=15;
    public static final int FLOAT=63;
    public static final int NOT=46;
    public static final int ID=60;
    public static final int CLOSE_RECT_BRACKET=52;
    public static final int EOF=-1;
    public static final int LOGICAL_AND=44;
    public static final int LINEFEED=64;
    public static final int IF=18;
    public static final int AT=57;
    public static final int OPEN_BRACKET=53;
    public static final int FINAL=28;
    public static final int INC=40;
    public static final int IMPORT=5;
    public static final int ESC_SEQ=67;
    public static final int BOOLEAN=59;
    public static final int SLASH=35;
    public static final int IMPLEMENTS=8;
    public static final int BIT_OR=43;
    public static final int COMMA=48;
    public static final int TRANSIENT=30;
    public static final int EQUAL=37;
    public static final int TILDE=58;
    public static final int THIS=13;
    public static final int RETURN=11;
    public static final int LOGICAL_OR=42;
    public static final int PLUS=33;
    public static final int VOID=9;
    public static final int SUPER=14;
    public static final int COMMENT=65;
    public static final int DOT=47;
    public static final int OPEN_CURLY_BRACKET=31;
    public static final int PRIVATE=26;
    public static final int STATIC=29;
    public static final int SWITCH=20;
    public static final int UNICODE_ESC=71;
    public static final int NULL=12;
    public static final int ELSE=19;
    public static final int CLOSE_CURLY_BRACKET=32;
    public static final int HEX_DIGIT=70;
    public static final int INT=61;
    public static final int SEMICOLON=56;
    public static final int MINUS=34;
    public static final int TRY=22;
    public static final int UNEQUAL=38;
    public static final int COLON=55;
    public static final int WS=66;
    public static final int FINALLY=24;
    public static final int ASSIGN=39;
    public static final int GT=50;
    public static final int OPEN_RECT_BRACKET=51;
    public static final int CATCH=23;
    public static final int EXTENDS=7;
    public static final int PUBLIC=25;
    public static final int STRING=68;
    public static final int CLOSE_BRACKET=54;

    // delegates
    // delegators

    public JavaLexer() {;} 
    public JavaLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public JavaLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g"; }

    // $ANTLR start "PACKAGE"
    public final void mPACKAGE() throws RecognitionException {
        try {
            int _type = PACKAGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:9:9: ( 'package' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:9:12: 'package'
            {
            match("package"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PACKAGE"

    // $ANTLR start "IMPORT"
    public final void mIMPORT() throws RecognitionException {
        try {
            int _type = IMPORT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:10:8: ( 'import' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:10:11: 'import'
            {
            match("import"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IMPORT"

    // $ANTLR start "CLASS"
    public final void mCLASS() throws RecognitionException {
        try {
            int _type = CLASS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:12:7: ( 'class' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:12:9: 'class'
            {
            match("class"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CLASS"

    // $ANTLR start "EXTENDS"
    public final void mEXTENDS() throws RecognitionException {
        try {
            int _type = EXTENDS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:13:9: ( 'extends' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:13:11: 'extends'
            {
            match("extends"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EXTENDS"

    // $ANTLR start "IMPLEMENTS"
    public final void mIMPLEMENTS() throws RecognitionException {
        try {
            int _type = IMPLEMENTS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:14:12: ( 'implements' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:14:14: 'implements'
            {
            match("implements"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IMPLEMENTS"

    // $ANTLR start "VOID"
    public final void mVOID() throws RecognitionException {
        try {
            int _type = VOID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:16:6: ( 'void' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:16:8: 'void'
            {
            match("void"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VOID"

    // $ANTLR start "NEW"
    public final void mNEW() throws RecognitionException {
        try {
            int _type = NEW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:17:5: ( 'new' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:17:7: 'new'
            {
            match("new"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NEW"

    // $ANTLR start "RETURN"
    public final void mRETURN() throws RecognitionException {
        try {
            int _type = RETURN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:18:8: ( 'return' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:18:10: 'return'
            {
            match("return"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RETURN"

    // $ANTLR start "NULL"
    public final void mNULL() throws RecognitionException {
        try {
            int _type = NULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:19:6: ( 'null' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:19:8: 'null'
            {
            match("null"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NULL"

    // $ANTLR start "THIS"
    public final void mTHIS() throws RecognitionException {
        try {
            int _type = THIS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:20:6: ( 'this' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:20:8: 'this'
            {
            match("this"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "THIS"

    // $ANTLR start "SUPER"
    public final void mSUPER() throws RecognitionException {
        try {
            int _type = SUPER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:21:7: ( 'super' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:21:9: 'super'
            {
            match("super"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SUPER"

    // $ANTLR start "FOR"
    public final void mFOR() throws RecognitionException {
        try {
            int _type = FOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:23:5: ( 'for' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:23:7: 'for'
            {
            match("for"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FOR"

    // $ANTLR start "DO"
    public final void mDO() throws RecognitionException {
        try {
            int _type = DO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:24:4: ( 'do' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:24:6: 'do'
            {
            match("do"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DO"

    // $ANTLR start "WHILE"
    public final void mWHILE() throws RecognitionException {
        try {
            int _type = WHILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:25:7: ( 'while' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:25:9: 'while'
            {
            match("while"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHILE"

    // $ANTLR start "IF"
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:26:4: ( 'if' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:26:6: 'if'
            {
            match("if"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IF"

    // $ANTLR start "ELSE"
    public final void mELSE() throws RecognitionException {
        try {
            int _type = ELSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:27:6: ( 'else' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:27:8: 'else'
            {
            match("else"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ELSE"

    // $ANTLR start "SWITCH"
    public final void mSWITCH() throws RecognitionException {
        try {
            int _type = SWITCH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:28:8: ( 'switch' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:28:10: 'switch'
            {
            match("switch"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SWITCH"

    // $ANTLR start "CASE"
    public final void mCASE() throws RecognitionException {
        try {
            int _type = CASE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:29:6: ( 'case' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:29:8: 'case'
            {
            match("case"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CASE"

    // $ANTLR start "TRY"
    public final void mTRY() throws RecognitionException {
        try {
            int _type = TRY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:31:5: ( 'try' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:31:7: 'try'
            {
            match("try"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TRY"

    // $ANTLR start "CATCH"
    public final void mCATCH() throws RecognitionException {
        try {
            int _type = CATCH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:32:7: ( 'catch' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:32:9: 'catch'
            {
            match("catch"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CATCH"

    // $ANTLR start "FINALLY"
    public final void mFINALLY() throws RecognitionException {
        try {
            int _type = FINALLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:33:9: ( 'finally' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:33:11: 'finally'
            {
            match("finally"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FINALLY"

    // $ANTLR start "PUBLIC"
    public final void mPUBLIC() throws RecognitionException {
        try {
            int _type = PUBLIC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:35:8: ( 'public' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:35:10: 'public'
            {
            match("public"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PUBLIC"

    // $ANTLR start "PRIVATE"
    public final void mPRIVATE() throws RecognitionException {
        try {
            int _type = PRIVATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:36:9: ( 'private' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:36:11: 'private'
            {
            match("private"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PRIVATE"

    // $ANTLR start "PROTECTED"
    public final void mPROTECTED() throws RecognitionException {
        try {
            int _type = PROTECTED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:38:2: ( 'protected' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:38:4: 'protected'
            {
            match("protected"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PROTECTED"

    // $ANTLR start "FINAL"
    public final void mFINAL() throws RecognitionException {
        try {
            int _type = FINAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:39:7: ( 'final' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:39:9: 'final'
            {
            match("final"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FINAL"

    // $ANTLR start "STATIC"
    public final void mSTATIC() throws RecognitionException {
        try {
            int _type = STATIC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:40:8: ( 'static' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:40:10: 'static'
            {
            match("static"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STATIC"

    // $ANTLR start "TRANSIENT"
    public final void mTRANSIENT() throws RecognitionException {
        try {
            int _type = TRANSIENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:42:2: ( 'transient' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:42:4: 'transient'
            {
            match("transient"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TRANSIENT"

    // $ANTLR start "OPEN_CURLY_BRACKET"
    public final void mOPEN_CURLY_BRACKET() throws RecognitionException {
        try {
            int _type = OPEN_CURLY_BRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:45:2: ( '{' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:45:4: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OPEN_CURLY_BRACKET"

    // $ANTLR start "CLOSE_CURLY_BRACKET"
    public final void mCLOSE_CURLY_BRACKET() throws RecognitionException {
        try {
            int _type = CLOSE_CURLY_BRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:49:2: ( '}' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:49:4: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CLOSE_CURLY_BRACKET"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:52:6: ( '+' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:52:8: '+'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:53:7: ( '-' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:53:9: '-'
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

    // $ANTLR start "SLASH"
    public final void mSLASH() throws RecognitionException {
        try {
            int _type = SLASH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:54:7: ( '/' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:54:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SLASH"

    // $ANTLR start "STAR"
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:55:6: ( '*' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:55:8: '*'
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

    // $ANTLR start "EQUAL"
    public final void mEQUAL() throws RecognitionException {
        try {
            int _type = EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:57:7: ( '==' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:57:9: '=='
            {
            match("=="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQUAL"

    // $ANTLR start "UNEQUAL"
    public final void mUNEQUAL() throws RecognitionException {
        try {
            int _type = UNEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:58:9: ( '!=' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:58:11: '!='
            {
            match("!="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UNEQUAL"

    // $ANTLR start "ASSIGN"
    public final void mASSIGN() throws RecognitionException {
        try {
            int _type = ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:59:8: ( '=' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:59:10: '='
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

    // $ANTLR start "INC"
    public final void mINC() throws RecognitionException {
        try {
            int _type = INC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:61:5: ( '++' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:61:7: '++'
            {
            match("++"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INC"

    // $ANTLR start "DEC"
    public final void mDEC() throws RecognitionException {
        try {
            int _type = DEC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:62:5: ( '--' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:62:7: '--'
            {
            match("--"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DEC"

    // $ANTLR start "LOGICAL_OR"
    public final void mLOGICAL_OR() throws RecognitionException {
        try {
            int _type = LOGICAL_OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:65:2: ( '||' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:65:4: '||'
            {
            match("||"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LOGICAL_OR"

    // $ANTLR start "BIT_OR"
    public final void mBIT_OR() throws RecognitionException {
        try {
            int _type = BIT_OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:66:8: ( '|' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:66:10: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BIT_OR"

    // $ANTLR start "LOGICAL_AND"
    public final void mLOGICAL_AND() throws RecognitionException {
        try {
            int _type = LOGICAL_AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:69:2: ( '&&' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:69:4: '&&'
            {
            match("&&"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LOGICAL_AND"

    // $ANTLR start "BIT_AND"
    public final void mBIT_AND() throws RecognitionException {
        try {
            int _type = BIT_AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:70:9: ( '&' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:70:11: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BIT_AND"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:72:5: ( '!' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:72:7: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:74:5: ( '.' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:74:7: '.'
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

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:75:7: ( ',' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:75:9: ','
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

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:76:4: ( '<' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:76:6: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LT"

    // $ANTLR start "GT"
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:77:4: ( '>' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:77:6: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GT"

    // $ANTLR start "OPEN_RECT_BRACKET"
    public final void mOPEN_RECT_BRACKET() throws RecognitionException {
        try {
            int _type = OPEN_RECT_BRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:80:2: ( '[' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:80:4: '['
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:84:2: ( ']' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:84:4: ']'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:88:2: ( '(' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:88:4: '('
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

    // $ANTLR start "CLOSE_BRACKET"
    public final void mCLOSE_BRACKET() throws RecognitionException {
        try {
            int _type = CLOSE_BRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:92:2: ( ')' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:92:4: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CLOSE_BRACKET"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:95:7: ( ':' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:95:9: ':'
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

    // $ANTLR start "SEMICOLON"
    public final void mSEMICOLON() throws RecognitionException {
        try {
            int _type = SEMICOLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:97:2: ( ';' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:97:4: ';'
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

    // $ANTLR start "AT"
    public final void mAT() throws RecognitionException {
        try {
            int _type = AT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:98:4: ( '@' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:98:6: '@'
            {
            match('@'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AT"

    // $ANTLR start "TILDE"
    public final void mTILDE() throws RecognitionException {
        try {
            int _type = TILDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:99:7: ( '~' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:99:9: '~'
            {
            match('~'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TILDE"

    // $ANTLR start "BOOLEAN"
    public final void mBOOLEAN() throws RecognitionException {
        try {
            int _type = BOOLEAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:101:9: ( 'true' | 'false' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='t') ) {
                alt1=1;
            }
            else if ( (LA1_0=='f') ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:101:11: 'true'
                    {
                    match("true"); 


                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:102:4: 'false'
                    {
                    match("false"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BOOLEAN"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:105:5: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:105:7: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:105:31: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')||(LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:
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

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:108:5: ( ( '0' .. '9' )+ )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:108:7: ( '0' .. '9' )+
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:108:7: ( '0' .. '9' )+
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
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:108:7: '0' .. '9'
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
    // $ANTLR end "INT"

    // $ANTLR start "FLOAT"
    public final void mFLOAT() throws RecognitionException {
        try {
            int _type = FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:112:5: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )+ ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT )
            int alt10=3;
            alt10 = dfa10.predict(input);
            switch (alt10) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:112:9: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )?
                    {
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:112:9: ( '0' .. '9' )+
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
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:112:10: '0' .. '9'
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

                    match('.'); 
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:112:25: ( '0' .. '9' )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:112:26: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:112:37: ( EXPONENT )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0=='E'||LA6_0=='e') ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:112:37: EXPONENT
                            {
                            mEXPONENT(); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:113:9: '.' ( '0' .. '9' )+ ( EXPONENT )?
                    {
                    match('.'); 
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:113:13: ( '0' .. '9' )+
                    int cnt7=0;
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( ((LA7_0>='0' && LA7_0<='9')) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:113:14: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt7 >= 1 ) break loop7;
                                EarlyExitException eee =
                                    new EarlyExitException(7, input);
                                throw eee;
                        }
                        cnt7++;
                    } while (true);

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:113:25: ( EXPONENT )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0=='E'||LA8_0=='e') ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:113:25: EXPONENT
                            {
                            mEXPONENT(); 

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:114:9: ( '0' .. '9' )+ EXPONENT
                    {
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:114:9: ( '0' .. '9' )+
                    int cnt9=0;
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( ((LA9_0>='0' && LA9_0<='9')) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:114:10: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt9 >= 1 ) break loop9;
                                EarlyExitException eee =
                                    new EarlyExitException(9, input);
                                throw eee;
                        }
                        cnt9++;
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:118:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? LINEFEED | '/*' ( options {greedy=false; } : ( LINEFEED | ~ ( '\\n' ) ) )* '*/' )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0=='/') ) {
                int LA15_1 = input.LA(2);

                if ( (LA15_1=='/') ) {
                    alt15=1;
                }
                else if ( (LA15_1=='*') ) {
                    alt15=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 15, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:118:9: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? LINEFEED
                    {
                    match("//"); 

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:118:14: (~ ( '\\n' | '\\r' ) )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( ((LA11_0>='\u0000' && LA11_0<='\t')||(LA11_0>='\u000B' && LA11_0<='\f')||(LA11_0>='\u000E' && LA11_0<='\uFFFF')) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:118:14: ~ ( '\\n' | '\\r' )
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
                    	    break loop11;
                        }
                    } while (true);

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:118:28: ( '\\r' )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0=='\r') ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:118:28: '\\r'
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
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:119:9: '/*' ( options {greedy=false; } : ( LINEFEED | ~ ( '\\n' ) ) )* '*/'
                    {
                    match("/*"); 

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:119:14: ( options {greedy=false; } : ( LINEFEED | ~ ( '\\n' ) ) )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0=='*') ) {
                            int LA14_1 = input.LA(2);

                            if ( (LA14_1=='/') ) {
                                alt14=2;
                            }
                            else if ( ((LA14_1>='\u0000' && LA14_1<='.')||(LA14_1>='0' && LA14_1<='\uFFFF')) ) {
                                alt14=1;
                            }


                        }
                        else if ( ((LA14_0>='\u0000' && LA14_0<=')')||(LA14_0>='+' && LA14_0<='\uFFFF')) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:119:42: ( LINEFEED | ~ ( '\\n' ) )
                    	    {
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:119:42: ( LINEFEED | ~ ( '\\n' ) )
                    	    int alt13=2;
                    	    int LA13_0 = input.LA(1);

                    	    if ( (LA13_0=='\n') ) {
                    	        alt13=1;
                    	    }
                    	    else if ( ((LA13_0>='\u0000' && LA13_0<='\t')||(LA13_0>='\u000B' && LA13_0<='\uFFFF')) ) {
                    	        alt13=2;
                    	    }
                    	    else {
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 13, 0, input);

                    	        throw nvae;
                    	    }
                    	    switch (alt13) {
                    	        case 1 :
                    	            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:119:43: LINEFEED
                    	            {
                    	            mLINEFEED(); 

                    	            }
                    	            break;
                    	        case 2 :
                    	            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:119:52: ~ ( '\\n' )
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
                    	    break loop14;
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:122:5: ( ( ' ' | '\\t' | '\\r' | LINEFEED ) )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:122:9: ( ' ' | '\\t' | '\\r' | LINEFEED )
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:130:9: ( '\\n' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:130:11: '\\n'
            {
            match('\n'); 

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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:133:5: ( '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:133:8: '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"'
            {
            match('\"'); 
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:133:12: ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )*
            loop16:
            do {
                int alt16=3;
                int LA16_0 = input.LA(1);

                if ( (LA16_0=='\\') ) {
                    alt16=1;
                }
                else if ( ((LA16_0>='\u0000' && LA16_0<='!')||(LA16_0>='#' && LA16_0<='[')||(LA16_0>=']' && LA16_0<='\uFFFF')) ) {
                    alt16=2;
                }


                switch (alt16) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:133:14: ESC_SEQ
            	    {
            	    mESC_SEQ(); 

            	    }
            	    break;
            	case 2 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:133:24: ~ ( '\\\\' | '\"' )
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
            	    break loop16;
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:136:5: ( '\\'' ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) ) '\\'' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:136:8: '\\'' ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) ) '\\''
            {
            match('\''); 
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:136:13: ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0=='\\') ) {
                alt17=1;
            }
            else if ( ((LA17_0>='\u0000' && LA17_0<='&')||(LA17_0>='(' && LA17_0<='[')||(LA17_0>=']' && LA17_0<='\uFFFF')) ) {
                alt17=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }
            switch (alt17) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:136:15: ESC_SEQ
                    {
                    mESC_SEQ(); 

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:136:25: ~ ( '\\'' | '\\\\' )
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:140:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:140:12: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:140:22: ( '+' | '-' )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0=='+'||LA18_0=='-') ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:
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

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:140:33: ( '0' .. '9' )+
            int cnt19=0;
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( ((LA19_0>='0' && LA19_0<='9')) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:140:34: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt19 >= 1 ) break loop19;
                        EarlyExitException eee =
                            new EarlyExitException(19, input);
                        throw eee;
                }
                cnt19++;
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:143:11: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:143:13: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:147:5: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | UNICODE_ESC | OCTAL_ESC )
            int alt20=3;
            int LA20_0 = input.LA(1);

            if ( (LA20_0=='\\') ) {
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
                    alt20=1;
                    }
                    break;
                case 'u':
                    {
                    alt20=2;
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
                    alt20=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 20, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:147:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
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
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:148:9: UNICODE_ESC
                    {
                    mUNICODE_ESC(); 

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:149:9: OCTAL_ESC
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:154:5: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
            int alt21=3;
            int LA21_0 = input.LA(1);

            if ( (LA21_0=='\\') ) {
                int LA21_1 = input.LA(2);

                if ( ((LA21_1>='0' && LA21_1<='3')) ) {
                    int LA21_2 = input.LA(3);

                    if ( ((LA21_2>='0' && LA21_2<='7')) ) {
                        int LA21_4 = input.LA(4);

                        if ( ((LA21_4>='0' && LA21_4<='7')) ) {
                            alt21=1;
                        }
                        else {
                            alt21=2;}
                    }
                    else {
                        alt21=3;}
                }
                else if ( ((LA21_1>='4' && LA21_1<='7')) ) {
                    int LA21_3 = input.LA(3);

                    if ( ((LA21_3>='0' && LA21_3<='7')) ) {
                        alt21=2;
                    }
                    else {
                        alt21=3;}
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 21, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }
            switch (alt21) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:154:9: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:154:14: ( '0' .. '3' )
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:154:15: '0' .. '3'
                    {
                    matchRange('0','3'); 

                    }

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:154:25: ( '0' .. '7' )
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:154:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:154:36: ( '0' .. '7' )
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:154:37: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:155:9: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:155:14: ( '0' .. '7' )
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:155:15: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:155:25: ( '0' .. '7' )
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:155:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:156:9: '\\\\' ( '0' .. '7' )
                    {
                    match('\\'); 
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:156:14: ( '0' .. '7' )
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:156:15: '0' .. '7'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:161:5: ( '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:161:9: '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
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
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:8: ( PACKAGE | IMPORT | CLASS | EXTENDS | IMPLEMENTS | VOID | NEW | RETURN | NULL | THIS | SUPER | FOR | DO | WHILE | IF | ELSE | SWITCH | CASE | TRY | CATCH | FINALLY | PUBLIC | PRIVATE | PROTECTED | FINAL | STATIC | TRANSIENT | OPEN_CURLY_BRACKET | CLOSE_CURLY_BRACKET | PLUS | MINUS | SLASH | STAR | EQUAL | UNEQUAL | ASSIGN | INC | DEC | LOGICAL_OR | BIT_OR | LOGICAL_AND | BIT_AND | NOT | DOT | COMMA | LT | GT | OPEN_RECT_BRACKET | CLOSE_RECT_BRACKET | OPEN_BRACKET | CLOSE_BRACKET | COLON | SEMICOLON | AT | TILDE | BOOLEAN | ID | INT | FLOAT | COMMENT | WS | STRING | CHAR )
        int alt22=63;
        alt22 = dfa22.predict(input);
        switch (alt22) {
            case 1 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:10: PACKAGE
                {
                mPACKAGE(); 

                }
                break;
            case 2 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:18: IMPORT
                {
                mIMPORT(); 

                }
                break;
            case 3 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:25: CLASS
                {
                mCLASS(); 

                }
                break;
            case 4 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:31: EXTENDS
                {
                mEXTENDS(); 

                }
                break;
            case 5 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:39: IMPLEMENTS
                {
                mIMPLEMENTS(); 

                }
                break;
            case 6 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:50: VOID
                {
                mVOID(); 

                }
                break;
            case 7 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:55: NEW
                {
                mNEW(); 

                }
                break;
            case 8 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:59: RETURN
                {
                mRETURN(); 

                }
                break;
            case 9 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:66: NULL
                {
                mNULL(); 

                }
                break;
            case 10 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:71: THIS
                {
                mTHIS(); 

                }
                break;
            case 11 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:76: SUPER
                {
                mSUPER(); 

                }
                break;
            case 12 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:82: FOR
                {
                mFOR(); 

                }
                break;
            case 13 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:86: DO
                {
                mDO(); 

                }
                break;
            case 14 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:89: WHILE
                {
                mWHILE(); 

                }
                break;
            case 15 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:95: IF
                {
                mIF(); 

                }
                break;
            case 16 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:98: ELSE
                {
                mELSE(); 

                }
                break;
            case 17 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:103: SWITCH
                {
                mSWITCH(); 

                }
                break;
            case 18 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:110: CASE
                {
                mCASE(); 

                }
                break;
            case 19 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:115: TRY
                {
                mTRY(); 

                }
                break;
            case 20 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:119: CATCH
                {
                mCATCH(); 

                }
                break;
            case 21 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:125: FINALLY
                {
                mFINALLY(); 

                }
                break;
            case 22 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:133: PUBLIC
                {
                mPUBLIC(); 

                }
                break;
            case 23 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:140: PRIVATE
                {
                mPRIVATE(); 

                }
                break;
            case 24 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:148: PROTECTED
                {
                mPROTECTED(); 

                }
                break;
            case 25 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:158: FINAL
                {
                mFINAL(); 

                }
                break;
            case 26 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:164: STATIC
                {
                mSTATIC(); 

                }
                break;
            case 27 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:171: TRANSIENT
                {
                mTRANSIENT(); 

                }
                break;
            case 28 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:181: OPEN_CURLY_BRACKET
                {
                mOPEN_CURLY_BRACKET(); 

                }
                break;
            case 29 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:200: CLOSE_CURLY_BRACKET
                {
                mCLOSE_CURLY_BRACKET(); 

                }
                break;
            case 30 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:220: PLUS
                {
                mPLUS(); 

                }
                break;
            case 31 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:225: MINUS
                {
                mMINUS(); 

                }
                break;
            case 32 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:231: SLASH
                {
                mSLASH(); 

                }
                break;
            case 33 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:237: STAR
                {
                mSTAR(); 

                }
                break;
            case 34 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:242: EQUAL
                {
                mEQUAL(); 

                }
                break;
            case 35 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:248: UNEQUAL
                {
                mUNEQUAL(); 

                }
                break;
            case 36 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:256: ASSIGN
                {
                mASSIGN(); 

                }
                break;
            case 37 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:263: INC
                {
                mINC(); 

                }
                break;
            case 38 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:267: DEC
                {
                mDEC(); 

                }
                break;
            case 39 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:271: LOGICAL_OR
                {
                mLOGICAL_OR(); 

                }
                break;
            case 40 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:282: BIT_OR
                {
                mBIT_OR(); 

                }
                break;
            case 41 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:289: LOGICAL_AND
                {
                mLOGICAL_AND(); 

                }
                break;
            case 42 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:301: BIT_AND
                {
                mBIT_AND(); 

                }
                break;
            case 43 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:309: NOT
                {
                mNOT(); 

                }
                break;
            case 44 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:313: DOT
                {
                mDOT(); 

                }
                break;
            case 45 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:317: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 46 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:323: LT
                {
                mLT(); 

                }
                break;
            case 47 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:326: GT
                {
                mGT(); 

                }
                break;
            case 48 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:329: OPEN_RECT_BRACKET
                {
                mOPEN_RECT_BRACKET(); 

                }
                break;
            case 49 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:347: CLOSE_RECT_BRACKET
                {
                mCLOSE_RECT_BRACKET(); 

                }
                break;
            case 50 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:366: OPEN_BRACKET
                {
                mOPEN_BRACKET(); 

                }
                break;
            case 51 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:379: CLOSE_BRACKET
                {
                mCLOSE_BRACKET(); 

                }
                break;
            case 52 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:393: COLON
                {
                mCOLON(); 

                }
                break;
            case 53 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:399: SEMICOLON
                {
                mSEMICOLON(); 

                }
                break;
            case 54 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:409: AT
                {
                mAT(); 

                }
                break;
            case 55 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:412: TILDE
                {
                mTILDE(); 

                }
                break;
            case 56 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:418: BOOLEAN
                {
                mBOOLEAN(); 

                }
                break;
            case 57 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:426: ID
                {
                mID(); 

                }
                break;
            case 58 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:429: INT
                {
                mINT(); 

                }
                break;
            case 59 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:433: FLOAT
                {
                mFLOAT(); 

                }
                break;
            case 60 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:439: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 61 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:447: WS
                {
                mWS(); 

                }
                break;
            case 62 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:450: STRING
                {
                mSTRING(); 

                }
                break;
            case 63 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:457: CHAR
                {
                mCHAR(); 

                }
                break;

        }

    }


    protected DFA10 dfa10 = new DFA10(this);
    protected DFA22 dfa22 = new DFA22(this);
    static final String DFA10_eotS =
        "\5\uffff";
    static final String DFA10_eofS =
        "\5\uffff";
    static final String DFA10_minS =
        "\2\56\3\uffff";
    static final String DFA10_maxS =
        "\1\71\1\145\3\uffff";
    static final String DFA10_acceptS =
        "\2\uffff\1\2\1\1\1\3";
    static final String DFA10_specialS =
        "\5\uffff}>";
    static final String[] DFA10_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\3\1\uffff\12\1\13\uffff\1\4\37\uffff\1\4",
            "",
            "",
            ""
    };

    static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
    static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
    static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
    static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
    static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
    static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
    static final short[][] DFA10_transition;

    static {
        int numStates = DFA10_transitionS.length;
        DFA10_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = DFA10_eot;
            this.eof = DFA10_eof;
            this.min = DFA10_min;
            this.max = DFA10_max;
            this.accept = DFA10_accept;
            this.special = DFA10_special;
            this.transition = DFA10_transition;
        }
        public String getDescription() {
            return "111:1: FLOAT : ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )+ ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT );";
        }
    }
    static final String DFA22_eotS =
        "\1\uffff\14\43\2\uffff\1\100\1\102\1\104\1\uffff\1\106\1\110\1\112"+
        "\1\114\1\115\14\uffff\1\117\3\uffff\4\43\1\125\20\43\1\151\1\43"+
        "\21\uffff\5\43\1\uffff\6\43\1\167\3\43\1\173\5\43\1\u0081\2\43\1"+
        "\uffff\10\43\1\u008c\2\43\1\u008f\1\u0090\1\uffff\1\u0091\1\43\1"+
        "\u0093\1\uffff\1\43\1\u0095\3\43\1\uffff\11\43\1\u00a2\1\uffff\1"+
        "\u00a3\1\43\3\uffff\1\43\1\uffff\1\43\1\uffff\1\u00a7\2\43\1\u00ab"+
        "\1\u0095\1\u00ac\1\43\1\u00ae\2\43\1\u00b1\1\43\2\uffff\1\43\1\u00b4"+
        "\1\43\1\uffff\1\u00b6\1\u00b7\1\43\2\uffff\1\u00b9\1\uffff\1\u00ba"+
        "\1\43\1\uffff\1\43\1\u00bd\1\uffff\1\43\2\uffff\1\u00bf\2\uffff"+
        "\2\43\1\uffff\1\43\1\uffff\1\u00c3\1\43\1\u00c5\1\uffff\1\u00c6"+
        "\2\uffff";
    static final String DFA22_eofS =
        "\u00c7\uffff";
    static final String DFA22_minS =
        "\1\11\1\141\1\146\1\141\1\154\1\157\2\145\1\150\1\164\1\141\1\157"+
        "\1\150\2\uffff\1\53\1\55\1\52\1\uffff\2\75\1\174\1\46\1\60\14\uffff"+
        "\1\56\3\uffff\1\143\1\142\1\151\1\160\1\60\1\141\1\163\1\164\1\163"+
        "\1\151\1\167\1\154\1\164\1\151\1\141\1\160\1\151\1\141\1\162\1\156"+
        "\1\154\1\60\1\151\21\uffff\1\153\1\154\1\166\1\164\1\154\1\uffff"+
        "\1\163\1\145\1\143\2\145\1\144\1\60\1\154\1\165\1\163\1\60\1\156"+
        "\2\145\2\164\1\60\1\141\1\163\1\uffff\1\154\1\141\1\151\1\141\1"+
        "\145\1\162\1\145\1\163\1\60\1\150\1\156\2\60\1\uffff\1\60\1\162"+
        "\1\60\1\uffff\1\163\1\60\1\162\1\143\1\151\1\uffff\1\154\2\145\1"+
        "\147\1\143\1\164\1\143\1\164\1\155\1\60\1\uffff\1\60\1\144\3\uffff"+
        "\1\156\1\uffff\1\151\1\uffff\1\60\1\150\1\143\3\60\1\145\1\60\1"+
        "\145\1\164\1\60\1\145\2\uffff\1\163\1\60\1\145\1\uffff\2\60\1\171"+
        "\2\uffff\1\60\1\uffff\1\60\1\145\1\uffff\1\156\1\60\1\uffff\1\156"+
        "\2\uffff\1\60\2\uffff\1\144\1\164\1\uffff\1\164\1\uffff\1\60\1\163"+
        "\1\60\1\uffff\1\60\2\uffff";
    static final String DFA22_maxS =
        "\1\176\1\165\1\155\1\154\1\170\1\157\1\165\1\145\1\162\1\167\2\157"+
        "\1\150\2\uffff\1\53\1\55\1\57\1\uffff\2\75\1\174\1\46\1\71\14\uffff"+
        "\1\145\3\uffff\1\143\1\142\1\157\1\160\1\172\1\141\2\164\1\163\1"+
        "\151\1\167\1\154\1\164\1\151\1\171\1\160\1\151\1\141\1\162\1\156"+
        "\1\154\1\172\1\151\21\uffff\1\153\1\154\1\166\1\164\1\157\1\uffff"+
        "\1\163\1\145\1\143\2\145\1\144\1\172\1\154\1\165\1\163\1\172\1\156"+
        "\2\145\2\164\1\172\1\141\1\163\1\uffff\1\154\1\141\1\151\1\141\1"+
        "\145\1\162\1\145\1\163\1\172\1\150\1\156\2\172\1\uffff\1\172\1\162"+
        "\1\172\1\uffff\1\163\1\172\1\162\1\143\1\151\1\uffff\1\154\2\145"+
        "\1\147\1\143\1\164\1\143\1\164\1\155\1\172\1\uffff\1\172\1\144\3"+
        "\uffff\1\156\1\uffff\1\151\1\uffff\1\172\1\150\1\143\3\172\1\145"+
        "\1\172\1\145\1\164\1\172\1\145\2\uffff\1\163\1\172\1\145\1\uffff"+
        "\2\172\1\171\2\uffff\1\172\1\uffff\1\172\1\145\1\uffff\1\156\1\172"+
        "\1\uffff\1\156\2\uffff\1\172\2\uffff\1\144\1\164\1\uffff\1\164\1"+
        "\uffff\1\172\1\163\1\172\1\uffff\1\172\2\uffff";
    static final String DFA22_acceptS =
        "\15\uffff\1\34\1\35\3\uffff\1\41\5\uffff\1\55\1\56\1\57\1\60\1\61"+
        "\1\62\1\63\1\64\1\65\1\66\1\67\1\71\1\uffff\1\75\1\76\1\77\27\uffff"+
        "\1\45\1\36\1\46\1\37\1\74\1\40\1\42\1\44\1\43\1\53\1\47\1\50\1\51"+
        "\1\52\1\54\1\73\1\72\5\uffff\1\17\23\uffff\1\15\15\uffff\1\7\3\uffff"+
        "\1\23\5\uffff\1\14\12\uffff\1\22\2\uffff\1\20\1\6\1\11\1\uffff\1"+
        "\12\1\uffff\1\70\14\uffff\1\3\1\24\3\uffff\1\13\3\uffff\1\31\1\16"+
        "\1\uffff\1\26\2\uffff\1\2\2\uffff\1\10\1\uffff\1\21\1\32\1\uffff"+
        "\1\1\1\27\2\uffff\1\4\1\uffff\1\25\3\uffff\1\30\1\uffff\1\33\1\5";
    static final String DFA22_specialS =
        "\u00c7\uffff}>";
    static final String[] DFA22_transitionS = {
            "\2\45\2\uffff\1\45\22\uffff\1\45\1\24\1\46\3\uffff\1\26\1\47"+
            "\1\35\1\36\1\22\1\17\1\30\1\20\1\27\1\21\12\44\1\37\1\40\1\31"+
            "\1\23\1\32\1\uffff\1\41\32\43\1\33\1\uffff\1\34\1\uffff\1\43"+
            "\1\uffff\2\43\1\3\1\13\1\4\1\12\2\43\1\2\4\43\1\6\1\43\1\1\1"+
            "\43\1\7\1\11\1\10\1\43\1\5\1\14\3\43\1\15\1\25\1\16\1\42",
            "\1\50\20\uffff\1\52\2\uffff\1\51",
            "\1\54\6\uffff\1\53",
            "\1\56\12\uffff\1\55",
            "\1\60\13\uffff\1\57",
            "\1\61",
            "\1\62\17\uffff\1\63",
            "\1\64",
            "\1\65\11\uffff\1\66",
            "\1\71\1\67\1\uffff\1\70",
            "\1\74\7\uffff\1\73\5\uffff\1\72",
            "\1\75",
            "\1\76",
            "",
            "",
            "\1\77",
            "\1\101",
            "\1\103\4\uffff\1\103",
            "",
            "\1\105",
            "\1\107",
            "\1\111",
            "\1\113",
            "\12\116",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\116\1\uffff\12\44\13\uffff\1\116\37\uffff\1\116",
            "",
            "",
            "",
            "\1\120",
            "\1\121",
            "\1\122\5\uffff\1\123",
            "\1\124",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\126",
            "\1\127\1\130",
            "\1\131",
            "\1\132",
            "\1\133",
            "\1\134",
            "\1\135",
            "\1\136",
            "\1\137",
            "\1\141\23\uffff\1\142\3\uffff\1\140",
            "\1\143",
            "\1\144",
            "\1\145",
            "\1\146",
            "\1\147",
            "\1\150",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\152",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\153",
            "\1\154",
            "\1\155",
            "\1\156",
            "\1\160\2\uffff\1\157",
            "",
            "\1\161",
            "\1\162",
            "\1\163",
            "\1\164",
            "\1\165",
            "\1\166",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\170",
            "\1\171",
            "\1\172",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\174",
            "\1\175",
            "\1\176",
            "\1\177",
            "\1\u0080",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\u0082",
            "\1\u0083",
            "",
            "\1\u0084",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088",
            "\1\u0089",
            "\1\u008a",
            "\1\u008b",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\u008d",
            "\1\u008e",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\u0092",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "",
            "\1\u0094",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\u0096",
            "\1\u0097",
            "\1\u0098",
            "",
            "\1\u0099",
            "\1\u009a",
            "\1\u009b",
            "\1\u009c",
            "\1\u009d",
            "\1\u009e",
            "\1\u009f",
            "\1\u00a0",
            "\1\u00a1",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\u00a4",
            "",
            "",
            "",
            "\1\u00a5",
            "",
            "\1\u00a6",
            "",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\u00a8",
            "\1\u00a9",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\13\43\1\u00aa\16"+
            "\43",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\u00ad",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\u00af",
            "\1\u00b0",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\u00b2",
            "",
            "",
            "\1\u00b3",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\u00b5",
            "",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\u00b8",
            "",
            "",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\u00bb",
            "",
            "\1\u00bc",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "",
            "\1\u00be",
            "",
            "",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "",
            "",
            "\1\u00c0",
            "\1\u00c1",
            "",
            "\1\u00c2",
            "",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\u00c4",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "",
            ""
    };

    static final short[] DFA22_eot = DFA.unpackEncodedString(DFA22_eotS);
    static final short[] DFA22_eof = DFA.unpackEncodedString(DFA22_eofS);
    static final char[] DFA22_min = DFA.unpackEncodedStringToUnsignedChars(DFA22_minS);
    static final char[] DFA22_max = DFA.unpackEncodedStringToUnsignedChars(DFA22_maxS);
    static final short[] DFA22_accept = DFA.unpackEncodedString(DFA22_acceptS);
    static final short[] DFA22_special = DFA.unpackEncodedString(DFA22_specialS);
    static final short[][] DFA22_transition;

    static {
        int numStates = DFA22_transitionS.length;
        DFA22_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA22_transition[i] = DFA.unpackEncodedString(DFA22_transitionS[i]);
        }
    }

    class DFA22 extends DFA {

        public DFA22(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 22;
            this.eot = DFA22_eot;
            this.eof = DFA22_eof;
            this.min = DFA22_min;
            this.max = DFA22_max;
            this.accept = DFA22_accept;
            this.special = DFA22_special;
            this.transition = DFA22_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( PACKAGE | IMPORT | CLASS | EXTENDS | IMPLEMENTS | VOID | NEW | RETURN | NULL | THIS | SUPER | FOR | DO | WHILE | IF | ELSE | SWITCH | CASE | TRY | CATCH | FINALLY | PUBLIC | PRIVATE | PROTECTED | FINAL | STATIC | TRANSIENT | OPEN_CURLY_BRACKET | CLOSE_CURLY_BRACKET | PLUS | MINUS | SLASH | STAR | EQUAL | UNEQUAL | ASSIGN | INC | DEC | LOGICAL_OR | BIT_OR | LOGICAL_AND | BIT_AND | NOT | DOT | COMMA | LT | GT | OPEN_RECT_BRACKET | CLOSE_RECT_BRACKET | OPEN_BRACKET | CLOSE_BRACKET | COLON | SEMICOLON | AT | TILDE | BOOLEAN | ID | INT | FLOAT | COMMENT | WS | STRING | CHAR );";
        }
    }
 

}