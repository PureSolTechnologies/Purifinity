// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g 2009-12-06 16:32:26

package com.puresol.coding.java.antlr.output;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class JavaParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CLASS", "EXTENDS", "IMPLEMENTS", "VOID", "NEW", "RETURN", "FOR", "DO", "WHILE", "IF", "ELSE", "SWITCH", "CASE", "PUBLIC", "PRIVATE", "PROTECTED", "FINAL", "STATIC", "TRANSIENT", "OPEN_CURLY_BRACKET", "CLOSE_CURLY_BRACKET", "PLUS", "MINUS", "SLASH", "STAR", "EQUAL", "UNEQUAL", "ASSIGN", "INC", "DEC", "LOGICAL_OR", "BIT_OR", "LOGICAL_AND", "BIT_AND", "NOT", "DOT", "COMMA", "LT", "GT", "OPEN_RECT_BRACKET", "CLOSE_RECT_BRACKET", "OPEN_BRACKET", "CLOSE_BRACKER", "COLON", "SEMICOLON", "ID", "INT", "EXPONENT", "FLOAT", "LINEFEED", "COMMENT", "WS", "ESC_SEQ", "STRING", "CHAR", "HEX_DIGIT", "UNICODE_ESC", "OCTAL_ESC", "PACKAGE"
    };
    public static final int PACKAGE=62;
    public static final int PROTECTED=19;
    public static final int DEC=33;
    public static final int LT=41;
    public static final int EXPONENT=51;
    public static final int CLASS=4;
    public static final int STAR=28;
    public static final int BIT_AND=37;
    public static final int WHILE=12;
    public static final int CASE=16;
    public static final int OCTAL_ESC=61;
    public static final int NEW=8;
    public static final int CHAR=58;
    public static final int FOR=10;
    public static final int DO=11;
    public static final int FLOAT=52;
    public static final int NOT=38;
    public static final int ID=49;
    public static final int EOF=-1;
    public static final int CLOSE_RECT_BRACKET=44;
    public static final int LOGICAL_AND=36;
    public static final int LINEFEED=53;
    public static final int IF=13;
    public static final int OPEN_BRACKET=45;
    public static final int FINAL=20;
    public static final int INC=32;
    public static final int ESC_SEQ=56;
    public static final int SLASH=27;
    public static final int IMPLEMENTS=6;
    public static final int BIT_OR=35;
    public static final int COMMA=40;
    public static final int TRANSIENT=22;
    public static final int EQUAL=29;
    public static final int RETURN=9;
    public static final int LOGICAL_OR=34;
    public static final int PLUS=25;
    public static final int VOID=7;
    public static final int COMMENT=54;
    public static final int DOT=39;
    public static final int CLOSE_BRACKER=46;
    public static final int OPEN_CURLY_BRACKET=23;
    public static final int STATIC=21;
    public static final int PRIVATE=18;
    public static final int SWITCH=15;
    public static final int UNICODE_ESC=60;
    public static final int CLOSE_CURLY_BRACKET=24;
    public static final int ELSE=14;
    public static final int HEX_DIGIT=59;
    public static final int SEMICOLON=48;
    public static final int INT=50;
    public static final int MINUS=26;
    public static final int UNEQUAL=30;
    public static final int COLON=47;
    public static final int WS=55;
    public static final int ASSIGN=31;
    public static final int GT=42;
    public static final int OPEN_RECT_BRACKET=43;
    public static final int EXTENDS=5;
    public static final int PUBLIC=17;
    public static final int STRING=57;

    // delegates
    // delegators


        public JavaParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public JavaParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return JavaParser.tokenNames; }
    public String getGrammarFileName() { return "/home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g"; }



    // $ANTLR start "file"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:11:1: file : PACKAGE ( ID DOT )* ID ;
    public final void file() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:11:6: ( PACKAGE ( ID DOT )* ID )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:11:8: PACKAGE ( ID DOT )* ID
            {
            match(input,PACKAGE,FOLLOW_PACKAGE_in_file28); 
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:11:16: ( ID DOT )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==ID) ) {
                    int LA1_1 = input.LA(2);

                    if ( (LA1_1==DOT) ) {
                        alt1=1;
                    }


                }


                switch (alt1) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:11:17: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_file31); 
            	    match(input,DOT,FOLLOW_DOT_in_file33); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_file37); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "file"

    // Delegated rules


 

    public static final BitSet FOLLOW_PACKAGE_in_file28 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_ID_in_file31 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_DOT_in_file33 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_ID_in_file37 = new BitSet(new long[]{0x0000000000000002L});

}