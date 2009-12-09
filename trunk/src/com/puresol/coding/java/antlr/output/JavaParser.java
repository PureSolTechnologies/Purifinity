// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g 2009-12-09 22:11:14

package com.puresol.coding.java.antlr.output;

import com.puresol.coding.ParserHelper;

import org.antlr.runtime.*;
import java.util.HashMap;

public class JavaParser extends Parser {
	public static final String[] tokenNames = new String[] { "<invalid>",
			"<EOR>", "<DOWN>", "<UP>", "PACKAGE", "IMPORT", "CLASS", "EXTENDS",
			"IMPLEMENTS", "VOID", "NEW", "RETURN", "BREAK", "CONTINUE", "NULL",
			"THIS", "SUPER", "FOR", "DO", "WHILE", "IF", "ELSE", "SWITCH",
			"CASE", "TRY", "CATCH", "FINALLY", "PUBLIC", "PRIVATE",
			"PROTECTED", "FINAL", "STATIC", "TRANSIENT", "BOOLEAN", "BYTE",
			"CHAR", "SHORT", "INTEGER", "LONG", "FLOAT", "DOUBLE",
			"OPEN_CURLY_BRACKET", "CLOSE_CURLY_BRACKET", "LE", "GE", "EQUAL",
			"UNEQUAL", "ASSIGN", "INCASSIGN", "DECASSIGN", "INC", "DEC",
			"PLUS", "MINUS", "SLASH", "STAR", "LOGICAL_OR", "BIT_OR",
			"LOGICAL_AND", "BIT_AND", "NOT", "DOT", "COMMA", "LT", "GT",
			"OPEN_RECT_BRACKET", "CLOSE_RECT_BRACKET", "OPEN_BRACKET",
			"CLOSE_BRACKET", "COLON", "SEMICOLON", "AT", "TILDE", "BOOL_CONST",
			"ID", "INT_CONST", "EXPONENT", "FLOAT_CONST", "LINEFEED",
			"COMMENT", "WS", "ESC_SEQ", "STRING_CONST", "CHAR_CONST",
			"HEX_DIGIT", "UNICODE_ESC", "OCTAL_ESC" };
	public static final int PACKAGE = 4;
	public static final int DEC = 51;
	public static final int PROTECTED = 29;
	public static final int CLASS = 6;
	public static final int LT = 63;
	public static final int EXPONENT = 76;
	public static final int STAR = 55;
	public static final int INCASSIGN = 48;
	public static final int WHILE = 19;
	public static final int BIT_AND = 59;
	public static final int CASE = 23;
	public static final int OCTAL_ESC = 86;
	public static final int CHAR = 35;
	public static final int NEW = 10;
	public static final int FOR = 17;
	public static final int DO = 18;
	public static final int FLOAT = 39;
	public static final int NOT = 60;
	public static final int ID = 74;
	public static final int EOF = -1;
	public static final int CLOSE_RECT_BRACKET = 66;
	public static final int LOGICAL_AND = 58;
	public static final int BREAK = 12;
	public static final int FLOAT_CONST = 77;
	public static final int CHAR_CONST = 83;
	public static final int LINEFEED = 78;
	public static final int IF = 20;
	public static final int AT = 71;
	public static final int OPEN_BRACKET = 67;
	public static final int INC = 50;
	public static final int FINAL = 30;
	public static final int IMPORT = 5;
	public static final int ESC_SEQ = 81;
	public static final int SLASH = 54;
	public static final int BOOLEAN = 33;
	public static final int IMPLEMENTS = 8;
	public static final int BIT_OR = 57;
	public static final int CONTINUE = 13;
	public static final int COMMA = 62;
	public static final int TRANSIENT = 32;
	public static final int STRING_CONST = 82;
	public static final int EQUAL = 45;
	public static final int RETURN = 11;
	public static final int THIS = 15;
	public static final int LOGICAL_OR = 56;
	public static final int TILDE = 72;
	public static final int DOUBLE = 40;
	public static final int PLUS = 52;
	public static final int VOID = 9;
	public static final int SUPER = 16;
	public static final int DOT = 61;
	public static final int COMMENT = 79;
	public static final int INTEGER = 37;
	public static final int BYTE = 34;
	public static final int GE = 44;
	public static final int OPEN_CURLY_BRACKET = 41;
	public static final int STATIC = 31;
	public static final int PRIVATE = 28;
	public static final int SWITCH = 22;
	public static final int NULL = 14;
	public static final int UNICODE_ESC = 85;
	public static final int CLOSE_CURLY_BRACKET = 42;
	public static final int ELSE = 21;
	public static final int INT_CONST = 75;
	public static final int HEX_DIGIT = 84;
	public static final int SHORT = 36;
	public static final int SEMICOLON = 70;
	public static final int DECASSIGN = 49;
	public static final int MINUS = 53;
	public static final int TRY = 24;
	public static final int UNEQUAL = 46;
	public static final int COLON = 69;
	public static final int WS = 80;
	public static final int FINALLY = 26;
	public static final int ASSIGN = 47;
	public static final int GT = 64;
	public static final int CATCH = 25;
	public static final int OPEN_RECT_BRACKET = 65;
	public static final int LONG = 38;
	public static final int PUBLIC = 27;
	public static final int EXTENDS = 7;
	public static final int BOOL_CONST = 73;
	public static final int LE = 43;
	public static final int CLOSE_BRACKET = 68;

	// delegates
	// delegators

