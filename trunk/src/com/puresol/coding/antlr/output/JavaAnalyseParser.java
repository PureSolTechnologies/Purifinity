// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g 2009-12-05 19:11:56

package com.puresol.coding.antlr.output;

import com.puresol.coding.antlr.ANTLRJavaHelper;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class JavaAnalyseParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SEMICOLON", "VISIBILITY", "MODIFIER", "ID", "DOT", "STAR", "BLOCK_BEGIN", "BLOCK_END", "PLUS", "MINUS", "COMMA", "EQUAL", "OPEN_RECT_BRACKET", "CLOSE_RECT_BRACKET", "OPEN_BRACKET", "CLOSE_BRACKER", "INT", "EXPONENT", "FLOAT", "LINEFEED", "COMMENT", "WS", "ESC_SEQ", "STRING", "CHAR", "HEX_DIGIT", "UNICODE_ESC", "OCTAL_ESC", "'package'", "'import'"
    };
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
    public static final int ESC_SEQ=26;
    public static final int WS=25;
    public static final int T__33=33;
    public static final int COMMA=14;
    public static final int EQUAL=15;
    public static final int BLOCK_END=11;
    public static final int PLUS=12;
    public static final int COMMENT=24;
    public static final int DOT=8;
    public static final int OPEN_RECT_BRACKET=16;
    public static final int BLOCK_BEGIN=10;
    public static final int STRING=27;

    // delegates
    // delegators


        public JavaAnalyseParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public JavaAnalyseParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return JavaAnalyseParser.tokenNames; }
    public String getGrammarFileName() { return "/home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g"; }


    private ANTLRJavaHelper helper = null;

    public JavaAnalyseParser(TokenStream stream, ANTLRJavaHelper helper)
    {
    	this(stream);
    	this.helper = helper;
    }




    // $ANTLR start "file"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:38:1: file : package_decl ( import_decl )* ;
    public final void file() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:38:6: ( package_decl ( import_decl )* )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:38:8: package_decl ( import_decl )*
            {
            pushFollow(FOLLOW_package_decl_in_file49);
            package_decl();

            state._fsp--;

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:38:21: ( import_decl )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==33) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:38:21: import_decl
            	    {
            	    pushFollow(FOLLOW_import_decl_in_file51);
            	    import_decl();

            	    state._fsp--;


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


    // $ANTLR start "package_decl"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:40:1: package_decl : 'package' package_name SEMICOLON ;
    public final void package_decl() throws RecognitionException {
        JavaAnalyseParser.package_name_return package_name1 = null;


        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:40:14: ( 'package' package_name SEMICOLON )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:40:16: 'package' package_name SEMICOLON
            {
            match(input,32,FOLLOW_32_in_package_decl62); 
            pushFollow(FOLLOW_package_name_in_package_decl64);
            package_name1=package_name();

            state._fsp--;

            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_package_decl66); 
            helper.setPackageName((package_name1!=null?input.toString(package_name1.start,package_name1.stop):null));

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
    // $ANTLR end "package_decl"


    // $ANTLR start "import_decl"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:44:1: import_decl : 'import' import_name SEMICOLON ;
    public final void import_decl() throws RecognitionException {
        JavaAnalyseParser.import_name_return import_name2 = null;


        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:44:13: ( 'import' import_name SEMICOLON )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:44:15: 'import' import_name SEMICOLON
            {
            match(input,33,FOLLOW_33_in_import_decl78); 
            pushFollow(FOLLOW_import_name_in_import_decl81);
            import_name2=import_name();

            state._fsp--;

            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_import_decl83); 
            helper.addImport((import_name2!=null?input.toString(import_name2.start,import_name2.stop):null));

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
    // $ANTLR end "import_decl"

    public static class package_name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "package_name"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:62:1: package_name : ID ( DOT ID )* ;
    public final JavaAnalyseParser.package_name_return package_name() throws RecognitionException {
        JavaAnalyseParser.package_name_return retval = new JavaAnalyseParser.package_name_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:62:14: ( ID ( DOT ID )* )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:62:16: ID ( DOT ID )*
            {
            match(input,ID,FOLLOW_ID_in_package_name144); 
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:62:19: ( DOT ID )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==DOT) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:62:20: DOT ID
            	    {
            	    match(input,DOT,FOLLOW_DOT_in_package_name147); 
            	    match(input,ID,FOLLOW_ID_in_package_name149); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "package_name"

    public static class import_name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "import_name"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:63:1: import_name : ( ID DOT )* ( ID | STAR ) ;
    public final JavaAnalyseParser.import_name_return import_name() throws RecognitionException {
        JavaAnalyseParser.import_name_return retval = new JavaAnalyseParser.import_name_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:63:13: ( ( ID DOT )* ( ID | STAR ) )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:63:15: ( ID DOT )* ( ID | STAR )
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:63:15: ( ID DOT )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==ID) ) {
                    int LA3_1 = input.LA(2);

                    if ( (LA3_1==DOT) ) {
                        alt3=1;
                    }


                }


                switch (alt3) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:63:16: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_import_name159); 
            	    match(input,DOT,FOLLOW_DOT_in_import_name161); 

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            if ( input.LA(1)==ID||input.LA(1)==STAR ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "import_name"

    // Delegated rules


 

    public static final BitSet FOLLOW_package_decl_in_file49 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_import_decl_in_file51 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_32_in_package_decl62 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_package_name_in_package_decl64 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SEMICOLON_in_package_decl66 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_import_decl78 = new BitSet(new long[]{0x0000000000000280L});
    public static final BitSet FOLLOW_import_name_in_import_decl81 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SEMICOLON_in_import_decl83 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_package_name144 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_DOT_in_package_name147 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ID_in_package_name149 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_ID_in_import_name159 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_DOT_in_import_name161 = new BitSet(new long[]{0x0000000000000280L});
    public static final BitSet FOLLOW_set_in_import_name165 = new BitSet(new long[]{0x0000000000000002L});

}