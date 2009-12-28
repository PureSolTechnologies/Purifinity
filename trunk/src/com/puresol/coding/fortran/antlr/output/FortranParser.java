// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranParser.g 2009-12-28 12:26:50

package com.puresol.coding.fortran.antlr.output;

import com.puresol.coding.CodeRangeType;
import com.puresol.coding.ParserHelper;
import java.io.File;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class FortranParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "S", "U", "B", "R", "O", "T", "I", "N", "E", "SUBROUTINE", "G", "INTEGER", "A", "L", "REAL", "F", "IF", "H", "THEN", "D", "WS", "END_IF", "DOLLAR", "COMMA", "LPAREN", "RPAREN", "COLON", "ASSIGN", "MINUS", "PLUS", "DIV", "POWER", "STAR", "LNOT", "LAND", "LOR", "Q", "V", "EQV", "NEQV", "X", "XOR", "EOR", "LT", "LE", "GT", "GE", "NE", "EQ", "TRUE", "FALSE", "ID", "INT_CONST", "HEX_DIGIT", "HEX_CONST", "EXPONENT", "FLOAT_CONST", "LINEFEED", "COMMENT", "NOTNL", "ESC_SEQ", "STRING_CONST", "CHAR_CONST", "UNICODE_ESC", "OCTAL_ESC", "C", "J", "K", "M", "P", "W", "Y", "Z"
    };
    public static final int DOLLAR=26;
    public static final int LOR=39;
    public static final int EXPONENT=59;
    public static final int LT=47;
    public static final int STAR=36;
    public static final int OCTAL_ESC=68;
    public static final int ID=55;
    public static final int EOF=-1;
    public static final int LPAREN=28;
    public static final int FLOAT_CONST=60;
    public static final int IF=20;
    public static final int LINEFEED=61;
    public static final int CHAR_CONST=66;
    public static final int LNOT=37;
    public static final int RPAREN=29;
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
    public static final int I=10;
    public static final int UNICODE_ESC=67;
    public static final int J=70;
    public static final int K=71;
    public static final int U=5;
    public static final int T=9;
    public static final int INT_CONST=56;
    public static final int POWER=35;
    public static final int HEX_DIGIT=57;
    public static final int W=74;
    public static final int NEQV=43;
    public static final int V=41;
    public static final int Q=40;
    public static final int P=73;
    public static final int S=4;
    public static final int MINUS=32;
    public static final int R=7;
    public static final int TRUE=53;
    public static final int Y=75;
    public static final int X=44;
    public static final int NOTNL=63;
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


        public FortranParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public FortranParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return FortranParser.tokenNames; }
    public String getGrammarFileName() { return "/home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranParser.g"; }


    private ParserHelper helper = null;

    public FortranParser(File file, TokenStream input) {
    	this(input, new RecognizerSharedState());
    	helper = new ParserHelper(file, this);
    }

    public ParserHelper getParserHelper() {
    	return helper;
    }



    // $ANTLR start "file"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranParser.g:30:1: file : (~ WS | WS )* ;
    public final void file() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranParser.g:30:6: ( (~ WS | WS )* )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranParser.g:30:8: (~ WS | WS )*
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranParser.g:30:8: (~ WS | WS )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=S && LA1_0<=Z)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/fortran/antlr/FortranParser.g:
            	    {
            	    if ( (input.LA(1)>=S && input.LA(1)<=Z) ) {
            	        input.consume();
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


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


 

    public static final BitSet FOLLOW_set_in_file49 = new BitSet(new long[]{0xFFFFFFFFFFFFFFF2L,0x0000000000001FFFL});

}