	public JavaParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}

	public JavaParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
		this.state.ruleMemo = new HashMap[203 + 1];

	}

	public String[] getTokenNames() {
		return JavaParser.tokenNames;
	}

	public String getGrammarFileName() {
		return "/home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g";
	}

	private ParserHelper helper = new ParserHelper(this);

	public ParserHelper getParserHelper() {
		return helper;
	}

	// $ANTLR start "file"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:23:1:
	// file : package_def ( import_def )* ( class_def )* ;
	public final void file() throws RecognitionException {
		int file_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 1)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:23:6:
			// ( package_def ( import_def )* ( class_def )* )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:23:8:
			// package_def ( import_def )* ( class_def )*
			{
				pushFollow(FOLLOW_package_def_in_file44);
				package_def();

				state._fsp--;
				if (state.failed)
					return;
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:23:20:
				// ( import_def )*
				loop1: do {
					int alt1 = 2;
					int LA1_0 = input.LA(1);

					if ((LA1_0 == IMPORT)) {
						alt1 = 1;
					}

					switch (alt1) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
						// import_def
					{
						pushFollow(FOLLOW_import_def_in_file46);
						import_def();

						state._fsp--;
						if (state.failed)
							return;

					}
						break;

					default:
						break loop1;
					}
				} while (true);

				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:23:32:
				// ( class_def )*
				loop2: do {
					int alt2 = 2;
					int LA2_0 = input.LA(1);

					if ((LA2_0 == CLASS
							|| (LA2_0 >= PUBLIC && LA2_0 <= TRANSIENT) || LA2_0 == AT)) {
						alt2 = 1;
					}

					switch (alt2) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
						// class_def
					{
						pushFollow(FOLLOW_class_def_in_file49);
						class_def();

						state._fsp--;
						if (state.failed)
							return;

					}
						break;

					default:
						break loop2;
					}
				} while (true);

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 1, file_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "file"

	// $ANTLR start "package_def"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:25:1:
	// package_def : package_ package_name semicolon ;
	public final void package_def() throws RecognitionException {
		int package_def_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 2)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:26:2:
			// ( package_ package_name semicolon )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:26:4:
			// package_ package_name semicolon
			{
				pushFollow(FOLLOW_package__in_package_def60);
				package_();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_package_name_in_package_def62);
				package_name();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_semicolon_in_package_def64);
				semicolon();

				state._fsp--;
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 2, package_def_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "package_def"

	// $ANTLR start "import_def"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:29:1:
	// import_def : import_ import_name semicolon ;
	public final void import_def() throws RecognitionException {
		int import_def_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 3)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:30:2:
			// ( import_ import_name semicolon )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:30:4:
			// import_ import_name semicolon
			{
				pushFollow(FOLLOW_import__in_import_def76);
				import_();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_import_name_in_import_def78);
				import_name();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_semicolon_in_import_def80);
				semicolon();

				state._fsp--;
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 3, import_def_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "import_def"

	public static class class_def_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "class_def"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:33:1:
	// class_def : ( annotation )* ( modifier )* class_ id ( extends_ class_name
	// )? ( implements_ class_name ( comma class_name )* )? class_block ;
	public final JavaParser.class_def_return class_def()
			throws RecognitionException {
		JavaParser.class_def_return retval = new JavaParser.class_def_return();
		retval.start = input.LT(1);
		int class_def_StartIndex = input.index();
		JavaParser.id_return id1 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 4)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:34:2:
			// ( ( annotation )* ( modifier )* class_ id ( extends_ class_name
			// )? ( implements_ class_name ( comma class_name )* )? class_block
			// )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:34:4:
			// ( annotation )* ( modifier )* class_ id ( extends_ class_name )?
			// ( implements_ class_name ( comma class_name )* )? class_block
			{
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:34:4:
				// ( annotation )*
				loop3: do {
					int alt3 = 2;
					int LA3_0 = input.LA(1);

					if ((LA3_0 == AT)) {
						alt3 = 1;
					}

					switch (alt3) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
						// annotation
					{
						pushFollow(FOLLOW_annotation_in_class_def92);
						annotation();

						state._fsp--;
						if (state.failed)
							return retval;

					}
						break;

					default:
						break loop3;
					}
				} while (true);

				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:34:16:
				// ( modifier )*
				loop4: do {
					int alt4 = 2;
					int LA4_0 = input.LA(1);

					if (((LA4_0 >= PUBLIC && LA4_0 <= TRANSIENT))) {
						alt4 = 1;
					}

					switch (alt4) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
						// modifier
					{
						pushFollow(FOLLOW_modifier_in_class_def95);
						modifier();

						state._fsp--;
						if (state.failed)
							return retval;

					}
						break;

					default:
						break loop4;
					}
				} while (true);

				pushFollow(FOLLOW_class__in_class_def98);
				class_();

				state._fsp--;
				if (state.failed)
					return retval;
				pushFollow(FOLLOW_id_in_class_def100);
				id1 = id();

				state._fsp--;
				if (state.failed)
					return retval;
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:34:36:
				// ( extends_ class_name )?
				int alt5 = 2;
				int LA5_0 = input.LA(1);

				if ((LA5_0 == EXTENDS)) {
					alt5 = 1;
				}
				switch (alt5) {
				case 1:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:34:37:
					// extends_ class_name
				{
					pushFollow(FOLLOW_extends__in_class_def103);
					extends_();

					state._fsp--;
					if (state.failed)
						return retval;
					pushFollow(FOLLOW_class_name_in_class_def105);
					class_name();

					state._fsp--;
					if (state.failed)
						return retval;

				}
					break;

				}

				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:34:59:
				// ( implements_ class_name ( comma class_name )* )?
				int alt7 = 2;
				int LA7_0 = input.LA(1);

				if ((LA7_0 == IMPLEMENTS)) {
					alt7 = 1;
				}
				switch (alt7) {
				case 1:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:34:60:
					// implements_ class_name ( comma class_name )*
				{
					pushFollow(FOLLOW_implements__in_class_def110);
					implements_();

					state._fsp--;
					if (state.failed)
						return retval;
					pushFollow(FOLLOW_class_name_in_class_def112);
					class_name();

					state._fsp--;
					if (state.failed)
						return retval;
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:34:83:
					// ( comma class_name )*
					loop6: do {
						int alt6 = 2;
						int LA6_0 = input.LA(1);

						if ((LA6_0 == COMMA)) {
							alt6 = 1;
						}

						switch (alt6) {
						case 1:
							// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:34:84:
							// comma class_name
						{
							pushFollow(FOLLOW_comma_in_class_def115);
							comma();

							state._fsp--;
							if (state.failed)
								return retval;
							pushFollow(FOLLOW_class_name_in_class_def117);
							class_name();

							state._fsp--;
							if (state.failed)
								return retval;

						}
							break;

						default:
							break loop6;
						}
					} while (true);

				}
					break;

				}

				pushFollow(FOLLOW_class_block_in_class_def123);
				class_block();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerRange("class", (id1 != null ? input
							.toString(id1.start, id1.stop) : null), input
							.toString(retval.start, input.LT(-1)), retval.start
							.getTokenIndex(), input.LT(-1).getTokenIndex());
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 4, class_def_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "class_def"

	public static class constructor_def_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "constructor_def"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:38:1:
	// constructor_def : ( annotation )* ( modifier )* id open_bracket
	// argument_def close_bracket code ;
	public final JavaParser.constructor_def_return constructor_def()
			throws RecognitionException {
		JavaParser.constructor_def_return retval = new JavaParser.constructor_def_return();
		retval.start = input.LT(1);
		int constructor_def_StartIndex = input.index();
		JavaParser.id_return id2 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 5)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:39:2:
			// ( ( annotation )* ( modifier )* id open_bracket argument_def
			// close_bracket code )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:39:4:
			// ( annotation )* ( modifier )* id open_bracket argument_def
			// close_bracket code
			{
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:39:4:
				// ( annotation )*
				loop8: do {
					int alt8 = 2;
					int LA8_0 = input.LA(1);

					if ((LA8_0 == AT)) {
						alt8 = 1;
					}

					switch (alt8) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
						// annotation
					{
						pushFollow(FOLLOW_annotation_in_constructor_def139);
						annotation();

						state._fsp--;
						if (state.failed)
							return retval;

					}
						break;

					default:
						break loop8;
					}
				} while (true);

				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:39:16:
				// ( modifier )*
				loop9: do {
					int alt9 = 2;
					int LA9_0 = input.LA(1);

					if (((LA9_0 >= PUBLIC && LA9_0 <= TRANSIENT))) {
						alt9 = 1;
					}

					switch (alt9) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
						// modifier
					{
						pushFollow(FOLLOW_modifier_in_constructor_def142);
						modifier();

						state._fsp--;
						if (state.failed)
							return retval;

					}
						break;

					default:
						break loop9;
					}
				} while (true);

				pushFollow(FOLLOW_id_in_constructor_def145);
				id2 = id();

				state._fsp--;
				if (state.failed)
					return retval;
				pushFollow(FOLLOW_open_bracket_in_constructor_def147);
				open_bracket();

				state._fsp--;
				if (state.failed)
					return retval;
				pushFollow(FOLLOW_argument_def_in_constructor_def149);
				argument_def();

				state._fsp--;
				if (state.failed)
					return retval;
				pushFollow(FOLLOW_close_bracket_in_constructor_def151);
				close_bracket();

				state._fsp--;
				if (state.failed)
					return retval;
				pushFollow(FOLLOW_code_in_constructor_def153);
				code();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerRange("constructor", (id2 != null ? input
							.toString(id2.start, id2.stop) : null), input
							.toString(retval.start, input.LT(-1)), retval.start
							.getTokenIndex(), input.LT(-1).getTokenIndex());
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 5, constructor_def_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "constructor_def"

	public static class method_def_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "method_def"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:43:1:
	// method_def : ( annotation )* ( modifier )* variable_type id open_bracket
	// argument_def close_bracket code ;
	public final JavaParser.method_def_return method_def()
			throws RecognitionException {
		JavaParser.method_def_return retval = new JavaParser.method_def_return();
		retval.start = input.LT(1);
		int method_def_StartIndex = input.index();
		JavaParser.id_return id3 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 6)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:44:2:
			// ( ( annotation )* ( modifier )* variable_type id open_bracket
			// argument_def close_bracket code )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:44:4:
			// ( annotation )* ( modifier )* variable_type id open_bracket
			// argument_def close_bracket code
			{
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:44:4:
				// ( annotation )*
				loop10: do {
					int alt10 = 2;
					int LA10_0 = input.LA(1);

					if ((LA10_0 == AT)) {
						alt10 = 1;
					}

					switch (alt10) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
						// annotation
					{
						pushFollow(FOLLOW_annotation_in_method_def168);
						annotation();

						state._fsp--;
						if (state.failed)
							return retval;

					}
						break;

					default:
						break loop10;
					}
				} while (true);

				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:44:16:
				// ( modifier )*
				loop11: do {
					int alt11 = 2;
					int LA11_0 = input.LA(1);

					if (((LA11_0 >= PUBLIC && LA11_0 <= TRANSIENT))) {
						alt11 = 1;
					}

					switch (alt11) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
						// modifier
					{
						pushFollow(FOLLOW_modifier_in_method_def171);
						modifier();

						state._fsp--;
						if (state.failed)
							return retval;

					}
						break;

					default:
						break loop11;
					}
				} while (true);

				pushFollow(FOLLOW_variable_type_in_method_def174);
				variable_type();

				state._fsp--;
				if (state.failed)
					return retval;
				pushFollow(FOLLOW_id_in_method_def176);
				id3 = id();

				state._fsp--;
				if (state.failed)
					return retval;
				pushFollow(FOLLOW_open_bracket_in_method_def178);
				open_bracket();

				state._fsp--;
				if (state.failed)
					return retval;
				pushFollow(FOLLOW_argument_def_in_method_def180);
				argument_def();

				state._fsp--;
				if (state.failed)
					return retval;
				pushFollow(FOLLOW_close_bracket_in_method_def182);
				close_bracket();

				state._fsp--;
				if (state.failed)
					return retval;
				pushFollow(FOLLOW_code_in_method_def184);
				code();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerRange("method", (id3 != null ? input
							.toString(id3.start, id3.stop) : null), input
							.toString(retval.start, input.LT(-1)), retval.start
							.getTokenIndex(), input.LT(-1).getTokenIndex());
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 6, method_def_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "method_def"

	// $ANTLR start "field_def"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:48:1:
	// field_def : ( annotation )* ( modifier )* variable_type variable_name (
	// assign value )? semicolon ;
	public final void field_def() throws RecognitionException {
		int field_def_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 7)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:49:2:
			// ( ( annotation )* ( modifier )* variable_type variable_name (
			// assign value )? semicolon )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:49:4:
			// ( annotation )* ( modifier )* variable_type variable_name (
			// assign value )? semicolon
			{
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:49:4:
				// ( annotation )*
				loop12: do {
					int alt12 = 2;
					int LA12_0 = input.LA(1);

					if ((LA12_0 == AT)) {
						alt12 = 1;
					}

					switch (alt12) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
						// annotation
					{
						pushFollow(FOLLOW_annotation_in_field_def200);
						annotation();

						state._fsp--;
						if (state.failed)
							return;

					}
						break;

					default:
						break loop12;
					}
				} while (true);

				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:49:16:
				// ( modifier )*
				loop13: do {
					int alt13 = 2;
					int LA13_0 = input.LA(1);

					if (((LA13_0 >= PUBLIC && LA13_0 <= TRANSIENT))) {
						alt13 = 1;
					}

					switch (alt13) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
						// modifier
					{
						pushFollow(FOLLOW_modifier_in_field_def203);
						modifier();

						state._fsp--;
						if (state.failed)
							return;

					}
						break;

					default:
						break loop13;
					}
				} while (true);

				pushFollow(FOLLOW_variable_type_in_field_def206);
				variable_type();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_variable_name_in_field_def208);
				variable_name();

				state._fsp--;
				if (state.failed)
					return;
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:49:54:
				// ( assign value )?
				int alt14 = 2;
				int LA14_0 = input.LA(1);

				if (((LA14_0 >= ASSIGN && LA14_0 <= DECASSIGN))) {
					alt14 = 1;
				}
				switch (alt14) {
				case 1:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:49:55:
					// assign value
				{
					pushFollow(FOLLOW_assign_in_field_def211);
					assign();

					state._fsp--;
					if (state.failed)
						return;
					pushFollow(FOLLOW_value_in_field_def213);
					value();

					state._fsp--;
					if (state.failed)
						return;

				}
					break;

				}

				pushFollow(FOLLOW_semicolon_in_field_def217);
				semicolon();

				state._fsp--;
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 7, field_def_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "field_def"

	// $ANTLR start "argument_def"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:52:1:
	// argument_def : ( variable_type variable_name ( comma variable_type
	// variable_name )* )? ;
	public final void argument_def() throws RecognitionException {
		int argument_def_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 8)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:53:2:
			// ( ( variable_type variable_name ( comma variable_type
			// variable_name )* )? )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:53:4:
			// ( variable_type variable_name ( comma variable_type variable_name
			// )* )?
			{
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:53:4:
				// ( variable_type variable_name ( comma variable_type
				// variable_name )* )?
				int alt16 = 2;
				int LA16_0 = input.LA(1);

				if ((LA16_0 == VOID || (LA16_0 >= BOOLEAN && LA16_0 <= DOUBLE) || LA16_0 == ID)) {
					alt16 = 1;
				}
				switch (alt16) {
				case 1:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:53:5:
					// variable_type variable_name ( comma variable_type
					// variable_name )*
				{
					pushFollow(FOLLOW_variable_type_in_argument_def229);
					variable_type();

					state._fsp--;
					if (state.failed)
						return;
					pushFollow(FOLLOW_variable_name_in_argument_def231);
					variable_name();

					state._fsp--;
					if (state.failed)
						return;
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:53:33:
					// ( comma variable_type variable_name )*
					loop15: do {
						int alt15 = 2;
						int LA15_0 = input.LA(1);

						if ((LA15_0 == COMMA)) {
							alt15 = 1;
						}

						switch (alt15) {
						case 1:
							// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:53:34:
							// comma variable_type variable_name
						{
							pushFollow(FOLLOW_comma_in_argument_def234);
							comma();

							state._fsp--;
							if (state.failed)
								return;
							pushFollow(FOLLOW_variable_type_in_argument_def236);
							variable_type();

							state._fsp--;
							if (state.failed)
								return;
							pushFollow(FOLLOW_variable_name_in_argument_def238);
							variable_name();

							state._fsp--;
							if (state.failed)
								return;

						}
							break;

						default:
							break loop15;
						}
					} while (true);

				}
					break;

				}

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 8, argument_def_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "argument_def"

	// $ANTLR start "variable_def"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:56:1:
	// variable_def : ( annotation )* variable_type variable_name ( assign value
	// )? ;
	public final void variable_def() throws RecognitionException {
		int variable_def_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 9)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:57:2:
			// ( ( annotation )* variable_type variable_name ( assign value )? )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:57:4:
			// ( annotation )* variable_type variable_name ( assign value )?
			{
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:57:4:
				// ( annotation )*
				loop17: do {
					int alt17 = 2;
					int LA17_0 = input.LA(1);

					if ((LA17_0 == AT)) {
						alt17 = 1;
					}

					switch (alt17) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
						// annotation
					{
						pushFollow(FOLLOW_annotation_in_variable_def253);
						annotation();

						state._fsp--;
						if (state.failed)
							return;

					}
						break;

					default:
						break loop17;
					}
				} while (true);

				pushFollow(FOLLOW_variable_type_in_variable_def256);
				variable_type();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_variable_name_in_variable_def258);
				variable_name();

				state._fsp--;
				if (state.failed)
					return;
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:57:44:
				// ( assign value )?
				int alt18 = 2;
				int LA18_0 = input.LA(1);

				if (((LA18_0 >= ASSIGN && LA18_0 <= DECASSIGN))) {
					alt18 = 1;
				}
				switch (alt18) {
				case 1:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:57:45:
					// assign value
				{
					pushFollow(FOLLOW_assign_in_variable_def261);
					assign();

					state._fsp--;
					if (state.failed)
						return;
					pushFollow(FOLLOW_value_in_variable_def263);
					value();

					state._fsp--;
					if (state.failed)
						return;

				}
					break;

				}

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 9, variable_def_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "variable_def"

	// $ANTLR start "modifier"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:60:1:
	// modifier : ( public_ | private_ | protected_ | static_ | final_ |
	// transient_ );
	public final void modifier() throws RecognitionException {
		int modifier_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 10)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:60:9:
			// ( public_ | private_ | protected_ | static_ | final_ | transient_
			// )
			int alt19 = 6;
			switch (input.LA(1)) {
			case PUBLIC: {
				alt19 = 1;
			}
				break;
			case PRIVATE: {
				alt19 = 2;
			}
				break;
			case PROTECTED: {
				alt19 = 3;
			}
				break;
			case STATIC: {
				alt19 = 4;
			}
				break;
			case FINAL: {
				alt19 = 5;
			}
				break;
			case TRANSIENT: {
				alt19 = 6;
			}
				break;
			default:
				if (state.backtracking > 0) {
					state.failed = true;
					return;
				}
				NoViableAltException nvae = new NoViableAltException("", 19, 0,
						input);

				throw nvae;
			}

			switch (alt19) {
			case 1:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:60:11:
				// public_
			{
				pushFollow(FOLLOW_public__in_modifier275);
				public_();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 2:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:61:4:
				// private_
			{
				pushFollow(FOLLOW_private__in_modifier280);
				private_();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 3:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:62:4:
				// protected_
			{
				pushFollow(FOLLOW_protected__in_modifier285);
				protected_();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 4:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:63:4:
				// static_
			{
				pushFollow(FOLLOW_static__in_modifier290);
				static_();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 5:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:64:4:
				// final_
			{
				pushFollow(FOLLOW_final__in_modifier295);
				final_();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 6:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:65:4:
				// transient_
			{
				pushFollow(FOLLOW_transient__in_modifier300);
				transient_();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;

			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 10, modifier_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "modifier"

	// $ANTLR start "class_block"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:69:1:
	// class_block : block_begin ( class_def | constructor_def | method_def |
	// field_def )* block_end ;
	public final void class_block() throws RecognitionException {
		int class_block_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 11)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:70:2:
			// ( block_begin ( class_def | constructor_def | method_def |
			// field_def )* block_end )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:70:4:
			// block_begin ( class_def | constructor_def | method_def |
			// field_def )* block_end
			{
				pushFollow(FOLLOW_block_begin_in_class_block312);
				block_begin();

				state._fsp--;
				if (state.failed)
					return;
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:70:16:
				// ( class_def | constructor_def | method_def | field_def )*
				loop20: do {
					int alt20 = 5;
					alt20 = dfa20.predict(input);
					switch (alt20) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:70:17:
						// class_def
					{
						pushFollow(FOLLOW_class_def_in_class_block315);
						class_def();

						state._fsp--;
						if (state.failed)
							return;

					}
						break;
					case 2:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:70:29:
						// constructor_def
					{
						pushFollow(FOLLOW_constructor_def_in_class_block319);
						constructor_def();

						state._fsp--;
						if (state.failed)
							return;

					}
						break;
					case 3:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:70:47:
						// method_def
					{
						pushFollow(FOLLOW_method_def_in_class_block323);
						method_def();

						state._fsp--;
						if (state.failed)
							return;

					}
						break;
					case 4:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:70:60:
						// field_def
					{
						pushFollow(FOLLOW_field_def_in_class_block327);
						field_def();

						state._fsp--;
						if (state.failed)
							return;

					}
						break;

					default:
						break loop20;
					}
				} while (true);

				pushFollow(FOLLOW_block_end_in_class_block331);
				block_end();

				state._fsp--;
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 11, class_block_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "class_block"

	// $ANTLR start "method_call"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:73:1:
	// method_call : method_name open_bracket arguments close_bracket ( dot id
	// open_bracket arguments close_bracket )* ;
	public final void method_call() throws RecognitionException {
		int method_call_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 12)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:74:2:
			// ( method_name open_bracket arguments close_bracket ( dot id
			// open_bracket arguments close_bracket )* )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:74:4:
			// method_name open_bracket arguments close_bracket ( dot id
			// open_bracket arguments close_bracket )*
			{
				pushFollow(FOLLOW_method_name_in_method_call343);
				method_name();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_open_bracket_in_method_call345);
				open_bracket();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_arguments_in_method_call347);
				arguments();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_close_bracket_in_method_call349);
				close_bracket();

				state._fsp--;
				if (state.failed)
					return;
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:74:53:
				// ( dot id open_bracket arguments close_bracket )*
				loop21: do {
					int alt21 = 2;
					int LA21_0 = input.LA(1);

					if ((LA21_0 == DOT)) {
						alt21 = 1;
					}

					switch (alt21) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:74:54:
						// dot id open_bracket arguments close_bracket
					{
						pushFollow(FOLLOW_dot_in_method_call352);
						dot();

						state._fsp--;
						if (state.failed)
							return;
						pushFollow(FOLLOW_id_in_method_call354);
						id();

						state._fsp--;
						if (state.failed)
							return;
						pushFollow(FOLLOW_open_bracket_in_method_call356);
						open_bracket();

						state._fsp--;
						if (state.failed)
							return;
						pushFollow(FOLLOW_arguments_in_method_call358);
						arguments();

						state._fsp--;
						if (state.failed)
							return;
						pushFollow(FOLLOW_close_bracket_in_method_call360);
						close_bracket();

						state._fsp--;
						if (state.failed)
							return;

					}
						break;

					default:
						break loop21;
					}
				} while (true);

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 12, method_call_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "method_call"

	// $ANTLR start "annotation"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:77:1:
	// annotation : annotation_name ( open_bracket value ( comma value )*
	// close_bracket )? ;
	public final void annotation() throws RecognitionException {
		int annotation_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 13)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:78:2:
			// ( annotation_name ( open_bracket value ( comma value )*
			// close_bracket )? )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:78:4:
			// annotation_name ( open_bracket value ( comma value )*
			// close_bracket )?
			{
				pushFollow(FOLLOW_annotation_name_in_annotation373);
				annotation_name();

				state._fsp--;
				if (state.failed)
					return;
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:78:20:
				// ( open_bracket value ( comma value )* close_bracket )?
				int alt23 = 2;
				int LA23_0 = input.LA(1);

				if ((LA23_0 == OPEN_BRACKET)) {
					alt23 = 1;
				}
				switch (alt23) {
				case 1:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:78:21:
					// open_bracket value ( comma value )* close_bracket
				{
					pushFollow(FOLLOW_open_bracket_in_annotation376);
					open_bracket();

					state._fsp--;
					if (state.failed)
						return;
					pushFollow(FOLLOW_value_in_annotation378);
					value();

					state._fsp--;
					if (state.failed)
						return;
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:78:40:
					// ( comma value )*
					loop22: do {
						int alt22 = 2;
						int LA22_0 = input.LA(1);

						if ((LA22_0 == COMMA)) {
							alt22 = 1;
						}

						switch (alt22) {
						case 1:
							// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:78:41:
							// comma value
						{
							pushFollow(FOLLOW_comma_in_annotation381);
							comma();

							state._fsp--;
							if (state.failed)
								return;
							pushFollow(FOLLOW_value_in_annotation383);
							value();

							state._fsp--;
							if (state.failed)
								return;

						}
							break;

						default:
							break loop22;
						}
					} while (true);

					pushFollow(FOLLOW_close_bracket_in_annotation387);
					close_bracket();

					state._fsp--;
					if (state.failed)
						return;

				}
					break;

				}

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 13, annotation_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "annotation"

	// $ANTLR start "value"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:81:1:
	// value : ( ( constant | class_name dot class_ | ( unary )? variable_name (
	// unary )? | method_call | new_class ) ( binary_operator value )* | (
	// open_bracket ( | constant | class_name dot class_ | ( unary )?
	// variable_name ( unary )? | method_call | new_class ) ) ( binary_operator
	// value )* close_bracket );
	public final void value() throws RecognitionException {
		int value_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 14)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:81:7:
			// ( ( constant | class_name dot class_ | ( unary )? variable_name (
			// unary )? | method_call | new_class ) ( binary_operator value )* |
			// ( open_bracket ( | constant | class_name dot class_ | ( unary )?
			// variable_name ( unary )? | method_call | new_class ) ) (
			// binary_operator value )* close_bracket )
			int alt32 = 2;
			int LA32_0 = input.LA(1);

			if ((LA32_0 == NEW || LA32_0 == NULL
					|| (LA32_0 >= INC && LA32_0 <= DEC)
					|| (LA32_0 >= BOOL_CONST && LA32_0 <= INT_CONST)
					|| LA32_0 == FLOAT_CONST || (LA32_0 >= STRING_CONST && LA32_0 <= CHAR_CONST))) {
				alt32 = 1;
			} else if ((LA32_0 == OPEN_BRACKET)) {
				alt32 = 2;
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return;
				}
				NoViableAltException nvae = new NoViableAltException("", 32, 0,
						input);

				throw nvae;
			}
			switch (alt32) {
			case 1:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:82:2:
				// ( constant | class_name dot class_ | ( unary )? variable_name
				// ( unary )? | method_call | new_class ) ( binary_operator
				// value )*
			{
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:82:2:
				// ( constant | class_name dot class_ | ( unary )? variable_name
				// ( unary )? | method_call | new_class )
				int alt26 = 5;
				alt26 = dfa26.predict(input);
				switch (alt26) {
				case 1:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:82:4:
					// constant
				{
					pushFollow(FOLLOW_constant_in_value402);
					constant();

					state._fsp--;
					if (state.failed)
						return;

				}
					break;
				case 2:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:83:4:
					// class_name dot class_
				{
					pushFollow(FOLLOW_class_name_in_value407);
					class_name();

					state._fsp--;
					if (state.failed)
						return;
					pushFollow(FOLLOW_dot_in_value409);
					dot();

					state._fsp--;
					if (state.failed)
						return;
					pushFollow(FOLLOW_class__in_value411);
					class_();

					state._fsp--;
					if (state.failed)
						return;

				}
					break;
				case 3:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:84:4:
					// ( unary )? variable_name ( unary )?
				{
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:84:4:
					// ( unary )?
					int alt24 = 2;
					int LA24_0 = input.LA(1);

					if (((LA24_0 >= INC && LA24_0 <= DEC))) {
						alt24 = 1;
					}
					switch (alt24) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
						// unary
					{
						pushFollow(FOLLOW_unary_in_value416);
						unary();

						state._fsp--;
						if (state.failed)
							return;

					}
						break;

					}

					pushFollow(FOLLOW_variable_name_in_value419);
					variable_name();

					state._fsp--;
					if (state.failed)
						return;
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:84:25:
					// ( unary )?
					int alt25 = 2;
					int LA25_0 = input.LA(1);

					if (((LA25_0 >= INC && LA25_0 <= DEC))) {
						alt25 = 1;
					}
					switch (alt25) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
						// unary
					{
						pushFollow(FOLLOW_unary_in_value421);
						unary();

						state._fsp--;
						if (state.failed)
							return;

					}
						break;

					}

				}
					break;
				case 4:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:85:4:
					// method_call
				{
					pushFollow(FOLLOW_method_call_in_value427);
					method_call();

					state._fsp--;
					if (state.failed)
						return;

				}
					break;
				case 5:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:86:4:
					// new_class
				{
					pushFollow(FOLLOW_new_class_in_value432);
					new_class();

					state._fsp--;
					if (state.failed)
						return;

				}
					break;

				}

				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:87:5:
				// ( binary_operator value )*
				loop27: do {
					int alt27 = 2;
					alt27 = dfa27.predict(input);
					switch (alt27) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:87:6:
						// binary_operator value
					{
						pushFollow(FOLLOW_binary_operator_in_value439);
						binary_operator();

						state._fsp--;
						if (state.failed)
							return;
						pushFollow(FOLLOW_value_in_value441);
						value();

						state._fsp--;
						if (state.failed)
							return;

					}
						break;

					default:
						break loop27;
					}
				} while (true);

			}
				break;
			case 2:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:89:2:
				// ( open_bracket ( | constant | class_name dot class_ | ( unary
				// )? variable_name ( unary )? | method_call | new_class ) ) (
				// binary_operator value )* close_bracket
			{
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:89:2:
				// ( open_bracket ( | constant | class_name dot class_ | ( unary
				// )? variable_name ( unary )? | method_call | new_class ) )
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:89:4:
				// open_bracket ( | constant | class_name dot class_ | ( unary
				// )? variable_name ( unary )? | method_call | new_class )
				{
					pushFollow(FOLLOW_open_bracket_in_value451);
					open_bracket();

					state._fsp--;
					if (state.failed)
						return;
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:89:17:
					// ( | constant | class_name dot class_ | ( unary )?
					// variable_name ( unary )? | method_call | new_class )
					int alt30 = 6;
					alt30 = dfa30.predict(input);
					switch (alt30) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:2:
					{
					}
						break;
					case 2:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:90:4:
						// constant
					{
						pushFollow(FOLLOW_constant_in_value458);
						constant();

						state._fsp--;
						if (state.failed)
							return;

					}
						break;
					case 3:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:91:4:
						// class_name dot class_
					{
						pushFollow(FOLLOW_class_name_in_value463);
						class_name();

						state._fsp--;
						if (state.failed)
							return;
						pushFollow(FOLLOW_dot_in_value465);
						dot();

						state._fsp--;
						if (state.failed)
							return;
						pushFollow(FOLLOW_class__in_value467);
						class_();

						state._fsp--;
						if (state.failed)
							return;

					}
						break;
					case 4:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:92:4:
						// ( unary )? variable_name ( unary )?
					{
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:92:4:
						// ( unary )?
						int alt28 = 2;
						int LA28_0 = input.LA(1);

						if (((LA28_0 >= INC && LA28_0 <= DEC))) {
							alt28 = 1;
						}
						switch (alt28) {
						case 1:
							// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
							// unary
						{
							pushFollow(FOLLOW_unary_in_value472);
							unary();

							state._fsp--;
							if (state.failed)
								return;

						}
							break;

						}

						pushFollow(FOLLOW_variable_name_in_value475);
						variable_name();

						state._fsp--;
						if (state.failed)
							return;
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:92:25:
						// ( unary )?
						int alt29 = 2;
						int LA29_0 = input.LA(1);

						if (((LA29_0 >= INC && LA29_0 <= DEC))) {
							alt29 = 1;
						}
						switch (alt29) {
						case 1:
							// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
							// unary
						{
							pushFollow(FOLLOW_unary_in_value477);
							unary();

							state._fsp--;
							if (state.failed)
								return;

						}
							break;

						}

					}
						break;
					case 5:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:93:4:
						// method_call
					{
						pushFollow(FOLLOW_method_call_in_value483);
						method_call();

						state._fsp--;
						if (state.failed)
							return;

					}
						break;
					case 6:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:94:4:
						// new_class
					{
						pushFollow(FOLLOW_new_class_in_value488);
						new_class();

						state._fsp--;
						if (state.failed)
							return;

					}
						break;

					}

				}

				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:95:6:
				// ( binary_operator value )*
				loop31: do {
					int alt31 = 2;
					int LA31_0 = input.LA(1);

					if (((LA31_0 >= LE && LA31_0 <= UNEQUAL)
							|| (LA31_0 >= PLUS && LA31_0 <= STAR) || (LA31_0 >= LT && LA31_0 <= GT))) {
						alt31 = 1;
					}

					switch (alt31) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:95:7:
						// binary_operator value
					{
						pushFollow(FOLLOW_binary_operator_in_value496);
						binary_operator();

						state._fsp--;
						if (state.failed)
							return;
						pushFollow(FOLLOW_value_in_value498);
						value();

						state._fsp--;
						if (state.failed)
							return;

					}
						break;

					default:
						break loop31;
					}
				} while (true);

				pushFollow(FOLLOW_close_bracket_in_value503);
				close_bracket();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;

			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 14, value_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "value"

	// $ANTLR start "constant"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:98:1:
	// constant : ( int_const | string_const | float_const | char_const |
	// null_const | boolean_const );
	public final void constant() throws RecognitionException {
		int constant_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 15)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:98:9:
			// ( int_const | string_const | float_const | char_const |
			// null_const | boolean_const )
			int alt33 = 6;
			switch (input.LA(1)) {
			case INT_CONST: {
				alt33 = 1;
			}
				break;
			case STRING_CONST: {
				alt33 = 2;
			}
				break;
			case FLOAT_CONST: {
				alt33 = 3;
			}
				break;
			case CHAR_CONST: {
				alt33 = 4;
			}
				break;
			case NULL: {
				alt33 = 5;
			}
				break;
			case BOOL_CONST: {
				alt33 = 6;
			}
				break;
			default:
				if (state.backtracking > 0) {
					state.failed = true;
					return;
				}
				NoViableAltException nvae = new NoViableAltException("", 33, 0,
						input);

				throw nvae;
			}

			switch (alt33) {
			case 1:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:98:11:
				// int_const
			{
				pushFollow(FOLLOW_int_const_in_constant512);
				int_const();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 2:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:99:4:
				// string_const
			{
				pushFollow(FOLLOW_string_const_in_constant517);
				string_const();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 3:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:100:4:
				// float_const
			{
				pushFollow(FOLLOW_float_const_in_constant522);
				float_const();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 4:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:101:4:
				// char_const
			{
				pushFollow(FOLLOW_char_const_in_constant527);
				char_const();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 5:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:102:4:
				// null_const
			{
				pushFollow(FOLLOW_null_const_in_constant532);
				null_const();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 6:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:103:4:
				// boolean_const
			{
				pushFollow(FOLLOW_boolean_const_in_constant537);
				boolean_const();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;

			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 15, constant_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "constant"

	// $ANTLR start "new_class"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:106:1:
	// new_class : new_ class_name open_bracket arguments close_bracket ;
	public final void new_class() throws RecognitionException {
		int new_class_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 16)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:107:2:
			// ( new_ class_name open_bracket arguments close_bracket )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:107:4:
			// new_ class_name open_bracket arguments close_bracket
			{
				pushFollow(FOLLOW_new__in_new_class549);
				new_();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_class_name_in_new_class551);
				class_name();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_open_bracket_in_new_class553);
				open_bracket();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_arguments_in_new_class555);
				arguments();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_close_bracket_in_new_class557);
				close_bracket();

				state._fsp--;
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 16, new_class_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "new_class"

	// $ANTLR start "arguments"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:110:1:
	// arguments : ( value ( comma value )* )? ;
	public final void arguments() throws RecognitionException {
		int arguments_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 17)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:2:
			// ( ( value ( comma value )* )? )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:4:
			// ( value ( comma value )* )?
			{
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:4:
				// ( value ( comma value )* )?
				int alt35 = 2;
				int LA35_0 = input.LA(1);

				if ((LA35_0 == NEW || LA35_0 == NULL
						|| (LA35_0 >= INC && LA35_0 <= DEC)
						|| LA35_0 == OPEN_BRACKET
						|| (LA35_0 >= BOOL_CONST && LA35_0 <= INT_CONST)
						|| LA35_0 == FLOAT_CONST || (LA35_0 >= STRING_CONST && LA35_0 <= CHAR_CONST))) {
					alt35 = 1;
				}
				switch (alt35) {
				case 1:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:5:
					// value ( comma value )*
				{
					pushFollow(FOLLOW_value_in_arguments570);
					value();

					state._fsp--;
					if (state.failed)
						return;
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:11:
					// ( comma value )*
					loop34: do {
						int alt34 = 2;
						int LA34_0 = input.LA(1);

						if ((LA34_0 == COMMA)) {
							alt34 = 1;
						}

						switch (alt34) {
						case 1:
							// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:111:12:
							// comma value
						{
							pushFollow(FOLLOW_comma_in_arguments573);
							comma();

							state._fsp--;
							if (state.failed)
								return;
							pushFollow(FOLLOW_value_in_arguments575);
							value();

							state._fsp--;
							if (state.failed)
								return;

						}
							break;

						default:
							break loop34;
						}
					} while (true);

				}
					break;

				}

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 17, arguments_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "arguments"

	// $ANTLR start "code"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:1:
	// code : ( statement | block_begin ( statement )* block_end );
	public final void code() throws RecognitionException {
		int code_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 18)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:6:
			// ( statement | block_begin ( statement )* block_end )
			int alt37 = 2;
			int LA37_0 = input.LA(1);

			if ((LA37_0 == VOID || LA37_0 == RETURN
					|| (LA37_0 >= THIS && LA37_0 <= IF) || LA37_0 == TRY
					|| (LA37_0 >= BOOLEAN && LA37_0 <= DOUBLE)
					|| (LA37_0 >= INC && LA37_0 <= DEC)
					|| (LA37_0 >= SEMICOLON && LA37_0 <= AT) || LA37_0 == ID)) {
				alt37 = 1;
			} else if ((LA37_0 == OPEN_CURLY_BRACKET)) {
				alt37 = 2;
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return;
				}
				NoViableAltException nvae = new NoViableAltException("", 37, 0,
						input);

				throw nvae;
			}
			switch (alt37) {
			case 1:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:114:8:
				// statement
			{
				pushFollow(FOLLOW_statement_in_code589);
				statement();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 2:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:4:
				// block_begin ( statement )* block_end
			{
				pushFollow(FOLLOW_block_begin_in_code594);
				block_begin();

				state._fsp--;
				if (state.failed)
					return;
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:115:16:
				// ( statement )*
				loop36: do {
					int alt36 = 2;
					int LA36_0 = input.LA(1);

					if ((LA36_0 == VOID || LA36_0 == RETURN
							|| (LA36_0 >= THIS && LA36_0 <= IF)
							|| LA36_0 == TRY
							|| (LA36_0 >= BOOLEAN && LA36_0 <= DOUBLE)
							|| (LA36_0 >= INC && LA36_0 <= DEC)
							|| (LA36_0 >= SEMICOLON && LA36_0 <= AT) || LA36_0 == ID)) {
						alt36 = 1;
					}

					switch (alt36) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
						// statement
					{
						pushFollow(FOLLOW_statement_in_code596);
						statement();

						state._fsp--;
						if (state.failed)
							return;

					}
						break;

					default:
						break loop36;
					}
				} while (true);

				pushFollow(FOLLOW_block_end_in_code599);
				block_end();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;

			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 18, code_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "code"

	// $ANTLR start "statement"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:118:1:
	// statement : ( variable_assignment semicolon | variable_def semicolon |
	// method_call semicolon | semicolon | return_statement | ( unary )?
	// variable_name ( unary )? | for_loop | while_loop | do_loop | if_else |
	// try_catch );
	public final void statement() throws RecognitionException {
		int statement_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 19)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:119:2:
			// ( variable_assignment semicolon | variable_def semicolon |
			// method_call semicolon | semicolon | return_statement | ( unary )?
			// variable_name ( unary )? | for_loop | while_loop | do_loop |
			// if_else | try_catch )
			int alt40 = 11;
			alt40 = dfa40.predict(input);
			switch (alt40) {
			case 1:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:119:4:
				// variable_assignment semicolon
			{
				pushFollow(FOLLOW_variable_assignment_in_statement611);
				variable_assignment();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_semicolon_in_statement613);
				semicolon();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 2:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:120:4:
				// variable_def semicolon
			{
				pushFollow(FOLLOW_variable_def_in_statement618);
				variable_def();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_semicolon_in_statement620);
				semicolon();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 3:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:121:4:
				// method_call semicolon
			{
				pushFollow(FOLLOW_method_call_in_statement625);
				method_call();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_semicolon_in_statement627);
				semicolon();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 4:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:122:5:
				// semicolon
			{
				pushFollow(FOLLOW_semicolon_in_statement633);
				semicolon();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 5:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:123:4:
				// return_statement
			{
				pushFollow(FOLLOW_return_statement_in_statement638);
				return_statement();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 6:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:124:4:
				// ( unary )? variable_name ( unary )?
			{
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:124:4:
				// ( unary )?
				int alt38 = 2;
				int LA38_0 = input.LA(1);

				if (((LA38_0 >= INC && LA38_0 <= DEC))) {
					alt38 = 1;
				}
				switch (alt38) {
				case 1:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
					// unary
				{
					pushFollow(FOLLOW_unary_in_statement643);
					unary();

					state._fsp--;
					if (state.failed)
						return;

				}
					break;

				}

				pushFollow(FOLLOW_variable_name_in_statement646);
				variable_name();

				state._fsp--;
				if (state.failed)
					return;
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:124:25:
				// ( unary )?
				int alt39 = 2;
				int LA39_0 = input.LA(1);

				if ((LA39_0 == INC)) {
					if ((synpred62_JavaParser())) {
						alt39 = 1;
					}
				} else if ((LA39_0 == DEC)) {
					if ((synpred62_JavaParser())) {
						alt39 = 1;
					}
				}
				switch (alt39) {
				case 1:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
					// unary
				{
					pushFollow(FOLLOW_unary_in_statement648);
					unary();

					state._fsp--;
					if (state.failed)
						return;

				}
					break;

				}

			}
				break;
			case 7:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:125:4:
				// for_loop
			{
				pushFollow(FOLLOW_for_loop_in_statement654);
				for_loop();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 8:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:126:4:
				// while_loop
			{
				pushFollow(FOLLOW_while_loop_in_statement659);
				while_loop();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 9:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:127:4:
				// do_loop
			{
				pushFollow(FOLLOW_do_loop_in_statement664);
				do_loop();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 10:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:128:4:
				// if_else
			{
				pushFollow(FOLLOW_if_else_in_statement669);
				if_else();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 11:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:129:4:
				// try_catch
			{
				pushFollow(FOLLOW_try_catch_in_statement674);
				try_catch();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;

			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 19, statement_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "statement"

	// $ANTLR start "statement_wosemicolon"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:132:1:
	// statement_wosemicolon : ( variable_assignment | variable_def |
	// method_call | return_statement | ( unary )? variable_name ( unary )? |
	// for_loop | while_loop | do_loop | if_else | try_catch );
	public final void statement_wosemicolon() throws RecognitionException {
		int statement_wosemicolon_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 20)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:133:2:
			// ( variable_assignment | variable_def | method_call |
			// return_statement | ( unary )? variable_name ( unary )? | for_loop
			// | while_loop | do_loop | if_else | try_catch )
			int alt43 = 10;
			alt43 = dfa43.predict(input);
			switch (alt43) {
			case 1:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:133:4:
				// variable_assignment
			{
				pushFollow(FOLLOW_variable_assignment_in_statement_wosemicolon685);
				variable_assignment();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 2:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:134:4:
				// variable_def
			{
				pushFollow(FOLLOW_variable_def_in_statement_wosemicolon690);
				variable_def();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 3:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:135:4:
				// method_call
			{
				pushFollow(FOLLOW_method_call_in_statement_wosemicolon695);
				method_call();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 4:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:136:4:
				// return_statement
			{
				pushFollow(FOLLOW_return_statement_in_statement_wosemicolon700);
				return_statement();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 5:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:137:4:
				// ( unary )? variable_name ( unary )?
			{
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:137:4:
				// ( unary )?
				int alt41 = 2;
				int LA41_0 = input.LA(1);

				if (((LA41_0 >= INC && LA41_0 <= DEC))) {
					alt41 = 1;
				}
				switch (alt41) {
				case 1:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
					// unary
				{
					pushFollow(FOLLOW_unary_in_statement_wosemicolon705);
					unary();

					state._fsp--;
					if (state.failed)
						return;

				}
					break;

				}

				pushFollow(FOLLOW_variable_name_in_statement_wosemicolon708);
				variable_name();

				state._fsp--;
				if (state.failed)
					return;
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:137:25:
				// ( unary )?
				int alt42 = 2;
				int LA42_0 = input.LA(1);

				if (((LA42_0 >= INC && LA42_0 <= DEC))) {
					alt42 = 1;
				}
				switch (alt42) {
				case 1:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
					// unary
				{
					pushFollow(FOLLOW_unary_in_statement_wosemicolon710);
					unary();

					state._fsp--;
					if (state.failed)
						return;

				}
					break;

				}

			}
				break;
			case 6:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:138:4:
				// for_loop
			{
				pushFollow(FOLLOW_for_loop_in_statement_wosemicolon716);
				for_loop();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 7:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:139:4:
				// while_loop
			{
				pushFollow(FOLLOW_while_loop_in_statement_wosemicolon721);
				while_loop();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 8:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:140:4:
				// do_loop
			{
				pushFollow(FOLLOW_do_loop_in_statement_wosemicolon726);
				do_loop();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 9:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:141:4:
				// if_else
			{
				pushFollow(FOLLOW_if_else_in_statement_wosemicolon731);
				if_else();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 10:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:142:4:
				// try_catch
			{
				pushFollow(FOLLOW_try_catch_in_statement_wosemicolon736);
				try_catch();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;

			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 20, statement_wosemicolon_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "statement_wosemicolon"

	// $ANTLR start "return_statement"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:145:1:
	// return_statement : return_ value semicolon ;
	public final void return_statement() throws RecognitionException {
		int return_statement_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 21)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:146:2:
			// ( return_ value semicolon )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:146:4:
			// return_ value semicolon
			{
				pushFollow(FOLLOW_return__in_return_statement748);
				return_();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_value_in_return_statement750);
				value();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_semicolon_in_return_statement752);
				semicolon();

				state._fsp--;
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 21, return_statement_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "return_statement"

	// $ANTLR start "variable_assignment"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:149:1:
	// variable_assignment : ( this_ dot | super_ dot )? variable_name assign
	// value ;
	public final void variable_assignment() throws RecognitionException {
		int variable_assignment_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 22)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:150:2:
			// ( ( this_ dot | super_ dot )? variable_name assign value )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:150:4:
			// ( this_ dot | super_ dot )? variable_name assign value
			{
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:150:4:
				// ( this_ dot | super_ dot )?
				int alt44 = 3;
				int LA44_0 = input.LA(1);

				if ((LA44_0 == THIS)) {
					alt44 = 1;
				} else if ((LA44_0 == SUPER)) {
					alt44 = 2;
				}
				switch (alt44) {
				case 1:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:150:5:
					// this_ dot
				{
					pushFollow(FOLLOW_this__in_variable_assignment765);
					this_();

					state._fsp--;
					if (state.failed)
						return;
					pushFollow(FOLLOW_dot_in_variable_assignment767);
					dot();

					state._fsp--;
					if (state.failed)
						return;

				}
					break;
				case 2:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:150:17:
					// super_ dot
				{
					pushFollow(FOLLOW_super__in_variable_assignment771);
					super_();

					state._fsp--;
					if (state.failed)
						return;
					pushFollow(FOLLOW_dot_in_variable_assignment773);
					dot();

					state._fsp--;
					if (state.failed)
						return;

				}
					break;

				}

				pushFollow(FOLLOW_variable_name_in_variable_assignment777);
				variable_name();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_assign_in_variable_assignment779);
				assign();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_value_in_variable_assignment781);
				value();

				state._fsp--;
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 22, variable_assignment_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "variable_assignment"

	// $ANTLR start "for_loop"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:1:
	// for_loop : ( for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )?
	// semicolon ( value )? semicolon ( statement_wosemicolon ( comma
	// statement_wosemicolon )* )? CLOSE_BRACKET code | for_ OPEN_BRACKET
	// variable_type variable_name colon value CLOSE_BRACKET code );
	public final void for_loop() throws RecognitionException {
		int for_loop_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 23)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:9:
			// ( for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )?
			// semicolon ( value )? semicolon ( statement_wosemicolon ( comma
			// statement_wosemicolon )* )? CLOSE_BRACKET code | for_
			// OPEN_BRACKET variable_type variable_name colon value
			// CLOSE_BRACKET code )
			int alt50 = 2;
			int LA50_0 = input.LA(1);

			if ((LA50_0 == FOR)) {
				if ((synpred86_JavaParser())) {
					alt50 = 1;
				} else if ((true)) {
					alt50 = 2;
				}
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return;
				}
				NoViableAltException nvae = new NoViableAltException("", 50, 0,
						input);

				throw nvae;
			}
			switch (alt50) {
			case 1:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:11:
				// for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )?
				// semicolon ( value )? semicolon ( statement_wosemicolon (
				// comma statement_wosemicolon )* )? CLOSE_BRACKET code
			{
				pushFollow(FOLLOW_for__in_for_loop791);
				for_();

				state._fsp--;
				if (state.failed)
					return;
				match(input, OPEN_BRACKET, FOLLOW_OPEN_BRACKET_in_for_loop793);
				if (state.failed)
					return;
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:29:
				// ( variable_def ( comma variable_def )* )?
				int alt46 = 2;
				int LA46_0 = input.LA(1);

				if ((LA46_0 == VOID || (LA46_0 >= BOOLEAN && LA46_0 <= DOUBLE)
						|| LA46_0 == AT || LA46_0 == ID)) {
					alt46 = 1;
				}
				switch (alt46) {
				case 1:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:30:
					// variable_def ( comma variable_def )*
				{
					pushFollow(FOLLOW_variable_def_in_for_loop796);
					variable_def();

					state._fsp--;
					if (state.failed)
						return;
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:43:
					// ( comma variable_def )*
					loop45: do {
						int alt45 = 2;
						int LA45_0 = input.LA(1);

						if ((LA45_0 == COMMA)) {
							alt45 = 1;
						}

						switch (alt45) {
						case 1:
							// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:44:
							// comma variable_def
						{
							pushFollow(FOLLOW_comma_in_for_loop799);
							comma();

							state._fsp--;
							if (state.failed)
								return;
							pushFollow(FOLLOW_variable_def_in_for_loop801);
							variable_def();

							state._fsp--;
							if (state.failed)
								return;

						}
							break;

						default:
							break loop45;
						}
					} while (true);

				}
					break;

				}

				pushFollow(FOLLOW_semicolon_in_for_loop807);
				semicolon();

				state._fsp--;
				if (state.failed)
					return;
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:77:
				// ( value )?
				int alt47 = 2;
				int LA47_0 = input.LA(1);

				if ((LA47_0 == NEW || LA47_0 == NULL
						|| (LA47_0 >= INC && LA47_0 <= DEC)
						|| LA47_0 == OPEN_BRACKET
						|| (LA47_0 >= BOOL_CONST && LA47_0 <= INT_CONST)
						|| LA47_0 == FLOAT_CONST || (LA47_0 >= STRING_CONST && LA47_0 <= CHAR_CONST))) {
					alt47 = 1;
				}
				switch (alt47) {
				case 1:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
					// value
				{
					pushFollow(FOLLOW_value_in_for_loop809);
					value();

					state._fsp--;
					if (state.failed)
						return;

				}
					break;

				}

				pushFollow(FOLLOW_semicolon_in_for_loop812);
				semicolon();

				state._fsp--;
				if (state.failed)
					return;
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:94:
				// ( statement_wosemicolon ( comma statement_wosemicolon )* )?
				int alt49 = 2;
				int LA49_0 = input.LA(1);

				if ((LA49_0 == VOID || LA49_0 == RETURN
						|| (LA49_0 >= THIS && LA49_0 <= IF) || LA49_0 == TRY
						|| (LA49_0 >= BOOLEAN && LA49_0 <= DOUBLE)
						|| (LA49_0 >= INC && LA49_0 <= DEC) || LA49_0 == AT || LA49_0 == ID)) {
					alt49 = 1;
				}
				switch (alt49) {
				case 1:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:95:
					// statement_wosemicolon ( comma statement_wosemicolon )*
				{
					pushFollow(FOLLOW_statement_wosemicolon_in_for_loop815);
					statement_wosemicolon();

					state._fsp--;
					if (state.failed)
						return;
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:117:
					// ( comma statement_wosemicolon )*
					loop48: do {
						int alt48 = 2;
						int LA48_0 = input.LA(1);

						if ((LA48_0 == COMMA)) {
							alt48 = 1;
						}

						switch (alt48) {
						case 1:
							// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:118:
							// comma statement_wosemicolon
						{
							pushFollow(FOLLOW_comma_in_for_loop818);
							comma();

							state._fsp--;
							if (state.failed)
								return;
							pushFollow(FOLLOW_statement_wosemicolon_in_for_loop820);
							statement_wosemicolon();

							state._fsp--;
							if (state.failed)
								return;

						}
							break;

						default:
							break loop48;
						}
					} while (true);

				}
					break;

				}

				match(input, CLOSE_BRACKET, FOLLOW_CLOSE_BRACKET_in_for_loop826);
				if (state.failed)
					return;
				pushFollow(FOLLOW_code_in_for_loop828);
				code();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 2:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:154:4:
				// for_ OPEN_BRACKET variable_type variable_name colon value
				// CLOSE_BRACKET code
			{
				pushFollow(FOLLOW_for__in_for_loop833);
				for_();

				state._fsp--;
				if (state.failed)
					return;
				match(input, OPEN_BRACKET, FOLLOW_OPEN_BRACKET_in_for_loop835);
				if (state.failed)
					return;
				pushFollow(FOLLOW_variable_type_in_for_loop837);
				variable_type();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_variable_name_in_for_loop839);
				variable_name();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_colon_in_for_loop841);
				colon();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_value_in_for_loop843);
				value();

				state._fsp--;
				if (state.failed)
					return;
				match(input, CLOSE_BRACKET, FOLLOW_CLOSE_BRACKET_in_for_loop845);
				if (state.failed)
					return;
				pushFollow(FOLLOW_code_in_for_loop847);
				code();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;

			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 23, for_loop_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "for_loop"

	// $ANTLR start "while_loop"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:157:1:
	// while_loop : while_ OPEN_BRACKET value CLOSE_BRACKET code ;
	public final void while_loop() throws RecognitionException {
		int while_loop_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 24)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:158:2:
			// ( while_ OPEN_BRACKET value CLOSE_BRACKET code )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:158:4:
			// while_ OPEN_BRACKET value CLOSE_BRACKET code
			{
				pushFollow(FOLLOW_while__in_while_loop859);
				while_();

				state._fsp--;
				if (state.failed)
					return;
				match(input, OPEN_BRACKET, FOLLOW_OPEN_BRACKET_in_while_loop861);
				if (state.failed)
					return;
				pushFollow(FOLLOW_value_in_while_loop863);
				value();

				state._fsp--;
				if (state.failed)
					return;
				match(input, CLOSE_BRACKET,
						FOLLOW_CLOSE_BRACKET_in_while_loop865);
				if (state.failed)
					return;
				pushFollow(FOLLOW_code_in_while_loop867);
				code();

				state._fsp--;
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 24, while_loop_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "while_loop"

	// $ANTLR start "do_loop"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:161:1:
	// do_loop : do_ code while_ OPEN_BRACKET value CLOSE_BRACKET ;
	public final void do_loop() throws RecognitionException {
		int do_loop_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 25)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:161:9:
			// ( do_ code while_ OPEN_BRACKET value CLOSE_BRACKET )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:161:11:
			// do_ code while_ OPEN_BRACKET value CLOSE_BRACKET
			{
				pushFollow(FOLLOW_do__in_do_loop878);
				do_();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_code_in_do_loop880);
				code();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_while__in_do_loop882);
				while_();

				state._fsp--;
				if (state.failed)
					return;
				match(input, OPEN_BRACKET, FOLLOW_OPEN_BRACKET_in_do_loop884);
				if (state.failed)
					return;
				pushFollow(FOLLOW_value_in_do_loop886);
				value();

				state._fsp--;
				if (state.failed)
					return;
				match(input, CLOSE_BRACKET, FOLLOW_CLOSE_BRACKET_in_do_loop888);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 25, do_loop_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "do_loop"

	// $ANTLR start "if_else"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:164:1:
	// if_else : if_ OPEN_BRACKET value CLOSE_BRACKET code else_ code ;
	public final void if_else() throws RecognitionException {
		int if_else_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 26)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:164:9:
			// ( if_ OPEN_BRACKET value CLOSE_BRACKET code else_ code )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:164:11:
			// if_ OPEN_BRACKET value CLOSE_BRACKET code else_ code
			{
				pushFollow(FOLLOW_if__in_if_else899);
				if_();

				state._fsp--;
				if (state.failed)
					return;
				match(input, OPEN_BRACKET, FOLLOW_OPEN_BRACKET_in_if_else901);
				if (state.failed)
					return;
				pushFollow(FOLLOW_value_in_if_else903);
				value();

				state._fsp--;
				if (state.failed)
					return;
				match(input, CLOSE_BRACKET, FOLLOW_CLOSE_BRACKET_in_if_else905);
				if (state.failed)
					return;
				pushFollow(FOLLOW_code_in_if_else907);
				code();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_else__in_if_else909);
				else_();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_code_in_if_else911);
				code();

				state._fsp--;
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 26, if_else_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "if_else"

	// $ANTLR start "try_catch"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:167:1:
	// try_catch : try_ code ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )+ (
	// finally_ code )? ;
	public final void try_catch() throws RecognitionException {
		int try_catch_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 27)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:168:2:
			// ( try_ code ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )+ (
			// finally_ code )? )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:168:4:
			// try_ code ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )+ (
			// finally_ code )?
			{
				pushFollow(FOLLOW_try__in_try_catch922);
				try_();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_code_in_try_catch924);
				code();

				state._fsp--;
				if (state.failed)
					return;
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:168:14:
				// ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )+
				int cnt51 = 0;
				loop51: do {
					int alt51 = 2;
					int LA51_0 = input.LA(1);

					if ((LA51_0 == CATCH)) {
						if ((synpred87_JavaParser())) {
							alt51 = 1;
						}

					}

					switch (alt51) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:168:15:
						// catch_ OPEN_BRACKET id id CLOSE_BRACKET code
					{
						pushFollow(FOLLOW_catch__in_try_catch927);
						catch_();

						state._fsp--;
						if (state.failed)
							return;
						match(input, OPEN_BRACKET,
								FOLLOW_OPEN_BRACKET_in_try_catch929);
						if (state.failed)
							return;
						pushFollow(FOLLOW_id_in_try_catch931);
						id();

						state._fsp--;
						if (state.failed)
							return;
						pushFollow(FOLLOW_id_in_try_catch933);
						id();

						state._fsp--;
						if (state.failed)
							return;
						match(input, CLOSE_BRACKET,
								FOLLOW_CLOSE_BRACKET_in_try_catch935);
						if (state.failed)
							return;
						pushFollow(FOLLOW_code_in_try_catch937);
						code();

						state._fsp--;
						if (state.failed)
							return;

					}
						break;

					default:
						if (cnt51 >= 1)
							break loop51;
						if (state.backtracking > 0) {
							state.failed = true;
							return;
						}
						EarlyExitException eee = new EarlyExitException(51,
								input);
						throw eee;
					}
					cnt51++;
				} while (true);

				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:168:62:
				// ( finally_ code )?
				int alt52 = 2;
				int LA52_0 = input.LA(1);

				if ((LA52_0 == FINALLY)) {
					if ((synpred88_JavaParser())) {
						alt52 = 1;
					}
				}
				switch (alt52) {
				case 1:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:168:63:
					// finally_ code
				{
					pushFollow(FOLLOW_finally__in_try_catch942);
					finally_();

					state._fsp--;
					if (state.failed)
						return;
					pushFollow(FOLLOW_code_in_try_catch944);
					code();

					state._fsp--;
					if (state.failed)
						return;

				}
					break;

				}

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 27, try_catch_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "try_catch"

	// $ANTLR start "variable_type"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:172:1:
	// variable_type : ( primitive ( array )? | class_name ( array )? | void_ );
	public final void variable_type() throws RecognitionException {
		int variable_type_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 28)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:173:2:
			// ( primitive ( array )? | class_name ( array )? | void_ )
			int alt55 = 3;
			switch (input.LA(1)) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case SHORT:
			case INTEGER:
			case LONG:
			case FLOAT:
			case DOUBLE: {
				alt55 = 1;
			}
				break;
			case ID: {
				alt55 = 2;
			}
				break;
			case VOID: {
				alt55 = 3;
			}
				break;
			default:
				if (state.backtracking > 0) {
					state.failed = true;
					return;
				}
				NoViableAltException nvae = new NoViableAltException("", 55, 0,
						input);

				throw nvae;
			}

			switch (alt55) {
			case 1:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:173:4:
				// primitive ( array )?
			{
				pushFollow(FOLLOW_primitive_in_variable_type958);
				primitive();

				state._fsp--;
				if (state.failed)
					return;
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:173:14:
				// ( array )?
				int alt53 = 2;
				int LA53_0 = input.LA(1);

				if ((LA53_0 == OPEN_RECT_BRACKET)) {
					alt53 = 1;
				}
				switch (alt53) {
				case 1:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
					// array
				{
					pushFollow(FOLLOW_array_in_variable_type960);
					array();

					state._fsp--;
					if (state.failed)
						return;

				}
					break;

				}

			}
				break;
			case 2:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:174:4:
				// class_name ( array )?
			{
				pushFollow(FOLLOW_class_name_in_variable_type966);
				class_name();

				state._fsp--;
				if (state.failed)
					return;
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:174:15:
				// ( array )?
				int alt54 = 2;
				int LA54_0 = input.LA(1);

				if ((LA54_0 == OPEN_RECT_BRACKET)) {
					alt54 = 1;
				}
				switch (alt54) {
				case 1:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
					// array
				{
					pushFollow(FOLLOW_array_in_variable_type968);
					array();

					state._fsp--;
					if (state.failed)
						return;

				}
					break;

				}

			}
				break;
			case 3:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:175:4:
				// void_
			{
				pushFollow(FOLLOW_void__in_variable_type974);
				void_();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;

			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 28, variable_type_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "variable_type"

	public static class id_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "id"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:178:1:
	// id : ID ;
	public final JavaParser.id_return id() throws RecognitionException {
		JavaParser.id_return retval = new JavaParser.id_return();
		retval.start = input.LT(1);
		int id_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 29)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:178:4:
			// ( ID )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:178:6:
			// ID
			{
				match(input, ID, FOLLOW_ID_in_id984);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperant(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 29, id_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "id"

	public static class binary_operator_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "binary_operator"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:180:1:
	// binary_operator : ( PLUS | MINUS | STAR | SLASH | EQUAL | UNEQUAL | LT |
	// GT | LE | GE );
	public final JavaParser.binary_operator_return binary_operator()
			throws RecognitionException {
		JavaParser.binary_operator_return retval = new JavaParser.binary_operator_return();
		retval.start = input.LT(1);
		int binary_operator_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 30)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:180:16:
			// ( PLUS | MINUS | STAR | SLASH | EQUAL | UNEQUAL | LT | GT | LE |
			// GE )
			int alt56 = 10;
			switch (input.LA(1)) {
			case PLUS: {
				alt56 = 1;
			}
				break;
			case MINUS: {
				alt56 = 2;
			}
				break;
			case STAR: {
				alt56 = 3;
			}
				break;
			case SLASH: {
				alt56 = 4;
			}
				break;
			case EQUAL: {
				alt56 = 5;
			}
				break;
			case UNEQUAL: {
				alt56 = 6;
			}
				break;
			case LT: {
				alt56 = 7;
			}
				break;
			case GT: {
				alt56 = 8;
			}
				break;
			case LE: {
				alt56 = 9;
			}
				break;
			case GE: {
				alt56 = 10;
			}
				break;
			default:
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 56, 0,
						input);

				throw nvae;
			}

			switch (alt56) {
			case 1:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:180:18:
				// PLUS
			{
				match(input, PLUS, FOLLOW_PLUS_in_binary_operator993);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}
				break;
			case 2:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:181:4:
				// MINUS
			{
				match(input, MINUS, FOLLOW_MINUS_in_binary_operator1000);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}
				break;
			case 3:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:182:4:
				// STAR
			{
				match(input, STAR, FOLLOW_STAR_in_binary_operator1007);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}
				break;
			case 4:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:183:4:
				// SLASH
			{
				match(input, SLASH, FOLLOW_SLASH_in_binary_operator1014);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}
				break;
			case 5:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:184:4:
				// EQUAL
			{
				match(input, EQUAL, FOLLOW_EQUAL_in_binary_operator1021);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}
				break;
			case 6:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:185:4:
				// UNEQUAL
			{
				match(input, UNEQUAL, FOLLOW_UNEQUAL_in_binary_operator1028);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}
				break;
			case 7:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:186:4:
				// LT
			{
				match(input, LT, FOLLOW_LT_in_binary_operator1035);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}
				break;
			case 8:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:187:4:
				// GT
			{
				match(input, GT, FOLLOW_GT_in_binary_operator1042);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}
				break;
			case 9:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:188:4:
				// LE
			{
				match(input, LE, FOLLOW_LE_in_binary_operator1049);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}
				break;
			case 10:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:189:4:
				// GE
			{
				match(input, GE, FOLLOW_GE_in_binary_operator1056);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}
				break;

			}
			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 30, binary_operator_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "binary_operator"

	public static class unary_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "unary"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:192:1:
	// unary : ( INC | DEC );
	public final JavaParser.unary_return unary() throws RecognitionException {
		JavaParser.unary_return retval = new JavaParser.unary_return();
		retval.start = input.LT(1);
		int unary_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 31)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:192:7:
			// ( INC | DEC )
			int alt57 = 2;
			int LA57_0 = input.LA(1);

			if ((LA57_0 == INC)) {
				alt57 = 1;
			} else if ((LA57_0 == DEC)) {
				alt57 = 2;
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 57, 0,
						input);

				throw nvae;
			}
			switch (alt57) {
			case 1:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:192:9:
				// INC
			{
				match(input, INC, FOLLOW_INC_in_unary1068);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}
				break;
			case 2:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:193:4:
				// DEC
			{
				match(input, DEC, FOLLOW_DEC_in_unary1075);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}
				break;

			}
			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 31, unary_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "unary"

	public static class primitive_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "primitive"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:196:1:
	// primitive : ( BOOLEAN | BYTE | CHAR | SHORT | INTEGER | LONG | FLOAT |
	// DOUBLE );
	public final JavaParser.primitive_return primitive()
			throws RecognitionException {
		JavaParser.primitive_return retval = new JavaParser.primitive_return();
		retval.start = input.LT(1);
		int primitive_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 32)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:197:2:
			// ( BOOLEAN | BYTE | CHAR | SHORT | INTEGER | LONG | FLOAT | DOUBLE
			// )
			int alt58 = 8;
			switch (input.LA(1)) {
			case BOOLEAN: {
				alt58 = 1;
			}
				break;
			case BYTE: {
				alt58 = 2;
			}
				break;
			case CHAR: {
				alt58 = 3;
			}
				break;
			case SHORT: {
				alt58 = 4;
			}
				break;
			case INTEGER: {
				alt58 = 5;
			}
				break;
			case LONG: {
				alt58 = 6;
			}
				break;
			case FLOAT: {
				alt58 = 7;
			}
				break;
			case DOUBLE: {
				alt58 = 8;
			}
				break;
			default:
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 58, 0,
						input);

				throw nvae;
			}

			switch (alt58) {
			case 1:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:197:4:
				// BOOLEAN
			{
				match(input, BOOLEAN, FOLLOW_BOOLEAN_in_primitive1088);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperant(input.toString(retval.start, input
							.LT(-1)));
				}

			}
				break;
			case 2:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:198:4:
				// BYTE
			{
				match(input, BYTE, FOLLOW_BYTE_in_primitive1095);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperant(input.toString(retval.start, input
							.LT(-1)));
				}

			}
				break;
			case 3:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:199:4:
				// CHAR
			{
				match(input, CHAR, FOLLOW_CHAR_in_primitive1102);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperant(input.toString(retval.start, input
							.LT(-1)));
				}

			}
				break;
			case 4:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:200:4:
				// SHORT
			{
				match(input, SHORT, FOLLOW_SHORT_in_primitive1109);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperant(input.toString(retval.start, input
							.LT(-1)));
				}

			}
				break;
			case 5:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:201:4:
				// INTEGER
			{
				match(input, INTEGER, FOLLOW_INTEGER_in_primitive1116);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperant(input.toString(retval.start, input
							.LT(-1)));
				}

			}
				break;
			case 6:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:202:4:
				// LONG
			{
				match(input, LONG, FOLLOW_LONG_in_primitive1123);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperant(input.toString(retval.start, input
							.LT(-1)));
				}

			}
				break;
			case 7:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:203:4:
				// FLOAT
			{
				match(input, FLOAT, FOLLOW_FLOAT_in_primitive1130);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperant(input.toString(retval.start, input
							.LT(-1)));
				}

			}
				break;
			case 8:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:204:4:
				// DOUBLE
			{
				match(input, DOUBLE, FOLLOW_DOUBLE_in_primitive1137);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperant(input.toString(retval.start, input
							.LT(-1)));
				}

			}
				break;

			}
			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 32, primitive_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "primitive"

	public static class int_const_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "int_const"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:212:1:
	// int_const : INT_CONST ;
	public final JavaParser.int_const_return int_const()
			throws RecognitionException {
		JavaParser.int_const_return retval = new JavaParser.int_const_return();
		retval.start = input.LT(1);
		int int_const_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 33)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:213:2:
			// ( INT_CONST )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:213:4:
			// INT_CONST
			{
				match(input, INT_CONST, FOLLOW_INT_CONST_in_int_const1155);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperant(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 33, int_const_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "int_const"

	public static class string_const_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "string_const"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:214:1:
	// string_const : STRING_CONST ;
	public final JavaParser.string_const_return string_const()
			throws RecognitionException {
		JavaParser.string_const_return retval = new JavaParser.string_const_return();
		retval.start = input.LT(1);
		int string_const_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 34)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:215:2:
			// ( STRING_CONST )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:215:4:
			// STRING_CONST
			{
				match(input, STRING_CONST,
						FOLLOW_STRING_CONST_in_string_const1165);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperant(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 34, string_const_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "string_const"

	public static class float_const_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "float_const"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:216:1:
	// float_const : FLOAT_CONST ;
	public final JavaParser.float_const_return float_const()
			throws RecognitionException {
		JavaParser.float_const_return retval = new JavaParser.float_const_return();
		retval.start = input.LT(1);
		int float_const_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 35)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:217:2:
			// ( FLOAT_CONST )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:217:4:
			// FLOAT_CONST
			{
				match(input, FLOAT_CONST, FOLLOW_FLOAT_CONST_in_float_const1175);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperant(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 35, float_const_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "float_const"

	public static class char_const_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "char_const"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:218:1:
	// char_const : CHAR_CONST ;
	public final JavaParser.char_const_return char_const()
			throws RecognitionException {
		JavaParser.char_const_return retval = new JavaParser.char_const_return();
		retval.start = input.LT(1);
		int char_const_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 36)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:219:2:
			// ( CHAR_CONST )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:219:4:
			// CHAR_CONST
			{
				match(input, CHAR_CONST, FOLLOW_CHAR_CONST_in_char_const1185);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperant(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 36, char_const_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "char_const"

	public static class null_const_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "null_const"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:220:1:
	// null_const : NULL ;
	public final JavaParser.null_const_return null_const()
			throws RecognitionException {
		JavaParser.null_const_return retval = new JavaParser.null_const_return();
		retval.start = input.LT(1);
		int null_const_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 37)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:221:2:
			// ( NULL )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:221:4:
			// NULL
			{
				match(input, NULL, FOLLOW_NULL_in_null_const1195);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperant(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 37, null_const_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "null_const"

	public static class boolean_const_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "boolean_const"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:222:1:
	// boolean_const : BOOL_CONST ;
	public final JavaParser.boolean_const_return boolean_const()
			throws RecognitionException {
		JavaParser.boolean_const_return retval = new JavaParser.boolean_const_return();
		retval.start = input.LT(1);
		int boolean_const_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 38)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:223:2:
			// ( BOOL_CONST )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:223:4:
			// BOOL_CONST
			{
				match(input, BOOL_CONST, FOLLOW_BOOL_CONST_in_boolean_const1206);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperant(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 38, boolean_const_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "boolean_const"

	public static class package_name_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "package_name"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:229:1:
	// package_name : ( ID DOT )* ID ;
	public final JavaParser.package_name_return package_name()
			throws RecognitionException {
		JavaParser.package_name_return retval = new JavaParser.package_name_return();
		retval.start = input.LT(1);
		int package_name_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 39)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:230:2:
			// ( ( ID DOT )* ID )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:230:4:
			// ( ID DOT )* ID
			{
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:230:4:
				// ( ID DOT )*
				loop59: do {
					int alt59 = 2;
					int LA59_0 = input.LA(1);

					if ((LA59_0 == ID)) {
						int LA59_1 = input.LA(2);

						if ((LA59_1 == DOT)) {
							alt59 = 1;
						}

					}

					switch (alt59) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:230:5:
						// ID DOT
					{
						match(input, ID, FOLLOW_ID_in_package_name1222);
						if (state.failed)
							return retval;
						match(input, DOT, FOLLOW_DOT_in_package_name1224);
						if (state.failed)
							return retval;

					}
						break;

					default:
						break loop59;
					}
				} while (true);

				match(input, ID, FOLLOW_ID_in_package_name1228);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperant(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 39, package_name_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "package_name"

	public static class import_name_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "import_name"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:233:1:
	// import_name : ( ID DOT )+ ( ID | STAR ) ;
	public final JavaParser.import_name_return import_name()
			throws RecognitionException {
		JavaParser.import_name_return retval = new JavaParser.import_name_return();
		retval.start = input.LT(1);
		int import_name_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 40)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:234:2:
			// ( ( ID DOT )+ ( ID | STAR ) )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:234:4:
			// ( ID DOT )+ ( ID | STAR )
			{
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:234:4:
				// ( ID DOT )+
				int cnt60 = 0;
				loop60: do {
					int alt60 = 2;
					int LA60_0 = input.LA(1);

					if ((LA60_0 == ID)) {
						int LA60_1 = input.LA(2);

						if ((LA60_1 == DOT)) {
							alt60 = 1;
						}

					}

					switch (alt60) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:234:5:
						// ID DOT
					{
						match(input, ID, FOLLOW_ID_in_import_name1242);
						if (state.failed)
							return retval;
						match(input, DOT, FOLLOW_DOT_in_import_name1244);
						if (state.failed)
							return retval;

					}
						break;

					default:
						if (cnt60 >= 1)
							break loop60;
						if (state.backtracking > 0) {
							state.failed = true;
							return retval;
						}
						EarlyExitException eee = new EarlyExitException(60,
								input);
						throw eee;
					}
					cnt60++;
				} while (true);

				if (input.LA(1) == STAR || input.LA(1) == ID) {
					input.consume();
					state.errorRecovery = false;
					state.failed = false;
				} else {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					MismatchedSetException mse = new MismatchedSetException(
							null, input);
					throw mse;
				}

				if (state.backtracking == 0) {
					helper.registerOperant(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 40, import_name_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "import_name"

	public static class class_name_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "class_name"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:237:1:
	// class_name : ( ID DOT )* ID ;
	public final JavaParser.class_name_return class_name()
			throws RecognitionException {
		JavaParser.class_name_return retval = new JavaParser.class_name_return();
		retval.start = input.LT(1);
		int class_name_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 41)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:238:2:
			// ( ( ID DOT )* ID )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:238:4:
			// ( ID DOT )* ID
			{
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:238:4:
				// ( ID DOT )*
				loop61: do {
					int alt61 = 2;
					int LA61_0 = input.LA(1);

					if ((LA61_0 == ID)) {
						int LA61_1 = input.LA(2);

						if ((LA61_1 == DOT)) {
							int LA61_2 = input.LA(3);

							if ((LA61_2 == ID)) {
								alt61 = 1;
							}

						}

					}

					switch (alt61) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:238:5:
						// ID DOT
					{
						match(input, ID, FOLLOW_ID_in_class_name1266);
						if (state.failed)
							return retval;
						match(input, DOT, FOLLOW_DOT_in_class_name1268);
						if (state.failed)
							return retval;

					}
						break;

					default:
						break loop61;
					}
				} while (true);

				match(input, ID, FOLLOW_ID_in_class_name1272);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperant(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 41, class_name_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "class_name"

	public static class method_name_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "method_name"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:241:1:
	// method_name : ( ID DOT )* ID ;
	public final JavaParser.method_name_return method_name()
			throws RecognitionException {
		JavaParser.method_name_return retval = new JavaParser.method_name_return();
		retval.start = input.LT(1);
		int method_name_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 42)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:242:2:
			// ( ( ID DOT )* ID )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:242:4:
			// ( ID DOT )* ID
			{
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:242:4:
				// ( ID DOT )*
				loop62: do {
					int alt62 = 2;
					int LA62_0 = input.LA(1);

					if ((LA62_0 == ID)) {
						int LA62_1 = input.LA(2);

						if ((LA62_1 == DOT)) {
							alt62 = 1;
						}

					}

					switch (alt62) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:242:5:
						// ID DOT
					{
						match(input, ID, FOLLOW_ID_in_method_name1286);
						if (state.failed)
							return retval;
						match(input, DOT, FOLLOW_DOT_in_method_name1288);
						if (state.failed)
							return retval;

					}
						break;

					default:
						break loop62;
					}
				} while (true);

				match(input, ID, FOLLOW_ID_in_method_name1292);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperant(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 42, method_name_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "method_name"

	// $ANTLR start "variable_name"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:245:1:
	// variable_name : name ( array )? ;
	public final void variable_name() throws RecognitionException {
		int variable_name_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 43)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:246:2:
			// ( name ( array )? )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:246:4:
			// name ( array )?
			{
				pushFollow(FOLLOW_name_in_variable_name1305);
				name();

				state._fsp--;
				if (state.failed)
					return;
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:246:9:
				// ( array )?
				int alt63 = 2;
				int LA63_0 = input.LA(1);

				if ((LA63_0 == OPEN_RECT_BRACKET)) {
					alt63 = 1;
				}
				switch (alt63) {
				case 1:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
					// array
				{
					pushFollow(FOLLOW_array_in_variable_name1307);
					array();

					state._fsp--;
					if (state.failed)
						return;

				}
					break;

				}

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 43, variable_name_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "variable_name"

	public static class annotation_name_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "annotation_name"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:248:1:
	// annotation_name : AT name ;
	public final JavaParser.annotation_name_return annotation_name()
			throws RecognitionException {
		JavaParser.annotation_name_return retval = new JavaParser.annotation_name_return();
		retval.start = input.LT(1);
		int annotation_name_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 44)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:249:2:
			// ( AT name )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:249:4:
			// AT name
			{
				match(input, AT, FOLLOW_AT_in_annotation_name1318);
				if (state.failed)
					return retval;
				pushFollow(FOLLOW_name_in_annotation_name1320);
				name();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperant(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 44, annotation_name_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "annotation_name"

	public static class name_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "name"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:250:1:
	// name : ( ID DOT )* ID ;
	public final JavaParser.name_return name() throws RecognitionException {
		JavaParser.name_return retval = new JavaParser.name_return();
		retval.start = input.LT(1);
		int name_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 45)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:250:6:
			// ( ( ID DOT )* ID )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:250:8:
			// ( ID DOT )* ID
			{
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:250:8:
				// ( ID DOT )*
				loop64: do {
					int alt64 = 2;
					int LA64_0 = input.LA(1);

					if ((LA64_0 == ID)) {
						int LA64_1 = input.LA(2);

						if ((LA64_1 == DOT)) {
							alt64 = 1;
						}

					}

					switch (alt64) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:250:9:
						// ID DOT
					{
						match(input, ID, FOLLOW_ID_in_name1330);
						if (state.failed)
							return retval;
						match(input, DOT, FOLLOW_DOT_in_name1332);
						if (state.failed)
							return retval;

					}
						break;

					default:
						break loop64;
					}
				} while (true);

				match(input, ID, FOLLOW_ID_in_name1336);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperant(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 45, name_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "name"

	// $ANTLR start "array"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:252:1:
	// array : open_rect_bracket ( value )? close_rect_bracket ;
	public final void array() throws RecognitionException {
		int array_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 46)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:252:7:
			// ( open_rect_bracket ( value )? close_rect_bracket )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:252:9:
			// open_rect_bracket ( value )? close_rect_bracket
			{
				pushFollow(FOLLOW_open_rect_bracket_in_array1346);
				open_rect_bracket();

				state._fsp--;
				if (state.failed)
					return;
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:252:27:
				// ( value )?
				int alt65 = 2;
				int LA65_0 = input.LA(1);

				if ((LA65_0 == NEW || LA65_0 == NULL
						|| (LA65_0 >= INC && LA65_0 <= DEC)
						|| LA65_0 == OPEN_BRACKET
						|| (LA65_0 >= BOOL_CONST && LA65_0 <= INT_CONST)
						|| LA65_0 == FLOAT_CONST || (LA65_0 >= STRING_CONST && LA65_0 <= CHAR_CONST))) {
					alt65 = 1;
				}
				switch (alt65) {
				case 1:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
					// value
				{
					pushFollow(FOLLOW_value_in_array1348);
					value();

					state._fsp--;
					if (state.failed)
						return;

				}
					break;

				}

				pushFollow(FOLLOW_close_rect_bracket_in_array1351);
				close_rect_bracket();

				state._fsp--;
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 46, array_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "array"

	public static class package__return extends ParserRuleReturnScope {
	};

	// $ANTLR start "package_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:258:1:
	// package_ : PACKAGE ;
	public final JavaParser.package__return package_()
			throws RecognitionException {
		JavaParser.package__return retval = new JavaParser.package__return();
		retval.start = input.LT(1);
		int package__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 47)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:259:2:
			// ( PACKAGE )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:259:4:
			// PACKAGE
			{
				match(input, PACKAGE, FOLLOW_PACKAGE_in_package_1364);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 47, package__StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "package_"

	public static class import__return extends ParserRuleReturnScope {
	};

	// $ANTLR start "import_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:260:1:
	// import_ : IMPORT ;
	public final JavaParser.import__return import_()
			throws RecognitionException {
		JavaParser.import__return retval = new JavaParser.import__return();
		retval.start = input.LT(1);
		int import__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 48)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:261:2:
			// ( IMPORT )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:261:4:
			// IMPORT
			{
				match(input, IMPORT, FOLLOW_IMPORT_in_import_1375);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 48, import__StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "import_"

	public static class class__return extends ParserRuleReturnScope {
	};

	// $ANTLR start "class_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:262:1:
	// class_ : CLASS ;
	public final JavaParser.class__return class_() throws RecognitionException {
		JavaParser.class__return retval = new JavaParser.class__return();
		retval.start = input.LT(1);
		int class__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 49)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:262:8:
			// ( CLASS )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:262:10:
			// CLASS
			{
				match(input, CLASS, FOLLOW_CLASS_in_class_1385);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 49, class__StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "class_"

	public static class extends__return extends ParserRuleReturnScope {
	};

	// $ANTLR start "extends_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:263:1:
	// extends_ : EXTENDS ;
	public final JavaParser.extends__return extends_()
			throws RecognitionException {
		JavaParser.extends__return retval = new JavaParser.extends__return();
		retval.start = input.LT(1);
		int extends__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 50)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:263:9:
			// ( EXTENDS )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:263:11:
			// EXTENDS
			{
				match(input, EXTENDS, FOLLOW_EXTENDS_in_extends_1393);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 50, extends__StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "extends_"

	public static class implements__return extends ParserRuleReturnScope {
	};

	// $ANTLR start "implements_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:264:1:
	// implements_ : IMPLEMENTS ;
	public final JavaParser.implements__return implements_()
			throws RecognitionException {
		JavaParser.implements__return retval = new JavaParser.implements__return();
		retval.start = input.LT(1);
		int implements__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 51)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:265:2:
			// ( IMPLEMENTS )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:265:4:
			// IMPLEMENTS
			{
				match(input, IMPLEMENTS, FOLLOW_IMPLEMENTS_in_implements_1403);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 51, implements__StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "implements_"

	public static class this__return extends ParserRuleReturnScope {
	};

	// $ANTLR start "this_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:266:1:
	// this_ : THIS ;
	public final JavaParser.this__return this_() throws RecognitionException {
		JavaParser.this__return retval = new JavaParser.this__return();
		retval.start = input.LT(1);
		int this__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 52)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:266:7:
			// ( THIS )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:266:9:
			// THIS
			{
				match(input, THIS, FOLLOW_THIS_in_this_1412);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperant(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 52, this__StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "this_"

	public static class super__return extends ParserRuleReturnScope {
	};

	// $ANTLR start "super_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:267:1:
	// super_ : SUPER ;
	public final JavaParser.super__return super_() throws RecognitionException {
		JavaParser.super__return retval = new JavaParser.super__return();
		retval.start = input.LT(1);
		int super__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 53)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:267:8:
			// ( SUPER )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:267:10:
			// SUPER
			{
				match(input, SUPER, FOLLOW_SUPER_in_super_1421);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperant(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 53, super__StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "super_"

	public static class void__return extends ParserRuleReturnScope {
	};

	// $ANTLR start "void_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:268:1:
	// void_ : VOID ;
	public final JavaParser.void__return void_() throws RecognitionException {
		JavaParser.void__return retval = new JavaParser.void__return();
		retval.start = input.LT(1);
		int void__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 54)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:268:7:
			// ( VOID )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:268:9:
			// VOID
			{
				match(input, VOID, FOLLOW_VOID_in_void_1430);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperant(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 54, void__StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "void_"

	public static class public__return extends ParserRuleReturnScope {
	};

	// $ANTLR start "public_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:269:1:
	// public_ : PUBLIC ;
	public final JavaParser.public__return public_()
			throws RecognitionException {
		JavaParser.public__return retval = new JavaParser.public__return();
		retval.start = input.LT(1);
		int public__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 55)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:269:9:
			// ( PUBLIC )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:269:11:
			// PUBLIC
			{
				match(input, PUBLIC, FOLLOW_PUBLIC_in_public_1439);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 55, public__StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "public_"

	public static class private__return extends ParserRuleReturnScope {
	};

	// $ANTLR start "private_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:270:1:
	// private_ : PRIVATE ;
	public final JavaParser.private__return private_()
			throws RecognitionException {
		JavaParser.private__return retval = new JavaParser.private__return();
		retval.start = input.LT(1);
		int private__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 56)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:270:9:
			// ( PRIVATE )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:270:11:
			// PRIVATE
			{
				match(input, PRIVATE, FOLLOW_PRIVATE_in_private_1447);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 56, private__StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "private_"

	public static class protected__return extends ParserRuleReturnScope {
	};

	// $ANTLR start "protected_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:271:1:
	// protected_ : PROTECTED ;
	public final JavaParser.protected__return protected_()
			throws RecognitionException {
		JavaParser.protected__return retval = new JavaParser.protected__return();
		retval.start = input.LT(1);
		int protected__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 57)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:272:2:
			// ( PROTECTED )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:272:4:
			// PROTECTED
			{
				match(input, PROTECTED, FOLLOW_PROTECTED_in_protected_1457);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 57, protected__StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "protected_"

	public static class static__return extends ParserRuleReturnScope {
	};

	// $ANTLR start "static_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:273:1:
	// static_ : STATIC ;
	public final JavaParser.static__return static_()
			throws RecognitionException {
		JavaParser.static__return retval = new JavaParser.static__return();
		retval.start = input.LT(1);
		int static__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 58)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:273:9:
			// ( STATIC )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:273:11:
			// STATIC
			{
				match(input, STATIC, FOLLOW_STATIC_in_static_1466);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 58, static__StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "static_"

	public static class final__return extends ParserRuleReturnScope {
	};

	// $ANTLR start "final_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:274:1:
	// final_ : FINAL ;
	public final JavaParser.final__return final_() throws RecognitionException {
		JavaParser.final__return retval = new JavaParser.final__return();
		retval.start = input.LT(1);
		int final__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 59)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:274:8:
			// ( FINAL )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:274:10:
			// FINAL
			{
				match(input, FINAL, FOLLOW_FINAL_in_final_1475);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 59, final__StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "final_"

	public static class transient__return extends ParserRuleReturnScope {
	};

	// $ANTLR start "transient_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:275:1:
	// transient_ : TRANSIENT ;
	public final JavaParser.transient__return transient_()
			throws RecognitionException {
		JavaParser.transient__return retval = new JavaParser.transient__return();
		retval.start = input.LT(1);
		int transient__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 60)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:276:2:
			// ( TRANSIENT )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:276:4:
			// TRANSIENT
			{
				match(input, TRANSIENT, FOLLOW_TRANSIENT_in_transient_1485);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 60, transient__StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "transient_"

	public static class new__return extends ParserRuleReturnScope {
	};

	// $ANTLR start "new_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:277:1:
	// new_ : NEW ;
	public final JavaParser.new__return new_() throws RecognitionException {
		JavaParser.new__return retval = new JavaParser.new__return();
		retval.start = input.LT(1);
		int new__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 61)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:277:6:
			// ( NEW )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:277:8:
			// NEW
			{
				match(input, NEW, FOLLOW_NEW_in_new_1494);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 61, new__StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "new_"

	public static class try__return extends ParserRuleReturnScope {
	};

	// $ANTLR start "try_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:278:1:
	// try_ : TRY ;
	public final JavaParser.try__return try_() throws RecognitionException {
		JavaParser.try__return retval = new JavaParser.try__return();
		retval.start = input.LT(1);
		int try__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 62)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:278:6:
			// ( TRY )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:278:8:
			// TRY
			{
				match(input, TRY, FOLLOW_TRY_in_try_1503);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 62, try__StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "try_"

	public static class catch__return extends ParserRuleReturnScope {
	};

	// $ANTLR start "catch_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:279:1:
	// catch_ : CATCH ;
	public final JavaParser.catch__return catch_() throws RecognitionException {
		JavaParser.catch__return retval = new JavaParser.catch__return();
		retval.start = input.LT(1);
		int catch__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 63)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:279:8:
			// ( CATCH )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:279:10:
			// CATCH
			{
				match(input, CATCH, FOLLOW_CATCH_in_catch_1512);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)), 1);
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 63, catch__StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "catch_"

	public static class finally__return extends ParserRuleReturnScope {
	};

	// $ANTLR start "finally_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:280:1:
	// finally_ : FINALLY ;
	public final JavaParser.finally__return finally_()
			throws RecognitionException {
		JavaParser.finally__return retval = new JavaParser.finally__return();
		retval.start = input.LT(1);
		int finally__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 64)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:280:9:
			// ( FINALLY )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:280:11:
			// FINALLY
			{
				match(input, FINALLY, FOLLOW_FINALLY_in_finally_1520);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)), 1);
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 64, finally__StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "finally_"

	// $ANTLR start "for_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:282:1:
	// for_ : FOR ;
	public final void for_() throws RecognitionException {
		int for__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 65)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:282:6:
			// ( FOR )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:282:8:
			// FOR
			{
				match(input, FOR, FOLLOW_FOR_in_for_1530);
				if (state.failed)
					return;
				if (state.backtracking == 0) {
					helper.registerOperator("for()", 1);
				}

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 65, for__StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "for_"

	// $ANTLR start "while_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:283:1:
	// while_ : WHILE ;
	public final void while_() throws RecognitionException {
		int while__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 66)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:283:8:
			// ( WHILE )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:283:10:
			// WHILE
			{
				match(input, WHILE, FOLLOW_WHILE_in_while_1539);
				if (state.failed)
					return;
				if (state.backtracking == 0) {
					helper.registerOperator("while()", 1);
				}

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 66, while__StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "while_"

	// $ANTLR start "do_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:284:1:
	// do_ : DO ;
	public final void do_() throws RecognitionException {
		int do__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 67)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:284:5:
			// ( DO )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:284:7:
			// DO
			{
				match(input, DO, FOLLOW_DO_in_do_1548);
				if (state.failed)
					return;
				if (state.backtracking == 0) {
					helper.registerOperator("do()", 1);
				}

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 67, do__StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "do_"

	// $ANTLR start "if_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:285:1:
	// if_ : IF ;
	public final void if_() throws RecognitionException {
		int if__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 68)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:285:5:
			// ( IF )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:285:7:
			// IF
			{
				match(input, IF, FOLLOW_IF_in_if_1557);
				if (state.failed)
					return;
				if (state.backtracking == 0) {
					helper.registerOperator("if()", 1);
				}

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 68, if__StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "if_"

	// $ANTLR start "else_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:286:1:
	// else_ : ELSE ;
	public final void else_() throws RecognitionException {
		int else__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 69)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:286:7:
			// ( ELSE )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:286:9:
			// ELSE
			{
				match(input, ELSE, FOLLOW_ELSE_in_else_1566);
				if (state.failed)
					return;
				if (state.backtracking == 0) {
					helper.registerOperator("else");
				}

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 69, else__StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "else_"

	public static class return__return extends ParserRuleReturnScope {
	};

	// $ANTLR start "return_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:288:1:
	// return_ : RETURN ;
	public final JavaParser.return__return return_()
			throws RecognitionException {
		JavaParser.return__return retval = new JavaParser.return__return();
		retval.start = input.LT(1);
		int return__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 70)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:288:9:
			// ( RETURN )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:288:11:
			// RETURN
			{
				match(input, RETURN, FOLLOW_RETURN_in_return_1576);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 70, return__StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "return_"

	public static class break__return extends ParserRuleReturnScope {
	};

	// $ANTLR start "break_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:289:1:
	// break_ : BREAK ;
	public final JavaParser.break__return break_() throws RecognitionException {
		JavaParser.break__return retval = new JavaParser.break__return();
		retval.start = input.LT(1);
		int break__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 71)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:289:8:
			// ( BREAK )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:289:10:
			// BREAK
			{
				match(input, BREAK, FOLLOW_BREAK_in_break_1585);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 71, break__StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "break_"

	public static class continue__return extends ParserRuleReturnScope {
	};

	// $ANTLR start "continue_"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:290:1:
	// continue_ : CONTINUE ;
	public final JavaParser.continue__return continue_()
			throws RecognitionException {
		JavaParser.continue__return retval = new JavaParser.continue__return();
		retval.start = input.LT(1);
		int continue__StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 72)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:291:2:
			// ( CONTINUE )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:291:4:
			// CONTINUE
			{
				match(input, CONTINUE, FOLLOW_CONTINUE_in_continue_1595);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 72, continue__StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "continue_"

	public static class semicolon_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "semicolon"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:297:1:
	// semicolon : SEMICOLON ;
	public final JavaParser.semicolon_return semicolon()
			throws RecognitionException {
		JavaParser.semicolon_return retval = new JavaParser.semicolon_return();
		retval.start = input.LT(1);
		int semicolon_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 73)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:298:2:
			// ( SEMICOLON )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:298:4:
			// SEMICOLON
			{
				match(input, SEMICOLON, FOLLOW_SEMICOLON_in_semicolon1610);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 73, semicolon_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "semicolon"

	public static class comma_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "comma"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:299:1:
	// comma : COMMA ;
	public final JavaParser.comma_return comma() throws RecognitionException {
		JavaParser.comma_return retval = new JavaParser.comma_return();
		retval.start = input.LT(1);
		int comma_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 74)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:299:7:
			// ( COMMA )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:299:9:
			// COMMA
			{
				match(input, COMMA, FOLLOW_COMMA_in_comma1619);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 74, comma_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "comma"

	public static class colon_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "colon"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:300:1:
	// colon : COLON ;
	public final JavaParser.colon_return colon() throws RecognitionException {
		JavaParser.colon_return retval = new JavaParser.colon_return();
		retval.start = input.LT(1);
		int colon_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 75)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:300:7:
			// ( COLON )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:300:9:
			// COLON
			{
				match(input, COLON, FOLLOW_COLON_in_colon1628);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 75, colon_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "colon"

	public static class dot_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "dot"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:301:1:
	// dot : DOT ;
	public final JavaParser.dot_return dot() throws RecognitionException {
		JavaParser.dot_return retval = new JavaParser.dot_return();
		retval.start = input.LT(1);
		int dot_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 76)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:301:5:
			// ( DOT )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:301:7:
			// DOT
			{
				match(input, DOT, FOLLOW_DOT_in_dot1637);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 76, dot_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "dot"

	public static class assign_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "assign"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:302:1:
	// assign : ( ASSIGN | ASSIGN | INCASSIGN | DECASSIGN );
	public final JavaParser.assign_return assign() throws RecognitionException {
		JavaParser.assign_return retval = new JavaParser.assign_return();
		retval.start = input.LT(1);
		int assign_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 77)) {
				return retval;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:302:8:
			// ( ASSIGN | ASSIGN | INCASSIGN | DECASSIGN )
			int alt66 = 4;
			switch (input.LA(1)) {
			case ASSIGN: {
				if ((synpred118_JavaParser())) {
					alt66 = 1;
				} else if ((synpred119_JavaParser())) {
					alt66 = 2;
				} else {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					NoViableAltException nvae = new NoViableAltException("",
							66, 1, input);

					throw nvae;
				}
			}
				break;
			case INCASSIGN: {
				alt66 = 3;
			}
				break;
			case DECASSIGN: {
				alt66 = 4;
			}
				break;
			default:
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 66, 0,
						input);

				throw nvae;
			}

			switch (alt66) {
			case 1:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:302:10:
				// ASSIGN
			{
				match(input, ASSIGN, FOLLOW_ASSIGN_in_assign1646);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}
				break;
			case 2:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:303:4:
				// ASSIGN
			{
				match(input, ASSIGN, FOLLOW_ASSIGN_in_assign1653);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}
				break;
			case 3:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:304:4:
				// INCASSIGN
			{
				match(input, INCASSIGN, FOLLOW_INCASSIGN_in_assign1660);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}
				break;
			case 4:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:305:4:
				// DECASSIGN
			{
				match(input, DECASSIGN, FOLLOW_DECASSIGN_in_assign1667);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					helper.registerOperator(input.toString(retval.start, input
							.LT(-1)));
				}

			}
				break;

			}
			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 77, assign_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "assign"

	// $ANTLR start "block_begin"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:307:1:
	// block_begin : OPEN_CURLY_BRACKET ;
	public final void block_begin() throws RecognitionException {
		int block_begin_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 78)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:308:2:
			// ( OPEN_CURLY_BRACKET )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:308:4:
			// OPEN_CURLY_BRACKET
			{
				match(input, OPEN_CURLY_BRACKET,
						FOLLOW_OPEN_CURLY_BRACKET_in_block_begin1679);
				if (state.failed)
					return;
				if (state.backtracking == 0) {
					helper.registerOperator("{}");
				}

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 78, block_begin_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "block_begin"

	// $ANTLR start "block_end"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:309:1:
	// block_end : CLOSE_CURLY_BRACKET ;
	public final void block_end() throws RecognitionException {
		int block_end_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 79)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:310:2:
			// ( CLOSE_CURLY_BRACKET )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:310:4:
			// CLOSE_CURLY_BRACKET
			{
				match(input, CLOSE_CURLY_BRACKET,
						FOLLOW_CLOSE_CURLY_BRACKET_in_block_end1689);
				if (state.failed)
					return;
				if (state.backtracking == 0) {
					helper.registerOperator("");
				}

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 79, block_end_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "block_end"

	// $ANTLR start "open_bracket"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:311:1:
	// open_bracket : OPEN_BRACKET ;
	public final void open_bracket() throws RecognitionException {
		int open_bracket_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 80)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:312:2:
			// ( OPEN_BRACKET )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:312:4:
			// OPEN_BRACKET
			{
				match(input, OPEN_BRACKET,
						FOLLOW_OPEN_BRACKET_in_open_bracket1699);
				if (state.failed)
					return;
				if (state.backtracking == 0) {
					helper.registerOperator("()");
				}

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 80, open_bracket_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "open_bracket"

	// $ANTLR start "close_bracket"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:313:1:
	// close_bracket : CLOSE_BRACKET ;
	public final void close_bracket() throws RecognitionException {
		int close_bracket_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 81)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:314:2:
			// ( CLOSE_BRACKET )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:314:4:
			// CLOSE_BRACKET
			{
				match(input, CLOSE_BRACKET,
						FOLLOW_CLOSE_BRACKET_in_close_bracket1709);
				if (state.failed)
					return;
				if (state.backtracking == 0) {
					helper.registerOperator("");
				}

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 81, close_bracket_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "close_bracket"

	// $ANTLR start "open_rect_bracket"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:315:1:
	// open_rect_bracket : OPEN_RECT_BRACKET ;
	public final void open_rect_bracket() throws RecognitionException {
		int open_rect_bracket_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 82)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:316:2:
			// ( OPEN_RECT_BRACKET )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:316:4:
			// OPEN_RECT_BRACKET
			{
				match(input, OPEN_RECT_BRACKET,
						FOLLOW_OPEN_RECT_BRACKET_in_open_rect_bracket1720);
				if (state.failed)
					return;
				if (state.backtracking == 0) {
					helper.registerOperator("[]");
				}

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 82, open_rect_bracket_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "open_rect_bracket"

	// $ANTLR start "close_rect_bracket"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:317:1:
	// close_rect_bracket : CLOSE_RECT_BRACKET ;
	public final void close_rect_bracket() throws RecognitionException {
		int close_rect_bracket_StartIndex = input.index();
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 83)) {
				return;
			}
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:318:2:
			// ( CLOSE_RECT_BRACKET )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:318:4:
			// CLOSE_RECT_BRACKET
			{
				match(input, CLOSE_RECT_BRACKET,
						FOLLOW_CLOSE_RECT_BRACKET_in_close_rect_bracket1730);
				if (state.failed)
					return;
				if (state.backtracking == 0) {
					helper.registerOperator("");
				}

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
			if (state.backtracking > 0) {
				memoize(input, 83, close_rect_bracket_StartIndex);
			}
		}
		return;
	}

	// $ANTLR end "close_rect_bracket"

	// $ANTLR start synpred24_JavaParser
	public final void synpred24_JavaParser_fragment()
			throws RecognitionException {
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:70:17:
		// ( class_def )
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:70:17:
		// class_def
		{
			pushFollow(FOLLOW_class_def_in_synpred24_JavaParser315);
			class_def();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred24_JavaParser

	// $ANTLR start synpred25_JavaParser
	public final void synpred25_JavaParser_fragment()
			throws RecognitionException {
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:70:29:
		// ( constructor_def )
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:70:29:
		// constructor_def
		{
			pushFollow(FOLLOW_constructor_def_in_synpred25_JavaParser319);
			constructor_def();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred25_JavaParser

	// $ANTLR start synpred26_JavaParser
	public final void synpred26_JavaParser_fragment()
			throws RecognitionException {
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:70:47:
		// ( method_def )
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:70:47:
		// method_def
		{
			pushFollow(FOLLOW_method_def_in_synpred26_JavaParser323);
			method_def();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred26_JavaParser

	// $ANTLR start synpred27_JavaParser
	public final void synpred27_JavaParser_fragment()
			throws RecognitionException {
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:70:60:
		// ( field_def )
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:70:60:
		// field_def
		{
			pushFollow(FOLLOW_field_def_in_synpred27_JavaParser327);
			field_def();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred27_JavaParser

	// $ANTLR start synpred37_JavaParser
	public final void synpred37_JavaParser_fragment()
			throws RecognitionException {
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:87:6:
		// ( binary_operator value )
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:87:6:
		// binary_operator value
		{
			pushFollow(FOLLOW_binary_operator_in_synpred37_JavaParser439);
			binary_operator();

			state._fsp--;
			if (state.failed)
				return;
			pushFollow(FOLLOW_value_in_synpred37_JavaParser441);
			value();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred37_JavaParser

	// $ANTLR start synpred56_JavaParser
	public final void synpred56_JavaParser_fragment()
			throws RecognitionException {
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:119:4:
		// ( variable_assignment semicolon )
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:119:4:
		// variable_assignment semicolon
		{
			pushFollow(FOLLOW_variable_assignment_in_synpred56_JavaParser611);
			variable_assignment();

			state._fsp--;
			if (state.failed)
				return;
			pushFollow(FOLLOW_semicolon_in_synpred56_JavaParser613);
			semicolon();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred56_JavaParser

	// $ANTLR start synpred57_JavaParser
	public final void synpred57_JavaParser_fragment()
			throws RecognitionException {
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:120:4:
		// ( variable_def semicolon )
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:120:4:
		// variable_def semicolon
		{
			pushFollow(FOLLOW_variable_def_in_synpred57_JavaParser618);
			variable_def();

			state._fsp--;
			if (state.failed)
				return;
			pushFollow(FOLLOW_semicolon_in_synpred57_JavaParser620);
			semicolon();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred57_JavaParser

	// $ANTLR start synpred58_JavaParser
	public final void synpred58_JavaParser_fragment()
			throws RecognitionException {
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:121:4:
		// ( method_call semicolon )
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:121:4:
		// method_call semicolon
		{
			pushFollow(FOLLOW_method_call_in_synpred58_JavaParser625);
			method_call();

			state._fsp--;
			if (state.failed)
				return;
			pushFollow(FOLLOW_semicolon_in_synpred58_JavaParser627);
			semicolon();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred58_JavaParser

	// $ANTLR start synpred62_JavaParser
	public final void synpred62_JavaParser_fragment()
			throws RecognitionException {
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:124:25:
		// ( unary )
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:124:25:
		// unary
		{
			pushFollow(FOLLOW_unary_in_synpred62_JavaParser648);
			unary();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred62_JavaParser

	// $ANTLR start synpred63_JavaParser
	public final void synpred63_JavaParser_fragment()
			throws RecognitionException {
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:124:4:
		// ( ( unary )? variable_name ( unary )? )
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:124:4:
		// ( unary )? variable_name ( unary )?
		{
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:124:4:
			// ( unary )?
			int alt79 = 2;
			int LA79_0 = input.LA(1);

			if (((LA79_0 >= INC && LA79_0 <= DEC))) {
				alt79 = 1;
			}
			switch (alt79) {
			case 1:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
				// unary
			{
				pushFollow(FOLLOW_unary_in_synpred63_JavaParser643);
				unary();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;

			}

			pushFollow(FOLLOW_variable_name_in_synpred63_JavaParser646);
			variable_name();

			state._fsp--;
			if (state.failed)
				return;
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:124:25:
			// ( unary )?
			int alt80 = 2;
			int LA80_0 = input.LA(1);

			if (((LA80_0 >= INC && LA80_0 <= DEC))) {
				alt80 = 1;
			}
			switch (alt80) {
			case 1:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
				// unary
			{
				pushFollow(FOLLOW_unary_in_synpred63_JavaParser648);
				unary();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;

			}

		}
	}

	// $ANTLR end synpred63_JavaParser

	// $ANTLR start synpred68_JavaParser
	public final void synpred68_JavaParser_fragment()
			throws RecognitionException {
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:133:4:
		// ( variable_assignment )
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:133:4:
		// variable_assignment
		{
			pushFollow(FOLLOW_variable_assignment_in_synpred68_JavaParser685);
			variable_assignment();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred68_JavaParser

	// $ANTLR start synpred69_JavaParser
	public final void synpred69_JavaParser_fragment()
			throws RecognitionException {
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:134:4:
		// ( variable_def )
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:134:4:
		// variable_def
		{
			pushFollow(FOLLOW_variable_def_in_synpred69_JavaParser690);
			variable_def();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred69_JavaParser

	// $ANTLR start synpred70_JavaParser
	public final void synpred70_JavaParser_fragment()
			throws RecognitionException {
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:135:4:
		// ( method_call )
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:135:4:
		// method_call
		{
			pushFollow(FOLLOW_method_call_in_synpred70_JavaParser695);
			method_call();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred70_JavaParser

	// $ANTLR start synpred74_JavaParser
	public final void synpred74_JavaParser_fragment()
			throws RecognitionException {
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:137:4:
		// ( ( unary )? variable_name ( unary )? )
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:137:4:
		// ( unary )? variable_name ( unary )?
		{
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:137:4:
			// ( unary )?
			int alt81 = 2;
			int LA81_0 = input.LA(1);

			if (((LA81_0 >= INC && LA81_0 <= DEC))) {
				alt81 = 1;
			}
			switch (alt81) {
			case 1:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
				// unary
			{
				pushFollow(FOLLOW_unary_in_synpred74_JavaParser705);
				unary();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;

			}

			pushFollow(FOLLOW_variable_name_in_synpred74_JavaParser708);
			variable_name();

			state._fsp--;
			if (state.failed)
				return;
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:137:25:
			// ( unary )?
			int alt82 = 2;
			int LA82_0 = input.LA(1);

			if (((LA82_0 >= INC && LA82_0 <= DEC))) {
				alt82 = 1;
			}
			switch (alt82) {
			case 1:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
				// unary
			{
				pushFollow(FOLLOW_unary_in_synpred74_JavaParser710);
				unary();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;

			}

		}
	}

	// $ANTLR end synpred74_JavaParser

	// $ANTLR start synpred86_JavaParser
	public final void synpred86_JavaParser_fragment()
			throws RecognitionException {
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:11:
		// ( for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )?
		// semicolon ( value )? semicolon ( statement_wosemicolon ( comma
		// statement_wosemicolon )* )? CLOSE_BRACKET code )
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:11:
		// for_ OPEN_BRACKET ( variable_def ( comma variable_def )* )? semicolon
		// ( value )? semicolon ( statement_wosemicolon ( comma
		// statement_wosemicolon )* )? CLOSE_BRACKET code
		{
			pushFollow(FOLLOW_for__in_synpred86_JavaParser791);
			for_();

			state._fsp--;
			if (state.failed)
				return;
			match(input, OPEN_BRACKET,
					FOLLOW_OPEN_BRACKET_in_synpred86_JavaParser793);
			if (state.failed)
				return;
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:29:
			// ( variable_def ( comma variable_def )* )?
			int alt86 = 2;
			int LA86_0 = input.LA(1);

			if ((LA86_0 == VOID || (LA86_0 >= BOOLEAN && LA86_0 <= DOUBLE)
					|| LA86_0 == AT || LA86_0 == ID)) {
				alt86 = 1;
			}
			switch (alt86) {
			case 1:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:30:
				// variable_def ( comma variable_def )*
			{
				pushFollow(FOLLOW_variable_def_in_synpred86_JavaParser796);
				variable_def();

				state._fsp--;
				if (state.failed)
					return;
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:43:
				// ( comma variable_def )*
				loop85: do {
					int alt85 = 2;
					int LA85_0 = input.LA(1);

					if ((LA85_0 == COMMA)) {
						alt85 = 1;
					}

					switch (alt85) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:44:
						// comma variable_def
					{
						pushFollow(FOLLOW_comma_in_synpred86_JavaParser799);
						comma();

						state._fsp--;
						if (state.failed)
							return;
						pushFollow(FOLLOW_variable_def_in_synpred86_JavaParser801);
						variable_def();

						state._fsp--;
						if (state.failed)
							return;

					}
						break;

					default:
						break loop85;
					}
				} while (true);

			}
				break;

			}

			pushFollow(FOLLOW_semicolon_in_synpred86_JavaParser807);
			semicolon();

			state._fsp--;
			if (state.failed)
				return;
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:77:
			// ( value )?
			int alt87 = 2;
			int LA87_0 = input.LA(1);

			if ((LA87_0 == NEW || LA87_0 == NULL
					|| (LA87_0 >= INC && LA87_0 <= DEC)
					|| LA87_0 == OPEN_BRACKET
					|| (LA87_0 >= BOOL_CONST && LA87_0 <= INT_CONST)
					|| LA87_0 == FLOAT_CONST || (LA87_0 >= STRING_CONST && LA87_0 <= CHAR_CONST))) {
				alt87 = 1;
			}
			switch (alt87) {
			case 1:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:0:0:
				// value
			{
				pushFollow(FOLLOW_value_in_synpred86_JavaParser809);
				value();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;

			}

			pushFollow(FOLLOW_semicolon_in_synpred86_JavaParser812);
			semicolon();

			state._fsp--;
			if (state.failed)
				return;
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:94:
			// ( statement_wosemicolon ( comma statement_wosemicolon )* )?
			int alt89 = 2;
			int LA89_0 = input.LA(1);

			if ((LA89_0 == VOID || LA89_0 == RETURN
					|| (LA89_0 >= THIS && LA89_0 <= IF) || LA89_0 == TRY
					|| (LA89_0 >= BOOLEAN && LA89_0 <= DOUBLE)
					|| (LA89_0 >= INC && LA89_0 <= DEC) || LA89_0 == AT || LA89_0 == ID)) {
				alt89 = 1;
			}
			switch (alt89) {
			case 1:
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:95:
				// statement_wosemicolon ( comma statement_wosemicolon )*
			{
				pushFollow(FOLLOW_statement_wosemicolon_in_synpred86_JavaParser815);
				statement_wosemicolon();

				state._fsp--;
				if (state.failed)
					return;
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:117:
				// ( comma statement_wosemicolon )*
				loop88: do {
					int alt88 = 2;
					int LA88_0 = input.LA(1);

					if ((LA88_0 == COMMA)) {
						alt88 = 1;
					}

					switch (alt88) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:153:118:
						// comma statement_wosemicolon
					{
						pushFollow(FOLLOW_comma_in_synpred86_JavaParser818);
						comma();

						state._fsp--;
						if (state.failed)
							return;
						pushFollow(FOLLOW_statement_wosemicolon_in_synpred86_JavaParser820);
						statement_wosemicolon();

						state._fsp--;
						if (state.failed)
							return;

					}
						break;

					default:
						break loop88;
					}
				} while (true);

			}
				break;

			}

			match(input, CLOSE_BRACKET,
					FOLLOW_CLOSE_BRACKET_in_synpred86_JavaParser826);
			if (state.failed)
				return;
			pushFollow(FOLLOW_code_in_synpred86_JavaParser828);
			code();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred86_JavaParser

	// $ANTLR start synpred87_JavaParser
	public final void synpred87_JavaParser_fragment()
			throws RecognitionException {
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:168:15:
		// ( catch_ OPEN_BRACKET id id CLOSE_BRACKET code )
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:168:15:
		// catch_ OPEN_BRACKET id id CLOSE_BRACKET code
		{
			pushFollow(FOLLOW_catch__in_synpred87_JavaParser927);
			catch_();

			state._fsp--;
			if (state.failed)
				return;
			match(input, OPEN_BRACKET,
					FOLLOW_OPEN_BRACKET_in_synpred87_JavaParser929);
			if (state.failed)
				return;
			pushFollow(FOLLOW_id_in_synpred87_JavaParser931);
			id();

			state._fsp--;
			if (state.failed)
				return;
			pushFollow(FOLLOW_id_in_synpred87_JavaParser933);
			id();

			state._fsp--;
			if (state.failed)
				return;
			match(input, CLOSE_BRACKET,
					FOLLOW_CLOSE_BRACKET_in_synpred87_JavaParser935);
			if (state.failed)
				return;
			pushFollow(FOLLOW_code_in_synpred87_JavaParser937);
			code();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred87_JavaParser

	// $ANTLR start synpred88_JavaParser
	public final void synpred88_JavaParser_fragment()
			throws RecognitionException {
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:168:63:
		// ( finally_ code )
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:168:63:
		// finally_ code
		{
			pushFollow(FOLLOW_finally__in_synpred88_JavaParser942);
			finally_();

			state._fsp--;
			if (state.failed)
				return;
			pushFollow(FOLLOW_code_in_synpred88_JavaParser944);
			code();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred88_JavaParser

	// $ANTLR start synpred118_JavaParser
	public final void synpred118_JavaParser_fragment()
			throws RecognitionException {
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:302:10:
		// ( ASSIGN )
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:302:10:
		// ASSIGN
		{
			match(input, ASSIGN, FOLLOW_ASSIGN_in_synpred118_JavaParser1646);
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred118_JavaParser

	// $ANTLR start synpred119_JavaParser
	public final void synpred119_JavaParser_fragment()
			throws RecognitionException {
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:303:4:
		// ( ASSIGN )
		// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/java/antlr/JavaParser.g:303:4:
		// ASSIGN
		{
			match(input, ASSIGN, FOLLOW_ASSIGN_in_synpred119_JavaParser1653);
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred119_JavaParser

	// Delegated rules

	public final boolean synpred57_JavaParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred57_JavaParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred63_JavaParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred63_JavaParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred24_JavaParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred24_JavaParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred62_JavaParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred62_JavaParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred37_JavaParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred37_JavaParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred69_JavaParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred69_JavaParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred56_JavaParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred56_JavaParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred87_JavaParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred87_JavaParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred25_JavaParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred25_JavaParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred88_JavaParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred88_JavaParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred70_JavaParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred70_JavaParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred68_JavaParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred68_JavaParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred74_JavaParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred74_JavaParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred119_JavaParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred119_JavaParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred58_JavaParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred58_JavaParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred26_JavaParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred26_JavaParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred118_JavaParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred118_JavaParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred86_JavaParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred86_JavaParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred27_JavaParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred27_JavaParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	protected DFA20 dfa20 = new DFA20(this);
	protected DFA26 dfa26 = new DFA26(this);
	protected DFA27 dfa27 = new DFA27(this);
	protected DFA30 dfa30 = new DFA30(this);
	protected DFA40 dfa40 = new DFA40(this);
	protected DFA43 dfa43 = new DFA43(this);
	static final String DFA20_eotS = "\27\uffff";
	static final String DFA20_eofS = "\27\uffff";
	static final String DFA20_minS = "\1\6\1\uffff\7\0\1\uffff\12\0\3\uffff";
	static final String DFA20_maxS = "\1\112\1\uffff\7\0\1\uffff\12\0\3\uffff";
	static final String DFA20_acceptS = "\1\uffff\1\5\7\uffff\1\1\12\uffff\1\2\1\3\1\4";
	static final String DFA20_specialS = "\2\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\7\1\10\1\11\1\12"
			+ "\1\13\1\14\1\15\1\16\1\17\1\20\3\uffff}>";
	static final String[] DFA20_transitionS = {
			"\1\11\2\uffff\1\23\21\uffff\1\3\1\4\1\5\1\7\1\6\1\10\1\13\1"
					+ "\14\1\15\1\16\1\17\1\20\1\21\1\22\1\uffff\1\1\34\uffff\1\2\2"
					+ "\uffff\1\12", "", "\1\uffff", "\1\uffff", "\1\uffff",
			"\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "", "\1\uffff",
			"\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff",
			"\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "", "", "" };

	static final short[] DFA20_eot = DFA.unpackEncodedString(DFA20_eotS);
	static final short[] DFA20_eof = DFA.unpackEncodedString(DFA20_eofS);
	static final char[] DFA20_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA20_minS);
	static final char[] DFA20_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA20_maxS);
	static final short[] DFA20_accept = DFA.unpackEncodedString(DFA20_acceptS);
	static final short[] DFA20_special = DFA
			.unpackEncodedString(DFA20_specialS);
	static final short[][] DFA20_transition;

	static {
		int numStates = DFA20_transitionS.length;
		DFA20_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
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

		public int specialStateTransition(int s, IntStream _input)
				throws NoViableAltException {
			TokenStream input = (TokenStream) _input;
			int _s = s;
			switch (s) {
			case 0:
				int index20_2 = input.index();
				input.rewind();
				s = -1;
				if ((synpred24_JavaParser())) {
					s = 9;
				}

				else if ((synpred25_JavaParser())) {
					s = 20;
				}

				else if ((synpred26_JavaParser())) {
					s = 21;
				}

				else if ((synpred27_JavaParser())) {
					s = 22;
				}

				input.seek(index20_2);
				if (s >= 0)
					return s;
				break;
			case 1:
				int index20_3 = input.index();
				input.rewind();
				s = -1;
				if ((synpred24_JavaParser())) {
					s = 9;
				}

				else if ((synpred25_JavaParser())) {
					s = 20;
				}

				else if ((synpred26_JavaParser())) {
					s = 21;
				}

				else if ((synpred27_JavaParser())) {
					s = 22;
				}

				input.seek(index20_3);
				if (s >= 0)
					return s;
				break;
			case 2:
				int index20_4 = input.index();
				input.rewind();
				s = -1;
				if ((synpred24_JavaParser())) {
					s = 9;
				}

				else if ((synpred25_JavaParser())) {
					s = 20;
				}

				else if ((synpred26_JavaParser())) {
					s = 21;
				}

				else if ((synpred27_JavaParser())) {
					s = 22;
				}

				input.seek(index20_4);
				if (s >= 0)
					return s;
				break;
			case 3:
				int index20_5 = input.index();
				input.rewind();
				s = -1;
				if ((synpred24_JavaParser())) {
					s = 9;
				}

				else if ((synpred25_JavaParser())) {
					s = 20;
				}

				else if ((synpred26_JavaParser())) {
					s = 21;
				}

				else if ((synpred27_JavaParser())) {
					s = 22;
				}

				input.seek(index20_5);
				if (s >= 0)
					return s;
				break;
			case 4:
				int index20_6 = input.index();
				input.rewind();
				s = -1;
				if ((synpred24_JavaParser())) {
					s = 9;
				}

				else if ((synpred25_JavaParser())) {
					s = 20;
				}

				else if ((synpred26_JavaParser())) {
					s = 21;
				}

				else if ((synpred27_JavaParser())) {
					s = 22;
				}

				input.seek(index20_6);
				if (s >= 0)
					return s;
				break;
			case 5:
				int index20_7 = input.index();
				input.rewind();
				s = -1;
				if ((synpred24_JavaParser())) {
					s = 9;
				}

				else if ((synpred25_JavaParser())) {
					s = 20;
				}

				else if ((synpred26_JavaParser())) {
					s = 21;
				}

				else if ((synpred27_JavaParser())) {
					s = 22;
				}

				input.seek(index20_7);
				if (s >= 0)
					return s;
				break;
			case 6:
				int index20_8 = input.index();
				input.rewind();
				s = -1;
				if ((synpred24_JavaParser())) {
					s = 9;
				}

				else if ((synpred25_JavaParser())) {
					s = 20;
				}

				else if ((synpred26_JavaParser())) {
					s = 21;
				}

				else if ((synpred27_JavaParser())) {
					s = 22;
				}

				input.seek(index20_8);
				if (s >= 0)
					return s;
				break;
			case 7:
				int index20_10 = input.index();
				input.rewind();
				s = -1;
				if ((synpred25_JavaParser())) {
					s = 20;
				}

				else if ((synpred26_JavaParser())) {
					s = 21;
				}

				else if ((synpred27_JavaParser())) {
					s = 22;
				}

				input.seek(index20_10);
				if (s >= 0)
					return s;
				break;
			case 8:
				int index20_11 = input.index();
				input.rewind();
				s = -1;
				if ((synpred26_JavaParser())) {
					s = 21;
				}

				else if ((synpred27_JavaParser())) {
					s = 22;
				}

				input.seek(index20_11);
				if (s >= 0)
					return s;
				break;
			case 9:
				int index20_12 = input.index();
				input.rewind();
				s = -1;
				if ((synpred26_JavaParser())) {
					s = 21;
				}

				else if ((synpred27_JavaParser())) {
					s = 22;
				}

				input.seek(index20_12);
				if (s >= 0)
					return s;
				break;
			case 10:
				int index20_13 = input.index();
				input.rewind();
				s = -1;
				if ((synpred26_JavaParser())) {
					s = 21;
				}

				else if ((synpred27_JavaParser())) {
					s = 22;
				}

				input.seek(index20_13);
				if (s >= 0)
					return s;
				break;
			case 11:
				int index20_14 = input.index();
				input.rewind();
				s = -1;
				if ((synpred26_JavaParser())) {
					s = 21;
				}

				else if ((synpred27_JavaParser())) {
					s = 22;
				}

				input.seek(index20_14);
				if (s >= 0)
					return s;
				break;
			case 12:
				int index20_15 = input.index();
				input.rewind();
				s = -1;
				if ((synpred26_JavaParser())) {
					s = 21;
				}

				else if ((synpred27_JavaParser())) {
					s = 22;
				}

				input.seek(index20_15);
				if (s >= 0)
					return s;
				break;
			case 13:
				int index20_16 = input.index();
				input.rewind();
				s = -1;
				if ((synpred26_JavaParser())) {
					s = 21;
				}

				else if ((synpred27_JavaParser())) {
					s = 22;
				}

				input.seek(index20_16);
				if (s >= 0)
					return s;
				break;
			case 14:
				int index20_17 = input.index();
				input.rewind();
				s = -1;
				if ((synpred26_JavaParser())) {
					s = 21;
				}

				else if ((synpred27_JavaParser())) {
					s = 22;
				}

				input.seek(index20_17);
				if (s >= 0)
					return s;
				break;
			case 15:
				int index20_18 = input.index();
				input.rewind();
				s = -1;
				if ((synpred26_JavaParser())) {
					s = 21;
				}

				else if ((synpred27_JavaParser())) {
					s = 22;
				}

				input.seek(index20_18);
				if (s >= 0)
					return s;
				break;
			case 16:
				int index20_19 = input.index();
				input.rewind();
				s = -1;
				if ((synpred26_JavaParser())) {
					s = 21;
				}

				else if ((synpred27_JavaParser())) {
					s = 22;
				}

				input.seek(index20_19);
				if (s >= 0)
					return s;
				break;
			}
			if (state.backtracking > 0) {
				state.failed = true;
				return -1;
			}
			NoViableAltException nvae = new NoViableAltException(
					getDescription(), 20, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	static final String DFA26_eotS = "\10\uffff";
	static final String DFA26_eofS = "\2\uffff\1\3\5\uffff";
	static final String DFA26_minS = "\1\12\1\uffff\1\53\2\uffff\1\6\2\uffff";
	static final String DFA26_maxS = "\1\123\1\uffff\1\106\2\uffff\1\112\2\uffff";
	static final String DFA26_acceptS = "\1\uffff\1\1\1\uffff\1\3\1\5\1\uffff\1\4\1\2";
	static final String DFA26_specialS = "\10\uffff}>";
	static final String[] DFA26_transitionS = {
			"\1\4\3\uffff\1\1\43\uffff\2\3\25\uffff\1\1\1\2\1\1\1\uffff\1"
					+ "\1\4\uffff\2\1", "",
			"\4\3\3\uffff\6\3\5\uffff\1\5\5\3\1\6\1\3\1\uffff\1\3", "", "",
			"\1\7\103\uffff\1\2", "", "" };

	static final short[] DFA26_eot = DFA.unpackEncodedString(DFA26_eotS);
	static final short[] DFA26_eof = DFA.unpackEncodedString(DFA26_eofS);
	static final char[] DFA26_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA26_minS);
	static final char[] DFA26_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA26_maxS);
	static final short[] DFA26_accept = DFA.unpackEncodedString(DFA26_acceptS);
	static final short[] DFA26_special = DFA
			.unpackEncodedString(DFA26_specialS);
	static final short[][] DFA26_transition;

	static {
		int numStates = DFA26_transitionS.length;
		DFA26_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA26_transition[i] = DFA.unpackEncodedString(DFA26_transitionS[i]);
		}
	}

	class DFA26 extends DFA {

		public DFA26(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 26;
			this.eot = DFA26_eot;
			this.eof = DFA26_eof;
			this.min = DFA26_min;
			this.max = DFA26_max;
			this.accept = DFA26_accept;
			this.special = DFA26_special;
			this.transition = DFA26_transition;
		}

		public String getDescription() {
			return "82:2: ( constant | class_name dot class_ | ( unary )? variable_name ( unary )? | method_call | new_class )";
		}
	}

	static final String DFA27_eotS = "\15\uffff";
	static final String DFA27_eofS = "\1\1\14\uffff";
	static final String DFA27_minS = "\1\53\1\uffff\12\0\1\uffff";
	static final String DFA27_maxS = "\1\106\1\uffff\12\0\1\uffff";
	static final String DFA27_acceptS = "\1\uffff\1\2\12\uffff\1\1";
	static final String DFA27_specialS = "\2\uffff\1\0\1\6\1\11\1\2\1\10\1\1\1\5\1\7\1\3\1\4\1\uffff}>";
	static final String[] DFA27_transitionS = {
			"\1\12\1\13\1\6\1\7\5\uffff\1\2\1\3\1\5\1\4\6\uffff\1\1\1\10"
					+ "\1\11\1\uffff\1\1\1\uffff\1\1\1\uffff\1\1", "",
			"\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff",
			"\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "" };

	static final short[] DFA27_eot = DFA.unpackEncodedString(DFA27_eotS);
	static final short[] DFA27_eof = DFA.unpackEncodedString(DFA27_eofS);
	static final char[] DFA27_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA27_minS);
	static final char[] DFA27_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA27_maxS);
	static final short[] DFA27_accept = DFA.unpackEncodedString(DFA27_acceptS);
	static final short[] DFA27_special = DFA
			.unpackEncodedString(DFA27_specialS);
	static final short[][] DFA27_transition;

	static {
		int numStates = DFA27_transitionS.length;
		DFA27_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA27_transition[i] = DFA.unpackEncodedString(DFA27_transitionS[i]);
		}
	}

	class DFA27 extends DFA {

		public DFA27(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 27;
			this.eot = DFA27_eot;
			this.eof = DFA27_eof;
			this.min = DFA27_min;
			this.max = DFA27_max;
			this.accept = DFA27_accept;
			this.special = DFA27_special;
			this.transition = DFA27_transition;
		}

		public String getDescription() {
			return "()* loopback of 87:5: ( binary_operator value )*";
		}

		public int specialStateTransition(int s, IntStream _input)
				throws NoViableAltException {
			TokenStream input = (TokenStream) _input;
			int _s = s;
			switch (s) {
			case 0:
				int index27_2 = input.index();
				input.rewind();
				s = -1;
				if ((synpred37_JavaParser())) {
					s = 12;
				}

				else if ((true)) {
					s = 1;
				}

				input.seek(index27_2);
				if (s >= 0)
					return s;
				break;
			case 1:
				int index27_7 = input.index();
				input.rewind();
				s = -1;
				if ((synpred37_JavaParser())) {
					s = 12;
				}

				else if ((true)) {
					s = 1;
				}

				input.seek(index27_7);
				if (s >= 0)
					return s;
				break;
			case 2:
				int index27_5 = input.index();
				input.rewind();
				s = -1;
				if ((synpred37_JavaParser())) {
					s = 12;
				}

				else if ((true)) {
					s = 1;
				}

				input.seek(index27_5);
				if (s >= 0)
					return s;
				break;
			case 3:
				int index27_10 = input.index();
				input.rewind();
				s = -1;
				if ((synpred37_JavaParser())) {
					s = 12;
				}

				else if ((true)) {
					s = 1;
				}

				input.seek(index27_10);
				if (s >= 0)
					return s;
				break;
			case 4:
				int index27_11 = input.index();
				input.rewind();
				s = -1;
				if ((synpred37_JavaParser())) {
					s = 12;
				}

				else if ((true)) {
					s = 1;
				}

				input.seek(index27_11);
				if (s >= 0)
					return s;
				break;
			case 5:
				int index27_8 = input.index();
				input.rewind();
				s = -1;
				if ((synpred37_JavaParser())) {
					s = 12;
				}

				else if ((true)) {
					s = 1;
				}

				input.seek(index27_8);
				if (s >= 0)
					return s;
				break;
			case 6:
				int index27_3 = input.index();
				input.rewind();
				s = -1;
				if ((synpred37_JavaParser())) {
					s = 12;
				}

				else if ((true)) {
					s = 1;
				}

				input.seek(index27_3);
				if (s >= 0)
					return s;
				break;
			case 7:
				int index27_9 = input.index();
				input.rewind();
				s = -1;
				if ((synpred37_JavaParser())) {
					s = 12;
				}

				else if ((true)) {
					s = 1;
				}

				input.seek(index27_9);
				if (s >= 0)
					return s;
				break;
			case 8:
				int index27_6 = input.index();
				input.rewind();
				s = -1;
				if ((synpred37_JavaParser())) {
					s = 12;
				}

				else if ((true)) {
					s = 1;
				}

				input.seek(index27_6);
				if (s >= 0)
					return s;
				break;
			case 9:

				int index27_4 = input.index();
				input.rewind();
				s = -1;
				if ((synpred37_JavaParser())) {
					s = 12;
				}

				else if ((true)) {
					s = 1;
				}

				input.seek(index27_4);
				if (s >= 0)
					return s;
				break;
			}
			if (state.backtracking > 0) {
				state.failed = true;
				return -1;
			}
			NoViableAltException nvae = new NoViableAltException(
					getDescription(), 27, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	static final String DFA30_eotS = "\11\uffff";
	static final String DFA30_eofS = "\11\uffff";
	static final String DFA30_minS = "\1\12\2\uffff\1\53\2\uffff\1\6\2\uffff";
	static final String DFA30_maxS = "\1\123\2\uffff\1\104\2\uffff\1\112\2\uffff";
	static final String DFA30_acceptS = "\1\uffff\1\1\1\2\1\uffff\1\4\1\6\1\uffff\1\5\1\3";
	static final String DFA30_specialS = "\11\uffff}>";
	static final String[] DFA30_transitionS = {
			"\1\5\3\uffff\1\2\34\uffff\4\1\3\uffff\2\4\4\1\7\uffff\2\1\3"
					+ "\uffff\1\1\4\uffff\1\2\1\3\1\2\1\uffff\1\2\4\uffff\2\2",
			"", "", "\4\4\3\uffff\6\4\5\uffff\1\6\1\uffff\3\4\1\uffff\1\7\1\4",
			"", "", "\1\10\103\uffff\1\3", "", "" };

	static final short[] DFA30_eot = DFA.unpackEncodedString(DFA30_eotS);
	static final short[] DFA30_eof = DFA.unpackEncodedString(DFA30_eofS);
	static final char[] DFA30_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA30_minS);
	static final char[] DFA30_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA30_maxS);
	static final short[] DFA30_accept = DFA.unpackEncodedString(DFA30_acceptS);
	static final short[] DFA30_special = DFA
			.unpackEncodedString(DFA30_specialS);
	static final short[][] DFA30_transition;

	static {
		int numStates = DFA30_transitionS.length;
		DFA30_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA30_transition[i] = DFA.unpackEncodedString(DFA30_transitionS[i]);
		}
	}

	class DFA30 extends DFA {

		public DFA30(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 30;
			this.eot = DFA30_eot;
			this.eof = DFA30_eof;
			this.min = DFA30_min;
			this.max = DFA30_max;
			this.accept = DFA30_accept;
			this.special = DFA30_special;
			this.transition = DFA30_transition;
		}

		public String getDescription() {
			return "89:17: ( | constant | class_name dot class_ | ( unary )? variable_name ( unary )? | method_call | new_class )";
		}
	}

	static final String DFA40_eotS = "\30\uffff";
	static final String DFA40_eofS = "\30\uffff";
	static final String DFA40_minS = "\1\11\2\uffff\1\0\24\uffff";
	static final String DFA40_maxS = "\1\112\2\uffff\1\0\24\uffff";
	static final String DFA40_acceptS = "\1\uffff\1\1\2\uffff\1\2\11\uffff\1\4\1\5\1\6\1\uffff\1\7\1\10\1"
			+ "\11\1\12\1\13\1\3";
	static final String DFA40_specialS = "\3\uffff\1\0\24\uffff}>";
	static final String[] DFA40_transitionS = {
			"\1\4\1\uffff\1\17\3\uffff\2\1\1\22\1\24\1\23\1\25\3\uffff\1"
					+ "\26\10\uffff\10\4\11\uffff\2\20\22\uffff\1\16\1\4\2\uffff\1"
					+ "\3", "", "", "\1\uffff", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "", "", "" };

	static final short[] DFA40_eot = DFA.unpackEncodedString(DFA40_eotS);
	static final short[] DFA40_eof = DFA.unpackEncodedString(DFA40_eofS);
	static final char[] DFA40_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA40_minS);
	static final char[] DFA40_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA40_maxS);
	static final short[] DFA40_accept = DFA.unpackEncodedString(DFA40_acceptS);
	static final short[] DFA40_special = DFA
			.unpackEncodedString(DFA40_specialS);
	static final short[][] DFA40_transition;

	static {
		int numStates = DFA40_transitionS.length;
		DFA40_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA40_transition[i] = DFA.unpackEncodedString(DFA40_transitionS[i]);
		}
	}

	class DFA40 extends DFA {

		public DFA40(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 40;
			this.eot = DFA40_eot;
			this.eof = DFA40_eof;
			this.min = DFA40_min;
			this.max = DFA40_max;
			this.accept = DFA40_accept;
			this.special = DFA40_special;
			this.transition = DFA40_transition;
		}

		public String getDescription() {
			return "118:1: statement : ( variable_assignment semicolon | variable_def semicolon | method_call semicolon | semicolon | return_statement | ( unary )? variable_name ( unary )? | for_loop | while_loop | do_loop | if_else | try_catch );";
		}

		public int specialStateTransition(int s, IntStream _input)
				throws NoViableAltException {
			TokenStream input = (TokenStream) _input;
			int _s = s;
			switch (s) {
			case 0:
				int index40_3 = input.index();
				input.rewind();
				s = -1;
				if ((synpred56_JavaParser())) {
					s = 1;
				}

				else if ((synpred57_JavaParser())) {
					s = 4;
				}

				else if ((synpred58_JavaParser())) {
					s = 23;
				}

				else if ((synpred63_JavaParser())) {
					s = 16;
				}

				input.seek(index40_3);
				if (s >= 0)
					return s;
				break;
			}
			if (state.backtracking > 0) {
				state.failed = true;
				return -1;
			}
			NoViableAltException nvae = new NoViableAltException(
					getDescription(), 40, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	static final String DFA43_eotS = "\27\uffff";
	static final String DFA43_eofS = "\27\uffff";
	static final String DFA43_minS = "\1\11\2\uffff\1\0\23\uffff";
	static final String DFA43_maxS = "\1\112\2\uffff\1\0\23\uffff";
	static final String DFA43_acceptS = "\1\uffff\1\1\2\uffff\1\2\11\uffff\1\4\1\5\1\uffff\1\6\1\7\1\10\1"
			+ "\11\1\12\1\3";
	static final String DFA43_specialS = "\3\uffff\1\0\23\uffff}>";
	static final String[] DFA43_transitionS = {
			"\1\4\1\uffff\1\16\3\uffff\2\1\1\21\1\23\1\22\1\24\3\uffff\1"
					+ "\25\10\uffff\10\4\11\uffff\2\17\23\uffff\1\4\2\uffff\1\3",
			"", "", "\1\uffff", "", "", "", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "" };

	static final short[] DFA43_eot = DFA.unpackEncodedString(DFA43_eotS);
	static final short[] DFA43_eof = DFA.unpackEncodedString(DFA43_eofS);
	static final char[] DFA43_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA43_minS);
	static final char[] DFA43_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA43_maxS);
	static final short[] DFA43_accept = DFA.unpackEncodedString(DFA43_acceptS);
	static final short[] DFA43_special = DFA
			.unpackEncodedString(DFA43_specialS);
	static final short[][] DFA43_transition;

	static {
		int numStates = DFA43_transitionS.length;
		DFA43_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
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
			return "132:1: statement_wosemicolon : ( variable_assignment | variable_def | method_call | return_statement | ( unary )? variable_name ( unary )? | for_loop | while_loop | do_loop | if_else | try_catch );";
		}

		public int specialStateTransition(int s, IntStream _input)
				throws NoViableAltException {
			TokenStream input = (TokenStream) _input;
			int _s = s;
			switch (s) {
			case 0:
				int index43_3 = input.index();
				input.rewind();
				s = -1;
				if ((synpred68_JavaParser())) {
					s = 1;
				}

				else if ((synpred69_JavaParser())) {
					s = 4;
				}

				else if ((synpred70_JavaParser())) {
					s = 22;
				}

				else if ((synpred74_JavaParser())) {
					s = 15;
				}

				input.seek(index43_3);
				if (s >= 0)
					return s;
				break;
			}
			if (state.backtracking > 0) {
				state.failed = true;
				return -1;
			}
			NoViableAltException nvae = new NoViableAltException(
					getDescription(), 43, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	public static final BitSet FOLLOW_package_def_in_file44 = new BitSet(
			new long[] { 0x00000001F8000062L, 0x0000000000000080L });
	public static final BitSet FOLLOW_import_def_in_file46 = new BitSet(
			new long[] { 0x00000001F8000062L, 0x0000000000000080L });
	public static final BitSet FOLLOW_class_def_in_file49 = new BitSet(
			new long[] { 0x00000001F8000042L, 0x0000000000000080L });
	public static final BitSet FOLLOW_package__in_package_def60 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_package_name_in_package_def62 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000040L });
	public static final BitSet FOLLOW_semicolon_in_package_def64 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_import__in_import_def76 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_import_name_in_import_def78 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000040L });
	public static final BitSet FOLLOW_semicolon_in_import_def80 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_annotation_in_class_def92 = new BitSet(
			new long[] { 0x00000001F8000040L, 0x0000000000000080L });
	public static final BitSet FOLLOW_modifier_in_class_def95 = new BitSet(
			new long[] { 0x00000001F8000040L, 0x0000000000000080L });
	public static final BitSet FOLLOW_class__in_class_def98 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_id_in_class_def100 = new BitSet(
			new long[] { 0x0000020000000180L });
	public static final BitSet FOLLOW_extends__in_class_def103 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_class_name_in_class_def105 = new BitSet(
			new long[] { 0x0000020000000180L });
	public static final BitSet FOLLOW_implements__in_class_def110 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_class_name_in_class_def112 = new BitSet(
			new long[] { 0x4000020000000180L });
	public static final BitSet FOLLOW_comma_in_class_def115 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_class_name_in_class_def117 = new BitSet(
			new long[] { 0x4000020000000180L });
	public static final BitSet FOLLOW_class_block_in_class_def123 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_annotation_in_constructor_def139 = new BitSet(
			new long[] { 0x00000001F8000000L, 0x0000000000000480L });
	public static final BitSet FOLLOW_modifier_in_constructor_def142 = new BitSet(
			new long[] { 0x00000001F8000000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_id_in_constructor_def145 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000008L });
	public static final BitSet FOLLOW_open_bracket_in_constructor_def147 = new BitSet(
			new long[] { 0x000001FE00000200L, 0x0000000000000410L });
	public static final BitSet FOLLOW_argument_def_in_constructor_def149 = new BitSet(
			new long[] { 0x000001FE00000200L, 0x0000000000000410L });
	public static final BitSet FOLLOW_close_bracket_in_constructor_def151 = new BitSet(
			new long[] { 0x000C03FE011F8B80L, 0x00000000000004C0L });
	public static final BitSet FOLLOW_code_in_constructor_def153 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_annotation_in_method_def168 = new BitSet(
			new long[] { 0x000001FFF8000200L, 0x0000000000000480L });
	public static final BitSet FOLLOW_modifier_in_method_def171 = new BitSet(
			new long[] { 0x000001FFF8000200L, 0x0000000000000400L });
	public static final BitSet FOLLOW_variable_type_in_method_def174 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_id_in_method_def176 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000008L });
	public static final BitSet FOLLOW_open_bracket_in_method_def178 = new BitSet(
			new long[] { 0x000001FE00000200L, 0x0000000000000410L });
	public static final BitSet FOLLOW_argument_def_in_method_def180 = new BitSet(
			new long[] { 0x000001FE00000200L, 0x0000000000000410L });
	public static final BitSet FOLLOW_close_bracket_in_method_def182 = new BitSet(
			new long[] { 0x000C03FE011F8B80L, 0x00000000000004C0L });
	public static final BitSet FOLLOW_code_in_method_def184 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_annotation_in_field_def200 = new BitSet(
			new long[] { 0x000001FFF8000200L, 0x0000000000000480L });
	public static final BitSet FOLLOW_modifier_in_field_def203 = new BitSet(
			new long[] { 0x000001FFF8000200L, 0x0000000000000400L });
	public static final BitSet FOLLOW_variable_type_in_field_def206 = new BitSet(
			new long[] { 0x0000000000018000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_variable_name_in_field_def208 = new BitSet(
			new long[] { 0x0003800000000000L, 0x0000000000000040L });
	public static final BitSet FOLLOW_assign_in_field_def211 = new BitSet(
			new long[] { 0x000C00000001C400L, 0x00000000000C2E08L });
	public static final BitSet FOLLOW_value_in_field_def213 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000040L });
	public static final BitSet FOLLOW_semicolon_in_field_def217 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_variable_type_in_argument_def229 = new BitSet(
			new long[] { 0x0000000000018000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_variable_name_in_argument_def231 = new BitSet(
			new long[] { 0x4000000000000002L });
	public static final BitSet FOLLOW_comma_in_argument_def234 = new BitSet(
			new long[] { 0x000001FE00000200L, 0x0000000000000400L });
	public static final BitSet FOLLOW_variable_type_in_argument_def236 = new BitSet(
			new long[] { 0x0000000000018000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_variable_name_in_argument_def238 = new BitSet(
			new long[] { 0x4000000000000002L });
	public static final BitSet FOLLOW_annotation_in_variable_def253 = new BitSet(
			new long[] { 0x000001FE00000200L, 0x0000000000000480L });
	public static final BitSet FOLLOW_variable_type_in_variable_def256 = new BitSet(
			new long[] { 0x0000000000018000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_variable_name_in_variable_def258 = new BitSet(
			new long[] { 0x0003800000000002L });
	public static final BitSet FOLLOW_assign_in_variable_def261 = new BitSet(
			new long[] { 0x000C00000001C400L, 0x00000000000C2E08L });
	public static final BitSet FOLLOW_value_in_variable_def263 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_public__in_modifier275 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_private__in_modifier280 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_protected__in_modifier285 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_static__in_modifier290 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_final__in_modifier295 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_transient__in_modifier300 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_block_begin_in_class_block312 = new BitSet(
			new long[] { 0x000005FFF8000240L, 0x0000000000000480L });
	public static final BitSet FOLLOW_class_def_in_class_block315 = new BitSet(
			new long[] { 0x000005FFF8000240L, 0x0000000000000480L });
	public static final BitSet FOLLOW_constructor_def_in_class_block319 = new BitSet(
			new long[] { 0x000005FFF8000240L, 0x0000000000000480L });
	public static final BitSet FOLLOW_method_def_in_class_block323 = new BitSet(
			new long[] { 0x000005FFF8000240L, 0x0000000000000480L });
	public static final BitSet FOLLOW_field_def_in_class_block327 = new BitSet(
			new long[] { 0x000005FFF8000240L, 0x0000000000000480L });
	public static final BitSet FOLLOW_block_end_in_class_block331 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_method_name_in_method_call343 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000008L });
	public static final BitSet FOLLOW_open_bracket_in_method_call345 = new BitSet(
			new long[] { 0x000C01FE0001C600L, 0x00000000000C2E18L });
	public static final BitSet FOLLOW_arguments_in_method_call347 = new BitSet(
			new long[] { 0x000001FE00000200L, 0x0000000000000410L });
	public static final BitSet FOLLOW_close_bracket_in_method_call349 = new BitSet(
			new long[] { 0x2000000000000002L });
	public static final BitSet FOLLOW_dot_in_method_call352 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_id_in_method_call354 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000008L });
	public static final BitSet FOLLOW_open_bracket_in_method_call356 = new BitSet(
			new long[] { 0x000C01FE0001C600L, 0x00000000000C2E18L });
	public static final BitSet FOLLOW_arguments_in_method_call358 = new BitSet(
			new long[] { 0x000001FE00000200L, 0x0000000000000410L });
	public static final BitSet FOLLOW_close_bracket_in_method_call360 = new BitSet(
			new long[] { 0x2000000000000002L });
	public static final BitSet FOLLOW_annotation_name_in_annotation373 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000000000000008L });
	public static final BitSet FOLLOW_open_bracket_in_annotation376 = new BitSet(
			new long[] { 0x000C00000001C400L, 0x00000000000C2E08L });
	public static final BitSet FOLLOW_value_in_annotation378 = new BitSet(
			new long[] { 0x400001FE00000200L, 0x0000000000000410L });
	public static final BitSet FOLLOW_comma_in_annotation381 = new BitSet(
			new long[] { 0x000C00000001C400L, 0x00000000000C2E08L });
	public static final BitSet FOLLOW_value_in_annotation383 = new BitSet(
			new long[] { 0x400001FE00000200L, 0x0000000000000410L });
	public static final BitSet FOLLOW_close_bracket_in_annotation387 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_constant_in_value402 = new BitSet(
			new long[] { 0x80F0780000000002L, 0x0000000000000001L });
	public static final BitSet FOLLOW_class_name_in_value407 = new BitSet(
			new long[] { 0x2000000000000000L });
	public static final BitSet FOLLOW_dot_in_value409 = new BitSet(new long[] {
			0x00000001F8000040L, 0x0000000000000080L });
	public static final BitSet FOLLOW_class__in_value411 = new BitSet(
			new long[] { 0x80F0780000000002L, 0x0000000000000001L });
	public static final BitSet FOLLOW_unary_in_value416 = new BitSet(
			new long[] { 0x0000000000018000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_variable_name_in_value419 = new BitSet(
			new long[] { 0x80FC780000000002L, 0x0000000000000001L });
	public static final BitSet FOLLOW_unary_in_value421 = new BitSet(
			new long[] { 0x80F0780000000002L, 0x0000000000000001L });
	public static final BitSet FOLLOW_method_call_in_value427 = new BitSet(
			new long[] { 0x80F0780000000002L, 0x0000000000000001L });
	public static final BitSet FOLLOW_new_class_in_value432 = new BitSet(
			new long[] { 0x80F0780000000002L, 0x0000000000000001L });
	public static final BitSet FOLLOW_binary_operator_in_value439 = new BitSet(
			new long[] { 0x000C00000001C400L, 0x00000000000C2E08L });
	public static final BitSet FOLLOW_value_in_value441 = new BitSet(
			new long[] { 0x80F0780000000002L, 0x0000000000000001L });
	public static final BitSet FOLLOW_open_bracket_in_value451 = new BitSet(
			new long[] { 0x80FC79FE0001C600L, 0x00000000000C2E11L });
	public static final BitSet FOLLOW_constant_in_value458 = new BitSet(
			new long[] { 0x80F079FE00000200L, 0x0000000000000411L });
	public static final BitSet FOLLOW_class_name_in_value463 = new BitSet(
			new long[] { 0x2000000000000000L });
	public static final BitSet FOLLOW_dot_in_value465 = new BitSet(new long[] {
			0x00000001F8000040L, 0x0000000000000080L });
	public static final BitSet FOLLOW_class__in_value467 = new BitSet(
			new long[] { 0x80F079FE00000200L, 0x0000000000000411L });
	public static final BitSet FOLLOW_unary_in_value472 = new BitSet(
			new long[] { 0x0000000000018000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_variable_name_in_value475 = new BitSet(
			new long[] { 0x80FC79FE00000200L, 0x0000000000000411L });
	public static final BitSet FOLLOW_unary_in_value477 = new BitSet(
			new long[] { 0x80F079FE00000200L, 0x0000000000000411L });
	public static final BitSet FOLLOW_method_call_in_value483 = new BitSet(
			new long[] { 0x80F079FE00000200L, 0x0000000000000411L });
	public static final BitSet FOLLOW_new_class_in_value488 = new BitSet(
			new long[] { 0x80F079FE00000200L, 0x0000000000000411L });
	public static final BitSet FOLLOW_binary_operator_in_value496 = new BitSet(
			new long[] { 0x000C00000001C400L, 0x00000000000C2E08L });
	public static final BitSet FOLLOW_value_in_value498 = new BitSet(
			new long[] { 0x80F079FE00000200L, 0x0000000000000411L });
	public static final BitSet FOLLOW_close_bracket_in_value503 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_int_const_in_constant512 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_string_const_in_constant517 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_float_const_in_constant522 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_char_const_in_constant527 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_null_const_in_constant532 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_boolean_const_in_constant537 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_new__in_new_class549 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_class_name_in_new_class551 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000008L });
	public static final BitSet FOLLOW_open_bracket_in_new_class553 = new BitSet(
			new long[] { 0x000C01FE0001C600L, 0x00000000000C2E18L });
	public static final BitSet FOLLOW_arguments_in_new_class555 = new BitSet(
			new long[] { 0x000001FE00000200L, 0x0000000000000410L });
	public static final BitSet FOLLOW_close_bracket_in_new_class557 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_value_in_arguments570 = new BitSet(
			new long[] { 0x4000000000000002L });
	public static final BitSet FOLLOW_comma_in_arguments573 = new BitSet(
			new long[] { 0x000C00000001C400L, 0x00000000000C2E08L });
	public static final BitSet FOLLOW_value_in_arguments575 = new BitSet(
			new long[] { 0x4000000000000002L });
	public static final BitSet FOLLOW_statement_in_code589 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_block_begin_in_code594 = new BitSet(
			new long[] { 0x000C05FFF91F8A40L, 0x00000000000004C0L });
	public static final BitSet FOLLOW_statement_in_code596 = new BitSet(
			new long[] { 0x000C05FFF91F8A40L, 0x00000000000004C0L });
	public static final BitSet FOLLOW_block_end_in_code599 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_variable_assignment_in_statement611 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000040L });
	public static final BitSet FOLLOW_semicolon_in_statement613 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_variable_def_in_statement618 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000040L });
	public static final BitSet FOLLOW_semicolon_in_statement620 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_method_call_in_statement625 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000040L });
	public static final BitSet FOLLOW_semicolon_in_statement627 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_semicolon_in_statement633 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_return_statement_in_statement638 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_unary_in_statement643 = new BitSet(
			new long[] { 0x0000000000018000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_variable_name_in_statement646 = new BitSet(
			new long[] { 0x000C000000000002L });
	public static final BitSet FOLLOW_unary_in_statement648 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_for_loop_in_statement654 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_while_loop_in_statement659 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_do_loop_in_statement664 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_if_else_in_statement669 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_try_catch_in_statement674 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_variable_assignment_in_statement_wosemicolon685 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_variable_def_in_statement_wosemicolon690 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_method_call_in_statement_wosemicolon695 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_return_statement_in_statement_wosemicolon700 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_unary_in_statement_wosemicolon705 = new BitSet(
			new long[] { 0x0000000000018000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_variable_name_in_statement_wosemicolon708 = new BitSet(
			new long[] { 0x000C000000000002L });
	public static final BitSet FOLLOW_unary_in_statement_wosemicolon710 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_for_loop_in_statement_wosemicolon716 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_while_loop_in_statement_wosemicolon721 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_do_loop_in_statement_wosemicolon726 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_if_else_in_statement_wosemicolon731 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_try_catch_in_statement_wosemicolon736 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_return__in_return_statement748 = new BitSet(
			new long[] { 0x000C00000001C400L, 0x00000000000C2E08L });
	public static final BitSet FOLLOW_value_in_return_statement750 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000040L });
	public static final BitSet FOLLOW_semicolon_in_return_statement752 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_this__in_variable_assignment765 = new BitSet(
			new long[] { 0x2000000000000000L });
	public static final BitSet FOLLOW_dot_in_variable_assignment767 = new BitSet(
			new long[] { 0x0000000000018000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_super__in_variable_assignment771 = new BitSet(
			new long[] { 0x2000000000000000L });
	public static final BitSet FOLLOW_dot_in_variable_assignment773 = new BitSet(
			new long[] { 0x0000000000018000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_variable_name_in_variable_assignment777 = new BitSet(
			new long[] { 0x0003800000000000L });
	public static final BitSet FOLLOW_assign_in_variable_assignment779 = new BitSet(
			new long[] { 0x000C00000001C400L, 0x00000000000C2E08L });
	public static final BitSet FOLLOW_value_in_variable_assignment781 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_for__in_for_loop791 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000008L });
	public static final BitSet FOLLOW_OPEN_BRACKET_in_for_loop793 = new BitSet(
			new long[] { 0x000001FE00000200L, 0x00000000000004C0L });
	public static final BitSet FOLLOW_variable_def_in_for_loop796 = new BitSet(
			new long[] { 0x4000000000000000L, 0x0000000000000040L });
	public static final BitSet FOLLOW_comma_in_for_loop799 = new BitSet(
			new long[] { 0x000001FE00000200L, 0x0000000000000480L });
	public static final BitSet FOLLOW_variable_def_in_for_loop801 = new BitSet(
			new long[] { 0x4000000000000000L, 0x0000000000000040L });
	public static final BitSet FOLLOW_semicolon_in_for_loop807 = new BitSet(
			new long[] { 0x000C00000001C400L, 0x00000000000C2E48L });
	public static final BitSet FOLLOW_value_in_for_loop809 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000040L });
	public static final BitSet FOLLOW_semicolon_in_for_loop812 = new BitSet(
			new long[] { 0x000C01FE011F8A00L, 0x00000000000004D0L });
	public static final BitSet FOLLOW_statement_wosemicolon_in_for_loop815 = new BitSet(
			new long[] { 0x4000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_comma_in_for_loop818 = new BitSet(
			new long[] { 0x000C01FE011F8A00L, 0x00000000000004C0L });
	public static final BitSet FOLLOW_statement_wosemicolon_in_for_loop820 = new BitSet(
			new long[] { 0x4000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_CLOSE_BRACKET_in_for_loop826 = new BitSet(
			new long[] { 0x000C03FE011F8B80L, 0x00000000000004C0L });
	public static final BitSet FOLLOW_code_in_for_loop828 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_for__in_for_loop833 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000008L });
	public static final BitSet FOLLOW_OPEN_BRACKET_in_for_loop835 = new BitSet(
			new long[] { 0x000001FE00000200L, 0x0000000000000400L });
	public static final BitSet FOLLOW_variable_type_in_for_loop837 = new BitSet(
			new long[] { 0x0000000000018000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_variable_name_in_for_loop839 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000020L });
	public static final BitSet FOLLOW_colon_in_for_loop841 = new BitSet(
			new long[] { 0x000C00000001C400L, 0x00000000000C2E08L });
	public static final BitSet FOLLOW_value_in_for_loop843 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_CLOSE_BRACKET_in_for_loop845 = new BitSet(
			new long[] { 0x000C03FE011F8B80L, 0x00000000000004C0L });
	public static final BitSet FOLLOW_code_in_for_loop847 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_while__in_while_loop859 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000008L });
	public static final BitSet FOLLOW_OPEN_BRACKET_in_while_loop861 = new BitSet(
			new long[] { 0x000C00000001C400L, 0x00000000000C2E08L });
	public static final BitSet FOLLOW_value_in_while_loop863 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_CLOSE_BRACKET_in_while_loop865 = new BitSet(
			new long[] { 0x000C03FE011F8B80L, 0x00000000000004C0L });
	public static final BitSet FOLLOW_code_in_while_loop867 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_do__in_do_loop878 = new BitSet(
			new long[] { 0x000C03FE011F8B80L, 0x00000000000004C0L });
	public static final BitSet FOLLOW_code_in_do_loop880 = new BitSet(
			new long[] { 0x0000000000080000L });
	public static final BitSet FOLLOW_while__in_do_loop882 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000008L });
	public static final BitSet FOLLOW_OPEN_BRACKET_in_do_loop884 = new BitSet(
			new long[] { 0x000C00000001C400L, 0x00000000000C2E08L });
	public static final BitSet FOLLOW_value_in_do_loop886 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_CLOSE_BRACKET_in_do_loop888 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_if__in_if_else899 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000008L });
	public static final BitSet FOLLOW_OPEN_BRACKET_in_if_else901 = new BitSet(
			new long[] { 0x000C00000001C400L, 0x00000000000C2E08L });
	public static final BitSet FOLLOW_value_in_if_else903 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_CLOSE_BRACKET_in_if_else905 = new BitSet(
			new long[] { 0x000C03FE011F8B80L, 0x00000000000004C0L });
	public static final BitSet FOLLOW_code_in_if_else907 = new BitSet(
			new long[] { 0x0000000000200000L });
	public static final BitSet FOLLOW_else__in_if_else909 = new BitSet(
			new long[] { 0x000C03FE011F8B80L, 0x00000000000004C0L });
	public static final BitSet FOLLOW_code_in_if_else911 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_try__in_try_catch922 = new BitSet(
			new long[] { 0x000C03FE011F8B80L, 0x00000000000004C0L });
	public static final BitSet FOLLOW_code_in_try_catch924 = new BitSet(
			new long[] { 0x0000000002000000L });
	public static final BitSet FOLLOW_catch__in_try_catch927 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000008L });
	public static final BitSet FOLLOW_OPEN_BRACKET_in_try_catch929 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_id_in_try_catch931 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_id_in_try_catch933 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_CLOSE_BRACKET_in_try_catch935 = new BitSet(
			new long[] { 0x000C03FE011F8B80L, 0x00000000000004C0L });
	public static final BitSet FOLLOW_code_in_try_catch937 = new BitSet(
			new long[] { 0x0000000006000002L });
	public static final BitSet FOLLOW_finally__in_try_catch942 = new BitSet(
			new long[] { 0x000C03FE011F8B80L, 0x00000000000004C0L });
	public static final BitSet FOLLOW_code_in_try_catch944 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_primitive_in_variable_type958 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000000000000002L });
	public static final BitSet FOLLOW_array_in_variable_type960 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_class_name_in_variable_type966 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000000000000002L });
	public static final BitSet FOLLOW_array_in_variable_type968 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_void__in_variable_type974 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ID_in_id984 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_PLUS_in_binary_operator993 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_MINUS_in_binary_operator1000 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_STAR_in_binary_operator1007 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_SLASH_in_binary_operator1014 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_EQUAL_in_binary_operator1021 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_UNEQUAL_in_binary_operator1028 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_LT_in_binary_operator1035 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_GT_in_binary_operator1042 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_LE_in_binary_operator1049 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_GE_in_binary_operator1056 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_INC_in_unary1068 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_DEC_in_unary1075 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_BOOLEAN_in_primitive1088 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_BYTE_in_primitive1095 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_CHAR_in_primitive1102 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_SHORT_in_primitive1109 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_INTEGER_in_primitive1116 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_LONG_in_primitive1123 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_FLOAT_in_primitive1130 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_DOUBLE_in_primitive1137 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_INT_CONST_in_int_const1155 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_STRING_CONST_in_string_const1165 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_FLOAT_CONST_in_float_const1175 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_CHAR_CONST_in_char_const1185 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_NULL_in_null_const1195 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_BOOL_CONST_in_boolean_const1206 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ID_in_package_name1222 = new BitSet(
			new long[] { 0x2000000000000000L });
	public static final BitSet FOLLOW_DOT_in_package_name1224 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_ID_in_package_name1228 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ID_in_import_name1242 = new BitSet(
			new long[] { 0x2000000000000000L });
	public static final BitSet FOLLOW_DOT_in_import_name1244 = new BitSet(
			new long[] { 0x0080000000000000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_set_in_import_name1248 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ID_in_class_name1266 = new BitSet(
			new long[] { 0x2000000000000000L });
	public static final BitSet FOLLOW_DOT_in_class_name1268 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_ID_in_class_name1272 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ID_in_method_name1286 = new BitSet(
			new long[] { 0x2000000000000000L });
	public static final BitSet FOLLOW_DOT_in_method_name1288 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_ID_in_method_name1292 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_name_in_variable_name1305 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000000000000002L });
	public static final BitSet FOLLOW_array_in_variable_name1307 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_AT_in_annotation_name1318 = new BitSet(
			new long[] { 0x0000000000018000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_name_in_annotation_name1320 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ID_in_name1330 = new BitSet(
			new long[] { 0x2000000000000000L });
	public static final BitSet FOLLOW_DOT_in_name1332 = new BitSet(new long[] {
			0x0000000000000000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_ID_in_name1336 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_open_rect_bracket_in_array1346 = new BitSet(
			new long[] { 0x000C00000001C400L, 0x00000000000C2E0CL });
	public static final BitSet FOLLOW_value_in_array1348 = new BitSet(
			new long[] { 0x000C00000001C400L, 0x00000000000C2E0CL });
	public static final BitSet FOLLOW_close_rect_bracket_in_array1351 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_PACKAGE_in_package_1364 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_IMPORT_in_import_1375 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_CLASS_in_class_1385 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_EXTENDS_in_extends_1393 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_IMPLEMENTS_in_implements_1403 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_THIS_in_this_1412 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_SUPER_in_super_1421 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_VOID_in_void_1430 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_PUBLIC_in_public_1439 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_PRIVATE_in_private_1447 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_PROTECTED_in_protected_1457 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_STATIC_in_static_1466 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_FINAL_in_final_1475 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_TRANSIENT_in_transient_1485 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_NEW_in_new_1494 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_TRY_in_try_1503 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_CATCH_in_catch_1512 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_FINALLY_in_finally_1520 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_FOR_in_for_1530 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_WHILE_in_while_1539 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_DO_in_do_1548 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_IF_in_if_1557 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ELSE_in_else_1566 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_RETURN_in_return_1576 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_BREAK_in_break_1585 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_CONTINUE_in_continue_1595 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_SEMICOLON_in_semicolon1610 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_COMMA_in_comma1619 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_COLON_in_colon1628 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_DOT_in_dot1637 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ASSIGN_in_assign1646 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ASSIGN_in_assign1653 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_INCASSIGN_in_assign1660 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_DECASSIGN_in_assign1667 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_OPEN_CURLY_BRACKET_in_block_begin1679 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_CLOSE_CURLY_BRACKET_in_block_end1689 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_OPEN_BRACKET_in_open_bracket1699 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_CLOSE_BRACKET_in_close_bracket1709 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_OPEN_RECT_BRACKET_in_open_rect_bracket1720 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_CLOSE_RECT_BRACKET_in_close_rect_bracket1730 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_class_def_in_synpred24_JavaParser315 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_constructor_def_in_synpred25_JavaParser319 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_method_def_in_synpred26_JavaParser323 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_field_def_in_synpred27_JavaParser327 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_binary_operator_in_synpred37_JavaParser439 = new BitSet(
			new long[] { 0x000C00000001C400L, 0x00000000000C2E08L });
	public static final BitSet FOLLOW_value_in_synpred37_JavaParser441 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_variable_assignment_in_synpred56_JavaParser611 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000040L });
	public static final BitSet FOLLOW_semicolon_in_synpred56_JavaParser613 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_variable_def_in_synpred57_JavaParser618 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000040L });
	public static final BitSet FOLLOW_semicolon_in_synpred57_JavaParser620 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_method_call_in_synpred58_JavaParser625 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000040L });
	public static final BitSet FOLLOW_semicolon_in_synpred58_JavaParser627 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_unary_in_synpred62_JavaParser648 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_unary_in_synpred63_JavaParser643 = new BitSet(
			new long[] { 0x0000000000018000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_variable_name_in_synpred63_JavaParser646 = new BitSet(
			new long[] { 0x000C000000000002L });
	public static final BitSet FOLLOW_unary_in_synpred63_JavaParser648 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_variable_assignment_in_synpred68_JavaParser685 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_variable_def_in_synpred69_JavaParser690 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_method_call_in_synpred70_JavaParser695 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_unary_in_synpred74_JavaParser705 = new BitSet(
			new long[] { 0x0000000000018000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_variable_name_in_synpred74_JavaParser708 = new BitSet(
			new long[] { 0x000C000000000002L });
	public static final BitSet FOLLOW_unary_in_synpred74_JavaParser710 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_for__in_synpred86_JavaParser791 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000008L });
	public static final BitSet FOLLOW_OPEN_BRACKET_in_synpred86_JavaParser793 = new BitSet(
			new long[] { 0x000001FE00000200L, 0x00000000000004C0L });
	public static final BitSet FOLLOW_variable_def_in_synpred86_JavaParser796 = new BitSet(
			new long[] { 0x4000000000000000L, 0x0000000000000040L });
	public static final BitSet FOLLOW_comma_in_synpred86_JavaParser799 = new BitSet(
			new long[] { 0x000001FE00000200L, 0x0000000000000480L });
	public static final BitSet FOLLOW_variable_def_in_synpred86_JavaParser801 = new BitSet(
			new long[] { 0x4000000000000000L, 0x0000000000000040L });
	public static final BitSet FOLLOW_semicolon_in_synpred86_JavaParser807 = new BitSet(
			new long[] { 0x000C00000001C400L, 0x00000000000C2E48L });
	public static final BitSet FOLLOW_value_in_synpred86_JavaParser809 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000040L });
	public static final BitSet FOLLOW_semicolon_in_synpred86_JavaParser812 = new BitSet(
			new long[] { 0x000C01FE011F8A00L, 0x00000000000004D0L });
	public static final BitSet FOLLOW_statement_wosemicolon_in_synpred86_JavaParser815 = new BitSet(
			new long[] { 0x4000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_comma_in_synpred86_JavaParser818 = new BitSet(
			new long[] { 0x000C01FE011F8A00L, 0x00000000000004C0L });
	public static final BitSet FOLLOW_statement_wosemicolon_in_synpred86_JavaParser820 = new BitSet(
			new long[] { 0x4000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_CLOSE_BRACKET_in_synpred86_JavaParser826 = new BitSet(
			new long[] { 0x000C03FE011F8B80L, 0x00000000000004C0L });
	public static final BitSet FOLLOW_code_in_synpred86_JavaParser828 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_catch__in_synpred87_JavaParser927 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000008L });
	public static final BitSet FOLLOW_OPEN_BRACKET_in_synpred87_JavaParser929 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_id_in_synpred87_JavaParser931 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000400L });
	public static final BitSet FOLLOW_id_in_synpred87_JavaParser933 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_CLOSE_BRACKET_in_synpred87_JavaParser935 = new BitSet(
			new long[] { 0x000C03FE011F8B80L, 0x00000000000004C0L });
	public static final BitSet FOLLOW_code_in_synpred87_JavaParser937 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_finally__in_synpred88_JavaParser942 = new BitSet(
			new long[] { 0x000C03FE011F8B80L, 0x00000000000004C0L });
	public static final BitSet FOLLOW_code_in_synpred88_JavaParser944 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ASSIGN_in_synpred118_JavaParser1646 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ASSIGN_in_synpred119_JavaParser1653 = new BitSet(
			new long[] { 0x0000000000000002L });

}