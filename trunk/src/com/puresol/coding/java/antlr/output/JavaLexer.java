// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g 2009-12-11 17:09:21

package com.puresol.coding.java.antlr.output;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class JavaLexer extends Lexer {
    public static final int PACKAGE=4;
    public static final int LT=69;
    public static final int EXPONENT=86;
    public static final int STAR=60;
    public static final int WHILE=21;
    public static final int OCTAL_ESC=95;
    public static final int CASE=25;
    public static final int CHAR=40;
    public static final int NEW=12;
    public static final int DO=20;
    public static final int NOT=66;
    public static final int EOF=-1;
    public static final int BREAK=14;
    public static final int LOGICAL_AND=64;
    public static final int HEX_LONG_CONST=85;
    public static final int OPEN_BRACKET=73;
    public static final int FINAL=35;
    public static final int INC=55;
    public static final int IMPORT=5;
    public static final int BIT_OR=63;
    public static final int HEX_CONST=83;
    public static final int STRING_CONST=92;
    public static final int RETURN=13;
    public static final int THIS=17;
    public static final int DOUBLE=45;
    public static final int VOID=11;
    public static final int SUPER=18;
    public static final int COMMENT=89;
    public static final int GE=49;
    public static final int STATIC=36;
    public static final int PRIVATE=33;
    public static final int SWITCH=24;
    public static final int NULL=16;
    public static final int ELSE=23;
    public static final int THROWS=31;
    public static final int SEMICOLON=76;
    public static final int TRY=27;
    public static final int WS=90;
    public static final int GT=70;
    public static final int CATCH=28;
    public static final int THROW=30;
    public static final int CLOSE_BRACKET=74;
    public static final int PROTECTED=34;
    public static final int DEC=56;
    public static final int CLASS=6;
    public static final int INCASSIGN=53;
    public static final int BIT_AND=65;
    public static final int FOR=19;
    public static final int FLOAT=44;
    public static final int LONG_CONST=84;
    public static final int ID=80;
    public static final int CLOSE_RECT_BRACKET=72;
    public static final int FLOAT_CONST=87;
    public static final int CHAR_CONST=93;
    public static final int IF=22;
    public static final int LINEFEED=88;
    public static final int AT=77;
    public static final int BOOLEAN=38;
    public static final int SLASH=59;
    public static final int ESC_SEQ=91;
    public static final int IMPLEMENTS=10;
    public static final int CONTINUE=15;
    public static final int COMMA=68;
    public static final int TRANSIENT=37;
    public static final int EQUAL=50;
    public static final int LOGICAL_OR=62;
    public static final int TILDE=78;
    public static final int PLUS=57;
    public static final int DOT=67;
    public static final int INTEGER=42;
    public static final int BYTE=39;
    public static final int OPEN_CURLY_BRACKET=46;
    public static final int UNICODE_ESC=94;
    public static final int DEFAULT=26;
    public static final int CLOSE_CURLY_BRACKET=47;
    public static final int INT_CONST=81;
    public static final int HEX_DIGIT=82;
    public static final int SHORT=41;
    public static final int DECASSIGN=54;
    public static final int MINUS=58;
    public static final int UNEQUAL=51;
    public static final int COLON=75;
    public static final int ENUM=8;
    public static final int QUESTION=61;
    public static final int FINALLY=29;
    public static final int ASSIGN=52;
    public static final int INTERFACE=7;
    public static final int OPEN_RECT_BRACKET=71;
    public static final int LONG=43;
    public static final int PUBLIC=32;
    public static final int EXTENDS=9;
    public static final int BOOL_CONST=79;
    public static final int LE=48;

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

    // $ANTLR start "INTERFACE"
    public final void mINTERFACE() throws RecognitionException {
        try {
            int _type = INTERFACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:14:2: ( 'interface' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:14:4: 'interface'
            {
            match("interface"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INTERFACE"

    // $ANTLR start "ENUM"
    public final void mENUM() throws RecognitionException {
        try {
            int _type = ENUM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:15:6: ( 'enum' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:15:8: 'enum'
            {
            match("enum"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ENUM"

    // $ANTLR start "EXTENDS"
    public final void mEXTENDS() throws RecognitionException {
        try {
            int _type = EXTENDS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:16:9: ( 'extends' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:16:11: 'extends'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:17:12: ( 'implements' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:17:14: 'implements'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:19:6: ( 'void' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:19:8: 'void'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:20:5: ( 'new' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:20:7: 'new'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:21:8: ( 'return' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:21:10: 'return'
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

    // $ANTLR start "BREAK"
    public final void mBREAK() throws RecognitionException {
        try {
            int _type = BREAK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:22:7: ( 'break' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:22:9: 'break'
            {
            match("break"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BREAK"

    // $ANTLR start "CONTINUE"
    public final void mCONTINUE() throws RecognitionException {
        try {
            int _type = CONTINUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:23:9: ( 'continue' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:23:11: 'continue'
            {
            match("continue"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CONTINUE"

    // $ANTLR start "NULL"
    public final void mNULL() throws RecognitionException {
        try {
            int _type = NULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:24:6: ( 'null' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:24:8: 'null'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:25:6: ( 'this' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:25:8: 'this'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:26:7: ( 'super' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:26:9: 'super'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:28:5: ( 'for' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:28:7: 'for'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:29:4: ( 'do' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:29:6: 'do'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:30:7: ( 'while' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:30:9: 'while'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:31:4: ( 'if' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:31:6: 'if'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:32:6: ( 'else' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:32:8: 'else'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:33:8: ( 'switch' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:33:10: 'switch'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:34:6: ( 'case' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:34:8: 'case'
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

    // $ANTLR start "DEFAULT"
    public final void mDEFAULT() throws RecognitionException {
        try {
            int _type = DEFAULT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:35:9: ( 'default' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:35:11: 'default'
            {
            match("default"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DEFAULT"

    // $ANTLR start "TRY"
    public final void mTRY() throws RecognitionException {
        try {
            int _type = TRY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:37:5: ( 'try' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:37:7: 'try'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:38:7: ( 'catch' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:38:9: 'catch'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:39:9: ( 'finally' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:39:11: 'finally'
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

    // $ANTLR start "THROW"
    public final void mTHROW() throws RecognitionException {
        try {
            int _type = THROW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:40:7: ( 'throw' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:40:9: 'throw'
            {
            match("throw"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "THROW"

    // $ANTLR start "THROWS"
    public final void mTHROWS() throws RecognitionException {
        try {
            int _type = THROWS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:41:8: ( 'throws' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:41:10: 'throws'
            {
            match("throws"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "THROWS"

    // $ANTLR start "PUBLIC"
    public final void mPUBLIC() throws RecognitionException {
        try {
            int _type = PUBLIC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:43:8: ( 'public' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:43:10: 'public'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:44:9: ( 'private' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:44:11: 'private'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:46:2: ( 'protected' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:46:4: 'protected'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:47:7: ( 'final' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:47:9: 'final'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:48:8: ( 'static' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:48:10: 'static'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:50:2: ( 'transient' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:50:4: 'transient'
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

    // $ANTLR start "BOOLEAN"
    public final void mBOOLEAN() throws RecognitionException {
        try {
            int _type = BOOLEAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:52:9: ( 'boolean' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:52:11: 'boolean'
            {
            match("boolean"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BOOLEAN"

    // $ANTLR start "BYTE"
    public final void mBYTE() throws RecognitionException {
        try {
            int _type = BYTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:53:6: ( 'byte' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:53:8: 'byte'
            {
            match("byte"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BYTE"

    // $ANTLR start "CHAR"
    public final void mCHAR() throws RecognitionException {
        try {
            int _type = CHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:54:6: ( 'char' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:54:8: 'char'
            {
            match("char"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CHAR"

    // $ANTLR start "SHORT"
    public final void mSHORT() throws RecognitionException {
        try {
            int _type = SHORT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:55:7: ( 'short' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:55:9: 'short'
            {
            match("short"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SHORT"

    // $ANTLR start "INTEGER"
    public final void mINTEGER() throws RecognitionException {
        try {
            int _type = INTEGER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:56:9: ( 'int' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:56:11: 'int'
            {
            match("int"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INTEGER"

    // $ANTLR start "LONG"
    public final void mLONG() throws RecognitionException {
        try {
            int _type = LONG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:57:6: ( 'long' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:57:8: 'long'
            {
            match("long"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LONG"

    // $ANTLR start "FLOAT"
    public final void mFLOAT() throws RecognitionException {
        try {
            int _type = FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:58:7: ( 'float' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:58:9: 'float'
            {
            match("float"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FLOAT"

    // $ANTLR start "DOUBLE"
    public final void mDOUBLE() throws RecognitionException {
        try {
            int _type = DOUBLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:59:8: ( 'double' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:59:10: 'double'
            {
            match("double"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOUBLE"

    // $ANTLR start "OPEN_CURLY_BRACKET"
    public final void mOPEN_CURLY_BRACKET() throws RecognitionException {
        try {
            int _type = OPEN_CURLY_BRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:62:2: ( '{' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:62:4: '{'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:66:2: ( '}' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:66:4: '}'
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

    // $ANTLR start "LE"
    public final void mLE() throws RecognitionException {
        try {
            int _type = LE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:69:4: ( '<=' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:69:6: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LE"

    // $ANTLR start "GE"
    public final void mGE() throws RecognitionException {
        try {
            int _type = GE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:70:4: ( '>=' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:70:6: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GE"

    // $ANTLR start "EQUAL"
    public final void mEQUAL() throws RecognitionException {
        try {
            int _type = EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:71:7: ( '==' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:71:9: '=='
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:72:9: ( '!=' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:72:11: '!='
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:73:8: ( '=' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:73:10: '='
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

    // $ANTLR start "INCASSIGN"
    public final void mINCASSIGN() throws RecognitionException {
        try {
            int _type = INCASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:75:2: ( '+=' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:75:4: '+='
            {
            match("+="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INCASSIGN"

    // $ANTLR start "DECASSIGN"
    public final void mDECASSIGN() throws RecognitionException {
        try {
            int _type = DECASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:77:2: ( '-=' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:77:4: '-='
            {
            match("-="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DECASSIGN"

    // $ANTLR start "INC"
    public final void mINC() throws RecognitionException {
        try {
            int _type = INC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:79:5: ( '++' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:79:7: '++'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:80:5: ( '--' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:80:7: '--'
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

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:82:6: ( '+' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:82:8: '+'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:83:7: ( '-' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:83:9: '-'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:84:7: ( '/' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:84:9: '/'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:85:6: ( '*' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:85:8: '*'
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

    // $ANTLR start "QUESTION"
    public final void mQUESTION() throws RecognitionException {
        try {
            int _type = QUESTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:86:9: ( '?' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:86:11: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QUESTION"

    // $ANTLR start "LOGICAL_OR"
    public final void mLOGICAL_OR() throws RecognitionException {
        try {
            int _type = LOGICAL_OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:89:2: ( '||' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:89:4: '||'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:90:8: ( '|' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:90:10: '|'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:93:2: ( '&&' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:93:4: '&&'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:94:9: ( '&' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:94:11: '&'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:96:5: ( '!' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:96:7: '!'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:98:5: ( '.' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:98:7: '.'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:99:7: ( ',' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:99:9: ','
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:100:4: ( '<' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:100:6: '<'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:101:4: ( '>' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:101:6: '>'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:104:2: ( '[' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:104:4: '['
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:108:2: ( ']' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:108:4: ']'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:112:2: ( '(' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:112:4: '('
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:116:2: ( ')' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:116:4: ')'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:119:7: ( ':' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:119:9: ':'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:121:2: ( ';' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:121:4: ';'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:122:4: ( '@' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:122:6: '@'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:123:7: ( '~' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:123:9: '~'
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

    // $ANTLR start "BOOL_CONST"
    public final void mBOOL_CONST() throws RecognitionException {
        try {
            int _type = BOOL_CONST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:126:2: ( 'true' | 'false' )
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
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:126:4: 'true'
                    {
                    match("true"); 


                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:127:4: 'false'
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
    // $ANTLR end "BOOL_CONST"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:130:4: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:130:6: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:130:30: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
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

    // $ANTLR start "INT_CONST"
    public final void mINT_CONST() throws RecognitionException {
        try {
            int _type = INT_CONST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:134:2: ( ( '0' .. '9' )+ )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:134:4: ( '0' .. '9' )+
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:134:4: ( '0' .. '9' )+
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
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:134:4: '0' .. '9'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:138:2: ( '0x' ( HEX_DIGIT )+ )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:138:4: '0x' ( HEX_DIGIT )+
            {
            match("0x"); 

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:138:9: ( HEX_DIGIT )+
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
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:138:9: HEX_DIGIT
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

    // $ANTLR start "LONG_CONST"
    public final void mLONG_CONST() throws RecognitionException {
        try {
            int _type = LONG_CONST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:142:2: ( INT_CONST 'L' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:142:4: INT_CONST 'L'
            {
            mINT_CONST(); 
            match('L'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LONG_CONST"

    // $ANTLR start "HEX_LONG_CONST"
    public final void mHEX_LONG_CONST() throws RecognitionException {
        try {
            int _type = HEX_LONG_CONST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:146:2: ( HEX_CONST 'L' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:146:4: HEX_CONST 'L'
            {
            mHEX_CONST(); 
            match('L'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "HEX_LONG_CONST"

    // $ANTLR start "FLOAT_CONST"
    public final void mFLOAT_CONST() throws RecognitionException {
        try {
            int _type = FLOAT_CONST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:150:2: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )+ ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT )
            int alt11=3;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:150:6: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )?
                    {
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:150:6: ( '0' .. '9' )+
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
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:150:7: '0' .. '9'
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
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:150:22: ( '0' .. '9' )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0>='0' && LA6_0<='9')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:150:23: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:150:34: ( EXPONENT )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0=='E'||LA7_0=='e') ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:150:34: EXPONENT
                            {
                            mEXPONENT(); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:151:6: '.' ( '0' .. '9' )+ ( EXPONENT )?
                    {
                    match('.'); 
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:151:10: ( '0' .. '9' )+
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
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:151:11: '0' .. '9'
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

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:151:22: ( EXPONENT )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0=='E'||LA9_0=='e') ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:151:22: EXPONENT
                            {
                            mEXPONENT(); 

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:152:6: ( '0' .. '9' )+ EXPONENT
                    {
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:152:6: ( '0' .. '9' )+
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
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:152:7: '0' .. '9'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:156:2: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? LINEFEED | '/*' ( options {greedy=false; } : ( LINEFEED | ~ ( '\\n' ) ) )* '*/' )
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
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:156:6: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? LINEFEED
                    {
                    match("//"); 

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:156:11: (~ ( '\\n' | '\\r' ) )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( ((LA12_0>='\u0000' && LA12_0<='\t')||(LA12_0>='\u000B' && LA12_0<='\f')||(LA12_0>='\u000E' && LA12_0<='\uFFFF')) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:156:11: ~ ( '\\n' | '\\r' )
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

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:156:25: ( '\\r' )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0=='\r') ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:156:25: '\\r'
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
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:157:6: '/*' ( options {greedy=false; } : ( LINEFEED | ~ ( '\\n' ) ) )* '*/'
                    {
                    match("/*"); 

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:157:11: ( options {greedy=false; } : ( LINEFEED | ~ ( '\\n' ) ) )*
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
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:157:39: ( LINEFEED | ~ ( '\\n' ) )
                    	    {
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:157:39: ( LINEFEED | ~ ( '\\n' ) )
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
                    	            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:157:40: LINEFEED
                    	            {
                    	            mLINEFEED(); 

                    	            }
                    	            break;
                    	        case 2 :
                    	            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:157:49: ~ ( '\\n' )
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:160:4: ( ( ' ' | '\\t' | '\\r' | LINEFEED ) )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:160:8: ( ' ' | '\\t' | '\\r' | LINEFEED )
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:168:9: ( '\\n' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:168:11: '\\n'
            {
            match('\n'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "LINEFEED"

    // $ANTLR start "STRING_CONST"
    public final void mSTRING_CONST() throws RecognitionException {
        try {
            int _type = STRING_CONST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:171:2: ( '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:171:5: '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"'
            {
            match('\"'); 
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:171:9: ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )*
            loop17:
            do {
                int alt17=3;
                int LA17_0 = input.LA(1);

                if ( (LA17_0=='\\') ) {
                    alt17=1;
                }
                else if ( ((LA17_0>='\u0000' && LA17_0<='!')||(LA17_0>='#' && LA17_0<='[')||(LA17_0>=']' && LA17_0<='\uFFFF')) ) {
                    alt17=2;
                }


                switch (alt17) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:171:11: ESC_SEQ
            	    {
            	    mESC_SEQ(); 

            	    }
            	    break;
            	case 2 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:171:21: ~ ( '\\\\' | '\"' )
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
            	    break loop17;
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:175:2: ( '\\'' ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) ) '\\'' )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:175:5: '\\'' ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) ) '\\''
            {
            match('\''); 
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:175:10: ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0=='\\') ) {
                alt18=1;
            }
            else if ( ((LA18_0>='\u0000' && LA18_0<='&')||(LA18_0>='(' && LA18_0<='[')||(LA18_0>=']' && LA18_0<='\uFFFF')) ) {
                alt18=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:175:12: ESC_SEQ
                    {
                    mESC_SEQ(); 

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:175:22: ~ ( '\\'' | '\\\\' )
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:179:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:179:12: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:179:22: ( '+' | '-' )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0=='+'||LA19_0=='-') ) {
                alt19=1;
            }
            switch (alt19) {
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

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:179:33: ( '0' .. '9' )+
            int cnt20=0;
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( ((LA20_0>='0' && LA20_0<='9')) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:179:34: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt20 >= 1 ) break loop20;
                        EarlyExitException eee =
                            new EarlyExitException(20, input);
                        throw eee;
                }
                cnt20++;
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:182:11: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:182:13: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:186:5: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | UNICODE_ESC | OCTAL_ESC )
            int alt21=3;
            int LA21_0 = input.LA(1);

            if ( (LA21_0=='\\') ) {
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
                    alt21=1;
                    }
                    break;
                case 'u':
                    {
                    alt21=2;
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
                    alt21=3;
                    }
                    break;
                default:
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
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:186:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
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
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:187:9: UNICODE_ESC
                    {
                    mUNICODE_ESC(); 

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:188:9: OCTAL_ESC
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:193:5: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
            int alt22=3;
            int LA22_0 = input.LA(1);

            if ( (LA22_0=='\\') ) {
                int LA22_1 = input.LA(2);

                if ( ((LA22_1>='0' && LA22_1<='3')) ) {
                    int LA22_2 = input.LA(3);

                    if ( ((LA22_2>='0' && LA22_2<='7')) ) {
                        int LA22_4 = input.LA(4);

                        if ( ((LA22_4>='0' && LA22_4<='7')) ) {
                            alt22=1;
                        }
                        else {
                            alt22=2;}
                    }
                    else {
                        alt22=3;}
                }
                else if ( ((LA22_1>='4' && LA22_1<='7')) ) {
                    int LA22_3 = input.LA(3);

                    if ( ((LA22_3>='0' && LA22_3<='7')) ) {
                        alt22=2;
                    }
                    else {
                        alt22=3;}
                }
                else {
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
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:193:9: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:193:14: ( '0' .. '3' )
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:193:15: '0' .. '3'
                    {
                    matchRange('0','3'); 

                    }

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:193:25: ( '0' .. '7' )
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:193:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:193:36: ( '0' .. '7' )
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:193:37: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:194:9: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:194:14: ( '0' .. '7' )
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:194:15: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:194:25: ( '0' .. '7' )
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:194:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:195:9: '\\\\' ( '0' .. '7' )
                    {
                    match('\\'); 
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:195:14: ( '0' .. '7' )
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:195:15: '0' .. '7'
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
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:200:5: ( '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:200:9: '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
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
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:8: ( PACKAGE | IMPORT | CLASS | INTERFACE | ENUM | EXTENDS | IMPLEMENTS | VOID | NEW | RETURN | BREAK | CONTINUE | NULL | THIS | SUPER | FOR | DO | WHILE | IF | ELSE | SWITCH | CASE | DEFAULT | TRY | CATCH | FINALLY | THROW | THROWS | PUBLIC | PRIVATE | PROTECTED | FINAL | STATIC | TRANSIENT | BOOLEAN | BYTE | CHAR | SHORT | INTEGER | LONG | FLOAT | DOUBLE | OPEN_CURLY_BRACKET | CLOSE_CURLY_BRACKET | LE | GE | EQUAL | UNEQUAL | ASSIGN | INCASSIGN | DECASSIGN | INC | DEC | PLUS | MINUS | SLASH | STAR | QUESTION | LOGICAL_OR | BIT_OR | LOGICAL_AND | BIT_AND | NOT | DOT | COMMA | LT | GT | OPEN_RECT_BRACKET | CLOSE_RECT_BRACKET | OPEN_BRACKET | CLOSE_BRACKET | COLON | SEMICOLON | AT | TILDE | BOOL_CONST | ID | INT_CONST | HEX_CONST | LONG_CONST | HEX_LONG_CONST | FLOAT_CONST | COMMENT | WS | STRING_CONST | CHAR_CONST )
        int alt23=86;
        alt23 = dfa23.predict(input);
        switch (alt23) {
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
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:31: INTERFACE
                {
                mINTERFACE(); 

                }
                break;
            case 5 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:41: ENUM
                {
                mENUM(); 

                }
                break;
            case 6 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:46: EXTENDS
                {
                mEXTENDS(); 

                }
                break;
            case 7 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:54: IMPLEMENTS
                {
                mIMPLEMENTS(); 

                }
                break;
            case 8 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:65: VOID
                {
                mVOID(); 

                }
                break;
            case 9 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:70: NEW
                {
                mNEW(); 

                }
                break;
            case 10 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:74: RETURN
                {
                mRETURN(); 

                }
                break;
            case 11 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:81: BREAK
                {
                mBREAK(); 

                }
                break;
            case 12 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:87: CONTINUE
                {
                mCONTINUE(); 

                }
                break;
            case 13 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:96: NULL
                {
                mNULL(); 

                }
                break;
            case 14 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:101: THIS
                {
                mTHIS(); 

                }
                break;
            case 15 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:106: SUPER
                {
                mSUPER(); 

                }
                break;
            case 16 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:112: FOR
                {
                mFOR(); 

                }
                break;
            case 17 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:116: DO
                {
                mDO(); 

                }
                break;
            case 18 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:119: WHILE
                {
                mWHILE(); 

                }
                break;
            case 19 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:125: IF
                {
                mIF(); 

                }
                break;
            case 20 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:128: ELSE
                {
                mELSE(); 

                }
                break;
            case 21 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:133: SWITCH
                {
                mSWITCH(); 

                }
                break;
            case 22 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:140: CASE
                {
                mCASE(); 

                }
                break;
            case 23 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:145: DEFAULT
                {
                mDEFAULT(); 

                }
                break;
            case 24 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:153: TRY
                {
                mTRY(); 

                }
                break;
            case 25 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:157: CATCH
                {
                mCATCH(); 

                }
                break;
            case 26 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:163: FINALLY
                {
                mFINALLY(); 

                }
                break;
            case 27 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:171: THROW
                {
                mTHROW(); 

                }
                break;
            case 28 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:177: THROWS
                {
                mTHROWS(); 

                }
                break;
            case 29 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:184: PUBLIC
                {
                mPUBLIC(); 

                }
                break;
            case 30 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:191: PRIVATE
                {
                mPRIVATE(); 

                }
                break;
            case 31 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:199: PROTECTED
                {
                mPROTECTED(); 

                }
                break;
            case 32 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:209: FINAL
                {
                mFINAL(); 

                }
                break;
            case 33 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:215: STATIC
                {
                mSTATIC(); 

                }
                break;
            case 34 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:222: TRANSIENT
                {
                mTRANSIENT(); 

                }
                break;
            case 35 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:232: BOOLEAN
                {
                mBOOLEAN(); 

                }
                break;
            case 36 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:240: BYTE
                {
                mBYTE(); 

                }
                break;
            case 37 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:245: CHAR
                {
                mCHAR(); 

                }
                break;
            case 38 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:250: SHORT
                {
                mSHORT(); 

                }
                break;
            case 39 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:256: INTEGER
                {
                mINTEGER(); 

                }
                break;
            case 40 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:264: LONG
                {
                mLONG(); 

                }
                break;
            case 41 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:269: FLOAT
                {
                mFLOAT(); 

                }
                break;
            case 42 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:275: DOUBLE
                {
                mDOUBLE(); 

                }
                break;
            case 43 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:282: OPEN_CURLY_BRACKET
                {
                mOPEN_CURLY_BRACKET(); 

                }
                break;
            case 44 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:301: CLOSE_CURLY_BRACKET
                {
                mCLOSE_CURLY_BRACKET(); 

                }
                break;
            case 45 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:321: LE
                {
                mLE(); 

                }
                break;
            case 46 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:324: GE
                {
                mGE(); 

                }
                break;
            case 47 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:327: EQUAL
                {
                mEQUAL(); 

                }
                break;
            case 48 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:333: UNEQUAL
                {
                mUNEQUAL(); 

                }
                break;
            case 49 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:341: ASSIGN
                {
                mASSIGN(); 

                }
                break;
            case 50 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:348: INCASSIGN
                {
                mINCASSIGN(); 

                }
                break;
            case 51 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:358: DECASSIGN
                {
                mDECASSIGN(); 

                }
                break;
            case 52 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:368: INC
                {
                mINC(); 

                }
                break;
            case 53 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:372: DEC
                {
                mDEC(); 

                }
                break;
            case 54 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:376: PLUS
                {
                mPLUS(); 

                }
                break;
            case 55 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:381: MINUS
                {
                mMINUS(); 

                }
                break;
            case 56 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:387: SLASH
                {
                mSLASH(); 

                }
                break;
            case 57 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:393: STAR
                {
                mSTAR(); 

                }
                break;
            case 58 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:398: QUESTION
                {
                mQUESTION(); 

                }
                break;
            case 59 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:407: LOGICAL_OR
                {
                mLOGICAL_OR(); 

                }
                break;
            case 60 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:418: BIT_OR
                {
                mBIT_OR(); 

                }
                break;
            case 61 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:425: LOGICAL_AND
                {
                mLOGICAL_AND(); 

                }
                break;
            case 62 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:437: BIT_AND
                {
                mBIT_AND(); 

                }
                break;
            case 63 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:445: NOT
                {
                mNOT(); 

                }
                break;
            case 64 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:449: DOT
                {
                mDOT(); 

                }
                break;
            case 65 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:453: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 66 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:459: LT
                {
                mLT(); 

                }
                break;
            case 67 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:462: GT
                {
                mGT(); 

                }
                break;
            case 68 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:465: OPEN_RECT_BRACKET
                {
                mOPEN_RECT_BRACKET(); 

                }
                break;
            case 69 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:483: CLOSE_RECT_BRACKET
                {
                mCLOSE_RECT_BRACKET(); 

                }
                break;
            case 70 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:502: OPEN_BRACKET
                {
                mOPEN_BRACKET(); 

                }
                break;
            case 71 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:515: CLOSE_BRACKET
                {
                mCLOSE_BRACKET(); 

                }
                break;
            case 72 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:529: COLON
                {
                mCOLON(); 

                }
                break;
            case 73 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:535: SEMICOLON
                {
                mSEMICOLON(); 

                }
                break;
            case 74 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:545: AT
                {
                mAT(); 

                }
                break;
            case 75 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:548: TILDE
                {
                mTILDE(); 

                }
                break;
            case 76 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:554: BOOL_CONST
                {
                mBOOL_CONST(); 

                }
                break;
            case 77 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:565: ID
                {
                mID(); 

                }
                break;
            case 78 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:568: INT_CONST
                {
                mINT_CONST(); 

                }
                break;
            case 79 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:578: HEX_CONST
                {
                mHEX_CONST(); 

                }
                break;
            case 80 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:588: LONG_CONST
                {
                mLONG_CONST(); 

                }
                break;
            case 81 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:599: HEX_LONG_CONST
                {
                mHEX_LONG_CONST(); 

                }
                break;
            case 82 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:614: FLOAT_CONST
                {
                mFLOAT_CONST(); 

                }
                break;
            case 83 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:626: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 84 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:634: WS
                {
                mWS(); 

                }
                break;
            case 85 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:637: STRING_CONST
                {
                mSTRING_CONST(); 

                }
                break;
            case 86 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaLexer.g:1:650: CHAR_CONST
                {
                mCHAR_CONST(); 

                }
                break;

        }

    }


    protected DFA11 dfa11 = new DFA11(this);
    protected DFA23 dfa23 = new DFA23(this);
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
            return "149:1: FLOAT_CONST : ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )+ ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT );";
        }
    }
    static final String DFA23_eotS =
        "\1\uffff\16\46\2\uffff\1\117\1\121\1\123\1\125\1\130\1\133\1\135"+
        "\2\uffff\1\137\1\141\1\143\12\uffff\2\145\3\uffff\5\46\1\155\30"+
        "\46\1\u008b\3\46\31\uffff\5\46\1\u0097\1\uffff\11\46\1\u00a1\7\46"+
        "\1\u00a9\6\46\1\u00b0\4\46\1\uffff\3\46\1\u00b8\7\46\1\uffff\2\46"+
        "\1\u00c3\1\46\1\u00c5\1\u00c6\1\46\1\u00c8\1\u00c9\1\uffff\1\u00ca"+
        "\3\46\1\u00ce\1\u00cf\1\46\1\uffff\1\46\1\u00d2\4\46\1\uffff\6\46"+
        "\1\u00dd\2\uffff\7\46\1\u00e5\1\46\1\uffff\1\u00e7\2\uffff\1\46"+
        "\3\uffff\1\46\1\u00ea\1\46\2\uffff\1\u00ed\1\46\1\uffff\1\u00ef"+
        "\2\46\1\u00f2\1\u00f4\1\u00f5\1\u00d2\2\46\1\u00f8\1\uffff\1\46"+
        "\1\u00fa\2\46\1\u00fd\2\46\1\uffff\1\46\1\uffff\1\46\1\u0102\1\uffff"+
        "\1\46\1\u0104\1\uffff\1\46\1\uffff\1\u0106\1\u0107\1\uffff\1\46"+
        "\2\uffff\1\u0109\1\46\1\uffff\1\u010b\1\uffff\1\u010c\1\46\1\uffff"+
        "\3\46\1\u0111\1\uffff\1\u0112\1\uffff\1\46\2\uffff\1\u0114\1\uffff"+
        "\1\u0115\2\uffff\3\46\1\u0119\2\uffff\1\46\2\uffff\1\u011b\1\46"+
        "\1\u011d\1\uffff\1\u011e\1\uffff\1\u011f\3\uffff";
    static final String DFA23_eofS =
        "\u0120\uffff";
    static final String DFA23_minS =
        "\1\11\1\141\1\146\1\141\1\154\1\157\2\145\1\157\2\150\1\141\1\145"+
        "\1\150\1\157\2\uffff\4\75\1\53\1\55\1\52\2\uffff\1\174\1\46\1\60"+
        "\12\uffff\2\56\3\uffff\1\143\1\142\1\151\1\160\1\164\1\60\1\141"+
        "\1\156\1\163\1\141\1\165\1\164\1\163\1\151\1\167\1\154\1\164\1\145"+
        "\1\157\1\164\1\151\1\141\1\160\1\151\1\141\1\157\1\162\1\156\1\157"+
        "\1\154\1\60\1\146\1\151\1\156\26\uffff\1\60\2\uffff\1\153\1\154"+
        "\1\166\1\164\1\154\1\60\1\uffff\1\163\1\164\1\145\1\143\1\162\1"+
        "\155\2\145\1\144\1\60\1\154\1\165\1\141\1\154\1\145\1\163\1\157"+
        "\1\60\1\156\2\145\2\164\1\162\1\60\2\141\1\163\1\142\1\uffff\1\141"+
        "\1\154\1\147\1\60\1\141\1\151\1\141\1\145\1\162\1\145\1\162\1\uffff"+
        "\1\163\1\151\1\60\1\150\2\60\1\156\2\60\1\uffff\1\60\1\162\1\153"+
        "\1\145\2\60\1\167\1\uffff\1\163\1\60\1\162\1\143\1\151\1\164\1\uffff"+
        "\1\154\1\164\1\145\1\154\1\165\1\145\1\60\2\uffff\1\147\1\143\1"+
        "\164\1\143\1\164\1\155\1\146\1\60\1\156\1\uffff\1\60\2\uffff\1\144"+
        "\3\uffff\1\156\1\60\1\141\2\uffff\1\60\1\151\1\uffff\1\60\1\150"+
        "\1\143\4\60\1\145\1\154\1\60\1\uffff\1\145\1\60\1\145\1\164\1\60"+
        "\1\145\1\141\1\uffff\1\165\1\uffff\1\163\1\60\1\uffff\1\156\1\60"+
        "\1\uffff\1\145\1\uffff\2\60\1\uffff\1\171\2\uffff\1\60\1\164\1\uffff"+
        "\1\60\1\uffff\1\60\1\145\1\uffff\1\156\1\143\1\145\1\60\1\uffff"+
        "\1\60\1\uffff\1\156\2\uffff\1\60\1\uffff\1\60\2\uffff\1\144\1\164"+
        "\1\145\1\60\2\uffff\1\164\2\uffff\1\60\1\163\1\60\1\uffff\1\60\1"+
        "\uffff\1\60\3\uffff";
    static final String DFA23_maxS =
        "\1\176\1\165\1\156\1\157\1\170\1\157\1\165\1\145\1\171\1\162\1\167"+
        "\2\157\1\150\1\157\2\uffff\6\75\1\57\2\uffff\1\174\1\46\1\71\12"+
        "\uffff\1\170\1\145\3\uffff\1\143\1\142\1\157\1\160\1\164\1\172\1"+
        "\141\1\156\1\164\1\141\1\165\1\164\1\163\1\151\1\167\1\154\1\164"+
        "\1\145\1\157\1\164\1\162\1\171\1\160\1\151\1\141\1\157\1\162\1\156"+
        "\1\157\1\154\1\172\1\146\1\151\1\156\26\uffff\1\146\2\uffff\1\153"+
        "\1\154\1\166\1\164\1\157\1\172\1\uffff\1\163\1\164\1\145\1\143\1"+
        "\162\1\155\2\145\1\144\1\172\1\154\1\165\1\141\1\154\1\145\1\163"+
        "\1\157\1\172\1\156\2\145\2\164\1\162\1\172\2\141\1\163\1\142\1\uffff"+
        "\1\141\1\154\1\147\1\146\1\141\1\151\1\141\1\145\1\162\1\145\1\162"+
        "\1\uffff\1\163\1\151\1\172\1\150\2\172\1\156\2\172\1\uffff\1\172"+
        "\1\162\1\153\1\145\2\172\1\167\1\uffff\1\163\1\172\1\162\1\143\1"+
        "\151\1\164\1\uffff\1\154\1\164\1\145\1\154\1\165\1\145\1\172\2\uffff"+
        "\1\147\1\143\1\164\1\143\1\164\1\155\1\146\1\172\1\156\1\uffff\1"+
        "\172\2\uffff\1\144\3\uffff\1\156\1\172\1\141\2\uffff\1\172\1\151"+
        "\1\uffff\1\172\1\150\1\143\4\172\1\145\1\154\1\172\1\uffff\1\145"+
        "\1\172\1\145\1\164\1\172\1\145\1\141\1\uffff\1\165\1\uffff\1\163"+
        "\1\172\1\uffff\1\156\1\172\1\uffff\1\145\1\uffff\2\172\1\uffff\1"+
        "\171\2\uffff\1\172\1\164\1\uffff\1\172\1\uffff\1\172\1\145\1\uffff"+
        "\1\156\1\143\1\145\1\172\1\uffff\1\172\1\uffff\1\156\2\uffff\1\172"+
        "\1\uffff\1\172\2\uffff\1\144\1\164\1\145\1\172\2\uffff\1\164\2\uffff"+
        "\1\172\1\163\1\172\1\uffff\1\172\1\uffff\1\172\3\uffff";
    static final String DFA23_acceptS =
        "\17\uffff\1\53\1\54\7\uffff\1\71\1\72\3\uffff\1\101\1\104\1\105"+
        "\1\106\1\107\1\110\1\111\1\112\1\113\1\115\2\uffff\1\124\1\125\1"+
        "\126\42\uffff\1\55\1\102\1\56\1\103\1\57\1\61\1\60\1\77\1\62\1\64"+
        "\1\66\1\63\1\65\1\67\1\123\1\70\1\73\1\74\1\75\1\76\1\122\1\100"+
        "\1\uffff\1\116\1\120\6\uffff\1\23\35\uffff\1\21\13\uffff\1\47\11"+
        "\uffff\1\11\7\uffff\1\30\6\uffff\1\20\7\uffff\1\117\1\121\11\uffff"+
        "\1\26\1\uffff\1\45\1\5\1\uffff\1\24\1\10\1\15\3\uffff\1\44\1\16"+
        "\2\uffff\1\114\12\uffff\1\50\7\uffff\1\3\1\uffff\1\31\2\uffff\1"+
        "\13\2\uffff\1\33\1\uffff\1\17\2\uffff\1\46\1\uffff\1\40\1\51\2\uffff"+
        "\1\22\1\uffff\1\35\2\uffff\1\2\4\uffff\1\12\1\uffff\1\34\1\uffff"+
        "\1\25\1\41\1\uffff\1\52\1\uffff\1\1\1\36\4\uffff\1\6\1\43\1\uffff"+
        "\1\32\1\27\3\uffff\1\14\1\uffff\1\37\1\uffff\1\4\1\42\1\7";
    static final String DFA23_specialS =
        "\u0120\uffff}>";
    static final String[] DFA23_transitionS = {
            "\2\51\2\uffff\1\51\22\uffff\1\51\1\24\1\52\3\uffff\1\33\1\53"+
            "\1\40\1\41\1\30\1\25\1\35\1\26\1\34\1\27\1\47\11\50\1\42\1\43"+
            "\1\21\1\23\1\22\1\31\1\44\32\46\1\36\1\uffff\1\37\1\uffff\1"+
            "\46\1\uffff\1\46\1\10\1\3\1\14\1\4\1\13\2\46\1\2\2\46\1\16\1"+
            "\46\1\6\1\46\1\1\1\46\1\7\1\12\1\11\1\46\1\5\1\15\3\46\1\17"+
            "\1\32\1\20\1\45",
            "\1\54\20\uffff\1\56\2\uffff\1\55",
            "\1\61\6\uffff\1\57\1\60",
            "\1\64\6\uffff\1\65\3\uffff\1\62\2\uffff\1\63",
            "\1\70\1\uffff\1\66\11\uffff\1\67",
            "\1\71",
            "\1\72\17\uffff\1\73",
            "\1\74",
            "\1\76\2\uffff\1\75\6\uffff\1\77",
            "\1\100\11\uffff\1\101",
            "\1\105\13\uffff\1\104\1\102\1\uffff\1\103",
            "\1\111\7\uffff\1\107\2\uffff\1\110\2\uffff\1\106",
            "\1\113\11\uffff\1\112",
            "\1\114",
            "\1\115",
            "",
            "",
            "\1\116",
            "\1\120",
            "\1\122",
            "\1\124",
            "\1\127\21\uffff\1\126",
            "\1\132\17\uffff\1\131",
            "\1\134\4\uffff\1\134",
            "",
            "",
            "\1\136",
            "\1\140",
            "\12\142",
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
            "\1\142\1\uffff\12\50\13\uffff\1\142\6\uffff\1\146\30\uffff"+
            "\1\142\22\uffff\1\144",
            "\1\142\1\uffff\12\50\13\uffff\1\142\6\uffff\1\146\30\uffff"+
            "\1\142",
            "",
            "",
            "",
            "\1\147",
            "\1\150",
            "\1\151\5\uffff\1\152",
            "\1\153",
            "\1\154",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\156",
            "\1\157",
            "\1\160\1\161",
            "\1\162",
            "\1\163",
            "\1\164",
            "\1\165",
            "\1\166",
            "\1\167",
            "\1\170",
            "\1\171",
            "\1\172",
            "\1\173",
            "\1\174",
            "\1\175\10\uffff\1\176",
            "\1\u0080\23\uffff\1\u0081\3\uffff\1\177",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088",
            "\1\u0089",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\24\46\1\u008a\5\46",
            "\1\u008c",
            "\1\u008d",
            "\1\u008e",
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
            "",
            "",
            "",
            "",
            "",
            "\12\u008f\7\uffff\6\u008f\32\uffff\6\u008f",
            "",
            "",
            "\1\u0090",
            "\1\u0091",
            "\1\u0092",
            "\1\u0093",
            "\1\u0095\2\uffff\1\u0094",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\4\46\1\u0096\25\46",
            "",
            "\1\u0098",
            "\1\u0099",
            "\1\u009a",
            "\1\u009b",
            "\1\u009c",
            "\1\u009d",
            "\1\u009e",
            "\1\u009f",
            "\1\u00a0",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\u00a2",
            "\1\u00a3",
            "\1\u00a4",
            "\1\u00a5",
            "\1\u00a6",
            "\1\u00a7",
            "\1\u00a8",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\u00aa",
            "\1\u00ab",
            "\1\u00ac",
            "\1\u00ad",
            "\1\u00ae",
            "\1\u00af",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\u00b1",
            "\1\u00b2",
            "\1\u00b3",
            "\1\u00b4",
            "",
            "\1\u00b5",
            "\1\u00b6",
            "\1\u00b7",
            "\12\u008f\7\uffff\6\u008f\5\uffff\1\u00b9\24\uffff\6\u008f",
            "\1\u00ba",
            "\1\u00bb",
            "\1\u00bc",
            "\1\u00bd",
            "\1\u00be",
            "\1\u00bf",
            "\1\u00c0",
            "",
            "\1\u00c1",
            "\1\u00c2",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\u00c4",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\u00c7",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\u00cb",
            "\1\u00cc",
            "\1\u00cd",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\u00d0",
            "",
            "\1\u00d1",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\u00d3",
            "\1\u00d4",
            "\1\u00d5",
            "\1\u00d6",
            "",
            "\1\u00d7",
            "\1\u00d8",
            "\1\u00d9",
            "\1\u00da",
            "\1\u00db",
            "\1\u00dc",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "",
            "",
            "\1\u00de",
            "\1\u00df",
            "\1\u00e0",
            "\1\u00e1",
            "\1\u00e2",
            "\1\u00e3",
            "\1\u00e4",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\u00e6",
            "",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "",
            "",
            "\1\u00e8",
            "",
            "",
            "",
            "\1\u00e9",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\u00eb",
            "",
            "",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\22\46\1\u00ec\7\46",
            "\1\u00ee",
            "",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\u00f0",
            "\1\u00f1",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\13\46\1\u00f3\16"+
            "\46",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\u00f6",
            "\1\u00f7",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "",
            "\1\u00f9",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\u00fb",
            "\1\u00fc",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\u00fe",
            "\1\u00ff",
            "",
            "\1\u0100",
            "",
            "\1\u0101",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "",
            "\1\u0103",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "",
            "\1\u0105",
            "",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "",
            "\1\u0108",
            "",
            "",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\u010a",
            "",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\u010d",
            "",
            "\1\u010e",
            "\1\u010f",
            "\1\u0110",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "",
            "\1\u0113",
            "",
            "",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "",
            "",
            "\1\u0116",
            "\1\u0117",
            "\1\u0118",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "",
            "",
            "\1\u011a",
            "",
            "",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\u011c",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "",
            "\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "",
            "",
            ""
    };

    static final short[] DFA23_eot = DFA.unpackEncodedString(DFA23_eotS);
    static final short[] DFA23_eof = DFA.unpackEncodedString(DFA23_eofS);
    static final char[] DFA23_min = DFA.unpackEncodedStringToUnsignedChars(DFA23_minS);
    static final char[] DFA23_max = DFA.unpackEncodedStringToUnsignedChars(DFA23_maxS);
    static final short[] DFA23_accept = DFA.unpackEncodedString(DFA23_acceptS);
    static final short[] DFA23_special = DFA.unpackEncodedString(DFA23_specialS);
    static final short[][] DFA23_transition;

    static {
        int numStates = DFA23_transitionS.length;
        DFA23_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA23_transition[i] = DFA.unpackEncodedString(DFA23_transitionS[i]);
        }
    }

    class DFA23 extends DFA {

        public DFA23(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 23;
            this.eot = DFA23_eot;
            this.eof = DFA23_eof;
            this.min = DFA23_min;
            this.max = DFA23_max;
            this.accept = DFA23_accept;
            this.special = DFA23_special;
            this.transition = DFA23_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( PACKAGE | IMPORT | CLASS | INTERFACE | ENUM | EXTENDS | IMPLEMENTS | VOID | NEW | RETURN | BREAK | CONTINUE | NULL | THIS | SUPER | FOR | DO | WHILE | IF | ELSE | SWITCH | CASE | DEFAULT | TRY | CATCH | FINALLY | THROW | THROWS | PUBLIC | PRIVATE | PROTECTED | FINAL | STATIC | TRANSIENT | BOOLEAN | BYTE | CHAR | SHORT | INTEGER | LONG | FLOAT | DOUBLE | OPEN_CURLY_BRACKET | CLOSE_CURLY_BRACKET | LE | GE | EQUAL | UNEQUAL | ASSIGN | INCASSIGN | DECASSIGN | INC | DEC | PLUS | MINUS | SLASH | STAR | QUESTION | LOGICAL_OR | BIT_OR | LOGICAL_AND | BIT_AND | NOT | DOT | COMMA | LT | GT | OPEN_RECT_BRACKET | CLOSE_RECT_BRACKET | OPEN_BRACKET | CLOSE_BRACKET | COLON | SEMICOLON | AT | TILDE | BOOL_CONST | ID | INT_CONST | HEX_CONST | LONG_CONST | HEX_LONG_CONST | FLOAT_CONST | COMMENT | WS | STRING_CONST | CHAR_CONST );";
        }
    }
 

}