// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g 2009-12-10 15:46:47

package com.puresol.coding.java.antlr.output;

import com.puresol.coding.CodeRangeType;
import com.puresol.coding.ParserHelper;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class JavaParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "PACKAGE", "IMPORT", "CLASS", "EXTENDS", "IMPLEMENTS", "VOID", "NEW", "RETURN", "BREAK", "CONTINUE", "NULL", "THIS", "SUPER", "FOR", "DO", "WHILE", "IF", "ELSE", "SWITCH", "CASE", "TRY", "CATCH", "FINALLY", "PUBLIC", "PRIVATE", "PROTECTED", "FINAL", "STATIC", "TRANSIENT", "BOOLEAN", "BYTE", "CHAR", "SHORT", "INTEGER", "LONG", "FLOAT", "DOUBLE", "OPEN_CURLY_BRACKET", "CLOSE_CURLY_BRACKET", "LE", "GE", "EQUAL", "UNEQUAL", "ASSIGN", "INCASSIGN", "DECASSIGN", "INC", "DEC", "PLUS", "MINUS", "SLASH", "STAR", "LOGICAL_OR", "BIT_OR", "LOGICAL_AND", "BIT_AND", "NOT", "DOT", "COMMA", "LT", "GT", "OPEN_RECT_BRACKET", "CLOSE_RECT_BRACKET", "OPEN_BRACKET", "CLOSE_BRACKET", "COLON", "SEMICOLON", "AT", "TILDE", "BOOL_CONST", "ID", "INT_CONST", "EXPONENT", "FLOAT_CONST", "LINEFEED", "COMMENT", "WS", "ESC_SEQ", "STRING_CONST", "CHAR_CONST", "HEX_DIGIT", "UNICODE_ESC", "OCTAL_ESC"
    };
    public static final int PACKAGE=4;
    public static final int DEC=51;
    public static final int PROTECTED=29;
    public static final int CLASS=6;
    public static final int LT=63;
    public static final int EXPONENT=76;
    public static final int STAR=55;
    public static final int INCASSIGN=48;
    public static final int WHILE=19;
    public static final int BIT_AND=59;
    public static final int CASE=23;
    public static final int OCTAL_ESC=86;
    public static final int CHAR=35;
    public static final int NEW=10;
    public static final int FOR=17;
    public static final int DO=18;
    public static final int FLOAT=39;
    public static final int NOT=60;
    public static final int ID=74;
    public static final int EOF=-1;
    public static final int CLOSE_RECT_BRACKET=66;
    public static final int LOGICAL_AND=58;
    public static final int BREAK=12;
    public static final int FLOAT_CONST=77;
    public static final int CHAR_CONST=83;
    public static final int LINEFEED=78;
    public static final int IF=20;
    public static final int AT=71;
    public static final int OPEN_BRACKET=67;
    public static final int INC=50;
    public static final int FINAL=30;
    public static final int IMPORT=5;
    public static final int ESC_SEQ=81;
    public static final int SLASH=54;
    public static final int BOOLEAN=33;
    public static final int IMPLEMENTS=8;
    public static final int BIT_OR=57;
    public static final int CONTINUE=13;
    public static final int COMMA=62;
    public static final int TRANSIENT=32;
    public static final int STRING_CONST=82;
    public static final int EQUAL=45;
    public static final int RETURN=11;
    public static final int THIS=15;
    public static final int LOGICAL_OR=56;
    public static final int TILDE=72;
    public static final int DOUBLE=40;
    public static final int PLUS=52;
    public static final int VOID=9;
    public static final int SUPER=16;
    public static final int DOT=61;
    public static final int COMMENT=79;
    public static final int INTEGER=37;
    public static final int BYTE=34;
    public static final int GE=44;
    public static final int OPEN_CURLY_BRACKET=41;
    public static final int STATIC=31;
    public static final int PRIVATE=28;
    public static final int SWITCH=22;
    public static final int NULL=14;
    public static final int UNICODE_ESC=85;
    public static final int CLOSE_CURLY_BRACKET=42;
    public static final int ELSE=21;
    public static final int INT_CONST=75;
    public static final int HEX_DIGIT=84;
    public static final int SHORT=36;
    public static final int SEMICOLON=70;
    public static final int DECASSIGN=49;
    public static final int MINUS=53;
    public static final int TRY=24;
    public static final int UNEQUAL=46;
    public static final int COLON=69;
    public static final int WS=80;
    public static final int FINALLY=26;
    public static final int ASSIGN=47;
    public static final int GT=64;
    public static final int CATCH=25;
    public static final int OPEN_RECT_BRACKET=65;
    public static final int LONG=38;
    public static final int PUBLIC=27;
    public static final int EXTENDS=7;
    public static final int BOOL_CONST=73;
    public static final int LE=43;
    public static final int CLOSE_BRACKET=68;

    // delegates
    // delegators


        public JavaParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public JavaParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
            this.state.ruleMemo = new HashMap[220+1];
             
             
        }
        

    public String[] getTokenNames() { return JavaParser.tokenNames; }
    public String getGrammarFileName() { return "/home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g"; }


    private ParserHelper helper = new ParserHelper(this);

    public ParserHelper getParserHelper() {
    	return helper;
    }



    // $ANTLR start "file"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:1: file : package_def ( import_def )* ( class_def )* ;
    public final void file() throws RecognitionException {
        int file_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:6: ( package_def ( import_def )* ( class_def )* )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:8: package_def ( import_def )* ( class_def )*
            {
            pushFollow(FOLLOW_package_def_in_file44);
            package_def();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:20: ( import_def )*
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

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:32: ( class_def )*
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:27:1: package_def : package_ package_name semicolon ;
    public final void package_def() throws RecognitionException {
        int package_def_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:28:2: ( package_ package_name semicolon )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:28:4: package_ package_name semicolon
            {
            pushFollow(FOLLOW_package__in_package_def63);
            package_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_package_name_in_package_def65);
            package_name();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_semicolon_in_package_def67);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:31:1: import_def : import_ import_name semicolon ;
    public final void import_def() throws RecognitionException {
        int import_def_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:32:2: ( import_ import_name semicolon )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:32:4: import_ import_name semicolon
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
            if ( state.backtracking>0 ) { memoize(input, 3, import_def_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "import_def"

    public static class class_def_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "class_def"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:35:1: class_def : ( annotation )* ( modifier )* class_ id ( generic )? ( extends_ class_name ( generic )? )? ( implements_ class_name ( comma class_name )* )? class_block ;
    public final JavaParser.class_def_return class_def() throws RecognitionException {
        JavaParser.class_def_return retval = new JavaParser.class_def_return();
        retval.start = input.LT(1);
        int class_def_StartIndex = input.index();
        JavaParser.id_return id1 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:36:2: ( ( annotation )* ( modifier )* class_ id ( generic )? ( extends_ class_name ( generic )? )? ( implements_ class_name ( comma class_name )* )? class_block )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:36:4: ( annotation )* ( modifier )* class_ id ( generic )? ( extends_ class_name ( generic )? )? ( implements_ class_name ( comma class_name )* )? class_block
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:36:4: ( annotation )*
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
            	    pushFollow(FOLLOW_annotation_in_class_def95);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:36:16: ( modifier )*
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
            	    pushFollow(FOLLOW_modifier_in_class_def98);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            pushFollow(FOLLOW_class__in_class_def101);
            class_();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_id_in_class_def103);
            id1=id();

            state._fsp--;
            if (state.failed) return retval;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:36:36: ( generic )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==LT) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: generic
                    {
                    pushFollow(FOLLOW_generic_in_class_def105);
                    generic();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;

            }

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:36:45: ( extends_ class_name ( generic )? )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==EXTENDS) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:36:46: extends_ class_name ( generic )?
                    {
                    pushFollow(FOLLOW_extends__in_class_def109);
                    extends_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_class_def111);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:36:66: ( generic )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==LT) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: generic
                            {
                            pushFollow(FOLLOW_generic_in_class_def113);
                            generic();

                            state._fsp--;
                            if (state.failed) return retval;

                            }
                            break;

                    }


                    }
                    break;

            }

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:36:77: ( implements_ class_name ( comma class_name )* )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==IMPLEMENTS) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:36:78: implements_ class_name ( comma class_name )*
                    {
                    pushFollow(FOLLOW_implements__in_class_def119);
                    implements_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_class_def121);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:36:101: ( comma class_name )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==COMMA) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:36:102: comma class_name
                    	    {
                    	    pushFollow(FOLLOW_comma_in_class_def124);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    pushFollow(FOLLOW_class_name_in_class_def126);
                    	    class_name();

                    	    state._fsp--;
                    	    if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);


                    }
                    break;

            }

            pushFollow(FOLLOW_class_block_in_class_def132);
            class_block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerRange(CodeRangeType.CLASS, (id1!=null?input.toString(id1.start,id1.stop):null), input.toString(retval.start,input.LT(-1)), retval.start.getTokenIndex(), input.LT(-1).getTokenIndex());
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:40:1: constructor_def : ( annotation )* ( modifier )* id open_bracket argument_def close_bracket code ;
    public final JavaParser.constructor_def_return constructor_def() throws RecognitionException {
        JavaParser.constructor_def_return retval = new JavaParser.constructor_def_return();
        retval.start = input.LT(1);
        int constructor_def_StartIndex = input.index();
        JavaParser.id_return id2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:41:2: ( ( annotation )* ( modifier )* id open_bracket argument_def close_bracket code )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:41:4: ( annotation )* ( modifier )* id open_bracket argument_def close_bracket code
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:41:4: ( annotation )*
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
            	    pushFollow(FOLLOW_annotation_in_constructor_def148);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:41:16: ( modifier )*
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
            	    pushFollow(FOLLOW_modifier_in_constructor_def151);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            pushFollow(FOLLOW_id_in_constructor_def154);
            id2=id();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_open_bracket_in_constructor_def156);
            open_bracket();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_argument_def_in_constructor_def158);
            argument_def();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_close_bracket_in_constructor_def160);
            close_bracket();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_code_in_constructor_def162);
            code();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerRange(CodeRangeType.CONSTRUCTOR, (id2!=null?input.toString(id2.start,id2.stop):null), input.toString(retval.start,input.LT(-1)), retval.start.getTokenIndex(), input.LT(-1).getTokenIndex());
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:45:1: method_def : ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket code ;
    public final JavaParser.method_def_return method_def() throws RecognitionException {
        JavaParser.method_def_return retval = new JavaParser.method_def_return();
        retval.start = input.LT(1);
        int method_def_StartIndex = input.index();
        JavaParser.id_return id3 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:46:2: ( ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket code )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:46:4: ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket code
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:46:4: ( annotation )*
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
            	    pushFollow(FOLLOW_annotation_in_method_def177);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:46:16: ( modifier )*
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
            	    pushFollow(FOLLOW_modifier_in_method_def180);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            pushFollow(FOLLOW_variable_type_in_method_def183);
            variable_type();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_id_in_method_def185);
            id3=id();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_open_bracket_in_method_def187);
            open_bracket();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_argument_def_in_method_def189);
            argument_def();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_close_bracket_in_method_def191);
            close_bracket();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_code_in_method_def193);
            code();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerRange(CodeRangeType.METHOD, (id3!=null?input.toString(id3.start,id3.stop):null), input.toString(retval.start,input.LT(-1)), retval.start.getTokenIndex(), input.LT(-1).getTokenIndex());
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:50:1: field_def : ( annotation )* ( modifier )* variable_type variable_name ( assign value )? semicolon ;
    public final void field_def() throws RecognitionException {
        int field_def_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:51:2: ( ( annotation )* ( modifier )* variable_type variable_name ( assign value )? semicolon )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:51:4: ( annotation )* ( modifier )* variable_type variable_name ( assign value )? semicolon
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:51:4: ( annotation )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==AT) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_field_def209);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:51:16: ( modifier )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>=PUBLIC && LA15_0<=TRANSIENT)) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: modifier
            	    {
            	    pushFollow(FOLLOW_modifier_in_field_def212);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

            pushFollow(FOLLOW_variable_type_in_field_def215);
            variable_type();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_variable_name_in_field_def217);
            variable_name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:51:54: ( assign value )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( ((LA16_0>=ASSIGN && LA16_0<=DECASSIGN)) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:51:55: assign value
                    {
                    pushFollow(FOLLOW_assign_in_field_def220);
                    assign();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_field_def222);
                    value();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_semicolon_in_field_def226);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:54:1: argument_def : ( variable_type variable_name ( comma variable_type variable_name )* )? ;
    public final void argument_def() throws RecognitionException {
        int argument_def_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:55:2: ( ( variable_type variable_name ( comma variable_type variable_name )* )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:55:4: ( variable_type variable_name ( comma variable_type variable_name )* )?
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:55:4: ( variable_type variable_name ( comma variable_type variable_name )* )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==VOID||(LA18_0>=BOOLEAN && LA18_0<=DOUBLE)||LA18_0==ID) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:55:5: variable_type variable_name ( comma variable_type variable_name )*
                    {
                    pushFollow(FOLLOW_variable_type_in_argument_def238);
                    variable_type();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_variable_name_in_argument_def240);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:55:33: ( comma variable_type variable_name )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0==COMMA) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:55:34: comma variable_type variable_name
                    	    {
                    	    pushFollow(FOLLOW_comma_in_argument_def243);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_variable_type_in_argument_def245);
                    	    variable_type();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_variable_name_in_argument_def247);
                    	    variable_name();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop17;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:58:1: variable_def : ( annotation )* variable_type variable_name ( assign value )? ;
    public final void variable_def() throws RecognitionException {
        int variable_def_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:59:2: ( ( annotation )* variable_type variable_name ( assign value )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:59:4: ( annotation )* variable_type variable_name ( assign value )?
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:59:4: ( annotation )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==AT) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_variable_def262);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            pushFollow(FOLLOW_variable_type_in_variable_def265);
            variable_type();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_variable_name_in_variable_def267);
            variable_name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:59:44: ( assign value )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( ((LA20_0>=ASSIGN && LA20_0<=DECASSIGN)) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:59:45: assign value
                    {
                    pushFollow(FOLLOW_assign_in_variable_def270);
                    assign();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_variable_def272);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:62:1: modifier : ( public_ | private_ | protected_ | static_ | final_ | transient_ );
    public final void modifier() throws RecognitionException {
        int modifier_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:62:9: ( public_ | private_ | protected_ | static_ | final_ | transient_ )
            int alt21=6;
            switch ( input.LA(1) ) {
            case PUBLIC:
                {
                alt21=1;
                }
                break;
            case PRIVATE:
                {
                alt21=2;
                }
                break;
            case PROTECTED:
                {
                alt21=3;
                }
                break;
            case STATIC:
                {
                alt21=4;
                }
                break;
            case FINAL:
                {
                alt21=5;
                }
                break;
            case TRANSIENT:
                {
                alt21=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }

            switch (alt21) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:62:11: public_
                    {
                    pushFollow(FOLLOW_public__in_modifier284);
                    public_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:63:4: private_
                    {
                    pushFollow(FOLLOW_private__in_modifier289);
                    private_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:64:4: protected_
                    {
                    pushFollow(FOLLOW_protected__in_modifier294);
                    protected_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:65:4: static_
                    {
                    pushFollow(FOLLOW_static__in_modifier299);
                    static_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:66:4: final_
                    {
                    pushFollow(FOLLOW_final__in_modifier304);
                    final_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:67:4: transient_
                    {
                    pushFollow(FOLLOW_transient__in_modifier309);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:71:1: class_block : block_begin ( class_def | constructor_def | method_def | field_def )* block_end ;
    public final void class_block() throws RecognitionException {
        int class_block_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:72:2: ( block_begin ( class_def | constructor_def | method_def | field_def )* block_end )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:72:4: block_begin ( class_def | constructor_def | method_def | field_def )* block_end
            {
            pushFollow(FOLLOW_block_begin_in_class_block321);
            block_begin();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:72:16: ( class_def | constructor_def | method_def | field_def )*
            loop22:
            do {
                int alt22=5;
                alt22 = dfa22.predict(input);
                switch (alt22) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:72:17: class_def
            	    {
            	    pushFollow(FOLLOW_class_def_in_class_block324);
            	    class_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 2 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:72:29: constructor_def
            	    {
            	    pushFollow(FOLLOW_constructor_def_in_class_block328);
            	    constructor_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 3 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:72:47: method_def
            	    {
            	    pushFollow(FOLLOW_method_def_in_class_block332);
            	    method_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 4 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:72:60: field_def
            	    {
            	    pushFollow(FOLLOW_field_def_in_class_block336);
            	    field_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            pushFollow(FOLLOW_block_end_in_class_block340);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:75:1: method_call : method_name open_bracket arguments close_bracket ( dot id open_bracket arguments close_bracket )* ;
    public final void method_call() throws RecognitionException {
        int method_call_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:76:2: ( method_name open_bracket arguments close_bracket ( dot id open_bracket arguments close_bracket )* )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:76:4: method_name open_bracket arguments close_bracket ( dot id open_bracket arguments close_bracket )*
            {
            pushFollow(FOLLOW_method_name_in_method_call352);
            method_name();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_open_bracket_in_method_call354);
            open_bracket();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_arguments_in_method_call356);
            arguments();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_close_bracket_in_method_call358);
            close_bracket();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:76:53: ( dot id open_bracket arguments close_bracket )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==DOT) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:76:54: dot id open_bracket arguments close_bracket
            	    {
            	    pushFollow(FOLLOW_dot_in_method_call361);
            	    dot();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_method_call363);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_open_bracket_in_method_call365);
            	    open_bracket();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_arguments_in_method_call367);
            	    arguments();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_close_bracket_in_method_call369);
            	    close_bracket();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop23;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:79:1: annotation : annotation_name ( open_bracket value ( comma value )* close_bracket )? ;
    public final void annotation() throws RecognitionException {
        int annotation_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:80:2: ( annotation_name ( open_bracket value ( comma value )* close_bracket )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:80:4: annotation_name ( open_bracket value ( comma value )* close_bracket )?
            {
            pushFollow(FOLLOW_annotation_name_in_annotation382);
            annotation_name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:80:20: ( open_bracket value ( comma value )* close_bracket )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==OPEN_BRACKET) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:80:21: open_bracket value ( comma value )* close_bracket
                    {
                    pushFollow(FOLLOW_open_bracket_in_annotation385);
                    open_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_annotation387);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:80:40: ( comma value )*
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==COMMA) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:80:41: comma value
                    	    {
                    	    pushFollow(FOLLOW_comma_in_annotation390);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_value_in_annotation392);
                    	    value();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop24;
                        }
                    } while (true);

                    pushFollow(FOLLOW_close_bracket_in_annotation396);
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


    // $ANTLR start "generic"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:83:1: generic : LT ( id ( comma id )* ) GT ;
    public final void generic() throws RecognitionException {
        int generic_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:83:9: ( LT ( id ( comma id )* ) GT )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:83:11: LT ( id ( comma id )* ) GT
            {
            match(input,LT,FOLLOW_LT_in_generic408); if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:83:14: ( id ( comma id )* )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:83:15: id ( comma id )*
            {
            pushFollow(FOLLOW_id_in_generic411);
            id();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:83:18: ( comma id )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==COMMA) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:83:19: comma id
            	    {
            	    pushFollow(FOLLOW_comma_in_generic414);
            	    comma();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_generic416);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);


            }

            match(input,GT,FOLLOW_GT_in_generic421); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              helper.registerOperator("<>");
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 14, generic_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "generic"


    // $ANTLR start "value"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:87:1: value : ( ( ( unary )? constant ( unary )? | class_name dot class_ | ( unary )? variable_name ( unary )? | ( unary )? method_call ( unary )? | new_class ) ( binary_operator value )* | ( open_bracket ( | ( unary )? constant ( unary )? | class_name dot class_ | ( unary )? variable_name ( unary )? | ( unary )? method_call ( unary )? | new_class ) ) ( binary_operator value )* close_bracket );
    public final void value() throws RecognitionException {
        int value_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:87:7: ( ( ( unary )? constant ( unary )? | class_name dot class_ | ( unary )? variable_name ( unary )? | ( unary )? method_call ( unary )? | new_class ) ( binary_operator value )* | ( open_bracket ( | ( unary )? constant ( unary )? | class_name dot class_ | ( unary )? variable_name ( unary )? | ( unary )? method_call ( unary )? | new_class ) ) ( binary_operator value )* close_bracket )
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==NEW||LA43_0==NULL||(LA43_0>=INC && LA43_0<=DEC)||LA43_0==NOT||(LA43_0>=BOOL_CONST && LA43_0<=INT_CONST)||LA43_0==FLOAT_CONST||(LA43_0>=STRING_CONST && LA43_0<=CHAR_CONST)) ) {
                alt43=1;
            }
            else if ( (LA43_0==OPEN_BRACKET) ) {
                alt43=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;
            }
            switch (alt43) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:88:2: ( ( unary )? constant ( unary )? | class_name dot class_ | ( unary )? variable_name ( unary )? | ( unary )? method_call ( unary )? | new_class ) ( binary_operator value )*
                    {
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:88:2: ( ( unary )? constant ( unary )? | class_name dot class_ | ( unary )? variable_name ( unary )? | ( unary )? method_call ( unary )? | new_class )
                    int alt33=5;
                    alt33 = dfa33.predict(input);
                    switch (alt33) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:88:4: ( unary )? constant ( unary )?
                            {
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:88:4: ( unary )?
                            int alt27=2;
                            int LA27_0 = input.LA(1);

                            if ( ((LA27_0>=INC && LA27_0<=DEC)||LA27_0==NOT) ) {
                                alt27=1;
                            }
                            switch (alt27) {
                                case 1 :
                                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                                    {
                                    pushFollow(FOLLOW_unary_in_value437);
                                    unary();

                                    state._fsp--;
                                    if (state.failed) return ;

                                    }
                                    break;

                            }

                            pushFollow(FOLLOW_constant_in_value440);
                            constant();

                            state._fsp--;
                            if (state.failed) return ;
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:88:20: ( unary )?
                            int alt28=2;
                            int LA28_0 = input.LA(1);

                            if ( ((LA28_0>=INC && LA28_0<=DEC)||LA28_0==NOT) ) {
                                alt28=1;
                            }
                            switch (alt28) {
                                case 1 :
                                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                                    {
                                    pushFollow(FOLLOW_unary_in_value442);
                                    unary();

                                    state._fsp--;
                                    if (state.failed) return ;

                                    }
                                    break;

                            }


                            }
                            break;
                        case 2 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:89:4: class_name dot class_
                            {
                            pushFollow(FOLLOW_class_name_in_value449);
                            class_name();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_dot_in_value451);
                            dot();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_class__in_value453);
                            class_();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;
                        case 3 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:90:4: ( unary )? variable_name ( unary )?
                            {
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:90:4: ( unary )?
                            int alt29=2;
                            int LA29_0 = input.LA(1);

                            if ( ((LA29_0>=INC && LA29_0<=DEC)||LA29_0==NOT) ) {
                                alt29=1;
                            }
                            switch (alt29) {
                                case 1 :
                                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                                    {
                                    pushFollow(FOLLOW_unary_in_value458);
                                    unary();

                                    state._fsp--;
                                    if (state.failed) return ;

                                    }
                                    break;

                            }

                            pushFollow(FOLLOW_variable_name_in_value461);
                            variable_name();

                            state._fsp--;
                            if (state.failed) return ;
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:90:25: ( unary )?
                            int alt30=2;
                            int LA30_0 = input.LA(1);

                            if ( ((LA30_0>=INC && LA30_0<=DEC)||LA30_0==NOT) ) {
                                alt30=1;
                            }
                            switch (alt30) {
                                case 1 :
                                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                                    {
                                    pushFollow(FOLLOW_unary_in_value463);
                                    unary();

                                    state._fsp--;
                                    if (state.failed) return ;

                                    }
                                    break;

                            }


                            }
                            break;
                        case 4 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:91:4: ( unary )? method_call ( unary )?
                            {
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:91:4: ( unary )?
                            int alt31=2;
                            int LA31_0 = input.LA(1);

                            if ( ((LA31_0>=INC && LA31_0<=DEC)||LA31_0==NOT) ) {
                                alt31=1;
                            }
                            switch (alt31) {
                                case 1 :
                                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                                    {
                                    pushFollow(FOLLOW_unary_in_value469);
                                    unary();

                                    state._fsp--;
                                    if (state.failed) return ;

                                    }
                                    break;

                            }

                            pushFollow(FOLLOW_method_call_in_value472);
                            method_call();

                            state._fsp--;
                            if (state.failed) return ;
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:91:23: ( unary )?
                            int alt32=2;
                            int LA32_0 = input.LA(1);

                            if ( ((LA32_0>=INC && LA32_0<=DEC)||LA32_0==NOT) ) {
                                alt32=1;
                            }
                            switch (alt32) {
                                case 1 :
                                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                                    {
                                    pushFollow(FOLLOW_unary_in_value474);
                                    unary();

                                    state._fsp--;
                                    if (state.failed) return ;

                                    }
                                    break;

                            }


                            }
                            break;
                        case 5 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:92:4: new_class
                            {
                            pushFollow(FOLLOW_new_class_in_value481);
                            new_class();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:93:5: ( binary_operator value )*
                    loop34:
                    do {
                        int alt34=2;
                        alt34 = dfa34.predict(input);
                        switch (alt34) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:93:6: binary_operator value
                    	    {
                    	    pushFollow(FOLLOW_binary_operator_in_value488);
                    	    binary_operator();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_value_in_value490);
                    	    value();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop34;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:95:2: ( open_bracket ( | ( unary )? constant ( unary )? | class_name dot class_ | ( unary )? variable_name ( unary )? | ( unary )? method_call ( unary )? | new_class ) ) ( binary_operator value )* close_bracket
                    {
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:95:2: ( open_bracket ( | ( unary )? constant ( unary )? | class_name dot class_ | ( unary )? variable_name ( unary )? | ( unary )? method_call ( unary )? | new_class ) )
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:95:4: open_bracket ( | ( unary )? constant ( unary )? | class_name dot class_ | ( unary )? variable_name ( unary )? | ( unary )? method_call ( unary )? | new_class )
                    {
                    pushFollow(FOLLOW_open_bracket_in_value500);
                    open_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:95:17: ( | ( unary )? constant ( unary )? | class_name dot class_ | ( unary )? variable_name ( unary )? | ( unary )? method_call ( unary )? | new_class )
                    int alt41=6;
                    alt41 = dfa41.predict(input);
                    switch (alt41) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:96:2: 
                            {
                            }
                            break;
                        case 2 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:96:4: ( unary )? constant ( unary )?
                            {
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:96:4: ( unary )?
                            int alt35=2;
                            int LA35_0 = input.LA(1);

                            if ( ((LA35_0>=INC && LA35_0<=DEC)||LA35_0==NOT) ) {
                                alt35=1;
                            }
                            switch (alt35) {
                                case 1 :
                                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                                    {
                                    pushFollow(FOLLOW_unary_in_value507);
                                    unary();

                                    state._fsp--;
                                    if (state.failed) return ;

                                    }
                                    break;

                            }

                            pushFollow(FOLLOW_constant_in_value510);
                            constant();

                            state._fsp--;
                            if (state.failed) return ;
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:96:20: ( unary )?
                            int alt36=2;
                            int LA36_0 = input.LA(1);

                            if ( ((LA36_0>=INC && LA36_0<=DEC)||LA36_0==NOT) ) {
                                alt36=1;
                            }
                            switch (alt36) {
                                case 1 :
                                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                                    {
                                    pushFollow(FOLLOW_unary_in_value512);
                                    unary();

                                    state._fsp--;
                                    if (state.failed) return ;

                                    }
                                    break;

                            }


                            }
                            break;
                        case 3 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:97:4: class_name dot class_
                            {
                            pushFollow(FOLLOW_class_name_in_value519);
                            class_name();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_dot_in_value521);
                            dot();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_class__in_value523);
                            class_();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;
                        case 4 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:98:4: ( unary )? variable_name ( unary )?
                            {
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:98:4: ( unary )?
                            int alt37=2;
                            int LA37_0 = input.LA(1);

                            if ( ((LA37_0>=INC && LA37_0<=DEC)||LA37_0==NOT) ) {
                                alt37=1;
                            }
                            switch (alt37) {
                                case 1 :
                                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                                    {
                                    pushFollow(FOLLOW_unary_in_value528);
                                    unary();

                                    state._fsp--;
                                    if (state.failed) return ;

                                    }
                                    break;

                            }

                            pushFollow(FOLLOW_variable_name_in_value531);
                            variable_name();

                            state._fsp--;
                            if (state.failed) return ;
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:98:25: ( unary )?
                            int alt38=2;
                            int LA38_0 = input.LA(1);

                            if ( ((LA38_0>=INC && LA38_0<=DEC)||LA38_0==NOT) ) {
                                alt38=1;
                            }
                            switch (alt38) {
                                case 1 :
                                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                                    {
                                    pushFollow(FOLLOW_unary_in_value533);
                                    unary();

                                    state._fsp--;
                                    if (state.failed) return ;

                                    }
                                    break;

                            }


                            }
                            break;
                        case 5 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:99:4: ( unary )? method_call ( unary )?
                            {
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:99:4: ( unary )?
                            int alt39=2;
                            int LA39_0 = input.LA(1);

                            if ( ((LA39_0>=INC && LA39_0<=DEC)||LA39_0==NOT) ) {
                                alt39=1;
                            }
                            switch (alt39) {
                                case 1 :
                                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                                    {
                                    pushFollow(FOLLOW_unary_in_value539);
                                    unary();

                                    state._fsp--;
                                    if (state.failed) return ;

                                    }
                                    break;

                            }

                            pushFollow(FOLLOW_method_call_in_value542);
                            method_call();

                            state._fsp--;
                            if (state.failed) return ;
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:99:23: ( unary )?
                            int alt40=2;
                            int LA40_0 = input.LA(1);

                            if ( ((LA40_0>=INC && LA40_0<=DEC)||LA40_0==NOT) ) {
                                alt40=1;
                            }
                            switch (alt40) {
                                case 1 :
                                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                                    {
                                    pushFollow(FOLLOW_unary_in_value544);
                                    unary();

                                    state._fsp--;
                                    if (state.failed) return ;

                                    }
                                    break;

                            }


                            }
                            break;
                        case 6 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:100:4: new_class
                            {
                            pushFollow(FOLLOW_new_class_in_value551);
                            new_class();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:101:6: ( binary_operator value )*
                    loop42:
                    do {
                        int alt42=2;
                        int LA42_0 = input.LA(1);

                        if ( ((LA42_0>=LE && LA42_0<=UNEQUAL)||(LA42_0>=PLUS && LA42_0<=STAR)||(LA42_0>=LT && LA42_0<=GT)) ) {
                            alt42=1;
                        }


                        switch (alt42) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:101:7: binary_operator value
                    	    {
                    	    pushFollow(FOLLOW_binary_operator_in_value559);
                    	    binary_operator();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_value_in_value561);
                    	    value();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop42;
                        }
                    } while (true);

                    pushFollow(FOLLOW_close_bracket_in_value566);
                    close_bracket();

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
            if ( state.backtracking>0 ) { memoize(input, 15, value_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "value"


    // $ANTLR start "constant"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:104:1: constant : ( int_const | string_const | float_const | char_const | null_const | boolean_const );
    public final void constant() throws RecognitionException {
        int constant_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:104:9: ( int_const | string_const | float_const | char_const | null_const | boolean_const )
            int alt44=6;
            switch ( input.LA(1) ) {
            case INT_CONST:
                {
                alt44=1;
                }
                break;
            case STRING_CONST:
                {
                alt44=2;
                }
                break;
            case FLOAT_CONST:
                {
                alt44=3;
                }
                break;
            case CHAR_CONST:
                {
                alt44=4;
                }
                break;
            case NULL:
                {
                alt44=5;
                }
                break;
            case BOOL_CONST:
                {
                alt44=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                throw nvae;
            }

            switch (alt44) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:104:11: int_const
                    {
                    pushFollow(FOLLOW_int_const_in_constant575);
                    int_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:105:4: string_const
                    {
                    pushFollow(FOLLOW_string_const_in_constant580);
                    string_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:106:4: float_const
                    {
                    pushFollow(FOLLOW_float_const_in_constant585);
                    float_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:107:4: char_const
                    {
                    pushFollow(FOLLOW_char_const_in_constant590);
                    char_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:108:4: null_const
                    {
                    pushFollow(FOLLOW_null_const_in_constant595);
                    null_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:109:4: boolean_const
                    {
                    pushFollow(FOLLOW_boolean_const_in_constant600);
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
            if ( state.backtracking>0 ) { memoize(input, 16, constant_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "constant"


    // $ANTLR start "new_class"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:112:1: new_class : new_ class_name ( generic )? open_bracket arguments close_bracket ;
    public final void new_class() throws RecognitionException {
        int new_class_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:113:2: ( new_ class_name ( generic )? open_bracket arguments close_bracket )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:113:4: new_ class_name ( generic )? open_bracket arguments close_bracket
            {
            pushFollow(FOLLOW_new__in_new_class612);
            new_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_class_name_in_new_class614);
            class_name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:113:20: ( generic )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==LT) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: generic
                    {
                    pushFollow(FOLLOW_generic_in_new_class616);
                    generic();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_open_bracket_in_new_class619);
            open_bracket();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_arguments_in_new_class621);
            arguments();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_close_bracket_in_new_class623);
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
            if ( state.backtracking>0 ) { memoize(input, 17, new_class_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "new_class"


    // $ANTLR start "arguments"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:116:1: arguments : ( value ( comma value )* )? ;
    public final void arguments() throws RecognitionException {
        int arguments_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:117:2: ( ( value ( comma value )* )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:117:4: ( value ( comma value )* )?
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:117:4: ( value ( comma value )* )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==NEW||LA47_0==NULL||(LA47_0>=INC && LA47_0<=DEC)||LA47_0==NOT||LA47_0==OPEN_BRACKET||(LA47_0>=BOOL_CONST && LA47_0<=INT_CONST)||LA47_0==FLOAT_CONST||(LA47_0>=STRING_CONST && LA47_0<=CHAR_CONST)) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:117:5: value ( comma value )*
                    {
                    pushFollow(FOLLOW_value_in_arguments636);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:117:11: ( comma value )*
                    loop46:
                    do {
                        int alt46=2;
                        int LA46_0 = input.LA(1);

                        if ( (LA46_0==COMMA) ) {
                            alt46=1;
                        }


                        switch (alt46) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:117:12: comma value
                    	    {
                    	    pushFollow(FOLLOW_comma_in_arguments639);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_value_in_arguments641);
                    	    value();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop46;
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
            if ( state.backtracking>0 ) { memoize(input, 18, arguments_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "arguments"


    // $ANTLR start "code"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:120:1: code : ( statement | block_begin ( statement )* block_end );
    public final void code() throws RecognitionException {
        int code_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:120:6: ( statement | block_begin ( statement )* block_end )
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==VOID||(LA49_0>=RETURN && LA49_0<=CONTINUE)||(LA49_0>=THIS && LA49_0<=IF)||LA49_0==TRY||(LA49_0>=BOOLEAN && LA49_0<=DOUBLE)||(LA49_0>=INC && LA49_0<=DEC)||LA49_0==NOT||(LA49_0>=SEMICOLON && LA49_0<=AT)||LA49_0==ID) ) {
                alt49=1;
            }
            else if ( (LA49_0==OPEN_CURLY_BRACKET) ) {
                alt49=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;
            }
            switch (alt49) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:120:8: statement
                    {
                    pushFollow(FOLLOW_statement_in_code655);
                    statement();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:121:4: block_begin ( statement )* block_end
                    {
                    pushFollow(FOLLOW_block_begin_in_code660);
                    block_begin();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:121:16: ( statement )*
                    loop48:
                    do {
                        int alt48=2;
                        int LA48_0 = input.LA(1);

                        if ( (LA48_0==VOID||(LA48_0>=RETURN && LA48_0<=CONTINUE)||(LA48_0>=THIS && LA48_0<=IF)||LA48_0==TRY||(LA48_0>=BOOLEAN && LA48_0<=DOUBLE)||(LA48_0>=INC && LA48_0<=DEC)||LA48_0==NOT||(LA48_0>=SEMICOLON && LA48_0<=AT)||LA48_0==ID) ) {
                            alt48=1;
                        }


                        switch (alt48) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: statement
                    	    {
                    	    pushFollow(FOLLOW_statement_in_code662);
                    	    statement();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop48;
                        }
                    } while (true);

                    pushFollow(FOLLOW_block_end_in_code665);
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
            if ( state.backtracking>0 ) { memoize(input, 19, code_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "code"


    // $ANTLR start "statement"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:124:1: statement : ( variable_assignment semicolon | variable_def semicolon | method_call semicolon | semicolon | return_statement | continue_ | break_ | ( unary )? variable_name ( unary )? | for_loop | while_loop | do_loop | if_else | try_catch );
    public final void statement() throws RecognitionException {
        int statement_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:125:2: ( variable_assignment semicolon | variable_def semicolon | method_call semicolon | semicolon | return_statement | continue_ | break_ | ( unary )? variable_name ( unary )? | for_loop | while_loop | do_loop | if_else | try_catch )
            int alt52=13;
            alt52 = dfa52.predict(input);
            switch (alt52) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:125:4: variable_assignment semicolon
                    {
                    pushFollow(FOLLOW_variable_assignment_in_statement677);
                    variable_assignment();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement679);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:126:4: variable_def semicolon
                    {
                    pushFollow(FOLLOW_variable_def_in_statement684);
                    variable_def();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement686);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:127:4: method_call semicolon
                    {
                    pushFollow(FOLLOW_method_call_in_statement691);
                    method_call();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement693);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:128:5: semicolon
                    {
                    pushFollow(FOLLOW_semicolon_in_statement699);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:129:4: return_statement
                    {
                    pushFollow(FOLLOW_return_statement_in_statement704);
                    return_statement();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:130:4: continue_
                    {
                    pushFollow(FOLLOW_continue__in_statement709);
                    continue_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:131:4: break_
                    {
                    pushFollow(FOLLOW_break__in_statement714);
                    break_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 8 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:132:4: ( unary )? variable_name ( unary )?
                    {
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:132:4: ( unary )?
                    int alt50=2;
                    int LA50_0 = input.LA(1);

                    if ( ((LA50_0>=INC && LA50_0<=DEC)||LA50_0==NOT) ) {
                        alt50=1;
                    }
                    switch (alt50) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                            {
                            pushFollow(FOLLOW_unary_in_statement719);
                            unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_variable_name_in_statement722);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:132:25: ( unary )?
                    int alt51=2;
                    switch ( input.LA(1) ) {
                        case INC:
                            {
                            int LA51_1 = input.LA(2);

                            if ( (synpred76_JavaParser()) ) {
                                alt51=1;
                            }
                            }
                            break;
                        case DEC:
                            {
                            int LA51_2 = input.LA(2);

                            if ( (synpred76_JavaParser()) ) {
                                alt51=1;
                            }
                            }
                            break;
                        case NOT:
                            {
                            int LA51_3 = input.LA(2);

                            if ( (synpred76_JavaParser()) ) {
                                alt51=1;
                            }
                            }
                            break;
                    }

                    switch (alt51) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                            {
                            pushFollow(FOLLOW_unary_in_statement724);
                            unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 9 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:133:4: for_loop
                    {
                    pushFollow(FOLLOW_for_loop_in_statement730);
                    for_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 10 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:134:4: while_loop
                    {
                    pushFollow(FOLLOW_while_loop_in_statement735);
                    while_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 11 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:135:4: do_loop
                    {
                    pushFollow(FOLLOW_do_loop_in_statement740);
                    do_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 12 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:136:4: if_else
                    {
                    pushFollow(FOLLOW_if_else_in_statement745);
                    if_else();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 13 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:137:4: try_catch
                    {
                    pushFollow(FOLLOW_try_catch_in_statement750);
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
            if ( state.backtracking>0 ) { memoize(input, 20, statement_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "statement"


    // $ANTLR start "statement_wosemicolon"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:140:1: statement_wosemicolon : ( variable_assignment | variable_def | method_call | return_statement | ( unary )? variable_name ( unary )? | for_loop | while_loop | do_loop | if_else | try_catch );
    public final void statement_wosemicolon() throws RecognitionException {
        int statement_wosemicolon_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:141:2: ( variable_assignment | variable_def | method_call | return_statement | ( unary )? variable_name ( unary )? | for_loop | while_loop | do_loop | if_else | try_catch )
            int alt55=10;
            alt55 = dfa55.predict(input);
            switch (alt55) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:141:4: variable_assignment
                    {
                    pushFollow(FOLLOW_variable_assignment_in_statement_wosemicolon761);
                    variable_assignment();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:142:4: variable_def
                    {
                    pushFollow(FOLLOW_variable_def_in_statement_wosemicolon766);
                    variable_def();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:143:4: method_call
                    {
                    pushFollow(FOLLOW_method_call_in_statement_wosemicolon771);
                    method_call();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:144:4: return_statement
                    {
                    pushFollow(FOLLOW_return_statement_in_statement_wosemicolon776);
                    return_statement();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:145:4: ( unary )? variable_name ( unary )?
                    {
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:145:4: ( unary )?
                    int alt53=2;
                    int LA53_0 = input.LA(1);

                    if ( ((LA53_0>=INC && LA53_0<=DEC)||LA53_0==NOT) ) {
                        alt53=1;
                    }
                    switch (alt53) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                            {
                            pushFollow(FOLLOW_unary_in_statement_wosemicolon781);
                            unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_variable_name_in_statement_wosemicolon784);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:145:25: ( unary )?
                    int alt54=2;
                    int LA54_0 = input.LA(1);

                    if ( ((LA54_0>=INC && LA54_0<=DEC)||LA54_0==NOT) ) {
                        alt54=1;
                    }
                    switch (alt54) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                            {
                            pushFollow(FOLLOW_unary_in_statement_wosemicolon786);
                            unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 6 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:146:4: for_loop
                    {
                    pushFollow(FOLLOW_for_loop_in_statement_wosemicolon792);
                    for_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:147:4: while_loop
                    {
                    pushFollow(FOLLOW_while_loop_in_statement_wosemicolon797);
                    while_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 8 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:148:4: do_loop
                    {
                    pushFollow(FOLLOW_do_loop_in_statement_wosemicolon802);
                    do_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 9 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:149:4: if_else
                    {
                    pushFollow(FOLLOW_if_else_in_statement_wosemicolon807);
                    if_else();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 10 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:150:4: try_catch
                    {
                    pushFollow(FOLLOW_try_catch_in_statement_wosemicolon812);
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
            if ( state.backtracking>0 ) { memoize(input, 21, statement_wosemicolon_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "statement_wosemicolon"


    // $ANTLR start "return_statement"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:153:1: return_statement : return_ value semicolon ;
    public final void return_statement() throws RecognitionException {
        int return_statement_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:154:2: ( return_ value semicolon )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:154:4: return_ value semicolon
            {
            pushFollow(FOLLOW_return__in_return_statement824);
            return_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_value_in_return_statement826);
            value();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_semicolon_in_return_statement828);
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
            if ( state.backtracking>0 ) { memoize(input, 22, return_statement_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "return_statement"


    // $ANTLR start "variable_assignment"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:157:1: variable_assignment : ( this_ dot | super_ dot )? variable_name assign value ;
    public final void variable_assignment() throws RecognitionException {
        int variable_assignment_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:158:2: ( ( this_ dot | super_ dot )? variable_name assign value )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:158:4: ( this_ dot | super_ dot )? variable_name assign value
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:158:4: ( this_ dot | super_ dot )?
            int alt56=3;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==THIS) ) {
                alt56=1;
            }
            else if ( (LA56_0==SUPER) ) {
                alt56=2;
            }
            switch (alt56) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:158:5: this_ dot
                    {
                    pushFollow(FOLLOW_this__in_variable_assignment841);
                    this_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_dot_in_variable_assignment843);
                    dot();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:158:17: super_ dot
                    {
                    pushFollow(FOLLOW_super__in_variable_assignment847);
                    super_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_dot_in_variable_assignment849);
                    dot();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_variable_name_in_variable_assignment853);
            variable_name();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_assign_in_variable_assignment855);
            assign();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_value_in_variable_assignment857);
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
            if ( state.backtracking>0 ) { memoize(input, 23, variable_assignment_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "variable_assignment"


    // $ANTLR start "for_loop"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:1: for_loop : ( for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon ( value )? semicolon ( statement_wosemicolon ( comma statement_wosemicolon )* )? CLOSE_BRACKET code | for_ OPEN_BRACKET variable_type variable_name colon value CLOSE_BRACKET code );
    public final void for_loop() throws RecognitionException {
        int for_loop_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:9: ( for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon ( value )? semicolon ( statement_wosemicolon ( comma statement_wosemicolon )* )? CLOSE_BRACKET code | for_ OPEN_BRACKET variable_type variable_name colon value CLOSE_BRACKET code )
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==FOR) ) {
                int LA62_1 = input.LA(2);

                if ( (synpred100_JavaParser()) ) {
                    alt62=1;
                }
                else if ( (true) ) {
                    alt62=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 62, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                throw nvae;
            }
            switch (alt62) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:11: for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon ( value )? semicolon ( statement_wosemicolon ( comma statement_wosemicolon )* )? CLOSE_BRACKET code
                    {
                    pushFollow(FOLLOW_for__in_for_loop867);
                    for_();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_for_loop869); if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:29: ( variable_def ( comma variable_def )* )?
                    int alt58=2;
                    int LA58_0 = input.LA(1);

                    if ( (LA58_0==VOID||(LA58_0>=BOOLEAN && LA58_0<=DOUBLE)||LA58_0==AT||LA58_0==ID) ) {
                        alt58=1;
                    }
                    switch (alt58) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:30: variable_def ( comma variable_def )*
                            {
                            pushFollow(FOLLOW_variable_def_in_for_loop872);
                            variable_def();

                            state._fsp--;
                            if (state.failed) return ;
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:43: ( comma variable_def )*
                            loop57:
                            do {
                                int alt57=2;
                                int LA57_0 = input.LA(1);

                                if ( (LA57_0==COMMA) ) {
                                    alt57=1;
                                }


                                switch (alt57) {
                            	case 1 :
                            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:44: comma variable_def
                            	    {
                            	    pushFollow(FOLLOW_comma_in_for_loop875);
                            	    comma();

                            	    state._fsp--;
                            	    if (state.failed) return ;
                            	    pushFollow(FOLLOW_variable_def_in_for_loop877);
                            	    variable_def();

                            	    state._fsp--;
                            	    if (state.failed) return ;

                            	    }
                            	    break;

                            	default :
                            	    break loop57;
                                }
                            } while (true);


                            }
                            break;

                    }

                    pushFollow(FOLLOW_semicolon_in_for_loop883);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:77: ( value )?
                    int alt59=2;
                    int LA59_0 = input.LA(1);

                    if ( (LA59_0==NEW||LA59_0==NULL||(LA59_0>=INC && LA59_0<=DEC)||LA59_0==NOT||LA59_0==OPEN_BRACKET||(LA59_0>=BOOL_CONST && LA59_0<=INT_CONST)||LA59_0==FLOAT_CONST||(LA59_0>=STRING_CONST && LA59_0<=CHAR_CONST)) ) {
                        alt59=1;
                    }
                    switch (alt59) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: value
                            {
                            pushFollow(FOLLOW_value_in_for_loop885);
                            value();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_semicolon_in_for_loop888);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:94: ( statement_wosemicolon ( comma statement_wosemicolon )* )?
                    int alt61=2;
                    int LA61_0 = input.LA(1);

                    if ( (LA61_0==VOID||LA61_0==RETURN||(LA61_0>=THIS && LA61_0<=IF)||LA61_0==TRY||(LA61_0>=BOOLEAN && LA61_0<=DOUBLE)||(LA61_0>=INC && LA61_0<=DEC)||LA61_0==NOT||LA61_0==AT||LA61_0==ID) ) {
                        alt61=1;
                    }
                    switch (alt61) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:95: statement_wosemicolon ( comma statement_wosemicolon )*
                            {
                            pushFollow(FOLLOW_statement_wosemicolon_in_for_loop891);
                            statement_wosemicolon();

                            state._fsp--;
                            if (state.failed) return ;
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:117: ( comma statement_wosemicolon )*
                            loop60:
                            do {
                                int alt60=2;
                                int LA60_0 = input.LA(1);

                                if ( (LA60_0==COMMA) ) {
                                    alt60=1;
                                }


                                switch (alt60) {
                            	case 1 :
                            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:118: comma statement_wosemicolon
                            	    {
                            	    pushFollow(FOLLOW_comma_in_for_loop894);
                            	    comma();

                            	    state._fsp--;
                            	    if (state.failed) return ;
                            	    pushFollow(FOLLOW_statement_wosemicolon_in_for_loop896);
                            	    statement_wosemicolon();

                            	    state._fsp--;
                            	    if (state.failed) return ;

                            	    }
                            	    break;

                            	default :
                            	    break loop60;
                                }
                            } while (true);


                            }
                            break;

                    }

                    match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_for_loop902); if (state.failed) return ;
                    pushFollow(FOLLOW_code_in_for_loop904);
                    code();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:162:4: for_ OPEN_BRACKET variable_type variable_name colon value CLOSE_BRACKET code
                    {
                    pushFollow(FOLLOW_for__in_for_loop909);
                    for_();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_for_loop911); if (state.failed) return ;
                    pushFollow(FOLLOW_variable_type_in_for_loop913);
                    variable_type();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_variable_name_in_for_loop915);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_colon_in_for_loop917);
                    colon();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_for_loop919);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_for_loop921); if (state.failed) return ;
                    pushFollow(FOLLOW_code_in_for_loop923);
                    code();

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
            if ( state.backtracking>0 ) { memoize(input, 24, for_loop_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "for_loop"


    // $ANTLR start "while_loop"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:165:1: while_loop : while_ OPEN_BRACKET value CLOSE_BRACKET code ;
    public final void while_loop() throws RecognitionException {
        int while_loop_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:166:2: ( while_ OPEN_BRACKET value CLOSE_BRACKET code )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:166:4: while_ OPEN_BRACKET value CLOSE_BRACKET code
            {
            pushFollow(FOLLOW_while__in_while_loop935);
            while_();

            state._fsp--;
            if (state.failed) return ;
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_while_loop937); if (state.failed) return ;
            pushFollow(FOLLOW_value_in_while_loop939);
            value();

            state._fsp--;
            if (state.failed) return ;
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_while_loop941); if (state.failed) return ;
            pushFollow(FOLLOW_code_in_while_loop943);
            code();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 25, while_loop_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "while_loop"


    // $ANTLR start "do_loop"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:169:1: do_loop : do_ code while_ OPEN_BRACKET value CLOSE_BRACKET ;
    public final void do_loop() throws RecognitionException {
        int do_loop_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:169:9: ( do_ code while_ OPEN_BRACKET value CLOSE_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:169:11: do_ code while_ OPEN_BRACKET value CLOSE_BRACKET
            {
            pushFollow(FOLLOW_do__in_do_loop954);
            do_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_code_in_do_loop956);
            code();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_while__in_do_loop958);
            while_();

            state._fsp--;
            if (state.failed) return ;
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_do_loop960); if (state.failed) return ;
            pushFollow(FOLLOW_value_in_do_loop962);
            value();

            state._fsp--;
            if (state.failed) return ;
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_do_loop964); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 26, do_loop_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "do_loop"


    // $ANTLR start "if_else"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:172:1: if_else : if_ OPEN_BRACKET value CLOSE_BRACKET code ( else_ code )? ;
    public final void if_else() throws RecognitionException {
        int if_else_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:172:9: ( if_ OPEN_BRACKET value CLOSE_BRACKET code ( else_ code )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:172:11: if_ OPEN_BRACKET value CLOSE_BRACKET code ( else_ code )?
            {
            pushFollow(FOLLOW_if__in_if_else975);
            if_();

            state._fsp--;
            if (state.failed) return ;
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_if_else977); if (state.failed) return ;
            pushFollow(FOLLOW_value_in_if_else979);
            value();

            state._fsp--;
            if (state.failed) return ;
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_if_else981); if (state.failed) return ;
            pushFollow(FOLLOW_code_in_if_else983);
            code();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:172:53: ( else_ code )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==ELSE) ) {
                int LA63_1 = input.LA(2);

                if ( (synpred101_JavaParser()) ) {
                    alt63=1;
                }
            }
            switch (alt63) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:172:54: else_ code
                    {
                    pushFollow(FOLLOW_else__in_if_else986);
                    else_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_code_in_if_else988);
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
            if ( state.backtracking>0 ) { memoize(input, 27, if_else_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "if_else"


    // $ANTLR start "try_catch"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:175:1: try_catch : try_ code ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )+ ( finally_ code )? ;
    public final void try_catch() throws RecognitionException {
        int try_catch_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:176:2: ( try_ code ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )+ ( finally_ code )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:176:4: try_ code ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )+ ( finally_ code )?
            {
            pushFollow(FOLLOW_try__in_try_catch1001);
            try_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_code_in_try_catch1003);
            code();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:176:14: ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )+
            int cnt64=0;
            loop64:
            do {
                int alt64=2;
                int LA64_0 = input.LA(1);

                if ( (LA64_0==CATCH) ) {
                    int LA64_2 = input.LA(2);

                    if ( (synpred102_JavaParser()) ) {
                        alt64=1;
                    }


                }


                switch (alt64) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:176:15: catch_ OPEN_BRACKET id id CLOSE_BRACKET code
            	    {
            	    pushFollow(FOLLOW_catch__in_try_catch1006);
            	    catch_();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_try_catch1008); if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_try_catch1010);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_try_catch1012);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_try_catch1014); if (state.failed) return ;
            	    pushFollow(FOLLOW_code_in_try_catch1016);
            	    code();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt64 >= 1 ) break loop64;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(64, input);
                        throw eee;
                }
                cnt64++;
            } while (true);

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:176:62: ( finally_ code )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==FINALLY) ) {
                int LA65_1 = input.LA(2);

                if ( (synpred103_JavaParser()) ) {
                    alt65=1;
                }
            }
            switch (alt65) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:176:63: finally_ code
                    {
                    pushFollow(FOLLOW_finally__in_try_catch1021);
                    finally_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_code_in_try_catch1023);
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
            if ( state.backtracking>0 ) { memoize(input, 28, try_catch_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "try_catch"


    // $ANTLR start "variable_type"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:180:1: variable_type : ( primitive ( array )? | class_name ( generic )? ( array )? | void_ );
    public final void variable_type() throws RecognitionException {
        int variable_type_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:181:2: ( primitive ( array )? | class_name ( generic )? ( array )? | void_ )
            int alt69=3;
            switch ( input.LA(1) ) {
            case BOOLEAN:
            case BYTE:
            case CHAR:
            case SHORT:
            case INTEGER:
            case LONG:
            case FLOAT:
            case DOUBLE:
                {
                alt69=1;
                }
                break;
            case ID:
                {
                alt69=2;
                }
                break;
            case VOID:
                {
                alt69=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 69, 0, input);

                throw nvae;
            }

            switch (alt69) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:181:4: primitive ( array )?
                    {
                    pushFollow(FOLLOW_primitive_in_variable_type1037);
                    primitive();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:181:14: ( array )?
                    int alt66=2;
                    int LA66_0 = input.LA(1);

                    if ( (LA66_0==OPEN_RECT_BRACKET) ) {
                        alt66=1;
                    }
                    switch (alt66) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: array
                            {
                            pushFollow(FOLLOW_array_in_variable_type1039);
                            array();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:182:4: class_name ( generic )? ( array )?
                    {
                    pushFollow(FOLLOW_class_name_in_variable_type1045);
                    class_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:182:15: ( generic )?
                    int alt67=2;
                    int LA67_0 = input.LA(1);

                    if ( (LA67_0==LT) ) {
                        alt67=1;
                    }
                    switch (alt67) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: generic
                            {
                            pushFollow(FOLLOW_generic_in_variable_type1047);
                            generic();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:182:24: ( array )?
                    int alt68=2;
                    int LA68_0 = input.LA(1);

                    if ( (LA68_0==OPEN_RECT_BRACKET) ) {
                        alt68=1;
                    }
                    switch (alt68) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: array
                            {
                            pushFollow(FOLLOW_array_in_variable_type1050);
                            array();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:183:4: void_
                    {
                    pushFollow(FOLLOW_void__in_variable_type1056);
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
            if ( state.backtracking>0 ) { memoize(input, 29, variable_type_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "variable_type"

    public static class id_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "id"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:186:1: id : ID ;
    public final JavaParser.id_return id() throws RecognitionException {
        JavaParser.id_return retval = new JavaParser.id_return();
        retval.start = input.LT(1);
        int id_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:186:4: ( ID )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:186:6: ID
            {
            match(input,ID,FOLLOW_ID_in_id1066); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 30, id_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "id"

    public static class binary_operator_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "binary_operator"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:188:1: binary_operator : ( PLUS | MINUS | STAR | SLASH | EQUAL | UNEQUAL | LT | GT | LE | GE );
    public final JavaParser.binary_operator_return binary_operator() throws RecognitionException {
        JavaParser.binary_operator_return retval = new JavaParser.binary_operator_return();
        retval.start = input.LT(1);
        int binary_operator_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:188:16: ( PLUS | MINUS | STAR | SLASH | EQUAL | UNEQUAL | LT | GT | LE | GE )
            int alt70=10;
            switch ( input.LA(1) ) {
            case PLUS:
                {
                alt70=1;
                }
                break;
            case MINUS:
                {
                alt70=2;
                }
                break;
            case STAR:
                {
                alt70=3;
                }
                break;
            case SLASH:
                {
                alt70=4;
                }
                break;
            case EQUAL:
                {
                alt70=5;
                }
                break;
            case UNEQUAL:
                {
                alt70=6;
                }
                break;
            case LT:
                {
                alt70=7;
                }
                break;
            case GT:
                {
                alt70=8;
                }
                break;
            case LE:
                {
                alt70=9;
                }
                break;
            case GE:
                {
                alt70=10;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 70, 0, input);

                throw nvae;
            }

            switch (alt70) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:188:18: PLUS
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_binary_operator1075); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:189:4: MINUS
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_binary_operator1082); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:190:4: STAR
                    {
                    match(input,STAR,FOLLOW_STAR_in_binary_operator1089); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:191:4: SLASH
                    {
                    match(input,SLASH,FOLLOW_SLASH_in_binary_operator1096); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 5 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:192:4: EQUAL
                    {
                    match(input,EQUAL,FOLLOW_EQUAL_in_binary_operator1103); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 6 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:193:4: UNEQUAL
                    {
                    match(input,UNEQUAL,FOLLOW_UNEQUAL_in_binary_operator1110); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 7 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:194:4: LT
                    {
                    match(input,LT,FOLLOW_LT_in_binary_operator1117); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 8 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:195:4: GT
                    {
                    match(input,GT,FOLLOW_GT_in_binary_operator1124); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 9 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:196:4: LE
                    {
                    match(input,LE,FOLLOW_LE_in_binary_operator1131); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 10 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:197:4: GE
                    {
                    match(input,GE,FOLLOW_GE_in_binary_operator1138); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 31, binary_operator_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "binary_operator"

    public static class unary_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "unary"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:200:1: unary : ( INC | DEC | NOT );
    public final JavaParser.unary_return unary() throws RecognitionException {
        JavaParser.unary_return retval = new JavaParser.unary_return();
        retval.start = input.LT(1);
        int unary_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:200:7: ( INC | DEC | NOT )
            int alt71=3;
            switch ( input.LA(1) ) {
            case INC:
                {
                alt71=1;
                }
                break;
            case DEC:
                {
                alt71=2;
                }
                break;
            case NOT:
                {
                alt71=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 71, 0, input);

                throw nvae;
            }

            switch (alt71) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:200:9: INC
                    {
                    match(input,INC,FOLLOW_INC_in_unary1150); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:201:4: DEC
                    {
                    match(input,DEC,FOLLOW_DEC_in_unary1157); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:202:4: NOT
                    {
                    match(input,NOT,FOLLOW_NOT_in_unary1164); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 32, unary_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "unary"

    public static class primitive_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "primitive"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:205:1: primitive : ( BOOLEAN | BYTE | CHAR | SHORT | INTEGER | LONG | FLOAT | DOUBLE );
    public final JavaParser.primitive_return primitive() throws RecognitionException {
        JavaParser.primitive_return retval = new JavaParser.primitive_return();
        retval.start = input.LT(1);
        int primitive_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:206:2: ( BOOLEAN | BYTE | CHAR | SHORT | INTEGER | LONG | FLOAT | DOUBLE )
            int alt72=8;
            switch ( input.LA(1) ) {
            case BOOLEAN:
                {
                alt72=1;
                }
                break;
            case BYTE:
                {
                alt72=2;
                }
                break;
            case CHAR:
                {
                alt72=3;
                }
                break;
            case SHORT:
                {
                alt72=4;
                }
                break;
            case INTEGER:
                {
                alt72=5;
                }
                break;
            case LONG:
                {
                alt72=6;
                }
                break;
            case FLOAT:
                {
                alt72=7;
                }
                break;
            case DOUBLE:
                {
                alt72=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 72, 0, input);

                throw nvae;
            }

            switch (alt72) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:206:4: BOOLEAN
                    {
                    match(input,BOOLEAN,FOLLOW_BOOLEAN_in_primitive1177); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:207:4: BYTE
                    {
                    match(input,BYTE,FOLLOW_BYTE_in_primitive1184); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:208:4: CHAR
                    {
                    match(input,CHAR,FOLLOW_CHAR_in_primitive1191); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:209:4: SHORT
                    {
                    match(input,SHORT,FOLLOW_SHORT_in_primitive1198); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 5 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:210:4: INTEGER
                    {
                    match(input,INTEGER,FOLLOW_INTEGER_in_primitive1205); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 6 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:211:4: LONG
                    {
                    match(input,LONG,FOLLOW_LONG_in_primitive1212); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 7 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:212:4: FLOAT
                    {
                    match(input,FLOAT,FOLLOW_FLOAT_in_primitive1219); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 8 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:213:4: DOUBLE
                    {
                    match(input,DOUBLE,FOLLOW_DOUBLE_in_primitive1226); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
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
            if ( state.backtracking>0 ) { memoize(input, 33, primitive_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "primitive"

    public static class int_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "int_const"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:221:1: int_const : INT_CONST ;
    public final JavaParser.int_const_return int_const() throws RecognitionException {
        JavaParser.int_const_return retval = new JavaParser.int_const_return();
        retval.start = input.LT(1);
        int int_const_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:222:2: ( INT_CONST )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:222:4: INT_CONST
            {
            match(input,INT_CONST,FOLLOW_INT_CONST_in_int_const1244); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 34, int_const_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "int_const"

    public static class string_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "string_const"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:223:1: string_const : STRING_CONST ;
    public final JavaParser.string_const_return string_const() throws RecognitionException {
        JavaParser.string_const_return retval = new JavaParser.string_const_return();
        retval.start = input.LT(1);
        int string_const_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 35) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:224:2: ( STRING_CONST )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:224:4: STRING_CONST
            {
            match(input,STRING_CONST,FOLLOW_STRING_CONST_in_string_const1254); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 35, string_const_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "string_const"

    public static class float_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "float_const"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:225:1: float_const : FLOAT_CONST ;
    public final JavaParser.float_const_return float_const() throws RecognitionException {
        JavaParser.float_const_return retval = new JavaParser.float_const_return();
        retval.start = input.LT(1);
        int float_const_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 36) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:226:2: ( FLOAT_CONST )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:226:4: FLOAT_CONST
            {
            match(input,FLOAT_CONST,FOLLOW_FLOAT_CONST_in_float_const1264); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 36, float_const_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "float_const"

    public static class char_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "char_const"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:227:1: char_const : CHAR_CONST ;
    public final JavaParser.char_const_return char_const() throws RecognitionException {
        JavaParser.char_const_return retval = new JavaParser.char_const_return();
        retval.start = input.LT(1);
        int char_const_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 37) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:228:2: ( CHAR_CONST )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:228:4: CHAR_CONST
            {
            match(input,CHAR_CONST,FOLLOW_CHAR_CONST_in_char_const1274); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 37, char_const_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "char_const"

    public static class null_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "null_const"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:229:1: null_const : NULL ;
    public final JavaParser.null_const_return null_const() throws RecognitionException {
        JavaParser.null_const_return retval = new JavaParser.null_const_return();
        retval.start = input.LT(1);
        int null_const_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:230:2: ( NULL )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:230:4: NULL
            {
            match(input,NULL,FOLLOW_NULL_in_null_const1284); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 38, null_const_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "null_const"

    public static class boolean_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "boolean_const"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:231:1: boolean_const : BOOL_CONST ;
    public final JavaParser.boolean_const_return boolean_const() throws RecognitionException {
        JavaParser.boolean_const_return retval = new JavaParser.boolean_const_return();
        retval.start = input.LT(1);
        int boolean_const_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:232:2: ( BOOL_CONST )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:232:4: BOOL_CONST
            {
            match(input,BOOL_CONST,FOLLOW_BOOL_CONST_in_boolean_const1295); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 39, boolean_const_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "boolean_const"

    public static class package_name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "package_name"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:238:1: package_name : ( ID DOT )* ID ;
    public final JavaParser.package_name_return package_name() throws RecognitionException {
        JavaParser.package_name_return retval = new JavaParser.package_name_return();
        retval.start = input.LT(1);
        int package_name_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 40) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:239:2: ( ( ID DOT )* ID )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:239:4: ( ID DOT )* ID
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:239:4: ( ID DOT )*
            loop73:
            do {
                int alt73=2;
                int LA73_0 = input.LA(1);

                if ( (LA73_0==ID) ) {
                    int LA73_1 = input.LA(2);

                    if ( (LA73_1==DOT) ) {
                        alt73=1;
                    }


                }


                switch (alt73) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:239:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_package_name1311); if (state.failed) return retval;
            	    match(input,DOT,FOLLOW_DOT_in_package_name1313); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop73;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_package_name1317); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 40, package_name_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "package_name"

    public static class import_name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "import_name"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:242:1: import_name : ( ID DOT )+ ( ID | STAR ) ;
    public final JavaParser.import_name_return import_name() throws RecognitionException {
        JavaParser.import_name_return retval = new JavaParser.import_name_return();
        retval.start = input.LT(1);
        int import_name_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:243:2: ( ( ID DOT )+ ( ID | STAR ) )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:243:4: ( ID DOT )+ ( ID | STAR )
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:243:4: ( ID DOT )+
            int cnt74=0;
            loop74:
            do {
                int alt74=2;
                int LA74_0 = input.LA(1);

                if ( (LA74_0==ID) ) {
                    int LA74_1 = input.LA(2);

                    if ( (LA74_1==DOT) ) {
                        alt74=1;
                    }


                }


                switch (alt74) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:243:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_import_name1331); if (state.failed) return retval;
            	    match(input,DOT,FOLLOW_DOT_in_import_name1333); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    if ( cnt74 >= 1 ) break loop74;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(74, input);
                        throw eee;
                }
                cnt74++;
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
            if ( state.backtracking>0 ) { memoize(input, 41, import_name_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "import_name"

    public static class class_name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "class_name"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:246:1: class_name : ( ID DOT )* ID ;
    public final JavaParser.class_name_return class_name() throws RecognitionException {
        JavaParser.class_name_return retval = new JavaParser.class_name_return();
        retval.start = input.LT(1);
        int class_name_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 42) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:247:2: ( ( ID DOT )* ID )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:247:4: ( ID DOT )* ID
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:247:4: ( ID DOT )*
            loop75:
            do {
                int alt75=2;
                int LA75_0 = input.LA(1);

                if ( (LA75_0==ID) ) {
                    int LA75_1 = input.LA(2);

                    if ( (LA75_1==DOT) ) {
                        int LA75_2 = input.LA(3);

                        if ( (LA75_2==ID) ) {
                            alt75=1;
                        }


                    }


                }


                switch (alt75) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:247:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_class_name1355); if (state.failed) return retval;
            	    match(input,DOT,FOLLOW_DOT_in_class_name1357); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop75;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_class_name1361); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 42, class_name_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "class_name"

    public static class method_name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "method_name"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:250:1: method_name : ( ID DOT )* ID ;
    public final JavaParser.method_name_return method_name() throws RecognitionException {
        JavaParser.method_name_return retval = new JavaParser.method_name_return();
        retval.start = input.LT(1);
        int method_name_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 43) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:251:2: ( ( ID DOT )* ID )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:251:4: ( ID DOT )* ID
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:251:4: ( ID DOT )*
            loop76:
            do {
                int alt76=2;
                int LA76_0 = input.LA(1);

                if ( (LA76_0==ID) ) {
                    int LA76_1 = input.LA(2);

                    if ( (LA76_1==DOT) ) {
                        alt76=1;
                    }


                }


                switch (alt76) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:251:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_method_name1375); if (state.failed) return retval;
            	    match(input,DOT,FOLLOW_DOT_in_method_name1377); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop76;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_method_name1381); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 43, method_name_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "method_name"


    // $ANTLR start "variable_name"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:254:1: variable_name : name ( array )? ;
    public final void variable_name() throws RecognitionException {
        int variable_name_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 44) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:255:2: ( name ( array )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:255:4: name ( array )?
            {
            pushFollow(FOLLOW_name_in_variable_name1394);
            name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:255:9: ( array )?
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==OPEN_RECT_BRACKET) ) {
                alt77=1;
            }
            switch (alt77) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: array
                    {
                    pushFollow(FOLLOW_array_in_variable_name1396);
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
            if ( state.backtracking>0 ) { memoize(input, 44, variable_name_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "variable_name"

    public static class annotation_name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "annotation_name"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:257:1: annotation_name : AT name ;
    public final JavaParser.annotation_name_return annotation_name() throws RecognitionException {
        JavaParser.annotation_name_return retval = new JavaParser.annotation_name_return();
        retval.start = input.LT(1);
        int annotation_name_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 45) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:258:2: ( AT name )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:258:4: AT name
            {
            match(input,AT,FOLLOW_AT_in_annotation_name1407); if (state.failed) return retval;
            pushFollow(FOLLOW_name_in_annotation_name1409);
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
            if ( state.backtracking>0 ) { memoize(input, 45, annotation_name_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "annotation_name"

    public static class name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "name"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:259:1: name : ( ID DOT )* ID ;
    public final JavaParser.name_return name() throws RecognitionException {
        JavaParser.name_return retval = new JavaParser.name_return();
        retval.start = input.LT(1);
        int name_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 46) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:259:6: ( ( ID DOT )* ID )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:259:8: ( ID DOT )* ID
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:259:8: ( ID DOT )*
            loop78:
            do {
                int alt78=2;
                int LA78_0 = input.LA(1);

                if ( (LA78_0==ID) ) {
                    int LA78_1 = input.LA(2);

                    if ( (LA78_1==DOT) ) {
                        alt78=1;
                    }


                }


                switch (alt78) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:259:9: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_name1419); if (state.failed) return retval;
            	    match(input,DOT,FOLLOW_DOT_in_name1421); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop78;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_name1425); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 46, name_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "name"


    // $ANTLR start "array"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:261:1: array : open_rect_bracket ( value )? close_rect_bracket ;
    public final void array() throws RecognitionException {
        int array_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 47) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:261:7: ( open_rect_bracket ( value )? close_rect_bracket )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:261:9: open_rect_bracket ( value )? close_rect_bracket
            {
            pushFollow(FOLLOW_open_rect_bracket_in_array1435);
            open_rect_bracket();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:261:27: ( value )?
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==NEW||LA79_0==NULL||(LA79_0>=INC && LA79_0<=DEC)||LA79_0==NOT||LA79_0==OPEN_BRACKET||(LA79_0>=BOOL_CONST && LA79_0<=INT_CONST)||LA79_0==FLOAT_CONST||(LA79_0>=STRING_CONST && LA79_0<=CHAR_CONST)) ) {
                alt79=1;
            }
            switch (alt79) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: value
                    {
                    pushFollow(FOLLOW_value_in_array1437);
                    value();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_close_rect_bracket_in_array1440);
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
            if ( state.backtracking>0 ) { memoize(input, 47, array_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "array"

    public static class package__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "package_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:267:1: package_ : PACKAGE ;
    public final JavaParser.package__return package_() throws RecognitionException {
        JavaParser.package__return retval = new JavaParser.package__return();
        retval.start = input.LT(1);
        int package__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 48) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:268:2: ( PACKAGE )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:268:4: PACKAGE
            {
            match(input,PACKAGE,FOLLOW_PACKAGE_in_package_1453); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 48, package__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "package_"

    public static class import__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "import_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:269:1: import_ : IMPORT ;
    public final JavaParser.import__return import_() throws RecognitionException {
        JavaParser.import__return retval = new JavaParser.import__return();
        retval.start = input.LT(1);
        int import__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 49) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:270:2: ( IMPORT )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:270:4: IMPORT
            {
            match(input,IMPORT,FOLLOW_IMPORT_in_import_1464); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 49, import__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "import_"

    public static class class__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "class_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:271:1: class_ : CLASS ;
    public final JavaParser.class__return class_() throws RecognitionException {
        JavaParser.class__return retval = new JavaParser.class__return();
        retval.start = input.LT(1);
        int class__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 50) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:271:8: ( CLASS )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:271:10: CLASS
            {
            match(input,CLASS,FOLLOW_CLASS_in_class_1474); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 50, class__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "class_"

    public static class extends__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "extends_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:272:1: extends_ : EXTENDS ;
    public final JavaParser.extends__return extends_() throws RecognitionException {
        JavaParser.extends__return retval = new JavaParser.extends__return();
        retval.start = input.LT(1);
        int extends__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 51) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:272:9: ( EXTENDS )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:272:11: EXTENDS
            {
            match(input,EXTENDS,FOLLOW_EXTENDS_in_extends_1482); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 51, extends__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "extends_"

    public static class implements__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "implements_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:273:1: implements_ : IMPLEMENTS ;
    public final JavaParser.implements__return implements_() throws RecognitionException {
        JavaParser.implements__return retval = new JavaParser.implements__return();
        retval.start = input.LT(1);
        int implements__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 52) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:274:2: ( IMPLEMENTS )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:274:4: IMPLEMENTS
            {
            match(input,IMPLEMENTS,FOLLOW_IMPLEMENTS_in_implements_1492); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 52, implements__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "implements_"

    public static class this__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "this_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:275:1: this_ : THIS ;
    public final JavaParser.this__return this_() throws RecognitionException {
        JavaParser.this__return retval = new JavaParser.this__return();
        retval.start = input.LT(1);
        int this__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 53) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:275:7: ( THIS )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:275:9: THIS
            {
            match(input,THIS,FOLLOW_THIS_in_this_1501); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 53, this__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "this_"

    public static class super__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "super_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:276:1: super_ : SUPER ;
    public final JavaParser.super__return super_() throws RecognitionException {
        JavaParser.super__return retval = new JavaParser.super__return();
        retval.start = input.LT(1);
        int super__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 54) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:276:8: ( SUPER )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:276:10: SUPER
            {
            match(input,SUPER,FOLLOW_SUPER_in_super_1510); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 54, super__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "super_"

    public static class void__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "void_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:277:1: void_ : VOID ;
    public final JavaParser.void__return void_() throws RecognitionException {
        JavaParser.void__return retval = new JavaParser.void__return();
        retval.start = input.LT(1);
        int void__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 55) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:277:7: ( VOID )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:277:9: VOID
            {
            match(input,VOID,FOLLOW_VOID_in_void_1519); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 55, void__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "void_"

    public static class public__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "public_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:278:1: public_ : PUBLIC ;
    public final JavaParser.public__return public_() throws RecognitionException {
        JavaParser.public__return retval = new JavaParser.public__return();
        retval.start = input.LT(1);
        int public__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 56) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:278:9: ( PUBLIC )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:278:11: PUBLIC
            {
            match(input,PUBLIC,FOLLOW_PUBLIC_in_public_1528); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 56, public__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "public_"

    public static class private__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "private_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:279:1: private_ : PRIVATE ;
    public final JavaParser.private__return private_() throws RecognitionException {
        JavaParser.private__return retval = new JavaParser.private__return();
        retval.start = input.LT(1);
        int private__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 57) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:279:9: ( PRIVATE )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:279:11: PRIVATE
            {
            match(input,PRIVATE,FOLLOW_PRIVATE_in_private_1536); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 57, private__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "private_"

    public static class protected__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "protected_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:280:1: protected_ : PROTECTED ;
    public final JavaParser.protected__return protected_() throws RecognitionException {
        JavaParser.protected__return retval = new JavaParser.protected__return();
        retval.start = input.LT(1);
        int protected__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 58) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:281:2: ( PROTECTED )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:281:4: PROTECTED
            {
            match(input,PROTECTED,FOLLOW_PROTECTED_in_protected_1546); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 58, protected__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "protected_"

    public static class static__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "static_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:282:1: static_ : STATIC ;
    public final JavaParser.static__return static_() throws RecognitionException {
        JavaParser.static__return retval = new JavaParser.static__return();
        retval.start = input.LT(1);
        int static__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 59) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:282:9: ( STATIC )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:282:11: STATIC
            {
            match(input,STATIC,FOLLOW_STATIC_in_static_1555); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 59, static__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "static_"

    public static class final__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "final_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:283:1: final_ : FINAL ;
    public final JavaParser.final__return final_() throws RecognitionException {
        JavaParser.final__return retval = new JavaParser.final__return();
        retval.start = input.LT(1);
        int final__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 60) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:283:8: ( FINAL )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:283:10: FINAL
            {
            match(input,FINAL,FOLLOW_FINAL_in_final_1564); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 60, final__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "final_"

    public static class transient__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "transient_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:284:1: transient_ : TRANSIENT ;
    public final JavaParser.transient__return transient_() throws RecognitionException {
        JavaParser.transient__return retval = new JavaParser.transient__return();
        retval.start = input.LT(1);
        int transient__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 61) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:285:2: ( TRANSIENT )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:285:4: TRANSIENT
            {
            match(input,TRANSIENT,FOLLOW_TRANSIENT_in_transient_1574); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 61, transient__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "transient_"

    public static class new__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "new_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:286:1: new_ : NEW ;
    public final JavaParser.new__return new_() throws RecognitionException {
        JavaParser.new__return retval = new JavaParser.new__return();
        retval.start = input.LT(1);
        int new__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 62) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:286:6: ( NEW )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:286:8: NEW
            {
            match(input,NEW,FOLLOW_NEW_in_new_1583); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 62, new__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "new_"

    public static class try__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "try_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:287:1: try_ : TRY ;
    public final JavaParser.try__return try_() throws RecognitionException {
        JavaParser.try__return retval = new JavaParser.try__return();
        retval.start = input.LT(1);
        int try__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 63) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:287:6: ( TRY )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:287:8: TRY
            {
            match(input,TRY,FOLLOW_TRY_in_try_1592); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 63, try__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "try_"

    public static class catch__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "catch_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:288:1: catch_ : CATCH ;
    public final JavaParser.catch__return catch_() throws RecognitionException {
        JavaParser.catch__return retval = new JavaParser.catch__return();
        retval.start = input.LT(1);
        int catch__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 64) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:288:8: ( CATCH )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:288:10: CATCH
            {
            match(input,CATCH,FOLLOW_CATCH_in_catch_1601); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 64, catch__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "catch_"

    public static class finally__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "finally_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:289:1: finally_ : FINALLY ;
    public final JavaParser.finally__return finally_() throws RecognitionException {
        JavaParser.finally__return retval = new JavaParser.finally__return();
        retval.start = input.LT(1);
        int finally__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 65) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:289:9: ( FINALLY )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:289:11: FINALLY
            {
            match(input,FINALLY,FOLLOW_FINALLY_in_finally_1609); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 65, finally__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "finally_"


    // $ANTLR start "for_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:291:1: for_ : FOR ;
    public final void for_() throws RecognitionException {
        int for__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 66) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:291:6: ( FOR )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:291:8: FOR
            {
            match(input,FOR,FOLLOW_FOR_in_for_1619); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              helper.registerOperator("for()", 1);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 66, for__StartIndex); }
        }
        return ;
    }
    // $ANTLR end "for_"


    // $ANTLR start "while_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:292:1: while_ : WHILE ;
    public final void while_() throws RecognitionException {
        int while__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 67) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:292:8: ( WHILE )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:292:10: WHILE
            {
            match(input,WHILE,FOLLOW_WHILE_in_while_1628); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              helper.registerOperator("while()", 1);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 67, while__StartIndex); }
        }
        return ;
    }
    // $ANTLR end "while_"


    // $ANTLR start "do_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:293:1: do_ : DO ;
    public final void do_() throws RecognitionException {
        int do__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 68) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:293:5: ( DO )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:293:7: DO
            {
            match(input,DO,FOLLOW_DO_in_do_1637); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              helper.registerOperator("do()", 1);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 68, do__StartIndex); }
        }
        return ;
    }
    // $ANTLR end "do_"


    // $ANTLR start "if_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:294:1: if_ : IF ;
    public final void if_() throws RecognitionException {
        int if__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 69) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:294:5: ( IF )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:294:7: IF
            {
            match(input,IF,FOLLOW_IF_in_if_1646); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              helper.registerOperator("if()", 1);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 69, if__StartIndex); }
        }
        return ;
    }
    // $ANTLR end "if_"


    // $ANTLR start "else_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:295:1: else_ : ELSE ;
    public final void else_() throws RecognitionException {
        int else__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 70) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:295:7: ( ELSE )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:295:9: ELSE
            {
            match(input,ELSE,FOLLOW_ELSE_in_else_1655); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              helper.registerOperator("else");
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 70, else__StartIndex); }
        }
        return ;
    }
    // $ANTLR end "else_"

    public static class return__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "return_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:297:1: return_ : RETURN ;
    public final JavaParser.return__return return_() throws RecognitionException {
        JavaParser.return__return retval = new JavaParser.return__return();
        retval.start = input.LT(1);
        int return__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 71) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:297:9: ( RETURN )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:297:11: RETURN
            {
            match(input,RETURN,FOLLOW_RETURN_in_return_1665); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 71, return__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "return_"

    public static class break__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "break_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:298:1: break_ : BREAK ;
    public final JavaParser.break__return break_() throws RecognitionException {
        JavaParser.break__return retval = new JavaParser.break__return();
        retval.start = input.LT(1);
        int break__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 72) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:298:8: ( BREAK )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:298:10: BREAK
            {
            match(input,BREAK,FOLLOW_BREAK_in_break_1674); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 72, break__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "break_"

    public static class continue__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "continue_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:299:1: continue_ : CONTINUE ;
    public final JavaParser.continue__return continue_() throws RecognitionException {
        JavaParser.continue__return retval = new JavaParser.continue__return();
        retval.start = input.LT(1);
        int continue__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 73) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:300:2: ( CONTINUE )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:300:4: CONTINUE
            {
            match(input,CONTINUE,FOLLOW_CONTINUE_in_continue_1684); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 73, continue__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "continue_"

    public static class semicolon_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "semicolon"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:306:1: semicolon : SEMICOLON ;
    public final JavaParser.semicolon_return semicolon() throws RecognitionException {
        JavaParser.semicolon_return retval = new JavaParser.semicolon_return();
        retval.start = input.LT(1);
        int semicolon_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 74) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:307:2: ( SEMICOLON )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:307:4: SEMICOLON
            {
            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_semicolon1699); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 74, semicolon_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "semicolon"

    public static class comma_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "comma"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:308:1: comma : COMMA ;
    public final JavaParser.comma_return comma() throws RecognitionException {
        JavaParser.comma_return retval = new JavaParser.comma_return();
        retval.start = input.LT(1);
        int comma_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 75) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:308:7: ( COMMA )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:308:9: COMMA
            {
            match(input,COMMA,FOLLOW_COMMA_in_comma1708); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 75, comma_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "comma"

    public static class colon_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "colon"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:309:1: colon : COLON ;
    public final JavaParser.colon_return colon() throws RecognitionException {
        JavaParser.colon_return retval = new JavaParser.colon_return();
        retval.start = input.LT(1);
        int colon_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 76) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:309:7: ( COLON )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:309:9: COLON
            {
            match(input,COLON,FOLLOW_COLON_in_colon1717); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 76, colon_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "colon"

    public static class dot_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "dot"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:310:1: dot : DOT ;
    public final JavaParser.dot_return dot() throws RecognitionException {
        JavaParser.dot_return retval = new JavaParser.dot_return();
        retval.start = input.LT(1);
        int dot_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 77) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:310:5: ( DOT )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:310:7: DOT
            {
            match(input,DOT,FOLLOW_DOT_in_dot1726); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 77, dot_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "dot"

    public static class assign_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "assign"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:311:1: assign : ( ASSIGN | INCASSIGN | DECASSIGN );
    public final JavaParser.assign_return assign() throws RecognitionException {
        JavaParser.assign_return retval = new JavaParser.assign_return();
        retval.start = input.LT(1);
        int assign_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 78) ) { return retval; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:311:8: ( ASSIGN | INCASSIGN | DECASSIGN )
            int alt80=3;
            switch ( input.LA(1) ) {
            case ASSIGN:
                {
                alt80=1;
                }
                break;
            case INCASSIGN:
                {
                alt80=2;
                }
                break;
            case DECASSIGN:
                {
                alt80=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 80, 0, input);

                throw nvae;
            }

            switch (alt80) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:311:10: ASSIGN
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_assign1735); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:312:4: INCASSIGN
                    {
                    match(input,INCASSIGN,FOLLOW_INCASSIGN_in_assign1742); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:313:4: DECASSIGN
                    {
                    match(input,DECASSIGN,FOLLOW_DECASSIGN_in_assign1749); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 78, assign_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "assign"


    // $ANTLR start "block_begin"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:315:1: block_begin : OPEN_CURLY_BRACKET ;
    public final void block_begin() throws RecognitionException {
        int block_begin_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 79) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:316:2: ( OPEN_CURLY_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:316:4: OPEN_CURLY_BRACKET
            {
            match(input,OPEN_CURLY_BRACKET,FOLLOW_OPEN_CURLY_BRACKET_in_block_begin1761); if (state.failed) return ;
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
            if ( state.backtracking>0 ) { memoize(input, 79, block_begin_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "block_begin"


    // $ANTLR start "block_end"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:317:1: block_end : CLOSE_CURLY_BRACKET ;
    public final void block_end() throws RecognitionException {
        int block_end_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 80) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:318:2: ( CLOSE_CURLY_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:318:4: CLOSE_CURLY_BRACKET
            {
            match(input,CLOSE_CURLY_BRACKET,FOLLOW_CLOSE_CURLY_BRACKET_in_block_end1771); if (state.failed) return ;
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
            if ( state.backtracking>0 ) { memoize(input, 80, block_end_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "block_end"


    // $ANTLR start "open_bracket"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:319:1: open_bracket : OPEN_BRACKET ;
    public final void open_bracket() throws RecognitionException {
        int open_bracket_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 81) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:320:2: ( OPEN_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:320:4: OPEN_BRACKET
            {
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_open_bracket1781); if (state.failed) return ;
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
            if ( state.backtracking>0 ) { memoize(input, 81, open_bracket_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "open_bracket"


    // $ANTLR start "close_bracket"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:321:1: close_bracket : CLOSE_BRACKET ;
    public final void close_bracket() throws RecognitionException {
        int close_bracket_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 82) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:322:2: ( CLOSE_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:322:4: CLOSE_BRACKET
            {
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_close_bracket1791); if (state.failed) return ;
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
            if ( state.backtracking>0 ) { memoize(input, 82, close_bracket_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "close_bracket"


    // $ANTLR start "open_rect_bracket"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:323:1: open_rect_bracket : OPEN_RECT_BRACKET ;
    public final void open_rect_bracket() throws RecognitionException {
        int open_rect_bracket_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 83) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:324:2: ( OPEN_RECT_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:324:4: OPEN_RECT_BRACKET
            {
            match(input,OPEN_RECT_BRACKET,FOLLOW_OPEN_RECT_BRACKET_in_open_rect_bracket1802); if (state.failed) return ;
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
            if ( state.backtracking>0 ) { memoize(input, 83, open_rect_bracket_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "open_rect_bracket"


    // $ANTLR start "close_rect_bracket"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:325:1: close_rect_bracket : CLOSE_RECT_BRACKET ;
    public final void close_rect_bracket() throws RecognitionException {
        int close_rect_bracket_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 84) ) { return ; }
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:326:2: ( CLOSE_RECT_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:326:4: CLOSE_RECT_BRACKET
            {
            match(input,CLOSE_RECT_BRACKET,FOLLOW_CLOSE_RECT_BRACKET_in_close_rect_bracket1812); if (state.failed) return ;
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
            if ( state.backtracking>0 ) { memoize(input, 84, close_rect_bracket_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "close_rect_bracket"

    // $ANTLR start synpred26_JavaParser
    public final void synpred26_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:72:17: ( class_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:72:17: class_def
        {
        pushFollow(FOLLOW_class_def_in_synpred26_JavaParser324);
        class_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred26_JavaParser

    // $ANTLR start synpred27_JavaParser
    public final void synpred27_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:72:29: ( constructor_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:72:29: constructor_def
        {
        pushFollow(FOLLOW_constructor_def_in_synpred27_JavaParser328);
        constructor_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred27_JavaParser

    // $ANTLR start synpred28_JavaParser
    public final void synpred28_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:72:47: ( method_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:72:47: method_def
        {
        pushFollow(FOLLOW_method_def_in_synpred28_JavaParser332);
        method_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred28_JavaParser

    // $ANTLR start synpred29_JavaParser
    public final void synpred29_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:72:60: ( field_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:72:60: field_def
        {
        pushFollow(FOLLOW_field_def_in_synpred29_JavaParser336);
        field_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred29_JavaParser

    // $ANTLR start synpred44_JavaParser
    public final void synpred44_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:93:6: ( binary_operator value )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:93:6: binary_operator value
        {
        pushFollow(FOLLOW_binary_operator_in_synpred44_JavaParser488);
        binary_operator();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_value_in_synpred44_JavaParser490);
        value();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred44_JavaParser

    // $ANTLR start synpred68_JavaParser
    public final void synpred68_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:125:4: ( variable_assignment semicolon )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:125:4: variable_assignment semicolon
        {
        pushFollow(FOLLOW_variable_assignment_in_synpred68_JavaParser677);
        variable_assignment();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_semicolon_in_synpred68_JavaParser679);
        semicolon();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred68_JavaParser

    // $ANTLR start synpred69_JavaParser
    public final void synpred69_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:126:4: ( variable_def semicolon )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:126:4: variable_def semicolon
        {
        pushFollow(FOLLOW_variable_def_in_synpred69_JavaParser684);
        variable_def();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_semicolon_in_synpred69_JavaParser686);
        semicolon();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred69_JavaParser

    // $ANTLR start synpred70_JavaParser
    public final void synpred70_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:127:4: ( method_call semicolon )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:127:4: method_call semicolon
        {
        pushFollow(FOLLOW_method_call_in_synpred70_JavaParser691);
        method_call();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_semicolon_in_synpred70_JavaParser693);
        semicolon();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred70_JavaParser

    // $ANTLR start synpred76_JavaParser
    public final void synpred76_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:132:25: ( unary )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:132:25: unary
        {
        pushFollow(FOLLOW_unary_in_synpred76_JavaParser724);
        unary();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred76_JavaParser

    // $ANTLR start synpred77_JavaParser
    public final void synpred77_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:132:4: ( ( unary )? variable_name ( unary )? )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:132:4: ( unary )? variable_name ( unary )?
        {
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:132:4: ( unary )?
        int alt106=2;
        int LA106_0 = input.LA(1);

        if ( ((LA106_0>=INC && LA106_0<=DEC)||LA106_0==NOT) ) {
            alt106=1;
        }
        switch (alt106) {
            case 1 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                {
                pushFollow(FOLLOW_unary_in_synpred77_JavaParser719);
                unary();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        pushFollow(FOLLOW_variable_name_in_synpred77_JavaParser722);
        variable_name();

        state._fsp--;
        if (state.failed) return ;
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:132:25: ( unary )?
        int alt107=2;
        int LA107_0 = input.LA(1);

        if ( ((LA107_0>=INC && LA107_0<=DEC)||LA107_0==NOT) ) {
            alt107=1;
        }
        switch (alt107) {
            case 1 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                {
                pushFollow(FOLLOW_unary_in_synpred77_JavaParser724);
                unary();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred77_JavaParser

    // $ANTLR start synpred82_JavaParser
    public final void synpred82_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:141:4: ( variable_assignment )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:141:4: variable_assignment
        {
        pushFollow(FOLLOW_variable_assignment_in_synpred82_JavaParser761);
        variable_assignment();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred82_JavaParser

    // $ANTLR start synpred83_JavaParser
    public final void synpred83_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:142:4: ( variable_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:142:4: variable_def
        {
        pushFollow(FOLLOW_variable_def_in_synpred83_JavaParser766);
        variable_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred83_JavaParser

    // $ANTLR start synpred84_JavaParser
    public final void synpred84_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:143:4: ( method_call )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:143:4: method_call
        {
        pushFollow(FOLLOW_method_call_in_synpred84_JavaParser771);
        method_call();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred84_JavaParser

    // $ANTLR start synpred88_JavaParser
    public final void synpred88_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:145:4: ( ( unary )? variable_name ( unary )? )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:145:4: ( unary )? variable_name ( unary )?
        {
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:145:4: ( unary )?
        int alt108=2;
        int LA108_0 = input.LA(1);

        if ( ((LA108_0>=INC && LA108_0<=DEC)||LA108_0==NOT) ) {
            alt108=1;
        }
        switch (alt108) {
            case 1 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                {
                pushFollow(FOLLOW_unary_in_synpred88_JavaParser781);
                unary();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        pushFollow(FOLLOW_variable_name_in_synpred88_JavaParser784);
        variable_name();

        state._fsp--;
        if (state.failed) return ;
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:145:25: ( unary )?
        int alt109=2;
        int LA109_0 = input.LA(1);

        if ( ((LA109_0>=INC && LA109_0<=DEC)||LA109_0==NOT) ) {
            alt109=1;
        }
        switch (alt109) {
            case 1 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                {
                pushFollow(FOLLOW_unary_in_synpred88_JavaParser786);
                unary();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred88_JavaParser

    // $ANTLR start synpred100_JavaParser
    public final void synpred100_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:11: ( for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon ( value )? semicolon ( statement_wosemicolon ( comma statement_wosemicolon )* )? CLOSE_BRACKET code )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:11: for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon ( value )? semicolon ( statement_wosemicolon ( comma statement_wosemicolon )* )? CLOSE_BRACKET code
        {
        pushFollow(FOLLOW_for__in_synpred100_JavaParser867);
        for_();

        state._fsp--;
        if (state.failed) return ;
        match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_synpred100_JavaParser869); if (state.failed) return ;
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:29: ( variable_def ( comma variable_def )* )?
        int alt113=2;
        int LA113_0 = input.LA(1);

        if ( (LA113_0==VOID||(LA113_0>=BOOLEAN && LA113_0<=DOUBLE)||LA113_0==AT||LA113_0==ID) ) {
            alt113=1;
        }
        switch (alt113) {
            case 1 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:30: variable_def ( comma variable_def )*
                {
                pushFollow(FOLLOW_variable_def_in_synpred100_JavaParser872);
                variable_def();

                state._fsp--;
                if (state.failed) return ;
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:43: ( comma variable_def )*
                loop112:
                do {
                    int alt112=2;
                    int LA112_0 = input.LA(1);

                    if ( (LA112_0==COMMA) ) {
                        alt112=1;
                    }


                    switch (alt112) {
                	case 1 :
                	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:44: comma variable_def
                	    {
                	    pushFollow(FOLLOW_comma_in_synpred100_JavaParser875);
                	    comma();

                	    state._fsp--;
                	    if (state.failed) return ;
                	    pushFollow(FOLLOW_variable_def_in_synpred100_JavaParser877);
                	    variable_def();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop112;
                    }
                } while (true);


                }
                break;

        }

        pushFollow(FOLLOW_semicolon_in_synpred100_JavaParser883);
        semicolon();

        state._fsp--;
        if (state.failed) return ;
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:77: ( value )?
        int alt114=2;
        int LA114_0 = input.LA(1);

        if ( (LA114_0==NEW||LA114_0==NULL||(LA114_0>=INC && LA114_0<=DEC)||LA114_0==NOT||LA114_0==OPEN_BRACKET||(LA114_0>=BOOL_CONST && LA114_0<=INT_CONST)||LA114_0==FLOAT_CONST||(LA114_0>=STRING_CONST && LA114_0<=CHAR_CONST)) ) {
            alt114=1;
        }
        switch (alt114) {
            case 1 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: value
                {
                pushFollow(FOLLOW_value_in_synpred100_JavaParser885);
                value();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        pushFollow(FOLLOW_semicolon_in_synpred100_JavaParser888);
        semicolon();

        state._fsp--;
        if (state.failed) return ;
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:94: ( statement_wosemicolon ( comma statement_wosemicolon )* )?
        int alt116=2;
        int LA116_0 = input.LA(1);

        if ( (LA116_0==VOID||LA116_0==RETURN||(LA116_0>=THIS && LA116_0<=IF)||LA116_0==TRY||(LA116_0>=BOOLEAN && LA116_0<=DOUBLE)||(LA116_0>=INC && LA116_0<=DEC)||LA116_0==NOT||LA116_0==AT||LA116_0==ID) ) {
            alt116=1;
        }
        switch (alt116) {
            case 1 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:95: statement_wosemicolon ( comma statement_wosemicolon )*
                {
                pushFollow(FOLLOW_statement_wosemicolon_in_synpred100_JavaParser891);
                statement_wosemicolon();

                state._fsp--;
                if (state.failed) return ;
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:117: ( comma statement_wosemicolon )*
                loop115:
                do {
                    int alt115=2;
                    int LA115_0 = input.LA(1);

                    if ( (LA115_0==COMMA) ) {
                        alt115=1;
                    }


                    switch (alt115) {
                	case 1 :
                	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:118: comma statement_wosemicolon
                	    {
                	    pushFollow(FOLLOW_comma_in_synpred100_JavaParser894);
                	    comma();

                	    state._fsp--;
                	    if (state.failed) return ;
                	    pushFollow(FOLLOW_statement_wosemicolon_in_synpred100_JavaParser896);
                	    statement_wosemicolon();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop115;
                    }
                } while (true);


                }
                break;

        }

        match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_synpred100_JavaParser902); if (state.failed) return ;
        pushFollow(FOLLOW_code_in_synpred100_JavaParser904);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred100_JavaParser

    // $ANTLR start synpred101_JavaParser
    public final void synpred101_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:172:54: ( else_ code )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:172:54: else_ code
        {
        pushFollow(FOLLOW_else__in_synpred101_JavaParser986);
        else_();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_code_in_synpred101_JavaParser988);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred101_JavaParser

    // $ANTLR start synpred102_JavaParser
    public final void synpred102_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:176:15: ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:176:15: catch_ OPEN_BRACKET id id CLOSE_BRACKET code
        {
        pushFollow(FOLLOW_catch__in_synpred102_JavaParser1006);
        catch_();

        state._fsp--;
        if (state.failed) return ;
        match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_synpred102_JavaParser1008); if (state.failed) return ;
        pushFollow(FOLLOW_id_in_synpred102_JavaParser1010);
        id();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_id_in_synpred102_JavaParser1012);
        id();

        state._fsp--;
        if (state.failed) return ;
        match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_synpred102_JavaParser1014); if (state.failed) return ;
        pushFollow(FOLLOW_code_in_synpred102_JavaParser1016);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred102_JavaParser

    // $ANTLR start synpred103_JavaParser
    public final void synpred103_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:176:63: ( finally_ code )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:176:63: finally_ code
        {
        pushFollow(FOLLOW_finally__in_synpred103_JavaParser1021);
        finally_();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_code_in_synpred103_JavaParser1023);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred103_JavaParser

    // Delegated rules

    public final boolean synpred83_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred83_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred82_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred82_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred77_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred77_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred28_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred28_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred44_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred44_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred100_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred100_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred69_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred69_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred101_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred101_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred29_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred29_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred84_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred84_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred102_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred102_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred88_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred88_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred70_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred70_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred68_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred68_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred76_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred76_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred103_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred103_JavaParser_fragment(); // can never throw exception
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


    protected DFA22 dfa22 = new DFA22(this);
    protected DFA33 dfa33 = new DFA33(this);
    protected DFA34 dfa34 = new DFA34(this);
    protected DFA41 dfa41 = new DFA41(this);
    protected DFA52 dfa52 = new DFA52(this);
    protected DFA55 dfa55 = new DFA55(this);
    static final String DFA22_eotS =
        "\27\uffff";
    static final String DFA22_eofS =
        "\27\uffff";
    static final String DFA22_minS =
        "\1\6\1\uffff\7\0\1\uffff\12\0\3\uffff";
    static final String DFA22_maxS =
        "\1\112\1\uffff\7\0\1\uffff\12\0\3\uffff";
    static final String DFA22_acceptS =
        "\1\uffff\1\5\7\uffff\1\1\12\uffff\1\2\1\3\1\4";
    static final String DFA22_specialS =
        "\2\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\7\1\10\1\11\1\12"+
        "\1\13\1\14\1\15\1\16\1\17\1\20\3\uffff}>";
    static final String[] DFA22_transitionS = {
            "\1\11\2\uffff\1\23\21\uffff\1\3\1\4\1\5\1\7\1\6\1\10\1\13\1"+
            "\14\1\15\1\16\1\17\1\20\1\21\1\22\1\uffff\1\1\34\uffff\1\2\2"+
            "\uffff\1\12",
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
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
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
            return "()* loopback of 72:16: ( class_def | constructor_def | method_def | field_def )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA22_2 = input.LA(1);

                         
                        int index22_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred26_JavaParser()) ) {s = 9;}

                        else if ( (synpred27_JavaParser()) ) {s = 20;}

                        else if ( (synpred28_JavaParser()) ) {s = 21;}

                        else if ( (synpred29_JavaParser()) ) {s = 22;}

                         
                        input.seek(index22_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA22_3 = input.LA(1);

                         
                        int index22_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred26_JavaParser()) ) {s = 9;}

                        else if ( (synpred27_JavaParser()) ) {s = 20;}

                        else if ( (synpred28_JavaParser()) ) {s = 21;}

                        else if ( (synpred29_JavaParser()) ) {s = 22;}

                         
                        input.seek(index22_3);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA22_4 = input.LA(1);

                         
                        int index22_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred26_JavaParser()) ) {s = 9;}

                        else if ( (synpred27_JavaParser()) ) {s = 20;}

                        else if ( (synpred28_JavaParser()) ) {s = 21;}

                        else if ( (synpred29_JavaParser()) ) {s = 22;}

                         
                        input.seek(index22_4);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA22_5 = input.LA(1);

                         
                        int index22_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred26_JavaParser()) ) {s = 9;}

                        else if ( (synpred27_JavaParser()) ) {s = 20;}

                        else if ( (synpred28_JavaParser()) ) {s = 21;}

                        else if ( (synpred29_JavaParser()) ) {s = 22;}

                         
                        input.seek(index22_5);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA22_6 = input.LA(1);

                         
                        int index22_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred26_JavaParser()) ) {s = 9;}

                        else if ( (synpred27_JavaParser()) ) {s = 20;}

                        else if ( (synpred28_JavaParser()) ) {s = 21;}

                        else if ( (synpred29_JavaParser()) ) {s = 22;}

                         
                        input.seek(index22_6);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA22_7 = input.LA(1);

                         
                        int index22_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred26_JavaParser()) ) {s = 9;}

                        else if ( (synpred27_JavaParser()) ) {s = 20;}

                        else if ( (synpred28_JavaParser()) ) {s = 21;}

                        else if ( (synpred29_JavaParser()) ) {s = 22;}

                         
                        input.seek(index22_7);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA22_8 = input.LA(1);

                         
                        int index22_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred26_JavaParser()) ) {s = 9;}

                        else if ( (synpred27_JavaParser()) ) {s = 20;}

                        else if ( (synpred28_JavaParser()) ) {s = 21;}

                        else if ( (synpred29_JavaParser()) ) {s = 22;}

                         
                        input.seek(index22_8);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA22_10 = input.LA(1);

                         
                        int index22_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred27_JavaParser()) ) {s = 20;}

                        else if ( (synpred28_JavaParser()) ) {s = 21;}

                        else if ( (synpred29_JavaParser()) ) {s = 22;}

                         
                        input.seek(index22_10);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA22_11 = input.LA(1);

                         
                        int index22_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred28_JavaParser()) ) {s = 21;}

                        else if ( (synpred29_JavaParser()) ) {s = 22;}

                         
                        input.seek(index22_11);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA22_12 = input.LA(1);

                         
                        int index22_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred28_JavaParser()) ) {s = 21;}

                        else if ( (synpred29_JavaParser()) ) {s = 22;}

                         
                        input.seek(index22_12);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA22_13 = input.LA(1);

                         
                        int index22_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred28_JavaParser()) ) {s = 21;}

                        else if ( (synpred29_JavaParser()) ) {s = 22;}

                         
                        input.seek(index22_13);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA22_14 = input.LA(1);

                         
                        int index22_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred28_JavaParser()) ) {s = 21;}

                        else if ( (synpred29_JavaParser()) ) {s = 22;}

                         
                        input.seek(index22_14);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA22_15 = input.LA(1);

                         
                        int index22_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred28_JavaParser()) ) {s = 21;}

                        else if ( (synpred29_JavaParser()) ) {s = 22;}

                         
                        input.seek(index22_15);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA22_16 = input.LA(1);

                         
                        int index22_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred28_JavaParser()) ) {s = 21;}

                        else if ( (synpred29_JavaParser()) ) {s = 22;}

                         
                        input.seek(index22_16);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA22_17 = input.LA(1);

                         
                        int index22_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred28_JavaParser()) ) {s = 21;}

                        else if ( (synpred29_JavaParser()) ) {s = 22;}

                         
                        input.seek(index22_17);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA22_18 = input.LA(1);

                         
                        int index22_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred28_JavaParser()) ) {s = 21;}

                        else if ( (synpred29_JavaParser()) ) {s = 22;}

                         
                        input.seek(index22_18);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA22_19 = input.LA(1);

                         
                        int index22_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred28_JavaParser()) ) {s = 21;}

                        else if ( (synpred29_JavaParser()) ) {s = 22;}

                         
                        input.seek(index22_19);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 22, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA33_eotS =
        "\15\uffff";
    static final String DFA33_eofS =
        "\5\uffff\1\12\1\uffff\1\12\5\uffff";
    static final String DFA33_minS =
        "\1\12\3\16\1\uffff\1\53\1\uffff\1\53\1\6\2\uffff\1\112\1\uffff";
    static final String DFA33_maxS =
        "\4\123\1\uffff\1\106\1\uffff\1\106\1\112\2\uffff\1\112\1\uffff";
    static final String DFA33_acceptS =
        "\4\uffff\1\1\1\uffff\1\5\2\uffff\1\4\1\3\1\uffff\1\2";
    static final String DFA33_specialS =
        "\15\uffff}>";
    static final String[] DFA33_transitionS = {
            "\1\6\3\uffff\1\4\43\uffff\1\1\1\2\10\uffff\1\3\14\uffff\1\4"+
            "\1\5\1\4\1\uffff\1\4\4\uffff\2\4",
            "\1\4\72\uffff\1\4\1\7\1\4\1\uffff\1\4\4\uffff\2\4",
            "\1\4\72\uffff\1\4\1\7\1\4\1\uffff\1\4\4\uffff\2\4",
            "\1\4\72\uffff\1\4\1\7\1\4\1\uffff\1\4\4\uffff\2\4",
            "",
            "\4\12\3\uffff\6\12\4\uffff\1\12\1\10\5\12\1\11\1\12\1\uffff"+
            "\1\12",
            "",
            "\4\12\3\uffff\6\12\4\uffff\1\12\1\13\5\12\1\11\1\12\1\uffff"+
            "\1\12",
            "\1\14\103\uffff\1\5",
            "",
            "",
            "\1\7",
            ""
    };

    static final short[] DFA33_eot = DFA.unpackEncodedString(DFA33_eotS);
    static final short[] DFA33_eof = DFA.unpackEncodedString(DFA33_eofS);
    static final char[] DFA33_min = DFA.unpackEncodedStringToUnsignedChars(DFA33_minS);
    static final char[] DFA33_max = DFA.unpackEncodedStringToUnsignedChars(DFA33_maxS);
    static final short[] DFA33_accept = DFA.unpackEncodedString(DFA33_acceptS);
    static final short[] DFA33_special = DFA.unpackEncodedString(DFA33_specialS);
    static final short[][] DFA33_transition;

    static {
        int numStates = DFA33_transitionS.length;
        DFA33_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA33_transition[i] = DFA.unpackEncodedString(DFA33_transitionS[i]);
        }
    }

    class DFA33 extends DFA {

        public DFA33(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 33;
            this.eot = DFA33_eot;
            this.eof = DFA33_eof;
            this.min = DFA33_min;
            this.max = DFA33_max;
            this.accept = DFA33_accept;
            this.special = DFA33_special;
            this.transition = DFA33_transition;
        }
        public String getDescription() {
            return "88:2: ( ( unary )? constant ( unary )? | class_name dot class_ | ( unary )? variable_name ( unary )? | ( unary )? method_call ( unary )? | new_class )";
        }
    }
    static final String DFA34_eotS =
        "\15\uffff";
    static final String DFA34_eofS =
        "\1\1\14\uffff";
    static final String DFA34_minS =
        "\1\53\1\uffff\12\0\1\uffff";
    static final String DFA34_maxS =
        "\1\106\1\uffff\12\0\1\uffff";
    static final String DFA34_acceptS =
        "\1\uffff\1\2\12\uffff\1\1";
    static final String DFA34_specialS =
        "\2\uffff\1\10\1\3\1\1\1\5\1\4\1\11\1\2\1\0\1\6\1\7\1\uffff}>";
    static final String[] DFA34_transitionS = {
            "\1\12\1\13\1\6\1\7\5\uffff\1\2\1\3\1\5\1\4\6\uffff\1\1\1\10"+
            "\1\11\1\uffff\1\1\1\uffff\1\1\1\uffff\1\1",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            ""
    };

    static final short[] DFA34_eot = DFA.unpackEncodedString(DFA34_eotS);
    static final short[] DFA34_eof = DFA.unpackEncodedString(DFA34_eofS);
    static final char[] DFA34_min = DFA.unpackEncodedStringToUnsignedChars(DFA34_minS);
    static final char[] DFA34_max = DFA.unpackEncodedStringToUnsignedChars(DFA34_maxS);
    static final short[] DFA34_accept = DFA.unpackEncodedString(DFA34_acceptS);
    static final short[] DFA34_special = DFA.unpackEncodedString(DFA34_specialS);
    static final short[][] DFA34_transition;

    static {
        int numStates = DFA34_transitionS.length;
        DFA34_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA34_transition[i] = DFA.unpackEncodedString(DFA34_transitionS[i]);
        }
    }

    class DFA34 extends DFA {

        public DFA34(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 34;
            this.eot = DFA34_eot;
            this.eof = DFA34_eof;
            this.min = DFA34_min;
            this.max = DFA34_max;
            this.accept = DFA34_accept;
            this.special = DFA34_special;
            this.transition = DFA34_transition;
        }
        public String getDescription() {
            return "()* loopback of 93:5: ( binary_operator value )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA34_9 = input.LA(1);

                         
                        int index34_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 12;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index34_9);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA34_4 = input.LA(1);

                         
                        int index34_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 12;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index34_4);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA34_8 = input.LA(1);

                         
                        int index34_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 12;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index34_8);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA34_3 = input.LA(1);

                         
                        int index34_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 12;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index34_3);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA34_6 = input.LA(1);

                         
                        int index34_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 12;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index34_6);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA34_5 = input.LA(1);

                         
                        int index34_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 12;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index34_5);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA34_10 = input.LA(1);

                         
                        int index34_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 12;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index34_10);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA34_11 = input.LA(1);

                         
                        int index34_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 12;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index34_11);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA34_2 = input.LA(1);

                         
                        int index34_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 12;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index34_2);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA34_7 = input.LA(1);

                         
                        int index34_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 12;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index34_7);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 34, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA41_eotS =
        "\16\uffff";
    static final String DFA41_eofS =
        "\16\uffff";
    static final String DFA41_minS =
        "\1\12\1\uffff\3\16\1\uffff\1\53\1\uffff\1\53\1\6\2\uffff\1\112\1"+
        "\uffff";
    static final String DFA41_maxS =
        "\1\123\1\uffff\3\123\1\uffff\1\104\1\uffff\1\104\1\112\2\uffff\1"+
        "\112\1\uffff";
    static final String DFA41_acceptS =
        "\1\uffff\1\1\3\uffff\1\2\1\uffff\1\6\2\uffff\1\5\1\4\1\uffff\1\3";
    static final String DFA41_specialS =
        "\16\uffff}>";
    static final String[] DFA41_transitionS = {
            "\1\7\3\uffff\1\5\34\uffff\4\1\3\uffff\1\2\1\3\4\1\4\uffff\1"+
            "\4\2\uffff\2\1\3\uffff\1\1\4\uffff\1\5\1\6\1\5\1\uffff\1\5\4"+
            "\uffff\2\5",
            "",
            "\1\5\72\uffff\1\5\1\10\1\5\1\uffff\1\5\4\uffff\2\5",
            "\1\5\72\uffff\1\5\1\10\1\5\1\uffff\1\5\4\uffff\2\5",
            "\1\5\72\uffff\1\5\1\10\1\5\1\uffff\1\5\4\uffff\2\5",
            "",
            "\4\13\3\uffff\6\13\4\uffff\1\13\1\11\1\uffff\3\13\1\uffff\1"+
            "\12\1\13",
            "",
            "\4\13\3\uffff\6\13\4\uffff\1\13\1\14\1\uffff\3\13\1\uffff\1"+
            "\12\1\13",
            "\1\15\103\uffff\1\6",
            "",
            "",
            "\1\10",
            ""
    };

    static final short[] DFA41_eot = DFA.unpackEncodedString(DFA41_eotS);
    static final short[] DFA41_eof = DFA.unpackEncodedString(DFA41_eofS);
    static final char[] DFA41_min = DFA.unpackEncodedStringToUnsignedChars(DFA41_minS);
    static final char[] DFA41_max = DFA.unpackEncodedStringToUnsignedChars(DFA41_maxS);
    static final short[] DFA41_accept = DFA.unpackEncodedString(DFA41_acceptS);
    static final short[] DFA41_special = DFA.unpackEncodedString(DFA41_specialS);
    static final short[][] DFA41_transition;

    static {
        int numStates = DFA41_transitionS.length;
        DFA41_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA41_transition[i] = DFA.unpackEncodedString(DFA41_transitionS[i]);
        }
    }

    class DFA41 extends DFA {

        public DFA41(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 41;
            this.eot = DFA41_eot;
            this.eof = DFA41_eof;
            this.min = DFA41_min;
            this.max = DFA41_max;
            this.accept = DFA41_accept;
            this.special = DFA41_special;
            this.transition = DFA41_transition;
        }
        public String getDescription() {
            return "95:17: ( | ( unary )? constant ( unary )? | class_name dot class_ | ( unary )? variable_name ( unary )? | ( unary )? method_call ( unary )? | new_class )";
        }
    }
    static final String DFA52_eotS =
        "\33\uffff";
    static final String DFA52_eofS =
        "\33\uffff";
    static final String DFA52_minS =
        "\1\11\2\uffff\1\0\27\uffff";
    static final String DFA52_maxS =
        "\1\112\2\uffff\1\0\27\uffff";
    static final String DFA52_acceptS =
        "\1\uffff\1\1\2\uffff\1\2\11\uffff\1\4\1\5\1\6\1\7\1\10\2\uffff\1"+
        "\11\1\12\1\13\1\14\1\15\1\3";
    static final String DFA52_specialS =
        "\3\uffff\1\0\27\uffff}>";
    static final String[] DFA52_transitionS = {
            "\1\4\1\uffff\1\17\1\21\1\20\1\uffff\2\1\1\25\1\27\1\26\1\30"+
            "\3\uffff\1\31\10\uffff\10\4\11\uffff\2\22\10\uffff\1\22\11\uffff"+
            "\1\16\1\4\2\uffff\1\3",
            "",
            "",
            "\1\uffff",
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
            ""
    };

    static final short[] DFA52_eot = DFA.unpackEncodedString(DFA52_eotS);
    static final short[] DFA52_eof = DFA.unpackEncodedString(DFA52_eofS);
    static final char[] DFA52_min = DFA.unpackEncodedStringToUnsignedChars(DFA52_minS);
    static final char[] DFA52_max = DFA.unpackEncodedStringToUnsignedChars(DFA52_maxS);
    static final short[] DFA52_accept = DFA.unpackEncodedString(DFA52_acceptS);
    static final short[] DFA52_special = DFA.unpackEncodedString(DFA52_specialS);
    static final short[][] DFA52_transition;

    static {
        int numStates = DFA52_transitionS.length;
        DFA52_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA52_transition[i] = DFA.unpackEncodedString(DFA52_transitionS[i]);
        }
    }

    class DFA52 extends DFA {

        public DFA52(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 52;
            this.eot = DFA52_eot;
            this.eof = DFA52_eof;
            this.min = DFA52_min;
            this.max = DFA52_max;
            this.accept = DFA52_accept;
            this.special = DFA52_special;
            this.transition = DFA52_transition;
        }
        public String getDescription() {
            return "124:1: statement : ( variable_assignment semicolon | variable_def semicolon | method_call semicolon | semicolon | return_statement | continue_ | break_ | ( unary )? variable_name ( unary )? | for_loop | while_loop | do_loop | if_else | try_catch );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA52_3 = input.LA(1);

                         
                        int index52_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred68_JavaParser()) ) {s = 1;}

                        else if ( (synpred69_JavaParser()) ) {s = 4;}

                        else if ( (synpred70_JavaParser()) ) {s = 26;}

                        else if ( (synpred77_JavaParser()) ) {s = 18;}

                         
                        input.seek(index52_3);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 52, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA55_eotS =
        "\30\uffff";
    static final String DFA55_eofS =
        "\30\uffff";
    static final String DFA55_minS =
        "\1\11\2\uffff\1\0\24\uffff";
    static final String DFA55_maxS =
        "\1\112\2\uffff\1\0\24\uffff";
    static final String DFA55_acceptS =
        "\1\uffff\1\1\2\uffff\1\2\11\uffff\1\4\1\5\2\uffff\1\6\1\7\1\10\1"+
        "\11\1\12\1\3";
    static final String DFA55_specialS =
        "\3\uffff\1\0\24\uffff}>";
    static final String[] DFA55_transitionS = {
            "\1\4\1\uffff\1\16\3\uffff\2\1\1\22\1\24\1\23\1\25\3\uffff\1"+
            "\26\10\uffff\10\4\11\uffff\2\17\10\uffff\1\17\12\uffff\1\4\2"+
            "\uffff\1\3",
            "",
            "",
            "\1\uffff",
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
            ""
    };

    static final short[] DFA55_eot = DFA.unpackEncodedString(DFA55_eotS);
    static final short[] DFA55_eof = DFA.unpackEncodedString(DFA55_eofS);
    static final char[] DFA55_min = DFA.unpackEncodedStringToUnsignedChars(DFA55_minS);
    static final char[] DFA55_max = DFA.unpackEncodedStringToUnsignedChars(DFA55_maxS);
    static final short[] DFA55_accept = DFA.unpackEncodedString(DFA55_acceptS);
    static final short[] DFA55_special = DFA.unpackEncodedString(DFA55_specialS);
    static final short[][] DFA55_transition;

    static {
        int numStates = DFA55_transitionS.length;
        DFA55_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA55_transition[i] = DFA.unpackEncodedString(DFA55_transitionS[i]);
        }
    }

    class DFA55 extends DFA {

        public DFA55(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 55;
            this.eot = DFA55_eot;
            this.eof = DFA55_eof;
            this.min = DFA55_min;
            this.max = DFA55_max;
            this.accept = DFA55_accept;
            this.special = DFA55_special;
            this.transition = DFA55_transition;
        }
        public String getDescription() {
            return "140:1: statement_wosemicolon : ( variable_assignment | variable_def | method_call | return_statement | ( unary )? variable_name ( unary )? | for_loop | while_loop | do_loop | if_else | try_catch );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA55_3 = input.LA(1);

                         
                        int index55_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred82_JavaParser()) ) {s = 1;}

                        else if ( (synpred83_JavaParser()) ) {s = 4;}

                        else if ( (synpred84_JavaParser()) ) {s = 23;}

                        else if ( (synpred88_JavaParser()) ) {s = 15;}

                         
                        input.seek(index55_3);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 55, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_package_def_in_file44 = new BitSet(new long[]{0x00000001F8000062L,0x0000000000000080L});
    public static final BitSet FOLLOW_import_def_in_file46 = new BitSet(new long[]{0x00000001F8000062L,0x0000000000000080L});
    public static final BitSet FOLLOW_class_def_in_file49 = new BitSet(new long[]{0x00000001F8000042L,0x0000000000000080L});
    public static final BitSet FOLLOW_package__in_package_def63 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_package_name_in_package_def65 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_semicolon_in_package_def67 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_import__in_import_def79 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_import_name_in_import_def81 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_semicolon_in_import_def83 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_class_def95 = new BitSet(new long[]{0x00000001F8000040L,0x0000000000000080L});
    public static final BitSet FOLLOW_modifier_in_class_def98 = new BitSet(new long[]{0x00000001F8000040L,0x0000000000000080L});
    public static final BitSet FOLLOW_class__in_class_def101 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_id_in_class_def103 = new BitSet(new long[]{0x8000020000000180L});
    public static final BitSet FOLLOW_generic_in_class_def105 = new BitSet(new long[]{0x8000020000000180L});
    public static final BitSet FOLLOW_extends__in_class_def109 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_class_name_in_class_def111 = new BitSet(new long[]{0x8000020000000180L});
    public static final BitSet FOLLOW_generic_in_class_def113 = new BitSet(new long[]{0x8000020000000180L});
    public static final BitSet FOLLOW_implements__in_class_def119 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_class_name_in_class_def121 = new BitSet(new long[]{0xC000020000000180L});
    public static final BitSet FOLLOW_comma_in_class_def124 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_class_name_in_class_def126 = new BitSet(new long[]{0xC000020000000180L});
    public static final BitSet FOLLOW_class_block_in_class_def132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_constructor_def148 = new BitSet(new long[]{0x00000001F8000000L,0x0000000000000480L});
    public static final BitSet FOLLOW_modifier_in_constructor_def151 = new BitSet(new long[]{0x00000001F8000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_id_in_constructor_def154 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_open_bracket_in_constructor_def156 = new BitSet(new long[]{0x000001FE00000200L,0x0000000000000410L});
    public static final BitSet FOLLOW_argument_def_in_constructor_def158 = new BitSet(new long[]{0x000001FE00000200L,0x0000000000000410L});
    public static final BitSet FOLLOW_close_bracket_in_constructor_def160 = new BitSet(new long[]{0x900C03FE011FBB80L,0x00000000000004C0L});
    public static final BitSet FOLLOW_code_in_constructor_def162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_method_def177 = new BitSet(new long[]{0x000001FFF8000200L,0x0000000000000480L});
    public static final BitSet FOLLOW_modifier_in_method_def180 = new BitSet(new long[]{0x000001FFF8000200L,0x0000000000000400L});
    public static final BitSet FOLLOW_variable_type_in_method_def183 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_id_in_method_def185 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_open_bracket_in_method_def187 = new BitSet(new long[]{0x000001FE00000200L,0x0000000000000410L});
    public static final BitSet FOLLOW_argument_def_in_method_def189 = new BitSet(new long[]{0x000001FE00000200L,0x0000000000000410L});
    public static final BitSet FOLLOW_close_bracket_in_method_def191 = new BitSet(new long[]{0x900C03FE011FBB80L,0x00000000000004C0L});
    public static final BitSet FOLLOW_code_in_method_def193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_field_def209 = new BitSet(new long[]{0x000001FFF8000200L,0x0000000000000480L});
    public static final BitSet FOLLOW_modifier_in_field_def212 = new BitSet(new long[]{0x000001FFF8000200L,0x0000000000000400L});
    public static final BitSet FOLLOW_variable_type_in_field_def215 = new BitSet(new long[]{0x0000000000018000L,0x0000000000000400L});
    public static final BitSet FOLLOW_variable_name_in_field_def217 = new BitSet(new long[]{0x0003800000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_assign_in_field_def220 = new BitSet(new long[]{0x100C00000001C400L,0x00000000000C2E08L});
    public static final BitSet FOLLOW_value_in_field_def222 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_semicolon_in_field_def226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_type_in_argument_def238 = new BitSet(new long[]{0x0000000000018000L,0x0000000000000400L});
    public static final BitSet FOLLOW_variable_name_in_argument_def240 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_comma_in_argument_def243 = new BitSet(new long[]{0x000001FE00000200L,0x0000000000000400L});
    public static final BitSet FOLLOW_variable_type_in_argument_def245 = new BitSet(new long[]{0x0000000000018000L,0x0000000000000400L});
    public static final BitSet FOLLOW_variable_name_in_argument_def247 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_annotation_in_variable_def262 = new BitSet(new long[]{0x000001FE00000200L,0x0000000000000480L});
    public static final BitSet FOLLOW_variable_type_in_variable_def265 = new BitSet(new long[]{0x0000000000018000L,0x0000000000000400L});
    public static final BitSet FOLLOW_variable_name_in_variable_def267 = new BitSet(new long[]{0x0003800000000002L});
    public static final BitSet FOLLOW_assign_in_variable_def270 = new BitSet(new long[]{0x100C00000001C400L,0x00000000000C2E08L});
    public static final BitSet FOLLOW_value_in_variable_def272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_public__in_modifier284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_private__in_modifier289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_protected__in_modifier294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_static__in_modifier299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_final__in_modifier304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_transient__in_modifier309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_begin_in_class_block321 = new BitSet(new long[]{0x000005FFF8000240L,0x0000000000000480L});
    public static final BitSet FOLLOW_class_def_in_class_block324 = new BitSet(new long[]{0x000005FFF8000240L,0x0000000000000480L});
    public static final BitSet FOLLOW_constructor_def_in_class_block328 = new BitSet(new long[]{0x000005FFF8000240L,0x0000000000000480L});
    public static final BitSet FOLLOW_method_def_in_class_block332 = new BitSet(new long[]{0x000005FFF8000240L,0x0000000000000480L});
    public static final BitSet FOLLOW_field_def_in_class_block336 = new BitSet(new long[]{0x000005FFF8000240L,0x0000000000000480L});
    public static final BitSet FOLLOW_block_end_in_class_block340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_name_in_method_call352 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_open_bracket_in_method_call354 = new BitSet(new long[]{0x100C01FE0001C600L,0x00000000000C2E18L});
    public static final BitSet FOLLOW_arguments_in_method_call356 = new BitSet(new long[]{0x000001FE00000200L,0x0000000000000410L});
    public static final BitSet FOLLOW_close_bracket_in_method_call358 = new BitSet(new long[]{0x2000000000000002L});
    public static final BitSet FOLLOW_dot_in_method_call361 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_id_in_method_call363 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_open_bracket_in_method_call365 = new BitSet(new long[]{0x100C01FE0001C600L,0x00000000000C2E18L});
    public static final BitSet FOLLOW_arguments_in_method_call367 = new BitSet(new long[]{0x000001FE00000200L,0x0000000000000410L});
    public static final BitSet FOLLOW_close_bracket_in_method_call369 = new BitSet(new long[]{0x2000000000000002L});
    public static final BitSet FOLLOW_annotation_name_in_annotation382 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_open_bracket_in_annotation385 = new BitSet(new long[]{0x100C00000001C400L,0x00000000000C2E08L});
    public static final BitSet FOLLOW_value_in_annotation387 = new BitSet(new long[]{0x400001FE00000200L,0x0000000000000410L});
    public static final BitSet FOLLOW_comma_in_annotation390 = new BitSet(new long[]{0x100C00000001C400L,0x00000000000C2E08L});
    public static final BitSet FOLLOW_value_in_annotation392 = new BitSet(new long[]{0x400001FE00000200L,0x0000000000000410L});
    public static final BitSet FOLLOW_close_bracket_in_annotation396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_generic408 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_id_in_generic411 = new BitSet(new long[]{0x4000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_comma_in_generic414 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_id_in_generic416 = new BitSet(new long[]{0x4000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_GT_in_generic421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_value437 = new BitSet(new long[]{0x100C000000004000L,0x00000000000C2A00L});
    public static final BitSet FOLLOW_constant_in_value440 = new BitSet(new long[]{0x90FC780000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_unary_in_value442 = new BitSet(new long[]{0x80F0780000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_class_name_in_value449 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_dot_in_value451 = new BitSet(new long[]{0x00000001F8000040L,0x0000000000000080L});
    public static final BitSet FOLLOW_class__in_value453 = new BitSet(new long[]{0x80F0780000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_unary_in_value458 = new BitSet(new long[]{0x0000000000018000L,0x0000000000000400L});
    public static final BitSet FOLLOW_variable_name_in_value461 = new BitSet(new long[]{0x90FC780000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_unary_in_value463 = new BitSet(new long[]{0x80F0780000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_unary_in_value469 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_method_call_in_value472 = new BitSet(new long[]{0x90FC780000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_unary_in_value474 = new BitSet(new long[]{0x80F0780000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_new_class_in_value481 = new BitSet(new long[]{0x80F0780000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_binary_operator_in_value488 = new BitSet(new long[]{0x100C00000001C400L,0x00000000000C2E08L});
    public static final BitSet FOLLOW_value_in_value490 = new BitSet(new long[]{0x80F0780000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_open_bracket_in_value500 = new BitSet(new long[]{0x90FC79FE0001C600L,0x00000000000C2E11L});
    public static final BitSet FOLLOW_unary_in_value507 = new BitSet(new long[]{0x100C000000004000L,0x00000000000C2A00L});
    public static final BitSet FOLLOW_constant_in_value510 = new BitSet(new long[]{0x90FC79FE00000200L,0x0000000000000411L});
    public static final BitSet FOLLOW_unary_in_value512 = new BitSet(new long[]{0x80F079FE00000200L,0x0000000000000411L});
    public static final BitSet FOLLOW_class_name_in_value519 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_dot_in_value521 = new BitSet(new long[]{0x00000001F8000040L,0x0000000000000080L});
    public static final BitSet FOLLOW_class__in_value523 = new BitSet(new long[]{0x80F079FE00000200L,0x0000000000000411L});
    public static final BitSet FOLLOW_unary_in_value528 = new BitSet(new long[]{0x0000000000018000L,0x0000000000000400L});
    public static final BitSet FOLLOW_variable_name_in_value531 = new BitSet(new long[]{0x90FC79FE00000200L,0x0000000000000411L});
    public static final BitSet FOLLOW_unary_in_value533 = new BitSet(new long[]{0x80F079FE00000200L,0x0000000000000411L});
    public static final BitSet FOLLOW_unary_in_value539 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_method_call_in_value542 = new BitSet(new long[]{0x90FC79FE00000200L,0x0000000000000411L});
    public static final BitSet FOLLOW_unary_in_value544 = new BitSet(new long[]{0x80F079FE00000200L,0x0000000000000411L});
    public static final BitSet FOLLOW_new_class_in_value551 = new BitSet(new long[]{0x80F079FE00000200L,0x0000000000000411L});
    public static final BitSet FOLLOW_binary_operator_in_value559 = new BitSet(new long[]{0x100C00000001C400L,0x00000000000C2E08L});
    public static final BitSet FOLLOW_value_in_value561 = new BitSet(new long[]{0x80F079FE00000200L,0x0000000000000411L});
    public static final BitSet FOLLOW_close_bracket_in_value566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_int_const_in_constant575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_const_in_constant580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_float_const_in_constant585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_char_const_in_constant590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_null_const_in_constant595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_boolean_const_in_constant600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_new__in_new_class612 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_class_name_in_new_class614 = new BitSet(new long[]{0x8000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_generic_in_new_class616 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_open_bracket_in_new_class619 = new BitSet(new long[]{0x100C01FE0001C600L,0x00000000000C2E18L});
    public static final BitSet FOLLOW_arguments_in_new_class621 = new BitSet(new long[]{0x000001FE00000200L,0x0000000000000410L});
    public static final BitSet FOLLOW_close_bracket_in_new_class623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_value_in_arguments636 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_comma_in_arguments639 = new BitSet(new long[]{0x100C00000001C400L,0x00000000000C2E08L});
    public static final BitSet FOLLOW_value_in_arguments641 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_statement_in_code655 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_begin_in_code660 = new BitSet(new long[]{0x100C05FFF91FBA40L,0x00000000000004C0L});
    public static final BitSet FOLLOW_statement_in_code662 = new BitSet(new long[]{0x100C05FFF91FBA40L,0x00000000000004C0L});
    public static final BitSet FOLLOW_block_end_in_code665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_statement677 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_semicolon_in_statement679 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_statement684 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_semicolon_in_statement686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_statement691 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_semicolon_in_statement693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_semicolon_in_statement699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_statement_in_statement704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_continue__in_statement709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_break__in_statement714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_statement719 = new BitSet(new long[]{0x0000000000018000L,0x0000000000000400L});
    public static final BitSet FOLLOW_variable_name_in_statement722 = new BitSet(new long[]{0x100C000000000002L});
    public static final BitSet FOLLOW_unary_in_statement724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for_loop_in_statement730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while_loop_in_statement735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_do_loop_in_statement740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_else_in_statement745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_try_catch_in_statement750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_statement_wosemicolon761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_statement_wosemicolon766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_statement_wosemicolon771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_statement_in_statement_wosemicolon776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_statement_wosemicolon781 = new BitSet(new long[]{0x0000000000018000L,0x0000000000000400L});
    public static final BitSet FOLLOW_variable_name_in_statement_wosemicolon784 = new BitSet(new long[]{0x100C000000000002L});
    public static final BitSet FOLLOW_unary_in_statement_wosemicolon786 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for_loop_in_statement_wosemicolon792 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while_loop_in_statement_wosemicolon797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_do_loop_in_statement_wosemicolon802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_else_in_statement_wosemicolon807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_try_catch_in_statement_wosemicolon812 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return__in_return_statement824 = new BitSet(new long[]{0x100C00000001C400L,0x00000000000C2E08L});
    public static final BitSet FOLLOW_value_in_return_statement826 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_semicolon_in_return_statement828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_this__in_variable_assignment841 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_dot_in_variable_assignment843 = new BitSet(new long[]{0x0000000000018000L,0x0000000000000400L});
    public static final BitSet FOLLOW_super__in_variable_assignment847 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_dot_in_variable_assignment849 = new BitSet(new long[]{0x0000000000018000L,0x0000000000000400L});
    public static final BitSet FOLLOW_variable_name_in_variable_assignment853 = new BitSet(new long[]{0x0003800000000000L});
    public static final BitSet FOLLOW_assign_in_variable_assignment855 = new BitSet(new long[]{0x100C00000001C400L,0x00000000000C2E08L});
    public static final BitSet FOLLOW_value_in_variable_assignment857 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for__in_for_loop867 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_for_loop869 = new BitSet(new long[]{0x000001FE00000200L,0x00000000000004C0L});
    public static final BitSet FOLLOW_variable_def_in_for_loop872 = new BitSet(new long[]{0x4000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_comma_in_for_loop875 = new BitSet(new long[]{0x000001FE00000200L,0x0000000000000480L});
    public static final BitSet FOLLOW_variable_def_in_for_loop877 = new BitSet(new long[]{0x4000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_semicolon_in_for_loop883 = new BitSet(new long[]{0x100C00000001C400L,0x00000000000C2E48L});
    public static final BitSet FOLLOW_value_in_for_loop885 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_semicolon_in_for_loop888 = new BitSet(new long[]{0x100C01FE011FBA00L,0x00000000000004D0L});
    public static final BitSet FOLLOW_statement_wosemicolon_in_for_loop891 = new BitSet(new long[]{0x4000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_comma_in_for_loop894 = new BitSet(new long[]{0x100C01FE011FBA00L,0x00000000000004C0L});
    public static final BitSet FOLLOW_statement_wosemicolon_in_for_loop896 = new BitSet(new long[]{0x4000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_for_loop902 = new BitSet(new long[]{0x900C03FE011FBB80L,0x00000000000004C0L});
    public static final BitSet FOLLOW_code_in_for_loop904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for__in_for_loop909 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_for_loop911 = new BitSet(new long[]{0x000001FE00000200L,0x0000000000000400L});
    public static final BitSet FOLLOW_variable_type_in_for_loop913 = new BitSet(new long[]{0x0000000000018000L,0x0000000000000400L});
    public static final BitSet FOLLOW_variable_name_in_for_loop915 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_colon_in_for_loop917 = new BitSet(new long[]{0x100C00000001C400L,0x00000000000C2E08L});
    public static final BitSet FOLLOW_value_in_for_loop919 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_for_loop921 = new BitSet(new long[]{0x900C03FE011FBB80L,0x00000000000004C0L});
    public static final BitSet FOLLOW_code_in_for_loop923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while__in_while_loop935 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_while_loop937 = new BitSet(new long[]{0x100C00000001C400L,0x00000000000C2E08L});
    public static final BitSet FOLLOW_value_in_while_loop939 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_while_loop941 = new BitSet(new long[]{0x900C03FE011FBB80L,0x00000000000004C0L});
    public static final BitSet FOLLOW_code_in_while_loop943 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_do__in_do_loop954 = new BitSet(new long[]{0x900C03FE011FBB80L,0x00000000000004C0L});
    public static final BitSet FOLLOW_code_in_do_loop956 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_while__in_do_loop958 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_do_loop960 = new BitSet(new long[]{0x100C00000001C400L,0x00000000000C2E08L});
    public static final BitSet FOLLOW_value_in_do_loop962 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_do_loop964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if__in_if_else975 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_if_else977 = new BitSet(new long[]{0x100C00000001C400L,0x00000000000C2E08L});
    public static final BitSet FOLLOW_value_in_if_else979 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_if_else981 = new BitSet(new long[]{0x900C03FE011FBB80L,0x00000000000004C0L});
    public static final BitSet FOLLOW_code_in_if_else983 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_else__in_if_else986 = new BitSet(new long[]{0x900C03FE011FBB80L,0x00000000000004C0L});
    public static final BitSet FOLLOW_code_in_if_else988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_try__in_try_catch1001 = new BitSet(new long[]{0x900C03FE011FBB80L,0x00000000000004C0L});
    public static final BitSet FOLLOW_code_in_try_catch1003 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_catch__in_try_catch1006 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_try_catch1008 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_id_in_try_catch1010 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_id_in_try_catch1012 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_try_catch1014 = new BitSet(new long[]{0x900C03FE011FBB80L,0x00000000000004C0L});
    public static final BitSet FOLLOW_code_in_try_catch1016 = new BitSet(new long[]{0x0000000006000002L});
    public static final BitSet FOLLOW_finally__in_try_catch1021 = new BitSet(new long[]{0x900C03FE011FBB80L,0x00000000000004C0L});
    public static final BitSet FOLLOW_code_in_try_catch1023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primitive_in_variable_type1037 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
    public static final BitSet FOLLOW_array_in_variable_type1039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_name_in_variable_type1045 = new BitSet(new long[]{0x8000000000000002L,0x0000000000000002L});
    public static final BitSet FOLLOW_generic_in_variable_type1047 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
    public static final BitSet FOLLOW_array_in_variable_type1050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_void__in_variable_type1056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_id1066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_binary_operator1075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_binary_operator1082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_binary_operator1089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_binary_operator1096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUAL_in_binary_operator1103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNEQUAL_in_binary_operator1110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_binary_operator1117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_binary_operator1124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LE_in_binary_operator1131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GE_in_binary_operator1138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INC_in_unary1150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEC_in_unary1157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_unary1164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_primitive1177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BYTE_in_primitive1184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_in_primitive1191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SHORT_in_primitive1198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_primitive1205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LONG_in_primitive1212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_primitive1219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLE_in_primitive1226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_CONST_in_int_const1244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_CONST_in_string_const1254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_CONST_in_float_const1264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_CONST_in_char_const1274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_null_const1284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOL_CONST_in_boolean_const1295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_package_name1311 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_DOT_in_package_name1313 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_package_name1317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_import_name1331 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_DOT_in_import_name1333 = new BitSet(new long[]{0x0080000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_set_in_import_name1337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_class_name1355 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_DOT_in_class_name1357 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_class_name1361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_method_name1375 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_DOT_in_method_name1377 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_method_name1381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_name_in_variable_name1394 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
    public static final BitSet FOLLOW_array_in_variable_name1396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_in_annotation_name1407 = new BitSet(new long[]{0x0000000000018000L,0x0000000000000400L});
    public static final BitSet FOLLOW_name_in_annotation_name1409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_name1419 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_DOT_in_name1421 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_name1425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_open_rect_bracket_in_array1435 = new BitSet(new long[]{0x100C00000001C400L,0x00000000000C2E0CL});
    public static final BitSet FOLLOW_value_in_array1437 = new BitSet(new long[]{0x100C00000001C400L,0x00000000000C2E0CL});
    public static final BitSet FOLLOW_close_rect_bracket_in_array1440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PACKAGE_in_package_1453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_import_1464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASS_in_class_1474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXTENDS_in_extends_1482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPLEMENTS_in_implements_1492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_THIS_in_this_1501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUPER_in_super_1510 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOID_in_void_1519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PUBLIC_in_public_1528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRIVATE_in_private_1536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROTECTED_in_protected_1546 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STATIC_in_static_1555 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FINAL_in_final_1564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSIENT_in_transient_1574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEW_in_new_1583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRY_in_try_1592 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CATCH_in_catch_1601 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FINALLY_in_finally_1609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FOR_in_for_1619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHILE_in_while_1628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DO_in_do_1637 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_if_1646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELSE_in_else_1655 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETURN_in_return_1665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BREAK_in_break_1674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTINUE_in_continue_1684 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_semicolon1699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_comma1708 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_colon1717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_dot1726 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_assign1735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCASSIGN_in_assign1742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECASSIGN_in_assign1749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_CURLY_BRACKET_in_block_begin1761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLOSE_CURLY_BRACKET_in_block_end1771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_open_bracket1781 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_close_bracket1791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_RECT_BRACKET_in_open_rect_bracket1802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLOSE_RECT_BRACKET_in_close_rect_bracket1812 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_def_in_synpred26_JavaParser324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constructor_def_in_synpred27_JavaParser328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_def_in_synpred28_JavaParser332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_field_def_in_synpred29_JavaParser336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_binary_operator_in_synpred44_JavaParser488 = new BitSet(new long[]{0x100C00000001C400L,0x00000000000C2E08L});
    public static final BitSet FOLLOW_value_in_synpred44_JavaParser490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_synpred68_JavaParser677 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_semicolon_in_synpred68_JavaParser679 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_synpred69_JavaParser684 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_semicolon_in_synpred69_JavaParser686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_synpred70_JavaParser691 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_semicolon_in_synpred70_JavaParser693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_synpred76_JavaParser724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_synpred77_JavaParser719 = new BitSet(new long[]{0x0000000000018000L,0x0000000000000400L});
    public static final BitSet FOLLOW_variable_name_in_synpred77_JavaParser722 = new BitSet(new long[]{0x100C000000000002L});
    public static final BitSet FOLLOW_unary_in_synpred77_JavaParser724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_synpred82_JavaParser761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_synpred83_JavaParser766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_synpred84_JavaParser771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_synpred88_JavaParser781 = new BitSet(new long[]{0x0000000000018000L,0x0000000000000400L});
    public static final BitSet FOLLOW_variable_name_in_synpred88_JavaParser784 = new BitSet(new long[]{0x100C000000000002L});
    public static final BitSet FOLLOW_unary_in_synpred88_JavaParser786 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for__in_synpred100_JavaParser867 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_synpred100_JavaParser869 = new BitSet(new long[]{0x000001FE00000200L,0x00000000000004C0L});
    public static final BitSet FOLLOW_variable_def_in_synpred100_JavaParser872 = new BitSet(new long[]{0x4000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_comma_in_synpred100_JavaParser875 = new BitSet(new long[]{0x000001FE00000200L,0x0000000000000480L});
    public static final BitSet FOLLOW_variable_def_in_synpred100_JavaParser877 = new BitSet(new long[]{0x4000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_semicolon_in_synpred100_JavaParser883 = new BitSet(new long[]{0x100C00000001C400L,0x00000000000C2E48L});
    public static final BitSet FOLLOW_value_in_synpred100_JavaParser885 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_semicolon_in_synpred100_JavaParser888 = new BitSet(new long[]{0x100C01FE011FBA00L,0x00000000000004D0L});
    public static final BitSet FOLLOW_statement_wosemicolon_in_synpred100_JavaParser891 = new BitSet(new long[]{0x4000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_comma_in_synpred100_JavaParser894 = new BitSet(new long[]{0x100C01FE011FBA00L,0x00000000000004C0L});
    public static final BitSet FOLLOW_statement_wosemicolon_in_synpred100_JavaParser896 = new BitSet(new long[]{0x4000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_synpred100_JavaParser902 = new BitSet(new long[]{0x900C03FE011FBB80L,0x00000000000004C0L});
    public static final BitSet FOLLOW_code_in_synpred100_JavaParser904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_else__in_synpred101_JavaParser986 = new BitSet(new long[]{0x900C03FE011FBB80L,0x00000000000004C0L});
    public static final BitSet FOLLOW_code_in_synpred101_JavaParser988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_catch__in_synpred102_JavaParser1006 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_synpred102_JavaParser1008 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_id_in_synpred102_JavaParser1010 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_id_in_synpred102_JavaParser1012 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_synpred102_JavaParser1014 = new BitSet(new long[]{0x900C03FE011FBB80L,0x00000000000004C0L});
    public static final BitSet FOLLOW_code_in_synpred102_JavaParser1016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_finally__in_synpred103_JavaParser1021 = new BitSet(new long[]{0x900C03FE011FBB80L,0x00000000000004C0L});
    public static final BitSet FOLLOW_code_in_synpred103_JavaParser1023 = new BitSet(new long[]{0x0000000000000002L});

}