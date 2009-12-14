// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g 2009-12-14 18:26:21

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
    public String getGrammarFileName() { return "/home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g"; }


    private ParserHelper helper = new ParserHelper(this);

    public ParserHelper getParserHelper() {
    	return helper;
    }



    // $ANTLR start "file"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:1: file : package_def ( import_def )* ( class_def | enum_def | interface_def | annotation_def )+ ;
    public final void file() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:6: ( package_def ( import_def )* ( class_def | enum_def | interface_def | annotation_def )+ )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:8: package_def ( import_def )* ( class_def | enum_def | interface_def | annotation_def )+
            {
            pushFollow(FOLLOW_package_def_in_file40);
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

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:32: ( class_def | enum_def | interface_def | annotation_def )+
            int cnt2=0;
            loop2:
            do {
                int alt2=5;
                alt2 = dfa2.predict(input);
                switch (alt2) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:33: class_def
            	    {
            	    pushFollow(FOLLOW_class_def_in_file46);
            	    class_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 2 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:45: enum_def
            	    {
            	    pushFollow(FOLLOW_enum_def_in_file50);
            	    enum_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 3 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:56: interface_def
            	    {
            	    pushFollow(FOLLOW_interface_def_in_file54);
            	    interface_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 4 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:72: annotation_def
            	    {
            	    pushFollow(FOLLOW_annotation_def_in_file58);
            	    annotation_def();

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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:27:1: package_def : package_ package_name semicolon ;
    public final void package_def() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:28:2: ( package_ package_name semicolon )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:28:4: package_ package_name semicolon
            {
            pushFollow(FOLLOW_package__in_package_def72);
            package_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_package_name_in_package_def74);
            package_name();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_semicolon_in_package_def76);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:31:1: import_def : import_ import_name semicolon ;
    public final void import_def() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:32:2: ( import_ import_name semicolon )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:32:4: import_ import_name semicolon
            {
            pushFollow(FOLLOW_import__in_import_def88);
            import_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_import_name_in_import_def90);
            import_name();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_semicolon_in_import_def92);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:35:1: class_def : ( annotation )* ( class_modifier )* class_ id ( generic )? ( extends_ class_name )? ( implements_ class_name ( comma class_name )* )? class_block ;
    public final JavaParser.class_def_return class_def() throws RecognitionException {
        JavaParser.class_def_return retval = new JavaParser.class_def_return();
        retval.start = input.LT(1);

        JavaParser.id_return id1 = null;


        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:36:2: ( ( annotation )* ( class_modifier )* class_ id ( generic )? ( extends_ class_name )? ( implements_ class_name ( comma class_name )* )? class_block )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:36:4: ( annotation )* ( class_modifier )* class_ id ( generic )? ( extends_ class_name )? ( implements_ class_name ( comma class_name )* )? class_block
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
            	    pushFollow(FOLLOW_annotation_in_class_def104);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:36:16: ( class_modifier )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>=PUBLIC && LA4_0<=FINAL)||LA4_0==STATIC||LA4_0==ABSTRACT) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: class_modifier
            	    {
            	    pushFollow(FOLLOW_class_modifier_in_class_def107);
            	    class_modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            pushFollow(FOLLOW_class__in_class_def110);
            class_();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_id_in_class_def112);
            id1=id();

            state._fsp--;
            if (state.failed) return retval;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:36:42: ( generic )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==LT) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: generic
                    {
                    pushFollow(FOLLOW_generic_in_class_def114);
                    generic();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;

            }

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:36:51: ( extends_ class_name )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==EXTENDS) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:36:52: extends_ class_name
                    {
                    pushFollow(FOLLOW_extends__in_class_def118);
                    extends_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_class_def120);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;

            }

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:36:74: ( implements_ class_name ( comma class_name )* )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==IMPLEMENTS) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:36:75: implements_ class_name ( comma class_name )*
                    {
                    pushFollow(FOLLOW_implements__in_class_def125);
                    implements_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_class_def127);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:36:98: ( comma class_name )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==COMMA) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:36:99: comma class_name
                    	    {
                    	    pushFollow(FOLLOW_comma_in_class_def130);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    pushFollow(FOLLOW_class_name_in_class_def132);
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

            pushFollow(FOLLOW_class_block_in_class_def138);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:40:1: enum_def : ( annotation )* ( class_modifier )* enum_ id ( generic )? ( extends_ class_name )? enum_block ;
    public final JavaParser.enum_def_return enum_def() throws RecognitionException {
        JavaParser.enum_def_return retval = new JavaParser.enum_def_return();
        retval.start = input.LT(1);

        JavaParser.id_return id2 = null;


        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:41:2: ( ( annotation )* ( class_modifier )* enum_ id ( generic )? ( extends_ class_name )? enum_block )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:41:4: ( annotation )* ( class_modifier )* enum_ id ( generic )? ( extends_ class_name )? enum_block
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:41:4: ( annotation )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==AT) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_enum_def154);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:41:16: ( class_modifier )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>=PUBLIC && LA10_0<=FINAL)||LA10_0==STATIC||LA10_0==ABSTRACT) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: class_modifier
            	    {
            	    pushFollow(FOLLOW_class_modifier_in_enum_def157);
            	    class_modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            pushFollow(FOLLOW_enum__in_enum_def160);
            enum_();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_id_in_enum_def162);
            id2=id();

            state._fsp--;
            if (state.failed) return retval;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:41:41: ( generic )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==LT) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: generic
                    {
                    pushFollow(FOLLOW_generic_in_enum_def164);
                    generic();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;

            }

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:41:50: ( extends_ class_name )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==EXTENDS) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:41:51: extends_ class_name
                    {
                    pushFollow(FOLLOW_extends__in_enum_def168);
                    extends_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_enum_def170);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;

            }

            pushFollow(FOLLOW_enum_block_in_enum_def174);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:45:1: interface_def : ( annotation )* ( class_modifier )* interface_ id ( generic )? ( extends_ class_name )? interface_block ;
    public final JavaParser.interface_def_return interface_def() throws RecognitionException {
        JavaParser.interface_def_return retval = new JavaParser.interface_def_return();
        retval.start = input.LT(1);

        JavaParser.id_return id3 = null;


        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:46:2: ( ( annotation )* ( class_modifier )* interface_ id ( generic )? ( extends_ class_name )? interface_block )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:46:4: ( annotation )* ( class_modifier )* interface_ id ( generic )? ( extends_ class_name )? interface_block
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:46:4: ( annotation )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==AT) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_interface_def190);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:46:16: ( class_modifier )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0>=PUBLIC && LA14_0<=FINAL)||LA14_0==STATIC||LA14_0==ABSTRACT) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: class_modifier
            	    {
            	    pushFollow(FOLLOW_class_modifier_in_interface_def193);
            	    class_modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            pushFollow(FOLLOW_interface__in_interface_def196);
            interface_();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_id_in_interface_def198);
            id3=id();

            state._fsp--;
            if (state.failed) return retval;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:46:46: ( generic )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==LT) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: generic
                    {
                    pushFollow(FOLLOW_generic_in_interface_def200);
                    generic();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;

            }

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:46:55: ( extends_ class_name )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==EXTENDS) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:46:56: extends_ class_name
                    {
                    pushFollow(FOLLOW_extends__in_interface_def204);
                    extends_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_interface_def206);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;

            }

            pushFollow(FOLLOW_interface_block_in_interface_def210);
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

    public static class annotation_def_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "annotation_def"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:50:1: annotation_def : ( annotation )* ( class_modifier )* annotation_ id interface_block ;
    public final JavaParser.annotation_def_return annotation_def() throws RecognitionException {
        JavaParser.annotation_def_return retval = new JavaParser.annotation_def_return();
        retval.start = input.LT(1);

        JavaParser.id_return id4 = null;


        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:51:2: ( ( annotation )* ( class_modifier )* annotation_ id interface_block )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:51:4: ( annotation )* ( class_modifier )* annotation_ id interface_block
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:51:4: ( annotation )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==AT) ) {
                    int LA17_2 = input.LA(2);

                    if ( (LA17_2==ID) ) {
                        alt17=1;
                    }


                }


                switch (alt17) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_annotation_def226);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:51:16: ( class_modifier )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( ((LA18_0>=PUBLIC && LA18_0<=FINAL)||LA18_0==STATIC||LA18_0==ABSTRACT) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: class_modifier
            	    {
            	    pushFollow(FOLLOW_class_modifier_in_annotation_def229);
            	    class_modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

            pushFollow(FOLLOW_annotation__in_annotation_def232);
            annotation_();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_id_in_annotation_def234);
            id4=id();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_interface_block_in_annotation_def236);
            interface_block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerRange(CodeRangeType.INTERFACE, (id4!=null?input.toString(id4.start,id4.stop):null), input.toString(retval.start,input.LT(-1)), retval.start.getTokenIndex(), input.LT(-1).getTokenIndex());
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
    // $ANTLR end "annotation_def"


    // $ANTLR start "static_init"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:55:1: static_init : static_ block_begin ( code )* block_end ( semicolon )? ;
    public final void static_init() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:56:2: ( static_ block_begin ( code )* block_end ( semicolon )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:56:4: static_ block_begin ( code )* block_end ( semicolon )?
            {
            pushFollow(FOLLOW_static__in_static_init252);
            static_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_block_begin_in_static_init254);
            block_begin();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:56:24: ( code )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( ((LA19_0>=VOID && LA19_0<=CONTINUE)||(LA19_0>=THIS && LA19_0<=IF)||LA19_0==SWITCH||LA19_0==TRY||LA19_0==THROW||LA19_0==FINAL||LA19_0==SYNCHRONIZED||(LA19_0>=BOOLEAN && LA19_0<=OPEN_CURLY_BRACKET)||(LA19_0>=INC && LA19_0<=MINUS)||LA19_0==NOT||(LA19_0>=SEMICOLON && LA19_0<=AT)||LA19_0==ID) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: code
            	    {
            	    pushFollow(FOLLOW_code_in_static_init256);
            	    code();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            pushFollow(FOLLOW_block_end_in_static_init259);
            block_end();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:56:40: ( semicolon )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==SEMICOLON) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: semicolon
                    {
                    pushFollow(FOLLOW_semicolon_in_static_init261);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:59:1: constructor_def : ( annotation )* ( method_modifier )* id open_bracket argument_def close_bracket ( throws_ class_name ( comma class_name )* )? code ;
    public final JavaParser.constructor_def_return constructor_def() throws RecognitionException {
        JavaParser.constructor_def_return retval = new JavaParser.constructor_def_return();
        retval.start = input.LT(1);

        JavaParser.id_return id5 = null;


        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:60:2: ( ( annotation )* ( method_modifier )* id open_bracket argument_def close_bracket ( throws_ class_name ( comma class_name )* )? code )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:60:4: ( annotation )* ( method_modifier )* id open_bracket argument_def close_bracket ( throws_ class_name ( comma class_name )* )? code
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:60:4: ( annotation )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==AT) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_constructor_def273);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:60:16: ( method_modifier )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0>=PUBLIC && LA22_0<=FINAL)||(LA22_0>=SYNCHRONIZED && LA22_0<=ABSTRACT)) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: method_modifier
            	    {
            	    pushFollow(FOLLOW_method_modifier_in_constructor_def276);
            	    method_modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            pushFollow(FOLLOW_id_in_constructor_def279);
            id5=id();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_open_bracket_in_constructor_def281);
            open_bracket();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_argument_def_in_constructor_def283);
            argument_def();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_close_bracket_in_constructor_def285);
            close_bracket();

            state._fsp--;
            if (state.failed) return retval;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:60:76: ( throws_ class_name ( comma class_name )* )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==THROWS) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:60:77: throws_ class_name ( comma class_name )*
                    {
                    pushFollow(FOLLOW_throws__in_constructor_def288);
                    throws_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_constructor_def290);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:60:96: ( comma class_name )*
                    loop23:
                    do {
                        int alt23=2;
                        int LA23_0 = input.LA(1);

                        if ( (LA23_0==COMMA) ) {
                            alt23=1;
                        }


                        switch (alt23) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:60:97: comma class_name
                    	    {
                    	    pushFollow(FOLLOW_comma_in_constructor_def293);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    pushFollow(FOLLOW_class_name_in_constructor_def295);
                    	    class_name();

                    	    state._fsp--;
                    	    if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop23;
                        }
                    } while (true);


                    }
                    break;

            }

            pushFollow(FOLLOW_code_in_constructor_def301);
            code();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerRange(CodeRangeType.CONSTRUCTOR, (id5!=null?input.toString(id5.start,id5.stop):null), input.toString(retval.start,input.LT(-1)), retval.start.getTokenIndex(), input.LT(-1).getTokenIndex());
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:64:1: method_def : ( annotation )* ( method_modifier )* variable_type id open_bracket argument_def close_bracket ( throws_ class_name ( comma class_name )* )? code ;
    public final JavaParser.method_def_return method_def() throws RecognitionException {
        JavaParser.method_def_return retval = new JavaParser.method_def_return();
        retval.start = input.LT(1);

        JavaParser.id_return id6 = null;


        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:65:2: ( ( annotation )* ( method_modifier )* variable_type id open_bracket argument_def close_bracket ( throws_ class_name ( comma class_name )* )? code )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:65:4: ( annotation )* ( method_modifier )* variable_type id open_bracket argument_def close_bracket ( throws_ class_name ( comma class_name )* )? code
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:65:4: ( annotation )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==AT) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_method_def316);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:65:16: ( method_modifier )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==FINAL) ) {
                    int LA26_1 = input.LA(2);

                    if ( (synpred29_JavaParser()) ) {
                        alt26=1;
                    }


                }
                else if ( ((LA26_0>=PUBLIC && LA26_0<=PROTECTED)||(LA26_0>=SYNCHRONIZED && LA26_0<=ABSTRACT)) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: method_modifier
            	    {
            	    pushFollow(FOLLOW_method_modifier_in_method_def319);
            	    method_modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);

            pushFollow(FOLLOW_variable_type_in_method_def322);
            variable_type();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_id_in_method_def324);
            id6=id();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_open_bracket_in_method_def326);
            open_bracket();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_argument_def_in_method_def328);
            argument_def();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_close_bracket_in_method_def330);
            close_bracket();

            state._fsp--;
            if (state.failed) return retval;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:65:90: ( throws_ class_name ( comma class_name )* )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==THROWS) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:65:91: throws_ class_name ( comma class_name )*
                    {
                    pushFollow(FOLLOW_throws__in_method_def333);
                    throws_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_method_def335);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:65:110: ( comma class_name )*
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==COMMA) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:65:111: comma class_name
                    	    {
                    	    pushFollow(FOLLOW_comma_in_method_def338);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    pushFollow(FOLLOW_class_name_in_method_def340);
                    	    class_name();

                    	    state._fsp--;
                    	    if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop27;
                        }
                    } while (true);


                    }
                    break;

            }

            pushFollow(FOLLOW_code_in_method_def346);
            code();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              helper.registerRange(CodeRangeType.METHOD, (id6!=null?input.toString(id6.start,id6.stop):null), input.toString(retval.start,input.LT(-1)), retval.start.getTokenIndex(), input.LT(-1).getTokenIndex());
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:69:1: field_def : ( annotation )* ( field_modifier )* variable_type variable_name ( assign value )? semicolon ;
    public final void field_def() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:2: ( ( annotation )* ( field_modifier )* variable_type variable_name ( assign value )? semicolon )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:4: ( annotation )* ( field_modifier )* variable_type variable_name ( assign value )? semicolon
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:4: ( annotation )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==AT) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_field_def362);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop29;
                }
            } while (true);

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:16: ( field_modifier )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( ((LA30_0>=PUBLIC && LA30_0<=PROTECTED)||LA30_0==VOLATILE||(LA30_0>=STATIC && LA30_0<=TRANSIENT)) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: field_modifier
            	    {
            	    pushFollow(FOLLOW_field_modifier_in_field_def365);
            	    field_modifier();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

            pushFollow(FOLLOW_variable_type_in_field_def368);
            variable_type();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_variable_name_in_field_def370);
            variable_name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:60: ( assign value )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( ((LA31_0>=ASSIGN && LA31_0<=BITANDASSIGN)) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:70:61: assign value
                    {
                    pushFollow(FOLLOW_assign_in_field_def373);
                    assign();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_field_def375);
                    value();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_semicolon_in_field_def379);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:73:1: argument_def : ( variable_type ( dot dot dot )? variable_name ( comma variable_type ( dot dot dot )? variable_name )* )? ;
    public final void argument_def() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:74:2: ( ( variable_type ( dot dot dot )? variable_name ( comma variable_type ( dot dot dot )? variable_name )* )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:74:4: ( variable_type ( dot dot dot )? variable_name ( comma variable_type ( dot dot dot )? variable_name )* )?
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:74:4: ( variable_type ( dot dot dot )? variable_name ( comma variable_type ( dot dot dot )? variable_name )* )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==VOID||LA35_0==FINAL||(LA35_0>=BOOLEAN && LA35_0<=DOUBLE)||LA35_0==ID) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:74:5: variable_type ( dot dot dot )? variable_name ( comma variable_type ( dot dot dot )? variable_name )*
                    {
                    pushFollow(FOLLOW_variable_type_in_argument_def391);
                    variable_type();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:74:19: ( dot dot dot )?
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( (LA32_0==DOT) ) {
                        alt32=1;
                    }
                    switch (alt32) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:74:20: dot dot dot
                            {
                            pushFollow(FOLLOW_dot_in_argument_def394);
                            dot();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_dot_in_argument_def396);
                            dot();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_dot_in_argument_def398);
                            dot();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_variable_name_in_argument_def402);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:74:48: ( comma variable_type ( dot dot dot )? variable_name )*
                    loop34:
                    do {
                        int alt34=2;
                        int LA34_0 = input.LA(1);

                        if ( (LA34_0==COMMA) ) {
                            alt34=1;
                        }


                        switch (alt34) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:74:49: comma variable_type ( dot dot dot )? variable_name
                    	    {
                    	    pushFollow(FOLLOW_comma_in_argument_def405);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_variable_type_in_argument_def407);
                    	    variable_type();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:74:69: ( dot dot dot )?
                    	    int alt33=2;
                    	    int LA33_0 = input.LA(1);

                    	    if ( (LA33_0==DOT) ) {
                    	        alt33=1;
                    	    }
                    	    switch (alt33) {
                    	        case 1 :
                    	            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:74:70: dot dot dot
                    	            {
                    	            pushFollow(FOLLOW_dot_in_argument_def410);
                    	            dot();

                    	            state._fsp--;
                    	            if (state.failed) return ;
                    	            pushFollow(FOLLOW_dot_in_argument_def412);
                    	            dot();

                    	            state._fsp--;
                    	            if (state.failed) return ;
                    	            pushFollow(FOLLOW_dot_in_argument_def414);
                    	            dot();

                    	            state._fsp--;
                    	            if (state.failed) return ;

                    	            }
                    	            break;

                    	    }

                    	    pushFollow(FOLLOW_variable_name_in_argument_def418);
                    	    variable_name();

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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:77:1: variable_def : ( annotation )* variable_type variable_name ( assign value )? ;
    public final void variable_def() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:78:2: ( ( annotation )* variable_type variable_name ( assign value )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:78:4: ( annotation )* variable_type variable_name ( assign value )?
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:78:4: ( annotation )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==AT) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_variable_def433);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);

            pushFollow(FOLLOW_variable_type_in_variable_def436);
            variable_type();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_variable_name_in_variable_def438);
            variable_name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:78:44: ( assign value )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( ((LA37_0>=ASSIGN && LA37_0<=BITANDASSIGN)) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:78:45: assign value
                    {
                    pushFollow(FOLLOW_assign_in_variable_def441);
                    assign();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_variable_def443);
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


    // $ANTLR start "class_modifier"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:81:1: class_modifier : ( public_ | private_ | protected_ | static_ | abstract_ | final_ );
    public final void class_modifier() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:82:2: ( public_ | private_ | protected_ | static_ | abstract_ | final_ )
            int alt38=6;
            switch ( input.LA(1) ) {
            case PUBLIC:
                {
                alt38=1;
                }
                break;
            case PRIVATE:
                {
                alt38=2;
                }
                break;
            case PROTECTED:
                {
                alt38=3;
                }
                break;
            case STATIC:
                {
                alt38=4;
                }
                break;
            case ABSTRACT:
                {
                alt38=5;
                }
                break;
            case FINAL:
                {
                alt38=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }

            switch (alt38) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:82:4: public_
                    {
                    pushFollow(FOLLOW_public__in_class_modifier457);
                    public_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:83:4: private_
                    {
                    pushFollow(FOLLOW_private__in_class_modifier462);
                    private_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:84:4: protected_
                    {
                    pushFollow(FOLLOW_protected__in_class_modifier467);
                    protected_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:85:4: static_
                    {
                    pushFollow(FOLLOW_static__in_class_modifier472);
                    static_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:86:4: abstract_
                    {
                    pushFollow(FOLLOW_abstract__in_class_modifier477);
                    abstract_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:87:4: final_
                    {
                    pushFollow(FOLLOW_final__in_class_modifier482);
                    final_();

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
    // $ANTLR end "class_modifier"


    // $ANTLR start "method_modifier"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:90:1: method_modifier : ( public_ | private_ | protected_ | static_ | final_ | transient_ | synchronized_ | abstract_ );
    public final void method_modifier() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:91:2: ( public_ | private_ | protected_ | static_ | final_ | transient_ | synchronized_ | abstract_ )
            int alt39=8;
            switch ( input.LA(1) ) {
            case PUBLIC:
                {
                alt39=1;
                }
                break;
            case PRIVATE:
                {
                alt39=2;
                }
                break;
            case PROTECTED:
                {
                alt39=3;
                }
                break;
            case STATIC:
                {
                alt39=4;
                }
                break;
            case FINAL:
                {
                alt39=5;
                }
                break;
            case TRANSIENT:
                {
                alt39=6;
                }
                break;
            case SYNCHRONIZED:
                {
                alt39=7;
                }
                break;
            case ABSTRACT:
                {
                alt39=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;
            }

            switch (alt39) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:91:4: public_
                    {
                    pushFollow(FOLLOW_public__in_method_modifier493);
                    public_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:92:4: private_
                    {
                    pushFollow(FOLLOW_private__in_method_modifier498);
                    private_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:93:4: protected_
                    {
                    pushFollow(FOLLOW_protected__in_method_modifier503);
                    protected_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:94:4: static_
                    {
                    pushFollow(FOLLOW_static__in_method_modifier508);
                    static_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:95:4: final_
                    {
                    pushFollow(FOLLOW_final__in_method_modifier513);
                    final_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:96:4: transient_
                    {
                    pushFollow(FOLLOW_transient__in_method_modifier518);
                    transient_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:97:4: synchronized_
                    {
                    pushFollow(FOLLOW_synchronized__in_method_modifier523);
                    synchronized_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 8 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:98:4: abstract_
                    {
                    pushFollow(FOLLOW_abstract__in_method_modifier528);
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
    // $ANTLR end "method_modifier"


    // $ANTLR start "field_modifier"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:101:1: field_modifier : ( public_ | private_ | protected_ | static_ | transient_ | volatile_ );
    public final void field_modifier() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:102:2: ( public_ | private_ | protected_ | static_ | transient_ | volatile_ )
            int alt40=6;
            switch ( input.LA(1) ) {
            case PUBLIC:
                {
                alt40=1;
                }
                break;
            case PRIVATE:
                {
                alt40=2;
                }
                break;
            case PROTECTED:
                {
                alt40=3;
                }
                break;
            case STATIC:
                {
                alt40=4;
                }
                break;
            case TRANSIENT:
                {
                alt40=5;
                }
                break;
            case VOLATILE:
                {
                alt40=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;
            }

            switch (alt40) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:102:4: public_
                    {
                    pushFollow(FOLLOW_public__in_field_modifier539);
                    public_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:103:4: private_
                    {
                    pushFollow(FOLLOW_private__in_field_modifier544);
                    private_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:104:4: protected_
                    {
                    pushFollow(FOLLOW_protected__in_field_modifier549);
                    protected_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:105:4: static_
                    {
                    pushFollow(FOLLOW_static__in_field_modifier554);
                    static_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:106:4: transient_
                    {
                    pushFollow(FOLLOW_transient__in_field_modifier559);
                    transient_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:107:4: volatile_
                    {
                    pushFollow(FOLLOW_volatile__in_field_modifier564);
                    volatile_();

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
    // $ANTLR end "field_modifier"


    // $ANTLR start "variable_modifier"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:110:1: variable_modifier : final_ ;
    public final void variable_modifier() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:111:2: ( final_ )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:111:4: final_
            {
            pushFollow(FOLLOW_final__in_variable_modifier575);
            final_();

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
    // $ANTLR end "variable_modifier"


    // $ANTLR start "class_block"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:114:1: class_block : block_begin ( class_def | static_init | constructor_def | method_def | field_def )* block_end ( semicolon )? ;
    public final void class_block() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:115:2: ( block_begin ( class_def | static_init | constructor_def | method_def | field_def )* block_end ( semicolon )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:115:4: block_begin ( class_def | static_init | constructor_def | method_def | field_def )* block_end ( semicolon )?
            {
            pushFollow(FOLLOW_block_begin_in_class_block586);
            block_begin();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:115:16: ( class_def | static_init | constructor_def | method_def | field_def )*
            loop41:
            do {
                int alt41=6;
                alt41 = dfa41.predict(input);
                switch (alt41) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:115:17: class_def
            	    {
            	    pushFollow(FOLLOW_class_def_in_class_block589);
            	    class_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 2 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:115:29: static_init
            	    {
            	    pushFollow(FOLLOW_static_init_in_class_block593);
            	    static_init();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 3 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:115:43: constructor_def
            	    {
            	    pushFollow(FOLLOW_constructor_def_in_class_block597);
            	    constructor_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 4 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:115:61: method_def
            	    {
            	    pushFollow(FOLLOW_method_def_in_class_block601);
            	    method_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 5 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:115:74: field_def
            	    {
            	    pushFollow(FOLLOW_field_def_in_class_block605);
            	    field_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);

            pushFollow(FOLLOW_block_end_in_class_block609);
            block_end();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:115:96: ( semicolon )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==SEMICOLON) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: semicolon
                    {
                    pushFollow(FOLLOW_semicolon_in_class_block611);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:118:1: enum_block : block_begin ( class_def | constructor_def | method_def | field_def | enum_content )* block_end ( semicolon )? ;
    public final void enum_block() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:2: ( block_begin ( class_def | constructor_def | method_def | field_def | enum_content )* block_end ( semicolon )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:4: block_begin ( class_def | constructor_def | method_def | field_def | enum_content )* block_end ( semicolon )?
            {
            pushFollow(FOLLOW_block_begin_in_enum_block624);
            block_begin();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:16: ( class_def | constructor_def | method_def | field_def | enum_content )*
            loop43:
            do {
                int alt43=6;
                alt43 = dfa43.predict(input);
                switch (alt43) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:17: class_def
            	    {
            	    pushFollow(FOLLOW_class_def_in_enum_block627);
            	    class_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 2 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:29: constructor_def
            	    {
            	    pushFollow(FOLLOW_constructor_def_in_enum_block631);
            	    constructor_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 3 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:47: method_def
            	    {
            	    pushFollow(FOLLOW_method_def_in_enum_block635);
            	    method_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 4 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:60: field_def
            	    {
            	    pushFollow(FOLLOW_field_def_in_enum_block639);
            	    field_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 5 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:72: enum_content
            	    {
            	    pushFollow(FOLLOW_enum_content_in_enum_block643);
            	    enum_content();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop43;
                }
            } while (true);

            pushFollow(FOLLOW_block_end_in_enum_block647);
            block_end();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:97: ( semicolon )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==SEMICOLON) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: semicolon
                    {
                    pushFollow(FOLLOW_semicolon_in_enum_block649);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:122:1: enum_content : id ( open_bracket arguments close_bracket )? ( comma id ( open_bracket arguments close_bracket )? )* semicolon ;
    public final void enum_content() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:123:2: ( id ( open_bracket arguments close_bracket )? ( comma id ( open_bracket arguments close_bracket )? )* semicolon )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:123:4: id ( open_bracket arguments close_bracket )? ( comma id ( open_bracket arguments close_bracket )? )* semicolon
            {
            pushFollow(FOLLOW_id_in_enum_content661);
            id();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:123:7: ( open_bracket arguments close_bracket )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==OPEN_BRACKET) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:123:8: open_bracket arguments close_bracket
                    {
                    pushFollow(FOLLOW_open_bracket_in_enum_content664);
                    open_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_arguments_in_enum_content666);
                    arguments();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_close_bracket_in_enum_content668);
                    close_bracket();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:123:47: ( comma id ( open_bracket arguments close_bracket )? )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( (LA47_0==COMMA) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:123:48: comma id ( open_bracket arguments close_bracket )?
            	    {
            	    pushFollow(FOLLOW_comma_in_enum_content673);
            	    comma();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_enum_content675);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:123:57: ( open_bracket arguments close_bracket )?
            	    int alt46=2;
            	    int LA46_0 = input.LA(1);

            	    if ( (LA46_0==OPEN_BRACKET) ) {
            	        alt46=1;
            	    }
            	    switch (alt46) {
            	        case 1 :
            	            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:123:58: open_bracket arguments close_bracket
            	            {
            	            pushFollow(FOLLOW_open_bracket_in_enum_content678);
            	            open_bracket();

            	            state._fsp--;
            	            if (state.failed) return ;
            	            pushFollow(FOLLOW_arguments_in_enum_content680);
            	            arguments();

            	            state._fsp--;
            	            if (state.failed) return ;
            	            pushFollow(FOLLOW_close_bracket_in_enum_content682);
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

            pushFollow(FOLLOW_semicolon_in_enum_content688);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:126:1: interface_block : block_begin ( ( annotation )* ( method_modifier )* variable_type id open_bracket argument_def close_bracket ( throws_ class_name ( comma class_name )* )? semicolon )* block_end ( semicolon )? ;
    public final void interface_block() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:127:2: ( block_begin ( ( annotation )* ( method_modifier )* variable_type id open_bracket argument_def close_bracket ( throws_ class_name ( comma class_name )* )? semicolon )* block_end ( semicolon )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:127:4: block_begin ( ( annotation )* ( method_modifier )* variable_type id open_bracket argument_def close_bracket ( throws_ class_name ( comma class_name )* )? semicolon )* block_end ( semicolon )?
            {
            pushFollow(FOLLOW_block_begin_in_interface_block700);
            block_begin();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:127:16: ( ( annotation )* ( method_modifier )* variable_type id open_bracket argument_def close_bracket ( throws_ class_name ( comma class_name )* )? semicolon )*
            loop52:
            do {
                int alt52=2;
                int LA52_0 = input.LA(1);

                if ( (LA52_0==VOID||(LA52_0>=PUBLIC && LA52_0<=FINAL)||(LA52_0>=SYNCHRONIZED && LA52_0<=DOUBLE)||LA52_0==AT||LA52_0==ID) ) {
                    alt52=1;
                }


                switch (alt52) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:127:17: ( annotation )* ( method_modifier )* variable_type id open_bracket argument_def close_bracket ( throws_ class_name ( comma class_name )* )? semicolon
            	    {
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:127:17: ( annotation )*
            	    loop48:
            	    do {
            	        int alt48=2;
            	        int LA48_0 = input.LA(1);

            	        if ( (LA48_0==AT) ) {
            	            alt48=1;
            	        }


            	        switch (alt48) {
            	    	case 1 :
            	    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    	    {
            	    	    pushFollow(FOLLOW_annotation_in_interface_block703);
            	    	    annotation();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop48;
            	        }
            	    } while (true);

            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:127:29: ( method_modifier )*
            	    loop49:
            	    do {
            	        int alt49=2;
            	        int LA49_0 = input.LA(1);

            	        if ( (LA49_0==FINAL) ) {
            	            int LA49_1 = input.LA(2);

            	            if ( (synpred74_JavaParser()) ) {
            	                alt49=1;
            	            }


            	        }
            	        else if ( ((LA49_0>=PUBLIC && LA49_0<=PROTECTED)||(LA49_0>=SYNCHRONIZED && LA49_0<=ABSTRACT)) ) {
            	            alt49=1;
            	        }


            	        switch (alt49) {
            	    	case 1 :
            	    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: method_modifier
            	    	    {
            	    	    pushFollow(FOLLOW_method_modifier_in_interface_block706);
            	    	    method_modifier();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop49;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_variable_type_in_interface_block709);
            	    variable_type();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_interface_block711);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_open_bracket_in_interface_block713);
            	    open_bracket();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_argument_def_in_interface_block715);
            	    argument_def();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_close_bracket_in_interface_block717);
            	    close_bracket();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:127:103: ( throws_ class_name ( comma class_name )* )?
            	    int alt51=2;
            	    int LA51_0 = input.LA(1);

            	    if ( (LA51_0==THROWS) ) {
            	        alt51=1;
            	    }
            	    switch (alt51) {
            	        case 1 :
            	            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:127:104: throws_ class_name ( comma class_name )*
            	            {
            	            pushFollow(FOLLOW_throws__in_interface_block720);
            	            throws_();

            	            state._fsp--;
            	            if (state.failed) return ;
            	            pushFollow(FOLLOW_class_name_in_interface_block722);
            	            class_name();

            	            state._fsp--;
            	            if (state.failed) return ;
            	            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:127:123: ( comma class_name )*
            	            loop50:
            	            do {
            	                int alt50=2;
            	                int LA50_0 = input.LA(1);

            	                if ( (LA50_0==COMMA) ) {
            	                    alt50=1;
            	                }


            	                switch (alt50) {
            	            	case 1 :
            	            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:127:124: comma class_name
            	            	    {
            	            	    pushFollow(FOLLOW_comma_in_interface_block725);
            	            	    comma();

            	            	    state._fsp--;
            	            	    if (state.failed) return ;
            	            	    pushFollow(FOLLOW_class_name_in_interface_block727);
            	            	    class_name();

            	            	    state._fsp--;
            	            	    if (state.failed) return ;

            	            	    }
            	            	    break;

            	            	default :
            	            	    break loop50;
            	                }
            	            } while (true);


            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_semicolon_in_interface_block733);
            	    semicolon();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop52;
                }
            } while (true);

            pushFollow(FOLLOW_block_end_in_interface_block737);
            block_end();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:127:167: ( semicolon )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==SEMICOLON) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: semicolon
                    {
                    pushFollow(FOLLOW_semicolon_in_interface_block739);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:130:1: method_call : method_name open_bracket arguments close_bracket ( dot ( id | class_ ) ( open_bracket arguments close_bracket )? )* ;
    public final void method_call() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:131:2: ( method_name open_bracket arguments close_bracket ( dot ( id | class_ ) ( open_bracket arguments close_bracket )? )* )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:131:4: method_name open_bracket arguments close_bracket ( dot ( id | class_ ) ( open_bracket arguments close_bracket )? )*
            {
            pushFollow(FOLLOW_method_name_in_method_call752);
            method_name();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_open_bracket_in_method_call754);
            open_bracket();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_arguments_in_method_call756);
            arguments();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_close_bracket_in_method_call758);
            close_bracket();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:131:53: ( dot ( id | class_ ) ( open_bracket arguments close_bracket )? )*
            loop56:
            do {
                int alt56=2;
                int LA56_0 = input.LA(1);

                if ( (LA56_0==DOT) ) {
                    alt56=1;
                }


                switch (alt56) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:131:54: dot ( id | class_ ) ( open_bracket arguments close_bracket )?
            	    {
            	    pushFollow(FOLLOW_dot_in_method_call761);
            	    dot();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:131:58: ( id | class_ )
            	    int alt54=2;
            	    int LA54_0 = input.LA(1);

            	    if ( (LA54_0==ID) ) {
            	        alt54=1;
            	    }
            	    else if ( (LA54_0==CLASS) ) {
            	        alt54=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 54, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt54) {
            	        case 1 :
            	            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:131:59: id
            	            {
            	            pushFollow(FOLLOW_id_in_method_call764);
            	            id();

            	            state._fsp--;
            	            if (state.failed) return ;

            	            }
            	            break;
            	        case 2 :
            	            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:131:64: class_
            	            {
            	            pushFollow(FOLLOW_class__in_method_call768);
            	            class_();

            	            state._fsp--;
            	            if (state.failed) return ;

            	            }
            	            break;

            	    }

            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:131:72: ( open_bracket arguments close_bracket )?
            	    int alt55=2;
            	    int LA55_0 = input.LA(1);

            	    if ( (LA55_0==OPEN_BRACKET) ) {
            	        alt55=1;
            	    }
            	    switch (alt55) {
            	        case 1 :
            	            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:131:73: open_bracket arguments close_bracket
            	            {
            	            pushFollow(FOLLOW_open_bracket_in_method_call772);
            	            open_bracket();

            	            state._fsp--;
            	            if (state.failed) return ;
            	            pushFollow(FOLLOW_arguments_in_method_call774);
            	            arguments();

            	            state._fsp--;
            	            if (state.failed) return ;
            	            pushFollow(FOLLOW_close_bracket_in_method_call776);
            	            close_bracket();

            	            state._fsp--;
            	            if (state.failed) return ;

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop56;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:134:1: annotation : annotation_name ( open_bracket value ( comma value )* close_bracket )? ;
    public final void annotation() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:135:2: ( annotation_name ( open_bracket value ( comma value )* close_bracket )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:135:4: annotation_name ( open_bracket value ( comma value )* close_bracket )?
            {
            pushFollow(FOLLOW_annotation_name_in_annotation791);
            annotation_name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:135:20: ( open_bracket value ( comma value )* close_bracket )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==OPEN_BRACKET) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:135:21: open_bracket value ( comma value )* close_bracket
                    {
                    pushFollow(FOLLOW_open_bracket_in_annotation794);
                    open_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_annotation796);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:135:40: ( comma value )*
                    loop57:
                    do {
                        int alt57=2;
                        int LA57_0 = input.LA(1);

                        if ( (LA57_0==COMMA) ) {
                            alt57=1;
                        }


                        switch (alt57) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:135:41: comma value
                    	    {
                    	    pushFollow(FOLLOW_comma_in_annotation799);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_value_in_annotation801);
                    	    value();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop57;
                        }
                    } while (true);

                    pushFollow(FOLLOW_close_bracket_in_annotation805);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:138:1: generic : LT ( variable_type | question_ ) ( comma ( variable_type | question_ ) )* GT ;
    public final void generic() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:138:9: ( LT ( variable_type | question_ ) ( comma ( variable_type | question_ ) )* GT )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:138:11: LT ( variable_type | question_ ) ( comma ( variable_type | question_ ) )* GT
            {
            match(input,LT,FOLLOW_LT_in_generic817); if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:138:14: ( variable_type | question_ )
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==VOID||LA59_0==FINAL||(LA59_0>=BOOLEAN && LA59_0<=DOUBLE)||LA59_0==ID) ) {
                alt59=1;
            }
            else if ( (LA59_0==QUESTION) ) {
                alt59=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 59, 0, input);

                throw nvae;
            }
            switch (alt59) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:138:15: variable_type
                    {
                    pushFollow(FOLLOW_variable_type_in_generic820);
                    variable_type();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:138:31: question_
                    {
                    pushFollow(FOLLOW_question__in_generic824);
                    question_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:138:42: ( comma ( variable_type | question_ ) )*
            loop61:
            do {
                int alt61=2;
                int LA61_0 = input.LA(1);

                if ( (LA61_0==COMMA) ) {
                    alt61=1;
                }


                switch (alt61) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:138:43: comma ( variable_type | question_ )
            	    {
            	    pushFollow(FOLLOW_comma_in_generic828);
            	    comma();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:138:49: ( variable_type | question_ )
            	    int alt60=2;
            	    int LA60_0 = input.LA(1);

            	    if ( (LA60_0==VOID||LA60_0==FINAL||(LA60_0>=BOOLEAN && LA60_0<=DOUBLE)||LA60_0==ID) ) {
            	        alt60=1;
            	    }
            	    else if ( (LA60_0==QUESTION) ) {
            	        alt60=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 60, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt60) {
            	        case 1 :
            	            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:138:50: variable_type
            	            {
            	            pushFollow(FOLLOW_variable_type_in_generic831);
            	            variable_type();

            	            state._fsp--;
            	            if (state.failed) return ;

            	            }
            	            break;
            	        case 2 :
            	            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:138:66: question_
            	            {
            	            pushFollow(FOLLOW_question__in_generic835);
            	            question_();

            	            state._fsp--;
            	            if (state.failed) return ;

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop61;
                }
            } while (true);

            match(input,GT,FOLLOW_GT_in_generic840); if (state.failed) return ;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:142:1: value : ( ( cast )? ( left_unary )? ( single_value | open_bracket value close_bracket ) ( right_unary )? ( binary_operator value )? ( question_ value colon value )? | ( left_unary )? ( single_value | open_bracket value close_bracket ) ( right_unary )? ( binary_operator value )? ( question_ value colon value )? );
    public final void value() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:142:7: ( ( cast )? ( left_unary )? ( single_value | open_bracket value close_bracket ) ( right_unary )? ( binary_operator value )? ( question_ value colon value )? | ( left_unary )? ( single_value | open_bracket value close_bracket ) ( right_unary )? ( binary_operator value )? ( question_ value colon value )? )
            int alt73=2;
            alt73 = dfa73.predict(input);
            switch (alt73) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:142:9: ( cast )? ( left_unary )? ( single_value | open_bracket value close_bracket ) ( right_unary )? ( binary_operator value )? ( question_ value colon value )?
                    {
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:142:9: ( cast )?
                    int alt62=2;
                    alt62 = dfa62.predict(input);
                    switch (alt62) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: cast
                            {
                            pushFollow(FOLLOW_cast_in_value853);
                            cast();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:142:15: ( left_unary )?
                    int alt63=2;
                    int LA63_0 = input.LA(1);

                    if ( ((LA63_0>=INC && LA63_0<=MINUS)||LA63_0==NOT) ) {
                        alt63=1;
                    }
                    switch (alt63) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: left_unary
                            {
                            pushFollow(FOLLOW_left_unary_in_value856);
                            left_unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:143:3: ( single_value | open_bracket value close_bracket )
                    int alt64=2;
                    int LA64_0 = input.LA(1);

                    if ( ((LA64_0>=VOID && LA64_0<=NEW)||(LA64_0>=NULL && LA64_0<=SUPER)||(LA64_0>=BOOLEAN && LA64_0<=OPEN_CURLY_BRACKET)||(LA64_0>=BOOL_CONST && LA64_0<=INT_CONST)||(LA64_0>=HEX_CONST && LA64_0<=HEX_LONG_CONST)||LA64_0==FLOAT_CONST||(LA64_0>=STRING_CONST && LA64_0<=CHAR_CONST)) ) {
                        alt64=1;
                    }
                    else if ( (LA64_0==OPEN_BRACKET) ) {
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
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:143:4: single_value
                            {
                            pushFollow(FOLLOW_single_value_in_value863);
                            single_value();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;
                        case 2 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:143:19: open_bracket value close_bracket
                            {
                            pushFollow(FOLLOW_open_bracket_in_value867);
                            open_bracket();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_value_in_value869);
                            value();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_close_bracket_in_value871);
                            close_bracket();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:144:3: ( right_unary )?
                    int alt65=2;
                    switch ( input.LA(1) ) {
                        case INC:
                            {
                            int LA65_1 = input.LA(2);

                            if ( (synpred90_JavaParser()) ) {
                                alt65=1;
                            }
                            }
                            break;
                        case DEC:
                            {
                            int LA65_2 = input.LA(2);

                            if ( (synpred90_JavaParser()) ) {
                                alt65=1;
                            }
                            }
                            break;
                        case NOT:
                            {
                            int LA65_3 = input.LA(2);

                            if ( (synpred90_JavaParser()) ) {
                                alt65=1;
                            }
                            }
                            break;
                    }

                    switch (alt65) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: right_unary
                            {
                            pushFollow(FOLLOW_right_unary_in_value877);
                            right_unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:145:3: ( binary_operator value )?
                    int alt66=2;
                    alt66 = dfa66.predict(input);
                    switch (alt66) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:145:4: binary_operator value
                            {
                            pushFollow(FOLLOW_binary_operator_in_value884);
                            binary_operator();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_value_in_value886);
                            value();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:146:3: ( question_ value colon value )?
                    int alt67=2;
                    int LA67_0 = input.LA(1);

                    if ( (LA67_0==QUESTION) ) {
                        int LA67_1 = input.LA(2);

                        if ( (synpred92_JavaParser()) ) {
                            alt67=1;
                        }
                    }
                    switch (alt67) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:146:4: question_ value colon value
                            {
                            pushFollow(FOLLOW_question__in_value894);
                            question_();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_value_in_value896);
                            value();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_colon_in_value898);
                            colon();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_value_in_value900);
                            value();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:147:4: ( left_unary )? ( single_value | open_bracket value close_bracket ) ( right_unary )? ( binary_operator value )? ( question_ value colon value )?
                    {
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:147:4: ( left_unary )?
                    int alt68=2;
                    int LA68_0 = input.LA(1);

                    if ( ((LA68_0>=INC && LA68_0<=MINUS)||LA68_0==NOT) ) {
                        alt68=1;
                    }
                    switch (alt68) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: left_unary
                            {
                            pushFollow(FOLLOW_left_unary_in_value907);
                            left_unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:148:3: ( single_value | open_bracket value close_bracket )
                    int alt69=2;
                    int LA69_0 = input.LA(1);

                    if ( ((LA69_0>=VOID && LA69_0<=NEW)||(LA69_0>=NULL && LA69_0<=SUPER)||(LA69_0>=BOOLEAN && LA69_0<=OPEN_CURLY_BRACKET)||(LA69_0>=BOOL_CONST && LA69_0<=INT_CONST)||(LA69_0>=HEX_CONST && LA69_0<=HEX_LONG_CONST)||LA69_0==FLOAT_CONST||(LA69_0>=STRING_CONST && LA69_0<=CHAR_CONST)) ) {
                        alt69=1;
                    }
                    else if ( (LA69_0==OPEN_BRACKET) ) {
                        alt69=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 69, 0, input);

                        throw nvae;
                    }
                    switch (alt69) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:148:4: single_value
                            {
                            pushFollow(FOLLOW_single_value_in_value914);
                            single_value();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;
                        case 2 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:148:19: open_bracket value close_bracket
                            {
                            pushFollow(FOLLOW_open_bracket_in_value918);
                            open_bracket();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_value_in_value920);
                            value();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_close_bracket_in_value922);
                            close_bracket();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:149:3: ( right_unary )?
                    int alt70=2;
                    switch ( input.LA(1) ) {
                        case INC:
                            {
                            int LA70_1 = input.LA(2);

                            if ( (synpred96_JavaParser()) ) {
                                alt70=1;
                            }
                            }
                            break;
                        case DEC:
                            {
                            int LA70_2 = input.LA(2);

                            if ( (synpred96_JavaParser()) ) {
                                alt70=1;
                            }
                            }
                            break;
                        case NOT:
                            {
                            int LA70_3 = input.LA(2);

                            if ( (synpred96_JavaParser()) ) {
                                alt70=1;
                            }
                            }
                            break;
                    }

                    switch (alt70) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: right_unary
                            {
                            pushFollow(FOLLOW_right_unary_in_value928);
                            right_unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:150:3: ( binary_operator value )?
                    int alt71=2;
                    alt71 = dfa71.predict(input);
                    switch (alt71) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:150:4: binary_operator value
                            {
                            pushFollow(FOLLOW_binary_operator_in_value935);
                            binary_operator();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_value_in_value937);
                            value();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:151:3: ( question_ value colon value )?
                    int alt72=2;
                    int LA72_0 = input.LA(1);

                    if ( (LA72_0==QUESTION) ) {
                        int LA72_1 = input.LA(2);

                        if ( (synpred98_JavaParser()) ) {
                            alt72=1;
                        }
                    }
                    switch (alt72) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:151:4: question_ value colon value
                            {
                            pushFollow(FOLLOW_question__in_value945);
                            question_();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_value_in_value947);
                            value();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_colon_in_value949);
                            colon();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_value_in_value951);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:154:1: single_value : ( method_call | variable_assignment | constant | ( ( this_ | super_ ) dot )? variable_name | new_class | this_ | super_ );
    public final void single_value() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:155:2: ( method_call | variable_assignment | constant | ( ( this_ | super_ ) dot )? variable_name | new_class | this_ | super_ )
            int alt76=7;
            alt76 = dfa76.predict(input);
            switch (alt76) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:155:4: method_call
                    {
                    pushFollow(FOLLOW_method_call_in_single_value964);
                    method_call();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:156:5: variable_assignment
                    {
                    pushFollow(FOLLOW_variable_assignment_in_single_value970);
                    variable_assignment();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:157:4: constant
                    {
                    pushFollow(FOLLOW_constant_in_single_value975);
                    constant();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:158:4: ( ( this_ | super_ ) dot )? variable_name
                    {
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:158:4: ( ( this_ | super_ ) dot )?
                    int alt75=2;
                    int LA75_0 = input.LA(1);

                    if ( ((LA75_0>=THIS && LA75_0<=SUPER)) ) {
                        alt75=1;
                    }
                    switch (alt75) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:158:5: ( this_ | super_ ) dot
                            {
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:158:5: ( this_ | super_ )
                            int alt74=2;
                            int LA74_0 = input.LA(1);

                            if ( (LA74_0==THIS) ) {
                                alt74=1;
                            }
                            else if ( (LA74_0==SUPER) ) {
                                alt74=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return ;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 74, 0, input);

                                throw nvae;
                            }
                            switch (alt74) {
                                case 1 :
                                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:158:6: this_
                                    {
                                    pushFollow(FOLLOW_this__in_single_value983);
                                    this_();

                                    state._fsp--;
                                    if (state.failed) return ;

                                    }
                                    break;
                                case 2 :
                                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:158:14: super_
                                    {
                                    pushFollow(FOLLOW_super__in_single_value987);
                                    super_();

                                    state._fsp--;
                                    if (state.failed) return ;

                                    }
                                    break;

                            }

                            pushFollow(FOLLOW_dot_in_single_value990);
                            dot();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_variable_name_in_single_value994);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:159:4: new_class
                    {
                    pushFollow(FOLLOW_new_class_in_single_value999);
                    new_class();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:160:4: this_
                    {
                    pushFollow(FOLLOW_this__in_single_value1004);
                    this_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:161:4: super_
                    {
                    pushFollow(FOLLOW_super__in_single_value1009);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:164:1: constant : ( class_const | hex_long_const | hex_const | long_const | int_const | string_const | float_const | char_const | null_const | boolean_const | array_const );
    public final void constant() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:164:9: ( class_const | hex_long_const | hex_const | long_const | int_const | string_const | float_const | char_const | null_const | boolean_const | array_const )
            int alt77=11;
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
                alt77=1;
                }
                break;
            case HEX_LONG_CONST:
                {
                alt77=2;
                }
                break;
            case HEX_CONST:
                {
                alt77=3;
                }
                break;
            case LONG_CONST:
                {
                alt77=4;
                }
                break;
            case INT_CONST:
                {
                alt77=5;
                }
                break;
            case STRING_CONST:
                {
                alt77=6;
                }
                break;
            case FLOAT_CONST:
                {
                alt77=7;
                }
                break;
            case CHAR_CONST:
                {
                alt77=8;
                }
                break;
            case NULL:
                {
                alt77=9;
                }
                break;
            case BOOL_CONST:
                {
                alt77=10;
                }
                break;
            case OPEN_CURLY_BRACKET:
                {
                alt77=11;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 77, 0, input);

                throw nvae;
            }

            switch (alt77) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:164:11: class_const
                    {
                    pushFollow(FOLLOW_class_const_in_constant1018);
                    class_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:165:4: hex_long_const
                    {
                    pushFollow(FOLLOW_hex_long_const_in_constant1023);
                    hex_long_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:166:4: hex_const
                    {
                    pushFollow(FOLLOW_hex_const_in_constant1028);
                    hex_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:167:4: long_const
                    {
                    pushFollow(FOLLOW_long_const_in_constant1033);
                    long_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:168:4: int_const
                    {
                    pushFollow(FOLLOW_int_const_in_constant1038);
                    int_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:169:4: string_const
                    {
                    pushFollow(FOLLOW_string_const_in_constant1043);
                    string_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:170:4: float_const
                    {
                    pushFollow(FOLLOW_float_const_in_constant1048);
                    float_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 8 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:171:4: char_const
                    {
                    pushFollow(FOLLOW_char_const_in_constant1053);
                    char_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 9 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:172:4: null_const
                    {
                    pushFollow(FOLLOW_null_const_in_constant1058);
                    null_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 10 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:173:4: boolean_const
                    {
                    pushFollow(FOLLOW_boolean_const_in_constant1063);
                    boolean_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 11 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:174:4: array_const
                    {
                    pushFollow(FOLLOW_array_const_in_constant1068);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:177:1: new_class : new_ variable_type ( array_const )? ( open_bracket arguments close_bracket )? ;
    public final void new_class() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:178:2: ( new_ variable_type ( array_const )? ( open_bracket arguments close_bracket )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:178:4: new_ variable_type ( array_const )? ( open_bracket arguments close_bracket )?
            {
            pushFollow(FOLLOW_new__in_new_class1080);
            new_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_variable_type_in_new_class1082);
            variable_type();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:178:23: ( array_const )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==OPEN_CURLY_BRACKET) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: array_const
                    {
                    pushFollow(FOLLOW_array_const_in_new_class1084);
                    array_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:178:36: ( open_bracket arguments close_bracket )?
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==OPEN_BRACKET) ) {
                alt79=1;
            }
            switch (alt79) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:178:37: open_bracket arguments close_bracket
                    {
                    pushFollow(FOLLOW_open_bracket_in_new_class1088);
                    open_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_arguments_in_new_class1090);
                    arguments();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_close_bracket_in_new_class1092);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:181:1: arguments : ( value ( comma value )* )? ;
    public final void arguments() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:182:2: ( ( value ( comma value )* )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:182:4: ( value ( comma value )* )?
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:182:4: ( value ( comma value )* )?
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( ((LA81_0>=VOID && LA81_0<=NEW)||(LA81_0>=NULL && LA81_0<=SUPER)||(LA81_0>=BOOLEAN && LA81_0<=OPEN_CURLY_BRACKET)||(LA81_0>=INC && LA81_0<=MINUS)||LA81_0==NOT||LA81_0==OPEN_BRACKET||(LA81_0>=BOOL_CONST && LA81_0<=INT_CONST)||(LA81_0>=HEX_CONST && LA81_0<=HEX_LONG_CONST)||LA81_0==FLOAT_CONST||(LA81_0>=STRING_CONST && LA81_0<=CHAR_CONST)) ) {
                alt81=1;
            }
            switch (alt81) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:182:5: value ( comma value )*
                    {
                    pushFollow(FOLLOW_value_in_arguments1108);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:182:11: ( comma value )*
                    loop80:
                    do {
                        int alt80=2;
                        int LA80_0 = input.LA(1);

                        if ( (LA80_0==COMMA) ) {
                            alt80=1;
                        }


                        switch (alt80) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:182:12: comma value
                    	    {
                    	    pushFollow(FOLLOW_comma_in_arguments1111);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_value_in_arguments1113);
                    	    value();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop80;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:185:1: code : ( statement | block_begin ( code )* block_end ( semicolon )? );
    public final void code() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:185:6: ( statement | block_begin ( code )* block_end ( semicolon )? )
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( ((LA84_0>=VOID && LA84_0<=CONTINUE)||(LA84_0>=THIS && LA84_0<=IF)||LA84_0==SWITCH||LA84_0==TRY||LA84_0==THROW||LA84_0==FINAL||LA84_0==SYNCHRONIZED||(LA84_0>=BOOLEAN && LA84_0<=DOUBLE)||(LA84_0>=INC && LA84_0<=MINUS)||LA84_0==NOT||(LA84_0>=SEMICOLON && LA84_0<=AT)||LA84_0==ID) ) {
                alt84=1;
            }
            else if ( (LA84_0==OPEN_CURLY_BRACKET) ) {
                alt84=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;
            }
            switch (alt84) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:185:8: statement
                    {
                    pushFollow(FOLLOW_statement_in_code1127);
                    statement();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:186:4: block_begin ( code )* block_end ( semicolon )?
                    {
                    pushFollow(FOLLOW_block_begin_in_code1132);
                    block_begin();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:186:16: ( code )*
                    loop82:
                    do {
                        int alt82=2;
                        int LA82_0 = input.LA(1);

                        if ( ((LA82_0>=VOID && LA82_0<=CONTINUE)||(LA82_0>=THIS && LA82_0<=IF)||LA82_0==SWITCH||LA82_0==TRY||LA82_0==THROW||LA82_0==FINAL||LA82_0==SYNCHRONIZED||(LA82_0>=BOOLEAN && LA82_0<=OPEN_CURLY_BRACKET)||(LA82_0>=INC && LA82_0<=MINUS)||LA82_0==NOT||(LA82_0>=SEMICOLON && LA82_0<=AT)||LA82_0==ID) ) {
                            alt82=1;
                        }


                        switch (alt82) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: code
                    	    {
                    	    pushFollow(FOLLOW_code_in_code1134);
                    	    code();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop82;
                        }
                    } while (true);

                    pushFollow(FOLLOW_block_end_in_code1137);
                    block_end();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:186:32: ( semicolon )?
                    int alt83=2;
                    int LA83_0 = input.LA(1);

                    if ( (LA83_0==SEMICOLON) ) {
                        int LA83_1 = input.LA(2);

                        if ( (synpred123_JavaParser()) ) {
                            alt83=1;
                        }
                    }
                    switch (alt83) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: semicolon
                            {
                            pushFollow(FOLLOW_semicolon_in_code1139);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:189:1: statement : ( label | variable_assignment semicolon | variable_def semicolon | method_call semicolon | semicolon | return_statement | continue_ | break_ | for_loop | while_loop | do_loop semicolon | switch_case | if_else | try_catch | synchronized_block | throw_ value semicolon | ( left_unary )? variable_name ( right_unary )? );
    public final void statement() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:190:2: ( label | variable_assignment semicolon | variable_def semicolon | method_call semicolon | semicolon | return_statement | continue_ | break_ | for_loop | while_loop | do_loop semicolon | switch_case | if_else | try_catch | synchronized_block | throw_ value semicolon | ( left_unary )? variable_name ( right_unary )? )
            int alt87=17;
            alt87 = dfa87.predict(input);
            switch (alt87) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:190:4: label
                    {
                    pushFollow(FOLLOW_label_in_statement1152);
                    label();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:191:4: variable_assignment semicolon
                    {
                    pushFollow(FOLLOW_variable_assignment_in_statement1158);
                    variable_assignment();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement1160);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:192:4: variable_def semicolon
                    {
                    pushFollow(FOLLOW_variable_def_in_statement1165);
                    variable_def();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement1167);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:193:4: method_call semicolon
                    {
                    pushFollow(FOLLOW_method_call_in_statement1172);
                    method_call();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement1174);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:194:5: semicolon
                    {
                    pushFollow(FOLLOW_semicolon_in_statement1180);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:195:4: return_statement
                    {
                    pushFollow(FOLLOW_return_statement_in_statement1185);
                    return_statement();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:196:4: continue_
                    {
                    pushFollow(FOLLOW_continue__in_statement1190);
                    continue_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 8 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:197:4: break_
                    {
                    pushFollow(FOLLOW_break__in_statement1195);
                    break_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 9 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:198:4: for_loop
                    {
                    pushFollow(FOLLOW_for_loop_in_statement1200);
                    for_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 10 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:199:4: while_loop
                    {
                    pushFollow(FOLLOW_while_loop_in_statement1205);
                    while_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 11 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:200:4: do_loop semicolon
                    {
                    pushFollow(FOLLOW_do_loop_in_statement1210);
                    do_loop();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement1212);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 12 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:201:4: switch_case
                    {
                    pushFollow(FOLLOW_switch_case_in_statement1217);
                    switch_case();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 13 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:202:4: if_else
                    {
                    pushFollow(FOLLOW_if_else_in_statement1222);
                    if_else();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 14 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:203:4: try_catch
                    {
                    pushFollow(FOLLOW_try_catch_in_statement1227);
                    try_catch();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 15 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:204:4: synchronized_block
                    {
                    pushFollow(FOLLOW_synchronized_block_in_statement1232);
                    synchronized_block();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 16 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:205:4: throw_ value semicolon
                    {
                    pushFollow(FOLLOW_throw__in_statement1237);
                    throw_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_statement1239);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement1241);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 17 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:206:4: ( left_unary )? variable_name ( right_unary )?
                    {
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:206:4: ( left_unary )?
                    int alt85=2;
                    int LA85_0 = input.LA(1);

                    if ( ((LA85_0>=INC && LA85_0<=MINUS)||LA85_0==NOT) ) {
                        alt85=1;
                    }
                    switch (alt85) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: left_unary
                            {
                            pushFollow(FOLLOW_left_unary_in_statement1246);
                            left_unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_variable_name_in_statement1249);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:206:30: ( right_unary )?
                    int alt86=2;
                    switch ( input.LA(1) ) {
                        case INC:
                            {
                            int LA86_1 = input.LA(2);

                            if ( (synpred141_JavaParser()) ) {
                                alt86=1;
                            }
                            }
                            break;
                        case DEC:
                            {
                            int LA86_2 = input.LA(2);

                            if ( (synpred141_JavaParser()) ) {
                                alt86=1;
                            }
                            }
                            break;
                        case NOT:
                            {
                            int LA86_3 = input.LA(2);

                            if ( (synpred141_JavaParser()) ) {
                                alt86=1;
                            }
                            }
                            break;
                    }

                    switch (alt86) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: right_unary
                            {
                            pushFollow(FOLLOW_right_unary_in_statement1251);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:209:1: statement_wosemicolon : ( label | variable_assignment | variable_def | method_call | return_statement | continue_ | break_ | for_loop | while_loop | do_loop | switch_case | if_else | try_catch | synchronized_block | throw_ value semicolon | ( left_unary )? variable_name ( right_unary )? );
    public final void statement_wosemicolon() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:210:2: ( label | variable_assignment | variable_def | method_call | return_statement | continue_ | break_ | for_loop | while_loop | do_loop | switch_case | if_else | try_catch | synchronized_block | throw_ value semicolon | ( left_unary )? variable_name ( right_unary )? )
            int alt90=16;
            alt90 = dfa90.predict(input);
            switch (alt90) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:210:4: label
                    {
                    pushFollow(FOLLOW_label_in_statement_wosemicolon1263);
                    label();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:211:4: variable_assignment
                    {
                    pushFollow(FOLLOW_variable_assignment_in_statement_wosemicolon1268);
                    variable_assignment();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:212:4: variable_def
                    {
                    pushFollow(FOLLOW_variable_def_in_statement_wosemicolon1273);
                    variable_def();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:213:4: method_call
                    {
                    pushFollow(FOLLOW_method_call_in_statement_wosemicolon1278);
                    method_call();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:214:4: return_statement
                    {
                    pushFollow(FOLLOW_return_statement_in_statement_wosemicolon1284);
                    return_statement();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:215:4: continue_
                    {
                    pushFollow(FOLLOW_continue__in_statement_wosemicolon1289);
                    continue_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:216:4: break_
                    {
                    pushFollow(FOLLOW_break__in_statement_wosemicolon1294);
                    break_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 8 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:217:4: for_loop
                    {
                    pushFollow(FOLLOW_for_loop_in_statement_wosemicolon1299);
                    for_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 9 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:218:4: while_loop
                    {
                    pushFollow(FOLLOW_while_loop_in_statement_wosemicolon1304);
                    while_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 10 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:219:4: do_loop
                    {
                    pushFollow(FOLLOW_do_loop_in_statement_wosemicolon1309);
                    do_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 11 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:220:4: switch_case
                    {
                    pushFollow(FOLLOW_switch_case_in_statement_wosemicolon1314);
                    switch_case();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 12 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:221:4: if_else
                    {
                    pushFollow(FOLLOW_if_else_in_statement_wosemicolon1319);
                    if_else();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 13 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:222:4: try_catch
                    {
                    pushFollow(FOLLOW_try_catch_in_statement_wosemicolon1324);
                    try_catch();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 14 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:223:4: synchronized_block
                    {
                    pushFollow(FOLLOW_synchronized_block_in_statement_wosemicolon1329);
                    synchronized_block();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 15 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:224:4: throw_ value semicolon
                    {
                    pushFollow(FOLLOW_throw__in_statement_wosemicolon1334);
                    throw_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_statement_wosemicolon1336);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement_wosemicolon1338);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 16 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:225:4: ( left_unary )? variable_name ( right_unary )?
                    {
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:225:4: ( left_unary )?
                    int alt88=2;
                    int LA88_0 = input.LA(1);

                    if ( ((LA88_0>=INC && LA88_0<=MINUS)||LA88_0==NOT) ) {
                        alt88=1;
                    }
                    switch (alt88) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: left_unary
                            {
                            pushFollow(FOLLOW_left_unary_in_statement_wosemicolon1343);
                            left_unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_variable_name_in_statement_wosemicolon1346);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:225:30: ( right_unary )?
                    int alt89=2;
                    int LA89_0 = input.LA(1);

                    if ( ((LA89_0>=INC && LA89_0<=DEC)||LA89_0==NOT) ) {
                        alt89=1;
                    }
                    switch (alt89) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: right_unary
                            {
                            pushFollow(FOLLOW_right_unary_in_statement_wosemicolon1348);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:228:1: return_statement : return_ ( value )? semicolon ;
    public final void return_statement() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:229:2: ( return_ ( value )? semicolon )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:229:4: return_ ( value )? semicolon
            {
            pushFollow(FOLLOW_return__in_return_statement1361);
            return_();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:229:12: ( value )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( ((LA91_0>=VOID && LA91_0<=NEW)||(LA91_0>=NULL && LA91_0<=SUPER)||(LA91_0>=BOOLEAN && LA91_0<=OPEN_CURLY_BRACKET)||(LA91_0>=INC && LA91_0<=MINUS)||LA91_0==NOT||LA91_0==OPEN_BRACKET||(LA91_0>=BOOL_CONST && LA91_0<=INT_CONST)||(LA91_0>=HEX_CONST && LA91_0<=HEX_LONG_CONST)||LA91_0==FLOAT_CONST||(LA91_0>=STRING_CONST && LA91_0<=CHAR_CONST)) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: value
                    {
                    pushFollow(FOLLOW_value_in_return_statement1363);
                    value();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_semicolon_in_return_statement1366);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:232:1: variable_assignment : ( this_ dot | super_ dot )? variable_name assign value ;
    public final void variable_assignment() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:233:2: ( ( this_ dot | super_ dot )? variable_name assign value )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:233:4: ( this_ dot | super_ dot )? variable_name assign value
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:233:4: ( this_ dot | super_ dot )?
            int alt92=3;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==THIS) ) {
                alt92=1;
            }
            else if ( (LA92_0==SUPER) ) {
                alt92=2;
            }
            switch (alt92) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:233:5: this_ dot
                    {
                    pushFollow(FOLLOW_this__in_variable_assignment1379);
                    this_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_dot_in_variable_assignment1381);
                    dot();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:233:17: super_ dot
                    {
                    pushFollow(FOLLOW_super__in_variable_assignment1385);
                    super_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_dot_in_variable_assignment1387);
                    dot();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_variable_name_in_variable_assignment1391);
            variable_name();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_assign_in_variable_assignment1393);
            assign();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_value_in_variable_assignment1395);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:236:1: for_loop : ( for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon ( value )? semicolon ( statement_wosemicolon ( comma statement_wosemicolon )* )? CLOSE_BRACKET code | for_ OPEN_BRACKET variable_type variable_name colon value CLOSE_BRACKET code );
    public final void for_loop() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:236:9: ( for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon ( value )? semicolon ( statement_wosemicolon ( comma statement_wosemicolon )* )? CLOSE_BRACKET code | for_ OPEN_BRACKET variable_type variable_name colon value CLOSE_BRACKET code )
            int alt98=2;
            int LA98_0 = input.LA(1);

            if ( (LA98_0==FOR) ) {
                int LA98_1 = input.LA(2);

                if ( (synpred167_JavaParser()) ) {
                    alt98=1;
                }
                else if ( (true) ) {
                    alt98=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 98, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 98, 0, input);

                throw nvae;
            }
            switch (alt98) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:236:11: for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon ( value )? semicolon ( statement_wosemicolon ( comma statement_wosemicolon )* )? CLOSE_BRACKET code
                    {
                    pushFollow(FOLLOW_for__in_for_loop1405);
                    for_();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_for_loop1407); if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:236:29: ( variable_def ( comma variable_def )* )?
                    int alt94=2;
                    int LA94_0 = input.LA(1);

                    if ( (LA94_0==VOID||LA94_0==FINAL||(LA94_0>=BOOLEAN && LA94_0<=DOUBLE)||LA94_0==AT||LA94_0==ID) ) {
                        alt94=1;
                    }
                    switch (alt94) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:236:30: variable_def ( comma variable_def )*
                            {
                            pushFollow(FOLLOW_variable_def_in_for_loop1410);
                            variable_def();

                            state._fsp--;
                            if (state.failed) return ;
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:236:43: ( comma variable_def )*
                            loop93:
                            do {
                                int alt93=2;
                                int LA93_0 = input.LA(1);

                                if ( (LA93_0==COMMA) ) {
                                    alt93=1;
                                }


                                switch (alt93) {
                            	case 1 :
                            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:236:44: comma variable_def
                            	    {
                            	    pushFollow(FOLLOW_comma_in_for_loop1413);
                            	    comma();

                            	    state._fsp--;
                            	    if (state.failed) return ;
                            	    pushFollow(FOLLOW_variable_def_in_for_loop1415);
                            	    variable_def();

                            	    state._fsp--;
                            	    if (state.failed) return ;

                            	    }
                            	    break;

                            	default :
                            	    break loop93;
                                }
                            } while (true);


                            }
                            break;

                    }

                    pushFollow(FOLLOW_semicolon_in_for_loop1421);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:236:77: ( value )?
                    int alt95=2;
                    int LA95_0 = input.LA(1);

                    if ( ((LA95_0>=VOID && LA95_0<=NEW)||(LA95_0>=NULL && LA95_0<=SUPER)||(LA95_0>=BOOLEAN && LA95_0<=OPEN_CURLY_BRACKET)||(LA95_0>=INC && LA95_0<=MINUS)||LA95_0==NOT||LA95_0==OPEN_BRACKET||(LA95_0>=BOOL_CONST && LA95_0<=INT_CONST)||(LA95_0>=HEX_CONST && LA95_0<=HEX_LONG_CONST)||LA95_0==FLOAT_CONST||(LA95_0>=STRING_CONST && LA95_0<=CHAR_CONST)) ) {
                        alt95=1;
                    }
                    switch (alt95) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: value
                            {
                            pushFollow(FOLLOW_value_in_for_loop1423);
                            value();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_semicolon_in_for_loop1426);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:236:94: ( statement_wosemicolon ( comma statement_wosemicolon )* )?
                    int alt97=2;
                    int LA97_0 = input.LA(1);

                    if ( ((LA97_0>=VOID && LA97_0<=CONTINUE)||(LA97_0>=THIS && LA97_0<=IF)||LA97_0==SWITCH||LA97_0==TRY||LA97_0==THROW||LA97_0==FINAL||LA97_0==SYNCHRONIZED||(LA97_0>=BOOLEAN && LA97_0<=DOUBLE)||(LA97_0>=INC && LA97_0<=MINUS)||LA97_0==NOT||LA97_0==AT||LA97_0==ID) ) {
                        alt97=1;
                    }
                    switch (alt97) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:236:95: statement_wosemicolon ( comma statement_wosemicolon )*
                            {
                            pushFollow(FOLLOW_statement_wosemicolon_in_for_loop1429);
                            statement_wosemicolon();

                            state._fsp--;
                            if (state.failed) return ;
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:236:117: ( comma statement_wosemicolon )*
                            loop96:
                            do {
                                int alt96=2;
                                int LA96_0 = input.LA(1);

                                if ( (LA96_0==COMMA) ) {
                                    alt96=1;
                                }


                                switch (alt96) {
                            	case 1 :
                            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:236:118: comma statement_wosemicolon
                            	    {
                            	    pushFollow(FOLLOW_comma_in_for_loop1432);
                            	    comma();

                            	    state._fsp--;
                            	    if (state.failed) return ;
                            	    pushFollow(FOLLOW_statement_wosemicolon_in_for_loop1434);
                            	    statement_wosemicolon();

                            	    state._fsp--;
                            	    if (state.failed) return ;

                            	    }
                            	    break;

                            	default :
                            	    break loop96;
                                }
                            } while (true);


                            }
                            break;

                    }

                    match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_for_loop1440); if (state.failed) return ;
                    pushFollow(FOLLOW_code_in_for_loop1442);
                    code();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:237:4: for_ OPEN_BRACKET variable_type variable_name colon value CLOSE_BRACKET code
                    {
                    pushFollow(FOLLOW_for__in_for_loop1447);
                    for_();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_for_loop1449); if (state.failed) return ;
                    pushFollow(FOLLOW_variable_type_in_for_loop1451);
                    variable_type();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_variable_name_in_for_loop1453);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_colon_in_for_loop1455);
                    colon();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_for_loop1457);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_for_loop1459); if (state.failed) return ;
                    pushFollow(FOLLOW_code_in_for_loop1461);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:240:1: while_loop : while_ OPEN_BRACKET value CLOSE_BRACKET code ;
    public final void while_loop() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:241:2: ( while_ OPEN_BRACKET value CLOSE_BRACKET code )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:241:4: while_ OPEN_BRACKET value CLOSE_BRACKET code
            {
            pushFollow(FOLLOW_while__in_while_loop1473);
            while_();

            state._fsp--;
            if (state.failed) return ;
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_while_loop1475); if (state.failed) return ;
            pushFollow(FOLLOW_value_in_while_loop1477);
            value();

            state._fsp--;
            if (state.failed) return ;
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_while_loop1479); if (state.failed) return ;
            pushFollow(FOLLOW_code_in_while_loop1481);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:244:1: do_loop : do_ code while_ OPEN_BRACKET value CLOSE_BRACKET ;
    public final void do_loop() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:244:9: ( do_ code while_ OPEN_BRACKET value CLOSE_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:244:11: do_ code while_ OPEN_BRACKET value CLOSE_BRACKET
            {
            pushFollow(FOLLOW_do__in_do_loop1492);
            do_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_code_in_do_loop1494);
            code();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_while__in_do_loop1496);
            while_();

            state._fsp--;
            if (state.failed) return ;
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_do_loop1498); if (state.failed) return ;
            pushFollow(FOLLOW_value_in_do_loop1500);
            value();

            state._fsp--;
            if (state.failed) return ;
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_do_loop1502); if (state.failed) return ;

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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:247:1: switch_case : switch_ OPEN_BRACKET value CLOSE_BRACKET block_begin ( ( case_ ( constant | variable_name ) colon )+ ( code | statement )* ( break_ semicolon )? | default_ colon ( code | statement )* )+ block_end ;
    public final void switch_case() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:248:2: ( switch_ OPEN_BRACKET value CLOSE_BRACKET block_begin ( ( case_ ( constant | variable_name ) colon )+ ( code | statement )* ( break_ semicolon )? | default_ colon ( code | statement )* )+ block_end )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:248:4: switch_ OPEN_BRACKET value CLOSE_BRACKET block_begin ( ( case_ ( constant | variable_name ) colon )+ ( code | statement )* ( break_ semicolon )? | default_ colon ( code | statement )* )+ block_end
            {
            pushFollow(FOLLOW_switch__in_switch_case1513);
            switch_();

            state._fsp--;
            if (state.failed) return ;
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_switch_case1515); if (state.failed) return ;
            pushFollow(FOLLOW_value_in_switch_case1517);
            value();

            state._fsp--;
            if (state.failed) return ;
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_switch_case1519); if (state.failed) return ;
            pushFollow(FOLLOW_block_begin_in_switch_case1521);
            block_begin();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:249:3: ( ( case_ ( constant | variable_name ) colon )+ ( code | statement )* ( break_ semicolon )? | default_ colon ( code | statement )* )+
            int cnt104=0;
            loop104:
            do {
                int alt104=3;
                int LA104_0 = input.LA(1);

                if ( (LA104_0==CASE) ) {
                    alt104=1;
                }
                else if ( (LA104_0==DEFAULT) ) {
                    alt104=2;
                }


                switch (alt104) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:250:4: ( case_ ( constant | variable_name ) colon )+ ( code | statement )* ( break_ semicolon )?
            	    {
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:250:4: ( case_ ( constant | variable_name ) colon )+
            	    int cnt100=0;
            	    loop100:
            	    do {
            	        int alt100=2;
            	        int LA100_0 = input.LA(1);

            	        if ( (LA100_0==CASE) ) {
            	            int LA100_2 = input.LA(2);

            	            if ( (synpred169_JavaParser()) ) {
            	                alt100=1;
            	            }


            	        }


            	        switch (alt100) {
            	    	case 1 :
            	    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:250:5: case_ ( constant | variable_name ) colon
            	    	    {
            	    	    pushFollow(FOLLOW_case__in_switch_case1532);
            	    	    case_();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;
            	    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:250:12: ( constant | variable_name )
            	    	    int alt99=2;
            	    	    alt99 = dfa99.predict(input);
            	    	    switch (alt99) {
            	    	        case 1 :
            	    	            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:250:13: constant
            	    	            {
            	    	            pushFollow(FOLLOW_constant_in_switch_case1536);
            	    	            constant();

            	    	            state._fsp--;
            	    	            if (state.failed) return ;

            	    	            }
            	    	            break;
            	    	        case 2 :
            	    	            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:250:24: variable_name
            	    	            {
            	    	            pushFollow(FOLLOW_variable_name_in_switch_case1540);
            	    	            variable_name();

            	    	            state._fsp--;
            	    	            if (state.failed) return ;

            	    	            }
            	    	            break;

            	    	    }

            	    	    pushFollow(FOLLOW_colon_in_switch_case1543);
            	    	    colon();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;

            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt100 >= 1 ) break loop100;
            	    	    if (state.backtracking>0) {state.failed=true; return ;}
            	                EarlyExitException eee =
            	                    new EarlyExitException(100, input);
            	                throw eee;
            	        }
            	        cnt100++;
            	    } while (true);

            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:250:47: ( code | statement )*
            	    loop101:
            	    do {
            	        int alt101=3;
            	        alt101 = dfa101.predict(input);
            	        switch (alt101) {
            	    	case 1 :
            	    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:250:48: code
            	    	    {
            	    	    pushFollow(FOLLOW_code_in_switch_case1548);
            	    	    code();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;

            	    	    }
            	    	    break;
            	    	case 2 :
            	    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:250:55: statement
            	    	    {
            	    	    pushFollow(FOLLOW_statement_in_switch_case1552);
            	    	    statement();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop101;
            	        }
            	    } while (true);

            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:250:67: ( break_ semicolon )?
            	    int alt102=2;
            	    int LA102_0 = input.LA(1);

            	    if ( (LA102_0==BREAK) ) {
            	        alt102=1;
            	    }
            	    switch (alt102) {
            	        case 1 :
            	            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:250:68: break_ semicolon
            	            {
            	            pushFollow(FOLLOW_break__in_switch_case1557);
            	            break_();

            	            state._fsp--;
            	            if (state.failed) return ;
            	            pushFollow(FOLLOW_semicolon_in_switch_case1559);
            	            semicolon();

            	            state._fsp--;
            	            if (state.failed) return ;

            	            }
            	            break;

            	    }


            	    }
            	    break;
            	case 2 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:252:4: default_ colon ( code | statement )*
            	    {
            	    pushFollow(FOLLOW_default__in_switch_case1570);
            	    default_();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_colon_in_switch_case1572);
            	    colon();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:252:19: ( code | statement )*
            	    loop103:
            	    do {
            	        int alt103=3;
            	        alt103 = dfa103.predict(input);
            	        switch (alt103) {
            	    	case 1 :
            	    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:252:20: code
            	    	    {
            	    	    pushFollow(FOLLOW_code_in_switch_case1575);
            	    	    code();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;

            	    	    }
            	    	    break;
            	    	case 2 :
            	    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:252:27: statement
            	    	    {
            	    	    pushFollow(FOLLOW_statement_in_switch_case1579);
            	    	    statement();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop103;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    if ( cnt104 >= 1 ) break loop104;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(104, input);
                        throw eee;
                }
                cnt104++;
            } while (true);

            pushFollow(FOLLOW_block_end_in_switch_case1590);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:257:1: if_else : if_ OPEN_BRACKET value CLOSE_BRACKET code ( else_ code )? ;
    public final void if_else() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:257:9: ( if_ OPEN_BRACKET value CLOSE_BRACKET code ( else_ code )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:257:11: if_ OPEN_BRACKET value CLOSE_BRACKET code ( else_ code )?
            {
            pushFollow(FOLLOW_if__in_if_else1600);
            if_();

            state._fsp--;
            if (state.failed) return ;
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_if_else1602); if (state.failed) return ;
            pushFollow(FOLLOW_value_in_if_else1604);
            value();

            state._fsp--;
            if (state.failed) return ;
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_if_else1606); if (state.failed) return ;
            pushFollow(FOLLOW_code_in_if_else1608);
            code();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:257:53: ( else_ code )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==ELSE) ) {
                int LA105_1 = input.LA(2);

                if ( (synpred177_JavaParser()) ) {
                    alt105=1;
                }
            }
            switch (alt105) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:257:54: else_ code
                    {
                    pushFollow(FOLLOW_else__in_if_else1611);
                    else_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_code_in_if_else1613);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:260:1: try_catch : try_ code ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )* ( finally_ code )? ;
    public final void try_catch() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:261:2: ( try_ code ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )* ( finally_ code )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:261:4: try_ code ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )* ( finally_ code )?
            {
            pushFollow(FOLLOW_try__in_try_catch1626);
            try_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_code_in_try_catch1628);
            code();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:261:14: ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )*
            loop106:
            do {
                int alt106=2;
                int LA106_0 = input.LA(1);

                if ( (LA106_0==CATCH) ) {
                    int LA106_2 = input.LA(2);

                    if ( (synpred178_JavaParser()) ) {
                        alt106=1;
                    }


                }


                switch (alt106) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:261:15: catch_ OPEN_BRACKET id id CLOSE_BRACKET code
            	    {
            	    pushFollow(FOLLOW_catch__in_try_catch1631);
            	    catch_();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_try_catch1633); if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_try_catch1635);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_try_catch1637);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_try_catch1639); if (state.failed) return ;
            	    pushFollow(FOLLOW_code_in_try_catch1641);
            	    code();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop106;
                }
            } while (true);

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:261:62: ( finally_ code )?
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==FINALLY) ) {
                int LA107_1 = input.LA(2);

                if ( (synpred179_JavaParser()) ) {
                    alt107=1;
                }
            }
            switch (alt107) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:261:63: finally_ code
                    {
                    pushFollow(FOLLOW_finally__in_try_catch1646);
                    finally_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_code_in_try_catch1648);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:263:1: synchronized_block : synchronized_ code ;
    public final void synchronized_block() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:264:2: ( synchronized_ code )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:264:4: synchronized_ code
            {
            pushFollow(FOLLOW_synchronized__in_synchronized_block1660);
            synchronized_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_code_in_synchronized_block1662);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:267:1: label : id colon ;
    public final void label() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:267:7: ( id colon )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:267:9: id colon
            {
            pushFollow(FOLLOW_id_in_label1672);
            id();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_colon_in_label1674);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:269:1: cast : OPEN_BRACKET variable_type CLOSE_BRACKET ;
    public final void cast() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:269:6: ( OPEN_BRACKET variable_type CLOSE_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:269:8: OPEN_BRACKET variable_type CLOSE_BRACKET
            {
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_cast1682); if (state.failed) return ;
            pushFollow(FOLLOW_variable_type_in_cast1684);
            variable_type();

            state._fsp--;
            if (state.failed) return ;
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_cast1686); if (state.failed) return ;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:272:1: variable_type : ( ( variable_modifier )* primitive ( array )? | ( variable_modifier )* class_name ( array )? | ( variable_modifier )* void_ );
    public final void variable_type() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:273:2: ( ( variable_modifier )* primitive ( array )? | ( variable_modifier )* class_name ( array )? | ( variable_modifier )* void_ )
            int alt113=3;
            alt113 = dfa113.predict(input);
            switch (alt113) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:273:4: ( variable_modifier )* primitive ( array )?
                    {
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:273:4: ( variable_modifier )*
                    loop108:
                    do {
                        int alt108=2;
                        int LA108_0 = input.LA(1);

                        if ( (LA108_0==FINAL) ) {
                            alt108=1;
                        }


                        switch (alt108) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: variable_modifier
                    	    {
                    	    pushFollow(FOLLOW_variable_modifier_in_variable_type1699);
                    	    variable_modifier();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop108;
                        }
                    } while (true);

                    pushFollow(FOLLOW_primitive_in_variable_type1702);
                    primitive();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:273:33: ( array )?
                    int alt109=2;
                    int LA109_0 = input.LA(1);

                    if ( (LA109_0==OPEN_RECT_BRACKET) ) {
                        alt109=1;
                    }
                    switch (alt109) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: array
                            {
                            pushFollow(FOLLOW_array_in_variable_type1704);
                            array();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:274:4: ( variable_modifier )* class_name ( array )?
                    {
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:274:4: ( variable_modifier )*
                    loop110:
                    do {
                        int alt110=2;
                        int LA110_0 = input.LA(1);

                        if ( (LA110_0==FINAL) ) {
                            alt110=1;
                        }


                        switch (alt110) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: variable_modifier
                    	    {
                    	    pushFollow(FOLLOW_variable_modifier_in_variable_type1710);
                    	    variable_modifier();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop110;
                        }
                    } while (true);

                    pushFollow(FOLLOW_class_name_in_variable_type1713);
                    class_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:274:34: ( array )?
                    int alt111=2;
                    int LA111_0 = input.LA(1);

                    if ( (LA111_0==OPEN_RECT_BRACKET) ) {
                        alt111=1;
                    }
                    switch (alt111) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: array
                            {
                            pushFollow(FOLLOW_array_in_variable_type1715);
                            array();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:275:4: ( variable_modifier )* void_
                    {
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:275:4: ( variable_modifier )*
                    loop112:
                    do {
                        int alt112=2;
                        int LA112_0 = input.LA(1);

                        if ( (LA112_0==FINAL) ) {
                            alt112=1;
                        }


                        switch (alt112) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: variable_modifier
                    	    {
                    	    pushFollow(FOLLOW_variable_modifier_in_variable_type1721);
                    	    variable_modifier();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop112;
                        }
                    } while (true);

                    pushFollow(FOLLOW_void__in_variable_type1724);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:278:1: id : ID ;
    public final JavaParser.id_return id() throws RecognitionException {
        JavaParser.id_return retval = new JavaParser.id_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:278:4: ( ID )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:278:6: ID
            {
            match(input,ID,FOLLOW_ID_in_id1734); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:280:1: binary_operator : ( plus | minus | star | SLASH | PERCENT | EQUAL | UNEQUAL | LT | GT | LE | GE | LOGICAL_OR | BIT_OR | LOGICAL_AND | BIT_AND );
    public final JavaParser.binary_operator_return binary_operator() throws RecognitionException {
        JavaParser.binary_operator_return retval = new JavaParser.binary_operator_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:281:2: ( plus | minus | star | SLASH | PERCENT | EQUAL | UNEQUAL | LT | GT | LE | GE | LOGICAL_OR | BIT_OR | LOGICAL_AND | BIT_AND )
            int alt114=15;
            switch ( input.LA(1) ) {
            case PLUS:
                {
                alt114=1;
                }
                break;
            case MINUS:
                {
                alt114=2;
                }
                break;
            case STAR:
                {
                alt114=3;
                }
                break;
            case SLASH:
                {
                alt114=4;
                }
                break;
            case PERCENT:
                {
                alt114=5;
                }
                break;
            case EQUAL:
                {
                alt114=6;
                }
                break;
            case UNEQUAL:
                {
                alt114=7;
                }
                break;
            case LT:
                {
                alt114=8;
                }
                break;
            case GT:
                {
                alt114=9;
                }
                break;
            case LE:
                {
                alt114=10;
                }
                break;
            case GE:
                {
                alt114=11;
                }
                break;
            case LOGICAL_OR:
                {
                alt114=12;
                }
                break;
            case BIT_OR:
                {
                alt114=13;
                }
                break;
            case LOGICAL_AND:
                {
                alt114=14;
                }
                break;
            case BIT_AND:
                {
                alt114=15;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 114, 0, input);

                throw nvae;
            }

            switch (alt114) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:281:4: plus
                    {
                    pushFollow(FOLLOW_plus_in_binary_operator1745);
                    plus();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:282:4: minus
                    {
                    pushFollow(FOLLOW_minus_in_binary_operator1750);
                    minus();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:283:4: star
                    {
                    pushFollow(FOLLOW_star_in_binary_operator1755);
                    star();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:284:4: SLASH
                    {
                    match(input,SLASH,FOLLOW_SLASH_in_binary_operator1760); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 5 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:285:4: PERCENT
                    {
                    match(input,PERCENT,FOLLOW_PERCENT_in_binary_operator1767); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 6 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:286:4: EQUAL
                    {
                    match(input,EQUAL,FOLLOW_EQUAL_in_binary_operator1774); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 7 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:287:4: UNEQUAL
                    {
                    match(input,UNEQUAL,FOLLOW_UNEQUAL_in_binary_operator1781); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 8 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:288:4: LT
                    {
                    match(input,LT,FOLLOW_LT_in_binary_operator1788); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 9 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:289:4: GT
                    {
                    match(input,GT,FOLLOW_GT_in_binary_operator1795); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 10 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:290:4: LE
                    {
                    match(input,LE,FOLLOW_LE_in_binary_operator1802); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 11 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:291:4: GE
                    {
                    match(input,GE,FOLLOW_GE_in_binary_operator1809); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 12 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:292:4: LOGICAL_OR
                    {
                    match(input,LOGICAL_OR,FOLLOW_LOGICAL_OR_in_binary_operator1816); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 13 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:293:4: BIT_OR
                    {
                    match(input,BIT_OR,FOLLOW_BIT_OR_in_binary_operator1823); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 14 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:294:4: LOGICAL_AND
                    {
                    match(input,LOGICAL_AND,FOLLOW_LOGICAL_AND_in_binary_operator1830); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 15 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:295:4: BIT_AND
                    {
                    match(input,BIT_AND,FOLLOW_BIT_AND_in_binary_operator1837); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:298:1: left_unary : ( INC | DEC | NOT | MINUS | PLUS );
    public final JavaParser.left_unary_return left_unary() throws RecognitionException {
        JavaParser.left_unary_return retval = new JavaParser.left_unary_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:299:2: ( INC | DEC | NOT | MINUS | PLUS )
            int alt115=5;
            switch ( input.LA(1) ) {
            case INC:
                {
                alt115=1;
                }
                break;
            case DEC:
                {
                alt115=2;
                }
                break;
            case NOT:
                {
                alt115=3;
                }
                break;
            case MINUS:
                {
                alt115=4;
                }
                break;
            case PLUS:
                {
                alt115=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 115, 0, input);

                throw nvae;
            }

            switch (alt115) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:299:4: INC
                    {
                    match(input,INC,FOLLOW_INC_in_left_unary1851); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:300:4: DEC
                    {
                    match(input,DEC,FOLLOW_DEC_in_left_unary1858); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:301:4: NOT
                    {
                    match(input,NOT,FOLLOW_NOT_in_left_unary1865); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:302:4: MINUS
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_left_unary1872); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 5 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:303:4: PLUS
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_left_unary1879); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:306:1: right_unary : ( INC | DEC | NOT );
    public final JavaParser.right_unary_return right_unary() throws RecognitionException {
        JavaParser.right_unary_return retval = new JavaParser.right_unary_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:307:2: ( INC | DEC | NOT )
            int alt116=3;
            switch ( input.LA(1) ) {
            case INC:
                {
                alt116=1;
                }
                break;
            case DEC:
                {
                alt116=2;
                }
                break;
            case NOT:
                {
                alt116=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 116, 0, input);

                throw nvae;
            }

            switch (alt116) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:307:4: INC
                    {
                    match(input,INC,FOLLOW_INC_in_right_unary1893); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:308:4: DEC
                    {
                    match(input,DEC,FOLLOW_DEC_in_right_unary1900); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:309:4: NOT
                    {
                    match(input,NOT,FOLLOW_NOT_in_right_unary1907); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:312:1: primitive : ( BOOLEAN | BYTE | CHAR | SHORT | INTEGER | LONG | FLOAT | DOUBLE );
    public final JavaParser.primitive_return primitive() throws RecognitionException {
        JavaParser.primitive_return retval = new JavaParser.primitive_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:313:2: ( BOOLEAN | BYTE | CHAR | SHORT | INTEGER | LONG | FLOAT | DOUBLE )
            int alt117=8;
            switch ( input.LA(1) ) {
            case BOOLEAN:
                {
                alt117=1;
                }
                break;
            case BYTE:
                {
                alt117=2;
                }
                break;
            case CHAR:
                {
                alt117=3;
                }
                break;
            case SHORT:
                {
                alt117=4;
                }
                break;
            case INTEGER:
                {
                alt117=5;
                }
                break;
            case LONG:
                {
                alt117=6;
                }
                break;
            case FLOAT:
                {
                alt117=7;
                }
                break;
            case DOUBLE:
                {
                alt117=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 117, 0, input);

                throw nvae;
            }

            switch (alt117) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:313:4: BOOLEAN
                    {
                    match(input,BOOLEAN,FOLLOW_BOOLEAN_in_primitive1920); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:314:4: BYTE
                    {
                    match(input,BYTE,FOLLOW_BYTE_in_primitive1927); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:315:4: CHAR
                    {
                    match(input,CHAR,FOLLOW_CHAR_in_primitive1934); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:316:4: SHORT
                    {
                    match(input,SHORT,FOLLOW_SHORT_in_primitive1941); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 5 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:317:4: INTEGER
                    {
                    match(input,INTEGER,FOLLOW_INTEGER_in_primitive1948); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 6 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:318:4: LONG
                    {
                    match(input,LONG,FOLLOW_LONG_in_primitive1955); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 7 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:319:4: FLOAT
                    {
                    match(input,FLOAT,FOLLOW_FLOAT_in_primitive1962); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 8 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:320:4: DOUBLE
                    {
                    match(input,DOUBLE,FOLLOW_DOUBLE_in_primitive1969); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:328:1: int_const : INT_CONST ;
    public final JavaParser.int_const_return int_const() throws RecognitionException {
        JavaParser.int_const_return retval = new JavaParser.int_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:329:2: ( INT_CONST )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:329:4: INT_CONST
            {
            match(input,INT_CONST,FOLLOW_INT_CONST_in_int_const1987); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:330:1: long_const : LONG_CONST ;
    public final JavaParser.long_const_return long_const() throws RecognitionException {
        JavaParser.long_const_return retval = new JavaParser.long_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:331:2: ( LONG_CONST )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:331:4: LONG_CONST
            {
            match(input,LONG_CONST,FOLLOW_LONG_CONST_in_long_const1997); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:332:1: hex_const : HEX_CONST ;
    public final JavaParser.hex_const_return hex_const() throws RecognitionException {
        JavaParser.hex_const_return retval = new JavaParser.hex_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:333:2: ( HEX_CONST )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:333:4: HEX_CONST
            {
            match(input,HEX_CONST,FOLLOW_HEX_CONST_in_hex_const2007); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:334:1: hex_long_const : HEX_LONG_CONST ;
    public final JavaParser.hex_long_const_return hex_long_const() throws RecognitionException {
        JavaParser.hex_long_const_return retval = new JavaParser.hex_long_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:335:2: ( HEX_LONG_CONST )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:335:4: HEX_LONG_CONST
            {
            match(input,HEX_LONG_CONST,FOLLOW_HEX_LONG_CONST_in_hex_long_const2017); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:336:1: string_const : STRING_CONST ;
    public final JavaParser.string_const_return string_const() throws RecognitionException {
        JavaParser.string_const_return retval = new JavaParser.string_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:337:2: ( STRING_CONST )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:337:4: STRING_CONST
            {
            match(input,STRING_CONST,FOLLOW_STRING_CONST_in_string_const2027); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:338:1: float_const : FLOAT_CONST ;
    public final JavaParser.float_const_return float_const() throws RecognitionException {
        JavaParser.float_const_return retval = new JavaParser.float_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:339:2: ( FLOAT_CONST )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:339:4: FLOAT_CONST
            {
            match(input,FLOAT_CONST,FOLLOW_FLOAT_CONST_in_float_const2037); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:340:1: char_const : CHAR_CONST ;
    public final JavaParser.char_const_return char_const() throws RecognitionException {
        JavaParser.char_const_return retval = new JavaParser.char_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:341:2: ( CHAR_CONST )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:341:4: CHAR_CONST
            {
            match(input,CHAR_CONST,FOLLOW_CHAR_CONST_in_char_const2047); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:342:1: null_const : NULL ;
    public final JavaParser.null_const_return null_const() throws RecognitionException {
        JavaParser.null_const_return retval = new JavaParser.null_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:343:2: ( NULL )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:343:4: NULL
            {
            match(input,NULL,FOLLOW_NULL_in_null_const2057); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:344:1: boolean_const : BOOL_CONST ;
    public final JavaParser.boolean_const_return boolean_const() throws RecognitionException {
        JavaParser.boolean_const_return retval = new JavaParser.boolean_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:345:2: ( BOOL_CONST )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:345:4: BOOL_CONST
            {
            match(input,BOOL_CONST,FOLLOW_BOOL_CONST_in_boolean_const2068); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:346:1: array_const : block_begin value ( comma value )* block_end ;
    public final void array_const() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:347:2: ( block_begin value ( comma value )* block_end )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:347:4: block_begin value ( comma value )* block_end
            {
            pushFollow(FOLLOW_block_begin_in_array_const2078);
            block_begin();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_value_in_array_const2080);
            value();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:347:22: ( comma value )*
            loop118:
            do {
                int alt118=2;
                int LA118_0 = input.LA(1);

                if ( (LA118_0==COMMA) ) {
                    alt118=1;
                }


                switch (alt118) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:347:23: comma value
            	    {
            	    pushFollow(FOLLOW_comma_in_array_const2083);
            	    comma();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_value_in_array_const2085);
            	    value();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop118;
                }
            } while (true);

            pushFollow(FOLLOW_block_end_in_array_const2089);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:348:1: class_const : ( class_name | primitive | void_ ) dot class_ ;
    public final void class_const() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:349:2: ( ( class_name | primitive | void_ ) dot class_ )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:349:4: ( class_name | primitive | void_ ) dot class_
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:349:4: ( class_name | primitive | void_ )
            int alt119=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt119=1;
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
                alt119=2;
                }
                break;
            case VOID:
                {
                alt119=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 119, 0, input);

                throw nvae;
            }

            switch (alt119) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:349:5: class_name
                    {
                    pushFollow(FOLLOW_class_name_in_class_const2098);
                    class_name();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:349:18: primitive
                    {
                    pushFollow(FOLLOW_primitive_in_class_const2102);
                    primitive();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:349:30: void_
                    {
                    pushFollow(FOLLOW_void__in_class_const2106);
                    void_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_dot_in_class_const2109);
            dot();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_class__in_class_const2111);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:356:1: package_name : ( id dot )* id ;
    public final JavaParser.package_name_return package_name() throws RecognitionException {
        JavaParser.package_name_return retval = new JavaParser.package_name_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:357:2: ( ( id dot )* id )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:357:4: ( id dot )* id
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:357:4: ( id dot )*
            loop120:
            do {
                int alt120=2;
                int LA120_0 = input.LA(1);

                if ( (LA120_0==ID) ) {
                    int LA120_1 = input.LA(2);

                    if ( (LA120_1==DOT) ) {
                        alt120=1;
                    }


                }


                switch (alt120) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:357:5: id dot
            	    {
            	    pushFollow(FOLLOW_id_in_package_name2127);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    pushFollow(FOLLOW_dot_in_package_name2129);
            	    dot();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop120;
                }
            } while (true);

            pushFollow(FOLLOW_id_in_package_name2133);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:360:1: import_name : ( id dot )+ ( id | star ) ;
    public final void import_name() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:361:2: ( ( id dot )+ ( id | star ) )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:361:4: ( id dot )+ ( id | star )
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:361:4: ( id dot )+
            int cnt121=0;
            loop121:
            do {
                int alt121=2;
                int LA121_0 = input.LA(1);

                if ( (LA121_0==ID) ) {
                    int LA121_1 = input.LA(2);

                    if ( (LA121_1==DOT) ) {
                        alt121=1;
                    }


                }


                switch (alt121) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:361:5: id dot
            	    {
            	    pushFollow(FOLLOW_id_in_import_name2147);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_dot_in_import_name2149);
            	    dot();

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

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:361:14: ( id | star )
            int alt122=2;
            int LA122_0 = input.LA(1);

            if ( (LA122_0==ID) ) {
                alt122=1;
            }
            else if ( (LA122_0==STAR) ) {
                alt122=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 122, 0, input);

                throw nvae;
            }
            switch (alt122) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:361:15: id
                    {
                    pushFollow(FOLLOW_id_in_import_name2154);
                    id();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:361:20: star
                    {
                    pushFollow(FOLLOW_star_in_import_name2158);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:364:1: class_name : id ( dot id )* ( generic )? ;
    public final void class_name() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:365:2: ( id ( dot id )* ( generic )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:365:4: id ( dot id )* ( generic )?
            {
            pushFollow(FOLLOW_id_in_class_name2170);
            id();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:365:7: ( dot id )*
            loop123:
            do {
                int alt123=2;
                alt123 = dfa123.predict(input);
                switch (alt123) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:365:8: dot id
            	    {
            	    pushFollow(FOLLOW_dot_in_class_name2173);
            	    dot();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_class_name2175);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop123;
                }
            } while (true);

            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:365:17: ( generic )?
            int alt124=2;
            alt124 = dfa124.predict(input);
            switch (alt124) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: generic
                    {
                    pushFollow(FOLLOW_generic_in_class_name2179);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:368:1: method_name : ( ( ( super_ | this_ | new_class ) dot )? variable_name ( dot ( class_ | variable_name ) )* | super_ | this_ );
    public final void method_name() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:369:2: ( ( ( super_ | this_ | new_class ) dot )? variable_name ( dot ( class_ | variable_name ) )* | super_ | this_ )
            int alt129=3;
            switch ( input.LA(1) ) {
            case SUPER:
                {
                int LA129_1 = input.LA(2);

                if ( (LA129_1==OPEN_BRACKET) ) {
                    alt129=2;
                }
                else if ( (LA129_1==DOT) ) {
                    alt129=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 129, 1, input);

                    throw nvae;
                }
                }
                break;
            case THIS:
                {
                int LA129_2 = input.LA(2);

                if ( (LA129_2==OPEN_BRACKET) ) {
                    alt129=3;
                }
                else if ( (LA129_2==DOT) ) {
                    alt129=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 129, 2, input);

                    throw nvae;
                }
                }
                break;
            case NEW:
            case ID:
                {
                alt129=1;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 129, 0, input);

                throw nvae;
            }

            switch (alt129) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:369:4: ( ( super_ | this_ | new_class ) dot )? variable_name ( dot ( class_ | variable_name ) )*
                    {
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:369:4: ( ( super_ | this_ | new_class ) dot )?
                    int alt126=2;
                    int LA126_0 = input.LA(1);

                    if ( (LA126_0==NEW||(LA126_0>=THIS && LA126_0<=SUPER)) ) {
                        alt126=1;
                    }
                    switch (alt126) {
                        case 1 :
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:370:4: ( super_ | this_ | new_class ) dot
                            {
                            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:370:4: ( super_ | this_ | new_class )
                            int alt125=3;
                            switch ( input.LA(1) ) {
                            case SUPER:
                                {
                                alt125=1;
                                }
                                break;
                            case THIS:
                                {
                                alt125=2;
                                }
                                break;
                            case NEW:
                                {
                                alt125=3;
                                }
                                break;
                            default:
                                if (state.backtracking>0) {state.failed=true; return ;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 125, 0, input);

                                throw nvae;
                            }

                            switch (alt125) {
                                case 1 :
                                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:370:5: super_
                                    {
                                    pushFollow(FOLLOW_super__in_method_name2197);
                                    super_();

                                    state._fsp--;
                                    if (state.failed) return ;

                                    }
                                    break;
                                case 2 :
                                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:370:14: this_
                                    {
                                    pushFollow(FOLLOW_this__in_method_name2201);
                                    this_();

                                    state._fsp--;
                                    if (state.failed) return ;

                                    }
                                    break;
                                case 3 :
                                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:370:22: new_class
                                    {
                                    pushFollow(FOLLOW_new_class_in_method_name2205);
                                    new_class();

                                    state._fsp--;
                                    if (state.failed) return ;

                                    }
                                    break;

                            }

                            pushFollow(FOLLOW_dot_in_method_name2208);
                            dot();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_variable_name_in_method_name2217);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:373:3: ( dot ( class_ | variable_name ) )*
                    loop128:
                    do {
                        int alt128=2;
                        int LA128_0 = input.LA(1);

                        if ( (LA128_0==DOT) ) {
                            alt128=1;
                        }


                        switch (alt128) {
                    	case 1 :
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:374:4: dot ( class_ | variable_name )
                    	    {
                    	    pushFollow(FOLLOW_dot_in_method_name2227);
                    	    dot();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:374:8: ( class_ | variable_name )
                    	    int alt127=2;
                    	    int LA127_0 = input.LA(1);

                    	    if ( (LA127_0==CLASS) ) {
                    	        alt127=1;
                    	    }
                    	    else if ( (LA127_0==ID) ) {
                    	        alt127=2;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return ;}
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 127, 0, input);

                    	        throw nvae;
                    	    }
                    	    switch (alt127) {
                    	        case 1 :
                    	            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:374:9: class_
                    	            {
                    	            pushFollow(FOLLOW_class__in_method_name2230);
                    	            class_();

                    	            state._fsp--;
                    	            if (state.failed) return ;

                    	            }
                    	            break;
                    	        case 2 :
                    	            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:374:18: variable_name
                    	            {
                    	            pushFollow(FOLLOW_variable_name_in_method_name2234);
                    	            variable_name();

                    	            state._fsp--;
                    	            if (state.failed) return ;

                    	            }
                    	            break;

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop128;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:376:4: super_
                    {
                    pushFollow(FOLLOW_super__in_method_name2245);
                    super_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:377:4: this_
                    {
                    pushFollow(FOLLOW_this__in_method_name2250);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:380:1: variable_name : name ( array )? ;
    public final void variable_name() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:381:2: ( name ( array )? )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:381:4: name ( array )?
            {
            pushFollow(FOLLOW_name_in_variable_name2261);
            name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:381:9: ( array )?
            int alt130=2;
            int LA130_0 = input.LA(1);

            if ( (LA130_0==OPEN_RECT_BRACKET) ) {
                alt130=1;
            }
            switch (alt130) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: array
                    {
                    pushFollow(FOLLOW_array_in_variable_name2263);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:383:1: annotation_name : AT ID ;
    public final JavaParser.annotation_name_return annotation_name() throws RecognitionException {
        JavaParser.annotation_name_return retval = new JavaParser.annotation_name_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:384:2: ( AT ID )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:384:4: AT ID
            {
            match(input,AT,FOLLOW_AT_in_annotation_name2274); if (state.failed) return retval;
            match(input,ID,FOLLOW_ID_in_annotation_name2276); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:385:1: name : id ( dot id )* ;
    public final void name() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:385:6: ( id ( dot id )* )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:385:8: id ( dot id )*
            {
            pushFollow(FOLLOW_id_in_name2285);
            id();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:385:11: ( dot id )*
            loop131:
            do {
                int alt131=2;
                int LA131_0 = input.LA(1);

                if ( (LA131_0==DOT) ) {
                    int LA131_2 = input.LA(2);

                    if ( (LA131_2==ID) ) {
                        int LA131_3 = input.LA(3);

                        if ( (synpred230_JavaParser()) ) {
                            alt131=1;
                        }


                    }


                }


                switch (alt131) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:385:12: dot id
            	    {
            	    pushFollow(FOLLOW_dot_in_name2288);
            	    dot();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_name2290);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop131;
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
    // $ANTLR end "name"


    // $ANTLR start "array"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:387:1: array : ( open_rect_bracket ( value )? close_rect_bracket )+ ;
    public final void array() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:387:7: ( ( open_rect_bracket ( value )? close_rect_bracket )+ )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:387:9: ( open_rect_bracket ( value )? close_rect_bracket )+
            {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:387:9: ( open_rect_bracket ( value )? close_rect_bracket )+
            int cnt133=0;
            loop133:
            do {
                int alt133=2;
                int LA133_0 = input.LA(1);

                if ( (LA133_0==OPEN_RECT_BRACKET) ) {
                    alt133=1;
                }


                switch (alt133) {
            	case 1 :
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:387:10: open_rect_bracket ( value )? close_rect_bracket
            	    {
            	    pushFollow(FOLLOW_open_rect_bracket_in_array2301);
            	    open_rect_bracket();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:387:28: ( value )?
            	    int alt132=2;
            	    int LA132_0 = input.LA(1);

            	    if ( ((LA132_0>=VOID && LA132_0<=NEW)||(LA132_0>=NULL && LA132_0<=SUPER)||(LA132_0>=BOOLEAN && LA132_0<=OPEN_CURLY_BRACKET)||(LA132_0>=INC && LA132_0<=MINUS)||LA132_0==NOT||LA132_0==OPEN_BRACKET||(LA132_0>=BOOL_CONST && LA132_0<=INT_CONST)||(LA132_0>=HEX_CONST && LA132_0<=HEX_LONG_CONST)||LA132_0==FLOAT_CONST||(LA132_0>=STRING_CONST && LA132_0<=CHAR_CONST)) ) {
            	        alt132=1;
            	    }
            	    switch (alt132) {
            	        case 1 :
            	            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: value
            	            {
            	            pushFollow(FOLLOW_value_in_array2303);
            	            value();

            	            state._fsp--;
            	            if (state.failed) return ;

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_close_rect_bracket_in_array2306);
            	    close_rect_bracket();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt133 >= 1 ) break loop133;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(133, input);
                        throw eee;
                }
                cnt133++;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:393:1: package_ : PACKAGE ;
    public final JavaParser.package__return package_() throws RecognitionException {
        JavaParser.package__return retval = new JavaParser.package__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:394:2: ( PACKAGE )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:394:4: PACKAGE
            {
            match(input,PACKAGE,FOLLOW_PACKAGE_in_package_2321); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:395:1: import_ : IMPORT ;
    public final JavaParser.import__return import_() throws RecognitionException {
        JavaParser.import__return retval = new JavaParser.import__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:396:2: ( IMPORT )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:396:4: IMPORT
            {
            match(input,IMPORT,FOLLOW_IMPORT_in_import_2332); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:397:1: class_ : CLASS ;
    public final JavaParser.class__return class_() throws RecognitionException {
        JavaParser.class__return retval = new JavaParser.class__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:397:8: ( CLASS )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:397:10: CLASS
            {
            match(input,CLASS,FOLLOW_CLASS_in_class_2342); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:398:1: enum_ : ENUM ;
    public final JavaParser.enum__return enum_() throws RecognitionException {
        JavaParser.enum__return retval = new JavaParser.enum__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:398:7: ( ENUM )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:398:9: ENUM
            {
            match(input,ENUM,FOLLOW_ENUM_in_enum_2351); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:399:1: interface_ : INTERFACE ;
    public final JavaParser.interface__return interface_() throws RecognitionException {
        JavaParser.interface__return retval = new JavaParser.interface__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:400:2: ( INTERFACE )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:400:4: INTERFACE
            {
            match(input,INTERFACE,FOLLOW_INTERFACE_in_interface_2361); if (state.failed) return retval;
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

    public static class annotation__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "annotation_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:401:1: annotation_ : AT INTERFACE ;
    public final JavaParser.annotation__return annotation_() throws RecognitionException {
        JavaParser.annotation__return retval = new JavaParser.annotation__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:402:2: ( AT INTERFACE )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:402:4: AT INTERFACE
            {
            match(input,AT,FOLLOW_AT_in_annotation_2371); if (state.failed) return retval;
            match(input,INTERFACE,FOLLOW_INTERFACE_in_annotation_2373); if (state.failed) return retval;
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
    // $ANTLR end "annotation_"

    public static class extends__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "extends_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:403:1: extends_ : EXTENDS ;
    public final JavaParser.extends__return extends_() throws RecognitionException {
        JavaParser.extends__return retval = new JavaParser.extends__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:403:9: ( EXTENDS )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:403:11: EXTENDS
            {
            match(input,EXTENDS,FOLLOW_EXTENDS_in_extends_2381); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:404:1: implements_ : IMPLEMENTS ;
    public final JavaParser.implements__return implements_() throws RecognitionException {
        JavaParser.implements__return retval = new JavaParser.implements__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:405:2: ( IMPLEMENTS )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:405:4: IMPLEMENTS
            {
            match(input,IMPLEMENTS,FOLLOW_IMPLEMENTS_in_implements_2391); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:406:1: this_ : THIS ;
    public final JavaParser.this__return this_() throws RecognitionException {
        JavaParser.this__return retval = new JavaParser.this__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:406:7: ( THIS )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:406:9: THIS
            {
            match(input,THIS,FOLLOW_THIS_in_this_2400); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:407:1: super_ : SUPER ;
    public final JavaParser.super__return super_() throws RecognitionException {
        JavaParser.super__return retval = new JavaParser.super__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:407:8: ( SUPER )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:407:10: SUPER
            {
            match(input,SUPER,FOLLOW_SUPER_in_super_2409); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:408:1: void_ : VOID ;
    public final JavaParser.void__return void_() throws RecognitionException {
        JavaParser.void__return retval = new JavaParser.void__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:408:7: ( VOID )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:408:9: VOID
            {
            match(input,VOID,FOLLOW_VOID_in_void_2418); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:409:1: public_ : PUBLIC ;
    public final JavaParser.public__return public_() throws RecognitionException {
        JavaParser.public__return retval = new JavaParser.public__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:409:9: ( PUBLIC )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:409:11: PUBLIC
            {
            match(input,PUBLIC,FOLLOW_PUBLIC_in_public_2427); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:410:1: private_ : PRIVATE ;
    public final JavaParser.private__return private_() throws RecognitionException {
        JavaParser.private__return retval = new JavaParser.private__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:410:9: ( PRIVATE )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:410:11: PRIVATE
            {
            match(input,PRIVATE,FOLLOW_PRIVATE_in_private_2435); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:411:1: protected_ : PROTECTED ;
    public final JavaParser.protected__return protected_() throws RecognitionException {
        JavaParser.protected__return retval = new JavaParser.protected__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:412:2: ( PROTECTED )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:412:4: PROTECTED
            {
            match(input,PROTECTED,FOLLOW_PROTECTED_in_protected_2445); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:413:1: static_ : STATIC ;
    public final JavaParser.static__return static_() throws RecognitionException {
        JavaParser.static__return retval = new JavaParser.static__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:413:9: ( STATIC )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:413:11: STATIC
            {
            match(input,STATIC,FOLLOW_STATIC_in_static_2454); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:414:1: final_ : FINAL ;
    public final JavaParser.final__return final_() throws RecognitionException {
        JavaParser.final__return retval = new JavaParser.final__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:414:8: ( FINAL )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:414:10: FINAL
            {
            match(input,FINAL,FOLLOW_FINAL_in_final_2463); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:415:1: volatile_ : VOLATILE ;
    public final JavaParser.volatile__return volatile_() throws RecognitionException {
        JavaParser.volatile__return retval = new JavaParser.volatile__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:416:2: ( VOLATILE )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:416:4: VOLATILE
            {
            match(input,VOLATILE,FOLLOW_VOLATILE_in_volatile_2474); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:417:1: synchronized_ : SYNCHRONIZED ;
    public final JavaParser.synchronized__return synchronized_() throws RecognitionException {
        JavaParser.synchronized__return retval = new JavaParser.synchronized__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:418:2: ( SYNCHRONIZED )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:418:4: SYNCHRONIZED
            {
            match(input,SYNCHRONIZED,FOLLOW_SYNCHRONIZED_in_synchronized_2484); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:419:1: abstract_ : ABSTRACT ;
    public final JavaParser.abstract__return abstract_() throws RecognitionException {
        JavaParser.abstract__return retval = new JavaParser.abstract__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:420:2: ( ABSTRACT )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:420:4: ABSTRACT
            {
            match(input,ABSTRACT,FOLLOW_ABSTRACT_in_abstract_2494); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:421:1: transient_ : TRANSIENT ;
    public final JavaParser.transient__return transient_() throws RecognitionException {
        JavaParser.transient__return retval = new JavaParser.transient__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:422:2: ( TRANSIENT )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:422:4: TRANSIENT
            {
            match(input,TRANSIENT,FOLLOW_TRANSIENT_in_transient_2504); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:423:1: new_ : NEW ;
    public final JavaParser.new__return new_() throws RecognitionException {
        JavaParser.new__return retval = new JavaParser.new__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:423:6: ( NEW )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:423:8: NEW
            {
            match(input,NEW,FOLLOW_NEW_in_new_2513); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:424:1: try_ : TRY ;
    public final JavaParser.try__return try_() throws RecognitionException {
        JavaParser.try__return retval = new JavaParser.try__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:424:6: ( TRY )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:424:8: TRY
            {
            match(input,TRY,FOLLOW_TRY_in_try_2522); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:425:1: catch_ : CATCH ;
    public final JavaParser.catch__return catch_() throws RecognitionException {
        JavaParser.catch__return retval = new JavaParser.catch__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:425:8: ( CATCH )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:425:10: CATCH
            {
            match(input,CATCH,FOLLOW_CATCH_in_catch_2531); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:426:1: finally_ : FINALLY ;
    public final JavaParser.finally__return finally_() throws RecognitionException {
        JavaParser.finally__return retval = new JavaParser.finally__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:426:9: ( FINALLY )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:426:11: FINALLY
            {
            match(input,FINALLY,FOLLOW_FINALLY_in_finally_2539); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:427:1: throws_ : THROWS ;
    public final JavaParser.throws__return throws_() throws RecognitionException {
        JavaParser.throws__return retval = new JavaParser.throws__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:427:9: ( THROWS )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:427:11: THROWS
            {
            match(input,THROWS,FOLLOW_THROWS_in_throws_2548); if (state.failed) return retval;
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
    // $ANTLR end "throws_"

    public static class throw__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "throw_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:428:1: throw_ : THROW ;
    public final JavaParser.throw__return throw_() throws RecognitionException {
        JavaParser.throw__return retval = new JavaParser.throw__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:428:8: ( THROW )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:428:10: THROW
            {
            match(input,THROW,FOLLOW_THROW_in_throw_2557); if (state.failed) return retval;
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
    // $ANTLR end "throw_"


    // $ANTLR start "for_"
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:430:1: for_ : FOR ;
    public final void for_() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:430:6: ( FOR )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:430:8: FOR
            {
            match(input,FOR,FOLLOW_FOR_in_for_2567); if (state.failed) return ;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:431:1: while_ : WHILE ;
    public final void while_() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:431:8: ( WHILE )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:431:10: WHILE
            {
            match(input,WHILE,FOLLOW_WHILE_in_while_2576); if (state.failed) return ;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:432:1: do_ : DO ;
    public final void do_() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:432:5: ( DO )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:432:7: DO
            {
            match(input,DO,FOLLOW_DO_in_do_2585); if (state.failed) return ;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:433:1: if_ : IF ;
    public final void if_() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:433:5: ( IF )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:433:7: IF
            {
            match(input,IF,FOLLOW_IF_in_if_2594); if (state.failed) return ;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:434:1: else_ : ELSE ;
    public final void else_() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:434:7: ( ELSE )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:434:9: ELSE
            {
            match(input,ELSE,FOLLOW_ELSE_in_else_2603); if (state.failed) return ;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:436:1: switch_ : SWITCH ;
    public final void switch_() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:436:9: ( SWITCH )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:436:11: SWITCH
            {
            match(input,SWITCH,FOLLOW_SWITCH_in_switch_2613); if (state.failed) return ;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:437:1: case_ : CASE ;
    public final void case_() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:437:7: ( CASE )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:437:9: CASE
            {
            match(input,CASE,FOLLOW_CASE_in_case_2622); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              helper.registerOperator("case", 1);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:438:1: default_ : DEFAULT ;
    public final void default_() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:438:9: ( DEFAULT )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:438:11: DEFAULT
            {
            match(input,DEFAULT,FOLLOW_DEFAULT_in_default_2630); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              helper.registerOperator("default", 1);
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:440:1: return_ : RETURN ;
    public final JavaParser.return__return return_() throws RecognitionException {
        JavaParser.return__return retval = new JavaParser.return__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:440:9: ( RETURN )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:440:11: RETURN
            {
            match(input,RETURN,FOLLOW_RETURN_in_return_2640); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:441:1: break_ : ( BREAK id | BREAK );
    public final JavaParser.break__return break_() throws RecognitionException {
        JavaParser.break__return retval = new JavaParser.break__return();
        retval.start = input.LT(1);

        Token BREAK7=null;

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:441:8: ( BREAK id | BREAK )
            int alt134=2;
            int LA134_0 = input.LA(1);

            if ( (LA134_0==BREAK) ) {
                int LA134_1 = input.LA(2);

                if ( (LA134_1==EOF||LA134_1==CLASS||(LA134_1>=VOID && LA134_1<=CONTINUE)||(LA134_1>=THIS && LA134_1<=THROW)||(LA134_1>=PUBLIC && LA134_1<=CLOSE_CURLY_BRACKET)||(LA134_1>=INC && LA134_1<=MINUS)||LA134_1==NOT||LA134_1==COMMA||LA134_1==CLOSE_BRACKET||(LA134_1>=SEMICOLON && LA134_1<=AT)) ) {
                    alt134=2;
                }
                else if ( (LA134_1==ID) ) {
                    int LA134_3 = input.LA(3);

                    if ( (synpred233_JavaParser()) ) {
                        alt134=1;
                    }
                    else if ( (true) ) {
                        alt134=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 134, 3, input);

                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 134, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 134, 0, input);

                throw nvae;
            }
            switch (alt134) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:441:10: BREAK id
                    {
                    BREAK7=(Token)match(input,BREAK,FOLLOW_BREAK_in_break_2649); if (state.failed) return retval;
                    pushFollow(FOLLOW_id_in_break_2651);
                    id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator((BREAK7!=null?BREAK7.getText():null));
                    }

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:442:4: BREAK
                    {
                    match(input,BREAK,FOLLOW_BREAK_in_break_2658); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:444:1: continue_ : CONTINUE ;
    public final JavaParser.continue__return continue_() throws RecognitionException {
        JavaParser.continue__return retval = new JavaParser.continue__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:445:2: ( CONTINUE )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:445:4: CONTINUE
            {
            match(input,CONTINUE,FOLLOW_CONTINUE_in_continue_2670); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:451:1: semicolon : SEMICOLON ;
    public final JavaParser.semicolon_return semicolon() throws RecognitionException {
        JavaParser.semicolon_return retval = new JavaParser.semicolon_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:452:2: ( SEMICOLON )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:452:4: SEMICOLON
            {
            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_semicolon2685); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:453:1: comma : COMMA ;
    public final JavaParser.comma_return comma() throws RecognitionException {
        JavaParser.comma_return retval = new JavaParser.comma_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:453:7: ( COMMA )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:453:9: COMMA
            {
            match(input,COMMA,FOLLOW_COMMA_in_comma2694); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:454:1: colon : COLON ;
    public final JavaParser.colon_return colon() throws RecognitionException {
        JavaParser.colon_return retval = new JavaParser.colon_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:454:7: ( COLON )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:454:9: COLON
            {
            match(input,COLON,FOLLOW_COLON_in_colon2703); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:455:1: question_ : QUESTION ;
    public final JavaParser.question__return question_() throws RecognitionException {
        JavaParser.question__return retval = new JavaParser.question__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:456:2: ( QUESTION )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:456:4: QUESTION
            {
            match(input,QUESTION,FOLLOW_QUESTION_in_question_2713); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:457:1: plus : PLUS ;
    public final JavaParser.plus_return plus() throws RecognitionException {
        JavaParser.plus_return retval = new JavaParser.plus_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:457:6: ( PLUS )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:457:8: PLUS
            {
            match(input,PLUS,FOLLOW_PLUS_in_plus2722); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:458:1: minus : MINUS ;
    public final JavaParser.minus_return minus() throws RecognitionException {
        JavaParser.minus_return retval = new JavaParser.minus_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:458:7: ( MINUS )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:458:9: MINUS
            {
            match(input,MINUS,FOLLOW_MINUS_in_minus2731); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:459:1: star : STAR ;
    public final JavaParser.star_return star() throws RecognitionException {
        JavaParser.star_return retval = new JavaParser.star_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:459:6: ( STAR )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:459:8: STAR
            {
            match(input,STAR,FOLLOW_STAR_in_star2740); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:460:1: dot : DOT ;
    public final JavaParser.dot_return dot() throws RecognitionException {
        JavaParser.dot_return retval = new JavaParser.dot_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:460:5: ( DOT )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:460:7: DOT
            {
            match(input,DOT,FOLLOW_DOT_in_dot2749); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:461:1: assign : ( ASSIGN | INCASSIGN | DECASSIGN | BITORASSIGN | BITANDASSIGN );
    public final JavaParser.assign_return assign() throws RecognitionException {
        JavaParser.assign_return retval = new JavaParser.assign_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:461:8: ( ASSIGN | INCASSIGN | DECASSIGN | BITORASSIGN | BITANDASSIGN )
            int alt135=5;
            switch ( input.LA(1) ) {
            case ASSIGN:
                {
                alt135=1;
                }
                break;
            case INCASSIGN:
                {
                alt135=2;
                }
                break;
            case DECASSIGN:
                {
                alt135=3;
                }
                break;
            case BITORASSIGN:
                {
                alt135=4;
                }
                break;
            case BITANDASSIGN:
                {
                alt135=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 135, 0, input);

                throw nvae;
            }

            switch (alt135) {
                case 1 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:461:10: ASSIGN
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_assign2758); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 2 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:462:4: INCASSIGN
                    {
                    match(input,INCASSIGN,FOLLOW_INCASSIGN_in_assign2765); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 3 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:463:4: DECASSIGN
                    {
                    match(input,DECASSIGN,FOLLOW_DECASSIGN_in_assign2772); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 4 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:464:4: BITORASSIGN
                    {
                    match(input,BITORASSIGN,FOLLOW_BITORASSIGN_in_assign2779); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 5 :
                    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:465:4: BITANDASSIGN
                    {
                    match(input,BITANDASSIGN,FOLLOW_BITANDASSIGN_in_assign2786); if (state.failed) return retval;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:467:1: block_begin : OPEN_CURLY_BRACKET ;
    public final void block_begin() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:468:2: ( OPEN_CURLY_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:468:4: OPEN_CURLY_BRACKET
            {
            match(input,OPEN_CURLY_BRACKET,FOLLOW_OPEN_CURLY_BRACKET_in_block_begin2798); if (state.failed) return ;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:469:1: block_end : CLOSE_CURLY_BRACKET ;
    public final void block_end() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:470:2: ( CLOSE_CURLY_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:470:4: CLOSE_CURLY_BRACKET
            {
            match(input,CLOSE_CURLY_BRACKET,FOLLOW_CLOSE_CURLY_BRACKET_in_block_end2808); if (state.failed) return ;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:471:1: open_bracket : OPEN_BRACKET ;
    public final void open_bracket() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:472:2: ( OPEN_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:472:4: OPEN_BRACKET
            {
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_open_bracket2818); if (state.failed) return ;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:473:1: close_bracket : CLOSE_BRACKET ;
    public final void close_bracket() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:474:2: ( CLOSE_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:474:4: CLOSE_BRACKET
            {
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_close_bracket2828); if (state.failed) return ;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:475:1: open_rect_bracket : OPEN_RECT_BRACKET ;
    public final void open_rect_bracket() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:476:2: ( OPEN_RECT_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:476:4: OPEN_RECT_BRACKET
            {
            match(input,OPEN_RECT_BRACKET,FOLLOW_OPEN_RECT_BRACKET_in_open_rect_bracket2839); if (state.failed) return ;
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
    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:477:1: close_rect_bracket : CLOSE_RECT_BRACKET ;
    public final void close_rect_bracket() throws RecognitionException {
        try {
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:478:2: ( CLOSE_RECT_BRACKET )
            // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:478:4: CLOSE_RECT_BRACKET
            {
            match(input,CLOSE_RECT_BRACKET,FOLLOW_CLOSE_RECT_BRACKET_in_close_rect_bracket2849); if (state.failed) return ;
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
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:33: ( class_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:33: class_def
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
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:45: ( enum_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:45: enum_def
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
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:56: ( interface_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:56: interface_def
        {
        pushFollow(FOLLOW_interface_def_in_synpred4_JavaParser54);
        interface_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred4_JavaParser

    // $ANTLR start synpred5_JavaParser
    public final void synpred5_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:72: ( annotation_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:24:72: annotation_def
        {
        pushFollow(FOLLOW_annotation_def_in_synpred5_JavaParser58);
        annotation_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred5_JavaParser

    // $ANTLR start synpred29_JavaParser
    public final void synpred29_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:65:16: ( method_modifier )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:65:16: method_modifier
        {
        pushFollow(FOLLOW_method_modifier_in_synpred29_JavaParser319);
        method_modifier();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred29_JavaParser

    // $ANTLR start synpred58_JavaParser
    public final void synpred58_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:115:17: ( class_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:115:17: class_def
        {
        pushFollow(FOLLOW_class_def_in_synpred58_JavaParser589);
        class_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred58_JavaParser

    // $ANTLR start synpred59_JavaParser
    public final void synpred59_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:115:29: ( static_init )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:115:29: static_init
        {
        pushFollow(FOLLOW_static_init_in_synpred59_JavaParser593);
        static_init();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred59_JavaParser

    // $ANTLR start synpred60_JavaParser
    public final void synpred60_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:115:43: ( constructor_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:115:43: constructor_def
        {
        pushFollow(FOLLOW_constructor_def_in_synpred60_JavaParser597);
        constructor_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred60_JavaParser

    // $ANTLR start synpred61_JavaParser
    public final void synpred61_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:115:61: ( method_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:115:61: method_def
        {
        pushFollow(FOLLOW_method_def_in_synpred61_JavaParser601);
        method_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred61_JavaParser

    // $ANTLR start synpred62_JavaParser
    public final void synpred62_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:115:74: ( field_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:115:74: field_def
        {
        pushFollow(FOLLOW_field_def_in_synpred62_JavaParser605);
        field_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred62_JavaParser

    // $ANTLR start synpred64_JavaParser
    public final void synpred64_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:17: ( class_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:17: class_def
        {
        pushFollow(FOLLOW_class_def_in_synpred64_JavaParser627);
        class_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred64_JavaParser

    // $ANTLR start synpred65_JavaParser
    public final void synpred65_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:29: ( constructor_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:29: constructor_def
        {
        pushFollow(FOLLOW_constructor_def_in_synpred65_JavaParser631);
        constructor_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred65_JavaParser

    // $ANTLR start synpred66_JavaParser
    public final void synpred66_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:47: ( method_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:47: method_def
        {
        pushFollow(FOLLOW_method_def_in_synpred66_JavaParser635);
        method_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred66_JavaParser

    // $ANTLR start synpred67_JavaParser
    public final void synpred67_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:60: ( field_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:60: field_def
        {
        pushFollow(FOLLOW_field_def_in_synpred67_JavaParser639);
        field_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred67_JavaParser

    // $ANTLR start synpred68_JavaParser
    public final void synpred68_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:72: ( enum_content )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:119:72: enum_content
        {
        pushFollow(FOLLOW_enum_content_in_synpred68_JavaParser643);
        enum_content();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred68_JavaParser

    // $ANTLR start synpred74_JavaParser
    public final void synpred74_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:127:29: ( method_modifier )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:127:29: method_modifier
        {
        pushFollow(FOLLOW_method_modifier_in_synpred74_JavaParser706);
        method_modifier();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred74_JavaParser

    // $ANTLR start synpred87_JavaParser
    public final void synpred87_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:142:9: ( cast )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:142:9: cast
        {
        pushFollow(FOLLOW_cast_in_synpred87_JavaParser853);
        cast();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred87_JavaParser

    // $ANTLR start synpred90_JavaParser
    public final void synpred90_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:144:3: ( right_unary )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:144:3: right_unary
        {
        pushFollow(FOLLOW_right_unary_in_synpred90_JavaParser877);
        right_unary();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred90_JavaParser

    // $ANTLR start synpred91_JavaParser
    public final void synpred91_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:145:4: ( binary_operator value )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:145:4: binary_operator value
        {
        pushFollow(FOLLOW_binary_operator_in_synpred91_JavaParser884);
        binary_operator();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_value_in_synpred91_JavaParser886);
        value();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred91_JavaParser

    // $ANTLR start synpred92_JavaParser
    public final void synpred92_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:146:4: ( question_ value colon value )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:146:4: question_ value colon value
        {
        pushFollow(FOLLOW_question__in_synpred92_JavaParser894);
        question_();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_value_in_synpred92_JavaParser896);
        value();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_colon_in_synpred92_JavaParser898);
        colon();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_value_in_synpred92_JavaParser900);
        value();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred92_JavaParser

    // $ANTLR start synpred93_JavaParser
    public final void synpred93_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:142:9: ( ( cast )? ( left_unary )? ( single_value | open_bracket value close_bracket ) ( right_unary )? ( binary_operator value )? ( question_ value colon value )? )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:142:9: ( cast )? ( left_unary )? ( single_value | open_bracket value close_bracket ) ( right_unary )? ( binary_operator value )? ( question_ value colon value )?
        {
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:142:9: ( cast )?
        int alt153=2;
        alt153 = dfa153.predict(input);
        switch (alt153) {
            case 1 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: cast
                {
                pushFollow(FOLLOW_cast_in_synpred93_JavaParser853);
                cast();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:142:15: ( left_unary )?
        int alt154=2;
        int LA154_0 = input.LA(1);

        if ( ((LA154_0>=INC && LA154_0<=MINUS)||LA154_0==NOT) ) {
            alt154=1;
        }
        switch (alt154) {
            case 1 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: left_unary
                {
                pushFollow(FOLLOW_left_unary_in_synpred93_JavaParser856);
                left_unary();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:143:3: ( single_value | open_bracket value close_bracket )
        int alt155=2;
        int LA155_0 = input.LA(1);

        if ( ((LA155_0>=VOID && LA155_0<=NEW)||(LA155_0>=NULL && LA155_0<=SUPER)||(LA155_0>=BOOLEAN && LA155_0<=OPEN_CURLY_BRACKET)||(LA155_0>=BOOL_CONST && LA155_0<=INT_CONST)||(LA155_0>=HEX_CONST && LA155_0<=HEX_LONG_CONST)||LA155_0==FLOAT_CONST||(LA155_0>=STRING_CONST && LA155_0<=CHAR_CONST)) ) {
            alt155=1;
        }
        else if ( (LA155_0==OPEN_BRACKET) ) {
            alt155=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 155, 0, input);

            throw nvae;
        }
        switch (alt155) {
            case 1 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:143:4: single_value
                {
                pushFollow(FOLLOW_single_value_in_synpred93_JavaParser863);
                single_value();

                state._fsp--;
                if (state.failed) return ;

                }
                break;
            case 2 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:143:19: open_bracket value close_bracket
                {
                pushFollow(FOLLOW_open_bracket_in_synpred93_JavaParser867);
                open_bracket();

                state._fsp--;
                if (state.failed) return ;
                pushFollow(FOLLOW_value_in_synpred93_JavaParser869);
                value();

                state._fsp--;
                if (state.failed) return ;
                pushFollow(FOLLOW_close_bracket_in_synpred93_JavaParser871);
                close_bracket();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:144:3: ( right_unary )?
        int alt156=2;
        int LA156_0 = input.LA(1);

        if ( ((LA156_0>=INC && LA156_0<=DEC)||LA156_0==NOT) ) {
            alt156=1;
        }
        switch (alt156) {
            case 1 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: right_unary
                {
                pushFollow(FOLLOW_right_unary_in_synpred93_JavaParser877);
                right_unary();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:145:3: ( binary_operator value )?
        int alt157=2;
        int LA157_0 = input.LA(1);

        if ( ((LA157_0>=LE && LA157_0<=UNEQUAL)||(LA157_0>=PLUS && LA157_0<=STAR)||(LA157_0>=PERCENT && LA157_0<=BIT_AND)||(LA157_0>=LT && LA157_0<=GT)) ) {
            alt157=1;
        }
        switch (alt157) {
            case 1 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:145:4: binary_operator value
                {
                pushFollow(FOLLOW_binary_operator_in_synpred93_JavaParser884);
                binary_operator();

                state._fsp--;
                if (state.failed) return ;
                pushFollow(FOLLOW_value_in_synpred93_JavaParser886);
                value();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:146:3: ( question_ value colon value )?
        int alt158=2;
        int LA158_0 = input.LA(1);

        if ( (LA158_0==QUESTION) ) {
            alt158=1;
        }
        switch (alt158) {
            case 1 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:146:4: question_ value colon value
                {
                pushFollow(FOLLOW_question__in_synpred93_JavaParser894);
                question_();

                state._fsp--;
                if (state.failed) return ;
                pushFollow(FOLLOW_value_in_synpred93_JavaParser896);
                value();

                state._fsp--;
                if (state.failed) return ;
                pushFollow(FOLLOW_colon_in_synpred93_JavaParser898);
                colon();

                state._fsp--;
                if (state.failed) return ;
                pushFollow(FOLLOW_value_in_synpred93_JavaParser900);
                value();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred93_JavaParser

    // $ANTLR start synpred96_JavaParser
    public final void synpred96_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:149:3: ( right_unary )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:149:3: right_unary
        {
        pushFollow(FOLLOW_right_unary_in_synpred96_JavaParser928);
        right_unary();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred96_JavaParser

    // $ANTLR start synpred97_JavaParser
    public final void synpred97_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:150:4: ( binary_operator value )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:150:4: binary_operator value
        {
        pushFollow(FOLLOW_binary_operator_in_synpred97_JavaParser935);
        binary_operator();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_value_in_synpred97_JavaParser937);
        value();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred97_JavaParser

    // $ANTLR start synpred98_JavaParser
    public final void synpred98_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:151:4: ( question_ value colon value )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:151:4: question_ value colon value
        {
        pushFollow(FOLLOW_question__in_synpred98_JavaParser945);
        question_();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_value_in_synpred98_JavaParser947);
        value();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_colon_in_synpred98_JavaParser949);
        colon();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_value_in_synpred98_JavaParser951);
        value();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred98_JavaParser

    // $ANTLR start synpred99_JavaParser
    public final void synpred99_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:155:4: ( method_call )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:155:4: method_call
        {
        pushFollow(FOLLOW_method_call_in_synpred99_JavaParser964);
        method_call();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred99_JavaParser

    // $ANTLR start synpred100_JavaParser
    public final void synpred100_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:156:5: ( variable_assignment )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:156:5: variable_assignment
        {
        pushFollow(FOLLOW_variable_assignment_in_synpred100_JavaParser970);
        variable_assignment();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred100_JavaParser

    // $ANTLR start synpred101_JavaParser
    public final void synpred101_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:157:4: ( constant )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:157:4: constant
        {
        pushFollow(FOLLOW_constant_in_synpred101_JavaParser975);
        constant();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred101_JavaParser

    // $ANTLR start synpred104_JavaParser
    public final void synpred104_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:158:4: ( ( ( this_ | super_ ) dot )? variable_name )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:158:4: ( ( this_ | super_ ) dot )? variable_name
        {
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:158:4: ( ( this_ | super_ ) dot )?
        int alt161=2;
        int LA161_0 = input.LA(1);

        if ( ((LA161_0>=THIS && LA161_0<=SUPER)) ) {
            alt161=1;
        }
        switch (alt161) {
            case 1 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:158:5: ( this_ | super_ ) dot
                {
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:158:5: ( this_ | super_ )
                int alt160=2;
                int LA160_0 = input.LA(1);

                if ( (LA160_0==THIS) ) {
                    alt160=1;
                }
                else if ( (LA160_0==SUPER) ) {
                    alt160=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 160, 0, input);

                    throw nvae;
                }
                switch (alt160) {
                    case 1 :
                        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:158:6: this_
                        {
                        pushFollow(FOLLOW_this__in_synpred104_JavaParser983);
                        this_();

                        state._fsp--;
                        if (state.failed) return ;

                        }
                        break;
                    case 2 :
                        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:158:14: super_
                        {
                        pushFollow(FOLLOW_super__in_synpred104_JavaParser987);
                        super_();

                        state._fsp--;
                        if (state.failed) return ;

                        }
                        break;

                }

                pushFollow(FOLLOW_dot_in_synpred104_JavaParser990);
                dot();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        pushFollow(FOLLOW_variable_name_in_synpred104_JavaParser994);
        variable_name();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred104_JavaParser

    // $ANTLR start synpred105_JavaParser
    public final void synpred105_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:159:4: ( new_class )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:159:4: new_class
        {
        pushFollow(FOLLOW_new_class_in_synpred105_JavaParser999);
        new_class();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred105_JavaParser

    // $ANTLR start synpred106_JavaParser
    public final void synpred106_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:160:4: ( this_ )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:160:4: this_
        {
        pushFollow(FOLLOW_this__in_synpred106_JavaParser1004);
        this_();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred106_JavaParser

    // $ANTLR start synpred123_JavaParser
    public final void synpred123_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:186:32: ( semicolon )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:186:32: semicolon
        {
        pushFollow(FOLLOW_semicolon_in_synpred123_JavaParser1139);
        semicolon();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred123_JavaParser

    // $ANTLR start synpred124_JavaParser
    public final void synpred124_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:190:4: ( label )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:190:4: label
        {
        pushFollow(FOLLOW_label_in_synpred124_JavaParser1152);
        label();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred124_JavaParser

    // $ANTLR start synpred125_JavaParser
    public final void synpred125_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:191:4: ( variable_assignment semicolon )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:191:4: variable_assignment semicolon
        {
        pushFollow(FOLLOW_variable_assignment_in_synpred125_JavaParser1158);
        variable_assignment();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_semicolon_in_synpred125_JavaParser1160);
        semicolon();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred125_JavaParser

    // $ANTLR start synpred126_JavaParser
    public final void synpred126_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:192:4: ( variable_def semicolon )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:192:4: variable_def semicolon
        {
        pushFollow(FOLLOW_variable_def_in_synpred126_JavaParser1165);
        variable_def();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_semicolon_in_synpred126_JavaParser1167);
        semicolon();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred126_JavaParser

    // $ANTLR start synpred127_JavaParser
    public final void synpred127_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:193:4: ( method_call semicolon )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:193:4: method_call semicolon
        {
        pushFollow(FOLLOW_method_call_in_synpred127_JavaParser1172);
        method_call();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_semicolon_in_synpred127_JavaParser1174);
        semicolon();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred127_JavaParser

    // $ANTLR start synpred141_JavaParser
    public final void synpred141_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:206:30: ( right_unary )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:206:30: right_unary
        {
        pushFollow(FOLLOW_right_unary_in_synpred141_JavaParser1251);
        right_unary();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred141_JavaParser

    // $ANTLR start synpred142_JavaParser
    public final void synpred142_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:210:4: ( label )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:210:4: label
        {
        pushFollow(FOLLOW_label_in_synpred142_JavaParser1263);
        label();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred142_JavaParser

    // $ANTLR start synpred143_JavaParser
    public final void synpred143_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:211:4: ( variable_assignment )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:211:4: variable_assignment
        {
        pushFollow(FOLLOW_variable_assignment_in_synpred143_JavaParser1268);
        variable_assignment();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred143_JavaParser

    // $ANTLR start synpred144_JavaParser
    public final void synpred144_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:212:4: ( variable_def )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:212:4: variable_def
        {
        pushFollow(FOLLOW_variable_def_in_synpred144_JavaParser1273);
        variable_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred144_JavaParser

    // $ANTLR start synpred145_JavaParser
    public final void synpred145_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:213:4: ( method_call )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:213:4: method_call
        {
        pushFollow(FOLLOW_method_call_in_synpred145_JavaParser1278);
        method_call();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred145_JavaParser

    // $ANTLR start synpred167_JavaParser
    public final void synpred167_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:236:11: ( for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon ( value )? semicolon ( statement_wosemicolon ( comma statement_wosemicolon )* )? CLOSE_BRACKET code )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:236:11: for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon ( value )? semicolon ( statement_wosemicolon ( comma statement_wosemicolon )* )? CLOSE_BRACKET code
        {
        pushFollow(FOLLOW_for__in_synpred167_JavaParser1405);
        for_();

        state._fsp--;
        if (state.failed) return ;
        match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_synpred167_JavaParser1407); if (state.failed) return ;
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:236:29: ( variable_def ( comma variable_def )* )?
        int alt166=2;
        int LA166_0 = input.LA(1);

        if ( (LA166_0==VOID||LA166_0==FINAL||(LA166_0>=BOOLEAN && LA166_0<=DOUBLE)||LA166_0==AT||LA166_0==ID) ) {
            alt166=1;
        }
        switch (alt166) {
            case 1 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:236:30: variable_def ( comma variable_def )*
                {
                pushFollow(FOLLOW_variable_def_in_synpred167_JavaParser1410);
                variable_def();

                state._fsp--;
                if (state.failed) return ;
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:236:43: ( comma variable_def )*
                loop165:
                do {
                    int alt165=2;
                    int LA165_0 = input.LA(1);

                    if ( (LA165_0==COMMA) ) {
                        alt165=1;
                    }


                    switch (alt165) {
                	case 1 :
                	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:236:44: comma variable_def
                	    {
                	    pushFollow(FOLLOW_comma_in_synpred167_JavaParser1413);
                	    comma();

                	    state._fsp--;
                	    if (state.failed) return ;
                	    pushFollow(FOLLOW_variable_def_in_synpred167_JavaParser1415);
                	    variable_def();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop165;
                    }
                } while (true);


                }
                break;

        }

        pushFollow(FOLLOW_semicolon_in_synpred167_JavaParser1421);
        semicolon();

        state._fsp--;
        if (state.failed) return ;
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:236:77: ( value )?
        int alt167=2;
        int LA167_0 = input.LA(1);

        if ( ((LA167_0>=VOID && LA167_0<=NEW)||(LA167_0>=NULL && LA167_0<=SUPER)||(LA167_0>=BOOLEAN && LA167_0<=OPEN_CURLY_BRACKET)||(LA167_0>=INC && LA167_0<=MINUS)||LA167_0==NOT||LA167_0==OPEN_BRACKET||(LA167_0>=BOOL_CONST && LA167_0<=INT_CONST)||(LA167_0>=HEX_CONST && LA167_0<=HEX_LONG_CONST)||LA167_0==FLOAT_CONST||(LA167_0>=STRING_CONST && LA167_0<=CHAR_CONST)) ) {
            alt167=1;
        }
        switch (alt167) {
            case 1 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: value
                {
                pushFollow(FOLLOW_value_in_synpred167_JavaParser1423);
                value();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        pushFollow(FOLLOW_semicolon_in_synpred167_JavaParser1426);
        semicolon();

        state._fsp--;
        if (state.failed) return ;
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:236:94: ( statement_wosemicolon ( comma statement_wosemicolon )* )?
        int alt169=2;
        int LA169_0 = input.LA(1);

        if ( ((LA169_0>=VOID && LA169_0<=CONTINUE)||(LA169_0>=THIS && LA169_0<=IF)||LA169_0==SWITCH||LA169_0==TRY||LA169_0==THROW||LA169_0==FINAL||LA169_0==SYNCHRONIZED||(LA169_0>=BOOLEAN && LA169_0<=DOUBLE)||(LA169_0>=INC && LA169_0<=MINUS)||LA169_0==NOT||LA169_0==AT||LA169_0==ID) ) {
            alt169=1;
        }
        switch (alt169) {
            case 1 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:236:95: statement_wosemicolon ( comma statement_wosemicolon )*
                {
                pushFollow(FOLLOW_statement_wosemicolon_in_synpred167_JavaParser1429);
                statement_wosemicolon();

                state._fsp--;
                if (state.failed) return ;
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:236:117: ( comma statement_wosemicolon )*
                loop168:
                do {
                    int alt168=2;
                    int LA168_0 = input.LA(1);

                    if ( (LA168_0==COMMA) ) {
                        alt168=1;
                    }


                    switch (alt168) {
                	case 1 :
                	    // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:236:118: comma statement_wosemicolon
                	    {
                	    pushFollow(FOLLOW_comma_in_synpred167_JavaParser1432);
                	    comma();

                	    state._fsp--;
                	    if (state.failed) return ;
                	    pushFollow(FOLLOW_statement_wosemicolon_in_synpred167_JavaParser1434);
                	    statement_wosemicolon();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop168;
                    }
                } while (true);


                }
                break;

        }

        match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_synpred167_JavaParser1440); if (state.failed) return ;
        pushFollow(FOLLOW_code_in_synpred167_JavaParser1442);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred167_JavaParser

    // $ANTLR start synpred169_JavaParser
    public final void synpred169_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:250:5: ( case_ ( constant | variable_name ) colon )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:250:5: case_ ( constant | variable_name ) colon
        {
        pushFollow(FOLLOW_case__in_synpred169_JavaParser1532);
        case_();

        state._fsp--;
        if (state.failed) return ;
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:250:12: ( constant | variable_name )
        int alt170=2;
        alt170 = dfa170.predict(input);
        switch (alt170) {
            case 1 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:250:13: constant
                {
                pushFollow(FOLLOW_constant_in_synpred169_JavaParser1536);
                constant();

                state._fsp--;
                if (state.failed) return ;

                }
                break;
            case 2 :
                // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:250:24: variable_name
                {
                pushFollow(FOLLOW_variable_name_in_synpred169_JavaParser1540);
                variable_name();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        pushFollow(FOLLOW_colon_in_synpred169_JavaParser1543);
        colon();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred169_JavaParser

    // $ANTLR start synpred170_JavaParser
    public final void synpred170_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:250:48: ( code )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:250:48: code
        {
        pushFollow(FOLLOW_code_in_synpred170_JavaParser1548);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred170_JavaParser

    // $ANTLR start synpred171_JavaParser
    public final void synpred171_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:250:55: ( statement )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:250:55: statement
        {
        pushFollow(FOLLOW_statement_in_synpred171_JavaParser1552);
        statement();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred171_JavaParser

    // $ANTLR start synpred174_JavaParser
    public final void synpred174_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:252:20: ( code )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:252:20: code
        {
        pushFollow(FOLLOW_code_in_synpred174_JavaParser1575);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred174_JavaParser

    // $ANTLR start synpred175_JavaParser
    public final void synpred175_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:252:27: ( statement )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:252:27: statement
        {
        pushFollow(FOLLOW_statement_in_synpred175_JavaParser1579);
        statement();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred175_JavaParser

    // $ANTLR start synpred177_JavaParser
    public final void synpred177_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:257:54: ( else_ code )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:257:54: else_ code
        {
        pushFollow(FOLLOW_else__in_synpred177_JavaParser1611);
        else_();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_code_in_synpred177_JavaParser1613);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred177_JavaParser

    // $ANTLR start synpred178_JavaParser
    public final void synpred178_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:261:15: ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:261:15: catch_ OPEN_BRACKET id id CLOSE_BRACKET code
        {
        pushFollow(FOLLOW_catch__in_synpred178_JavaParser1631);
        catch_();

        state._fsp--;
        if (state.failed) return ;
        match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_synpred178_JavaParser1633); if (state.failed) return ;
        pushFollow(FOLLOW_id_in_synpred178_JavaParser1635);
        id();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_id_in_synpred178_JavaParser1637);
        id();

        state._fsp--;
        if (state.failed) return ;
        match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_synpred178_JavaParser1639); if (state.failed) return ;
        pushFollow(FOLLOW_code_in_synpred178_JavaParser1641);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred178_JavaParser

    // $ANTLR start synpred179_JavaParser
    public final void synpred179_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:261:63: ( finally_ code )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:261:63: finally_ code
        {
        pushFollow(FOLLOW_finally__in_synpred179_JavaParser1646);
        finally_();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_code_in_synpred179_JavaParser1648);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred179_JavaParser

    // $ANTLR start synpred220_JavaParser
    public final void synpred220_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:365:8: ( dot id )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:365:8: dot id
        {
        pushFollow(FOLLOW_dot_in_synpred220_JavaParser2173);
        dot();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_id_in_synpred220_JavaParser2175);
        id();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred220_JavaParser

    // $ANTLR start synpred221_JavaParser
    public final void synpred221_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:365:17: ( generic )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:365:17: generic
        {
        pushFollow(FOLLOW_generic_in_synpred221_JavaParser2179);
        generic();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred221_JavaParser

    // $ANTLR start synpred230_JavaParser
    public final void synpred230_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:385:12: ( dot id )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:385:12: dot id
        {
        pushFollow(FOLLOW_dot_in_synpred230_JavaParser2288);
        dot();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_id_in_synpred230_JavaParser2290);
        id();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred230_JavaParser

    // $ANTLR start synpred233_JavaParser
    public final void synpred233_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:441:10: ( BREAK id )
        // /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/JavaParser.g:441:10: BREAK id
        {
        match(input,BREAK,FOLLOW_BREAK_in_synpred233_JavaParser2649); if (state.failed) return ;
        pushFollow(FOLLOW_id_in_synpred233_JavaParser2651);
        id();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred233_JavaParser

    // Delegated rules

    public final boolean synpred59_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred59_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred127_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred127_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred65_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred65_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred62_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred62_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred97_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred97_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred220_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred220_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred96_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred96_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred92_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred92_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred99_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred99_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred91_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred91_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred126_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred126_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred5_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred177_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred177_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred90_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred90_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred144_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred144_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred174_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred174_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred178_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred178_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred125_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred125_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred143_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred143_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred175_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred175_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred179_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred179_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred67_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred67_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred58_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred58_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred233_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred233_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred169_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred169_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred141_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred141_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred61_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred61_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred87_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred87_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred93_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred93_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred64_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred64_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred98_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred98_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred142_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred142_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred221_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred221_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred60_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred60_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred230_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred230_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred124_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred124_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred66_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred66_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred171_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred171_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred106_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred106_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred170_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred170_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred167_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred167_JavaParser_fragment(); // can never throw exception
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


    protected DFA2 dfa2 = new DFA2(this);
    protected DFA41 dfa41 = new DFA41(this);
    protected DFA43 dfa43 = new DFA43(this);
    protected DFA73 dfa73 = new DFA73(this);
    protected DFA62 dfa62 = new DFA62(this);
    protected DFA66 dfa66 = new DFA66(this);
    protected DFA71 dfa71 = new DFA71(this);
    protected DFA76 dfa76 = new DFA76(this);
    protected DFA87 dfa87 = new DFA87(this);
    protected DFA90 dfa90 = new DFA90(this);
    protected DFA99 dfa99 = new DFA99(this);
    protected DFA101 dfa101 = new DFA101(this);
    protected DFA103 dfa103 = new DFA103(this);
    protected DFA113 dfa113 = new DFA113(this);
    protected DFA123 dfa123 = new DFA123(this);
    protected DFA124 dfa124 = new DFA124(this);
    protected DFA153 dfa153 = new DFA153(this);
    protected DFA170 dfa170 = new DFA170(this);
    static final String DFA2_eotS =
        "\15\uffff";
    static final String DFA2_eofS =
        "\1\1\14\uffff";
    static final String DFA2_minS =
        "\1\6\1\uffff\7\0\4\uffff";
    static final String DFA2_maxS =
        "\1\123\1\uffff\7\0\4\uffff";
    static final String DFA2_acceptS =
        "\1\uffff\1\5\7\uffff\1\1\1\2\1\3\1\4";
    static final String DFA2_specialS =
        "\2\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\4\uffff}>";
    static final String[] DFA2_transitionS = {
            "\1\11\1\13\1\12\27\uffff\1\3\1\4\1\5\1\10\2\uffff\1\6\1\uffff"+
            "\1\7\52\uffff\1\2",
            "",
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
            return "()+ loopback of 24:32: ( class_def | enum_def | interface_def | annotation_def )+";
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
                        if ( (synpred2_JavaParser()) ) {s = 9;}

                        else if ( (synpred3_JavaParser()) ) {s = 10;}

                        else if ( (synpred4_JavaParser()) ) {s = 11;}

                        else if ( (synpred5_JavaParser()) ) {s = 12;}

                         
                        input.seek(index2_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA2_3 = input.LA(1);

                         
                        int index2_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_JavaParser()) ) {s = 9;}

                        else if ( (synpred3_JavaParser()) ) {s = 10;}

                        else if ( (synpred4_JavaParser()) ) {s = 11;}

                        else if ( (synpred5_JavaParser()) ) {s = 12;}

                         
                        input.seek(index2_3);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA2_4 = input.LA(1);

                         
                        int index2_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_JavaParser()) ) {s = 9;}

                        else if ( (synpred3_JavaParser()) ) {s = 10;}

                        else if ( (synpred4_JavaParser()) ) {s = 11;}

                        else if ( (synpred5_JavaParser()) ) {s = 12;}

                         
                        input.seek(index2_4);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA2_5 = input.LA(1);

                         
                        int index2_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_JavaParser()) ) {s = 9;}

                        else if ( (synpred3_JavaParser()) ) {s = 10;}

                        else if ( (synpred4_JavaParser()) ) {s = 11;}

                        else if ( (synpred5_JavaParser()) ) {s = 12;}

                         
                        input.seek(index2_5);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA2_6 = input.LA(1);

                         
                        int index2_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_JavaParser()) ) {s = 9;}

                        else if ( (synpred3_JavaParser()) ) {s = 10;}

                        else if ( (synpred4_JavaParser()) ) {s = 11;}

                        else if ( (synpred5_JavaParser()) ) {s = 12;}

                         
                        input.seek(index2_6);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA2_7 = input.LA(1);

                         
                        int index2_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_JavaParser()) ) {s = 9;}

                        else if ( (synpred3_JavaParser()) ) {s = 10;}

                        else if ( (synpred4_JavaParser()) ) {s = 11;}

                        else if ( (synpred5_JavaParser()) ) {s = 12;}

                         
                        input.seek(index2_7);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA2_8 = input.LA(1);

                         
                        int index2_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_JavaParser()) ) {s = 9;}

                        else if ( (synpred3_JavaParser()) ) {s = 10;}

                        else if ( (synpred4_JavaParser()) ) {s = 11;}

                        else if ( (synpred5_JavaParser()) ) {s = 12;}

                         
                        input.seek(index2_8);
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
    static final String DFA41_eotS =
        "\32\uffff";
    static final String DFA41_eofS =
        "\32\uffff";
    static final String DFA41_minS =
        "\1\6\1\uffff\7\0\1\uffff\14\0\4\uffff";
    static final String DFA41_maxS =
        "\1\126\1\uffff\7\0\1\uffff\14\0\4\uffff";
    static final String DFA41_acceptS =
        "\1\uffff\1\6\7\uffff\1\1\14\uffff\1\5\1\3\1\4\1\2";
    static final String DFA41_specialS =
        "\2\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\7\1\10\1\11\1\12"+
        "\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22\4\uffff}>";
    static final String[] DFA41_transitionS = {
            "\1\11\4\uffff\1\25\24\uffff\1\3\1\4\1\5\1\10\1\26\1\13\1\6\1"+
            "\12\1\7\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\uffff\1\1"+
            "\40\uffff\1\2\2\uffff\1\14",
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
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
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
            return "()* loopback of 115:16: ( class_def | static_init | constructor_def | method_def | field_def )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA41_2 = input.LA(1);

                         
                        int index41_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred58_JavaParser()) ) {s = 9;}

                        else if ( (synpred60_JavaParser()) ) {s = 23;}

                        else if ( (synpred61_JavaParser()) ) {s = 24;}

                        else if ( (synpred62_JavaParser()) ) {s = 22;}

                         
                        input.seek(index41_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA41_3 = input.LA(1);

                         
                        int index41_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred58_JavaParser()) ) {s = 9;}

                        else if ( (synpred60_JavaParser()) ) {s = 23;}

                        else if ( (synpred61_JavaParser()) ) {s = 24;}

                        else if ( (synpred62_JavaParser()) ) {s = 22;}

                         
                        input.seek(index41_3);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA41_4 = input.LA(1);

                         
                        int index41_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred58_JavaParser()) ) {s = 9;}

                        else if ( (synpred60_JavaParser()) ) {s = 23;}

                        else if ( (synpred61_JavaParser()) ) {s = 24;}

                        else if ( (synpred62_JavaParser()) ) {s = 22;}

                         
                        input.seek(index41_4);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA41_5 = input.LA(1);

                         
                        int index41_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred58_JavaParser()) ) {s = 9;}

                        else if ( (synpred60_JavaParser()) ) {s = 23;}

                        else if ( (synpred61_JavaParser()) ) {s = 24;}

                        else if ( (synpred62_JavaParser()) ) {s = 22;}

                         
                        input.seek(index41_5);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA41_6 = input.LA(1);

                         
                        int index41_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred58_JavaParser()) ) {s = 9;}

                        else if ( (synpred59_JavaParser()) ) {s = 25;}

                        else if ( (synpred60_JavaParser()) ) {s = 23;}

                        else if ( (synpred61_JavaParser()) ) {s = 24;}

                        else if ( (synpred62_JavaParser()) ) {s = 22;}

                         
                        input.seek(index41_6);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA41_7 = input.LA(1);

                         
                        int index41_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred58_JavaParser()) ) {s = 9;}

                        else if ( (synpred60_JavaParser()) ) {s = 23;}

                        else if ( (synpred61_JavaParser()) ) {s = 24;}

                         
                        input.seek(index41_7);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA41_8 = input.LA(1);

                         
                        int index41_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred58_JavaParser()) ) {s = 9;}

                        else if ( (synpred60_JavaParser()) ) {s = 23;}

                        else if ( (synpred61_JavaParser()) ) {s = 24;}

                        else if ( (synpred62_JavaParser()) ) {s = 22;}

                         
                        input.seek(index41_8);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA41_10 = input.LA(1);

                         
                        int index41_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred60_JavaParser()) ) {s = 23;}

                        else if ( (synpred61_JavaParser()) ) {s = 24;}

                        else if ( (synpred62_JavaParser()) ) {s = 22;}

                         
                        input.seek(index41_10);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA41_11 = input.LA(1);

                         
                        int index41_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred60_JavaParser()) ) {s = 23;}

                        else if ( (synpred61_JavaParser()) ) {s = 24;}

                         
                        input.seek(index41_11);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA41_12 = input.LA(1);

                         
                        int index41_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred60_JavaParser()) ) {s = 23;}

                        else if ( (synpred61_JavaParser()) ) {s = 24;}

                        else if ( (synpred62_JavaParser()) ) {s = 22;}

                         
                        input.seek(index41_12);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA41_13 = input.LA(1);

                         
                        int index41_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred61_JavaParser()) ) {s = 24;}

                        else if ( (synpred62_JavaParser()) ) {s = 22;}

                         
                        input.seek(index41_13);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA41_14 = input.LA(1);

                         
                        int index41_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred61_JavaParser()) ) {s = 24;}

                        else if ( (synpred62_JavaParser()) ) {s = 22;}

                         
                        input.seek(index41_14);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA41_15 = input.LA(1);

                         
                        int index41_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred61_JavaParser()) ) {s = 24;}

                        else if ( (synpred62_JavaParser()) ) {s = 22;}

                         
                        input.seek(index41_15);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA41_16 = input.LA(1);

                         
                        int index41_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred61_JavaParser()) ) {s = 24;}

                        else if ( (synpred62_JavaParser()) ) {s = 22;}

                         
                        input.seek(index41_16);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA41_17 = input.LA(1);

                         
                        int index41_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred61_JavaParser()) ) {s = 24;}

                        else if ( (synpred62_JavaParser()) ) {s = 22;}

                         
                        input.seek(index41_17);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA41_18 = input.LA(1);

                         
                        int index41_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred61_JavaParser()) ) {s = 24;}

                        else if ( (synpred62_JavaParser()) ) {s = 22;}

                         
                        input.seek(index41_18);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA41_19 = input.LA(1);

                         
                        int index41_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred61_JavaParser()) ) {s = 24;}

                        else if ( (synpred62_JavaParser()) ) {s = 22;}

                         
                        input.seek(index41_19);
                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA41_20 = input.LA(1);

                         
                        int index41_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred61_JavaParser()) ) {s = 24;}

                        else if ( (synpred62_JavaParser()) ) {s = 22;}

                         
                        input.seek(index41_20);
                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA41_21 = input.LA(1);

                         
                        int index41_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred61_JavaParser()) ) {s = 24;}

                        else if ( (synpred62_JavaParser()) ) {s = 22;}

                         
                        input.seek(index41_21);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 41, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA43_eotS =
        "\32\uffff";
    static final String DFA43_eofS =
        "\32\uffff";
    static final String DFA43_minS =
        "\1\6\1\uffff\7\0\1\uffff\14\0\4\uffff";
    static final String DFA43_maxS =
        "\1\126\1\uffff\7\0\1\uffff\14\0\4\uffff";
    static final String DFA43_acceptS =
        "\1\uffff\1\6\7\uffff\1\1\14\uffff\1\4\1\2\1\3\1\5";
    static final String DFA43_specialS =
        "\2\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\7\1\10\1\11\1\12"+
        "\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22\4\uffff}>";
    static final String[] DFA43_transitionS = {
            "\1\11\4\uffff\1\25\24\uffff\1\3\1\4\1\5\1\10\1\26\1\13\1\6\1"+
            "\12\1\7\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\uffff\1\1"+
            "\40\uffff\1\2\2\uffff\1\14",
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
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA43_eot = DFA.unpackEncodedString(DFA43_eotS);
    static final short[] DFA43_eof = DFA.unpackEncodedString(DFA43_eofS);
    static final char[] DFA43_min = DFA.unpackEncodedStringToUnsignedChars(DFA43_minS);
    static final char[] DFA43_max = DFA.unpackEncodedStringToUnsignedChars(DFA43_maxS);
    static final short[] DFA43_accept = DFA.unpackEncodedString(DFA43_acceptS);
    static final short[] DFA43_special = DFA.unpackEncodedString(DFA43_specialS);
    static final short[][] DFA43_transition;

    static {
        int numStates = DFA43_transitionS.length;
        DFA43_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA43_transition[i] = DFA.unpackEncodedString(DFA43_transitionS[i]);
        }
    }

    class DFA43 extends DFA {

        public DFA43(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 43;
            this.eot = DFA43_eot;
            this.eof = DFA43_eof;
            this.min = DFA43_min;
            this.max = DFA43_max;
            this.accept = DFA43_accept;
            this.special = DFA43_special;
            this.transition = DFA43_transition;
        }
        public String getDescription() {
            return "()* loopback of 119:16: ( class_def | constructor_def | method_def | field_def | enum_content )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA43_2 = input.LA(1);

                         
                        int index43_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred64_JavaParser()) ) {s = 9;}

                        else if ( (synpred65_JavaParser()) ) {s = 23;}

                        else if ( (synpred66_JavaParser()) ) {s = 24;}

                        else if ( (synpred67_JavaParser()) ) {s = 22;}

                         
                        input.seek(index43_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA43_3 = input.LA(1);

                         
                        int index43_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred64_JavaParser()) ) {s = 9;}

                        else if ( (synpred65_JavaParser()) ) {s = 23;}

                        else if ( (synpred66_JavaParser()) ) {s = 24;}

                        else if ( (synpred67_JavaParser()) ) {s = 22;}

                         
                        input.seek(index43_3);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA43_4 = input.LA(1);

                         
                        int index43_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred64_JavaParser()) ) {s = 9;}

                        else if ( (synpred65_JavaParser()) ) {s = 23;}

                        else if ( (synpred66_JavaParser()) ) {s = 24;}

                        else if ( (synpred67_JavaParser()) ) {s = 22;}

                         
                        input.seek(index43_4);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA43_5 = input.LA(1);

                         
                        int index43_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred64_JavaParser()) ) {s = 9;}

                        else if ( (synpred65_JavaParser()) ) {s = 23;}

                        else if ( (synpred66_JavaParser()) ) {s = 24;}

                        else if ( (synpred67_JavaParser()) ) {s = 22;}

                         
                        input.seek(index43_5);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA43_6 = input.LA(1);

                         
                        int index43_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred64_JavaParser()) ) {s = 9;}

                        else if ( (synpred65_JavaParser()) ) {s = 23;}

                        else if ( (synpred66_JavaParser()) ) {s = 24;}

                        else if ( (synpred67_JavaParser()) ) {s = 22;}

                         
                        input.seek(index43_6);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA43_7 = input.LA(1);

                         
                        int index43_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred64_JavaParser()) ) {s = 9;}

                        else if ( (synpred65_JavaParser()) ) {s = 23;}

                        else if ( (synpred66_JavaParser()) ) {s = 24;}

                         
                        input.seek(index43_7);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA43_8 = input.LA(1);

                         
                        int index43_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred64_JavaParser()) ) {s = 9;}

                        else if ( (synpred65_JavaParser()) ) {s = 23;}

                        else if ( (synpred66_JavaParser()) ) {s = 24;}

                        else if ( (synpred67_JavaParser()) ) {s = 22;}

                         
                        input.seek(index43_8);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA43_10 = input.LA(1);

                         
                        int index43_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred65_JavaParser()) ) {s = 23;}

                        else if ( (synpred66_JavaParser()) ) {s = 24;}

                        else if ( (synpred67_JavaParser()) ) {s = 22;}

                         
                        input.seek(index43_10);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA43_11 = input.LA(1);

                         
                        int index43_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred65_JavaParser()) ) {s = 23;}

                        else if ( (synpred66_JavaParser()) ) {s = 24;}

                         
                        input.seek(index43_11);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA43_12 = input.LA(1);

                         
                        int index43_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred65_JavaParser()) ) {s = 23;}

                        else if ( (synpred66_JavaParser()) ) {s = 24;}

                        else if ( (synpred67_JavaParser()) ) {s = 22;}

                        else if ( (synpred68_JavaParser()) ) {s = 25;}

                         
                        input.seek(index43_12);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA43_13 = input.LA(1);

                         
                        int index43_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred66_JavaParser()) ) {s = 24;}

                        else if ( (synpred67_JavaParser()) ) {s = 22;}

                         
                        input.seek(index43_13);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA43_14 = input.LA(1);

                         
                        int index43_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred66_JavaParser()) ) {s = 24;}

                        else if ( (synpred67_JavaParser()) ) {s = 22;}

                         
                        input.seek(index43_14);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA43_15 = input.LA(1);

                         
                        int index43_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred66_JavaParser()) ) {s = 24;}

                        else if ( (synpred67_JavaParser()) ) {s = 22;}

                         
                        input.seek(index43_15);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA43_16 = input.LA(1);

                         
                        int index43_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred66_JavaParser()) ) {s = 24;}

                        else if ( (synpred67_JavaParser()) ) {s = 22;}

                         
                        input.seek(index43_16);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA43_17 = input.LA(1);

                         
                        int index43_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred66_JavaParser()) ) {s = 24;}

                        else if ( (synpred67_JavaParser()) ) {s = 22;}

                         
                        input.seek(index43_17);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA43_18 = input.LA(1);

                         
                        int index43_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred66_JavaParser()) ) {s = 24;}

                        else if ( (synpred67_JavaParser()) ) {s = 22;}

                         
                        input.seek(index43_18);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA43_19 = input.LA(1);

                         
                        int index43_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred66_JavaParser()) ) {s = 24;}

                        else if ( (synpred67_JavaParser()) ) {s = 22;}

                         
                        input.seek(index43_19);
                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA43_20 = input.LA(1);

                         
                        int index43_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred66_JavaParser()) ) {s = 24;}

                        else if ( (synpred67_JavaParser()) ) {s = 22;}

                         
                        input.seek(index43_20);
                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA43_21 = input.LA(1);

                         
                        int index43_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred66_JavaParser()) ) {s = 24;}

                        else if ( (synpred67_JavaParser()) ) {s = 22;}

                         
                        input.seek(index43_21);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 43, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA73_eotS =
        "\40\uffff";
    static final String DFA73_eofS =
        "\40\uffff";
    static final String DFA73_minS =
        "\1\13\35\0\2\uffff";
    static final String DFA73_maxS =
        "\1\143\35\0\2\uffff";
    static final String DFA73_acceptS =
        "\36\uffff\1\1\1\2";
    static final String DFA73_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
        "\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
        "\1\32\1\33\1\34\2\uffff}>";
    static final String[] DFA73_transitionS = {
            "\1\23\1\11\3\uffff\1\33\1\10\1\7\26\uffff\1\13\1\14\1\15\1\16"+
            "\1\17\1\20\1\21\1\22\1\35\12\uffff\1\2\1\3\1\6\1\5\10\uffff"+
            "\1\4\6\uffff\1\1\5\uffff\1\34\1\12\1\27\1\uffff\1\25\1\26\1"+
            "\24\1\uffff\1\31\4\uffff\1\30\1\32",
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

    static final short[] DFA73_eot = DFA.unpackEncodedString(DFA73_eotS);
    static final short[] DFA73_eof = DFA.unpackEncodedString(DFA73_eofS);
    static final char[] DFA73_min = DFA.unpackEncodedStringToUnsignedChars(DFA73_minS);
    static final char[] DFA73_max = DFA.unpackEncodedStringToUnsignedChars(DFA73_maxS);
    static final short[] DFA73_accept = DFA.unpackEncodedString(DFA73_acceptS);
    static final short[] DFA73_special = DFA.unpackEncodedString(DFA73_specialS);
    static final short[][] DFA73_transition;

    static {
        int numStates = DFA73_transitionS.length;
        DFA73_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA73_transition[i] = DFA.unpackEncodedString(DFA73_transitionS[i]);
        }
    }

    class DFA73 extends DFA {

        public DFA73(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 73;
            this.eot = DFA73_eot;
            this.eof = DFA73_eof;
            this.min = DFA73_min;
            this.max = DFA73_max;
            this.accept = DFA73_accept;
            this.special = DFA73_special;
            this.transition = DFA73_transition;
        }
        public String getDescription() {
            return "142:1: value : ( ( cast )? ( left_unary )? ( single_value | open_bracket value close_bracket ) ( right_unary )? ( binary_operator value )? ( question_ value colon value )? | ( left_unary )? ( single_value | open_bracket value close_bracket ) ( right_unary )? ( binary_operator value )? ( question_ value colon value )? );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA73_1 = input.LA(1);

                         
                        int index73_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA73_2 = input.LA(1);

                         
                        int index73_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA73_3 = input.LA(1);

                         
                        int index73_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA73_4 = input.LA(1);

                         
                        int index73_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA73_5 = input.LA(1);

                         
                        int index73_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA73_6 = input.LA(1);

                         
                        int index73_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA73_7 = input.LA(1);

                         
                        int index73_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA73_8 = input.LA(1);

                         
                        int index73_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA73_9 = input.LA(1);

                         
                        int index73_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA73_10 = input.LA(1);

                         
                        int index73_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA73_11 = input.LA(1);

                         
                        int index73_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA73_12 = input.LA(1);

                         
                        int index73_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA73_13 = input.LA(1);

                         
                        int index73_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_13);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA73_14 = input.LA(1);

                         
                        int index73_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_14);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA73_15 = input.LA(1);

                         
                        int index73_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_15);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA73_16 = input.LA(1);

                         
                        int index73_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_16);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA73_17 = input.LA(1);

                         
                        int index73_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_17);
                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA73_18 = input.LA(1);

                         
                        int index73_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_18);
                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA73_19 = input.LA(1);

                         
                        int index73_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_19);
                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA73_20 = input.LA(1);

                         
                        int index73_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_20);
                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA73_21 = input.LA(1);

                         
                        int index73_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_21);
                        if ( s>=0 ) return s;
                        break;
                    case 21 : 
                        int LA73_22 = input.LA(1);

                         
                        int index73_22 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_22);
                        if ( s>=0 ) return s;
                        break;
                    case 22 : 
                        int LA73_23 = input.LA(1);

                         
                        int index73_23 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_23);
                        if ( s>=0 ) return s;
                        break;
                    case 23 : 
                        int LA73_24 = input.LA(1);

                         
                        int index73_24 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_24);
                        if ( s>=0 ) return s;
                        break;
                    case 24 : 
                        int LA73_25 = input.LA(1);

                         
                        int index73_25 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_25);
                        if ( s>=0 ) return s;
                        break;
                    case 25 : 
                        int LA73_26 = input.LA(1);

                         
                        int index73_26 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_26);
                        if ( s>=0 ) return s;
                        break;
                    case 26 : 
                        int LA73_27 = input.LA(1);

                         
                        int index73_27 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_27);
                        if ( s>=0 ) return s;
                        break;
                    case 27 : 
                        int LA73_28 = input.LA(1);

                         
                        int index73_28 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_28);
                        if ( s>=0 ) return s;
                        break;
                    case 28 : 
                        int LA73_29 = input.LA(1);

                         
                        int index73_29 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index73_29);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 73, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA62_eotS =
        "\37\uffff";
    static final String DFA62_eofS =
        "\37\uffff";
    static final String DFA62_minS =
        "\1\13\1\0\35\uffff";
    static final String DFA62_maxS =
        "\1\143\1\0\35\uffff";
    static final String DFA62_acceptS =
        "\2\uffff\1\2\33\uffff\1\1";
    static final String DFA62_specialS =
        "\1\uffff\1\0\35\uffff}>";
    static final String[] DFA62_transitionS = {
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
            return "142:9: ( cast )?";
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
                        if ( (synpred87_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index62_1);
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
    static final String DFA66_eotS =
        "\22\uffff";
    static final String DFA66_eofS =
        "\1\20\21\uffff";
    static final String DFA66_minS =
        "\1\62\17\0\2\uffff";
    static final String DFA66_maxS =
        "\1\122\17\0\2\uffff";
    static final String DFA66_acceptS =
        "\20\uffff\1\2\1\1";
    static final String DFA66_specialS =
        "\1\uffff\1\3\1\5\1\0\1\13\1\1\1\4\1\16\1\7\1\11\1\2\1\14\1\15\1"+
        "\6\1\12\1\10\2\uffff}>";
    static final String[] DFA66_transitionS = {
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

    static final short[] DFA66_eot = DFA.unpackEncodedString(DFA66_eotS);
    static final short[] DFA66_eof = DFA.unpackEncodedString(DFA66_eofS);
    static final char[] DFA66_min = DFA.unpackEncodedStringToUnsignedChars(DFA66_minS);
    static final char[] DFA66_max = DFA.unpackEncodedStringToUnsignedChars(DFA66_maxS);
    static final short[] DFA66_accept = DFA.unpackEncodedString(DFA66_acceptS);
    static final short[] DFA66_special = DFA.unpackEncodedString(DFA66_specialS);
    static final short[][] DFA66_transition;

    static {
        int numStates = DFA66_transitionS.length;
        DFA66_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA66_transition[i] = DFA.unpackEncodedString(DFA66_transitionS[i]);
        }
    }

    class DFA66 extends DFA {

        public DFA66(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 66;
            this.eot = DFA66_eot;
            this.eof = DFA66_eof;
            this.min = DFA66_min;
            this.max = DFA66_max;
            this.accept = DFA66_accept;
            this.special = DFA66_special;
            this.transition = DFA66_transition;
        }
        public String getDescription() {
            return "145:3: ( binary_operator value )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA66_3 = input.LA(1);

                         
                        int index66_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred91_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index66_3);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA66_5 = input.LA(1);

                         
                        int index66_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred91_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index66_5);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA66_10 = input.LA(1);

                         
                        int index66_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred91_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index66_10);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA66_1 = input.LA(1);

                         
                        int index66_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred91_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index66_1);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA66_6 = input.LA(1);

                         
                        int index66_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred91_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index66_6);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA66_2 = input.LA(1);

                         
                        int index66_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred91_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index66_2);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA66_13 = input.LA(1);

                         
                        int index66_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred91_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index66_13);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA66_8 = input.LA(1);

                         
                        int index66_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred91_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index66_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA66_15 = input.LA(1);

                         
                        int index66_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred91_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index66_15);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA66_9 = input.LA(1);

                         
                        int index66_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred91_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index66_9);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA66_14 = input.LA(1);

                         
                        int index66_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred91_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index66_14);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA66_4 = input.LA(1);

                         
                        int index66_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred91_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index66_4);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA66_11 = input.LA(1);

                         
                        int index66_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred91_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index66_11);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA66_12 = input.LA(1);

                         
                        int index66_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred91_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index66_12);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA66_7 = input.LA(1);

                         
                        int index66_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred91_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index66_7);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 66, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA71_eotS =
        "\22\uffff";
    static final String DFA71_eofS =
        "\1\20\21\uffff";
    static final String DFA71_minS =
        "\1\62\17\0\2\uffff";
    static final String DFA71_maxS =
        "\1\122\17\0\2\uffff";
    static final String DFA71_acceptS =
        "\20\uffff\1\2\1\1";
    static final String DFA71_specialS =
        "\1\uffff\1\7\1\15\1\12\1\6\1\2\1\11\1\0\1\14\1\3\1\13\1\10\1\1\1"+
        "\16\1\5\1\4\2\uffff}>";
    static final String[] DFA71_transitionS = {
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

    static final short[] DFA71_eot = DFA.unpackEncodedString(DFA71_eotS);
    static final short[] DFA71_eof = DFA.unpackEncodedString(DFA71_eofS);
    static final char[] DFA71_min = DFA.unpackEncodedStringToUnsignedChars(DFA71_minS);
    static final char[] DFA71_max = DFA.unpackEncodedStringToUnsignedChars(DFA71_maxS);
    static final short[] DFA71_accept = DFA.unpackEncodedString(DFA71_acceptS);
    static final short[] DFA71_special = DFA.unpackEncodedString(DFA71_specialS);
    static final short[][] DFA71_transition;

    static {
        int numStates = DFA71_transitionS.length;
        DFA71_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA71_transition[i] = DFA.unpackEncodedString(DFA71_transitionS[i]);
        }
    }

    class DFA71 extends DFA {

        public DFA71(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 71;
            this.eot = DFA71_eot;
            this.eof = DFA71_eof;
            this.min = DFA71_min;
            this.max = DFA71_max;
            this.accept = DFA71_accept;
            this.special = DFA71_special;
            this.transition = DFA71_transition;
        }
        public String getDescription() {
            return "150:3: ( binary_operator value )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA71_7 = input.LA(1);

                         
                        int index71_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred97_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index71_7);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA71_12 = input.LA(1);

                         
                        int index71_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred97_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index71_12);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA71_5 = input.LA(1);

                         
                        int index71_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred97_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index71_5);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA71_9 = input.LA(1);

                         
                        int index71_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred97_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index71_9);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA71_15 = input.LA(1);

                         
                        int index71_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred97_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index71_15);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA71_14 = input.LA(1);

                         
                        int index71_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred97_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index71_14);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA71_4 = input.LA(1);

                         
                        int index71_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred97_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index71_4);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA71_1 = input.LA(1);

                         
                        int index71_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred97_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index71_1);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA71_11 = input.LA(1);

                         
                        int index71_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred97_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index71_11);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA71_6 = input.LA(1);

                         
                        int index71_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred97_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index71_6);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA71_3 = input.LA(1);

                         
                        int index71_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred97_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index71_3);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA71_10 = input.LA(1);

                         
                        int index71_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred97_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index71_10);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA71_8 = input.LA(1);

                         
                        int index71_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred97_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index71_8);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA71_2 = input.LA(1);

                         
                        int index71_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred97_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index71_2);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA71_13 = input.LA(1);

                         
                        int index71_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred97_JavaParser()) ) {s = 17;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index71_13);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 71, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA76_eotS =
        "\36\uffff";
    static final String DFA76_eofS =
        "\36\uffff";
    static final String DFA76_minS =
        "\1\13\4\0\31\uffff";
    static final String DFA76_maxS =
        "\1\143\4\0\31\uffff";
    static final String DFA76_acceptS =
        "\5\uffff\1\3\22\uffff\1\1\1\2\1\4\1\7\1\6\1\5";
    static final String DFA76_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\31\uffff}>";
    static final String[] DFA76_transitionS = {
            "\1\5\1\3\3\uffff\1\5\1\2\1\1\26\uffff\11\5\43\uffff\1\5\1\4"+
            "\1\5\1\uffff\3\5\1\uffff\1\5\4\uffff\2\5",
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

    static final short[] DFA76_eot = DFA.unpackEncodedString(DFA76_eotS);
    static final short[] DFA76_eof = DFA.unpackEncodedString(DFA76_eofS);
    static final char[] DFA76_min = DFA.unpackEncodedStringToUnsignedChars(DFA76_minS);
    static final char[] DFA76_max = DFA.unpackEncodedStringToUnsignedChars(DFA76_maxS);
    static final short[] DFA76_accept = DFA.unpackEncodedString(DFA76_acceptS);
    static final short[] DFA76_special = DFA.unpackEncodedString(DFA76_specialS);
    static final short[][] DFA76_transition;

    static {
        int numStates = DFA76_transitionS.length;
        DFA76_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA76_transition[i] = DFA.unpackEncodedString(DFA76_transitionS[i]);
        }
    }

    class DFA76 extends DFA {

        public DFA76(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 76;
            this.eot = DFA76_eot;
            this.eof = DFA76_eof;
            this.min = DFA76_min;
            this.max = DFA76_max;
            this.accept = DFA76_accept;
            this.special = DFA76_special;
            this.transition = DFA76_transition;
        }
        public String getDescription() {
            return "154:1: single_value : ( method_call | variable_assignment | constant | ( ( this_ | super_ ) dot )? variable_name | new_class | this_ | super_ );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA76_1 = input.LA(1);

                         
                        int index76_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred99_JavaParser()) ) {s = 24;}

                        else if ( (synpred100_JavaParser()) ) {s = 25;}

                        else if ( (synpred104_JavaParser()) ) {s = 26;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index76_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA76_2 = input.LA(1);

                         
                        int index76_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred99_JavaParser()) ) {s = 24;}

                        else if ( (synpred100_JavaParser()) ) {s = 25;}

                        else if ( (synpred104_JavaParser()) ) {s = 26;}

                        else if ( (synpred106_JavaParser()) ) {s = 28;}

                         
                        input.seek(index76_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA76_3 = input.LA(1);

                         
                        int index76_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred99_JavaParser()) ) {s = 24;}

                        else if ( (synpred105_JavaParser()) ) {s = 29;}

                         
                        input.seek(index76_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA76_4 = input.LA(1);

                         
                        int index76_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred99_JavaParser()) ) {s = 24;}

                        else if ( (synpred100_JavaParser()) ) {s = 25;}

                        else if ( (synpred101_JavaParser()) ) {s = 5;}

                        else if ( (synpred104_JavaParser()) ) {s = 26;}

                         
                        input.seek(index76_4);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 76, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA87_eotS =
        "\43\uffff";
    static final String DFA87_eofS =
        "\43\uffff";
    static final String DFA87_minS =
        "\1\13\3\0\37\uffff";
    static final String DFA87_maxS =
        "\1\126\3\0\37\uffff";
    static final String DFA87_acceptS =
        "\4\uffff\1\3\12\uffff\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21\4\uffff\1\1\1\2";
    static final String DFA87_specialS =
        "\1\uffff\1\0\1\1\1\2\37\uffff}>";
    static final String[] DFA87_transitionS = {
            "\1\4\1\17\1\21\1\23\1\22\1\uffff\1\2\1\3\1\24\1\26\1\25\1\30"+
            "\1\uffff\1\27\2\uffff\1\31\2\uffff\1\33\4\uffff\1\4\1\uffff"+
            "\1\32\3\uffff\10\4\13\uffff\4\34\10\uffff\1\34\11\uffff\1\20"+
            "\1\4\2\uffff\1\1",
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
            return "189:1: statement : ( label | variable_assignment semicolon | variable_def semicolon | method_call semicolon | semicolon | return_statement | continue_ | break_ | for_loop | while_loop | do_loop semicolon | switch_case | if_else | try_catch | synchronized_block | throw_ value semicolon | ( left_unary )? variable_name ( right_unary )? );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA87_1 = input.LA(1);

                         
                        int index87_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred124_JavaParser()) ) {s = 33;}

                        else if ( (synpred125_JavaParser()) ) {s = 34;}

                        else if ( (synpred126_JavaParser()) ) {s = 4;}

                        else if ( (synpred127_JavaParser()) ) {s = 15;}

                        else if ( (true) ) {s = 28;}

                         
                        input.seek(index87_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA87_2 = input.LA(1);

                         
                        int index87_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred125_JavaParser()) ) {s = 34;}

                        else if ( (synpred127_JavaParser()) ) {s = 15;}

                         
                        input.seek(index87_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA87_3 = input.LA(1);

                         
                        int index87_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred125_JavaParser()) ) {s = 34;}

                        else if ( (synpred127_JavaParser()) ) {s = 15;}

                         
                        input.seek(index87_3);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 87, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA90_eotS =
        "\42\uffff";
    static final String DFA90_eofS =
        "\42\uffff";
    static final String DFA90_minS =
        "\1\13\3\0\36\uffff";
    static final String DFA90_maxS =
        "\1\126\3\0\36\uffff";
    static final String DFA90_acceptS =
        "\4\uffff\1\3\12\uffff\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\4\uffff\1\1\1\2";
    static final String DFA90_specialS =
        "\1\uffff\1\0\1\1\1\2\36\uffff}>";
    static final String[] DFA90_transitionS = {
            "\1\4\1\17\1\20\1\22\1\21\1\uffff\1\2\1\3\1\23\1\25\1\24\1\27"+
            "\1\uffff\1\26\2\uffff\1\30\2\uffff\1\32\4\uffff\1\4\1\uffff"+
            "\1\31\3\uffff\10\4\13\uffff\4\33\10\uffff\1\33\12\uffff\1\4"+
            "\2\uffff\1\1",
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
            ""
    };

    static final short[] DFA90_eot = DFA.unpackEncodedString(DFA90_eotS);
    static final short[] DFA90_eof = DFA.unpackEncodedString(DFA90_eofS);
    static final char[] DFA90_min = DFA.unpackEncodedStringToUnsignedChars(DFA90_minS);
    static final char[] DFA90_max = DFA.unpackEncodedStringToUnsignedChars(DFA90_maxS);
    static final short[] DFA90_accept = DFA.unpackEncodedString(DFA90_acceptS);
    static final short[] DFA90_special = DFA.unpackEncodedString(DFA90_specialS);
    static final short[][] DFA90_transition;

    static {
        int numStates = DFA90_transitionS.length;
        DFA90_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA90_transition[i] = DFA.unpackEncodedString(DFA90_transitionS[i]);
        }
    }

    class DFA90 extends DFA {

        public DFA90(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 90;
            this.eot = DFA90_eot;
            this.eof = DFA90_eof;
            this.min = DFA90_min;
            this.max = DFA90_max;
            this.accept = DFA90_accept;
            this.special = DFA90_special;
            this.transition = DFA90_transition;
        }
        public String getDescription() {
            return "209:1: statement_wosemicolon : ( label | variable_assignment | variable_def | method_call | return_statement | continue_ | break_ | for_loop | while_loop | do_loop | switch_case | if_else | try_catch | synchronized_block | throw_ value semicolon | ( left_unary )? variable_name ( right_unary )? );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA90_1 = input.LA(1);

                         
                        int index90_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred142_JavaParser()) ) {s = 32;}

                        else if ( (synpred143_JavaParser()) ) {s = 33;}

                        else if ( (synpred144_JavaParser()) ) {s = 4;}

                        else if ( (synpred145_JavaParser()) ) {s = 15;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index90_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA90_2 = input.LA(1);

                         
                        int index90_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred143_JavaParser()) ) {s = 33;}

                        else if ( (synpred145_JavaParser()) ) {s = 15;}

                         
                        input.seek(index90_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA90_3 = input.LA(1);

                         
                        int index90_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred143_JavaParser()) ) {s = 33;}

                        else if ( (synpred145_JavaParser()) ) {s = 15;}

                         
                        input.seek(index90_3);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 90, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA99_eotS =
        "\6\uffff";
    static final String DFA99_eofS =
        "\6\uffff";
    static final String DFA99_minS =
        "\1\13\1\111\1\uffff\1\6\1\uffff\1\111";
    static final String DFA99_maxS =
        "\1\143\1\121\1\uffff\1\126\1\uffff\1\121";
    static final String DFA99_acceptS =
        "\2\uffff\1\1\1\uffff\1\2\1\uffff";
    static final String DFA99_specialS =
        "\6\uffff}>";
    static final String[] DFA99_transitionS = {
            "\1\2\4\uffff\1\2\30\uffff\11\2\43\uffff\1\2\1\1\1\2\1\uffff"+
            "\3\2\1\uffff\1\2\4\uffff\2\2",
            "\1\3\1\uffff\1\2\1\uffff\1\4\3\uffff\1\4",
            "",
            "\1\2\117\uffff\1\5",
            "",
            "\1\3\1\uffff\1\2\1\uffff\1\4\3\uffff\1\4"
    };

    static final short[] DFA99_eot = DFA.unpackEncodedString(DFA99_eotS);
    static final short[] DFA99_eof = DFA.unpackEncodedString(DFA99_eofS);
    static final char[] DFA99_min = DFA.unpackEncodedStringToUnsignedChars(DFA99_minS);
    static final char[] DFA99_max = DFA.unpackEncodedStringToUnsignedChars(DFA99_maxS);
    static final short[] DFA99_accept = DFA.unpackEncodedString(DFA99_acceptS);
    static final short[] DFA99_special = DFA.unpackEncodedString(DFA99_specialS);
    static final short[][] DFA99_transition;

    static {
        int numStates = DFA99_transitionS.length;
        DFA99_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA99_transition[i] = DFA.unpackEncodedString(DFA99_transitionS[i]);
        }
    }

    class DFA99 extends DFA {

        public DFA99(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 99;
            this.eot = DFA99_eot;
            this.eof = DFA99_eof;
            this.min = DFA99_min;
            this.max = DFA99_max;
            this.accept = DFA99_accept;
            this.special = DFA99_special;
            this.transition = DFA99_transition;
        }
        public String getDescription() {
            return "250:12: ( constant | variable_name )";
        }
    }
    static final String DFA101_eotS =
        "\46\uffff";
    static final String DFA101_eofS =
        "\46\uffff";
    static final String DFA101_minS =
        "\1\13\1\0\3\uffff\37\0\2\uffff";
    static final String DFA101_maxS =
        "\1\126\1\0\3\uffff\37\0\2\uffff";
    static final String DFA101_acceptS =
        "\2\uffff\1\3\41\uffff\1\1\1\2";
    static final String DFA101_specialS =
        "\1\uffff\1\0\3\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12"+
        "\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27"+
        "\1\30\1\31\1\32\1\33\1\34\1\35\1\36\1\37\2\uffff}>";
    static final String[] DFA101_transitionS = {
            "\1\22\1\23\1\25\1\1\1\26\1\uffff\1\6\1\7\1\27\1\31\1\30\1\33"+
            "\1\uffff\1\32\2\2\1\34\2\uffff\1\36\4\uffff\1\11\1\uffff\1\35"+
            "\3\uffff\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\44\1\2\11"+
            "\uffff\1\37\1\40\1\43\1\42\10\uffff\1\41\11\uffff\1\24\1\10"+
            "\2\uffff\1\5",
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
            "",
            ""
    };

    static final short[] DFA101_eot = DFA.unpackEncodedString(DFA101_eotS);
    static final short[] DFA101_eof = DFA.unpackEncodedString(DFA101_eofS);
    static final char[] DFA101_min = DFA.unpackEncodedStringToUnsignedChars(DFA101_minS);
    static final char[] DFA101_max = DFA.unpackEncodedStringToUnsignedChars(DFA101_maxS);
    static final short[] DFA101_accept = DFA.unpackEncodedString(DFA101_acceptS);
    static final short[] DFA101_special = DFA.unpackEncodedString(DFA101_specialS);
    static final short[][] DFA101_transition;

    static {
        int numStates = DFA101_transitionS.length;
        DFA101_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA101_transition[i] = DFA.unpackEncodedString(DFA101_transitionS[i]);
        }
    }

    class DFA101 extends DFA {

        public DFA101(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 101;
            this.eot = DFA101_eot;
            this.eof = DFA101_eof;
            this.min = DFA101_min;
            this.max = DFA101_max;
            this.accept = DFA101_accept;
            this.special = DFA101_special;
            this.transition = DFA101_transition;
        }
        public String getDescription() {
            return "()* loopback of 250:47: ( code | statement )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA101_1 = input.LA(1);

                         
                        int index101_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index101_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA101_5 = input.LA(1);

                         
                        int index101_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_5);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA101_6 = input.LA(1);

                         
                        int index101_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_6);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA101_7 = input.LA(1);

                         
                        int index101_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_7);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA101_8 = input.LA(1);

                         
                        int index101_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_8);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA101_9 = input.LA(1);

                         
                        int index101_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_9);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA101_10 = input.LA(1);

                         
                        int index101_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_10);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA101_11 = input.LA(1);

                         
                        int index101_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_11);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA101_12 = input.LA(1);

                         
                        int index101_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_12);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA101_13 = input.LA(1);

                         
                        int index101_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_13);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA101_14 = input.LA(1);

                         
                        int index101_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_14);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA101_15 = input.LA(1);

                         
                        int index101_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_15);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA101_16 = input.LA(1);

                         
                        int index101_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_16);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA101_17 = input.LA(1);

                         
                        int index101_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_17);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA101_18 = input.LA(1);

                         
                        int index101_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_18);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA101_19 = input.LA(1);

                         
                        int index101_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_19);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA101_20 = input.LA(1);

                         
                        int index101_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_20);
                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA101_21 = input.LA(1);

                         
                        int index101_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_21);
                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA101_22 = input.LA(1);

                         
                        int index101_22 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_22);
                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA101_23 = input.LA(1);

                         
                        int index101_23 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_23);
                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA101_24 = input.LA(1);

                         
                        int index101_24 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_24);
                        if ( s>=0 ) return s;
                        break;
                    case 21 : 
                        int LA101_25 = input.LA(1);

                         
                        int index101_25 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_25);
                        if ( s>=0 ) return s;
                        break;
                    case 22 : 
                        int LA101_26 = input.LA(1);

                         
                        int index101_26 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_26);
                        if ( s>=0 ) return s;
                        break;
                    case 23 : 
                        int LA101_27 = input.LA(1);

                         
                        int index101_27 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_27);
                        if ( s>=0 ) return s;
                        break;
                    case 24 : 
                        int LA101_28 = input.LA(1);

                         
                        int index101_28 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_28);
                        if ( s>=0 ) return s;
                        break;
                    case 25 : 
                        int LA101_29 = input.LA(1);

                         
                        int index101_29 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_29);
                        if ( s>=0 ) return s;
                        break;
                    case 26 : 
                        int LA101_30 = input.LA(1);

                         
                        int index101_30 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_30);
                        if ( s>=0 ) return s;
                        break;
                    case 27 : 
                        int LA101_31 = input.LA(1);

                         
                        int index101_31 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_31);
                        if ( s>=0 ) return s;
                        break;
                    case 28 : 
                        int LA101_32 = input.LA(1);

                         
                        int index101_32 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_32);
                        if ( s>=0 ) return s;
                        break;
                    case 29 : 
                        int LA101_33 = input.LA(1);

                         
                        int index101_33 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_33);
                        if ( s>=0 ) return s;
                        break;
                    case 30 : 
                        int LA101_34 = input.LA(1);

                         
                        int index101_34 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_34);
                        if ( s>=0 ) return s;
                        break;
                    case 31 : 
                        int LA101_35 = input.LA(1);

                         
                        int index101_35 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred170_JavaParser()) ) {s = 36;}

                        else if ( (synpred171_JavaParser()) ) {s = 37;}

                         
                        input.seek(index101_35);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 101, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA103_eotS =
        "\46\uffff";
    static final String DFA103_eofS =
        "\46\uffff";
    static final String DFA103_minS =
        "\1\13\3\uffff\40\0\2\uffff";
    static final String DFA103_maxS =
        "\1\126\3\uffff\40\0\2\uffff";
    static final String DFA103_acceptS =
        "\1\uffff\1\3\42\uffff\1\1\1\2";
    static final String DFA103_specialS =
        "\4\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
        "\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
        "\1\32\1\33\1\34\1\35\1\36\1\37\2\uffff}>";
    static final String[] DFA103_transitionS = {
            "\1\21\1\22\1\24\1\26\1\25\1\uffff\1\5\1\6\1\27\1\31\1\30\1\33"+
            "\1\uffff\1\32\2\1\1\34\2\uffff\1\36\4\uffff\1\10\1\uffff\1\35"+
            "\3\uffff\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\44\1\1\11"+
            "\uffff\1\37\1\40\1\43\1\42\10\uffff\1\41\11\uffff\1\23\1\7\2"+
            "\uffff\1\4",
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

    static final short[] DFA103_eot = DFA.unpackEncodedString(DFA103_eotS);
    static final short[] DFA103_eof = DFA.unpackEncodedString(DFA103_eofS);
    static final char[] DFA103_min = DFA.unpackEncodedStringToUnsignedChars(DFA103_minS);
    static final char[] DFA103_max = DFA.unpackEncodedStringToUnsignedChars(DFA103_maxS);
    static final short[] DFA103_accept = DFA.unpackEncodedString(DFA103_acceptS);
    static final short[] DFA103_special = DFA.unpackEncodedString(DFA103_specialS);
    static final short[][] DFA103_transition;

    static {
        int numStates = DFA103_transitionS.length;
        DFA103_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA103_transition[i] = DFA.unpackEncodedString(DFA103_transitionS[i]);
        }
    }

    class DFA103 extends DFA {

        public DFA103(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 103;
            this.eot = DFA103_eot;
            this.eof = DFA103_eof;
            this.min = DFA103_min;
            this.max = DFA103_max;
            this.accept = DFA103_accept;
            this.special = DFA103_special;
            this.transition = DFA103_transition;
        }
        public String getDescription() {
            return "()* loopback of 252:19: ( code | statement )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA103_4 = input.LA(1);

                         
                        int index103_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_4);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA103_5 = input.LA(1);

                         
                        int index103_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_5);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA103_6 = input.LA(1);

                         
                        int index103_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_6);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA103_7 = input.LA(1);

                         
                        int index103_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_7);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA103_8 = input.LA(1);

                         
                        int index103_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_8);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA103_9 = input.LA(1);

                         
                        int index103_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_9);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA103_10 = input.LA(1);

                         
                        int index103_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_10);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA103_11 = input.LA(1);

                         
                        int index103_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_11);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA103_12 = input.LA(1);

                         
                        int index103_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_12);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA103_13 = input.LA(1);

                         
                        int index103_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_13);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA103_14 = input.LA(1);

                         
                        int index103_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_14);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA103_15 = input.LA(1);

                         
                        int index103_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_15);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA103_16 = input.LA(1);

                         
                        int index103_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_16);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA103_17 = input.LA(1);

                         
                        int index103_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_17);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA103_18 = input.LA(1);

                         
                        int index103_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_18);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA103_19 = input.LA(1);

                         
                        int index103_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_19);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA103_20 = input.LA(1);

                         
                        int index103_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_20);
                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA103_21 = input.LA(1);

                         
                        int index103_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_21);
                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA103_22 = input.LA(1);

                         
                        int index103_22 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_22);
                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA103_23 = input.LA(1);

                         
                        int index103_23 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_23);
                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA103_24 = input.LA(1);

                         
                        int index103_24 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_24);
                        if ( s>=0 ) return s;
                        break;
                    case 21 : 
                        int LA103_25 = input.LA(1);

                         
                        int index103_25 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_25);
                        if ( s>=0 ) return s;
                        break;
                    case 22 : 
                        int LA103_26 = input.LA(1);

                         
                        int index103_26 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_26);
                        if ( s>=0 ) return s;
                        break;
                    case 23 : 
                        int LA103_27 = input.LA(1);

                         
                        int index103_27 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_27);
                        if ( s>=0 ) return s;
                        break;
                    case 24 : 
                        int LA103_28 = input.LA(1);

                         
                        int index103_28 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_28);
                        if ( s>=0 ) return s;
                        break;
                    case 25 : 
                        int LA103_29 = input.LA(1);

                         
                        int index103_29 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_29);
                        if ( s>=0 ) return s;
                        break;
                    case 26 : 
                        int LA103_30 = input.LA(1);

                         
                        int index103_30 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_30);
                        if ( s>=0 ) return s;
                        break;
                    case 27 : 
                        int LA103_31 = input.LA(1);

                         
                        int index103_31 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_31);
                        if ( s>=0 ) return s;
                        break;
                    case 28 : 
                        int LA103_32 = input.LA(1);

                         
                        int index103_32 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_32);
                        if ( s>=0 ) return s;
                        break;
                    case 29 : 
                        int LA103_33 = input.LA(1);

                         
                        int index103_33 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_33);
                        if ( s>=0 ) return s;
                        break;
                    case 30 : 
                        int LA103_34 = input.LA(1);

                         
                        int index103_34 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_34);
                        if ( s>=0 ) return s;
                        break;
                    case 31 : 
                        int LA103_35 = input.LA(1);

                         
                        int index103_35 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_JavaParser()) ) {s = 36;}

                        else if ( (synpred175_JavaParser()) ) {s = 37;}

                         
                        input.seek(index103_35);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 103, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA113_eotS =
        "\5\uffff";
    static final String DFA113_eofS =
        "\5\uffff";
    static final String DFA113_minS =
        "\2\13\3\uffff";
    static final String DFA113_maxS =
        "\2\126\3\uffff";
    static final String DFA113_acceptS =
        "\2\uffff\1\1\1\2\1\3";
    static final String DFA113_specialS =
        "\5\uffff}>";
    static final String[] DFA113_transitionS = {
            "\1\4\27\uffff\1\1\5\uffff\10\2\45\uffff\1\3",
            "\1\4\27\uffff\1\1\5\uffff\10\2\45\uffff\1\3",
            "",
            "",
            ""
    };

    static final short[] DFA113_eot = DFA.unpackEncodedString(DFA113_eotS);
    static final short[] DFA113_eof = DFA.unpackEncodedString(DFA113_eofS);
    static final char[] DFA113_min = DFA.unpackEncodedStringToUnsignedChars(DFA113_minS);
    static final char[] DFA113_max = DFA.unpackEncodedStringToUnsignedChars(DFA113_maxS);
    static final short[] DFA113_accept = DFA.unpackEncodedString(DFA113_acceptS);
    static final short[] DFA113_special = DFA.unpackEncodedString(DFA113_specialS);
    static final short[][] DFA113_transition;

    static {
        int numStates = DFA113_transitionS.length;
        DFA113_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA113_transition[i] = DFA.unpackEncodedString(DFA113_transitionS[i]);
        }
    }

    class DFA113 extends DFA {

        public DFA113(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 113;
            this.eot = DFA113_eot;
            this.eof = DFA113_eof;
            this.min = DFA113_min;
            this.max = DFA113_max;
            this.accept = DFA113_accept;
            this.special = DFA113_special;
            this.transition = DFA113_transition;
        }
        public String getDescription() {
            return "272:1: variable_type : ( ( variable_modifier )* primitive ( array )? | ( variable_modifier )* class_name ( array )? | ( variable_modifier )* void_ );";
        }
    }
    static final String DFA123_eotS =
        "\73\uffff";
    static final String DFA123_eofS =
        "\1\1\72\uffff";
    static final String DFA123_minS =
        "\1\12\45\uffff\1\0\24\uffff";
    static final String DFA123_maxS =
        "\1\126\45\uffff\1\0\24\uffff";
    static final String DFA123_acceptS =
        "\1\uffff\1\2\70\uffff\1\1";
    static final String DFA123_specialS =
        "\46\uffff\1\0\24\uffff}>";
    static final String[] DFA123_transitionS = {
            "\6\1\1\uffff\6\1\1\uffff\1\1\2\uffff\1\1\2\uffff\1\1\4\uffff"+
            "\1\1\1\uffff\1\1\3\uffff\16\1\5\uffff\15\1\1\46\12\1\2\uffff"+
            "\1\1",
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

    static final short[] DFA123_eot = DFA.unpackEncodedString(DFA123_eotS);
    static final short[] DFA123_eof = DFA.unpackEncodedString(DFA123_eofS);
    static final char[] DFA123_min = DFA.unpackEncodedStringToUnsignedChars(DFA123_minS);
    static final char[] DFA123_max = DFA.unpackEncodedStringToUnsignedChars(DFA123_maxS);
    static final short[] DFA123_accept = DFA.unpackEncodedString(DFA123_acceptS);
    static final short[] DFA123_special = DFA.unpackEncodedString(DFA123_specialS);
    static final short[][] DFA123_transition;

    static {
        int numStates = DFA123_transitionS.length;
        DFA123_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA123_transition[i] = DFA.unpackEncodedString(DFA123_transitionS[i]);
        }
    }

    class DFA123 extends DFA {

        public DFA123(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 123;
            this.eot = DFA123_eot;
            this.eof = DFA123_eof;
            this.min = DFA123_min;
            this.max = DFA123_max;
            this.accept = DFA123_accept;
            this.special = DFA123_special;
            this.transition = DFA123_transition;
        }
        public String getDescription() {
            return "()* loopback of 365:7: ( dot id )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA123_38 = input.LA(1);

                         
                        int index123_38 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred220_JavaParser()) ) {s = 58;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index123_38);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 123, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA124_eotS =
        "\73\uffff";
    static final String DFA124_eofS =
        "\1\2\72\uffff";
    static final String DFA124_minS =
        "\1\12\1\0\71\uffff";
    static final String DFA124_maxS =
        "\1\126\1\0\71\uffff";
    static final String DFA124_acceptS =
        "\2\uffff\1\2\67\uffff\1\1";
    static final String DFA124_specialS =
        "\1\uffff\1\0\71\uffff}>";
    static final String[] DFA124_transitionS = {
            "\6\2\1\uffff\6\2\1\uffff\1\2\2\uffff\1\2\2\uffff\1\2\4\uffff"+
            "\1\2\1\uffff\1\2\3\uffff\16\2\5\uffff\17\2\1\1\10\2\2\uffff"+
            "\1\2",
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
            ""
    };

    static final short[] DFA124_eot = DFA.unpackEncodedString(DFA124_eotS);
    static final short[] DFA124_eof = DFA.unpackEncodedString(DFA124_eofS);
    static final char[] DFA124_min = DFA.unpackEncodedStringToUnsignedChars(DFA124_minS);
    static final char[] DFA124_max = DFA.unpackEncodedStringToUnsignedChars(DFA124_maxS);
    static final short[] DFA124_accept = DFA.unpackEncodedString(DFA124_acceptS);
    static final short[] DFA124_special = DFA.unpackEncodedString(DFA124_specialS);
    static final short[][] DFA124_transition;

    static {
        int numStates = DFA124_transitionS.length;
        DFA124_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA124_transition[i] = DFA.unpackEncodedString(DFA124_transitionS[i]);
        }
    }

    class DFA124 extends DFA {

        public DFA124(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 124;
            this.eot = DFA124_eot;
            this.eof = DFA124_eof;
            this.min = DFA124_min;
            this.max = DFA124_max;
            this.accept = DFA124_accept;
            this.special = DFA124_special;
            this.transition = DFA124_transition;
        }
        public String getDescription() {
            return "365:17: ( generic )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA124_1 = input.LA(1);

                         
                        int index124_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred221_JavaParser()) ) {s = 58;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index124_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 124, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA153_eotS =
        "\37\uffff";
    static final String DFA153_eofS =
        "\37\uffff";
    static final String DFA153_minS =
        "\1\13\1\0\35\uffff";
    static final String DFA153_maxS =
        "\1\143\1\0\35\uffff";
    static final String DFA153_acceptS =
        "\2\uffff\1\2\33\uffff\1\1";
    static final String DFA153_specialS =
        "\1\uffff\1\0\35\uffff}>";
    static final String[] DFA153_transitionS = {
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

    static final short[] DFA153_eot = DFA.unpackEncodedString(DFA153_eotS);
    static final short[] DFA153_eof = DFA.unpackEncodedString(DFA153_eofS);
    static final char[] DFA153_min = DFA.unpackEncodedStringToUnsignedChars(DFA153_minS);
    static final char[] DFA153_max = DFA.unpackEncodedStringToUnsignedChars(DFA153_maxS);
    static final short[] DFA153_accept = DFA.unpackEncodedString(DFA153_acceptS);
    static final short[] DFA153_special = DFA.unpackEncodedString(DFA153_specialS);
    static final short[][] DFA153_transition;

    static {
        int numStates = DFA153_transitionS.length;
        DFA153_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA153_transition[i] = DFA.unpackEncodedString(DFA153_transitionS[i]);
        }
    }

    class DFA153 extends DFA {

        public DFA153(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 153;
            this.eot = DFA153_eot;
            this.eof = DFA153_eof;
            this.min = DFA153_min;
            this.max = DFA153_max;
            this.accept = DFA153_accept;
            this.special = DFA153_special;
            this.transition = DFA153_transition;
        }
        public String getDescription() {
            return "142:9: ( cast )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA153_1 = input.LA(1);

                         
                        int index153_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred87_JavaParser()) ) {s = 30;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index153_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 153, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA170_eotS =
        "\6\uffff";
    static final String DFA170_eofS =
        "\6\uffff";
    static final String DFA170_minS =
        "\1\13\1\111\1\uffff\1\6\1\uffff\1\111";
    static final String DFA170_maxS =
        "\1\143\1\121\1\uffff\1\126\1\uffff\1\121";
    static final String DFA170_acceptS =
        "\2\uffff\1\1\1\uffff\1\2\1\uffff";
    static final String DFA170_specialS =
        "\6\uffff}>";
    static final String[] DFA170_transitionS = {
            "\1\2\4\uffff\1\2\30\uffff\11\2\43\uffff\1\2\1\1\1\2\1\uffff"+
            "\3\2\1\uffff\1\2\4\uffff\2\2",
            "\1\3\1\uffff\1\2\1\uffff\1\4\3\uffff\1\4",
            "",
            "\1\2\117\uffff\1\5",
            "",
            "\1\3\1\uffff\1\2\1\uffff\1\4\3\uffff\1\4"
    };

    static final short[] DFA170_eot = DFA.unpackEncodedString(DFA170_eotS);
    static final short[] DFA170_eof = DFA.unpackEncodedString(DFA170_eofS);
    static final char[] DFA170_min = DFA.unpackEncodedStringToUnsignedChars(DFA170_minS);
    static final char[] DFA170_max = DFA.unpackEncodedStringToUnsignedChars(DFA170_maxS);
    static final short[] DFA170_accept = DFA.unpackEncodedString(DFA170_acceptS);
    static final short[] DFA170_special = DFA.unpackEncodedString(DFA170_specialS);
    static final short[][] DFA170_transition;

    static {
        int numStates = DFA170_transitionS.length;
        DFA170_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA170_transition[i] = DFA.unpackEncodedString(DFA170_transitionS[i]);
        }
    }

    class DFA170 extends DFA {

        public DFA170(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 170;
            this.eot = DFA170_eot;
            this.eof = DFA170_eof;
            this.min = DFA170_min;
            this.max = DFA170_max;
            this.accept = DFA170_accept;
            this.special = DFA170_special;
            this.transition = DFA170_transition;
        }
        public String getDescription() {
            return "250:12: ( constant | variable_name )";
        }
    }
 

    public static final BitSet FOLLOW_package_def_in_file40 = new BitSet(new long[]{0x0000014F000001E0L,0x0000000000080000L});
    public static final BitSet FOLLOW_import_def_in_file42 = new BitSet(new long[]{0x0000014F000001E0L,0x0000000000080000L});
    public static final BitSet FOLLOW_class_def_in_file46 = new BitSet(new long[]{0x0000014F000001E2L,0x0000000000080000L});
    public static final BitSet FOLLOW_enum_def_in_file50 = new BitSet(new long[]{0x0000014F000001E2L,0x0000000000080000L});
    public static final BitSet FOLLOW_interface_def_in_file54 = new BitSet(new long[]{0x0000014F000001E2L,0x0000000000080000L});
    public static final BitSet FOLLOW_annotation_def_in_file58 = new BitSet(new long[]{0x0000014F000001E2L,0x0000000000080000L});
    public static final BitSet FOLLOW_package__in_package_def72 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_package_name_in_package_def74 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_package_def76 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_import__in_import_def88 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_import_name_in_import_def90 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_import_def92 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_class_def104 = new BitSet(new long[]{0x0000014F00000040L,0x0000000000080000L});
    public static final BitSet FOLLOW_class_modifier_in_class_def107 = new BitSet(new long[]{0x0000014F00000040L,0x0000000000080000L});
    public static final BitSet FOLLOW_class__in_class_def110 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_class_def112 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000800L});
    public static final BitSet FOLLOW_generic_in_class_def114 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000800L});
    public static final BitSet FOLLOW_extends__in_class_def118 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_class_name_in_class_def120 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000800L});
    public static final BitSet FOLLOW_implements__in_class_def125 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_class_name_in_class_def127 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000C00L});
    public static final BitSet FOLLOW_comma_in_class_def130 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_class_name_in_class_def132 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000C00L});
    public static final BitSet FOLLOW_class_block_in_class_def138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_enum_def154 = new BitSet(new long[]{0x0000014F00000100L,0x0000000000080000L});
    public static final BitSet FOLLOW_class_modifier_in_enum_def157 = new BitSet(new long[]{0x0000014F00000100L,0x0000000000080000L});
    public static final BitSet FOLLOW_enum__in_enum_def160 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_enum_def162 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000800L});
    public static final BitSet FOLLOW_generic_in_enum_def164 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000800L});
    public static final BitSet FOLLOW_extends__in_enum_def168 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_class_name_in_enum_def170 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000800L});
    public static final BitSet FOLLOW_enum_block_in_enum_def174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_interface_def190 = new BitSet(new long[]{0x0000014F00000080L,0x0000000000080000L});
    public static final BitSet FOLLOW_class_modifier_in_interface_def193 = new BitSet(new long[]{0x0000014F00000080L,0x0000000000080000L});
    public static final BitSet FOLLOW_interface__in_interface_def196 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_interface_def198 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000800L});
    public static final BitSet FOLLOW_generic_in_interface_def200 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000800L});
    public static final BitSet FOLLOW_extends__in_interface_def204 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_class_name_in_interface_def206 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000800L});
    public static final BitSet FOLLOW_interface_block_in_interface_def210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_annotation_def226 = new BitSet(new long[]{0x0000014F000001E0L,0x0000000000080000L});
    public static final BitSet FOLLOW_class_modifier_in_annotation_def229 = new BitSet(new long[]{0x0000014F000001E0L,0x0000000000080000L});
    public static final BitSet FOLLOW_annotation__in_annotation_def232 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_annotation_def234 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000800L});
    public static final BitSet FOLLOW_interface_block_in_annotation_def236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_static__in_static_init252 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000800L});
    public static final BitSet FOLLOW_block_begin_in_static_init254 = new BitSet(new long[]{0xF007FF6F497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_static_init256 = new BitSet(new long[]{0xF007FF6F497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_block_end_in_static_init259 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_static_init261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_constructor_def273 = new BitSet(new long[]{0x000001EF00000000L,0x0000000000480000L});
    public static final BitSet FOLLOW_method_modifier_in_constructor_def276 = new BitSet(new long[]{0x000001EF00000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_constructor_def279 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_open_bracket_in_constructor_def281 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_argument_def_in_constructor_def283 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_close_bracket_in_constructor_def285 = new BitSet(new long[]{0xF003FF6FC97EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_throws__in_constructor_def288 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_class_name_in_constructor_def290 = new BitSet(new long[]{0xF003FF6F497EFE00L,0x00000000004C0D00L});
    public static final BitSet FOLLOW_comma_in_constructor_def293 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_class_name_in_constructor_def295 = new BitSet(new long[]{0xF003FF6F497EFE00L,0x00000000004C0D00L});
    public static final BitSet FOLLOW_code_in_constructor_def301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_method_def316 = new BitSet(new long[]{0x0001FFEF00000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_method_modifier_in_method_def319 = new BitSet(new long[]{0x0001FFEF00000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_variable_type_in_method_def322 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_method_def324 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_open_bracket_in_method_def326 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_argument_def_in_method_def328 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_close_bracket_in_method_def330 = new BitSet(new long[]{0xF003FF6FC97EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_throws__in_method_def333 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_class_name_in_method_def335 = new BitSet(new long[]{0xF003FF6F497EFE00L,0x00000000004C0D00L});
    public static final BitSet FOLLOW_comma_in_method_def338 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_class_name_in_method_def340 = new BitSet(new long[]{0xF003FF6F497EFE00L,0x00000000004C0D00L});
    public static final BitSet FOLLOW_code_in_method_def346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_field_def362 = new BitSet(new long[]{0x0001FFDF00000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_field_modifier_in_field_def365 = new BitSet(new long[]{0x0001FFDF00000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_variable_type_in_field_def368 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400000L});
    public static final BitSet FOLLOW_variable_name_in_field_def370 = new BitSet(new long[]{0x0F80000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_assign_in_field_def373 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_field_def375 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_field_def379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_type_in_argument_def391 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400200L});
    public static final BitSet FOLLOW_dot_in_argument_def394 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_argument_def396 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_argument_def398 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400000L});
    public static final BitSet FOLLOW_variable_name_in_argument_def402 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_comma_in_argument_def405 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_variable_type_in_argument_def407 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400200L});
    public static final BitSet FOLLOW_dot_in_argument_def410 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_argument_def412 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_argument_def414 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400000L});
    public static final BitSet FOLLOW_variable_name_in_argument_def418 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_annotation_in_variable_def433 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_variable_type_in_variable_def436 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400000L});
    public static final BitSet FOLLOW_variable_name_in_variable_def438 = new BitSet(new long[]{0x0F80000000000002L});
    public static final BitSet FOLLOW_assign_in_variable_def441 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_variable_def443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_public__in_class_modifier457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_private__in_class_modifier462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_protected__in_class_modifier467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_static__in_class_modifier472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_abstract__in_class_modifier477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_final__in_class_modifier482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_public__in_method_modifier493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_private__in_method_modifier498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_protected__in_method_modifier503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_static__in_method_modifier508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_final__in_method_modifier513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_transient__in_method_modifier518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_synchronized__in_method_modifier523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_abstract__in_method_modifier528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_public__in_field_modifier539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_private__in_field_modifier544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_protected__in_field_modifier549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_static__in_field_modifier554 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_transient__in_field_modifier559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_volatile__in_field_modifier564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_final__in_variable_modifier575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_begin_in_class_block586 = new BitSet(new long[]{0xF007FFFF497EFE40L,0x00000000004C0900L});
    public static final BitSet FOLLOW_class_def_in_class_block589 = new BitSet(new long[]{0xF007FFFF497EFE40L,0x00000000004C0900L});
    public static final BitSet FOLLOW_static_init_in_class_block593 = new BitSet(new long[]{0xF007FFFF497EFE40L,0x00000000004C0900L});
    public static final BitSet FOLLOW_constructor_def_in_class_block597 = new BitSet(new long[]{0xF007FFFF497EFE40L,0x00000000004C0900L});
    public static final BitSet FOLLOW_method_def_in_class_block601 = new BitSet(new long[]{0xF007FFFF497EFE40L,0x00000000004C0900L});
    public static final BitSet FOLLOW_field_def_in_class_block605 = new BitSet(new long[]{0xF007FFFF497EFE40L,0x00000000004C0900L});
    public static final BitSet FOLLOW_block_end_in_class_block609 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_class_block611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_begin_in_enum_block624 = new BitSet(new long[]{0xF007FFFF497EFE40L,0x00000000004C0900L});
    public static final BitSet FOLLOW_class_def_in_enum_block627 = new BitSet(new long[]{0xF007FFFF497EFE40L,0x00000000004C0900L});
    public static final BitSet FOLLOW_constructor_def_in_enum_block631 = new BitSet(new long[]{0xF007FFFF497EFE40L,0x00000000004C0900L});
    public static final BitSet FOLLOW_method_def_in_enum_block635 = new BitSet(new long[]{0xF007FFFF497EFE40L,0x00000000004C0900L});
    public static final BitSet FOLLOW_field_def_in_enum_block639 = new BitSet(new long[]{0xF007FFFF497EFE40L,0x00000000004C0900L});
    public static final BitSet FOLLOW_enum_content_in_enum_block643 = new BitSet(new long[]{0xF007FFFF497EFE40L,0x00000000004C0900L});
    public static final BitSet FOLLOW_block_end_in_enum_block647 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_enum_block649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_enum_content661 = new BitSet(new long[]{0x0000000000000000L,0x0000000000048400L});
    public static final BitSet FOLLOW_open_bracket_in_enum_content664 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE98900L});
    public static final BitSet FOLLOW_arguments_in_enum_content666 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_close_bracket_in_enum_content668 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040400L});
    public static final BitSet FOLLOW_comma_in_enum_content673 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_enum_content675 = new BitSet(new long[]{0x0000000000000000L,0x0000000000048400L});
    public static final BitSet FOLLOW_open_bracket_in_enum_content678 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE98900L});
    public static final BitSet FOLLOW_arguments_in_enum_content680 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_close_bracket_in_enum_content682 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040400L});
    public static final BitSet FOLLOW_semicolon_in_enum_content688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_begin_in_interface_block700 = new BitSet(new long[]{0xF007FFEF497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_annotation_in_interface_block703 = new BitSet(new long[]{0x0001FFEF00000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_method_modifier_in_interface_block706 = new BitSet(new long[]{0x0001FFEF00000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_variable_type_in_interface_block709 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_interface_block711 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_open_bracket_in_interface_block713 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_argument_def_in_interface_block715 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_close_bracket_in_interface_block717 = new BitSet(new long[]{0x0000000080000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_throws__in_interface_block720 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_class_name_in_interface_block722 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040400L});
    public static final BitSet FOLLOW_comma_in_interface_block725 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_class_name_in_interface_block727 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040400L});
    public static final BitSet FOLLOW_semicolon_in_interface_block733 = new BitSet(new long[]{0xF007FFEF497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_block_end_in_interface_block737 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_interface_block739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_name_in_method_call752 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_open_bracket_in_method_call754 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE98900L});
    public static final BitSet FOLLOW_arguments_in_method_call756 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_close_bracket_in_method_call758 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_method_call761 = new BitSet(new long[]{0x0000014F00000040L,0x0000000000480000L});
    public static final BitSet FOLLOW_id_in_method_call764 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008200L});
    public static final BitSet FOLLOW_class__in_method_call768 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008200L});
    public static final BitSet FOLLOW_open_bracket_in_method_call772 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE98900L});
    public static final BitSet FOLLOW_arguments_in_method_call774 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_close_bracket_in_method_call776 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_annotation_name_in_annotation791 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_open_bracket_in_annotation794 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_annotation796 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000490400L});
    public static final BitSet FOLLOW_comma_in_annotation799 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_annotation801 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000490400L});
    public static final BitSet FOLLOW_close_bracket_in_annotation805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_generic817 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000480004L});
    public static final BitSet FOLLOW_variable_type_in_generic820 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001400L});
    public static final BitSet FOLLOW_question__in_generic824 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001400L});
    public static final BitSet FOLLOW_comma_in_generic828 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000480004L});
    public static final BitSet FOLLOW_variable_type_in_generic831 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001400L});
    public static final BitSet FOLLOW_question__in_generic835 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001400L});
    public static final BitSet FOLLOW_GT_in_generic840 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cast_in_value853 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_left_unary_in_value856 = new BitSet(new long[]{0x0003FF4F00071E00L,0x0000000C2EE88800L});
    public static final BitSet FOLLOW_single_value_in_value863 = new BitSet(new long[]{0xF079FF4F00000802L,0x00000000004819FFL});
    public static final BitSet FOLLOW_open_bracket_in_value867 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_value869 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_close_bracket_in_value871 = new BitSet(new long[]{0xF079FF4F00000802L,0x00000000004819FFL});
    public static final BitSet FOLLOW_right_unary_in_value877 = new BitSet(new long[]{0xC079FF4F00000802L,0x00000000004818FFL});
    public static final BitSet FOLLOW_binary_operator_in_value884 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_value886 = new BitSet(new long[]{0x0001FF4F00000802L,0x0000000000480004L});
    public static final BitSet FOLLOW_question__in_value894 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_value896 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_colon_in_value898 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_value900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_left_unary_in_value907 = new BitSet(new long[]{0x0003FF4F00071E00L,0x0000000C2EE88800L});
    public static final BitSet FOLLOW_single_value_in_value914 = new BitSet(new long[]{0xF079FF4F00000802L,0x00000000004819FFL});
    public static final BitSet FOLLOW_open_bracket_in_value918 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_value920 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_close_bracket_in_value922 = new BitSet(new long[]{0xF079FF4F00000802L,0x00000000004819FFL});
    public static final BitSet FOLLOW_right_unary_in_value928 = new BitSet(new long[]{0xC079FF4F00000802L,0x00000000004818FFL});
    public static final BitSet FOLLOW_binary_operator_in_value935 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_value937 = new BitSet(new long[]{0x0001FF4F00000802L,0x0000000000480004L});
    public static final BitSet FOLLOW_question__in_value945 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_value947 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_colon_in_value949 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_value951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_single_value964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_single_value970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constant_in_single_value975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_this__in_single_value983 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_super__in_single_value987 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_single_value990 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400000L});
    public static final BitSet FOLLOW_variable_name_in_single_value994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_new_class_in_single_value999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_this__in_single_value1004 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_super__in_single_value1009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_const_in_constant1018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_hex_long_const_in_constant1023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_hex_const_in_constant1028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_long_const_in_constant1033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_int_const_in_constant1038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_const_in_constant1043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_float_const_in_constant1048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_char_const_in_constant1053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_null_const_in_constant1058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_boolean_const_in_constant1063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_array_const_in_constant1068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_new__in_new_class1080 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_variable_type_in_new_class1082 = new BitSet(new long[]{0x0003FF4F00010E02L,0x0000000C2EE88800L});
    public static final BitSet FOLLOW_array_const_in_new_class1084 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_open_bracket_in_new_class1088 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE98900L});
    public static final BitSet FOLLOW_arguments_in_new_class1090 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_close_bracket_in_new_class1092 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_value_in_arguments1108 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_comma_in_arguments1111 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_arguments1113 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_statement_in_code1127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_begin_in_code1132 = new BitSet(new long[]{0xF007FF6F497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_code1134 = new BitSet(new long[]{0xF007FF6F497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_block_end_in_code1137 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_code1139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_label_in_statement1152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_statement1158 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_statement1160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_statement1165 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_statement1167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_statement1172 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_statement1174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_semicolon_in_statement1180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_statement_in_statement1185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_continue__in_statement1190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_break__in_statement1195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for_loop_in_statement1200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while_loop_in_statement1205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_do_loop_in_statement1210 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_statement1212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_switch_case_in_statement1217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_else_in_statement1222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_try_catch_in_statement1227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_synchronized_block_in_statement1232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_throw__in_statement1237 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_statement1239 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_statement1241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_left_unary_in_statement1246 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400000L});
    public static final BitSet FOLLOW_variable_name_in_statement1249 = new BitSet(new long[]{0x3000000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_right_unary_in_statement1251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_label_in_statement_wosemicolon1263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_statement_wosemicolon1268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_statement_wosemicolon1273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_statement_wosemicolon1278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_statement_in_statement_wosemicolon1284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_continue__in_statement_wosemicolon1289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_break__in_statement_wosemicolon1294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for_loop_in_statement_wosemicolon1299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while_loop_in_statement_wosemicolon1304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_do_loop_in_statement_wosemicolon1309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_switch_case_in_statement_wosemicolon1314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_else_in_statement_wosemicolon1319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_try_catch_in_statement_wosemicolon1324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_synchronized_block_in_statement_wosemicolon1329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_throw__in_statement_wosemicolon1334 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_statement_wosemicolon1336 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_statement_wosemicolon1338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_left_unary_in_statement_wosemicolon1343 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400000L});
    public static final BitSet FOLLOW_variable_name_in_statement_wosemicolon1346 = new BitSet(new long[]{0x3000000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_right_unary_in_statement_wosemicolon1348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return__in_return_statement1361 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EEC8900L});
    public static final BitSet FOLLOW_value_in_return_statement1363 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_return_statement1366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_this__in_variable_assignment1379 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_variable_assignment1381 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400000L});
    public static final BitSet FOLLOW_super__in_variable_assignment1385 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_variable_assignment1387 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400000L});
    public static final BitSet FOLLOW_variable_name_in_variable_assignment1391 = new BitSet(new long[]{0x0F80000000000000L});
    public static final BitSet FOLLOW_assign_in_variable_assignment1393 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_variable_assignment1395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for__in_for_loop1405 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_for_loop1407 = new BitSet(new long[]{0x0001FF4F00000800L,0x00000000004C0000L});
    public static final BitSet FOLLOW_variable_def_in_for_loop1410 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040400L});
    public static final BitSet FOLLOW_comma_in_for_loop1413 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_variable_def_in_for_loop1415 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040400L});
    public static final BitSet FOLLOW_semicolon_in_for_loop1421 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EEC8900L});
    public static final BitSet FOLLOW_value_in_for_loop1423 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_for_loop1426 = new BitSet(new long[]{0xF001FF6F497EF800L,0x0000000000490100L});
    public static final BitSet FOLLOW_statement_wosemicolon_in_for_loop1429 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010400L});
    public static final BitSet FOLLOW_comma_in_for_loop1432 = new BitSet(new long[]{0xF001FF6F497EF800L,0x0000000000480100L});
    public static final BitSet FOLLOW_statement_wosemicolon_in_for_loop1434 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010400L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_for_loop1440 = new BitSet(new long[]{0xF003FF6F497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_for_loop1442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for__in_for_loop1447 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_for_loop1449 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_variable_type_in_for_loop1451 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400000L});
    public static final BitSet FOLLOW_variable_name_in_for_loop1453 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_colon_in_for_loop1455 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_for_loop1457 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_for_loop1459 = new BitSet(new long[]{0xF003FF6F497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_for_loop1461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while__in_while_loop1473 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_while_loop1475 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_while_loop1477 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_while_loop1479 = new BitSet(new long[]{0xF003FF6F497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_while_loop1481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_do__in_do_loop1492 = new BitSet(new long[]{0xF003FF6F497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_do_loop1494 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_while__in_do_loop1496 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_do_loop1498 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_do_loop1500 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_do_loop1502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_switch__in_switch_case1513 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_switch_case1515 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_switch_case1517 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_switch_case1519 = new BitSet(new long[]{0x0002000000000600L,0x0000000000000800L});
    public static final BitSet FOLLOW_block_begin_in_switch_case1521 = new BitSet(new long[]{0x0000000006000000L});
    public static final BitSet FOLLOW_case__in_switch_case1532 = new BitSet(new long[]{0x0003FF4F00070E00L,0x0000000C2EE80800L});
    public static final BitSet FOLLOW_constant_in_switch_case1536 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_variable_name_in_switch_case1540 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_colon_in_switch_case1543 = new BitSet(new long[]{0xF007FF6F4F7EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_switch_case1548 = new BitSet(new long[]{0xF007FF6F4F7EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_statement_in_switch_case1552 = new BitSet(new long[]{0xF007FF6F4F7EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_break__in_switch_case1557 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_switch_case1559 = new BitSet(new long[]{0xF007FF6F4F7EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_default__in_switch_case1570 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_colon_in_switch_case1572 = new BitSet(new long[]{0xF007FF6F4F7EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_switch_case1575 = new BitSet(new long[]{0xF007FF6F4F7EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_statement_in_switch_case1579 = new BitSet(new long[]{0xF007FF6F4F7EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_block_end_in_switch_case1590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if__in_if_else1600 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_if_else1602 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_if_else1604 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_if_else1606 = new BitSet(new long[]{0xF003FF6F497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_if_else1608 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_else__in_if_else1611 = new BitSet(new long[]{0xF003FF6F497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_if_else1613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_try__in_try_catch1626 = new BitSet(new long[]{0xF003FF6F497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_try_catch1628 = new BitSet(new long[]{0x0000000030000002L});
    public static final BitSet FOLLOW_catch__in_try_catch1631 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_try_catch1633 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_try_catch1635 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_try_catch1637 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_try_catch1639 = new BitSet(new long[]{0xF003FF6F497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_try_catch1641 = new BitSet(new long[]{0x0000000030000002L});
    public static final BitSet FOLLOW_finally__in_try_catch1646 = new BitSet(new long[]{0xF003FF6F497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_try_catch1648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_synchronized__in_synchronized_block1660 = new BitSet(new long[]{0xF003FF6F497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_synchronized_block1662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_label1672 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_colon_in_label1674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_cast1682 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_variable_type_in_cast1684 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_cast1686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_modifier_in_variable_type1699 = new BitSet(new long[]{0x0001FF4F00000000L});
    public static final BitSet FOLLOW_primitive_in_variable_type1702 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_array_in_variable_type1704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_modifier_in_variable_type1710 = new BitSet(new long[]{0x0000014F00000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_class_name_in_variable_type1713 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_array_in_variable_type1715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_modifier_in_variable_type1721 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_void__in_variable_type1724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_id1734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_plus_in_binary_operator1745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_minus_in_binary_operator1750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_star_in_binary_operator1755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_binary_operator1760 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENT_in_binary_operator1767 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUAL_in_binary_operator1774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNEQUAL_in_binary_operator1781 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_binary_operator1788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_binary_operator1795 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LE_in_binary_operator1802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GE_in_binary_operator1809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOGICAL_OR_in_binary_operator1816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BIT_OR_in_binary_operator1823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOGICAL_AND_in_binary_operator1830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BIT_AND_in_binary_operator1837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INC_in_left_unary1851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEC_in_left_unary1858 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_left_unary1865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_left_unary1872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_left_unary1879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INC_in_right_unary1893 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEC_in_right_unary1900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_right_unary1907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_primitive1920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BYTE_in_primitive1927 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_in_primitive1934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SHORT_in_primitive1941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_primitive1948 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LONG_in_primitive1955 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_primitive1962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLE_in_primitive1969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_CONST_in_int_const1987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LONG_CONST_in_long_const1997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HEX_CONST_in_hex_const2007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HEX_LONG_CONST_in_hex_long_const2017 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_CONST_in_string_const2027 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_CONST_in_float_const2037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_CONST_in_char_const2047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_null_const2057 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOL_CONST_in_boolean_const2068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_begin_in_array_const2078 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_array_const2080 = new BitSet(new long[]{0xF007FF6F497EFE00L,0x00000000004C0D00L});
    public static final BitSet FOLLOW_comma_in_array_const2083 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_array_const2085 = new BitSet(new long[]{0xF007FF6F497EFE00L,0x00000000004C0D00L});
    public static final BitSet FOLLOW_block_end_in_array_const2089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_name_in_class_const2098 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_primitive_in_class_const2102 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_void__in_class_const2106 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_class_const2109 = new BitSet(new long[]{0x0000014F00000040L,0x0000000000080000L});
    public static final BitSet FOLLOW_class__in_class_const2111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_package_name2127 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_package_name2129 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_package_name2133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_import_name2147 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_import_name2149 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400002L});
    public static final BitSet FOLLOW_id_in_import_name2154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_star_in_import_name2158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_class_name2170 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000A00L});
    public static final BitSet FOLLOW_dot_in_class_name2173 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_class_name2175 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000A00L});
    public static final BitSet FOLLOW_generic_in_class_name2179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_super__in_method_name2197 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_this__in_method_name2201 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_new_class_in_method_name2205 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_method_name2208 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400000L});
    public static final BitSet FOLLOW_variable_name_in_method_name2217 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_method_name2227 = new BitSet(new long[]{0x0000014F00060040L,0x0000000000480000L});
    public static final BitSet FOLLOW_class__in_method_name2230 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_variable_name_in_method_name2234 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_super__in_method_name2245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_this__in_method_name2250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_name_in_variable_name2261 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_array_in_variable_name2263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_in_annotation_name2274 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_ID_in_annotation_name2276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_name2285 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_name2288 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_name2290 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_open_rect_bracket_in_array2301 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE8C900L});
    public static final BitSet FOLLOW_value_in_array2303 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE8C900L});
    public static final BitSet FOLLOW_close_rect_bracket_in_array2306 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_PACKAGE_in_package_2321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_import_2332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASS_in_class_2342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENUM_in_enum_2351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTERFACE_in_interface_2361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_in_annotation_2371 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_INTERFACE_in_annotation_2373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXTENDS_in_extends_2381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPLEMENTS_in_implements_2391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_THIS_in_this_2400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUPER_in_super_2409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOID_in_void_2418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PUBLIC_in_public_2427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRIVATE_in_private_2435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROTECTED_in_protected_2445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STATIC_in_static_2454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FINAL_in_final_2463 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOLATILE_in_volatile_2474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SYNCHRONIZED_in_synchronized_2484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ABSTRACT_in_abstract_2494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSIENT_in_transient_2504 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEW_in_new_2513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRY_in_try_2522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CATCH_in_catch_2531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FINALLY_in_finally_2539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_THROWS_in_throws_2548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_THROW_in_throw_2557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FOR_in_for_2567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHILE_in_while_2576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DO_in_do_2585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_if_2594 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELSE_in_else_2603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SWITCH_in_switch_2613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CASE_in_case_2622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEFAULT_in_default_2630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETURN_in_return_2640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BREAK_in_break_2649 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_break_2651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BREAK_in_break_2658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTINUE_in_continue_2670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_semicolon2685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_comma2694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_colon2703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUESTION_in_question_2713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_plus2722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_minus2731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_star2740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_dot2749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_assign2758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCASSIGN_in_assign2765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECASSIGN_in_assign2772 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BITORASSIGN_in_assign2779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BITANDASSIGN_in_assign2786 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_CURLY_BRACKET_in_block_begin2798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLOSE_CURLY_BRACKET_in_block_end2808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_open_bracket2818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_close_bracket2828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_RECT_BRACKET_in_open_rect_bracket2839 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLOSE_RECT_BRACKET_in_close_rect_bracket2849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_def_in_synpred2_JavaParser46 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enum_def_in_synpred3_JavaParser50 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interface_def_in_synpred4_JavaParser54 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_def_in_synpred5_JavaParser58 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_modifier_in_synpred29_JavaParser319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_def_in_synpred58_JavaParser589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_static_init_in_synpred59_JavaParser593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constructor_def_in_synpred60_JavaParser597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_def_in_synpred61_JavaParser601 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_field_def_in_synpred62_JavaParser605 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_def_in_synpred64_JavaParser627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constructor_def_in_synpred65_JavaParser631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_def_in_synpred66_JavaParser635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_field_def_in_synpred67_JavaParser639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enum_content_in_synpred68_JavaParser643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_modifier_in_synpred74_JavaParser706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cast_in_synpred87_JavaParser853 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_right_unary_in_synpred90_JavaParser877 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_binary_operator_in_synpred91_JavaParser884 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_synpred91_JavaParser886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_question__in_synpred92_JavaParser894 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_synpred92_JavaParser896 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_colon_in_synpred92_JavaParser898 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_synpred92_JavaParser900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cast_in_synpred93_JavaParser853 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_left_unary_in_synpred93_JavaParser856 = new BitSet(new long[]{0x0003FF4F00071E00L,0x0000000C2EE88800L});
    public static final BitSet FOLLOW_single_value_in_synpred93_JavaParser863 = new BitSet(new long[]{0xF079FF4F00000802L,0x00000000004819FFL});
    public static final BitSet FOLLOW_open_bracket_in_synpred93_JavaParser867 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_synpred93_JavaParser869 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000490000L});
    public static final BitSet FOLLOW_close_bracket_in_synpred93_JavaParser871 = new BitSet(new long[]{0xF079FF4F00000802L,0x00000000004819FFL});
    public static final BitSet FOLLOW_right_unary_in_synpred93_JavaParser877 = new BitSet(new long[]{0xC079FF4F00000802L,0x00000000004818FFL});
    public static final BitSet FOLLOW_binary_operator_in_synpred93_JavaParser884 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_synpred93_JavaParser886 = new BitSet(new long[]{0x0001FF4F00000802L,0x0000000000480004L});
    public static final BitSet FOLLOW_question__in_synpred93_JavaParser894 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_synpred93_JavaParser896 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_colon_in_synpred93_JavaParser898 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_synpred93_JavaParser900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_right_unary_in_synpred96_JavaParser928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_binary_operator_in_synpred97_JavaParser935 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_synpred97_JavaParser937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_question__in_synpred98_JavaParser945 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_synpred98_JavaParser947 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_colon_in_synpred98_JavaParser949 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EE88900L});
    public static final BitSet FOLLOW_value_in_synpred98_JavaParser951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_synpred99_JavaParser964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_synpred100_JavaParser970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constant_in_synpred101_JavaParser975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_this__in_synpred104_JavaParser983 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_super__in_synpred104_JavaParser987 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_dot_in_synpred104_JavaParser990 = new BitSet(new long[]{0x0000000000060000L,0x0000000000400000L});
    public static final BitSet FOLLOW_variable_name_in_synpred104_JavaParser994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_new_class_in_synpred105_JavaParser999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_this__in_synpred106_JavaParser1004 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_semicolon_in_synpred123_JavaParser1139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_label_in_synpred124_JavaParser1152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_synpred125_JavaParser1158 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_synpred125_JavaParser1160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_synpred126_JavaParser1165 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_synpred126_JavaParser1167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_synpred127_JavaParser1172 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_synpred127_JavaParser1174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_right_unary_in_synpred141_JavaParser1251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_label_in_synpred142_JavaParser1263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_synpred143_JavaParser1268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_synpred144_JavaParser1273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_synpred145_JavaParser1278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for__in_synpred167_JavaParser1405 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_synpred167_JavaParser1407 = new BitSet(new long[]{0x0001FF4F00000800L,0x00000000004C0000L});
    public static final BitSet FOLLOW_variable_def_in_synpred167_JavaParser1410 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040400L});
    public static final BitSet FOLLOW_comma_in_synpred167_JavaParser1413 = new BitSet(new long[]{0x0001FF4F00000800L,0x0000000000480000L});
    public static final BitSet FOLLOW_variable_def_in_synpred167_JavaParser1415 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040400L});
    public static final BitSet FOLLOW_semicolon_in_synpred167_JavaParser1421 = new BitSet(new long[]{0xF003FF4F00071E00L,0x0000000C2EEC8900L});
    public static final BitSet FOLLOW_value_in_synpred167_JavaParser1423 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_semicolon_in_synpred167_JavaParser1426 = new BitSet(new long[]{0xF001FF6F497EF800L,0x0000000000490100L});
    public static final BitSet FOLLOW_statement_wosemicolon_in_synpred167_JavaParser1429 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010400L});
    public static final BitSet FOLLOW_comma_in_synpred167_JavaParser1432 = new BitSet(new long[]{0xF001FF6F497EF800L,0x0000000000480100L});
    public static final BitSet FOLLOW_statement_wosemicolon_in_synpred167_JavaParser1434 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010400L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_synpred167_JavaParser1440 = new BitSet(new long[]{0xF003FF6F497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_synpred167_JavaParser1442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_case__in_synpred169_JavaParser1532 = new BitSet(new long[]{0x0003FF4F00070E00L,0x0000000C2EE80800L});
    public static final BitSet FOLLOW_constant_in_synpred169_JavaParser1536 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_variable_name_in_synpred169_JavaParser1540 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_colon_in_synpred169_JavaParser1543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_code_in_synpred170_JavaParser1548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_synpred171_JavaParser1552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_code_in_synpred174_JavaParser1575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_synpred175_JavaParser1579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_else__in_synpred177_JavaParser1611 = new BitSet(new long[]{0xF003FF6F497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_synpred177_JavaParser1613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_catch__in_synpred178_JavaParser1631 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_synpred178_JavaParser1633 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_synpred178_JavaParser1635 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_synpred178_JavaParser1637 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_synpred178_JavaParser1639 = new BitSet(new long[]{0xF003FF6F497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_synpred178_JavaParser1641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_finally__in_synpred179_JavaParser1646 = new BitSet(new long[]{0xF003FF6F497EFE00L,0x00000000004C0900L});
    public static final BitSet FOLLOW_code_in_synpred179_JavaParser1648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dot_in_synpred220_JavaParser2173 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_synpred220_JavaParser2175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_generic_in_synpred221_JavaParser2179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dot_in_synpred230_JavaParser2288 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_synpred230_JavaParser2290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BREAK_in_synpred233_JavaParser2649 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_id_in_synpred233_JavaParser2651 = new BitSet(new long[]{0x0000000000000002L});

}