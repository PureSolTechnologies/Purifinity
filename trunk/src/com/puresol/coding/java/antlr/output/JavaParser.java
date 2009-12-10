// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g 2009-12-10 20:34:16

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "PACKAGE", "IMPORT", "CLASS", "INTERFACE", "ENUM", "EXTENDS", "IMPLEMENTS", "VOID", "NEW", "RETURN", "BREAK", "CONTINUE", "NULL", "THIS", "SUPER", "FOR", "DO", "WHILE", "IF", "ELSE", "SWITCH", "CASE", "TRY", "CATCH", "FINALLY", "THROW", "THROWS", "PUBLIC", "PRIVATE", "PROTECTED", "FINAL", "STATIC", "TRANSIENT", "BOOLEAN", "BYTE", "CHAR", "SHORT", "INTEGER", "LONG", "FLOAT", "DOUBLE", "OPEN_CURLY_BRACKET", "CLOSE_CURLY_BRACKET", "LE", "GE", "EQUAL", "UNEQUAL", "ASSIGN", "INCASSIGN", "DECASSIGN", "INC", "DEC", "PLUS", "MINUS", "SLASH", "STAR", "LOGICAL_OR", "BIT_OR", "LOGICAL_AND", "BIT_AND", "NOT", "DOT", "COMMA", "LT", "GT", "OPEN_RECT_BRACKET", "CLOSE_RECT_BRACKET", "OPEN_BRACKET", "CLOSE_BRACKET", "COLON", "SEMICOLON", "AT", "TILDE", "BOOL_CONST", "ID", "INT_CONST", "LONG_CONST", "EXPONENT", "FLOAT_CONST", "LINEFEED", "COMMENT", "WS", "ESC_SEQ", "STRING_CONST", "CHAR_CONST", "HEX_DIGIT", "UNICODE_ESC", "OCTAL_ESC"
    };
    public static final int PACKAGE=4;
    public static final int EXPONENT=81;
    public static final int LT=67;
    public static final int STAR=59;
    public static final int WHILE=21;
    public static final int CASE=25;
    public static final int OCTAL_ESC=91;
    public static final int CHAR=39;
    public static final int NEW=12;
    public static final int DO=20;
    public static final int NOT=64;
    public static final int EOF=-1;
    public static final int BREAK=14;
    public static final int LOGICAL_AND=62;
    public static final int OPEN_BRACKET=71;
    public static final int FINAL=34;
    public static final int INC=54;
    public static final int IMPORT=5;
    public static final int BIT_OR=61;
    public static final int STRING_CONST=87;
    public static final int THIS=17;
    public static final int RETURN=13;
    public static final int DOUBLE=44;
    public static final int VOID=11;
    public static final int SUPER=18;
    public static final int COMMENT=84;
    public static final int GE=48;
    public static final int PRIVATE=32;
    public static final int STATIC=35;
    public static final int SWITCH=24;
    public static final int NULL=16;
    public static final int ELSE=23;
    public static final int THROWS=30;
    public static final int SEMICOLON=74;
    public static final int TRY=26;
    public static final int WS=85;
    public static final int GT=68;
    public static final int CATCH=27;
    public static final int THROW=29;
    public static final int CLOSE_BRACKET=72;
    public static final int PROTECTED=33;
    public static final int DEC=55;
    public static final int CLASS=6;
    public static final int INCASSIGN=52;
    public static final int BIT_AND=63;
    public static final int FOR=19;
    public static final int FLOAT=43;
    public static final int LONG_CONST=80;
    public static final int ID=78;
    public static final int CLOSE_RECT_BRACKET=70;
    public static final int FLOAT_CONST=82;
    public static final int IF=22;
    public static final int LINEFEED=83;
    public static final int CHAR_CONST=88;
    public static final int AT=75;
    public static final int BOOLEAN=37;
    public static final int SLASH=58;
    public static final int ESC_SEQ=86;
    public static final int IMPLEMENTS=10;
    public static final int CONTINUE=15;
    public static final int COMMA=66;
    public static final int TRANSIENT=36;
    public static final int EQUAL=49;
    public static final int LOGICAL_OR=60;
    public static final int TILDE=76;
    public static final int PLUS=56;
    public static final int DOT=65;
    public static final int INTEGER=41;
    public static final int BYTE=38;
    public static final int OPEN_CURLY_BRACKET=45;
    public static final int UNICODE_ESC=90;
    public static final int CLOSE_CURLY_BRACKET=46;
    public static final int INT_CONST=79;
    public static final int HEX_DIGIT=89;
    public static final int SHORT=40;
    public static final int DECASSIGN=53;
    public static final int MINUS=57;
    public static final int UNEQUAL=50;
    public static final int COLON=73;
    public static final int ENUM=8;
    public static final int FINALLY=28;
    public static final int ASSIGN=51;
    public static final int INTERFACE=7;
    public static final int OPEN_RECT_BRACKET=69;
    public static final int LONG=42;
    public static final int PUBLIC=31;
    public static final int EXTENDS=9;
    public static final int BOOL_CONST=77;
    public static final int LE=47;

    // delegates
    // delegators


        public JavaParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public JavaParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
            this.state.ruleMemo = new HashMap[257+1];
             
             
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
        int file_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:24:6: ( package_def ( import_def )* ( class_def | enum_def | interface_def )+ )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:24:8: package_def ( import_def )* ( class_def | enum_def | interface_def )+
            {
            pushFollow(FOLLOW_package_def_in_file44);
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
            	    pushFollow(FOLLOW_class_def_in_file50);
            	    class_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 2 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:24:45: enum_def
            	    {
            	    pushFollow(FOLLOW_enum_def_in_file54);
            	    enum_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 3 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:24:56: interface_def
            	    {
            	    pushFollow(FOLLOW_interface_def_in_file58);
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
            if ( state.backtracking>0 ) { memoize(input, 1, file_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "file"


    // $ANTLR start "package_def"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:27:1: package_def : package_ package_name semicolon ;
    public final void package_def() throws RecognitionException {
        int package_def_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:28:2: ( package_ package_name semicolon )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:28:4: package_ package_name semicolon
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
            if ( state.backtracking>0 ) { memoize(input, 2, package_def_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "package_def"


    // $ANTLR start "import_def"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:31:1: import_def : import_ import_name semicolon ;
    public final void import_def() throws RecognitionException {
        int import_def_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:32:2: ( import_ import_name semicolon )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:32:4: import_ import_name semicolon
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
            if ( state.backtracking>0 ) { memoize(input, 3, import_def_StartIndex); }
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
        int class_def_StartIndex = input.index();
        JavaParser.id_return id1 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }
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
            	    pushFollow(FOLLOW_modifier_in_class_def107);
            	    modifier();

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
                    pushFollow(FOLLOW_generic_in_class_def114);
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
                    pushFollow(FOLLOW_extends__in_class_def118);
                    extends_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_class_def120);
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
                            pushFollow(FOLLOW_generic_in_class_def122);
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
                    pushFollow(FOLLOW_implements__in_class_def128);
                    implements_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_class_def130);
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
                    	    pushFollow(FOLLOW_comma_in_class_def133);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    pushFollow(FOLLOW_class_name_in_class_def135);
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

            pushFollow(FOLLOW_class_block_in_class_def141);
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

    public static class enum_def_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "enum_def"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:40:1: enum_def : ( annotation )* ( modifier )* enum_ id ( generic )? ( extends_ class_name ( generic )? )? enum_block ;
    public final JavaParser.enum_def_return enum_def() throws RecognitionException {
        JavaParser.enum_def_return retval = new JavaParser.enum_def_return();
        retval.start = input.LT(1);
        int enum_def_StartIndex = input.index();
        JavaParser.id_return id2 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }
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
            	    pushFollow(FOLLOW_annotation_in_enum_def157);
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
            	    pushFollow(FOLLOW_modifier_in_enum_def160);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            pushFollow(FOLLOW_enum__in_enum_def163);
            enum_();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_id_in_enum_def165);
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
                    pushFollow(FOLLOW_generic_in_enum_def167);
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
                    pushFollow(FOLLOW_extends__in_enum_def171);
                    extends_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_enum_def173);
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
                            pushFollow(FOLLOW_generic_in_enum_def175);
                            generic();

                            state._fsp--;
                            if (state.failed) return retval;

                            }
                            break;

                    }


                    }
                    break;

            }

            pushFollow(FOLLOW_enum_block_in_enum_def180);
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
            if ( state.backtracking>0 ) { memoize(input, 5, enum_def_StartIndex); }
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
        int interface_def_StartIndex = input.index();
        JavaParser.id_return id3 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }
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
            	    pushFollow(FOLLOW_annotation_in_interface_def196);
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
            	    pushFollow(FOLLOW_modifier_in_interface_def199);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            pushFollow(FOLLOW_interface__in_interface_def202);
            interface_();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_id_in_interface_def204);
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
                    pushFollow(FOLLOW_generic_in_interface_def206);
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
                    pushFollow(FOLLOW_extends__in_interface_def210);
                    extends_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_interface_def212);
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
                            pushFollow(FOLLOW_generic_in_interface_def214);
                            generic();

                            state._fsp--;
                            if (state.failed) return retval;

                            }
                            break;

                    }


                    }
                    break;

            }

            pushFollow(FOLLOW_interface_block_in_interface_def219);
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
            if ( state.backtracking>0 ) { memoize(input, 6, interface_def_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "interface_def"

    public static class constructor_def_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "constructor_def"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:50:1: constructor_def : ( annotation )* ( modifier )* id open_bracket argument_def close_bracket code ;
    public final JavaParser.constructor_def_return constructor_def() throws RecognitionException {
        JavaParser.constructor_def_return retval = new JavaParser.constructor_def_return();
        retval.start = input.LT(1);
        int constructor_def_StartIndex = input.index();
        JavaParser.id_return id4 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:51:2: ( ( annotation )* ( modifier )* id open_bracket argument_def close_bracket code )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:51:4: ( annotation )* ( modifier )* id open_bracket argument_def close_bracket code
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:51:4: ( annotation )*
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
            	    pushFollow(FOLLOW_annotation_in_constructor_def235);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:51:16: ( modifier )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( ((LA21_0>=PUBLIC && LA21_0<=TRANSIENT)) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: modifier
            	    {
            	    pushFollow(FOLLOW_modifier_in_constructor_def238);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

            pushFollow(FOLLOW_id_in_constructor_def241);
            id4=id();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_open_bracket_in_constructor_def243);
            open_bracket();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_argument_def_in_constructor_def245);
            argument_def();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_close_bracket_in_constructor_def247);
            close_bracket();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_code_in_constructor_def249);
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
            if ( state.backtracking>0 ) { memoize(input, 7, constructor_def_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "constructor_def"

    public static class method_def_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "method_def"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:55:1: method_def : ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket ( throws_ class_name ( comma class_name )* )? code ;
    public final JavaParser.method_def_return method_def() throws RecognitionException {
        JavaParser.method_def_return retval = new JavaParser.method_def_return();
        retval.start = input.LT(1);
        int method_def_StartIndex = input.index();
        JavaParser.id_return id5 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:56:2: ( ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket ( throws_ class_name ( comma class_name )* )? code )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:56:4: ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket ( throws_ class_name ( comma class_name )* )? code
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:56:4: ( annotation )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==AT) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_method_def264);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:56:16: ( modifier )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( ((LA23_0>=PUBLIC && LA23_0<=TRANSIENT)) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: modifier
            	    {
            	    pushFollow(FOLLOW_modifier_in_method_def267);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

            pushFollow(FOLLOW_variable_type_in_method_def270);
            variable_type();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_id_in_method_def272);
            id5=id();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_open_bracket_in_method_def274);
            open_bracket();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_argument_def_in_method_def276);
            argument_def();

            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_close_bracket_in_method_def278);
            close_bracket();

            state._fsp--;
            if (state.failed) return retval;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:56:83: ( throws_ class_name ( comma class_name )* )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==THROWS) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:56:84: throws_ class_name ( comma class_name )*
                    {
                    pushFollow(FOLLOW_throws__in_method_def281);
                    throws_();

                    state._fsp--;
                    if (state.failed) return retval;
                    pushFollow(FOLLOW_class_name_in_method_def283);
                    class_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:56:103: ( comma class_name )*
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==COMMA) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:56:104: comma class_name
                    	    {
                    	    pushFollow(FOLLOW_comma_in_method_def286);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    pushFollow(FOLLOW_class_name_in_method_def288);
                    	    class_name();

                    	    state._fsp--;
                    	    if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop24;
                        }
                    } while (true);


                    }
                    break;

            }

            pushFollow(FOLLOW_code_in_method_def294);
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
            if ( state.backtracking>0 ) { memoize(input, 8, method_def_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "method_def"


    // $ANTLR start "field_def"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:60:1: field_def : ( annotation )* ( modifier )* variable_type variable_name ( assign value )? semicolon ;
    public final void field_def() throws RecognitionException {
        int field_def_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:61:2: ( ( annotation )* ( modifier )* variable_type variable_name ( assign value )? semicolon )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:61:4: ( annotation )* ( modifier )* variable_type variable_name ( assign value )? semicolon
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:61:4: ( annotation )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==AT) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_field_def310);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:61:16: ( modifier )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( ((LA27_0>=PUBLIC && LA27_0<=TRANSIENT)) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: modifier
            	    {
            	    pushFollow(FOLLOW_modifier_in_field_def313);
            	    modifier();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);

            pushFollow(FOLLOW_variable_type_in_field_def316);
            variable_type();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_variable_name_in_field_def318);
            variable_name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:61:54: ( assign value )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( ((LA28_0>=ASSIGN && LA28_0<=DECASSIGN)) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:61:55: assign value
                    {
                    pushFollow(FOLLOW_assign_in_field_def321);
                    assign();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_field_def323);
                    value();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_semicolon_in_field_def327);
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
            if ( state.backtracking>0 ) { memoize(input, 9, field_def_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "field_def"


    // $ANTLR start "argument_def"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:64:1: argument_def : ( variable_type variable_name ( comma variable_type variable_name )* )? ;
    public final void argument_def() throws RecognitionException {
        int argument_def_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:65:2: ( ( variable_type variable_name ( comma variable_type variable_name )* )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:65:4: ( variable_type variable_name ( comma variable_type variable_name )* )?
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:65:4: ( variable_type variable_name ( comma variable_type variable_name )* )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==VOID||(LA30_0>=BOOLEAN && LA30_0<=DOUBLE)||LA30_0==ID) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:65:5: variable_type variable_name ( comma variable_type variable_name )*
                    {
                    pushFollow(FOLLOW_variable_type_in_argument_def339);
                    variable_type();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_variable_name_in_argument_def341);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:65:33: ( comma variable_type variable_name )*
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==COMMA) ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:65:34: comma variable_type variable_name
                    	    {
                    	    pushFollow(FOLLOW_comma_in_argument_def344);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_variable_type_in_argument_def346);
                    	    variable_type();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_variable_name_in_argument_def348);
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
            if ( state.backtracking>0 ) { memoize(input, 10, argument_def_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "argument_def"


    // $ANTLR start "variable_def"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:68:1: variable_def : ( annotation )* variable_type variable_name ( assign value )? ;
    public final void variable_def() throws RecognitionException {
        int variable_def_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:69:2: ( ( annotation )* variable_type variable_name ( assign value )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:69:4: ( annotation )* variable_type variable_name ( assign value )?
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:69:4: ( annotation )*
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
            	    pushFollow(FOLLOW_annotation_in_variable_def363);
            	    annotation();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);

            pushFollow(FOLLOW_variable_type_in_variable_def366);
            variable_type();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_variable_name_in_variable_def368);
            variable_name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:69:44: ( assign value )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( ((LA32_0>=ASSIGN && LA32_0<=DECASSIGN)) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:69:45: assign value
                    {
                    pushFollow(FOLLOW_assign_in_variable_def371);
                    assign();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_variable_def373);
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
            if ( state.backtracking>0 ) { memoize(input, 11, variable_def_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "variable_def"


    // $ANTLR start "modifier"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:72:1: modifier : ( public_ | private_ | protected_ | static_ | final_ | transient_ );
    public final void modifier() throws RecognitionException {
        int modifier_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:72:9: ( public_ | private_ | protected_ | static_ | final_ | transient_ )
            int alt33=6;
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
            case FINAL:
                {
                alt33=5;
                }
                break;
            case TRANSIENT:
                {
                alt33=6;
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
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:72:11: public_
                    {
                    pushFollow(FOLLOW_public__in_modifier385);
                    public_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:73:4: private_
                    {
                    pushFollow(FOLLOW_private__in_modifier390);
                    private_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:74:4: protected_
                    {
                    pushFollow(FOLLOW_protected__in_modifier395);
                    protected_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:75:4: static_
                    {
                    pushFollow(FOLLOW_static__in_modifier400);
                    static_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:76:4: final_
                    {
                    pushFollow(FOLLOW_final__in_modifier405);
                    final_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:77:4: transient_
                    {
                    pushFollow(FOLLOW_transient__in_modifier410);
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
            if ( state.backtracking>0 ) { memoize(input, 12, modifier_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "modifier"


    // $ANTLR start "class_block"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:81:1: class_block : block_begin ( class_def | constructor_def | method_def | field_def )* block_end ;
    public final void class_block() throws RecognitionException {
        int class_block_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:82:2: ( block_begin ( class_def | constructor_def | method_def | field_def )* block_end )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:82:4: block_begin ( class_def | constructor_def | method_def | field_def )* block_end
            {
            pushFollow(FOLLOW_block_begin_in_class_block422);
            block_begin();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:82:16: ( class_def | constructor_def | method_def | field_def )*
            loop34:
            do {
                int alt34=5;
                alt34 = dfa34.predict(input);
                switch (alt34) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:82:17: class_def
            	    {
            	    pushFollow(FOLLOW_class_def_in_class_block425);
            	    class_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 2 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:82:29: constructor_def
            	    {
            	    pushFollow(FOLLOW_constructor_def_in_class_block429);
            	    constructor_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 3 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:82:47: method_def
            	    {
            	    pushFollow(FOLLOW_method_def_in_class_block433);
            	    method_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 4 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:82:60: field_def
            	    {
            	    pushFollow(FOLLOW_field_def_in_class_block437);
            	    field_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);

            pushFollow(FOLLOW_block_end_in_class_block441);
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
            if ( state.backtracking>0 ) { memoize(input, 13, class_block_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "class_block"


    // $ANTLR start "enum_block"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:85:1: enum_block : block_begin ( class_def | constructor_def | method_def | field_def | enum_content )* block_end ;
    public final void enum_block() throws RecognitionException {
        int enum_block_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:2: ( block_begin ( class_def | constructor_def | method_def | field_def | enum_content )* block_end )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:4: block_begin ( class_def | constructor_def | method_def | field_def | enum_content )* block_end
            {
            pushFollow(FOLLOW_block_begin_in_enum_block452);
            block_begin();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:16: ( class_def | constructor_def | method_def | field_def | enum_content )*
            loop35:
            do {
                int alt35=6;
                alt35 = dfa35.predict(input);
                switch (alt35) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:17: class_def
            	    {
            	    pushFollow(FOLLOW_class_def_in_enum_block455);
            	    class_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 2 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:29: constructor_def
            	    {
            	    pushFollow(FOLLOW_constructor_def_in_enum_block459);
            	    constructor_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 3 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:47: method_def
            	    {
            	    pushFollow(FOLLOW_method_def_in_enum_block463);
            	    method_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 4 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:60: field_def
            	    {
            	    pushFollow(FOLLOW_field_def_in_enum_block467);
            	    field_def();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;
            	case 5 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:72: enum_content
            	    {
            	    pushFollow(FOLLOW_enum_content_in_enum_block471);
            	    enum_content();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);

            pushFollow(FOLLOW_block_end_in_enum_block475);
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
            if ( state.backtracking>0 ) { memoize(input, 14, enum_block_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "enum_block"


    // $ANTLR start "enum_content"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:89:1: enum_content : id ( open_bracket arguments close_bracket )? ( comma id ( open_bracket arguments close_bracket )? )* semicolon ;
    public final void enum_content() throws RecognitionException {
        int enum_content_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:2: ( id ( open_bracket arguments close_bracket )? ( comma id ( open_bracket arguments close_bracket )? )* semicolon )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:4: id ( open_bracket arguments close_bracket )? ( comma id ( open_bracket arguments close_bracket )? )* semicolon
            {
            pushFollow(FOLLOW_id_in_enum_content486);
            id();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:7: ( open_bracket arguments close_bracket )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==OPEN_BRACKET) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:8: open_bracket arguments close_bracket
                    {
                    pushFollow(FOLLOW_open_bracket_in_enum_content489);
                    open_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_arguments_in_enum_content491);
                    arguments();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_close_bracket_in_enum_content493);
                    close_bracket();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:47: ( comma id ( open_bracket arguments close_bracket )? )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==COMMA) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:48: comma id ( open_bracket arguments close_bracket )?
            	    {
            	    pushFollow(FOLLOW_comma_in_enum_content498);
            	    comma();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_enum_content500);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:57: ( open_bracket arguments close_bracket )?
            	    int alt37=2;
            	    int LA37_0 = input.LA(1);

            	    if ( (LA37_0==OPEN_BRACKET) ) {
            	        alt37=1;
            	    }
            	    switch (alt37) {
            	        case 1 :
            	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:58: open_bracket arguments close_bracket
            	            {
            	            pushFollow(FOLLOW_open_bracket_in_enum_content503);
            	            open_bracket();

            	            state._fsp--;
            	            if (state.failed) return ;
            	            pushFollow(FOLLOW_arguments_in_enum_content505);
            	            arguments();

            	            state._fsp--;
            	            if (state.failed) return ;
            	            pushFollow(FOLLOW_close_bracket_in_enum_content507);
            	            close_bracket();

            	            state._fsp--;
            	            if (state.failed) return ;

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);

            pushFollow(FOLLOW_semicolon_in_enum_content513);
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
            if ( state.backtracking>0 ) { memoize(input, 15, enum_content_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "enum_content"


    // $ANTLR start "interface_block"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:93:1: interface_block : block_begin ( ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket semicolon )* block_end ;
    public final void interface_block() throws RecognitionException {
        int interface_block_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:94:2: ( block_begin ( ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket semicolon )* block_end )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:94:4: block_begin ( ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket semicolon )* block_end
            {
            pushFollow(FOLLOW_block_begin_in_interface_block525);
            block_begin();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:94:16: ( ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket semicolon )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==VOID||(LA41_0>=PUBLIC && LA41_0<=DOUBLE)||LA41_0==AT||LA41_0==ID) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:94:17: ( annotation )* ( modifier )* variable_type id open_bracket argument_def close_bracket semicolon
            	    {
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:94:17: ( annotation )*
            	    loop39:
            	    do {
            	        int alt39=2;
            	        int LA39_0 = input.LA(1);

            	        if ( (LA39_0==AT) ) {
            	            alt39=1;
            	        }


            	        switch (alt39) {
            	    	case 1 :
            	    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: annotation
            	    	    {
            	    	    pushFollow(FOLLOW_annotation_in_interface_block528);
            	    	    annotation();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop39;
            	        }
            	    } while (true);

            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:94:29: ( modifier )*
            	    loop40:
            	    do {
            	        int alt40=2;
            	        int LA40_0 = input.LA(1);

            	        if ( ((LA40_0>=PUBLIC && LA40_0<=TRANSIENT)) ) {
            	            alt40=1;
            	        }


            	        switch (alt40) {
            	    	case 1 :
            	    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: modifier
            	    	    {
            	    	    pushFollow(FOLLOW_modifier_in_interface_block531);
            	    	    modifier();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop40;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_variable_type_in_interface_block534);
            	    variable_type();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_interface_block536);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_open_bracket_in_interface_block538);
            	    open_bracket();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_argument_def_in_interface_block540);
            	    argument_def();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_close_bracket_in_interface_block542);
            	    close_bracket();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_semicolon_in_interface_block544);
            	    semicolon();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);

            pushFollow(FOLLOW_block_end_in_interface_block548);
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
            if ( state.backtracking>0 ) { memoize(input, 16, interface_block_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "interface_block"


    // $ANTLR start "method_call"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:97:1: method_call : ( method_name open_bracket arguments close_bracket ( dot id ( open_bracket arguments close_bracket )? )* | super_ open_bracket arguments close_bracket ( dot id ( open_bracket arguments close_bracket )? )* | this_ open_bracket arguments close_bracket ( dot id ( open_bracket arguments close_bracket )? )* );
    public final void method_call() throws RecognitionException {
        int method_call_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:98:2: ( method_name open_bracket arguments close_bracket ( dot id ( open_bracket arguments close_bracket )? )* | super_ open_bracket arguments close_bracket ( dot id ( open_bracket arguments close_bracket )? )* | this_ open_bracket arguments close_bracket ( dot id ( open_bracket arguments close_bracket )? )* )
            int alt48=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt48=1;
                }
                break;
            case SUPER:
                {
                alt48=2;
                }
                break;
            case THIS:
                {
                alt48=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 48, 0, input);

                throw nvae;
            }

            switch (alt48) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:98:4: method_name open_bracket arguments close_bracket ( dot id ( open_bracket arguments close_bracket )? )*
                    {
                    pushFollow(FOLLOW_method_name_in_method_call560);
                    method_name();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_open_bracket_in_method_call562);
                    open_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_arguments_in_method_call564);
                    arguments();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_close_bracket_in_method_call566);
                    close_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:98:53: ( dot id ( open_bracket arguments close_bracket )? )*
                    loop43:
                    do {
                        int alt43=2;
                        int LA43_0 = input.LA(1);

                        if ( (LA43_0==DOT) ) {
                            alt43=1;
                        }


                        switch (alt43) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:98:54: dot id ( open_bracket arguments close_bracket )?
                    	    {
                    	    pushFollow(FOLLOW_dot_in_method_call569);
                    	    dot();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_id_in_method_call571);
                    	    id();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:98:61: ( open_bracket arguments close_bracket )?
                    	    int alt42=2;
                    	    int LA42_0 = input.LA(1);

                    	    if ( (LA42_0==OPEN_BRACKET) ) {
                    	        alt42=1;
                    	    }
                    	    switch (alt42) {
                    	        case 1 :
                    	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:98:62: open_bracket arguments close_bracket
                    	            {
                    	            pushFollow(FOLLOW_open_bracket_in_method_call574);
                    	            open_bracket();

                    	            state._fsp--;
                    	            if (state.failed) return ;
                    	            pushFollow(FOLLOW_arguments_in_method_call576);
                    	            arguments();

                    	            state._fsp--;
                    	            if (state.failed) return ;
                    	            pushFollow(FOLLOW_close_bracket_in_method_call578);
                    	            close_bracket();

                    	            state._fsp--;
                    	            if (state.failed) return ;

                    	            }
                    	            break;

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop43;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:99:4: super_ open_bracket arguments close_bracket ( dot id ( open_bracket arguments close_bracket )? )*
                    {
                    pushFollow(FOLLOW_super__in_method_call587);
                    super_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_open_bracket_in_method_call589);
                    open_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_arguments_in_method_call591);
                    arguments();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_close_bracket_in_method_call593);
                    close_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:99:48: ( dot id ( open_bracket arguments close_bracket )? )*
                    loop45:
                    do {
                        int alt45=2;
                        int LA45_0 = input.LA(1);

                        if ( (LA45_0==DOT) ) {
                            alt45=1;
                        }


                        switch (alt45) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:99:49: dot id ( open_bracket arguments close_bracket )?
                    	    {
                    	    pushFollow(FOLLOW_dot_in_method_call596);
                    	    dot();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_id_in_method_call598);
                    	    id();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:99:56: ( open_bracket arguments close_bracket )?
                    	    int alt44=2;
                    	    int LA44_0 = input.LA(1);

                    	    if ( (LA44_0==OPEN_BRACKET) ) {
                    	        alt44=1;
                    	    }
                    	    switch (alt44) {
                    	        case 1 :
                    	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:99:57: open_bracket arguments close_bracket
                    	            {
                    	            pushFollow(FOLLOW_open_bracket_in_method_call601);
                    	            open_bracket();

                    	            state._fsp--;
                    	            if (state.failed) return ;
                    	            pushFollow(FOLLOW_arguments_in_method_call603);
                    	            arguments();

                    	            state._fsp--;
                    	            if (state.failed) return ;
                    	            pushFollow(FOLLOW_close_bracket_in_method_call605);
                    	            close_bracket();

                    	            state._fsp--;
                    	            if (state.failed) return ;

                    	            }
                    	            break;

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop45;
                        }
                    } while (true);


                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:100:4: this_ open_bracket arguments close_bracket ( dot id ( open_bracket arguments close_bracket )? )*
                    {
                    pushFollow(FOLLOW_this__in_method_call614);
                    this_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_open_bracket_in_method_call616);
                    open_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_arguments_in_method_call618);
                    arguments();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_close_bracket_in_method_call620);
                    close_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:100:47: ( dot id ( open_bracket arguments close_bracket )? )*
                    loop47:
                    do {
                        int alt47=2;
                        int LA47_0 = input.LA(1);

                        if ( (LA47_0==DOT) ) {
                            alt47=1;
                        }


                        switch (alt47) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:100:48: dot id ( open_bracket arguments close_bracket )?
                    	    {
                    	    pushFollow(FOLLOW_dot_in_method_call623);
                    	    dot();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_id_in_method_call625);
                    	    id();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:100:55: ( open_bracket arguments close_bracket )?
                    	    int alt46=2;
                    	    int LA46_0 = input.LA(1);

                    	    if ( (LA46_0==OPEN_BRACKET) ) {
                    	        alt46=1;
                    	    }
                    	    switch (alt46) {
                    	        case 1 :
                    	            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:100:56: open_bracket arguments close_bracket
                    	            {
                    	            pushFollow(FOLLOW_open_bracket_in_method_call628);
                    	            open_bracket();

                    	            state._fsp--;
                    	            if (state.failed) return ;
                    	            pushFollow(FOLLOW_arguments_in_method_call630);
                    	            arguments();

                    	            state._fsp--;
                    	            if (state.failed) return ;
                    	            pushFollow(FOLLOW_close_bracket_in_method_call632);
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

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 17, method_call_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "method_call"


    // $ANTLR start "annotation"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:103:1: annotation : annotation_name ( open_bracket value ( comma value )* close_bracket )? ;
    public final void annotation() throws RecognitionException {
        int annotation_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:104:2: ( annotation_name ( open_bracket value ( comma value )* close_bracket )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:104:4: annotation_name ( open_bracket value ( comma value )* close_bracket )?
            {
            pushFollow(FOLLOW_annotation_name_in_annotation647);
            annotation_name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:104:20: ( open_bracket value ( comma value )* close_bracket )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==OPEN_BRACKET) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:104:21: open_bracket value ( comma value )* close_bracket
                    {
                    pushFollow(FOLLOW_open_bracket_in_annotation650);
                    open_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_annotation652);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:104:40: ( comma value )*
                    loop49:
                    do {
                        int alt49=2;
                        int LA49_0 = input.LA(1);

                        if ( (LA49_0==COMMA) ) {
                            alt49=1;
                        }


                        switch (alt49) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:104:41: comma value
                    	    {
                    	    pushFollow(FOLLOW_comma_in_annotation655);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_value_in_annotation657);
                    	    value();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop49;
                        }
                    } while (true);

                    pushFollow(FOLLOW_close_bracket_in_annotation661);
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
            if ( state.backtracking>0 ) { memoize(input, 18, annotation_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "annotation"


    // $ANTLR start "generic"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:107:1: generic : LT ( id ( comma id )* ) GT ;
    public final void generic() throws RecognitionException {
        int generic_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:107:9: ( LT ( id ( comma id )* ) GT )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:107:11: LT ( id ( comma id )* ) GT
            {
            match(input,LT,FOLLOW_LT_in_generic673); if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:107:14: ( id ( comma id )* )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:107:15: id ( comma id )*
            {
            pushFollow(FOLLOW_id_in_generic676);
            id();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:107:18: ( comma id )*
            loop51:
            do {
                int alt51=2;
                int LA51_0 = input.LA(1);

                if ( (LA51_0==COMMA) ) {
                    alt51=1;
                }


                switch (alt51) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:107:19: comma id
            	    {
            	    pushFollow(FOLLOW_comma_in_generic679);
            	    comma();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_generic681);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop51;
                }
            } while (true);


            }

            match(input,GT,FOLLOW_GT_in_generic686); if (state.failed) return ;
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
            if ( state.backtracking>0 ) { memoize(input, 19, generic_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "generic"


    // $ANTLR start "value"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:1: value : ( ( cast )? ( unary )? single_value ( unary )? ( binary_operator value )? | open_bracket value close_bracket );
    public final void value() throws RecognitionException {
        int value_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:7: ( ( cast )? ( unary )? single_value ( unary )? ( binary_operator value )? | open_bracket value close_bracket )
            int alt56=2;
            alt56 = dfa56.predict(input);
            switch (alt56) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:9: ( cast )? ( unary )? single_value ( unary )? ( binary_operator value )?
                    {
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:9: ( cast )?
                    int alt52=2;
                    int LA52_0 = input.LA(1);

                    if ( (LA52_0==OPEN_BRACKET) ) {
                        alt52=1;
                    }
                    switch (alt52) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: cast
                            {
                            pushFollow(FOLLOW_cast_in_value699);
                            cast();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:15: ( unary )?
                    int alt53=2;
                    int LA53_0 = input.LA(1);

                    if ( ((LA53_0>=INC && LA53_0<=DEC)||LA53_0==NOT) ) {
                        alt53=1;
                    }
                    switch (alt53) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                            {
                            pushFollow(FOLLOW_unary_in_value702);
                            unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_single_value_in_value705);
                    single_value();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:35: ( unary )?
                    int alt54=2;
                    int LA54_0 = input.LA(1);

                    if ( ((LA54_0>=INC && LA54_0<=DEC)||LA54_0==NOT) ) {
                        alt54=1;
                    }
                    switch (alt54) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                            {
                            pushFollow(FOLLOW_unary_in_value707);
                            unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:42: ( binary_operator value )?
                    int alt55=2;
                    int LA55_0 = input.LA(1);

                    if ( ((LA55_0>=LE && LA55_0<=UNEQUAL)||(LA55_0>=PLUS && LA55_0<=BIT_AND)||(LA55_0>=LT && LA55_0<=GT)) ) {
                        alt55=1;
                    }
                    switch (alt55) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:43: binary_operator value
                            {
                            pushFollow(FOLLOW_binary_operator_in_value711);
                            binary_operator();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_value_in_value713);
                            value();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:112:4: open_bracket value close_bracket
                    {
                    pushFollow(FOLLOW_open_bracket_in_value720);
                    open_bracket();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_value722);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_close_bracket_in_value724);
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
            if ( state.backtracking>0 ) { memoize(input, 20, value_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "value"


    // $ANTLR start "single_value"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:1: single_value : ( constant | class_name dot class_ | variable_name | method_call | new_class | this_ | super_ );
    public final void single_value() throws RecognitionException {
        int single_value_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:116:2: ( constant | class_name dot class_ | variable_name | method_call | new_class | this_ | super_ )
            int alt57=7;
            alt57 = dfa57.predict(input);
            switch (alt57) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:116:4: constant
                    {
                    pushFollow(FOLLOW_constant_in_single_value735);
                    constant();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:117:4: class_name dot class_
                    {
                    pushFollow(FOLLOW_class_name_in_single_value740);
                    class_name();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_dot_in_single_value742);
                    dot();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_class__in_single_value744);
                    class_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:118:4: variable_name
                    {
                    pushFollow(FOLLOW_variable_name_in_single_value749);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:119:4: method_call
                    {
                    pushFollow(FOLLOW_method_call_in_single_value754);
                    method_call();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:120:4: new_class
                    {
                    pushFollow(FOLLOW_new_class_in_single_value759);
                    new_class();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:121:4: this_
                    {
                    pushFollow(FOLLOW_this__in_single_value764);
                    this_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:122:4: super_
                    {
                    pushFollow(FOLLOW_super__in_single_value769);
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
            if ( state.backtracking>0 ) { memoize(input, 21, single_value_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "single_value"


    // $ANTLR start "constant"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:125:1: constant : ( int_const | string_const | float_const | char_const | null_const | boolean_const | long_const );
    public final void constant() throws RecognitionException {
        int constant_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:125:9: ( int_const | string_const | float_const | char_const | null_const | boolean_const | long_const )
            int alt58=7;
            switch ( input.LA(1) ) {
            case INT_CONST:
                {
                alt58=1;
                }
                break;
            case STRING_CONST:
                {
                alt58=2;
                }
                break;
            case FLOAT_CONST:
                {
                alt58=3;
                }
                break;
            case CHAR_CONST:
                {
                alt58=4;
                }
                break;
            case NULL:
                {
                alt58=5;
                }
                break;
            case BOOL_CONST:
                {
                alt58=6;
                }
                break;
            case LONG_CONST:
                {
                alt58=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 58, 0, input);

                throw nvae;
            }

            switch (alt58) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:125:11: int_const
                    {
                    pushFollow(FOLLOW_int_const_in_constant778);
                    int_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:126:4: string_const
                    {
                    pushFollow(FOLLOW_string_const_in_constant783);
                    string_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:127:4: float_const
                    {
                    pushFollow(FOLLOW_float_const_in_constant788);
                    float_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:128:4: char_const
                    {
                    pushFollow(FOLLOW_char_const_in_constant793);
                    char_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:129:4: null_const
                    {
                    pushFollow(FOLLOW_null_const_in_constant798);
                    null_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:130:4: boolean_const
                    {
                    pushFollow(FOLLOW_boolean_const_in_constant803);
                    boolean_const();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:131:4: long_const
                    {
                    pushFollow(FOLLOW_long_const_in_constant808);
                    long_const();

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
            if ( state.backtracking>0 ) { memoize(input, 22, constant_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "constant"


    // $ANTLR start "new_class"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:134:1: new_class : new_ class_name ( generic )? open_bracket arguments close_bracket ;
    public final void new_class() throws RecognitionException {
        int new_class_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:135:2: ( new_ class_name ( generic )? open_bracket arguments close_bracket )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:135:4: new_ class_name ( generic )? open_bracket arguments close_bracket
            {
            pushFollow(FOLLOW_new__in_new_class820);
            new_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_class_name_in_new_class822);
            class_name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:135:20: ( generic )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==LT) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: generic
                    {
                    pushFollow(FOLLOW_generic_in_new_class824);
                    generic();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_open_bracket_in_new_class827);
            open_bracket();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_arguments_in_new_class829);
            arguments();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_close_bracket_in_new_class831);
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
            if ( state.backtracking>0 ) { memoize(input, 23, new_class_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "new_class"


    // $ANTLR start "arguments"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:138:1: arguments : ( value ( comma value )* )? ;
    public final void arguments() throws RecognitionException {
        int arguments_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:139:2: ( ( value ( comma value )* )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:139:4: ( value ( comma value )* )?
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:139:4: ( value ( comma value )* )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==NEW||(LA61_0>=NULL && LA61_0<=SUPER)||(LA61_0>=INC && LA61_0<=DEC)||LA61_0==NOT||LA61_0==OPEN_BRACKET||(LA61_0>=BOOL_CONST && LA61_0<=LONG_CONST)||LA61_0==FLOAT_CONST||(LA61_0>=STRING_CONST && LA61_0<=CHAR_CONST)) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:139:5: value ( comma value )*
                    {
                    pushFollow(FOLLOW_value_in_arguments844);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:139:11: ( comma value )*
                    loop60:
                    do {
                        int alt60=2;
                        int LA60_0 = input.LA(1);

                        if ( (LA60_0==COMMA) ) {
                            alt60=1;
                        }


                        switch (alt60) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:139:12: comma value
                    	    {
                    	    pushFollow(FOLLOW_comma_in_arguments847);
                    	    comma();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    pushFollow(FOLLOW_value_in_arguments849);
                    	    value();

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


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 24, arguments_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "arguments"


    // $ANTLR start "code"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:1: code : ( statement | block_begin ( statement )* block_end );
    public final void code() throws RecognitionException {
        int code_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:6: ( statement | block_begin ( statement )* block_end )
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==VOID||(LA63_0>=RETURN && LA63_0<=CONTINUE)||(LA63_0>=THIS && LA63_0<=IF)||LA63_0==TRY||LA63_0==THROW||(LA63_0>=BOOLEAN && LA63_0<=DOUBLE)||(LA63_0>=INC && LA63_0<=DEC)||LA63_0==NOT||(LA63_0>=SEMICOLON && LA63_0<=AT)||LA63_0==ID) ) {
                alt63=1;
            }
            else if ( (LA63_0==OPEN_CURLY_BRACKET) ) {
                alt63=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }
            switch (alt63) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:8: statement
                    {
                    pushFollow(FOLLOW_statement_in_code863);
                    statement();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:143:4: block_begin ( statement )* block_end
                    {
                    pushFollow(FOLLOW_block_begin_in_code868);
                    block_begin();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:143:16: ( statement )*
                    loop62:
                    do {
                        int alt62=2;
                        int LA62_0 = input.LA(1);

                        if ( (LA62_0==VOID||(LA62_0>=RETURN && LA62_0<=CONTINUE)||(LA62_0>=THIS && LA62_0<=IF)||LA62_0==TRY||LA62_0==THROW||(LA62_0>=BOOLEAN && LA62_0<=DOUBLE)||(LA62_0>=INC && LA62_0<=DEC)||LA62_0==NOT||(LA62_0>=SEMICOLON && LA62_0<=AT)||LA62_0==ID) ) {
                            alt62=1;
                        }


                        switch (alt62) {
                    	case 1 :
                    	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: statement
                    	    {
                    	    pushFollow(FOLLOW_statement_in_code870);
                    	    statement();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop62;
                        }
                    } while (true);

                    pushFollow(FOLLOW_block_end_in_code873);
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
            if ( state.backtracking>0 ) { memoize(input, 25, code_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "code"


    // $ANTLR start "statement"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:146:1: statement : ( variable_assignment semicolon | variable_def semicolon | method_call semicolon | semicolon | return_statement | continue_ | break_ | ( unary )? variable_name ( unary )? | for_loop | while_loop | do_loop | if_else | try_catch | throw_ value semicolon );
    public final void statement() throws RecognitionException {
        int statement_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:147:2: ( variable_assignment semicolon | variable_def semicolon | method_call semicolon | semicolon | return_statement | continue_ | break_ | ( unary )? variable_name ( unary )? | for_loop | while_loop | do_loop | if_else | try_catch | throw_ value semicolon )
            int alt66=14;
            alt66 = dfa66.predict(input);
            switch (alt66) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:147:4: variable_assignment semicolon
                    {
                    pushFollow(FOLLOW_variable_assignment_in_statement885);
                    variable_assignment();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement887);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:148:4: variable_def semicolon
                    {
                    pushFollow(FOLLOW_variable_def_in_statement892);
                    variable_def();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement894);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:149:4: method_call semicolon
                    {
                    pushFollow(FOLLOW_method_call_in_statement899);
                    method_call();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement901);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:150:5: semicolon
                    {
                    pushFollow(FOLLOW_semicolon_in_statement907);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:151:4: return_statement
                    {
                    pushFollow(FOLLOW_return_statement_in_statement912);
                    return_statement();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:152:4: continue_
                    {
                    pushFollow(FOLLOW_continue__in_statement917);
                    continue_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:4: break_
                    {
                    pushFollow(FOLLOW_break__in_statement922);
                    break_();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 8 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:154:4: ( unary )? variable_name ( unary )?
                    {
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:154:4: ( unary )?
                    int alt64=2;
                    int LA64_0 = input.LA(1);

                    if ( ((LA64_0>=INC && LA64_0<=DEC)||LA64_0==NOT) ) {
                        alt64=1;
                    }
                    switch (alt64) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                            {
                            pushFollow(FOLLOW_unary_in_statement927);
                            unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_variable_name_in_statement930);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:154:25: ( unary )?
                    int alt65=2;
                    switch ( input.LA(1) ) {
                        case INC:
                            {
                            int LA65_1 = input.LA(2);

                            if ( (synpred96_JavaParser()) ) {
                                alt65=1;
                            }
                            }
                            break;
                        case DEC:
                            {
                            int LA65_2 = input.LA(2);

                            if ( (synpred96_JavaParser()) ) {
                                alt65=1;
                            }
                            }
                            break;
                        case NOT:
                            {
                            int LA65_3 = input.LA(2);

                            if ( (synpred96_JavaParser()) ) {
                                alt65=1;
                            }
                            }
                            break;
                    }

                    switch (alt65) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                            {
                            pushFollow(FOLLOW_unary_in_statement932);
                            unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 9 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:155:4: for_loop
                    {
                    pushFollow(FOLLOW_for_loop_in_statement938);
                    for_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 10 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:156:4: while_loop
                    {
                    pushFollow(FOLLOW_while_loop_in_statement943);
                    while_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 11 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:157:4: do_loop
                    {
                    pushFollow(FOLLOW_do_loop_in_statement948);
                    do_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 12 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:158:4: if_else
                    {
                    pushFollow(FOLLOW_if_else_in_statement953);
                    if_else();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 13 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:159:4: try_catch
                    {
                    pushFollow(FOLLOW_try_catch_in_statement958);
                    try_catch();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 14 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:160:4: throw_ value semicolon
                    {
                    pushFollow(FOLLOW_throw__in_statement963);
                    throw_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_statement965);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_semicolon_in_statement967);
                    semicolon();

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
            if ( state.backtracking>0 ) { memoize(input, 26, statement_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "statement"


    // $ANTLR start "statement_wosemicolon"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:163:1: statement_wosemicolon : ( variable_assignment | variable_def | method_call | return_statement | ( unary )? variable_name ( unary )? | for_loop | while_loop | do_loop | if_else | try_catch );
    public final void statement_wosemicolon() throws RecognitionException {
        int statement_wosemicolon_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:164:2: ( variable_assignment | variable_def | method_call | return_statement | ( unary )? variable_name ( unary )? | for_loop | while_loop | do_loop | if_else | try_catch )
            int alt69=10;
            alt69 = dfa69.predict(input);
            switch (alt69) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:164:4: variable_assignment
                    {
                    pushFollow(FOLLOW_variable_assignment_in_statement_wosemicolon978);
                    variable_assignment();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:165:4: variable_def
                    {
                    pushFollow(FOLLOW_variable_def_in_statement_wosemicolon983);
                    variable_def();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:166:4: method_call
                    {
                    pushFollow(FOLLOW_method_call_in_statement_wosemicolon988);
                    method_call();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:167:4: return_statement
                    {
                    pushFollow(FOLLOW_return_statement_in_statement_wosemicolon994);
                    return_statement();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:168:4: ( unary )? variable_name ( unary )?
                    {
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:168:4: ( unary )?
                    int alt67=2;
                    int LA67_0 = input.LA(1);

                    if ( ((LA67_0>=INC && LA67_0<=DEC)||LA67_0==NOT) ) {
                        alt67=1;
                    }
                    switch (alt67) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                            {
                            pushFollow(FOLLOW_unary_in_statement_wosemicolon999);
                            unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_variable_name_in_statement_wosemicolon1002);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:168:25: ( unary )?
                    int alt68=2;
                    int LA68_0 = input.LA(1);

                    if ( ((LA68_0>=INC && LA68_0<=DEC)||LA68_0==NOT) ) {
                        alt68=1;
                    }
                    switch (alt68) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                            {
                            pushFollow(FOLLOW_unary_in_statement_wosemicolon1004);
                            unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 6 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:169:4: for_loop
                    {
                    pushFollow(FOLLOW_for_loop_in_statement_wosemicolon1010);
                    for_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:170:4: while_loop
                    {
                    pushFollow(FOLLOW_while_loop_in_statement_wosemicolon1015);
                    while_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 8 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:171:4: do_loop
                    {
                    pushFollow(FOLLOW_do_loop_in_statement_wosemicolon1020);
                    do_loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 9 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:172:4: if_else
                    {
                    pushFollow(FOLLOW_if_else_in_statement_wosemicolon1025);
                    if_else();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 10 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:173:4: try_catch
                    {
                    pushFollow(FOLLOW_try_catch_in_statement_wosemicolon1030);
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
            if ( state.backtracking>0 ) { memoize(input, 27, statement_wosemicolon_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "statement_wosemicolon"


    // $ANTLR start "return_statement"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:176:1: return_statement : return_ value semicolon ;
    public final void return_statement() throws RecognitionException {
        int return_statement_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:177:2: ( return_ value semicolon )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:177:4: return_ value semicolon
            {
            pushFollow(FOLLOW_return__in_return_statement1042);
            return_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_value_in_return_statement1044);
            value();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_semicolon_in_return_statement1046);
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
            if ( state.backtracking>0 ) { memoize(input, 28, return_statement_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "return_statement"


    // $ANTLR start "variable_assignment"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:180:1: variable_assignment : ( this_ dot | super_ dot )? variable_name assign value ;
    public final void variable_assignment() throws RecognitionException {
        int variable_assignment_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:181:2: ( ( this_ dot | super_ dot )? variable_name assign value )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:181:4: ( this_ dot | super_ dot )? variable_name assign value
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:181:4: ( this_ dot | super_ dot )?
            int alt70=3;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==THIS) ) {
                alt70=1;
            }
            else if ( (LA70_0==SUPER) ) {
                alt70=2;
            }
            switch (alt70) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:181:5: this_ dot
                    {
                    pushFollow(FOLLOW_this__in_variable_assignment1059);
                    this_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_dot_in_variable_assignment1061);
                    dot();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:181:17: super_ dot
                    {
                    pushFollow(FOLLOW_super__in_variable_assignment1065);
                    super_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_dot_in_variable_assignment1067);
                    dot();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_variable_name_in_variable_assignment1071);
            variable_name();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_assign_in_variable_assignment1073);
            assign();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_value_in_variable_assignment1075);
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
            if ( state.backtracking>0 ) { memoize(input, 29, variable_assignment_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "variable_assignment"


    // $ANTLR start "for_loop"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:1: for_loop : ( for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon ( value )? semicolon ( statement_wosemicolon ( comma statement_wosemicolon )* )? CLOSE_BRACKET code | for_ OPEN_BRACKET variable_type variable_name colon value CLOSE_BRACKET code );
    public final void for_loop() throws RecognitionException {
        int for_loop_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:9: ( for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon ( value )? semicolon ( statement_wosemicolon ( comma statement_wosemicolon )* )? CLOSE_BRACKET code | for_ OPEN_BRACKET variable_type variable_name colon value CLOSE_BRACKET code )
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==FOR) ) {
                int LA76_1 = input.LA(2);

                if ( (synpred121_JavaParser()) ) {
                    alt76=1;
                }
                else if ( (true) ) {
                    alt76=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 76, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 76, 0, input);

                throw nvae;
            }
            switch (alt76) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:11: for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon ( value )? semicolon ( statement_wosemicolon ( comma statement_wosemicolon )* )? CLOSE_BRACKET code
                    {
                    pushFollow(FOLLOW_for__in_for_loop1085);
                    for_();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_for_loop1087); if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:29: ( variable_def ( comma variable_def )* )?
                    int alt72=2;
                    int LA72_0 = input.LA(1);

                    if ( (LA72_0==VOID||(LA72_0>=BOOLEAN && LA72_0<=DOUBLE)||LA72_0==AT||LA72_0==ID) ) {
                        alt72=1;
                    }
                    switch (alt72) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:30: variable_def ( comma variable_def )*
                            {
                            pushFollow(FOLLOW_variable_def_in_for_loop1090);
                            variable_def();

                            state._fsp--;
                            if (state.failed) return ;
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:43: ( comma variable_def )*
                            loop71:
                            do {
                                int alt71=2;
                                int LA71_0 = input.LA(1);

                                if ( (LA71_0==COMMA) ) {
                                    alt71=1;
                                }


                                switch (alt71) {
                            	case 1 :
                            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:44: comma variable_def
                            	    {
                            	    pushFollow(FOLLOW_comma_in_for_loop1093);
                            	    comma();

                            	    state._fsp--;
                            	    if (state.failed) return ;
                            	    pushFollow(FOLLOW_variable_def_in_for_loop1095);
                            	    variable_def();

                            	    state._fsp--;
                            	    if (state.failed) return ;

                            	    }
                            	    break;

                            	default :
                            	    break loop71;
                                }
                            } while (true);


                            }
                            break;

                    }

                    pushFollow(FOLLOW_semicolon_in_for_loop1101);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:77: ( value )?
                    int alt73=2;
                    int LA73_0 = input.LA(1);

                    if ( (LA73_0==NEW||(LA73_0>=NULL && LA73_0<=SUPER)||(LA73_0>=INC && LA73_0<=DEC)||LA73_0==NOT||LA73_0==OPEN_BRACKET||(LA73_0>=BOOL_CONST && LA73_0<=LONG_CONST)||LA73_0==FLOAT_CONST||(LA73_0>=STRING_CONST && LA73_0<=CHAR_CONST)) ) {
                        alt73=1;
                    }
                    switch (alt73) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: value
                            {
                            pushFollow(FOLLOW_value_in_for_loop1103);
                            value();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_semicolon_in_for_loop1106);
                    semicolon();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:94: ( statement_wosemicolon ( comma statement_wosemicolon )* )?
                    int alt75=2;
                    int LA75_0 = input.LA(1);

                    if ( (LA75_0==VOID||LA75_0==RETURN||(LA75_0>=THIS && LA75_0<=IF)||LA75_0==TRY||(LA75_0>=BOOLEAN && LA75_0<=DOUBLE)||(LA75_0>=INC && LA75_0<=DEC)||LA75_0==NOT||LA75_0==AT||LA75_0==ID) ) {
                        alt75=1;
                    }
                    switch (alt75) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:95: statement_wosemicolon ( comma statement_wosemicolon )*
                            {
                            pushFollow(FOLLOW_statement_wosemicolon_in_for_loop1109);
                            statement_wosemicolon();

                            state._fsp--;
                            if (state.failed) return ;
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:117: ( comma statement_wosemicolon )*
                            loop74:
                            do {
                                int alt74=2;
                                int LA74_0 = input.LA(1);

                                if ( (LA74_0==COMMA) ) {
                                    alt74=1;
                                }


                                switch (alt74) {
                            	case 1 :
                            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:118: comma statement_wosemicolon
                            	    {
                            	    pushFollow(FOLLOW_comma_in_for_loop1112);
                            	    comma();

                            	    state._fsp--;
                            	    if (state.failed) return ;
                            	    pushFollow(FOLLOW_statement_wosemicolon_in_for_loop1114);
                            	    statement_wosemicolon();

                            	    state._fsp--;
                            	    if (state.failed) return ;

                            	    }
                            	    break;

                            	default :
                            	    break loop74;
                                }
                            } while (true);


                            }
                            break;

                    }

                    match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_for_loop1120); if (state.failed) return ;
                    pushFollow(FOLLOW_code_in_for_loop1122);
                    code();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:185:4: for_ OPEN_BRACKET variable_type variable_name colon value CLOSE_BRACKET code
                    {
                    pushFollow(FOLLOW_for__in_for_loop1127);
                    for_();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_for_loop1129); if (state.failed) return ;
                    pushFollow(FOLLOW_variable_type_in_for_loop1131);
                    variable_type();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_variable_name_in_for_loop1133);
                    variable_name();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_colon_in_for_loop1135);
                    colon();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_value_in_for_loop1137);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_for_loop1139); if (state.failed) return ;
                    pushFollow(FOLLOW_code_in_for_loop1141);
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
            if ( state.backtracking>0 ) { memoize(input, 30, for_loop_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "for_loop"


    // $ANTLR start "while_loop"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:188:1: while_loop : while_ OPEN_BRACKET value CLOSE_BRACKET code ;
    public final void while_loop() throws RecognitionException {
        int while_loop_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:189:2: ( while_ OPEN_BRACKET value CLOSE_BRACKET code )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:189:4: while_ OPEN_BRACKET value CLOSE_BRACKET code
            {
            pushFollow(FOLLOW_while__in_while_loop1153);
            while_();

            state._fsp--;
            if (state.failed) return ;
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_while_loop1155); if (state.failed) return ;
            pushFollow(FOLLOW_value_in_while_loop1157);
            value();

            state._fsp--;
            if (state.failed) return ;
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_while_loop1159); if (state.failed) return ;
            pushFollow(FOLLOW_code_in_while_loop1161);
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
            if ( state.backtracking>0 ) { memoize(input, 31, while_loop_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "while_loop"


    // $ANTLR start "do_loop"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:192:1: do_loop : do_ code while_ OPEN_BRACKET value CLOSE_BRACKET ;
    public final void do_loop() throws RecognitionException {
        int do_loop_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:192:9: ( do_ code while_ OPEN_BRACKET value CLOSE_BRACKET )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:192:11: do_ code while_ OPEN_BRACKET value CLOSE_BRACKET
            {
            pushFollow(FOLLOW_do__in_do_loop1172);
            do_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_code_in_do_loop1174);
            code();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_while__in_do_loop1176);
            while_();

            state._fsp--;
            if (state.failed) return ;
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_do_loop1178); if (state.failed) return ;
            pushFollow(FOLLOW_value_in_do_loop1180);
            value();

            state._fsp--;
            if (state.failed) return ;
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_do_loop1182); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 32, do_loop_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "do_loop"


    // $ANTLR start "if_else"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:195:1: if_else : if_ OPEN_BRACKET value CLOSE_BRACKET code ( else_ code )? ;
    public final void if_else() throws RecognitionException {
        int if_else_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:195:9: ( if_ OPEN_BRACKET value CLOSE_BRACKET code ( else_ code )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:195:11: if_ OPEN_BRACKET value CLOSE_BRACKET code ( else_ code )?
            {
            pushFollow(FOLLOW_if__in_if_else1193);
            if_();

            state._fsp--;
            if (state.failed) return ;
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_if_else1195); if (state.failed) return ;
            pushFollow(FOLLOW_value_in_if_else1197);
            value();

            state._fsp--;
            if (state.failed) return ;
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_if_else1199); if (state.failed) return ;
            pushFollow(FOLLOW_code_in_if_else1201);
            code();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:195:53: ( else_ code )?
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==ELSE) ) {
                int LA77_1 = input.LA(2);

                if ( (synpred122_JavaParser()) ) {
                    alt77=1;
                }
            }
            switch (alt77) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:195:54: else_ code
                    {
                    pushFollow(FOLLOW_else__in_if_else1204);
                    else_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_code_in_if_else1206);
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
            if ( state.backtracking>0 ) { memoize(input, 33, if_else_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "if_else"


    // $ANTLR start "try_catch"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:1: try_catch : try_ code ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )+ ( finally_ code )? ;
    public final void try_catch() throws RecognitionException {
        int try_catch_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:199:2: ( try_ code ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )+ ( finally_ code )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:199:4: try_ code ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )+ ( finally_ code )?
            {
            pushFollow(FOLLOW_try__in_try_catch1219);
            try_();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_code_in_try_catch1221);
            code();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:199:14: ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )+
            int cnt78=0;
            loop78:
            do {
                int alt78=2;
                int LA78_0 = input.LA(1);

                if ( (LA78_0==CATCH) ) {
                    int LA78_2 = input.LA(2);

                    if ( (synpred123_JavaParser()) ) {
                        alt78=1;
                    }


                }


                switch (alt78) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:199:15: catch_ OPEN_BRACKET id id CLOSE_BRACKET code
            	    {
            	    pushFollow(FOLLOW_catch__in_try_catch1224);
            	    catch_();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_try_catch1226); if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_try_catch1228);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    pushFollow(FOLLOW_id_in_try_catch1230);
            	    id();

            	    state._fsp--;
            	    if (state.failed) return ;
            	    match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_try_catch1232); if (state.failed) return ;
            	    pushFollow(FOLLOW_code_in_try_catch1234);
            	    code();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt78 >= 1 ) break loop78;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(78, input);
                        throw eee;
                }
                cnt78++;
            } while (true);

            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:199:62: ( finally_ code )?
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==FINALLY) ) {
                int LA79_1 = input.LA(2);

                if ( (synpred124_JavaParser()) ) {
                    alt79=1;
                }
            }
            switch (alt79) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:199:63: finally_ code
                    {
                    pushFollow(FOLLOW_finally__in_try_catch1239);
                    finally_();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_code_in_try_catch1241);
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
            if ( state.backtracking>0 ) { memoize(input, 34, try_catch_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "try_catch"


    // $ANTLR start "cast"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:202:1: cast : OPEN_BRACKET variable_type CLOSE_BRACKET ;
    public final void cast() throws RecognitionException {
        int cast_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 35) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:202:6: ( OPEN_BRACKET variable_type CLOSE_BRACKET )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:202:8: OPEN_BRACKET variable_type CLOSE_BRACKET
            {
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_cast1253); if (state.failed) return ;
            pushFollow(FOLLOW_variable_type_in_cast1255);
            variable_type();

            state._fsp--;
            if (state.failed) return ;
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_cast1257); if (state.failed) return ;
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
            if ( state.backtracking>0 ) { memoize(input, 35, cast_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "cast"


    // $ANTLR start "variable_type"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:205:1: variable_type : ( primitive ( array )? | class_name ( generic )? ( array )? | void_ );
    public final void variable_type() throws RecognitionException {
        int variable_type_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 36) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:206:2: ( primitive ( array )? | class_name ( generic )? ( array )? | void_ )
            int alt83=3;
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
                alt83=1;
                }
                break;
            case ID:
                {
                alt83=2;
                }
                break;
            case VOID:
                {
                alt83=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 83, 0, input);

                throw nvae;
            }

            switch (alt83) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:206:4: primitive ( array )?
                    {
                    pushFollow(FOLLOW_primitive_in_variable_type1270);
                    primitive();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:206:14: ( array )?
                    int alt80=2;
                    int LA80_0 = input.LA(1);

                    if ( (LA80_0==OPEN_RECT_BRACKET) ) {
                        alt80=1;
                    }
                    switch (alt80) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: array
                            {
                            pushFollow(FOLLOW_array_in_variable_type1272);
                            array();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:207:4: class_name ( generic )? ( array )?
                    {
                    pushFollow(FOLLOW_class_name_in_variable_type1278);
                    class_name();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:207:15: ( generic )?
                    int alt81=2;
                    int LA81_0 = input.LA(1);

                    if ( (LA81_0==LT) ) {
                        alt81=1;
                    }
                    switch (alt81) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: generic
                            {
                            pushFollow(FOLLOW_generic_in_variable_type1280);
                            generic();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:207:24: ( array )?
                    int alt82=2;
                    int LA82_0 = input.LA(1);

                    if ( (LA82_0==OPEN_RECT_BRACKET) ) {
                        alt82=1;
                    }
                    switch (alt82) {
                        case 1 :
                            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: array
                            {
                            pushFollow(FOLLOW_array_in_variable_type1283);
                            array();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:208:4: void_
                    {
                    pushFollow(FOLLOW_void__in_variable_type1289);
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
            if ( state.backtracking>0 ) { memoize(input, 36, variable_type_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "variable_type"

    public static class id_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "id"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:211:1: id : ID ;
    public final JavaParser.id_return id() throws RecognitionException {
        JavaParser.id_return retval = new JavaParser.id_return();
        retval.start = input.LT(1);
        int id_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 37) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:211:4: ( ID )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:211:6: ID
            {
            match(input,ID,FOLLOW_ID_in_id1299); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 37, id_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "id"

    public static class binary_operator_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "binary_operator"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:213:1: binary_operator : ( PLUS | MINUS | STAR | SLASH | EQUAL | UNEQUAL | LT | GT | LE | GE | LOGICAL_OR | BIT_OR | LOGICAL_AND | BIT_AND );
    public final JavaParser.binary_operator_return binary_operator() throws RecognitionException {
        JavaParser.binary_operator_return retval = new JavaParser.binary_operator_return();
        retval.start = input.LT(1);
        int binary_operator_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:2: ( PLUS | MINUS | STAR | SLASH | EQUAL | UNEQUAL | LT | GT | LE | GE | LOGICAL_OR | BIT_OR | LOGICAL_AND | BIT_AND )
            int alt84=14;
            switch ( input.LA(1) ) {
            case PLUS:
                {
                alt84=1;
                }
                break;
            case MINUS:
                {
                alt84=2;
                }
                break;
            case STAR:
                {
                alt84=3;
                }
                break;
            case SLASH:
                {
                alt84=4;
                }
                break;
            case EQUAL:
                {
                alt84=5;
                }
                break;
            case UNEQUAL:
                {
                alt84=6;
                }
                break;
            case LT:
                {
                alt84=7;
                }
                break;
            case GT:
                {
                alt84=8;
                }
                break;
            case LE:
                {
                alt84=9;
                }
                break;
            case GE:
                {
                alt84=10;
                }
                break;
            case LOGICAL_OR:
                {
                alt84=11;
                }
                break;
            case BIT_OR:
                {
                alt84=12;
                }
                break;
            case LOGICAL_AND:
                {
                alt84=13;
                }
                break;
            case BIT_AND:
                {
                alt84=14;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;
            }

            switch (alt84) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:4: PLUS
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_binary_operator1310); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:215:4: MINUS
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_binary_operator1317); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:216:4: STAR
                    {
                    match(input,STAR,FOLLOW_STAR_in_binary_operator1324); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:217:4: SLASH
                    {
                    match(input,SLASH,FOLLOW_SLASH_in_binary_operator1331); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 5 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:218:4: EQUAL
                    {
                    match(input,EQUAL,FOLLOW_EQUAL_in_binary_operator1338); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 6 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:219:4: UNEQUAL
                    {
                    match(input,UNEQUAL,FOLLOW_UNEQUAL_in_binary_operator1345); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 7 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:220:4: LT
                    {
                    match(input,LT,FOLLOW_LT_in_binary_operator1352); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 8 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:221:4: GT
                    {
                    match(input,GT,FOLLOW_GT_in_binary_operator1359); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 9 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:222:4: LE
                    {
                    match(input,LE,FOLLOW_LE_in_binary_operator1366); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 10 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:223:4: GE
                    {
                    match(input,GE,FOLLOW_GE_in_binary_operator1373); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 11 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:224:4: LOGICAL_OR
                    {
                    match(input,LOGICAL_OR,FOLLOW_LOGICAL_OR_in_binary_operator1380); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 12 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:225:4: BIT_OR
                    {
                    match(input,BIT_OR,FOLLOW_BIT_OR_in_binary_operator1387); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 13 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:226:4: LOGICAL_AND
                    {
                    match(input,LOGICAL_AND,FOLLOW_LOGICAL_AND_in_binary_operator1394); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 14 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:227:4: BIT_AND
                    {
                    match(input,BIT_AND,FOLLOW_BIT_AND_in_binary_operator1401); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 38, binary_operator_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "binary_operator"

    public static class unary_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "unary"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:230:1: unary : ( INC | DEC | NOT );
    public final JavaParser.unary_return unary() throws RecognitionException {
        JavaParser.unary_return retval = new JavaParser.unary_return();
        retval.start = input.LT(1);
        int unary_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:230:7: ( INC | DEC | NOT )
            int alt85=3;
            switch ( input.LA(1) ) {
            case INC:
                {
                alt85=1;
                }
                break;
            case DEC:
                {
                alt85=2;
                }
                break;
            case NOT:
                {
                alt85=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 85, 0, input);

                throw nvae;
            }

            switch (alt85) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:230:9: INC
                    {
                    match(input,INC,FOLLOW_INC_in_unary1413); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:231:4: DEC
                    {
                    match(input,DEC,FOLLOW_DEC_in_unary1420); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:232:4: NOT
                    {
                    match(input,NOT,FOLLOW_NOT_in_unary1427); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 39, unary_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "unary"

    public static class primitive_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "primitive"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:235:1: primitive : ( BOOLEAN | BYTE | CHAR | SHORT | INTEGER | LONG | FLOAT | DOUBLE );
    public final JavaParser.primitive_return primitive() throws RecognitionException {
        JavaParser.primitive_return retval = new JavaParser.primitive_return();
        retval.start = input.LT(1);
        int primitive_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 40) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:236:2: ( BOOLEAN | BYTE | CHAR | SHORT | INTEGER | LONG | FLOAT | DOUBLE )
            int alt86=8;
            switch ( input.LA(1) ) {
            case BOOLEAN:
                {
                alt86=1;
                }
                break;
            case BYTE:
                {
                alt86=2;
                }
                break;
            case CHAR:
                {
                alt86=3;
                }
                break;
            case SHORT:
                {
                alt86=4;
                }
                break;
            case INTEGER:
                {
                alt86=5;
                }
                break;
            case LONG:
                {
                alt86=6;
                }
                break;
            case FLOAT:
                {
                alt86=7;
                }
                break;
            case DOUBLE:
                {
                alt86=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 86, 0, input);

                throw nvae;
            }

            switch (alt86) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:236:4: BOOLEAN
                    {
                    match(input,BOOLEAN,FOLLOW_BOOLEAN_in_primitive1440); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:237:4: BYTE
                    {
                    match(input,BYTE,FOLLOW_BYTE_in_primitive1447); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:238:4: CHAR
                    {
                    match(input,CHAR,FOLLOW_CHAR_in_primitive1454); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 4 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:239:4: SHORT
                    {
                    match(input,SHORT,FOLLOW_SHORT_in_primitive1461); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 5 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:240:4: INTEGER
                    {
                    match(input,INTEGER,FOLLOW_INTEGER_in_primitive1468); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 6 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:241:4: LONG
                    {
                    match(input,LONG,FOLLOW_LONG_in_primitive1475); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 7 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:242:4: FLOAT
                    {
                    match(input,FLOAT,FOLLOW_FLOAT_in_primitive1482); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperant(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 8 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:243:4: DOUBLE
                    {
                    match(input,DOUBLE,FOLLOW_DOUBLE_in_primitive1489); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 40, primitive_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "primitive"

    public static class int_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "int_const"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:251:1: int_const : INT_CONST ;
    public final JavaParser.int_const_return int_const() throws RecognitionException {
        JavaParser.int_const_return retval = new JavaParser.int_const_return();
        retval.start = input.LT(1);
        int int_const_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:252:2: ( INT_CONST )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:252:4: INT_CONST
            {
            match(input,INT_CONST,FOLLOW_INT_CONST_in_int_const1507); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 41, int_const_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "int_const"

    public static class long_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "long_const"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:253:1: long_const : LONG_CONST ;
    public final JavaParser.long_const_return long_const() throws RecognitionException {
        JavaParser.long_const_return retval = new JavaParser.long_const_return();
        retval.start = input.LT(1);
        int long_const_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 42) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:254:2: ( LONG_CONST )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:254:4: LONG_CONST
            {
            match(input,LONG_CONST,FOLLOW_LONG_CONST_in_long_const1517); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 42, long_const_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "long_const"

    public static class string_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "string_const"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:255:1: string_const : STRING_CONST ;
    public final JavaParser.string_const_return string_const() throws RecognitionException {
        JavaParser.string_const_return retval = new JavaParser.string_const_return();
        retval.start = input.LT(1);
        int string_const_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 43) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:256:2: ( STRING_CONST )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:256:4: STRING_CONST
            {
            match(input,STRING_CONST,FOLLOW_STRING_CONST_in_string_const1527); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 43, string_const_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "string_const"

    public static class float_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "float_const"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:257:1: float_const : FLOAT_CONST ;
    public final JavaParser.float_const_return float_const() throws RecognitionException {
        JavaParser.float_const_return retval = new JavaParser.float_const_return();
        retval.start = input.LT(1);
        int float_const_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 44) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:258:2: ( FLOAT_CONST )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:258:4: FLOAT_CONST
            {
            match(input,FLOAT_CONST,FOLLOW_FLOAT_CONST_in_float_const1537); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 44, float_const_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "float_const"

    public static class char_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "char_const"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:259:1: char_const : CHAR_CONST ;
    public final JavaParser.char_const_return char_const() throws RecognitionException {
        JavaParser.char_const_return retval = new JavaParser.char_const_return();
        retval.start = input.LT(1);
        int char_const_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 45) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:260:2: ( CHAR_CONST )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:260:4: CHAR_CONST
            {
            match(input,CHAR_CONST,FOLLOW_CHAR_CONST_in_char_const1547); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 45, char_const_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "char_const"

    public static class null_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "null_const"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:261:1: null_const : NULL ;
    public final JavaParser.null_const_return null_const() throws RecognitionException {
        JavaParser.null_const_return retval = new JavaParser.null_const_return();
        retval.start = input.LT(1);
        int null_const_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 46) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:262:2: ( NULL )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:262:4: NULL
            {
            match(input,NULL,FOLLOW_NULL_in_null_const1557); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 46, null_const_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "null_const"

    public static class boolean_const_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "boolean_const"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:263:1: boolean_const : BOOL_CONST ;
    public final JavaParser.boolean_const_return boolean_const() throws RecognitionException {
        JavaParser.boolean_const_return retval = new JavaParser.boolean_const_return();
        retval.start = input.LT(1);
        int boolean_const_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 47) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:264:2: ( BOOL_CONST )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:264:4: BOOL_CONST
            {
            match(input,BOOL_CONST,FOLLOW_BOOL_CONST_in_boolean_const1568); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 47, boolean_const_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "boolean_const"

    public static class package_name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "package_name"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:270:1: package_name : ( ID DOT )* ID ;
    public final JavaParser.package_name_return package_name() throws RecognitionException {
        JavaParser.package_name_return retval = new JavaParser.package_name_return();
        retval.start = input.LT(1);
        int package_name_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 48) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:271:2: ( ( ID DOT )* ID )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:271:4: ( ID DOT )* ID
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:271:4: ( ID DOT )*
            loop87:
            do {
                int alt87=2;
                int LA87_0 = input.LA(1);

                if ( (LA87_0==ID) ) {
                    int LA87_1 = input.LA(2);

                    if ( (LA87_1==DOT) ) {
                        alt87=1;
                    }


                }


                switch (alt87) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:271:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_package_name1584); if (state.failed) return retval;
            	    match(input,DOT,FOLLOW_DOT_in_package_name1586); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop87;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_package_name1590); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 48, package_name_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "package_name"

    public static class import_name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "import_name"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:274:1: import_name : ( ID DOT )+ ( ID | STAR ) ;
    public final JavaParser.import_name_return import_name() throws RecognitionException {
        JavaParser.import_name_return retval = new JavaParser.import_name_return();
        retval.start = input.LT(1);
        int import_name_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 49) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:275:2: ( ( ID DOT )+ ( ID | STAR ) )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:275:4: ( ID DOT )+ ( ID | STAR )
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:275:4: ( ID DOT )+
            int cnt88=0;
            loop88:
            do {
                int alt88=2;
                int LA88_0 = input.LA(1);

                if ( (LA88_0==ID) ) {
                    int LA88_1 = input.LA(2);

                    if ( (LA88_1==DOT) ) {
                        alt88=1;
                    }


                }


                switch (alt88) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:275:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_import_name1604); if (state.failed) return retval;
            	    match(input,DOT,FOLLOW_DOT_in_import_name1606); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    if ( cnt88 >= 1 ) break loop88;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(88, input);
                        throw eee;
                }
                cnt88++;
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
            if ( state.backtracking>0 ) { memoize(input, 49, import_name_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "import_name"

    public static class class_name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "class_name"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:278:1: class_name : ( ID DOT )* ID ;
    public final JavaParser.class_name_return class_name() throws RecognitionException {
        JavaParser.class_name_return retval = new JavaParser.class_name_return();
        retval.start = input.LT(1);
        int class_name_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 50) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:279:2: ( ( ID DOT )* ID )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:279:4: ( ID DOT )* ID
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:279:4: ( ID DOT )*
            loop89:
            do {
                int alt89=2;
                int LA89_0 = input.LA(1);

                if ( (LA89_0==ID) ) {
                    int LA89_1 = input.LA(2);

                    if ( (LA89_1==DOT) ) {
                        int LA89_2 = input.LA(3);

                        if ( (LA89_2==ID) ) {
                            alt89=1;
                        }


                    }


                }


                switch (alt89) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:279:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_class_name1628); if (state.failed) return retval;
            	    match(input,DOT,FOLLOW_DOT_in_class_name1630); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop89;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_class_name1634); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 50, class_name_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "class_name"

    public static class method_name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "method_name"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:282:1: method_name : ( ID DOT )* ID ;
    public final JavaParser.method_name_return method_name() throws RecognitionException {
        JavaParser.method_name_return retval = new JavaParser.method_name_return();
        retval.start = input.LT(1);
        int method_name_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 51) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:283:2: ( ( ID DOT )* ID )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:283:4: ( ID DOT )* ID
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:283:4: ( ID DOT )*
            loop90:
            do {
                int alt90=2;
                int LA90_0 = input.LA(1);

                if ( (LA90_0==ID) ) {
                    int LA90_1 = input.LA(2);

                    if ( (LA90_1==DOT) ) {
                        alt90=1;
                    }


                }


                switch (alt90) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:283:5: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_method_name1648); if (state.failed) return retval;
            	    match(input,DOT,FOLLOW_DOT_in_method_name1650); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop90;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_method_name1654); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 51, method_name_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "method_name"


    // $ANTLR start "variable_name"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:286:1: variable_name : name ( array )? ;
    public final void variable_name() throws RecognitionException {
        int variable_name_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 52) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:287:2: ( name ( array )? )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:287:4: name ( array )?
            {
            pushFollow(FOLLOW_name_in_variable_name1667);
            name();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:287:9: ( array )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==OPEN_RECT_BRACKET) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: array
                    {
                    pushFollow(FOLLOW_array_in_variable_name1669);
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
            if ( state.backtracking>0 ) { memoize(input, 52, variable_name_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "variable_name"

    public static class annotation_name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "annotation_name"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:289:1: annotation_name : AT name ;
    public final JavaParser.annotation_name_return annotation_name() throws RecognitionException {
        JavaParser.annotation_name_return retval = new JavaParser.annotation_name_return();
        retval.start = input.LT(1);
        int annotation_name_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 53) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:290:2: ( AT name )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:290:4: AT name
            {
            match(input,AT,FOLLOW_AT_in_annotation_name1680); if (state.failed) return retval;
            pushFollow(FOLLOW_name_in_annotation_name1682);
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
            if ( state.backtracking>0 ) { memoize(input, 53, annotation_name_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "annotation_name"

    public static class name_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "name"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:291:1: name : ( ID DOT )* ID ;
    public final JavaParser.name_return name() throws RecognitionException {
        JavaParser.name_return retval = new JavaParser.name_return();
        retval.start = input.LT(1);
        int name_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 54) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:291:6: ( ( ID DOT )* ID )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:291:8: ( ID DOT )* ID
            {
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:291:8: ( ID DOT )*
            loop92:
            do {
                int alt92=2;
                int LA92_0 = input.LA(1);

                if ( (LA92_0==ID) ) {
                    int LA92_1 = input.LA(2);

                    if ( (LA92_1==DOT) ) {
                        alt92=1;
                    }


                }


                switch (alt92) {
            	case 1 :
            	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:291:9: ID DOT
            	    {
            	    match(input,ID,FOLLOW_ID_in_name1692); if (state.failed) return retval;
            	    match(input,DOT,FOLLOW_DOT_in_name1694); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop92;
                }
            } while (true);

            match(input,ID,FOLLOW_ID_in_name1698); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 54, name_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "name"


    // $ANTLR start "array"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:293:1: array : open_rect_bracket ( value )? close_rect_bracket ;
    public final void array() throws RecognitionException {
        int array_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 55) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:293:7: ( open_rect_bracket ( value )? close_rect_bracket )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:293:9: open_rect_bracket ( value )? close_rect_bracket
            {
            pushFollow(FOLLOW_open_rect_bracket_in_array1708);
            open_rect_bracket();

            state._fsp--;
            if (state.failed) return ;
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:293:27: ( value )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==NEW||(LA93_0>=NULL && LA93_0<=SUPER)||(LA93_0>=INC && LA93_0<=DEC)||LA93_0==NOT||LA93_0==OPEN_BRACKET||(LA93_0>=BOOL_CONST && LA93_0<=LONG_CONST)||LA93_0==FLOAT_CONST||(LA93_0>=STRING_CONST && LA93_0<=CHAR_CONST)) ) {
                alt93=1;
            }
            switch (alt93) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: value
                    {
                    pushFollow(FOLLOW_value_in_array1710);
                    value();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_close_rect_bracket_in_array1713);
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
            if ( state.backtracking>0 ) { memoize(input, 55, array_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "array"

    public static class package__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "package_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:299:1: package_ : PACKAGE ;
    public final JavaParser.package__return package_() throws RecognitionException {
        JavaParser.package__return retval = new JavaParser.package__return();
        retval.start = input.LT(1);
        int package__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 56) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:300:2: ( PACKAGE )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:300:4: PACKAGE
            {
            match(input,PACKAGE,FOLLOW_PACKAGE_in_package_1726); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 56, package__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "package_"

    public static class import__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "import_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:301:1: import_ : IMPORT ;
    public final JavaParser.import__return import_() throws RecognitionException {
        JavaParser.import__return retval = new JavaParser.import__return();
        retval.start = input.LT(1);
        int import__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 57) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:302:2: ( IMPORT )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:302:4: IMPORT
            {
            match(input,IMPORT,FOLLOW_IMPORT_in_import_1737); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 57, import__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "import_"

    public static class class__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "class_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:303:1: class_ : CLASS ;
    public final JavaParser.class__return class_() throws RecognitionException {
        JavaParser.class__return retval = new JavaParser.class__return();
        retval.start = input.LT(1);
        int class__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 58) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:303:8: ( CLASS )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:303:10: CLASS
            {
            match(input,CLASS,FOLLOW_CLASS_in_class_1747); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 58, class__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "class_"

    public static class enum__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "enum_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:304:1: enum_ : ENUM ;
    public final JavaParser.enum__return enum_() throws RecognitionException {
        JavaParser.enum__return retval = new JavaParser.enum__return();
        retval.start = input.LT(1);
        int enum__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 59) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:304:7: ( ENUM )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:304:9: ENUM
            {
            match(input,ENUM,FOLLOW_ENUM_in_enum_1756); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 59, enum__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "enum_"

    public static class interface__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "interface_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:305:1: interface_ : INTERFACE ;
    public final JavaParser.interface__return interface_() throws RecognitionException {
        JavaParser.interface__return retval = new JavaParser.interface__return();
        retval.start = input.LT(1);
        int interface__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 60) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:306:2: ( INTERFACE )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:306:4: INTERFACE
            {
            match(input,INTERFACE,FOLLOW_INTERFACE_in_interface_1766); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 60, interface__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "interface_"

    public static class extends__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "extends_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:307:1: extends_ : EXTENDS ;
    public final JavaParser.extends__return extends_() throws RecognitionException {
        JavaParser.extends__return retval = new JavaParser.extends__return();
        retval.start = input.LT(1);
        int extends__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 61) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:307:9: ( EXTENDS )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:307:11: EXTENDS
            {
            match(input,EXTENDS,FOLLOW_EXTENDS_in_extends_1774); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 61, extends__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "extends_"

    public static class implements__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "implements_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:308:1: implements_ : IMPLEMENTS ;
    public final JavaParser.implements__return implements_() throws RecognitionException {
        JavaParser.implements__return retval = new JavaParser.implements__return();
        retval.start = input.LT(1);
        int implements__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 62) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:309:2: ( IMPLEMENTS )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:309:4: IMPLEMENTS
            {
            match(input,IMPLEMENTS,FOLLOW_IMPLEMENTS_in_implements_1784); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 62, implements__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "implements_"

    public static class this__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "this_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:310:1: this_ : THIS ;
    public final JavaParser.this__return this_() throws RecognitionException {
        JavaParser.this__return retval = new JavaParser.this__return();
        retval.start = input.LT(1);
        int this__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 63) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:310:7: ( THIS )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:310:9: THIS
            {
            match(input,THIS,FOLLOW_THIS_in_this_1793); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 63, this__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "this_"

    public static class super__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "super_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:311:1: super_ : SUPER ;
    public final JavaParser.super__return super_() throws RecognitionException {
        JavaParser.super__return retval = new JavaParser.super__return();
        retval.start = input.LT(1);
        int super__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 64) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:311:8: ( SUPER )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:311:10: SUPER
            {
            match(input,SUPER,FOLLOW_SUPER_in_super_1802); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 64, super__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "super_"

    public static class void__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "void_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:312:1: void_ : VOID ;
    public final JavaParser.void__return void_() throws RecognitionException {
        JavaParser.void__return retval = new JavaParser.void__return();
        retval.start = input.LT(1);
        int void__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 65) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:312:7: ( VOID )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:312:9: VOID
            {
            match(input,VOID,FOLLOW_VOID_in_void_1811); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 65, void__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "void_"

    public static class public__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "public_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:313:1: public_ : PUBLIC ;
    public final JavaParser.public__return public_() throws RecognitionException {
        JavaParser.public__return retval = new JavaParser.public__return();
        retval.start = input.LT(1);
        int public__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 66) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:313:9: ( PUBLIC )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:313:11: PUBLIC
            {
            match(input,PUBLIC,FOLLOW_PUBLIC_in_public_1820); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 66, public__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "public_"

    public static class private__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "private_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:314:1: private_ : PRIVATE ;
    public final JavaParser.private__return private_() throws RecognitionException {
        JavaParser.private__return retval = new JavaParser.private__return();
        retval.start = input.LT(1);
        int private__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 67) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:314:9: ( PRIVATE )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:314:11: PRIVATE
            {
            match(input,PRIVATE,FOLLOW_PRIVATE_in_private_1828); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 67, private__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "private_"

    public static class protected__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "protected_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:315:1: protected_ : PROTECTED ;
    public final JavaParser.protected__return protected_() throws RecognitionException {
        JavaParser.protected__return retval = new JavaParser.protected__return();
        retval.start = input.LT(1);
        int protected__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 68) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:316:2: ( PROTECTED )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:316:4: PROTECTED
            {
            match(input,PROTECTED,FOLLOW_PROTECTED_in_protected_1838); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 68, protected__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "protected_"

    public static class static__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "static_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:317:1: static_ : STATIC ;
    public final JavaParser.static__return static_() throws RecognitionException {
        JavaParser.static__return retval = new JavaParser.static__return();
        retval.start = input.LT(1);
        int static__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 69) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:317:9: ( STATIC )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:317:11: STATIC
            {
            match(input,STATIC,FOLLOW_STATIC_in_static_1847); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 69, static__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "static_"

    public static class final__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "final_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:318:1: final_ : FINAL ;
    public final JavaParser.final__return final_() throws RecognitionException {
        JavaParser.final__return retval = new JavaParser.final__return();
        retval.start = input.LT(1);
        int final__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 70) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:318:8: ( FINAL )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:318:10: FINAL
            {
            match(input,FINAL,FOLLOW_FINAL_in_final_1856); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 70, final__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "final_"

    public static class transient__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "transient_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:319:1: transient_ : TRANSIENT ;
    public final JavaParser.transient__return transient_() throws RecognitionException {
        JavaParser.transient__return retval = new JavaParser.transient__return();
        retval.start = input.LT(1);
        int transient__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 71) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:320:2: ( TRANSIENT )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:320:4: TRANSIENT
            {
            match(input,TRANSIENT,FOLLOW_TRANSIENT_in_transient_1866); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 71, transient__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "transient_"

    public static class new__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "new_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:321:1: new_ : NEW ;
    public final JavaParser.new__return new_() throws RecognitionException {
        JavaParser.new__return retval = new JavaParser.new__return();
        retval.start = input.LT(1);
        int new__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 72) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:321:6: ( NEW )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:321:8: NEW
            {
            match(input,NEW,FOLLOW_NEW_in_new_1875); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 72, new__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "new_"

    public static class try__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "try_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:322:1: try_ : TRY ;
    public final JavaParser.try__return try_() throws RecognitionException {
        JavaParser.try__return retval = new JavaParser.try__return();
        retval.start = input.LT(1);
        int try__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 73) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:322:6: ( TRY )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:322:8: TRY
            {
            match(input,TRY,FOLLOW_TRY_in_try_1884); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 73, try__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "try_"

    public static class catch__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "catch_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:323:1: catch_ : CATCH ;
    public final JavaParser.catch__return catch_() throws RecognitionException {
        JavaParser.catch__return retval = new JavaParser.catch__return();
        retval.start = input.LT(1);
        int catch__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 74) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:323:8: ( CATCH )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:323:10: CATCH
            {
            match(input,CATCH,FOLLOW_CATCH_in_catch_1893); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 74, catch__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "catch_"

    public static class finally__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "finally_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:324:1: finally_ : FINALLY ;
    public final JavaParser.finally__return finally_() throws RecognitionException {
        JavaParser.finally__return retval = new JavaParser.finally__return();
        retval.start = input.LT(1);
        int finally__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 75) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:324:9: ( FINALLY )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:324:11: FINALLY
            {
            match(input,FINALLY,FOLLOW_FINALLY_in_finally_1901); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 75, finally__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "finally_"

    public static class throws__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "throws_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:325:1: throws_ : THROWS ;
    public final JavaParser.throws__return throws_() throws RecognitionException {
        JavaParser.throws__return retval = new JavaParser.throws__return();
        retval.start = input.LT(1);
        int throws__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 76) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:325:9: ( THROWS )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:325:11: THROWS
            {
            match(input,THROWS,FOLLOW_THROWS_in_throws_1910); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 76, throws__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "throws_"

    public static class throw__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "throw_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:326:1: throw_ : THROW ;
    public final JavaParser.throw__return throw_() throws RecognitionException {
        JavaParser.throw__return retval = new JavaParser.throw__return();
        retval.start = input.LT(1);
        int throw__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 77) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:326:8: ( THROW )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:326:10: THROW
            {
            match(input,THROW,FOLLOW_THROW_in_throw_1919); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 77, throw__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "throw_"


    // $ANTLR start "for_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:328:1: for_ : FOR ;
    public final void for_() throws RecognitionException {
        int for__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 78) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:328:6: ( FOR )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:328:8: FOR
            {
            match(input,FOR,FOLLOW_FOR_in_for_1929); if (state.failed) return ;
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
            if ( state.backtracking>0 ) { memoize(input, 78, for__StartIndex); }
        }
        return ;
    }
    // $ANTLR end "for_"


    // $ANTLR start "while_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:329:1: while_ : WHILE ;
    public final void while_() throws RecognitionException {
        int while__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 79) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:329:8: ( WHILE )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:329:10: WHILE
            {
            match(input,WHILE,FOLLOW_WHILE_in_while_1938); if (state.failed) return ;
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
            if ( state.backtracking>0 ) { memoize(input, 79, while__StartIndex); }
        }
        return ;
    }
    // $ANTLR end "while_"


    // $ANTLR start "do_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:330:1: do_ : DO ;
    public final void do_() throws RecognitionException {
        int do__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 80) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:330:5: ( DO )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:330:7: DO
            {
            match(input,DO,FOLLOW_DO_in_do_1947); if (state.failed) return ;
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
            if ( state.backtracking>0 ) { memoize(input, 80, do__StartIndex); }
        }
        return ;
    }
    // $ANTLR end "do_"


    // $ANTLR start "if_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:331:1: if_ : IF ;
    public final void if_() throws RecognitionException {
        int if__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 81) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:331:5: ( IF )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:331:7: IF
            {
            match(input,IF,FOLLOW_IF_in_if_1956); if (state.failed) return ;
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
            if ( state.backtracking>0 ) { memoize(input, 81, if__StartIndex); }
        }
        return ;
    }
    // $ANTLR end "if_"


    // $ANTLR start "else_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:332:1: else_ : ELSE ;
    public final void else_() throws RecognitionException {
        int else__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 82) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:332:7: ( ELSE )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:332:9: ELSE
            {
            match(input,ELSE,FOLLOW_ELSE_in_else_1965); if (state.failed) return ;
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
            if ( state.backtracking>0 ) { memoize(input, 82, else__StartIndex); }
        }
        return ;
    }
    // $ANTLR end "else_"

    public static class return__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "return_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:334:1: return_ : RETURN ;
    public final JavaParser.return__return return_() throws RecognitionException {
        JavaParser.return__return retval = new JavaParser.return__return();
        retval.start = input.LT(1);
        int return__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 83) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:334:9: ( RETURN )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:334:11: RETURN
            {
            match(input,RETURN,FOLLOW_RETURN_in_return_1975); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 83, return__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "return_"

    public static class break__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "break_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:335:1: break_ : BREAK ;
    public final JavaParser.break__return break_() throws RecognitionException {
        JavaParser.break__return retval = new JavaParser.break__return();
        retval.start = input.LT(1);
        int break__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 84) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:335:8: ( BREAK )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:335:10: BREAK
            {
            match(input,BREAK,FOLLOW_BREAK_in_break_1984); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 84, break__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "break_"

    public static class continue__return extends ParserRuleReturnScope {
    };

    // $ANTLR start "continue_"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:336:1: continue_ : CONTINUE ;
    public final JavaParser.continue__return continue_() throws RecognitionException {
        JavaParser.continue__return retval = new JavaParser.continue__return();
        retval.start = input.LT(1);
        int continue__StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 85) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:337:2: ( CONTINUE )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:337:4: CONTINUE
            {
            match(input,CONTINUE,FOLLOW_CONTINUE_in_continue_1994); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 85, continue__StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "continue_"

    public static class semicolon_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "semicolon"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:343:1: semicolon : SEMICOLON ;
    public final JavaParser.semicolon_return semicolon() throws RecognitionException {
        JavaParser.semicolon_return retval = new JavaParser.semicolon_return();
        retval.start = input.LT(1);
        int semicolon_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 86) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:344:2: ( SEMICOLON )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:344:4: SEMICOLON
            {
            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_semicolon2009); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 86, semicolon_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "semicolon"

    public static class comma_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "comma"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:345:1: comma : COMMA ;
    public final JavaParser.comma_return comma() throws RecognitionException {
        JavaParser.comma_return retval = new JavaParser.comma_return();
        retval.start = input.LT(1);
        int comma_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 87) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:345:7: ( COMMA )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:345:9: COMMA
            {
            match(input,COMMA,FOLLOW_COMMA_in_comma2018); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 87, comma_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "comma"

    public static class colon_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "colon"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:346:1: colon : COLON ;
    public final JavaParser.colon_return colon() throws RecognitionException {
        JavaParser.colon_return retval = new JavaParser.colon_return();
        retval.start = input.LT(1);
        int colon_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 88) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:346:7: ( COLON )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:346:9: COLON
            {
            match(input,COLON,FOLLOW_COLON_in_colon2027); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 88, colon_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "colon"

    public static class dot_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "dot"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:347:1: dot : DOT ;
    public final JavaParser.dot_return dot() throws RecognitionException {
        JavaParser.dot_return retval = new JavaParser.dot_return();
        retval.start = input.LT(1);
        int dot_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 89) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:347:5: ( DOT )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:347:7: DOT
            {
            match(input,DOT,FOLLOW_DOT_in_dot2036); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 89, dot_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "dot"

    public static class assign_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "assign"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:348:1: assign : ( ASSIGN | INCASSIGN | DECASSIGN );
    public final JavaParser.assign_return assign() throws RecognitionException {
        JavaParser.assign_return retval = new JavaParser.assign_return();
        retval.start = input.LT(1);
        int assign_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 90) ) { return retval; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:348:8: ( ASSIGN | INCASSIGN | DECASSIGN )
            int alt94=3;
            switch ( input.LA(1) ) {
            case ASSIGN:
                {
                alt94=1;
                }
                break;
            case INCASSIGN:
                {
                alt94=2;
                }
                break;
            case DECASSIGN:
                {
                alt94=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 94, 0, input);

                throw nvae;
            }

            switch (alt94) {
                case 1 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:348:10: ASSIGN
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_assign2045); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 2 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:349:4: INCASSIGN
                    {
                    match(input,INCASSIGN,FOLLOW_INCASSIGN_in_assign2052); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      helper.registerOperator(input.toString(retval.start,input.LT(-1)));
                    }

                    }
                    break;
                case 3 :
                    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:350:4: DECASSIGN
                    {
                    match(input,DECASSIGN,FOLLOW_DECASSIGN_in_assign2059); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 90, assign_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "assign"


    // $ANTLR start "block_begin"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:352:1: block_begin : OPEN_CURLY_BRACKET ;
    public final void block_begin() throws RecognitionException {
        int block_begin_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 91) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:353:2: ( OPEN_CURLY_BRACKET )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:353:4: OPEN_CURLY_BRACKET
            {
            match(input,OPEN_CURLY_BRACKET,FOLLOW_OPEN_CURLY_BRACKET_in_block_begin2071); if (state.failed) return ;
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
            if ( state.backtracking>0 ) { memoize(input, 91, block_begin_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "block_begin"


    // $ANTLR start "block_end"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:354:1: block_end : CLOSE_CURLY_BRACKET ;
    public final void block_end() throws RecognitionException {
        int block_end_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 92) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:355:2: ( CLOSE_CURLY_BRACKET )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:355:4: CLOSE_CURLY_BRACKET
            {
            match(input,CLOSE_CURLY_BRACKET,FOLLOW_CLOSE_CURLY_BRACKET_in_block_end2081); if (state.failed) return ;
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
            if ( state.backtracking>0 ) { memoize(input, 92, block_end_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "block_end"


    // $ANTLR start "open_bracket"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:356:1: open_bracket : OPEN_BRACKET ;
    public final void open_bracket() throws RecognitionException {
        int open_bracket_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 93) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:357:2: ( OPEN_BRACKET )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:357:4: OPEN_BRACKET
            {
            match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_open_bracket2091); if (state.failed) return ;
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
            if ( state.backtracking>0 ) { memoize(input, 93, open_bracket_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "open_bracket"


    // $ANTLR start "close_bracket"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:358:1: close_bracket : CLOSE_BRACKET ;
    public final void close_bracket() throws RecognitionException {
        int close_bracket_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 94) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:359:2: ( CLOSE_BRACKET )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:359:4: CLOSE_BRACKET
            {
            match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_close_bracket2101); if (state.failed) return ;
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
            if ( state.backtracking>0 ) { memoize(input, 94, close_bracket_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "close_bracket"


    // $ANTLR start "open_rect_bracket"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:360:1: open_rect_bracket : OPEN_RECT_BRACKET ;
    public final void open_rect_bracket() throws RecognitionException {
        int open_rect_bracket_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 95) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:361:2: ( OPEN_RECT_BRACKET )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:361:4: OPEN_RECT_BRACKET
            {
            match(input,OPEN_RECT_BRACKET,FOLLOW_OPEN_RECT_BRACKET_in_open_rect_bracket2112); if (state.failed) return ;
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
            if ( state.backtracking>0 ) { memoize(input, 95, open_rect_bracket_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "open_rect_bracket"


    // $ANTLR start "close_rect_bracket"
    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:362:1: close_rect_bracket : CLOSE_RECT_BRACKET ;
    public final void close_rect_bracket() throws RecognitionException {
        int close_rect_bracket_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 96) ) { return ; }
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:363:2: ( CLOSE_RECT_BRACKET )
            // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:363:4: CLOSE_RECT_BRACKET
            {
            match(input,CLOSE_RECT_BRACKET,FOLLOW_CLOSE_RECT_BRACKET_in_close_rect_bracket2122); if (state.failed) return ;
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
            if ( state.backtracking>0 ) { memoize(input, 96, close_rect_bracket_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "close_rect_bracket"

    // $ANTLR start synpred2_JavaParser
    public final void synpred2_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:24:33: ( class_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:24:33: class_def
        {
        pushFollow(FOLLOW_class_def_in_synpred2_JavaParser50);
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
        pushFollow(FOLLOW_enum_def_in_synpred3_JavaParser54);
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
        pushFollow(FOLLOW_interface_def_in_synpred4_JavaParser58);
        interface_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred4_JavaParser

    // $ANTLR start synpred40_JavaParser
    public final void synpred40_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:82:17: ( class_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:82:17: class_def
        {
        pushFollow(FOLLOW_class_def_in_synpred40_JavaParser425);
        class_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred40_JavaParser

    // $ANTLR start synpred41_JavaParser
    public final void synpred41_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:82:29: ( constructor_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:82:29: constructor_def
        {
        pushFollow(FOLLOW_constructor_def_in_synpred41_JavaParser429);
        constructor_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred41_JavaParser

    // $ANTLR start synpred42_JavaParser
    public final void synpred42_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:82:47: ( method_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:82:47: method_def
        {
        pushFollow(FOLLOW_method_def_in_synpred42_JavaParser433);
        method_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred42_JavaParser

    // $ANTLR start synpred43_JavaParser
    public final void synpred43_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:82:60: ( field_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:82:60: field_def
        {
        pushFollow(FOLLOW_field_def_in_synpred43_JavaParser437);
        field_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred43_JavaParser

    // $ANTLR start synpred44_JavaParser
    public final void synpred44_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:17: ( class_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:17: class_def
        {
        pushFollow(FOLLOW_class_def_in_synpred44_JavaParser455);
        class_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred44_JavaParser

    // $ANTLR start synpred45_JavaParser
    public final void synpred45_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:29: ( constructor_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:29: constructor_def
        {
        pushFollow(FOLLOW_constructor_def_in_synpred45_JavaParser459);
        constructor_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred45_JavaParser

    // $ANTLR start synpred46_JavaParser
    public final void synpred46_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:47: ( method_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:47: method_def
        {
        pushFollow(FOLLOW_method_def_in_synpred46_JavaParser463);
        method_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred46_JavaParser

    // $ANTLR start synpred47_JavaParser
    public final void synpred47_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:60: ( field_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:60: field_def
        {
        pushFollow(FOLLOW_field_def_in_synpred47_JavaParser467);
        field_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred47_JavaParser

    // $ANTLR start synpred48_JavaParser
    public final void synpred48_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:72: ( enum_content )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:72: enum_content
        {
        pushFollow(FOLLOW_enum_content_in_synpred48_JavaParser471);
        enum_content();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred48_JavaParser

    // $ANTLR start synpred70_JavaParser
    public final void synpred70_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:9: ( ( cast )? ( unary )? single_value ( unary )? ( binary_operator value )? )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:9: ( cast )? ( unary )? single_value ( unary )? ( binary_operator value )?
        {
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:9: ( cast )?
        int alt112=2;
        int LA112_0 = input.LA(1);

        if ( (LA112_0==OPEN_BRACKET) ) {
            alt112=1;
        }
        switch (alt112) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: cast
                {
                pushFollow(FOLLOW_cast_in_synpred70_JavaParser699);
                cast();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:15: ( unary )?
        int alt113=2;
        int LA113_0 = input.LA(1);

        if ( ((LA113_0>=INC && LA113_0<=DEC)||LA113_0==NOT) ) {
            alt113=1;
        }
        switch (alt113) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                {
                pushFollow(FOLLOW_unary_in_synpred70_JavaParser702);
                unary();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        pushFollow(FOLLOW_single_value_in_synpred70_JavaParser705);
        single_value();

        state._fsp--;
        if (state.failed) return ;
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:35: ( unary )?
        int alt114=2;
        int LA114_0 = input.LA(1);

        if ( ((LA114_0>=INC && LA114_0<=DEC)||LA114_0==NOT) ) {
            alt114=1;
        }
        switch (alt114) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                {
                pushFollow(FOLLOW_unary_in_synpred70_JavaParser707);
                unary();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:42: ( binary_operator value )?
        int alt115=2;
        int LA115_0 = input.LA(1);

        if ( ((LA115_0>=LE && LA115_0<=UNEQUAL)||(LA115_0>=PLUS && LA115_0<=BIT_AND)||(LA115_0>=LT && LA115_0<=GT)) ) {
            alt115=1;
        }
        switch (alt115) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:43: binary_operator value
                {
                pushFollow(FOLLOW_binary_operator_in_synpred70_JavaParser711);
                binary_operator();

                state._fsp--;
                if (state.failed) return ;
                pushFollow(FOLLOW_value_in_synpred70_JavaParser713);
                value();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred70_JavaParser

    // $ANTLR start synpred88_JavaParser
    public final void synpred88_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:147:4: ( variable_assignment semicolon )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:147:4: variable_assignment semicolon
        {
        pushFollow(FOLLOW_variable_assignment_in_synpred88_JavaParser885);
        variable_assignment();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_semicolon_in_synpred88_JavaParser887);
        semicolon();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred88_JavaParser

    // $ANTLR start synpred89_JavaParser
    public final void synpred89_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:148:4: ( variable_def semicolon )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:148:4: variable_def semicolon
        {
        pushFollow(FOLLOW_variable_def_in_synpred89_JavaParser892);
        variable_def();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_semicolon_in_synpred89_JavaParser894);
        semicolon();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred89_JavaParser

    // $ANTLR start synpred90_JavaParser
    public final void synpred90_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:149:4: ( method_call semicolon )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:149:4: method_call semicolon
        {
        pushFollow(FOLLOW_method_call_in_synpred90_JavaParser899);
        method_call();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_semicolon_in_synpred90_JavaParser901);
        semicolon();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred90_JavaParser

    // $ANTLR start synpred96_JavaParser
    public final void synpred96_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:154:25: ( unary )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:154:25: unary
        {
        pushFollow(FOLLOW_unary_in_synpred96_JavaParser932);
        unary();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred96_JavaParser

    // $ANTLR start synpred97_JavaParser
    public final void synpred97_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:154:4: ( ( unary )? variable_name ( unary )? )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:154:4: ( unary )? variable_name ( unary )?
        {
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:154:4: ( unary )?
        int alt117=2;
        int LA117_0 = input.LA(1);

        if ( ((LA117_0>=INC && LA117_0<=DEC)||LA117_0==NOT) ) {
            alt117=1;
        }
        switch (alt117) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                {
                pushFollow(FOLLOW_unary_in_synpred97_JavaParser927);
                unary();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        pushFollow(FOLLOW_variable_name_in_synpred97_JavaParser930);
        variable_name();

        state._fsp--;
        if (state.failed) return ;
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:154:25: ( unary )?
        int alt118=2;
        int LA118_0 = input.LA(1);

        if ( ((LA118_0>=INC && LA118_0<=DEC)||LA118_0==NOT) ) {
            alt118=1;
        }
        switch (alt118) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                {
                pushFollow(FOLLOW_unary_in_synpred97_JavaParser932);
                unary();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred97_JavaParser

    // $ANTLR start synpred103_JavaParser
    public final void synpred103_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:164:4: ( variable_assignment )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:164:4: variable_assignment
        {
        pushFollow(FOLLOW_variable_assignment_in_synpred103_JavaParser978);
        variable_assignment();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred103_JavaParser

    // $ANTLR start synpred104_JavaParser
    public final void synpred104_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:165:4: ( variable_def )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:165:4: variable_def
        {
        pushFollow(FOLLOW_variable_def_in_synpred104_JavaParser983);
        variable_def();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred104_JavaParser

    // $ANTLR start synpred105_JavaParser
    public final void synpred105_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:166:4: ( method_call )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:166:4: method_call
        {
        pushFollow(FOLLOW_method_call_in_synpred105_JavaParser988);
        method_call();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred105_JavaParser

    // $ANTLR start synpred109_JavaParser
    public final void synpred109_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:168:4: ( ( unary )? variable_name ( unary )? )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:168:4: ( unary )? variable_name ( unary )?
        {
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:168:4: ( unary )?
        int alt119=2;
        int LA119_0 = input.LA(1);

        if ( ((LA119_0>=INC && LA119_0<=DEC)||LA119_0==NOT) ) {
            alt119=1;
        }
        switch (alt119) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                {
                pushFollow(FOLLOW_unary_in_synpred109_JavaParser999);
                unary();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        pushFollow(FOLLOW_variable_name_in_synpred109_JavaParser1002);
        variable_name();

        state._fsp--;
        if (state.failed) return ;
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:168:25: ( unary )?
        int alt120=2;
        int LA120_0 = input.LA(1);

        if ( ((LA120_0>=INC && LA120_0<=DEC)||LA120_0==NOT) ) {
            alt120=1;
        }
        switch (alt120) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: unary
                {
                pushFollow(FOLLOW_unary_in_synpred109_JavaParser1004);
                unary();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred109_JavaParser

    // $ANTLR start synpred121_JavaParser
    public final void synpred121_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:11: ( for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon ( value )? semicolon ( statement_wosemicolon ( comma statement_wosemicolon )* )? CLOSE_BRACKET code )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:11: for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon ( value )? semicolon ( statement_wosemicolon ( comma statement_wosemicolon )* )? CLOSE_BRACKET code
        {
        pushFollow(FOLLOW_for__in_synpred121_JavaParser1085);
        for_();

        state._fsp--;
        if (state.failed) return ;
        match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_synpred121_JavaParser1087); if (state.failed) return ;
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:29: ( variable_def ( comma variable_def )* )?
        int alt124=2;
        int LA124_0 = input.LA(1);

        if ( (LA124_0==VOID||(LA124_0>=BOOLEAN && LA124_0<=DOUBLE)||LA124_0==AT||LA124_0==ID) ) {
            alt124=1;
        }
        switch (alt124) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:30: variable_def ( comma variable_def )*
                {
                pushFollow(FOLLOW_variable_def_in_synpred121_JavaParser1090);
                variable_def();

                state._fsp--;
                if (state.failed) return ;
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:43: ( comma variable_def )*
                loop123:
                do {
                    int alt123=2;
                    int LA123_0 = input.LA(1);

                    if ( (LA123_0==COMMA) ) {
                        alt123=1;
                    }


                    switch (alt123) {
                	case 1 :
                	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:44: comma variable_def
                	    {
                	    pushFollow(FOLLOW_comma_in_synpred121_JavaParser1093);
                	    comma();

                	    state._fsp--;
                	    if (state.failed) return ;
                	    pushFollow(FOLLOW_variable_def_in_synpred121_JavaParser1095);
                	    variable_def();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop123;
                    }
                } while (true);


                }
                break;

        }

        pushFollow(FOLLOW_semicolon_in_synpred121_JavaParser1101);
        semicolon();

        state._fsp--;
        if (state.failed) return ;
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:77: ( value )?
        int alt125=2;
        int LA125_0 = input.LA(1);

        if ( (LA125_0==NEW||(LA125_0>=NULL && LA125_0<=SUPER)||(LA125_0>=INC && LA125_0<=DEC)||LA125_0==NOT||LA125_0==OPEN_BRACKET||(LA125_0>=BOOL_CONST && LA125_0<=LONG_CONST)||LA125_0==FLOAT_CONST||(LA125_0>=STRING_CONST && LA125_0<=CHAR_CONST)) ) {
            alt125=1;
        }
        switch (alt125) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0: value
                {
                pushFollow(FOLLOW_value_in_synpred121_JavaParser1103);
                value();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        pushFollow(FOLLOW_semicolon_in_synpred121_JavaParser1106);
        semicolon();

        state._fsp--;
        if (state.failed) return ;
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:94: ( statement_wosemicolon ( comma statement_wosemicolon )* )?
        int alt127=2;
        int LA127_0 = input.LA(1);

        if ( (LA127_0==VOID||LA127_0==RETURN||(LA127_0>=THIS && LA127_0<=IF)||LA127_0==TRY||(LA127_0>=BOOLEAN && LA127_0<=DOUBLE)||(LA127_0>=INC && LA127_0<=DEC)||LA127_0==NOT||LA127_0==AT||LA127_0==ID) ) {
            alt127=1;
        }
        switch (alt127) {
            case 1 :
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:95: statement_wosemicolon ( comma statement_wosemicolon )*
                {
                pushFollow(FOLLOW_statement_wosemicolon_in_synpred121_JavaParser1109);
                statement_wosemicolon();

                state._fsp--;
                if (state.failed) return ;
                // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:117: ( comma statement_wosemicolon )*
                loop126:
                do {
                    int alt126=2;
                    int LA126_0 = input.LA(1);

                    if ( (LA126_0==COMMA) ) {
                        alt126=1;
                    }


                    switch (alt126) {
                	case 1 :
                	    // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:118: comma statement_wosemicolon
                	    {
                	    pushFollow(FOLLOW_comma_in_synpred121_JavaParser1112);
                	    comma();

                	    state._fsp--;
                	    if (state.failed) return ;
                	    pushFollow(FOLLOW_statement_wosemicolon_in_synpred121_JavaParser1114);
                	    statement_wosemicolon();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop126;
                    }
                } while (true);


                }
                break;

        }

        match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_synpred121_JavaParser1120); if (state.failed) return ;
        pushFollow(FOLLOW_code_in_synpred121_JavaParser1122);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred121_JavaParser

    // $ANTLR start synpred122_JavaParser
    public final void synpred122_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:195:54: ( else_ code )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:195:54: else_ code
        {
        pushFollow(FOLLOW_else__in_synpred122_JavaParser1204);
        else_();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_code_in_synpred122_JavaParser1206);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred122_JavaParser

    // $ANTLR start synpred123_JavaParser
    public final void synpred123_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:199:15: ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:199:15: catch_ OPEN_BRACKET id id CLOSE_BRACKET code
        {
        pushFollow(FOLLOW_catch__in_synpred123_JavaParser1224);
        catch_();

        state._fsp--;
        if (state.failed) return ;
        match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_synpred123_JavaParser1226); if (state.failed) return ;
        pushFollow(FOLLOW_id_in_synpred123_JavaParser1228);
        id();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_id_in_synpred123_JavaParser1230);
        id();

        state._fsp--;
        if (state.failed) return ;
        match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_synpred123_JavaParser1232); if (state.failed) return ;
        pushFollow(FOLLOW_code_in_synpred123_JavaParser1234);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred123_JavaParser

    // $ANTLR start synpred124_JavaParser
    public final void synpred124_JavaParser_fragment() throws RecognitionException {   
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:199:63: ( finally_ code )
        // /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:199:63: finally_ code
        {
        pushFollow(FOLLOW_finally__in_synpred124_JavaParser1239);
        finally_();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_code_in_synpred124_JavaParser1241);
        code();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred124_JavaParser

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
    public final boolean synpred109_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred109_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred46_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred46_JavaParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred89_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred89_JavaParser_fragment(); // can never throw exception
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
    public final boolean synpred40_JavaParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred40_JavaParser_fragment(); // can never throw exception
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
    protected DFA34 dfa34 = new DFA34(this);
    protected DFA35 dfa35 = new DFA35(this);
    protected DFA56 dfa56 = new DFA56(this);
    protected DFA57 dfa57 = new DFA57(this);
    protected DFA66 dfa66 = new DFA66(this);
    protected DFA69 dfa69 = new DFA69(this);
    static final String DFA2_eotS =
        "\14\uffff";
    static final String DFA2_eofS =
        "\1\1\13\uffff";
    static final String DFA2_minS =
        "\1\6\1\uffff\7\0\3\uffff";
    static final String DFA2_maxS =
        "\1\113\1\uffff\7\0\3\uffff";
    static final String DFA2_acceptS =
        "\1\uffff\1\4\7\uffff\1\1\1\2\1\3";
    static final String DFA2_specialS =
        "\2\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\3\uffff}>";
    static final String[] DFA2_transitionS = {
            "\1\11\1\13\1\12\26\uffff\1\3\1\4\1\5\1\7\1\6\1\10\46\uffff\1"+
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
    static final String DFA34_eotS =
        "\27\uffff";
    static final String DFA34_eofS =
        "\27\uffff";
    static final String DFA34_minS =
        "\1\6\1\uffff\7\0\1\uffff\12\0\3\uffff";
    static final String DFA34_maxS =
        "\1\116\1\uffff\7\0\1\uffff\12\0\3\uffff";
    static final String DFA34_acceptS =
        "\1\uffff\1\5\7\uffff\1\1\12\uffff\1\2\1\3\1\4";
    static final String DFA34_specialS =
        "\2\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\7\1\10\1\11\1\12"+
        "\1\13\1\14\1\15\1\16\1\17\1\20\3\uffff}>";
    static final String[] DFA34_transitionS = {
            "\1\11\4\uffff\1\23\23\uffff\1\3\1\4\1\5\1\7\1\6\1\10\1\13\1"+
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
            return "()* loopback of 82:16: ( class_def | constructor_def | method_def | field_def )*";
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
                        if ( (synpred40_JavaParser()) ) {s = 9;}

                        else if ( (synpred41_JavaParser()) ) {s = 20;}

                        else if ( (synpred42_JavaParser()) ) {s = 21;}

                        else if ( (synpred43_JavaParser()) ) {s = 22;}

                         
                        input.seek(index34_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA34_3 = input.LA(1);

                         
                        int index34_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred40_JavaParser()) ) {s = 9;}

                        else if ( (synpred41_JavaParser()) ) {s = 20;}

                        else if ( (synpred42_JavaParser()) ) {s = 21;}

                        else if ( (synpred43_JavaParser()) ) {s = 22;}

                         
                        input.seek(index34_3);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA34_4 = input.LA(1);

                         
                        int index34_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred40_JavaParser()) ) {s = 9;}

                        else if ( (synpred41_JavaParser()) ) {s = 20;}

                        else if ( (synpred42_JavaParser()) ) {s = 21;}

                        else if ( (synpred43_JavaParser()) ) {s = 22;}

                         
                        input.seek(index34_4);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA34_5 = input.LA(1);

                         
                        int index34_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred40_JavaParser()) ) {s = 9;}

                        else if ( (synpred41_JavaParser()) ) {s = 20;}

                        else if ( (synpred42_JavaParser()) ) {s = 21;}

                        else if ( (synpred43_JavaParser()) ) {s = 22;}

                         
                        input.seek(index34_5);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA34_6 = input.LA(1);

                         
                        int index34_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred40_JavaParser()) ) {s = 9;}

                        else if ( (synpred41_JavaParser()) ) {s = 20;}

                        else if ( (synpred42_JavaParser()) ) {s = 21;}

                        else if ( (synpred43_JavaParser()) ) {s = 22;}

                         
                        input.seek(index34_6);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA34_7 = input.LA(1);

                         
                        int index34_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred40_JavaParser()) ) {s = 9;}

                        else if ( (synpred41_JavaParser()) ) {s = 20;}

                        else if ( (synpred42_JavaParser()) ) {s = 21;}

                        else if ( (synpred43_JavaParser()) ) {s = 22;}

                         
                        input.seek(index34_7);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA34_8 = input.LA(1);

                         
                        int index34_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred40_JavaParser()) ) {s = 9;}

                        else if ( (synpred41_JavaParser()) ) {s = 20;}

                        else if ( (synpred42_JavaParser()) ) {s = 21;}

                        else if ( (synpred43_JavaParser()) ) {s = 22;}

                         
                        input.seek(index34_8);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA34_10 = input.LA(1);

                         
                        int index34_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred41_JavaParser()) ) {s = 20;}

                        else if ( (synpred42_JavaParser()) ) {s = 21;}

                        else if ( (synpred43_JavaParser()) ) {s = 22;}

                         
                        input.seek(index34_10);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA34_11 = input.LA(1);

                         
                        int index34_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred42_JavaParser()) ) {s = 21;}

                        else if ( (synpred43_JavaParser()) ) {s = 22;}

                         
                        input.seek(index34_11);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA34_12 = input.LA(1);

                         
                        int index34_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred42_JavaParser()) ) {s = 21;}

                        else if ( (synpred43_JavaParser()) ) {s = 22;}

                         
                        input.seek(index34_12);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA34_13 = input.LA(1);

                         
                        int index34_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred42_JavaParser()) ) {s = 21;}

                        else if ( (synpred43_JavaParser()) ) {s = 22;}

                         
                        input.seek(index34_13);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA34_14 = input.LA(1);

                         
                        int index34_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred42_JavaParser()) ) {s = 21;}

                        else if ( (synpred43_JavaParser()) ) {s = 22;}

                         
                        input.seek(index34_14);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA34_15 = input.LA(1);

                         
                        int index34_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred42_JavaParser()) ) {s = 21;}

                        else if ( (synpred43_JavaParser()) ) {s = 22;}

                         
                        input.seek(index34_15);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA34_16 = input.LA(1);

                         
                        int index34_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred42_JavaParser()) ) {s = 21;}

                        else if ( (synpred43_JavaParser()) ) {s = 22;}

                         
                        input.seek(index34_16);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA34_17 = input.LA(1);

                         
                        int index34_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred42_JavaParser()) ) {s = 21;}

                        else if ( (synpred43_JavaParser()) ) {s = 22;}

                         
                        input.seek(index34_17);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA34_18 = input.LA(1);

                         
                        int index34_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred42_JavaParser()) ) {s = 21;}

                        else if ( (synpred43_JavaParser()) ) {s = 22;}

                         
                        input.seek(index34_18);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA34_19 = input.LA(1);

                         
                        int index34_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred42_JavaParser()) ) {s = 21;}

                        else if ( (synpred43_JavaParser()) ) {s = 22;}

                         
                        input.seek(index34_19);
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
    static final String DFA35_eotS =
        "\30\uffff";
    static final String DFA35_eofS =
        "\30\uffff";
    static final String DFA35_minS =
        "\1\6\1\uffff\7\0\1\uffff\12\0\4\uffff";
    static final String DFA35_maxS =
        "\1\116\1\uffff\7\0\1\uffff\12\0\4\uffff";
    static final String DFA35_acceptS =
        "\1\uffff\1\6\7\uffff\1\1\12\uffff\1\2\1\3\1\4\1\5";
    static final String DFA35_specialS =
        "\2\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\7\1\10\1\11\1\12"+
        "\1\13\1\14\1\15\1\16\1\17\1\20\4\uffff}>";
    static final String[] DFA35_transitionS = {
            "\1\11\4\uffff\1\23\23\uffff\1\3\1\4\1\5\1\7\1\6\1\10\1\13\1"+
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
            return "()* loopback of 86:16: ( class_def | constructor_def | method_def | field_def | enum_content )*";
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
                        if ( (synpred44_JavaParser()) ) {s = 9;}

                        else if ( (synpred45_JavaParser()) ) {s = 20;}

                        else if ( (synpred46_JavaParser()) ) {s = 21;}

                        else if ( (synpred47_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA35_3 = input.LA(1);

                         
                        int index35_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 9;}

                        else if ( (synpred45_JavaParser()) ) {s = 20;}

                        else if ( (synpred46_JavaParser()) ) {s = 21;}

                        else if ( (synpred47_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_3);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA35_4 = input.LA(1);

                         
                        int index35_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 9;}

                        else if ( (synpred45_JavaParser()) ) {s = 20;}

                        else if ( (synpred46_JavaParser()) ) {s = 21;}

                        else if ( (synpred47_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_4);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA35_5 = input.LA(1);

                         
                        int index35_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 9;}

                        else if ( (synpred45_JavaParser()) ) {s = 20;}

                        else if ( (synpred46_JavaParser()) ) {s = 21;}

                        else if ( (synpred47_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_5);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA35_6 = input.LA(1);

                         
                        int index35_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 9;}

                        else if ( (synpred45_JavaParser()) ) {s = 20;}

                        else if ( (synpred46_JavaParser()) ) {s = 21;}

                        else if ( (synpred47_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_6);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA35_7 = input.LA(1);

                         
                        int index35_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 9;}

                        else if ( (synpred45_JavaParser()) ) {s = 20;}

                        else if ( (synpred46_JavaParser()) ) {s = 21;}

                        else if ( (synpred47_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_7);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA35_8 = input.LA(1);

                         
                        int index35_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_JavaParser()) ) {s = 9;}

                        else if ( (synpred45_JavaParser()) ) {s = 20;}

                        else if ( (synpred46_JavaParser()) ) {s = 21;}

                        else if ( (synpred47_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_8);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA35_10 = input.LA(1);

                         
                        int index35_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred45_JavaParser()) ) {s = 20;}

                        else if ( (synpred46_JavaParser()) ) {s = 21;}

                        else if ( (synpred47_JavaParser()) ) {s = 22;}

                        else if ( (synpred48_JavaParser()) ) {s = 23;}

                         
                        input.seek(index35_10);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA35_11 = input.LA(1);

                         
                        int index35_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred46_JavaParser()) ) {s = 21;}

                        else if ( (synpred47_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_11);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA35_12 = input.LA(1);

                         
                        int index35_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred46_JavaParser()) ) {s = 21;}

                        else if ( (synpred47_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_12);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA35_13 = input.LA(1);

                         
                        int index35_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred46_JavaParser()) ) {s = 21;}

                        else if ( (synpred47_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_13);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA35_14 = input.LA(1);

                         
                        int index35_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred46_JavaParser()) ) {s = 21;}

                        else if ( (synpred47_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_14);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA35_15 = input.LA(1);

                         
                        int index35_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred46_JavaParser()) ) {s = 21;}

                        else if ( (synpred47_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_15);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA35_16 = input.LA(1);

                         
                        int index35_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred46_JavaParser()) ) {s = 21;}

                        else if ( (synpred47_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_16);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA35_17 = input.LA(1);

                         
                        int index35_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred46_JavaParser()) ) {s = 21;}

                        else if ( (synpred47_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_17);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA35_18 = input.LA(1);

                         
                        int index35_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred46_JavaParser()) ) {s = 21;}

                        else if ( (synpred47_JavaParser()) ) {s = 22;}

                         
                        input.seek(index35_18);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA35_19 = input.LA(1);

                         
                        int index35_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred46_JavaParser()) ) {s = 21;}

                        else if ( (synpred47_JavaParser()) ) {s = 22;}

                         
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
    static final String DFA56_eotS =
        "\21\uffff";
    static final String DFA56_eofS =
        "\21\uffff";
    static final String DFA56_minS =
        "\1\14\1\0\17\uffff";
    static final String DFA56_maxS =
        "\1\130\1\0\17\uffff";
    static final String DFA56_acceptS =
        "\2\uffff\1\1\15\uffff\1\2";
    static final String DFA56_specialS =
        "\1\uffff\1\0\17\uffff}>";
    static final String[] DFA56_transitionS = {
            "\1\2\3\uffff\3\2\43\uffff\2\2\10\uffff\1\2\6\uffff\1\1\5\uffff"+
            "\4\2\1\uffff\1\2\4\uffff\2\2",
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
            ""
    };

    static final short[] DFA56_eot = DFA.unpackEncodedString(DFA56_eotS);
    static final short[] DFA56_eof = DFA.unpackEncodedString(DFA56_eofS);
    static final char[] DFA56_min = DFA.unpackEncodedStringToUnsignedChars(DFA56_minS);
    static final char[] DFA56_max = DFA.unpackEncodedStringToUnsignedChars(DFA56_maxS);
    static final short[] DFA56_accept = DFA.unpackEncodedString(DFA56_acceptS);
    static final short[] DFA56_special = DFA.unpackEncodedString(DFA56_specialS);
    static final short[][] DFA56_transition;

    static {
        int numStates = DFA56_transitionS.length;
        DFA56_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA56_transition[i] = DFA.unpackEncodedString(DFA56_transitionS[i]);
        }
    }

    class DFA56 extends DFA {

        public DFA56(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 56;
            this.eot = DFA56_eot;
            this.eof = DFA56_eof;
            this.min = DFA56_min;
            this.max = DFA56_max;
            this.accept = DFA56_accept;
            this.special = DFA56_special;
            this.transition = DFA56_transition;
        }
        public String getDescription() {
            return "111:1: value : ( ( cast )? ( unary )? single_value ( unary )? ( binary_operator value )? | open_bracket value close_bracket );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA56_1 = input.LA(1);

                         
                        int index56_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred70_JavaParser()) ) {s = 2;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index56_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 56, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA57_eotS =
        "\14\uffff";
    static final String DFA57_eofS =
        "\2\uffff\1\7\1\11\1\12\7\uffff";
    static final String DFA57_minS =
        "\1\14\1\uffff\3\57\1\uffff\1\6\5\uffff";
    static final String DFA57_maxS =
        "\1\130\1\uffff\3\112\1\uffff\1\116\5\uffff";
    static final String DFA57_acceptS =
        "\1\uffff\1\1\3\uffff\1\5\1\uffff\1\3\1\4\1\7\1\6\1\2";
    static final String DFA57_specialS =
        "\14\uffff}>";
    static final String[] DFA57_transitionS = {
            "\1\5\3\uffff\1\1\1\4\1\3\72\uffff\1\1\1\2\2\1\1\uffff\1\1\4"+
            "\uffff\2\1",
            "",
            "\4\7\3\uffff\13\7\1\6\5\7\1\10\1\7\1\uffff\1\7",
            "\4\11\3\uffff\13\11\1\uffff\3\11\1\uffff\1\11\1\10\1\11\1\uffff"+
            "\1\11",
            "\4\12\3\uffff\13\12\1\uffff\3\12\1\uffff\1\12\1\10\1\12\1\uffff"+
            "\1\12",
            "",
            "\1\13\107\uffff\1\2",
            "",
            "",
            "",
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
            return "115:1: single_value : ( constant | class_name dot class_ | variable_name | method_call | new_class | this_ | super_ );";
        }
    }
    static final String DFA66_eotS =
        "\35\uffff";
    static final String DFA66_eofS =
        "\35\uffff";
    static final String DFA66_minS =
        "\1\13\3\0\31\uffff";
    static final String DFA66_maxS =
        "\1\116\3\0\31\uffff";
    static final String DFA66_acceptS =
        "\4\uffff\1\2\11\uffff\1\4\1\5\1\6\1\7\1\10\2\uffff\1\11\1\12\1\13"+
        "\1\14\1\15\1\16\1\1\1\3";
    static final String DFA66_specialS =
        "\1\uffff\1\0\1\1\1\2\31\uffff}>";
    static final String[] DFA66_transitionS = {
            "\1\4\1\uffff\1\17\1\21\1\20\1\uffff\1\1\1\2\1\25\1\27\1\26\1"+
            "\30\3\uffff\1\31\2\uffff\1\32\7\uffff\10\4\11\uffff\2\22\10"+
            "\uffff\1\22\11\uffff\1\16\1\4\2\uffff\1\3",
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
            return "146:1: statement : ( variable_assignment semicolon | variable_def semicolon | method_call semicolon | semicolon | return_statement | continue_ | break_ | ( unary )? variable_name ( unary )? | for_loop | while_loop | do_loop | if_else | try_catch | throw_ value semicolon );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA66_1 = input.LA(1);

                         
                        int index66_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred88_JavaParser()) ) {s = 27;}

                        else if ( (synpred90_JavaParser()) ) {s = 28;}

                         
                        input.seek(index66_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA66_2 = input.LA(1);

                         
                        int index66_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred88_JavaParser()) ) {s = 27;}

                        else if ( (synpred90_JavaParser()) ) {s = 28;}

                         
                        input.seek(index66_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA66_3 = input.LA(1);

                         
                        int index66_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred88_JavaParser()) ) {s = 27;}

                        else if ( (synpred89_JavaParser()) ) {s = 4;}

                        else if ( (synpred90_JavaParser()) ) {s = 28;}

                        else if ( (synpred97_JavaParser()) ) {s = 18;}

                         
                        input.seek(index66_3);
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
    static final String DFA69_eotS =
        "\31\uffff";
    static final String DFA69_eofS =
        "\31\uffff";
    static final String DFA69_minS =
        "\1\13\3\0\25\uffff";
    static final String DFA69_maxS =
        "\1\116\3\0\25\uffff";
    static final String DFA69_acceptS =
        "\4\uffff\1\2\11\uffff\1\4\1\5\2\uffff\1\6\1\7\1\10\1\11\1\12\1\1"+
        "\1\3";
    static final String DFA69_specialS =
        "\1\uffff\1\0\1\1\1\2\25\uffff}>";
    static final String[] DFA69_transitionS = {
            "\1\4\1\uffff\1\16\3\uffff\1\1\1\2\1\22\1\24\1\23\1\25\3\uffff"+
            "\1\26\12\uffff\10\4\11\uffff\2\17\10\uffff\1\17\12\uffff\1\4"+
            "\2\uffff\1\3",
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
            ""
    };

    static final short[] DFA69_eot = DFA.unpackEncodedString(DFA69_eotS);
    static final short[] DFA69_eof = DFA.unpackEncodedString(DFA69_eofS);
    static final char[] DFA69_min = DFA.unpackEncodedStringToUnsignedChars(DFA69_minS);
    static final char[] DFA69_max = DFA.unpackEncodedStringToUnsignedChars(DFA69_maxS);
    static final short[] DFA69_accept = DFA.unpackEncodedString(DFA69_acceptS);
    static final short[] DFA69_special = DFA.unpackEncodedString(DFA69_specialS);
    static final short[][] DFA69_transition;

    static {
        int numStates = DFA69_transitionS.length;
        DFA69_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA69_transition[i] = DFA.unpackEncodedString(DFA69_transitionS[i]);
        }
    }

    class DFA69 extends DFA {

        public DFA69(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 69;
            this.eot = DFA69_eot;
            this.eof = DFA69_eof;
            this.min = DFA69_min;
            this.max = DFA69_max;
            this.accept = DFA69_accept;
            this.special = DFA69_special;
            this.transition = DFA69_transition;
        }
        public String getDescription() {
            return "163:1: statement_wosemicolon : ( variable_assignment | variable_def | method_call | return_statement | ( unary )? variable_name ( unary )? | for_loop | while_loop | do_loop | if_else | try_catch );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA69_1 = input.LA(1);

                         
                        int index69_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_JavaParser()) ) {s = 23;}

                        else if ( (synpred105_JavaParser()) ) {s = 24;}

                         
                        input.seek(index69_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA69_2 = input.LA(1);

                         
                        int index69_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_JavaParser()) ) {s = 23;}

                        else if ( (synpred105_JavaParser()) ) {s = 24;}

                         
                        input.seek(index69_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA69_3 = input.LA(1);

                         
                        int index69_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_JavaParser()) ) {s = 23;}

                        else if ( (synpred104_JavaParser()) ) {s = 4;}

                        else if ( (synpred105_JavaParser()) ) {s = 24;}

                        else if ( (synpred109_JavaParser()) ) {s = 15;}

                         
                        input.seek(index69_3);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 69, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_package_def_in_file44 = new BitSet(new long[]{0x0000001F800001E0L,0x0000000000000800L});
    public static final BitSet FOLLOW_import_def_in_file46 = new BitSet(new long[]{0x0000001F800001E0L,0x0000000000000800L});
    public static final BitSet FOLLOW_class_def_in_file50 = new BitSet(new long[]{0x0000001F800001E2L,0x0000000000000800L});
    public static final BitSet FOLLOW_enum_def_in_file54 = new BitSet(new long[]{0x0000001F800001E2L,0x0000000000000800L});
    public static final BitSet FOLLOW_interface_def_in_file58 = new BitSet(new long[]{0x0000001F800001E2L,0x0000000000000800L});
    public static final BitSet FOLLOW_package__in_package_def72 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_package_name_in_package_def74 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_semicolon_in_package_def76 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_import__in_import_def88 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_import_name_in_import_def90 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_semicolon_in_import_def92 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_class_def104 = new BitSet(new long[]{0x0000001F80000040L,0x0000000000000800L});
    public static final BitSet FOLLOW_modifier_in_class_def107 = new BitSet(new long[]{0x0000001F80000040L,0x0000000000000800L});
    public static final BitSet FOLLOW_class__in_class_def110 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_id_in_class_def112 = new BitSet(new long[]{0x0000200000000600L,0x0000000000000008L});
    public static final BitSet FOLLOW_generic_in_class_def114 = new BitSet(new long[]{0x0000200000000600L,0x0000000000000008L});
    public static final BitSet FOLLOW_extends__in_class_def118 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_class_name_in_class_def120 = new BitSet(new long[]{0x0000200000000600L,0x0000000000000008L});
    public static final BitSet FOLLOW_generic_in_class_def122 = new BitSet(new long[]{0x0000200000000600L,0x0000000000000008L});
    public static final BitSet FOLLOW_implements__in_class_def128 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_class_name_in_class_def130 = new BitSet(new long[]{0x0000200000000600L,0x000000000000000CL});
    public static final BitSet FOLLOW_comma_in_class_def133 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_class_name_in_class_def135 = new BitSet(new long[]{0x0000200000000600L,0x000000000000000CL});
    public static final BitSet FOLLOW_class_block_in_class_def141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_enum_def157 = new BitSet(new long[]{0x0000001F80000100L,0x0000000000000800L});
    public static final BitSet FOLLOW_modifier_in_enum_def160 = new BitSet(new long[]{0x0000001F80000100L,0x0000000000000800L});
    public static final BitSet FOLLOW_enum__in_enum_def163 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_id_in_enum_def165 = new BitSet(new long[]{0x0000200000000600L,0x0000000000000008L});
    public static final BitSet FOLLOW_generic_in_enum_def167 = new BitSet(new long[]{0x0000200000000600L,0x0000000000000008L});
    public static final BitSet FOLLOW_extends__in_enum_def171 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_class_name_in_enum_def173 = new BitSet(new long[]{0x0000200000000600L,0x0000000000000008L});
    public static final BitSet FOLLOW_generic_in_enum_def175 = new BitSet(new long[]{0x0000200000000600L,0x0000000000000008L});
    public static final BitSet FOLLOW_enum_block_in_enum_def180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_interface_def196 = new BitSet(new long[]{0x0000001F800001E0L,0x0000000000000800L});
    public static final BitSet FOLLOW_modifier_in_interface_def199 = new BitSet(new long[]{0x0000001F800001E0L,0x0000000000000800L});
    public static final BitSet FOLLOW_interface__in_interface_def202 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_id_in_interface_def204 = new BitSet(new long[]{0x0000200000000600L,0x0000000000000008L});
    public static final BitSet FOLLOW_generic_in_interface_def206 = new BitSet(new long[]{0x0000200000000600L,0x0000000000000008L});
    public static final BitSet FOLLOW_extends__in_interface_def210 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_class_name_in_interface_def212 = new BitSet(new long[]{0x0000200000000600L,0x0000000000000008L});
    public static final BitSet FOLLOW_generic_in_interface_def214 = new BitSet(new long[]{0x0000200000000600L,0x0000000000000008L});
    public static final BitSet FOLLOW_interface_block_in_interface_def219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_constructor_def235 = new BitSet(new long[]{0x0000001F80000000L,0x0000000000004800L});
    public static final BitSet FOLLOW_modifier_in_constructor_def238 = new BitSet(new long[]{0x0000001F80000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_id_in_constructor_def241 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_open_bracket_in_constructor_def243 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004100L});
    public static final BitSet FOLLOW_argument_def_in_constructor_def245 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004100L});
    public static final BitSet FOLLOW_close_bracket_in_constructor_def247 = new BitSet(new long[]{0x00C03FE0247EEE00L,0x0000000000004C09L});
    public static final BitSet FOLLOW_code_in_constructor_def249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_method_def264 = new BitSet(new long[]{0x00001FFF80000800L,0x0000000000004800L});
    public static final BitSet FOLLOW_modifier_in_method_def267 = new BitSet(new long[]{0x00001FFF80000800L,0x0000000000004000L});
    public static final BitSet FOLLOW_variable_type_in_method_def270 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_id_in_method_def272 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_open_bracket_in_method_def274 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004100L});
    public static final BitSet FOLLOW_argument_def_in_method_def276 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004100L});
    public static final BitSet FOLLOW_close_bracket_in_method_def278 = new BitSet(new long[]{0x00C03FE0647EEE00L,0x0000000000004C09L});
    public static final BitSet FOLLOW_throws__in_method_def281 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_class_name_in_method_def283 = new BitSet(new long[]{0x00C03FE0247EEE00L,0x0000000000004C0DL});
    public static final BitSet FOLLOW_comma_in_method_def286 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_class_name_in_method_def288 = new BitSet(new long[]{0x00C03FE0247EEE00L,0x0000000000004C0DL});
    public static final BitSet FOLLOW_code_in_method_def294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_field_def310 = new BitSet(new long[]{0x00001FFF80000800L,0x0000000000004800L});
    public static final BitSet FOLLOW_modifier_in_field_def313 = new BitSet(new long[]{0x00001FFF80000800L,0x0000000000004000L});
    public static final BitSet FOLLOW_variable_type_in_field_def316 = new BitSet(new long[]{0x0000000000060000L,0x0000000000004000L});
    public static final BitSet FOLLOW_variable_name_in_field_def318 = new BitSet(new long[]{0x0038000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_assign_in_field_def321 = new BitSet(new long[]{0x00C0000000071000L,0x000000000185E081L});
    public static final BitSet FOLLOW_value_in_field_def323 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_semicolon_in_field_def327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_type_in_argument_def339 = new BitSet(new long[]{0x0000000000060000L,0x0000000000004000L});
    public static final BitSet FOLLOW_variable_name_in_argument_def341 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_comma_in_argument_def344 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004000L});
    public static final BitSet FOLLOW_variable_type_in_argument_def346 = new BitSet(new long[]{0x0000000000060000L,0x0000000000004000L});
    public static final BitSet FOLLOW_variable_name_in_argument_def348 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_annotation_in_variable_def363 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004800L});
    public static final BitSet FOLLOW_variable_type_in_variable_def366 = new BitSet(new long[]{0x0000000000060000L,0x0000000000004000L});
    public static final BitSet FOLLOW_variable_name_in_variable_def368 = new BitSet(new long[]{0x0038000000000002L});
    public static final BitSet FOLLOW_assign_in_variable_def371 = new BitSet(new long[]{0x00C0000000071000L,0x000000000185E081L});
    public static final BitSet FOLLOW_value_in_variable_def373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_public__in_modifier385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_private__in_modifier390 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_protected__in_modifier395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_static__in_modifier400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_final__in_modifier405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_transient__in_modifier410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_begin_in_class_block422 = new BitSet(new long[]{0x00005FFF80000840L,0x0000000000004800L});
    public static final BitSet FOLLOW_class_def_in_class_block425 = new BitSet(new long[]{0x00005FFF80000840L,0x0000000000004800L});
    public static final BitSet FOLLOW_constructor_def_in_class_block429 = new BitSet(new long[]{0x00005FFF80000840L,0x0000000000004800L});
    public static final BitSet FOLLOW_method_def_in_class_block433 = new BitSet(new long[]{0x00005FFF80000840L,0x0000000000004800L});
    public static final BitSet FOLLOW_field_def_in_class_block437 = new BitSet(new long[]{0x00005FFF80000840L,0x0000000000004800L});
    public static final BitSet FOLLOW_block_end_in_class_block441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_begin_in_enum_block452 = new BitSet(new long[]{0x00005FFF80000840L,0x0000000000004800L});
    public static final BitSet FOLLOW_class_def_in_enum_block455 = new BitSet(new long[]{0x00005FFF80000840L,0x0000000000004800L});
    public static final BitSet FOLLOW_constructor_def_in_enum_block459 = new BitSet(new long[]{0x00005FFF80000840L,0x0000000000004800L});
    public static final BitSet FOLLOW_method_def_in_enum_block463 = new BitSet(new long[]{0x00005FFF80000840L,0x0000000000004800L});
    public static final BitSet FOLLOW_field_def_in_enum_block467 = new BitSet(new long[]{0x00005FFF80000840L,0x0000000000004800L});
    public static final BitSet FOLLOW_enum_content_in_enum_block471 = new BitSet(new long[]{0x00005FFF80000840L,0x0000000000004800L});
    public static final BitSet FOLLOW_block_end_in_enum_block475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_enum_content486 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000484L});
    public static final BitSet FOLLOW_open_bracket_in_enum_content489 = new BitSet(new long[]{0x00C01FE000071800L,0x000000000185E181L});
    public static final BitSet FOLLOW_arguments_in_enum_content491 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004100L});
    public static final BitSet FOLLOW_close_bracket_in_enum_content493 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000404L});
    public static final BitSet FOLLOW_comma_in_enum_content498 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_id_in_enum_content500 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000484L});
    public static final BitSet FOLLOW_open_bracket_in_enum_content503 = new BitSet(new long[]{0x00C01FE000071800L,0x000000000185E181L});
    public static final BitSet FOLLOW_arguments_in_enum_content505 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004100L});
    public static final BitSet FOLLOW_close_bracket_in_enum_content507 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000404L});
    public static final BitSet FOLLOW_semicolon_in_enum_content513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_begin_in_interface_block525 = new BitSet(new long[]{0x00005FFF80000840L,0x0000000000004800L});
    public static final BitSet FOLLOW_annotation_in_interface_block528 = new BitSet(new long[]{0x00001FFF80000800L,0x0000000000004800L});
    public static final BitSet FOLLOW_modifier_in_interface_block531 = new BitSet(new long[]{0x00001FFF80000800L,0x0000000000004000L});
    public static final BitSet FOLLOW_variable_type_in_interface_block534 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_id_in_interface_block536 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_open_bracket_in_interface_block538 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004100L});
    public static final BitSet FOLLOW_argument_def_in_interface_block540 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004100L});
    public static final BitSet FOLLOW_close_bracket_in_interface_block542 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_semicolon_in_interface_block544 = new BitSet(new long[]{0x00005FFF80000840L,0x0000000000004800L});
    public static final BitSet FOLLOW_block_end_in_interface_block548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_name_in_method_call560 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_open_bracket_in_method_call562 = new BitSet(new long[]{0x00C01FE000071800L,0x000000000185E181L});
    public static final BitSet FOLLOW_arguments_in_method_call564 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004100L});
    public static final BitSet FOLLOW_close_bracket_in_method_call566 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
    public static final BitSet FOLLOW_dot_in_method_call569 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_id_in_method_call571 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000082L});
    public static final BitSet FOLLOW_open_bracket_in_method_call574 = new BitSet(new long[]{0x00C01FE000071800L,0x000000000185E181L});
    public static final BitSet FOLLOW_arguments_in_method_call576 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004100L});
    public static final BitSet FOLLOW_close_bracket_in_method_call578 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
    public static final BitSet FOLLOW_super__in_method_call587 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_open_bracket_in_method_call589 = new BitSet(new long[]{0x00C01FE000071800L,0x000000000185E181L});
    public static final BitSet FOLLOW_arguments_in_method_call591 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004100L});
    public static final BitSet FOLLOW_close_bracket_in_method_call593 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
    public static final BitSet FOLLOW_dot_in_method_call596 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_id_in_method_call598 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000082L});
    public static final BitSet FOLLOW_open_bracket_in_method_call601 = new BitSet(new long[]{0x00C01FE000071800L,0x000000000185E181L});
    public static final BitSet FOLLOW_arguments_in_method_call603 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004100L});
    public static final BitSet FOLLOW_close_bracket_in_method_call605 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
    public static final BitSet FOLLOW_this__in_method_call614 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_open_bracket_in_method_call616 = new BitSet(new long[]{0x00C01FE000071800L,0x000000000185E181L});
    public static final BitSet FOLLOW_arguments_in_method_call618 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004100L});
    public static final BitSet FOLLOW_close_bracket_in_method_call620 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
    public static final BitSet FOLLOW_dot_in_method_call623 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_id_in_method_call625 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000082L});
    public static final BitSet FOLLOW_open_bracket_in_method_call628 = new BitSet(new long[]{0x00C01FE000071800L,0x000000000185E181L});
    public static final BitSet FOLLOW_arguments_in_method_call630 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004100L});
    public static final BitSet FOLLOW_close_bracket_in_method_call632 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_name_in_annotation647 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000080L});
    public static final BitSet FOLLOW_open_bracket_in_annotation650 = new BitSet(new long[]{0x00C0000000071000L,0x000000000185E081L});
    public static final BitSet FOLLOW_value_in_annotation652 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004104L});
    public static final BitSet FOLLOW_comma_in_annotation655 = new BitSet(new long[]{0x00C0000000071000L,0x000000000185E081L});
    public static final BitSet FOLLOW_value_in_annotation657 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004104L});
    public static final BitSet FOLLOW_close_bracket_in_annotation661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_generic673 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_id_in_generic676 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000014L});
    public static final BitSet FOLLOW_comma_in_generic679 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_id_in_generic681 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000014L});
    public static final BitSet FOLLOW_GT_in_generic686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cast_in_value699 = new BitSet(new long[]{0x00C0000000071000L,0x000000000185E081L});
    public static final BitSet FOLLOW_unary_in_value702 = new BitSet(new long[]{0x00C0000000071000L,0x000000000185E081L});
    public static final BitSet FOLLOW_single_value_in_value705 = new BitSet(new long[]{0xFFC7800000000002L,0x0000000000000019L});
    public static final BitSet FOLLOW_unary_in_value707 = new BitSet(new long[]{0xFF07800000000002L,0x0000000000000018L});
    public static final BitSet FOLLOW_binary_operator_in_value711 = new BitSet(new long[]{0x00C0000000071000L,0x000000000185E081L});
    public static final BitSet FOLLOW_value_in_value713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_open_bracket_in_value720 = new BitSet(new long[]{0x00C0000000071000L,0x000000000185E081L});
    public static final BitSet FOLLOW_value_in_value722 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004100L});
    public static final BitSet FOLLOW_close_bracket_in_value724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constant_in_single_value735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_name_in_single_value740 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_dot_in_single_value742 = new BitSet(new long[]{0x0000001F80000040L,0x0000000000000800L});
    public static final BitSet FOLLOW_class__in_single_value744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_name_in_single_value749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_single_value754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_new_class_in_single_value759 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_this__in_single_value764 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_super__in_single_value769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_int_const_in_constant778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_const_in_constant783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_float_const_in_constant788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_char_const_in_constant793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_null_const_in_constant798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_boolean_const_in_constant803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_long_const_in_constant808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_new__in_new_class820 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_class_name_in_new_class822 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000088L});
    public static final BitSet FOLLOW_generic_in_new_class824 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_open_bracket_in_new_class827 = new BitSet(new long[]{0x00C01FE000071800L,0x000000000185E181L});
    public static final BitSet FOLLOW_arguments_in_new_class829 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004100L});
    public static final BitSet FOLLOW_close_bracket_in_new_class831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_value_in_arguments844 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_comma_in_arguments847 = new BitSet(new long[]{0x00C0000000071000L,0x000000000185E081L});
    public static final BitSet FOLLOW_value_in_arguments849 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_code863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_begin_in_code868 = new BitSet(new long[]{0x00C05FFFA47EE840L,0x0000000000004C01L});
    public static final BitSet FOLLOW_statement_in_code870 = new BitSet(new long[]{0x00C05FFFA47EE840L,0x0000000000004C01L});
    public static final BitSet FOLLOW_block_end_in_code873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_statement885 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_semicolon_in_statement887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_statement892 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_semicolon_in_statement894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_statement899 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_semicolon_in_statement901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_semicolon_in_statement907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_statement_in_statement912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_continue__in_statement917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_break__in_statement922 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_statement927 = new BitSet(new long[]{0x0000000000060000L,0x0000000000004000L});
    public static final BitSet FOLLOW_variable_name_in_statement930 = new BitSet(new long[]{0x00C0000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_unary_in_statement932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for_loop_in_statement938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while_loop_in_statement943 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_do_loop_in_statement948 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_else_in_statement953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_try_catch_in_statement958 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_throw__in_statement963 = new BitSet(new long[]{0x00C0000000071000L,0x000000000185E081L});
    public static final BitSet FOLLOW_value_in_statement965 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_semicolon_in_statement967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_statement_wosemicolon978 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_statement_wosemicolon983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_statement_wosemicolon988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_statement_in_statement_wosemicolon994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_statement_wosemicolon999 = new BitSet(new long[]{0x0000000000060000L,0x0000000000004000L});
    public static final BitSet FOLLOW_variable_name_in_statement_wosemicolon1002 = new BitSet(new long[]{0x00C0000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_unary_in_statement_wosemicolon1004 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for_loop_in_statement_wosemicolon1010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while_loop_in_statement_wosemicolon1015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_do_loop_in_statement_wosemicolon1020 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_else_in_statement_wosemicolon1025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_try_catch_in_statement_wosemicolon1030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return__in_return_statement1042 = new BitSet(new long[]{0x00C0000000071000L,0x000000000185E081L});
    public static final BitSet FOLLOW_value_in_return_statement1044 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_semicolon_in_return_statement1046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_this__in_variable_assignment1059 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_dot_in_variable_assignment1061 = new BitSet(new long[]{0x0000000000060000L,0x0000000000004000L});
    public static final BitSet FOLLOW_super__in_variable_assignment1065 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_dot_in_variable_assignment1067 = new BitSet(new long[]{0x0000000000060000L,0x0000000000004000L});
    public static final BitSet FOLLOW_variable_name_in_variable_assignment1071 = new BitSet(new long[]{0x0038000000000000L});
    public static final BitSet FOLLOW_assign_in_variable_assignment1073 = new BitSet(new long[]{0x00C0000000071000L,0x000000000185E081L});
    public static final BitSet FOLLOW_value_in_variable_assignment1075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for__in_for_loop1085 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_for_loop1087 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004C00L});
    public static final BitSet FOLLOW_variable_def_in_for_loop1090 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000404L});
    public static final BitSet FOLLOW_comma_in_for_loop1093 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004800L});
    public static final BitSet FOLLOW_variable_def_in_for_loop1095 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000404L});
    public static final BitSet FOLLOW_semicolon_in_for_loop1101 = new BitSet(new long[]{0x00C0000000071000L,0x000000000185E481L});
    public static final BitSet FOLLOW_value_in_for_loop1103 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_semicolon_in_for_loop1106 = new BitSet(new long[]{0x00C01FE0047E2800L,0x0000000000004901L});
    public static final BitSet FOLLOW_statement_wosemicolon_in_for_loop1109 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000104L});
    public static final BitSet FOLLOW_comma_in_for_loop1112 = new BitSet(new long[]{0x00C01FE0047E2800L,0x0000000000004801L});
    public static final BitSet FOLLOW_statement_wosemicolon_in_for_loop1114 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000104L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_for_loop1120 = new BitSet(new long[]{0x00C03FE0247EEE00L,0x0000000000004C09L});
    public static final BitSet FOLLOW_code_in_for_loop1122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for__in_for_loop1127 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_for_loop1129 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004000L});
    public static final BitSet FOLLOW_variable_type_in_for_loop1131 = new BitSet(new long[]{0x0000000000060000L,0x0000000000004000L});
    public static final BitSet FOLLOW_variable_name_in_for_loop1133 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_colon_in_for_loop1135 = new BitSet(new long[]{0x00C0000000071000L,0x000000000185E081L});
    public static final BitSet FOLLOW_value_in_for_loop1137 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_for_loop1139 = new BitSet(new long[]{0x00C03FE0247EEE00L,0x0000000000004C09L});
    public static final BitSet FOLLOW_code_in_for_loop1141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while__in_while_loop1153 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_while_loop1155 = new BitSet(new long[]{0x00C0000000071000L,0x000000000185E081L});
    public static final BitSet FOLLOW_value_in_while_loop1157 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_while_loop1159 = new BitSet(new long[]{0x00C03FE0247EEE00L,0x0000000000004C09L});
    public static final BitSet FOLLOW_code_in_while_loop1161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_do__in_do_loop1172 = new BitSet(new long[]{0x00C03FE0247EEE00L,0x0000000000004C09L});
    public static final BitSet FOLLOW_code_in_do_loop1174 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_while__in_do_loop1176 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_do_loop1178 = new BitSet(new long[]{0x00C0000000071000L,0x000000000185E081L});
    public static final BitSet FOLLOW_value_in_do_loop1180 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_do_loop1182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if__in_if_else1193 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_if_else1195 = new BitSet(new long[]{0x00C0000000071000L,0x000000000185E081L});
    public static final BitSet FOLLOW_value_in_if_else1197 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_if_else1199 = new BitSet(new long[]{0x00C03FE0247EEE00L,0x0000000000004C09L});
    public static final BitSet FOLLOW_code_in_if_else1201 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_else__in_if_else1204 = new BitSet(new long[]{0x00C03FE0247EEE00L,0x0000000000004C09L});
    public static final BitSet FOLLOW_code_in_if_else1206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_try__in_try_catch1219 = new BitSet(new long[]{0x00C03FE0247EEE00L,0x0000000000004C09L});
    public static final BitSet FOLLOW_code_in_try_catch1221 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_catch__in_try_catch1224 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_try_catch1226 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_id_in_try_catch1228 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_id_in_try_catch1230 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_try_catch1232 = new BitSet(new long[]{0x00C03FE0247EEE00L,0x0000000000004C09L});
    public static final BitSet FOLLOW_code_in_try_catch1234 = new BitSet(new long[]{0x0000000018000002L});
    public static final BitSet FOLLOW_finally__in_try_catch1239 = new BitSet(new long[]{0x00C03FE0247EEE00L,0x0000000000004C09L});
    public static final BitSet FOLLOW_code_in_try_catch1241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_cast1253 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004000L});
    public static final BitSet FOLLOW_variable_type_in_cast1255 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_cast1257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primitive_in_variable_type1270 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000020L});
    public static final BitSet FOLLOW_array_in_variable_type1272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_name_in_variable_type1278 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000028L});
    public static final BitSet FOLLOW_generic_in_variable_type1280 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000020L});
    public static final BitSet FOLLOW_array_in_variable_type1283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_void__in_variable_type1289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_id1299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_binary_operator1310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_binary_operator1317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_binary_operator1324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_binary_operator1331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUAL_in_binary_operator1338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNEQUAL_in_binary_operator1345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_binary_operator1352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_binary_operator1359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LE_in_binary_operator1366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GE_in_binary_operator1373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOGICAL_OR_in_binary_operator1380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BIT_OR_in_binary_operator1387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOGICAL_AND_in_binary_operator1394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BIT_AND_in_binary_operator1401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INC_in_unary1413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEC_in_unary1420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_unary1427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_primitive1440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BYTE_in_primitive1447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_in_primitive1454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SHORT_in_primitive1461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_primitive1468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LONG_in_primitive1475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_primitive1482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLE_in_primitive1489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_CONST_in_int_const1507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LONG_CONST_in_long_const1517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_CONST_in_string_const1527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_CONST_in_float_const1537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_CONST_in_char_const1547 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_null_const1557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOL_CONST_in_boolean_const1568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_package_name1584 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_package_name1586 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_package_name1590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_import_name1604 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_import_name1606 = new BitSet(new long[]{0x0800000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_set_in_import_name1610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_class_name1628 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_class_name1630 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_class_name1634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_method_name1648 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_method_name1650 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_method_name1654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_name_in_variable_name1667 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000020L});
    public static final BitSet FOLLOW_array_in_variable_name1669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_in_annotation_name1680 = new BitSet(new long[]{0x0000000000060000L,0x0000000000004000L});
    public static final BitSet FOLLOW_name_in_annotation_name1682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_name1692 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_name1694 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_name1698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_open_rect_bracket_in_array1708 = new BitSet(new long[]{0x00C0000000071000L,0x000000000185E0C1L});
    public static final BitSet FOLLOW_value_in_array1710 = new BitSet(new long[]{0x00C0000000071000L,0x000000000185E0C1L});
    public static final BitSet FOLLOW_close_rect_bracket_in_array1713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PACKAGE_in_package_1726 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_import_1737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASS_in_class_1747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENUM_in_enum_1756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTERFACE_in_interface_1766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXTENDS_in_extends_1774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPLEMENTS_in_implements_1784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_THIS_in_this_1793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUPER_in_super_1802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOID_in_void_1811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PUBLIC_in_public_1820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRIVATE_in_private_1828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROTECTED_in_protected_1838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STATIC_in_static_1847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FINAL_in_final_1856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSIENT_in_transient_1866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEW_in_new_1875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRY_in_try_1884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CATCH_in_catch_1893 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FINALLY_in_finally_1901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_THROWS_in_throws_1910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_THROW_in_throw_1919 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FOR_in_for_1929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHILE_in_while_1938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DO_in_do_1947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_if_1956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELSE_in_else_1965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETURN_in_return_1975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BREAK_in_break_1984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTINUE_in_continue_1994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_semicolon2009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_comma2018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_colon2027 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_dot2036 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_assign2045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCASSIGN_in_assign2052 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECASSIGN_in_assign2059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_CURLY_BRACKET_in_block_begin2071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLOSE_CURLY_BRACKET_in_block_end2081 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_open_bracket2091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_close_bracket2101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_RECT_BRACKET_in_open_rect_bracket2112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLOSE_RECT_BRACKET_in_close_rect_bracket2122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_def_in_synpred2_JavaParser50 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enum_def_in_synpred3_JavaParser54 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interface_def_in_synpred4_JavaParser58 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_def_in_synpred40_JavaParser425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constructor_def_in_synpred41_JavaParser429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_def_in_synpred42_JavaParser433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_field_def_in_synpred43_JavaParser437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_def_in_synpred44_JavaParser455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constructor_def_in_synpred45_JavaParser459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_def_in_synpred46_JavaParser463 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_field_def_in_synpred47_JavaParser467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enum_content_in_synpred48_JavaParser471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cast_in_synpred70_JavaParser699 = new BitSet(new long[]{0x00C0000000071000L,0x000000000185E081L});
    public static final BitSet FOLLOW_unary_in_synpred70_JavaParser702 = new BitSet(new long[]{0x00C0000000071000L,0x000000000185E081L});
    public static final BitSet FOLLOW_single_value_in_synpred70_JavaParser705 = new BitSet(new long[]{0xFFC7800000000002L,0x0000000000000019L});
    public static final BitSet FOLLOW_unary_in_synpred70_JavaParser707 = new BitSet(new long[]{0xFF07800000000002L,0x0000000000000018L});
    public static final BitSet FOLLOW_binary_operator_in_synpred70_JavaParser711 = new BitSet(new long[]{0x00C0000000071000L,0x000000000185E081L});
    public static final BitSet FOLLOW_value_in_synpred70_JavaParser713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_synpred88_JavaParser885 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_semicolon_in_synpred88_JavaParser887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_synpred89_JavaParser892 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_semicolon_in_synpred89_JavaParser894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_synpred90_JavaParser899 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_semicolon_in_synpred90_JavaParser901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_synpred96_JavaParser932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_synpred97_JavaParser927 = new BitSet(new long[]{0x0000000000060000L,0x0000000000004000L});
    public static final BitSet FOLLOW_variable_name_in_synpred97_JavaParser930 = new BitSet(new long[]{0x00C0000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_unary_in_synpred97_JavaParser932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_assignment_in_synpred103_JavaParser978 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_def_in_synpred104_JavaParser983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_call_in_synpred105_JavaParser988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_synpred109_JavaParser999 = new BitSet(new long[]{0x0000000000060000L,0x0000000000004000L});
    public static final BitSet FOLLOW_variable_name_in_synpred109_JavaParser1002 = new BitSet(new long[]{0x00C0000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_unary_in_synpred109_JavaParser1004 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for__in_synpred121_JavaParser1085 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_synpred121_JavaParser1087 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004C00L});
    public static final BitSet FOLLOW_variable_def_in_synpred121_JavaParser1090 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000404L});
    public static final BitSet FOLLOW_comma_in_synpred121_JavaParser1093 = new BitSet(new long[]{0x00001FE000000800L,0x0000000000004800L});
    public static final BitSet FOLLOW_variable_def_in_synpred121_JavaParser1095 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000404L});
    public static final BitSet FOLLOW_semicolon_in_synpred121_JavaParser1101 = new BitSet(new long[]{0x00C0000000071000L,0x000000000185E481L});
    public static final BitSet FOLLOW_value_in_synpred121_JavaParser1103 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_semicolon_in_synpred121_JavaParser1106 = new BitSet(new long[]{0x00C01FE0047E2800L,0x0000000000004901L});
    public static final BitSet FOLLOW_statement_wosemicolon_in_synpred121_JavaParser1109 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000104L});
    public static final BitSet FOLLOW_comma_in_synpred121_JavaParser1112 = new BitSet(new long[]{0x00C01FE0047E2800L,0x0000000000004801L});
    public static final BitSet FOLLOW_statement_wosemicolon_in_synpred121_JavaParser1114 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000104L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_synpred121_JavaParser1120 = new BitSet(new long[]{0x00C03FE0247EEE00L,0x0000000000004C09L});
    public static final BitSet FOLLOW_code_in_synpred121_JavaParser1122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_else__in_synpred122_JavaParser1204 = new BitSet(new long[]{0x00C03FE0247EEE00L,0x0000000000004C09L});
    public static final BitSet FOLLOW_code_in_synpred122_JavaParser1206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_catch__in_synpred123_JavaParser1224 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_OPEN_BRACKET_in_synpred123_JavaParser1226 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_id_in_synpred123_JavaParser1228 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_id_in_synpred123_JavaParser1230 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_CLOSE_BRACKET_in_synpred123_JavaParser1232 = new BitSet(new long[]{0x00C03FE0247EEE00L,0x0000000000004C09L});
    public static final BitSet FOLLOW_code_in_synpred123_JavaParser1234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_finally__in_synpred124_JavaParser1239 = new BitSet(new long[]{0x00C03FE0247EEE00L,0x0000000000004C09L});
    public static final BitSet FOLLOW_code_in_synpred124_JavaParser1241 = new BitSet(new long[]{0x0000000000000002L});

}