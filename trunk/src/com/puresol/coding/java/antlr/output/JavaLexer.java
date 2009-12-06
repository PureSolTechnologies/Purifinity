// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g 2009-12-06 16:44:44

package com.puresol.coding.java.antlr.output;

import com.puresol.coding.java.antlr.JavaLexerHelper;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class JavaLexer extends Lexer {
    public static final int PACKAGE=4;
    public static final int PROTECTED=21;
    public static final int DEC=35;
    public static final int EXPONENT=53;
    public static final int CLASS=6;
    public static final int LT=43;
    public static final int STAR=30;
    public static final int WHILE=14;
    public static final int BIT_AND=39;
    public static final int OCTAL_ESC=63;
    public static final int CASE=18;
    public static final int CHAR=60;
    public static final int NEW=10;
    public static final int DO=13;
    public static final int FOR=12;
    public static final int FLOAT=54;
    public static final int NOT=40;
    public static final int ID=51;
    public static final int CLOSE_RECT_BRACKET=46;
    public static final int EOF=-1;
    public static final int LOGICAL_AND=38;
    public static final int LINEFEED=55;
    public static final int IF=15;
    public static final int OPEN_BRACKET=47;
    public static final int FINAL=22;
    public static final int INC=34;
    public static final int IMPORT=5;
    public static final int ESC_SEQ=58;
    public static final int SLASH=29;
    public static final int IMPLEMENTS=8;
    public static final int BIT_OR=37;
    public static final int COMMA=42;
    public static final int TRANSIENT=24;
    public static final int EQUAL=31;
    public static final int RETURN=11;
    public static final int LOGICAL_OR=36;
    public static final int PLUS=27;
    public static final int VOID=9;
    public static final int COMMENT=56;
    public static final int DOT=41;
    public static final int CLOSE_BRACKER=48;
    public static final int OPEN_CURLY_BRACKET=25;
    public static final int PRIVATE=20;
    public static final int STATIC=23;
    public static final int SWITCH=17;
    public static final int UNICODE_ESC=62;
    public static final int ELSE=16;
    public static final int CLOSE_CURLY_BRACKET=26;
    public static final int HEX_DIGIT=61;
    public static final int INT=52;
    public static final int SEMICOLON=50;
    public static final int MINUS=28;
    public static final int UNEQUAL=32;
    public static final int COLON=49;
    public static final int WS=57;
    public static final int ASSIGN=33;
    public static final int GT=44;
    public static final int OPEN_RECT_BRACKET=45;
    public static final int PUBLIC=19;
    public static final int EXTENDS=7;
    public static final int STRING=59;

    private JavaLexerHelper helper = null;

    public JavaLexer(CharStream stream, JavaLexerHelper helper)
    {
    	this(stream);
    	this.helper = helper;
    }


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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:20:9: ( 'package' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:20:12: 'package'
            {
            match("package"); 

            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:21:8: ( 'import' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:21:11: 'import'
            {
            match("import"); 

            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:23:7: ( 'class' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:23:9: 'class'
            {
            match("class"); 

            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:24:9: ( 'extends' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:24:11: 'extends'
            {
            match("extends"); 

            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:25:12: ( 'implements' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:25:14: 'implements'
            {
            match("implements"); 

            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:27:6: ( 'void' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:27:8: 'void'
            {
            match("void"); 

            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:28:5: ( 'new' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:28:7: 'new'
            {
            match("new"); 

            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:29:8: ( 'return' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:29:10: 'return'
            {
            match("return"); 

            helper.addOperator(getText());

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RETURN"

    // $ANTLR start "FOR"
    public final void mFOR() throws RecognitionException {
        try {
            int _type = FOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:31:5: ( 'for' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:31:7: 'for'
            {
            match("for"); 

            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:32:4: ( 'do' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:32:6: 'do'
            {
            match("do"); 

            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:33:7: ( 'while' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:33:9: 'while'
            {
            match("while"); 

            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:34:4: ( 'if' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:34:6: 'if'
            {
            match("if"); 

            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:35:6: ( 'else' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:35:8: 'else'
            {
            match("else"); 

            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:36:8: ( 'switch' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:36:10: 'switch'
            {
            match("switch"); 

            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:37:6: ( 'case' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:37:8: 'case'
            {
            match("case"); 

            helper.addOperator(getText());

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CASE"

    // $ANTLR start "PUBLIC"
    public final void mPUBLIC() throws RecognitionException {
        try {
            int _type = PUBLIC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:39:8: ( 'public' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:39:10: 'public'
            {
            match("public"); 

            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:40:9: ( 'private' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:40:11: 'private'
            {
            match("private"); 

            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:42:2: ( 'protected' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:42:4: 'protected'
            {
            match("protected"); 

            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:43:7: ( 'final' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:43:9: 'final'
            {
            match("final"); 

            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:44:8: ( 'static' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:44:10: 'static'
            {
            match("static"); 

            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:46:2: ( 'transient' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:46:4: 'transient'
            {
            match("transient"); 

            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:49:2: ( '{' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:49:4: '{'
            {
            match('{'); 
            helper.addBlockBegin();helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:53:2: ( '}' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:53:4: '}'
            {
            match('}'); 
            helper.addBlockEnd();helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:56:6: ( '+' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:56:8: '+'
            {
            match('+'); 
            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:57:7: ( '-' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:57:9: '-'
            {
            match('-'); 
            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:58:7: ( '/' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:58:9: '/'
            {
            match('/'); 
            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:59:6: ( '*' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:59:8: '*'
            {
            match('*'); 
            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:61:7: ( '==' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:61:9: '=='
            {
            match("=="); 

            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:62:9: ( '!=' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:62:11: '!='
            {
            match("!="); 

            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:63:8: ( '=' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:63:10: '='
            {
            match('='); 
            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:65:5: ( '++' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:65:7: '++'
            {
            match("++"); 

            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:66:5: ( '--' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:66:7: '--'
            {
            match("--"); 

            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:69:2: ( '||' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:69:4: '||'
            {
            match("||"); 

            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:70:8: ( '|' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:70:10: '|'
            {
            match('|'); 
            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:73:2: ( '&&' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:73:4: '&&'
            {
            match("&&"); 

            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:74:9: ( '&' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:74:11: '&'
            {
            match('&'); 
            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:76:5: ( '!' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:76:7: '!'
            {
            match('!'); 
            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:78:5: ( '.' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:78:7: '.'
            {
            match('.'); 
            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:79:7: ( ',' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:79:9: ','
            {
            match(','); 
            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:80:4: ( '<' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:80:6: '<'
            {
            match('<'); 
            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:81:4: ( '>' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:81:6: '>'
            {
            match('>'); 
            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:84:2: ( '[' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:84:4: '['
            {
            match('['); 
            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:88:2: ( ']' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:88:4: ']'
            {
            match(']'); 
            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:92:2: ( '(' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:92:4: '('
            {
            match('('); 
            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:96:2: ( ')' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:96:4: ')'
            {
            match(')'); 
            helper.addOperator(getText());

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CLOSE_BRACKER"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:99:7: ( ':' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:99:9: ':'
            {
            match(':'); 
            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:101:2: ( ';' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:101:4: ';'
            {
            match(';'); 
            helper.addOperator(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:103:5: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:103:7: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:103:31: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
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
            	    break loop1;
                }
            } while (true);

            helper.addOperand(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:106:5: ( ( '0' .. '9' )+ )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:106:7: ( '0' .. '9' )+
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:106:7: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:106:7: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);

            helper.addOperand(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:110:5: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )+ ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT )
            int alt9=3;
            alt9 = dfa9.predict(input);
            switch (alt9) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:110:9: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )?
                    {
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:110:9: ( '0' .. '9' )+
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
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:110:10: '0' .. '9'
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

                    match('.'); 
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:110:25: ( '0' .. '9' )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:110:26: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:110:37: ( EXPONENT )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0=='E'||LA5_0=='e') ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:110:37: EXPONENT
                            {
                            mEXPONENT(); 

                            }
                            break;

                    }

                    helper.addOperand(getText());

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:111:9: '.' ( '0' .. '9' )+ ( EXPONENT )?
                    {
                    match('.'); 
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:111:13: ( '0' .. '9' )+
                    int cnt6=0;
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0>='0' && LA6_0<='9')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:111:14: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt6 >= 1 ) break loop6;
                                EarlyExitException eee =
                                    new EarlyExitException(6, input);
                                throw eee;
                        }
                        cnt6++;
                    } while (true);

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:111:25: ( EXPONENT )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0=='E'||LA7_0=='e') ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:111:25: EXPONENT
                            {
                            mEXPONENT(); 

                            }
                            break;

                    }

                    helper.addOperand(getText());

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:112:9: ( '0' .. '9' )+ EXPONENT
                    {
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:112:9: ( '0' .. '9' )+
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
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:112:10: '0' .. '9'
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

                    mEXPONENT(); 
                    helper.addOperand(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:116:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? LINEFEED | '/*' ( options {greedy=false; } : ( LINEFEED | ~ ( '\\n' ) ) )* '*/' )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0=='/') ) {
                int LA14_1 = input.LA(2);

                if ( (LA14_1=='/') ) {
                    alt14=1;
                }
                else if ( (LA14_1=='*') ) {
                    alt14=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:116:9: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? LINEFEED
                    {
                    match("//"); 

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:116:14: (~ ( '\\n' | '\\r' ) )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( ((LA10_0>='\u0000' && LA10_0<='\t')||(LA10_0>='\u000B' && LA10_0<='\f')||(LA10_0>='\u000E' && LA10_0<='\uFFFF')) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:116:14: ~ ( '\\n' | '\\r' )
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
                    	    break loop10;
                        }
                    } while (true);

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:116:28: ( '\\r' )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0=='\r') ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:116:28: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    mLINEFEED(); 
                    _channel=HIDDEN; helper.addOperand(getText());

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:117:9: '/*' ( options {greedy=false; } : ( LINEFEED | ~ ( '\\n' ) ) )* '*/'
                    {
                    match("/*"); 

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:117:14: ( options {greedy=false; } : ( LINEFEED | ~ ( '\\n' ) ) )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0=='*') ) {
                            int LA13_1 = input.LA(2);

                            if ( (LA13_1=='/') ) {
                                alt13=2;
                            }
                            else if ( ((LA13_1>='\u0000' && LA13_1<='.')||(LA13_1>='0' && LA13_1<='\uFFFF')) ) {
                                alt13=1;
                            }


                        }
                        else if ( ((LA13_0>='\u0000' && LA13_0<=')')||(LA13_0>='+' && LA13_0<='\uFFFF')) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:117:42: ( LINEFEED | ~ ( '\\n' ) )
                    	    {
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:117:42: ( LINEFEED | ~ ( '\\n' ) )
                    	    int alt12=2;
                    	    int LA12_0 = input.LA(1);

                    	    if ( (LA12_0=='\n') ) {
                    	        alt12=1;
                    	    }
                    	    else if ( ((LA12_0>='\u0000' && LA12_0<='\t')||(LA12_0>='\u000B' && LA12_0<='\uFFFF')) ) {
                    	        alt12=2;
                    	    }
                    	    else {
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 12, 0, input);

                    	        throw nvae;
                    	    }
                    	    switch (alt12) {
                    	        case 1 :
                    	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:117:43: LINEFEED
                    	            {
                    	            mLINEFEED(); 

                    	            }
                    	            break;
                    	        case 2 :
                    	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:117:52: ~ ( '\\n' )
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
                    	    break loop13;
                        }
                    } while (true);

                    match("*/"); 

                    _channel=HIDDEN; helper.addOperand(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:120:5: ( ( ' ' | '\\t' | '\\r' | LINEFEED ) )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:120:9: ( ' ' | '\\t' | '\\r' | LINEFEED )
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:120:9: ( ' ' | '\\t' | '\\r' | LINEFEED )
            int alt15=4;
            switch ( input.LA(1) ) {
            case ' ':
                {
                alt15=1;
                }
                break;
            case '\t':
                {
                alt15=2;
                }
                break;
            case '\r':
                {
                alt15=3;
                }
                break;
            case '\n':
                {
                alt15=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:120:11: ' '
                    {
                    match(' '); 

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:121:11: '\\t'
                    {
                    match('\t'); 

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:122:11: '\\r'
                    {
                    match('\r'); 

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:123:11: LINEFEED
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:128:9: ( '\\n' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:128:11: '\\n'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:131:5: ( '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:131:8: '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"'
            {
            match('\"'); 
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:131:12: ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )*
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
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:131:14: ESC_SEQ
            	    {
            	    mESC_SEQ(); 

            	    }
            	    break;
            	case 2 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:131:24: ~ ( '\\\\' | '\"' )
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
            helper.addOperand(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:134:5: ( '\\'' ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) ) '\\'' )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:134:8: '\\'' ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) ) '\\''
            {
            match('\''); 
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:134:13: ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) )
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
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:134:15: ESC_SEQ
                    {
                    mESC_SEQ(); 

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:134:25: ~ ( '\\'' | '\\\\' )
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
            helper.addOperand(getText());

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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:138:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:138:12: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:138:22: ( '+' | '-' )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0=='+'||LA18_0=='-') ) {
                alt18=1;
            }
            switch (alt18) {
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

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:138:33: ( '0' .. '9' )+
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
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:138:34: '0' .. '9'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:141:11: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:141:13: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:145:5: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | UNICODE_ESC | OCTAL_ESC )
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
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:145:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
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
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:146:9: UNICODE_ESC
                    {
                    mUNICODE_ESC(); 

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:147:9: OCTAL_ESC
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:152:5: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
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
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:152:9: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:152:14: ( '0' .. '3' )
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:152:15: '0' .. '3'
                    {
                    matchRange('0','3'); 

                    }

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:152:25: ( '0' .. '7' )
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:152:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:152:36: ( '0' .. '7' )
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:152:37: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:153:9: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:153:14: ( '0' .. '7' )
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:153:15: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:153:25: ( '0' .. '7' )
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:153:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:154:9: '\\\\' ( '0' .. '7' )
                    {
                    match('\\'); 
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:154:14: ( '0' .. '7' )
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:154:15: '0' .. '7'
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:159:5: ( '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:159:9: '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
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
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:8: ( PACKAGE | IMPORT | CLASS | EXTENDS | IMPLEMENTS | VOID | NEW | RETURN | FOR | DO | WHILE | IF | ELSE | SWITCH | CASE | PUBLIC | PRIVATE | PROTECTED | FINAL | STATIC | TRANSIENT | OPEN_CURLY_BRACKET | CLOSE_CURLY_BRACKET | PLUS | MINUS | SLASH | STAR | EQUAL | UNEQUAL | ASSIGN | INC | DEC | LOGICAL_OR | BIT_OR | LOGICAL_AND | BIT_AND | NOT | DOT | COMMA | LT | GT | OPEN_RECT_BRACKET | CLOSE_RECT_BRACKET | OPEN_BRACKET | CLOSE_BRACKER | COLON | SEMICOLON | ID | INT | FLOAT | COMMENT | WS | STRING | CHAR )
        int alt22=54;
        alt22 = dfa22.predict(input);
        switch (alt22) {
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
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:31: EXTENDS
                {
                mEXTENDS(); 

                }
                break;
            case 5 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:39: IMPLEMENTS
                {
                mIMPLEMENTS(); 

                }
                break;
            case 6 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:50: VOID
                {
                mVOID(); 

                }
                break;
            case 7 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:55: NEW
                {
                mNEW(); 

                }
                break;
            case 8 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:59: RETURN
                {
                mRETURN(); 

                }
                break;
            case 9 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:66: FOR
                {
                mFOR(); 

                }
                break;
            case 10 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:70: DO
                {
                mDO(); 

                }
                break;
            case 11 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:73: WHILE
                {
                mWHILE(); 

                }
                break;
            case 12 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:79: IF
                {
                mIF(); 

                }
                break;
            case 13 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:82: ELSE
                {
                mELSE(); 

                }
                break;
            case 14 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:87: SWITCH
                {
                mSWITCH(); 

                }
                break;
            case 15 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:94: CASE
                {
                mCASE(); 

                }
                break;
            case 16 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:99: PUBLIC
                {
                mPUBLIC(); 

                }
                break;
            case 17 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:106: PRIVATE
                {
                mPRIVATE(); 

                }
                break;
            case 18 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:114: PROTECTED
                {
                mPROTECTED(); 

                }
                break;
            case 19 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:124: FINAL
                {
                mFINAL(); 

                }
                break;
            case 20 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:130: STATIC
                {
                mSTATIC(); 

                }
                break;
            case 21 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:137: TRANSIENT
                {
                mTRANSIENT(); 

                }
                break;
            case 22 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:147: OPEN_CURLY_BRACKET
                {
                mOPEN_CURLY_BRACKET(); 

                }
                break;
            case 23 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:166: CLOSE_CURLY_BRACKET
                {
                mCLOSE_CURLY_BRACKET(); 

                }
                break;
            case 24 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:186: PLUS
                {
                mPLUS(); 

                }
                break;
            case 25 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:191: MINUS
                {
                mMINUS(); 

                }
                break;
            case 26 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:197: SLASH
                {
                mSLASH(); 

                }
                break;
            case 27 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:203: STAR
                {
                mSTAR(); 

                }
                break;
            case 28 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:208: EQUAL
                {
                mEQUAL(); 

                }
                break;
            case 29 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:214: UNEQUAL
                {
                mUNEQUAL(); 

                }
                break;
            case 30 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:222: ASSIGN
                {
                mASSIGN(); 

                }
                break;
            case 31 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:229: INC
                {
                mINC(); 

                }
                break;
            case 32 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:233: DEC
                {
                mDEC(); 

                }
                break;
            case 33 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:237: LOGICAL_OR
                {
                mLOGICAL_OR(); 

                }
                break;
            case 34 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:248: BIT_OR
                {
                mBIT_OR(); 

                }
                break;
            case 35 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:255: LOGICAL_AND
                {
                mLOGICAL_AND(); 

                }
                break;
            case 36 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:267: BIT_AND
                {
                mBIT_AND(); 

                }
                break;
            case 37 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:275: NOT
                {
                mNOT(); 

                }
                break;
            case 38 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:279: DOT
                {
                mDOT(); 

                }
                break;
            case 39 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:283: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 40 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:289: LT
                {
                mLT(); 

                }
                break;
            case 41 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:292: GT
                {
                mGT(); 

                }
                break;
            case 42 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:295: OPEN_RECT_BRACKET
                {
                mOPEN_RECT_BRACKET(); 

                }
                break;
            case 43 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:313: CLOSE_RECT_BRACKET
                {
                mCLOSE_RECT_BRACKET(); 

                }
                break;
            case 44 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:332: OPEN_BRACKET
                {
                mOPEN_BRACKET(); 

                }
                break;
            case 45 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:345: CLOSE_BRACKER
                {
                mCLOSE_BRACKER(); 

                }
                break;
            case 46 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:359: COLON
                {
                mCOLON(); 

                }
                break;
            case 47 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:365: SEMICOLON
                {
                mSEMICOLON(); 

                }
                break;
            case 48 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:375: ID
                {
                mID(); 

                }
                break;
            case 49 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:378: INT
                {
                mINT(); 

                }
                break;
            case 50 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:382: FLOAT
                {
                mFLOAT(); 

                }
                break;
            case 51 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:388: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 52 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:396: WS
                {
                mWS(); 

                }
                break;
            case 53 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:399: STRING
                {
                mSTRING(); 

                }
                break;
            case 54 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaLexer.g:1:406: CHAR
                {
                mCHAR(); 

                }
                break;

        }

    }


    protected DFA9 dfa9 = new DFA9(this);
    protected DFA22 dfa22 = new DFA22(this);
    static final String DFA9_eotS =
        "\5\uffff";
    static final String DFA9_eofS =
        "\5\uffff";
    static final String DFA9_minS =
        "\2\56\3\uffff";
    static final String DFA9_maxS =
        "\1\71\1\145\3\uffff";
    static final String DFA9_acceptS =
        "\2\uffff\1\2\1\1\1\3";
    static final String DFA9_specialS =
        "\5\uffff}>";
    static final String[] DFA9_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\3\1\uffff\12\1\13\uffff\1\4\37\uffff\1\4",
            "",
            "",
            ""
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "109:1: FLOAT : ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )+ ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT );";
        }
    }
    static final String DFA22_eotS =
        "\1\uffff\14\41\2\uffff\1\72\1\74\1\76\1\uffff\1\100\1\102\1\104"+
        "\1\106\1\110\12\uffff\1\111\3\uffff\4\41\1\117\11\41\1\131\4\41"+
        "\21\uffff\5\41\1\uffff\5\41\1\151\1\41\1\153\1\41\1\uffff\13\41"+
        "\1\170\1\41\1\172\1\173\1\uffff\1\41\1\uffff\13\41\1\u0088\1\uffff"+
        "\1\41\2\uffff\1\41\1\u008b\1\u008c\4\41\1\u0091\2\41\1\u0094\1\41"+
        "\1\uffff\1\41\1\u0097\2\uffff\1\u0098\1\u0099\1\41\1\u009b\1\uffff"+
        "\1\u009c\1\41\1\uffff\1\41\1\u009f\3\uffff\1\41\2\uffff\2\41\1\uffff"+
        "\1\41\1\u00a4\1\41\1\u00a6\1\uffff\1\u00a7\2\uffff";
    static final String DFA22_eofS =
        "\u00a8\uffff";
    static final String DFA22_minS =
        "\1\11\1\141\1\146\1\141\1\154\1\157\2\145\1\151\1\157\1\150\1\164"+
        "\1\162\2\uffff\1\53\1\55\1\52\1\uffff\2\75\1\174\1\46\1\60\12\uffff"+
        "\1\56\3\uffff\1\143\1\142\1\151\1\160\1\60\1\141\1\163\1\164\1\163"+
        "\1\151\1\167\1\164\1\162\1\156\1\60\2\151\2\141\21\uffff\1\153\1"+
        "\154\1\166\1\164\1\154\1\uffff\1\163\3\145\1\144\1\60\1\165\1\60"+
        "\1\141\1\uffff\1\154\2\164\1\156\1\141\1\151\1\141\1\145\1\162\1"+
        "\145\1\163\1\60\1\156\2\60\1\uffff\1\162\1\uffff\1\154\1\145\1\143"+
        "\1\151\1\163\1\147\1\143\1\164\1\143\1\164\1\155\1\60\1\uffff\1"+
        "\144\2\uffff\1\156\2\60\1\150\1\143\1\151\1\145\1\60\1\145\1\164"+
        "\1\60\1\145\1\uffff\1\163\1\60\2\uffff\2\60\1\145\1\60\1\uffff\1"+
        "\60\1\145\1\uffff\1\156\1\60\3\uffff\1\156\2\uffff\1\144\1\164\1"+
        "\uffff\1\164\1\60\1\163\1\60\1\uffff\1\60\2\uffff";
    static final String DFA22_maxS =
        "\1\175\1\165\1\155\1\154\1\170\1\157\2\145\2\157\1\150\1\167\1\162"+
        "\2\uffff\1\53\1\55\1\57\1\uffff\2\75\1\174\1\46\1\71\12\uffff\1"+
        "\145\3\uffff\1\143\1\142\1\157\1\160\1\172\1\141\1\163\1\164\1\163"+
        "\1\151\1\167\1\164\1\162\1\156\1\172\2\151\2\141\21\uffff\1\153"+
        "\1\154\1\166\1\164\1\157\1\uffff\1\163\3\145\1\144\1\172\1\165\1"+
        "\172\1\141\1\uffff\1\154\2\164\1\156\1\141\1\151\1\141\1\145\1\162"+
        "\1\145\1\163\1\172\1\156\2\172\1\uffff\1\162\1\uffff\1\154\1\145"+
        "\1\143\1\151\1\163\1\147\1\143\1\164\1\143\1\164\1\155\1\172\1\uffff"+
        "\1\144\2\uffff\1\156\2\172\1\150\1\143\1\151\1\145\1\172\1\145\1"+
        "\164\1\172\1\145\1\uffff\1\163\1\172\2\uffff\2\172\1\145\1\172\1"+
        "\uffff\1\172\1\145\1\uffff\1\156\1\172\3\uffff\1\156\2\uffff\1\144"+
        "\1\164\1\uffff\1\164\1\172\1\163\1\172\1\uffff\1\172\2\uffff";
    static final String DFA22_acceptS =
        "\15\uffff\1\26\1\27\3\uffff\1\33\5\uffff\1\47\1\50\1\51\1\52\1\53"+
        "\1\54\1\55\1\56\1\57\1\60\1\uffff\1\64\1\65\1\66\23\uffff\1\37\1"+
        "\30\1\40\1\31\1\63\1\32\1\34\1\36\1\35\1\45\1\41\1\42\1\43\1\44"+
        "\1\62\1\46\1\61\5\uffff\1\14\11\uffff\1\12\17\uffff\1\7\1\uffff"+
        "\1\11\14\uffff\1\17\1\uffff\1\15\1\6\14\uffff\1\3\2\uffff\1\23\1"+
        "\13\4\uffff\1\20\2\uffff\1\2\2\uffff\1\10\1\16\1\24\1\uffff\1\1"+
        "\1\21\2\uffff\1\4\4\uffff\1\22\1\uffff\1\25\1\5";
    static final String DFA22_specialS =
        "\u00a8\uffff}>";
    static final String[] DFA22_transitionS = {
            "\2\43\2\uffff\1\43\22\uffff\1\43\1\24\1\44\3\uffff\1\26\1\45"+
            "\1\35\1\36\1\22\1\17\1\30\1\20\1\27\1\21\12\42\1\37\1\40\1\31"+
            "\1\23\1\32\2\uffff\32\41\1\33\1\uffff\1\34\1\uffff\1\41\1\uffff"+
            "\2\41\1\3\1\11\1\4\1\10\2\41\1\2\4\41\1\6\1\41\1\1\1\41\1\7"+
            "\1\13\1\14\1\41\1\5\1\12\3\41\1\15\1\25\1\16",
            "\1\46\20\uffff\1\50\2\uffff\1\47",
            "\1\52\6\uffff\1\51",
            "\1\54\12\uffff\1\53",
            "\1\56\13\uffff\1\55",
            "\1\57",
            "\1\60",
            "\1\61",
            "\1\63\5\uffff\1\62",
            "\1\64",
            "\1\65",
            "\1\67\2\uffff\1\66",
            "\1\70",
            "",
            "",
            "\1\71",
            "\1\73",
            "\1\75\4\uffff\1\75",
            "",
            "\1\77",
            "\1\101",
            "\1\103",
            "\1\105",
            "\12\107",
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
            "\1\107\1\uffff\12\42\13\uffff\1\107\37\uffff\1\107",
            "",
            "",
            "",
            "\1\112",
            "\1\113",
            "\1\114\5\uffff\1\115",
            "\1\116",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\120",
            "\1\121",
            "\1\122",
            "\1\123",
            "\1\124",
            "\1\125",
            "\1\126",
            "\1\127",
            "\1\130",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\132",
            "\1\133",
            "\1\134",
            "\1\135",
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
            "\1\136",
            "\1\137",
            "\1\140",
            "\1\141",
            "\1\143\2\uffff\1\142",
            "",
            "\1\144",
            "\1\145",
            "\1\146",
            "\1\147",
            "\1\150",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\152",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\154",
            "",
            "\1\155",
            "\1\156",
            "\1\157",
            "\1\160",
            "\1\161",
            "\1\162",
            "\1\163",
            "\1\164",
            "\1\165",
            "\1\166",
            "\1\167",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\171",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "\1\174",
            "",
            "\1\175",
            "\1\176",
            "\1\177",
            "\1\u0080",
            "\1\u0081",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "\1\u0089",
            "",
            "",
            "\1\u008a",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u008d",
            "\1\u008e",
            "\1\u008f",
            "\1\u0090",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0092",
            "\1\u0093",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u0095",
            "",
            "\1\u0096",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u009a",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u009d",
            "",
            "\1\u009e",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "",
            "",
            "\1\u00a0",
            "",
            "",
            "\1\u00a1",
            "\1\u00a2",
            "",
            "\1\u00a3",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\u00a5",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
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
            return "1:1: Tokens : ( PACKAGE | IMPORT | CLASS | EXTENDS | IMPLEMENTS | VOID | NEW | RETURN | FOR | DO | WHILE | IF | ELSE | SWITCH | CASE | PUBLIC | PRIVATE | PROTECTED | FINAL | STATIC | TRANSIENT | OPEN_CURLY_BRACKET | CLOSE_CURLY_BRACKET | PLUS | MINUS | SLASH | STAR | EQUAL | UNEQUAL | ASSIGN | INC | DEC | LOGICAL_OR | BIT_OR | LOGICAL_AND | BIT_AND | NOT | DOT | COMMA | LT | GT | OPEN_RECT_BRACKET | CLOSE_RECT_BRACKET | OPEN_BRACKET | CLOSE_BRACKER | COLON | SEMICOLON | ID | INT | FLOAT | COMMENT | WS | STRING | CHAR );";
        }
    }
 

}