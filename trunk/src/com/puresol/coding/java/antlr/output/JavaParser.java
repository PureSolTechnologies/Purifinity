// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g 2009-12-09 17:00:34

package com.puresol.coding.java.antlr.output;

import com.puresol.coding.ParserHelper;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class JavaParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "PACKAGE", "IMPORT", "CLASS", "EXTENDS", "IMPLEMENTS", "VOID", "NEW", "RETURN", "NULL", "THIS", "SUPER", "FOR", "DO", "WHILE", "IF", "ELSE", "SWITCH", "CASE", "TRY", "CATCH", "FINALLY", "PUBLIC", "PRIVATE", "PROTECTED", "FINAL", "STATIC", "TRANSIENT", "OPEN_CURLY_BRACKET", "CLOSE_CURLY_BRACKET", "PLUS", "MINUS", "SLASH", "STAR", "EQUAL", "UNEQUAL", "ASSIGN", "INC", "DEC", "LOGICAL_OR", "BIT_OR", "LOGICAL_AND", "BIT_AND", "NOT", "DOT", "COMMA", "LT", "GT", "OPEN_RECT_BRACKET", "CLOSE_RECT_BRACKET", "OPEN_BRACKET", "CLOSE_BRACKET", "COLON", "SEMICOLON", "AT", "TILDE", "BOOLEAN", "ID", "INT", "EXPONENT", "FLOAT", "LINEFEED", "COMMENT", "WS", "ESC_SEQ", "STRING", "CHAR", "HEX_DIGIT", "UNICODE_ESC", "OCTAL_ESC"
    };
    public static final int PACKAGE=4;
    public static final int PROTECTED=27;
    public static final int DEC=41;
    public static final int CLASS=6;
    public static final int LT=49;
    public static final int EXPONENT=62;
    public static final int STAR=36;
    public static final int WHILE=17;
    public static final int BIT_AND=45;
    public static final int CASE=21;
    public static final int OCTAL_ESC=72;
    public static final int NEW=10;
    public static final int CHAR=69;
    public static final int FOR=15;
    public static final int DO=16;
    public static final int FLOAT=63;
    public static final int NOT=46;
    public static final int ID=60;
    public static final int EOF=-1;
    public static final int CLOSE_RECT_BRACKET=52;
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
    public static final int DOT=47;
    public static final int COMMENT=65;
    public static final int OPEN_CURLY_BRACKET=31;
    public static final int STATIC=29;
    public static final int PRIVATE=26;
    public static final int SWITCH=20;
    public static final int NULL=12;
    public static final int UNICODE_ESC=71;
    public static final int CLOSE_CURLY_BRACKET=32;
    public static final int ELSE=19;
    public static final int HEX_DIGIT=70;
    public static final int SEMICOLON=56;
    public static final int INT=61;
    public static final int MINUS=34;
    public static final int TRY=22;
    public static final int UNEQUAL=38;
    public static final int COLON=55;
    public static final int WS=66;
    public static final int FINALLY=24;
    public static final int ASSIGN=39;
    public static final int GT=50;
    public static final int CATCH=23;
    public static final int OPEN_RECT_BRACKET=51;
    public static final int PUBLIC=25;
    public static final int EXTENDS=7;
    public static final int STRING=68;
    public static final int CLOSE_BRACKET=54;

    // delegates
    // delegators


        public JavaParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public JavaParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
            this.state.ruleMemo = new HashMap[132+1];
             
             
        }
        

    public String[] getTokenNames() { return JavaParser.tokenNames; }
    public String getGrammarFileName() { return "/home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g"; }


    private ParserHelper helper = new ParserHelper(this);

    public ParserHelper getParserHelper() {
    	return helper;
    }



    // $ANTLR start "file"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:23:1: file : package_def ( import_def )* ( class_def )* ;
    public final void file() throws RecognitionException {
        int file_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:23:6: ( package_def ( import_def )* ( class_def )* )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:23:8: package_def ( import_def )* ( class_def )*
            {
            pushFollow(FOLLOW_package_def_in_file44);
            package_def();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:23:20: ( import_def )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==IMPORT) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: import_def
            	    {
            	    pushFollow(FOLLOW_import_def_in_file46);
            	    import_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:23:32: ( class_def )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==CLASS||(LA2_0>=PUBLIC && LA2_0<=TRANSIENT)||LA2_0==AT) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: class_def
            	    {
            	    pushFollow(FOLLOW_class_def_in_file49);
            	    class_def();

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
            if ( state.backtracking>0 ) { memoize(input, 1, file_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "file"


    // $ANTLR start "package_def"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:25:1: package_def : package_ package_name semicolon ;
    public final void package_def() throws RecognitionException {
        int package_def_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:26:2: ( package_ package_name semicolon )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:26:4: package_ package_name semicolon
            {
            pushFollow(FOLLOW_package__in_package_def60);
            package_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_package_name_in_package_def62);
            package_name();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_semicolon_in_package_def64);
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
            if ( state.backtracking>0 ) { memoize(input, 2, package_def_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "package_def"


    // $ANTLR start "import_def"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:29:1: import_def : import_ import_name semicolon ;
    public final void import_def() throws RecognitionException {
        int import_def_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:30:2: ( import_ import_name semicolon )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:30:4: import_ import_name semicolon
            {
            pushFollow(FOLLOW_import__in_import_def76);
            import_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_import_name_in_import_def78);
            import_name();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_semicolon_in_import_def80);
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
            if ( state.backtracking>0 ) { memoize(input, 3, import_def_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "import_def"

    public static class class_def_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "class_def"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:33:1: class_def : ( annotation )* ( modifier )* class_ id ( extends_ class_name )? ( implements_ class_name ( comma class_name )* )? class_block ;
    public final JavaParser.class_def_return class_def() throws RecognitionException {
        JavaParser.class_def_return retval = new JavaParser.class_def_return();
        retval.start = input.LT(1);
        int class_def_StartIndex = input.index();
        JavaParser.id_return id1 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:34:2: ( ( annotation )* ( modifier )* class_ id ( extends_ class_name )? ( implements_ class_name ( comma class_name )* )? class_block )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:34:4: ( annotation )* ( modifier )* class_ id ( extends_ class_name )? ( implements_ class_name ( comma class_name )* )? class_block
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:34:4: ( annotation )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==AT) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_class_def92);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:34:16: ( modifier )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>=PUBLIC && LA4_0<=TRANSIENT)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: modifier
            	    {
            	    pushFollow(FOLLOW_modifier_in_class_def95);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            pushFollow(FOLLOW_class__in_class_def98);
            class_();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_id_in_class_def100);
            id1=id();

            state._fsp--;
            if (state.failed) return retval;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:34:36: ( extends_ class_name )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==EXTENDS) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:34:37: extends_ class_name
                    {
                    pushFollow(FOLLOW_extends__in_class_def103);
                    extends_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_class_def105);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;

            }

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:34:59: ( implements_ class_name ( comma class_name )* )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==IMPLEMENTS) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:34:60: implements_ class_name ( comma class_name )*
                    {
                    pushFollow(FOLLOW_implements__in_class_def110);
                    implements_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_class_def112);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:34:83: ( comma class_name )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==COMMA) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:34:84: comma class_name
                    	    {
                    	    pushFollow(FOLLOW_comma_in_class_def115);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    pushFollow(FOLLOW_class_name_in_class_def117);
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

            pushFollow(FOLLOW_class_block_in_class_def123);
            class_block();

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
            if ( state.backtracking>0 ) { memoize(input, 4, class_def_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "class_def"

    public static class constructor_def_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "constructor_def"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:38:1: constructor_def : ( annotation )* ( modifier )* id open_bracket argument_def close_bracket code ;
    public final JavaParser.constructor_def_return constructor_def() throws RecognitionException {
        JavaParser.constructor_def_return retval = new JavaParser.constructor_def_return();
        retval.start = input.LT(1);
        int constructor_def_StartIndex = input.index();
        JavaParser.id_return id2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:39:2: ( ( annotation )* ( modifier )* id open_bracket argument_def close_bracket code )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:39:4: ( annotation )* ( modifier )* id open_bracket argument_def close_bracket code
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:39:4: ( annotation )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==AT) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_constructor_def139);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:39:16: ( modifier )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>=PUBLIC && LA9_0<=TRANSIENT)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: modifier
            	    {
            	    pushFollow(FOLLOW_modifier_in_constructor_def142);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            pushFollow(FOLLOW_id_in_constructor_def145);
            id2=id();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_open_bracket_in_constructor_def147);
            open_bracket();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_argument_def_in_constructor_def149);
            argument_def();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_close_bracket_in_constructor_def151);
            close_bracket();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_code_in_constructor_def153);
            code();

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
            if ( state.backtracking>0 ) { memoize(input, 5, constructor_def_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "constructor_def"

    public static class method_def_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "method_def"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:43:1: method_def : ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket code ;
    public final JavaParser.method_def_return method_def() throws RecognitionException {
        JavaParser.method_def_return retval = new JavaParser.method_def_return();
        retval.start = input.LT(1);
        int method_def_StartIndex = input.index();
        JavaParser.id_return id3 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:44:2: ( ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket code )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:44:4: ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket code
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:44:4: ( annotation )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==AT) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_method_def168);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:44:16: ( modifier )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>=PUBLIC && LA11_0<=TRANSIENT)) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: modifier
            	    {
            	    pushFollow(FOLLOW_modifier_in_method_def171);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            pushFollow(FOLLOW_variable_type_in_method_def174);
            variable_type();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_id_in_method_def176);
            id3=id();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_open_bracket_in_method_def178);
            open_bracket();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_argument_def_in_method_def180);
            argument_def();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_close_bracket_in_method_def182);
            close_bracket();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_code_in_method_def184);
            code();

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
            if ( state.backtracking>0 ) { memoize(input, 6, method_def_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "method_def"


    // $ANTLR start "field_def"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:48:1: field_def : ( annotation )* ( modifier )* variable_type variable_name ( assign value )? semicolon ;
    public final void field_def() throws RecognitionException {
        int field_def_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:49:2: ( ( annotation )* ( modifier )* variable_type variable_name ( assign value )? semicolon )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:49:4: ( annotation )* ( modifier )* variable_type variable_name ( assign value )? semicolon
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:49:4: ( annotation )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==AT) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_field_def200);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:49:16: ( modifier )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0>=PUBLIC && LA13_0<=TRANSIENT)) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: modifier
            	    {
            	    pushFollow(FOLLOW_modifier_in_field_def203);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            pushFollow(FOLLOW_variable_type_in_field_def206);
            variable_type();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_variable_name_in_field_def208);
            variable_name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:49:54: ( assign value )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==ASSIGN) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:49:55: assign value
                    {
                    pushFollow(FOLLOW_assign_in_field_def211);
                    assign();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_field_def213);
                    value();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_semicolon_in_field_def217);
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
            if ( state.backtracking>0 ) { memoize(input, 7, field_def_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "field_def"


    // $ANTLR start "argument_def"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:52:1: argument_def : ( variable_def ( comma variable_def )* )? ;
    public final void argument_def() throws RecognitionException {
        int argument_def_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:53:2: ( ( variable_def ( comma variable_def )* )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:53:4: ( variable_def ( comma variable_def )* )?
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:53:4: ( variable_def ( comma variable_def )* )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==VOID||LA16_0==AT||LA16_0==ID) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:53:5: variable_def ( comma variable_def )*
                    {
                    pushFollow(FOLLOW_variable_def_in_argument_def229);
                    variable_def();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:53:18: ( comma variable_def )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==COMMA) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:53:19: comma variable_def
                    	    {
                    	    pushFollow(FOLLOW_comma_in_argument_def232);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_variable_def_in_argument_def234);
                    	    variable_def();

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
            if ( state.backtracking>0 ) { memoize(input, 8, argument_def_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "argument_def"


    // $ANTLR start "variable_def"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:56:1: variable_def : ( annotation )* variable_type variable_name ( assign value )? ;
    public final void variable_def() throws RecognitionException {
        int variable_def_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:57:2: ( ( annotation )* variable_type variable_name ( assign value )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:57:4: ( annotation )* variable_type variable_name ( assign value )?
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:57:4: ( annotation )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==AT) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_variable_def249);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

            pushFollow(FOLLOW_variable_type_in_variable_def252);
            variable_type();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_variable_name_in_variable_def254);
            variable_name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:57:44: ( assign value )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==ASSIGN) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:57:45: assign value
                    {
                    pushFollow(FOLLOW_assign_in_variable_def257);
                    assign();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_variable_def259);
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
            if ( state.backtracking>0 ) { memoize(input, 9, variable_def_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "variable_def"


    // $ANTLR start "modifier"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:60:1: modifier : ( public_ | private_ | protected_ | static_ | final_ | transient_ );
    public final void modifier() throws RecognitionException {
        int modifier_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:60:9: ( public_ | private_ | protected_ | static_ | final_ | transient_ )
            int alt19=6;
            switch ( input.LA(1) ) {
            case PUBLIC:
                {
                alt19=1;
                }
                break;
            case PRIVATE:
                {
                alt19=2;
                }
                break;
            case PROTECTED:
                {
                alt19=3;
                }
                break;
            case STATIC:
                {
                alt19=4;
                }
                break;
            case FINAL:
                {
                alt19=5;
                }
                break;
            case TRANSIENT:
                {
                alt19=6;
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
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:60:11: public_
                    {
                    pushFollow(FOLLOW_public__in_modifier271);
                    public_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:61:4: private_
                    {
                    pushFollow(FOLLOW_private__in_modifier276);
                    private_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:62:4: protected_
                    {
                    pushFollow(FOLLOW_protected__in_modifier281);
                    protected_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:63:4: static_
                    {
                    pushFollow(FOLLOW_static__in_modifier286);
                    static_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:64:4: final_
                    {
                    pushFollow(FOLLOW_final__in_modifier291);
                    final_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:65:4: transient_
                    {
                    pushFollow(FOLLOW_transient__in_modifier296);
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
            if ( state.backtracking>0 ) { memoize(input, 10, modifier_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "modifier"


    // $ANTLR start "class_block"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:69:1: class_block : block_begin ( class_def | constructor_def | method_def | field_def )* block_end ;
    public final void class_block() throws RecognitionException {
        int class_block_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:2: ( block_begin ( class_def | constructor_def | method_def | field_def )* block_end )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:4: block_begin ( class_def | constructor_def | method_def | field_def )* block_end
            {
            pushFollow(FOLLOW_block_begin_in_class_block308);
            block_begin();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:16: ( class_def | constructor_def | method_def | field_def )*
            loop20:
            do {
                int alt20=5;
                alt20 = dfa20.predict(input);
                switch (alt20) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:17: class_def
            	    {
            	    pushFollow(FOLLOW_class_def_in_class_block311);
            	    class_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 2 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:29: constructor_def
            	    {
            	    pushFollow(FOLLOW_constructor_def_in_class_block315);
            	    constructor_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 3 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:47: method_def
            	    {
            	    pushFollow(FOLLOW_method_def_in_class_block319);
            	    method_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 4 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:60: field_def
            	    {
            	    pushFollow(FOLLOW_field_def_in_class_block323);
            	    field_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            pushFollow(FOLLOW_block_end_in_class_block327);
            block_end();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 11, class_block_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "class_block"


    // $ANTLR start "method_call"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:73:1: method_call : method_name open_bracket arguments close_bracket ( dot id open_bracket arguments close_bracket )* ;
    public final void method_call() throws RecognitionException {
        int method_call_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:74:2: ( method_name open_bracket arguments close_bracket ( dot id open_bracket arguments close_bracket )* )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:74:4: method_name open_bracket arguments close_bracket ( dot id open_bracket arguments close_bracket )*
            {
            pushFollow(FOLLOW_method_name_in_method_call339);
            method_name();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_open_bracket_in_method_call341);
            open_bracket();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_arguments_in_method_call343);
            arguments();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_close_bracket_in_method_call345);
            close_bracket();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:74:53: ( dot id open_bracket arguments close_bracket )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==DOT) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:74:54: dot id open_bracket arguments close_bracket
            	    {
            	    pushFollow(FOLLOW_dot_in_method_call348);
            	    dot();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_method_call350);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_open_bracket_in_method_call352);
            	    open_bracket();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_arguments_in_method_call354);
            	    arguments();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_close_bracket_in_method_call356);
            	    close_bracket();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 12, method_call_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "method_call"


    // $ANTLR start "annotation"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:77:1: annotation : annotation_name ( open_bracket value ( comma value )* close_bracket )? ;
    public final void annotation() throws RecognitionException {
        int annotation_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:78:2: ( annotation_name ( open_bracket value ( comma value )* close_bracket )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:78:4: annotation_name ( open_bracket value ( comma value )* close_bracket )?
            {
            pushFollow(FOLLOW_annotation_name_in_annotation369);
            annotation_name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:78:20: ( open_bracket value ( comma value )* close_bracket )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==OPEN_BRACKET) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:78:21: open_bracket value ( comma value )* close_bracket
                    {
                    pushFollow(FOLLOW_open_bracket_in_annotation372);
                    open_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_annotation374);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:78:40: ( comma value )*
                    loop22:
                    do {
                        int alt22=2;
                        int LA22_0 = input.LA(1);

                        if ( (LA22_0==COMMA) ) {
                            alt22=1;
                        }


                        switch (alt22) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:78:41: comma value
                    	    {
                    	    pushFollow(FOLLOW_comma_in_annotation377);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_value_in_annotation379);
                    	    value();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop22;
                        }
                    } while (true);

                    pushFollow(FOLLOW_close_bracket_in_annotation383);
                    close_bracket();

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
            if ( state.backtracking>0 ) { memoize(input, 13, annotation_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "annotation"


    // $ANTLR start "value"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:81:1: value : ( open_bracket | constant | class_name dot class_ | variable_name | method_call | new_class ) ( operator value ( close_bracket )? )* ;
    public final void value() throws RecognitionException {
        int value_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:81:7: ( ( open_bracket | constant | class_name dot class_ | variable_name | method_call | new_class ) ( operator value ( close_bracket )? )* )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:82:2: ( open_bracket | constant | class_name dot class_ | variable_name | method_call | new_class ) ( operator value ( close_bracket )? )*
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:82:2: ( open_bracket | constant | class_name dot class_ | variable_name | method_call | new_class )
            int alt24=6;
            alt24 = dfa24.predict(input);
            switch (alt24) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:82:4: open_bracket
                    {
                    pushFollow(FOLLOW_open_bracket_in_value398);
                    open_bracket();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:83:4: constant
                    {
                    pushFollow(FOLLOW_constant_in_value403);
                    constant();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:84:4: class_name dot class_
                    {
                    pushFollow(FOLLOW_class_name_in_value408);
                    class_name();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_dot_in_value410);
                    dot();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_class__in_value412);
                    class_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:85:4: variable_name
                    {
                    pushFollow(FOLLOW_variable_name_in_value417);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:86:4: method_call
                    {
                    pushFollow(FOLLOW_method_call_in_value422);
                    method_call();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:87:4: new_class
                    {
                    pushFollow(FOLLOW_new_class_in_value427);
                    new_class();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:88:5: ( operator value ( close_bracket )? )*
            loop26:
            do {
                int alt26=2;
                switch ( input.LA(1) ) {
                case PLUS:
                    {
                    int LA26_2 = input.LA(2);

                    if ( (synpred37_JavaParser()) ) {
                        alt26=1;
                    }


                    }
                    break;
                case MINUS:
                    {
                    int LA26_3 = input.LA(2);

                    if ( (synpred37_JavaParser()) ) {
                        alt26=1;
                    }


                    }
                    break;
                case STAR:
                    {
                    int LA26_4 = input.LA(2);

                    if ( (synpred37_JavaParser()) ) {
                        alt26=1;
                    }


                    }
                    break;
                case SLASH:
                    {
                    int LA26_5 = input.LA(2);

                    if ( (synpred37_JavaParser()) ) {
                        alt26=1;
                    }


                    }
                    break;

                }

                switch (alt26) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:88:6: operator value ( close_bracket )?
            	    {
            	    pushFollow(FOLLOW_operator_in_value434);
            	    operator();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_value_in_value436);
            	    value();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:88:21: ( close_bracket )?
            	    int alt25=2;
            	    int LA25_0 = input.LA(1);

            	    if ( (LA25_0==CLOSE_BRACKET) ) {
            	        int LA25_1 = input.LA(2);

            	        if ( (synpred36_JavaParser()) ) {
            	            alt25=1;
            	        }
            	    }
            	    switch (alt25) {
            	        case 1 :
            	            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: close_bracket
            	            {
            	            pushFollow(FOLLOW_close_bracket_in_value438);
            	            close_bracket();

            	            state._fsp--;
            	            if (state.failed) return ;

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 14, value_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "value"


    // $ANTLR start "constant"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:91:1: constant : ( int_const | string_const | float_const | char_const | null_const | boolean_const );
    public final void constant() throws RecognitionException {
        int constant_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:91:9: ( int_const | string_const | float_const | char_const | null_const | boolean_const )
            int alt27=6;
            switch ( input.LA(1) ) {
            case INT:
                {
                alt27=1;
                }
                break;
            case STRING:
                {
                alt27=2;
                }
                break;
            case FLOAT:
                {
                alt27=3;
                }
                break;
            case CHAR:
                {
                alt27=4;
                }
                break;
            case NULL:
                {
                alt27=5;
                }
                break;
            case BOOLEAN:
                {
                alt27=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }

            switch (alt27) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:91:11: int_const
                    {
                    pushFollow(FOLLOW_int_const_in_constant450);
                    int_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:92:4: string_const
                    {
                    pushFollow(FOLLOW_string_const_in_constant455);
                    string_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:93:4: float_const
                    {
                    pushFollow(FOLLOW_float_const_in_constant460);
                    float_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:94:4: char_const
                    {
                    pushFollow(FOLLOW_char_const_in_constant465);
                    char_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:95:4: null_const
                    {
                    pushFollow(FOLLOW_null_const_in_constant470);
                    null_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:96:4: boolean_const
                    {
                    pushFollow(FOLLOW_boolean_const_in_constant475);
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
            if ( state.backtracking>0 ) { memoize(input, 15, constant_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "constant"


    // $ANTLR start "new_class"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:99:1: new_class : new_ class_name open_bracket arguments close_bracket ;
    public final void new_class() throws RecognitionException {
        int new_class_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:100:2: ( new_ class_name open_bracket arguments close_bracket )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:100:4: new_ class_name open_bracket arguments close_bracket
            {
            pushFollow(FOLLOW_new__in_new_class487);
            new_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_class_name_in_new_class489);
            class_name();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_open_bracket_in_new_class491);
            open_bracket();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_arguments_in_new_class493);
            arguments();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_close_bracket_in_new_class495);
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
            if ( state.backtracking>0 ) { memoize(input, 16, new_class_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "new_class"


    // $ANTLR start "arguments"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:103:1: arguments : ( value ( comma value )* )? ;
    public final void arguments() throws RecognitionException {
        int arguments_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:104:2: ( ( value ( comma value )* )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:104:4: ( value ( comma value )* )?
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:104:4: ( value ( comma value )* )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==NEW||LA29_0==NULL||LA29_0==OPEN_BRACKET||(LA29_0>=BOOLEAN && LA29_0<=INT)||LA29_0==FLOAT||(LA29_0>=STRING && LA29_0<=CHAR)) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:104:5: value ( comma value )*
                    {
                    pushFollow(FOLLOW_value_in_arguments508);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:104:11: ( comma value )*
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==COMMA) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:104:12: comma value
                    	    {
                    	    pushFollow(FOLLOW_comma_in_arguments511);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_value_in_arguments513);
                    	    value();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop28;
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
            if ( state.backtracking>0 ) { memoize(input, 17, arguments_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "arguments"


    // $ANTLR start "code"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:107:1: code : ( statement | block_begin ( statement )* block_end );
    public final void code() throws RecognitionException {
        int code_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:107:6: ( statement | block_begin ( statement )* block_end )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==VOID||(LA31_0>=THIS && LA31_0<=SUPER)||LA31_0==TRY||LA31_0==AT||LA31_0==ID) ) {
                alt31=1;
            }
            else if ( (LA31_0==OPEN_CURLY_BRACKET) ) {
                alt31=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:107:8: statement
                    {
                    pushFollow(FOLLOW_statement_in_code527);
                    statement();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:108:4: block_begin ( statement )* block_end
                    {
                    pushFollow(FOLLOW_block_begin_in_code532);
                    block_begin();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:108:16: ( statement )*
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==VOID||(LA30_0>=THIS && LA30_0<=SUPER)||LA30_0==TRY||LA30_0==AT||LA30_0==ID) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: statement
                    	    {
                    	    pushFollow(FOLLOW_statement_in_code534);
                    	    statement();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop30;
                        }
                    } while (true);

                    pushFollow(FOLLOW_block_end_in_code537);
                    block_end();

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
            if ( state.backtracking>0 ) { memoize(input, 18, code_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "code"


    // $ANTLR start "statement"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:111:1: statement : ( variable_assignment semicolon | variable_def semicolon | method_call semicolon | try_catch );
    public final void statement() throws RecognitionException {
        int statement_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:112:2: ( variable_assignment semicolon | variable_def semicolon | method_call semicolon | try_catch )
            int alt32=4;
            switch ( input.LA(1) ) {
            case THIS:
            case SUPER:
                {
                alt32=1;
                }
                break;
            case ID:
                {
                int LA32_3 = input.LA(2);

                if ( (synpred47_JavaParser()) ) {
                    alt32=1;
                }
                else if ( (synpred48_JavaParser()) ) {
                    alt32=2;
                }
                else if ( (synpred49_JavaParser()) ) {
                    alt32=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 32, 3, input);

                    throw nvae;
                }
                }
                break;
            case VOID:
            case AT:
                {
                alt32=2;
                }
                break;
            case TRY:
                {
                alt32=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }

            switch (alt32) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:112:4: variable_assignment semicolon
                    {
                    pushFollow(FOLLOW_variable_assignment_in_statement549);
                    variable_assignment();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement551);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:113:4: variable_def semicolon
                    {
                    pushFollow(FOLLOW_variable_def_in_statement556);
                    variable_def();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement558);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:114:4: method_call semicolon
                    {
                    pushFollow(FOLLOW_method_call_in_statement563);
                    method_call();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement565);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:115:4: try_catch
                    {
                    pushFollow(FOLLOW_try_catch_in_statement570);
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
            if ( state.backtracking>0 ) { memoize(input, 19, statement_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "statement"


    // $ANTLR start "variable_assignment"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:118:1: variable_assignment : ( this_ dot | super_ dot )? variable_name assign value ;
    public final void variable_assignment() throws RecognitionException {
        int variable_assignment_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:2: ( ( this_ dot | super_ dot )? variable_name assign value )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:4: ( this_ dot | super_ dot )? variable_name assign value
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:4: ( this_ dot | super_ dot )?
            int alt33=3;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==THIS) ) {
                alt33=1;
            }
            else if ( (LA33_0==SUPER) ) {
                alt33=2;
            }
            switch (alt33) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:5: this_ dot
                    {
                    pushFollow(FOLLOW_this__in_variable_assignment583);
                    this_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_dot_in_variable_assignment585);
                    dot();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:17: super_ dot
                    {
                    pushFollow(FOLLOW_super__in_variable_assignment589);
                    super_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_dot_in_variable_assignment591);
                    dot();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_variable_name_in_variable_assignment595);
            variable_name();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_assign_in_variable_assignment597);
            assign();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_value_in_variable_assignment599);
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
            if ( state.backtracking>0 ) { memoize(input, 20, variable_assignment_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "variable_assignment"


    // $ANTLR start "try_catch"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:122:1: try_catch : try_ code ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )+ ( finally_ OPEN_BRACKET id id CLOSE_BRACKET code )? ;
    public final void try_catch() throws RecognitionException {
        int try_catch_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:123:2: ( try_ code ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )+ ( finally_ OPEN_BRACKET id id CLOSE_BRACKET code )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:123:4: try_ code ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )+ ( finally_ OPEN_BRACKET id id CLOSE_BRACKET code )?
            {
            pushFollow(FOLLOW_try__in_try_catch611);
            try_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_code_in_try_catch613);
            code();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:123:14: ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )+
            int cnt34=0;
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==CATCH) ) {
                    int LA34_2 = input.LA(2);

                    if ( (synpred52_JavaParser()) ) {
                        alt34=1;
                    }


                }


                switch (alt34) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:123:15: catch_ OPEN_BRACKET id id CLOSE_BRACKET code
            	    {
            	    pushFollow(FOLLOW_catch__in_try_catch616);
            	    catch_();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_try_catch618); if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_try_catch620);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_try_catch622);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_try_catch624); if (state.failed) return ;
            	    pushFollow(FOLLOW_code_in_try_catch626);
            	    code();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt34 >= 1 ) break loop34;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(34, input);
                        throw eee;
                }
                cnt34++;
            } while (true);

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:123:62: ( finally_ OPEN_BRACKET id id CLOSE_BRACKET code )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==FINALLY) ) {
                int LA35_1 = input.LA(2);

                if ( (synpred53_JavaParser()) ) {
                    alt35=1;
                }
            }
            switch (alt35) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:123:63: finally_ OPEN_BRACKET id id CLOSE_BRACKET code
                    {
                    pushFollow(FOLLOW_finally__in_try_catch631);
                    finally_();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_try_catch633); if (state.failed) return ;
                    pushFollow(FOLLOW_id_in_try_catch635);
                    id();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_id_in_try_catch637);
                    id();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_try_catch639); if (state.failed) return ;
                    pushFollow(FOLLOW_code_in_try_catch641);
                    code();

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
            if ( state.backtracking>0 ) { memoize(input, 21, try_catch_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "try_catch"


    // $ANTLR start "variable_type"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:127:1: variable_type : ( class_name ( array )? | void_ );
    public final void variable_type() throws RecognitionException {
        int variable_type_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:128:2: ( class_name ( array )? | void_ )
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==ID) ) {
                alt37=1;
            }
            else if ( (LA37_0==VOID) ) {
                alt37=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }
            switch (alt37) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:128:4: class_name ( array )?
                    {
                    pushFollow(FOLLOW_class_name_in_variable_type655);
                    class_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:128:15: ( array )?
                    int alt36=2;
                    int LA36_0 = input.LA(1);

                    if ( (LA36_0==OPEN_RECT_BRACKET) ) {
                        alt36=1;
                    }
                    switch (alt36) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: array
                            {
                            pushFollow(FOLLOW_array_in_variable_type657);
                            array();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:129:4: void_
                    {
                    pushFollow(FOLLOW_void__in_variable_type663);
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
            if ( state.backtracking>0 ) { memoize(input, 22, variable_type_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "variable_type"

    public static class id_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "id"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:132:1: id : ID ;
    public final JavaParser.id_return id() throws RecognitionException {
        JavaParser.id_return retval = new JavaParser.id_return();
        retval.start = input.LT(1);
        int id_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:132:4: ( ID )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:132:6: ID
            {
            match(input,ID,FOLLOW_ID_in_id673); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 23, id_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "id"

    public static class operator_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "operator"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:134:1: operator : ( PLUS | MINUS | STAR | SLASH );
    public final JavaParser.operator_return operator() throws RecognitionException {
        JavaParser.operator_return retval = new JavaParser.operator_return();
        retval.start = input.LT(1);
        int operator_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:134:9: ( PLUS | MINUS | STAR | SLASH )
            int alt38=4;
            switch ( input.LA(1) ) {
            case PLUS:
                {
                alt38=1;
                }
                break;
            case MINUS:
                {
                alt38=2;
                }
                break;
            case STAR:
                {
                alt38=3;
                }
                break;
            case SLASH:
                {
                alt38=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }

            switch (alt38) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:134:11: PLUS
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_operator682); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:135:4: MINUS
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_operator689); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:136:4: STAR
                    {
                    match(input,STAR,FOLLOW_STAR_in_operator696); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:137:4: SLASH
                    {
                    match(input,SLASH,FOLLOW_SLASH_in_operator703); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
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
            if ( state.backtracking>0 ) { memoize(input, 24, operator_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "operator"

    public static class int_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "int_const"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:144:1: int_const : INT ;
    public final JavaParser.int_const_return int_const() throws RecognitionException {
        JavaParser.int_const_return retval = new JavaParser.int_const_return();
        retval.start = input.LT(1);
        int int_const_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:145:2: ( INT )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:145:4: INT
            {
            match(input,INT,FOLLOW_INT_in_int_const720); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 25, int_const_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "int_const"

    public static class string_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "string_const"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:146:1: string_const : STRING ;
    public final JavaParser.string_const_return string_const() throws RecognitionException {
        JavaParser.string_const_return retval = new JavaParser.string_const_return();
        retval.start = input.LT(1);
        int string_const_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:147:2: ( STRING )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:147:4: STRING
            {
            match(input,STRING,FOLLOW_STRING_in_string_const730); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 26, string_const_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "string_const"

    public static class float_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "float_const"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:148:1: float_const : FLOAT ;
    public final JavaParser.float_const_return float_const() throws RecognitionException {
        JavaParser.float_const_return retval = new JavaParser.float_const_return();
        retval.start = input.LT(1);
        int float_const_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:149:2: ( FLOAT )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:149:4: FLOAT
            {
            match(input,FLOAT,FOLLOW_FLOAT_in_float_const740); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 27, float_const_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "float_const"

    public static class char_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "char_const"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:150:1: char_const : CHAR ;
    public final JavaParser.char_const_return char_const() throws RecognitionException {
        JavaParser.char_const_return retval = new JavaParser.char_const_return();
        retval.start = input.LT(1);
        int char_const_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:151:2: ( CHAR )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:151:4: CHAR
            {
            match(input,CHAR,FOLLOW_CHAR_in_char_const750); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 28, char_const_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "char_const"

    public static class null_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "null_const"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:152:1: null_const : NULL ;
    public final JavaParser.null_const_return null_const() throws RecognitionException {
        JavaParser.null_const_return retval = new JavaParser.null_const_return();
        retval.start = input.LT(1);
        int null_const_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:153:2: ( NULL )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:153:4: NULL
            {
            match(input,NULL,FOLLOW_NULL_in_null_const760); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 29, null_const_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "null_const"

    public static class boolean_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "boolean_const"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:154:1: boolean_const : BOOLEAN ;
    public final JavaParser.boolean_const_return boolean_const() throws RecognitionException {
        JavaParser.boolean_const_return retval = new JavaParser.boolean_const_return();
        retval.start = input.LT(1);
        int boolean_const_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:155:2: ( BOOLEAN )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:155:4: BOOLEAN
            {
            match(input,BOOLEAN,FOLLOW_BOOLEAN_in_boolean_const771); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 30, boolean_const_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "boolean_const"

    public static class package_name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "package_name"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:1: package_name : ( ID DOT )* ID ;
    public final JavaParser.package_name_return package_name() throws RecognitionException {
        JavaParser.package_name_return retval = new JavaParser.package_name_return();
        retval.start = input.LT(1);
        int package_name_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:162:2: ( ( ID DOT )* ID )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:162:4: ( ID DOT )* ID
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:162:4: ( ID DOT )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==ID) ) {
                    int LA39_1 = input.LA(2);

                    if ( (LA39_1==DOT) ) {
                        alt39=1;
                    }


                }


                switch (alt39) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:162:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_package_name787); if (state.failed) return retval;
            	    match(input,DOT,FOLLOW_DOT_in_package_name789); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop39;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_package_name793); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 31, package_name_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "package_name"

    public static class import_name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "import_name"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:165:1: import_name : ( ID DOT )+ ( ID | STAR ) ;
    public final JavaParser.import_name_return import_name() throws RecognitionException {
        JavaParser.import_name_return retval = new JavaParser.import_name_return();
        retval.start = input.LT(1);
        int import_name_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:166:2: ( ( ID DOT )+ ( ID | STAR ) )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:166:4: ( ID DOT )+ ( ID | STAR )
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:166:4: ( ID DOT )+
            int cnt40=0;
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==ID) ) {
                    int LA40_1 = input.LA(2);

                    if ( (LA40_1==DOT) ) {
                        alt40=1;
                    }


                }


                switch (alt40) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:166:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_import_name807); if (state.failed) return retval;
            	    match(input,DOT,FOLLOW_DOT_in_import_name809); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    if ( cnt40 >= 1 ) break loop40;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(40, input);
                        throw eee;
                }
                cnt40++;
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
            if ( state.backtracking>0 ) { memoize(input, 32, import_name_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "import_name"

    public static class class_name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "class_name"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:169:1: class_name : ( ID DOT )* ID ;
    public final JavaParser.class_name_return class_name() throws RecognitionException {
        JavaParser.class_name_return retval = new JavaParser.class_name_return();
        retval.start = input.LT(1);
        int class_name_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:170:2: ( ( ID DOT )* ID )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:170:4: ( ID DOT )* ID
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:170:4: ( ID DOT )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==ID) ) {
                    int LA41_1 = input.LA(2);

                    if ( (LA41_1==DOT) ) {
                        int LA41_2 = input.LA(3);

                        if ( (LA41_2==ID) ) {
                            alt41=1;
                        }


                    }


                }


                switch (alt41) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:170:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_class_name831); if (state.failed) return retval;
            	    match(input,DOT,FOLLOW_DOT_in_class_name833); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_class_name837); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 33, class_name_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "class_name"

    public static class method_name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "method_name"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:173:1: method_name : ( ID DOT )* ID ;
    public final JavaParser.method_name_return method_name() throws RecognitionException {
        JavaParser.method_name_return retval = new JavaParser.method_name_return();
        retval.start = input.LT(1);
        int method_name_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:174:2: ( ( ID DOT )* ID )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:174:4: ( ID DOT )* ID
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:174:4: ( ID DOT )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==ID) ) {
                    int LA42_1 = input.LA(2);

                    if ( (LA42_1==DOT) ) {
                        alt42=1;
                    }


                }


                switch (alt42) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:174:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_method_name851); if (state.failed) return retval;
            	    match(input,DOT,FOLLOW_DOT_in_method_name853); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop42;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_method_name857); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 34, method_name_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "method_name"


    // $ANTLR start "variable_name"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:177:1: variable_name : name ( array )? ;
    public final void variable_name() throws RecognitionException {
        int variable_name_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 35) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:178:2: ( name ( array )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:178:4: name ( array )?
            {
            pushFollow(FOLLOW_name_in_variable_name870);
            name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:178:9: ( array )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==OPEN_RECT_BRACKET) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: array
                    {
                    pushFollow(FOLLOW_array_in_variable_name872);
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
            if ( state.backtracking>0 ) { memoize(input, 35, variable_name_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "variable_name"

    public static class annotation_name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "annotation_name"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:180:1: annotation_name : AT name ;
    public final JavaParser.annotation_name_return annotation_name() throws RecognitionException {
        JavaParser.annotation_name_return retval = new JavaParser.annotation_name_return();
        retval.start = input.LT(1);
        int annotation_name_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 36) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:181:2: ( AT name )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:181:4: AT name
            {
            match(input,AT,FOLLOW_AT_in_annotation_name883); if (state.failed) return retval;
            pushFollow(FOLLOW_name_in_annotation_name885);
            name();

            state._fsp--;
            if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 36, annotation_name_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "annotation_name"

    public static class name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "name"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:182:1: name : ( ID DOT )* ID ;
    public final JavaParser.name_return name() throws RecognitionException {
        JavaParser.name_return retval = new JavaParser.name_return();
        retval.start = input.LT(1);
        int name_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 37) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:182:6: ( ( ID DOT )* ID )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:182:8: ( ID DOT )* ID
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:182:8: ( ID DOT )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==ID) ) {
                    int LA44_1 = input.LA(2);

                    if ( (LA44_1==DOT) ) {
                        alt44=1;
                    }


                }


                switch (alt44) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:182:9: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_name895); if (state.failed) return retval;
            	    match(input,DOT,FOLLOW_DOT_in_name897); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop44;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_name901); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 37, name_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "name"


    // $ANTLR start "array"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:184:1: array : open_rect_bracket ( value )? close_rect_bracket ;
    public final void array() throws RecognitionException {
        int array_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:184:7: ( open_rect_bracket ( value )? close_rect_bracket )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:184:9: open_rect_bracket ( value )? close_rect_bracket
            {
            pushFollow(FOLLOW_open_rect_bracket_in_array911);
            open_rect_bracket();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:184:27: ( value )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==NEW||LA45_0==NULL||LA45_0==OPEN_BRACKET||(LA45_0>=BOOLEAN && LA45_0<=INT)||LA45_0==FLOAT||(LA45_0>=STRING && LA45_0<=CHAR)) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: value
                    {
                    pushFollow(FOLLOW_value_in_array913);
                    value();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_close_rect_bracket_in_array916);
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
            if ( state.backtracking>0 ) { memoize(input, 38, array_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "array"

    public static class package__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "package_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:190:1: package_ : PACKAGE ;
    public final JavaParser.package__return package_() throws RecognitionException {
        JavaParser.package__return retval = new JavaParser.package__return();
        retval.start = input.LT(1);
        int package__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:191:2: ( PACKAGE )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:191:4: PACKAGE
            {
            match(input,PACKAGE,FOLLOW_PACKAGE_in_package_929); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 39, package__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "package_"

    public static class import__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "import_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:192:1: import_ : IMPORT ;
    public final JavaParser.import__return import_() throws RecognitionException {
        JavaParser.import__return retval = new JavaParser.import__return();
        retval.start = input.LT(1);
        int import__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 40) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:193:2: ( IMPORT )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:193:4: IMPORT
            {
            match(input,IMPORT,FOLLOW_IMPORT_in_import_940); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 40, import__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "import_"

    public static class class__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "class_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:194:1: class_ : CLASS ;
    public final JavaParser.class__return class_() throws RecognitionException {
        JavaParser.class__return retval = new JavaParser.class__return();
        retval.start = input.LT(1);
        int class__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:194:8: ( CLASS )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:194:10: CLASS
            {
            match(input,CLASS,FOLLOW_CLASS_in_class_950); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 41, class__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "class_"

    public static class extends__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "extends_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:195:1: extends_ : EXTENDS ;
    public final JavaParser.extends__return extends_() throws RecognitionException {
        JavaParser.extends__return retval = new JavaParser.extends__return();
        retval.start = input.LT(1);
        int extends__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 42) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:195:9: ( EXTENDS )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:195:11: EXTENDS
            {
            match(input,EXTENDS,FOLLOW_EXTENDS_in_extends_958); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 42, extends__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "extends_"

    public static class implements__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "implements_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:196:1: implements_ : IMPLEMENTS ;
    public final JavaParser.implements__return implements_() throws RecognitionException {
        JavaParser.implements__return retval = new JavaParser.implements__return();
        retval.start = input.LT(1);
        int implements__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 43) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:197:2: ( IMPLEMENTS )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:197:4: IMPLEMENTS
            {
            match(input,IMPLEMENTS,FOLLOW_IMPLEMENTS_in_implements_968); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 43, implements__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "implements_"

    public static class this__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "this_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:198:1: this_ : THIS ;
    public final JavaParser.this__return this_() throws RecognitionException {
        JavaParser.this__return retval = new JavaParser.this__return();
        retval.start = input.LT(1);
        int this__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 44) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:198:7: ( THIS )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:198:9: THIS
            {
            match(input,THIS,FOLLOW_THIS_in_this_977); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 44, this__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "this_"

    public static class super__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "super_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:199:1: super_ : SUPER ;
    public final JavaParser.super__return super_() throws RecognitionException {
        JavaParser.super__return retval = new JavaParser.super__return();
        retval.start = input.LT(1);
        int super__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 45) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:199:8: ( SUPER )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:199:10: SUPER
            {
            match(input,SUPER,FOLLOW_SUPER_in_super_986); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 45, super__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "super_"

    public static class void__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "void_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:200:1: void_ : VOID ;
    public final JavaParser.void__return void_() throws RecognitionException {
        JavaParser.void__return retval = new JavaParser.void__return();
        retval.start = input.LT(1);
        int void__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 46) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:200:7: ( VOID )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:200:9: VOID
            {
            match(input,VOID,FOLLOW_VOID_in_void_995); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 46, void__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "void_"

    public static class public__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "public_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:201:1: public_ : PUBLIC ;
    public final JavaParser.public__return public_() throws RecognitionException {
        JavaParser.public__return retval = new JavaParser.public__return();
        retval.start = input.LT(1);
        int public__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 47) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:201:9: ( PUBLIC )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:201:11: PUBLIC
            {
            match(input,PUBLIC,FOLLOW_PUBLIC_in_public_1004); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 47, public__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "public_"

    public static class private__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "private_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:202:1: private_ : PRIVATE ;
    public final JavaParser.private__return private_() throws RecognitionException {
        JavaParser.private__return retval = new JavaParser.private__return();
        retval.start = input.LT(1);
        int private__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 48) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:202:9: ( PRIVATE )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:202:11: PRIVATE
            {
            match(input,PRIVATE,FOLLOW_PRIVATE_in_private_1012); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 48, private__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "private_"

    public static class protected__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "protected_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:203:1: protected_ : PROTECTED ;
    public final JavaParser.protected__return protected_() throws RecognitionException {
        JavaParser.protected__return retval = new JavaParser.protected__return();
        retval.start = input.LT(1);
        int protected__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 49) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:204:2: ( PROTECTED )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:204:4: PROTECTED
            {
            match(input,PROTECTED,FOLLOW_PROTECTED_in_protected_1022); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 49, protected__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "protected_"

    public static class static__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "static_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:205:1: static_ : STATIC ;
    public final JavaParser.static__return static_() throws RecognitionException {
        JavaParser.static__return retval = new JavaParser.static__return();
        retval.start = input.LT(1);
        int static__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 50) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:205:9: ( STATIC )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:205:11: STATIC
            {
            match(input,STATIC,FOLLOW_STATIC_in_static_1031); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 50, static__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "static_"

    public static class final__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "final_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:206:1: final_ : FINAL ;
    public final JavaParser.final__return final_() throws RecognitionException {
        JavaParser.final__return retval = new JavaParser.final__return();
        retval.start = input.LT(1);
        int final__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 51) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:206:8: ( FINAL )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:206:10: FINAL
            {
            match(input,FINAL,FOLLOW_FINAL_in_final_1040); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 51, final__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "final_"

    public static class transient__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "transient_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:207:1: transient_ : TRANSIENT ;
    public final JavaParser.transient__return transient_() throws RecognitionException {
        JavaParser.transient__return retval = new JavaParser.transient__return();
        retval.start = input.LT(1);
        int transient__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 52) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:208:2: ( TRANSIENT )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:208:4: TRANSIENT
            {
            match(input,TRANSIENT,FOLLOW_TRANSIENT_in_transient_1050); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 52, transient__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "transient_"

    public static class new__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "new_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:209:1: new_ : NEW ;
    public final JavaParser.new__return new_() throws RecognitionException {
        JavaParser.new__return retval = new JavaParser.new__return();
        retval.start = input.LT(1);
        int new__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 53) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:209:6: ( NEW )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:209:8: NEW
            {
            match(input,NEW,FOLLOW_NEW_in_new_1059); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 53, new__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "new_"

    public static class try__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "try_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:210:1: try_ : TRY ;
    public final JavaParser.try__return try_() throws RecognitionException {
        JavaParser.try__return retval = new JavaParser.try__return();
        retval.start = input.LT(1);
        int try__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 54) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:210:6: ( TRY )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:210:8: TRY
            {
            match(input,TRY,FOLLOW_TRY_in_try_1068); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 54, try__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "try_"

    public static class catch__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "catch_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:211:1: catch_ : CATCH ;
    public final JavaParser.catch__return catch_() throws RecognitionException {
        JavaParser.catch__return retval = new JavaParser.catch__return();
        retval.start = input.LT(1);
        int catch__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 55) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:211:8: ( CATCH )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:211:10: CATCH
            {
            match(input,CATCH,FOLLOW_CATCH_in_catch_1077); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 55, catch__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "catch_"

    public static class finally__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "finally_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:212:1: finally_ : FINALLY ;
    public final JavaParser.finally__return finally_() throws RecognitionException {
        JavaParser.finally__return retval = new JavaParser.finally__return();
        retval.start = input.LT(1);
        int finally__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 56) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:212:9: ( FINALLY )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:212:11: FINALLY
            {
            match(input,FINALLY,FOLLOW_FINALLY_in_finally_1085); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 56, finally__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "finally_"

    public static class semicolon_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "semicolon"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:218:1: semicolon : SEMICOLON ;
    public final JavaParser.semicolon_return semicolon() throws RecognitionException {
        JavaParser.semicolon_return retval = new JavaParser.semicolon_return();
        retval.start = input.LT(1);
        int semicolon_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 57) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:219:2: ( SEMICOLON )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:219:4: SEMICOLON
            {
            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_semicolon1100); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 57, semicolon_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "semicolon"

    public static class comma_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "comma"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:220:1: comma : COMMA ;
    public final JavaParser.comma_return comma() throws RecognitionException {
        JavaParser.comma_return retval = new JavaParser.comma_return();
        retval.start = input.LT(1);
        int comma_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 58) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:220:7: ( COMMA )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:220:9: COMMA
            {
            match(input,COMMA,FOLLOW_COMMA_in_comma1109); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 58, comma_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "comma"

    public static class dot_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "dot"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:221:1: dot : DOT ;
    public final JavaParser.dot_return dot() throws RecognitionException {
        JavaParser.dot_return retval = new JavaParser.dot_return();
        retval.start = input.LT(1);
        int dot_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 59) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:221:5: ( DOT )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:221:7: DOT
            {
            match(input,DOT,FOLLOW_DOT_in_dot1118); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 59, dot_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "dot"

    public static class assign_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "assign"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:222:1: assign : ASSIGN ;
    public final JavaParser.assign_return assign() throws RecognitionException {
        JavaParser.assign_return retval = new JavaParser.assign_return();
        retval.start = input.LT(1);
        int assign_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 60) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:222:8: ( ASSIGN )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:222:10: ASSIGN
            {
            match(input,ASSIGN,FOLLOW_ASSIGN_in_assign1127); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 60, assign_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "assign"


    // $ANTLR start "block_begin"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:223:1: block_begin : OPEN_CURLY_BRACKET ;
    public final void block_begin() throws RecognitionException {
        int block_begin_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 61) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:224:2: ( OPEN_CURLY_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:224:4: OPEN_CURLY_BRACKET
            {
            match(input,OPEN_CURLY_BRACKET,FOLLOW_OPEN_CURLY_BRACKET_in_block_begin1137); if (state.failed) return ;
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
            if ( state.backtracking>0 ) { memoize(input, 61, block_begin_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "block_begin"


    // $ANTLR start "block_end"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:225:1: block_end : CLOSE_CURLY_BRACKET ;
    public final void block_end() throws RecognitionException {
        int block_end_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 62) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:226:2: ( CLOSE_CURLY_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:226:4: CLOSE_CURLY_BRACKET
            {
            match(input,CLOSE_CURLY_BRACKET,FOLLOW_CLOSE_CURLY_BRACKET_in_block_end1147); if (state.failed) return ;
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
            if ( state.backtracking>0 ) { memoize(input, 62, block_end_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "block_end"


    // $ANTLR start "open_bracket"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:227:1: open_bracket : OPEN_BRACKET ;
    public final void open_bracket() throws RecognitionException {
        int open_bracket_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 63) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:228:2: ( OPEN_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:228:4: OPEN_BRACKET
            {
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_open_bracket1157); if (state.failed) return ;
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
            if ( state.backtracking>0 ) { memoize(input, 63, open_bracket_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "open_bracket"


    // $ANTLR start "close_bracket"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:229:1: close_bracket : CLOSE_BRACKET ;
    public final void close_bracket() throws RecognitionException {
        int close_bracket_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 64) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:230:2: ( CLOSE_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:230:4: CLOSE_BRACKET
            {
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_close_bracket1167); if (state.failed) return ;
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
            if ( state.backtracking>0 ) { memoize(input, 64, close_bracket_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "close_bracket"


    // $ANTLR start "open_rect_bracket"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:231:1: open_rect_bracket : OPEN_RECT_BRACKET ;
    public final void open_rect_bracket() throws RecognitionException {
        int open_rect_bracket_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 65) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:232:2: ( OPEN_RECT_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:232:4: OPEN_RECT_BRACKET
            {
            match(input,OPEN_RECT_BRACKET,FOLLOW_OPEN_RECT_BRACKET_in_open_rect_bracket1178); if (state.failed) return ;
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
            if ( state.backtracking>0 ) { memoize(input, 65, open_rect_bracket_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "open_rect_bracket"


    // $ANTLR start "close_rect_bracket"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:233:1: close_rect_bracket : CLOSE_RECT_BRACKET ;
    public final void close_rect_bracket() throws RecognitionException {
        int close_rect_bracket_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 66) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:234:2: ( CLOSE_RECT_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:234:4: CLOSE_RECT_BRACKET
            {
            match(input,CLOSE_RECT_BRACKET,FOLLOW_CLOSE_RECT_BRACKET_in_close_rect_bracket1188); if (state.failed) return ;
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
            if ( state.backtracking>0 ) { memoize(input, 66, close_rect_bracket_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "close_rect_bracket"

    // $ANTLR start synpred24_JavaParser
    public final void synpred24_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:17: ( class_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:17: class_def
        {
        pushFollow(FOLLOW_class_def_in_synpred24_JavaParser311);
        class_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred24_JavaParser

    // $ANTLR start synpred25_JavaParser
    public final void synpred25_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:29: ( constructor_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:29: constructor_def
        {
        pushFollow(FOLLOW_constructor_def_in_synpred25_JavaParser315);
        constructor_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred25_JavaParser

    // $ANTLR start synpred26_JavaParser
    public final void synpred26_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:47: ( method_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:47: method_def
        {
        pushFollow(FOLLOW_method_def_in_synpred26_JavaParser319);
        method_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred26_JavaParser

    // $ANTLR start synpred27_JavaParser
    public final void synpred27_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:60: ( field_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:60: field_def
        {
        pushFollow(FOLLOW_field_def_in_synpred27_JavaParser323);
        field_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred27_JavaParser

    // $ANTLR start synpred36_JavaParser
    public final void synpred36_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:88:21: ( close_bracket )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:88:21: close_bracket
        {
        pushFollow(FOLLOW_close_bracket_in_synpred36_JavaParser438);
        close_bracket();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred36_JavaParser

    // $ANTLR start synpred37_JavaParser
    public final void synpred37_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:88:6: ( operator value ( close_bracket )? )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:88:6: operator value ( close_bracket )?
        {
        pushFollow(FOLLOW_operator_in_synpred37_JavaParser434);
        operator();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_value_in_synpred37_JavaParser436);
        value();

        state._fsp--;
        if (state.failed) return ;
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:88:21: ( close_bracket )?
        int alt49=2;
        int LA49_0 = input.LA(1);

        if ( (LA49_0==CLOSE_BRACKET) ) {
            alt49=1;
        }
        switch (alt49) {
            case 1 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: close_bracket
                {
                pushFollow(FOLLOW_close_bracket_in_synpred37_JavaParser438);
                close_bracket();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred37_JavaParser

    // $ANTLR start synpred47_JavaParser
    public final void synpred47_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:112:4: ( variable_assignment semicolon )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:112:4: variable_assignment semicolon
        {
        pushFollow(FOLLOW_variable_assignment_in_synpred47_JavaParser549);
        variable_assignment();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_semicolon_in_synpred47_JavaParser551);
        semicolon();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred47_JavaParser

    // $ANTLR start synpred48_JavaParser
    public final void synpred48_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:113:4: ( variable_def semicolon )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:113:4: variable_def semicolon
        {
        pushFollow(FOLLOW_variable_def_in_synpred48_JavaParser556);
        variable_def();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_semicolon_in_synpred48_JavaParser558);
        semicolon();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred48_JavaParser

    // $ANTLR start synpred49_JavaParser
    public final void synpred49_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:114:4: ( method_call semicolon )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:114:4: method_call semicolon
        {
        pushFollow(FOLLOW_method_call_in_synpred49_JavaParser563);
        method_call();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_semicolon_in_synpred49_JavaParser565);
        semicolon();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred49_JavaParser

    // $ANTLR start synpred52_JavaParser
    public final void synpred52_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:123:15: ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:123:15: catch_ OPEN_BRACKET id id CLOSE_BRACKET code
        {
        pushFollow(FOLLOW_catch__in_synpred52_JavaParser616);
        catch_();

        state._fsp--;
        if (state.failed) return ;
        match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_synpred52_JavaParser618); if (state.failed) return ;
        pushFollow(FOLLOW_id_in_synpred52_JavaParser620);
        id();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_id_in_synpred52_JavaParser622);
        id();

        state._fsp--;
        if (state.failed) return ;
        match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_synpred52_JavaParser624); if (state.failed) return ;
        pushFollow(FOLLOW_code_in_synpred52_JavaParser626);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred52_JavaParser

    // $ANTLR start synpred53_JavaParser
    public final void synpred53_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:123:63: ( finally_ OPEN_BRACKET id id CLOSE_BRACKET code )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:123:63: finally_ OPEN_BRACKET id id CLOSE_BRACKET code
        {
        pushFollow(FOLLOW_finally__in_synpred53_JavaParser631);
        finally_();

        state._fsp--;
        if (state.failed) return ;
        match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_synpred53_JavaParser633); if (state.failed) return ;
        pushFollow(FOLLOW_id_in_synpred53_JavaParser635);
        id();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_id_in_synpred53_JavaParser637);
        id();

        state._fsp--;
        if (state.failed) return ;
        match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_synpred53_JavaParser639); if (state.failed) return ;
        pushFollow(FOLLOW_code_in_synpred53_JavaParser641);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred53_JavaParser

    // Delegated rules

    public final boolean synpred25_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred25_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred49_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred49_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred48_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred48_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred52_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred52_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred47_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred47_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred24_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred24_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred26_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred26_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred53_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred53_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred27_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred27_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA20 dfa20 = new DFA20(this);
    protected DFA24 dfa24 = new DFA24(this);
    static final String DFA20_eotS =
        "\17\uffff";
    static final String DFA20_eofS =
        "\17\uffff";
    static final String DFA20_minS =
        "\1\6\1\uffff\7\0\1\uffff\2\0\3\uffff";
    static final String DFA20_maxS =
        "\1\74\1\uffff\7\0\1\uffff\2\0\3\uffff";
    static final String DFA20_acceptS =
        "\1\uffff\1\5\7\uffff\1\1\2\uffff\1\2\1\3\1\4";
    static final String DFA20_specialS =
        "\2\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\7\1\10\3\uffff}>";
    static final String[] DFA20_transitionS = {
            "\1\11\2\uffff\1\13\17\uffff\1\3\1\4\1\5\1\7\1\6\1\10\1\uffff"+
            "\1\1\30\uffff\1\2\2\uffff\1\12",
            "",
            "\1\uffff",
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

    static final short[] DFA20_eot = DFA.unpackEncodedString(DFA20_eotS);
    static final short[] DFA20_eof = DFA.unpackEncodedString(DFA20_eofS);
    static final char[] DFA20_min = DFA.unpackEncodedStringToUnsignedChars(DFA20_minS);
    static final char[] DFA20_max = DFA.unpackEncodedStringToUnsignedChars(DFA20_maxS);
    static final short[] DFA20_accept = DFA.unpackEncodedString(DFA20_acceptS);
    static final short[] DFA20_special = DFA.unpackEncodedString(DFA20_specialS);
    static final short[][] DFA20_transition;

    static {
        int numStates = DFA20_transitionS.length;
        DFA20_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA20_transition[i] = DFA.unpackEncodedString(DFA20_transitionS[i]);
        }
    }

    class DFA20 extends DFA {

        public DFA20(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 20;
            this.eot = DFA20_eot;
            this.eof = DFA20_eof;
            this.min = DFA20_min;
            this.max = DFA20_max;
            this.accept = DFA20_accept;
            this.special = DFA20_special;
            this.transition = DFA20_transition;
        }
        public String getDescription() {
            return "()* loopback of 70:16: ( class_def | constructor_def | method_def | field_def )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA20_2 = input.LA(1);

                         
                        int index20_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred24_JavaParser()) ) {s = 9;}

                        else if ( (synpred25_JavaParser()) ) {s = 12;}

                        else if ( (synpred26_JavaParser()) ) {s = 13;}

                        else if ( (synpred27_JavaParser()) ) {s = 14;}

                         
                        input.seek(index20_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA20_3 = input.LA(1);

                         
                        int index20_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred24_JavaParser()) ) {s = 9;}

                        else if ( (synpred25_JavaParser()) ) {s = 12;}

                        else if ( (synpred26_JavaParser()) ) {s = 13;}

                        else if ( (synpred27_JavaParser()) ) {s = 14;}

                         
                        input.seek(index20_3);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA20_4 = input.LA(1);

                         
                        int index20_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred24_JavaParser()) ) {s = 9;}

                        else if ( (synpred25_JavaParser()) ) {s = 12;}

                        else if ( (synpred26_JavaParser()) ) {s = 13;}

                        else if ( (synpred27_JavaParser()) ) {s = 14;}

                         
                        input.seek(index20_4);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA20_5 = input.LA(1);

                         
                        int index20_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred24_JavaParser()) ) {s = 9;}

                        else if ( (synpred25_JavaParser()) ) {s = 12;}

                        else if ( (synpred26_JavaParser()) ) {s = 13;}

                        else if ( (synpred27_JavaParser()) ) {s = 14;}

                         
                        input.seek(index20_5);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA20_6 = input.LA(1);

                         
                        int index20_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred24_JavaParser()) ) {s = 9;}

                        else if ( (synpred25_JavaParser()) ) {s = 12;}

                        else if ( (synpred26_JavaParser()) ) {s = 13;}

                        else if ( (synpred27_JavaParser()) ) {s = 14;}

                         
                        input.seek(index20_6);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA20_7 = input.LA(1);

                         
                        int index20_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred24_JavaParser()) ) {s = 9;}

                        else if ( (synpred25_JavaParser()) ) {s = 12;}

                        else if ( (synpred26_JavaParser()) ) {s = 13;}

                        else if ( (synpred27_JavaParser()) ) {s = 14;}

                         
                        input.seek(index20_7);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA20_8 = input.LA(1);

                         
                        int index20_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred24_JavaParser()) ) {s = 9;}

                        else if ( (synpred25_JavaParser()) ) {s = 12;}

                        else if ( (synpred26_JavaParser()) ) {s = 13;}

                        else if ( (synpred27_JavaParser()) ) {s = 14;}

                         
                        input.seek(index20_8);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA20_10 = input.LA(1);

                         
                        int index20_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred25_JavaParser()) ) {s = 12;}

                        else if ( (synpred26_JavaParser()) ) {s = 13;}

                        else if ( (synpred27_JavaParser()) ) {s = 14;}

                         
                        input.seek(index20_10);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA20_11 = input.LA(1);

                         
                        int index20_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred26_JavaParser()) ) {s = 13;}

                        else if ( (synpred27_JavaParser()) ) {s = 14;}

                         
                        input.seek(index20_11);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 20, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA24_eotS =
        "\11\uffff";
    static final String DFA24_eofS =
        "\3\uffff\1\7\5\uffff";
    static final String DFA24_minS =
        "\1\12\2\uffff\1\41\1\uffff\1\6\3\uffff";
    static final String DFA24_maxS =
        "\1\105\2\uffff\1\70\1\uffff\1\74\3\uffff";
    static final String DFA24_acceptS =
        "\1\uffff\1\1\1\2\1\uffff\1\6\1\uffff\1\5\1\4\1\3";
    static final String DFA24_specialS =
        "\11\uffff}>";
    static final String[] DFA24_transitionS = {
            "\1\4\1\uffff\1\2\50\uffff\1\1\5\uffff\1\2\1\3\1\2\1\uffff\1"+
            "\2\4\uffff\2\2",
            "",
            "",
            "\4\7\12\uffff\1\5\1\7\2\uffff\2\7\1\6\1\7\1\uffff\1\7",
            "",
            "\1\10\65\uffff\1\3",
            "",
            "",
            ""
    };

    static final short[] DFA24_eot = DFA.unpackEncodedString(DFA24_eotS);
    static final short[] DFA24_eof = DFA.unpackEncodedString(DFA24_eofS);
    static final char[] DFA24_min = DFA.unpackEncodedStringToUnsignedChars(DFA24_minS);
    static final char[] DFA24_max = DFA.unpackEncodedStringToUnsignedChars(DFA24_maxS);
    static final short[] DFA24_accept = DFA.unpackEncodedString(DFA24_acceptS);
    static final short[] DFA24_special = DFA.unpackEncodedString(DFA24_specialS);
    static final short[][] DFA24_transition;

    static {
        int numStates = DFA24_transitionS.length;
        DFA24_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA24_transition[i] = DFA.unpackEncodedString(DFA24_transitionS[i]);
        }
    }

    class DFA24 extends DFA {

        public DFA24(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 24;
            this.eot = DFA24_eot;
            this.eof = DFA24_eof;
            this.min = DFA24_min;
            this.max = DFA24_max;
            this.accept = DFA24_accept;
            this.special = DFA24_special;
            this.transition = DFA24_transition;
        }
        public String getDescription() {
            return "82:2: ( open_bracket | constant | class_name dot class_ | variable_name | method_call | new_class )";
        }
    }
 

    public static final BitSet FOLLOW_package_def_in_file44 = new BitSet(new long[]{0x020000007E000062L});
    public static final BitSet FOLLOW_import_def_in_file46 = new BitSet(new long[]{0x020000007E000062L});
    public static final BitSet FOLLOW_class_def_in_file49 = new BitSet(new long[]{0x020000007E000042L});
    public static final BitSet FOLLOW_package__in_package_def60 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_package_name_in_package_def62 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_semicolon_in_package_def64 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_import__in_import_def76 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_import_name_in_import_def78 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_semicolon_in_import_def80 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_class_def92 = new BitSet(new long[]{0x020000007E000040L});
    public static final BitSet FOLLOW_modifier_in_class_def95 = new BitSet(new long[]{0x020000007E000040L});
    public static final BitSet FOLLOW_class__in_class_def98 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_id_in_class_def100 = new BitSet(new long[]{0x0000000080000180L});
    public static final BitSet FOLLOW_extends__in_class_def103 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_class_name_in_class_def105 = new BitSet(new long[]{0x0000000080000180L});
    public static final BitSet FOLLOW_implements__in_class_def110 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_class_name_in_class_def112 = new BitSet(new long[]{0x0001000080000180L});
    public static final BitSet FOLLOW_comma_in_class_def115 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_class_name_in_class_def117 = new BitSet(new long[]{0x0001000080000180L});
    public static final BitSet FOLLOW_class_block_in_class_def123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_constructor_def139 = new BitSet(new long[]{0x120000007E000000L});
    public static final BitSet FOLLOW_modifier_in_constructor_def142 = new BitSet(new long[]{0x100000007E000000L});
    public static final BitSet FOLLOW_id_in_constructor_def145 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_open_bracket_in_constructor_def147 = new BitSet(new long[]{0x1240000000000200L});
    public static final BitSet FOLLOW_argument_def_in_constructor_def149 = new BitSet(new long[]{0x1240000000000200L});
    public static final BitSet FOLLOW_close_bracket_in_constructor_def151 = new BitSet(new long[]{0x1200000080406380L});
    public static final BitSet FOLLOW_code_in_constructor_def153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_method_def168 = new BitSet(new long[]{0x120000007E000200L});
    public static final BitSet FOLLOW_modifier_in_method_def171 = new BitSet(new long[]{0x120000007E000200L});
    public static final BitSet FOLLOW_variable_type_in_method_def174 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_id_in_method_def176 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_open_bracket_in_method_def178 = new BitSet(new long[]{0x1240000000000200L});
    public static final BitSet FOLLOW_argument_def_in_method_def180 = new BitSet(new long[]{0x1240000000000200L});
    public static final BitSet FOLLOW_close_bracket_in_method_def182 = new BitSet(new long[]{0x1200000080406380L});
    public static final BitSet FOLLOW_code_in_method_def184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_field_def200 = new BitSet(new long[]{0x120000007E000200L});
    public static final BitSet FOLLOW_modifier_in_field_def203 = new BitSet(new long[]{0x120000007E000200L});
    public static final BitSet FOLLOW_variable_type_in_field_def206 = new BitSet(new long[]{0x1000000000006000L});
    public static final BitSet FOLLOW_variable_name_in_field_def208 = new BitSet(new long[]{0x0100008000000000L});
    public static final BitSet FOLLOW_assign_in_field_def211 = new BitSet(new long[]{0xB820000000007400L,0x0000000000000030L});
    public static final BitSet FOLLOW_value_in_field_def213 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_semicolon_in_field_def217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_argument_def229 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_comma_in_argument_def232 = new BitSet(new long[]{0x1200000000000200L});
    public static final BitSet FOLLOW_variable_def_in_argument_def234 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_annotation_in_variable_def249 = new BitSet(new long[]{0x1200000000000200L});
    public static final BitSet FOLLOW_variable_type_in_variable_def252 = new BitSet(new long[]{0x1000000000006000L});
    public static final BitSet FOLLOW_variable_name_in_variable_def254 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_assign_in_variable_def257 = new BitSet(new long[]{0xB820000000007400L,0x0000000000000030L});
    public static final BitSet FOLLOW_value_in_variable_def259 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_public__in_modifier271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_private__in_modifier276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_protected__in_modifier281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_static__in_modifier286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_final__in_modifier291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_transient__in_modifier296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_begin_in_class_block308 = new BitSet(new long[]{0x120000017E000240L});
    public static final BitSet FOLLOW_class_def_in_class_block311 = new BitSet(new long[]{0x120000017E000240L});
    public static final BitSet FOLLOW_constructor_def_in_class_block315 = new BitSet(new long[]{0x120000017E000240L});
    public static final BitSet FOLLOW_method_def_in_class_block319 = new BitSet(new long[]{0x120000017E000240L});
    public static final BitSet FOLLOW_field_def_in_class_block323 = new BitSet(new long[]{0x120000017E000240L});
    public static final BitSet FOLLOW_block_end_in_class_block327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_name_in_method_call339 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_open_bracket_in_method_call341 = new BitSet(new long[]{0xBA60000000007600L,0x0000000000000030L});
    public static final BitSet FOLLOW_arguments_in_method_call343 = new BitSet(new long[]{0x1240000000000200L});
    public static final BitSet FOLLOW_close_bracket_in_method_call345 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_dot_in_method_call348 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_id_in_method_call350 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_open_bracket_in_method_call352 = new BitSet(new long[]{0xBA60000000007600L,0x0000000000000030L});
    public static final BitSet FOLLOW_arguments_in_method_call354 = new BitSet(new long[]{0x1240000000000200L});
    public static final BitSet FOLLOW_close_bracket_in_method_call356 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_annotation_name_in_annotation369 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_open_bracket_in_annotation372 = new BitSet(new long[]{0xB820000000007400L,0x0000000000000030L});
    public static final BitSet FOLLOW_value_in_annotation374 = new BitSet(new long[]{0x1241000000000200L});
    public static final BitSet FOLLOW_comma_in_annotation377 = new BitSet(new long[]{0xB820000000007400L,0x0000000000000030L});
    public static final BitSet FOLLOW_value_in_annotation379 = new BitSet(new long[]{0x1241000000000200L});
    public static final BitSet FOLLOW_close_bracket_in_annotation383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_open_bracket_in_value398 = new BitSet(new long[]{0x0000001E00000002L});
    public static final BitSet FOLLOW_constant_in_value403 = new BitSet(new long[]{0x0000001E00000002L});
    public static final BitSet FOLLOW_class_name_in_value408 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_dot_in_value410 = new BitSet(new long[]{0x020000007E000040L});
    public static final BitSet FOLLOW_class__in_value412 = new BitSet(new long[]{0x0000001E00000002L});
    public static final BitSet FOLLOW_variable_name_in_value417 = new BitSet(new long[]{0x0000001E00000002L});
    public static final BitSet FOLLOW_method_call_in_value422 = new BitSet(new long[]{0x0000001E00000002L});
    public static final BitSet FOLLOW_new_class_in_value427 = new BitSet(new long[]{0x0000001E00000002L});
    public static final BitSet FOLLOW_operator_in_value434 = new BitSet(new long[]{0xB820000000007400L,0x0000000000000030L});
    public static final BitSet FOLLOW_value_in_value436 = new BitSet(new long[]{0x1240001E00000202L});
    public static final BitSet FOLLOW_close_bracket_in_value438 = new BitSet(new long[]{0x0000001E00000002L});
    public static final BitSet FOLLOW_int_const_in_constant450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_const_in_constant455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_float_const_in_constant460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_char_const_in_constant465 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_null_const_in_constant470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_boolean_const_in_constant475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_new__in_new_class487 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_class_name_in_new_class489 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_open_bracket_in_new_class491 = new BitSet(new long[]{0xBA60000000007600L,0x0000000000000030L});
    public static final BitSet FOLLOW_arguments_in_new_class493 = new BitSet(new long[]{0x1240000000000200L});
    public static final BitSet FOLLOW_close_bracket_in_new_class495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_value_in_arguments508 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_comma_in_arguments511 = new BitSet(new long[]{0xB820000000007400L,0x0000000000000030L});
    public static final BitSet FOLLOW_value_in_arguments513 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_statement_in_code527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_begin_in_code532 = new BitSet(new long[]{0x120000017E406240L});
    public static final BitSet FOLLOW_statement_in_code534 = new BitSet(new long[]{0x120000017E406240L});
    public static final BitSet FOLLOW_block_end_in_code537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_statement549 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_semicolon_in_statement551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_statement556 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_semicolon_in_statement558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_statement563 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_semicolon_in_statement565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_try_catch_in_statement570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_this__in_variable_assignment583 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_dot_in_variable_assignment585 = new BitSet(new long[]{0x1000000000006000L});
    public static final BitSet FOLLOW_super__in_variable_assignment589 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_dot_in_variable_assignment591 = new BitSet(new long[]{0x1000000000006000L});
    public static final BitSet FOLLOW_variable_name_in_variable_assignment595 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_assign_in_variable_assignment597 = new BitSet(new long[]{0xB820000000007400L,0x0000000000000030L});
    public static final BitSet FOLLOW_value_in_variable_assignment599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_try__in_try_catch611 = new BitSet(new long[]{0x1200000080406380L});
    public static final BitSet FOLLOW_code_in_try_catch613 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_catch__in_try_catch616 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_try_catch618 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_id_in_try_catch620 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_id_in_try_catch622 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_try_catch624 = new BitSet(new long[]{0x1200000080406380L});
    public static final BitSet FOLLOW_code_in_try_catch626 = new BitSet(new long[]{0x0000000001800002L});
    public static final BitSet FOLLOW_finally__in_try_catch631 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_try_catch633 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_id_in_try_catch635 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_id_in_try_catch637 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_try_catch639 = new BitSet(new long[]{0x1200000080406380L});
    public static final BitSet FOLLOW_code_in_try_catch641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_name_in_variable_type655 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_array_in_variable_type657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_void__in_variable_type663 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_id673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_operator682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_operator689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_operator696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_operator703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_int_const720 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_string_const730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_float_const740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_in_char_const750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_null_const760 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_boolean_const771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_package_name787 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_DOT_in_package_name789 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_ID_in_package_name793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_import_name807 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_DOT_in_import_name809 = new BitSet(new long[]{0x1000001000000000L});
    public static final BitSet FOLLOW_set_in_import_name813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_class_name831 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_DOT_in_class_name833 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_ID_in_class_name837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_method_name851 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_DOT_in_method_name853 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_ID_in_method_name857 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_name_in_variable_name870 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_array_in_variable_name872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_in_annotation_name883 = new BitSet(new long[]{0x1000000000006000L});
    public static final BitSet FOLLOW_name_in_annotation_name885 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_name895 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_DOT_in_name897 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_ID_in_name901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_open_rect_bracket_in_array911 = new BitSet(new long[]{0xB830000000007400L,0x0000000000000030L});
    public static final BitSet FOLLOW_value_in_array913 = new BitSet(new long[]{0xB830000000007400L,0x0000000000000030L});
    public static final BitSet FOLLOW_close_rect_bracket_in_array916 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PACKAGE_in_package_929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_import_940 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASS_in_class_950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXTENDS_in_extends_958 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPLEMENTS_in_implements_968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_THIS_in_this_977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUPER_in_super_986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOID_in_void_995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PUBLIC_in_public_1004 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRIVATE_in_private_1012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROTECTED_in_protected_1022 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STATIC_in_static_1031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FINAL_in_final_1040 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSIENT_in_transient_1050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEW_in_new_1059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRY_in_try_1068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CATCH_in_catch_1077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FINALLY_in_finally_1085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_semicolon1100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_comma1109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_dot1118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_assign1127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_CURLY_BRACKET_in_block_begin1137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLOSE_CURLY_BRACKET_in_block_end1147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_open_bracket1157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_close_bracket1167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_RECT_BRACKET_in_open_rect_bracket1178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLOSE_RECT_BRACKET_in_close_rect_bracket1188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_def_in_synpred24_JavaParser311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constructor_def_in_synpred25_JavaParser315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_def_in_synpred26_JavaParser319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_field_def_in_synpred27_JavaParser323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_close_bracket_in_synpred36_JavaParser438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operator_in_synpred37_JavaParser434 = new BitSet(new long[]{0xB820000000007400L,0x0000000000000030L});
    public static final BitSet FOLLOW_value_in_synpred37_JavaParser436 = new BitSet(new long[]{0x1240000000000202L});
    public static final BitSet FOLLOW_close_bracket_in_synpred37_JavaParser438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_synpred47_JavaParser549 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_semicolon_in_synpred47_JavaParser551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_synpred48_JavaParser556 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_semicolon_in_synpred48_JavaParser558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_synpred49_JavaParser563 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_semicolon_in_synpred49_JavaParser565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_catch__in_synpred52_JavaParser616 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_synpred52_JavaParser618 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_id_in_synpred52_JavaParser620 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_id_in_synpred52_JavaParser622 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_synpred52_JavaParser624 = new BitSet(new long[]{0x1200000080406380L});
    public static final BitSet FOLLOW_code_in_synpred52_JavaParser626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_finally__in_synpred53_JavaParser631 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_synpred53_JavaParser633 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_id_in_synpred53_JavaParser635 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_id_in_synpred53_JavaParser637 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_synpred53_JavaParser639 = new BitSet(new long[]{0x1200000080406380L});
    public static final BitSet FOLLOW_code_in_synpred53_JavaParser641 = new BitSet(new long[]{0x0000000000000002L});

}