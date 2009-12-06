// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g 2009-12-06 14:53:55

package com.puresol.coding.java.antlr.output;

import org.antlr.runtime.*;

import com.puresol.coding.java.antlr.ANTLRJavaHelper;

public class JavaAnalyseParser extends Parser {
	public static final String[] tokenNames = new String[] { "<invalid>",
			"<EOR>", "<DOWN>", "<UP>", "SEMICOLON", "MODIFIERS", "ID", "DOT",
			"STAR", "COMMA", "MODIFIER", "WS", "VISIBILITY", "BLOCK_BEGIN",
			"BLOCK_END", "PLUS", "MINUS", "SLASH", "EQUAL", "UNEQUAL",
			"ASSIGN", "LOGICAL_OR", "BIT_OR", "LOGICAL_AND", "BIT_AND", "NOT",
			"LT", "GT", "OPEN_RECT_BRACKET", "CLOSE_RECT_BRACKET",
			"OPEN_BRACKET", "CLOSE_BRACKER", "INT", "EXPONENT", "FLOAT",
			"LINEFEED", "COMMENT", "ESC_SEQ", "STRING", "CHAR", "HEX_DIGIT",
			"UNICODE_ESC", "OCTAL_ESC", "'package'", "'import'", "'class'",
			"'extends'", "'implemented'" };
	public static final int LT = 26;
	public static final int EXPONENT = 33;
	public static final int STAR = 8;
	public static final int BIT_AND = 24;
	public static final int OCTAL_ESC = 42;
	public static final int CHAR = 39;
	public static final int FLOAT = 34;
	public static final int NOT = 25;
	public static final int ID = 6;
	public static final int EOF = -1;
	public static final int CLOSE_RECT_BRACKET = 29;
	public static final int LOGICAL_AND = 23;
	public static final int MODIFIER = 10;
	public static final int LINEFEED = 35;
	public static final int MODIFIERS = 5;
	public static final int OPEN_BRACKET = 30;
	public static final int SLASH = 17;
	public static final int ESC_SEQ = 37;
	public static final int BIT_OR = 22;
	public static final int COMMA = 9;
	public static final int EQUAL = 18;
	public static final int BLOCK_END = 14;
	public static final int LOGICAL_OR = 21;
	public static final int PLUS = 15;
	public static final int DOT = 7;
	public static final int COMMENT = 36;
	public static final int BLOCK_BEGIN = 13;
	public static final int T__43 = 43;
	public static final int CLOSE_BRACKER = 31;
	public static final int T__46 = 46;
	public static final int VISIBILITY = 12;
	public static final int T__47 = 47;
	public static final int T__44 = 44;
	public static final int T__45 = 45;
	public static final int UNICODE_ESC = 41;
	public static final int HEX_DIGIT = 40;
	public static final int SEMICOLON = 4;
	public static final int INT = 32;
	public static final int MINUS = 16;
	public static final int UNEQUAL = 19;
	public static final int WS = 11;
	public static final int ASSIGN = 20;
	public static final int GT = 27;
	public static final int OPEN_RECT_BRACKET = 28;
	public static final int STRING = 38;

	// delegates
	// delegators

