// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g 2009-12-08 18:22:13

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


    private JavaParserHelper helper = new JavaParserHelper(this);

    public JavaParserHelper getJavaParserHelper() {
    	return helper;
    }



    // $ANTLR start "file"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:22:1: file : package_def imports ( class_def )* ;
    public final void file() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:22:6: ( package_def imports ( class_def )* )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:22:8: package_def imports ( class_def )*
            {
            pushFollow(FOLLOW_package_def_in_file39);
            package_def();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_imports_in_file41);
            imports();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:22:28: ( class_def )*
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:1: package_def : package_ package_name semicolon ;
    public final void package_def() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:25:2: ( package_ package_name semicolon )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:25:4: package_ package_name semicolon
            {
            pushFollow(FOLLOW_package__in_package_def54);
            package_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_package_name_in_package_def56);
            package_name();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_semicolon_in_package_def58);
            semicolon();

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
    // $ANTLR end "package_def"


    // $ANTLR start "imports"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:28:1: imports : ( import_def )* ;
    public final void imports() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:28:9: ( ( import_def )* )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:28:11: ( import_def )*
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:28:11: ( import_def )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==IMPORT) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: import_def
            	    {
            	    pushFollow(FOLLOW_import_def_in_imports69);
            	    import_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop2;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:30:1: import_def : import_ import_name semicolon ;
    public final void import_def() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:31:2: ( import_ import_name semicolon )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:31:4: import_ import_name semicolon
            {
            pushFollow(FOLLOW_import__in_import_def79);
            import_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_import_name_in_import_def81);
            import_name();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_semicolon_in_import_def83);
            semicolon();

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
    // $ANTLR end "import_def"

    public static class class_def_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "class_def"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:36:1: class_def : modifiers class_ id ( extends_ class_name )? ( implements_ class_name ( comma class_name )* )? block_begin class_block block_end ;
    public final JavaParser.class_def_return class_def() throws RecognitionException {
        JavaParser.class_def_return retval = new JavaParser.class_def_return();
        retval.start = input.LT(1);

        JavaParser.id_return id1 = null;


        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:37:2: ( modifiers class_ id ( extends_ class_name )? ( implements_ class_name ( comma class_name )* )? block_begin class_block block_end )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:37:4: modifiers class_ id ( extends_ class_name )? ( implements_ class_name ( comma class_name )* )? block_begin class_block block_end
            {
            pushFollow(FOLLOW_modifiers_in_class_def97);
            modifiers();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_class__in_class_def99);
            class_();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_id_in_class_def101);
            id1=id();

            state._fsp--;
            if (state.failed) return retval;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:37:24: ( extends_ class_name )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==EXTENDS) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:37:25: extends_ class_name
                    {
                    pushFollow(FOLLOW_extends__in_class_def104);
                    extends_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_class_def106);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;

            }

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:37:47: ( implements_ class_name ( comma class_name )* )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==IMPLEMENTS) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:37:48: implements_ class_name ( comma class_name )*
                    {
                    pushFollow(FOLLOW_implements__in_class_def111);
                    implements_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_class_def113);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:37:71: ( comma class_name )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==COMMA) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:37:72: comma class_name
                    	    {
                    	    pushFollow(FOLLOW_comma_in_class_def116);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    pushFollow(FOLLOW_class_name_in_class_def118);
                    	    class_name();

                    	    state._fsp--;
                    	    if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    }
                    break;

            }

            pushFollow(FOLLOW_block_begin_in_class_def124);
            block_begin();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_class_block_in_class_def126);
            class_block();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_block_end_in_class_def128);
            block_end();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerRange("class", (id1!=null?input.toString(id1.start,id1.stop):null), input.toString(retval.start,input.LT(-1)), retval.start.getTokenIndex(), input.LT(-1).getTokenIndex());
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:42:1: modifiers : ( modifier )* ;
    public final void modifiers() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:43:2: ( ( modifier )* )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:43:4: ( modifier )*
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:43:4: ( modifier )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>=PUBLIC && LA6_0<=TRANSIENT)) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: modifier
            	    {
            	    pushFollow(FOLLOW_modifier_in_modifiers145);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop6;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:46:1: modifier : ( public_ | private_ | protected_ | static_ | final_ | transient_ );
    public final void modifier() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:46:9: ( public_ | private_ | protected_ | static_ | final_ | transient_ )
            int alt7=6;
            switch ( input.LA(1) ) {
            case PUBLIC:
                {
                alt7=1;
                }
                break;
            case PRIVATE:
                {
                alt7=2;
                }
                break;
            case PROTECTED:
                {
                alt7=3;
                }
                break;
            case STATIC:
                {
                alt7=4;
                }
                break;
            case FINAL:
                {
                alt7=5;
                }
                break;
            case TRANSIENT:
                {
                alt7=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:46:11: public_
                    {
                    pushFollow(FOLLOW_public__in_modifier155);
                    public_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:47:4: private_
                    {
                    pushFollow(FOLLOW_private__in_modifier160);
                    private_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:48:4: protected_
                    {
                    pushFollow(FOLLOW_protected__in_modifier165);
                    protected_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:49:4: static_
                    {
                    pushFollow(FOLLOW_static__in_modifier170);
                    static_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:50:4: final_
                    {
                    pushFollow(FOLLOW_final__in_modifier175);
                    final_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:51:4: transient_
                    {
                    pushFollow(FOLLOW_transient__in_modifier180);
                    transient_();

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
    // $ANTLR end "modifier"


    // $ANTLR start "class_block"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:55:1: class_block : ( class_def | constructor_def | method_def | field_def )* ;
    public final void class_block() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:56:2: ( ( class_def | constructor_def | method_def | field_def )* )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:56:4: ( class_def | constructor_def | method_def | field_def )*
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:56:4: ( class_def | constructor_def | method_def | field_def )*
            loop8:
            do {
                int alt8=5;
                alt8 = dfa8.predict(input);
                switch (alt8) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:56:5: class_def
            	    {
            	    pushFollow(FOLLOW_class_def_in_class_block193);
            	    class_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 2 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:56:17: constructor_def
            	    {
            	    pushFollow(FOLLOW_constructor_def_in_class_block197);
            	    constructor_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 3 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:56:35: method_def
            	    {
            	    pushFollow(FOLLOW_method_def_in_class_block201);
            	    method_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 4 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:56:48: field_def
            	    {
            	    pushFollow(FOLLOW_field_def_in_class_block205);
            	    field_def();

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
    // $ANTLR end "class_block"

    public static class constructor_def_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "constructor_def"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:59:1: constructor_def : modifiers id open_bracket argument_def close_bracket block_begin code_block block_end ;
    public final JavaParser.constructor_def_return constructor_def() throws RecognitionException {
        JavaParser.constructor_def_return retval = new JavaParser.constructor_def_return();
        retval.start = input.LT(1);

        JavaParser.id_return id2 = null;


        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:60:2: ( modifiers id open_bracket argument_def close_bracket block_begin code_block block_end )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:60:4: modifiers id open_bracket argument_def close_bracket block_begin code_block block_end
            {
            pushFollow(FOLLOW_modifiers_in_constructor_def219);
            modifiers();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_id_in_constructor_def221);
            id2=id();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_open_bracket_in_constructor_def223);
            open_bracket();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_argument_def_in_constructor_def225);
            argument_def();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_close_bracket_in_constructor_def227);
            close_bracket();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_block_begin_in_constructor_def229);
            block_begin();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_code_block_in_constructor_def231);
            code_block();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_block_end_in_constructor_def233);
            block_end();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerRange("constructor", (id2!=null?input.toString(id2.start,id2.stop):null), input.toString(retval.start,input.LT(-1)), retval.start.getTokenIndex(), input.LT(-1).getTokenIndex());
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:64:1: method_def : modifiers variable_type id open_bracket argument_def close_bracket block_begin code_block block_end ;
    public final JavaParser.method_def_return method_def() throws RecognitionException {
        JavaParser.method_def_return retval = new JavaParser.method_def_return();
        retval.start = input.LT(1);

        JavaParser.id_return id3 = null;


        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:65:2: ( modifiers variable_type id open_bracket argument_def close_bracket block_begin code_block block_end )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:65:4: modifiers variable_type id open_bracket argument_def close_bracket block_begin code_block block_end
            {
            pushFollow(FOLLOW_modifiers_in_method_def248);
            modifiers();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_variable_type_in_method_def250);
            variable_type();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_id_in_method_def252);
            id3=id();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_open_bracket_in_method_def254);
            open_bracket();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_argument_def_in_method_def256);
            argument_def();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_close_bracket_in_method_def258);
            close_bracket();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_block_begin_in_method_def260);
            block_begin();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_code_block_in_method_def262);
            code_block();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_block_end_in_method_def264);
            block_end();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerRange("method", (id3!=null?input.toString(id3.start,id3.stop):null), input.toString(retval.start,input.LT(-1)), retval.start.getTokenIndex(), input.LT(-1).getTokenIndex());
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


    // $ANTLR start "method_call"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:69:1: method_call : method_name open_bracket arguments close_bracket ( dot id open_bracket arguments close_bracket )* ;
    public final void method_call() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:2: ( method_name open_bracket arguments close_bracket ( dot id open_bracket arguments close_bracket )* )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:4: method_name open_bracket arguments close_bracket ( dot id open_bracket arguments close_bracket )*
            {
            pushFollow(FOLLOW_method_name_in_method_call281);
            method_name();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_open_bracket_in_method_call283);
            open_bracket();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_arguments_in_method_call285);
            arguments();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_close_bracket_in_method_call287);
            close_bracket();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:53: ( dot id open_bracket arguments close_bracket )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==DOT) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:54: dot id open_bracket arguments close_bracket
            	    {
            	    pushFollow(FOLLOW_dot_in_method_call290);
            	    dot();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_method_call292);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_open_bracket_in_method_call294);
            	    open_bracket();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_arguments_in_method_call296);
            	    arguments();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_close_bracket_in_method_call298);
            	    close_bracket();

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
    // $ANTLR end "method_call"


    // $ANTLR start "field_def"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:73:1: field_def : modifiers variable_type variable_name ( assign value )? semicolon ;
    public final void field_def() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:74:2: ( modifiers variable_type variable_name ( assign value )? semicolon )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:74:4: modifiers variable_type variable_name ( assign value )? semicolon
            {
            pushFollow(FOLLOW_modifiers_in_field_def311);
            modifiers();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_variable_type_in_field_def313);
            variable_type();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_variable_name_in_field_def315);
            variable_name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:74:42: ( assign value )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==ASSIGN) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:74:43: assign value
                    {
                    pushFollow(FOLLOW_assign_in_field_def318);
                    assign();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_field_def320);
                    value();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_semicolon_in_field_def324);
            semicolon();

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
    // $ANTLR end "field_def"


    // $ANTLR start "argument_def"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:78:1: argument_def : ( variable_type variable_name ( comma variable_type variable_name )* )? ;
    public final void argument_def() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:79:2: ( ( variable_type variable_name ( comma variable_type variable_name )* )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:79:4: ( variable_type variable_name ( comma variable_type variable_name )* )?
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:79:4: ( variable_type variable_name ( comma variable_type variable_name )* )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==VOID||LA12_0==ID) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:79:5: variable_type variable_name ( comma variable_type variable_name )*
                    {
                    pushFollow(FOLLOW_variable_type_in_argument_def337);
                    variable_type();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_variable_name_in_argument_def339);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:79:33: ( comma variable_type variable_name )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==COMMA) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:79:34: comma variable_type variable_name
                    	    {
                    	    pushFollow(FOLLOW_comma_in_argument_def342);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_variable_type_in_argument_def344);
                    	    variable_type();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_variable_name_in_argument_def346);
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


    // $ANTLR start "variable_def"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:81:1: variable_def : variable_type variable_name ( assign value )? ;
    public final void variable_def() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:82:2: ( variable_type variable_name ( assign value )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:82:4: variable_type variable_name ( assign value )?
            {
            pushFollow(FOLLOW_variable_type_in_variable_def360);
            variable_type();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_variable_name_in_variable_def362);
            variable_name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:82:32: ( assign value )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==ASSIGN) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:82:33: assign value
                    {
                    pushFollow(FOLLOW_assign_in_variable_def365);
                    assign();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_variable_def367);
                    value();

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
    // $ANTLR end "variable_def"


    // $ANTLR start "value"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:85:1: value : ( constant | class_name dot class_ | variable_name | method_call | new_class );
    public final void value() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:85:7: ( constant | class_name dot class_ | variable_name | method_call | new_class )
            int alt14=5;
            alt14 = dfa14.predict(input);
            switch (alt14) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:85:9: constant
                    {
                    pushFollow(FOLLOW_constant_in_value380);
                    constant();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:86:4: class_name dot class_
                    {
                    pushFollow(FOLLOW_class_name_in_value385);
                    class_name();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_dot_in_value387);
                    dot();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_class__in_value389);
                    class_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:87:4: variable_name
                    {
                    pushFollow(FOLLOW_variable_name_in_value394);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:88:4: method_call
                    {
                    pushFollow(FOLLOW_method_call_in_value399);
                    method_call();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:89:4: new_class
                    {
                    pushFollow(FOLLOW_new_class_in_value404);
                    new_class();

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
    // $ANTLR end "value"


    // $ANTLR start "constant"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:92:1: constant : ( int_const | string_const | float_const | char_const | null_const | boolean_const );
    public final void constant() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:92:9: ( int_const | string_const | float_const | char_const | null_const | boolean_const )
            int alt15=6;
            switch ( input.LA(1) ) {
            case INT:
                {
                alt15=1;
                }
                break;
            case STRING:
                {
                alt15=2;
                }
                break;
            case FLOAT:
                {
                alt15=3;
                }
                break;
            case CHAR:
                {
                alt15=4;
                }
                break;
            case NULL:
                {
                alt15=5;
                }
                break;
            case BOOLEAN:
                {
                alt15=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:92:11: int_const
                    {
                    pushFollow(FOLLOW_int_const_in_constant413);
                    int_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:93:4: string_const
                    {
                    pushFollow(FOLLOW_string_const_in_constant418);
                    string_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:94:4: float_const
                    {
                    pushFollow(FOLLOW_float_const_in_constant423);
                    float_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:95:4: char_const
                    {
                    pushFollow(FOLLOW_char_const_in_constant428);
                    char_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:96:4: null_const
                    {
                    pushFollow(FOLLOW_null_const_in_constant433);
                    null_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:97:4: boolean_const
                    {
                    pushFollow(FOLLOW_boolean_const_in_constant438);
                    boolean_const();

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
    // $ANTLR end "constant"


    // $ANTLR start "new_class"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:100:1: new_class : new_ class_name open_bracket arguments close_bracket ;
    public final void new_class() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:101:2: ( new_ class_name open_bracket arguments close_bracket )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:101:4: new_ class_name open_bracket arguments close_bracket
            {
            pushFollow(FOLLOW_new__in_new_class450);
            new_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_class_name_in_new_class452);
            class_name();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_open_bracket_in_new_class454);
            open_bracket();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_arguments_in_new_class456);
            arguments();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_close_bracket_in_new_class458);
            close_bracket();

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
    // $ANTLR end "new_class"


    // $ANTLR start "arguments"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:104:1: arguments : ( value ( comma value )* )? ;
    public final void arguments() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:105:2: ( ( value ( comma value )* )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:105:4: ( value ( comma value )* )?
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:105:4: ( value ( comma value )* )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==NEW||LA17_0==NULL||(LA17_0>=BOOLEAN && LA17_0<=INT)||LA17_0==FLOAT||(LA17_0>=STRING && LA17_0<=CHAR)) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:105:5: value ( comma value )*
                    {
                    pushFollow(FOLLOW_value_in_arguments471);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:105:11: ( comma value )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0==COMMA) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:105:12: comma value
                    	    {
                    	    pushFollow(FOLLOW_comma_in_arguments474);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_value_in_arguments476);
                    	    value();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop16;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:108:1: code_block : ( statement )* ;
    public final void code_block() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:109:2: ( ( statement )* )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:109:4: ( statement )*
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:109:4: ( statement )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==VOID||(LA18_0>=THIS && LA18_0<=SUPER)||LA18_0==TRY||LA18_0==ID) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_code_block492);
            	    statement();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop18;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:112:1: statement : ( variable_assignment semicolon | variable_def semicolon | method_call semicolon | try_catch );
    public final void statement() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:113:2: ( variable_assignment semicolon | variable_def semicolon | method_call semicolon | try_catch )
            int alt19=4;
            switch ( input.LA(1) ) {
            case THIS:
            case SUPER:
                {
                alt19=1;
                }
                break;
            case ID:
                {
                int LA19_3 = input.LA(2);

                if ( (synpred33_JavaParser()) ) {
                    alt19=1;
                }
                else if ( (synpred34_JavaParser()) ) {
                    alt19=2;
                }
                else if ( (synpred35_JavaParser()) ) {
                    alt19=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 19, 3, input);

                    throw nvae;
                }
                }
                break;
            case VOID:
                {
                alt19=2;
                }
                break;
            case TRY:
                {
                alt19=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }

            switch (alt19) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:113:4: variable_assignment semicolon
                    {
                    pushFollow(FOLLOW_variable_assignment_in_statement505);
                    variable_assignment();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement507);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:114:4: variable_def semicolon
                    {
                    pushFollow(FOLLOW_variable_def_in_statement512);
                    variable_def();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement514);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:115:4: method_call semicolon
                    {
                    pushFollow(FOLLOW_method_call_in_statement519);
                    method_call();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement521);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:116:4: try_catch
                    {
                    pushFollow(FOLLOW_try_catch_in_statement526);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:1: variable_assignment : ( this_ dot | super_ dot )? variable_name assign value ;
    public final void variable_assignment() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:120:2: ( ( this_ dot | super_ dot )? variable_name assign value )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:120:4: ( this_ dot | super_ dot )? variable_name assign value
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:120:4: ( this_ dot | super_ dot )?
            int alt20=3;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==THIS) ) {
                alt20=1;
            }
            else if ( (LA20_0==SUPER) ) {
                alt20=2;
            }
            switch (alt20) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:120:5: this_ dot
                    {
                    pushFollow(FOLLOW_this__in_variable_assignment539);
                    this_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_dot_in_variable_assignment541);
                    dot();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:120:17: super_ dot
                    {
                    pushFollow(FOLLOW_super__in_variable_assignment545);
                    super_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_dot_in_variable_assignment547);
                    dot();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_variable_name_in_variable_assignment551);
            variable_name();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_assign_in_variable_assignment553);
            assign();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_value_in_variable_assignment555);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:123:1: try_catch : try_ block_begin code_block block_end ( catch_ OPEN_BRACKET id id CLOSE_BRACKET block_begin code_block block_end )+ ( finally_ OPEN_BRACKET id id CLOSE_BRACKET block_begin code_block block_end )? ;
    public final void try_catch() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:124:2: ( try_ block_begin code_block block_end ( catch_ OPEN_BRACKET id id CLOSE_BRACKET block_begin code_block block_end )+ ( finally_ OPEN_BRACKET id id CLOSE_BRACKET block_begin code_block block_end )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:124:4: try_ block_begin code_block block_end ( catch_ OPEN_BRACKET id id CLOSE_BRACKET block_begin code_block block_end )+ ( finally_ OPEN_BRACKET id id CLOSE_BRACKET block_begin code_block block_end )?
            {
            pushFollow(FOLLOW_try__in_try_catch567);
            try_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_block_begin_in_try_catch569);
            block_begin();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_code_block_in_try_catch571);
            code_block();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_block_end_in_try_catch573);
            block_end();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:124:42: ( catch_ OPEN_BRACKET id id CLOSE_BRACKET block_begin code_block block_end )+
            int cnt21=0;
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==CATCH) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:124:43: catch_ OPEN_BRACKET id id CLOSE_BRACKET block_begin code_block block_end
            	    {
            	    pushFollow(FOLLOW_catch__in_try_catch576);
            	    catch_();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_try_catch578); if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_try_catch580);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_try_catch582);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_try_catch584); if (state.failed) return ;
            	    pushFollow(FOLLOW_block_begin_in_try_catch586);
            	    block_begin();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_code_block_in_try_catch588);
            	    code_block();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_block_end_in_try_catch590);
            	    block_end();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt21 >= 1 ) break loop21;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(21, input);
                        throw eee;
                }
                cnt21++;
            } while (true);

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:124:118: ( finally_ OPEN_BRACKET id id CLOSE_BRACKET block_begin code_block block_end )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==FINALLY) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:124:119: finally_ OPEN_BRACKET id id CLOSE_BRACKET block_begin code_block block_end
                    {
                    pushFollow(FOLLOW_finally__in_try_catch595);
                    finally_();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_try_catch597); if (state.failed) return ;
                    pushFollow(FOLLOW_id_in_try_catch599);
                    id();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_id_in_try_catch601);
                    id();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_try_catch603); if (state.failed) return ;
                    pushFollow(FOLLOW_block_begin_in_try_catch605);
                    block_begin();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_code_block_in_try_catch607);
                    code_block();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_block_end_in_try_catch609);
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


    // $ANTLR start "variable_type"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:128:1: variable_type : ( class_name ( array )? | void_ );
    public final void variable_type() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:129:2: ( class_name ( array )? | void_ )
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==ID) ) {
                alt24=1;
            }
            else if ( (LA24_0==VOID) ) {
                alt24=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }
            switch (alt24) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:129:4: class_name ( array )?
                    {
                    pushFollow(FOLLOW_class_name_in_variable_type623);
                    class_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:129:15: ( array )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==OPEN_RECT_BRACKET) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: array
                            {
                            pushFollow(FOLLOW_array_in_variable_type625);
                            array();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:130:4: void_
                    {
                    pushFollow(FOLLOW_void__in_variable_type631);
                    void_();

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
    // $ANTLR end "variable_type"

    public static class id_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "id"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:133:1: id : ID ;
    public final JavaParser.id_return id() throws RecognitionException {
        JavaParser.id_return retval = new JavaParser.id_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:133:4: ( ID )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:133:6: ID
            {
            match(input,ID,FOLLOW_ID_in_id641); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperant(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "id"

    public static class int_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "int_const"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:139:1: int_const : INT ;
    public final JavaParser.int_const_return int_const() throws RecognitionException {
        JavaParser.int_const_return retval = new JavaParser.int_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:140:2: ( INT )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:140:4: INT
            {
            match(input,INT,FOLLOW_INT_in_int_const656); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperant(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "int_const"

    public static class string_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "string_const"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:141:1: string_const : STRING ;
    public final JavaParser.string_const_return string_const() throws RecognitionException {
        JavaParser.string_const_return retval = new JavaParser.string_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:142:2: ( STRING )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:142:4: STRING
            {
            match(input,STRING,FOLLOW_STRING_in_string_const666); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperant(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "string_const"

    public static class float_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "float_const"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:143:1: float_const : FLOAT ;
    public final JavaParser.float_const_return float_const() throws RecognitionException {
        JavaParser.float_const_return retval = new JavaParser.float_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:144:2: ( FLOAT )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:144:4: FLOAT
            {
            match(input,FLOAT,FOLLOW_FLOAT_in_float_const676); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperant(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "float_const"

    public static class char_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "char_const"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:145:1: char_const : CHAR ;
    public final JavaParser.char_const_return char_const() throws RecognitionException {
        JavaParser.char_const_return retval = new JavaParser.char_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:146:2: ( CHAR )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:146:4: CHAR
            {
            match(input,CHAR,FOLLOW_CHAR_in_char_const686); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperant(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "char_const"

    public static class null_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "null_const"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:147:1: null_const : NULL ;
    public final JavaParser.null_const_return null_const() throws RecognitionException {
        JavaParser.null_const_return retval = new JavaParser.null_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:148:2: ( NULL )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:148:4: NULL
            {
            match(input,NULL,FOLLOW_NULL_in_null_const696); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperant(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "null_const"

    public static class boolean_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "boolean_const"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:149:1: boolean_const : BOOLEAN ;
    public final JavaParser.boolean_const_return boolean_const() throws RecognitionException {
        JavaParser.boolean_const_return retval = new JavaParser.boolean_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:150:2: ( BOOLEAN )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:150:4: BOOLEAN
            {
            match(input,BOOLEAN,FOLLOW_BOOLEAN_in_boolean_const707); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperant(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "boolean_const"

    public static class package_name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "package_name"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:156:1: package_name : ( ID DOT )* ID ;
    public final JavaParser.package_name_return package_name() throws RecognitionException {
        JavaParser.package_name_return retval = new JavaParser.package_name_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:157:2: ( ( ID DOT )* ID )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:157:4: ( ID DOT )* ID
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:157:4: ( ID DOT )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==ID) ) {
                    int LA25_1 = input.LA(2);

                    if ( (LA25_1==DOT) ) {
                        alt25=1;
                    }


                }


                switch (alt25) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:157:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_package_name723); if (state.failed) return retval;
            	    match(input,DOT,FOLLOW_DOT_in_package_name725); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_package_name729); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperant(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "package_name"

    public static class import_name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "import_name"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:160:1: import_name : ( ID DOT )+ ( ID | STAR ) ;
    public final JavaParser.import_name_return import_name() throws RecognitionException {
        JavaParser.import_name_return retval = new JavaParser.import_name_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:2: ( ( ID DOT )+ ( ID | STAR ) )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:4: ( ID DOT )+ ( ID | STAR )
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:4: ( ID DOT )+
            int cnt26=0;
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==ID) ) {
                    int LA26_1 = input.LA(2);

                    if ( (LA26_1==DOT) ) {
                        alt26=1;
                    }


                }


                switch (alt26) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_import_name743); if (state.failed) return retval;
            	    match(input,DOT,FOLLOW_DOT_in_import_name745); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    if ( cnt26 >= 1 ) break loop26;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(26, input);
                        throw eee;
                }
                cnt26++;
            } while (true);

            if ( input.LA(1)==STAR||input.LA(1)==ID ) {
                input.consume();
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            if ( state.backtracking==0 ) {
              helper.registerOperant(input.toString(retval.start,input.LT(-1)));
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

    public static class class_name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "class_name"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:164:1: class_name : ( ID DOT )* ID ;
    public final JavaParser.class_name_return class_name() throws RecognitionException {
        JavaParser.class_name_return retval = new JavaParser.class_name_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:165:2: ( ( ID DOT )* ID )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:165:4: ( ID DOT )* ID
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:165:4: ( ID DOT )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==ID) ) {
                    int LA27_1 = input.LA(2);

                    if ( (LA27_1==DOT) ) {
                        int LA27_2 = input.LA(3);

                        if ( (LA27_2==ID) ) {
                            alt27=1;
                        }


                    }


                }


                switch (alt27) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:165:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_class_name767); if (state.failed) return retval;
            	    match(input,DOT,FOLLOW_DOT_in_class_name769); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_class_name773); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperant(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "class_name"

    public static class method_name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "method_name"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:168:1: method_name : ( ID DOT )* ID ;
    public final JavaParser.method_name_return method_name() throws RecognitionException {
        JavaParser.method_name_return retval = new JavaParser.method_name_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:169:2: ( ( ID DOT )* ID )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:169:4: ( ID DOT )* ID
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:169:4: ( ID DOT )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==ID) ) {
                    int LA28_1 = input.LA(2);

                    if ( (LA28_1==DOT) ) {
                        alt28=1;
                    }


                }


                switch (alt28) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:169:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_method_name787); if (state.failed) return retval;
            	    match(input,DOT,FOLLOW_DOT_in_method_name789); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_method_name793); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperant(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "method_name"


    // $ANTLR start "variable_name"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:172:1: variable_name : name ( array )? ;
    public final void variable_name() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:173:2: ( name ( array )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:173:4: name ( array )?
            {
            pushFollow(FOLLOW_name_in_variable_name806);
            name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:173:9: ( array )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==OPEN_RECT_BRACKET) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: array
                    {
                    pushFollow(FOLLOW_array_in_variable_name808);
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

    public static class name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "name"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:176:1: name : ( ID DOT )* ID ;
    public final JavaParser.name_return name() throws RecognitionException {
        JavaParser.name_return retval = new JavaParser.name_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:176:6: ( ( ID DOT )* ID )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:176:8: ( ID DOT )* ID
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:176:8: ( ID DOT )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==ID) ) {
                    int LA30_1 = input.LA(2);

                    if ( (LA30_1==DOT) ) {
                        alt30=1;
                    }


                }


                switch (alt30) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:176:9: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_name820); if (state.failed) return retval;
            	    match(input,DOT,FOLLOW_DOT_in_name822); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_name826); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperant(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "name"


    // $ANTLR start "array"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:178:1: array : open_rect_bracket ( value )? close_rect_bracket ;
    public final void array() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:178:7: ( open_rect_bracket ( value )? close_rect_bracket )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:178:9: open_rect_bracket ( value )? close_rect_bracket
            {
            pushFollow(FOLLOW_open_rect_bracket_in_array836);
            open_rect_bracket();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:178:27: ( value )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==NEW||LA31_0==NULL||(LA31_0>=BOOLEAN && LA31_0<=INT)||LA31_0==FLOAT||(LA31_0>=STRING && LA31_0<=CHAR)) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: value
                    {
                    pushFollow(FOLLOW_value_in_array838);
                    value();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_close_rect_bracket_in_array841);
            close_rect_bracket();

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
    // $ANTLR end "array"

    public static class package__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "package_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:184:1: package_ : PACKAGE ;
    public final JavaParser.package__return package_() throws RecognitionException {
        JavaParser.package__return retval = new JavaParser.package__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:185:2: ( PACKAGE )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:185:4: PACKAGE
            {
            match(input,PACKAGE,FOLLOW_PACKAGE_in_package_854); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperator(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "package_"

    public static class import__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "import_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:186:1: import_ : IMPORT ;
    public final JavaParser.import__return import_() throws RecognitionException {
        JavaParser.import__return retval = new JavaParser.import__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:187:2: ( IMPORT )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:187:4: IMPORT
            {
            match(input,IMPORT,FOLLOW_IMPORT_in_import_865); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperator(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "import_"

    public static class class__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "class_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:188:1: class_ : CLASS ;
    public final JavaParser.class__return class_() throws RecognitionException {
        JavaParser.class__return retval = new JavaParser.class__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:188:8: ( CLASS )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:188:10: CLASS
            {
            match(input,CLASS,FOLLOW_CLASS_in_class_875); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperator(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "class_"

    public static class extends__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "extends_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:189:1: extends_ : EXTENDS ;
    public final JavaParser.extends__return extends_() throws RecognitionException {
        JavaParser.extends__return retval = new JavaParser.extends__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:189:9: ( EXTENDS )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:189:11: EXTENDS
            {
            match(input,EXTENDS,FOLLOW_EXTENDS_in_extends_883); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperator(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "extends_"

    public static class implements__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "implements_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:190:1: implements_ : IMPLEMENTS ;
    public final JavaParser.implements__return implements_() throws RecognitionException {
        JavaParser.implements__return retval = new JavaParser.implements__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:191:2: ( IMPLEMENTS )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:191:4: IMPLEMENTS
            {
            match(input,IMPLEMENTS,FOLLOW_IMPLEMENTS_in_implements_893); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperator(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "implements_"

    public static class this__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "this_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:192:1: this_ : THIS ;
    public final JavaParser.this__return this_() throws RecognitionException {
        JavaParser.this__return retval = new JavaParser.this__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:192:7: ( THIS )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:192:9: THIS
            {
            match(input,THIS,FOLLOW_THIS_in_this_902); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperant(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "this_"

    public static class super__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "super_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:193:1: super_ : SUPER ;
    public final JavaParser.super__return super_() throws RecognitionException {
        JavaParser.super__return retval = new JavaParser.super__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:193:8: ( SUPER )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:193:10: SUPER
            {
            match(input,SUPER,FOLLOW_SUPER_in_super_911); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperant(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "super_"

    public static class void__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "void_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:194:1: void_ : VOID ;
    public final JavaParser.void__return void_() throws RecognitionException {
        JavaParser.void__return retval = new JavaParser.void__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:194:7: ( VOID )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:194:9: VOID
            {
            match(input,VOID,FOLLOW_VOID_in_void_920); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperant(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "void_"

    public static class public__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "public_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:195:1: public_ : PUBLIC ;
    public final JavaParser.public__return public_() throws RecognitionException {
        JavaParser.public__return retval = new JavaParser.public__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:195:9: ( PUBLIC )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:195:11: PUBLIC
            {
            match(input,PUBLIC,FOLLOW_PUBLIC_in_public_929); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperator(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "public_"

    public static class private__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "private_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:196:1: private_ : PRIVATE ;
    public final JavaParser.private__return private_() throws RecognitionException {
        JavaParser.private__return retval = new JavaParser.private__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:196:9: ( PRIVATE )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:196:11: PRIVATE
            {
            match(input,PRIVATE,FOLLOW_PRIVATE_in_private_937); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperator(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "private_"

    public static class protected__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "protected_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:197:1: protected_ : PROTECTED ;
    public final JavaParser.protected__return protected_() throws RecognitionException {
        JavaParser.protected__return retval = new JavaParser.protected__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:198:2: ( PROTECTED )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:198:4: PROTECTED
            {
            match(input,PROTECTED,FOLLOW_PROTECTED_in_protected_947); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperator(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "protected_"

    public static class static__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "static_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:199:1: static_ : STATIC ;
    public final JavaParser.static__return static_() throws RecognitionException {
        JavaParser.static__return retval = new JavaParser.static__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:199:9: ( STATIC )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:199:11: STATIC
            {
            match(input,STATIC,FOLLOW_STATIC_in_static_956); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperator(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "static_"

    public static class final__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "final_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:200:1: final_ : FINAL ;
    public final JavaParser.final__return final_() throws RecognitionException {
        JavaParser.final__return retval = new JavaParser.final__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:200:8: ( FINAL )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:200:10: FINAL
            {
            match(input,FINAL,FOLLOW_FINAL_in_final_965); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperator(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "final_"

    public static class transient__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "transient_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:201:1: transient_ : TRANSIENT ;
    public final JavaParser.transient__return transient_() throws RecognitionException {
        JavaParser.transient__return retval = new JavaParser.transient__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:202:2: ( TRANSIENT )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:202:4: TRANSIENT
            {
            match(input,TRANSIENT,FOLLOW_TRANSIENT_in_transient_975); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperator(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "transient_"

    public static class new__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "new_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:203:1: new_ : NEW ;
    public final JavaParser.new__return new_() throws RecognitionException {
        JavaParser.new__return retval = new JavaParser.new__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:203:6: ( NEW )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:203:8: NEW
            {
            match(input,NEW,FOLLOW_NEW_in_new_984); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperator(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "new_"

    public static class try__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "try_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:204:1: try_ : TRY ;
    public final JavaParser.try__return try_() throws RecognitionException {
        JavaParser.try__return retval = new JavaParser.try__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:204:6: ( TRY )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:204:8: TRY
            {
            match(input,TRY,FOLLOW_TRY_in_try_993); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperator(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "try_"

    public static class catch__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "catch_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:205:1: catch_ : CATCH ;
    public final JavaParser.catch__return catch_() throws RecognitionException {
        JavaParser.catch__return retval = new JavaParser.catch__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:205:8: ( CATCH )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:205:10: CATCH
            {
            match(input,CATCH,FOLLOW_CATCH_in_catch_1002); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperator(input.toString(retval.start,input.LT(-1)), 1);
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
    // $ANTLR end "catch_"

    public static class finally__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "finally_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:206:1: finally_ : FINALLY ;
    public final JavaParser.finally__return finally_() throws RecognitionException {
        JavaParser.finally__return retval = new JavaParser.finally__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:206:9: ( FINALLY )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:206:11: FINALLY
            {
            match(input,FINALLY,FOLLOW_FINALLY_in_finally_1010); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperator(input.toString(retval.start,input.LT(-1)), 1);
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
    // $ANTLR end "finally_"

    public static class semicolon_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "semicolon"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:212:1: semicolon : SEMICOLON ;
    public final JavaParser.semicolon_return semicolon() throws RecognitionException {
        JavaParser.semicolon_return retval = new JavaParser.semicolon_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:213:2: ( SEMICOLON )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:213:4: SEMICOLON
            {
            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_semicolon1025); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperator(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "semicolon"

    public static class comma_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "comma"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:214:1: comma : COMMA ;
    public final JavaParser.comma_return comma() throws RecognitionException {
        JavaParser.comma_return retval = new JavaParser.comma_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:214:7: ( COMMA )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:214:9: COMMA
            {
            match(input,COMMA,FOLLOW_COMMA_in_comma1034); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperator(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "comma"

    public static class dot_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "dot"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:215:1: dot : DOT ;
    public final JavaParser.dot_return dot() throws RecognitionException {
        JavaParser.dot_return retval = new JavaParser.dot_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:215:5: ( DOT )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:215:7: DOT
            {
            match(input,DOT,FOLLOW_DOT_in_dot1043); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperator(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "dot"

    public static class assign_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "assign"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:216:1: assign : ASSIGN ;
    public final JavaParser.assign_return assign() throws RecognitionException {
        JavaParser.assign_return retval = new JavaParser.assign_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:216:8: ( ASSIGN )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:216:10: ASSIGN
            {
            match(input,ASSIGN,FOLLOW_ASSIGN_in_assign1052); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerOperator(input.toString(retval.start,input.LT(-1)));
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
    // $ANTLR end "assign"


    // $ANTLR start "block_begin"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:217:1: block_begin : OPEN_CURLY_BRACKET ;
    public final void block_begin() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:218:2: ( OPEN_CURLY_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:218:4: OPEN_CURLY_BRACKET
            {
            match(input,OPEN_CURLY_BRACKET,FOLLOW_OPEN_CURLY_BRACKET_in_block_begin1062); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              helper.registerOperator("{}");
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
    // $ANTLR end "block_begin"


    // $ANTLR start "block_end"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:219:1: block_end : CLOSE_CURLY_BRACKET ;
    public final void block_end() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:220:2: ( CLOSE_CURLY_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:220:4: CLOSE_CURLY_BRACKET
            {
            match(input,CLOSE_CURLY_BRACKET,FOLLOW_CLOSE_CURLY_BRACKET_in_block_end1072); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              helper.registerOperator("");
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
    // $ANTLR end "block_end"


    // $ANTLR start "open_bracket"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:221:1: open_bracket : OPEN_BRACKET ;
    public final void open_bracket() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:222:2: ( OPEN_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:222:4: OPEN_BRACKET
            {
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_open_bracket1082); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              helper.registerOperator("()");
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
    // $ANTLR end "open_bracket"


    // $ANTLR start "close_bracket"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:223:1: close_bracket : CLOSE_BRACKET ;
    public final void close_bracket() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:224:2: ( CLOSE_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:224:4: CLOSE_BRACKET
            {
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_close_bracket1092); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              helper.registerOperator("");
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
    // $ANTLR end "close_bracket"


    // $ANTLR start "open_rect_bracket"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:225:1: open_rect_bracket : OPEN_RECT_BRACKET ;
    public final void open_rect_bracket() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:226:2: ( OPEN_RECT_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:226:4: OPEN_RECT_BRACKET
            {
            match(input,OPEN_RECT_BRACKET,FOLLOW_OPEN_RECT_BRACKET_in_open_rect_bracket1103); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              helper.registerOperator("[]");
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
    // $ANTLR end "open_rect_bracket"


    // $ANTLR start "close_rect_bracket"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:227:1: close_rect_bracket : CLOSE_RECT_BRACKET ;
    public final void close_rect_bracket() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:228:2: ( CLOSE_RECT_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:228:4: CLOSE_RECT_BRACKET
            {
            match(input,CLOSE_RECT_BRACKET,FOLLOW_CLOSE_RECT_BRACKET_in_close_rect_bracket1113); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              helper.registerOperator("");
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
    // $ANTLR end "close_rect_bracket"

    // $ANTLR start synpred12_JavaParser
    public final void synpred12_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:56:5: ( class_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:56:5: class_def
        {
        pushFollow(FOLLOW_class_def_in_synpred12_JavaParser193);
        class_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred12_JavaParser

    // $ANTLR start synpred13_JavaParser
    public final void synpred13_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:56:17: ( constructor_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:56:17: constructor_def
        {
        pushFollow(FOLLOW_constructor_def_in_synpred13_JavaParser197);
        constructor_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred13_JavaParser

    // $ANTLR start synpred14_JavaParser
    public final void synpred14_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:56:35: ( method_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:56:35: method_def
        {
        pushFollow(FOLLOW_method_def_in_synpred14_JavaParser201);
        method_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred14_JavaParser

    // $ANTLR start synpred15_JavaParser
    public final void synpred15_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:56:48: ( field_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:56:48: field_def
        {
        pushFollow(FOLLOW_field_def_in_synpred15_JavaParser205);
        field_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred15_JavaParser

    // $ANTLR start synpred33_JavaParser
    public final void synpred33_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:113:4: ( variable_assignment semicolon )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:113:4: variable_assignment semicolon
        {
        pushFollow(FOLLOW_variable_assignment_in_synpred33_JavaParser505);
        variable_assignment();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_semicolon_in_synpred33_JavaParser507);
        semicolon();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred33_JavaParser

    // $ANTLR start synpred34_JavaParser
    public final void synpred34_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:114:4: ( variable_def semicolon )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:114:4: variable_def semicolon
        {
        pushFollow(FOLLOW_variable_def_in_synpred34_JavaParser512);
        variable_def();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_semicolon_in_synpred34_JavaParser514);
        semicolon();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred34_JavaParser

    // $ANTLR start synpred35_JavaParser
    public final void synpred35_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:115:4: ( method_call semicolon )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:115:4: method_call semicolon
        {
        pushFollow(FOLLOW_method_call_in_synpred35_JavaParser519);
        method_call();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_semicolon_in_synpred35_JavaParser521);
        semicolon();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred35_JavaParser

    // Delegated rules

    public final boolean synpred12_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred12_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
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
    public final boolean synpred33_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred33_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred14_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred14_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred34_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred34_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred13_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred13_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA8 dfa8 = new DFA8(this);
    protected DFA14 dfa14 = new DFA14(this);
    static final String DFA8_eotS =
        "\16\uffff";
    static final String DFA8_eofS =
        "\16\uffff";
    static final String DFA8_minS =
        "\1\6\1\uffff\6\0\1\uffff\2\0\3\uffff";
    static final String DFA8_maxS =
        "\1\72\1\uffff\6\0\1\uffff\2\0\3\uffff";
    static final String DFA8_acceptS =
        "\1\uffff\1\5\6\uffff\1\1\2\uffff\1\2\1\3\1\4";
    static final String DFA8_specialS =
        "\2\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\uffff\1\6\1\7\3\uffff}>";
    static final String[] DFA8_transitionS = {
            "\1\10\2\uffff\1\12\17\uffff\1\2\1\3\1\4\1\6\1\5\1\7\1\uffff"+
            "\1\1\31\uffff\1\11",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            ""
    };

    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }
        public String getDescription() {
            return "()* loopback of 56:4: ( class_def | constructor_def | method_def | field_def )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA8_2 = input.LA(1);

                         
                        int index8_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_JavaParser()) ) {s = 8;}

                        else if ( (synpred13_JavaParser()) ) {s = 11;}

                        else if ( (synpred14_JavaParser()) ) {s = 12;}

                        else if ( (synpred15_JavaParser()) ) {s = 13;}

                         
                        input.seek(index8_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA8_3 = input.LA(1);

                         
                        int index8_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_JavaParser()) ) {s = 8;}

                        else if ( (synpred13_JavaParser()) ) {s = 11;}

                        else if ( (synpred14_JavaParser()) ) {s = 12;}

                        else if ( (synpred15_JavaParser()) ) {s = 13;}

                         
                        input.seek(index8_3);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA8_4 = input.LA(1);

                         
                        int index8_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_JavaParser()) ) {s = 8;}

                        else if ( (synpred13_JavaParser()) ) {s = 11;}

                        else if ( (synpred14_JavaParser()) ) {s = 12;}

                        else if ( (synpred15_JavaParser()) ) {s = 13;}

                         
                        input.seek(index8_4);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA8_5 = input.LA(1);

                         
                        int index8_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_JavaParser()) ) {s = 8;}

                        else if ( (synpred13_JavaParser()) ) {s = 11;}

                        else if ( (synpred14_JavaParser()) ) {s = 12;}

                        else if ( (synpred15_JavaParser()) ) {s = 13;}

                         
                        input.seek(index8_5);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA8_6 = input.LA(1);

                         
                        int index8_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_JavaParser()) ) {s = 8;}

                        else if ( (synpred13_JavaParser()) ) {s = 11;}

                        else if ( (synpred14_JavaParser()) ) {s = 12;}

                        else if ( (synpred15_JavaParser()) ) {s = 13;}

                         
                        input.seek(index8_6);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA8_7 = input.LA(1);

                         
                        int index8_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_JavaParser()) ) {s = 8;}

                        else if ( (synpred13_JavaParser()) ) {s = 11;}

                        else if ( (synpred14_JavaParser()) ) {s = 12;}

                        else if ( (synpred15_JavaParser()) ) {s = 13;}

                         
                        input.seek(index8_7);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA8_9 = input.LA(1);

                         
                        int index8_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_JavaParser()) ) {s = 11;}

                        else if ( (synpred14_JavaParser()) ) {s = 12;}

                        else if ( (synpred15_JavaParser()) ) {s = 13;}

                         
                        input.seek(index8_9);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA8_10 = input.LA(1);

                         
                        int index8_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred14_JavaParser()) ) {s = 12;}

                        else if ( (synpred15_JavaParser()) ) {s = 13;}

                         
                        input.seek(index8_10);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 8, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA14_eotS =
        "\10\uffff";
    static final String DFA14_eofS =
        "\2\uffff\1\5\5\uffff";
    static final String DFA14_minS =
        "\1\12\1\uffff\1\57\1\uffff\1\6\3\uffff";
    static final String DFA14_maxS =
        "\1\103\1\uffff\1\70\1\uffff\1\72\3\uffff";
    static final String DFA14_acceptS =
        "\1\uffff\1\1\1\uffff\1\5\1\uffff\1\3\1\4\1\2";
    static final String DFA14_specialS =
        "\10\uffff}>";
    static final String[] DFA14_transitionS = {
            "\1\3\1\uffff\1\1\54\uffff\1\1\1\2\1\1\1\uffff\1\1\4\uffff\2"+
            "\1",
            "",
            "\1\4\1\5\2\uffff\2\5\1\6\1\5\1\uffff\1\5",
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
            return "85:1: value : ( constant | class_name dot class_ | variable_name | method_call | new_class );";
        }
    }
 

    public static final BitSet FOLLOW_package_def_in_file39 = new BitSet(new long[]{0x000000007E000060L});
    public static final BitSet FOLLOW_imports_in_file41 = new BitSet(new long[]{0x000000007E000042L});
    public static final BitSet FOLLOW_class_def_in_file43 = new BitSet(new long[]{0x000000007E000042L});
    public static final BitSet FOLLOW_package__in_package_def54 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_package_name_in_package_def56 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_semicolon_in_package_def58 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_import_def_in_imports69 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_import__in_import_def79 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_import_name_in_import_def81 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_semicolon_in_import_def83 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_class_def97 = new BitSet(new long[]{0x000000007E000040L});
    public static final BitSet FOLLOW_class__in_class_def99 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_id_in_class_def101 = new BitSet(new long[]{0x0000000080000180L});
    public static final BitSet FOLLOW_extends__in_class_def104 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_class_name_in_class_def106 = new BitSet(new long[]{0x0000000080000180L});
    public static final BitSet FOLLOW_implements__in_class_def111 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_class_name_in_class_def113 = new BitSet(new long[]{0x0001000080000180L});
    public static final BitSet FOLLOW_comma_in_class_def116 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_class_name_in_class_def118 = new BitSet(new long[]{0x0001000080000180L});
    public static final BitSet FOLLOW_block_begin_in_class_def124 = new BitSet(new long[]{0x040000017E000240L});
    public static final BitSet FOLLOW_class_block_in_class_def126 = new BitSet(new long[]{0x040000017E000240L});
    public static final BitSet FOLLOW_block_end_in_class_def128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifier_in_modifiers145 = new BitSet(new long[]{0x000000007E000002L});
    public static final BitSet FOLLOW_public__in_modifier155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_private__in_modifier160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_protected__in_modifier165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_static__in_modifier170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_final__in_modifier175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_transient__in_modifier180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_def_in_class_block193 = new BitSet(new long[]{0x040000007E000242L});
    public static final BitSet FOLLOW_constructor_def_in_class_block197 = new BitSet(new long[]{0x040000007E000242L});
    public static final BitSet FOLLOW_method_def_in_class_block201 = new BitSet(new long[]{0x040000007E000242L});
    public static final BitSet FOLLOW_field_def_in_class_block205 = new BitSet(new long[]{0x040000007E000242L});
    public static final BitSet FOLLOW_modifiers_in_constructor_def219 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_id_in_constructor_def221 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_open_bracket_in_constructor_def223 = new BitSet(new long[]{0x044000007E000200L});
    public static final BitSet FOLLOW_argument_def_in_constructor_def225 = new BitSet(new long[]{0x044000007E000200L});
    public static final BitSet FOLLOW_close_bracket_in_constructor_def227 = new BitSet(new long[]{0x0000000080000180L});
    public static final BitSet FOLLOW_block_begin_in_constructor_def229 = new BitSet(new long[]{0x040000017E406240L});
    public static final BitSet FOLLOW_code_block_in_constructor_def231 = new BitSet(new long[]{0x040000017E000240L});
    public static final BitSet FOLLOW_block_end_in_constructor_def233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_method_def248 = new BitSet(new long[]{0x040000007E000200L});
    public static final BitSet FOLLOW_variable_type_in_method_def250 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_id_in_method_def252 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_open_bracket_in_method_def254 = new BitSet(new long[]{0x044000007E000200L});
    public static final BitSet FOLLOW_argument_def_in_method_def256 = new BitSet(new long[]{0x044000007E000200L});
    public static final BitSet FOLLOW_close_bracket_in_method_def258 = new BitSet(new long[]{0x0000000080000180L});
    public static final BitSet FOLLOW_block_begin_in_method_def260 = new BitSet(new long[]{0x040000017E406240L});
    public static final BitSet FOLLOW_code_block_in_method_def262 = new BitSet(new long[]{0x040000017E000240L});
    public static final BitSet FOLLOW_block_end_in_method_def264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_name_in_method_call281 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_open_bracket_in_method_call283 = new BitSet(new long[]{0x2E4000007E007600L,0x000000000000000CL});
    public static final BitSet FOLLOW_arguments_in_method_call285 = new BitSet(new long[]{0x044000007E000200L});
    public static final BitSet FOLLOW_close_bracket_in_method_call287 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_dot_in_method_call290 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_id_in_method_call292 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_open_bracket_in_method_call294 = new BitSet(new long[]{0x2E4000007E007600L,0x000000000000000CL});
    public static final BitSet FOLLOW_arguments_in_method_call296 = new BitSet(new long[]{0x044000007E000200L});
    public static final BitSet FOLLOW_close_bracket_in_method_call298 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_modifiers_in_field_def311 = new BitSet(new long[]{0x040000007E000200L});
    public static final BitSet FOLLOW_variable_type_in_field_def313 = new BitSet(new long[]{0x0400000000006000L});
    public static final BitSet FOLLOW_variable_name_in_field_def315 = new BitSet(new long[]{0x0100008000000000L});
    public static final BitSet FOLLOW_assign_in_field_def318 = new BitSet(new long[]{0x2E00000000007400L,0x000000000000000CL});
    public static final BitSet FOLLOW_value_in_field_def320 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_semicolon_in_field_def324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_type_in_argument_def337 = new BitSet(new long[]{0x0400000000006000L});
    public static final BitSet FOLLOW_variable_name_in_argument_def339 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_comma_in_argument_def342 = new BitSet(new long[]{0x040000007E000200L});
    public static final BitSet FOLLOW_variable_type_in_argument_def344 = new BitSet(new long[]{0x0400000000006000L});
    public static final BitSet FOLLOW_variable_name_in_argument_def346 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_variable_type_in_variable_def360 = new BitSet(new long[]{0x0400000000006000L});
    public static final BitSet FOLLOW_variable_name_in_variable_def362 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_assign_in_variable_def365 = new BitSet(new long[]{0x2E00000000007400L,0x000000000000000CL});
    public static final BitSet FOLLOW_value_in_variable_def367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constant_in_value380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_name_in_value385 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_dot_in_value387 = new BitSet(new long[]{0x000000007E000040L});
    public static final BitSet FOLLOW_class__in_value389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_name_in_value394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_value399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_new_class_in_value404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_int_const_in_constant413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_const_in_constant418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_float_const_in_constant423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_char_const_in_constant428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_null_const_in_constant433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_boolean_const_in_constant438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_new__in_new_class450 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_class_name_in_new_class452 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_open_bracket_in_new_class454 = new BitSet(new long[]{0x2E4000007E007600L,0x000000000000000CL});
    public static final BitSet FOLLOW_arguments_in_new_class456 = new BitSet(new long[]{0x044000007E000200L});
    public static final BitSet FOLLOW_close_bracket_in_new_class458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_value_in_arguments471 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_comma_in_arguments474 = new BitSet(new long[]{0x2E00000000007400L,0x000000000000000CL});
    public static final BitSet FOLLOW_value_in_arguments476 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_statement_in_code_block492 = new BitSet(new long[]{0x040000007E406202L});
    public static final BitSet FOLLOW_variable_assignment_in_statement505 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_semicolon_in_statement507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_statement512 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_semicolon_in_statement514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_statement519 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_semicolon_in_statement521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_try_catch_in_statement526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_this__in_variable_assignment539 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_dot_in_variable_assignment541 = new BitSet(new long[]{0x0400000000006000L});
    public static final BitSet FOLLOW_super__in_variable_assignment545 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_dot_in_variable_assignment547 = new BitSet(new long[]{0x0400000000006000L});
    public static final BitSet FOLLOW_variable_name_in_variable_assignment551 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_assign_in_variable_assignment553 = new BitSet(new long[]{0x2E00000000007400L,0x000000000000000CL});
    public static final BitSet FOLLOW_value_in_variable_assignment555 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_try__in_try_catch567 = new BitSet(new long[]{0x0000000080000180L});
    public static final BitSet FOLLOW_block_begin_in_try_catch569 = new BitSet(new long[]{0x040000017E406240L});
    public static final BitSet FOLLOW_code_block_in_try_catch571 = new BitSet(new long[]{0x040000017E000240L});
    public static final BitSet FOLLOW_block_end_in_try_catch573 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_catch__in_try_catch576 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_try_catch578 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_id_in_try_catch580 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_id_in_try_catch582 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_try_catch584 = new BitSet(new long[]{0x0000000080000180L});
    public static final BitSet FOLLOW_block_begin_in_try_catch586 = new BitSet(new long[]{0x040000017E406240L});
    public static final BitSet FOLLOW_code_block_in_try_catch588 = new BitSet(new long[]{0x040000017E000240L});
    public static final BitSet FOLLOW_block_end_in_try_catch590 = new BitSet(new long[]{0x0000000001800002L});
    public static final BitSet FOLLOW_finally__in_try_catch595 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_try_catch597 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_id_in_try_catch599 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_id_in_try_catch601 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_try_catch603 = new BitSet(new long[]{0x0000000080000180L});
    public static final BitSet FOLLOW_block_begin_in_try_catch605 = new BitSet(new long[]{0x040000017E406240L});
    public static final BitSet FOLLOW_code_block_in_try_catch607 = new BitSet(new long[]{0x040000017E000240L});
    public static final BitSet FOLLOW_block_end_in_try_catch609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_name_in_variable_type623 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_array_in_variable_type625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_void__in_variable_type631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_id641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_int_const656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_string_const666 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_float_const676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_in_char_const686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_null_const696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_boolean_const707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_package_name723 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_DOT_in_package_name725 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_ID_in_package_name729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_import_name743 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_DOT_in_import_name745 = new BitSet(new long[]{0x0400001000000000L});
    public static final BitSet FOLLOW_set_in_import_name749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_class_name767 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_DOT_in_class_name769 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_ID_in_class_name773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_method_name787 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_DOT_in_method_name789 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_ID_in_method_name793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_name_in_variable_name806 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_array_in_variable_name808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_name820 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_DOT_in_name822 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_ID_in_name826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_open_rect_bracket_in_array836 = new BitSet(new long[]{0x2E10000000007400L,0x000000000000000CL});
    public static final BitSet FOLLOW_value_in_array838 = new BitSet(new long[]{0x2E10000000007400L,0x000000000000000CL});
    public static final BitSet FOLLOW_close_rect_bracket_in_array841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PACKAGE_in_package_854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_import_865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASS_in_class_875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXTENDS_in_extends_883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPLEMENTS_in_implements_893 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_THIS_in_this_902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUPER_in_super_911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOID_in_void_920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PUBLIC_in_public_929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRIVATE_in_private_937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROTECTED_in_protected_947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STATIC_in_static_956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FINAL_in_final_965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSIENT_in_transient_975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEW_in_new_984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRY_in_try_993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CATCH_in_catch_1002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FINALLY_in_finally_1010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_semicolon1025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_comma1034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_dot1043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_assign1052 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_CURLY_BRACKET_in_block_begin1062 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLOSE_CURLY_BRACKET_in_block_end1072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_open_bracket1082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_close_bracket1092 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_RECT_BRACKET_in_open_rect_bracket1103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLOSE_RECT_BRACKET_in_close_rect_bracket1113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_def_in_synpred12_JavaParser193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constructor_def_in_synpred13_JavaParser197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_def_in_synpred14_JavaParser201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_field_def_in_synpred15_JavaParser205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_synpred33_JavaParser505 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_semicolon_in_synpred33_JavaParser507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_synpred34_JavaParser512 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_semicolon_in_synpred34_JavaParser514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_synpred35_JavaParser519 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_semicolon_in_synpred35_JavaParser521 = new BitSet(new long[]{0x0000000000000002L});

}