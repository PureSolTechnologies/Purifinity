// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g 2009-12-13 20:51:51

package com.puresol.coding.java.antlr.output;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class JavaLexer extends Lexer {
    public static final int PACKAGE=4;
    public static final int EXPONENT=92;
    public static final int LT=75;
    public static final int STAR=65;
    public static final int WHILE=21;
    public static final int OCTAL_ESC=101;
    public static final int CASE=25;
    public static final int CHAR=43;
    public static final int NEW=12;
    public static final int DO=20;
    public static final int NOT=72;
    public static final int EOF=-1;
    public static final int BREAK=14;
    public static final int LOGICAL_AND=70;
    public static final int HEX_LONG_CONST=91;
    public static final int OPEN_BRACKET=79;
    public static final int FINAL=35;
    public static final int INC=60;
    public static final int IMPORT=5;
    public static final int BIT_OR=69;
    public static final int HEX_CONST=89;
    public static final int STRING_CONST=98;
    public static final int RETURN=13;
    public static final int THIS=17;
    public static final int DOUBLE=48;
    public static final int VOID=11;
    public static final int SUPER=18;
    public static final int COMMENT=95;
    public static final int GE=52;
    public static final int STATIC=38;
    public static final int PRIVATE=33;
    public static final int SWITCH=24;
    public static final int NULL=16;
    public static final int ELSE=23;
    public static final int THROWS=31;
    public static final int SEMICOLON=82;
    public static final int TRY=27;
    public static final int WS=96;
    public static final int GT=76;
    public static final int CATCH=28;
    public static final int THROW=30;
    public static final int CLOSE_BRACKET=80;
    public static final int PROTECTED=34;
    public static final int DEC=61;
    public static final int CLASS=6;
    public static final int INCASSIGN=56;
    public static final int BIT_AND=71;
    public static final int FOR=19;
    public static final int FLOAT=47;
    public static final int ABSTRACT=40;
    public static final int LONG_CONST=90;
    public static final int ID=86;
    public static final int CLOSE_RECT_BRACKET=78;
    public static final int FLOAT_CONST=93;
    public static final int CHAR_CONST=99;
    public static final int LINEFEED=94;
    public static final int IF=22;
    public static final int AT=83;
    public static final int ESC_SEQ=97;
    public static final int BOOLEAN=41;
    public static final int SYNCHRONIZED=37;
    public static final int SLASH=64;
    public static final int IMPLEMENTS=10;
    public static final int CONTINUE=15;
    public static final int COMMA=74;
    public static final int TRANSIENT=39;
    public static final int EQUAL=53;
    public static final int BITORASSIGN=58;
    public static final int LOGICAL_OR=68;
    public static final int TILDE=84;
    public static final int BITANDASSIGN=59;
    public static final int PLUS=62;
    public static final int DOT=73;
    public static final int INTEGER=45;
    public static final int BYTE=42;
    public static final int OPEN_CURLY_BRACKET=49;
    public static final int PERCENT=67;
    public static final int VOLATILE=36;
    public static final int UNICODE_ESC=100;
    public static final int DEFAULT=26;
    public static final int CLOSE_CURLY_BRACKET=50;
    public static final int INT_CONST=87;
    public static final int HEX_DIGIT=88;
    public static final int SHORT=44;
    public static final int DECASSIGN=57;
    public static final int MINUS=63;
    public static final int UNEQUAL=54;
    public static final int COLON=81;
    public static final int ENUM=8;
    public static final int QUESTION=66;
    public static final int FINALLY=29;
    public static final int ASSIGN=55;
    public static final int INTERFACE=7;
    public static final int OPEN_RECT_BRACKET=77;
    public static final int LONG=46;
    public static final int PUBLIC=32;
    public static final int EXTENDS=9;
    public static final int BOOL_CONST=85;
    public static final int LE=51;

    // delegates
    // delegators

    public JavaLexer() {;} 
    public JavaLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public JavaLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g"; }

    // $ANTLR start "PACKAGE"
    public final void mPACKAGE() throws RecognitionException {
        try {
            int _type = PACKAGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:9:9: ( 'package' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:9:11: 'package'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:10:8: ( 'import' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:10:10: 'import'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:12:7: ( 'class' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:12:9: 'class'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:14:2: ( 'interface' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:14:4: 'interface'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:15:6: ( 'enum' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:15:8: 'enum'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:16:9: ( 'extends' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:16:11: 'extends'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:18:2: ( 'implements' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:18:4: 'implements'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:20:6: ( 'void' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:20:8: 'void'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:21:5: ( 'new' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:21:7: 'new'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:22:8: ( 'return' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:22:10: 'return'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:23:7: ( 'break' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:23:9: 'break'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:24:9: ( 'continue' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:24:11: 'continue'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:25:6: ( 'null' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:25:8: 'null'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:26:6: ( 'this' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:26:8: 'this'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:27:7: ( 'super' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:27:9: 'super'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:29:5: ( 'for' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:29:7: 'for'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:30:4: ( 'do' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:30:6: 'do'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:31:7: ( 'while' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:31:9: 'while'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:32:4: ( 'if' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:32:6: 'if'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:33:6: ( 'else' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:33:8: 'else'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:34:8: ( 'switch' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:34:10: 'switch'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:35:6: ( 'case' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:35:8: 'case'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:36:9: ( 'default' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:36:11: 'default'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:38:5: ( 'try' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:38:7: 'try'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:39:7: ( 'catch' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:39:9: 'catch'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:40:9: ( 'finally' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:40:11: 'finally'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:41:7: ( 'throw' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:41:9: 'throw'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:42:8: ( 'throws' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:42:10: 'throws'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:44:8: ( 'public' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:44:10: 'public'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:45:9: ( 'private' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:45:11: 'private'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:47:2: ( 'protected' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:47:4: 'protected'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:48:7: ( 'final' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:48:9: 'final'
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

    // $ANTLR start "VOLATILE"
    public final void mVOLATILE() throws RecognitionException {
        try {
            int _type = VOLATILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:49:9: ( 'volatile' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:49:11: 'volatile'
            {
            match("volatile"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VOLATILE"

    // $ANTLR start "SYNCHRONIZED"
    public final void mSYNCHRONIZED() throws RecognitionException {
        try {
            int _type = SYNCHRONIZED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:51:2: ( 'synchronized' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:51:4: 'synchronized'
            {
            match("synchronized"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SYNCHRONIZED"

    // $ANTLR start "STATIC"
    public final void mSTATIC() throws RecognitionException {
        try {
            int _type = STATIC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:52:8: ( 'static' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:52:10: 'static'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:54:2: ( 'transient' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:54:4: 'transient'
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

    // $ANTLR start "ABSTRACT"
    public final void mABSTRACT() throws RecognitionException {
        try {
            int _type = ABSTRACT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:55:9: ( 'abstract' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:55:11: 'abstract'
            {
            match("abstract"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ABSTRACT"

    // $ANTLR start "BOOLEAN"
    public final void mBOOLEAN() throws RecognitionException {
        try {
            int _type = BOOLEAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:57:9: ( 'boolean' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:57:11: 'boolean'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:58:6: ( 'byte' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:58:8: 'byte'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:59:6: ( 'char' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:59:8: 'char'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:60:7: ( 'short' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:60:9: 'short'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:61:9: ( 'int' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:61:11: 'int'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:62:6: ( 'long' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:62:8: 'long'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:63:7: ( 'float' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:63:9: 'float'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:64:8: ( 'double' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:64:10: 'double'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:67:2: ( '{' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:67:4: '{'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:71:2: ( '}' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:71:4: '}'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:74:4: ( '<=' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:74:6: '<='
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:75:4: ( '>=' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:75:6: '>='
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:76:7: ( '==' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:76:9: '=='
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:77:9: ( '!=' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:77:11: '!='
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:78:8: ( '=' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:78:10: '='
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:80:2: ( '+=' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:80:4: '+='
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:82:2: ( '-=' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:82:4: '-='
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

    // $ANTLR start "BITORASSIGN"
    public final void mBITORASSIGN() throws RecognitionException {
        try {
            int _type = BITORASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:84:2: ( '|=' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:84:4: '|='
            {
            match("|="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BITORASSIGN"

    // $ANTLR start "BITANDASSIGN"
    public final void mBITANDASSIGN() throws RecognitionException {
        try {
            int _type = BITANDASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:86:2: ( '&=' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:86:4: '&='
            {
            match("&="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BITANDASSIGN"

    // $ANTLR start "INC"
    public final void mINC() throws RecognitionException {
        try {
            int _type = INC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:88:5: ( '++' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:88:7: '++'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:89:5: ( '--' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:89:7: '--'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:91:6: ( '+' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:91:8: '+'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:92:7: ( '-' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:92:9: '-'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:93:7: ( '/' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:93:9: '/'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:94:6: ( '*' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:94:8: '*'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:95:9: ( '?' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:95:11: '?'
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

    // $ANTLR start "PERCENT"
    public final void mPERCENT() throws RecognitionException {
        try {
            int _type = PERCENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:96:9: ( '%' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:96:11: '%'
            {
            match('%'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PERCENT"

    // $ANTLR start "LOGICAL_OR"
    public final void mLOGICAL_OR() throws RecognitionException {
        try {
            int _type = LOGICAL_OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:99:2: ( '||' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:99:4: '||'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:100:8: ( '|' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:100:10: '|'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:103:2: ( '&&' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:103:4: '&&'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:104:9: ( '&' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:104:11: '&'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:106:5: ( '!' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:106:7: '!'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:108:5: ( '.' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:108:7: '.'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:109:7: ( ',' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:109:9: ','
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:110:4: ( '<' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:110:6: '<'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:111:4: ( '>' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:111:6: '>'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:114:2: ( '[' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:114:4: '['
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:118:2: ( ']' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:118:4: ']'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:122:2: ( '(' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:122:4: '('
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:126:2: ( ')' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:126:4: ')'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:129:7: ( ':' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:129:9: ':'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:131:2: ( ';' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:131:4: ';'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:132:4: ( '@' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:132:6: '@'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:133:7: ( '~' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:133:9: '~'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:136:2: ( 'true' | 'false' )
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
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:136:4: 'true'
                    {
                    match("true"); 


                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:137:4: 'false'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:140:4: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:140:6: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:140:30: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')||(LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:144:2: ( ( '0' .. '9' )+ )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:144:4: ( '0' .. '9' )+
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:144:4: ( '0' .. '9' )+
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
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:144:4: '0' .. '9'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:148:2: ( '0x' ( HEX_DIGIT )+ )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:148:4: '0x' ( HEX_DIGIT )+
            {
            match("0x"); 

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:148:9: ( HEX_DIGIT )+
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
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:148:9: HEX_DIGIT
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:152:2: ( INT_CONST 'L' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:152:4: INT_CONST 'L'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:156:2: ( HEX_CONST 'L' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:156:4: HEX_CONST 'L'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:160:2: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )+ ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT )
            int alt11=3;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:160:6: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )?
                    {
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:160:6: ( '0' .. '9' )+
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
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:160:7: '0' .. '9'
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
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:160:22: ( '0' .. '9' )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0>='0' && LA6_0<='9')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:160:23: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:160:34: ( EXPONENT )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0=='E'||LA7_0=='e') ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:160:34: EXPONENT
                            {
                            mEXPONENT(); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:161:6: '.' ( '0' .. '9' )+ ( EXPONENT )?
                    {
                    match('.'); 
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:161:10: ( '0' .. '9' )+
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
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:161:11: '0' .. '9'
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

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:161:22: ( EXPONENT )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0=='E'||LA9_0=='e') ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:161:22: EXPONENT
                            {
                            mEXPONENT(); 

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:162:6: ( '0' .. '9' )+ EXPONENT
                    {
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:162:6: ( '0' .. '9' )+
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
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:162:7: '0' .. '9'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:166:2: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? LINEFEED | '/*' ( options {greedy=false; } : ( LINEFEED | ~ ( '\\n' ) ) )* '*/' )
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
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:166:6: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? LINEFEED
                    {
                    match("//"); 

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:166:11: (~ ( '\\n' | '\\r' ) )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( ((LA12_0>='\u0000' && LA12_0<='\t')||(LA12_0>='\u000B' && LA12_0<='\f')||(LA12_0>='\u000E' && LA12_0<='\uFFFF')) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:166:11: ~ ( '\\n' | '\\r' )
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

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:166:25: ( '\\r' )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0=='\r') ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:166:25: '\\r'
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
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:167:6: '/*' ( options {greedy=false; } : ( LINEFEED | ~ ( '\\n' ) ) )* '*/'
                    {
                    match("/*"); 

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:167:11: ( options {greedy=false; } : ( LINEFEED | ~ ( '\\n' ) ) )*
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
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:167:39: ( LINEFEED | ~ ( '\\n' ) )
                    	    {
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:167:39: ( LINEFEED | ~ ( '\\n' ) )
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
                    	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:167:40: LINEFEED
                    	            {
                    	            mLINEFEED(); 

                    	            }
                    	            break;
                    	        case 2 :
                    	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:167:49: ~ ( '\\n' )
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:170:4: ( ( ' ' | '\\t' | '\\r' | LINEFEED ) )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:170:8: ( ' ' | '\\t' | '\\r' | LINEFEED )
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:178:9: ( '\\n' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:178:11: '\\n'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:181:2: ( '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:181:5: '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"'
            {
            match('\"'); 
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:181:9: ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )*
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
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:181:11: ESC_SEQ
            	    {
            	    mESC_SEQ(); 

            	    }
            	    break;
            	case 2 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:181:21: ~ ( '\\\\' | '\"' )
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:185:2: ( '\\'' ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) ) '\\'' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:185:5: '\\'' ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) ) '\\''
            {
            match('\''); 
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:185:10: ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) )
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
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:185:12: ESC_SEQ
                    {
                    mESC_SEQ(); 

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:185:22: ~ ( '\\'' | '\\\\' )
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:189:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:189:12: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:189:22: ( '+' | '-' )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0=='+'||LA19_0=='-') ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:
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

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:189:33: ( '0' .. '9' )+
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
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:189:34: '0' .. '9'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:192:11: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:192:13: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:196:5: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | UNICODE_ESC | OCTAL_ESC )
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
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:196:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
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
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:197:9: UNICODE_ESC
                    {
                    mUNICODE_ESC(); 

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:198:9: OCTAL_ESC
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:203:5: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
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
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:203:9: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:203:14: ( '0' .. '3' )
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:203:15: '0' .. '3'
                    {
                    matchRange('0','3'); 

                    }

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:203:25: ( '0' .. '7' )
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:203:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:203:36: ( '0' .. '7' )
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:203:37: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:204:9: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:204:14: ( '0' .. '7' )
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:204:15: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:204:25: ( '0' .. '7' )
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:204:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:205:9: '\\\\' ( '0' .. '7' )
                    {
                    match('\\'); 
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:205:14: ( '0' .. '7' )
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:205:15: '0' .. '7'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:210:5: ( '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:210:9: '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
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
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:8: ( PACKAGE | IMPORT | CLASS | INTERFACE | ENUM | EXTENDS | IMPLEMENTS | VOID | NEW | RETURN | BREAK | CONTINUE | NULL | THIS | SUPER | FOR | DO | WHILE | IF | ELSE | SWITCH | CASE | DEFAULT | TRY | CATCH | FINALLY | THROW | THROWS | PUBLIC | PRIVATE | PROTECTED | FINAL | VOLATILE | SYNCHRONIZED | STATIC | TRANSIENT | ABSTRACT | BOOLEAN | BYTE | CHAR | SHORT | INTEGER | LONG | FLOAT | DOUBLE | OPEN_CURLY_BRACKET | CLOSE_CURLY_BRACKET | LE | GE | EQUAL | UNEQUAL | ASSIGN | INCASSIGN | DECASSIGN | BITORASSIGN | BITANDASSIGN | INC | DEC | PLUS | MINUS | SLASH | STAR | QUESTION | PERCENT | LOGICAL_OR | BIT_OR | LOGICAL_AND | BIT_AND | NOT | DOT | COMMA | LT | GT | OPEN_RECT_BRACKET | CLOSE_RECT_BRACKET | OPEN_BRACKET | CLOSE_BRACKET | COLON | SEMICOLON | AT | TILDE | BOOL_CONST | ID | INT_CONST | HEX_CONST | LONG_CONST | HEX_LONG_CONST | FLOAT_CONST | COMMENT | WS | STRING_CONST | CHAR_CONST )
        int alt23=92;
        alt23 = dfa23.predict(input);
        switch (alt23) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:10: PACKAGE
                {
                mPACKAGE(); 

                }
                break;
            case 2 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:18: IMPORT
                {
                mIMPORT(); 

                }
                break;
            case 3 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:25: CLASS
                {
                mCLASS(); 

                }
                break;
            case 4 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:31: INTERFACE
                {
                mINTERFACE(); 

                }
                break;
            case 5 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:41: ENUM
                {
                mENUM(); 

                }
                break;
            case 6 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:46: EXTENDS
                {
                mEXTENDS(); 

                }
                break;
            case 7 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:54: IMPLEMENTS
                {
                mIMPLEMENTS(); 

                }
                break;
            case 8 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:65: VOID
                {
                mVOID(); 

                }
                break;
            case 9 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:70: NEW
                {
                mNEW(); 

                }
                break;
            case 10 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:74: RETURN
                {
                mRETURN(); 

                }
                break;
            case 11 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:81: BREAK
                {
                mBREAK(); 

                }
                break;
            case 12 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:87: CONTINUE
                {
                mCONTINUE(); 

                }
                break;
            case 13 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:96: NULL
                {
                mNULL(); 

                }
                break;
            case 14 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:101: THIS
                {
                mTHIS(); 

                }
                break;
            case 15 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:106: SUPER
                {
                mSUPER(); 

                }
                break;
            case 16 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:112: FOR
                {
                mFOR(); 

                }
                break;
            case 17 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:116: DO
                {
                mDO(); 

                }
                break;
            case 18 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:119: WHILE
                {
                mWHILE(); 

                }
                break;
            case 19 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:125: IF
                {
                mIF(); 

                }
                break;
            case 20 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:128: ELSE
                {
                mELSE(); 

                }
                break;
            case 21 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:133: SWITCH
                {
                mSWITCH(); 

                }
                break;
            case 22 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:140: CASE
                {
                mCASE(); 

                }
                break;
            case 23 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:145: DEFAULT
                {
                mDEFAULT(); 

                }
                break;
            case 24 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:153: TRY
                {
                mTRY(); 

                }
                break;
            case 25 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:157: CATCH
                {
                mCATCH(); 

                }
                break;
            case 26 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:163: FINALLY
                {
                mFINALLY(); 

                }
                break;
            case 27 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:171: THROW
                {
                mTHROW(); 

                }
                break;
            case 28 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:177: THROWS
                {
                mTHROWS(); 

                }
                break;
            case 29 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:184: PUBLIC
                {
                mPUBLIC(); 

                }
                break;
            case 30 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:191: PRIVATE
                {
                mPRIVATE(); 

                }
                break;
            case 31 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:199: PROTECTED
                {
                mPROTECTED(); 

                }
                break;
            case 32 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:209: FINAL
                {
                mFINAL(); 

                }
                break;
            case 33 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:215: VOLATILE
                {
                mVOLATILE(); 

                }
                break;
            case 34 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:224: SYNCHRONIZED
                {
                mSYNCHRONIZED(); 

                }
                break;
            case 35 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:237: STATIC
                {
                mSTATIC(); 

                }
                break;
            case 36 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:244: TRANSIENT
                {
                mTRANSIENT(); 

                }
                break;
            case 37 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:254: ABSTRACT
                {
                mABSTRACT(); 

                }
                break;
            case 38 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:263: BOOLEAN
                {
                mBOOLEAN(); 

                }
                break;
            case 39 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:271: BYTE
                {
                mBYTE(); 

                }
                break;
            case 40 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:276: CHAR
                {
                mCHAR(); 

                }
                break;
            case 41 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:281: SHORT
                {
                mSHORT(); 

                }
                break;
            case 42 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:287: INTEGER
                {
                mINTEGER(); 

                }
                break;
            case 43 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:295: LONG
                {
                mLONG(); 

                }
                break;
            case 44 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:300: FLOAT
                {
                mFLOAT(); 

                }
                break;
            case 45 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:306: DOUBLE
                {
                mDOUBLE(); 

                }
                break;
            case 46 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:313: OPEN_CURLY_BRACKET
                {
                mOPEN_CURLY_BRACKET(); 

                }
                break;
            case 47 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:332: CLOSE_CURLY_BRACKET
                {
                mCLOSE_CURLY_BRACKET(); 

                }
                break;
            case 48 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:352: LE
                {
                mLE(); 

                }
                break;
            case 49 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:355: GE
                {
                mGE(); 

                }
                break;
            case 50 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:358: EQUAL
                {
                mEQUAL(); 

                }
                break;
            case 51 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:364: UNEQUAL
                {
                mUNEQUAL(); 

                }
                break;
            case 52 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:372: ASSIGN
                {
                mASSIGN(); 

                }
                break;
            case 53 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:379: INCASSIGN
                {
                mINCASSIGN(); 

                }
                break;
            case 54 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:389: DECASSIGN
                {
                mDECASSIGN(); 

                }
                break;
            case 55 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:399: BITORASSIGN
                {
                mBITORASSIGN(); 

                }
                break;
            case 56 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:411: BITANDASSIGN
                {
                mBITANDASSIGN(); 

                }
                break;
            case 57 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:424: INC
                {
                mINC(); 

                }
                break;
            case 58 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:428: DEC
                {
                mDEC(); 

                }
                break;
            case 59 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:432: PLUS
                {
                mPLUS(); 

                }
                break;
            case 60 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:437: MINUS
                {
                mMINUS(); 

                }
                break;
            case 61 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:443: SLASH
                {
                mSLASH(); 

                }
                break;
            case 62 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:449: STAR
                {
                mSTAR(); 

                }
                break;
            case 63 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:454: QUESTION
                {
                mQUESTION(); 

                }
                break;
            case 64 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:463: PERCENT
                {
                mPERCENT(); 

                }
                break;
            case 65 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:471: LOGICAL_OR
                {
                mLOGICAL_OR(); 

                }
                break;
            case 66 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:482: BIT_OR
                {
                mBIT_OR(); 

                }
                break;
            case 67 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:489: LOGICAL_AND
                {
                mLOGICAL_AND(); 

                }
                break;
            case 68 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:501: BIT_AND
                {
                mBIT_AND(); 

                }
                break;
            case 69 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:509: NOT
                {
                mNOT(); 

                }
                break;
            case 70 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:513: DOT
                {
                mDOT(); 

                }
                break;
            case 71 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:517: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 72 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:523: LT
                {
                mLT(); 

                }
                break;
            case 73 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:526: GT
                {
                mGT(); 

                }
                break;
            case 74 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:529: OPEN_RECT_BRACKET
                {
                mOPEN_RECT_BRACKET(); 

                }
                break;
            case 75 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:547: CLOSE_RECT_BRACKET
                {
                mCLOSE_RECT_BRACKET(); 

                }
                break;
            case 76 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:566: OPEN_BRACKET
                {
                mOPEN_BRACKET(); 

                }
                break;
            case 77 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:579: CLOSE_BRACKET
                {
                mCLOSE_BRACKET(); 

                }
                break;
            case 78 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:593: COLON
                {
                mCOLON(); 

                }
                break;
            case 79 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:599: SEMICOLON
                {
                mSEMICOLON(); 

                }
                break;
            case 80 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:609: AT
                {
                mAT(); 

                }
                break;
            case 81 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:612: TILDE
                {
                mTILDE(); 

                }
                break;
            case 82 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:618: BOOL_CONST
                {
                mBOOL_CONST(); 

                }
                break;
            case 83 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:629: ID
                {
                mID(); 

                }
                break;
            case 84 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:632: INT_CONST
                {
                mINT_CONST(); 

                }
                break;
            case 85 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:642: HEX_CONST
                {
                mHEX_CONST(); 

                }
                break;
            case 86 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:652: LONG_CONST
                {
                mLONG_CONST(); 

                }
                break;
            case 87 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:663: HEX_LONG_CONST
                {
                mHEX_LONG_CONST(); 

                }
                break;
            case 88 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:678: FLOAT_CONST
                {
                mFLOAT_CONST(); 

                }
                break;
            case 89 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:690: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 90 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:698: WS
                {
                mWS(); 

                }
                break;
            case 91 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:701: STRING_CONST
                {
                mSTRING_CONST(); 

                }
                break;
            case 92 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:714: CHAR_CONST
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
        "\2\uffff\1\2\1\1\1\3";
    static final String DFA11_specialS =
        "\5\uffff}>";
    static final String[] DFA11_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\3\1\uffff\12\1\13\uffff\1\4\37\uffff\1\4",
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
            return "159:1: FLOAT_CONST : ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )+ ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT );";
        }
    }
    static final String DFA23_eotS =
        "\1\uffff\17\50\2\uffff\1\123\1\125\1\127\1\131\1\134\1\137\1\142"+
        "\1\145\1\147\3\uffff\1\150\12\uffff\2\153\3\uffff\5\50\1\163\31"+
        "\50\1\u0093\4\50\33\uffff\5\50\1\u00a0\1\uffff\12\50\1\u00ab\7\50"+
        "\1\u00b3\7\50\1\u00bb\4\50\1\uffff\4\50\1\u00c4\7\50\1\uffff\2\50"+
        "\1\u00cf\1\50\1\u00d1\1\u00d2\1\50\1\u00d4\1\u00d5\1\50\1\uffff"+
        "\1\u00d7\3\50\1\u00db\1\u00dc\1\50\1\uffff\1\50\1\u00df\5\50\1\uffff"+
        "\7\50\1\u00ec\2\uffff\7\50\1\u00f4\1\50\1\uffff\1\u00f6\2\uffff"+
        "\1\50\2\uffff\1\50\1\uffff\1\50\1\u00fa\1\50\2\uffff\1\u00fd\1\50"+
        "\1\uffff\1\u00ff\3\50\1\u0103\1\u0105\1\u0106\1\u00df\2\50\1\u0109"+
        "\1\50\1\uffff\1\50\1\u010c\2\50\1\u010f\2\50\1\uffff\1\50\1\uffff"+
        "\2\50\1\u0115\1\uffff\1\50\1\u0117\1\uffff\1\50\1\uffff\1\u0119"+
        "\1\50\1\u011b\1\uffff\1\50\2\uffff\1\u011d\1\50\1\uffff\1\50\1\u0120"+
        "\1\uffff\1\u0121\1\50\1\uffff\3\50\1\u0126\1\50\1\uffff\1\u0128"+
        "\1\uffff\1\50\1\uffff\1\50\1\uffff\1\u012b\1\uffff\1\u012c\1\50"+
        "\2\uffff\3\50\1\u0131\1\uffff\1\u0132\1\uffff\2\50\2\uffff\1\u0135"+
        "\1\u0136\1\50\1\u0138\2\uffff\1\u0139\1\50\2\uffff\1\u013b\2\uffff"+
        "\1\50\1\uffff\1\50\1\u013e\1\uffff";
    static final String DFA23_eofS =
        "\u013f\uffff";
    static final String DFA23_minS =
        "\1\11\1\141\1\146\1\141\1\154\1\157\2\145\1\157\2\150\1\141\1\145"+
        "\1\150\1\142\1\157\2\uffff\4\75\1\53\1\55\1\75\1\46\1\52\3\uffff"+
        "\1\60\12\uffff\2\56\3\uffff\1\143\1\142\1\151\1\160\1\164\1\60\1"+
        "\141\1\156\1\163\1\141\1\165\1\164\1\163\1\151\1\167\1\154\1\164"+
        "\1\145\1\157\1\164\1\151\1\141\1\160\1\151\1\156\1\141\1\157\1\162"+
        "\1\156\1\157\1\154\1\60\1\146\1\151\1\163\1\156\30\uffff\1\60\2"+
        "\uffff\1\153\1\154\1\166\1\164\1\154\1\60\1\uffff\1\163\1\164\1"+
        "\145\1\143\1\162\1\155\2\145\1\144\1\141\1\60\1\154\1\165\1\141"+
        "\1\154\1\145\1\163\1\157\1\60\1\156\2\145\1\164\1\143\1\164\1\162"+
        "\1\60\2\141\1\163\1\142\1\uffff\1\141\1\154\1\164\1\147\1\60\1\141"+
        "\1\151\1\141\1\145\1\162\1\145\1\162\1\uffff\1\163\1\151\1\60\1"+
        "\150\2\60\1\156\2\60\1\164\1\uffff\1\60\1\162\1\153\1\145\2\60\1"+
        "\167\1\uffff\1\163\1\60\1\162\1\143\1\150\1\151\1\164\1\uffff\1"+
        "\154\1\164\1\145\1\154\1\165\1\145\1\162\1\60\2\uffff\1\147\1\143"+
        "\1\164\1\143\1\164\1\155\1\146\1\60\1\156\1\uffff\1\60\2\uffff\1"+
        "\144\2\uffff\1\151\1\uffff\1\156\1\60\1\141\2\uffff\1\60\1\151\1"+
        "\uffff\1\60\1\150\1\162\1\143\4\60\1\145\1\154\1\60\1\141\1\uffff"+
        "\1\145\1\60\1\145\1\164\1\60\1\145\1\141\1\uffff\1\165\1\uffff\1"+
        "\163\1\154\1\60\1\uffff\1\156\1\60\1\uffff\1\145\1\uffff\1\60\1"+
        "\157\1\60\1\uffff\1\171\2\uffff\1\60\1\164\1\uffff\1\143\1\60\1"+
        "\uffff\1\60\1\145\1\uffff\1\156\1\143\1\145\1\60\1\145\1\uffff\1"+
        "\60\1\uffff\1\156\1\uffff\1\156\1\uffff\1\60\1\uffff\1\60\1\164"+
        "\2\uffff\1\144\1\164\1\145\1\60\1\uffff\1\60\1\uffff\1\164\1\151"+
        "\2\uffff\2\60\1\163\1\60\2\uffff\1\60\1\172\2\uffff\1\60\2\uffff"+
        "\1\145\1\uffff\1\144\1\60\1\uffff";
    static final String DFA23_maxS =
        "\1\176\1\165\1\156\1\157\1\170\1\157\1\165\1\145\1\171\1\162\1\171"+
        "\2\157\1\150\1\142\1\157\2\uffff\6\75\1\174\1\75\1\57\3\uffff\1"+
        "\71\12\uffff\1\170\1\145\3\uffff\1\143\1\142\1\157\1\160\1\164\1"+
        "\172\1\141\1\156\1\164\1\141\1\165\1\164\1\163\1\154\1\167\1\154"+
        "\1\164\1\145\1\157\1\164\1\162\1\171\1\160\1\151\1\156\1\141\1\157"+
        "\1\162\1\156\1\157\1\154\1\172\1\146\1\151\1\163\1\156\30\uffff"+
        "\1\146\2\uffff\1\153\1\154\1\166\1\164\1\157\1\172\1\uffff\1\163"+
        "\1\164\1\145\1\143\1\162\1\155\2\145\1\144\1\141\1\172\1\154\1\165"+
        "\1\141\1\154\1\145\1\163\1\157\1\172\1\156\2\145\1\164\1\143\1\164"+
        "\1\162\1\172\2\141\1\163\1\142\1\uffff\1\141\1\154\1\164\1\147\1"+
        "\146\1\141\1\151\1\141\1\145\1\162\1\145\1\162\1\uffff\1\163\1\151"+
        "\1\172\1\150\2\172\1\156\2\172\1\164\1\uffff\1\172\1\162\1\153\1"+
        "\145\2\172\1\167\1\uffff\1\163\1\172\1\162\1\143\1\150\1\151\1\164"+
        "\1\uffff\1\154\1\164\1\145\1\154\1\165\1\145\1\162\1\172\2\uffff"+
        "\1\147\1\143\1\164\1\143\1\164\1\155\1\146\1\172\1\156\1\uffff\1"+
        "\172\2\uffff\1\144\2\uffff\1\151\1\uffff\1\156\1\172\1\141\2\uffff"+
        "\1\172\1\151\1\uffff\1\172\1\150\1\162\1\143\4\172\1\145\1\154\1"+
        "\172\1\141\1\uffff\1\145\1\172\1\145\1\164\1\172\1\145\1\141\1\uffff"+
        "\1\165\1\uffff\1\163\1\154\1\172\1\uffff\1\156\1\172\1\uffff\1\145"+
        "\1\uffff\1\172\1\157\1\172\1\uffff\1\171\2\uffff\1\172\1\164\1\uffff"+
        "\1\143\1\172\1\uffff\1\172\1\145\1\uffff\1\156\1\143\1\145\1\172"+
        "\1\145\1\uffff\1\172\1\uffff\1\156\1\uffff\1\156\1\uffff\1\172\1"+
        "\uffff\1\172\1\164\2\uffff\1\144\1\164\1\145\1\172\1\uffff\1\172"+
        "\1\uffff\1\164\1\151\2\uffff\2\172\1\163\1\172\2\uffff\2\172\2\uffff"+
        "\1\172\2\uffff\1\145\1\uffff\1\144\1\172\1\uffff";
    static final String DFA23_acceptS =
        "\20\uffff\1\56\1\57\11\uffff\1\76\1\77\1\100\1\uffff\1\107\1\112"+
        "\1\113\1\114\1\115\1\116\1\117\1\120\1\121\1\123\2\uffff\1\132\1"+
        "\133\1\134\44\uffff\1\60\1\110\1\61\1\111\1\62\1\64\1\63\1\105\1"+
        "\65\1\71\1\73\1\66\1\72\1\74\1\67\1\101\1\102\1\70\1\103\1\104\1"+
        "\131\1\75\1\106\1\130\1\uffff\1\124\1\126\6\uffff\1\23\37\uffff"+
        "\1\21\14\uffff\1\52\12\uffff\1\11\7\uffff\1\30\7\uffff\1\20\10\uffff"+
        "\1\125\1\127\11\uffff\1\26\1\uffff\1\50\1\5\1\uffff\1\24\1\10\1"+
        "\uffff\1\15\3\uffff\1\47\1\16\2\uffff\1\122\14\uffff\1\53\7\uffff"+
        "\1\3\1\uffff\1\31\3\uffff\1\13\2\uffff\1\33\1\uffff\1\17\3\uffff"+
        "\1\51\1\uffff\1\40\1\54\2\uffff\1\22\2\uffff\1\35\2\uffff\1\2\5"+
        "\uffff\1\12\1\uffff\1\34\1\uffff\1\25\1\uffff\1\43\1\uffff\1\55"+
        "\2\uffff\1\1\1\36\4\uffff\1\6\1\uffff\1\46\2\uffff\1\32\1\27\4\uffff"+
        "\1\14\1\41\2\uffff\1\45\1\37\1\uffff\1\4\1\44\1\uffff\1\7\2\uffff"+
        "\1\42";
    static final String DFA23_specialS =
        "\u013f\uffff}>";
    static final String[] DFA23_transitionS = {
            "\2\53\2\uffff\1\53\22\uffff\1\53\1\25\1\54\2\uffff\1\35\1\31"+
            "\1\55\1\42\1\43\1\33\1\26\1\37\1\27\1\36\1\32\1\51\11\52\1\44"+
            "\1\45\1\22\1\24\1\23\1\34\1\46\32\50\1\40\1\uffff\1\41\1\uffff"+
            "\1\50\1\uffff\1\16\1\10\1\3\1\14\1\4\1\13\2\50\1\2\2\50\1\17"+
            "\1\50\1\6\1\50\1\1\1\50\1\7\1\12\1\11\1\50\1\5\1\15\3\50\1\20"+
            "\1\30\1\21\1\47",
            "\1\56\20\uffff\1\60\2\uffff\1\57",
            "\1\63\6\uffff\1\61\1\62",
            "\1\66\6\uffff\1\67\3\uffff\1\64\2\uffff\1\65",
            "\1\72\1\uffff\1\70\11\uffff\1\71",
            "\1\73",
            "\1\74\17\uffff\1\75",
            "\1\76",
            "\1\100\2\uffff\1\77\6\uffff\1\101",
            "\1\102\11\uffff\1\103",
            "\1\110\13\uffff\1\107\1\104\1\uffff\1\105\1\uffff\1\106",
            "\1\114\7\uffff\1\112\2\uffff\1\113\2\uffff\1\111",
            "\1\116\11\uffff\1\115",
            "\1\117",
            "\1\120",
            "\1\121",
            "",
            "",
            "\1\122",
            "\1\124",
            "\1\126",
            "\1\130",
            "\1\133\21\uffff\1\132",
            "\1\136\17\uffff\1\135",
            "\1\140\76\uffff\1\141",
            "\1\144\26\uffff\1\143",
            "\1\146\4\uffff\1\146",
            "",
            "",
            "",
            "\12\151",
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
            "\1\151\1\uffff\12\52\13\uffff\1\151\6\uffff\1\154\30\uffff"+
            "\1\151\22\uffff\1\152",
            "\1\151\1\uffff\12\52\13\uffff\1\151\6\uffff\1\154\30\uffff"+
            "\1\151",
            "",
            "",
            "",
            "\1\155",
            "\1\156",
            "\1\157\5\uffff\1\160",
            "\1\161",
            "\1\162",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\164",
            "\1\165",
            "\1\166\1\167",
            "\1\170",
            "\1\171",
            "\1\172",
            "\1\173",
            "\1\174\2\uffff\1\175",
            "\1\176",
            "\1\177",
            "\1\u0080",
            "\1\u0081",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084\10\uffff\1\u0085",
            "\1\u0087\23\uffff\1\u0088\3\uffff\1\u0086",
            "\1\u0089",
            "\1\u008a",
            "\1\u008b",
            "\1\u008c",
            "\1\u008d",
            "\1\u008e",
            "\1\u008f",
            "\1\u0090",
            "\1\u0091",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\24\50\1\u0092\5\50",
            "\1\u0094",
            "\1\u0095",
            "\1\u0096",
            "\1\u0097",
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
            "",
            "",
            "\12\u0098\7\uffff\6\u0098\32\uffff\6\u0098",
            "",
            "",
            "\1\u0099",
            "\1\u009a",
            "\1\u009b",
            "\1\u009c",
            "\1\u009e\2\uffff\1\u009d",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u009f\25\50",
            "",
            "\1\u00a1",
            "\1\u00a2",
            "\1\u00a3",
            "\1\u00a4",
            "\1\u00a5",
            "\1\u00a6",
            "\1\u00a7",
            "\1\u00a8",
            "\1\u00a9",
            "\1\u00aa",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00ac",
            "\1\u00ad",
            "\1\u00ae",
            "\1\u00af",
            "\1\u00b0",
            "\1\u00b1",
            "\1\u00b2",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00b4",
            "\1\u00b5",
            "\1\u00b6",
            "\1\u00b7",
            "\1\u00b8",
            "\1\u00b9",
            "\1\u00ba",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00bc",
            "\1\u00bd",
            "\1\u00be",
            "\1\u00bf",
            "",
            "\1\u00c0",
            "\1\u00c1",
            "\1\u00c2",
            "\1\u00c3",
            "\12\u0098\7\uffff\6\u0098\5\uffff\1\u00c5\24\uffff\6\u0098",
            "\1\u00c6",
            "\1\u00c7",
            "\1\u00c8",
            "\1\u00c9",
            "\1\u00ca",
            "\1\u00cb",
            "\1\u00cc",
            "",
            "\1\u00cd",
            "\1\u00ce",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00d0",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00d3",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00d6",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00d8",
            "\1\u00d9",
            "\1\u00da",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00dd",
            "",
            "\1\u00de",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00e0",
            "\1\u00e1",
            "\1\u00e2",
            "\1\u00e3",
            "\1\u00e4",
            "",
            "\1\u00e5",
            "\1\u00e6",
            "\1\u00e7",
            "\1\u00e8",
            "\1\u00e9",
            "\1\u00ea",
            "\1\u00eb",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "",
            "\1\u00ed",
            "\1\u00ee",
            "\1\u00ef",
            "\1\u00f0",
            "\1\u00f1",
            "\1\u00f2",
            "\1\u00f3",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00f5",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "",
            "\1\u00f7",
            "",
            "",
            "\1\u00f8",
            "",
            "\1\u00f9",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00fb",
            "",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\22\50\1\u00fc\7\50",
            "\1\u00fe",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0100",
            "\1\u0101",
            "\1\u0102",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\13\50\1\u0104\16"+
            "\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0107",
            "\1\u0108",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u010a",
            "",
            "\1\u010b",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u010d",
            "\1\u010e",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0110",
            "\1\u0111",
            "",
            "\1\u0112",
            "",
            "\1\u0113",
            "\1\u0114",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u0116",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u0118",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u011a",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u011c",
            "",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u011e",
            "",
            "\1\u011f",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0122",
            "",
            "\1\u0123",
            "\1\u0124",
            "\1\u0125",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0127",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u0129",
            "",
            "\1\u012a",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u012d",
            "",
            "",
            "\1\u012e",
            "\1\u012f",
            "\1\u0130",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u0133",
            "\1\u0134",
            "",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0137",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u013a",
            "",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "",
            "\1\u013c",
            "",
            "\1\u013d",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
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
            return "1:1: Tokens : ( PACKAGE | IMPORT | CLASS | INTERFACE | ENUM | EXTENDS | IMPLEMENTS | VOID | NEW | RETURN | BREAK | CONTINUE | NULL | THIS | SUPER | FOR | DO | WHILE | IF | ELSE | SWITCH | CASE | DEFAULT | TRY | CATCH | FINALLY | THROW | THROWS | PUBLIC | PRIVATE | PROTECTED | FINAL | VOLATILE | SYNCHRONIZED | STATIC | TRANSIENT | ABSTRACT | BOOLEAN | BYTE | CHAR | SHORT | INTEGER | LONG | FLOAT | DOUBLE | OPEN_CURLY_BRACKET | CLOSE_CURLY_BRACKET | LE | GE | EQUAL | UNEQUAL | ASSIGN | INCASSIGN | DECASSIGN | BITORASSIGN | BITANDASSIGN | INC | DEC | PLUS | MINUS | SLASH | STAR | QUESTION | PERCENT | LOGICAL_OR | BIT_OR | LOGICAL_AND | BIT_AND | NOT | DOT | COMMA | LT | GT | OPEN_RECT_BRACKET | CLOSE_RECT_BRACKET | OPEN_BRACKET | CLOSE_BRACKET | COLON | SEMICOLON | AT | TILDE | BOOL_CONST | ID | INT_CONST | HEX_CONST | LONG_CONST | HEX_LONG_CONST | FLOAT_CONST | COMMENT | WS | STRING_CONST | CHAR_CONST );";
        }
    }
 

}