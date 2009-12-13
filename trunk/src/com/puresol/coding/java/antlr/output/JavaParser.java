// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g 2009-12-13 20:58:06

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "PACKAGE", "IMPORT", "CLASS", "INTERFACE", "ENUM", "EXTENDS", "IMPLEMENTS", "VOID", "NEW", "RETURN", "BREAK", "CONTINUE", "NULL", "THIS", "SUPER", "FOR", "DO", "WHILE", "IF", "ELSE", "SWITCH", "CASE", "DEFAULT", "TRY", "CATCH", "FINALLY", "THROW", "THROWS", "PUBLIC", "PRIVATE", "PROTECTED", "FINAL", "VOLATILE", "SYNCHRONIZED", "STATIC", "TRANSIENT", "ABSTRACT", "BOOLEAN", "BYTE", "CHAR", "SHORT", "INTEGER", "LONG", "FLOAT", "DOUBLE", "OPEN_CURLY_BRACKET", "CLOSE_CURLY_BRACKET", "LE", "GE", "EQUAL", "UNEQUAL", "ASSIGN", "INCASSIGN", "DECASSIGN", "BITORASSIGN", "BITANDASSIGN", "INC", "DEC", "PLUS", "MINUS", "SLASH", "STAR", "QUESTION", "PERCENT", "LOGICAL_OR", "BIT_OR", "LOGICAL_AND", "BIT_AND", "NOT", "DOT", "COMMA", "LT", "GT", "OPEN_RECT_BRACKET", "CLOSE_RECT_BRACKET", "OPEN_BRACKET", "CLOSE_BRACKET", "COLON", "SEMICOLON", "AT", "TILDE", "BOOL_CONST", "ID", "INT_CONST", "HEX_DIGIT", "HEX_CONST", "LONG_CONST", "HEX_LONG_CONST", "EXPONENT", "FLOAT_CONST", "LINEFEED", "COMMENT", "WS", "ESC_SEQ", "STRING_CONST", "CHAR_CONST", "UNICODE_ESC", "OCTAL_ESC"
    };
    public static final int PACKAGE=4;
    public static final int LT=75;
    public static final int EXPONENT=92;
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
    public static final int INC=60;
    public static final int FINAL=35;
    public static final int IMPORT=5;
    public static final int BIT_OR=69;
    public static final int HEX_CONST=89;
    public static final int STRING_CONST=98;
    public static final int THIS=17;
    public static final int RETURN=13;
    public static final int DOUBLE=48;
    public static final int VOID=11;
    public static final int SUPER=18;
    public static final int COMMENT=95;
    public static final int GE=52;
    public static final int PRIVATE=33;
    public static final int STATIC=38;
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
    public static final int BOOL_CONST=85;
    public static final int EXTENDS=9;
    public static final int PUBLIC=32;
    public static final int LE=51;

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


    private ParserHelper helper = new ParserHelper(this);

    public ParserHelper getParserHelper() {
    	return helper;
    }



    // $ANTLR start "file"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:24:1: file : package_def ( import_def )* ( class_def | enum_def | interface_def )+ ;
    public final void file() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:24:6: ( package_def ( import_def )* ( class_def | enum_def | interface_def )+ )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:24:8: package_def ( import_def )* ( class_def | enum_def | interface_def )+
            {
            pushFollow(FOLLOW_package_def_in_file40);
            package_def();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:24:20: ( import_def )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==IMPORT) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: import_def
            	    {
            	    pushFollow(FOLLOW_import_def_in_file42);
            	    import_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:24:32: ( class_def | enum_def | interface_def )+
            int cnt2=0;
            loop2:
            do {
                int alt2=4;
                alt2 = dfa2.predict(input);
                switch (alt2) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:24:33: class_def
            	    {
            	    pushFollow(FOLLOW_class_def_in_file46);
            	    class_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 2 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:24:45: enum_def
            	    {
            	    pushFollow(FOLLOW_enum_def_in_file50);
            	    enum_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 3 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:24:56: interface_def
            	    {
            	    pushFollow(FOLLOW_interface_def_in_file54);
            	    interface_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:27:1: package_def : package_ package_name semicolon ;
    public final void package_def() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:28:2: ( package_ package_name semicolon )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:28:4: package_ package_name semicolon
            {
            pushFollow(FOLLOW_package__in_package_def68);
            package_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_package_name_in_package_def70);
            package_name();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_semicolon_in_package_def72);
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


    // $ANTLR start "import_def"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:31:1: import_def : import_ import_name semicolon ;
    public final void import_def() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:32:2: ( import_ import_name semicolon )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:32:4: import_ import_name semicolon
            {
            pushFollow(FOLLOW_import__in_import_def84);
            import_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_import_name_in_import_def86);
            import_name();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_semicolon_in_import_def88);
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:35:1: class_def : ( annotation )* ( modifier )* class_ id ( generic )? ( extends_ class_name )? ( implements_ class_name ( comma class_name )* )? class_block ;
    public final JavaParser.class_def_return class_def() throws RecognitionException {
        JavaParser.class_def_return retval = new JavaParser.class_def_return();
        retval.start = input.LT(1);

        JavaParser.id_return id1 = null;


        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:36:2: ( ( annotation )* ( modifier )* class_ id ( generic )? ( extends_ class_name )? ( implements_ class_name ( comma class_name )* )? class_block )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:36:4: ( annotation )* ( modifier )* class_ id ( generic )? ( extends_ class_name )? ( implements_ class_name ( comma class_name )* )? class_block
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:36:4: ( annotation )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==AT) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_class_def100);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:36:16: ( modifier )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>=PUBLIC && LA4_0<=PROTECTED)||(LA4_0>=SYNCHRONIZED && LA4_0<=ABSTRACT)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: modifier
            	    {
            	    pushFollow(FOLLOW_modifier_in_class_def103);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            pushFollow(FOLLOW_class__in_class_def106);
            class_();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_id_in_class_def108);
            id1=id();

            state._fsp--;
            if (state.failed) return retval;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:36:36: ( generic )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==LT) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: generic
                    {
                    pushFollow(FOLLOW_generic_in_class_def110);
                    generic();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;

            }

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:36:45: ( extends_ class_name )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==EXTENDS) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:36:46: extends_ class_name
                    {
                    pushFollow(FOLLOW_extends__in_class_def114);
                    extends_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_class_def116);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;

            }

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:36:68: ( implements_ class_name ( comma class_name )* )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==IMPLEMENTS) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:36:69: implements_ class_name ( comma class_name )*
                    {
                    pushFollow(FOLLOW_implements__in_class_def121);
                    implements_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_class_def123);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:36:92: ( comma class_name )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==COMMA) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:36:93: comma class_name
                    	    {
                    	    pushFollow(FOLLOW_comma_in_class_def126);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    pushFollow(FOLLOW_class_name_in_class_def128);
                    	    class_name();

                    	    state._fsp--;
                    	    if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);


                    }
                    break;

            }

            pushFollow(FOLLOW_class_block_in_class_def134);
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
        }
        return retval;
    }
    // $ANTLR end "class_def"

    public static class enum_def_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "enum_def"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:40:1: enum_def : ( annotation )* ( modifier )* enum_ id ( generic )? ( extends_ class_name )? enum_block ;
    public final JavaParser.enum_def_return enum_def() throws RecognitionException {
        JavaParser.enum_def_return retval = new JavaParser.enum_def_return();
        retval.start = input.LT(1);

        JavaParser.id_return id2 = null;


        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:41:2: ( ( annotation )* ( modifier )* enum_ id ( generic )? ( extends_ class_name )? enum_block )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:41:4: ( annotation )* ( modifier )* enum_ id ( generic )? ( extends_ class_name )? enum_block
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:41:4: ( annotation )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==AT) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_enum_def150);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:41:16: ( modifier )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>=PUBLIC && LA10_0<=PROTECTED)||(LA10_0>=SYNCHRONIZED && LA10_0<=ABSTRACT)) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: modifier
            	    {
            	    pushFollow(FOLLOW_modifier_in_enum_def153);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            pushFollow(FOLLOW_enum__in_enum_def156);
            enum_();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_id_in_enum_def158);
            id2=id();

            state._fsp--;
            if (state.failed) return retval;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:41:35: ( generic )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==LT) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: generic
                    {
                    pushFollow(FOLLOW_generic_in_enum_def160);
                    generic();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;

            }

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:41:44: ( extends_ class_name )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==EXTENDS) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:41:45: extends_ class_name
                    {
                    pushFollow(FOLLOW_extends__in_enum_def164);
                    extends_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_enum_def166);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;

            }

            pushFollow(FOLLOW_enum_block_in_enum_def170);
            enum_block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerRange(CodeRangeType.ENUMERATION, (id2!=null?input.toString(id2.start,id2.stop):null), input.toString(retval.start,input.LT(-1)), retval.start.getTokenIndex(), input.LT(-1).getTokenIndex());
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
    // $ANTLR end "enum_def"

    public static class interface_def_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "interface_def"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:45:1: interface_def : ( annotation )* ( modifier )* interface_ id ( generic )? ( extends_ class_name )? interface_block ;
    public final JavaParser.interface_def_return interface_def() throws RecognitionException {
        JavaParser.interface_def_return retval = new JavaParser.interface_def_return();
        retval.start = input.LT(1);

        JavaParser.id_return id3 = null;


        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:46:2: ( ( annotation )* ( modifier )* interface_ id ( generic )? ( extends_ class_name )? interface_block )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:46:4: ( annotation )* ( modifier )* interface_ id ( generic )? ( extends_ class_name )? interface_block
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:46:4: ( annotation )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==AT) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_interface_def186);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:46:16: ( modifier )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0>=PUBLIC && LA14_0<=PROTECTED)||(LA14_0>=SYNCHRONIZED && LA14_0<=ABSTRACT)) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: modifier
            	    {
            	    pushFollow(FOLLOW_modifier_in_interface_def189);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            pushFollow(FOLLOW_interface__in_interface_def192);
            interface_();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_id_in_interface_def194);
            id3=id();

            state._fsp--;
            if (state.failed) return retval;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:46:40: ( generic )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==LT) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: generic
                    {
                    pushFollow(FOLLOW_generic_in_interface_def196);
                    generic();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;

            }

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:46:49: ( extends_ class_name )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==EXTENDS) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:46:50: extends_ class_name
                    {
                    pushFollow(FOLLOW_extends__in_interface_def200);
                    extends_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_interface_def202);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;

            }

            pushFollow(FOLLOW_interface_block_in_interface_def206);
            interface_block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerRange(CodeRangeType.INTERFACE, (id3!=null?input.toString(id3.start,id3.stop):null), input.toString(retval.start,input.LT(-1)), retval.start.getTokenIndex(), input.LT(-1).getTokenIndex());
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
    // $ANTLR end "interface_def"


    // $ANTLR start "static_init"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:50:1: static_init : static_ code ( semicolon )? ;
    public final void static_init() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:51:2: ( static_ code ( semicolon )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:51:4: static_ code ( semicolon )?
            {
            pushFollow(FOLLOW_static__in_static_init222);
            static_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_code_in_static_init224);
            code();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:51:17: ( semicolon )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==SEMICOLON) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: semicolon
                    {
                    pushFollow(FOLLOW_semicolon_in_static_init226);
                    semicolon();

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
    // $ANTLR end "static_init"

    public static class constructor_def_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "constructor_def"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:54:1: constructor_def : ( annotation )* ( modifier )* id open_bracket argument_def close_bracket code ;
    public final JavaParser.constructor_def_return constructor_def() throws RecognitionException {
        JavaParser.constructor_def_return retval = new JavaParser.constructor_def_return();
        retval.start = input.LT(1);

        JavaParser.id_return id4 = null;


        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:55:2: ( ( annotation )* ( modifier )* id open_bracket argument_def close_bracket code )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:55:4: ( annotation )* ( modifier )* id open_bracket argument_def close_bracket code
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:55:4: ( annotation )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==AT) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_constructor_def238);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:55:16: ( modifier )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( ((LA19_0>=PUBLIC && LA19_0<=PROTECTED)||(LA19_0>=SYNCHRONIZED && LA19_0<=ABSTRACT)) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: modifier
            	    {
            	    pushFollow(FOLLOW_modifier_in_constructor_def241);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            pushFollow(FOLLOW_id_in_constructor_def244);
            id4=id();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_open_bracket_in_constructor_def246);
            open_bracket();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_argument_def_in_constructor_def248);
            argument_def();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_close_bracket_in_constructor_def250);
            close_bracket();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_code_in_constructor_def252);
            code();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerRange(CodeRangeType.CONSTRUCTOR, (id4!=null?input.toString(id4.start,id4.stop):null), input.toString(retval.start,input.LT(-1)), retval.start.getTokenIndex(), input.LT(-1).getTokenIndex());
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:59:1: method_def : ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket ( throws_ class_name ( comma class_name )* )? code ;
    public final JavaParser.method_def_return method_def() throws RecognitionException {
        JavaParser.method_def_return retval = new JavaParser.method_def_return();
        retval.start = input.LT(1);

        JavaParser.id_return id5 = null;


        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:60:2: ( ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket ( throws_ class_name ( comma class_name )* )? code )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:60:4: ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket ( throws_ class_name ( comma class_name )* )? code
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:60:4: ( annotation )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==AT) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_method_def267);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:60:16: ( modifier )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( ((LA21_0>=PUBLIC && LA21_0<=PROTECTED)||(LA21_0>=SYNCHRONIZED && LA21_0<=ABSTRACT)) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: modifier
            	    {
            	    pushFollow(FOLLOW_modifier_in_method_def270);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

            pushFollow(FOLLOW_variable_type_in_method_def273);
            variable_type();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_id_in_method_def275);
            id5=id();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_open_bracket_in_method_def277);
            open_bracket();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_argument_def_in_method_def279);
            argument_def();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_close_bracket_in_method_def281);
            close_bracket();

            state._fsp--;
            if (state.failed) return retval;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:60:83: ( throws_ class_name ( comma class_name )* )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==THROWS) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:60:84: throws_ class_name ( comma class_name )*
                    {
                    pushFollow(FOLLOW_throws__in_method_def284);
                    throws_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_method_def286);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:60:103: ( comma class_name )*
                    loop22:
                    do {
                        int alt22=2;
                        int LA22_0 = input.LA(1);

                        if ( (LA22_0==COMMA) ) {
                            alt22=1;
                        }


                        switch (alt22) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:60:104: comma class_name
                    	    {
                    	    pushFollow(FOLLOW_comma_in_method_def289);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    pushFollow(FOLLOW_class_name_in_method_def291);
                    	    class_name();

                    	    state._fsp--;
                    	    if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop22;
                        }
                    } while (true);


                    }
                    break;

            }

            pushFollow(FOLLOW_code_in_method_def297);
            code();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerRange(CodeRangeType.METHOD, (id5!=null?input.toString(id5.start,id5.stop):null), input.toString(retval.start,input.LT(-1)), retval.start.getTokenIndex(), input.LT(-1).getTokenIndex());
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


    // $ANTLR start "field_def"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:64:1: field_def : ( annotation )* ( modifier )* variable_type variable_name ( assign value )? semicolon ;
    public final void field_def() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:65:2: ( ( annotation )* ( modifier )* variable_type variable_name ( assign value )? semicolon )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:65:4: ( annotation )* ( modifier )* variable_type variable_name ( assign value )? semicolon
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:65:4: ( annotation )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==AT) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_field_def313);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:65:16: ( modifier )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( ((LA25_0>=PUBLIC && LA25_0<=PROTECTED)||(LA25_0>=SYNCHRONIZED && LA25_0<=ABSTRACT)) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: modifier
            	    {
            	    pushFollow(FOLLOW_modifier_in_field_def316);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);

            pushFollow(FOLLOW_variable_type_in_field_def319);
            variable_type();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_variable_name_in_field_def321);
            variable_name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:65:54: ( assign value )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( ((LA26_0>=ASSIGN && LA26_0<=BITANDASSIGN)) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:65:55: assign value
                    {
                    pushFollow(FOLLOW_assign_in_field_def324);
                    assign();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_field_def326);
                    value();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_semicolon_in_field_def330);
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:68:1: argument_def : ( variable_type ( dot dot dot )? variable_name ( comma variable_type ( dot dot dot )? variable_name )* )? ;
    public final void argument_def() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:69:2: ( ( variable_type ( dot dot dot )? variable_name ( comma variable_type ( dot dot dot )? variable_name )* )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:69:4: ( variable_type ( dot dot dot )? variable_name ( comma variable_type ( dot dot dot )? variable_name )* )?
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:69:4: ( variable_type ( dot dot dot )? variable_name ( comma variable_type ( dot dot dot )? variable_name )* )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==VOID||(LA30_0>=FINAL && LA30_0<=VOLATILE)||(LA30_0>=BOOLEAN && LA30_0<=DOUBLE)||LA30_0==ID) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:69:5: variable_type ( dot dot dot )? variable_name ( comma variable_type ( dot dot dot )? variable_name )*
                    {
                    pushFollow(FOLLOW_variable_type_in_argument_def342);
                    variable_type();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:69:19: ( dot dot dot )?
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==DOT) ) {
                        alt27=1;
                    }
                    switch (alt27) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:69:20: dot dot dot
                            {
                            pushFollow(FOLLOW_dot_in_argument_def345);
                            dot();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_dot_in_argument_def347);
                            dot();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_dot_in_argument_def349);
                            dot();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_variable_name_in_argument_def353);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:69:48: ( comma variable_type ( dot dot dot )? variable_name )*
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==COMMA) ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:69:49: comma variable_type ( dot dot dot )? variable_name
                    	    {
                    	    pushFollow(FOLLOW_comma_in_argument_def356);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_variable_type_in_argument_def358);
                    	    variable_type();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:69:69: ( dot dot dot )?
                    	    int alt28=2;
                    	    int LA28_0 = input.LA(1);

                    	    if ( (LA28_0==DOT) ) {
                    	        alt28=1;
                    	    }
                    	    switch (alt28) {
                    	        case 1 :
                    	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:69:70: dot dot dot
                    	            {
                    	            pushFollow(FOLLOW_dot_in_argument_def361);
                    	            dot();

                    	            state._fsp--;
                    	            if (state.failed) return ;
                    	            pushFollow(FOLLOW_dot_in_argument_def363);
                    	            dot();

                    	            state._fsp--;
                    	            if (state.failed) return ;
                    	            pushFollow(FOLLOW_dot_in_argument_def365);
                    	            dot();

                    	            state._fsp--;
                    	            if (state.failed) return ;

                    	            }
                    	            break;

                    	    }

                    	    pushFollow(FOLLOW_variable_name_in_argument_def369);
                    	    variable_name();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop29;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:72:1: variable_def : ( annotation )* variable_type variable_name ( assign value )? ;
    public final void variable_def() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:73:2: ( ( annotation )* variable_type variable_name ( assign value )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:73:4: ( annotation )* variable_type variable_name ( assign value )?
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:73:4: ( annotation )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==AT) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_variable_def384);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);

            pushFollow(FOLLOW_variable_type_in_variable_def387);
            variable_type();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_variable_name_in_variable_def389);
            variable_name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:73:44: ( assign value )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( ((LA32_0>=ASSIGN && LA32_0<=BITANDASSIGN)) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:73:45: assign value
                    {
                    pushFollow(FOLLOW_assign_in_variable_def392);
                    assign();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_variable_def394);
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


    // $ANTLR start "modifier"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:76:1: modifier : ( public_ | private_ | protected_ | static_ | transient_ | synchronized_ | abstract_ );
    public final void modifier() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:76:9: ( public_ | private_ | protected_ | static_ | transient_ | synchronized_ | abstract_ )
            int alt33=7;
            switch ( input.LA(1) ) {
            case PUBLIC:
                {
                alt33=1;
                }
                break;
            case PRIVATE:
                {
                alt33=2;
                }
                break;
            case PROTECTED:
                {
                alt33=3;
                }
                break;
            case STATIC:
                {
                alt33=4;
                }
                break;
            case TRANSIENT:
                {
                alt33=5;
                }
                break;
            case SYNCHRONIZED:
                {
                alt33=6;
                }
                break;
            case ABSTRACT:
                {
                alt33=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }

            switch (alt33) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:76:11: public_
                    {
                    pushFollow(FOLLOW_public__in_modifier406);
                    public_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:77:4: private_
                    {
                    pushFollow(FOLLOW_private__in_modifier411);
                    private_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:78:4: protected_
                    {
                    pushFollow(FOLLOW_protected__in_modifier416);
                    protected_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:79:4: static_
                    {
                    pushFollow(FOLLOW_static__in_modifier421);
                    static_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:80:4: transient_
                    {
                    pushFollow(FOLLOW_transient__in_modifier426);
                    transient_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:81:4: synchronized_
                    {
                    pushFollow(FOLLOW_synchronized__in_modifier431);
                    synchronized_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:82:4: abstract_
                    {
                    pushFollow(FOLLOW_abstract__in_modifier436);
                    abstract_();

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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:1: class_block : block_begin ( class_def | static_init | constructor_def | method_def | field_def )* block_end ( semicolon )? ;
    public final void class_block() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:87:2: ( block_begin ( class_def | static_init | constructor_def | method_def | field_def )* block_end ( semicolon )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:87:4: block_begin ( class_def | static_init | constructor_def | method_def | field_def )* block_end ( semicolon )?
            {
            pushFollow(FOLLOW_block_begin_in_class_block448);
            block_begin();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:87:16: ( class_def | static_init | constructor_def | method_def | field_def )*
            loop34:
            do {
                int alt34=6;
                alt34 = dfa34.predict(input);
                switch (alt34) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:87:17: class_def
            	    {
            	    pushFollow(FOLLOW_class_def_in_class_block451);
            	    class_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 2 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:87:29: static_init
            	    {
            	    pushFollow(FOLLOW_static_init_in_class_block455);
            	    static_init();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 3 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:87:43: constructor_def
            	    {
            	    pushFollow(FOLLOW_constructor_def_in_class_block459);
            	    constructor_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 4 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:87:61: method_def
            	    {
            	    pushFollow(FOLLOW_method_def_in_class_block463);
            	    method_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 5 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:87:74: field_def
            	    {
            	    pushFollow(FOLLOW_field_def_in_class_block467);
            	    field_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);

            pushFollow(FOLLOW_block_end_in_class_block471);
            block_end();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:87:96: ( semicolon )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==SEMICOLON) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: semicolon
                    {
                    pushFollow(FOLLOW_semicolon_in_class_block473);
                    semicolon();

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
    // $ANTLR end "class_block"


    // $ANTLR start "enum_block"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:1: enum_block : block_begin ( class_def | constructor_def | method_def | field_def | enum_content )* block_end ( semicolon )? ;
    public final void enum_block() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:91:2: ( block_begin ( class_def | constructor_def | method_def | field_def | enum_content )* block_end ( semicolon )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:91:4: block_begin ( class_def | constructor_def | method_def | field_def | enum_content )* block_end ( semicolon )?
            {
            pushFollow(FOLLOW_block_begin_in_enum_block486);
            block_begin();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:91:16: ( class_def | constructor_def | method_def | field_def | enum_content )*
            loop36:
            do {
                int alt36=6;
                alt36 = dfa36.predict(input);
                switch (alt36) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:91:17: class_def
            	    {
            	    pushFollow(FOLLOW_class_def_in_enum_block489);
            	    class_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 2 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:91:29: constructor_def
            	    {
            	    pushFollow(FOLLOW_constructor_def_in_enum_block493);
            	    constructor_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 3 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:91:47: method_def
            	    {
            	    pushFollow(FOLLOW_method_def_in_enum_block497);
            	    method_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 4 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:91:60: field_def
            	    {
            	    pushFollow(FOLLOW_field_def_in_enum_block501);
            	    field_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 5 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:91:72: enum_content
            	    {
            	    pushFollow(FOLLOW_enum_content_in_enum_block505);
            	    enum_content();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);

            pushFollow(FOLLOW_block_end_in_enum_block509);
            block_end();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:91:97: ( semicolon )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==SEMICOLON) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: semicolon
                    {
                    pushFollow(FOLLOW_semicolon_in_enum_block511);
                    semicolon();

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
    // $ANTLR end "enum_block"


    // $ANTLR start "enum_content"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:94:1: enum_content : id ( open_bracket arguments close_bracket )? ( comma id ( open_bracket arguments close_bracket )? )* semicolon ;
    public final void enum_content() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:95:2: ( id ( open_bracket arguments close_bracket )? ( comma id ( open_bracket arguments close_bracket )? )* semicolon )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:95:4: id ( open_bracket arguments close_bracket )? ( comma id ( open_bracket arguments close_bracket )? )* semicolon
            {
            pushFollow(FOLLOW_id_in_enum_content523);
            id();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:95:7: ( open_bracket arguments close_bracket )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==OPEN_BRACKET) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:95:8: open_bracket arguments close_bracket
                    {
                    pushFollow(FOLLOW_open_bracket_in_enum_content526);
                    open_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_arguments_in_enum_content528);
                    arguments();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_close_bracket_in_enum_content530);
                    close_bracket();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:95:47: ( comma id ( open_bracket arguments close_bracket )? )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==COMMA) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:95:48: comma id ( open_bracket arguments close_bracket )?
            	    {
            	    pushFollow(FOLLOW_comma_in_enum_content535);
            	    comma();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_enum_content537);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:95:57: ( open_bracket arguments close_bracket )?
            	    int alt39=2;
            	    int LA39_0 = input.LA(1);

            	    if ( (LA39_0==OPEN_BRACKET) ) {
            	        alt39=1;
            	    }
            	    switch (alt39) {
            	        case 1 :
            	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:95:58: open_bracket arguments close_bracket
            	            {
            	            pushFollow(FOLLOW_open_bracket_in_enum_content540);
            	            open_bracket();

            	            state._fsp--;
            	            if (state.failed) return ;
            	            pushFollow(FOLLOW_arguments_in_enum_content542);
            	            arguments();

            	            state._fsp--;
            	            if (state.failed) return ;
            	            pushFollow(FOLLOW_close_bracket_in_enum_content544);
            	            close_bracket();

            	            state._fsp--;
            	            if (state.failed) return ;

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop40;
                }
            } while (true);

            pushFollow(FOLLOW_semicolon_in_enum_content550);
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
    // $ANTLR end "enum_content"


    // $ANTLR start "interface_block"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:98:1: interface_block : block_begin ( ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket semicolon )* block_end ( semicolon )? ;
    public final void interface_block() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:99:2: ( block_begin ( ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket semicolon )* block_end ( semicolon )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:99:4: block_begin ( ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket semicolon )* block_end ( semicolon )?
            {
            pushFollow(FOLLOW_block_begin_in_interface_block562);
            block_begin();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:99:16: ( ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket semicolon )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==VOID||(LA43_0>=PUBLIC && LA43_0<=DOUBLE)||LA43_0==AT||LA43_0==ID) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:99:17: ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket semicolon
            	    {
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:99:17: ( annotation )*
            	    loop41:
            	    do {
            	        int alt41=2;
            	        int LA41_0 = input.LA(1);

            	        if ( (LA41_0==AT) ) {
            	            alt41=1;
            	        }


            	        switch (alt41) {
            	    	case 1 :
            	    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    	    {
            	    	    pushFollow(FOLLOW_annotation_in_interface_block565);
            	    	    annotation();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop41;
            	        }
            	    } while (true);

            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:99:29: ( modifier )*
            	    loop42:
            	    do {
            	        int alt42=2;
            	        int LA42_0 = input.LA(1);

            	        if ( ((LA42_0>=PUBLIC && LA42_0<=PROTECTED)||(LA42_0>=SYNCHRONIZED && LA42_0<=ABSTRACT)) ) {
            	            alt42=1;
            	        }


            	        switch (alt42) {
            	    	case 1 :
            	    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: modifier
            	    	    {
            	    	    pushFollow(FOLLOW_modifier_in_interface_block568);
            	    	    modifier();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop42;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_variable_type_in_interface_block571);
            	    variable_type();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_interface_block573);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_open_bracket_in_interface_block575);
            	    open_bracket();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_argument_def_in_interface_block577);
            	    argument_def();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_close_bracket_in_interface_block579);
            	    close_bracket();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_semicolon_in_interface_block581);
            	    semicolon();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop43;
                }
            } while (true);

            pushFollow(FOLLOW_block_end_in_interface_block585);
            block_end();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:99:118: ( semicolon )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==SEMICOLON) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: semicolon
                    {
                    pushFollow(FOLLOW_semicolon_in_interface_block587);
                    semicolon();

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
    // $ANTLR end "interface_block"


    // $ANTLR start "method_call"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:102:1: method_call : method_name open_bracket arguments close_bracket ( dot ( id | class_ ) ( open_bracket arguments close_bracket )? )* ;
    public final void method_call() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:103:2: ( method_name open_bracket arguments close_bracket ( dot ( id | class_ ) ( open_bracket arguments close_bracket )? )* )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:103:4: method_name open_bracket arguments close_bracket ( dot ( id | class_ ) ( open_bracket arguments close_bracket )? )*
            {
            pushFollow(FOLLOW_method_name_in_method_call600);
            method_name();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_open_bracket_in_method_call602);
            open_bracket();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_arguments_in_method_call604);
            arguments();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_close_bracket_in_method_call606);
            close_bracket();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:103:53: ( dot ( id | class_ ) ( open_bracket arguments close_bracket )? )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( (LA47_0==DOT) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:103:54: dot ( id | class_ ) ( open_bracket arguments close_bracket )?
            	    {
            	    pushFollow(FOLLOW_dot_in_method_call609);
            	    dot();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:103:58: ( id | class_ )
            	    int alt45=2;
            	    int LA45_0 = input.LA(1);

            	    if ( (LA45_0==ID) ) {
            	        alt45=1;
            	    }
            	    else if ( (LA45_0==CLASS) ) {
            	        alt45=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 45, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt45) {
            	        case 1 :
            	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:103:59: id
            	            {
            	            pushFollow(FOLLOW_id_in_method_call612);
            	            id();

            	            state._fsp--;
            	            if (state.failed) return ;

            	            }
            	            break;
            	        case 2 :
            	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:103:64: class_
            	            {
            	            pushFollow(FOLLOW_class__in_method_call616);
            	            class_();

            	            state._fsp--;
            	            if (state.failed) return ;

            	            }
            	            break;

            	    }

            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:103:72: ( open_bracket arguments close_bracket )?
            	    int alt46=2;
            	    int LA46_0 = input.LA(1);

            	    if ( (LA46_0==OPEN_BRACKET) ) {
            	        alt46=1;
            	    }
            	    switch (alt46) {
            	        case 1 :
            	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:103:73: open_bracket arguments close_bracket
            	            {
            	            pushFollow(FOLLOW_open_bracket_in_method_call620);
            	            open_bracket();

            	            state._fsp--;
            	            if (state.failed) return ;
            	            pushFollow(FOLLOW_arguments_in_method_call622);
            	            arguments();

            	            state._fsp--;
            	            if (state.failed) return ;
            	            pushFollow(FOLLOW_close_bracket_in_method_call624);
            	            close_bracket();

            	            state._fsp--;
            	            if (state.failed) return ;

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop47;
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


    // $ANTLR start "annotation"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:106:1: annotation : annotation_name ( open_bracket value ( comma value )* close_bracket )? ;
    public final void annotation() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:107:2: ( annotation_name ( open_bracket value ( comma value )* close_bracket )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:107:4: annotation_name ( open_bracket value ( comma value )* close_bracket )?
            {
            pushFollow(FOLLOW_annotation_name_in_annotation639);
            annotation_name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:107:20: ( open_bracket value ( comma value )* close_bracket )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==OPEN_BRACKET) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:107:21: open_bracket value ( comma value )* close_bracket
                    {
                    pushFollow(FOLLOW_open_bracket_in_annotation642);
                    open_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_annotation644);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:107:40: ( comma value )*
                    loop48:
                    do {
                        int alt48=2;
                        int LA48_0 = input.LA(1);

                        if ( (LA48_0==COMMA) ) {
                            alt48=1;
                        }


                        switch (alt48) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:107:41: comma value
                    	    {
                    	    pushFollow(FOLLOW_comma_in_annotation647);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_value_in_annotation649);
                    	    value();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop48;
                        }
                    } while (true);

                    pushFollow(FOLLOW_close_bracket_in_annotation653);
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
        }
        return ;
    }
    // $ANTLR end "annotation"


    // $ANTLR start "generic"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:110:1: generic : LT ( ( variable_type | question_ ) ( comma ( variable_type | question_ ) )* )? GT ;
    public final void generic() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:110:9: ( LT ( ( variable_type | question_ ) ( comma ( variable_type | question_ ) )* )? GT )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:110:11: LT ( ( variable_type | question_ ) ( comma ( variable_type | question_ ) )* )? GT
            {
            match(input,LT,FOLLOW_LT_in_generic665); if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:110:14: ( ( variable_type | question_ ) ( comma ( variable_type | question_ ) )* )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==VOID||(LA53_0>=FINAL && LA53_0<=VOLATILE)||(LA53_0>=BOOLEAN && LA53_0<=DOUBLE)||LA53_0==QUESTION||LA53_0==ID) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:110:15: ( variable_type | question_ ) ( comma ( variable_type | question_ ) )*
                    {
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:110:15: ( variable_type | question_ )
                    int alt50=2;
                    int LA50_0 = input.LA(1);

                    if ( (LA50_0==VOID||(LA50_0>=FINAL && LA50_0<=VOLATILE)||(LA50_0>=BOOLEAN && LA50_0<=DOUBLE)||LA50_0==ID) ) {
                        alt50=1;
                    }
                    else if ( (LA50_0==QUESTION) ) {
                        alt50=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 50, 0, input);

                        throw nvae;
                    }
                    switch (alt50) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:110:16: variable_type
                            {
                            pushFollow(FOLLOW_variable_type_in_generic669);
                            variable_type();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;
                        case 2 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:110:32: question_
                            {
                            pushFollow(FOLLOW_question__in_generic673);
                            question_();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:110:43: ( comma ( variable_type | question_ ) )*
                    loop52:
                    do {
                        int alt52=2;
                        int LA52_0 = input.LA(1);

                        if ( (LA52_0==COMMA) ) {
                            alt52=1;
                        }


                        switch (alt52) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:110:44: comma ( variable_type | question_ )
                    	    {
                    	    pushFollow(FOLLOW_comma_in_generic677);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:110:50: ( variable_type | question_ )
                    	    int alt51=2;
                    	    int LA51_0 = input.LA(1);

                    	    if ( (LA51_0==VOID||(LA51_0>=FINAL && LA51_0<=VOLATILE)||(LA51_0>=BOOLEAN && LA51_0<=DOUBLE)||LA51_0==ID) ) {
                    	        alt51=1;
                    	    }
                    	    else if ( (LA51_0==QUESTION) ) {
                    	        alt51=2;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return ;}
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 51, 0, input);

                    	        throw nvae;
                    	    }
                    	    switch (alt51) {
                    	        case 1 :
                    	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:110:51: variable_type
                    	            {
                    	            pushFollow(FOLLOW_variable_type_in_generic680);
                    	            variable_type();

                    	            state._fsp--;
                    	            if (state.failed) return ;

                    	            }
                    	            break;
                    	        case 2 :
                    	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:110:67: question_
                    	            {
                    	            pushFollow(FOLLOW_question__in_generic684);
                    	            question_();

                    	            state._fsp--;
                    	            if (state.failed) return ;

                    	            }
                    	            break;

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop52;
                        }
                    } while (true);


                    }
                    break;

            }

            match(input,GT,FOLLOW_GT_in_generic691); if (state.failed) return ;
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
        }
        return ;
    }
    // $ANTLR end "generic"


    // $ANTLR start "value"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:1: value : ( ( cast )? ( left_unary )? single_value ( right_unary )? ( binary_operator value )? ( question_ value colon value )? | open_bracket value close_bracket ( binary_operator value )? ( question_ value colon value )? );
    public final void value() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:7: ( ( cast )? ( left_unary )? single_value ( right_unary )? ( binary_operator value )? ( question_ value colon value )? | open_bracket value close_bracket ( binary_operator value )? ( question_ value colon value )? )
            int alt61=2;
            alt61 = dfa61.predict(input);
            switch (alt61) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:9: ( cast )? ( left_unary )? single_value ( right_unary )? ( binary_operator value )? ( question_ value colon value )?
                    {
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:9: ( cast )?
                    int alt54=2;
                    int LA54_0 = input.LA(1);

                    if ( (LA54_0==OPEN_BRACKET) ) {
                        alt54=1;
                    }
                    switch (alt54) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: cast
                            {
                            pushFollow(FOLLOW_cast_in_value704);
                            cast();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:15: ( left_unary )?
                    int alt55=2;
                    int LA55_0 = input.LA(1);

                    if ( ((LA55_0>=INC && LA55_0<=MINUS)||LA55_0==NOT) ) {
                        alt55=1;
                    }
                    switch (alt55) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: left_unary
                            {
                            pushFollow(FOLLOW_left_unary_in_value707);
                            left_unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_single_value_in_value710);
                    single_value();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:40: ( right_unary )?
                    int alt56=2;
                    switch ( input.LA(1) ) {
                        case INC:
                            {
                            int LA56_1 = input.LA(2);

                            if ( (synpred71_JavaParser()) ) {
                                alt56=1;
                            }
                            }
                            break;
                        case DEC:
                            {
                            int LA56_2 = input.LA(2);

                            if ( (synpred71_JavaParser()) ) {
                                alt56=1;
                            }
                            }
                            break;
                        case NOT:
                            {
                            int LA56_3 = input.LA(2);

                            if ( (synpred71_JavaParser()) ) {
                                alt56=1;
                            }
                            }
                            break;
                    }

                    switch (alt56) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: right_unary
                            {
                            pushFollow(FOLLOW_right_unary_in_value712);
                            right_unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:53: ( binary_operator value )?
                    int alt57=2;
                    alt57 = dfa57.predict(input);
                    switch (alt57) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:54: binary_operator value
                            {
                            pushFollow(FOLLOW_binary_operator_in_value716);
                            binary_operator();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_value_in_value718);
                            value();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:78: ( question_ value colon value )?
                    int alt58=2;
                    int LA58_0 = input.LA(1);

                    if ( (LA58_0==QUESTION) ) {
                        int LA58_1 = input.LA(2);

                        if ( (synpred73_JavaParser()) ) {
                            alt58=1;
                        }
                    }
                    switch (alt58) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:79: question_ value colon value
                            {
                            pushFollow(FOLLOW_question__in_value723);
                            question_();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_value_in_value725);
                            value();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_colon_in_value727);
                            colon();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_value_in_value729);
                            value();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:4: open_bracket value close_bracket ( binary_operator value )? ( question_ value colon value )?
                    {
                    pushFollow(FOLLOW_open_bracket_in_value736);
                    open_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_value738);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_close_bracket_in_value740);
                    close_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:37: ( binary_operator value )?
                    int alt59=2;
                    alt59 = dfa59.predict(input);
                    switch (alt59) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:38: binary_operator value
                            {
                            pushFollow(FOLLOW_binary_operator_in_value743);
                            binary_operator();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_value_in_value745);
                            value();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:62: ( question_ value colon value )?
                    int alt60=2;
                    int LA60_0 = input.LA(1);

                    if ( (LA60_0==QUESTION) ) {
                        int LA60_1 = input.LA(2);

                        if ( (synpred76_JavaParser()) ) {
                            alt60=1;
                        }
                    }
                    switch (alt60) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:63: question_ value colon value
                            {
                            pushFollow(FOLLOW_question__in_value750);
                            question_();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_value_in_value752);
                            value();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_colon_in_value754);
                            colon();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_value_in_value756);
                            value();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


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


    // $ANTLR start "single_value"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:118:1: single_value : ( constant | method_call | variable_assignment | variable_name | new_class | this_ | super_ );
    public final void single_value() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:119:2: ( constant | method_call | variable_assignment | variable_name | new_class | this_ | super_ )
            int alt62=7;
            alt62 = dfa62.predict(input);
            switch (alt62) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:119:4: constant
                    {
                    pushFollow(FOLLOW_constant_in_single_value769);
                    constant();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:120:4: method_call
                    {
                    pushFollow(FOLLOW_method_call_in_single_value774);
                    method_call();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:121:5: variable_assignment
                    {
                    pushFollow(FOLLOW_variable_assignment_in_single_value780);
                    variable_assignment();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:122:4: variable_name
                    {
                    pushFollow(FOLLOW_variable_name_in_single_value785);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:123:4: new_class
                    {
                    pushFollow(FOLLOW_new_class_in_single_value790);
                    new_class();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:124:4: this_
                    {
                    pushFollow(FOLLOW_this__in_single_value795);
                    this_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:125:4: super_
                    {
                    pushFollow(FOLLOW_super__in_single_value800);
                    super_();

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
    // $ANTLR end "single_value"


    // $ANTLR start "constant"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:128:1: constant : ( class_const | hex_long_const | hex_const | long_const | int_const | string_const | float_const | char_const | null_const | boolean_const | array_const );
    public final void constant() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:128:9: ( class_const | hex_long_const | hex_const | long_const | int_const | string_const | float_const | char_const | null_const | boolean_const | array_const )
            int alt63=11;
            switch ( input.LA(1) ) {
            case VOID:
            case BOOLEAN:
            case BYTE:
            case CHAR:
            case SHORT:
            case INTEGER:
            case LONG:
            case FLOAT:
            case DOUBLE:
            case ID:
                {
                alt63=1;
                }
                break;
            case HEX_LONG_CONST:
                {
                alt63=2;
                }
                break;
            case HEX_CONST:
                {
                alt63=3;
                }
                break;
            case LONG_CONST:
                {
                alt63=4;
                }
                break;
            case INT_CONST:
                {
                alt63=5;
                }
                break;
            case STRING_CONST:
                {
                alt63=6;
                }
                break;
            case FLOAT_CONST:
                {
                alt63=7;
                }
                break;
            case CHAR_CONST:
                {
                alt63=8;
                }
                break;
            case NULL:
                {
                alt63=9;
                }
                break;
            case BOOL_CONST:
                {
                alt63=10;
                }
                break;
            case OPEN_CURLY_BRACKET:
                {
                alt63=11;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }

            switch (alt63) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:128:11: class_const
                    {
                    pushFollow(FOLLOW_class_const_in_constant809);
                    class_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:129:4: hex_long_const
                    {
                    pushFollow(FOLLOW_hex_long_const_in_constant814);
                    hex_long_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:130:4: hex_const
                    {
                    pushFollow(FOLLOW_hex_const_in_constant819);
                    hex_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:131:4: long_const
                    {
                    pushFollow(FOLLOW_long_const_in_constant824);
                    long_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:132:4: int_const
                    {
                    pushFollow(FOLLOW_int_const_in_constant829);
                    int_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:133:4: string_const
                    {
                    pushFollow(FOLLOW_string_const_in_constant834);
                    string_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:134:4: float_const
                    {
                    pushFollow(FOLLOW_float_const_in_constant839);
                    float_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 8 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:135:4: char_const
                    {
                    pushFollow(FOLLOW_char_const_in_constant844);
                    char_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 9 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:136:4: null_const
                    {
                    pushFollow(FOLLOW_null_const_in_constant849);
                    null_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 10 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:137:4: boolean_const
                    {
                    pushFollow(FOLLOW_boolean_const_in_constant854);
                    boolean_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 11 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:138:4: array_const
                    {
                    pushFollow(FOLLOW_array_const_in_constant859);
                    array_const();

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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:141:1: new_class : new_ ( class_name | primitive ) ( array ( array_const )? )? ( open_bracket arguments close_bracket )? ;
    public final void new_class() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:2: ( new_ ( class_name | primitive ) ( array ( array_const )? )? ( open_bracket arguments close_bracket )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:4: new_ ( class_name | primitive ) ( array ( array_const )? )? ( open_bracket arguments close_bracket )?
            {
            pushFollow(FOLLOW_new__in_new_class871);
            new_();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:9: ( class_name | primitive )
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==ID) ) {
                alt64=1;
            }
            else if ( ((LA64_0>=BOOLEAN && LA64_0<=DOUBLE)) ) {
                alt64=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 64, 0, input);

                throw nvae;
            }
            switch (alt64) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:10: class_name
                    {
                    pushFollow(FOLLOW_class_name_in_new_class874);
                    class_name();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:23: primitive
                    {
                    pushFollow(FOLLOW_primitive_in_new_class878);
                    primitive();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:34: ( array ( array_const )? )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==OPEN_RECT_BRACKET) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:35: array ( array_const )?
                    {
                    pushFollow(FOLLOW_array_in_new_class882);
                    array();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:41: ( array_const )?
                    int alt65=2;
                    int LA65_0 = input.LA(1);

                    if ( (LA65_0==OPEN_CURLY_BRACKET) ) {
                        alt65=1;
                    }
                    switch (alt65) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: array_const
                            {
                            pushFollow(FOLLOW_array_const_in_new_class884);
                            array_const();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;

            }

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:56: ( open_bracket arguments close_bracket )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==OPEN_BRACKET) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:57: open_bracket arguments close_bracket
                    {
                    pushFollow(FOLLOW_open_bracket_in_new_class890);
                    open_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_arguments_in_new_class892);
                    arguments();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_close_bracket_in_new_class894);
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
        }
        return ;
    }
    // $ANTLR end "new_class"


    // $ANTLR start "arguments"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:145:1: arguments : ( value ( comma value )* )? ;
    public final void arguments() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:146:2: ( ( value ( comma value )* )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:146:4: ( value ( comma value )* )?
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:146:4: ( value ( comma value )* )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( ((LA69_0>=VOID && LA69_0<=NEW)||(LA69_0>=NULL && LA69_0<=SUPER)||(LA69_0>=BOOLEAN && LA69_0<=OPEN_CURLY_BRACKET)||(LA69_0>=INC && LA69_0<=MINUS)||LA69_0==NOT||LA69_0==OPEN_BRACKET||(LA69_0>=BOOL_CONST && LA69_0<=INT_CONST)||(LA69_0>=HEX_CONST && LA69_0<=HEX_LONG_CONST)||LA69_0==FLOAT_CONST||(LA69_0>=STRING_CONST && LA69_0<=CHAR_CONST)) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:146:5: value ( comma value )*
                    {
                    pushFollow(FOLLOW_value_in_arguments910);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:146:11: ( comma value )*
                    loop68:
                    do {
                        int alt68=2;
                        int LA68_0 = input.LA(1);

                        if ( (LA68_0==COMMA) ) {
                            alt68=1;
                        }


                        switch (alt68) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:146:12: comma value
                    	    {
                    	    pushFollow(FOLLOW_comma_in_arguments913);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_value_in_arguments915);
                    	    value();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop68;
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


    // $ANTLR start "code"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:149:1: code : ( statement | block_begin ( code )* block_end ( semicolon )? );
    public final void code() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:149:6: ( statement | block_begin ( code )* block_end ( semicolon )? )
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( ((LA72_0>=VOID && LA72_0<=CONTINUE)||(LA72_0>=THIS && LA72_0<=IF)||LA72_0==SWITCH||LA72_0==TRY||LA72_0==THROW||(LA72_0>=FINAL && LA72_0<=SYNCHRONIZED)||(LA72_0>=BOOLEAN && LA72_0<=DOUBLE)||(LA72_0>=INC && LA72_0<=MINUS)||LA72_0==NOT||(LA72_0>=SEMICOLON && LA72_0<=AT)||LA72_0==ID) ) {
                alt72=1;
            }
            else if ( (LA72_0==OPEN_CURLY_BRACKET) ) {
                alt72=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 72, 0, input);

                throw nvae;
            }
            switch (alt72) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:149:8: statement
                    {
                    pushFollow(FOLLOW_statement_in_code929);
                    statement();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:150:4: block_begin ( code )* block_end ( semicolon )?
                    {
                    pushFollow(FOLLOW_block_begin_in_code934);
                    block_begin();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:150:16: ( code )*
                    loop70:
                    do {
                        int alt70=2;
                        int LA70_0 = input.LA(1);

                        if ( ((LA70_0>=VOID && LA70_0<=CONTINUE)||(LA70_0>=THIS && LA70_0<=IF)||LA70_0==SWITCH||LA70_0==TRY||LA70_0==THROW||(LA70_0>=FINAL && LA70_0<=SYNCHRONIZED)||(LA70_0>=BOOLEAN && LA70_0<=OPEN_CURLY_BRACKET)||(LA70_0>=INC && LA70_0<=MINUS)||LA70_0==NOT||(LA70_0>=SEMICOLON && LA70_0<=AT)||LA70_0==ID) ) {
                            alt70=1;
                        }


                        switch (alt70) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: code
                    	    {
                    	    pushFollow(FOLLOW_code_in_code936);
                    	    code();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop70;
                        }
                    } while (true);

                    pushFollow(FOLLOW_block_end_in_code939);
                    block_end();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:150:32: ( semicolon )?
                    int alt71=2;
                    int LA71_0 = input.LA(1);

                    if ( (LA71_0==SEMICOLON) ) {
                        int LA71_1 = input.LA(2);

                        if ( (synpred101_JavaParser()) ) {
                            alt71=1;
                        }
                    }
                    switch (alt71) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: semicolon
                            {
                            pushFollow(FOLLOW_semicolon_in_code941);
                            semicolon();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


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
    // $ANTLR end "code"


    // $ANTLR start "statement"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:1: statement : ( label | variable_assignment semicolon | variable_def semicolon | method_call semicolon | semicolon | return_statement | continue_ | break_ | for_loop | while_loop | do_loop semicolon | switch_case | if_else | try_catch | synchronized_block | throw_ value semicolon | ( left_unary )? variable_name ( right_unary )? );
    public final void statement() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:154:2: ( label | variable_assignment semicolon | variable_def semicolon | method_call semicolon | semicolon | return_statement | continue_ | break_ | for_loop | while_loop | do_loop semicolon | switch_case | if_else | try_catch | synchronized_block | throw_ value semicolon | ( left_unary )? variable_name ( right_unary )? )
            int alt75=17;
            alt75 = dfa75.predict(input);
            switch (alt75) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:154:4: label
                    {
                    pushFollow(FOLLOW_label_in_statement954);
                    label();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:155:4: variable_assignment semicolon
                    {
                    pushFollow(FOLLOW_variable_assignment_in_statement960);
                    variable_assignment();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement962);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:156:4: variable_def semicolon
                    {
                    pushFollow(FOLLOW_variable_def_in_statement967);
                    variable_def();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement969);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:157:4: method_call semicolon
                    {
                    pushFollow(FOLLOW_method_call_in_statement974);
                    method_call();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement976);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:158:5: semicolon
                    {
                    pushFollow(FOLLOW_semicolon_in_statement982);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:159:4: return_statement
                    {
                    pushFollow(FOLLOW_return_statement_in_statement987);
                    return_statement();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:160:4: continue_
                    {
                    pushFollow(FOLLOW_continue__in_statement992);
                    continue_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 8 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:161:4: break_
                    {
                    pushFollow(FOLLOW_break__in_statement997);
                    break_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 9 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:162:4: for_loop
                    {
                    pushFollow(FOLLOW_for_loop_in_statement1002);
                    for_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 10 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:163:4: while_loop
                    {
                    pushFollow(FOLLOW_while_loop_in_statement1007);
                    while_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 11 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:164:4: do_loop semicolon
                    {
                    pushFollow(FOLLOW_do_loop_in_statement1012);
                    do_loop();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement1014);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 12 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:165:4: switch_case
                    {
                    pushFollow(FOLLOW_switch_case_in_statement1019);
                    switch_case();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 13 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:166:4: if_else
                    {
                    pushFollow(FOLLOW_if_else_in_statement1024);
                    if_else();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 14 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:167:4: try_catch
                    {
                    pushFollow(FOLLOW_try_catch_in_statement1029);
                    try_catch();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 15 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:168:4: synchronized_block
                    {
                    pushFollow(FOLLOW_synchronized_block_in_statement1034);
                    synchronized_block();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 16 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:169:4: throw_ value semicolon
                    {
                    pushFollow(FOLLOW_throw__in_statement1039);
                    throw_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_statement1041);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement1043);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 17 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:170:4: ( left_unary )? variable_name ( right_unary )?
                    {
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:170:4: ( left_unary )?
                    int alt73=2;
                    int LA73_0 = input.LA(1);

                    if ( ((LA73_0>=INC && LA73_0<=MINUS)||LA73_0==NOT) ) {
                        alt73=1;
                    }
                    switch (alt73) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: left_unary
                            {
                            pushFollow(FOLLOW_left_unary_in_statement1048);
                            left_unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_variable_name_in_statement1051);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:170:30: ( right_unary )?
                    int alt74=2;
                    switch ( input.LA(1) ) {
                        case INC:
                            {
                            int LA74_1 = input.LA(2);

                            if ( (synpred119_JavaParser()) ) {
                                alt74=1;
                            }
                            }
                            break;
                        case DEC:
                            {
                            int LA74_2 = input.LA(2);

                            if ( (synpred119_JavaParser()) ) {
                                alt74=1;
                            }
                            }
                            break;
                        case NOT:
                            {
                            int LA74_3 = input.LA(2);

                            if ( (synpred119_JavaParser()) ) {
                                alt74=1;
                            }
                            }
                            break;
                    }

                    switch (alt74) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: right_unary
                            {
                            pushFollow(FOLLOW_right_unary_in_statement1053);
                            right_unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


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


    // $ANTLR start "statement_wosemicolon"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:173:1: statement_wosemicolon : ( label | variable_assignment | variable_def | method_call | return_statement | continue_ | break_ | for_loop | while_loop | do_loop | switch_case | if_else | try_catch | synchronized_block | throw_ value semicolon | ( left_unary )? variable_name ( right_unary )? );
    public final void statement_wosemicolon() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:174:2: ( label | variable_assignment | variable_def | method_call | return_statement | continue_ | break_ | for_loop | while_loop | do_loop | switch_case | if_else | try_catch | synchronized_block | throw_ value semicolon | ( left_unary )? variable_name ( right_unary )? )
            int alt78=16;
            alt78 = dfa78.predict(input);
            switch (alt78) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:174:4: label
                    {
                    pushFollow(FOLLOW_label_in_statement_wosemicolon1065);
                    label();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:175:4: variable_assignment
                    {
                    pushFollow(FOLLOW_variable_assignment_in_statement_wosemicolon1070);
                    variable_assignment();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:176:4: variable_def
                    {
                    pushFollow(FOLLOW_variable_def_in_statement_wosemicolon1075);
                    variable_def();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:177:4: method_call
                    {
                    pushFollow(FOLLOW_method_call_in_statement_wosemicolon1080);
                    method_call();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:178:4: return_statement
                    {
                    pushFollow(FOLLOW_return_statement_in_statement_wosemicolon1086);
                    return_statement();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:179:4: continue_
                    {
                    pushFollow(FOLLOW_continue__in_statement_wosemicolon1091);
                    continue_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:180:4: break_
                    {
                    pushFollow(FOLLOW_break__in_statement_wosemicolon1096);
                    break_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 8 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:181:4: for_loop
                    {
                    pushFollow(FOLLOW_for_loop_in_statement_wosemicolon1101);
                    for_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 9 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:182:4: while_loop
                    {
                    pushFollow(FOLLOW_while_loop_in_statement_wosemicolon1106);
                    while_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 10 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:183:4: do_loop
                    {
                    pushFollow(FOLLOW_do_loop_in_statement_wosemicolon1111);
                    do_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 11 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:4: switch_case
                    {
                    pushFollow(FOLLOW_switch_case_in_statement_wosemicolon1116);
                    switch_case();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 12 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:185:4: if_else
                    {
                    pushFollow(FOLLOW_if_else_in_statement_wosemicolon1121);
                    if_else();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 13 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:186:4: try_catch
                    {
                    pushFollow(FOLLOW_try_catch_in_statement_wosemicolon1126);
                    try_catch();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 14 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:187:4: synchronized_block
                    {
                    pushFollow(FOLLOW_synchronized_block_in_statement_wosemicolon1131);
                    synchronized_block();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 15 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:188:4: throw_ value semicolon
                    {
                    pushFollow(FOLLOW_throw__in_statement_wosemicolon1136);
                    throw_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_statement_wosemicolon1138);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement_wosemicolon1140);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 16 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:189:4: ( left_unary )? variable_name ( right_unary )?
                    {
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:189:4: ( left_unary )?
                    int alt76=2;
                    int LA76_0 = input.LA(1);

                    if ( ((LA76_0>=INC && LA76_0<=MINUS)||LA76_0==NOT) ) {
                        alt76=1;
                    }
                    switch (alt76) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: left_unary
                            {
                            pushFollow(FOLLOW_left_unary_in_statement_wosemicolon1145);
                            left_unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_variable_name_in_statement_wosemicolon1148);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:189:30: ( right_unary )?
                    int alt77=2;
                    int LA77_0 = input.LA(1);

                    if ( ((LA77_0>=INC && LA77_0<=DEC)||LA77_0==NOT) ) {
                        alt77=1;
                    }
                    switch (alt77) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: right_unary
                            {
                            pushFollow(FOLLOW_right_unary_in_statement_wosemicolon1150);
                            right_unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


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
    // $ANTLR end "statement_wosemicolon"


    // $ANTLR start "return_statement"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:192:1: return_statement : return_ ( value )? semicolon ;
    public final void return_statement() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:193:2: ( return_ ( value )? semicolon )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:193:4: return_ ( value )? semicolon
            {
            pushFollow(FOLLOW_return__in_return_statement1163);
            return_();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:193:12: ( value )?
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( ((LA79_0>=VOID && LA79_0<=NEW)||(LA79_0>=NULL && LA79_0<=SUPER)||(LA79_0>=BOOLEAN && LA79_0<=OPEN_CURLY_BRACKET)||(LA79_0>=INC && LA79_0<=MINUS)||LA79_0==NOT||LA79_0==OPEN_BRACKET||(LA79_0>=BOOL_CONST && LA79_0<=INT_CONST)||(LA79_0>=HEX_CONST && LA79_0<=HEX_LONG_CONST)||LA79_0==FLOAT_CONST||(LA79_0>=STRING_CONST && LA79_0<=CHAR_CONST)) ) {
                alt79=1;
            }
            switch (alt79) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: value
                    {
                    pushFollow(FOLLOW_value_in_return_statement1165);
                    value();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_semicolon_in_return_statement1168);
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
    // $ANTLR end "return_statement"


    // $ANTLR start "variable_assignment"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:196:1: variable_assignment : ( this_ dot | super_ dot )? variable_name assign value ;
    public final void variable_assignment() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:197:2: ( ( this_ dot | super_ dot )? variable_name assign value )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:197:4: ( this_ dot | super_ dot )? variable_name assign value
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:197:4: ( this_ dot | super_ dot )?
            int alt80=3;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==THIS) ) {
                alt80=1;
            }
            else if ( (LA80_0==SUPER) ) {
                alt80=2;
            }
            switch (alt80) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:197:5: this_ dot
                    {
                    pushFollow(FOLLOW_this__in_variable_assignment1181);
                    this_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_dot_in_variable_assignment1183);
                    dot();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:197:17: super_ dot
                    {
                    pushFollow(FOLLOW_super__in_variable_assignment1187);
                    super_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_dot_in_variable_assignment1189);
                    dot();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_variable_name_in_variable_assignment1193);
            variable_name();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_assign_in_variable_assignment1195);
            assign();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_value_in_variable_assignment1197);
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


    // $ANTLR start "for_loop"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:200:1: for_loop : ( for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon ( value )? semicolon ( statement_wosemicolon ( comma statement_wosemicolon )* )? CLOSE_BRACKET code | for_ OPEN_BRACKET variable_type variable_name colon value CLOSE_BRACKET code );
    public final void for_loop() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:200:9: ( for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon ( value )? semicolon ( statement_wosemicolon ( comma statement_wosemicolon )* )? CLOSE_BRACKET code | for_ OPEN_BRACKET variable_type variable_name colon value CLOSE_BRACKET code )
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==FOR) ) {
                int LA86_1 = input.LA(2);

                if ( (synpred145_JavaParser()) ) {
                    alt86=1;
                }
                else if ( (true) ) {
                    alt86=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 86, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 86, 0, input);

                throw nvae;
            }
            switch (alt86) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:200:11: for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon ( value )? semicolon ( statement_wosemicolon ( comma statement_wosemicolon )* )? CLOSE_BRACKET code
                    {
                    pushFollow(FOLLOW_for__in_for_loop1207);
                    for_();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_for_loop1209); if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:200:29: ( variable_def ( comma variable_def )* )?
                    int alt82=2;
                    int LA82_0 = input.LA(1);

                    if ( (LA82_0==VOID||(LA82_0>=FINAL && LA82_0<=VOLATILE)||(LA82_0>=BOOLEAN && LA82_0<=DOUBLE)||LA82_0==AT||LA82_0==ID) ) {
                        alt82=1;
                    }
                    switch (alt82) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:200:30: variable_def ( comma variable_def )*
                            {
                            pushFollow(FOLLOW_variable_def_in_for_loop1212);
                            variable_def();

                            state._fsp--;
                            if (state.failed) return ;
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:200:43: ( comma variable_def )*
                            loop81:
                            do {
                                int alt81=2;
                                int LA81_0 = input.LA(1);

                                if ( (LA81_0==COMMA) ) {
                                    alt81=1;
                                }


                                switch (alt81) {
                            	case 1 :
                            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:200:44: comma variable_def
                            	    {
                            	    pushFollow(FOLLOW_comma_in_for_loop1215);
                            	    comma();

                            	    state._fsp--;
                            	    if (state.failed) return ;
                            	    pushFollow(FOLLOW_variable_def_in_for_loop1217);
                            	    variable_def();

                            	    state._fsp--;
                            	    if (state.failed) return ;

                            	    }
                            	    break;

                            	default :
                            	    break loop81;
                                }
                            } while (true);


                            }
                            break;

                    }

                    pushFollow(FOLLOW_semicolon_in_for_loop1223);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:200:77: ( value )?
                    int alt83=2;
                    int LA83_0 = input.LA(1);

                    if ( ((LA83_0>=VOID && LA83_0<=NEW)||(LA83_0>=NULL && LA83_0<=SUPER)||(LA83_0>=BOOLEAN && LA83_0<=OPEN_CURLY_BRACKET)||(LA83_0>=INC && LA83_0<=MINUS)||LA83_0==NOT||LA83_0==OPEN_BRACKET||(LA83_0>=BOOL_CONST && LA83_0<=INT_CONST)||(LA83_0>=HEX_CONST && LA83_0<=HEX_LONG_CONST)||LA83_0==FLOAT_CONST||(LA83_0>=STRING_CONST && LA83_0<=CHAR_CONST)) ) {
                        alt83=1;
                    }
                    switch (alt83) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: value
                            {
                            pushFollow(FOLLOW_value_in_for_loop1225);
                            value();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_semicolon_in_for_loop1228);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:200:94: ( statement_wosemicolon ( comma statement_wosemicolon )* )?
                    int alt85=2;
                    int LA85_0 = input.LA(1);

                    if ( ((LA85_0>=VOID && LA85_0<=CONTINUE)||(LA85_0>=THIS && LA85_0<=IF)||LA85_0==SWITCH||LA85_0==TRY||LA85_0==THROW||(LA85_0>=FINAL && LA85_0<=SYNCHRONIZED)||(LA85_0>=BOOLEAN && LA85_0<=DOUBLE)||(LA85_0>=INC && LA85_0<=MINUS)||LA85_0==NOT||LA85_0==AT||LA85_0==ID) ) {
                        alt85=1;
                    }
                    switch (alt85) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:200:95: statement_wosemicolon ( comma statement_wosemicolon )*
                            {
                            pushFollow(FOLLOW_statement_wosemicolon_in_for_loop1231);
                            statement_wosemicolon();

                            state._fsp--;
                            if (state.failed) return ;
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:200:117: ( comma statement_wosemicolon )*
                            loop84:
                            do {
                                int alt84=2;
                                int LA84_0 = input.LA(1);

                                if ( (LA84_0==COMMA) ) {
                                    alt84=1;
                                }


                                switch (alt84) {
                            	case 1 :
                            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:200:118: comma statement_wosemicolon
                            	    {
                            	    pushFollow(FOLLOW_comma_in_for_loop1234);
                            	    comma();

                            	    state._fsp--;
                            	    if (state.failed) return ;
                            	    pushFollow(FOLLOW_statement_wosemicolon_in_for_loop1236);
                            	    statement_wosemicolon();

                            	    state._fsp--;
                            	    if (state.failed) return ;

                            	    }
                            	    break;

                            	default :
                            	    break loop84;
                                }
                            } while (true);


                            }
                            break;

                    }

                    match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_for_loop1242); if (state.failed) return ;
                    pushFollow(FOLLOW_code_in_for_loop1244);
                    code();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:201:4: for_ OPEN_BRACKET variable_type variable_name colon value CLOSE_BRACKET code
                    {
                    pushFollow(FOLLOW_for__in_for_loop1249);
                    for_();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_for_loop1251); if (state.failed) return ;
                    pushFollow(FOLLOW_variable_type_in_for_loop1253);
                    variable_type();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_variable_name_in_for_loop1255);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_colon_in_for_loop1257);
                    colon();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_for_loop1259);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_for_loop1261); if (state.failed) return ;
                    pushFollow(FOLLOW_code_in_for_loop1263);
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
        }
        return ;
    }
    // $ANTLR end "for_loop"


    // $ANTLR start "while_loop"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:204:1: while_loop : while_ OPEN_BRACKET value CLOSE_BRACKET code ;
    public final void while_loop() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:205:2: ( while_ OPEN_BRACKET value CLOSE_BRACKET code )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:205:4: while_ OPEN_BRACKET value CLOSE_BRACKET code
            {
            pushFollow(FOLLOW_while__in_while_loop1275);
            while_();

            state._fsp--;
            if (state.failed) return ;
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_while_loop1277); if (state.failed) return ;
            pushFollow(FOLLOW_value_in_while_loop1279);
            value();

            state._fsp--;
            if (state.failed) return ;
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_while_loop1281); if (state.failed) return ;
            pushFollow(FOLLOW_code_in_while_loop1283);
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
        }
        return ;
    }
    // $ANTLR end "while_loop"


    // $ANTLR start "do_loop"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:208:1: do_loop : do_ code while_ OPEN_BRACKET value CLOSE_BRACKET ;
    public final void do_loop() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:208:9: ( do_ code while_ OPEN_BRACKET value CLOSE_BRACKET )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:208:11: do_ code while_ OPEN_BRACKET value CLOSE_BRACKET
            {
            pushFollow(FOLLOW_do__in_do_loop1294);
            do_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_code_in_do_loop1296);
            code();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_while__in_do_loop1298);
            while_();

            state._fsp--;
            if (state.failed) return ;
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_do_loop1300); if (state.failed) return ;
            pushFollow(FOLLOW_value_in_do_loop1302);
            value();

            state._fsp--;
            if (state.failed) return ;
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_do_loop1304); if (state.failed) return ;

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
    // $ANTLR end "do_loop"


    // $ANTLR start "switch_case"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:211:1: switch_case : switch_ OPEN_BRACKET value CLOSE_BRACKET block_begin ( ( case_ ( constant | variable_name ) colon )+ ( code | statement )* ( break_ semicolon )? | default_ colon ( code | statement )* )+ block_end ;
    public final void switch_case() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:212:2: ( switch_ OPEN_BRACKET value CLOSE_BRACKET block_begin ( ( case_ ( constant | variable_name ) colon )+ ( code | statement )* ( break_ semicolon )? | default_ colon ( code | statement )* )+ block_end )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:212:4: switch_ OPEN_BRACKET value CLOSE_BRACKET block_begin ( ( case_ ( constant | variable_name ) colon )+ ( code | statement )* ( break_ semicolon )? | default_ colon ( code | statement )* )+ block_end
            {
            pushFollow(FOLLOW_switch__in_switch_case1315);
            switch_();

            state._fsp--;
            if (state.failed) return ;
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_switch_case1317); if (state.failed) return ;
            pushFollow(FOLLOW_value_in_switch_case1319);
            value();

            state._fsp--;
            if (state.failed) return ;
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_switch_case1321); if (state.failed) return ;
            pushFollow(FOLLOW_block_begin_in_switch_case1323);
            block_begin();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:213:3: ( ( case_ ( constant | variable_name ) colon )+ ( code | statement )* ( break_ semicolon )? | default_ colon ( code | statement )* )+
            int cnt92=0;
            loop92:
            do {
                int alt92=3;
                int LA92_0 = input.LA(1);

                if ( (LA92_0==CASE) ) {
                    alt92=1;
                }
                else if ( (LA92_0==DEFAULT) ) {
                    alt92=2;
                }


                switch (alt92) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:4: ( case_ ( constant | variable_name ) colon )+ ( code | statement )* ( break_ semicolon )?
            	    {
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:4: ( case_ ( constant | variable_name ) colon )+
            	    int cnt88=0;
            	    loop88:
            	    do {
            	        int alt88=2;
            	        int LA88_0 = input.LA(1);

            	        if ( (LA88_0==CASE) ) {
            	            int LA88_2 = input.LA(2);

            	            if ( (synpred147_JavaParser()) ) {
            	                alt88=1;
            	            }


            	        }


            	        switch (alt88) {
            	    	case 1 :
            	    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:5: case_ ( constant | variable_name ) colon
            	    	    {
            	    	    pushFollow(FOLLOW_case__in_switch_case1334);
            	    	    case_();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;
            	    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:12: ( constant | variable_name )
            	    	    int alt87=2;
            	    	    alt87 = dfa87.predict(input);
            	    	    switch (alt87) {
            	    	        case 1 :
            	    	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:13: constant
            	    	            {
            	    	            pushFollow(FOLLOW_constant_in_switch_case1338);
            	    	            constant();

            	    	            state._fsp--;
            	    	            if (state.failed) return ;

            	    	            }
            	    	            break;
            	    	        case 2 :
            	    	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:24: variable_name
            	    	            {
            	    	            pushFollow(FOLLOW_variable_name_in_switch_case1342);
            	    	            variable_name();

            	    	            state._fsp--;
            	    	            if (state.failed) return ;

            	    	            }
            	    	            break;

            	    	    }

            	    	    pushFollow(FOLLOW_colon_in_switch_case1345);
            	    	    colon();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;

            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt88 >= 1 ) break loop88;
            	    	    if (state.backtracking>0) {state.failed=true; return ;}
            	                EarlyExitException eee =
            	                    new EarlyExitException(88, input);
            	                throw eee;
            	        }
            	        cnt88++;
            	    } while (true);

            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:47: ( code | statement )*
            	    loop89:
            	    do {
            	        int alt89=3;
            	        alt89 = dfa89.predict(input);
            	        switch (alt89) {
            	    	case 1 :
            	    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:48: code
            	    	    {
            	    	    pushFollow(FOLLOW_code_in_switch_case1350);
            	    	    code();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;

            	    	    }
            	    	    break;
            	    	case 2 :
            	    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:55: statement
            	    	    {
            	    	    pushFollow(FOLLOW_statement_in_switch_case1354);
            	    	    statement();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop89;
            	        }
            	    } while (true);

            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:67: ( break_ semicolon )?
            	    int alt90=2;
            	    int LA90_0 = input.LA(1);

            	    if ( (LA90_0==BREAK) ) {
            	        alt90=1;
            	    }
            	    switch (alt90) {
            	        case 1 :
            	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:68: break_ semicolon
            	            {
            	            pushFollow(FOLLOW_break__in_switch_case1359);
            	            break_();

            	            state._fsp--;
            	            if (state.failed) return ;
            	            pushFollow(FOLLOW_semicolon_in_switch_case1361);
            	            semicolon();

            	            state._fsp--;
            	            if (state.failed) return ;

            	            }
            	            break;

            	    }


            	    }
            	    break;
            	case 2 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:216:4: default_ colon ( code | statement )*
            	    {
            	    pushFollow(FOLLOW_default__in_switch_case1372);
            	    default_();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_colon_in_switch_case1374);
            	    colon();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:216:19: ( code | statement )*
            	    loop91:
            	    do {
            	        int alt91=3;
            	        alt91 = dfa91.predict(input);
            	        switch (alt91) {
            	    	case 1 :
            	    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:216:20: code
            	    	    {
            	    	    pushFollow(FOLLOW_code_in_switch_case1377);
            	    	    code();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;

            	    	    }
            	    	    break;
            	    	case 2 :
            	    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:216:27: statement
            	    	    {
            	    	    pushFollow(FOLLOW_statement_in_switch_case1381);
            	    	    statement();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop91;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    if ( cnt92 >= 1 ) break loop92;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(92, input);
                        throw eee;
                }
                cnt92++;
            } while (true);

            pushFollow(FOLLOW_block_end_in_switch_case1392);
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
        }
        return ;
    }
    // $ANTLR end "switch_case"


    // $ANTLR start "if_else"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:221:1: if_else : if_ OPEN_BRACKET value CLOSE_BRACKET code ( else_ code )? ;
    public final void if_else() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:221:9: ( if_ OPEN_BRACKET value CLOSE_BRACKET code ( else_ code )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:221:11: if_ OPEN_BRACKET value CLOSE_BRACKET code ( else_ code )?
            {
            pushFollow(FOLLOW_if__in_if_else1403);
            if_();

            state._fsp--;
            if (state.failed) return ;
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_if_else1405); if (state.failed) return ;
            pushFollow(FOLLOW_value_in_if_else1407);
            value();

            state._fsp--;
            if (state.failed) return ;
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_if_else1409); if (state.failed) return ;
            pushFollow(FOLLOW_code_in_if_else1411);
            code();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:221:53: ( else_ code )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==ELSE) ) {
                int LA93_1 = input.LA(2);

                if ( (synpred155_JavaParser()) ) {
                    alt93=1;
                }
            }
            switch (alt93) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:221:54: else_ code
                    {
                    pushFollow(FOLLOW_else__in_if_else1414);
                    else_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_code_in_if_else1416);
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
        }
        return ;
    }
    // $ANTLR end "if_else"


    // $ANTLR start "try_catch"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:224:1: try_catch : try_ code ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )* ( finally_ code )? ;
    public final void try_catch() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:225:2: ( try_ code ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )* ( finally_ code )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:225:4: try_ code ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )* ( finally_ code )?
            {
            pushFollow(FOLLOW_try__in_try_catch1429);
            try_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_code_in_try_catch1431);
            code();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:225:14: ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )*
            loop94:
            do {
                int alt94=2;
                int LA94_0 = input.LA(1);

                if ( (LA94_0==CATCH) ) {
                    int LA94_2 = input.LA(2);

                    if ( (synpred156_JavaParser()) ) {
                        alt94=1;
                    }


                }


                switch (alt94) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:225:15: catch_ OPEN_BRACKET id id CLOSE_BRACKET code
            	    {
            	    pushFollow(FOLLOW_catch__in_try_catch1434);
            	    catch_();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_try_catch1436); if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_try_catch1438);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_try_catch1440);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_try_catch1442); if (state.failed) return ;
            	    pushFollow(FOLLOW_code_in_try_catch1444);
            	    code();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop94;
                }
            } while (true);

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:225:62: ( finally_ code )?
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==FINALLY) ) {
                int LA95_1 = input.LA(2);

                if ( (synpred157_JavaParser()) ) {
                    alt95=1;
                }
            }
            switch (alt95) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:225:63: finally_ code
                    {
                    pushFollow(FOLLOW_finally__in_try_catch1449);
                    finally_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_code_in_try_catch1451);
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
        }
        return ;
    }
    // $ANTLR end "try_catch"


    // $ANTLR start "synchronized_block"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:227:1: synchronized_block : synchronized_ code ;
    public final void synchronized_block() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:228:2: ( synchronized_ code )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:228:4: synchronized_ code
            {
            pushFollow(FOLLOW_synchronized__in_synchronized_block1463);
            synchronized_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_code_in_synchronized_block1465);
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
        }
        return ;
    }
    // $ANTLR end "synchronized_block"


    // $ANTLR start "label"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:231:1: label : id colon ;
    public final void label() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:231:7: ( id colon )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:231:9: id colon
            {
            pushFollow(FOLLOW_id_in_label1475);
            id();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_colon_in_label1477);
            colon();

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
    // $ANTLR end "label"


    // $ANTLR start "cast"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:233:1: cast : OPEN_BRACKET variable_type CLOSE_BRACKET ;
    public final void cast() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:233:6: ( OPEN_BRACKET variable_type CLOSE_BRACKET )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:233:8: OPEN_BRACKET variable_type CLOSE_BRACKET
            {
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_cast1485); if (state.failed) return ;
            pushFollow(FOLLOW_variable_type_in_cast1487);
            variable_type();

            state._fsp--;
            if (state.failed) return ;
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_cast1489); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              helper.registerOperator("(cast)");
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
    // $ANTLR end "cast"


    // $ANTLR start "variable_type"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:236:1: variable_type : ( ( final_ | volatile_ )? primitive ( array )? | ( final_ | volatile_ )? class_name ( array )? | ( final_ | volatile_ )? void_ );
    public final void variable_type() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:237:2: ( ( final_ | volatile_ )? primitive ( array )? | ( final_ | volatile_ )? class_name ( array )? | ( final_ | volatile_ )? void_ )
            int alt101=3;
            switch ( input.LA(1) ) {
            case FINAL:
                {
                switch ( input.LA(2) ) {
                case VOID:
                    {
                    alt101=3;
                    }
                    break;
                case BOOLEAN:
                case BYTE:
                case CHAR:
                case SHORT:
                case INTEGER:
                case LONG:
                case FLOAT:
                case DOUBLE:
                    {
                    alt101=1;
                    }
                    break;
                case ID:
                    {
                    alt101=2;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 101, 1, input);

                    throw nvae;
                }

                }
                break;
            case VOLATILE:
                {
                switch ( input.LA(2) ) {
                case VOID:
                    {
                    alt101=3;
                    }
                    break;
                case ID:
                    {
                    alt101=2;
                    }
                    break;
                case BOOLEAN:
                case BYTE:
                case CHAR:
                case SHORT:
                case INTEGER:
                case LONG:
                case FLOAT:
                case DOUBLE:
                    {
                    alt101=1;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 101, 2, input);

                    throw nvae;
                }

                }
                break;
            case BOOLEAN:
            case BYTE:
            case CHAR:
            case SHORT:
            case INTEGER:
            case LONG:
            case FLOAT:
            case DOUBLE:
                {
                alt101=1;
                }
                break;
            case ID:
                {
                alt101=2;
                }
                break;
            case VOID:
                {
                alt101=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 101, 0, input);

                throw nvae;
            }

            switch (alt101) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:237:4: ( final_ | volatile_ )? primitive ( array )?
                    {
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:237:4: ( final_ | volatile_ )?
                    int alt96=3;
                    int LA96_0 = input.LA(1);

                    if ( (LA96_0==FINAL) ) {
                        alt96=1;
                    }
                    else if ( (LA96_0==VOLATILE) ) {
                        alt96=2;
                    }
                    switch (alt96) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:237:5: final_
                            {
                            pushFollow(FOLLOW_final__in_variable_type1503);
                            final_();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;
                        case 2 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:237:14: volatile_
                            {
                            pushFollow(FOLLOW_volatile__in_variable_type1507);
                            volatile_();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_primitive_in_variable_type1511);
                    primitive();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:237:36: ( array )?
                    int alt97=2;
                    int LA97_0 = input.LA(1);

                    if ( (LA97_0==OPEN_RECT_BRACKET) ) {
                        alt97=1;
                    }
                    switch (alt97) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: array
                            {
                            pushFollow(FOLLOW_array_in_variable_type1513);
                            array();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:238:4: ( final_ | volatile_ )? class_name ( array )?
                    {
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:238:4: ( final_ | volatile_ )?
                    int alt98=3;
                    int LA98_0 = input.LA(1);

                    if ( (LA98_0==FINAL) ) {
                        alt98=1;
                    }
                    else if ( (LA98_0==VOLATILE) ) {
                        alt98=2;
                    }
                    switch (alt98) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:238:5: final_
                            {
                            pushFollow(FOLLOW_final__in_variable_type1520);
                            final_();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;
                        case 2 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:238:14: volatile_
                            {
                            pushFollow(FOLLOW_volatile__in_variable_type1524);
                            volatile_();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_class_name_in_variable_type1528);
                    class_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:238:37: ( array )?
                    int alt99=2;
                    int LA99_0 = input.LA(1);

                    if ( (LA99_0==OPEN_RECT_BRACKET) ) {
                        alt99=1;
                    }
                    switch (alt99) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: array
                            {
                            pushFollow(FOLLOW_array_in_variable_type1530);
                            array();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:239:4: ( final_ | volatile_ )? void_
                    {
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:239:4: ( final_ | volatile_ )?
                    int alt100=3;
                    int LA100_0 = input.LA(1);

                    if ( (LA100_0==FINAL) ) {
                        alt100=1;
                    }
                    else if ( (LA100_0==VOLATILE) ) {
                        alt100=2;
                    }
                    switch (alt100) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:239:5: final_
                            {
                            pushFollow(FOLLOW_final__in_variable_type1537);
                            final_();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;
                        case 2 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:239:14: volatile_
                            {
                            pushFollow(FOLLOW_volatile__in_variable_type1541);
                            volatile_();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_void__in_variable_type1545);
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:242:1: id : ID ;
    public final JavaParser.id_return id() throws RecognitionException {
        JavaParser.id_return retval = new JavaParser.id_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:242:4: ( ID )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:242:6: ID
            {
            match(input,ID,FOLLOW_ID_in_id1555); if (state.failed) return retval;
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

    public static class binary_operator_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "binary_operator"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:244:1: binary_operator : ( plus | minus | star | SLASH | PERCENT | EQUAL | UNEQUAL | LT | GT | LE | GE | LOGICAL_OR | BIT_OR | LOGICAL_AND | BIT_AND );
    public final JavaParser.binary_operator_return binary_operator() throws RecognitionException {
        JavaParser.binary_operator_return retval = new JavaParser.binary_operator_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:245:2: ( plus | minus | star | SLASH | PERCENT | EQUAL | UNEQUAL | LT | GT | LE | GE | LOGICAL_OR | BIT_OR | LOGICAL_AND | BIT_AND )
            int alt102=15;
            switch ( input.LA(1) ) {
            case PLUS:
                {
                alt102=1;
                }
                break;
            case MINUS:
                {
                alt102=2;
                }
                break;
            case STAR:
                {
                alt102=3;
                }
                break;
            case SLASH:
                {
                alt102=4;
                }
                break;
            case PERCENT:
                {
                alt102=5;
                }
                break;
            case EQUAL:
                {
                alt102=6;
                }
                break;
            case UNEQUAL:
                {
                alt102=7;
                }
                break;
            case LT:
                {
                alt102=8;
                }
                break;
            case GT:
                {
                alt102=9;
                }
                break;
            case LE:
                {
                alt102=10;
                }
                break;
            case GE:
                {
                alt102=11;
                }
                break;
            case LOGICAL_OR:
                {
                alt102=12;
                }
                break;
            case BIT_OR:
                {
                alt102=13;
                }
                break;
            case LOGICAL_AND:
                {
                alt102=14;
                }
                break;
            case BIT_AND:
                {
                alt102=15;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 102, 0, input);

                throw nvae;
            }

            switch (alt102) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:245:4: plus
                    {
                    pushFollow(FOLLOW_plus_in_binary_operator1566);
                    plus();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:246:4: minus
                    {
                    pushFollow(FOLLOW_minus_in_binary_operator1571);
                    minus();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:247:4: star
                    {
                    pushFollow(FOLLOW_star_in_binary_operator1576);
                    star();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:248:4: SLASH
                    {
                    match(input,SLASH,FOLLOW_SLASH_in_binary_operator1581); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 5 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:249:4: PERCENT
                    {
                    match(input,PERCENT,FOLLOW_PERCENT_in_binary_operator1588); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 6 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:250:4: EQUAL
                    {
                    match(input,EQUAL,FOLLOW_EQUAL_in_binary_operator1595); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 7 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:251:4: UNEQUAL
                    {
                    match(input,UNEQUAL,FOLLOW_UNEQUAL_in_binary_operator1602); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 8 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:252:4: LT
                    {
                    match(input,LT,FOLLOW_LT_in_binary_operator1609); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 9 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:253:4: GT
                    {
                    match(input,GT,FOLLOW_GT_in_binary_operator1616); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 10 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:254:4: LE
                    {
                    match(input,LE,FOLLOW_LE_in_binary_operator1623); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 11 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:255:4: GE
                    {
                    match(input,GE,FOLLOW_GE_in_binary_operator1630); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 12 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:256:4: LOGICAL_OR
                    {
                    match(input,LOGICAL_OR,FOLLOW_LOGICAL_OR_in_binary_operator1637); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 13 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:257:4: BIT_OR
                    {
                    match(input,BIT_OR,FOLLOW_BIT_OR_in_binary_operator1644); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 14 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:258:4: LOGICAL_AND
                    {
                    match(input,LOGICAL_AND,FOLLOW_LOGICAL_AND_in_binary_operator1651); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 15 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:259:4: BIT_AND
                    {
                    match(input,BIT_AND,FOLLOW_BIT_AND_in_binary_operator1658); if (state.failed) return retval;
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
        }
        return retval;
    }
    // $ANTLR end "binary_operator"

    public static class left_unary_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "left_unary"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:262:1: left_unary : ( INC | DEC | NOT | MINUS | PLUS );
    public final JavaParser.left_unary_return left_unary() throws RecognitionException {
        JavaParser.left_unary_return retval = new JavaParser.left_unary_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:263:2: ( INC | DEC | NOT | MINUS | PLUS )
            int alt103=5;
            switch ( input.LA(1) ) {
            case INC:
                {
                alt103=1;
                }
                break;
            case DEC:
                {
                alt103=2;
                }
                break;
            case NOT:
                {
                alt103=3;
                }
                break;
            case MINUS:
                {
                alt103=4;
                }
                break;
            case PLUS:
                {
                alt103=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 103, 0, input);

                throw nvae;
            }

            switch (alt103) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:263:4: INC
                    {
                    match(input,INC,FOLLOW_INC_in_left_unary1672); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:264:4: DEC
                    {
                    match(input,DEC,FOLLOW_DEC_in_left_unary1679); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:265:4: NOT
                    {
                    match(input,NOT,FOLLOW_NOT_in_left_unary1686); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:266:4: MINUS
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_left_unary1693); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 5 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:267:4: PLUS
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_left_unary1700); if (state.failed) return retval;
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
        }
        return retval;
    }
    // $ANTLR end "left_unary"

    public static class right_unary_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "right_unary"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:270:1: right_unary : ( INC | DEC | NOT );
    public final JavaParser.right_unary_return right_unary() throws RecognitionException {
        JavaParser.right_unary_return retval = new JavaParser.right_unary_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:271:2: ( INC | DEC | NOT )
            int alt104=3;
            switch ( input.LA(1) ) {
            case INC:
                {
                alt104=1;
                }
                break;
            case DEC:
                {
                alt104=2;
                }
                break;
            case NOT:
                {
                alt104=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 104, 0, input);

                throw nvae;
            }

            switch (alt104) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:271:4: INC
                    {
                    match(input,INC,FOLLOW_INC_in_right_unary1714); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:272:4: DEC
                    {
                    match(input,DEC,FOLLOW_DEC_in_right_unary1721); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:273:4: NOT
                    {
                    match(input,NOT,FOLLOW_NOT_in_right_unary1728); if (state.failed) return retval;
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
        }
        return retval;
    }
    // $ANTLR end "right_unary"

    public static class primitive_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "primitive"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:276:1: primitive : ( BOOLEAN | BYTE | CHAR | SHORT | INTEGER | LONG | FLOAT | DOUBLE );
    public final JavaParser.primitive_return primitive() throws RecognitionException {
        JavaParser.primitive_return retval = new JavaParser.primitive_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:277:2: ( BOOLEAN | BYTE | CHAR | SHORT | INTEGER | LONG | FLOAT | DOUBLE )
            int alt105=8;
            switch ( input.LA(1) ) {
            case BOOLEAN:
                {
                alt105=1;
                }
                break;
            case BYTE:
                {
                alt105=2;
                }
                break;
            case CHAR:
                {
                alt105=3;
                }
                break;
            case SHORT:
                {
                alt105=4;
                }
                break;
            case INTEGER:
                {
                alt105=5;
                }
                break;
            case LONG:
                {
                alt105=6;
                }
                break;
            case FLOAT:
                {
                alt105=7;
                }
                break;
            case DOUBLE:
                {
                alt105=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 105, 0, input);

                throw nvae;
            }

            switch (alt105) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:277:4: BOOLEAN
                    {
                    match(input,BOOLEAN,FOLLOW_BOOLEAN_in_primitive1741); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:278:4: BYTE
                    {
                    match(input,BYTE,FOLLOW_BYTE_in_primitive1748); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:279:4: CHAR
                    {
                    match(input,CHAR,FOLLOW_CHAR_in_primitive1755); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:280:4: SHORT
                    {
                    match(input,SHORT,FOLLOW_SHORT_in_primitive1762); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 5 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:281:4: INTEGER
                    {
                    match(input,INTEGER,FOLLOW_INTEGER_in_primitive1769); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 6 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:282:4: LONG
                    {
                    match(input,LONG,FOLLOW_LONG_in_primitive1776); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 7 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:283:4: FLOAT
                    {
                    match(input,FLOAT,FOLLOW_FLOAT_in_primitive1783); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 8 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:284:4: DOUBLE
                    {
                    match(input,DOUBLE,FOLLOW_DOUBLE_in_primitive1790); if (state.failed) return retval;
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
        }
        return retval;
    }
    // $ANTLR end "primitive"

    public static class int_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "int_const"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:292:1: int_const : INT_CONST ;
    public final JavaParser.int_const_return int_const() throws RecognitionException {
        JavaParser.int_const_return retval = new JavaParser.int_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:293:2: ( INT_CONST )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:293:4: INT_CONST
            {
            match(input,INT_CONST,FOLLOW_INT_CONST_in_int_const1808); if (state.failed) return retval;
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

    public static class long_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "long_const"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:294:1: long_const : LONG_CONST ;
    public final JavaParser.long_const_return long_const() throws RecognitionException {
        JavaParser.long_const_return retval = new JavaParser.long_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:295:2: ( LONG_CONST )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:295:4: LONG_CONST
            {
            match(input,LONG_CONST,FOLLOW_LONG_CONST_in_long_const1818); if (state.failed) return retval;
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
    // $ANTLR end "long_const"

    public static class hex_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "hex_const"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:296:1: hex_const : HEX_CONST ;
    public final JavaParser.hex_const_return hex_const() throws RecognitionException {
        JavaParser.hex_const_return retval = new JavaParser.hex_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:297:2: ( HEX_CONST )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:297:4: HEX_CONST
            {
            match(input,HEX_CONST,FOLLOW_HEX_CONST_in_hex_const1828); if (state.failed) return retval;
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
    // $ANTLR end "hex_const"

    public static class hex_long_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "hex_long_const"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:298:1: hex_long_const : HEX_LONG_CONST ;
    public final JavaParser.hex_long_const_return hex_long_const() throws RecognitionException {
        JavaParser.hex_long_const_return retval = new JavaParser.hex_long_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:299:2: ( HEX_LONG_CONST )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:299:4: HEX_LONG_CONST
            {
            match(input,HEX_LONG_CONST,FOLLOW_HEX_LONG_CONST_in_hex_long_const1838); if (state.failed) return retval;
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
    // $ANTLR end "hex_long_const"

    public static class string_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "string_const"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:300:1: string_const : STRING_CONST ;
    public final JavaParser.string_const_return string_const() throws RecognitionException {
        JavaParser.string_const_return retval = new JavaParser.string_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:301:2: ( STRING_CONST )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:301:4: STRING_CONST
            {
            match(input,STRING_CONST,FOLLOW_STRING_CONST_in_string_const1848); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:302:1: float_const : FLOAT_CONST ;
    public final JavaParser.float_const_return float_const() throws RecognitionException {
        JavaParser.float_const_return retval = new JavaParser.float_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:303:2: ( FLOAT_CONST )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:303:4: FLOAT_CONST
            {
            match(input,FLOAT_CONST,FOLLOW_FLOAT_CONST_in_float_const1858); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:304:1: char_const : CHAR_CONST ;
    public final JavaParser.char_const_return char_const() throws RecognitionException {
        JavaParser.char_const_return retval = new JavaParser.char_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:305:2: ( CHAR_CONST )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:305:4: CHAR_CONST
            {
            match(input,CHAR_CONST,FOLLOW_CHAR_CONST_in_char_const1868); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:306:1: null_const : NULL ;
    public final JavaParser.null_const_return null_const() throws RecognitionException {
        JavaParser.null_const_return retval = new JavaParser.null_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:307:2: ( NULL )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:307:4: NULL
            {
            match(input,NULL,FOLLOW_NULL_in_null_const1878); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:308:1: boolean_const : BOOL_CONST ;
    public final JavaParser.boolean_const_return boolean_const() throws RecognitionException {
        JavaParser.boolean_const_return retval = new JavaParser.boolean_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:309:2: ( BOOL_CONST )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:309:4: BOOL_CONST
            {
            match(input,BOOL_CONST,FOLLOW_BOOL_CONST_in_boolean_const1889); if (state.failed) return retval;
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


    // $ANTLR start "array_const"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:310:1: array_const : block_begin value ( comma value )* block_end ;
    public final void array_const() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:311:2: ( block_begin value ( comma value )* block_end )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:311:4: block_begin value ( comma value )* block_end
            {
            pushFollow(FOLLOW_block_begin_in_array_const1899);
            block_begin();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_value_in_array_const1901);
            value();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:311:22: ( comma value )*
            loop106:
            do {
                int alt106=2;
                int LA106_0 = input.LA(1);

                if ( (LA106_0==COMMA) ) {
                    alt106=1;
                }


                switch (alt106) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:311:23: comma value
            	    {
            	    pushFollow(FOLLOW_comma_in_array_const1904);
            	    comma();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_value_in_array_const1906);
            	    value();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop106;
                }
            } while (true);

            pushFollow(FOLLOW_block_end_in_array_const1910);
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
        }
        return ;
    }
    // $ANTLR end "array_const"


    // $ANTLR start "class_const"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:312:1: class_const : ( class_name | primitive | void_ ) dot class_ ;
    public final void class_const() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:313:2: ( ( class_name | primitive | void_ ) dot class_ )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:313:4: ( class_name | primitive | void_ ) dot class_
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:313:4: ( class_name | primitive | void_ )
            int alt107=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt107=1;
                }
                break;
            case BOOLEAN:
            case BYTE:
            case CHAR:
            case SHORT:
            case INTEGER:
            case LONG:
            case FLOAT:
            case DOUBLE:
                {
                alt107=2;
                }
                break;
            case VOID:
                {
                alt107=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 107, 0, input);

                throw nvae;
            }

            switch (alt107) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:313:5: class_name
                    {
                    pushFollow(FOLLOW_class_name_in_class_const1919);
                    class_name();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:313:18: primitive
                    {
                    pushFollow(FOLLOW_primitive_in_class_const1923);
                    primitive();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:313:30: void_
                    {
                    pushFollow(FOLLOW_void__in_class_const1927);
                    void_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_dot_in_class_const1930);
            dot();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_class__in_class_const1932);
            class_();

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
    // $ANTLR end "class_const"

    public static class package_name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "package_name"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:320:1: package_name : ( id dot )* id ;
    public final JavaParser.package_name_return package_name() throws RecognitionException {
        JavaParser.package_name_return retval = new JavaParser.package_name_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:321:2: ( ( id dot )* id )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:321:4: ( id dot )* id
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:321:4: ( id dot )*
            loop108:
            do {
                int alt108=2;
                int LA108_0 = input.LA(1);

                if ( (LA108_0==ID) ) {
                    int LA108_1 = input.LA(2);

                    if ( (LA108_1==DOT) ) {
                        alt108=1;
                    }


                }


                switch (alt108) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:321:5: id dot
            	    {
            	    pushFollow(FOLLOW_id_in_package_name1948);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    pushFollow(FOLLOW_dot_in_package_name1950);
            	    dot();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop108;
                }
            } while (true);

            pushFollow(FOLLOW_id_in_package_name1954);
            id();

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
        }
        return retval;
    }
    // $ANTLR end "package_name"


    // $ANTLR start "import_name"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:324:1: import_name : ( id dot )+ ( id | star ) ;
    public final void import_name() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:325:2: ( ( id dot )+ ( id | star ) )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:325:4: ( id dot )+ ( id | star )
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:325:4: ( id dot )+
            int cnt109=0;
            loop109:
            do {
                int alt109=2;
                int LA109_0 = input.LA(1);

                if ( (LA109_0==ID) ) {
                    int LA109_1 = input.LA(2);

                    if ( (LA109_1==DOT) ) {
                        alt109=1;
                    }


                }


                switch (alt109) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:325:5: id dot
            	    {
            	    pushFollow(FOLLOW_id_in_import_name1968);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_dot_in_import_name1970);
            	    dot();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt109 >= 1 ) break loop109;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(109, input);
                        throw eee;
                }
                cnt109++;
            } while (true);

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:325:14: ( id | star )
            int alt110=2;
            int LA110_0 = input.LA(1);

            if ( (LA110_0==ID) ) {
                alt110=1;
            }
            else if ( (LA110_0==STAR) ) {
                alt110=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 110, 0, input);

                throw nvae;
            }
            switch (alt110) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:325:15: id
                    {
                    pushFollow(FOLLOW_id_in_import_name1975);
                    id();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:325:20: star
                    {
                    pushFollow(FOLLOW_star_in_import_name1979);
                    star();

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
    // $ANTLR end "import_name"


    // $ANTLR start "class_name"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:328:1: class_name : id ( dot id )* ( generic )? ;
    public final void class_name() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:329:2: ( id ( dot id )* ( generic )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:329:4: id ( dot id )* ( generic )?
            {
            pushFollow(FOLLOW_id_in_class_name1991);
            id();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:329:7: ( dot id )*
            loop111:
            do {
                int alt111=2;
                alt111 = dfa111.predict(input);
                switch (alt111) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:329:8: dot id
            	    {
            	    pushFollow(FOLLOW_dot_in_class_name1994);
            	    dot();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_class_name1996);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop111;
                }
            } while (true);

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:329:17: ( generic )?
            int alt112=2;
            alt112 = dfa112.predict(input);
            switch (alt112) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: generic
                    {
                    pushFollow(FOLLOW_generic_in_class_name2000);
                    generic();

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
    // $ANTLR end "class_name"


    // $ANTLR start "method_name"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:332:1: method_name : ( ( ( super_ | this_ | new_class ) dot )? variable_name ( dot ( class_ | variable_name ) )* | super_ | this_ );
    public final void method_name() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:333:2: ( ( ( super_ | this_ | new_class ) dot )? variable_name ( dot ( class_ | variable_name ) )* | super_ | this_ )
            int alt117=3;
            switch ( input.LA(1) ) {
            case SUPER:
                {
                int LA117_1 = input.LA(2);

                if ( (LA117_1==DOT) ) {
                    alt117=1;
                }
                else if ( (LA117_1==OPEN_BRACKET) ) {
                    alt117=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 117, 1, input);

                    throw nvae;
                }
                }
                break;
            case THIS:
                {
                int LA117_2 = input.LA(2);

                if ( (LA117_2==DOT) ) {
                    alt117=1;
                }
                else if ( (LA117_2==OPEN_BRACKET) ) {
                    alt117=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 117, 2, input);

                    throw nvae;
                }
                }
                break;
            case NEW:
            case ID:
                {
                alt117=1;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 117, 0, input);

                throw nvae;
            }

            switch (alt117) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:333:4: ( ( super_ | this_ | new_class ) dot )? variable_name ( dot ( class_ | variable_name ) )*
                    {
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:333:4: ( ( super_ | this_ | new_class ) dot )?
                    int alt114=2;
                    int LA114_0 = input.LA(1);

                    if ( (LA114_0==NEW||(LA114_0>=THIS && LA114_0<=SUPER)) ) {
                        alt114=1;
                    }
                    switch (alt114) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:334:4: ( super_ | this_ | new_class ) dot
                            {
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:334:4: ( super_ | this_ | new_class )
                            int alt113=3;
                            switch ( input.LA(1) ) {
                            case SUPER:
                                {
                                alt113=1;
                                }
                                break;
                            case THIS:
                                {
                                alt113=2;
                                }
                                break;
                            case NEW:
                                {
                                alt113=3;
                                }
                                break;
                            default:
                                if (state.backtracking>0) {state.failed=true; return ;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 113, 0, input);

                                throw nvae;
                            }

                            switch (alt113) {
                                case 1 :
                                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:334:5: super_
                                    {
                                    pushFollow(FOLLOW_super__in_method_name2018);
                                    super_();

                                    state._fsp--;
                                    if (state.failed) return ;

                                    }
                                    break;
                                case 2 :
                                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:334:14: this_
                                    {
                                    pushFollow(FOLLOW_this__in_method_name2022);
                                    this_();

                                    state._fsp--;
                                    if (state.failed) return ;

                                    }
                                    break;
                                case 3 :
                                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:334:22: new_class
                                    {
                                    pushFollow(FOLLOW_new_class_in_method_name2026);
                                    new_class();

                                    state._fsp--;
                                    if (state.failed) return ;

                                    }
                                    break;

                            }

                            pushFollow(FOLLOW_dot_in_method_name2029);
                            dot();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_variable_name_in_method_name2038);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:337:3: ( dot ( class_ | variable_name ) )*
                    loop116:
                    do {
                        int alt116=2;
                        int LA116_0 = input.LA(1);

                        if ( (LA116_0==DOT) ) {
                            alt116=1;
                        }


                        switch (alt116) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:338:4: dot ( class_ | variable_name )
                    	    {
                    	    pushFollow(FOLLOW_dot_in_method_name2048);
                    	    dot();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:338:8: ( class_ | variable_name )
                    	    int alt115=2;
                    	    int LA115_0 = input.LA(1);

                    	    if ( (LA115_0==CLASS) ) {
                    	        alt115=1;
                    	    }
                    	    else if ( (LA115_0==ID) ) {
                    	        alt115=2;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return ;}
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 115, 0, input);

                    	        throw nvae;
                    	    }
                    	    switch (alt115) {
                    	        case 1 :
                    	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:338:9: class_
                    	            {
                    	            pushFollow(FOLLOW_class__in_method_name2051);
                    	            class_();

                    	            state._fsp--;
                    	            if (state.failed) return ;

                    	            }
                    	            break;
                    	        case 2 :
                    	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:338:18: variable_name
                    	            {
                    	            pushFollow(FOLLOW_variable_name_in_method_name2055);
                    	            variable_name();

                    	            state._fsp--;
                    	            if (state.failed) return ;

                    	            }
                    	            break;

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop116;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:340:4: super_
                    {
                    pushFollow(FOLLOW_super__in_method_name2066);
                    super_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:341:4: this_
                    {
                    pushFollow(FOLLOW_this__in_method_name2071);
                    this_();

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
    // $ANTLR end "method_name"


    // $ANTLR start "variable_name"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:344:1: variable_name : name ( array )? ;
    public final void variable_name() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:345:2: ( name ( array )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:345:4: name ( array )?
            {
            pushFollow(FOLLOW_name_in_variable_name2082);
            name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:345:9: ( array )?
            int alt118=2;
            int LA118_0 = input.LA(1);

            if ( (LA118_0==OPEN_RECT_BRACKET) ) {
                alt118=1;
            }
            switch (alt118) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: array
                    {
                    pushFollow(FOLLOW_array_in_variable_name2084);
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

    public static class annotation_name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "annotation_name"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:347:1: annotation_name : AT ID ;
    public final JavaParser.annotation_name_return annotation_name() throws RecognitionException {
        JavaParser.annotation_name_return retval = new JavaParser.annotation_name_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:348:2: ( AT ID )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:348:4: AT ID
            {
            match(input,AT,FOLLOW_AT_in_annotation_name2095); if (state.failed) return retval;
            match(input,ID,FOLLOW_ID_in_annotation_name2097); if (state.failed) return retval;
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
    // $ANTLR end "annotation_name"


    // $ANTLR start "name"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:349:1: name : ( id dot )* id ;
    public final void name() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:349:6: ( ( id dot )* id )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:349:8: ( id dot )* id
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:349:8: ( id dot )*
            loop119:
            do {
                int alt119=2;
                int LA119_0 = input.LA(1);

                if ( (LA119_0==ID) ) {
                    int LA119_1 = input.LA(2);

                    if ( (LA119_1==DOT) ) {
                        int LA119_3 = input.LA(3);

                        if ( (synpred211_JavaParser()) ) {
                            alt119=1;
                        }


                    }


                }


                switch (alt119) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:349:9: id dot
            	    {
            	    pushFollow(FOLLOW_id_in_name2107);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_dot_in_name2109);
            	    dot();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop119;
                }
            } while (true);

            pushFollow(FOLLOW_id_in_name2113);
            id();

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
    // $ANTLR end "name"


    // $ANTLR start "array"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:351:1: array : ( open_rect_bracket ( value )? close_rect_bracket )+ ;
    public final void array() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:351:7: ( ( open_rect_bracket ( value )? close_rect_bracket )+ )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:351:9: ( open_rect_bracket ( value )? close_rect_bracket )+
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:351:9: ( open_rect_bracket ( value )? close_rect_bracket )+
            int cnt121=0;
            loop121:
            do {
                int alt121=2;
                int LA121_0 = input.LA(1);

                if ( (LA121_0==OPEN_RECT_BRACKET) ) {
                    alt121=1;
                }


                switch (alt121) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:351:10: open_rect_bracket ( value )? close_rect_bracket
            	    {
            	    pushFollow(FOLLOW_open_rect_bracket_in_array2122);
            	    open_rect_bracket();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:351:28: ( value )?
            	    int alt120=2;
            	    int LA120_0 = input.LA(1);

            	    if ( ((LA120_0>=VOID && LA120_0<=NEW)||(LA120_0>=NULL && LA120_0<=SUPER)||(LA120_0>=BOOLEAN && LA120_0<=OPEN_CURLY_BRACKET)||(LA120_0>=INC && LA120_0<=MINUS)||LA120_0==NOT||LA120_0==OPEN_BRACKET||(LA120_0>=BOOL_CONST && LA120_0<=INT_CONST)||(LA120_0>=HEX_CONST && LA120_0<=HEX_LONG_CONST)||LA120_0==FLOAT_CONST||(LA120_0>=STRING_CONST && LA120_0<=CHAR_CONST)) ) {
            	        alt120=1;
            	    }
            	    switch (alt120) {
            	        case 1 :
            	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: value
            	            {
            	            pushFollow(FOLLOW_value_in_array2124);
            	            value();

            	            state._fsp--;
            	            if (state.failed) return ;

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_close_rect_bracket_in_array2127);
            	    close_rect_bracket();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt121 >= 1 ) break loop121;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(121, input);
                        throw eee;
                }
                cnt121++;
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
    // $ANTLR end "array"

    public static class package__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "package_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:357:1: package_ : PACKAGE ;
    public final JavaParser.package__return package_() throws RecognitionException {
        JavaParser.package__return retval = new JavaParser.package__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:358:2: ( PACKAGE )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:358:4: PACKAGE
            {
            match(input,PACKAGE,FOLLOW_PACKAGE_in_package_2142); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:359:1: import_ : IMPORT ;
    public final JavaParser.import__return import_() throws RecognitionException {
        JavaParser.import__return retval = new JavaParser.import__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:360:2: ( IMPORT )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:360:4: IMPORT
            {
            match(input,IMPORT,FOLLOW_IMPORT_in_import_2153); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:361:1: class_ : CLASS ;
    public final JavaParser.class__return class_() throws RecognitionException {
        JavaParser.class__return retval = new JavaParser.class__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:361:8: ( CLASS )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:361:10: CLASS
            {
            match(input,CLASS,FOLLOW_CLASS_in_class_2163); if (state.failed) return retval;
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

    public static class enum__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "enum_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:362:1: enum_ : ENUM ;
    public final JavaParser.enum__return enum_() throws RecognitionException {
        JavaParser.enum__return retval = new JavaParser.enum__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:362:7: ( ENUM )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:362:9: ENUM
            {
            match(input,ENUM,FOLLOW_ENUM_in_enum_2172); if (state.failed) return retval;
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
    // $ANTLR end "enum_"

    public static class interface__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "interface_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:363:1: interface_ : INTERFACE ;
    public final JavaParser.interface__return interface_() throws RecognitionException {
        JavaParser.interface__return retval = new JavaParser.interface__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:364:2: ( INTERFACE )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:364:4: INTERFACE
            {
            match(input,INTERFACE,FOLLOW_INTERFACE_in_interface_2182); if (state.failed) return retval;
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
    // $ANTLR end "interface_"

    public static class extends__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "extends_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:365:1: extends_ : EXTENDS ;
    public final JavaParser.extends__return extends_() throws RecognitionException {
        JavaParser.extends__return retval = new JavaParser.extends__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:365:9: ( EXTENDS )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:365:11: EXTENDS
            {
            match(input,EXTENDS,FOLLOW_EXTENDS_in_extends_2190); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:366:1: implements_ : IMPLEMENTS ;
    public final JavaParser.implements__return implements_() throws RecognitionException {
        JavaParser.implements__return retval = new JavaParser.implements__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:367:2: ( IMPLEMENTS )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:367:4: IMPLEMENTS
            {
            match(input,IMPLEMENTS,FOLLOW_IMPLEMENTS_in_implements_2200); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:368:1: this_ : THIS ;
    public final JavaParser.this__return this_() throws RecognitionException {
        JavaParser.this__return retval = new JavaParser.this__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:368:7: ( THIS )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:368:9: THIS
            {
            match(input,THIS,FOLLOW_THIS_in_this_2209); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:369:1: super_ : SUPER ;
    public final JavaParser.super__return super_() throws RecognitionException {
        JavaParser.super__return retval = new JavaParser.super__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:369:8: ( SUPER )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:369:10: SUPER
            {
            match(input,SUPER,FOLLOW_SUPER_in_super_2218); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:370:1: void_ : VOID ;
    public final JavaParser.void__return void_() throws RecognitionException {
        JavaParser.void__return retval = new JavaParser.void__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:370:7: ( VOID )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:370:9: VOID
            {
            match(input,VOID,FOLLOW_VOID_in_void_2227); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:371:1: public_ : PUBLIC ;
    public final JavaParser.public__return public_() throws RecognitionException {
        JavaParser.public__return retval = new JavaParser.public__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:371:9: ( PUBLIC )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:371:11: PUBLIC
            {
            match(input,PUBLIC,FOLLOW_PUBLIC_in_public_2236); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:372:1: private_ : PRIVATE ;
    public final JavaParser.private__return private_() throws RecognitionException {
        JavaParser.private__return retval = new JavaParser.private__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:372:9: ( PRIVATE )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:372:11: PRIVATE
            {
            match(input,PRIVATE,FOLLOW_PRIVATE_in_private_2244); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:373:1: protected_ : PROTECTED ;
    public final JavaParser.protected__return protected_() throws RecognitionException {
        JavaParser.protected__return retval = new JavaParser.protected__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:374:2: ( PROTECTED )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:374:4: PROTECTED
            {
            match(input,PROTECTED,FOLLOW_PROTECTED_in_protected_2254); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:375:1: static_ : STATIC ;
    public final JavaParser.static__return static_() throws RecognitionException {
        JavaParser.static__return retval = new JavaParser.static__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:375:9: ( STATIC )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:375:11: STATIC
            {
            match(input,STATIC,FOLLOW_STATIC_in_static_2263); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:376:1: final_ : FINAL ;
    public final JavaParser.final__return final_() throws RecognitionException {
        JavaParser.final__return retval = new JavaParser.final__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:376:8: ( FINAL )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:376:10: FINAL
            {
            match(input,FINAL,FOLLOW_FINAL_in_final_2272); if (state.failed) return retval;
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

    public static class volatile__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "volatile_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:377:1: volatile_ : VOLATILE ;
    public final JavaParser.volatile__return volatile_() throws RecognitionException {
        JavaParser.volatile__return retval = new JavaParser.volatile__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:378:2: ( VOLATILE )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:378:4: VOLATILE
            {
            match(input,VOLATILE,FOLLOW_VOLATILE_in_volatile_2283); if (state.failed) return retval;
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
    // $ANTLR end "volatile_"

    public static class synchronized__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "synchronized_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:379:1: synchronized_ : SYNCHRONIZED ;
    public final JavaParser.synchronized__return synchronized_() throws RecognitionException {
        JavaParser.synchronized__return retval = new JavaParser.synchronized__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:380:2: ( SYNCHRONIZED )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:380:4: SYNCHRONIZED
            {
            match(input,SYNCHRONIZED,FOLLOW_SYNCHRONIZED_in_synchronized_2293); if (state.failed) return retval;
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
    // $ANTLR end "synchronized_"

    public static class abstract__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "abstract_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:381:1: abstract_ : ABSTRACT ;
    public final JavaParser.abstract__return abstract_() throws RecognitionException {
        JavaParser.abstract__return retval = new JavaParser.abstract__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:382:2: ( ABSTRACT )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:382:4: ABSTRACT
            {
            match(input,ABSTRACT,FOLLOW_ABSTRACT_in_abstract_2303); if (state.failed) return retval;
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
    // $ANTLR end "abstract_"

    public static class transient__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "transient_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:383:1: transient_ : TRANSIENT ;
    public final JavaParser.transient__return transient_() throws RecognitionException {
        JavaParser.transient__return retval = new JavaParser.transient__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:384:2: ( TRANSIENT )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:384:4: TRANSIENT
            {
            match(input,TRANSIENT,FOLLOW_TRANSIENT_in_transient_2313); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:385:1: new_ : NEW ;
    public final JavaParser.new__return new_() throws RecognitionException {
        JavaParser.new__return retval = new JavaParser.new__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:385:6: ( NEW )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:385:8: NEW
            {
            match(input,NEW,FOLLOW_NEW_in_new_2322); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:386:1: try_ : TRY ;
    public final JavaParser.try__return try_() throws RecognitionException {
        JavaParser.try__return retval = new JavaParser.try__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:386:6: ( TRY )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:386:8: TRY
            {
            match(input,TRY,FOLLOW_TRY_in_try_2331); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:387:1: catch_ : CATCH ;
    public final JavaParser.catch__return catch_() throws RecognitionException {
        JavaParser.catch__return retval = new JavaParser.catch__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:387:8: ( CATCH )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:387:10: CATCH
            {
            match(input,CATCH,FOLLOW_CATCH_in_catch_2340); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:388:1: finally_ : FINALLY ;
    public final JavaParser.finally__return finally_() throws RecognitionException {
        JavaParser.finally__return retval = new JavaParser.finally__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:388:9: ( FINALLY )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:388:11: FINALLY
            {
            match(input,FINALLY,FOLLOW_FINALLY_in_finally_2348); if (state.failed) return retval;
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

    public static class throws__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "throws_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:389:1: throws_ : THROWS ;
    public final JavaParser.throws__return throws_() throws RecognitionException {
        JavaParser.throws__return retval = new JavaParser.throws__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:389:9: ( THROWS )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:389:11: THROWS
            {
            match(input,THROWS,FOLLOW_THROWS_in_throws_2357); if (state.failed) return retval;
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
    // $ANTLR end "throws_"

    public static class throw__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "throw_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:390:1: throw_ : THROW ;
    public final JavaParser.throw__return throw_() throws RecognitionException {
        JavaParser.throw__return retval = new JavaParser.throw__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:390:8: ( THROW )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:390:10: THROW
            {
            match(input,THROW,FOLLOW_THROW_in_throw_2366); if (state.failed) return retval;
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
    // $ANTLR end "throw_"


    // $ANTLR start "for_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:392:1: for_ : FOR ;
    public final void for_() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:392:6: ( FOR )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:392:8: FOR
            {
            match(input,FOR,FOLLOW_FOR_in_for_2376); if (state.failed) return ;
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
        }
        return ;
    }
    // $ANTLR end "for_"


    // $ANTLR start "while_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:393:1: while_ : WHILE ;
    public final void while_() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:393:8: ( WHILE )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:393:10: WHILE
            {
            match(input,WHILE,FOLLOW_WHILE_in_while_2385); if (state.failed) return ;
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
        }
        return ;
    }
    // $ANTLR end "while_"


    // $ANTLR start "do_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:394:1: do_ : DO ;
    public final void do_() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:394:5: ( DO )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:394:7: DO
            {
            match(input,DO,FOLLOW_DO_in_do_2394); if (state.failed) return ;
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
        }
        return ;
    }
    // $ANTLR end "do_"


    // $ANTLR start "if_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:395:1: if_ : IF ;
    public final void if_() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:395:5: ( IF )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:395:7: IF
            {
            match(input,IF,FOLLOW_IF_in_if_2403); if (state.failed) return ;
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
        }
        return ;
    }
    // $ANTLR end "if_"


    // $ANTLR start "else_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:396:1: else_ : ELSE ;
    public final void else_() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:396:7: ( ELSE )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:396:9: ELSE
            {
            match(input,ELSE,FOLLOW_ELSE_in_else_2412); if (state.failed) return ;
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
        }
        return ;
    }
    // $ANTLR end "else_"


    // $ANTLR start "switch_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:398:1: switch_ : SWITCH ;
    public final void switch_() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:398:9: ( SWITCH )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:398:11: SWITCH
            {
            match(input,SWITCH,FOLLOW_SWITCH_in_switch_2422); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              helper.registerOperator("switch()");
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
    // $ANTLR end "switch_"


    // $ANTLR start "case_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:399:1: case_ : CASE ;
    public final void case_() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:399:7: ( CASE )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:399:9: CASE
            {
            match(input,CASE,FOLLOW_CASE_in_case_2431); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              helper.registerOperator("switch()");
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
    // $ANTLR end "case_"


    // $ANTLR start "default_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:400:1: default_ : DEFAULT ;
    public final void default_() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:400:9: ( DEFAULT )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:400:11: DEFAULT
            {
            match(input,DEFAULT,FOLLOW_DEFAULT_in_default_2439); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              helper.registerOperator("switch()");
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
    // $ANTLR end "default_"

    public static class return__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "return_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:402:1: return_ : RETURN ;
    public final JavaParser.return__return return_() throws RecognitionException {
        JavaParser.return__return retval = new JavaParser.return__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:402:9: ( RETURN )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:402:11: RETURN
            {
            match(input,RETURN,FOLLOW_RETURN_in_return_2449); if (state.failed) return retval;
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
    // $ANTLR end "return_"

    public static class break__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "break_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:403:1: break_ : ( BREAK id | BREAK );
    public final JavaParser.break__return break_() throws RecognitionException {
        JavaParser.break__return retval = new JavaParser.break__return();
        retval.start = input.LT(1);

        Token BREAK6=null;

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:403:8: ( BREAK id | BREAK )
            int alt122=2;
            int LA122_0 = input.LA(1);

            if ( (LA122_0==BREAK) ) {
                int LA122_1 = input.LA(2);

                if ( (LA122_1==ID) ) {
                    int LA122_2 = input.LA(3);

                    if ( (synpred214_JavaParser()) ) {
                        alt122=1;
                    }
                    else if ( (true) ) {
                        alt122=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 122, 2, input);

                        throw nvae;
                    }
                }
                else if ( (LA122_1==EOF||LA122_1==CLASS||(LA122_1>=VOID && LA122_1<=CONTINUE)||(LA122_1>=THIS && LA122_1<=THROW)||(LA122_1>=PUBLIC && LA122_1<=CLOSE_CURLY_BRACKET)||(LA122_1>=INC && LA122_1<=MINUS)||LA122_1==NOT||LA122_1==COMMA||LA122_1==CLOSE_BRACKET||(LA122_1>=SEMICOLON && LA122_1<=AT)) ) {
                    alt122=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 122, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 122, 0, input);

                throw nvae;
            }
            switch (alt122) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:403:10: BREAK id
                    {
                    BREAK6=(Token)match(input,BREAK,FOLLOW_BREAK_in_break_2458); if (state.failed) return retval;
                    pushFollow(FOLLOW_id_in_break_2460);
                    id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator((BREAK6!=null?BREAK6.getText():null));
                    }

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:404:4: BREAK
                    {
                    match(input,BREAK,FOLLOW_BREAK_in_break_2467); if (state.failed) return retval;
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
        }
        return retval;
    }
    // $ANTLR end "break_"

    public static class continue__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "continue_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:406:1: continue_ : CONTINUE ;
    public final JavaParser.continue__return continue_() throws RecognitionException {
        JavaParser.continue__return retval = new JavaParser.continue__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:407:2: ( CONTINUE )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:407:4: CONTINUE
            {
            match(input,CONTINUE,FOLLOW_CONTINUE_in_continue_2479); if (state.failed) return retval;
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
    // $ANTLR end "continue_"

    public static class semicolon_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "semicolon"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:413:1: semicolon : SEMICOLON ;
    public final JavaParser.semicolon_return semicolon() throws RecognitionException {
        JavaParser.semicolon_return retval = new JavaParser.semicolon_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:414:2: ( SEMICOLON )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:414:4: SEMICOLON
            {
            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_semicolon2494); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:415:1: comma : COMMA ;
    public final JavaParser.comma_return comma() throws RecognitionException {
        JavaParser.comma_return retval = new JavaParser.comma_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:415:7: ( COMMA )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:415:9: COMMA
            {
            match(input,COMMA,FOLLOW_COMMA_in_comma2503); if (state.failed) return retval;
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

    public static class colon_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "colon"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:416:1: colon : COLON ;
    public final JavaParser.colon_return colon() throws RecognitionException {
        JavaParser.colon_return retval = new JavaParser.colon_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:416:7: ( COLON )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:416:9: COLON
            {
            match(input,COLON,FOLLOW_COLON_in_colon2512); if (state.failed) return retval;
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
    // $ANTLR end "colon"

    public static class question__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "question_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:417:1: question_ : QUESTION ;
    public final JavaParser.question__return question_() throws RecognitionException {
        JavaParser.question__return retval = new JavaParser.question__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:418:2: ( QUESTION )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:418:4: QUESTION
            {
            match(input,QUESTION,FOLLOW_QUESTION_in_question_2522); if (state.failed) return retval;
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
    // $ANTLR end "question_"

    public static class plus_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "plus"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:419:1: plus : PLUS ;
    public final JavaParser.plus_return plus() throws RecognitionException {
        JavaParser.plus_return retval = new JavaParser.plus_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:419:6: ( PLUS )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:419:8: PLUS
            {
            match(input,PLUS,FOLLOW_PLUS_in_plus2531); if (state.failed) return retval;
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
    // $ANTLR end "plus"

    public static class minus_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "minus"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:420:1: minus : MINUS ;
    public final JavaParser.minus_return minus() throws RecognitionException {
        JavaParser.minus_return retval = new JavaParser.minus_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:420:7: ( MINUS )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:420:9: MINUS
            {
            match(input,MINUS,FOLLOW_MINUS_in_minus2540); if (state.failed) return retval;
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
    // $ANTLR end "minus"

    public static class star_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "star"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:421:1: star : STAR ;
    public final JavaParser.star_return star() throws RecognitionException {
        JavaParser.star_return retval = new JavaParser.star_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:421:6: ( STAR )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:421:8: STAR
            {
            match(input,STAR,FOLLOW_STAR_in_star2549); if (state.failed) return retval;
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
    // $ANTLR end "star"

    public static class dot_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "dot"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:422:1: dot : DOT ;
    public final JavaParser.dot_return dot() throws RecognitionException {
        JavaParser.dot_return retval = new JavaParser.dot_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:422:5: ( DOT )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:422:7: DOT
            {
            match(input,DOT,FOLLOW_DOT_in_dot2558); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:423:1: assign : ( ASSIGN | INCASSIGN | DECASSIGN | BITORASSIGN | BITANDASSIGN );
    public final JavaParser.assign_return assign() throws RecognitionException {
        JavaParser.assign_return retval = new JavaParser.assign_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:423:8: ( ASSIGN | INCASSIGN | DECASSIGN | BITORASSIGN | BITANDASSIGN )
            int alt123=5;
            switch ( input.LA(1) ) {
            case ASSIGN:
                {
                alt123=1;
                }
                break;
            case INCASSIGN:
                {
                alt123=2;
                }
                break;
            case DECASSIGN:
                {
                alt123=3;
                }
                break;
            case BITORASSIGN:
                {
                alt123=4;
                }
                break;
            case BITANDASSIGN:
                {
                alt123=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 123, 0, input);

                throw nvae;
            }

            switch (alt123) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:423:10: ASSIGN
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_assign2567); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:424:4: INCASSIGN
                    {
                    match(input,INCASSIGN,FOLLOW_INCASSIGN_in_assign2574); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:425:4: DECASSIGN
                    {
                    match(input,DECASSIGN,FOLLOW_DECASSIGN_in_assign2581); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:426:4: BITORASSIGN
                    {
                    match(input,BITORASSIGN,FOLLOW_BITORASSIGN_in_assign2588); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 5 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:427:4: BITANDASSIGN
                    {
                    match(input,BITANDASSIGN,FOLLOW_BITANDASSIGN_in_assign2595); if (state.failed) return retval;
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
        }
        return retval;
    }
    // $ANTLR end "assign"


    // $ANTLR start "block_begin"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:429:1: block_begin : OPEN_CURLY_BRACKET ;
    public final void block_begin() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:430:2: ( OPEN_CURLY_BRACKET )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:430:4: OPEN_CURLY_BRACKET
            {
            match(input,OPEN_CURLY_BRACKET,FOLLOW_OPEN_CURLY_BRACKET_in_block_begin2607); if (state.failed) return ;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:431:1: block_end : CLOSE_CURLY_BRACKET ;
    public final void block_end() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:432:2: ( CLOSE_CURLY_BRACKET )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:432:4: CLOSE_CURLY_BRACKET
            {
            match(input,CLOSE_CURLY_BRACKET,FOLLOW_CLOSE_CURLY_BRACKET_in_block_end2617); if (state.failed) return ;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:433:1: open_bracket : OPEN_BRACKET ;
    public final void open_bracket() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:434:2: ( OPEN_BRACKET )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:434:4: OPEN_BRACKET
            {
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_open_bracket2627); if (state.failed) return ;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:435:1: close_bracket : CLOSE_BRACKET ;
    public final void close_bracket() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:436:2: ( CLOSE_BRACKET )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:436:4: CLOSE_BRACKET
            {
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_close_bracket2637); if (state.failed) return ;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:437:1: open_rect_bracket : OPEN_RECT_BRACKET ;
    public final void open_rect_bracket() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:438:2: ( OPEN_RECT_BRACKET )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:438:4: OPEN_RECT_BRACKET
            {
            match(input,OPEN_RECT_BRACKET,FOLLOW_OPEN_RECT_BRACKET_in_open_rect_bracket2648); if (state.failed) return ;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:439:1: close_rect_bracket : CLOSE_RECT_BRACKET ;
    public final void close_rect_bracket() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:440:2: ( CLOSE_RECT_BRACKET )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:440:4: CLOSE_RECT_BRACKET
            {
            match(input,CLOSE_RECT_BRACKET,FOLLOW_CLOSE_RECT_BRACKET_in_close_rect_bracket2658); if (state.failed) return ;
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

    // $ANTLR start synpred2_JavaParser
    public final void synpred2_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:24:33: ( class_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:24:33: class_def
        {
        pushFollow(FOLLOW_class_def_in_synpred2_JavaParser46);
        class_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred2_JavaParser

    // $ANTLR start synpred3_JavaParser
    public final void synpred3_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:24:45: ( enum_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:24:45: enum_def
        {
        pushFollow(FOLLOW_enum_def_in_synpred3_JavaParser50);
        enum_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred3_JavaParser

    // $ANTLR start synpred4_JavaParser
    public final void synpred4_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:24:56: ( interface_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:24:56: interface_def
        {
        pushFollow(FOLLOW_interface_def_in_synpred4_JavaParser54);
        interface_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred4_JavaParser

    // $ANTLR start synpred41_JavaParser
    public final void synpred41_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:87:17: ( class_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:87:17: class_def
        {
        pushFollow(FOLLOW_class_def_in_synpred41_JavaParser451);
        class_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred41_JavaParser

    // $ANTLR start synpred42_JavaParser
    public final void synpred42_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:87:29: ( static_init )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:87:29: static_init
        {
        pushFollow(FOLLOW_static_init_in_synpred42_JavaParser455);
        static_init();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred42_JavaParser

    // $ANTLR start synpred43_JavaParser
    public final void synpred43_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:87:43: ( constructor_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:87:43: constructor_def
        {
        pushFollow(FOLLOW_constructor_def_in_synpred43_JavaParser459);
        constructor_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred43_JavaParser

    // $ANTLR start synpred44_JavaParser
    public final void synpred44_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:87:61: ( method_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:87:61: method_def
        {
        pushFollow(FOLLOW_method_def_in_synpred44_JavaParser463);
        method_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred44_JavaParser

    // $ANTLR start synpred45_JavaParser
    public final void synpred45_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:87:74: ( field_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:87:74: field_def
        {
        pushFollow(FOLLOW_field_def_in_synpred45_JavaParser467);
        field_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred45_JavaParser

    // $ANTLR start synpred47_JavaParser
    public final void synpred47_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:91:17: ( class_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:91:17: class_def
        {
        pushFollow(FOLLOW_class_def_in_synpred47_JavaParser489);
        class_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred47_JavaParser

    // $ANTLR start synpred48_JavaParser
    public final void synpred48_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:91:29: ( constructor_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:91:29: constructor_def
        {
        pushFollow(FOLLOW_constructor_def_in_synpred48_JavaParser493);
        constructor_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred48_JavaParser

    // $ANTLR start synpred49_JavaParser
    public final void synpred49_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:91:47: ( method_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:91:47: method_def
        {
        pushFollow(FOLLOW_method_def_in_synpred49_JavaParser497);
        method_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred49_JavaParser

    // $ANTLR start synpred50_JavaParser
    public final void synpred50_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:91:60: ( field_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:91:60: field_def
        {
        pushFollow(FOLLOW_field_def_in_synpred50_JavaParser501);
        field_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred50_JavaParser

    // $ANTLR start synpred51_JavaParser
    public final void synpred51_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:91:72: ( enum_content )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:91:72: enum_content
        {
        pushFollow(FOLLOW_enum_content_in_synpred51_JavaParser505);
        enum_content();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred51_JavaParser

    // $ANTLR start synpred71_JavaParser
    public final void synpred71_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:40: ( right_unary )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:40: right_unary
        {
        pushFollow(FOLLOW_right_unary_in_synpred71_JavaParser712);
        right_unary();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred71_JavaParser

    // $ANTLR start synpred72_JavaParser
    public final void synpred72_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:54: ( binary_operator value )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:54: binary_operator value
        {
        pushFollow(FOLLOW_binary_operator_in_synpred72_JavaParser716);
        binary_operator();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_value_in_synpred72_JavaParser718);
        value();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred72_JavaParser

    // $ANTLR start synpred73_JavaParser
    public final void synpred73_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:79: ( question_ value colon value )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:79: question_ value colon value
        {
        pushFollow(FOLLOW_question__in_synpred73_JavaParser723);
        question_();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_value_in_synpred73_JavaParser725);
        value();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_colon_in_synpred73_JavaParser727);
        colon();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_value_in_synpred73_JavaParser729);
        value();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred73_JavaParser

    // $ANTLR start synpred74_JavaParser
    public final void synpred74_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:9: ( ( cast )? ( left_unary )? single_value ( right_unary )? ( binary_operator value )? ( question_ value colon value )? )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:9: ( cast )? ( left_unary )? single_value ( right_unary )? ( binary_operator value )? ( question_ value colon value )?
        {
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:9: ( cast )?
        int alt140=2;
        int LA140_0 = input.LA(1);

        if ( (LA140_0==OPEN_BRACKET) ) {
            alt140=1;
        }
        switch (alt140) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: cast
                {
                pushFollow(FOLLOW_cast_in_synpred74_JavaParser704);
                cast();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:15: ( left_unary )?
        int alt141=2;
        int LA141_0 = input.LA(1);

        if ( ((LA141_0>=INC && LA141_0<=MINUS)||LA141_0==NOT) ) {
            alt141=1;
        }
        switch (alt141) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: left_unary
                {
                pushFollow(FOLLOW_left_unary_in_synpred74_JavaParser707);
                left_unary();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        pushFollow(FOLLOW_single_value_in_synpred74_JavaParser710);
        single_value();

        state._fsp--;
        if (state.failed) return ;
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:40: ( right_unary )?
        int alt142=2;
        int LA142_0 = input.LA(1);

        if ( ((LA142_0>=INC && LA142_0<=DEC)||LA142_0==NOT) ) {
            alt142=1;
        }
        switch (alt142) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: right_unary
                {
                pushFollow(FOLLOW_right_unary_in_synpred74_JavaParser712);
                right_unary();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:53: ( binary_operator value )?
        int alt143=2;
        int LA143_0 = input.LA(1);

        if ( ((LA143_0>=LE && LA143_0<=UNEQUAL)||(LA143_0>=PLUS && LA143_0<=STAR)||(LA143_0>=PERCENT && LA143_0<=BIT_AND)||(LA143_0>=LT && LA143_0<=GT)) ) {
            alt143=1;
        }
        switch (alt143) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:54: binary_operator value
                {
                pushFollow(FOLLOW_binary_operator_in_synpred74_JavaParser716);
                binary_operator();

                state._fsp--;
                if (state.failed) return ;
                pushFollow(FOLLOW_value_in_synpred74_JavaParser718);
                value();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:78: ( question_ value colon value )?
        int alt144=2;
        int LA144_0 = input.LA(1);

        if ( (LA144_0==QUESTION) ) {
            alt144=1;
        }
        switch (alt144) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:79: question_ value colon value
                {
                pushFollow(FOLLOW_question__in_synpred74_JavaParser723);
                question_();

                state._fsp--;
                if (state.failed) return ;
                pushFollow(FOLLOW_value_in_synpred74_JavaParser725);
                value();

                state._fsp--;
                if (state.failed) return ;
                pushFollow(FOLLOW_colon_in_synpred74_JavaParser727);
                colon();

                state._fsp--;
                if (state.failed) return ;
                pushFollow(FOLLOW_value_in_synpred74_JavaParser729);
                value();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred74_JavaParser

    // $ANTLR start synpred75_JavaParser
    public final void synpred75_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:38: ( binary_operator value )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:38: binary_operator value
        {
        pushFollow(FOLLOW_binary_operator_in_synpred75_JavaParser743);
        binary_operator();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_value_in_synpred75_JavaParser745);
        value();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred75_JavaParser

    // $ANTLR start synpred76_JavaParser
    public final void synpred76_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:63: ( question_ value colon value )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:63: question_ value colon value
        {
        pushFollow(FOLLOW_question__in_synpred76_JavaParser750);
        question_();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_value_in_synpred76_JavaParser752);
        value();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_colon_in_synpred76_JavaParser754);
        colon();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_value_in_synpred76_JavaParser756);
        value();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred76_JavaParser

    // $ANTLR start synpred77_JavaParser
    public final void synpred77_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:119:4: ( constant )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:119:4: constant
        {
        pushFollow(FOLLOW_constant_in_synpred77_JavaParser769);
        constant();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred77_JavaParser

    // $ANTLR start synpred78_JavaParser
    public final void synpred78_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:120:4: ( method_call )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:120:4: method_call
        {
        pushFollow(FOLLOW_method_call_in_synpred78_JavaParser774);
        method_call();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred78_JavaParser

    // $ANTLR start synpred79_JavaParser
    public final void synpred79_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:121:5: ( variable_assignment )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:121:5: variable_assignment
        {
        pushFollow(FOLLOW_variable_assignment_in_synpred79_JavaParser780);
        variable_assignment();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred79_JavaParser

    // $ANTLR start synpred80_JavaParser
    public final void synpred80_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:122:4: ( variable_name )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:122:4: variable_name
        {
        pushFollow(FOLLOW_variable_name_in_synpred80_JavaParser785);
        variable_name();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred80_JavaParser

    // $ANTLR start synpred81_JavaParser
    public final void synpred81_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:123:4: ( new_class )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:123:4: new_class
        {
        pushFollow(FOLLOW_new_class_in_synpred81_JavaParser790);
        new_class();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred81_JavaParser

    // $ANTLR start synpred82_JavaParser
    public final void synpred82_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:124:4: ( this_ )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:124:4: this_
        {
        pushFollow(FOLLOW_this__in_synpred82_JavaParser795);
        this_();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred82_JavaParser

    // $ANTLR start synpred101_JavaParser
    public final void synpred101_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:150:32: ( semicolon )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:150:32: semicolon
        {
        pushFollow(FOLLOW_semicolon_in_synpred101_JavaParser941);
        semicolon();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred101_JavaParser

    // $ANTLR start synpred102_JavaParser
    public final void synpred102_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:154:4: ( label )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:154:4: label
        {
        pushFollow(FOLLOW_label_in_synpred102_JavaParser954);
        label();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred102_JavaParser

    // $ANTLR start synpred103_JavaParser
    public final void synpred103_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:155:4: ( variable_assignment semicolon )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:155:4: variable_assignment semicolon
        {
        pushFollow(FOLLOW_variable_assignment_in_synpred103_JavaParser960);
        variable_assignment();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_semicolon_in_synpred103_JavaParser962);
        semicolon();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred103_JavaParser

    // $ANTLR start synpred104_JavaParser
    public final void synpred104_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:156:4: ( variable_def semicolon )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:156:4: variable_def semicolon
        {
        pushFollow(FOLLOW_variable_def_in_synpred104_JavaParser967);
        variable_def();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_semicolon_in_synpred104_JavaParser969);
        semicolon();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred104_JavaParser

    // $ANTLR start synpred105_JavaParser
    public final void synpred105_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:157:4: ( method_call semicolon )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:157:4: method_call semicolon
        {
        pushFollow(FOLLOW_method_call_in_synpred105_JavaParser974);
        method_call();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_semicolon_in_synpred105_JavaParser976);
        semicolon();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred105_JavaParser

    // $ANTLR start synpred119_JavaParser
    public final void synpred119_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:170:30: ( right_unary )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:170:30: right_unary
        {
        pushFollow(FOLLOW_right_unary_in_synpred119_JavaParser1053);
        right_unary();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred119_JavaParser

    // $ANTLR start synpred120_JavaParser
    public final void synpred120_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:174:4: ( label )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:174:4: label
        {
        pushFollow(FOLLOW_label_in_synpred120_JavaParser1065);
        label();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred120_JavaParser

    // $ANTLR start synpred121_JavaParser
    public final void synpred121_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:175:4: ( variable_assignment )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:175:4: variable_assignment
        {
        pushFollow(FOLLOW_variable_assignment_in_synpred121_JavaParser1070);
        variable_assignment();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred121_JavaParser

    // $ANTLR start synpred122_JavaParser
    public final void synpred122_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:176:4: ( variable_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:176:4: variable_def
        {
        pushFollow(FOLLOW_variable_def_in_synpred122_JavaParser1075);
        variable_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred122_JavaParser

    // $ANTLR start synpred123_JavaParser
    public final void synpred123_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:177:4: ( method_call )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:177:4: method_call
        {
        pushFollow(FOLLOW_method_call_in_synpred123_JavaParser1080);
        method_call();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred123_JavaParser

    // $ANTLR start synpred145_JavaParser
    public final void synpred145_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:200:11: ( for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon ( value )? semicolon ( statement_wosemicolon ( comma statement_wosemicolon )* )? CLOSE_BRACKET code )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:200:11: for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon ( value )? semicolon ( statement_wosemicolon ( comma statement_wosemicolon )* )? CLOSE_BRACKET code
        {
        pushFollow(FOLLOW_for__in_synpred145_JavaParser1207);
        for_();

        state._fsp--;
        if (state.failed) return ;
        match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_synpred145_JavaParser1209); if (state.failed) return ;
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:200:29: ( variable_def ( comma variable_def )* )?
        int alt150=2;
        int LA150_0 = input.LA(1);

        if ( (LA150_0==VOID||(LA150_0>=FINAL && LA150_0<=VOLATILE)||(LA150_0>=BOOLEAN && LA150_0<=DOUBLE)||LA150_0==AT||LA150_0==ID) ) {
            alt150=1;
        }
        switch (alt150) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:200:30: variable_def ( comma variable_def )*
                {
                pushFollow(FOLLOW_variable_def_in_synpred145_JavaParser1212);
                variable_def();

                state._fsp--;
                if (state.failed) return ;
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:200:43: ( comma variable_def )*
                loop149:
                do {
                    int alt149=2;
                    int LA149_0 = input.LA(1);

                    if ( (LA149_0==COMMA) ) {
                        alt149=1;
                    }


                    switch (alt149) {
                	case 1 :
                	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:200:44: comma variable_def
                	    {
                	    pushFollow(FOLLOW_comma_in_synpred145_JavaParser1215);
                	    comma();

                	    state._fsp--;
                	    if (state.failed) return ;
                	    pushFollow(FOLLOW_variable_def_in_synpred145_JavaParser1217);
                	    variable_def();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop149;
                    }
                } while (true);


                }
                break;

        }

        pushFollow(FOLLOW_semicolon_in_synpred145_JavaParser1223);
        semicolon();

        state._fsp--;
        if (state.failed) return ;
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:200:77: ( value )?
        int alt151=2;
        int LA151_0 = input.LA(1);

        if ( ((LA151_0>=VOID && LA151_0<=NEW)||(LA151_0>=NULL && LA151_0<=SUPER)||(LA151_0>=BOOLEAN && LA151_0<=OPEN_CURLY_BRACKET)||(LA151_0>=INC && LA151_0<=MINUS)||LA151_0==NOT||LA151_0==OPEN_BRACKET||(LA151_0>=BOOL_CONST && LA151_0<=INT_CONST)||(LA151_0>=HEX_CONST && LA151_0<=HEX_LONG_CONST)||LA151_0==FLOAT_CONST||(LA151_0>=STRING_CONST && LA151_0<=CHAR_CONST)) ) {
            alt151=1;
        }
        switch (alt151) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: value
                {
                pushFollow(FOLLOW_value_in_synpred145_JavaParser1225);
                value();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        pushFollow(FOLLOW_semicolon_in_synpred145_JavaParser1228);
        semicolon();

        state._fsp--;
        if (state.failed) return ;
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:200:94: ( statement_wosemicolon ( comma statement_wosemicolon )* )?
        int alt153=2;
        int LA153_0 = input.LA(1);

        if ( ((LA153_0>=VOID && LA153_0<=CONTINUE)||(LA153_0>=THIS && LA153_0<=IF)||LA153_0==SWITCH||LA153_0==TRY||LA153_0==THROW||(LA153_0>=FINAL && LA153_0<=SYNCHRONIZED)||(LA153_0>=BOOLEAN && LA153_0<=DOUBLE)||(LA153_0>=INC && LA153_0<=MINUS)||LA153_0==NOT||LA153_0==AT||LA153_0==ID) ) {
            alt153=1;
        }
        switch (alt153) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:200:95: statement_wosemicolon ( comma statement_wosemicolon )*
                {
                pushFollow(FOLLOW_statement_wosemicolon_in_synpred145_JavaParser1231);
                statement_wosemicolon();

                state._fsp--;
                if (state.failed) return ;
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:200:117: ( comma statement_wosemicolon )*
                loop152:
                do {
                    int alt152=2;
                    int LA152_0 = input.LA(1);

                    if ( (LA152_0==COMMA) ) {
                        alt152=1;
                    }


                    switch (alt152) {
                	case 1 :
                	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:200:118: comma statement_wosemicolon
                	    {
                	    pushFollow(FOLLOW_comma_in_synpred145_JavaParser1234);
                	    comma();

                	    state._fsp--;
                	    if (state.failed) return ;
                	    pushFollow(FOLLOW_statement_wosemicolon_in_synpred145_JavaParser1236);
                	    statement_wosemicolon();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop152;
                    }
                } while (true);


                }
                break;

        }

        match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_synpred145_JavaParser1242); if (state.failed) return ;
        pushFollow(FOLLOW_code_in_synpred145_JavaParser1244);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred145_JavaParser

    // $ANTLR start synpred147_JavaParser
    public final void synpred147_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:5: ( case_ ( constant | variable_name ) colon )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:5: case_ ( constant | variable_name ) colon
        {
        pushFollow(FOLLOW_case__in_synpred147_JavaParser1334);
        case_();

        state._fsp--;
        if (state.failed) return ;
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:12: ( constant | variable_name )
        int alt154=2;
        alt154 = dfa154.predict(input);
        switch (alt154) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:13: constant
                {
                pushFollow(FOLLOW_constant_in_synpred147_JavaParser1338);
                constant();

                state._fsp--;
                if (state.failed) return ;

                }
                break;
            case 2 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:24: variable_name
                {
                pushFollow(FOLLOW_variable_name_in_synpred147_JavaParser1342);
                variable_name();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        pushFollow(FOLLOW_colon_in_synpred147_JavaParser1345);
        colon();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred147_JavaParser

    // $ANTLR start synpred148_JavaParser
    public final void synpred148_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:48: ( code )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:48: code
        {
        pushFollow(FOLLOW_code_in_synpred148_JavaParser1350);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred148_JavaParser

    // $ANTLR start synpred149_JavaParser
    public final void synpred149_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:55: ( statement )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:55: statement
        {
        pushFollow(FOLLOW_statement_in_synpred149_JavaParser1354);
        statement();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred149_JavaParser

    // $ANTLR start synpred152_JavaParser
    public final void synpred152_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:216:20: ( code )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:216:20: code
        {
        pushFollow(FOLLOW_code_in_synpred152_JavaParser1377);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred152_JavaParser

    // $ANTLR start synpred153_JavaParser
    public final void synpred153_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:216:27: ( statement )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:216:27: statement
        {
        pushFollow(FOLLOW_statement_in_synpred153_JavaParser1381);
        statement();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred153_JavaParser

    // $ANTLR start synpred155_JavaParser
    public final void synpred155_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:221:54: ( else_ code )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:221:54: else_ code
        {
        pushFollow(FOLLOW_else__in_synpred155_JavaParser1414);
        else_();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_code_in_synpred155_JavaParser1416);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred155_JavaParser

    // $ANTLR start synpred156_JavaParser
    public final void synpred156_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:225:15: ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:225:15: catch_ OPEN_BRACKET id id CLOSE_BRACKET code
        {
        pushFollow(FOLLOW_catch__in_synpred156_JavaParser1434);
        catch_();

        state._fsp--;
        if (state.failed) return ;
        match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_synpred156_JavaParser1436); if (state.failed) return ;
        pushFollow(FOLLOW_id_in_synpred156_JavaParser1438);
        id();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_id_in_synpred156_JavaParser1440);
        id();

        state._fsp--;
        if (state.failed) return ;
        match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_synpred156_JavaParser1442); if (state.failed) return ;
        pushFollow(FOLLOW_code_in_synpred156_JavaParser1444);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred156_JavaParser

    // $ANTLR start synpred157_JavaParser
    public final void synpred157_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:225:63: ( finally_ code )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:225:63: finally_ code
        {
        pushFollow(FOLLOW_finally__in_synpred157_JavaParser1449);
        finally_();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_code_in_synpred157_JavaParser1451);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred157_JavaParser

    // $ANTLR start synpred201_JavaParser
    public final void synpred201_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:329:8: ( dot id )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:329:8: dot id
        {
        pushFollow(FOLLOW_dot_in_synpred201_JavaParser1994);
        dot();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_id_in_synpred201_JavaParser1996);
        id();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred201_JavaParser

    // $ANTLR start synpred202_JavaParser
    public final void synpred202_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:329:17: ( generic )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:329:17: generic
        {
        pushFollow(FOLLOW_generic_in_synpred202_JavaParser2000);
        generic();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred202_JavaParser

    // $ANTLR start synpred211_JavaParser
    public final void synpred211_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:349:9: ( id dot )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:349:9: id dot
        {
        pushFollow(FOLLOW_id_in_synpred211_JavaParser2107);
        id();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_dot_in_synpred211_JavaParser2109);
        dot();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred211_JavaParser

    // $ANTLR start synpred214_JavaParser
    public final void synpred214_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:403:10: ( BREAK id )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:403:10: BREAK id
        {
        match(input,BREAK,FOLLOW_BREAK_in_synpred214_JavaParser2458); if (state.failed) return ;
        pushFollow(FOLLOW_id_in_synpred214_JavaParser2460);
        id();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred214_JavaParser

    // Delegated rules

    public final boolean synpred149_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred149_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred41_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred41_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred75_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred75_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred104_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred104_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred73_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred73_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred71_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred71_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred72_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred72_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred157_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred157_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred74_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred74_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred51_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred51_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred122_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred122_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred105_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred105_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred119_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred119_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred42_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred42_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred211_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred211_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred153_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred153_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred155_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred155_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred214_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred214_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred43_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred43_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred152_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred152_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred148_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred148_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred50_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred50_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred3_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred4_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred120_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred120_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred81_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred81_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred145_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred145_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred202_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred202_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred45_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred45_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred79_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred79_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred80_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred80_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred156_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred156_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred201_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred201_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred121_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred121_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred78_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred78_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred123_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred123_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred147_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred147_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA2 dfa2 = new DFA2(this);
    protected DFA34 dfa34 = new DFA34(this);
    protected DFA36 dfa36 = new DFA36(this);
    protected DFA61 dfa61 = new DFA61(this);
    protected DFA57 dfa57 = new DFA57(this);
    protected DFA59 dfa59 = new DFA59(this);
    protected DFA62 dfa62 = new DFA62(this);
    protected DFA75 dfa75 = new DFA75(this);
    protected DFA78 dfa78 = new DFA78(this);
    protected DFA87 dfa87 = new DFA87(this);
    protected DFA89 dfa89 = new DFA89(this);
    protected DFA91 dfa91 = new DFA91(this);
    protected DFA111 dfa111 = new DFA111(this);
    protected DFA112 dfa112 = new DFA112(this);
    protected DFA154 dfa154 = new DFA154(this);
    static final String DFA2_eotS =
        "\15\uffff";
    static final String DFA2_eofS =
        "\1\1\14\uffff";
    static final String DFA2_minS =
        "\1\6\1\uffff\10\0\3\uffff";
    static final String DFA2_maxS =
        "\1\123\1\uffff\10\0\3\uffff";
    static final String DFA2_acceptS =
        "\1\uffff\1\4\10\uffff\1\1\1\2\1\3";
    static final String DFA2_specialS =
        "\2\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\3\uffff}>";
    static final String[] DFA2_transitionS = {
            "\1\12\1\14\1\13\27\uffff\1\3\1\4\1\5\2\uffff\1\10\1\6\1\7\1"+
            "\11\52\uffff\1\2",
            "",
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

    static final short[] DFA2_eot = DFA.unpackEncodedString(DFA2_eotS);
    static final short[] DFA2_eof = DFA.unpackEncodedString(DFA2_eofS);
    static final char[] DFA2_min = DFA.unpackEncodedStringToUnsignedChars(DFA2_minS);
    static final char[] DFA2_max = DFA.unpackEncodedStringToUnsignedChars(DFA2_maxS);
    static final short[] DFA2_accept = DFA.unpackEncodedString(DFA2_acceptS);
    static final short[] DFA2_special = DFA.unpackEncodedString(DFA2_specialS);
    static final short[][] DFA2_transition;

    static {
        int numStates = DFA2_transitionS.length;
        DFA2_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA2_transition[i] = DFA.unpackEncodedString(DFA2_transitionS[i]);
        }
    }

    class DFA2 extends DFA {

        public DFA2(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = DFA2_eot;
            this.eof = DFA2_eof;
            this.min = DFA2_min;
            this.max = DFA2_max;
            this.accept = DFA2_accept;
            this.special = DFA2_special;
            this.transition = DFA2_transition;
        }
        public String getDescription() {
            return "()+ loopback of 24:32: ( class_def | enum_def | interface_def )+";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA2_2 = input.LA(1);

                         
                        int index2_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_JavaParser()) ) {s = 10;}

                        else if ( (synpred3_JavaParser()) ) {s = 11;}

                        else if ( (synpred4_JavaParser()) ) {s = 12;}

                         
                        input.seek(index2_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA2_3 = input.LA(1);

                         
                        int index2_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_JavaParser()) ) {s = 10;}

                        else if ( (synpred3_JavaParser()) ) {s = 11;}

                        else if ( (synpred4_JavaParser()) ) {s = 12;}

                         
                        input.seek(index2_3);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA2_4 = input.LA(1);

                         
                        int index2_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_JavaParser()) ) {s = 10;}

                        else if ( (synpred3_JavaParser()) ) {s = 11;}

                        else if ( (synpred4_JavaParser()) ) {s = 12;}

                         
                        input.seek(index2_4);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA2_5 = input.LA(1);

                         
                        int index2_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_JavaParser()) ) {s = 10;}

                        else if ( (synpred3_JavaParser()) ) {s = 11;}

                        else if ( (synpred4_JavaParser()) ) {s = 12;}

                         
                        input.seek(index2_5);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA2_6 = input.LA(1);

                         
                        int index2_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_JavaParser()) ) {s = 10;}

                        else if ( (synpred3_JavaParser()) ) {s = 11;}

                        else if ( (synpred4_JavaParser()) ) {s = 12;}

                         
                        input.seek(index2_6);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA2_7 = input.LA(1);

                         
                        int index2_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_JavaParser()) ) {s = 10;}

                        else if ( (synpred3_JavaParser()) ) {s = 11;}

                        else if ( (synpred4_JavaParser()) ) {s = 12;}

                         
                        input.seek(index2_7);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA2_8 = input.LA(1);

                         
                        int index2_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_JavaParser()) ) {s = 10;}

                        else if ( (synpred3_JavaParser()) ) {s = 11;}

                        else if ( (synpred4_JavaParser()) ) {s = 12;}

                         
                        input.seek(index2_8);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA2_9 = input.LA(1);

                         
                        int index2_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_JavaParser()) ) {s = 10;}

                        else if ( (synpred3_JavaParser()) ) {s = 11;}

                        else if ( (synpred4_JavaParser()) ) {s = 12;}

                         
                        input.seek(index2_9);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 2, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA34_eotS =
        "\33\uffff";
    static final String DFA34_eofS =
        "\33\uffff";
    static final String DFA34_minS =
        "\1\6\1\uffff\10\0\1\uffff\14\0\4\uffff";
    static final String DFA34_maxS =
        "\1\126\1\uffff\10\0\1\uffff\14\0\4\uffff";
    static final String DFA34_acceptS =
        "\1\uffff\1\6\10\uffff\1\1\14\uffff\1\3\1\4\1\5\1\2";
    static final String DFA34_specialS =
        "\2\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\uffff\1\10\1\11\1\12"+
        "\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\4\uffff}>";
    static final String[] DFA34_transitionS = {
            "\1\12\4\uffff\1\26\24\uffff\1\3\1\4\1\5\1\14\1\15\1\10\1\6\1"+
            "\7\1\11\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\uffff\1\1"+
            "\40\uffff\1\2\2\uffff\1\13",
            "",
            "\1\uffff",
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
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
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
            return "()* loopback of 87:16: ( class_def | static_init | constructor_def | method_def | field_def )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA34_2 = input.LA(1);

                         
                        int index34_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred41_JavaParser()) ) {s = 10;}

                        else if ( (synpred43_JavaParser()) ) {s = 23;}

                        else if ( (synpred44_JavaParser()) ) {s = 24;}

                        else if ( (synpred45_JavaParser()) ) {s = 25;}

                         
                        input.seek(index34_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA34_3 = input.LA(1);

                         
                        int index34_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred41_JavaParser()) ) {s = 10;}

                        else if ( (synpred43_JavaParser()) ) {s = 23;}

                        else if ( (synpred44_JavaParser()) ) {s = 24;}

                        else if ( (synpred45_JavaParser()) ) {s = 25;}

                         
                        input.seek(index34_3);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA34_4 = input.LA(1);

                         
                        int index34_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred41_JavaParser()) ) {s = 10;}

                        else if ( (synpred43_JavaParser()) ) {s = 23;}

                        else if ( (synpred44_JavaParser()) ) {s = 24;}

                        else if ( (synpred45_JavaParser()) ) {s = 25;}

                         
                        input.seek(index34_4);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA34_5 = input.LA(1);

                         
                        int index34_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred41_JavaParser()) ) {s = 10;}

                        else if ( (synpred43_JavaParser()) ) {s = 23;}

                        else if ( (synpred44_JavaParser()) ) {s = 24;}

                        else if ( (synpred45_JavaParser()) ) {s = 25;}

                         
                        input.seek(index34_5);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA34_6 = input.LA(1);

                         
                        int index34_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred41_JavaParser()) ) {s = 10;}

                        else if ( (synpred42_JavaParser()) ) {s = 26;}

                        else if ( (synpred43_JavaParser()) ) {s = 23;}

                        else if ( (synpred44_JavaParser()) ) {s = 24;}

                        else if ( (synpred45_JavaParser()) ) {s = 25;}

                         
                        input.seek(index34_6);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA34_7 = input.LA(1);

                         
                        int index34_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred41_JavaParser()) ) {s = 10;}

                        else if ( (synpred43_JavaParser()) ) {s = 23;}

                        else if ( (synpred44_JavaParser()) ) {s = 24;}

                        else if ( (synpred45_JavaParser()) ) {s = 25;}

                         
                        input.seek(index34_7);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA34_8 = input.LA(1);

                         
                        int index34_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred41_JavaParser()) ) {s = 10;}

                        else if ( (synpred43_JavaParser()) ) {s = 23;}

                        else if ( (synpred44_JavaParser()) ) {s = 24;}

                        else if ( (synpred45_JavaParser()) ) {s = 25;}

                         
                        input.seek(index34_8);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA34_9 = input.LA(1);

                         
                        int index34_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred41_JavaParser()) ) {s = 10;}

                        else if ( (synpred43_JavaParser()) ) {s = 23;}

                        else if ( (synpred44_JavaParser()) ) {s = 24;}

                        else if ( (synpred45_JavaParser()) ) {s = 25;}

                         
                        input.seek(index34_9);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA34_11 = input.LA(1);

                         
                        int index34_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred43_JavaParser()) ) {s = 23;}

                        else if ( (synpred44_JavaParser()) ) {s = 24;}

                        else if ( (synpred45_JavaParser()) ) {s = 25;}

                         
                        input.seek(index34_11);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA34_12 = input.LA(1);

                         
                        int index34_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 24;}

                        else if ( (synpred45_JavaParser()) ) {s = 25;}

                         
                        input.seek(index34_12);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA34_13 = input.LA(1);

                         
                        int index34_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 24;}

                        else if ( (synpred45_JavaParser()) ) {s = 25;}

                         
                        input.seek(index34_13);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA34_14 = input.LA(1);

                         
                        int index34_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 24;}

                        else if ( (synpred45_JavaParser()) ) {s = 25;}

                         
                        input.seek(index34_14);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA34_15 = input.LA(1);

                         
                        int index34_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 24;}

                        else if ( (synpred45_JavaParser()) ) {s = 25;}

                         
                        input.seek(index34_15);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA34_16 = input.LA(1);

                         
                        int index34_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 24;}

                        else if ( (synpred45_JavaParser()) ) {s = 25;}

                         
                        input.seek(index34_16);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA34_17 = input.LA(1);

                         
                        int index34_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 24;}

                        else if ( (synpred45_JavaParser()) ) {s = 25;}

                         
                        input.seek(index34_17);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA34_18 = input.LA(1);

                         
                        int index34_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 24;}

                        else if ( (synpred45_JavaParser()) ) {s = 25;}

                         
                        input.seek(index34_18);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA34_19 = input.LA(1);

                         
                        int index34_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 24;}

                        else if ( (synpred45_JavaParser()) ) {s = 25;}

                         
                        input.seek(index34_19);
                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA34_20 = input.LA(1);

                         
                        int index34_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 24;}

                        else if ( (synpred45_JavaParser()) ) {s = 25;}

                         
                        input.seek(index34_20);
                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA34_21 = input.LA(1);

                         
                        int index34_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 24;}

                        else if ( (synpred45_JavaParser()) ) {s = 25;}

                         
                        input.seek(index34_21);
                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA34_22 = input.LA(1);

                         
                        int index34_22 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 24;}

                        else if ( (synpred45_JavaParser()) ) {s = 25;}

                         
                        input.seek(index34_22);
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
    static final String DFA36_eotS =
        "\33\uffff";
    static final String DFA36_eofS =
        "\33\uffff";
    static final String DFA36_minS =
        "\1\6\1\uffff\10\0\1\uffff\14\0\4\uffff";
    static final String DFA36_maxS =
        "\1\126\1\uffff\10\0\1\uffff\14\0\4\uffff";
    static final String DFA36_acceptS =
        "\1\uffff\1\6\10\uffff\1\1\14\uffff\1\2\1\3\1\4\1\5";
    static final String DFA36_specialS =
        "\2\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\uffff\1\10\1\11\1\12"+
        "\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\4\uffff}>";
    static final String[] DFA36_transitionS = {
            "\1\12\4\uffff\1\26\24\uffff\1\3\1\4\1\5\1\14\1\15\1\10\1\6\1"+
            "\7\1\11\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\uffff\1\1"+
            "\40\uffff\1\2\2\uffff\1\13",
            "",
            "\1\uffff",
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
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA36_eot = DFA.unpackEncodedString(DFA36_eotS);
    static final short[] DFA36_eof = DFA.unpackEncodedString(DFA36_eofS);
    static final char[] DFA36_min = DFA.unpackEncodedStringToUnsignedChars(DFA36_minS);
    static final char[] DFA36_max = DFA.unpackEncodedStringToUnsignedChars(DFA36_maxS);
    static final short[] DFA36_accept = DFA.unpackEncodedString(DFA36_acceptS);
    static final short[] DFA36_special = DFA.unpackEncodedString(DFA36_specialS);
    static final short[][] DFA36_transition;

    static {
        int numStates = DFA36_transitionS.length;
        DFA36_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA36_transition[i] = DFA.unpackEncodedString(DFA36_transitionS[i]);
        }
    }

    class DFA36 extends DFA {

        public DFA36(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 36;
            this.eot = DFA36_eot;
            this.eof = DFA36_eof;
            this.min = DFA36_min;
            this.max = DFA36_max;
            this.accept = DFA36_accept;
            this.special = DFA36_special;
            this.transition = DFA36_transition;
        }
        public String getDescription() {
            return "()* loopback of 91:16: ( class_def | constructor_def | method_def | field_def | enum_content )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA36_2 = input.LA(1);

                         
                        int index36_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred47_JavaParser()) ) {s = 10;}

                        else if ( (synpred48_JavaParser()) ) {s = 23;}

                        else if ( (synpred49_JavaParser()) ) {s = 24;}

                        else if ( (synpred50_JavaParser()) ) {s = 25;}

                         
                        input.seek(index36_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA36_3 = input.LA(1);

                         
                        int index36_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred47_JavaParser()) ) {s = 10;}

                        else if ( (synpred48_JavaParser()) ) {s = 23;}

                        else if ( (synpred49_JavaParser()) ) {s = 24;}

                        else if ( (synpred50_JavaParser()) ) {s = 25;}

                         
                        input.seek(index36_3);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA36_4 = input.LA(1);

                         
                        int index36_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred47_JavaParser()) ) {s = 10;}

                        else if ( (synpred48_JavaParser()) ) {s = 23;}

                        else if ( (synpred49_JavaParser()) ) {s = 24;}

                        else if ( (synpred50_JavaParser()) ) {s = 25;}

                         
                        input.seek(index36_4);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA36_5 = input.LA(1);

                         
                        int index36_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred47_JavaParser()) ) {s = 10;}

                        else if ( (synpred48_JavaParser()) ) {s = 23;}

                        else if ( (synpred49_JavaParser()) ) {s = 24;}

                        else if ( (synpred50_JavaParser()) ) {s = 25;}

                         
                        input.seek(index36_5);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA36_6 = input.LA(1);

                         
                        int index36_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred47_JavaParser()) ) {s = 10;}

                        else if ( (synpred48_JavaParser()) ) {s = 23;}

                        else if ( (synpred49_JavaParser()) ) {s = 24;}

                        else if ( (synpred50_JavaParser()) ) {s = 25;}

                         
                        input.seek(index36_6);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA36_7 = input.LA(1);

                         
                        int index36_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred47_JavaParser()) ) {s = 10;}

                        else if ( (synpred48_JavaParser()) ) {s = 23;}

                        else if ( (synpred49_JavaParser()) ) {s = 24;}

                        else if ( (synpred50_JavaParser()) ) {s = 25;}

                         
                        input.seek(index36_7);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA36_8 = input.LA(1);

                         
                        int index36_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred47_JavaParser()) ) {s = 10;}

                        else if ( (synpred48_JavaParser()) ) {s = 23;}

                        else if ( (synpred49_JavaParser()) ) {s = 24;}

                        else if ( (synpred50_JavaParser()) ) {s = 25;}

                         
                        input.seek(index36_8);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA36_9 = input.LA(1);

                         
                        int index36_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred47_JavaParser()) ) {s = 10;}

                        else if ( (synpred48_JavaParser()) ) {s = 23;}

                        else if ( (synpred49_JavaParser()) ) {s = 24;}

                        else if ( (synpred50_JavaParser()) ) {s = 25;}

                         
                        input.seek(index36_9);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA36_11 = input.LA(1);

                         
                        int index36_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred48_JavaParser()) ) {s = 23;}

                        else if ( (synpred49_JavaParser()) ) {s = 24;}

                        else if ( (synpred50_JavaParser()) ) {s = 25;}

                        else if ( (synpred51_JavaParser()) ) {s = 26;}

                         
                        input.seek(index36_11);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA36_12 = input.LA(1);

                         
                        int index36_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_JavaParser()) ) {s = 24;}

                        else if ( (synpred50_JavaParser()) ) {s = 25;}

                         
                        input.seek(index36_12);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA36_13 = input.LA(1);

                         
                        int index36_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_JavaParser()) ) {s = 24;}

                        else if ( (synpred50_JavaParser()) ) {s = 25;}

                         
                        input.seek(index36_13);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA36_14 = input.LA(1);

                         
                        int index36_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_JavaParser()) ) {s = 24;}

                        else if ( (synpred50_JavaParser()) ) {s = 25;}

                         
                        input.seek(index36_14);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA36_15 = input.LA(1);

                         
                        int index36_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_JavaParser()) ) {s = 24;}

                        else if ( (synpred50_JavaParser()) ) {s = 25;}

                         
                        input.seek(index36_15);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA36_16 = input.LA(1);

                         
                        int index36_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_JavaParser()) ) {s = 24;}

                        else if ( (synpred50_JavaParser()) ) {s = 25;}

                         
                        input.seek(index36_16);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA36_17 = input.LA(1);

                         
                        int index36_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_JavaParser()) ) {s = 24;}

                        else if ( (synpred50_JavaParser()) ) {s = 25;}

                         
                        input.seek(index36_17);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA36_18 = input.LA(1);

                         
                        int index36_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_JavaParser()) ) {s = 24;}

                        else if ( (synpred50_JavaParser()) ) {s = 25;}

                         
                        input.seek(index36_18);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA36_19 = input.LA(1);

                         
                        int index36_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_JavaParser()) ) {s = 24;}

                        else if ( (synpred50_JavaParser()) ) {s = 25;}

                         
                        input.seek(index36_19);
                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA36_20 = input.LA(1);

                         
                        int index36_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_JavaParser()) ) {s = 24;}

                        else if ( (synpred50_JavaParser()) ) {s = 25;}

                         
                        input.seek(index36_20);
                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA36_21 = input.LA(1);

                         
                        int index36_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_JavaParser()) ) {s = 24;}

                        else if ( (synpred50_JavaParser()) ) {s = 25;}

                         
                        input.seek(index36_21);
                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA36_22 = input.LA(1);

                         
                        int index36_22 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_JavaParser()) ) {s = 24;}

                        else if ( (synpred50_JavaParser()) ) {s = 25;}

                         
                        input.seek(index36_22);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 36, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA61_eotS =
        "\37\uffff";
    static final String DFA61_eofS =
        "\37\uffff";
    static final String DFA61_minS =
        "\1\13\1\0\35\uffff";
    static final String DFA61_maxS =
        "\1\143\1\0\35\uffff";
    static final String DFA61_acceptS =
        "\2\uffff\1\1\33\uffff\1\2";
    static final String DFA61_specialS =
        "\1\uffff\1\0\35\uffff}>";
    static final String[] DFA61_transitionS = {
            "\2\2\3\uffff\3\2\26\uffff\11\2\12\uffff\4\2\10\uffff\1\2\6\uffff"+
            "\1\1\5\uffff\3\2\1\uffff\3\2\1\uffff\1\2\4\uffff\2\2",
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
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA61_eot = DFA.unpackEncodedString(DFA61_eotS);
    static final short[] DFA61_eof = DFA.unpackEncodedString(DFA61_eofS);
    static final char[] DFA61_min = DFA.unpackEncodedStringToUnsignedChars(DFA61_minS);
    static final char[] DFA61_max = DFA.unpackEncodedStringToUnsignedChars(DFA61_maxS);
    static final short[] DFA61_accept = DFA.unpackEncodedString(DFA61_acceptS);
    static final short[] DFA61_special = DFA.unpackEncodedString(DFA61_specialS);
    static final short[][] DFA61_transition;

    static {
        int numStates = DFA61_transitionS.length;
        DFA61_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA61_transition[i] = DFA.unpackEncodedString(DFA61_transitionS[i]);
        }
    }

    class DFA61 extends DFA {

        public DFA61(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 61;
            this.eot = DFA61_eot;
            this.eof = DFA61_eof;
            this.min = DFA61_min;
            this.max = DFA61_max;
            this.accept = DFA61_accept;
            this.special = DFA61_special;
            this.transition = DFA61_transition;
        }
        public String getDescription() {
            return "114:1: value : ( ( cast )? ( left_unary )? single_value ( right_unary )? ( binary_operator value )? ( question_ value colon value )? | open_bracket value close_bracket ( binary_operator value )? ( question_ value colon value )? );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA61_1 = input.LA(1);

                         
                        int index61_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred74_JavaParser()) ) {s = 2;}

                        else if ( (true) ) {s = 30;}

                         
                        input.seek(index61_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 61, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA57_eotS =
        "\22\uffff";
    static final String DFA57_eofS =
        "\1\20\21\uffff";
    static final String DFA57_minS =
        "\1\62\17\0\2\uffff";
    static final String DFA57_maxS =
        "\1\122\17\0\2\uffff";
    static final String DFA57_acceptS =
        "\20\uffff\1\2\1\1";
    static final String DFA57_specialS =
        "\1\uffff\1\0\1\15\1\7\1\5\1\6\1\10\1\3\1\11\1\4\1\2\1\1\1\14\1\13"+
        "\1\16\1\12\2\uffff}>";
    static final String[] DFA57_transitionS = {
            "\1\20\1\12\1\13\1\6\1\7\5\uffff\2\20\1\1\1\2\1\4\1\3\1\20\1"+
            "\5\1\14\1\15\1\16\1\17\1\20\1\uffff\1\20\1\10\1\11\1\uffff\1"+
            "\20\1\uffff\3\20",
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
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            ""
    };

    static final short[] DFA57_eot = DFA.unpackEncodedString(DFA57_eotS);
    static final short[] DFA57_eof = DFA.unpackEncodedString(DFA57_eofS);
    static final char[] DFA57_min = DFA.unpackEncodedStringToUnsignedChars(DFA57_minS);
    static final char[] DFA57_max = DFA.unpackEncodedStringToUnsignedChars(DFA57_maxS);
    static final short[] DFA57_accept = DFA.unpackEncodedString(DFA57_acceptS);
    static final short[] DFA57_special = DFA.unpackEncodedString(DFA57_specialS);
    static final short[][] DFA57_transition;

    static {
        int numStates = DFA57_transitionS.length;
        DFA57_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA57_transition[i] = DFA.unpackEncodedString(DFA57_transitionS[i]);
        }
    }

    class DFA57 extends DFA {

        public DFA57(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 57;
            this.eot = DFA57_eot;
            this.eof = DFA57_eof;
            this.min = DFA57_min;
            this.max = DFA57_max;
            this.accept = DFA57_accept;
            this.special = DFA57_special;
            this.transition = DFA57_transition;
        }
        public String getDescription() {
            return "114:53: ( binary_operator value )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA57_1 = input.LA(1);

                         
                        int index57_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index57_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA57_11 = input.LA(1);

                         
                        int index57_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index57_11);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA57_10 = input.LA(1);

                         
                        int index57_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index57_10);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA57_7 = input.LA(1);

                         
                        int index57_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index57_7);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA57_9 = input.LA(1);

                         
                        int index57_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index57_9);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA57_4 = input.LA(1);

                         
                        int index57_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index57_4);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA57_5 = input.LA(1);

                         
                        int index57_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index57_5);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA57_3 = input.LA(1);

                         
                        int index57_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index57_3);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA57_6 = input.LA(1);

                         
                        int index57_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index57_6);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA57_8 = input.LA(1);

                         
                        int index57_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index57_8);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA57_15 = input.LA(1);

                         
                        int index57_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index57_15);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA57_13 = input.LA(1);

                         
                        int index57_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index57_13);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA57_12 = input.LA(1);

                         
                        int index57_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index57_12);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA57_2 = input.LA(1);

                         
                        int index57_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index57_2);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA57_14 = input.LA(1);

                         
                        int index57_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred72_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index57_14);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 57, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA59_eotS =
        "\34\uffff";
    static final String DFA59_eofS =
        "\1\20\33\uffff";
    static final String DFA59_minS =
        "\1\62\17\0\14\uffff";
    static final String DFA59_maxS =
        "\1\122\17\0\14\uffff";
    static final String DFA59_acceptS =
        "\20\uffff\1\2\12\uffff\1\1";
    static final String DFA59_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
        "\1\15\1\16\14\uffff}>";
    static final String[] DFA59_transitionS = {
            "\1\20\1\12\1\13\1\6\1\7\5\uffff\2\20\1\1\1\2\1\4\1\3\1\20\1"+
            "\5\1\14\1\15\1\16\1\17\1\20\1\uffff\1\20\1\10\1\11\1\uffff\1"+
            "\20\1\uffff\3\20",
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
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
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
            ""
    };

    static final short[] DFA59_eot = DFA.unpackEncodedString(DFA59_eotS);
    static final short[] DFA59_eof = DFA.unpackEncodedString(DFA59_eofS);
    static final char[] DFA59_min = DFA.unpackEncodedStringToUnsignedChars(DFA59_minS);
    static final char[] DFA59_max = DFA.unpackEncodedStringToUnsignedChars(DFA59_maxS);
    static final short[] DFA59_accept = DFA.unpackEncodedString(DFA59_acceptS);
    static final short[] DFA59_special = DFA.unpackEncodedString(DFA59_specialS);
    static final short[][] DFA59_transition;

    static {
        int numStates = DFA59_transitionS.length;
        DFA59_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA59_transition[i] = DFA.unpackEncodedString(DFA59_transitionS[i]);
        }
    }

    class DFA59 extends DFA {

        public DFA59(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 59;
            this.eot = DFA59_eot;
            this.eof = DFA59_eof;
            this.min = DFA59_min;
            this.max = DFA59_max;
            this.accept = DFA59_accept;
            this.special = DFA59_special;
            this.transition = DFA59_transition;
        }
        public String getDescription() {
            return "115:37: ( binary_operator value )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA59_1 = input.LA(1);

                         
                        int index59_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred75_JavaParser()) ) {s = 27;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index59_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA59_2 = input.LA(1);

                         
                        int index59_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred75_JavaParser()) ) {s = 27;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index59_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA59_3 = input.LA(1);

                         
                        int index59_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred75_JavaParser()) ) {s = 27;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index59_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA59_4 = input.LA(1);

                         
                        int index59_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred75_JavaParser()) ) {s = 27;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index59_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA59_5 = input.LA(1);

                         
                        int index59_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred75_JavaParser()) ) {s = 27;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index59_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA59_6 = input.LA(1);

                         
                        int index59_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred75_JavaParser()) ) {s = 27;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index59_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA59_7 = input.LA(1);

                         
                        int index59_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred75_JavaParser()) ) {s = 27;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index59_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA59_8 = input.LA(1);

                         
                        int index59_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred75_JavaParser()) ) {s = 27;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index59_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA59_9 = input.LA(1);

                         
                        int index59_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred75_JavaParser()) ) {s = 27;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index59_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA59_10 = input.LA(1);

                         
                        int index59_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred75_JavaParser()) ) {s = 27;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index59_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA59_11 = input.LA(1);

                         
                        int index59_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred75_JavaParser()) ) {s = 27;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index59_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA59_12 = input.LA(1);

                         
                        int index59_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred75_JavaParser()) ) {s = 27;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index59_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA59_13 = input.LA(1);

                         
                        int index59_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred75_JavaParser()) ) {s = 27;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index59_13);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA59_14 = input.LA(1);

                         
                        int index59_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred75_JavaParser()) ) {s = 27;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index59_14);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA59_15 = input.LA(1);

                         
                        int index59_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred75_JavaParser()) ) {s = 27;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index59_15);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 59, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA62_eotS =
        "\36\uffff";
    static final String DFA62_eofS =
        "\36\uffff";
    static final String DFA62_minS =
        "\1\13\1\0\23\uffff\3\0\6\uffff";
    static final String DFA62_maxS =
        "\1\143\1\0\23\uffff\3\0\6\uffff";
    static final String DFA62_acceptS =
        "\2\uffff\1\1\25\uffff\1\2\1\3\1\4\1\7\1\6\1\5";
    static final String DFA62_specialS =
        "\1\uffff\1\0\23\uffff\1\1\1\2\1\3\6\uffff}>";
    static final String[] DFA62_transitionS = {
            "\1\2\1\27\3\uffff\1\2\1\26\1\25\26\uffff\11\2\43\uffff\1\2\1"+
            "\1\1\2\1\uffff\3\2\1\uffff\1\2\4\uffff\2\2",
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
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA62_eot = DFA.unpackEncodedString(DFA62_eotS);
    static final short[] DFA62_eof = DFA.unpackEncodedString(DFA62_eofS);
    static final char[] DFA62_min = DFA.unpackEncodedStringToUnsignedChars(DFA62_minS);
    static final char[] DFA62_max = DFA.unpackEncodedStringToUnsignedChars(DFA62_maxS);
    static final short[] DFA62_accept = DFA.unpackEncodedString(DFA62_acceptS);
    static final short[] DFA62_special = DFA.unpackEncodedString(DFA62_specialS);
    static final short[][] DFA62_transition;

    static {
        int numStates = DFA62_transitionS.length;
        DFA62_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA62_transition[i] = DFA.unpackEncodedString(DFA62_transitionS[i]);
        }
    }

    class DFA62 extends DFA {

        public DFA62(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 62;
            this.eot = DFA62_eot;
            this.eof = DFA62_eof;
            this.min = DFA62_min;
            this.max = DFA62_max;
            this.accept = DFA62_accept;
            this.special = DFA62_special;
            this.transition = DFA62_transition;
        }
        public String getDescription() {
            return "118:1: single_value : ( constant | method_call | variable_assignment | variable_name | new_class | this_ | super_ );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA62_1 = input.LA(1);

                         
                        int index62_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred77_JavaParser()) ) {s = 2;}

                        else if ( (synpred78_JavaParser()) ) {s = 24;}

                        else if ( (synpred79_JavaParser()) ) {s = 25;}

                        else if ( (synpred80_JavaParser()) ) {s = 26;}

                         
                        input.seek(index62_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA62_21 = input.LA(1);

                         
                        int index62_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred78_JavaParser()) ) {s = 24;}

                        else if ( (synpred79_JavaParser()) ) {s = 25;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index62_21);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA62_22 = input.LA(1);

                         
                        int index62_22 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred78_JavaParser()) ) {s = 24;}

                        else if ( (synpred79_JavaParser()) ) {s = 25;}

                        else if ( (synpred82_JavaParser()) ) {s = 28;}

                         
                        input.seek(index62_22);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA62_23 = input.LA(1);

                         
                        int index62_23 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred78_JavaParser()) ) {s = 24;}

                        else if ( (synpred81_JavaParser()) ) {s = 29;}

                         
                        input.seek(index62_23);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 62, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA75_eotS =
        "\44\uffff";
    static final String DFA75_eofS =
        "\44\uffff";
    static final String DFA75_minS =
        "\1\13\3\0\40\uffff";
    static final String DFA75_maxS =
        "\1\126\3\0\40\uffff";
    static final String DFA75_acceptS =
        "\4\uffff\1\3\13\uffff\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21\4\uffff\1\1\1\2";
    static final String DFA75_specialS =
        "\1\uffff\1\0\1\1\1\2\40\uffff}>";
    static final String[] DFA75_transitionS = {
            "\1\4\1\20\1\22\1\24\1\23\1\uffff\1\2\1\3\1\25\1\27\1\26\1\31"+
            "\1\uffff\1\30\2\uffff\1\32\2\uffff\1\34\4\uffff\2\4\1\33\3\uffff"+
            "\10\4\13\uffff\4\35\10\uffff\1\35\11\uffff\1\21\1\4\2\uffff"+
            "\1\1",
            "\1\uffff",
            "\1\uffff",
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

    static final short[] DFA75_eot = DFA.unpackEncodedString(DFA75_eotS);
    static final short[] DFA75_eof = DFA.unpackEncodedString(DFA75_eofS);
    static final char[] DFA75_min = DFA.unpackEncodedStringToUnsignedChars(DFA75_minS);
    static final char[] DFA75_max = DFA.unpackEncodedStringToUnsignedChars(DFA75_maxS);
    static final short[] DFA75_accept = DFA.unpackEncodedString(DFA75_acceptS);
    static final short[] DFA75_special = DFA.unpackEncodedString(DFA75_specialS);
    static final short[][] DFA75_transition;

    static {
        int numStates = DFA75_transitionS.length;
        DFA75_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA75_transition[i] = DFA.unpackEncodedString(DFA75_transitionS[i]);
        }
    }

    class DFA75 extends DFA {

        public DFA75(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 75;
            this.eot = DFA75_eot;
            this.eof = DFA75_eof;
            this.min = DFA75_min;
            this.max = DFA75_max;
            this.accept = DFA75_accept;
            this.special = DFA75_special;
            this.transition = DFA75_transition;
        }
        public String getDescription() {
            return "153:1: statement : ( label | variable_assignment semicolon | variable_def semicolon | method_call semicolon | semicolon | return_statement | continue_ | break_ | for_loop | while_loop | do_loop semicolon | switch_case | if_else | try_catch | synchronized_block | throw_ value semicolon | ( left_unary )? variable_name ( right_unary )? );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA75_1 = input.LA(1);

                         
                        int index75_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred102_JavaParser()) ) {s = 34;}

                        else if ( (synpred103_JavaParser()) ) {s = 35;}

                        else if ( (synpred104_JavaParser()) ) {s = 4;}

                        else if ( (synpred105_JavaParser()) ) {s = 16;}

                        else if ( (true) ) {s = 29;}

                         
                        input.seek(index75_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA75_2 = input.LA(1);

                         
                        int index75_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_JavaParser()) ) {s = 35;}

                        else if ( (synpred105_JavaParser()) ) {s = 16;}

                         
                        input.seek(index75_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA75_3 = input.LA(1);

                         
                        int index75_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_JavaParser()) ) {s = 35;}

                        else if ( (synpred105_JavaParser()) ) {s = 16;}

                         
                        input.seek(index75_3);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 75, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA78_eotS =
        "\43\uffff";
    static final String DFA78_eofS =
        "\43\uffff";
    static final String DFA78_minS =
        "\1\13\3\0\37\uffff";
    static final String DFA78_maxS =
        "\1\126\3\0\37\uffff";
    static final String DFA78_acceptS =
        "\4\uffff\1\3\13\uffff\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\4\uffff\1\1\1\2";
    static final String DFA78_specialS =
        "\1\uffff\1\0\1\1\1\2\37\uffff}>";
    static final String[] DFA78_transitionS = {
            "\1\4\1\20\1\21\1\23\1\22\1\uffff\1\2\1\3\1\24\1\26\1\25\1\30"+
            "\1\uffff\1\27\2\uffff\1\31\2\uffff\1\33\4\uffff\2\4\1\32\3\uffff"+
            "\10\4\13\uffff\4\34\10\uffff\1\34\12\uffff\1\4\2\uffff\1\1",
            "\1\uffff",
            "\1\uffff",
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

    static final short[] DFA78_eot = DFA.unpackEncodedString(DFA78_eotS);
    static final short[] DFA78_eof = DFA.unpackEncodedString(DFA78_eofS);
    static final char[] DFA78_min = DFA.unpackEncodedStringToUnsignedChars(DFA78_minS);
    static final char[] DFA78_max = DFA.unpackEncodedStringToUnsignedChars(DFA78_maxS);
    static final short[] DFA78_accept = DFA.unpackEncodedString(DFA78_acceptS);
    static final short[] DFA78_special = DFA.unpackEncodedString(DFA78_specialS);
    static final short[][] DFA78_transition;

    static {
        int numStates = DFA78_transitionS.length;
        DFA78_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA78_transition[i] = DFA.unpackEncodedString(DFA78_transitionS[i]);
        }
    }

    class DFA78 extends DFA {

        public DFA78(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 78;
            this.eot = DFA78_eot;
            this.eof = DFA78_eof;
            this.min = DFA78_min;
            this.max = DFA78_max;
            this.accept = DFA78_accept;
            this.special = DFA78_special;
            this.transition = DFA78_transition;
        }
        public String getDescription() {
            return "173:1: statement_wosemicolon : ( label | variable_assignment | variable_def | method_call | return_statement | continue_ | break_ | for_loop | while_loop | do_loop | switch_case | if_else | try_catch | synchronized_block | throw_ value semicolon | ( left_unary )? variable_name ( right_unary )? );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA78_1 = input.LA(1);

                         
                        int index78_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred120_JavaParser()) ) {s = 33;}

                        else if ( (synpred121_JavaParser()) ) {s = 34;}

                        else if ( (synpred122_JavaParser()) ) {s = 4;}

                        else if ( (synpred123_JavaParser()) ) {s = 16;}

                        else if ( (true) ) {s = 28;}

                         
                        input.seek(index78_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA78_2 = input.LA(1);

                         
                        int index78_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred121_JavaParser()) ) {s = 34;}

                        else if ( (synpred123_JavaParser()) ) {s = 16;}

                         
                        input.seek(index78_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA78_3 = input.LA(1);

                         
                        int index78_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred121_JavaParser()) ) {s = 34;}

                        else if ( (synpred123_JavaParser()) ) {s = 16;}

                         
                        input.seek(index78_3);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 78, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA87_eotS =
        "\6\uffff";
    static final String DFA87_eofS =
        "\6\uffff";
    static final String DFA87_minS =
        "\1\13\1\111\2\uffff\1\6\1\111";
    static final String DFA87_maxS =
        "\1\143\1\121\2\uffff\1\126\1\121";
    static final String DFA87_acceptS =
        "\2\uffff\1\1\1\2\2\uffff";
    static final String DFA87_specialS =
        "\6\uffff}>";
    static final String[] DFA87_transitionS = {
            "\1\2\4\uffff\1\2\30\uffff\11\2\43\uffff\1\2\1\1\1\2\1\uffff"+
            "\3\2\1\uffff\1\2\4\uffff\2\2",
            "\1\4\1\uffff\1\2\1\uffff\1\3\3\uffff\1\3",
            "",
            "",
            "\1\2\117\uffff\1\5",
            "\1\4\1\uffff\1\2\1\uffff\1\3\3\uffff\1\3"
    };

    static final short[] DFA87_eot = DFA.unpackEncodedString(DFA87_eotS);
    static final short[] DFA87_eof = DFA.unpackEncodedString(DFA87_eofS);
    static final char[] DFA87_min = DFA.unpackEncodedStringToUnsignedChars(DFA87_minS);
    static final char[] DFA87_max = DFA.unpackEncodedStringToUnsignedChars(DFA87_maxS);
    static final short[] DFA87_accept = DFA.unpackEncodedString(DFA87_acceptS);
    static final short[] DFA87_special = DFA.unpackEncodedString(DFA87_specialS);
    static final short[][] DFA87_transition;

    static {
        int numStates = DFA87_transitionS.length;
        DFA87_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA87_transition[i] = DFA.unpackEncodedString(DFA87_transitionS[i]);
        }
    }

    class DFA87 extends DFA {

        public DFA87(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 87;
            this.eot = DFA87_eot;
            this.eof = DFA87_eof;
            this.min = DFA87_min;
            this.max = DFA87_max;
            this.accept = DFA87_accept;
            this.special = DFA87_special;
            this.transition = DFA87_transition;
        }
        public String getDescription() {
            return "214:12: ( constant | variable_name )";
        }
    }
    static final String DFA89_eotS =
        "\47\uffff";
    static final String DFA89_eofS =
        "\47\uffff";
    static final String DFA89_minS =
        "\1\13\1\0\3\uffff\40\0\2\uffff";
    static final String DFA89_maxS =
        "\1\126\1\0\3\uffff\40\0\2\uffff";
    static final String DFA89_acceptS =
        "\2\uffff\1\3\42\uffff\1\1\1\2";
    static final String DFA89_specialS =
        "\1\uffff\1\0\3\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12"+
        "\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27"+
        "\1\30\1\31\1\32\1\33\1\34\1\35\1\36\1\37\1\40\2\uffff}>";
    static final String[] DFA89_transitionS = {
            "\1\23\1\24\1\26\1\1\1\27\1\uffff\1\6\1\7\1\30\1\32\1\31\1\34"+
            "\1\uffff\1\33\2\2\1\35\2\uffff\1\37\4\uffff\1\11\1\12\1\36\3"+
            "\uffff\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\45\1\2\11\uffff"+
            "\1\40\1\41\1\44\1\43\10\uffff\1\42\11\uffff\1\25\1\10\2\uffff"+
            "\1\5",
            "\1\uffff",
            "",
            "",
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
            "\1\uffff",
            "\1\uffff",
            "",
            ""
    };

    static final short[] DFA89_eot = DFA.unpackEncodedString(DFA89_eotS);
    static final short[] DFA89_eof = DFA.unpackEncodedString(DFA89_eofS);
    static final char[] DFA89_min = DFA.unpackEncodedStringToUnsignedChars(DFA89_minS);
    static final char[] DFA89_max = DFA.unpackEncodedStringToUnsignedChars(DFA89_maxS);
    static final short[] DFA89_accept = DFA.unpackEncodedString(DFA89_acceptS);
    static final short[] DFA89_special = DFA.unpackEncodedString(DFA89_specialS);
    static final short[][] DFA89_transition;

    static {
        int numStates = DFA89_transitionS.length;
        DFA89_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA89_transition[i] = DFA.unpackEncodedString(DFA89_transitionS[i]);
        }
    }

    class DFA89 extends DFA {

        public DFA89(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 89;
            this.eot = DFA89_eot;
            this.eof = DFA89_eof;
            this.min = DFA89_min;
            this.max = DFA89_max;
            this.accept = DFA89_accept;
            this.special = DFA89_special;
            this.transition = DFA89_transition;
        }
        public String getDescription() {
            return "()* loopback of 214:47: ( code | statement )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA89_1 = input.LA(1);

                         
                        int index89_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index89_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA89_5 = input.LA(1);

                         
                        int index89_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_5);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA89_6 = input.LA(1);

                         
                        int index89_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_6);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA89_7 = input.LA(1);

                         
                        int index89_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_7);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA89_8 = input.LA(1);

                         
                        int index89_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_8);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA89_9 = input.LA(1);

                         
                        int index89_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_9);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA89_10 = input.LA(1);

                         
                        int index89_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_10);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA89_11 = input.LA(1);

                         
                        int index89_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_11);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA89_12 = input.LA(1);

                         
                        int index89_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_12);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA89_13 = input.LA(1);

                         
                        int index89_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_13);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA89_14 = input.LA(1);

                         
                        int index89_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_14);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA89_15 = input.LA(1);

                         
                        int index89_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_15);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA89_16 = input.LA(1);

                         
                        int index89_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_16);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA89_17 = input.LA(1);

                         
                        int index89_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_17);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA89_18 = input.LA(1);

                         
                        int index89_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_18);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA89_19 = input.LA(1);

                         
                        int index89_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_19);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA89_20 = input.LA(1);

                         
                        int index89_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_20);
                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA89_21 = input.LA(1);

                         
                        int index89_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_21);
                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA89_22 = input.LA(1);

                         
                        int index89_22 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_22);
                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA89_23 = input.LA(1);

                         
                        int index89_23 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_23);
                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA89_24 = input.LA(1);

                         
                        int index89_24 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_24);
                        if ( s>=0 ) return s;
                        break;
                    case 21 : 
                        int LA89_25 = input.LA(1);

                         
                        int index89_25 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_25);
                        if ( s>=0 ) return s;
                        break;
                    case 22 : 
                        int LA89_26 = input.LA(1);

                         
                        int index89_26 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_26);
                        if ( s>=0 ) return s;
                        break;
                    case 23 : 
                        int LA89_27 = input.LA(1);

                         
                        int index89_27 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_27);
                        if ( s>=0 ) return s;
                        break;
                    case 24 : 
                        int LA89_28 = input.LA(1);

                         
                        int index89_28 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_28);
                        if ( s>=0 ) return s;
                        break;
                    case 25 : 
                        int LA89_29 = input.LA(1);

                         
                        int index89_29 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_29);
                        if ( s>=0 ) return s;
                        break;
                    case 26 : 
                        int LA89_30 = input.LA(1);

                         
                        int index89_30 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_30);
                        if ( s>=0 ) return s;
                        break;
                    case 27 : 
                        int LA89_31 = input.LA(1);

                         
                        int index89_31 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_31);
                        if ( s>=0 ) return s;
                        break;
                    case 28 : 
                        int LA89_32 = input.LA(1);

                         
                        int index89_32 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_32);
                        if ( s>=0 ) return s;
                        break;
                    case 29 : 
                        int LA89_33 = input.LA(1);

                         
                        int index89_33 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_33);
                        if ( s>=0 ) return s;
                        break;
                    case 30 : 
                        int LA89_34 = input.LA(1);

                         
                        int index89_34 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_34);
                        if ( s>=0 ) return s;
                        break;
                    case 31 : 
                        int LA89_35 = input.LA(1);

                         
                        int index89_35 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_35);
                        if ( s>=0 ) return s;
                        break;
                    case 32 : 
                        int LA89_36 = input.LA(1);

                         
                        int index89_36 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_JavaParser()) ) {s = 37;}

                        else if ( (synpred149_JavaParser()) ) {s = 38;}

                         
                        input.seek(index89_36);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 89, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA91_eotS =
        "\47\uffff";
    static final String DFA91_eofS =
        "\47\uffff";
    static final String DFA91_minS =
        "\1\13\3\uffff\41\0\2\uffff";
    static final String DFA91_maxS =
        "\1\126\3\uffff\41\0\2\uffff";
    static final String DFA91_acceptS =
        "\1\uffff\1\3\43\uffff\1\1\1\2";
    static final String DFA91_specialS =
        "\4\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
        "\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
        "\1\32\1\33\1\34\1\35\1\36\1\37\1\40\2\uffff}>";
    static final String[] DFA91_transitionS = {
            "\1\22\1\23\1\25\1\27\1\26\1\uffff\1\5\1\6\1\30\1\32\1\31\1\34"+
            "\1\uffff\1\33\2\1\1\35\2\uffff\1\37\4\uffff\1\10\1\11\1\36\3"+
            "\uffff\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\45\1\1\11\uffff"+
            "\1\40\1\41\1\44\1\43\10\uffff\1\42\11\uffff\1\24\1\7\2\uffff"+
            "\1\4",
            "",
            "",
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
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            ""
    };

    static final short[] DFA91_eot = DFA.unpackEncodedString(DFA91_eotS);
    static final short[] DFA91_eof = DFA.unpackEncodedString(DFA91_eofS);
    static final char[] DFA91_min = DFA.unpackEncodedStringToUnsignedChars(DFA91_minS);
    static final char[] DFA91_max = DFA.unpackEncodedStringToUnsignedChars(DFA91_maxS);
    static final short[] DFA91_accept = DFA.unpackEncodedString(DFA91_acceptS);
    static final short[] DFA91_special = DFA.unpackEncodedString(DFA91_specialS);
    static final short[][] DFA91_transition;

    static {
        int numStates = DFA91_transitionS.length;
        DFA91_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA91_transition[i] = DFA.unpackEncodedString(DFA91_transitionS[i]);
        }
    }

    class DFA91 extends DFA {

        public DFA91(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 91;
            this.eot = DFA91_eot;
            this.eof = DFA91_eof;
            this.min = DFA91_min;
            this.max = DFA91_max;
            this.accept = DFA91_accept;
            this.special = DFA91_special;
            this.transition = DFA91_transition;
        }
        public String getDescription() {
            return "()* loopback of 216:19: ( code | statement )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA91_4 = input.LA(1);

                         
                        int index91_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_4);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA91_5 = input.LA(1);

                         
                        int index91_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_5);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA91_6 = input.LA(1);

                         
                        int index91_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_6);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA91_7 = input.LA(1);

                         
                        int index91_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_7);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA91_8 = input.LA(1);

                         
                        int index91_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_8);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA91_9 = input.LA(1);

                         
                        int index91_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_9);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA91_10 = input.LA(1);

                         
                        int index91_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_10);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA91_11 = input.LA(1);

                         
                        int index91_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_11);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA91_12 = input.LA(1);

                         
                        int index91_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_12);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA91_13 = input.LA(1);

                         
                        int index91_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_13);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA91_14 = input.LA(1);

                         
                        int index91_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_14);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA91_15 = input.LA(1);

                         
                        int index91_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_15);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA91_16 = input.LA(1);

                         
                        int index91_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_16);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA91_17 = input.LA(1);

                         
                        int index91_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_17);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA91_18 = input.LA(1);

                         
                        int index91_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_18);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA91_19 = input.LA(1);

                         
                        int index91_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_19);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA91_20 = input.LA(1);

                         
                        int index91_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_20);
                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA91_21 = input.LA(1);

                         
                        int index91_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_21);
                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA91_22 = input.LA(1);

                         
                        int index91_22 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_22);
                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA91_23 = input.LA(1);

                         
                        int index91_23 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_23);
                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA91_24 = input.LA(1);

                         
                        int index91_24 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_24);
                        if ( s>=0 ) return s;
                        break;
                    case 21 : 
                        int LA91_25 = input.LA(1);

                         
                        int index91_25 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_25);
                        if ( s>=0 ) return s;
                        break;
                    case 22 : 
                        int LA91_26 = input.LA(1);

                         
                        int index91_26 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_26);
                        if ( s>=0 ) return s;
                        break;
                    case 23 : 
                        int LA91_27 = input.LA(1);

                         
                        int index91_27 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_27);
                        if ( s>=0 ) return s;
                        break;
                    case 24 : 
                        int LA91_28 = input.LA(1);

                         
                        int index91_28 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_28);
                        if ( s>=0 ) return s;
                        break;
                    case 25 : 
                        int LA91_29 = input.LA(1);

                         
                        int index91_29 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_29);
                        if ( s>=0 ) return s;
                        break;
                    case 26 : 
                        int LA91_30 = input.LA(1);

                         
                        int index91_30 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_30);
                        if ( s>=0 ) return s;
                        break;
                    case 27 : 
                        int LA91_31 = input.LA(1);

                         
                        int index91_31 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_31);
                        if ( s>=0 ) return s;
                        break;
                    case 28 : 
                        int LA91_32 = input.LA(1);

                         
                        int index91_32 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_32);
                        if ( s>=0 ) return s;
                        break;
                    case 29 : 
                        int LA91_33 = input.LA(1);

                         
                        int index91_33 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_33);
                        if ( s>=0 ) return s;
                        break;
                    case 30 : 
                        int LA91_34 = input.LA(1);

                         
                        int index91_34 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_34);
                        if ( s>=0 ) return s;
                        break;
                    case 31 : 
                        int LA91_35 = input.LA(1);

                         
                        int index91_35 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_35);
                        if ( s>=0 ) return s;
                        break;
                    case 32 : 
                        int LA91_36 = input.LA(1);

                         
                        int index91_36 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred152_JavaParser()) ) {s = 37;}

                        else if ( (synpred153_JavaParser()) ) {s = 38;}

                         
                        input.seek(index91_36);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 91, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA111_eotS =
        "\74\uffff";
    static final String DFA111_eofS =
        "\1\1\73\uffff";
    static final String DFA111_minS =
        "\1\12\71\uffff\1\0\1\uffff";
    static final String DFA111_maxS =
        "\1\126\71\uffff\1\0\1\uffff";
    static final String DFA111_acceptS =
        "\1\uffff\1\2\71\uffff\1\1";
    static final String DFA111_specialS =
        "\72\uffff\1\0\1\uffff}>";
    static final String[] DFA111_transitionS = {
            "\6\1\1\uffff\6\1\1\uffff\1\1\2\uffff\1\1\2\uffff\1\1\4\uffff"+
            "\3\1\3\uffff\16\1\5\uffff\15\1\1\72\12\1\2\uffff\1\1",
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
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            ""
    };

    static final short[] DFA111_eot = DFA.unpackEncodedString(DFA111_eotS);
    static final short[] DFA111_eof = DFA.unpackEncodedString(DFA111_eofS);
    static final char[] DFA111_min = DFA.unpackEncodedStringToUnsignedChars(DFA111_minS);
    static final char[] DFA111_max = DFA.unpackEncodedStringToUnsignedChars(DFA111_maxS);
    static final short[] DFA111_accept = DFA.unpackEncodedString(DFA111_acceptS);
    static final short[] DFA111_special = DFA.unpackEncodedString(DFA111_specialS);
    static final short[][] DFA111_transition;

    static {
        int numStates = DFA111_transitionS.length;
        DFA111_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA111_transition[i] = DFA.unpackEncodedString(DFA111_transitionS[i]);
        }
    }

    class DFA111 extends DFA {

        public DFA111(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 111;
            this.eot = DFA111_eot;
            this.eof = DFA111_eof;
            this.min = DFA111_min;
            this.max = DFA111_max;
            this.accept = DFA111_accept;
            this.special = DFA111_special;
            this.transition = DFA111_transition;
        }
        public String getDescription() {
            return "()* loopback of 329:7: ( dot id )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA111_58 = input.LA(1);

                         
                        int index111_58 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred201_JavaParser()) ) {s = 59;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index111_58);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 111, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA112_eotS =
        "\74\uffff";
    static final String DFA112_eofS =
        "\1\2\73\uffff";
    static final String DFA112_minS =
        "\1\12\1\0\72\uffff";
    static final String DFA112_maxS =
        "\1\126\1\0\72\uffff";
    static final String DFA112_acceptS =
        "\2\uffff\1\2\70\uffff\1\1";
    static final String DFA112_specialS =
        "\1\uffff\1\0\72\uffff}>";
    static final String[] DFA112_transitionS = {
            "\6\2\1\uffff\6\2\1\uffff\1\2\2\uffff\1\2\2\uffff\1\2\4\uffff"+
            "\3\2\3\uffff\16\2\5\uffff\17\2\1\1\10\2\2\uffff\1\2",
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

    static final short[] DFA112_eot = DFA.unpackEncodedString(DFA112_eotS);
    static final short[] DFA112_eof = DFA.unpackEncodedString(DFA112_eofS);
    static final char[] DFA112_min = DFA.unpackEncodedStringToUnsignedChars(DFA112_minS);
    static final char[] DFA112_max = DFA.unpackEncodedStringToUnsignedChars(DFA112_maxS);
    static final short[] DFA112_accept = DFA.unpackEncodedString(DFA112_acceptS);
    static final short[] DFA112_special = DFA.unpackEncodedString(DFA112_specialS);
    static final short[][] DFA112_transition;

    static {
        int numStates = DFA112_transitionS.length;
        DFA112_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA112_transition[i] = DFA.unpackEncodedString(DFA112_transitionS[i]);
        }
    }

    class DFA112 extends DFA {

        public DFA112(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 112;
            this.eot = DFA112_eot;
            this.eof = DFA112_eof;
            this.min = DFA112_min;
            this.max = DFA112_max;
            this.accept = DFA112_accept;
            this.special = DFA112_special;
            this.transition = DFA112_transition;
        }
        public String getDescription() {
            return "329:17: ( generic )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA112_1 = input.LA(1);

                         
                        int index112_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred202_JavaParser()) ) {s = 59;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index112_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 112, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA154_eotS =
        "\6\uffff";
    static final String DFA154_eofS =
        "\6\uffff";
    static final String DFA154_minS =
        "\1\13\1\111\2\uffff\1\6\1\111";
    static final String DFA154_maxS =
        "\1\143\1\121\2\uffff\1\126\1\121";
    static final String DFA154_acceptS =
        "\2\uffff\1\1\1\2\2\uffff";
    static final String DFA154_specialS =
        "\6\uffff}>";
    static final String[] DFA154_transitionS = {
            "\1\2\4\uffff\1\2\30\uffff\11\2\43\uffff\1\2\1\1\1\2\1\uffff"+
            "\3\2\1\uffff\1\2\4\uffff\2\2",
            "\1\4\1\uffff\1\2\1\uffff\1\3\3\uffff\1\3",
            "",
            "",
            "\1\2\117\uffff\1\5",
            "\1\4\1\uffff\1\2\1\uffff\1\3\3\uffff\1\3"
    };

    static final short[] DFA154_eot = DFA.unpackEncodedString(DFA154_eotS);
    static final short[] DFA154_eof = DFA.unpackEncodedString(DFA154_eofS);
    static final char[] DFA154_min = DFA.unpackEncodedStringToUnsignedChars(DFA154_minS);
    static final char[] DFA154_max = DFA.unpackEncodedStringToUnsignedChars(DFA154_maxS);
    static final short[] DFA154_accept = DFA.unpackEncodedString(DFA154_acceptS);
    static final short[] DFA154_special = DFA.unpackEncodedString(DFA154_specialS);
    static final short[][] DFA154_transition;

    static {
        int numStates = DFA154_transitionS.length;
        DFA154_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA154_transition[i] = DFA.unpackEncodedString(DFA154_transitionS[i]);
        }
    }

    class DFA154 extends DFA {

        public DFA154(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 154;
            this.eot = DFA154_eot;
            this.eof = DFA154_eof;
            this.min = DFA154_min;
            this.max = DFA154_max;
            this.accept = DFA154_accept;
            this.special = DFA154_special;
            this.transition = DFA154_transition;
        }
        public String getDescription() {
            return "214:12: ( constant | variable_name )";
        }
    }
 

    public static final BitSet FOLLOW_package_def_in_file40 = new BitSet(new long[]{0x000001E7000001E0L,0x0000000000080000L});
    public static final BitSet FOLLOW_import_def_in_file42 = new BitSet(new long[]{0x000001E7000001E0L,0x0000000000080000L});
    public static final BitSet FOLLOW_class_def_in_file46 = new BitSet(new long[]{0x000001E7000001E2L,0x0000000000080000L});
    public static final BitSet FOLLOW_enum_def_in_file50 = new BitSet(new long[]{0x000001E7000001E2L,0x0000000000080000L});
    public static final BitSet FOLLOW_interface_def_in_file54 = new BitSet(new long[]{0x000001E7000001E2L,0x0000000000080000L});
    public static final BitSet FOLLOW_package__in_package_def68 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_package_name_in_package_def70 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_package_def72 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_import__in_import_def84 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_import_name_in_import_def86 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_import_def88 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_class_def100 = new BitSet(new long[]{0x000001E700000040L,0x0000000000080000L});
    public static final BitSet FOLLOW_modifier_in_class_def103 = new BitSet(new long[]{0x000001E700000040L,0x0000000000080000L});
    public static final BitSet FOLLOW_class__in_class_def106 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_class_def108 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000800L});
    public static final BitSet FOLLOW_generic_in_class_def110 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000800L});
    public static final BitSet FOLLOW_extends__in_class_def114 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_class_name_in_class_def116 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000800L});
    public static final BitSet FOLLOW_implements__in_class_def121 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_class_name_in_class_def123 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000C00L});
    public static final BitSet FOLLOW_comma_in_class_def126 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_class_name_in_class_def128 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000C00L});
    public static final BitSet FOLLOW_class_block_in_class_def134 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_enum_def150 = new BitSet(new long[]{0x000001E700000100L,0x0000000000080000L});
    public static final BitSet FOLLOW_modifier_in_enum_def153 = new BitSet(new long[]{0x000001E700000100L,0x0000000000080000L});
    public static final BitSet FOLLOW_enum__in_enum_def156 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_enum_def158 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000800L});
    public static final BitSet FOLLOW_generic_in_enum_def160 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000800L});
    public static final BitSet FOLLOW_extends__in_enum_def164 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_class_name_in_enum_def166 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000800L});
    public static final BitSet FOLLOW_enum_block_in_enum_def170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_interface_def186 = new BitSet(new long[]{0x000001E7000001E0L,0x0000000000080000L});
    public static final BitSet FOLLOW_modifier_in_interface_def189 = new BitSet(new long[]{0x000001E7000001E0L,0x0000000000080000L});
    public static final BitSet FOLLOW_interface__in_interface_def192 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_interface_def194 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000800L});
    public static final BitSet FOLLOW_generic_in_interface_def196 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000800L});
    public static final BitSet FOLLOW_extends__in_interface_def200 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_class_name_in_interface_def202 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000800L});
    public static final BitSet FOLLOW_interface_block_in_interface_def206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_static__in_static_init222 = new BitSet(new long[]{0xF003FE38497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_static_init224 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_static_init226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_constructor_def238 = new BitSet(new long[]{0x000001E700000000L,0x0000000000480000L});
    public static final BitSet FOLLOW_modifier_in_constructor_def241 = new BitSet(new long[]{0x000001E700000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_constructor_def244 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_open_bracket_in_constructor_def246 = new BitSet(new long[]{0x0001FE1800000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_argument_def_in_constructor_def248 = new BitSet(new long[]{0x0001FE1800000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_close_bracket_in_constructor_def250 = new BitSet(new long[]{0xF003FE38497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_constructor_def252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_method_def267 = new BitSet(new long[]{0x0001FFFF00000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_modifier_in_method_def270 = new BitSet(new long[]{0x0001FFFF00000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_variable_type_in_method_def273 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_method_def275 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_open_bracket_in_method_def277 = new BitSet(new long[]{0x0001FE1800000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_argument_def_in_method_def279 = new BitSet(new long[]{0x0001FE1800000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_close_bracket_in_method_def281 = new BitSet(new long[]{0xF003FE38C97EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_throws__in_method_def284 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_class_name_in_method_def286 = new BitSet(new long[]{0xF003FE38497EFE00L,0x00000000004C0D00L});
    public static final BitSet FOLLOW_comma_in_method_def289 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_class_name_in_method_def291 = new BitSet(new long[]{0xF003FE38497EFE00L,0x00000000004C0D00L});
    public static final BitSet FOLLOW_code_in_method_def297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_field_def313 = new BitSet(new long[]{0x0001FFFF00000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_modifier_in_field_def316 = new BitSet(new long[]{0x0001FFFF00000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_variable_type_in_field_def319 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400000L});
    public static final BitSet FOLLOW_variable_name_in_field_def321 = new BitSet(new long[]{0x0F80000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_assign_in_field_def324 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_field_def326 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_field_def330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_type_in_argument_def342 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400200L});
    public static final BitSet FOLLOW_dot_in_argument_def345 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_argument_def347 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_argument_def349 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400000L});
    public static final BitSet FOLLOW_variable_name_in_argument_def353 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_comma_in_argument_def356 = new BitSet(new long[]{0x0001FE1800000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_variable_type_in_argument_def358 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400200L});
    public static final BitSet FOLLOW_dot_in_argument_def361 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_argument_def363 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_argument_def365 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400000L});
    public static final BitSet FOLLOW_variable_name_in_argument_def369 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_annotation_in_variable_def384 = new BitSet(new long[]{0x0001FE1800000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_variable_type_in_variable_def387 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400000L});
    public static final BitSet FOLLOW_variable_name_in_variable_def389 = new BitSet(new long[]{0x0F80000000000002L});
    public static final BitSet FOLLOW_assign_in_variable_def392 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_variable_def394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_public__in_modifier406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_private__in_modifier411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_protected__in_modifier416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_static__in_modifier421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_transient__in_modifier426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_synchronized__in_modifier431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_abstract__in_modifier436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_begin_in_class_block448 = new BitSet(new long[]{0x0005FFFF00000840L,0x0000000000480000L});
    public static final BitSet FOLLOW_class_def_in_class_block451 = new BitSet(new long[]{0x0005FFFF00000840L,0x0000000000480000L});
    public static final BitSet FOLLOW_static_init_in_class_block455 = new BitSet(new long[]{0x0005FFFF00000840L,0x0000000000480000L});
    public static final BitSet FOLLOW_constructor_def_in_class_block459 = new BitSet(new long[]{0x0005FFFF00000840L,0x0000000000480000L});
    public static final BitSet FOLLOW_method_def_in_class_block463 = new BitSet(new long[]{0x0005FFFF00000840L,0x0000000000480000L});
    public static final BitSet FOLLOW_field_def_in_class_block467 = new BitSet(new long[]{0x0005FFFF00000840L,0x0000000000480000L});
    public static final BitSet FOLLOW_block_end_in_class_block471 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_class_block473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_begin_in_enum_block486 = new BitSet(new long[]{0x0005FFFF00000840L,0x0000000000480000L});
    public static final BitSet FOLLOW_class_def_in_enum_block489 = new BitSet(new long[]{0x0005FFFF00000840L,0x0000000000480000L});
    public static final BitSet FOLLOW_constructor_def_in_enum_block493 = new BitSet(new long[]{0x0005FFFF00000840L,0x0000000000480000L});
    public static final BitSet FOLLOW_method_def_in_enum_block497 = new BitSet(new long[]{0x0005FFFF00000840L,0x0000000000480000L});
    public static final BitSet FOLLOW_field_def_in_enum_block501 = new BitSet(new long[]{0x0005FFFF00000840L,0x0000000000480000L});
    public static final BitSet FOLLOW_enum_content_in_enum_block505 = new BitSet(new long[]{0x0005FFFF00000840L,0x0000000000480000L});
    public static final BitSet FOLLOW_block_end_in_enum_block509 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_enum_block511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_enum_content523 = new BitSet(new long[]{0x0000000000000000L,0x0000000000048400L});
    public static final BitSet FOLLOW_open_bracket_in_enum_content526 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE98900L});
    public static final BitSet FOLLOW_arguments_in_enum_content528 = new BitSet(new long[]{0x0001FE1800000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_close_bracket_in_enum_content530 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040400L});
    public static final BitSet FOLLOW_comma_in_enum_content535 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_enum_content537 = new BitSet(new long[]{0x0000000000000000L,0x0000000000048400L});
    public static final BitSet FOLLOW_open_bracket_in_enum_content540 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE98900L});
    public static final BitSet FOLLOW_arguments_in_enum_content542 = new BitSet(new long[]{0x0001FE1800000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_close_bracket_in_enum_content544 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040400L});
    public static final BitSet FOLLOW_semicolon_in_enum_content550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_begin_in_interface_block562 = new BitSet(new long[]{0x0005FFFF00000840L,0x0000000000480000L});
    public static final BitSet FOLLOW_annotation_in_interface_block565 = new BitSet(new long[]{0x0001FFFF00000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_modifier_in_interface_block568 = new BitSet(new long[]{0x0001FFFF00000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_variable_type_in_interface_block571 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_interface_block573 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_open_bracket_in_interface_block575 = new BitSet(new long[]{0x0001FE1800000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_argument_def_in_interface_block577 = new BitSet(new long[]{0x0001FE1800000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_close_bracket_in_interface_block579 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_interface_block581 = new BitSet(new long[]{0x0005FFFF00000840L,0x0000000000480000L});
    public static final BitSet FOLLOW_block_end_in_interface_block585 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_interface_block587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_name_in_method_call600 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_open_bracket_in_method_call602 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE98900L});
    public static final BitSet FOLLOW_arguments_in_method_call604 = new BitSet(new long[]{0x0001FE1800000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_close_bracket_in_method_call606 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_method_call609 = new BitSet(new long[]{0x000001E700000040L,0x0000000000480000L});
    public static final BitSet FOLLOW_id_in_method_call612 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008200L});
    public static final BitSet FOLLOW_class__in_method_call616 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008200L});
    public static final BitSet FOLLOW_open_bracket_in_method_call620 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE98900L});
    public static final BitSet FOLLOW_arguments_in_method_call622 = new BitSet(new long[]{0x0001FE1800000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_close_bracket_in_method_call624 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_annotation_name_in_annotation639 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_open_bracket_in_annotation642 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_annotation644 = new BitSet(new long[]{0x0001FE1800000800L,0x0000000000490400L});
    public static final BitSet FOLLOW_comma_in_annotation647 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_annotation649 = new BitSet(new long[]{0x0001FE1800000800L,0x0000000000490400L});
    public static final BitSet FOLLOW_close_bracket_in_annotation653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_generic665 = new BitSet(new long[]{0x0001FE1800000800L,0x0000000000481004L});
    public static final BitSet FOLLOW_variable_type_in_generic669 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001400L});
    public static final BitSet FOLLOW_question__in_generic673 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001400L});
    public static final BitSet FOLLOW_comma_in_generic677 = new BitSet(new long[]{0x0001FE1800000800L,0x0000000000480004L});
    public static final BitSet FOLLOW_variable_type_in_generic680 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001400L});
    public static final BitSet FOLLOW_question__in_generic684 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001400L});
    public static final BitSet FOLLOW_GT_in_generic691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cast_in_value704 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_left_unary_in_value707 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_single_value_in_value710 = new BitSet(new long[]{0xF079FE1800000802L,0x00000000004819FFL});
    public static final BitSet FOLLOW_right_unary_in_value712 = new BitSet(new long[]{0xC079FE1800000802L,0x00000000004818FFL});
    public static final BitSet FOLLOW_binary_operator_in_value716 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_value718 = new BitSet(new long[]{0x0001FE1800000802L,0x0000000000480004L});
    public static final BitSet FOLLOW_question__in_value723 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_value725 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_colon_in_value727 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_value729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_open_bracket_in_value736 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_value738 = new BitSet(new long[]{0x0001FE1800000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_close_bracket_in_value740 = new BitSet(new long[]{0xC079FE1800000802L,0x00000000004818FFL});
    public static final BitSet FOLLOW_binary_operator_in_value743 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_value745 = new BitSet(new long[]{0x0001FE1800000802L,0x0000000000480004L});
    public static final BitSet FOLLOW_question__in_value750 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_value752 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_colon_in_value754 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_value756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constant_in_single_value769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_single_value774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_single_value780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_name_in_single_value785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_new_class_in_single_value790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_this__in_single_value795 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_super__in_single_value800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_const_in_constant809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_hex_long_const_in_constant814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_hex_const_in_constant819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_long_const_in_constant824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_int_const_in_constant829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_const_in_constant834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_float_const_in_constant839 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_char_const_in_constant844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_null_const_in_constant849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_boolean_const_in_constant854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_array_const_in_constant859 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_new__in_new_class871 = new BitSet(new long[]{0x0001FE1800000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_class_name_in_new_class874 = new BitSet(new long[]{0x0000000000000002L,0x000000000000A000L});
    public static final BitSet FOLLOW_primitive_in_new_class878 = new BitSet(new long[]{0x0000000000000002L,0x000000000000A000L});
    public static final BitSet FOLLOW_array_in_new_class882 = new BitSet(new long[]{0x0003FE1800010E02L,0x0000000C2EE88800L});
    public static final BitSet FOLLOW_array_const_in_new_class884 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_open_bracket_in_new_class890 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE98900L});
    public static final BitSet FOLLOW_arguments_in_new_class892 = new BitSet(new long[]{0x0001FE1800000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_close_bracket_in_new_class894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_value_in_arguments910 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_comma_in_arguments913 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_arguments915 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_statement_in_code929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_begin_in_code934 = new BitSet(new long[]{0xF007FFFF497EFE40L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_code936 = new BitSet(new long[]{0xF007FFFF497EFE40L,0x00000000004C0900L});
    public static final BitSet FOLLOW_block_end_in_code939 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_code941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_label_in_statement954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_statement960 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_statement962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_statement967 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_statement969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_statement974 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_statement976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_semicolon_in_statement982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_statement_in_statement987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_continue__in_statement992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_break__in_statement997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for_loop_in_statement1002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while_loop_in_statement1007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_do_loop_in_statement1012 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_statement1014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_switch_case_in_statement1019 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_else_in_statement1024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_try_catch_in_statement1029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_synchronized_block_in_statement1034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_throw__in_statement1039 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_statement1041 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_statement1043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_left_unary_in_statement1048 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400000L});
    public static final BitSet FOLLOW_variable_name_in_statement1051 = new BitSet(new long[]{0x3000000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_right_unary_in_statement1053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_label_in_statement_wosemicolon1065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_statement_wosemicolon1070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_statement_wosemicolon1075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_statement_wosemicolon1080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_statement_in_statement_wosemicolon1086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_continue__in_statement_wosemicolon1091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_break__in_statement_wosemicolon1096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for_loop_in_statement_wosemicolon1101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while_loop_in_statement_wosemicolon1106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_do_loop_in_statement_wosemicolon1111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_switch_case_in_statement_wosemicolon1116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_else_in_statement_wosemicolon1121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_try_catch_in_statement_wosemicolon1126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_synchronized_block_in_statement_wosemicolon1131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_throw__in_statement_wosemicolon1136 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_statement_wosemicolon1138 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_statement_wosemicolon1140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_left_unary_in_statement_wosemicolon1145 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400000L});
    public static final BitSet FOLLOW_variable_name_in_statement_wosemicolon1148 = new BitSet(new long[]{0x3000000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_right_unary_in_statement_wosemicolon1150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return__in_return_statement1163 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EEC8900L});
    public static final BitSet FOLLOW_value_in_return_statement1165 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_return_statement1168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_this__in_variable_assignment1181 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_variable_assignment1183 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400000L});
    public static final BitSet FOLLOW_super__in_variable_assignment1187 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_variable_assignment1189 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400000L});
    public static final BitSet FOLLOW_variable_name_in_variable_assignment1193 = new BitSet(new long[]{0x0F80000000000000L});
    public static final BitSet FOLLOW_assign_in_variable_assignment1195 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_variable_assignment1197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for__in_for_loop1207 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_for_loop1209 = new BitSet(new long[]{0x0001FE1800000800L,0x00000000004C0000L});
    public static final BitSet FOLLOW_variable_def_in_for_loop1212 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040400L});
    public static final BitSet FOLLOW_comma_in_for_loop1215 = new BitSet(new long[]{0x0001FE1800000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_variable_def_in_for_loop1217 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040400L});
    public static final BitSet FOLLOW_semicolon_in_for_loop1223 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EEC8900L});
    public static final BitSet FOLLOW_value_in_for_loop1225 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_for_loop1228 = new BitSet(new long[]{0xF001FE38497EF800L,0x0000000000490100L});
    public static final BitSet FOLLOW_statement_wosemicolon_in_for_loop1231 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010400L});
    public static final BitSet FOLLOW_comma_in_for_loop1234 = new BitSet(new long[]{0xF001FE38497EF800L,0x0000000000480100L});
    public static final BitSet FOLLOW_statement_wosemicolon_in_for_loop1236 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010400L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_for_loop1242 = new BitSet(new long[]{0xF003FE38497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_for_loop1244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for__in_for_loop1249 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_for_loop1251 = new BitSet(new long[]{0x0001FE1800000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_variable_type_in_for_loop1253 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400000L});
    public static final BitSet FOLLOW_variable_name_in_for_loop1255 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_colon_in_for_loop1257 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_for_loop1259 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_for_loop1261 = new BitSet(new long[]{0xF003FE38497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_for_loop1263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while__in_while_loop1275 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_while_loop1277 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_while_loop1279 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_while_loop1281 = new BitSet(new long[]{0xF003FE38497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_while_loop1283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_do__in_do_loop1294 = new BitSet(new long[]{0xF003FE38497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_do_loop1296 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_while__in_do_loop1298 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_do_loop1300 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_do_loop1302 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_do_loop1304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_switch__in_switch_case1315 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_switch_case1317 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_switch_case1319 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_switch_case1321 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000800L});
    public static final BitSet FOLLOW_block_begin_in_switch_case1323 = new BitSet(new long[]{0x0000000006000000L});
    public static final BitSet FOLLOW_case__in_switch_case1334 = new BitSet(new long[]{0x0003FE1800070E00L,0x0000000C2EE80800L});
    public static final BitSet FOLLOW_constant_in_switch_case1338 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_variable_name_in_switch_case1342 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_colon_in_switch_case1345 = new BitSet(new long[]{0xF007FFFF4F7EFE40L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_switch_case1350 = new BitSet(new long[]{0xF007FFFF4F7EFE40L,0x00000000004C0900L});
    public static final BitSet FOLLOW_statement_in_switch_case1354 = new BitSet(new long[]{0xF007FFFF4F7EFE40L,0x00000000004C0900L});
    public static final BitSet FOLLOW_break__in_switch_case1359 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_switch_case1361 = new BitSet(new long[]{0x0005FFFF06000840L,0x0000000000480000L});
    public static final BitSet FOLLOW_default__in_switch_case1372 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_colon_in_switch_case1374 = new BitSet(new long[]{0xF007FFFF4F7EFE40L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_switch_case1377 = new BitSet(new long[]{0xF007FFFF4F7EFE40L,0x00000000004C0900L});
    public static final BitSet FOLLOW_statement_in_switch_case1381 = new BitSet(new long[]{0xF007FFFF4F7EFE40L,0x00000000004C0900L});
    public static final BitSet FOLLOW_block_end_in_switch_case1392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if__in_if_else1403 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_if_else1405 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_if_else1407 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_if_else1409 = new BitSet(new long[]{0xF003FE38497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_if_else1411 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_else__in_if_else1414 = new BitSet(new long[]{0xF003FE38497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_if_else1416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_try__in_try_catch1429 = new BitSet(new long[]{0xF003FE38497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_try_catch1431 = new BitSet(new long[]{0x0000000030000002L});
    public static final BitSet FOLLOW_catch__in_try_catch1434 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_try_catch1436 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_try_catch1438 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_try_catch1440 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_try_catch1442 = new BitSet(new long[]{0xF003FE38497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_try_catch1444 = new BitSet(new long[]{0x0000000030000002L});
    public static final BitSet FOLLOW_finally__in_try_catch1449 = new BitSet(new long[]{0xF003FE38497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_try_catch1451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_synchronized__in_synchronized_block1463 = new BitSet(new long[]{0xF003FE38497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_synchronized_block1465 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_label1475 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_colon_in_label1477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_cast1485 = new BitSet(new long[]{0x0001FE1800000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_variable_type_in_cast1487 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_cast1489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_final__in_variable_type1503 = new BitSet(new long[]{0x0001FE1800000000L});
    public static final BitSet FOLLOW_volatile__in_variable_type1507 = new BitSet(new long[]{0x0001FE1800000000L});
    public static final BitSet FOLLOW_primitive_in_variable_type1511 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_array_in_variable_type1513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_final__in_variable_type1520 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_volatile__in_variable_type1524 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_class_name_in_variable_type1528 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_array_in_variable_type1530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_final__in_variable_type1537 = new BitSet(new long[]{0x0001FE1800000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_volatile__in_variable_type1541 = new BitSet(new long[]{0x0001FE1800000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_void__in_variable_type1545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_id1555 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_plus_in_binary_operator1566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_minus_in_binary_operator1571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_star_in_binary_operator1576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_binary_operator1581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENT_in_binary_operator1588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUAL_in_binary_operator1595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNEQUAL_in_binary_operator1602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_binary_operator1609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_binary_operator1616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LE_in_binary_operator1623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GE_in_binary_operator1630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOGICAL_OR_in_binary_operator1637 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BIT_OR_in_binary_operator1644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOGICAL_AND_in_binary_operator1651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BIT_AND_in_binary_operator1658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INC_in_left_unary1672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEC_in_left_unary1679 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_left_unary1686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_left_unary1693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_left_unary1700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INC_in_right_unary1714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEC_in_right_unary1721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_right_unary1728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_primitive1741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BYTE_in_primitive1748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_in_primitive1755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SHORT_in_primitive1762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_primitive1769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LONG_in_primitive1776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_primitive1783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLE_in_primitive1790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_CONST_in_int_const1808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LONG_CONST_in_long_const1818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HEX_CONST_in_hex_const1828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HEX_LONG_CONST_in_hex_long_const1838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_CONST_in_string_const1848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_CONST_in_float_const1858 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_CONST_in_char_const1868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_null_const1878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOL_CONST_in_boolean_const1889 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_begin_in_array_const1899 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_array_const1901 = new BitSet(new long[]{0x0005FFFF00000840L,0x0000000000480400L});
    public static final BitSet FOLLOW_comma_in_array_const1904 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_array_const1906 = new BitSet(new long[]{0x0005FFFF00000840L,0x0000000000480400L});
    public static final BitSet FOLLOW_block_end_in_array_const1910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_name_in_class_const1919 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_primitive_in_class_const1923 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_void__in_class_const1927 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_class_const1930 = new BitSet(new long[]{0x000001E700000040L,0x0000000000080000L});
    public static final BitSet FOLLOW_class__in_class_const1932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_package_name1948 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_package_name1950 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_package_name1954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_import_name1968 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_import_name1970 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400002L});
    public static final BitSet FOLLOW_id_in_import_name1975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_star_in_import_name1979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_class_name1991 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000A00L});
    public static final BitSet FOLLOW_dot_in_class_name1994 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_class_name1996 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000A00L});
    public static final BitSet FOLLOW_generic_in_class_name2000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_super__in_method_name2018 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_this__in_method_name2022 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_new_class_in_method_name2026 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_method_name2029 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400000L});
    public static final BitSet FOLLOW_variable_name_in_method_name2038 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_method_name2048 = new BitSet(new long[]{0x000001E700060040L,0x0000000000480000L});
    public static final BitSet FOLLOW_class__in_method_name2051 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_variable_name_in_method_name2055 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_super__in_method_name2066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_this__in_method_name2071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_name_in_variable_name2082 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_array_in_variable_name2084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_in_annotation_name2095 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_ID_in_annotation_name2097 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_name2107 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_name2109 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_name2113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_open_rect_bracket_in_array2122 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE8C900L});
    public static final BitSet FOLLOW_value_in_array2124 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE8C900L});
    public static final BitSet FOLLOW_close_rect_bracket_in_array2127 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_PACKAGE_in_package_2142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_import_2153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASS_in_class_2163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENUM_in_enum_2172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTERFACE_in_interface_2182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXTENDS_in_extends_2190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPLEMENTS_in_implements_2200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_THIS_in_this_2209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUPER_in_super_2218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOID_in_void_2227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PUBLIC_in_public_2236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRIVATE_in_private_2244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROTECTED_in_protected_2254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STATIC_in_static_2263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FINAL_in_final_2272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOLATILE_in_volatile_2283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SYNCHRONIZED_in_synchronized_2293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ABSTRACT_in_abstract_2303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSIENT_in_transient_2313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEW_in_new_2322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRY_in_try_2331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CATCH_in_catch_2340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FINALLY_in_finally_2348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_THROWS_in_throws_2357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_THROW_in_throw_2366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FOR_in_for_2376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHILE_in_while_2385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DO_in_do_2394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_if_2403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELSE_in_else_2412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SWITCH_in_switch_2422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CASE_in_case_2431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEFAULT_in_default_2439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETURN_in_return_2449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BREAK_in_break_2458 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_break_2460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BREAK_in_break_2467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTINUE_in_continue_2479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_semicolon2494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_comma2503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_colon2512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUESTION_in_question_2522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_plus2531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_minus2540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_star2549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_dot2558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_assign2567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCASSIGN_in_assign2574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECASSIGN_in_assign2581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BITORASSIGN_in_assign2588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BITANDASSIGN_in_assign2595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_CURLY_BRACKET_in_block_begin2607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLOSE_CURLY_BRACKET_in_block_end2617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_open_bracket2627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_close_bracket2637 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_RECT_BRACKET_in_open_rect_bracket2648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLOSE_RECT_BRACKET_in_close_rect_bracket2658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_def_in_synpred2_JavaParser46 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enum_def_in_synpred3_JavaParser50 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interface_def_in_synpred4_JavaParser54 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_def_in_synpred41_JavaParser451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_static_init_in_synpred42_JavaParser455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constructor_def_in_synpred43_JavaParser459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_def_in_synpred44_JavaParser463 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_field_def_in_synpred45_JavaParser467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_def_in_synpred47_JavaParser489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constructor_def_in_synpred48_JavaParser493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_def_in_synpred49_JavaParser497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_field_def_in_synpred50_JavaParser501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enum_content_in_synpred51_JavaParser505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_right_unary_in_synpred71_JavaParser712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_binary_operator_in_synpred72_JavaParser716 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_synpred72_JavaParser718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_question__in_synpred73_JavaParser723 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_synpred73_JavaParser725 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_colon_in_synpred73_JavaParser727 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_synpred73_JavaParser729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cast_in_synpred74_JavaParser704 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_left_unary_in_synpred74_JavaParser707 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_single_value_in_synpred74_JavaParser710 = new BitSet(new long[]{0xF079FE1800000802L,0x00000000004819FFL});
    public static final BitSet FOLLOW_right_unary_in_synpred74_JavaParser712 = new BitSet(new long[]{0xC079FE1800000802L,0x00000000004818FFL});
    public static final BitSet FOLLOW_binary_operator_in_synpred74_JavaParser716 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_synpred74_JavaParser718 = new BitSet(new long[]{0x0001FE1800000802L,0x0000000000480004L});
    public static final BitSet FOLLOW_question__in_synpred74_JavaParser723 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_synpred74_JavaParser725 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_colon_in_synpred74_JavaParser727 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_synpred74_JavaParser729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_binary_operator_in_synpred75_JavaParser743 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_synpred75_JavaParser745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_question__in_synpred76_JavaParser750 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_synpred76_JavaParser752 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_colon_in_synpred76_JavaParser754 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_synpred76_JavaParser756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constant_in_synpred77_JavaParser769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_synpred78_JavaParser774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_synpred79_JavaParser780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_name_in_synpred80_JavaParser785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_new_class_in_synpred81_JavaParser790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_this__in_synpred82_JavaParser795 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_semicolon_in_synpred101_JavaParser941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_label_in_synpred102_JavaParser954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_synpred103_JavaParser960 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_synpred103_JavaParser962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_synpred104_JavaParser967 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_synpred104_JavaParser969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_synpred105_JavaParser974 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_synpred105_JavaParser976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_right_unary_in_synpred119_JavaParser1053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_label_in_synpred120_JavaParser1065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_synpred121_JavaParser1070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_synpred122_JavaParser1075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_synpred123_JavaParser1080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for__in_synpred145_JavaParser1207 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_synpred145_JavaParser1209 = new BitSet(new long[]{0x0001FE1800000800L,0x00000000004C0000L});
    public static final BitSet FOLLOW_variable_def_in_synpred145_JavaParser1212 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040400L});
    public static final BitSet FOLLOW_comma_in_synpred145_JavaParser1215 = new BitSet(new long[]{0x0001FE1800000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_variable_def_in_synpred145_JavaParser1217 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040400L});
    public static final BitSet FOLLOW_semicolon_in_synpred145_JavaParser1223 = new BitSet(new long[]{0xF003FE1800071E00L,0x0000000C2EEC8900L});
    public static final BitSet FOLLOW_value_in_synpred145_JavaParser1225 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_synpred145_JavaParser1228 = new BitSet(new long[]{0xF001FE38497EF800L,0x0000000000490100L});
    public static final BitSet FOLLOW_statement_wosemicolon_in_synpred145_JavaParser1231 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010400L});
    public static final BitSet FOLLOW_comma_in_synpred145_JavaParser1234 = new BitSet(new long[]{0xF001FE38497EF800L,0x0000000000480100L});
    public static final BitSet FOLLOW_statement_wosemicolon_in_synpred145_JavaParser1236 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010400L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_synpred145_JavaParser1242 = new BitSet(new long[]{0xF003FE38497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_synpred145_JavaParser1244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_case__in_synpred147_JavaParser1334 = new BitSet(new long[]{0x0003FE1800070E00L,0x0000000C2EE80800L});
    public static final BitSet FOLLOW_constant_in_synpred147_JavaParser1338 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_variable_name_in_synpred147_JavaParser1342 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_colon_in_synpred147_JavaParser1345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_code_in_synpred148_JavaParser1350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_synpred149_JavaParser1354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_code_in_synpred152_JavaParser1377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_synpred153_JavaParser1381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_else__in_synpred155_JavaParser1414 = new BitSet(new long[]{0xF003FE38497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_synpred155_JavaParser1416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_catch__in_synpred156_JavaParser1434 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_synpred156_JavaParser1436 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_synpred156_JavaParser1438 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_synpred156_JavaParser1440 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_synpred156_JavaParser1442 = new BitSet(new long[]{0xF003FE38497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_synpred156_JavaParser1444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_finally__in_synpred157_JavaParser1449 = new BitSet(new long[]{0xF003FE38497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_synpred157_JavaParser1451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dot_in_synpred201_JavaParser1994 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_synpred201_JavaParser1996 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_generic_in_synpred202_JavaParser2000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_synpred211_JavaParser2107 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_synpred211_JavaParser2109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BREAK_in_synpred214_JavaParser2458 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_synpred214_JavaParser2460 = new BitSet(new long[]{0x0000000000000002L});

}