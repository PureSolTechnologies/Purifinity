// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g 2009-12-06 16:59:22

package com.puresol.coding.java.antlr.output;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class JavaParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "PACKAGE", "IMPORT", "CLASS", "EXTENDS", "IMPLEMENTS", "VOID", "NEW", "RETURN", "FOR", "DO", "WHILE", "IF", "ELSE", "SWITCH", "CASE", "PUBLIC", "PRIVATE", "PROTECTED", "FINAL", "STATIC", "TRANSIENT", "OPEN_CURLY_BRACKET", "CLOSE_CURLY_BRACKET", "PLUS", "MINUS", "SLASH", "STAR", "EQUAL", "UNEQUAL", "ASSIGN", "INC", "DEC", "LOGICAL_OR", "BIT_OR", "LOGICAL_AND", "BIT_AND", "NOT", "DOT", "COMMA", "LT", "GT", "OPEN_RECT_BRACKET", "CLOSE_RECT_BRACKET", "OPEN_BRACKET", "CLOSE_BRACKER", "COLON", "SEMICOLON", "ID", "INT", "EXPONENT", "FLOAT", "LINEFEED", "COMMENT", "WS", "ESC_SEQ", "STRING", "CHAR", "HEX_DIGIT", "UNICODE_ESC", "OCTAL_ESC"
    };
    public static final int PACKAGE=4;
    public static final int PROTECTED=21;
    public static final int DEC=35;
    public static final int LT=43;
    public static final int CLASS=6;
    public static final int EXPONENT=53;
    public static final int STAR=30;
    public static final int BIT_AND=39;
    public static final int WHILE=14;
    public static final int CASE=18;
    public static final int OCTAL_ESC=63;
    public static final int NEW=10;
    public static final int CHAR=60;
    public static final int FOR=12;
    public static final int DO=13;
    public static final int FLOAT=54;
    public static final int NOT=40;
    public static final int ID=51;
    public static final int EOF=-1;
    public static final int CLOSE_RECT_BRACKET=46;
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
    public static final int STATIC=23;
    public static final int PRIVATE=20;
    public static final int SWITCH=17;
    public static final int UNICODE_ESC=62;
    public static final int CLOSE_CURLY_BRACKET=26;
    public static final int ELSE=16;
    public static final int HEX_DIGIT=61;
    public static final int SEMICOLON=50;
    public static final int INT=52;
    public static final int MINUS=28;
    public static final int UNEQUAL=32;
    public static final int COLON=49;
    public static final int WS=57;
    public static final int ASSIGN=33;
    public static final int GT=44;
    public static final int OPEN_RECT_BRACKET=45;
    public static final int EXTENDS=7;
    public static final int PUBLIC=19;
    public static final int STRING=59;

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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:11:1: file : package_def imports ( class_def )* ;
    public final void file() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:11:6: ( package_def imports ( class_def )* )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:11:8: package_def imports ( class_def )*
            {
            pushFollow(FOLLOW_package_def_in_file28);
            package_def();

            state._fsp--;

            pushFollow(FOLLOW_imports_in_file30);
            imports();

            state._fsp--;

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:11:28: ( class_def )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==CLASS||(LA1_0>=PUBLIC && LA1_0<=TRANSIENT)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:11:28: class_def
            	    {
            	    pushFollow(FOLLOW_class_def_in_file32);
            	    class_def();

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

    public static class package_def_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "package_def"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:14:1: package_def : PACKAGE package_name SEMICOLON ;
    public final JavaParser.package_def_return package_def() throws RecognitionException {
        JavaParser.package_def_return retval = new JavaParser.package_def_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:14:13: ( PACKAGE package_name SEMICOLON )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:14:15: PACKAGE package_name SEMICOLON
            {
            match(input,PACKAGE,FOLLOW_PACKAGE_in_package_def43); 
            pushFollow(FOLLOW_package_name_in_package_def45);
            package_name();

            state._fsp--;

            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_package_def47); 
            System.out.println(input.toString(retval.start,input.LT(-1)));

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
    // $ANTLR end "package_def"


    // $ANTLR start "package_name"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:16:1: fragment package_name : ( ID DOT )* ID ;
    public final void package_name() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:18:2: ( ( ID DOT )* ID )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:18:4: ( ID DOT )* ID
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:18:4: ( ID DOT )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==ID) ) {
                    int LA2_1 = input.LA(2);

                    if ( (LA2_1==DOT) ) {
                        alt2=1;
                    }


                }


                switch (alt2) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:18:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_package_name61); 
            	    match(input,DOT,FOLLOW_DOT_in_package_name63); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_package_name67); 

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
    // $ANTLR end "package_name"


    // $ANTLR start "imports"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:22:1: imports : ( import_def )* ;
    public final void imports() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:22:9: ( ( import_def )* )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:22:11: ( import_def )*
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:22:11: ( import_def )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==IMPORT) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:22:11: import_def
            	    {
            	    pushFollow(FOLLOW_import_def_in_imports79);
            	    import_def();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop3;
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
    // $ANTLR end "imports"

    public static class import_def_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "import_def"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:24:1: import_def : IMPORT import_name SEMICOLON ;
    public final JavaParser.import_def_return import_def() throws RecognitionException {
        JavaParser.import_def_return retval = new JavaParser.import_def_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:25:2: ( IMPORT import_name SEMICOLON )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:25:4: IMPORT import_name SEMICOLON
            {
            match(input,IMPORT,FOLLOW_IMPORT_in_import_def89); 
            pushFollow(FOLLOW_import_name_in_import_def91);
            import_name();

            state._fsp--;

            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_import_def93); 
            System.out.println(input.toString(retval.start,input.LT(-1)));

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
    // $ANTLR end "import_def"


    // $ANTLR start "import_name"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:28:1: fragment import_name : ( ID DOT )+ ( ID | STAR ) ;
    public final void import_name() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:30:2: ( ( ID DOT )+ ( ID | STAR ) )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:30:4: ( ID DOT )+ ( ID | STAR )
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:30:4: ( ID DOT )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==ID) ) {
                    int LA4_1 = input.LA(2);

                    if ( (LA4_1==DOT) ) {
                        alt4=1;
                    }


                }


                switch (alt4) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:30:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_import_name110); 
            	    match(input,DOT,FOLLOW_DOT_in_import_name112); 

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

            if ( input.LA(1)==STAR||input.LA(1)==ID ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


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
    // $ANTLR end "import_name"

    public static class class_def_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "class_def"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:33:1: class_def : modifiers CLASS ID ( EXTENDS class_name )? ( IMPLEMENTS class_name ( COMMA class_name )* )? ;
    public final JavaParser.class_def_return class_def() throws RecognitionException {
        JavaParser.class_def_return retval = new JavaParser.class_def_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:34:2: ( modifiers CLASS ID ( EXTENDS class_name )? ( IMPLEMENTS class_name ( COMMA class_name )* )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:34:4: modifiers CLASS ID ( EXTENDS class_name )? ( IMPLEMENTS class_name ( COMMA class_name )* )?
            {
            pushFollow(FOLLOW_modifiers_in_class_def132);
            modifiers();

            state._fsp--;

            match(input,CLASS,FOLLOW_CLASS_in_class_def134); 
            match(input,ID,FOLLOW_ID_in_class_def136); 
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:34:23: ( EXTENDS class_name )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==EXTENDS) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:34:24: EXTENDS class_name
                    {
                    match(input,EXTENDS,FOLLOW_EXTENDS_in_class_def139); 
                    pushFollow(FOLLOW_class_name_in_class_def141);
                    class_name();

                    state._fsp--;


                    }
                    break;

            }

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:34:45: ( IMPLEMENTS class_name ( COMMA class_name )* )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==IMPLEMENTS) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:34:46: IMPLEMENTS class_name ( COMMA class_name )*
                    {
                    match(input,IMPLEMENTS,FOLLOW_IMPLEMENTS_in_class_def146); 
                    pushFollow(FOLLOW_class_name_in_class_def148);
                    class_name();

                    state._fsp--;

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:34:68: ( COMMA class_name )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==COMMA) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:34:69: COMMA class_name
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_class_def151); 
                    	    pushFollow(FOLLOW_class_name_in_class_def153);
                    	    class_name();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);


                    }
                    break;

            }

            System.out.println(input.toString(retval.start,input.LT(-1)));

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
    // $ANTLR end "class_def"


    // $ANTLR start "class_name"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:37:1: fragment class_name : ( ID DOT )* ID ;
    public final void class_name() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:39:2: ( ( ID DOT )* ID )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:39:4: ( ID DOT )* ID
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:39:4: ( ID DOT )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==ID) ) {
                    int LA8_1 = input.LA(2);

                    if ( (LA8_1==DOT) ) {
                        alt8=1;
                    }


                }


                switch (alt8) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:39:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_class_name173); 
            	    match(input,DOT,FOLLOW_DOT_in_class_name175); 

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_class_name179); 

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
    // $ANTLR end "class_name"


    // $ANTLR start "modifiers"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:42:1: modifiers : ( modifier )* ;
    public final void modifiers() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:43:2: ( ( modifier )* )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:43:4: ( modifier )*
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:43:4: ( modifier )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>=PUBLIC && LA9_0<=TRANSIENT)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:43:4: modifier
            	    {
            	    pushFollow(FOLLOW_modifier_in_modifiers190);
            	    modifier();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
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
    // $ANTLR end "modifiers"


    // $ANTLR start "modifier"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:46:1: modifier : ( PUBLIC | PRIVATE | PROTECTED | STATIC | FINAL | TRANSIENT );
    public final void modifier() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:46:9: ( PUBLIC | PRIVATE | PROTECTED | STATIC | FINAL | TRANSIENT )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:
            {
            if ( (input.LA(1)>=PUBLIC && input.LA(1)<=TRANSIENT) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


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
    // $ANTLR end "modifier"

    // Delegated rules


 

    public static final BitSet FOLLOW_package_def_in_file28 = new BitSet(new long[]{0x0000000001F80060L});
    public static final BitSet FOLLOW_imports_in_file30 = new BitSet(new long[]{0x0000000001F80042L});
    public static final BitSet FOLLOW_class_def_in_file32 = new BitSet(new long[]{0x0000000001F80042L});
    public static final BitSet FOLLOW_PACKAGE_in_package_def43 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_package_name_in_package_def45 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_package_def47 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_package_name61 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_DOT_in_package_name63 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_ID_in_package_name67 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_import_def_in_imports79 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_IMPORT_in_import_def89 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_import_name_in_import_def91 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_import_def93 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_import_name110 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_DOT_in_import_name112 = new BitSet(new long[]{0x0008000040000000L});
    public static final BitSet FOLLOW_set_in_import_name116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_class_def132 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_CLASS_in_class_def134 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_ID_in_class_def136 = new BitSet(new long[]{0x0000000000000182L});
    public static final BitSet FOLLOW_EXTENDS_in_class_def139 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_class_name_in_class_def141 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_IMPLEMENTS_in_class_def146 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_class_name_in_class_def148 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_COMMA_in_class_def151 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_class_name_in_class_def153 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_ID_in_class_name173 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_DOT_in_class_name175 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_ID_in_class_name179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifier_in_modifiers190 = new BitSet(new long[]{0x0000000001F80002L});
    public static final BitSet FOLLOW_set_in_modifier0 = new BitSet(new long[]{0x0000000000000002L});

}