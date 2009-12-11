// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g 2009-12-11 20:09:32

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "PACKAGE", "IMPORT", "CLASS", "INTERFACE", "ENUM", "EXTENDS", "IMPLEMENTS", "VOID", "NEW", "RETURN", "BREAK", "CONTINUE", "NULL", "THIS", "SUPER", "FOR", "DO", "WHILE", "IF", "ELSE", "SWITCH", "CASE", "DEFAULT", "TRY", "CATCH", "FINALLY", "THROW", "THROWS", "PUBLIC", "PRIVATE", "PROTECTED", "FINAL", "STATIC", "TRANSIENT", "BOOLEAN", "BYTE", "CHAR", "SHORT", "INTEGER", "LONG", "FLOAT", "DOUBLE", "OPEN_CURLY_BRACKET", "CLOSE_CURLY_BRACKET", "LE", "GE", "EQUAL", "UNEQUAL", "ASSIGN", "INCASSIGN", "DECASSIGN", "INC", "DEC", "PLUS", "MINUS", "SLASH", "STAR", "QUESTION", "LOGICAL_OR", "BIT_OR", "LOGICAL_AND", "BIT_AND", "NOT", "DOT", "COMMA", "LT", "GT", "OPEN_RECT_BRACKET", "CLOSE_RECT_BRACKET", "OPEN_BRACKET", "CLOSE_BRACKET", "COLON", "SEMICOLON", "AT", "TILDE", "BOOL_CONST", "ID", "INT_CONST", "HEX_DIGIT", "HEX_CONST", "LONG_CONST", "HEX_LONG_CONST", "EXPONENT", "FLOAT_CONST", "LINEFEED", "COMMENT", "WS", "ESC_SEQ", "STRING_CONST", "CHAR_CONST", "UNICODE_ESC", "OCTAL_ESC"
    };
    public static final int PACKAGE=4;
    public static final int EXPONENT=86;
    public static final int LT=69;
    public static final int STAR=60;
    public static final int WHILE=21;
    public static final int OCTAL_ESC=95;
    public static final int CASE=25;
    public static final int CHAR=40;
    public static final int NEW=12;
    public static final int DO=20;
    public static final int NOT=66;
    public static final int EOF=-1;
    public static final int BREAK=14;
    public static final int LOGICAL_AND=64;
    public static final int HEX_LONG_CONST=85;
    public static final int OPEN_BRACKET=73;
    public static final int INC=55;
    public static final int FINAL=35;
    public static final int IMPORT=5;
    public static final int BIT_OR=63;
    public static final int HEX_CONST=83;
    public static final int STRING_CONST=92;
    public static final int THIS=17;
    public static final int RETURN=13;
    public static final int DOUBLE=45;
    public static final int VOID=11;
    public static final int SUPER=18;
    public static final int COMMENT=89;
    public static final int GE=49;
    public static final int PRIVATE=33;
    public static final int STATIC=36;
    public static final int SWITCH=24;
    public static final int NULL=16;
    public static final int ELSE=23;
    public static final int THROWS=31;
    public static final int SEMICOLON=76;
    public static final int TRY=27;
    public static final int WS=90;
    public static final int GT=70;
    public static final int CATCH=28;
    public static final int THROW=30;
    public static final int CLOSE_BRACKET=74;
    public static final int PROTECTED=34;
    public static final int DEC=56;
    public static final int CLASS=6;
    public static final int INCASSIGN=53;
    public static final int BIT_AND=65;
    public static final int FOR=19;
    public static final int FLOAT=44;
    public static final int LONG_CONST=84;
    public static final int ID=80;
    public static final int CLOSE_RECT_BRACKET=72;
    public static final int FLOAT_CONST=87;
    public static final int CHAR_CONST=93;
    public static final int IF=22;
    public static final int LINEFEED=88;
    public static final int AT=77;
    public static final int BOOLEAN=38;
    public static final int SLASH=59;
    public static final int ESC_SEQ=91;
    public static final int IMPLEMENTS=10;
    public static final int CONTINUE=15;
    public static final int COMMA=68;
    public static final int TRANSIENT=37;
    public static final int EQUAL=50;
    public static final int LOGICAL_OR=62;
    public static final int TILDE=78;
    public static final int PLUS=57;
    public static final int DOT=67;
    public static final int INTEGER=42;
    public static final int BYTE=39;
    public static final int OPEN_CURLY_BRACKET=46;
    public static final int UNICODE_ESC=94;
    public static final int DEFAULT=26;
    public static final int CLOSE_CURLY_BRACKET=47;
    public static final int INT_CONST=81;
    public static final int HEX_DIGIT=82;
    public static final int SHORT=41;
    public static final int DECASSIGN=54;
    public static final int MINUS=58;
    public static final int UNEQUAL=51;
    public static final int COLON=75;
    public static final int ENUM=8;
    public static final int QUESTION=61;
    public static final int FINALLY=29;
    public static final int ASSIGN=52;
    public static final int INTERFACE=7;
    public static final int OPEN_RECT_BRACKET=71;
    public static final int LONG=43;
    public static final int BOOL_CONST=79;
    public static final int EXTENDS=9;
    public static final int PUBLIC=32;
    public static final int LE=48;

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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:35:1: class_def : ( annotation )* ( modifier )* class_ id ( generic )? ( extends_ class_name ( generic )? )? ( implements_ class_name ( comma class_name )* )? class_block ;
    public final JavaParser.class_def_return class_def() throws RecognitionException {
        JavaParser.class_def_return retval = new JavaParser.class_def_return();
        retval.start = input.LT(1);

        JavaParser.id_return id1 = null;


        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:36:2: ( ( annotation )* ( modifier )* class_ id ( generic )? ( extends_ class_name ( generic )? )? ( implements_ class_name ( comma class_name )* )? class_block )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:36:4: ( annotation )* ( modifier )* class_ id ( generic )? ( extends_ class_name ( generic )? )? ( implements_ class_name ( comma class_name )* )? class_block
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

                if ( ((LA4_0>=PUBLIC && LA4_0<=TRANSIENT)) ) {
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

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:36:45: ( extends_ class_name ( generic )? )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==EXTENDS) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:36:46: extends_ class_name ( generic )?
                    {
                    pushFollow(FOLLOW_extends__in_class_def114);
                    extends_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_class_def116);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:36:66: ( generic )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==LT) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: generic
                            {
                            pushFollow(FOLLOW_generic_in_class_def118);
                            generic();

                            state._fsp--;
                            if (state.failed) return retval;

                            }
                            break;

                    }


                    }
                    break;

            }

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:36:77: ( implements_ class_name ( comma class_name )* )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==IMPLEMENTS) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:36:78: implements_ class_name ( comma class_name )*
                    {
                    pushFollow(FOLLOW_implements__in_class_def124);
                    implements_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_class_def126);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:36:101: ( comma class_name )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==COMMA) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:36:102: comma class_name
                    	    {
                    	    pushFollow(FOLLOW_comma_in_class_def129);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    pushFollow(FOLLOW_class_name_in_class_def131);
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

            pushFollow(FOLLOW_class_block_in_class_def137);
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:40:1: enum_def : ( annotation )* ( modifier )* enum_ id ( generic )? ( extends_ class_name ( generic )? )? enum_block ;
    public final JavaParser.enum_def_return enum_def() throws RecognitionException {
        JavaParser.enum_def_return retval = new JavaParser.enum_def_return();
        retval.start = input.LT(1);

        JavaParser.id_return id2 = null;


        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:41:2: ( ( annotation )* ( modifier )* enum_ id ( generic )? ( extends_ class_name ( generic )? )? enum_block )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:41:4: ( annotation )* ( modifier )* enum_ id ( generic )? ( extends_ class_name ( generic )? )? enum_block
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:41:4: ( annotation )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==AT) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_enum_def153);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:41:16: ( modifier )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>=PUBLIC && LA11_0<=TRANSIENT)) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: modifier
            	    {
            	    pushFollow(FOLLOW_modifier_in_enum_def156);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            pushFollow(FOLLOW_enum__in_enum_def159);
            enum_();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_id_in_enum_def161);
            id2=id();

            state._fsp--;
            if (state.failed) return retval;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:41:35: ( generic )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==LT) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: generic
                    {
                    pushFollow(FOLLOW_generic_in_enum_def163);
                    generic();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;

            }

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:41:44: ( extends_ class_name ( generic )? )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==EXTENDS) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:41:45: extends_ class_name ( generic )?
                    {
                    pushFollow(FOLLOW_extends__in_enum_def167);
                    extends_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_enum_def169);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:41:65: ( generic )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==LT) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: generic
                            {
                            pushFollow(FOLLOW_generic_in_enum_def171);
                            generic();

                            state._fsp--;
                            if (state.failed) return retval;

                            }
                            break;

                    }


                    }
                    break;

            }

            pushFollow(FOLLOW_enum_block_in_enum_def176);
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:45:1: interface_def : ( annotation )* ( modifier )* interface_ id ( generic )? ( extends_ class_name ( generic )? )? interface_block ;
    public final JavaParser.interface_def_return interface_def() throws RecognitionException {
        JavaParser.interface_def_return retval = new JavaParser.interface_def_return();
        retval.start = input.LT(1);

        JavaParser.id_return id3 = null;


        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:46:2: ( ( annotation )* ( modifier )* interface_ id ( generic )? ( extends_ class_name ( generic )? )? interface_block )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:46:4: ( annotation )* ( modifier )* interface_ id ( generic )? ( extends_ class_name ( generic )? )? interface_block
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:46:4: ( annotation )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==AT) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_interface_def192);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:46:16: ( modifier )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>=PUBLIC && LA16_0<=TRANSIENT)) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: modifier
            	    {
            	    pushFollow(FOLLOW_modifier_in_interface_def195);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            pushFollow(FOLLOW_interface__in_interface_def198);
            interface_();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_id_in_interface_def200);
            id3=id();

            state._fsp--;
            if (state.failed) return retval;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:46:40: ( generic )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==LT) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: generic
                    {
                    pushFollow(FOLLOW_generic_in_interface_def202);
                    generic();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;

            }

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:46:49: ( extends_ class_name ( generic )? )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==EXTENDS) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:46:50: extends_ class_name ( generic )?
                    {
                    pushFollow(FOLLOW_extends__in_interface_def206);
                    extends_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_interface_def208);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:46:70: ( generic )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==LT) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: generic
                            {
                            pushFollow(FOLLOW_generic_in_interface_def210);
                            generic();

                            state._fsp--;
                            if (state.failed) return retval;

                            }
                            break;

                    }


                    }
                    break;

            }

            pushFollow(FOLLOW_interface_block_in_interface_def215);
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
            pushFollow(FOLLOW_static__in_static_init231);
            static_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_code_in_static_init233);
            code();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:51:17: ( semicolon )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==SEMICOLON) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: semicolon
                    {
                    pushFollow(FOLLOW_semicolon_in_static_init235);
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
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==AT) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_constructor_def247);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:55:16: ( modifier )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0>=PUBLIC && LA22_0<=TRANSIENT)) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: modifier
            	    {
            	    pushFollow(FOLLOW_modifier_in_constructor_def250);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            pushFollow(FOLLOW_id_in_constructor_def253);
            id4=id();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_open_bracket_in_constructor_def255);
            open_bracket();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_argument_def_in_constructor_def257);
            argument_def();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_close_bracket_in_constructor_def259);
            close_bracket();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_code_in_constructor_def261);
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
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==AT) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_method_def276);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:60:16: ( modifier )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( ((LA24_0>=PUBLIC && LA24_0<=TRANSIENT)) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: modifier
            	    {
            	    pushFollow(FOLLOW_modifier_in_method_def279);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

            pushFollow(FOLLOW_variable_type_in_method_def282);
            variable_type();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_id_in_method_def284);
            id5=id();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_open_bracket_in_method_def286);
            open_bracket();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_argument_def_in_method_def288);
            argument_def();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_close_bracket_in_method_def290);
            close_bracket();

            state._fsp--;
            if (state.failed) return retval;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:60:83: ( throws_ class_name ( comma class_name )* )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==THROWS) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:60:84: throws_ class_name ( comma class_name )*
                    {
                    pushFollow(FOLLOW_throws__in_method_def293);
                    throws_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_method_def295);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:60:103: ( comma class_name )*
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0==COMMA) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:60:104: comma class_name
                    	    {
                    	    pushFollow(FOLLOW_comma_in_method_def298);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    pushFollow(FOLLOW_class_name_in_method_def300);
                    	    class_name();

                    	    state._fsp--;
                    	    if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop25;
                        }
                    } while (true);


                    }
                    break;

            }

            pushFollow(FOLLOW_code_in_method_def306);
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
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==AT) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_field_def322);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:65:16: ( modifier )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( ((LA28_0>=PUBLIC && LA28_0<=TRANSIENT)) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: modifier
            	    {
            	    pushFollow(FOLLOW_modifier_in_field_def325);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);

            pushFollow(FOLLOW_variable_type_in_field_def328);
            variable_type();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_variable_name_in_field_def330);
            variable_name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:65:54: ( assign value )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( ((LA29_0>=ASSIGN && LA29_0<=DECASSIGN)) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:65:55: assign value
                    {
                    pushFollow(FOLLOW_assign_in_field_def333);
                    assign();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_field_def335);
                    value();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_semicolon_in_field_def339);
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:68:1: argument_def : ( variable_type variable_name ( comma variable_type variable_name )* )? ;
    public final void argument_def() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:69:2: ( ( variable_type variable_name ( comma variable_type variable_name )* )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:69:4: ( variable_type variable_name ( comma variable_type variable_name )* )?
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:69:4: ( variable_type variable_name ( comma variable_type variable_name )* )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==VOID||(LA31_0>=BOOLEAN && LA31_0<=DOUBLE)||LA31_0==ID) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:69:5: variable_type variable_name ( comma variable_type variable_name )*
                    {
                    pushFollow(FOLLOW_variable_type_in_argument_def351);
                    variable_type();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_variable_name_in_argument_def353);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:69:33: ( comma variable_type variable_name )*
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==COMMA) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:69:34: comma variable_type variable_name
                    	    {
                    	    pushFollow(FOLLOW_comma_in_argument_def356);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_variable_type_in_argument_def358);
                    	    variable_type();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_variable_name_in_argument_def360);
                    	    variable_name();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop30;
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
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==AT) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_variable_def375);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);

            pushFollow(FOLLOW_variable_type_in_variable_def378);
            variable_type();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_variable_name_in_variable_def380);
            variable_name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:73:44: ( assign value )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( ((LA33_0>=ASSIGN && LA33_0<=DECASSIGN)) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:73:45: assign value
                    {
                    pushFollow(FOLLOW_assign_in_variable_def383);
                    assign();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_variable_def385);
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:76:1: modifier : ( public_ | private_ | protected_ | static_ | final_ | transient_ );
    public final void modifier() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:76:9: ( public_ | private_ | protected_ | static_ | final_ | transient_ )
            int alt34=6;
            switch ( input.LA(1) ) {
            case PUBLIC:
                {
                alt34=1;
                }
                break;
            case PRIVATE:
                {
                alt34=2;
                }
                break;
            case PROTECTED:
                {
                alt34=3;
                }
                break;
            case STATIC:
                {
                alt34=4;
                }
                break;
            case FINAL:
                {
                alt34=5;
                }
                break;
            case TRANSIENT:
                {
                alt34=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }

            switch (alt34) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:76:11: public_
                    {
                    pushFollow(FOLLOW_public__in_modifier397);
                    public_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:77:4: private_
                    {
                    pushFollow(FOLLOW_private__in_modifier402);
                    private_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:78:4: protected_
                    {
                    pushFollow(FOLLOW_protected__in_modifier407);
                    protected_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:79:4: static_
                    {
                    pushFollow(FOLLOW_static__in_modifier412);
                    static_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:80:4: final_
                    {
                    pushFollow(FOLLOW_final__in_modifier417);
                    final_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:81:4: transient_
                    {
                    pushFollow(FOLLOW_transient__in_modifier422);
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:85:1: class_block : block_begin ( class_def | static_init | constructor_def | method_def | field_def )* block_end ( semicolon )? ;
    public final void class_block() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:2: ( block_begin ( class_def | static_init | constructor_def | method_def | field_def )* block_end ( semicolon )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:4: block_begin ( class_def | static_init | constructor_def | method_def | field_def )* block_end ( semicolon )?
            {
            pushFollow(FOLLOW_block_begin_in_class_block434);
            block_begin();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:16: ( class_def | static_init | constructor_def | method_def | field_def )*
            loop35:
            do {
                int alt35=6;
                alt35 = dfa35.predict(input);
                switch (alt35) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:17: class_def
            	    {
            	    pushFollow(FOLLOW_class_def_in_class_block437);
            	    class_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 2 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:29: static_init
            	    {
            	    pushFollow(FOLLOW_static_init_in_class_block441);
            	    static_init();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 3 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:43: constructor_def
            	    {
            	    pushFollow(FOLLOW_constructor_def_in_class_block445);
            	    constructor_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 4 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:61: method_def
            	    {
            	    pushFollow(FOLLOW_method_def_in_class_block449);
            	    method_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 5 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:74: field_def
            	    {
            	    pushFollow(FOLLOW_field_def_in_class_block453);
            	    field_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);

            pushFollow(FOLLOW_block_end_in_class_block457);
            block_end();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:96: ( semicolon )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==SEMICOLON) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: semicolon
                    {
                    pushFollow(FOLLOW_semicolon_in_class_block459);
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:89:1: enum_block : block_begin ( class_def | constructor_def | method_def | field_def | enum_content )* block_end ( semicolon )? ;
    public final void enum_block() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:2: ( block_begin ( class_def | constructor_def | method_def | field_def | enum_content )* block_end ( semicolon )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:4: block_begin ( class_def | constructor_def | method_def | field_def | enum_content )* block_end ( semicolon )?
            {
            pushFollow(FOLLOW_block_begin_in_enum_block472);
            block_begin();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:16: ( class_def | constructor_def | method_def | field_def | enum_content )*
            loop37:
            do {
                int alt37=6;
                alt37 = dfa37.predict(input);
                switch (alt37) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:17: class_def
            	    {
            	    pushFollow(FOLLOW_class_def_in_enum_block475);
            	    class_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 2 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:29: constructor_def
            	    {
            	    pushFollow(FOLLOW_constructor_def_in_enum_block479);
            	    constructor_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 3 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:47: method_def
            	    {
            	    pushFollow(FOLLOW_method_def_in_enum_block483);
            	    method_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 4 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:60: field_def
            	    {
            	    pushFollow(FOLLOW_field_def_in_enum_block487);
            	    field_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 5 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:72: enum_content
            	    {
            	    pushFollow(FOLLOW_enum_content_in_enum_block491);
            	    enum_content();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop37;
                }
            } while (true);

            pushFollow(FOLLOW_block_end_in_enum_block495);
            block_end();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:97: ( semicolon )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==SEMICOLON) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: semicolon
                    {
                    pushFollow(FOLLOW_semicolon_in_enum_block497);
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:93:1: enum_content : id ( open_bracket arguments close_bracket )? ( comma id ( open_bracket arguments close_bracket )? )* semicolon ;
    public final void enum_content() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:94:2: ( id ( open_bracket arguments close_bracket )? ( comma id ( open_bracket arguments close_bracket )? )* semicolon )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:94:4: id ( open_bracket arguments close_bracket )? ( comma id ( open_bracket arguments close_bracket )? )* semicolon
            {
            pushFollow(FOLLOW_id_in_enum_content509);
            id();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:94:7: ( open_bracket arguments close_bracket )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==OPEN_BRACKET) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:94:8: open_bracket arguments close_bracket
                    {
                    pushFollow(FOLLOW_open_bracket_in_enum_content512);
                    open_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_arguments_in_enum_content514);
                    arguments();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_close_bracket_in_enum_content516);
                    close_bracket();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:94:47: ( comma id ( open_bracket arguments close_bracket )? )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==COMMA) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:94:48: comma id ( open_bracket arguments close_bracket )?
            	    {
            	    pushFollow(FOLLOW_comma_in_enum_content521);
            	    comma();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_enum_content523);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:94:57: ( open_bracket arguments close_bracket )?
            	    int alt40=2;
            	    int LA40_0 = input.LA(1);

            	    if ( (LA40_0==OPEN_BRACKET) ) {
            	        alt40=1;
            	    }
            	    switch (alt40) {
            	        case 1 :
            	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:94:58: open_bracket arguments close_bracket
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


            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);

            pushFollow(FOLLOW_semicolon_in_enum_content536);
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:97:1: interface_block : block_begin ( ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket semicolon )* block_end ( semicolon )? ;
    public final void interface_block() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:98:2: ( block_begin ( ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket semicolon )* block_end ( semicolon )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:98:4: block_begin ( ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket semicolon )* block_end ( semicolon )?
            {
            pushFollow(FOLLOW_block_begin_in_interface_block548);
            block_begin();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:98:16: ( ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket semicolon )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==VOID||(LA44_0>=PUBLIC && LA44_0<=DOUBLE)||LA44_0==AT||LA44_0==ID) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:98:17: ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket semicolon
            	    {
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:98:17: ( annotation )*
            	    loop42:
            	    do {
            	        int alt42=2;
            	        int LA42_0 = input.LA(1);

            	        if ( (LA42_0==AT) ) {
            	            alt42=1;
            	        }


            	        switch (alt42) {
            	    	case 1 :
            	    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    	    {
            	    	    pushFollow(FOLLOW_annotation_in_interface_block551);
            	    	    annotation();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop42;
            	        }
            	    } while (true);

            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:98:29: ( modifier )*
            	    loop43:
            	    do {
            	        int alt43=2;
            	        int LA43_0 = input.LA(1);

            	        if ( ((LA43_0>=PUBLIC && LA43_0<=TRANSIENT)) ) {
            	            alt43=1;
            	        }


            	        switch (alt43) {
            	    	case 1 :
            	    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: modifier
            	    	    {
            	    	    pushFollow(FOLLOW_modifier_in_interface_block554);
            	    	    modifier();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop43;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_variable_type_in_interface_block557);
            	    variable_type();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_interface_block559);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_open_bracket_in_interface_block561);
            	    open_bracket();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_argument_def_in_interface_block563);
            	    argument_def();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_close_bracket_in_interface_block565);
            	    close_bracket();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_semicolon_in_interface_block567);
            	    semicolon();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop44;
                }
            } while (true);

            pushFollow(FOLLOW_block_end_in_interface_block571);
            block_end();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:98:118: ( semicolon )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==SEMICOLON) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: semicolon
                    {
                    pushFollow(FOLLOW_semicolon_in_interface_block573);
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:101:1: method_call : ( method_name open_bracket arguments close_bracket ( dot id ( open_bracket arguments close_bracket )? )* | super_ open_bracket arguments close_bracket ( dot id ( open_bracket arguments close_bracket )? )* | this_ open_bracket arguments close_bracket ( dot id ( open_bracket arguments close_bracket )? )* );
    public final void method_call() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:102:2: ( method_name open_bracket arguments close_bracket ( dot id ( open_bracket arguments close_bracket )? )* | super_ open_bracket arguments close_bracket ( dot id ( open_bracket arguments close_bracket )? )* | this_ open_bracket arguments close_bracket ( dot id ( open_bracket arguments close_bracket )? )* )
            int alt52=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt52=1;
                }
                break;
            case SUPER:
                {
                alt52=2;
                }
                break;
            case THIS:
                {
                alt52=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                throw nvae;
            }

            switch (alt52) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:102:4: method_name open_bracket arguments close_bracket ( dot id ( open_bracket arguments close_bracket )? )*
                    {
                    pushFollow(FOLLOW_method_name_in_method_call586);
                    method_name();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_open_bracket_in_method_call588);
                    open_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_arguments_in_method_call590);
                    arguments();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_close_bracket_in_method_call592);
                    close_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:102:53: ( dot id ( open_bracket arguments close_bracket )? )*
                    loop47:
                    do {
                        int alt47=2;
                        int LA47_0 = input.LA(1);

                        if ( (LA47_0==DOT) ) {
                            alt47=1;
                        }


                        switch (alt47) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:102:54: dot id ( open_bracket arguments close_bracket )?
                    	    {
                    	    pushFollow(FOLLOW_dot_in_method_call595);
                    	    dot();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_id_in_method_call597);
                    	    id();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:102:61: ( open_bracket arguments close_bracket )?
                    	    int alt46=2;
                    	    int LA46_0 = input.LA(1);

                    	    if ( (LA46_0==OPEN_BRACKET) ) {
                    	        alt46=1;
                    	    }
                    	    switch (alt46) {
                    	        case 1 :
                    	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:102:62: open_bracket arguments close_bracket
                    	            {
                    	            pushFollow(FOLLOW_open_bracket_in_method_call600);
                    	            open_bracket();

                    	            state._fsp--;
                    	            if (state.failed) return ;
                    	            pushFollow(FOLLOW_arguments_in_method_call602);
                    	            arguments();

                    	            state._fsp--;
                    	            if (state.failed) return ;
                    	            pushFollow(FOLLOW_close_bracket_in_method_call604);
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
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:103:4: super_ open_bracket arguments close_bracket ( dot id ( open_bracket arguments close_bracket )? )*
                    {
                    pushFollow(FOLLOW_super__in_method_call613);
                    super_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_open_bracket_in_method_call615);
                    open_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_arguments_in_method_call617);
                    arguments();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_close_bracket_in_method_call619);
                    close_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:103:48: ( dot id ( open_bracket arguments close_bracket )? )*
                    loop49:
                    do {
                        int alt49=2;
                        int LA49_0 = input.LA(1);

                        if ( (LA49_0==DOT) ) {
                            alt49=1;
                        }


                        switch (alt49) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:103:49: dot id ( open_bracket arguments close_bracket )?
                    	    {
                    	    pushFollow(FOLLOW_dot_in_method_call622);
                    	    dot();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_id_in_method_call624);
                    	    id();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:103:56: ( open_bracket arguments close_bracket )?
                    	    int alt48=2;
                    	    int LA48_0 = input.LA(1);

                    	    if ( (LA48_0==OPEN_BRACKET) ) {
                    	        alt48=1;
                    	    }
                    	    switch (alt48) {
                    	        case 1 :
                    	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:103:57: open_bracket arguments close_bracket
                    	            {
                    	            pushFollow(FOLLOW_open_bracket_in_method_call627);
                    	            open_bracket();

                    	            state._fsp--;
                    	            if (state.failed) return ;
                    	            pushFollow(FOLLOW_arguments_in_method_call629);
                    	            arguments();

                    	            state._fsp--;
                    	            if (state.failed) return ;
                    	            pushFollow(FOLLOW_close_bracket_in_method_call631);
                    	            close_bracket();

                    	            state._fsp--;
                    	            if (state.failed) return ;

                    	            }
                    	            break;

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop49;
                        }
                    } while (true);


                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:104:4: this_ open_bracket arguments close_bracket ( dot id ( open_bracket arguments close_bracket )? )*
                    {
                    pushFollow(FOLLOW_this__in_method_call640);
                    this_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_open_bracket_in_method_call642);
                    open_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_arguments_in_method_call644);
                    arguments();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_close_bracket_in_method_call646);
                    close_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:104:47: ( dot id ( open_bracket arguments close_bracket )? )*
                    loop51:
                    do {
                        int alt51=2;
                        int LA51_0 = input.LA(1);

                        if ( (LA51_0==DOT) ) {
                            alt51=1;
                        }


                        switch (alt51) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:104:48: dot id ( open_bracket arguments close_bracket )?
                    	    {
                    	    pushFollow(FOLLOW_dot_in_method_call649);
                    	    dot();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_id_in_method_call651);
                    	    id();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:104:55: ( open_bracket arguments close_bracket )?
                    	    int alt50=2;
                    	    int LA50_0 = input.LA(1);

                    	    if ( (LA50_0==OPEN_BRACKET) ) {
                    	        alt50=1;
                    	    }
                    	    switch (alt50) {
                    	        case 1 :
                    	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:104:56: open_bracket arguments close_bracket
                    	            {
                    	            pushFollow(FOLLOW_open_bracket_in_method_call654);
                    	            open_bracket();

                    	            state._fsp--;
                    	            if (state.failed) return ;
                    	            pushFollow(FOLLOW_arguments_in_method_call656);
                    	            arguments();

                    	            state._fsp--;
                    	            if (state.failed) return ;
                    	            pushFollow(FOLLOW_close_bracket_in_method_call658);
                    	            close_bracket();

                    	            state._fsp--;
                    	            if (state.failed) return ;

                    	            }
                    	            break;

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop51;
                        }
                    } while (true);


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
    // $ANTLR end "method_call"


    // $ANTLR start "annotation"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:107:1: annotation : annotation_name ( open_bracket value ( comma value )* close_bracket )? ;
    public final void annotation() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:108:2: ( annotation_name ( open_bracket value ( comma value )* close_bracket )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:108:4: annotation_name ( open_bracket value ( comma value )* close_bracket )?
            {
            pushFollow(FOLLOW_annotation_name_in_annotation673);
            annotation_name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:108:20: ( open_bracket value ( comma value )* close_bracket )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==OPEN_BRACKET) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:108:21: open_bracket value ( comma value )* close_bracket
                    {
                    pushFollow(FOLLOW_open_bracket_in_annotation676);
                    open_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_annotation678);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:108:40: ( comma value )*
                    loop53:
                    do {
                        int alt53=2;
                        int LA53_0 = input.LA(1);

                        if ( (LA53_0==COMMA) ) {
                            alt53=1;
                        }


                        switch (alt53) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:108:41: comma value
                    	    {
                    	    pushFollow(FOLLOW_comma_in_annotation681);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_value_in_annotation683);
                    	    value();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop53;
                        }
                    } while (true);

                    pushFollow(FOLLOW_close_bracket_in_annotation687);
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:1: generic : LT ( id ( comma id )* ) GT ;
    public final void generic() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:9: ( LT ( id ( comma id )* ) GT )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:11: LT ( id ( comma id )* ) GT
            {
            match(input,LT,FOLLOW_LT_in_generic699); if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:14: ( id ( comma id )* )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:15: id ( comma id )*
            {
            pushFollow(FOLLOW_id_in_generic702);
            id();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:18: ( comma id )*
            loop55:
            do {
                int alt55=2;
                int LA55_0 = input.LA(1);

                if ( (LA55_0==COMMA) ) {
                    alt55=1;
                }


                switch (alt55) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:19: comma id
            	    {
            	    pushFollow(FOLLOW_comma_in_generic705);
            	    comma();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_generic707);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop55;
                }
            } while (true);


            }

            match(input,GT,FOLLOW_GT_in_generic712); if (state.failed) return ;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:1: value : ( ( cast )? ( left_unary )? single_value ( right_unary )? ( binary_operator value )? ( question_ value colon value )? | open_bracket value close_bracket ( binary_operator value )? );
    public final void value() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:7: ( ( cast )? ( left_unary )? single_value ( right_unary )? ( binary_operator value )? ( question_ value colon value )? | open_bracket value close_bracket ( binary_operator value )? )
            int alt62=2;
            alt62 = dfa62.predict(input);
            switch (alt62) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:9: ( cast )? ( left_unary )? single_value ( right_unary )? ( binary_operator value )? ( question_ value colon value )?
                    {
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:9: ( cast )?
                    int alt56=2;
                    int LA56_0 = input.LA(1);

                    if ( (LA56_0==OPEN_BRACKET) ) {
                        alt56=1;
                    }
                    switch (alt56) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: cast
                            {
                            pushFollow(FOLLOW_cast_in_value725);
                            cast();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:15: ( left_unary )?
                    int alt57=2;
                    switch ( input.LA(1) ) {
                        case INC:
                            {
                            int LA57_1 = input.LA(2);

                            if ( (synpred72_JavaParser()) ) {
                                alt57=1;
                            }
                            }
                            break;
                        case DEC:
                            {
                            int LA57_2 = input.LA(2);

                            if ( (synpred72_JavaParser()) ) {
                                alt57=1;
                            }
                            }
                            break;
                        case NOT:
                            {
                            int LA57_3 = input.LA(2);

                            if ( (synpred72_JavaParser()) ) {
                                alt57=1;
                            }
                            }
                            break;
                        case MINUS:
                            {
                            int LA57_4 = input.LA(2);

                            if ( (synpred72_JavaParser()) ) {
                                alt57=1;
                            }
                            }
                            break;
                        case PLUS:
                            {
                            int LA57_5 = input.LA(2);

                            if ( (synpred72_JavaParser()) ) {
                                alt57=1;
                            }
                            }
                            break;
                    }

                    switch (alt57) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: left_unary
                            {
                            pushFollow(FOLLOW_left_unary_in_value728);
                            left_unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_single_value_in_value731);
                    single_value();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:40: ( right_unary )?
                    int alt58=2;
                    int LA58_0 = input.LA(1);

                    if ( ((LA58_0>=INC && LA58_0<=DEC)||LA58_0==NOT) ) {
                        alt58=1;
                    }
                    switch (alt58) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: right_unary
                            {
                            pushFollow(FOLLOW_right_unary_in_value733);
                            right_unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:53: ( binary_operator value )?
                    int alt59=2;
                    int LA59_0 = input.LA(1);

                    if ( ((LA59_0>=LE && LA59_0<=UNEQUAL)||(LA59_0>=PLUS && LA59_0<=STAR)||(LA59_0>=LOGICAL_OR && LA59_0<=BIT_AND)||(LA59_0>=LT && LA59_0<=GT)) ) {
                        alt59=1;
                    }
                    switch (alt59) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:54: binary_operator value
                            {
                            pushFollow(FOLLOW_binary_operator_in_value737);
                            binary_operator();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_value_in_value739);
                            value();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:78: ( question_ value colon value )?
                    int alt60=2;
                    int LA60_0 = input.LA(1);

                    if ( (LA60_0==QUESTION) ) {
                        int LA60_1 = input.LA(2);

                        if ( (synpred75_JavaParser()) ) {
                            alt60=1;
                        }
                    }
                    switch (alt60) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:79: question_ value colon value
                            {
                            pushFollow(FOLLOW_question__in_value744);
                            question_();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_value_in_value746);
                            value();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_colon_in_value748);
                            colon();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_value_in_value750);
                            value();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:116:4: open_bracket value close_bracket ( binary_operator value )?
                    {
                    pushFollow(FOLLOW_open_bracket_in_value757);
                    open_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_value759);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_close_bracket_in_value761);
                    close_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:116:37: ( binary_operator value )?
                    int alt61=2;
                    int LA61_0 = input.LA(1);

                    if ( ((LA61_0>=LE && LA61_0<=UNEQUAL)||(LA61_0>=PLUS && LA61_0<=STAR)||(LA61_0>=LOGICAL_OR && LA61_0<=BIT_AND)||(LA61_0>=LT && LA61_0<=GT)) ) {
                        alt61=1;
                    }
                    switch (alt61) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:116:38: binary_operator value
                            {
                            pushFollow(FOLLOW_binary_operator_in_value764);
                            binary_operator();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_value_in_value766);
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:119:1: single_value : ( constant | class_name dot class_ | variable_name | method_call | new_class | this_ | super_ );
    public final void single_value() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:120:2: ( constant | class_name dot class_ | variable_name | method_call | new_class | this_ | super_ )
            int alt63=7;
            alt63 = dfa63.predict(input);
            switch (alt63) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:120:4: constant
                    {
                    pushFollow(FOLLOW_constant_in_single_value779);
                    constant();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:121:4: class_name dot class_
                    {
                    pushFollow(FOLLOW_class_name_in_single_value784);
                    class_name();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_dot_in_single_value786);
                    dot();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_class__in_single_value788);
                    class_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:122:4: variable_name
                    {
                    pushFollow(FOLLOW_variable_name_in_single_value793);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:123:4: method_call
                    {
                    pushFollow(FOLLOW_method_call_in_single_value798);
                    method_call();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:124:4: new_class
                    {
                    pushFollow(FOLLOW_new_class_in_single_value803);
                    new_class();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:125:4: this_
                    {
                    pushFollow(FOLLOW_this__in_single_value808);
                    this_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:126:4: super_
                    {
                    pushFollow(FOLLOW_super__in_single_value813);
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:129:1: constant : ( hex_long_const | hex_const | long_const | int_const | string_const | float_const | char_const | null_const | boolean_const | array_const );
    public final void constant() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:129:9: ( hex_long_const | hex_const | long_const | int_const | string_const | float_const | char_const | null_const | boolean_const | array_const )
            int alt64=10;
            switch ( input.LA(1) ) {
            case HEX_LONG_CONST:
                {
                alt64=1;
                }
                break;
            case HEX_CONST:
                {
                alt64=2;
                }
                break;
            case LONG_CONST:
                {
                alt64=3;
                }
                break;
            case INT_CONST:
                {
                alt64=4;
                }
                break;
            case STRING_CONST:
                {
                alt64=5;
                }
                break;
            case FLOAT_CONST:
                {
                alt64=6;
                }
                break;
            case CHAR_CONST:
                {
                alt64=7;
                }
                break;
            case NULL:
                {
                alt64=8;
                }
                break;
            case BOOL_CONST:
                {
                alt64=9;
                }
                break;
            case EOF:
            case OPEN_CURLY_BRACKET:
            case CLOSE_CURLY_BRACKET:
            case LE:
            case GE:
            case EQUAL:
            case UNEQUAL:
            case INC:
            case DEC:
            case PLUS:
            case MINUS:
            case SLASH:
            case STAR:
            case QUESTION:
            case LOGICAL_OR:
            case BIT_OR:
            case LOGICAL_AND:
            case BIT_AND:
            case NOT:
            case COMMA:
            case LT:
            case GT:
            case CLOSE_RECT_BRACKET:
            case OPEN_BRACKET:
            case CLOSE_BRACKET:
            case COLON:
            case SEMICOLON:
                {
                alt64=10;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 64, 0, input);

                throw nvae;
            }

            switch (alt64) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:129:11: hex_long_const
                    {
                    pushFollow(FOLLOW_hex_long_const_in_constant822);
                    hex_long_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:130:4: hex_const
                    {
                    pushFollow(FOLLOW_hex_const_in_constant827);
                    hex_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:131:4: long_const
                    {
                    pushFollow(FOLLOW_long_const_in_constant832);
                    long_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:132:4: int_const
                    {
                    pushFollow(FOLLOW_int_const_in_constant837);
                    int_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:133:4: string_const
                    {
                    pushFollow(FOLLOW_string_const_in_constant842);
                    string_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:134:4: float_const
                    {
                    pushFollow(FOLLOW_float_const_in_constant847);
                    float_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:135:4: char_const
                    {
                    pushFollow(FOLLOW_char_const_in_constant852);
                    char_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 8 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:136:4: null_const
                    {
                    pushFollow(FOLLOW_null_const_in_constant857);
                    null_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 9 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:137:4: boolean_const
                    {
                    pushFollow(FOLLOW_boolean_const_in_constant862);
                    boolean_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 10 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:138:4: array_const
                    {
                    pushFollow(FOLLOW_array_const_in_constant867);
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:141:1: new_class : new_ ( class_name ( generic )? | primitive ) ( array array_const )? ( open_bracket arguments close_bracket )? ;
    public final void new_class() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:2: ( new_ ( class_name ( generic )? | primitive ) ( array array_const )? ( open_bracket arguments close_bracket )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:4: new_ ( class_name ( generic )? | primitive ) ( array array_const )? ( open_bracket arguments close_bracket )?
            {
            pushFollow(FOLLOW_new__in_new_class879);
            new_();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:9: ( class_name ( generic )? | primitive )
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==ID) ) {
                alt66=1;
            }
            else if ( ((LA66_0>=BOOLEAN && LA66_0<=DOUBLE)) ) {
                alt66=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 66, 0, input);

                throw nvae;
            }
            switch (alt66) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:10: class_name ( generic )?
                    {
                    pushFollow(FOLLOW_class_name_in_new_class882);
                    class_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:21: ( generic )?
                    int alt65=2;
                    alt65 = dfa65.predict(input);
                    switch (alt65) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: generic
                            {
                            pushFollow(FOLLOW_generic_in_new_class884);
                            generic();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:32: primitive
                    {
                    pushFollow(FOLLOW_primitive_in_new_class889);
                    primitive();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:43: ( array array_const )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==OPEN_RECT_BRACKET) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:44: array array_const
                    {
                    pushFollow(FOLLOW_array_in_new_class893);
                    array();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_array_const_in_new_class895);
                    array_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:64: ( open_bracket arguments close_bracket )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==OPEN_BRACKET) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:65: open_bracket arguments close_bracket
                    {
                    pushFollow(FOLLOW_open_bracket_in_new_class900);
                    open_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_arguments_in_new_class902);
                    arguments();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_close_bracket_in_new_class904);
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
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==NEW||(LA70_0>=NULL && LA70_0<=SUPER)||LA70_0==OPEN_CURLY_BRACKET||(LA70_0>=LE && LA70_0<=UNEQUAL)||(LA70_0>=INC && LA70_0<=NOT)||(LA70_0>=COMMA && LA70_0<=GT)||LA70_0==OPEN_BRACKET||(LA70_0>=BOOL_CONST && LA70_0<=INT_CONST)||(LA70_0>=HEX_CONST && LA70_0<=HEX_LONG_CONST)||LA70_0==FLOAT_CONST||(LA70_0>=STRING_CONST && LA70_0<=CHAR_CONST)) ) {
                alt70=1;
            }
            else if ( (LA70_0==CLOSE_BRACKET) ) {
                int LA70_2 = input.LA(2);

                if ( (synpred98_JavaParser()) ) {
                    alt70=1;
                }
            }
            switch (alt70) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:146:5: value ( comma value )*
                    {
                    pushFollow(FOLLOW_value_in_arguments920);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:146:11: ( comma value )*
                    loop69:
                    do {
                        int alt69=2;
                        int LA69_0 = input.LA(1);

                        if ( (LA69_0==COMMA) ) {
                            alt69=1;
                        }


                        switch (alt69) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:146:12: comma value
                    	    {
                    	    pushFollow(FOLLOW_comma_in_arguments923);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_value_in_arguments925);
                    	    value();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop69;
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
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==VOID||(LA73_0>=RETURN && LA73_0<=CONTINUE)||(LA73_0>=THIS && LA73_0<=IF)||LA73_0==SWITCH||LA73_0==TRY||LA73_0==THROW||(LA73_0>=BOOLEAN && LA73_0<=DOUBLE)||(LA73_0>=INC && LA73_0<=MINUS)||LA73_0==NOT||(LA73_0>=SEMICOLON && LA73_0<=AT)||LA73_0==ID) ) {
                alt73=1;
            }
            else if ( (LA73_0==OPEN_CURLY_BRACKET) ) {
                alt73=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 73, 0, input);

                throw nvae;
            }
            switch (alt73) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:149:8: statement
                    {
                    pushFollow(FOLLOW_statement_in_code939);
                    statement();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:150:4: block_begin ( code )* block_end ( semicolon )?
                    {
                    pushFollow(FOLLOW_block_begin_in_code944);
                    block_begin();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:150:16: ( code )*
                    loop71:
                    do {
                        int alt71=2;
                        int LA71_0 = input.LA(1);

                        if ( (LA71_0==VOID||(LA71_0>=RETURN && LA71_0<=CONTINUE)||(LA71_0>=THIS && LA71_0<=IF)||LA71_0==SWITCH||LA71_0==TRY||LA71_0==THROW||(LA71_0>=BOOLEAN && LA71_0<=OPEN_CURLY_BRACKET)||(LA71_0>=INC && LA71_0<=MINUS)||LA71_0==NOT||(LA71_0>=SEMICOLON && LA71_0<=AT)||LA71_0==ID) ) {
                            alt71=1;
                        }


                        switch (alt71) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: code
                    	    {
                    	    pushFollow(FOLLOW_code_in_code946);
                    	    code();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop71;
                        }
                    } while (true);

                    pushFollow(FOLLOW_block_end_in_code949);
                    block_end();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:150:32: ( semicolon )?
                    int alt72=2;
                    int LA72_0 = input.LA(1);

                    if ( (LA72_0==SEMICOLON) ) {
                        int LA72_1 = input.LA(2);

                        if ( (synpred101_JavaParser()) ) {
                            alt72=1;
                        }
                    }
                    switch (alt72) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: semicolon
                            {
                            pushFollow(FOLLOW_semicolon_in_code951);
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:1: statement : ( label | variable_assignment semicolon | variable_def semicolon | method_call semicolon | semicolon | return_statement | continue_ | break_ | for_loop | while_loop | do_loop semicolon | switch_case | if_else | try_catch | throw_ value semicolon | ( left_unary )? variable_name ( right_unary )? );
    public final void statement() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:154:2: ( label | variable_assignment semicolon | variable_def semicolon | method_call semicolon | semicolon | return_statement | continue_ | break_ | for_loop | while_loop | do_loop semicolon | switch_case | if_else | try_catch | throw_ value semicolon | ( left_unary )? variable_name ( right_unary )? )
            int alt76=16;
            alt76 = dfa76.predict(input);
            switch (alt76) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:154:4: label
                    {
                    pushFollow(FOLLOW_label_in_statement964);
                    label();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:155:4: variable_assignment semicolon
                    {
                    pushFollow(FOLLOW_variable_assignment_in_statement970);
                    variable_assignment();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement972);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:156:4: variable_def semicolon
                    {
                    pushFollow(FOLLOW_variable_def_in_statement977);
                    variable_def();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement979);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:157:4: method_call semicolon
                    {
                    pushFollow(FOLLOW_method_call_in_statement984);
                    method_call();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement986);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:158:5: semicolon
                    {
                    pushFollow(FOLLOW_semicolon_in_statement992);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:159:4: return_statement
                    {
                    pushFollow(FOLLOW_return_statement_in_statement997);
                    return_statement();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:160:4: continue_
                    {
                    pushFollow(FOLLOW_continue__in_statement1002);
                    continue_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 8 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:161:4: break_
                    {
                    pushFollow(FOLLOW_break__in_statement1007);
                    break_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 9 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:162:4: for_loop
                    {
                    pushFollow(FOLLOW_for_loop_in_statement1012);
                    for_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 10 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:163:4: while_loop
                    {
                    pushFollow(FOLLOW_while_loop_in_statement1017);
                    while_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 11 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:164:4: do_loop semicolon
                    {
                    pushFollow(FOLLOW_do_loop_in_statement1022);
                    do_loop();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement1024);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 12 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:165:4: switch_case
                    {
                    pushFollow(FOLLOW_switch_case_in_statement1029);
                    switch_case();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 13 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:166:4: if_else
                    {
                    pushFollow(FOLLOW_if_else_in_statement1034);
                    if_else();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 14 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:167:4: try_catch
                    {
                    pushFollow(FOLLOW_try_catch_in_statement1039);
                    try_catch();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 15 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:168:4: throw_ value semicolon
                    {
                    pushFollow(FOLLOW_throw__in_statement1044);
                    throw_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_statement1046);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement1048);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 16 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:169:4: ( left_unary )? variable_name ( right_unary )?
                    {
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:169:4: ( left_unary )?
                    int alt74=2;
                    int LA74_0 = input.LA(1);

                    if ( ((LA74_0>=INC && LA74_0<=MINUS)||LA74_0==NOT) ) {
                        alt74=1;
                    }
                    switch (alt74) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: left_unary
                            {
                            pushFollow(FOLLOW_left_unary_in_statement1053);
                            left_unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_variable_name_in_statement1056);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:169:30: ( right_unary )?
                    int alt75=2;
                    switch ( input.LA(1) ) {
                        case INC:
                            {
                            int LA75_1 = input.LA(2);

                            if ( (synpred118_JavaParser()) ) {
                                alt75=1;
                            }
                            }
                            break;
                        case DEC:
                            {
                            int LA75_2 = input.LA(2);

                            if ( (synpred118_JavaParser()) ) {
                                alt75=1;
                            }
                            }
                            break;
                        case NOT:
                            {
                            int LA75_3 = input.LA(2);

                            if ( (synpred118_JavaParser()) ) {
                                alt75=1;
                            }
                            }
                            break;
                    }

                    switch (alt75) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: right_unary
                            {
                            pushFollow(FOLLOW_right_unary_in_statement1058);
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:172:1: statement_wosemicolon : ( label | variable_assignment | variable_def | method_call | return_statement | continue_ | break_ | for_loop | while_loop | do_loop | switch_case | if_else | try_catch | throw_ value semicolon | ( left_unary )? variable_name ( right_unary )? );
    public final void statement_wosemicolon() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:173:2: ( label | variable_assignment | variable_def | method_call | return_statement | continue_ | break_ | for_loop | while_loop | do_loop | switch_case | if_else | try_catch | throw_ value semicolon | ( left_unary )? variable_name ( right_unary )? )
            int alt79=15;
            alt79 = dfa79.predict(input);
            switch (alt79) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:173:4: label
                    {
                    pushFollow(FOLLOW_label_in_statement_wosemicolon1070);
                    label();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:174:4: variable_assignment
                    {
                    pushFollow(FOLLOW_variable_assignment_in_statement_wosemicolon1075);
                    variable_assignment();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:175:4: variable_def
                    {
                    pushFollow(FOLLOW_variable_def_in_statement_wosemicolon1080);
                    variable_def();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:176:4: method_call
                    {
                    pushFollow(FOLLOW_method_call_in_statement_wosemicolon1085);
                    method_call();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:177:4: return_statement
                    {
                    pushFollow(FOLLOW_return_statement_in_statement_wosemicolon1091);
                    return_statement();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:178:4: continue_
                    {
                    pushFollow(FOLLOW_continue__in_statement_wosemicolon1096);
                    continue_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:179:4: break_
                    {
                    pushFollow(FOLLOW_break__in_statement_wosemicolon1101);
                    break_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 8 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:180:4: for_loop
                    {
                    pushFollow(FOLLOW_for_loop_in_statement_wosemicolon1106);
                    for_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 9 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:181:4: while_loop
                    {
                    pushFollow(FOLLOW_while_loop_in_statement_wosemicolon1111);
                    while_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 10 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:182:4: do_loop
                    {
                    pushFollow(FOLLOW_do_loop_in_statement_wosemicolon1116);
                    do_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 11 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:183:4: switch_case
                    {
                    pushFollow(FOLLOW_switch_case_in_statement_wosemicolon1121);
                    switch_case();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 12 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:4: if_else
                    {
                    pushFollow(FOLLOW_if_else_in_statement_wosemicolon1126);
                    if_else();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 13 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:185:4: try_catch
                    {
                    pushFollow(FOLLOW_try_catch_in_statement_wosemicolon1131);
                    try_catch();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 14 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:186:4: throw_ value semicolon
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
                case 15 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:187:4: ( left_unary )? variable_name ( right_unary )?
                    {
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:187:4: ( left_unary )?
                    int alt77=2;
                    int LA77_0 = input.LA(1);

                    if ( ((LA77_0>=INC && LA77_0<=MINUS)||LA77_0==NOT) ) {
                        alt77=1;
                    }
                    switch (alt77) {
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
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:187:30: ( right_unary )?
                    int alt78=2;
                    int LA78_0 = input.LA(1);

                    if ( ((LA78_0>=INC && LA78_0<=DEC)||LA78_0==NOT) ) {
                        alt78=1;
                    }
                    switch (alt78) {
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:190:1: return_statement : return_ ( value )? semicolon ;
    public final void return_statement() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:191:2: ( return_ ( value )? semicolon )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:191:4: return_ ( value )? semicolon
            {
            pushFollow(FOLLOW_return__in_return_statement1163);
            return_();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:191:12: ( value )?
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==NEW||(LA80_0>=NULL && LA80_0<=SUPER)||LA80_0==OPEN_CURLY_BRACKET||(LA80_0>=LE && LA80_0<=UNEQUAL)||(LA80_0>=INC && LA80_0<=NOT)||(LA80_0>=LT && LA80_0<=GT)||LA80_0==OPEN_BRACKET||(LA80_0>=BOOL_CONST && LA80_0<=INT_CONST)||(LA80_0>=HEX_CONST && LA80_0<=HEX_LONG_CONST)||LA80_0==FLOAT_CONST||(LA80_0>=STRING_CONST && LA80_0<=CHAR_CONST)) ) {
                alt80=1;
            }
            else if ( (LA80_0==SEMICOLON) ) {
                int LA80_2 = input.LA(2);

                if ( (synpred135_JavaParser()) ) {
                    alt80=1;
                }
            }
            switch (alt80) {
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:194:1: variable_assignment : ( this_ dot | super_ dot )? variable_name assign value ;
    public final void variable_assignment() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:195:2: ( ( this_ dot | super_ dot )? variable_name assign value )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:195:4: ( this_ dot | super_ dot )? variable_name assign value
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:195:4: ( this_ dot | super_ dot )?
            int alt81=3;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==THIS) ) {
                alt81=1;
            }
            else if ( (LA81_0==SUPER) ) {
                alt81=2;
            }
            switch (alt81) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:195:5: this_ dot
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
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:195:17: super_ dot
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:1: for_loop : ( for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon ( value )? semicolon ( statement_wosemicolon ( comma statement_wosemicolon )* )? CLOSE_BRACKET code | for_ OPEN_BRACKET variable_type variable_name colon value CLOSE_BRACKET code );
    public final void for_loop() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:9: ( for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon ( value )? semicolon ( statement_wosemicolon ( comma statement_wosemicolon )* )? CLOSE_BRACKET code | for_ OPEN_BRACKET variable_type variable_name colon value CLOSE_BRACKET code )
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==FOR) ) {
                int LA87_1 = input.LA(2);

                if ( (synpred143_JavaParser()) ) {
                    alt87=1;
                }
                else if ( (true) ) {
                    alt87=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 87, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 87, 0, input);

                throw nvae;
            }
            switch (alt87) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:11: for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon ( value )? semicolon ( statement_wosemicolon ( comma statement_wosemicolon )* )? CLOSE_BRACKET code
                    {
                    pushFollow(FOLLOW_for__in_for_loop1207);
                    for_();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_for_loop1209); if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:29: ( variable_def ( comma variable_def )* )?
                    int alt83=2;
                    int LA83_0 = input.LA(1);

                    if ( (LA83_0==VOID||(LA83_0>=BOOLEAN && LA83_0<=DOUBLE)||LA83_0==AT||LA83_0==ID) ) {
                        alt83=1;
                    }
                    switch (alt83) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:30: variable_def ( comma variable_def )*
                            {
                            pushFollow(FOLLOW_variable_def_in_for_loop1212);
                            variable_def();

                            state._fsp--;
                            if (state.failed) return ;
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:43: ( comma variable_def )*
                            loop82:
                            do {
                                int alt82=2;
                                int LA82_0 = input.LA(1);

                                if ( (LA82_0==COMMA) ) {
                                    alt82=1;
                                }


                                switch (alt82) {
                            	case 1 :
                            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:44: comma variable_def
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
                            	    break loop82;
                                }
                            } while (true);


                            }
                            break;

                    }

                    pushFollow(FOLLOW_semicolon_in_for_loop1223);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:77: ( value )?
                    int alt84=2;
                    int LA84_0 = input.LA(1);

                    if ( (LA84_0==NEW||(LA84_0>=NULL && LA84_0<=SUPER)||LA84_0==OPEN_CURLY_BRACKET||(LA84_0>=LE && LA84_0<=UNEQUAL)||(LA84_0>=INC && LA84_0<=NOT)||(LA84_0>=LT && LA84_0<=GT)||LA84_0==OPEN_BRACKET||(LA84_0>=BOOL_CONST && LA84_0<=INT_CONST)||(LA84_0>=HEX_CONST && LA84_0<=HEX_LONG_CONST)||LA84_0==FLOAT_CONST||(LA84_0>=STRING_CONST && LA84_0<=CHAR_CONST)) ) {
                        alt84=1;
                    }
                    else if ( (LA84_0==SEMICOLON) ) {
                        int LA84_2 = input.LA(2);

                        if ( (synpred140_JavaParser()) ) {
                            alt84=1;
                        }
                    }
                    switch (alt84) {
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
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:94: ( statement_wosemicolon ( comma statement_wosemicolon )* )?
                    int alt86=2;
                    int LA86_0 = input.LA(1);

                    if ( (LA86_0==VOID||(LA86_0>=RETURN && LA86_0<=CONTINUE)||(LA86_0>=THIS && LA86_0<=IF)||LA86_0==SWITCH||LA86_0==TRY||LA86_0==THROW||(LA86_0>=BOOLEAN && LA86_0<=DOUBLE)||(LA86_0>=INC && LA86_0<=MINUS)||LA86_0==NOT||LA86_0==AT||LA86_0==ID) ) {
                        alt86=1;
                    }
                    switch (alt86) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:95: statement_wosemicolon ( comma statement_wosemicolon )*
                            {
                            pushFollow(FOLLOW_statement_wosemicolon_in_for_loop1231);
                            statement_wosemicolon();

                            state._fsp--;
                            if (state.failed) return ;
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:117: ( comma statement_wosemicolon )*
                            loop85:
                            do {
                                int alt85=2;
                                int LA85_0 = input.LA(1);

                                if ( (LA85_0==COMMA) ) {
                                    alt85=1;
                                }


                                switch (alt85) {
                            	case 1 :
                            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:118: comma statement_wosemicolon
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
                            	    break loop85;
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
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:199:4: for_ OPEN_BRACKET variable_type variable_name colon value CLOSE_BRACKET code
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:202:1: while_loop : while_ OPEN_BRACKET value CLOSE_BRACKET code ;
    public final void while_loop() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:203:2: ( while_ OPEN_BRACKET value CLOSE_BRACKET code )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:203:4: while_ OPEN_BRACKET value CLOSE_BRACKET code
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:206:1: do_loop : do_ code while_ OPEN_BRACKET value CLOSE_BRACKET ;
    public final void do_loop() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:206:9: ( do_ code while_ OPEN_BRACKET value CLOSE_BRACKET )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:206:11: do_ code while_ OPEN_BRACKET value CLOSE_BRACKET
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:209:1: switch_case : switch_ OPEN_BRACKET value CLOSE_BRACKET block_begin ( ( case_ ( constant | variable_name ) colon )+ ( code | statement )* ( break_ semicolon )? | default_ colon ( code | statement )* )+ block_end ;
    public final void switch_case() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:210:2: ( switch_ OPEN_BRACKET value CLOSE_BRACKET block_begin ( ( case_ ( constant | variable_name ) colon )+ ( code | statement )* ( break_ semicolon )? | default_ colon ( code | statement )* )+ block_end )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:210:4: switch_ OPEN_BRACKET value CLOSE_BRACKET block_begin ( ( case_ ( constant | variable_name ) colon )+ ( code | statement )* ( break_ semicolon )? | default_ colon ( code | statement )* )+ block_end
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:211:3: ( ( case_ ( constant | variable_name ) colon )+ ( code | statement )* ( break_ semicolon )? | default_ colon ( code | statement )* )+
            int cnt93=0;
            loop93:
            do {
                int alt93=3;
                int LA93_0 = input.LA(1);

                if ( (LA93_0==CASE) ) {
                    alt93=1;
                }
                else if ( (LA93_0==DEFAULT) ) {
                    alt93=2;
                }


                switch (alt93) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:212:4: ( case_ ( constant | variable_name ) colon )+ ( code | statement )* ( break_ semicolon )?
            	    {
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:212:4: ( case_ ( constant | variable_name ) colon )+
            	    int cnt89=0;
            	    loop89:
            	    do {
            	        int alt89=2;
            	        int LA89_0 = input.LA(1);

            	        if ( (LA89_0==CASE) ) {
            	            int LA89_2 = input.LA(2);

            	            if ( (synpred145_JavaParser()) ) {
            	                alt89=1;
            	            }


            	        }


            	        switch (alt89) {
            	    	case 1 :
            	    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:212:5: case_ ( constant | variable_name ) colon
            	    	    {
            	    	    pushFollow(FOLLOW_case__in_switch_case1334);
            	    	    case_();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;
            	    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:212:12: ( constant | variable_name )
            	    	    int alt88=2;
            	    	    int LA88_0 = input.LA(1);

            	    	    if ( (LA88_0==EOF||LA88_0==NULL||(LA88_0>=OPEN_CURLY_BRACKET && LA88_0<=UNEQUAL)||(LA88_0>=INC && LA88_0<=NOT)||(LA88_0>=COMMA && LA88_0<=GT)||(LA88_0>=CLOSE_RECT_BRACKET && LA88_0<=SEMICOLON)||LA88_0==BOOL_CONST||LA88_0==INT_CONST||(LA88_0>=HEX_CONST && LA88_0<=HEX_LONG_CONST)||LA88_0==FLOAT_CONST||(LA88_0>=STRING_CONST && LA88_0<=CHAR_CONST)) ) {
            	    	        alt88=1;
            	    	    }
            	    	    else if ( (LA88_0==ID) ) {
            	    	        alt88=2;
            	    	    }
            	    	    else {
            	    	        if (state.backtracking>0) {state.failed=true; return ;}
            	    	        NoViableAltException nvae =
            	    	            new NoViableAltException("", 88, 0, input);

            	    	        throw nvae;
            	    	    }
            	    	    switch (alt88) {
            	    	        case 1 :
            	    	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:212:13: constant
            	    	            {
            	    	            pushFollow(FOLLOW_constant_in_switch_case1338);
            	    	            constant();

            	    	            state._fsp--;
            	    	            if (state.failed) return ;

            	    	            }
            	    	            break;
            	    	        case 2 :
            	    	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:212:24: variable_name
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
            	    	    if ( cnt89 >= 1 ) break loop89;
            	    	    if (state.backtracking>0) {state.failed=true; return ;}
            	                EarlyExitException eee =
            	                    new EarlyExitException(89, input);
            	                throw eee;
            	        }
            	        cnt89++;
            	    } while (true);

            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:212:47: ( code | statement )*
            	    loop90:
            	    do {
            	        int alt90=3;
            	        alt90 = dfa90.predict(input);
            	        switch (alt90) {
            	    	case 1 :
            	    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:212:48: code
            	    	    {
            	    	    pushFollow(FOLLOW_code_in_switch_case1350);
            	    	    code();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;

            	    	    }
            	    	    break;
            	    	case 2 :
            	    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:212:55: statement
            	    	    {
            	    	    pushFollow(FOLLOW_statement_in_switch_case1354);
            	    	    statement();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop90;
            	        }
            	    } while (true);

            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:212:67: ( break_ semicolon )?
            	    int alt91=2;
            	    int LA91_0 = input.LA(1);

            	    if ( (LA91_0==BREAK) ) {
            	        alt91=1;
            	    }
            	    switch (alt91) {
            	        case 1 :
            	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:212:68: break_ semicolon
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
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:4: default_ colon ( code | statement )*
            	    {
            	    pushFollow(FOLLOW_default__in_switch_case1372);
            	    default_();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_colon_in_switch_case1374);
            	    colon();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:19: ( code | statement )*
            	    loop92:
            	    do {
            	        int alt92=3;
            	        alt92 = dfa92.predict(input);
            	        switch (alt92) {
            	    	case 1 :
            	    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:20: code
            	    	    {
            	    	    pushFollow(FOLLOW_code_in_switch_case1377);
            	    	    code();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;

            	    	    }
            	    	    break;
            	    	case 2 :
            	    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:27: statement
            	    	    {
            	    	    pushFollow(FOLLOW_statement_in_switch_case1381);
            	    	    statement();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop92;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    if ( cnt93 >= 1 ) break loop93;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(93, input);
                        throw eee;
                }
                cnt93++;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:219:1: if_else : if_ OPEN_BRACKET value CLOSE_BRACKET code ( else_ code )? ;
    public final void if_else() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:219:9: ( if_ OPEN_BRACKET value CLOSE_BRACKET code ( else_ code )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:219:11: if_ OPEN_BRACKET value CLOSE_BRACKET code ( else_ code )?
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
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:219:53: ( else_ code )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==ELSE) ) {
                int LA94_1 = input.LA(2);

                if ( (synpred153_JavaParser()) ) {
                    alt94=1;
                }
            }
            switch (alt94) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:219:54: else_ code
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:222:1: try_catch : try_ code ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )* ( finally_ code )? ;
    public final void try_catch() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:223:2: ( try_ code ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )* ( finally_ code )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:223:4: try_ code ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )* ( finally_ code )?
            {
            pushFollow(FOLLOW_try__in_try_catch1429);
            try_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_code_in_try_catch1431);
            code();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:223:14: ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )*
            loop95:
            do {
                int alt95=2;
                int LA95_0 = input.LA(1);

                if ( (LA95_0==CATCH) ) {
                    int LA95_2 = input.LA(2);

                    if ( (synpred154_JavaParser()) ) {
                        alt95=1;
                    }


                }


                switch (alt95) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:223:15: catch_ OPEN_BRACKET id id CLOSE_BRACKET code
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
            	    break loop95;
                }
            } while (true);

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:223:62: ( finally_ code )?
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==FINALLY) ) {
                int LA96_1 = input.LA(2);

                if ( (synpred155_JavaParser()) ) {
                    alt96=1;
                }
            }
            switch (alt96) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:223:63: finally_ code
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


    // $ANTLR start "label"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:226:1: label : id colon ;
    public final void label() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:226:7: ( id colon )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:226:9: id colon
            {
            pushFollow(FOLLOW_id_in_label1463);
            id();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_colon_in_label1465);
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:228:1: cast : OPEN_BRACKET variable_type CLOSE_BRACKET ;
    public final void cast() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:228:6: ( OPEN_BRACKET variable_type CLOSE_BRACKET )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:228:8: OPEN_BRACKET variable_type CLOSE_BRACKET
            {
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_cast1473); if (state.failed) return ;
            pushFollow(FOLLOW_variable_type_in_cast1475);
            variable_type();

            state._fsp--;
            if (state.failed) return ;
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_cast1477); if (state.failed) return ;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:231:1: variable_type : ( primitive ( array )? | class_name ( generic )? ( array )? | void_ );
    public final void variable_type() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:232:2: ( primitive ( array )? | class_name ( generic )? ( array )? | void_ )
            int alt100=3;
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
                alt100=1;
                }
                break;
            case ID:
                {
                alt100=2;
                }
                break;
            case VOID:
                {
                alt100=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 100, 0, input);

                throw nvae;
            }

            switch (alt100) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:232:4: primitive ( array )?
                    {
                    pushFollow(FOLLOW_primitive_in_variable_type1490);
                    primitive();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:232:14: ( array )?
                    int alt97=2;
                    int LA97_0 = input.LA(1);

                    if ( (LA97_0==OPEN_RECT_BRACKET) ) {
                        alt97=1;
                    }
                    switch (alt97) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: array
                            {
                            pushFollow(FOLLOW_array_in_variable_type1492);
                            array();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:233:4: class_name ( generic )? ( array )?
                    {
                    pushFollow(FOLLOW_class_name_in_variable_type1498);
                    class_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:233:15: ( generic )?
                    int alt98=2;
                    int LA98_0 = input.LA(1);

                    if ( (LA98_0==LT) ) {
                        alt98=1;
                    }
                    switch (alt98) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: generic
                            {
                            pushFollow(FOLLOW_generic_in_variable_type1500);
                            generic();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:233:24: ( array )?
                    int alt99=2;
                    int LA99_0 = input.LA(1);

                    if ( (LA99_0==OPEN_RECT_BRACKET) ) {
                        alt99=1;
                    }
                    switch (alt99) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: array
                            {
                            pushFollow(FOLLOW_array_in_variable_type1503);
                            array();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:234:4: void_
                    {
                    pushFollow(FOLLOW_void__in_variable_type1509);
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:237:1: id : ID ;
    public final JavaParser.id_return id() throws RecognitionException {
        JavaParser.id_return retval = new JavaParser.id_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:237:4: ( ID )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:237:6: ID
            {
            match(input,ID,FOLLOW_ID_in_id1519); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:239:1: binary_operator : ( plus | minus | STAR | SLASH | EQUAL | UNEQUAL | LT | GT | LE | GE | LOGICAL_OR | BIT_OR | LOGICAL_AND | BIT_AND );
    public final JavaParser.binary_operator_return binary_operator() throws RecognitionException {
        JavaParser.binary_operator_return retval = new JavaParser.binary_operator_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:240:2: ( plus | minus | STAR | SLASH | EQUAL | UNEQUAL | LT | GT | LE | GE | LOGICAL_OR | BIT_OR | LOGICAL_AND | BIT_AND )
            int alt101=14;
            switch ( input.LA(1) ) {
            case PLUS:
                {
                alt101=1;
                }
                break;
            case MINUS:
                {
                alt101=2;
                }
                break;
            case STAR:
                {
                alt101=3;
                }
                break;
            case SLASH:
                {
                alt101=4;
                }
                break;
            case EQUAL:
                {
                alt101=5;
                }
                break;
            case UNEQUAL:
                {
                alt101=6;
                }
                break;
            case LT:
                {
                alt101=7;
                }
                break;
            case GT:
                {
                alt101=8;
                }
                break;
            case LE:
                {
                alt101=9;
                }
                break;
            case GE:
                {
                alt101=10;
                }
                break;
            case LOGICAL_OR:
                {
                alt101=11;
                }
                break;
            case BIT_OR:
                {
                alt101=12;
                }
                break;
            case LOGICAL_AND:
                {
                alt101=13;
                }
                break;
            case BIT_AND:
                {
                alt101=14;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 101, 0, input);

                throw nvae;
            }

            switch (alt101) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:240:4: plus
                    {
                    pushFollow(FOLLOW_plus_in_binary_operator1530);
                    plus();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:241:4: minus
                    {
                    pushFollow(FOLLOW_minus_in_binary_operator1535);
                    minus();

                    state._fsp--;
                    if (state.failed) return retval;

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:242:4: STAR
                    {
                    match(input,STAR,FOLLOW_STAR_in_binary_operator1540); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:243:4: SLASH
                    {
                    match(input,SLASH,FOLLOW_SLASH_in_binary_operator1547); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 5 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:244:4: EQUAL
                    {
                    match(input,EQUAL,FOLLOW_EQUAL_in_binary_operator1554); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 6 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:245:4: UNEQUAL
                    {
                    match(input,UNEQUAL,FOLLOW_UNEQUAL_in_binary_operator1561); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 7 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:246:4: LT
                    {
                    match(input,LT,FOLLOW_LT_in_binary_operator1568); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 8 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:247:4: GT
                    {
                    match(input,GT,FOLLOW_GT_in_binary_operator1575); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 9 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:248:4: LE
                    {
                    match(input,LE,FOLLOW_LE_in_binary_operator1582); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 10 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:249:4: GE
                    {
                    match(input,GE,FOLLOW_GE_in_binary_operator1589); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 11 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:250:4: LOGICAL_OR
                    {
                    match(input,LOGICAL_OR,FOLLOW_LOGICAL_OR_in_binary_operator1596); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 12 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:251:4: BIT_OR
                    {
                    match(input,BIT_OR,FOLLOW_BIT_OR_in_binary_operator1603); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 13 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:252:4: LOGICAL_AND
                    {
                    match(input,LOGICAL_AND,FOLLOW_LOGICAL_AND_in_binary_operator1610); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 14 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:253:4: BIT_AND
                    {
                    match(input,BIT_AND,FOLLOW_BIT_AND_in_binary_operator1617); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:256:1: left_unary : ( INC | DEC | NOT | MINUS | PLUS );
    public final JavaParser.left_unary_return left_unary() throws RecognitionException {
        JavaParser.left_unary_return retval = new JavaParser.left_unary_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:257:2: ( INC | DEC | NOT | MINUS | PLUS )
            int alt102=5;
            switch ( input.LA(1) ) {
            case INC:
                {
                alt102=1;
                }
                break;
            case DEC:
                {
                alt102=2;
                }
                break;
            case NOT:
                {
                alt102=3;
                }
                break;
            case MINUS:
                {
                alt102=4;
                }
                break;
            case PLUS:
                {
                alt102=5;
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
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:257:4: INC
                    {
                    match(input,INC,FOLLOW_INC_in_left_unary1631); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:258:4: DEC
                    {
                    match(input,DEC,FOLLOW_DEC_in_left_unary1638); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:259:4: NOT
                    {
                    match(input,NOT,FOLLOW_NOT_in_left_unary1645); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:260:4: MINUS
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_left_unary1652); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 5 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:261:4: PLUS
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_left_unary1659); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:264:1: right_unary : ( INC | DEC | NOT );
    public final JavaParser.right_unary_return right_unary() throws RecognitionException {
        JavaParser.right_unary_return retval = new JavaParser.right_unary_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:265:2: ( INC | DEC | NOT )
            int alt103=3;
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
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 103, 0, input);

                throw nvae;
            }

            switch (alt103) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:265:4: INC
                    {
                    match(input,INC,FOLLOW_INC_in_right_unary1673); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:266:4: DEC
                    {
                    match(input,DEC,FOLLOW_DEC_in_right_unary1680); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:267:4: NOT
                    {
                    match(input,NOT,FOLLOW_NOT_in_right_unary1687); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:270:1: primitive : ( BOOLEAN | BYTE | CHAR | SHORT | INTEGER | LONG | FLOAT | DOUBLE );
    public final JavaParser.primitive_return primitive() throws RecognitionException {
        JavaParser.primitive_return retval = new JavaParser.primitive_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:271:2: ( BOOLEAN | BYTE | CHAR | SHORT | INTEGER | LONG | FLOAT | DOUBLE )
            int alt104=8;
            switch ( input.LA(1) ) {
            case BOOLEAN:
                {
                alt104=1;
                }
                break;
            case BYTE:
                {
                alt104=2;
                }
                break;
            case CHAR:
                {
                alt104=3;
                }
                break;
            case SHORT:
                {
                alt104=4;
                }
                break;
            case INTEGER:
                {
                alt104=5;
                }
                break;
            case LONG:
                {
                alt104=6;
                }
                break;
            case FLOAT:
                {
                alt104=7;
                }
                break;
            case DOUBLE:
                {
                alt104=8;
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
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:271:4: BOOLEAN
                    {
                    match(input,BOOLEAN,FOLLOW_BOOLEAN_in_primitive1700); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:272:4: BYTE
                    {
                    match(input,BYTE,FOLLOW_BYTE_in_primitive1707); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:273:4: CHAR
                    {
                    match(input,CHAR,FOLLOW_CHAR_in_primitive1714); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:274:4: SHORT
                    {
                    match(input,SHORT,FOLLOW_SHORT_in_primitive1721); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 5 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:275:4: INTEGER
                    {
                    match(input,INTEGER,FOLLOW_INTEGER_in_primitive1728); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 6 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:276:4: LONG
                    {
                    match(input,LONG,FOLLOW_LONG_in_primitive1735); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 7 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:277:4: FLOAT
                    {
                    match(input,FLOAT,FOLLOW_FLOAT_in_primitive1742); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 8 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:278:4: DOUBLE
                    {
                    match(input,DOUBLE,FOLLOW_DOUBLE_in_primitive1749); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:286:1: int_const : INT_CONST ;
    public final JavaParser.int_const_return int_const() throws RecognitionException {
        JavaParser.int_const_return retval = new JavaParser.int_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:287:2: ( INT_CONST )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:287:4: INT_CONST
            {
            match(input,INT_CONST,FOLLOW_INT_CONST_in_int_const1767); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:288:1: long_const : LONG_CONST ;
    public final JavaParser.long_const_return long_const() throws RecognitionException {
        JavaParser.long_const_return retval = new JavaParser.long_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:289:2: ( LONG_CONST )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:289:4: LONG_CONST
            {
            match(input,LONG_CONST,FOLLOW_LONG_CONST_in_long_const1777); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:290:1: hex_const : HEX_CONST ;
    public final JavaParser.hex_const_return hex_const() throws RecognitionException {
        JavaParser.hex_const_return retval = new JavaParser.hex_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:291:2: ( HEX_CONST )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:291:4: HEX_CONST
            {
            match(input,HEX_CONST,FOLLOW_HEX_CONST_in_hex_const1787); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:292:1: hex_long_const : HEX_LONG_CONST ;
    public final JavaParser.hex_long_const_return hex_long_const() throws RecognitionException {
        JavaParser.hex_long_const_return retval = new JavaParser.hex_long_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:293:2: ( HEX_LONG_CONST )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:293:4: HEX_LONG_CONST
            {
            match(input,HEX_LONG_CONST,FOLLOW_HEX_LONG_CONST_in_hex_long_const1797); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:294:1: string_const : STRING_CONST ;
    public final JavaParser.string_const_return string_const() throws RecognitionException {
        JavaParser.string_const_return retval = new JavaParser.string_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:295:2: ( STRING_CONST )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:295:4: STRING_CONST
            {
            match(input,STRING_CONST,FOLLOW_STRING_CONST_in_string_const1807); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:296:1: float_const : FLOAT_CONST ;
    public final JavaParser.float_const_return float_const() throws RecognitionException {
        JavaParser.float_const_return retval = new JavaParser.float_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:297:2: ( FLOAT_CONST )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:297:4: FLOAT_CONST
            {
            match(input,FLOAT_CONST,FOLLOW_FLOAT_CONST_in_float_const1817); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:298:1: char_const : CHAR_CONST ;
    public final JavaParser.char_const_return char_const() throws RecognitionException {
        JavaParser.char_const_return retval = new JavaParser.char_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:299:2: ( CHAR_CONST )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:299:4: CHAR_CONST
            {
            match(input,CHAR_CONST,FOLLOW_CHAR_CONST_in_char_const1827); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:300:1: null_const : NULL ;
    public final JavaParser.null_const_return null_const() throws RecognitionException {
        JavaParser.null_const_return retval = new JavaParser.null_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:301:2: ( NULL )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:301:4: NULL
            {
            match(input,NULL,FOLLOW_NULL_in_null_const1837); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:302:1: boolean_const : BOOL_CONST ;
    public final JavaParser.boolean_const_return boolean_const() throws RecognitionException {
        JavaParser.boolean_const_return retval = new JavaParser.boolean_const_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:303:2: ( BOOL_CONST )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:303:4: BOOL_CONST
            {
            match(input,BOOL_CONST,FOLLOW_BOOL_CONST_in_boolean_const1848); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:304:1: array_const : ( block_begin value ( comma value )* block_end )? ;
    public final void array_const() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:305:2: ( ( block_begin value ( comma value )* block_end )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:305:4: ( block_begin value ( comma value )* block_end )?
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:305:4: ( block_begin value ( comma value )* block_end )?
            int alt106=2;
            int LA106_0 = input.LA(1);

            if ( (LA106_0==OPEN_CURLY_BRACKET) ) {
                alt106=1;
            }
            switch (alt106) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:305:5: block_begin value ( comma value )* block_end
                    {
                    pushFollow(FOLLOW_block_begin_in_array_const1859);
                    block_begin();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_array_const1861);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:305:23: ( comma value )*
                    loop105:
                    do {
                        int alt105=2;
                        int LA105_0 = input.LA(1);

                        if ( (LA105_0==COMMA) ) {
                            alt105=1;
                        }


                        switch (alt105) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:305:24: comma value
                    	    {
                    	    pushFollow(FOLLOW_comma_in_array_const1864);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_value_in_array_const1866);
                    	    value();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop105;
                        }
                    } while (true);

                    pushFollow(FOLLOW_block_end_in_array_const1870);
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
    // $ANTLR end "array_const"

    public static class package_name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "package_name"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:310:1: package_name : ( ID DOT )* ID ;
    public final JavaParser.package_name_return package_name() throws RecognitionException {
        JavaParser.package_name_return retval = new JavaParser.package_name_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:311:2: ( ( ID DOT )* ID )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:311:4: ( ID DOT )* ID
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:311:4: ( ID DOT )*
            loop107:
            do {
                int alt107=2;
                int LA107_0 = input.LA(1);

                if ( (LA107_0==ID) ) {
                    int LA107_1 = input.LA(2);

                    if ( (LA107_1==DOT) ) {
                        alt107=1;
                    }


                }


                switch (alt107) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:311:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_package_name1885); if (state.failed) return retval;
            	    match(input,DOT,FOLLOW_DOT_in_package_name1887); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop107;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_package_name1891); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:314:1: import_name : ( ID DOT )+ ( ID | STAR ) ;
    public final JavaParser.import_name_return import_name() throws RecognitionException {
        JavaParser.import_name_return retval = new JavaParser.import_name_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:315:2: ( ( ID DOT )+ ( ID | STAR ) )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:315:4: ( ID DOT )+ ( ID | STAR )
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:315:4: ( ID DOT )+
            int cnt108=0;
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
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:315:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_import_name1905); if (state.failed) return retval;
            	    match(input,DOT,FOLLOW_DOT_in_import_name1907); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    if ( cnt108 >= 1 ) break loop108;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(108, input);
                        throw eee;
                }
                cnt108++;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:318:1: class_name : ( ID DOT )* ID ;
    public final JavaParser.class_name_return class_name() throws RecognitionException {
        JavaParser.class_name_return retval = new JavaParser.class_name_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:319:2: ( ( ID DOT )* ID )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:319:4: ( ID DOT )* ID
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:319:4: ( ID DOT )*
            loop109:
            do {
                int alt109=2;
                int LA109_0 = input.LA(1);

                if ( (LA109_0==ID) ) {
                    int LA109_1 = input.LA(2);

                    if ( (LA109_1==DOT) ) {
                        int LA109_2 = input.LA(3);

                        if ( (LA109_2==ID) ) {
                            alt109=1;
                        }


                    }


                }


                switch (alt109) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:319:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_class_name1929); if (state.failed) return retval;
            	    match(input,DOT,FOLLOW_DOT_in_class_name1931); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop109;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_class_name1935); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:322:1: method_name : ( ID DOT )* ID ;
    public final JavaParser.method_name_return method_name() throws RecognitionException {
        JavaParser.method_name_return retval = new JavaParser.method_name_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:323:2: ( ( ID DOT )* ID )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:323:4: ( ID DOT )* ID
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:323:4: ( ID DOT )*
            loop110:
            do {
                int alt110=2;
                int LA110_0 = input.LA(1);

                if ( (LA110_0==ID) ) {
                    int LA110_1 = input.LA(2);

                    if ( (LA110_1==DOT) ) {
                        alt110=1;
                    }


                }


                switch (alt110) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:323:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_method_name1949); if (state.failed) return retval;
            	    match(input,DOT,FOLLOW_DOT_in_method_name1951); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop110;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_method_name1955); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:326:1: variable_name : name ( array )? ;
    public final void variable_name() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:327:2: ( name ( array )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:327:4: name ( array )?
            {
            pushFollow(FOLLOW_name_in_variable_name1968);
            name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:327:9: ( array )?
            int alt111=2;
            int LA111_0 = input.LA(1);

            if ( (LA111_0==OPEN_RECT_BRACKET) ) {
                alt111=1;
            }
            switch (alt111) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: array
                    {
                    pushFollow(FOLLOW_array_in_variable_name1970);
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:329:1: annotation_name : AT name ;
    public final JavaParser.annotation_name_return annotation_name() throws RecognitionException {
        JavaParser.annotation_name_return retval = new JavaParser.annotation_name_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:330:2: ( AT name )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:330:4: AT name
            {
            match(input,AT,FOLLOW_AT_in_annotation_name1981); if (state.failed) return retval;
            pushFollow(FOLLOW_name_in_annotation_name1983);
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
        }
        return retval;
    }
    // $ANTLR end "annotation_name"

    public static class name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "name"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:331:1: name : ( ID DOT )* ID ;
    public final JavaParser.name_return name() throws RecognitionException {
        JavaParser.name_return retval = new JavaParser.name_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:331:6: ( ( ID DOT )* ID )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:331:8: ( ID DOT )* ID
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:331:8: ( ID DOT )*
            loop112:
            do {
                int alt112=2;
                int LA112_0 = input.LA(1);

                if ( (LA112_0==ID) ) {
                    int LA112_1 = input.LA(2);

                    if ( (LA112_1==DOT) ) {
                        alt112=1;
                    }


                }


                switch (alt112) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:331:9: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_name1993); if (state.failed) return retval;
            	    match(input,DOT,FOLLOW_DOT_in_name1995); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop112;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_name1999); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:333:1: array : ( open_rect_bracket ( value )? close_rect_bracket )+ ;
    public final void array() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:333:7: ( ( open_rect_bracket ( value )? close_rect_bracket )+ )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:333:9: ( open_rect_bracket ( value )? close_rect_bracket )+
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:333:9: ( open_rect_bracket ( value )? close_rect_bracket )+
            int cnt114=0;
            loop114:
            do {
                int alt114=2;
                int LA114_0 = input.LA(1);

                if ( (LA114_0==OPEN_RECT_BRACKET) ) {
                    alt114=1;
                }


                switch (alt114) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:333:10: open_rect_bracket ( value )? close_rect_bracket
            	    {
            	    pushFollow(FOLLOW_open_rect_bracket_in_array2010);
            	    open_rect_bracket();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:333:28: ( value )?
            	    int alt113=2;
            	    int LA113_0 = input.LA(1);

            	    if ( (LA113_0==NEW||(LA113_0>=NULL && LA113_0<=SUPER)||LA113_0==OPEN_CURLY_BRACKET||(LA113_0>=LE && LA113_0<=UNEQUAL)||(LA113_0>=INC && LA113_0<=NOT)||(LA113_0>=LT && LA113_0<=GT)||LA113_0==OPEN_BRACKET||(LA113_0>=BOOL_CONST && LA113_0<=INT_CONST)||(LA113_0>=HEX_CONST && LA113_0<=HEX_LONG_CONST)||LA113_0==FLOAT_CONST||(LA113_0>=STRING_CONST && LA113_0<=CHAR_CONST)) ) {
            	        alt113=1;
            	    }
            	    else if ( (LA113_0==CLOSE_RECT_BRACKET) ) {
            	        int LA113_2 = input.LA(2);

            	        if ( (synpred196_JavaParser()) ) {
            	            alt113=1;
            	        }
            	    }
            	    switch (alt113) {
            	        case 1 :
            	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: value
            	            {
            	            pushFollow(FOLLOW_value_in_array2012);
            	            value();

            	            state._fsp--;
            	            if (state.failed) return ;

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_close_rect_bracket_in_array2015);
            	    close_rect_bracket();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt114 >= 1 ) break loop114;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(114, input);
                        throw eee;
                }
                cnt114++;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:339:1: package_ : PACKAGE ;
    public final JavaParser.package__return package_() throws RecognitionException {
        JavaParser.package__return retval = new JavaParser.package__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:340:2: ( PACKAGE )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:340:4: PACKAGE
            {
            match(input,PACKAGE,FOLLOW_PACKAGE_in_package_2030); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:341:1: import_ : IMPORT ;
    public final JavaParser.import__return import_() throws RecognitionException {
        JavaParser.import__return retval = new JavaParser.import__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:342:2: ( IMPORT )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:342:4: IMPORT
            {
            match(input,IMPORT,FOLLOW_IMPORT_in_import_2041); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:343:1: class_ : CLASS ;
    public final JavaParser.class__return class_() throws RecognitionException {
        JavaParser.class__return retval = new JavaParser.class__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:343:8: ( CLASS )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:343:10: CLASS
            {
            match(input,CLASS,FOLLOW_CLASS_in_class_2051); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:344:1: enum_ : ENUM ;
    public final JavaParser.enum__return enum_() throws RecognitionException {
        JavaParser.enum__return retval = new JavaParser.enum__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:344:7: ( ENUM )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:344:9: ENUM
            {
            match(input,ENUM,FOLLOW_ENUM_in_enum_2060); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:345:1: interface_ : INTERFACE ;
    public final JavaParser.interface__return interface_() throws RecognitionException {
        JavaParser.interface__return retval = new JavaParser.interface__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:346:2: ( INTERFACE )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:346:4: INTERFACE
            {
            match(input,INTERFACE,FOLLOW_INTERFACE_in_interface_2070); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:347:1: extends_ : EXTENDS ;
    public final JavaParser.extends__return extends_() throws RecognitionException {
        JavaParser.extends__return retval = new JavaParser.extends__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:347:9: ( EXTENDS )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:347:11: EXTENDS
            {
            match(input,EXTENDS,FOLLOW_EXTENDS_in_extends_2078); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:348:1: implements_ : IMPLEMENTS ;
    public final JavaParser.implements__return implements_() throws RecognitionException {
        JavaParser.implements__return retval = new JavaParser.implements__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:349:2: ( IMPLEMENTS )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:349:4: IMPLEMENTS
            {
            match(input,IMPLEMENTS,FOLLOW_IMPLEMENTS_in_implements_2088); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:350:1: this_ : THIS ;
    public final JavaParser.this__return this_() throws RecognitionException {
        JavaParser.this__return retval = new JavaParser.this__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:350:7: ( THIS )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:350:9: THIS
            {
            match(input,THIS,FOLLOW_THIS_in_this_2097); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:351:1: super_ : SUPER ;
    public final JavaParser.super__return super_() throws RecognitionException {
        JavaParser.super__return retval = new JavaParser.super__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:351:8: ( SUPER )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:351:10: SUPER
            {
            match(input,SUPER,FOLLOW_SUPER_in_super_2106); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:352:1: void_ : VOID ;
    public final JavaParser.void__return void_() throws RecognitionException {
        JavaParser.void__return retval = new JavaParser.void__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:352:7: ( VOID )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:352:9: VOID
            {
            match(input,VOID,FOLLOW_VOID_in_void_2115); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:353:1: public_ : PUBLIC ;
    public final JavaParser.public__return public_() throws RecognitionException {
        JavaParser.public__return retval = new JavaParser.public__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:353:9: ( PUBLIC )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:353:11: PUBLIC
            {
            match(input,PUBLIC,FOLLOW_PUBLIC_in_public_2124); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:354:1: private_ : PRIVATE ;
    public final JavaParser.private__return private_() throws RecognitionException {
        JavaParser.private__return retval = new JavaParser.private__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:354:9: ( PRIVATE )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:354:11: PRIVATE
            {
            match(input,PRIVATE,FOLLOW_PRIVATE_in_private_2132); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:355:1: protected_ : PROTECTED ;
    public final JavaParser.protected__return protected_() throws RecognitionException {
        JavaParser.protected__return retval = new JavaParser.protected__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:356:2: ( PROTECTED )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:356:4: PROTECTED
            {
            match(input,PROTECTED,FOLLOW_PROTECTED_in_protected_2142); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:357:1: static_ : STATIC ;
    public final JavaParser.static__return static_() throws RecognitionException {
        JavaParser.static__return retval = new JavaParser.static__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:357:9: ( STATIC )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:357:11: STATIC
            {
            match(input,STATIC,FOLLOW_STATIC_in_static_2151); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:358:1: final_ : FINAL ;
    public final JavaParser.final__return final_() throws RecognitionException {
        JavaParser.final__return retval = new JavaParser.final__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:358:8: ( FINAL )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:358:10: FINAL
            {
            match(input,FINAL,FOLLOW_FINAL_in_final_2160); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:359:1: transient_ : TRANSIENT ;
    public final JavaParser.transient__return transient_() throws RecognitionException {
        JavaParser.transient__return retval = new JavaParser.transient__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:360:2: ( TRANSIENT )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:360:4: TRANSIENT
            {
            match(input,TRANSIENT,FOLLOW_TRANSIENT_in_transient_2170); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:361:1: new_ : NEW ;
    public final JavaParser.new__return new_() throws RecognitionException {
        JavaParser.new__return retval = new JavaParser.new__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:361:6: ( NEW )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:361:8: NEW
            {
            match(input,NEW,FOLLOW_NEW_in_new_2179); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:362:1: try_ : TRY ;
    public final JavaParser.try__return try_() throws RecognitionException {
        JavaParser.try__return retval = new JavaParser.try__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:362:6: ( TRY )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:362:8: TRY
            {
            match(input,TRY,FOLLOW_TRY_in_try_2188); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:363:1: catch_ : CATCH ;
    public final JavaParser.catch__return catch_() throws RecognitionException {
        JavaParser.catch__return retval = new JavaParser.catch__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:363:8: ( CATCH )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:363:10: CATCH
            {
            match(input,CATCH,FOLLOW_CATCH_in_catch_2197); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:364:1: finally_ : FINALLY ;
    public final JavaParser.finally__return finally_() throws RecognitionException {
        JavaParser.finally__return retval = new JavaParser.finally__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:364:9: ( FINALLY )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:364:11: FINALLY
            {
            match(input,FINALLY,FOLLOW_FINALLY_in_finally_2205); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:365:1: throws_ : THROWS ;
    public final JavaParser.throws__return throws_() throws RecognitionException {
        JavaParser.throws__return retval = new JavaParser.throws__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:365:9: ( THROWS )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:365:11: THROWS
            {
            match(input,THROWS,FOLLOW_THROWS_in_throws_2214); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:366:1: throw_ : THROW ;
    public final JavaParser.throw__return throw_() throws RecognitionException {
        JavaParser.throw__return retval = new JavaParser.throw__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:366:8: ( THROW )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:366:10: THROW
            {
            match(input,THROW,FOLLOW_THROW_in_throw_2223); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:368:1: for_ : FOR ;
    public final void for_() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:368:6: ( FOR )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:368:8: FOR
            {
            match(input,FOR,FOLLOW_FOR_in_for_2233); if (state.failed) return ;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:369:1: while_ : WHILE ;
    public final void while_() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:369:8: ( WHILE )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:369:10: WHILE
            {
            match(input,WHILE,FOLLOW_WHILE_in_while_2242); if (state.failed) return ;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:370:1: do_ : DO ;
    public final void do_() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:370:5: ( DO )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:370:7: DO
            {
            match(input,DO,FOLLOW_DO_in_do_2251); if (state.failed) return ;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:371:1: if_ : IF ;
    public final void if_() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:371:5: ( IF )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:371:7: IF
            {
            match(input,IF,FOLLOW_IF_in_if_2260); if (state.failed) return ;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:372:1: else_ : ELSE ;
    public final void else_() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:372:7: ( ELSE )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:372:9: ELSE
            {
            match(input,ELSE,FOLLOW_ELSE_in_else_2269); if (state.failed) return ;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:374:1: switch_ : SWITCH ;
    public final void switch_() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:374:9: ( SWITCH )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:374:11: SWITCH
            {
            match(input,SWITCH,FOLLOW_SWITCH_in_switch_2279); if (state.failed) return ;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:375:1: case_ : CASE ;
    public final void case_() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:375:7: ( CASE )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:375:9: CASE
            {
            match(input,CASE,FOLLOW_CASE_in_case_2288); if (state.failed) return ;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:376:1: default_ : DEFAULT ;
    public final void default_() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:376:9: ( DEFAULT )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:376:11: DEFAULT
            {
            match(input,DEFAULT,FOLLOW_DEFAULT_in_default_2296); if (state.failed) return ;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:378:1: return_ : RETURN ;
    public final JavaParser.return__return return_() throws RecognitionException {
        JavaParser.return__return retval = new JavaParser.return__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:378:9: ( RETURN )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:378:11: RETURN
            {
            match(input,RETURN,FOLLOW_RETURN_in_return_2306); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:379:1: break_ : ( BREAK id | BREAK );
    public final JavaParser.break__return break_() throws RecognitionException {
        JavaParser.break__return retval = new JavaParser.break__return();
        retval.start = input.LT(1);

        Token BREAK6=null;

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:379:8: ( BREAK id | BREAK )
            int alt115=2;
            int LA115_0 = input.LA(1);

            if ( (LA115_0==BREAK) ) {
                int LA115_1 = input.LA(2);

                if ( (LA115_1==EOF||LA115_1==CLASS||LA115_1==VOID||(LA115_1>=RETURN && LA115_1<=CONTINUE)||(LA115_1>=THIS && LA115_1<=THROW)||(LA115_1>=PUBLIC && LA115_1<=CLOSE_CURLY_BRACKET)||(LA115_1>=INC && LA115_1<=MINUS)||LA115_1==NOT||LA115_1==COMMA||LA115_1==CLOSE_BRACKET||(LA115_1>=SEMICOLON && LA115_1<=AT)) ) {
                    alt115=2;
                }
                else if ( (LA115_1==ID) ) {
                    int LA115_3 = input.LA(3);

                    if ( (synpred198_JavaParser()) ) {
                        alt115=1;
                    }
                    else if ( (true) ) {
                        alt115=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 115, 3, input);

                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 115, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 115, 0, input);

                throw nvae;
            }
            switch (alt115) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:379:10: BREAK id
                    {
                    BREAK6=(Token)match(input,BREAK,FOLLOW_BREAK_in_break_2315); if (state.failed) return retval;
                    pushFollow(FOLLOW_id_in_break_2317);
                    id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator((BREAK6!=null?BREAK6.getText():null));
                    }

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:380:4: BREAK
                    {
                    match(input,BREAK,FOLLOW_BREAK_in_break_2324); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:382:1: continue_ : CONTINUE ;
    public final JavaParser.continue__return continue_() throws RecognitionException {
        JavaParser.continue__return retval = new JavaParser.continue__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:383:2: ( CONTINUE )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:383:4: CONTINUE
            {
            match(input,CONTINUE,FOLLOW_CONTINUE_in_continue_2336); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:389:1: semicolon : SEMICOLON ;
    public final JavaParser.semicolon_return semicolon() throws RecognitionException {
        JavaParser.semicolon_return retval = new JavaParser.semicolon_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:390:2: ( SEMICOLON )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:390:4: SEMICOLON
            {
            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_semicolon2351); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:391:1: comma : COMMA ;
    public final JavaParser.comma_return comma() throws RecognitionException {
        JavaParser.comma_return retval = new JavaParser.comma_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:391:7: ( COMMA )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:391:9: COMMA
            {
            match(input,COMMA,FOLLOW_COMMA_in_comma2360); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:392:1: colon : COLON ;
    public final JavaParser.colon_return colon() throws RecognitionException {
        JavaParser.colon_return retval = new JavaParser.colon_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:392:7: ( COLON )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:392:9: COLON
            {
            match(input,COLON,FOLLOW_COLON_in_colon2369); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:393:1: question_ : QUESTION ;
    public final JavaParser.question__return question_() throws RecognitionException {
        JavaParser.question__return retval = new JavaParser.question__return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:394:2: ( QUESTION )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:394:4: QUESTION
            {
            match(input,QUESTION,FOLLOW_QUESTION_in_question_2379); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:395:1: plus : PLUS ;
    public final JavaParser.plus_return plus() throws RecognitionException {
        JavaParser.plus_return retval = new JavaParser.plus_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:395:6: ( PLUS )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:395:8: PLUS
            {
            match(input,PLUS,FOLLOW_PLUS_in_plus2388); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:396:1: minus : MINUS ;
    public final JavaParser.minus_return minus() throws RecognitionException {
        JavaParser.minus_return retval = new JavaParser.minus_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:396:7: ( MINUS )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:396:9: MINUS
            {
            match(input,MINUS,FOLLOW_MINUS_in_minus2397); if (state.failed) return retval;
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

    public static class dot_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "dot"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:397:1: dot : DOT ;
    public final JavaParser.dot_return dot() throws RecognitionException {
        JavaParser.dot_return retval = new JavaParser.dot_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:397:5: ( DOT )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:397:7: DOT
            {
            match(input,DOT,FOLLOW_DOT_in_dot2406); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:398:1: assign : ( ASSIGN | INCASSIGN | DECASSIGN );
    public final JavaParser.assign_return assign() throws RecognitionException {
        JavaParser.assign_return retval = new JavaParser.assign_return();
        retval.start = input.LT(1);

        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:398:8: ( ASSIGN | INCASSIGN | DECASSIGN )
            int alt116=3;
            switch ( input.LA(1) ) {
            case ASSIGN:
                {
                alt116=1;
                }
                break;
            case INCASSIGN:
                {
                alt116=2;
                }
                break;
            case DECASSIGN:
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
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:398:10: ASSIGN
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_assign2415); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:399:4: INCASSIGN
                    {
                    match(input,INCASSIGN,FOLLOW_INCASSIGN_in_assign2422); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:400:4: DECASSIGN
                    {
                    match(input,DECASSIGN,FOLLOW_DECASSIGN_in_assign2429); if (state.failed) return retval;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:402:1: block_begin : OPEN_CURLY_BRACKET ;
    public final void block_begin() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:403:2: ( OPEN_CURLY_BRACKET )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:403:4: OPEN_CURLY_BRACKET
            {
            match(input,OPEN_CURLY_BRACKET,FOLLOW_OPEN_CURLY_BRACKET_in_block_begin2441); if (state.failed) return ;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:404:1: block_end : CLOSE_CURLY_BRACKET ;
    public final void block_end() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:405:2: ( CLOSE_CURLY_BRACKET )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:405:4: CLOSE_CURLY_BRACKET
            {
            match(input,CLOSE_CURLY_BRACKET,FOLLOW_CLOSE_CURLY_BRACKET_in_block_end2451); if (state.failed) return ;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:406:1: open_bracket : OPEN_BRACKET ;
    public final void open_bracket() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:407:2: ( OPEN_BRACKET )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:407:4: OPEN_BRACKET
            {
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_open_bracket2461); if (state.failed) return ;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:408:1: close_bracket : CLOSE_BRACKET ;
    public final void close_bracket() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:409:2: ( CLOSE_BRACKET )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:409:4: CLOSE_BRACKET
            {
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_close_bracket2471); if (state.failed) return ;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:410:1: open_rect_bracket : OPEN_RECT_BRACKET ;
    public final void open_rect_bracket() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:411:2: ( OPEN_RECT_BRACKET )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:411:4: OPEN_RECT_BRACKET
            {
            match(input,OPEN_RECT_BRACKET,FOLLOW_OPEN_RECT_BRACKET_in_open_rect_bracket2482); if (state.failed) return ;
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
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:412:1: close_rect_bracket : CLOSE_RECT_BRACKET ;
    public final void close_rect_bracket() throws RecognitionException {
        try {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:413:2: ( CLOSE_RECT_BRACKET )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:413:4: CLOSE_RECT_BRACKET
            {
            match(input,CLOSE_RECT_BRACKET,FOLLOW_CLOSE_RECT_BRACKET_in_close_rect_bracket2492); if (state.failed) return ;
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
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:17: ( class_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:17: class_def
        {
        pushFollow(FOLLOW_class_def_in_synpred41_JavaParser437);
        class_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred41_JavaParser

    // $ANTLR start synpred42_JavaParser
    public final void synpred42_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:29: ( static_init )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:29: static_init
        {
        pushFollow(FOLLOW_static_init_in_synpred42_JavaParser441);
        static_init();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred42_JavaParser

    // $ANTLR start synpred43_JavaParser
    public final void synpred43_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:43: ( constructor_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:43: constructor_def
        {
        pushFollow(FOLLOW_constructor_def_in_synpred43_JavaParser445);
        constructor_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred43_JavaParser

    // $ANTLR start synpred44_JavaParser
    public final void synpred44_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:61: ( method_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:61: method_def
        {
        pushFollow(FOLLOW_method_def_in_synpred44_JavaParser449);
        method_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred44_JavaParser

    // $ANTLR start synpred45_JavaParser
    public final void synpred45_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:74: ( field_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:74: field_def
        {
        pushFollow(FOLLOW_field_def_in_synpred45_JavaParser453);
        field_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred45_JavaParser

    // $ANTLR start synpred47_JavaParser
    public final void synpred47_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:17: ( class_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:17: class_def
        {
        pushFollow(FOLLOW_class_def_in_synpred47_JavaParser475);
        class_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred47_JavaParser

    // $ANTLR start synpred48_JavaParser
    public final void synpred48_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:29: ( constructor_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:29: constructor_def
        {
        pushFollow(FOLLOW_constructor_def_in_synpred48_JavaParser479);
        constructor_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred48_JavaParser

    // $ANTLR start synpred49_JavaParser
    public final void synpred49_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:47: ( method_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:47: method_def
        {
        pushFollow(FOLLOW_method_def_in_synpred49_JavaParser483);
        method_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred49_JavaParser

    // $ANTLR start synpred50_JavaParser
    public final void synpred50_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:60: ( field_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:60: field_def
        {
        pushFollow(FOLLOW_field_def_in_synpred50_JavaParser487);
        field_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred50_JavaParser

    // $ANTLR start synpred51_JavaParser
    public final void synpred51_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:72: ( enum_content )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:72: enum_content
        {
        pushFollow(FOLLOW_enum_content_in_synpred51_JavaParser491);
        enum_content();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred51_JavaParser

    // $ANTLR start synpred72_JavaParser
    public final void synpred72_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:15: ( left_unary )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:15: left_unary
        {
        pushFollow(FOLLOW_left_unary_in_synpred72_JavaParser728);
        left_unary();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred72_JavaParser

    // $ANTLR start synpred75_JavaParser
    public final void synpred75_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:79: ( question_ value colon value )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:79: question_ value colon value
        {
        pushFollow(FOLLOW_question__in_synpred75_JavaParser744);
        question_();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_value_in_synpred75_JavaParser746);
        value();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_colon_in_synpred75_JavaParser748);
        colon();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_value_in_synpred75_JavaParser750);
        value();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred75_JavaParser

    // $ANTLR start synpred76_JavaParser
    public final void synpred76_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:9: ( ( cast )? ( left_unary )? single_value ( right_unary )? ( binary_operator value )? ( question_ value colon value )? )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:9: ( cast )? ( left_unary )? single_value ( right_unary )? ( binary_operator value )? ( question_ value colon value )?
        {
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:9: ( cast )?
        int alt134=2;
        int LA134_0 = input.LA(1);

        if ( (LA134_0==OPEN_BRACKET) ) {
            alt134=1;
        }
        switch (alt134) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: cast
                {
                pushFollow(FOLLOW_cast_in_synpred76_JavaParser725);
                cast();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:15: ( left_unary )?
        int alt135=2;
        switch ( input.LA(1) ) {
            case INC:
                {
                int LA135_1 = input.LA(2);

                if ( (synpred72_JavaParser()) ) {
                    alt135=1;
                }
                }
                break;
            case DEC:
                {
                int LA135_2 = input.LA(2);

                if ( (synpred72_JavaParser()) ) {
                    alt135=1;
                }
                }
                break;
            case NOT:
                {
                int LA135_3 = input.LA(2);

                if ( (synpred72_JavaParser()) ) {
                    alt135=1;
                }
                }
                break;
            case MINUS:
                {
                int LA135_4 = input.LA(2);

                if ( (synpred72_JavaParser()) ) {
                    alt135=1;
                }
                }
                break;
            case PLUS:
                {
                int LA135_5 = input.LA(2);

                if ( (synpred72_JavaParser()) ) {
                    alt135=1;
                }
                }
                break;
        }

        switch (alt135) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: left_unary
                {
                pushFollow(FOLLOW_left_unary_in_synpred76_JavaParser728);
                left_unary();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        pushFollow(FOLLOW_single_value_in_synpred76_JavaParser731);
        single_value();

        state._fsp--;
        if (state.failed) return ;
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:40: ( right_unary )?
        int alt136=2;
        int LA136_0 = input.LA(1);

        if ( ((LA136_0>=INC && LA136_0<=DEC)||LA136_0==NOT) ) {
            alt136=1;
        }
        switch (alt136) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: right_unary
                {
                pushFollow(FOLLOW_right_unary_in_synpred76_JavaParser733);
                right_unary();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:53: ( binary_operator value )?
        int alt137=2;
        int LA137_0 = input.LA(1);

        if ( ((LA137_0>=LE && LA137_0<=UNEQUAL)||(LA137_0>=PLUS && LA137_0<=STAR)||(LA137_0>=LOGICAL_OR && LA137_0<=BIT_AND)||(LA137_0>=LT && LA137_0<=GT)) ) {
            alt137=1;
        }
        switch (alt137) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:54: binary_operator value
                {
                pushFollow(FOLLOW_binary_operator_in_synpred76_JavaParser737);
                binary_operator();

                state._fsp--;
                if (state.failed) return ;
                pushFollow(FOLLOW_value_in_synpred76_JavaParser739);
                value();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:78: ( question_ value colon value )?
        int alt138=2;
        int LA138_0 = input.LA(1);

        if ( (LA138_0==QUESTION) ) {
            alt138=1;
        }
        switch (alt138) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:79: question_ value colon value
                {
                pushFollow(FOLLOW_question__in_synpred76_JavaParser744);
                question_();

                state._fsp--;
                if (state.failed) return ;
                pushFollow(FOLLOW_value_in_synpred76_JavaParser746);
                value();

                state._fsp--;
                if (state.failed) return ;
                pushFollow(FOLLOW_colon_in_synpred76_JavaParser748);
                colon();

                state._fsp--;
                if (state.failed) return ;
                pushFollow(FOLLOW_value_in_synpred76_JavaParser750);
                value();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred76_JavaParser

    // $ANTLR start synpred93_JavaParser
    public final void synpred93_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:21: ( generic )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:21: generic
        {
        pushFollow(FOLLOW_generic_in_synpred93_JavaParser884);
        generic();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred93_JavaParser

    // $ANTLR start synpred98_JavaParser
    public final void synpred98_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:146:5: ( value ( comma value )* )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:146:5: value ( comma value )*
        {
        pushFollow(FOLLOW_value_in_synpred98_JavaParser920);
        value();

        state._fsp--;
        if (state.failed) return ;
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:146:11: ( comma value )*
        loop140:
        do {
            int alt140=2;
            int LA140_0 = input.LA(1);

            if ( (LA140_0==COMMA) ) {
                alt140=1;
            }


            switch (alt140) {
        	case 1 :
        	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:146:12: comma value
        	    {
        	    pushFollow(FOLLOW_comma_in_synpred98_JavaParser923);
        	    comma();

        	    state._fsp--;
        	    if (state.failed) return ;
        	    pushFollow(FOLLOW_value_in_synpred98_JavaParser925);
        	    value();

        	    state._fsp--;
        	    if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop140;
            }
        } while (true);


        }
    }
    // $ANTLR end synpred98_JavaParser

    // $ANTLR start synpred101_JavaParser
    public final void synpred101_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:150:32: ( semicolon )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:150:32: semicolon
        {
        pushFollow(FOLLOW_semicolon_in_synpred101_JavaParser951);
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
        pushFollow(FOLLOW_label_in_synpred102_JavaParser964);
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
        pushFollow(FOLLOW_variable_assignment_in_synpred103_JavaParser970);
        variable_assignment();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_semicolon_in_synpred103_JavaParser972);
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
        pushFollow(FOLLOW_variable_def_in_synpred104_JavaParser977);
        variable_def();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_semicolon_in_synpred104_JavaParser979);
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
        pushFollow(FOLLOW_method_call_in_synpred105_JavaParser984);
        method_call();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_semicolon_in_synpred105_JavaParser986);
        semicolon();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred105_JavaParser

    // $ANTLR start synpred118_JavaParser
    public final void synpred118_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:169:30: ( right_unary )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:169:30: right_unary
        {
        pushFollow(FOLLOW_right_unary_in_synpred118_JavaParser1058);
        right_unary();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred118_JavaParser

    // $ANTLR start synpred119_JavaParser
    public final void synpred119_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:173:4: ( label )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:173:4: label
        {
        pushFollow(FOLLOW_label_in_synpred119_JavaParser1070);
        label();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred119_JavaParser

    // $ANTLR start synpred120_JavaParser
    public final void synpred120_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:174:4: ( variable_assignment )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:174:4: variable_assignment
        {
        pushFollow(FOLLOW_variable_assignment_in_synpred120_JavaParser1075);
        variable_assignment();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred120_JavaParser

    // $ANTLR start synpred121_JavaParser
    public final void synpred121_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:175:4: ( variable_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:175:4: variable_def
        {
        pushFollow(FOLLOW_variable_def_in_synpred121_JavaParser1080);
        variable_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred121_JavaParser

    // $ANTLR start synpred122_JavaParser
    public final void synpred122_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:176:4: ( method_call )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:176:4: method_call
        {
        pushFollow(FOLLOW_method_call_in_synpred122_JavaParser1085);
        method_call();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred122_JavaParser

    // $ANTLR start synpred135_JavaParser
    public final void synpred135_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:191:12: ( value )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:191:12: value
        {
        pushFollow(FOLLOW_value_in_synpred135_JavaParser1165);
        value();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred135_JavaParser

    // $ANTLR start synpred140_JavaParser
    public final void synpred140_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:77: ( value )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:77: value
        {
        pushFollow(FOLLOW_value_in_synpred140_JavaParser1225);
        value();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred140_JavaParser

    // $ANTLR start synpred143_JavaParser
    public final void synpred143_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:11: ( for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon ( value )? semicolon ( statement_wosemicolon ( comma statement_wosemicolon )* )? CLOSE_BRACKET code )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:11: for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon ( value )? semicolon ( statement_wosemicolon ( comma statement_wosemicolon )* )? CLOSE_BRACKET code
        {
        pushFollow(FOLLOW_for__in_synpred143_JavaParser1207);
        for_();

        state._fsp--;
        if (state.failed) return ;
        match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_synpred143_JavaParser1209); if (state.failed) return ;
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:29: ( variable_def ( comma variable_def )* )?
        int alt144=2;
        int LA144_0 = input.LA(1);

        if ( (LA144_0==VOID||(LA144_0>=BOOLEAN && LA144_0<=DOUBLE)||LA144_0==AT||LA144_0==ID) ) {
            alt144=1;
        }
        switch (alt144) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:30: variable_def ( comma variable_def )*
                {
                pushFollow(FOLLOW_variable_def_in_synpred143_JavaParser1212);
                variable_def();

                state._fsp--;
                if (state.failed) return ;
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:43: ( comma variable_def )*
                loop143:
                do {
                    int alt143=2;
                    int LA143_0 = input.LA(1);

                    if ( (LA143_0==COMMA) ) {
                        alt143=1;
                    }


                    switch (alt143) {
                	case 1 :
                	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:44: comma variable_def
                	    {
                	    pushFollow(FOLLOW_comma_in_synpred143_JavaParser1215);
                	    comma();

                	    state._fsp--;
                	    if (state.failed) return ;
                	    pushFollow(FOLLOW_variable_def_in_synpred143_JavaParser1217);
                	    variable_def();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop143;
                    }
                } while (true);


                }
                break;

        }

        pushFollow(FOLLOW_semicolon_in_synpred143_JavaParser1223);
        semicolon();

        state._fsp--;
        if (state.failed) return ;
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:77: ( value )?
        int alt145=2;
        int LA145_0 = input.LA(1);

        if ( (LA145_0==NEW||(LA145_0>=NULL && LA145_0<=SUPER)||LA145_0==OPEN_CURLY_BRACKET||(LA145_0>=LE && LA145_0<=UNEQUAL)||(LA145_0>=INC && LA145_0<=NOT)||(LA145_0>=LT && LA145_0<=GT)||LA145_0==OPEN_BRACKET||(LA145_0>=BOOL_CONST && LA145_0<=INT_CONST)||(LA145_0>=HEX_CONST && LA145_0<=HEX_LONG_CONST)||LA145_0==FLOAT_CONST||(LA145_0>=STRING_CONST && LA145_0<=CHAR_CONST)) ) {
            alt145=1;
        }
        else if ( (LA145_0==SEMICOLON) ) {
            int LA145_2 = input.LA(2);

            if ( (synpred140_JavaParser()) ) {
                alt145=1;
            }
        }
        switch (alt145) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: value
                {
                pushFollow(FOLLOW_value_in_synpred143_JavaParser1225);
                value();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        pushFollow(FOLLOW_semicolon_in_synpred143_JavaParser1228);
        semicolon();

        state._fsp--;
        if (state.failed) return ;
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:94: ( statement_wosemicolon ( comma statement_wosemicolon )* )?
        int alt147=2;
        int LA147_0 = input.LA(1);

        if ( (LA147_0==VOID||(LA147_0>=RETURN && LA147_0<=CONTINUE)||(LA147_0>=THIS && LA147_0<=IF)||LA147_0==SWITCH||LA147_0==TRY||LA147_0==THROW||(LA147_0>=BOOLEAN && LA147_0<=DOUBLE)||(LA147_0>=INC && LA147_0<=MINUS)||LA147_0==NOT||LA147_0==AT||LA147_0==ID) ) {
            alt147=1;
        }
        switch (alt147) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:95: statement_wosemicolon ( comma statement_wosemicolon )*
                {
                pushFollow(FOLLOW_statement_wosemicolon_in_synpred143_JavaParser1231);
                statement_wosemicolon();

                state._fsp--;
                if (state.failed) return ;
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:117: ( comma statement_wosemicolon )*
                loop146:
                do {
                    int alt146=2;
                    int LA146_0 = input.LA(1);

                    if ( (LA146_0==COMMA) ) {
                        alt146=1;
                    }


                    switch (alt146) {
                	case 1 :
                	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:118: comma statement_wosemicolon
                	    {
                	    pushFollow(FOLLOW_comma_in_synpred143_JavaParser1234);
                	    comma();

                	    state._fsp--;
                	    if (state.failed) return ;
                	    pushFollow(FOLLOW_statement_wosemicolon_in_synpred143_JavaParser1236);
                	    statement_wosemicolon();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop146;
                    }
                } while (true);


                }
                break;

        }

        match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_synpred143_JavaParser1242); if (state.failed) return ;
        pushFollow(FOLLOW_code_in_synpred143_JavaParser1244);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred143_JavaParser

    // $ANTLR start synpred145_JavaParser
    public final void synpred145_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:212:5: ( case_ ( constant | variable_name ) colon )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:212:5: case_ ( constant | variable_name ) colon
        {
        pushFollow(FOLLOW_case__in_synpred145_JavaParser1334);
        case_();

        state._fsp--;
        if (state.failed) return ;
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:212:12: ( constant | variable_name )
        int alt148=2;
        int LA148_0 = input.LA(1);

        if ( (LA148_0==EOF||LA148_0==NULL||(LA148_0>=OPEN_CURLY_BRACKET && LA148_0<=UNEQUAL)||(LA148_0>=INC && LA148_0<=NOT)||(LA148_0>=COMMA && LA148_0<=GT)||(LA148_0>=CLOSE_RECT_BRACKET && LA148_0<=SEMICOLON)||LA148_0==BOOL_CONST||LA148_0==INT_CONST||(LA148_0>=HEX_CONST && LA148_0<=HEX_LONG_CONST)||LA148_0==FLOAT_CONST||(LA148_0>=STRING_CONST && LA148_0<=CHAR_CONST)) ) {
            alt148=1;
        }
        else if ( (LA148_0==ID) ) {
            alt148=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 148, 0, input);

            throw nvae;
        }
        switch (alt148) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:212:13: constant
                {
                pushFollow(FOLLOW_constant_in_synpred145_JavaParser1338);
                constant();

                state._fsp--;
                if (state.failed) return ;

                }
                break;
            case 2 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:212:24: variable_name
                {
                pushFollow(FOLLOW_variable_name_in_synpred145_JavaParser1342);
                variable_name();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        pushFollow(FOLLOW_colon_in_synpred145_JavaParser1345);
        colon();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred145_JavaParser

    // $ANTLR start synpred146_JavaParser
    public final void synpred146_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:212:48: ( code )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:212:48: code
        {
        pushFollow(FOLLOW_code_in_synpred146_JavaParser1350);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred146_JavaParser

    // $ANTLR start synpred147_JavaParser
    public final void synpred147_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:212:55: ( statement )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:212:55: statement
        {
        pushFollow(FOLLOW_statement_in_synpred147_JavaParser1354);
        statement();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred147_JavaParser

    // $ANTLR start synpred150_JavaParser
    public final void synpred150_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:20: ( code )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:20: code
        {
        pushFollow(FOLLOW_code_in_synpred150_JavaParser1377);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred150_JavaParser

    // $ANTLR start synpred151_JavaParser
    public final void synpred151_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:27: ( statement )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:27: statement
        {
        pushFollow(FOLLOW_statement_in_synpred151_JavaParser1381);
        statement();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred151_JavaParser

    // $ANTLR start synpred153_JavaParser
    public final void synpred153_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:219:54: ( else_ code )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:219:54: else_ code
        {
        pushFollow(FOLLOW_else__in_synpred153_JavaParser1414);
        else_();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_code_in_synpred153_JavaParser1416);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred153_JavaParser

    // $ANTLR start synpred154_JavaParser
    public final void synpred154_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:223:15: ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:223:15: catch_ OPEN_BRACKET id id CLOSE_BRACKET code
        {
        pushFollow(FOLLOW_catch__in_synpred154_JavaParser1434);
        catch_();

        state._fsp--;
        if (state.failed) return ;
        match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_synpred154_JavaParser1436); if (state.failed) return ;
        pushFollow(FOLLOW_id_in_synpred154_JavaParser1438);
        id();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_id_in_synpred154_JavaParser1440);
        id();

        state._fsp--;
        if (state.failed) return ;
        match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_synpred154_JavaParser1442); if (state.failed) return ;
        pushFollow(FOLLOW_code_in_synpred154_JavaParser1444);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred154_JavaParser

    // $ANTLR start synpred155_JavaParser
    public final void synpred155_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:223:63: ( finally_ code )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:223:63: finally_ code
        {
        pushFollow(FOLLOW_finally__in_synpred155_JavaParser1449);
        finally_();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_code_in_synpred155_JavaParser1451);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred155_JavaParser

    // $ANTLR start synpred196_JavaParser
    public final void synpred196_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:333:28: ( value )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:333:28: value
        {
        pushFollow(FOLLOW_value_in_synpred196_JavaParser2012);
        value();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred196_JavaParser

    // $ANTLR start synpred198_JavaParser
    public final void synpred198_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:379:10: ( BREAK id )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:379:10: BREAK id
        {
        match(input,BREAK,FOLLOW_BREAK_in_synpred198_JavaParser2315); if (state.failed) return ;
        pushFollow(FOLLOW_id_in_synpred198_JavaParser2317);
        id();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred198_JavaParser

    // Delegated rules

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
    public final boolean synpred150_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred150_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred196_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred196_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred198_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred198_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred154_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred154_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred135_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred135_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred146_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred146_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred151_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred151_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred140_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred140_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred118_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred118_JavaParser_fragment(); // can never throw exception
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
    protected DFA35 dfa35 = new DFA35(this);
    protected DFA37 dfa37 = new DFA37(this);
    protected DFA62 dfa62 = new DFA62(this);
    protected DFA63 dfa63 = new DFA63(this);
    protected DFA65 dfa65 = new DFA65(this);
    protected DFA76 dfa76 = new DFA76(this);
    protected DFA79 dfa79 = new DFA79(this);
    protected DFA90 dfa90 = new DFA90(this);
    protected DFA92 dfa92 = new DFA92(this);
    static final String DFA2_eotS =
        "\14\uffff";
    static final String DFA2_eofS =
        "\1\1\13\uffff";
    static final String DFA2_minS =
        "\1\6\1\uffff\7\0\3\uffff";
    static final String DFA2_maxS =
        "\1\115\1\uffff\7\0\3\uffff";
    static final String DFA2_acceptS =
        "\1\uffff\1\4\7\uffff\1\1\1\2\1\3";
    static final String DFA2_specialS =
        "\2\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\3\uffff}>";
    static final String[] DFA2_transitionS = {
            "\1\11\1\13\1\12\27\uffff\1\3\1\4\1\5\1\7\1\6\1\10\47\uffff\1"+
            "\2",
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
                        if ( (synpred2_JavaParser()) ) {s = 9;}

                        else if ( (synpred3_JavaParser()) ) {s = 10;}

                        else if ( (synpred4_JavaParser()) ) {s = 11;}

                         
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
    static final String DFA35_eotS =
        "\30\uffff";
    static final String DFA35_eofS =
        "\30\uffff";
    static final String DFA35_minS =
        "\1\6\1\uffff\7\0\1\uffff\12\0\4\uffff";
    static final String DFA35_maxS =
        "\1\120\1\uffff\7\0\1\uffff\12\0\4\uffff";
    static final String DFA35_acceptS =
        "\1\uffff\1\6\7\uffff\1\1\12\uffff\1\3\1\4\1\5\1\2";
    static final String DFA35_specialS =
        "\2\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\7\1\10\1\11\1\12"+
        "\1\13\1\14\1\15\1\16\1\17\1\20\4\uffff}>";
    static final String[] DFA35_transitionS = {
            "\1\11\4\uffff\1\23\24\uffff\1\3\1\4\1\5\1\7\1\6\1\10\1\13\1"+
            "\14\1\15\1\16\1\17\1\20\1\21\1\22\1\uffff\1\1\35\uffff\1\2\2"+
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
            "",
            ""
    };

    static final short[] DFA35_eot = DFA.unpackEncodedString(DFA35_eotS);
    static final short[] DFA35_eof = DFA.unpackEncodedString(DFA35_eofS);
    static final char[] DFA35_min = DFA.unpackEncodedStringToUnsignedChars(DFA35_minS);
    static final char[] DFA35_max = DFA.unpackEncodedStringToUnsignedChars(DFA35_maxS);
    static final short[] DFA35_accept = DFA.unpackEncodedString(DFA35_acceptS);
    static final short[] DFA35_special = DFA.unpackEncodedString(DFA35_specialS);
    static final short[][] DFA35_transition;

    static {
        int numStates = DFA35_transitionS.length;
        DFA35_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA35_transition[i] = DFA.unpackEncodedString(DFA35_transitionS[i]);
        }
    }

    class DFA35 extends DFA {

        public DFA35(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 35;
            this.eot = DFA35_eot;
            this.eof = DFA35_eof;
            this.min = DFA35_min;
            this.max = DFA35_max;
            this.accept = DFA35_accept;
            this.special = DFA35_special;
            this.transition = DFA35_transition;
        }
        public String getDescription() {
            return "()* loopback of 86:16: ( class_def | static_init | constructor_def | method_def | field_def )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA35_2 = input.LA(1);

                         
                        int index35_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred41_JavaParser()) ) {s = 9;}

                        else if ( (synpred43_JavaParser()) ) {s = 20;}

                        else if ( (synpred44_JavaParser()) ) {s = 21;}

                        else if ( (synpred45_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA35_3 = input.LA(1);

                         
                        int index35_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred41_JavaParser()) ) {s = 9;}

                        else if ( (synpred43_JavaParser()) ) {s = 20;}

                        else if ( (synpred44_JavaParser()) ) {s = 21;}

                        else if ( (synpred45_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_3);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA35_4 = input.LA(1);

                         
                        int index35_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred41_JavaParser()) ) {s = 9;}

                        else if ( (synpred43_JavaParser()) ) {s = 20;}

                        else if ( (synpred44_JavaParser()) ) {s = 21;}

                        else if ( (synpred45_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_4);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA35_5 = input.LA(1);

                         
                        int index35_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred41_JavaParser()) ) {s = 9;}

                        else if ( (synpred43_JavaParser()) ) {s = 20;}

                        else if ( (synpred44_JavaParser()) ) {s = 21;}

                        else if ( (synpred45_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_5);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA35_6 = input.LA(1);

                         
                        int index35_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred41_JavaParser()) ) {s = 9;}

                        else if ( (synpred42_JavaParser()) ) {s = 23;}

                        else if ( (synpred43_JavaParser()) ) {s = 20;}

                        else if ( (synpred44_JavaParser()) ) {s = 21;}

                        else if ( (synpred45_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_6);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA35_7 = input.LA(1);

                         
                        int index35_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred41_JavaParser()) ) {s = 9;}

                        else if ( (synpred43_JavaParser()) ) {s = 20;}

                        else if ( (synpred44_JavaParser()) ) {s = 21;}

                        else if ( (synpred45_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_7);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA35_8 = input.LA(1);

                         
                        int index35_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred41_JavaParser()) ) {s = 9;}

                        else if ( (synpred43_JavaParser()) ) {s = 20;}

                        else if ( (synpred44_JavaParser()) ) {s = 21;}

                        else if ( (synpred45_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_8);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA35_10 = input.LA(1);

                         
                        int index35_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred43_JavaParser()) ) {s = 20;}

                        else if ( (synpred44_JavaParser()) ) {s = 21;}

                        else if ( (synpred45_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_10);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA35_11 = input.LA(1);

                         
                        int index35_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 21;}

                        else if ( (synpred45_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_11);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA35_12 = input.LA(1);

                         
                        int index35_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 21;}

                        else if ( (synpred45_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_12);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA35_13 = input.LA(1);

                         
                        int index35_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 21;}

                        else if ( (synpred45_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_13);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA35_14 = input.LA(1);

                         
                        int index35_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 21;}

                        else if ( (synpred45_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_14);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA35_15 = input.LA(1);

                         
                        int index35_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 21;}

                        else if ( (synpred45_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_15);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA35_16 = input.LA(1);

                         
                        int index35_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 21;}

                        else if ( (synpred45_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_16);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA35_17 = input.LA(1);

                         
                        int index35_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 21;}

                        else if ( (synpred45_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_17);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA35_18 = input.LA(1);

                         
                        int index35_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 21;}

                        else if ( (synpred45_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_18);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA35_19 = input.LA(1);

                         
                        int index35_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 21;}

                        else if ( (synpred45_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_19);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 35, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA37_eotS =
        "\30\uffff";
    static final String DFA37_eofS =
        "\30\uffff";
    static final String DFA37_minS =
        "\1\6\1\uffff\7\0\1\uffff\12\0\4\uffff";
    static final String DFA37_maxS =
        "\1\120\1\uffff\7\0\1\uffff\12\0\4\uffff";
    static final String DFA37_acceptS =
        "\1\uffff\1\6\7\uffff\1\1\12\uffff\1\2\1\3\1\4\1\5";
    static final String DFA37_specialS =
        "\2\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\7\1\10\1\11\1\12"+
        "\1\13\1\14\1\15\1\16\1\17\1\20\4\uffff}>";
    static final String[] DFA37_transitionS = {
            "\1\11\4\uffff\1\23\24\uffff\1\3\1\4\1\5\1\7\1\6\1\10\1\13\1"+
            "\14\1\15\1\16\1\17\1\20\1\21\1\22\1\uffff\1\1\35\uffff\1\2\2"+
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
            "",
            ""
    };

    static final short[] DFA37_eot = DFA.unpackEncodedString(DFA37_eotS);
    static final short[] DFA37_eof = DFA.unpackEncodedString(DFA37_eofS);
    static final char[] DFA37_min = DFA.unpackEncodedStringToUnsignedChars(DFA37_minS);
    static final char[] DFA37_max = DFA.unpackEncodedStringToUnsignedChars(DFA37_maxS);
    static final short[] DFA37_accept = DFA.unpackEncodedString(DFA37_acceptS);
    static final short[] DFA37_special = DFA.unpackEncodedString(DFA37_specialS);
    static final short[][] DFA37_transition;

    static {
        int numStates = DFA37_transitionS.length;
        DFA37_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA37_transition[i] = DFA.unpackEncodedString(DFA37_transitionS[i]);
        }
    }

    class DFA37 extends DFA {

        public DFA37(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 37;
            this.eot = DFA37_eot;
            this.eof = DFA37_eof;
            this.min = DFA37_min;
            this.max = DFA37_max;
            this.accept = DFA37_accept;
            this.special = DFA37_special;
            this.transition = DFA37_transition;
        }
        public String getDescription() {
            return "()* loopback of 90:16: ( class_def | constructor_def | method_def | field_def | enum_content )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA37_2 = input.LA(1);

                         
                        int index37_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred47_JavaParser()) ) {s = 9;}

                        else if ( (synpred48_JavaParser()) ) {s = 20;}

                        else if ( (synpred49_JavaParser()) ) {s = 21;}

                        else if ( (synpred50_JavaParser()) ) {s = 22;}

                         
                        input.seek(index37_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA37_3 = input.LA(1);

                         
                        int index37_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred47_JavaParser()) ) {s = 9;}

                        else if ( (synpred48_JavaParser()) ) {s = 20;}

                        else if ( (synpred49_JavaParser()) ) {s = 21;}

                        else if ( (synpred50_JavaParser()) ) {s = 22;}

                         
                        input.seek(index37_3);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA37_4 = input.LA(1);

                         
                        int index37_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred47_JavaParser()) ) {s = 9;}

                        else if ( (synpred48_JavaParser()) ) {s = 20;}

                        else if ( (synpred49_JavaParser()) ) {s = 21;}

                        else if ( (synpred50_JavaParser()) ) {s = 22;}

                         
                        input.seek(index37_4);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA37_5 = input.LA(1);

                         
                        int index37_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred47_JavaParser()) ) {s = 9;}

                        else if ( (synpred48_JavaParser()) ) {s = 20;}

                        else if ( (synpred49_JavaParser()) ) {s = 21;}

                        else if ( (synpred50_JavaParser()) ) {s = 22;}

                         
                        input.seek(index37_5);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA37_6 = input.LA(1);

                         
                        int index37_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred47_JavaParser()) ) {s = 9;}

                        else if ( (synpred48_JavaParser()) ) {s = 20;}

                        else if ( (synpred49_JavaParser()) ) {s = 21;}

                        else if ( (synpred50_JavaParser()) ) {s = 22;}

                         
                        input.seek(index37_6);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA37_7 = input.LA(1);

                         
                        int index37_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred47_JavaParser()) ) {s = 9;}

                        else if ( (synpred48_JavaParser()) ) {s = 20;}

                        else if ( (synpred49_JavaParser()) ) {s = 21;}

                        else if ( (synpred50_JavaParser()) ) {s = 22;}

                         
                        input.seek(index37_7);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA37_8 = input.LA(1);

                         
                        int index37_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred47_JavaParser()) ) {s = 9;}

                        else if ( (synpred48_JavaParser()) ) {s = 20;}

                        else if ( (synpred49_JavaParser()) ) {s = 21;}

                        else if ( (synpred50_JavaParser()) ) {s = 22;}

                         
                        input.seek(index37_8);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA37_10 = input.LA(1);

                         
                        int index37_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred48_JavaParser()) ) {s = 20;}

                        else if ( (synpred49_JavaParser()) ) {s = 21;}

                        else if ( (synpred50_JavaParser()) ) {s = 22;}

                        else if ( (synpred51_JavaParser()) ) {s = 23;}

                         
                        input.seek(index37_10);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA37_11 = input.LA(1);

                         
                        int index37_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_JavaParser()) ) {s = 21;}

                        else if ( (synpred50_JavaParser()) ) {s = 22;}

                         
                        input.seek(index37_11);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA37_12 = input.LA(1);

                         
                        int index37_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_JavaParser()) ) {s = 21;}

                        else if ( (synpred50_JavaParser()) ) {s = 22;}

                         
                        input.seek(index37_12);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA37_13 = input.LA(1);

                         
                        int index37_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_JavaParser()) ) {s = 21;}

                        else if ( (synpred50_JavaParser()) ) {s = 22;}

                         
                        input.seek(index37_13);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA37_14 = input.LA(1);

                         
                        int index37_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_JavaParser()) ) {s = 21;}

                        else if ( (synpred50_JavaParser()) ) {s = 22;}

                         
                        input.seek(index37_14);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA37_15 = input.LA(1);

                         
                        int index37_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_JavaParser()) ) {s = 21;}

                        else if ( (synpred50_JavaParser()) ) {s = 22;}

                         
                        input.seek(index37_15);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA37_16 = input.LA(1);

                         
                        int index37_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_JavaParser()) ) {s = 21;}

                        else if ( (synpred50_JavaParser()) ) {s = 22;}

                         
                        input.seek(index37_16);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA37_17 = input.LA(1);

                         
                        int index37_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_JavaParser()) ) {s = 21;}

                        else if ( (synpred50_JavaParser()) ) {s = 22;}

                         
                        input.seek(index37_17);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA37_18 = input.LA(1);

                         
                        int index37_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_JavaParser()) ) {s = 21;}

                        else if ( (synpred50_JavaParser()) ) {s = 22;}

                         
                        input.seek(index37_18);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA37_19 = input.LA(1);

                         
                        int index37_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_JavaParser()) ) {s = 21;}

                        else if ( (synpred50_JavaParser()) ) {s = 22;}

                         
                        input.seek(index37_19);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 37, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA62_eotS =
        "\52\uffff";
    static final String DFA62_eofS =
        "\1\2\51\uffff";
    static final String DFA62_minS =
        "\1\14\1\0\50\uffff";
    static final String DFA62_maxS =
        "\1\135\1\0\50\uffff";
    static final String DFA62_acceptS =
        "\2\uffff\1\1\46\uffff\1\2";
    static final String DFA62_specialS =
        "\1\uffff\1\0\50\uffff}>";
    static final String[] DFA62_transitionS = {
            "\1\2\3\uffff\3\2\33\uffff\6\2\3\uffff\14\2\1\uffff\3\2\1\uffff"+
            "\1\2\1\1\3\2\2\uffff\3\2\1\uffff\3\2\1\uffff\1\2\4\uffff\2\2",
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
            return "115:1: value : ( ( cast )? ( left_unary )? single_value ( right_unary )? ( binary_operator value )? ( question_ value colon value )? | open_bracket value close_bracket ( binary_operator value )? );";
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
                        if ( (synpred76_JavaParser()) ) {s = 2;}

                        else if ( (true) ) {s = 41;}

                         
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
    static final String DFA63_eotS =
        "\14\uffff";
    static final String DFA63_eofS =
        "\1\1\1\uffff\1\7\1\11\1\12\7\uffff";
    static final String DFA63_minS =
        "\1\14\1\uffff\3\57\1\uffff\1\6\5\uffff";
    static final String DFA63_maxS =
        "\1\135\1\uffff\3\114\1\uffff\1\120\5\uffff";
    static final String DFA63_acceptS =
        "\1\uffff\1\1\3\uffff\1\5\1\uffff\1\3\1\4\1\7\1\6\1\2";
    static final String DFA63_specialS =
        "\14\uffff}>";
    static final String[] DFA63_transitionS = {
            "\1\5\3\uffff\1\1\1\4\1\3\33\uffff\6\1\3\uffff\14\1\1\uffff\3"+
            "\1\1\uffff\1\1\1\uffff\3\1\2\uffff\1\1\1\2\1\1\1\uffff\3\1\1"+
            "\uffff\1\1\4\uffff\2\1",
            "",
            "\5\7\3\uffff\14\7\1\6\5\7\1\10\3\7",
            "\5\11\3\uffff\14\11\1\uffff\3\11\1\uffff\1\11\1\10\3\11",
            "\5\12\3\uffff\14\12\1\uffff\3\12\1\uffff\1\12\1\10\3\12",
            "",
            "\1\13\111\uffff\1\2",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA63_eot = DFA.unpackEncodedString(DFA63_eotS);
    static final short[] DFA63_eof = DFA.unpackEncodedString(DFA63_eofS);
    static final char[] DFA63_min = DFA.unpackEncodedStringToUnsignedChars(DFA63_minS);
    static final char[] DFA63_max = DFA.unpackEncodedStringToUnsignedChars(DFA63_maxS);
    static final short[] DFA63_accept = DFA.unpackEncodedString(DFA63_acceptS);
    static final short[] DFA63_special = DFA.unpackEncodedString(DFA63_specialS);
    static final short[][] DFA63_transition;

    static {
        int numStates = DFA63_transitionS.length;
        DFA63_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA63_transition[i] = DFA.unpackEncodedString(DFA63_transitionS[i]);
        }
    }

    class DFA63 extends DFA {

        public DFA63(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 63;
            this.eot = DFA63_eot;
            this.eof = DFA63_eof;
            this.min = DFA63_min;
            this.max = DFA63_max;
            this.accept = DFA63_accept;
            this.special = DFA63_special;
            this.transition = DFA63_transition;
        }
        public String getDescription() {
            return "119:1: single_value : ( constant | class_name dot class_ | variable_name | method_call | new_class | this_ | super_ );";
        }
    }
    static final String DFA65_eotS =
        "\13\uffff";
    static final String DFA65_eofS =
        "\2\2\1\uffff\1\2\1\uffff\1\2\1\uffff\2\2\1\uffff\1\2";
    static final String DFA65_minS =
        "\1\57\1\14\1\uffff\1\57\1\0\1\13\1\uffff\1\57\1\13\1\0\1\57";
    static final String DFA65_maxS =
        "\1\114\1\135\1\uffff\1\114\1\0\1\135\1\uffff\1\120\1\135\1\0\1\120";
    static final String DFA65_acceptS =
        "\2\uffff\1\2\3\uffff\1\1\4\uffff";
    static final String DFA65_specialS =
        "\4\uffff\1\0\4\uffff\1\1\1\uffff}>";
    static final String[] DFA65_transitionS = {
            "\5\2\3\uffff\14\2\1\uffff\1\2\1\1\7\2",
            "\1\2\3\uffff\3\2\33\uffff\6\2\3\uffff\14\2\1\uffff\3\2\1\uffff"+
            "\5\2\2\uffff\1\2\1\3\1\2\1\uffff\3\2\1\uffff\1\2\4\uffff\2\2",
            "",
            "\5\2\3\uffff\15\2\1\5\1\2\1\4\6\2",
            "\1\uffff",
            "\14\2\1\uffff\1\2\2\uffff\1\2\2\uffff\1\2\7\uffff\16\2\3\uffff"+
            "\14\2\1\uffff\3\2\2\uffff\2\2\2\uffff\1\2\1\uffff\1\2\1\7\1"+
            "\2\1\uffff\3\2\1\uffff\1\2\4\uffff\2\2",
            "",
            "\25\2\1\10\1\2\1\11\1\2\1\uffff\3\2\4\uffff\1\2",
            "\14\2\1\uffff\1\2\2\uffff\1\2\2\uffff\1\2\7\uffff\16\2\3\uffff"+
            "\14\2\1\uffff\3\2\2\uffff\2\2\2\uffff\1\2\1\uffff\1\2\1\12\1"+
            "\2\1\uffff\3\2\1\uffff\1\2\4\uffff\2\2",
            "\1\uffff",
            "\25\2\1\10\1\2\1\11\1\2\1\uffff\3\2\4\uffff\1\2"
    };

    static final short[] DFA65_eot = DFA.unpackEncodedString(DFA65_eotS);
    static final short[] DFA65_eof = DFA.unpackEncodedString(DFA65_eofS);
    static final char[] DFA65_min = DFA.unpackEncodedStringToUnsignedChars(DFA65_minS);
    static final char[] DFA65_max = DFA.unpackEncodedStringToUnsignedChars(DFA65_maxS);
    static final short[] DFA65_accept = DFA.unpackEncodedString(DFA65_acceptS);
    static final short[] DFA65_special = DFA.unpackEncodedString(DFA65_specialS);
    static final short[][] DFA65_transition;

    static {
        int numStates = DFA65_transitionS.length;
        DFA65_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA65_transition[i] = DFA.unpackEncodedString(DFA65_transitionS[i]);
        }
    }

    class DFA65 extends DFA {

        public DFA65(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 65;
            this.eot = DFA65_eot;
            this.eof = DFA65_eof;
            this.min = DFA65_min;
            this.max = DFA65_max;
            this.accept = DFA65_accept;
            this.special = DFA65_special;
            this.transition = DFA65_transition;
        }
        public String getDescription() {
            return "142:21: ( generic )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA65_4 = input.LA(1);

                         
                        int index65_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 6;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index65_4);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA65_9 = input.LA(1);

                         
                        int index65_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred93_JavaParser()) ) {s = 6;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index65_9);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 65, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA76_eotS =
        "\41\uffff";
    static final String DFA76_eofS =
        "\41\uffff";
    static final String DFA76_minS =
        "\1\13\3\0\35\uffff";
    static final String DFA76_maxS =
        "\1\120\3\0\35\uffff";
    static final String DFA76_acceptS =
        "\4\uffff\1\3\11\uffff\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
        "\1\16\1\17\1\20\4\uffff\1\1\1\2\1\4";
    static final String DFA76_specialS =
        "\1\uffff\1\0\1\1\1\2\35\uffff}>";
    static final String[] DFA76_transitionS = {
            "\1\4\1\uffff\1\17\1\21\1\20\1\uffff\1\2\1\3\1\22\1\24\1\23\1"+
            "\26\1\uffff\1\25\2\uffff\1\27\2\uffff\1\30\7\uffff\10\4\11\uffff"+
            "\4\31\7\uffff\1\31\11\uffff\1\16\1\4\2\uffff\1\1",
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
            return "153:1: statement : ( label | variable_assignment semicolon | variable_def semicolon | method_call semicolon | semicolon | return_statement | continue_ | break_ | for_loop | while_loop | do_loop semicolon | switch_case | if_else | try_catch | throw_ value semicolon | ( left_unary )? variable_name ( right_unary )? );";
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
                        if ( (synpred102_JavaParser()) ) {s = 30;}

                        else if ( (synpred103_JavaParser()) ) {s = 31;}

                        else if ( (synpred104_JavaParser()) ) {s = 4;}

                        else if ( (synpred105_JavaParser()) ) {s = 32;}

                        else if ( (true) ) {s = 25;}

                         
                        input.seek(index76_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA76_2 = input.LA(1);

                         
                        int index76_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_JavaParser()) ) {s = 31;}

                        else if ( (synpred105_JavaParser()) ) {s = 32;}

                         
                        input.seek(index76_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA76_3 = input.LA(1);

                         
                        int index76_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_JavaParser()) ) {s = 31;}

                        else if ( (synpred105_JavaParser()) ) {s = 32;}

                         
                        input.seek(index76_3);
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
    static final String DFA79_eotS =
        "\40\uffff";
    static final String DFA79_eofS =
        "\40\uffff";
    static final String DFA79_minS =
        "\1\13\3\0\34\uffff";
    static final String DFA79_maxS =
        "\1\120\3\0\34\uffff";
    static final String DFA79_acceptS =
        "\4\uffff\1\3\11\uffff\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
        "\1\16\1\17\4\uffff\1\1\1\2\1\4";
    static final String DFA79_specialS =
        "\1\uffff\1\0\1\1\1\2\34\uffff}>";
    static final String[] DFA79_transitionS = {
            "\1\4\1\uffff\1\16\1\20\1\17\1\uffff\1\2\1\3\1\21\1\23\1\22\1"+
            "\25\1\uffff\1\24\2\uffff\1\26\2\uffff\1\27\7\uffff\10\4\11\uffff"+
            "\4\30\7\uffff\1\30\12\uffff\1\4\2\uffff\1\1",
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
            ""
    };

    static final short[] DFA79_eot = DFA.unpackEncodedString(DFA79_eotS);
    static final short[] DFA79_eof = DFA.unpackEncodedString(DFA79_eofS);
    static final char[] DFA79_min = DFA.unpackEncodedStringToUnsignedChars(DFA79_minS);
    static final char[] DFA79_max = DFA.unpackEncodedStringToUnsignedChars(DFA79_maxS);
    static final short[] DFA79_accept = DFA.unpackEncodedString(DFA79_acceptS);
    static final short[] DFA79_special = DFA.unpackEncodedString(DFA79_specialS);
    static final short[][] DFA79_transition;

    static {
        int numStates = DFA79_transitionS.length;
        DFA79_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA79_transition[i] = DFA.unpackEncodedString(DFA79_transitionS[i]);
        }
    }

    class DFA79 extends DFA {

        public DFA79(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 79;
            this.eot = DFA79_eot;
            this.eof = DFA79_eof;
            this.min = DFA79_min;
            this.max = DFA79_max;
            this.accept = DFA79_accept;
            this.special = DFA79_special;
            this.transition = DFA79_transition;
        }
        public String getDescription() {
            return "172:1: statement_wosemicolon : ( label | variable_assignment | variable_def | method_call | return_statement | continue_ | break_ | for_loop | while_loop | do_loop | switch_case | if_else | try_catch | throw_ value semicolon | ( left_unary )? variable_name ( right_unary )? );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA79_1 = input.LA(1);

                         
                        int index79_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred119_JavaParser()) ) {s = 29;}

                        else if ( (synpred120_JavaParser()) ) {s = 30;}

                        else if ( (synpred121_JavaParser()) ) {s = 4;}

                        else if ( (synpred122_JavaParser()) ) {s = 31;}

                        else if ( (true) ) {s = 24;}

                         
                        input.seek(index79_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA79_2 = input.LA(1);

                         
                        int index79_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred120_JavaParser()) ) {s = 30;}

                        else if ( (synpred122_JavaParser()) ) {s = 31;}

                         
                        input.seek(index79_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA79_3 = input.LA(1);

                         
                        int index79_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred120_JavaParser()) ) {s = 30;}

                        else if ( (synpred122_JavaParser()) ) {s = 31;}

                         
                        input.seek(index79_3);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 79, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA90_eotS =
        "\43\uffff";
    static final String DFA90_eofS =
        "\43\uffff";
    static final String DFA90_minS =
        "\1\13\1\0\3\uffff\34\0\2\uffff";
    static final String DFA90_maxS =
        "\1\120\1\0\3\uffff\34\0\2\uffff";
    static final String DFA90_acceptS =
        "\2\uffff\1\3\36\uffff\1\1\1\2";
    static final String DFA90_specialS =
        "\1\uffff\1\0\3\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12"+
        "\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27"+
        "\1\30\1\31\1\32\1\33\1\34\2\uffff}>";
    static final String[] DFA90_transitionS = {
            "\1\21\1\uffff\1\23\1\1\1\24\1\uffff\1\6\1\7\1\25\1\27\1\26\1"+
            "\31\1\uffff\1\30\2\2\1\32\2\uffff\1\33\7\uffff\1\11\1\12\1\13"+
            "\1\14\1\15\1\16\1\17\1\20\1\41\1\2\7\uffff\1\34\1\35\1\40\1"+
            "\37\7\uffff\1\36\11\uffff\1\22\1\10\2\uffff\1\5",
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
            return "()* loopback of 212:47: ( code | statement )*";
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
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index90_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA90_5 = input.LA(1);

                         
                        int index90_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_5);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA90_6 = input.LA(1);

                         
                        int index90_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_6);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA90_7 = input.LA(1);

                         
                        int index90_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_7);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA90_8 = input.LA(1);

                         
                        int index90_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_8);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA90_9 = input.LA(1);

                         
                        int index90_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_9);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA90_10 = input.LA(1);

                         
                        int index90_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_10);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA90_11 = input.LA(1);

                         
                        int index90_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_11);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA90_12 = input.LA(1);

                         
                        int index90_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_12);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA90_13 = input.LA(1);

                         
                        int index90_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_13);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA90_14 = input.LA(1);

                         
                        int index90_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_14);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA90_15 = input.LA(1);

                         
                        int index90_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_15);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA90_16 = input.LA(1);

                         
                        int index90_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_16);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA90_17 = input.LA(1);

                         
                        int index90_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_17);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA90_18 = input.LA(1);

                         
                        int index90_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_18);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA90_19 = input.LA(1);

                         
                        int index90_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_19);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA90_20 = input.LA(1);

                         
                        int index90_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_20);
                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA90_21 = input.LA(1);

                         
                        int index90_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_21);
                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA90_22 = input.LA(1);

                         
                        int index90_22 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_22);
                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA90_23 = input.LA(1);

                         
                        int index90_23 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_23);
                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA90_24 = input.LA(1);

                         
                        int index90_24 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_24);
                        if ( s>=0 ) return s;
                        break;
                    case 21 : 
                        int LA90_25 = input.LA(1);

                         
                        int index90_25 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_25);
                        if ( s>=0 ) return s;
                        break;
                    case 22 : 
                        int LA90_26 = input.LA(1);

                         
                        int index90_26 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_26);
                        if ( s>=0 ) return s;
                        break;
                    case 23 : 
                        int LA90_27 = input.LA(1);

                         
                        int index90_27 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_27);
                        if ( s>=0 ) return s;
                        break;
                    case 24 : 
                        int LA90_28 = input.LA(1);

                         
                        int index90_28 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_28);
                        if ( s>=0 ) return s;
                        break;
                    case 25 : 
                        int LA90_29 = input.LA(1);

                         
                        int index90_29 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_29);
                        if ( s>=0 ) return s;
                        break;
                    case 26 : 
                        int LA90_30 = input.LA(1);

                         
                        int index90_30 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_30);
                        if ( s>=0 ) return s;
                        break;
                    case 27 : 
                        int LA90_31 = input.LA(1);

                         
                        int index90_31 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_31);
                        if ( s>=0 ) return s;
                        break;
                    case 28 : 
                        int LA90_32 = input.LA(1);

                         
                        int index90_32 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaParser()) ) {s = 33;}

                        else if ( (synpred147_JavaParser()) ) {s = 34;}

                         
                        input.seek(index90_32);
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
    static final String DFA92_eotS =
        "\43\uffff";
    static final String DFA92_eofS =
        "\43\uffff";
    static final String DFA92_minS =
        "\1\13\3\uffff\35\0\2\uffff";
    static final String DFA92_maxS =
        "\1\120\3\uffff\35\0\2\uffff";
    static final String DFA92_acceptS =
        "\1\uffff\1\3\37\uffff\1\1\1\2";
    static final String DFA92_specialS =
        "\4\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
        "\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
        "\1\32\1\33\1\34\2\uffff}>";
    static final String[] DFA92_transitionS = {
            "\1\20\1\uffff\1\22\1\24\1\23\1\uffff\1\5\1\6\1\25\1\27\1\26"+
            "\1\31\1\uffff\1\30\2\1\1\32\2\uffff\1\33\7\uffff\1\10\1\11\1"+
            "\12\1\13\1\14\1\15\1\16\1\17\1\41\1\1\7\uffff\1\34\1\35\1\40"+
            "\1\37\7\uffff\1\36\11\uffff\1\21\1\7\2\uffff\1\4",
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
            "",
            ""
    };

    static final short[] DFA92_eot = DFA.unpackEncodedString(DFA92_eotS);
    static final short[] DFA92_eof = DFA.unpackEncodedString(DFA92_eofS);
    static final char[] DFA92_min = DFA.unpackEncodedStringToUnsignedChars(DFA92_minS);
    static final char[] DFA92_max = DFA.unpackEncodedStringToUnsignedChars(DFA92_maxS);
    static final short[] DFA92_accept = DFA.unpackEncodedString(DFA92_acceptS);
    static final short[] DFA92_special = DFA.unpackEncodedString(DFA92_specialS);
    static final short[][] DFA92_transition;

    static {
        int numStates = DFA92_transitionS.length;
        DFA92_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA92_transition[i] = DFA.unpackEncodedString(DFA92_transitionS[i]);
        }
    }

    class DFA92 extends DFA {

        public DFA92(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 92;
            this.eot = DFA92_eot;
            this.eof = DFA92_eof;
            this.min = DFA92_min;
            this.max = DFA92_max;
            this.accept = DFA92_accept;
            this.special = DFA92_special;
            this.transition = DFA92_transition;
        }
        public String getDescription() {
            return "()* loopback of 214:19: ( code | statement )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA92_4 = input.LA(1);

                         
                        int index92_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_4);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA92_5 = input.LA(1);

                         
                        int index92_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_5);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA92_6 = input.LA(1);

                         
                        int index92_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_6);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA92_7 = input.LA(1);

                         
                        int index92_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_7);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA92_8 = input.LA(1);

                         
                        int index92_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_8);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA92_9 = input.LA(1);

                         
                        int index92_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_9);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA92_10 = input.LA(1);

                         
                        int index92_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_10);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA92_11 = input.LA(1);

                         
                        int index92_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_11);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA92_12 = input.LA(1);

                         
                        int index92_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_12);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA92_13 = input.LA(1);

                         
                        int index92_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_13);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA92_14 = input.LA(1);

                         
                        int index92_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_14);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA92_15 = input.LA(1);

                         
                        int index92_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_15);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA92_16 = input.LA(1);

                         
                        int index92_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_16);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA92_17 = input.LA(1);

                         
                        int index92_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_17);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA92_18 = input.LA(1);

                         
                        int index92_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_18);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA92_19 = input.LA(1);

                         
                        int index92_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_19);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA92_20 = input.LA(1);

                         
                        int index92_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_20);
                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA92_21 = input.LA(1);

                         
                        int index92_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_21);
                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA92_22 = input.LA(1);

                         
                        int index92_22 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_22);
                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA92_23 = input.LA(1);

                         
                        int index92_23 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_23);
                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA92_24 = input.LA(1);

                         
                        int index92_24 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_24);
                        if ( s>=0 ) return s;
                        break;
                    case 21 : 
                        int LA92_25 = input.LA(1);

                         
                        int index92_25 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_25);
                        if ( s>=0 ) return s;
                        break;
                    case 22 : 
                        int LA92_26 = input.LA(1);

                         
                        int index92_26 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_26);
                        if ( s>=0 ) return s;
                        break;
                    case 23 : 
                        int LA92_27 = input.LA(1);

                         
                        int index92_27 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_27);
                        if ( s>=0 ) return s;
                        break;
                    case 24 : 
                        int LA92_28 = input.LA(1);

                         
                        int index92_28 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_28);
                        if ( s>=0 ) return s;
                        break;
                    case 25 : 
                        int LA92_29 = input.LA(1);

                         
                        int index92_29 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_29);
                        if ( s>=0 ) return s;
                        break;
                    case 26 : 
                        int LA92_30 = input.LA(1);

                         
                        int index92_30 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_30);
                        if ( s>=0 ) return s;
                        break;
                    case 27 : 
                        int LA92_31 = input.LA(1);

                         
                        int index92_31 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_31);
                        if ( s>=0 ) return s;
                        break;
                    case 28 : 
                        int LA92_32 = input.LA(1);

                         
                        int index92_32 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_JavaParser()) ) {s = 33;}

                        else if ( (synpred151_JavaParser()) ) {s = 34;}

                         
                        input.seek(index92_32);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 92, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_package_def_in_file40 = new BitSet(new long[]{0x0000003F000001E0L,0x0000000000002000L});
    public static final BitSet FOLLOW_import_def_in_file42 = new BitSet(new long[]{0x0000003F000001E0L,0x0000000000002000L});
    public static final BitSet FOLLOW_class_def_in_file46 = new BitSet(new long[]{0x0000003F000001E2L,0x0000000000002000L});
    public static final BitSet FOLLOW_enum_def_in_file50 = new BitSet(new long[]{0x0000003F000001E2L,0x0000000000002000L});
    public static final BitSet FOLLOW_interface_def_in_file54 = new BitSet(new long[]{0x0000003F000001E2L,0x0000000000002000L});
    public static final BitSet FOLLOW_package__in_package_def68 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_package_name_in_package_def70 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_semicolon_in_package_def72 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_import__in_import_def84 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_import_name_in_import_def86 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_semicolon_in_import_def88 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_class_def100 = new BitSet(new long[]{0x0000003F00000040L,0x0000000000002000L});
    public static final BitSet FOLLOW_modifier_in_class_def103 = new BitSet(new long[]{0x0000003F00000040L,0x0000000000002000L});
    public static final BitSet FOLLOW_class__in_class_def106 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_id_in_class_def108 = new BitSet(new long[]{0x0000400000000600L,0x0000000000000020L});
    public static final BitSet FOLLOW_generic_in_class_def110 = new BitSet(new long[]{0x0000400000000600L,0x0000000000000020L});
    public static final BitSet FOLLOW_extends__in_class_def114 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_class_name_in_class_def116 = new BitSet(new long[]{0x0000400000000600L,0x0000000000000020L});
    public static final BitSet FOLLOW_generic_in_class_def118 = new BitSet(new long[]{0x0000400000000600L,0x0000000000000020L});
    public static final BitSet FOLLOW_implements__in_class_def124 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_class_name_in_class_def126 = new BitSet(new long[]{0x0000400000000600L,0x0000000000000030L});
    public static final BitSet FOLLOW_comma_in_class_def129 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_class_name_in_class_def131 = new BitSet(new long[]{0x0000400000000600L,0x0000000000000030L});
    public static final BitSet FOLLOW_class_block_in_class_def137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_enum_def153 = new BitSet(new long[]{0x0000003F00000100L,0x0000000000002000L});
    public static final BitSet FOLLOW_modifier_in_enum_def156 = new BitSet(new long[]{0x0000003F00000100L,0x0000000000002000L});
    public static final BitSet FOLLOW_enum__in_enum_def159 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_id_in_enum_def161 = new BitSet(new long[]{0x0000400000000600L,0x0000000000000020L});
    public static final BitSet FOLLOW_generic_in_enum_def163 = new BitSet(new long[]{0x0000400000000600L,0x0000000000000020L});
    public static final BitSet FOLLOW_extends__in_enum_def167 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_class_name_in_enum_def169 = new BitSet(new long[]{0x0000400000000600L,0x0000000000000020L});
    public static final BitSet FOLLOW_generic_in_enum_def171 = new BitSet(new long[]{0x0000400000000600L,0x0000000000000020L});
    public static final BitSet FOLLOW_enum_block_in_enum_def176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_interface_def192 = new BitSet(new long[]{0x0000003F000001E0L,0x0000000000002000L});
    public static final BitSet FOLLOW_modifier_in_interface_def195 = new BitSet(new long[]{0x0000003F000001E0L,0x0000000000002000L});
    public static final BitSet FOLLOW_interface__in_interface_def198 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_id_in_interface_def200 = new BitSet(new long[]{0x0000400000000600L,0x0000000000000020L});
    public static final BitSet FOLLOW_generic_in_interface_def202 = new BitSet(new long[]{0x0000400000000600L,0x0000000000000020L});
    public static final BitSet FOLLOW_extends__in_interface_def206 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_class_name_in_interface_def208 = new BitSet(new long[]{0x0000400000000600L,0x0000000000000020L});
    public static final BitSet FOLLOW_generic_in_interface_def210 = new BitSet(new long[]{0x0000400000000600L,0x0000000000000020L});
    public static final BitSet FOLLOW_interface_block_in_interface_def215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_static__in_static_init231 = new BitSet(new long[]{0x07807FC0497EEE00L,0x0000000000013024L});
    public static final BitSet FOLLOW_code_in_static_init233 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L});
    public static final BitSet FOLLOW_semicolon_in_static_init235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_constructor_def247 = new BitSet(new long[]{0x0000003F00000000L,0x0000000000012000L});
    public static final BitSet FOLLOW_modifier_in_constructor_def250 = new BitSet(new long[]{0x0000003F00000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_id_in_constructor_def253 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_open_bracket_in_constructor_def255 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000012400L});
    public static final BitSet FOLLOW_argument_def_in_constructor_def257 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000012400L});
    public static final BitSet FOLLOW_close_bracket_in_constructor_def259 = new BitSet(new long[]{0x07807FC0497EEE00L,0x0000000000013024L});
    public static final BitSet FOLLOW_code_in_constructor_def261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_method_def276 = new BitSet(new long[]{0x00003FFF00000800L,0x0000000000012000L});
    public static final BitSet FOLLOW_modifier_in_method_def279 = new BitSet(new long[]{0x00003FFF00000800L,0x0000000000012000L});
    public static final BitSet FOLLOW_variable_type_in_method_def282 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_id_in_method_def284 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_open_bracket_in_method_def286 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000012400L});
    public static final BitSet FOLLOW_argument_def_in_method_def288 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000012400L});
    public static final BitSet FOLLOW_close_bracket_in_method_def290 = new BitSet(new long[]{0x07807FC0C97EEE00L,0x0000000000013024L});
    public static final BitSet FOLLOW_throws__in_method_def293 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_class_name_in_method_def295 = new BitSet(new long[]{0x07807FC0497EEE00L,0x0000000000013034L});
    public static final BitSet FOLLOW_comma_in_method_def298 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_class_name_in_method_def300 = new BitSet(new long[]{0x07807FC0497EEE00L,0x0000000000013034L});
    public static final BitSet FOLLOW_code_in_method_def306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_field_def322 = new BitSet(new long[]{0x00003FFF00000800L,0x0000000000012000L});
    public static final BitSet FOLLOW_modifier_in_field_def325 = new BitSet(new long[]{0x00003FFF00000800L,0x0000000000012000L});
    public static final BitSet FOLLOW_variable_type_in_field_def328 = new BitSet(new long[]{0x0000000000060000L,0x0000000000010000L});
    public static final BitSet FOLLOW_variable_name_in_field_def330 = new BitSet(new long[]{0x0070000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_assign_in_field_def333 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_field_def335 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_semicolon_in_field_def339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_type_in_argument_def351 = new BitSet(new long[]{0x0000000000060000L,0x0000000000010000L});
    public static final BitSet FOLLOW_variable_name_in_argument_def353 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_comma_in_argument_def356 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000012000L});
    public static final BitSet FOLLOW_variable_type_in_argument_def358 = new BitSet(new long[]{0x0000000000060000L,0x0000000000010000L});
    public static final BitSet FOLLOW_variable_name_in_argument_def360 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_annotation_in_variable_def375 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000012000L});
    public static final BitSet FOLLOW_variable_type_in_variable_def378 = new BitSet(new long[]{0x0000000000060000L,0x0000000000010000L});
    public static final BitSet FOLLOW_variable_name_in_variable_def380 = new BitSet(new long[]{0x0070000000000002L});
    public static final BitSet FOLLOW_assign_in_variable_def383 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_variable_def385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_public__in_modifier397 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_private__in_modifier402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_protected__in_modifier407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_static__in_modifier412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_final__in_modifier417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_transient__in_modifier422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_begin_in_class_block434 = new BitSet(new long[]{0x0000BFFF00000840L,0x0000000000012000L});
    public static final BitSet FOLLOW_class_def_in_class_block437 = new BitSet(new long[]{0x0000BFFF00000840L,0x0000000000012000L});
    public static final BitSet FOLLOW_static_init_in_class_block441 = new BitSet(new long[]{0x0000BFFF00000840L,0x0000000000012000L});
    public static final BitSet FOLLOW_constructor_def_in_class_block445 = new BitSet(new long[]{0x0000BFFF00000840L,0x0000000000012000L});
    public static final BitSet FOLLOW_method_def_in_class_block449 = new BitSet(new long[]{0x0000BFFF00000840L,0x0000000000012000L});
    public static final BitSet FOLLOW_field_def_in_class_block453 = new BitSet(new long[]{0x0000BFFF00000840L,0x0000000000012000L});
    public static final BitSet FOLLOW_block_end_in_class_block457 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L});
    public static final BitSet FOLLOW_semicolon_in_class_block459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_begin_in_enum_block472 = new BitSet(new long[]{0x0000BFFF00000840L,0x0000000000012000L});
    public static final BitSet FOLLOW_class_def_in_enum_block475 = new BitSet(new long[]{0x0000BFFF00000840L,0x0000000000012000L});
    public static final BitSet FOLLOW_constructor_def_in_enum_block479 = new BitSet(new long[]{0x0000BFFF00000840L,0x0000000000012000L});
    public static final BitSet FOLLOW_method_def_in_enum_block483 = new BitSet(new long[]{0x0000BFFF00000840L,0x0000000000012000L});
    public static final BitSet FOLLOW_field_def_in_enum_block487 = new BitSet(new long[]{0x0000BFFF00000840L,0x0000000000012000L});
    public static final BitSet FOLLOW_enum_content_in_enum_block491 = new BitSet(new long[]{0x0000BFFF00000840L,0x0000000000012000L});
    public static final BitSet FOLLOW_block_end_in_enum_block495 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L});
    public static final BitSet FOLLOW_semicolon_in_enum_block497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_enum_content509 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001210L});
    public static final BitSet FOLLOW_open_bracket_in_enum_content512 = new BitSet(new long[]{0x07807FC000071E00L,0x0000000030BBA624L});
    public static final BitSet FOLLOW_arguments_in_enum_content514 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000012400L});
    public static final BitSet FOLLOW_close_bracket_in_enum_content516 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001010L});
    public static final BitSet FOLLOW_comma_in_enum_content521 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_id_in_enum_content523 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001210L});
    public static final BitSet FOLLOW_open_bracket_in_enum_content526 = new BitSet(new long[]{0x07807FC000071E00L,0x0000000030BBA624L});
    public static final BitSet FOLLOW_arguments_in_enum_content528 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000012400L});
    public static final BitSet FOLLOW_close_bracket_in_enum_content530 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001010L});
    public static final BitSet FOLLOW_semicolon_in_enum_content536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_begin_in_interface_block548 = new BitSet(new long[]{0x0000BFFF00000840L,0x0000000000012000L});
    public static final BitSet FOLLOW_annotation_in_interface_block551 = new BitSet(new long[]{0x00003FFF00000800L,0x0000000000012000L});
    public static final BitSet FOLLOW_modifier_in_interface_block554 = new BitSet(new long[]{0x00003FFF00000800L,0x0000000000012000L});
    public static final BitSet FOLLOW_variable_type_in_interface_block557 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_id_in_interface_block559 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_open_bracket_in_interface_block561 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000012400L});
    public static final BitSet FOLLOW_argument_def_in_interface_block563 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000012400L});
    public static final BitSet FOLLOW_close_bracket_in_interface_block565 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_semicolon_in_interface_block567 = new BitSet(new long[]{0x0000BFFF00000840L,0x0000000000012000L});
    public static final BitSet FOLLOW_block_end_in_interface_block571 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L});
    public static final BitSet FOLLOW_semicolon_in_interface_block573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_name_in_method_call586 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_open_bracket_in_method_call588 = new BitSet(new long[]{0x07807FC000071E00L,0x0000000030BBA624L});
    public static final BitSet FOLLOW_arguments_in_method_call590 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000012400L});
    public static final BitSet FOLLOW_close_bracket_in_method_call592 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_dot_in_method_call595 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_id_in_method_call597 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000208L});
    public static final BitSet FOLLOW_open_bracket_in_method_call600 = new BitSet(new long[]{0x07807FC000071E00L,0x0000000030BBA624L});
    public static final BitSet FOLLOW_arguments_in_method_call602 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000012400L});
    public static final BitSet FOLLOW_close_bracket_in_method_call604 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_super__in_method_call613 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_open_bracket_in_method_call615 = new BitSet(new long[]{0x07807FC000071E00L,0x0000000030BBA624L});
    public static final BitSet FOLLOW_arguments_in_method_call617 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000012400L});
    public static final BitSet FOLLOW_close_bracket_in_method_call619 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_dot_in_method_call622 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_id_in_method_call624 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000208L});
    public static final BitSet FOLLOW_open_bracket_in_method_call627 = new BitSet(new long[]{0x07807FC000071E00L,0x0000000030BBA624L});
    public static final BitSet FOLLOW_arguments_in_method_call629 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000012400L});
    public static final BitSet FOLLOW_close_bracket_in_method_call631 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_this__in_method_call640 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_open_bracket_in_method_call642 = new BitSet(new long[]{0x07807FC000071E00L,0x0000000030BBA624L});
    public static final BitSet FOLLOW_arguments_in_method_call644 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000012400L});
    public static final BitSet FOLLOW_close_bracket_in_method_call646 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_dot_in_method_call649 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_id_in_method_call651 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000208L});
    public static final BitSet FOLLOW_open_bracket_in_method_call654 = new BitSet(new long[]{0x07807FC000071E00L,0x0000000030BBA624L});
    public static final BitSet FOLLOW_arguments_in_method_call656 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000012400L});
    public static final BitSet FOLLOW_close_bracket_in_method_call658 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_annotation_name_in_annotation673 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_open_bracket_in_annotation676 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_annotation678 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000012410L});
    public static final BitSet FOLLOW_comma_in_annotation681 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_annotation683 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000012410L});
    public static final BitSet FOLLOW_close_bracket_in_annotation687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_generic699 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_id_in_generic702 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000050L});
    public static final BitSet FOLLOW_comma_in_generic705 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_id_in_generic707 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000050L});
    public static final BitSet FOLLOW_GT_in_generic712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cast_in_value725 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_left_unary_in_value728 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_single_value_in_value731 = new BitSet(new long[]{0xFF8F000000000002L,0x0000000000000067L});
    public static final BitSet FOLLOW_right_unary_in_value733 = new BitSet(new long[]{0xFE0F000000000002L,0x0000000000000063L});
    public static final BitSet FOLLOW_binary_operator_in_value737 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_value739 = new BitSet(new long[]{0x2000000000000002L});
    public static final BitSet FOLLOW_question__in_value744 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_value746 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_colon_in_value748 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_value750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_open_bracket_in_value757 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_value759 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000012400L});
    public static final BitSet FOLLOW_close_bracket_in_value761 = new BitSet(new long[]{0xDE0F000000000002L,0x0000000000000063L});
    public static final BitSet FOLLOW_binary_operator_in_value764 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_value766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constant_in_single_value779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_name_in_single_value784 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_dot_in_single_value786 = new BitSet(new long[]{0x0000003F00000040L,0x0000000000002000L});
    public static final BitSet FOLLOW_class__in_single_value788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_name_in_single_value793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_single_value798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_new_class_in_single_value803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_this__in_single_value808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_super__in_single_value813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_hex_long_const_in_constant822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_hex_const_in_constant827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_long_const_in_constant832 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_int_const_in_constant837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_const_in_constant842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_float_const_in_constant847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_char_const_in_constant852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_null_const_in_constant857 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_boolean_const_in_constant862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_array_const_in_constant867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_new__in_new_class879 = new BitSet(new long[]{0x00003FC000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_class_name_in_new_class882 = new BitSet(new long[]{0x0000000000000002L,0x00000000000002A0L});
    public static final BitSet FOLLOW_generic_in_new_class884 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000280L});
    public static final BitSet FOLLOW_primitive_in_new_class889 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000280L});
    public static final BitSet FOLLOW_array_in_new_class893 = new BitSet(new long[]{0x0000400000000600L,0x0000000000000220L});
    public static final BitSet FOLLOW_array_const_in_new_class895 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_open_bracket_in_new_class900 = new BitSet(new long[]{0x07807FC000071E00L,0x0000000030BBA624L});
    public static final BitSet FOLLOW_arguments_in_new_class902 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000012400L});
    public static final BitSet FOLLOW_close_bracket_in_new_class904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_value_in_arguments920 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_comma_in_arguments923 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_arguments925 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_statement_in_code939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_begin_in_code944 = new BitSet(new long[]{0x0780FFFF497EEE40L,0x0000000000013024L});
    public static final BitSet FOLLOW_code_in_code946 = new BitSet(new long[]{0x0780FFFF497EEE40L,0x0000000000013024L});
    public static final BitSet FOLLOW_block_end_in_code949 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L});
    public static final BitSet FOLLOW_semicolon_in_code951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_label_in_statement964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_statement970 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_semicolon_in_statement972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_statement977 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_semicolon_in_statement979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_statement984 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_semicolon_in_statement986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_semicolon_in_statement992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_statement_in_statement997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_continue__in_statement1002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_break__in_statement1007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for_loop_in_statement1012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while_loop_in_statement1017 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_do_loop_in_statement1022 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_semicolon_in_statement1024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_switch_case_in_statement1029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_else_in_statement1034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_try_catch_in_statement1039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_throw__in_statement1044 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_statement1046 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_semicolon_in_statement1048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_left_unary_in_statement1053 = new BitSet(new long[]{0x0000000000060000L,0x0000000000010000L});
    public static final BitSet FOLLOW_variable_name_in_statement1056 = new BitSet(new long[]{0x0180000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_right_unary_in_statement1058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_label_in_statement_wosemicolon1070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_statement_wosemicolon1075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_statement_wosemicolon1080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_statement_wosemicolon1085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_statement_in_statement_wosemicolon1091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_continue__in_statement_wosemicolon1096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_break__in_statement_wosemicolon1101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for_loop_in_statement_wosemicolon1106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while_loop_in_statement_wosemicolon1111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_do_loop_in_statement_wosemicolon1116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_switch_case_in_statement_wosemicolon1121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_else_in_statement_wosemicolon1126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_try_catch_in_statement_wosemicolon1131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_throw__in_statement_wosemicolon1136 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_statement_wosemicolon1138 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_semicolon_in_statement_wosemicolon1140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_left_unary_in_statement_wosemicolon1145 = new BitSet(new long[]{0x0000000000060000L,0x0000000000010000L});
    public static final BitSet FOLLOW_variable_name_in_statement_wosemicolon1148 = new BitSet(new long[]{0x0180000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_right_unary_in_statement_wosemicolon1150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return__in_return_statement1163 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB9224L});
    public static final BitSet FOLLOW_value_in_return_statement1165 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_semicolon_in_return_statement1168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_this__in_variable_assignment1181 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_dot_in_variable_assignment1183 = new BitSet(new long[]{0x0000000000060000L,0x0000000000010000L});
    public static final BitSet FOLLOW_super__in_variable_assignment1187 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_dot_in_variable_assignment1189 = new BitSet(new long[]{0x0000000000060000L,0x0000000000010000L});
    public static final BitSet FOLLOW_variable_name_in_variable_assignment1193 = new BitSet(new long[]{0x0070000000000000L});
    public static final BitSet FOLLOW_assign_in_variable_assignment1195 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_variable_assignment1197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for__in_for_loop1207 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_for_loop1209 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000013000L});
    public static final BitSet FOLLOW_variable_def_in_for_loop1212 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001010L});
    public static final BitSet FOLLOW_comma_in_for_loop1215 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000012000L});
    public static final BitSet FOLLOW_variable_def_in_for_loop1217 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001010L});
    public static final BitSet FOLLOW_semicolon_in_for_loop1223 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB9224L});
    public static final BitSet FOLLOW_value_in_for_loop1225 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_semicolon_in_for_loop1228 = new BitSet(new long[]{0x07803FC0497EE800L,0x0000000000012404L});
    public static final BitSet FOLLOW_statement_wosemicolon_in_for_loop1231 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000410L});
    public static final BitSet FOLLOW_comma_in_for_loop1234 = new BitSet(new long[]{0x07803FC0497EE800L,0x0000000000012004L});
    public static final BitSet FOLLOW_statement_wosemicolon_in_for_loop1236 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000410L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_for_loop1242 = new BitSet(new long[]{0x07807FC0497EEE00L,0x0000000000013024L});
    public static final BitSet FOLLOW_code_in_for_loop1244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for__in_for_loop1249 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_for_loop1251 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000012000L});
    public static final BitSet FOLLOW_variable_type_in_for_loop1253 = new BitSet(new long[]{0x0000000000060000L,0x0000000000010000L});
    public static final BitSet FOLLOW_variable_name_in_for_loop1255 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_colon_in_for_loop1257 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_for_loop1259 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_for_loop1261 = new BitSet(new long[]{0x07807FC0497EEE00L,0x0000000000013024L});
    public static final BitSet FOLLOW_code_in_for_loop1263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while__in_while_loop1275 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_while_loop1277 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_while_loop1279 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_while_loop1281 = new BitSet(new long[]{0x07807FC0497EEE00L,0x0000000000013024L});
    public static final BitSet FOLLOW_code_in_while_loop1283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_do__in_do_loop1294 = new BitSet(new long[]{0x07807FC0497EEE00L,0x0000000000013024L});
    public static final BitSet FOLLOW_code_in_do_loop1296 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_while__in_do_loop1298 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_do_loop1300 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_do_loop1302 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_do_loop1304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_switch__in_switch_case1315 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_switch_case1317 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_switch_case1319 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_switch_case1321 = new BitSet(new long[]{0x0000400000000600L,0x0000000000000020L});
    public static final BitSet FOLLOW_block_begin_in_switch_case1323 = new BitSet(new long[]{0x0000000006000000L});
    public static final BitSet FOLLOW_case__in_switch_case1334 = new BitSet(new long[]{0x0000400000070600L,0x0000000030BB8020L});
    public static final BitSet FOLLOW_constant_in_switch_case1338 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_variable_name_in_switch_case1342 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_colon_in_switch_case1345 = new BitSet(new long[]{0x0780FFFF4F7EEE40L,0x0000000000013024L});
    public static final BitSet FOLLOW_code_in_switch_case1350 = new BitSet(new long[]{0x0780FFFF4F7EEE40L,0x0000000000013024L});
    public static final BitSet FOLLOW_statement_in_switch_case1354 = new BitSet(new long[]{0x0780FFFF4F7EEE40L,0x0000000000013024L});
    public static final BitSet FOLLOW_break__in_switch_case1359 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_semicolon_in_switch_case1361 = new BitSet(new long[]{0x0000BFFF06000840L,0x0000000000012000L});
    public static final BitSet FOLLOW_default__in_switch_case1372 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_colon_in_switch_case1374 = new BitSet(new long[]{0x0780FFFF4F7EEE40L,0x0000000000013024L});
    public static final BitSet FOLLOW_code_in_switch_case1377 = new BitSet(new long[]{0x0780FFFF4F7EEE40L,0x0000000000013024L});
    public static final BitSet FOLLOW_statement_in_switch_case1381 = new BitSet(new long[]{0x0780FFFF4F7EEE40L,0x0000000000013024L});
    public static final BitSet FOLLOW_block_end_in_switch_case1392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if__in_if_else1403 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_if_else1405 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_if_else1407 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_if_else1409 = new BitSet(new long[]{0x07807FC0497EEE00L,0x0000000000013024L});
    public static final BitSet FOLLOW_code_in_if_else1411 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_else__in_if_else1414 = new BitSet(new long[]{0x07807FC0497EEE00L,0x0000000000013024L});
    public static final BitSet FOLLOW_code_in_if_else1416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_try__in_try_catch1429 = new BitSet(new long[]{0x07807FC0497EEE00L,0x0000000000013024L});
    public static final BitSet FOLLOW_code_in_try_catch1431 = new BitSet(new long[]{0x0000000030000002L});
    public static final BitSet FOLLOW_catch__in_try_catch1434 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_try_catch1436 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_id_in_try_catch1438 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_id_in_try_catch1440 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_try_catch1442 = new BitSet(new long[]{0x07807FC0497EEE00L,0x0000000000013024L});
    public static final BitSet FOLLOW_code_in_try_catch1444 = new BitSet(new long[]{0x0000000030000002L});
    public static final BitSet FOLLOW_finally__in_try_catch1449 = new BitSet(new long[]{0x07807FC0497EEE00L,0x0000000000013024L});
    public static final BitSet FOLLOW_code_in_try_catch1451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_label1463 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_colon_in_label1465 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_cast1473 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000012000L});
    public static final BitSet FOLLOW_variable_type_in_cast1475 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_cast1477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primitive_in_variable_type1490 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000080L});
    public static final BitSet FOLLOW_array_in_variable_type1492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_name_in_variable_type1498 = new BitSet(new long[]{0x0000000000000002L,0x00000000000000A0L});
    public static final BitSet FOLLOW_generic_in_variable_type1500 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000080L});
    public static final BitSet FOLLOW_array_in_variable_type1503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_void__in_variable_type1509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_id1519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_plus_in_binary_operator1530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_minus_in_binary_operator1535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_binary_operator1540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_binary_operator1547 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUAL_in_binary_operator1554 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNEQUAL_in_binary_operator1561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_binary_operator1568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_binary_operator1575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LE_in_binary_operator1582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GE_in_binary_operator1589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOGICAL_OR_in_binary_operator1596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BIT_OR_in_binary_operator1603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOGICAL_AND_in_binary_operator1610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BIT_AND_in_binary_operator1617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INC_in_left_unary1631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEC_in_left_unary1638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_left_unary1645 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_left_unary1652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_left_unary1659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INC_in_right_unary1673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEC_in_right_unary1680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_right_unary1687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_primitive1700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BYTE_in_primitive1707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_in_primitive1714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SHORT_in_primitive1721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_primitive1728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LONG_in_primitive1735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_primitive1742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLE_in_primitive1749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_CONST_in_int_const1767 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LONG_CONST_in_long_const1777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HEX_CONST_in_hex_const1787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HEX_LONG_CONST_in_hex_long_const1797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_CONST_in_string_const1807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_CONST_in_float_const1817 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_CONST_in_char_const1827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_null_const1837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOL_CONST_in_boolean_const1848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_begin_in_array_const1859 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_array_const1861 = new BitSet(new long[]{0x0000BFFF00000840L,0x0000000000012010L});
    public static final BitSet FOLLOW_comma_in_array_const1864 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_array_const1866 = new BitSet(new long[]{0x0000BFFF00000840L,0x0000000000012010L});
    public static final BitSet FOLLOW_block_end_in_array_const1870 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_package_name1885 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_DOT_in_package_name1887 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_package_name1891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_import_name1905 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_DOT_in_import_name1907 = new BitSet(new long[]{0x1000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_set_in_import_name1911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_class_name1929 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_DOT_in_class_name1931 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_class_name1935 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_method_name1949 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_DOT_in_method_name1951 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_method_name1955 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_name_in_variable_name1968 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000080L});
    public static final BitSet FOLLOW_array_in_variable_name1970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_in_annotation_name1981 = new BitSet(new long[]{0x0000000000060000L,0x0000000000010000L});
    public static final BitSet FOLLOW_name_in_annotation_name1983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_name1993 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_DOT_in_name1995 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_name1999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_open_rect_bracket_in_array2010 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8324L});
    public static final BitSet FOLLOW_value_in_array2012 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8324L});
    public static final BitSet FOLLOW_close_rect_bracket_in_array2015 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000080L});
    public static final BitSet FOLLOW_PACKAGE_in_package_2030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_import_2041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASS_in_class_2051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENUM_in_enum_2060 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTERFACE_in_interface_2070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXTENDS_in_extends_2078 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPLEMENTS_in_implements_2088 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_THIS_in_this_2097 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUPER_in_super_2106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOID_in_void_2115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PUBLIC_in_public_2124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRIVATE_in_private_2132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROTECTED_in_protected_2142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STATIC_in_static_2151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FINAL_in_final_2160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSIENT_in_transient_2170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEW_in_new_2179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRY_in_try_2188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CATCH_in_catch_2197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FINALLY_in_finally_2205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_THROWS_in_throws_2214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_THROW_in_throw_2223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FOR_in_for_2233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHILE_in_while_2242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DO_in_do_2251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_if_2260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELSE_in_else_2269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SWITCH_in_switch_2279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CASE_in_case_2288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEFAULT_in_default_2296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETURN_in_return_2306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BREAK_in_break_2315 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_id_in_break_2317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BREAK_in_break_2324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTINUE_in_continue_2336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_semicolon2351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_comma2360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_colon2369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUESTION_in_question_2379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_plus2388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_minus2397 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_dot2406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_assign2415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCASSIGN_in_assign2422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECASSIGN_in_assign2429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_CURLY_BRACKET_in_block_begin2441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLOSE_CURLY_BRACKET_in_block_end2451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_open_bracket2461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_close_bracket2471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_RECT_BRACKET_in_open_rect_bracket2482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLOSE_RECT_BRACKET_in_close_rect_bracket2492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_def_in_synpred2_JavaParser46 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enum_def_in_synpred3_JavaParser50 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interface_def_in_synpred4_JavaParser54 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_def_in_synpred41_JavaParser437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_static_init_in_synpred42_JavaParser441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constructor_def_in_synpred43_JavaParser445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_def_in_synpred44_JavaParser449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_field_def_in_synpred45_JavaParser453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_def_in_synpred47_JavaParser475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constructor_def_in_synpred48_JavaParser479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_def_in_synpred49_JavaParser483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_field_def_in_synpred50_JavaParser487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enum_content_in_synpred51_JavaParser491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_left_unary_in_synpred72_JavaParser728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_question__in_synpred75_JavaParser744 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_synpred75_JavaParser746 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_colon_in_synpred75_JavaParser748 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_synpred75_JavaParser750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cast_in_synpred76_JavaParser725 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_left_unary_in_synpred76_JavaParser728 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_single_value_in_synpred76_JavaParser731 = new BitSet(new long[]{0xFF8F000000000002L,0x0000000000000067L});
    public static final BitSet FOLLOW_right_unary_in_synpred76_JavaParser733 = new BitSet(new long[]{0xFE0F000000000002L,0x0000000000000063L});
    public static final BitSet FOLLOW_binary_operator_in_synpred76_JavaParser737 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_synpred76_JavaParser739 = new BitSet(new long[]{0x2000000000000002L});
    public static final BitSet FOLLOW_question__in_synpred76_JavaParser744 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_synpred76_JavaParser746 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_colon_in_synpred76_JavaParser748 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_synpred76_JavaParser750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_generic_in_synpred93_JavaParser884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_value_in_synpred98_JavaParser920 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_comma_in_synpred98_JavaParser923 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB8224L});
    public static final BitSet FOLLOW_value_in_synpred98_JavaParser925 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_semicolon_in_synpred101_JavaParser951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_label_in_synpred102_JavaParser964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_synpred103_JavaParser970 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_semicolon_in_synpred103_JavaParser972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_synpred104_JavaParser977 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_semicolon_in_synpred104_JavaParser979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_synpred105_JavaParser984 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_semicolon_in_synpred105_JavaParser986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_right_unary_in_synpred118_JavaParser1058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_label_in_synpred119_JavaParser1070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_synpred120_JavaParser1075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_synpred121_JavaParser1080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_synpred122_JavaParser1085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_value_in_synpred135_JavaParser1165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_value_in_synpred140_JavaParser1225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for__in_synpred143_JavaParser1207 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_synpred143_JavaParser1209 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000013000L});
    public static final BitSet FOLLOW_variable_def_in_synpred143_JavaParser1212 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001010L});
    public static final BitSet FOLLOW_comma_in_synpred143_JavaParser1215 = new BitSet(new long[]{0x00003FC000000800L,0x0000000000012000L});
    public static final BitSet FOLLOW_variable_def_in_synpred143_JavaParser1217 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001010L});
    public static final BitSet FOLLOW_semicolon_in_synpred143_JavaParser1223 = new BitSet(new long[]{0x0780400000071600L,0x0000000030BB9224L});
    public static final BitSet FOLLOW_value_in_synpred143_JavaParser1225 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_semicolon_in_synpred143_JavaParser1228 = new BitSet(new long[]{0x07803FC0497EE800L,0x0000000000012404L});
    public static final BitSet FOLLOW_statement_wosemicolon_in_synpred143_JavaParser1231 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000410L});
    public static final BitSet FOLLOW_comma_in_synpred143_JavaParser1234 = new BitSet(new long[]{0x07803FC0497EE800L,0x0000000000012004L});
    public static final BitSet FOLLOW_statement_wosemicolon_in_synpred143_JavaParser1236 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000410L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_synpred143_JavaParser1242 = new BitSet(new long[]{0x07807FC0497EEE00L,0x0000000000013024L});
    public static final BitSet FOLLOW_code_in_synpred143_JavaParser1244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_case__in_synpred145_JavaParser1334 = new BitSet(new long[]{0x0000400000070600L,0x0000000030BB8020L});
    public static final BitSet FOLLOW_constant_in_synpred145_JavaParser1338 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_variable_name_in_synpred145_JavaParser1342 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_colon_in_synpred145_JavaParser1345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_code_in_synpred146_JavaParser1350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_synpred147_JavaParser1354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_code_in_synpred150_JavaParser1377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_synpred151_JavaParser1381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_else__in_synpred153_JavaParser1414 = new BitSet(new long[]{0x07807FC0497EEE00L,0x0000000000013024L});
    public static final BitSet FOLLOW_code_in_synpred153_JavaParser1416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_catch__in_synpred154_JavaParser1434 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_synpred154_JavaParser1436 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_id_in_synpred154_JavaParser1438 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_id_in_synpred154_JavaParser1440 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_synpred154_JavaParser1442 = new BitSet(new long[]{0x07807FC0497EEE00L,0x0000000000013024L});
    public static final BitSet FOLLOW_code_in_synpred154_JavaParser1444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_finally__in_synpred155_JavaParser1449 = new BitSet(new long[]{0x07807FC0497EEE00L,0x0000000000013024L});
    public static final BitSet FOLLOW_code_in_synpred155_JavaParser1451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_value_in_synpred196_JavaParser2012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BREAK_in_synpred198_JavaParser2315 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_id_in_synpred198_JavaParser2317 = new BitSet(new long[]{0x0000000000000002L});

}