	public JavaAnalyseParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}

	public JavaAnalyseParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);

	}

	public String[] getTokenNames() {
		return JavaAnalyseParser.tokenNames;
	}

	public String getGrammarFileName() {
		return "/home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g";
	}

	private ANTLRJavaHelper helper = null;

	public JavaAnalyseParser(TokenStream stream, ANTLRJavaHelper helper) {
		this(stream);
		this.helper = helper;
	}

	// $ANTLR start "file"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:35:1:
	// file : package_decl ( import_decl )* ( class_decl ( constructor_decl |
	// method_decl )* )* ;
	public final void file() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:35:6:
			// ( package_decl ( import_decl )* ( class_decl ( constructor_decl |
			// method_decl )* )* )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:35:8:
			// package_decl ( import_decl )* ( class_decl ( constructor_decl |
			// method_decl )* )*
			{
				pushFollow(FOLLOW_package_decl_in_file46);
				package_decl();

				state._fsp--;

				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:35:21:
				// ( import_decl )*
				loop1: do {
					int alt1 = 2;
					int LA1_0 = input.LA(1);

					if ((LA1_0 == 44)) {
						alt1 = 1;
					}

					switch (alt1) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:35:21:
						// import_decl
					{
						pushFollow(FOLLOW_import_decl_in_file48);
						import_decl();

						state._fsp--;

					}
						break;

					default:
						break loop1;
					}
				} while (true);

				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:35:34:
				// ( class_decl ( constructor_decl | method_decl )* )*
				loop3: do {
					int alt3 = 2;
					int LA3_0 = input.LA(1);

					if ((LA3_0 == MODIFIERS)) {
						alt3 = 1;
					}

					switch (alt3) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:35:35:
						// class_decl ( constructor_decl | method_decl )*
					{
						pushFollow(FOLLOW_class_decl_in_file52);
						class_decl();

						state._fsp--;

						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:35:46:
						// ( constructor_decl | method_decl )*
						loop2: do {
							int alt2 = 3;
							int LA2_0 = input.LA(1);

							if ((LA2_0 == MODIFIERS)) {
								int LA2_2 = input.LA(2);

								if ((LA2_2 == ID)) {
									int LA2_3 = input.LA(3);

									if ((LA2_3 == OPEN_BRACKET)) {
										alt2 = 1;
									} else if ((LA2_3 == ID)) {
										alt2 = 2;
									}

								}

							}

							switch (alt2) {
							case 1:
								// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:35:47:
								// constructor_decl
							{
								pushFollow(FOLLOW_constructor_decl_in_file55);
								constructor_decl();

								state._fsp--;

							}
								break;
							case 2:
								// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:35:66:
								// method_decl
							{
								pushFollow(FOLLOW_method_decl_in_file59);
								method_decl();

								state._fsp--;

							}
								break;

							default:
								break loop2;
							}
						} while (true);

					}
						break;

					default:
						break loop3;
					}
				} while (true);

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "file"

	// $ANTLR start "package_decl"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:37:1:
	// package_decl : 'package' package_name SEMICOLON ;
	public final void package_decl() throws RecognitionException {
		JavaAnalyseParser.package_name_return package_name1 = null;

		try {
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:37:14:
			// ( 'package' package_name SEMICOLON )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:37:16:
			// 'package' package_name SEMICOLON
			{
				match(input, 43, FOLLOW_43_in_package_decl72);
				pushFollow(FOLLOW_package_name_in_package_decl74);
				package_name1 = package_name();

				state._fsp--;

				match(input, SEMICOLON, FOLLOW_SEMICOLON_in_package_decl76);
				helper.setPackageName((package_name1 != null ? input.toString(
						package_name1.start, package_name1.stop) : null));

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "package_decl"

	// $ANTLR start "import_decl"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:38:1:
	// import_decl : 'import' import_name SEMICOLON ;
	public final void import_decl() throws RecognitionException {
		JavaAnalyseParser.import_name_return import_name2 = null;

		try {
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:38:13:
			// ( 'import' import_name SEMICOLON )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:38:15:
			// 'import' import_name SEMICOLON
			{
				match(input, 44, FOLLOW_44_in_import_decl85);
				pushFollow(FOLLOW_import_name_in_import_decl88);
				import_name2 = import_name();

				state._fsp--;

				match(input, SEMICOLON, FOLLOW_SEMICOLON_in_import_decl90);
				helper.addImport((import_name2 != null ? input.toString(
						import_name2.start, import_name2.stop) : null));

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "import_decl"

	// $ANTLR start "class_decl"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:39:1:
	// class_decl : MODIFIERS 'class' class_name ( extended )? ( implemented )?
	// ;
	public final void class_decl() throws RecognitionException {
		Token MODIFIERS4 = null;
		JavaAnalyseParser.class_name_return class_name3 = null;

		JavaAnalyseParser.extended_return extended5 = null;

		JavaAnalyseParser.implemented_return implemented6 = null;

		try {
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:39:12:
			// ( MODIFIERS 'class' class_name ( extended )? ( implemented )? )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:39:14:
			// MODIFIERS 'class' class_name ( extended )? ( implemented )?
			{
				MODIFIERS4 = (Token) match(input, MODIFIERS,
						FOLLOW_MODIFIERS_in_class_decl99);
				match(input, 45, FOLLOW_45_in_class_decl101);
				pushFollow(FOLLOW_class_name_in_class_decl103);
				class_name3 = class_name();

				state._fsp--;

				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:39:43:
				// ( extended )?
				int alt4 = 2;
				int LA4_0 = input.LA(1);

				if ((LA4_0 == 46)) {
					alt4 = 1;
				}
				switch (alt4) {
				case 1:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:39:43:
					// extended
				{
					pushFollow(FOLLOW_extended_in_class_decl105);
					extended5 = extended();

					state._fsp--;

				}
					break;

				}

				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:39:53:
				// ( implemented )?
				int alt5 = 2;
				int LA5_0 = input.LA(1);

				if ((LA5_0 == 47)) {
					alt5 = 1;
				}
				switch (alt5) {
				case 1:
					// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:39:53:
					// implemented
				{
					pushFollow(FOLLOW_implemented_in_class_decl108);
					implemented6 = implemented();

					state._fsp--;

				}
					break;

				}

				helper.addClass((class_name3 != null ? input.toString(
						class_name3.start, class_name3.stop) : null),
						(MODIFIERS4 != null ? MODIFIERS4.getText() : null),
						(extended5 != null ? input.toString(extended5.start,
								extended5.stop) : null),
						(implemented6 != null ? input.toString(
								implemented6.start, implemented6.stop) : null));

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "class_decl"

	// $ANTLR start "method_decl"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:40:1:
	// method_decl : MODIFIERS ID ID '(' (~ ( ')' ) )* ')' ;
	public final void method_decl() throws RecognitionException {
		Token ID7 = null;
		Token MODIFIERS8 = null;

		try {
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:40:13:
			// ( MODIFIERS ID ID '(' (~ ( ')' ) )* ')' )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:40:15:
			// MODIFIERS ID ID '(' (~ ( ')' ) )* ')'
			{
				MODIFIERS8 = (Token) match(input, MODIFIERS,
						FOLLOW_MODIFIERS_in_method_decl118);
				ID7 = (Token) match(input, ID, FOLLOW_ID_in_method_decl120);
				match(input, ID, FOLLOW_ID_in_method_decl122);
				match(input, OPEN_BRACKET,
						FOLLOW_OPEN_BRACKET_in_method_decl124);
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:40:35:
				// (~ ( ')' ) )*
				loop6: do {
					int alt6 = 2;
					int LA6_0 = input.LA(1);

					if (((LA6_0 >= SEMICOLON && LA6_0 <= OPEN_BRACKET) || (LA6_0 >= INT && LA6_0 <= 47))) {
						alt6 = 1;
					}

					switch (alt6) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:40:35:
						// ~ ( ')' )
					{
						if ((input.LA(1) >= SEMICOLON && input.LA(1) <= OPEN_BRACKET)
								|| (input.LA(1) >= INT && input.LA(1) <= 47)) {
							input.consume();
							state.errorRecovery = false;
						} else {
							MismatchedSetException mse = new MismatchedSetException(
									null, input);
							throw mse;
						}

					}
						break;

					default:
						break loop6;
					}
				} while (true);

				match(input, CLOSE_BRACKER,
						FOLLOW_CLOSE_BRACKER_in_method_decl132);
				helper.addMethod((ID7 != null ? ID7.getText() : null),
						(MODIFIERS8 != null ? MODIFIERS8.getText() : null));

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "method_decl"

	// $ANTLR start "constructor_decl"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:41:1:
	// constructor_decl : MODIFIERS ID '(' (~ ( ')' ) )* ')' ;
	public final void constructor_decl() throws RecognitionException {
		Token ID9 = null;
		Token MODIFIERS10 = null;

		try {
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:41:17:
			// ( MODIFIERS ID '(' (~ ( ')' ) )* ')' )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:41:19:
			// MODIFIERS ID '(' (~ ( ')' ) )* ')'
			{
				MODIFIERS10 = (Token) match(input, MODIFIERS,
						FOLLOW_MODIFIERS_in_constructor_decl140);
				ID9 = (Token) match(input, ID, FOLLOW_ID_in_constructor_decl142);
				match(input, OPEN_BRACKET,
						FOLLOW_OPEN_BRACKET_in_constructor_decl144);
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:41:36:
				// (~ ( ')' ) )*
				loop7: do {
					int alt7 = 2;
					int LA7_0 = input.LA(1);

					if (((LA7_0 >= SEMICOLON && LA7_0 <= OPEN_BRACKET) || (LA7_0 >= INT && LA7_0 <= 47))) {
						alt7 = 1;
					}

					switch (alt7) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:41:36:
						// ~ ( ')' )
					{
						if ((input.LA(1) >= SEMICOLON && input.LA(1) <= OPEN_BRACKET)
								|| (input.LA(1) >= INT && input.LA(1) <= 47)) {
							input.consume();
							state.errorRecovery = false;
						} else {
							MismatchedSetException mse = new MismatchedSetException(
									null, input);
							throw mse;
						}

					}
						break;

					default:
						break loop7;
					}
				} while (true);

				match(input, CLOSE_BRACKER,
						FOLLOW_CLOSE_BRACKER_in_constructor_decl152);
				helper.addMethod((ID9 != null ? ID9.getText() : null),
						(MODIFIERS10 != null ? MODIFIERS10.getText() : null));

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "constructor_decl"

	public static class package_name_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "package_name"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:43:1:
	// package_name : ID ( DOT ID )* ;
	public final JavaAnalyseParser.package_name_return package_name()
			throws RecognitionException {
		JavaAnalyseParser.package_name_return retval = new JavaAnalyseParser.package_name_return();
		retval.start = input.LT(1);

		try {
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:43:14:
			// ( ID ( DOT ID )* )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:43:16:
			// ID ( DOT ID )*
			{
				match(input, ID, FOLLOW_ID_in_package_name162);
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:43:19:
				// ( DOT ID )*
				loop8: do {
					int alt8 = 2;
					int LA8_0 = input.LA(1);

					if ((LA8_0 == DOT)) {
						alt8 = 1;
					}

					switch (alt8) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:43:20:
						// DOT ID
					{
						match(input, DOT, FOLLOW_DOT_in_package_name165);
						match(input, ID, FOLLOW_ID_in_package_name167);

					}
						break;

					default:
						break loop8;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return retval;
	}

	// $ANTLR end "package_name"

	public static class import_name_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "import_name"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:44:1:
	// import_name : ( ID DOT )* ( ID | STAR ) ;
	public final JavaAnalyseParser.import_name_return import_name()
			throws RecognitionException {
		JavaAnalyseParser.import_name_return retval = new JavaAnalyseParser.import_name_return();
		retval.start = input.LT(1);

		try {
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:44:13:
			// ( ( ID DOT )* ( ID | STAR ) )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:44:15:
			// ( ID DOT )* ( ID | STAR )
			{
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:44:15:
				// ( ID DOT )*
				loop9: do {
					int alt9 = 2;
					int LA9_0 = input.LA(1);

					if ((LA9_0 == ID)) {
						int LA9_1 = input.LA(2);

						if ((LA9_1 == DOT)) {
							alt9 = 1;
						}

					}

					switch (alt9) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:44:16:
						// ID DOT
					{
						match(input, ID, FOLLOW_ID_in_import_name177);
						match(input, DOT, FOLLOW_DOT_in_import_name179);

					}
						break;

					default:
						break loop9;
					}
				} while (true);

				if (input.LA(1) == ID || input.LA(1) == STAR) {
					input.consume();
					state.errorRecovery = false;
				} else {
					MismatchedSetException mse = new MismatchedSetException(
							null, input);
					throw mse;
				}

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return retval;
	}

	// $ANTLR end "import_name"

	public static class class_name_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "class_name"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:45:1:
	// class_name : ( ID DOT )* ID ;
	public final JavaAnalyseParser.class_name_return class_name()
			throws RecognitionException {
		JavaAnalyseParser.class_name_return retval = new JavaAnalyseParser.class_name_return();
		retval.start = input.LT(1);

		try {
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:45:12:
			// ( ( ID DOT )* ID )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:45:14:
			// ( ID DOT )* ID
			{
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:45:14:
				// ( ID DOT )*
				loop10: do {
					int alt10 = 2;
					int LA10_0 = input.LA(1);

					if ((LA10_0 == ID)) {
						int LA10_1 = input.LA(2);

						if ((LA10_1 == DOT)) {
							alt10 = 1;
						}

					}

					switch (alt10) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:45:15:
						// ID DOT
					{
						match(input, ID, FOLLOW_ID_in_class_name195);
						match(input, DOT, FOLLOW_DOT_in_class_name197);

					}
						break;

					default:
						break loop10;
					}
				} while (true);

				match(input, ID, FOLLOW_ID_in_class_name201);

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return retval;
	}

	// $ANTLR end "class_name"

	public static class extended_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "extended"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:47:1:
	// extended : 'extends' ID ;
	public final JavaAnalyseParser.extended_return extended()
			throws RecognitionException {
		JavaAnalyseParser.extended_return retval = new JavaAnalyseParser.extended_return();
		retval.start = input.LT(1);

		try {
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:47:9:
			// ( 'extends' ID )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:47:11:
			// 'extends' ID
			{
				match(input, 46, FOLLOW_46_in_extended208);
				match(input, ID, FOLLOW_ID_in_extended210);

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return retval;
	}

	// $ANTLR end "extended"

	public static class implemented_return extends ParserRuleReturnScope {
	};

	// $ANTLR start "implemented"
	// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:48:1:
	// implemented : 'implemented' ID ( COMMA ID )* ;
	public final JavaAnalyseParser.implemented_return implemented()
			throws RecognitionException {
		JavaAnalyseParser.implemented_return retval = new JavaAnalyseParser.implemented_return();
		retval.start = input.LT(1);

		try {
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:48:12:
			// ( 'implemented' ID ( COMMA ID )* )
			// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:48:14:
			// 'implemented' ID ( COMMA ID )*
			{
				match(input, 47, FOLLOW_47_in_implemented216);
				match(input, ID, FOLLOW_ID_in_implemented218);
				// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:48:31:
				// ( COMMA ID )*
				loop11: do {
					int alt11 = 2;
					int LA11_0 = input.LA(1);

					if ((LA11_0 == COMMA)) {
						alt11 = 1;
					}

					switch (alt11) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnolgies_API/src/com/puresol/coding/antlr/JavaAnalyse.g:48:32:
						// COMMA ID
					{
						match(input, COMMA, FOLLOW_COMMA_in_implemented221);
						match(input, ID, FOLLOW_ID_in_implemented223);

					}
						break;

					default:
						break loop11;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return retval;
	}

	// $ANTLR end "implemented"

	// Delegated rules

	public static final BitSet FOLLOW_package_decl_in_file46 = new BitSet(
			new long[] { 0x0000100000000022L });
	public static final BitSet FOLLOW_import_decl_in_file48 = new BitSet(
			new long[] { 0x0000100000000022L });
	public static final BitSet FOLLOW_class_decl_in_file52 = new BitSet(
			new long[] { 0x0000000000000022L });
	public static final BitSet FOLLOW_constructor_decl_in_file55 = new BitSet(
			new long[] { 0x0000000000000022L });
	public static final BitSet FOLLOW_method_decl_in_file59 = new BitSet(
			new long[] { 0x0000000000000022L });
	public static final BitSet FOLLOW_43_in_package_decl72 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_package_name_in_package_decl74 = new BitSet(
			new long[] { 0x0000000000000010L });
	public static final BitSet FOLLOW_SEMICOLON_in_package_decl76 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_44_in_import_decl85 = new BitSet(
			new long[] { 0x0000000000000140L });
	public static final BitSet FOLLOW_import_name_in_import_decl88 = new BitSet(
			new long[] { 0x0000000000000010L });
	public static final BitSet FOLLOW_SEMICOLON_in_import_decl90 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_MODIFIERS_in_class_decl99 = new BitSet(
			new long[] { 0x0000200000000000L });
	public static final BitSet FOLLOW_45_in_class_decl101 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_class_name_in_class_decl103 = new BitSet(
			new long[] { 0x0000C00000000002L });
	public static final BitSet FOLLOW_extended_in_class_decl105 = new BitSet(
			new long[] { 0x0000800000000002L });
	public static final BitSet FOLLOW_implemented_in_class_decl108 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_MODIFIERS_in_method_decl118 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_ID_in_method_decl120 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_ID_in_method_decl122 = new BitSet(
			new long[] { 0x0000000040000000L });
	public static final BitSet FOLLOW_OPEN_BRACKET_in_method_decl124 = new BitSet(
			new long[] { 0x0000FFFFFFFFFFF0L });
	public static final BitSet FOLLOW_set_in_method_decl126 = new BitSet(
			new long[] { 0x0000FFFFFFFFFFF0L });
	public static final BitSet FOLLOW_CLOSE_BRACKER_in_method_decl132 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_MODIFIERS_in_constructor_decl140 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_ID_in_constructor_decl142 = new BitSet(
			new long[] { 0x0000000040000000L });
	public static final BitSet FOLLOW_OPEN_BRACKET_in_constructor_decl144 = new BitSet(
			new long[] { 0x0000FFFFFFFFFFF0L });
	public static final BitSet FOLLOW_set_in_constructor_decl146 = new BitSet(
			new long[] { 0x0000FFFFFFFFFFF0L });
	public static final BitSet FOLLOW_CLOSE_BRACKER_in_constructor_decl152 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ID_in_package_name162 = new BitSet(
			new long[] { 0x0000000000000082L });
	public static final BitSet FOLLOW_DOT_in_package_name165 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_ID_in_package_name167 = new BitSet(
			new long[] { 0x0000000000000082L });
	public static final BitSet FOLLOW_ID_in_import_name177 = new BitSet(
			new long[] { 0x0000000000000080L });
	public static final BitSet FOLLOW_DOT_in_import_name179 = new BitSet(
			new long[] { 0x0000000000000140L });
	public static final BitSet FOLLOW_set_in_import_name183 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ID_in_class_name195 = new BitSet(
			new long[] { 0x0000000000000080L });
	public static final BitSet FOLLOW_DOT_in_class_name197 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_ID_in_class_name201 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_46_in_extended208 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_ID_in_extended210 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_47_in_implemented216 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_ID_in_implemented218 = new BitSet(
			new long[] { 0x0000000000000202L });
	public static final BitSet FOLLOW_COMMA_in_implemented221 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_ID_in_implemented223 = new BitSet(
			new long[] { 0x0000000000000202L });

}