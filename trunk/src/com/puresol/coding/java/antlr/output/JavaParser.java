// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g 2009-12-07 18:06:06

package com.puresol.coding.java.antlr.output;

import com.puresol.coding.java.antlr.JavaParserHelper;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class JavaParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "PACKAGE", "IMPORT", "CLASS", "EXTENDS", "IMPLEMENTS", "VOID", "NEW", "RETURN", "NULL", "THIS", "SUPER", "FOR", "DO", "WHILE", "IF", "ELSE", "SWITCH", "CASE", "TRY", "CATCH", "FINALLY", "PUBLIC", "PRIVATE", "PROTECTED", "FINAL", "STATIC", "TRANSIENT", "OPEN_CURLY_BRACKET", "CLOSE_CURLY_BRACKET", "PLUS", "MINUS", "SLASH", "STAR", "EQUAL", "UNEQUAL", "ASSIGN", "INC", "DEC", "LOGICAL_OR", "BIT_OR", "LOGICAL_AND", "BIT_AND", "NOT", "DOT", "COMMA", "LT", "GT", "OPEN_RECT_BRACKET", "CLOSE_RECT_BRACKET", "OPEN_BRACKET", "CLOSE_BRACKET", "COLON", "SEMICOLON", "BOOLEAN", "ID", "INT", "EXPONENT", "FLOAT", "LINEFEED", "COMMENT", "WS", "ESC_SEQ", "STRING", "CHAR", "HEX_DIGIT", "UNICODE_ESC", "OCTAL_ESC"
    };
    public static final int PACKAGE=4;
    public static final int PROTECTED=27;
    public static final int DEC=41;
    public static final int CLASS=6;
    public static final int LT=49;
    public static final int EXPONENT=60;
    public static final int STAR=36;
    public static final int WHILE=17;
    public static final int BIT_AND=45;
    public static final int CASE=21;
    public static final int OCTAL_ESC=70;
    public static final int NEW=10;
    public static final int CHAR=67;
    public static final int FOR=15;
    public static final int DO=16;
    public static final int FLOAT=61;
    public static final int NOT=46;
    public static final int ID=58;
    public static final int EOF=-1;
    public static final int CLOSE_RECT_BRACKET=52;
    public static final int LOGICAL_AND=44;
    public static final int LINEFEED=62;
    public static final int IF=18;
    public static final int OPEN_BRACKET=53;
    public static final int FINAL=28;
    public static final int INC=40;
    public static final int IMPORT=5;
    public static final int ESC_SEQ=65;
    public static final int BOOLEAN=57;
    public static final int SLASH=35;
    public static final int IMPLEMENTS=8;
    public static final int BIT_OR=43;
    public static final int COMMA=48;
    public static final int TRANSIENT=30;
    public static final int EQUAL=37;
    public static final int THIS=13;
    public static final int RETURN=11;
    public static final int LOGICAL_OR=42;
    public static final int PLUS=33;
    public static final int VOID=9;
    public static final int SUPER=14;
    public static final int DOT=47;
    public static final int COMMENT=63;
    public static final int OPEN_CURLY_BRACKET=31;
    public static final int STATIC=29;
    public static final int PRIVATE=26;
    public static final int SWITCH=20;
    public static final int NULL=12;
    public static final int UNICODE_ESC=69;
    public static final int CLOSE_CURLY_BRACKET=32;
    public static final int ELSE=19;
    public static final int HEX_DIGIT=68;
    public static final int SEMICOLON=56;
    public static final int INT=59;
    public static final int MINUS=34;
    public static final int TRY=22;
    public static final int UNEQUAL=38;
    public static final int COLON=55;
    public static final int WS=64;
    public static final int FINALLY=24;
    public static final int ASSIGN=39;
    public static final int GT=50;
    public static final int CATCH=23;
    public static final int OPEN_RECT_BRACKET=51;
    public static final int PUBLIC=25;
    public static final int EXTENDS=7;
    public static final int STRING=66;
    public static final int CLOSE_BRACKET=54;

    // delegates
    // delegators


        public JavaParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public JavaParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return JavaParser.tokenNames; }
    public String getGrammarFileName() { return "/home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g"; }


    private JavaParserHelper helper = null;

    public JavaParser(TokenStream stream, JavaParserHelper helper)
    {
    	this(stream);
    	this.helper = helper;
    }



    // $ANTLR start "file"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:1: file : package_def imports ( class_def )* ;
    public final void file() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:6: ( package_def imports ( class_def )* )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:8: package_def imports ( class_def )*
            {
            pushFollow(FOLLOW_package_def_in_file39);
            package_def();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_imports_in_file41);
            imports();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:28: ( class_def )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==CLASS||LA1_0==VOID||(LA1_0>=PUBLIC && LA1_0<=TRANSIENT)||LA1_0==ID) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: class_def
            	    {
            	    pushFollow(FOLLOW_class_def_in_file43);
            	    class_def();

            	    state._fsp--;
            	    if (state.failed) return ;

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


    // $ANTLR start "package_def"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:26:1: package_def : PACKAGE package_name SEMICOLON ;
    public final void package_def() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:27:2: ( PACKAGE package_name SEMICOLON )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:27:4: PACKAGE package_name SEMICOLON
            {
            match(input,PACKAGE,FOLLOW_PACKAGE_in_package_def54); if (state.failed) return ;
            pushFollow(FOLLOW_package_name_in_package_def56);
            package_name();

            state._fsp--;
            if (state.failed) return ;
            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_package_def58); if (state.failed) return ;

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
    // $ANTLR end "package_def"


    // $ANTLR start "package_name"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:30:1: fragment package_name : ( ID DOT )* ID ;
    public final void package_name() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:32:2: ( ( ID DOT )* ID )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:32:4: ( ID DOT )* ID
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:32:4: ( ID DOT )*
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
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:32:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_package_name74); if (state.failed) return ;
            	    match(input,DOT,FOLLOW_DOT_in_package_name76); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_package_name80); if (state.failed) return ;

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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:35:1: imports : ( import_def )* ;
    public final void imports() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:35:9: ( ( import_def )* )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:35:11: ( import_def )*
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:35:11: ( import_def )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==IMPORT) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: import_def
            	    {
            	    pushFollow(FOLLOW_import_def_in_imports90);
            	    import_def();

            	    state._fsp--;
            	    if (state.failed) return ;

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


    // $ANTLR start "import_def"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:37:1: import_def : IMPORT import_name SEMICOLON ;
    public final void import_def() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:38:2: ( IMPORT import_name SEMICOLON )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:38:4: IMPORT import_name SEMICOLON
            {
            match(input,IMPORT,FOLLOW_IMPORT_in_import_def100); if (state.failed) return ;
            pushFollow(FOLLOW_import_name_in_import_def102);
            import_name();

            state._fsp--;
            if (state.failed) return ;
            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_import_def104); if (state.failed) return ;

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
    // $ANTLR end "import_def"


    // $ANTLR start "import_name"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:41:1: fragment import_name : ( ID DOT )+ ( ID | STAR ) ;
    public final void import_name() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:43:2: ( ( ID DOT )+ ( ID | STAR ) )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:43:4: ( ID DOT )+ ( ID | STAR )
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:43:4: ( ID DOT )+
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
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:43:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_import_name120); if (state.failed) return ;
            	    match(input,DOT,FOLLOW_DOT_in_import_name122); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);

            if ( input.LA(1)==STAR||input.LA(1)==ID ) {
                input.consume();
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:46:1: class_def : modifiers CLASS ID ( EXTENDS class_name )? ( IMPLEMENTS class_name ( COMMA class_name )* )? block_begin class_block block_end ;
    public final JavaParser.class_def_return class_def() throws RecognitionException {
        JavaParser.class_def_return retval = new JavaParser.class_def_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:47:2: ( modifiers CLASS ID ( EXTENDS class_name )? ( IMPLEMENTS class_name ( COMMA class_name )* )? block_begin class_block block_end )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:47:4: modifiers CLASS ID ( EXTENDS class_name )? ( IMPLEMENTS class_name ( COMMA class_name )* )? block_begin class_block block_end
            {
            pushFollow(FOLLOW_modifiers_in_class_def142);
            modifiers();

            state._fsp--;
            if (state.failed) return retval;
            match(input,CLASS,FOLLOW_CLASS_in_class_def144); if (state.failed) return retval;
            match(input,ID,FOLLOW_ID_in_class_def146); if (state.failed) return retval;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:47:23: ( EXTENDS class_name )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==EXTENDS) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:47:24: EXTENDS class_name
                    {
                    match(input,EXTENDS,FOLLOW_EXTENDS_in_class_def149); if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_class_def151);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;

            }

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:47:45: ( IMPLEMENTS class_name ( COMMA class_name )* )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==IMPLEMENTS) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:47:46: IMPLEMENTS class_name ( COMMA class_name )*
                    {
                    match(input,IMPLEMENTS,FOLLOW_IMPLEMENTS_in_class_def156); if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_class_def158);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:47:68: ( COMMA class_name )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==COMMA) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:47:69: COMMA class_name
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_class_def161); if (state.failed) return retval;
                    	    pushFollow(FOLLOW_class_name_in_class_def163);
                    	    class_name();

                    	    state._fsp--;
                    	    if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);


                    }
                    break;

            }

            pushFollow(FOLLOW_block_begin_in_class_def169);
            block_begin();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_class_block_in_class_def171);
            class_block();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_block_end_in_class_def173);
            block_end();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              System.out.println("class_def " + input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "class_def"


    // $ANTLR start "modifiers"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:50:1: modifiers : ( modifier )* ;
    public final void modifiers() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:51:2: ( ( modifier )* )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:51:4: ( modifier )*
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:51:4: ( modifier )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>=PUBLIC && LA8_0<=TRANSIENT)) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: modifier
            	    {
            	    pushFollow(FOLLOW_modifier_in_modifiers186);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop8;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:54:1: modifier : ( PUBLIC | PRIVATE | PROTECTED | STATIC | FINAL | TRANSIENT );
    public final void modifier() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:54:9: ( PUBLIC | PRIVATE | PROTECTED | STATIC | FINAL | TRANSIENT )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:
            {
            if ( (input.LA(1)>=PUBLIC && input.LA(1)<=TRANSIENT) ) {
                input.consume();
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
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


    // $ANTLR start "block_begin"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:62:1: block_begin : OPEN_CURLY_BRACKET ;
    public final void block_begin() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:63:2: ( OPEN_CURLY_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:63:4: OPEN_CURLY_BRACKET
            {
            match(input,OPEN_CURLY_BRACKET,FOLLOW_OPEN_CURLY_BRACKET_in_block_begin232); if (state.failed) return ;

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
    // $ANTLR end "block_begin"


    // $ANTLR start "block_end"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:66:1: block_end : CLOSE_CURLY_BRACKET ;
    public final void block_end() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:67:2: ( CLOSE_CURLY_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:67:4: CLOSE_CURLY_BRACKET
            {
            match(input,CLOSE_CURLY_BRACKET,FOLLOW_CLOSE_CURLY_BRACKET_in_block_end244); if (state.failed) return ;

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
    // $ANTLR end "block_end"


    // $ANTLR start "class_block"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:1: class_block : ( class_def | constructor_def | method_def | field_def )* ;
    public final void class_block() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:71:2: ( ( class_def | constructor_def | method_def | field_def )* )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:71:4: ( class_def | constructor_def | method_def | field_def )*
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:71:4: ( class_def | constructor_def | method_def | field_def )*
            loop9:
            do {
                int alt9=5;
                switch ( input.LA(1) ) {
                case PUBLIC:
                case PRIVATE:
                case PROTECTED:
                case FINAL:
                case STATIC:
                case TRANSIENT:
                    {
                    int LA9_2 = input.LA(2);

                    if ( (synpred15_JavaParser()) ) {
                        alt9=1;
                    }
                    else if ( (synpred16_JavaParser()) ) {
                        alt9=2;
                    }
                    else if ( (synpred17_JavaParser()) ) {
                        alt9=3;
                    }
                    else if ( (synpred18_JavaParser()) ) {
                        alt9=4;
                    }


                    }
                    break;
                case CLASS:
                    {
                    alt9=1;
                    }
                    break;
                case ID:
                    {
                    int LA9_4 = input.LA(2);

                    if ( (synpred16_JavaParser()) ) {
                        alt9=2;
                    }
                    else if ( (synpred17_JavaParser()) ) {
                        alt9=3;
                    }
                    else if ( (synpred18_JavaParser()) ) {
                        alt9=4;
                    }


                    }
                    break;
                case VOID:
                    {
                    int LA9_5 = input.LA(2);

                    if ( (synpred17_JavaParser()) ) {
                        alt9=3;
                    }
                    else if ( (synpred18_JavaParser()) ) {
                        alt9=4;
                    }


                    }
                    break;

                }

                switch (alt9) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:71:5: class_def
            	    {
            	    pushFollow(FOLLOW_class_def_in_class_block256);
            	    class_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 2 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:71:17: constructor_def
            	    {
            	    pushFollow(FOLLOW_constructor_def_in_class_block260);
            	    constructor_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 3 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:71:35: method_def
            	    {
            	    pushFollow(FOLLOW_method_def_in_class_block264);
            	    method_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 4 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:71:48: field_def
            	    {
            	    pushFollow(FOLLOW_field_def_in_class_block268);
            	    field_def();

            	    state._fsp--;
            	    if (state.failed) return ;

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
    // $ANTLR end "class_block"

    public static class constructor_def_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "constructor_def"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:74:1: constructor_def : modifiers ID OPEN_BRACKET argument_def CLOSE_BRACKET block_begin code_block block_end ;
    public final JavaParser.constructor_def_return constructor_def() throws RecognitionException {
        JavaParser.constructor_def_return retval = new JavaParser.constructor_def_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:75:2: ( modifiers ID OPEN_BRACKET argument_def CLOSE_BRACKET block_begin code_block block_end )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:75:4: modifiers ID OPEN_BRACKET argument_def CLOSE_BRACKET block_begin code_block block_end
            {
            pushFollow(FOLLOW_modifiers_in_constructor_def282);
            modifiers();

            state._fsp--;
            if (state.failed) return retval;
            match(input,ID,FOLLOW_ID_in_constructor_def284); if (state.failed) return retval;
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_constructor_def286); if (state.failed) return retval;
            pushFollow(FOLLOW_argument_def_in_constructor_def288);
            argument_def();

            state._fsp--;
            if (state.failed) return retval;
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_constructor_def290); if (state.failed) return retval;
            pushFollow(FOLLOW_block_begin_in_constructor_def292);
            block_begin();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_code_block_in_constructor_def294);
            code_block();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_block_end_in_constructor_def296);
            block_end();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              System.out.println("constructor_def: " + input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "constructor_def"

    public static class method_def_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "method_def"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:78:1: method_def : modifiers variable_type ID OPEN_BRACKET argument_def CLOSE_BRACKET block_begin code_block block_end ;
    public final JavaParser.method_def_return method_def() throws RecognitionException {
        JavaParser.method_def_return retval = new JavaParser.method_def_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:79:2: ( modifiers variable_type ID OPEN_BRACKET argument_def CLOSE_BRACKET block_begin code_block block_end )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:79:4: modifiers variable_type ID OPEN_BRACKET argument_def CLOSE_BRACKET block_begin code_block block_end
            {
            pushFollow(FOLLOW_modifiers_in_method_def310);
            modifiers();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_variable_type_in_method_def312);
            variable_type();

            state._fsp--;
            if (state.failed) return retval;
            match(input,ID,FOLLOW_ID_in_method_def314); if (state.failed) return retval;
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_method_def316); if (state.failed) return retval;
            pushFollow(FOLLOW_argument_def_in_method_def318);
            argument_def();

            state._fsp--;
            if (state.failed) return retval;
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_method_def320); if (state.failed) return retval;
            pushFollow(FOLLOW_block_begin_in_method_def322);
            block_begin();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_code_block_in_method_def324);
            code_block();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_block_end_in_method_def326);
            block_end();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              System.out.println("method_def: " + input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "method_def"

    public static class method_call_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "method_call"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:82:1: method_call : method_name OPEN_BRACKET arguments CLOSE_BRACKET ;
    public final JavaParser.method_call_return method_call() throws RecognitionException {
        JavaParser.method_call_return retval = new JavaParser.method_call_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:83:2: ( method_name OPEN_BRACKET arguments CLOSE_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:83:4: method_name OPEN_BRACKET arguments CLOSE_BRACKET
            {
            pushFollow(FOLLOW_method_name_in_method_call340);
            method_name();

            state._fsp--;
            if (state.failed) return retval;
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_method_call342); if (state.failed) return retval;
            pushFollow(FOLLOW_arguments_in_method_call344);
            arguments();

            state._fsp--;
            if (state.failed) return retval;
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_method_call346); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              System.out.println(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "method_call"

    public static class field_def_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "field_def"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:86:1: field_def : modifiers variable_type variable_name ( ASSIGN value )? SEMICOLON ;
    public final JavaParser.field_def_return field_def() throws RecognitionException {
        JavaParser.field_def_return retval = new JavaParser.field_def_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:87:2: ( modifiers variable_type variable_name ( ASSIGN value )? SEMICOLON )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:87:4: modifiers variable_type variable_name ( ASSIGN value )? SEMICOLON
            {
            pushFollow(FOLLOW_modifiers_in_field_def359);
            modifiers();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_variable_type_in_field_def361);
            variable_type();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_variable_name_in_field_def363);
            variable_name();

            state._fsp--;
            if (state.failed) return retval;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:87:42: ( ASSIGN value )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==ASSIGN) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:87:43: ASSIGN value
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_field_def366); if (state.failed) return retval;
                    pushFollow(FOLLOW_value_in_field_def368);
                    value();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;

            }

            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_field_def372); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              System.out.println("field_def " + input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "field_def"


    // $ANTLR start "argument_def"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:91:1: argument_def : ( variable_type variable_name ( COMMA variable_type variable_name )* )? ;
    public final void argument_def() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:92:2: ( ( variable_type variable_name ( COMMA variable_type variable_name )* )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:92:4: ( variable_type variable_name ( COMMA variable_type variable_name )* )?
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:92:4: ( variable_type variable_name ( COMMA variable_type variable_name )* )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==VOID||LA12_0==ID) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:92:5: variable_type variable_name ( COMMA variable_type variable_name )*
                    {
                    pushFollow(FOLLOW_variable_type_in_argument_def387);
                    variable_type();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_variable_name_in_argument_def389);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:92:33: ( COMMA variable_type variable_name )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==COMMA) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:92:34: COMMA variable_type variable_name
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_argument_def392); if (state.failed) return ;
                    	    pushFollow(FOLLOW_variable_type_in_argument_def394);
                    	    variable_type();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_variable_name_in_argument_def396);
                    	    variable_name();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);


                    }
                    break;

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
    // $ANTLR end "argument_def"

    public static class variable_def_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "variable_def"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:94:1: variable_def : variable_type variable_name ( ASSIGN value )? ;
    public final JavaParser.variable_def_return variable_def() throws RecognitionException {
        JavaParser.variable_def_return retval = new JavaParser.variable_def_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:95:2: ( variable_type variable_name ( ASSIGN value )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:95:4: variable_type variable_name ( ASSIGN value )?
            {
            pushFollow(FOLLOW_variable_type_in_variable_def410);
            variable_type();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_variable_name_in_variable_def412);
            variable_name();

            state._fsp--;
            if (state.failed) return retval;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:95:32: ( ASSIGN value )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==ASSIGN) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:95:33: ASSIGN value
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_variable_def415); if (state.failed) return retval;
                    pushFollow(FOLLOW_value_in_variable_def417);
                    value();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              System.out.println("variable_def " + input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "variable_def"

    public static class value_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "value"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:99:1: value : ( constant | class_name DOT CLASS | variable_name | method_call | new_class );
    public final JavaParser.value_return value() throws RecognitionException {
        JavaParser.value_return retval = new JavaParser.value_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:99:7: ( constant | class_name DOT CLASS | variable_name | method_call | new_class )
            int alt14=5;
            alt14 = dfa14.predict(input);
            switch (alt14) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:99:9: constant
                    {
                    pushFollow(FOLLOW_constant_in_value432);
                    constant();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      System.out.println("constant " + input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:100:4: class_name DOT CLASS
                    {
                    pushFollow(FOLLOW_class_name_in_value440);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    match(input,DOT,FOLLOW_DOT_in_value442); if (state.failed) return retval;
                    match(input,CLASS,FOLLOW_CLASS_in_value444); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      System.out.println(".class " + input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:101:4: variable_name
                    {
                    pushFollow(FOLLOW_variable_name_in_value451);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      System.out.println("variable_name " + input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:102:4: method_call
                    {
                    pushFollow(FOLLOW_method_call_in_value459);
                    method_call();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      System.out.println("method_call " + input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 5 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:103:4: new_class
                    {
                    pushFollow(FOLLOW_new_class_in_value467);
                    new_class();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      System.out.println("new_class " + input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;

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
    // $ANTLR end "value"


    // $ANTLR start "constant"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:106:1: constant : ( INT | STRING | FLOAT | CHAR | NULL | BOOLEAN );
    public final void constant() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:106:9: ( INT | STRING | FLOAT | CHAR | NULL | BOOLEAN )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:
            {
            if ( input.LA(1)==NULL||input.LA(1)==BOOLEAN||input.LA(1)==INT||input.LA(1)==FLOAT||(input.LA(1)>=STRING && input.LA(1)<=CHAR) ) {
                input.consume();
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
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
    // $ANTLR end "constant"


    // $ANTLR start "new_class"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:114:1: new_class : NEW class_name OPEN_BRACKET arguments CLOSE_BRACKET ;
    public final void new_class() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:115:2: ( NEW class_name OPEN_BRACKET arguments CLOSE_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:115:4: NEW class_name OPEN_BRACKET arguments CLOSE_BRACKET
            {
            match(input,NEW,FOLLOW_NEW_in_new_class516); if (state.failed) return ;
            pushFollow(FOLLOW_class_name_in_new_class518);
            class_name();

            state._fsp--;
            if (state.failed) return ;
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_new_class520); if (state.failed) return ;
            pushFollow(FOLLOW_arguments_in_new_class522);
            arguments();

            state._fsp--;
            if (state.failed) return ;
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_new_class524); if (state.failed) return ;

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
    // $ANTLR end "new_class"


    // $ANTLR start "arguments"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:118:1: arguments : ( value ( COMMA value )* )? ;
    public final void arguments() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:2: ( ( value ( COMMA value )* )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:4: ( value ( COMMA value )* )?
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:4: ( value ( COMMA value )* )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==NEW||LA16_0==NULL||(LA16_0>=BOOLEAN && LA16_0<=INT)||LA16_0==FLOAT||(LA16_0>=STRING && LA16_0<=CHAR)) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:5: value ( COMMA value )*
                    {
                    pushFollow(FOLLOW_value_in_arguments537);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:11: ( COMMA value )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==COMMA) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:12: COMMA value
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_arguments540); if (state.failed) return ;
                    	    pushFollow(FOLLOW_value_in_arguments542);
                    	    value();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);


                    }
                    break;

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
    // $ANTLR end "arguments"


    // $ANTLR start "code_block"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:122:1: code_block : ( statement )* ;
    public final void code_block() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:123:2: ( ( statement )* )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:123:4: ( statement )*
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:123:4: ( statement )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==VOID||(LA17_0>=THIS && LA17_0<=SUPER)||LA17_0==TRY||LA17_0==ID) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_code_block558);
            	    statement();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop17;
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
    // $ANTLR end "code_block"


    // $ANTLR start "statement"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:126:1: statement : ( variable_assignment SEMICOLON | variable_def SEMICOLON | method_call SEMICOLON | try_catch );
    public final void statement() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:127:2: ( variable_assignment SEMICOLON | variable_def SEMICOLON | method_call SEMICOLON | try_catch )
            int alt18=4;
            switch ( input.LA(1) ) {
            case THIS:
            case SUPER:
                {
                alt18=1;
                }
                break;
            case ID:
                {
                int LA18_3 = input.LA(2);

                if ( (synpred35_JavaParser()) ) {
                    alt18=1;
                }
                else if ( (synpred36_JavaParser()) ) {
                    alt18=2;
                }
                else if ( (synpred37_JavaParser()) ) {
                    alt18=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 18, 3, input);

                    throw nvae;
                }
                }
                break;
            case VOID:
                {
                alt18=2;
                }
                break;
            case TRY:
                {
                alt18=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:127:4: variable_assignment SEMICOLON
                    {
                    pushFollow(FOLLOW_variable_assignment_in_statement571);
                    variable_assignment();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_statement573); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:128:4: variable_def SEMICOLON
                    {
                    pushFollow(FOLLOW_variable_def_in_statement578);
                    variable_def();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_statement580); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:129:4: method_call SEMICOLON
                    {
                    pushFollow(FOLLOW_method_call_in_statement585);
                    method_call();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_statement587); if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:130:4: try_catch
                    {
                    pushFollow(FOLLOW_try_catch_in_statement592);
                    try_catch();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

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
    // $ANTLR end "statement"


    // $ANTLR start "variable_assignment"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:133:1: variable_assignment : ( THIS DOT | SUPER DOT )? variable_name ASSIGN value ;
    public final void variable_assignment() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:134:2: ( ( THIS DOT | SUPER DOT )? variable_name ASSIGN value )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:134:4: ( THIS DOT | SUPER DOT )? variable_name ASSIGN value
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:134:4: ( THIS DOT | SUPER DOT )?
            int alt19=3;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==THIS) ) {
                alt19=1;
            }
            else if ( (LA19_0==SUPER) ) {
                alt19=2;
            }
            switch (alt19) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:134:5: THIS DOT
                    {
                    match(input,THIS,FOLLOW_THIS_in_variable_assignment605); if (state.failed) return ;
                    match(input,DOT,FOLLOW_DOT_in_variable_assignment607); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:134:16: SUPER DOT
                    {
                    match(input,SUPER,FOLLOW_SUPER_in_variable_assignment611); if (state.failed) return ;
                    match(input,DOT,FOLLOW_DOT_in_variable_assignment613); if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_variable_name_in_variable_assignment617);
            variable_name();

            state._fsp--;
            if (state.failed) return ;
            match(input,ASSIGN,FOLLOW_ASSIGN_in_variable_assignment619); if (state.failed) return ;
            pushFollow(FOLLOW_value_in_variable_assignment621);
            value();

            state._fsp--;
            if (state.failed) return ;

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
    // $ANTLR end "variable_assignment"


    // $ANTLR start "try_catch"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:137:1: try_catch : TRY block_begin code_block block_end ( CATCH OPEN_BRACKET ID ID CLOSE_BRACKET block_begin code_block block_end )+ ( FINALLY OPEN_BRACKET ID ID CLOSE_BRACKET block_begin code_block block_end )? ;
    public final void try_catch() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:138:2: ( TRY block_begin code_block block_end ( CATCH OPEN_BRACKET ID ID CLOSE_BRACKET block_begin code_block block_end )+ ( FINALLY OPEN_BRACKET ID ID CLOSE_BRACKET block_begin code_block block_end )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:138:4: TRY block_begin code_block block_end ( CATCH OPEN_BRACKET ID ID CLOSE_BRACKET block_begin code_block block_end )+ ( FINALLY OPEN_BRACKET ID ID CLOSE_BRACKET block_begin code_block block_end )?
            {
            match(input,TRY,FOLLOW_TRY_in_try_catch633); if (state.failed) return ;
            pushFollow(FOLLOW_block_begin_in_try_catch635);
            block_begin();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_code_block_in_try_catch637);
            code_block();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_block_end_in_try_catch639);
            block_end();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:138:41: ( CATCH OPEN_BRACKET ID ID CLOSE_BRACKET block_begin code_block block_end )+
            int cnt20=0;
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==CATCH) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:138:42: CATCH OPEN_BRACKET ID ID CLOSE_BRACKET block_begin code_block block_end
            	    {
            	    match(input,CATCH,FOLLOW_CATCH_in_try_catch642); if (state.failed) return ;
            	    match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_try_catch644); if (state.failed) return ;
            	    match(input,ID,FOLLOW_ID_in_try_catch646); if (state.failed) return ;
            	    match(input,ID,FOLLOW_ID_in_try_catch648); if (state.failed) return ;
            	    match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_try_catch650); if (state.failed) return ;
            	    pushFollow(FOLLOW_block_begin_in_try_catch652);
            	    block_begin();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_code_block_in_try_catch654);
            	    code_block();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_block_end_in_try_catch656);
            	    block_end();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt20 >= 1 ) break loop20;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(20, input);
                        throw eee;
                }
                cnt20++;
            } while (true);

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:138:116: ( FINALLY OPEN_BRACKET ID ID CLOSE_BRACKET block_begin code_block block_end )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==FINALLY) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:138:117: FINALLY OPEN_BRACKET ID ID CLOSE_BRACKET block_begin code_block block_end
                    {
                    match(input,FINALLY,FOLLOW_FINALLY_in_try_catch661); if (state.failed) return ;
                    match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_try_catch663); if (state.failed) return ;
                    match(input,ID,FOLLOW_ID_in_try_catch665); if (state.failed) return ;
                    match(input,ID,FOLLOW_ID_in_try_catch667); if (state.failed) return ;
                    match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_try_catch669); if (state.failed) return ;
                    pushFollow(FOLLOW_block_begin_in_try_catch671);
                    block_begin();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_code_block_in_try_catch673);
                    code_block();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_block_end_in_try_catch675);
                    block_end();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

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
    // $ANTLR end "try_catch"


    // $ANTLR start "class_name"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:141:1: fragment class_name : ( ID DOT )* ID ;
    public final void class_name() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:143:2: ( ( ID DOT )* ID )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:143:4: ( ID DOT )* ID
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:143:4: ( ID DOT )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==ID) ) {
                    int LA22_1 = input.LA(2);

                    if ( (LA22_1==DOT) ) {
                        int LA22_2 = input.LA(3);

                        if ( (LA22_2==ID) ) {
                            alt22=1;
                        }


                    }


                }


                switch (alt22) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:143:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_class_name691); if (state.failed) return ;
            	    match(input,DOT,FOLLOW_DOT_in_class_name693); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_class_name697); if (state.failed) return ;

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


    // $ANTLR start "method_name"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:146:1: fragment method_name : ( ID DOT )* ID ;
    public final void method_name() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:148:2: ( ( ID DOT )* ID )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:148:4: ( ID DOT )* ID
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:148:4: ( ID DOT )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==ID) ) {
                    int LA23_1 = input.LA(2);

                    if ( (LA23_1==DOT) ) {
                        alt23=1;
                    }


                }


                switch (alt23) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:148:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_method_name711); if (state.failed) return ;
            	    match(input,DOT,FOLLOW_DOT_in_method_name713); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_method_name717); if (state.failed) return ;

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
    // $ANTLR end "method_name"


    // $ANTLR start "variable_name"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:152:1: fragment variable_name : ( ID DOT )* ID ( array )? ;
    public final void variable_name() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:154:2: ( ( ID DOT )* ID ( array )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:154:4: ( ID DOT )* ID ( array )?
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:154:4: ( ID DOT )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==ID) ) {
                    int LA24_1 = input.LA(2);

                    if ( (LA24_1==DOT) ) {
                        alt24=1;
                    }


                }


                switch (alt24) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:154:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_variable_name732); if (state.failed) return ;
            	    match(input,DOT,FOLLOW_DOT_in_variable_name734); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_variable_name738); if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:154:17: ( array )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==OPEN_RECT_BRACKET) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: array
                    {
                    pushFollow(FOLLOW_array_in_variable_name740);
                    array();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

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
    // $ANTLR end "variable_name"


    // $ANTLR start "variable_type"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:157:1: fragment variable_type : ( class_name ( array )? | VOID );
    public final void variable_type() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:159:2: ( class_name ( array )? | VOID )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==ID) ) {
                alt27=1;
            }
            else if ( (LA27_0==VOID) ) {
                alt27=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:159:4: class_name ( array )?
                    {
                    pushFollow(FOLLOW_class_name_in_variable_type754);
                    class_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:159:15: ( array )?
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==OPEN_RECT_BRACKET) ) {
                        alt26=1;
                    }
                    switch (alt26) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: array
                            {
                            pushFollow(FOLLOW_array_in_variable_type756);
                            array();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:160:4: VOID
                    {
                    match(input,VOID,FOLLOW_VOID_in_variable_type762); if (state.failed) return ;

                    }
                    break;

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
    // $ANTLR end "variable_type"


    // $ANTLR start "array"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:163:1: fragment array : OPEN_RECT_BRACKET ( value )? CLOSE_RECT_BRACKET ;
    public final void array() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:164:7: ( OPEN_RECT_BRACKET ( value )? CLOSE_RECT_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:164:9: OPEN_RECT_BRACKET ( value )? CLOSE_RECT_BRACKET
            {
            match(input,OPEN_RECT_BRACKET,FOLLOW_OPEN_RECT_BRACKET_in_array775); if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:164:27: ( value )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==NEW||LA28_0==NULL||(LA28_0>=BOOLEAN && LA28_0<=INT)||LA28_0==FLOAT||(LA28_0>=STRING && LA28_0<=CHAR)) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: value
                    {
                    pushFollow(FOLLOW_value_in_array777);
                    value();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            match(input,CLOSE_RECT_BRACKET,FOLLOW_CLOSE_RECT_BRACKET_in_array780); if (state.failed) return ;

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
    // $ANTLR end "array"

    // $ANTLR start synpred15_JavaParser
    public final void synpred15_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:71:5: ( class_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:71:5: class_def
        {
        pushFollow(FOLLOW_class_def_in_synpred15_JavaParser256);
        class_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred15_JavaParser

    // $ANTLR start synpred16_JavaParser
    public final void synpred16_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:71:17: ( constructor_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:71:17: constructor_def
        {
        pushFollow(FOLLOW_constructor_def_in_synpred16_JavaParser260);
        constructor_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred16_JavaParser

    // $ANTLR start synpred17_JavaParser
    public final void synpred17_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:71:35: ( method_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:71:35: method_def
        {
        pushFollow(FOLLOW_method_def_in_synpred17_JavaParser264);
        method_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred17_JavaParser

    // $ANTLR start synpred18_JavaParser
    public final void synpred18_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:71:48: ( field_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:71:48: field_def
        {
        pushFollow(FOLLOW_field_def_in_synpred18_JavaParser268);
        field_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred18_JavaParser

    // $ANTLR start synpred35_JavaParser
    public final void synpred35_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:127:4: ( variable_assignment SEMICOLON )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:127:4: variable_assignment SEMICOLON
        {
        pushFollow(FOLLOW_variable_assignment_in_synpred35_JavaParser571);
        variable_assignment();

        state._fsp--;
        if (state.failed) return ;
        match(input,SEMICOLON,FOLLOW_SEMICOLON_in_synpred35_JavaParser573); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred35_JavaParser

    // $ANTLR start synpred36_JavaParser
    public final void synpred36_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:128:4: ( variable_def SEMICOLON )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:128:4: variable_def SEMICOLON
        {
        pushFollow(FOLLOW_variable_def_in_synpred36_JavaParser578);
        variable_def();

        state._fsp--;
        if (state.failed) return ;
        match(input,SEMICOLON,FOLLOW_SEMICOLON_in_synpred36_JavaParser580); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred36_JavaParser

    // $ANTLR start synpred37_JavaParser
    public final void synpred37_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:129:4: ( method_call SEMICOLON )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:129:4: method_call SEMICOLON
        {
        pushFollow(FOLLOW_method_call_in_synpred37_JavaParser585);
        method_call();

        state._fsp--;
        if (state.failed) return ;
        match(input,SEMICOLON,FOLLOW_SEMICOLON_in_synpred37_JavaParser587); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred37_JavaParser

    // Delegated rules

    public final boolean synpred35_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred35_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred18_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred18_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred36_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred36_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred37_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred37_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred15_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred15_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred17_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred17_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred16_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred16_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA14 dfa14 = new DFA14(this);
    static final String DFA14_eotS =
        "\10\uffff";
    static final String DFA14_eofS =
        "\2\uffff\1\6\5\uffff";
    static final String DFA14_minS =
        "\1\12\1\uffff\1\57\1\uffff\1\6\3\uffff";
    static final String DFA14_maxS =
        "\1\103\1\uffff\1\70\1\uffff\1\72\3\uffff";
    static final String DFA14_acceptS =
        "\1\uffff\1\1\1\uffff\1\5\1\uffff\1\4\1\3\1\2";
    static final String DFA14_specialS =
        "\10\uffff}>";
    static final String[] DFA14_transitionS = {
            "\1\3\1\uffff\1\1\54\uffff\1\1\1\2\1\1\1\uffff\1\1\4\uffff\2"+
            "\1",
            "",
            "\1\4\1\6\2\uffff\2\6\1\5\1\6\1\uffff\1\6",
            "",
            "\1\7\63\uffff\1\2",
            "",
            "",
            ""
    };

    static final short[] DFA14_eot = DFA.unpackEncodedString(DFA14_eotS);
    static final short[] DFA14_eof = DFA.unpackEncodedString(DFA14_eofS);
    static final char[] DFA14_min = DFA.unpackEncodedStringToUnsignedChars(DFA14_minS);
    static final char[] DFA14_max = DFA.unpackEncodedStringToUnsignedChars(DFA14_maxS);
    static final short[] DFA14_accept = DFA.unpackEncodedString(DFA14_acceptS);
    static final short[] DFA14_special = DFA.unpackEncodedString(DFA14_specialS);
    static final short[][] DFA14_transition;

    static {
        int numStates = DFA14_transitionS.length;
        DFA14_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA14_transition[i] = DFA.unpackEncodedString(DFA14_transitionS[i]);
        }
    }

    class DFA14 extends DFA {

        public DFA14(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 14;
            this.eot = DFA14_eot;
            this.eof = DFA14_eof;
            this.min = DFA14_min;
            this.max = DFA14_max;
            this.accept = DFA14_accept;
            this.special = DFA14_special;
            this.transition = DFA14_transition;
        }
        public String getDescription() {
            return "99:1: value : ( constant | class_name DOT CLASS | variable_name | method_call | new_class );";
        }
    }
 

    public static final BitSet FOLLOW_package_def_in_file39 = new BitSet(new long[]{0x000000007E000060L});
    public static final BitSet FOLLOW_imports_in_file41 = new BitSet(new long[]{0x000000007E000042L});
    public static final BitSet FOLLOW_class_def_in_file43 = new BitSet(new long[]{0x000000007E000042L});
    public static final BitSet FOLLOW_PACKAGE_in_package_def54 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_package_name_in_package_def56 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_package_def58 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_package_name74 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_DOT_in_package_name76 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_ID_in_package_name80 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_import_def_in_imports90 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_IMPORT_in_import_def100 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_import_name_in_import_def102 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_import_def104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_import_name120 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_DOT_in_import_name122 = new BitSet(new long[]{0x0400001000000000L});
    public static final BitSet FOLLOW_set_in_import_name126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_class_def142 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_CLASS_in_class_def144 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_ID_in_class_def146 = new BitSet(new long[]{0x0000000080000180L});
    public static final BitSet FOLLOW_EXTENDS_in_class_def149 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_class_name_in_class_def151 = new BitSet(new long[]{0x0000000080000180L});
    public static final BitSet FOLLOW_IMPLEMENTS_in_class_def156 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_class_name_in_class_def158 = new BitSet(new long[]{0x0001000080000180L});
    public static final BitSet FOLLOW_COMMA_in_class_def161 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_class_name_in_class_def163 = new BitSet(new long[]{0x0001000080000180L});
    public static final BitSet FOLLOW_block_begin_in_class_def169 = new BitSet(new long[]{0x040000017E000240L});
    public static final BitSet FOLLOW_class_block_in_class_def171 = new BitSet(new long[]{0x040000017E000240L});
    public static final BitSet FOLLOW_block_end_in_class_def173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifier_in_modifiers186 = new BitSet(new long[]{0x000000007E000002L});
    public static final BitSet FOLLOW_set_in_modifier0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_CURLY_BRACKET_in_block_begin232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLOSE_CURLY_BRACKET_in_block_end244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_def_in_class_block256 = new BitSet(new long[]{0x040000007E000242L});
    public static final BitSet FOLLOW_constructor_def_in_class_block260 = new BitSet(new long[]{0x040000007E000242L});
    public static final BitSet FOLLOW_method_def_in_class_block264 = new BitSet(new long[]{0x040000007E000242L});
    public static final BitSet FOLLOW_field_def_in_class_block268 = new BitSet(new long[]{0x040000007E000242L});
    public static final BitSet FOLLOW_modifiers_in_constructor_def282 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_ID_in_constructor_def284 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_constructor_def286 = new BitSet(new long[]{0x044000007E000200L});
    public static final BitSet FOLLOW_argument_def_in_constructor_def288 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_constructor_def290 = new BitSet(new long[]{0x0000000080000180L});
    public static final BitSet FOLLOW_block_begin_in_constructor_def292 = new BitSet(new long[]{0x040000017E406240L});
    public static final BitSet FOLLOW_code_block_in_constructor_def294 = new BitSet(new long[]{0x040000017E000240L});
    public static final BitSet FOLLOW_block_end_in_constructor_def296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_method_def310 = new BitSet(new long[]{0x040000007E000200L});
    public static final BitSet FOLLOW_variable_type_in_method_def312 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_ID_in_method_def314 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_method_def316 = new BitSet(new long[]{0x044000007E000200L});
    public static final BitSet FOLLOW_argument_def_in_method_def318 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_method_def320 = new BitSet(new long[]{0x0000000080000180L});
    public static final BitSet FOLLOW_block_begin_in_method_def322 = new BitSet(new long[]{0x040000017E406240L});
    public static final BitSet FOLLOW_code_block_in_method_def324 = new BitSet(new long[]{0x040000017E000240L});
    public static final BitSet FOLLOW_block_end_in_method_def326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_name_in_method_call340 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_method_call342 = new BitSet(new long[]{0x2E40000000007400L,0x000000000000000CL});
    public static final BitSet FOLLOW_arguments_in_method_call344 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_method_call346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_field_def359 = new BitSet(new long[]{0x040000007E000200L});
    public static final BitSet FOLLOW_variable_type_in_field_def361 = new BitSet(new long[]{0x0400000000006000L});
    public static final BitSet FOLLOW_variable_name_in_field_def363 = new BitSet(new long[]{0x0100008000000000L});
    public static final BitSet FOLLOW_ASSIGN_in_field_def366 = new BitSet(new long[]{0x2E00000000007400L,0x000000000000000CL});
    public static final BitSet FOLLOW_value_in_field_def368 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_field_def372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_type_in_argument_def387 = new BitSet(new long[]{0x0400000000006000L});
    public static final BitSet FOLLOW_variable_name_in_argument_def389 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_COMMA_in_argument_def392 = new BitSet(new long[]{0x040000007E000200L});
    public static final BitSet FOLLOW_variable_type_in_argument_def394 = new BitSet(new long[]{0x0400000000006000L});
    public static final BitSet FOLLOW_variable_name_in_argument_def396 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_variable_type_in_variable_def410 = new BitSet(new long[]{0x0400000000006000L});
    public static final BitSet FOLLOW_variable_name_in_variable_def412 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_variable_def415 = new BitSet(new long[]{0x2E00000000007400L,0x000000000000000CL});
    public static final BitSet FOLLOW_value_in_variable_def417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constant_in_value432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_name_in_value440 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_DOT_in_value442 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_CLASS_in_value444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_name_in_value451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_value459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_new_class_in_value467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_constant0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEW_in_new_class516 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_class_name_in_new_class518 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_new_class520 = new BitSet(new long[]{0x2E40000000007400L,0x000000000000000CL});
    public static final BitSet FOLLOW_arguments_in_new_class522 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_new_class524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_value_in_arguments537 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_COMMA_in_arguments540 = new BitSet(new long[]{0x2E00000000007400L,0x000000000000000CL});
    public static final BitSet FOLLOW_value_in_arguments542 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_statement_in_code_block558 = new BitSet(new long[]{0x040000007E406202L});
    public static final BitSet FOLLOW_variable_assignment_in_statement571 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_statement573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_statement578 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_statement580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_statement585 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_statement587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_try_catch_in_statement592 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_THIS_in_variable_assignment605 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_DOT_in_variable_assignment607 = new BitSet(new long[]{0x0400000000006000L});
    public static final BitSet FOLLOW_SUPER_in_variable_assignment611 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_DOT_in_variable_assignment613 = new BitSet(new long[]{0x0400000000006000L});
    public static final BitSet FOLLOW_variable_name_in_variable_assignment617 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_ASSIGN_in_variable_assignment619 = new BitSet(new long[]{0x2E00000000007400L,0x000000000000000CL});
    public static final BitSet FOLLOW_value_in_variable_assignment621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRY_in_try_catch633 = new BitSet(new long[]{0x0000000080000180L});
    public static final BitSet FOLLOW_block_begin_in_try_catch635 = new BitSet(new long[]{0x040000017E406240L});
    public static final BitSet FOLLOW_code_block_in_try_catch637 = new BitSet(new long[]{0x040000017E000240L});
    public static final BitSet FOLLOW_block_end_in_try_catch639 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_CATCH_in_try_catch642 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_try_catch644 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_ID_in_try_catch646 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_ID_in_try_catch648 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_try_catch650 = new BitSet(new long[]{0x0000000080000180L});
    public static final BitSet FOLLOW_block_begin_in_try_catch652 = new BitSet(new long[]{0x040000017E406240L});
    public static final BitSet FOLLOW_code_block_in_try_catch654 = new BitSet(new long[]{0x040000017E000240L});
    public static final BitSet FOLLOW_block_end_in_try_catch656 = new BitSet(new long[]{0x0000000001800002L});
    public static final BitSet FOLLOW_FINALLY_in_try_catch661 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_try_catch663 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_ID_in_try_catch665 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_ID_in_try_catch667 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_try_catch669 = new BitSet(new long[]{0x0000000080000180L});
    public static final BitSet FOLLOW_block_begin_in_try_catch671 = new BitSet(new long[]{0x040000017E406240L});
    public static final BitSet FOLLOW_code_block_in_try_catch673 = new BitSet(new long[]{0x040000017E000240L});
    public static final BitSet FOLLOW_block_end_in_try_catch675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_class_name691 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_DOT_in_class_name693 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_ID_in_class_name697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_method_name711 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_DOT_in_method_name713 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_ID_in_method_name717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_variable_name732 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_DOT_in_variable_name734 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_ID_in_variable_name738 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_array_in_variable_name740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_name_in_variable_type754 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_array_in_variable_type756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOID_in_variable_type762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_RECT_BRACKET_in_array775 = new BitSet(new long[]{0x2E10000000007400L,0x000000000000000CL});
    public static final BitSet FOLLOW_value_in_array777 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_CLOSE_RECT_BRACKET_in_array780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_def_in_synpred15_JavaParser256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constructor_def_in_synpred16_JavaParser260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_def_in_synpred17_JavaParser264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_field_def_in_synpred18_JavaParser268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_synpred35_JavaParser571 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_synpred35_JavaParser573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_synpred36_JavaParser578 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_synpred36_JavaParser580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_synpred37_JavaParser585 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_synpred37_JavaParser587 = new BitSet(new long[]{0x0000000000000002L});

}