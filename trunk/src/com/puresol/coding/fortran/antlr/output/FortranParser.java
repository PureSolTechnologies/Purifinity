// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g 2010-01-09 18:43:02

package com.puresol.coding.fortran.antlr.output;

import com.puresol.coding.ParserHelper;
import java.io.File;

import org.antlr.runtime.*;

public class FortranParser extends Parser {
	public static final String[] tokenNames = new String[] { "<invalid>",
			"<EOR>", "<DOWN>", "<UP>", "P", "R", "O", "G", "A", "M", "PROGRAM",
			"S", "U", "B", "T", "I", "N", "E", "SUBROUTINE", "F", "C",
			"FUNCTION", "INTEGER", "L", "REAL", "DOUBLE", "WS", "COMPLEX",
			"DOUBLE_COMPLEX", "DOUBLE_PRECISION", "D", "X", "H", "CHARACTER",
			"LOGICAL", "IMPLICIT", "NONE", "DATA", "ALLOCATE", "PARAMETER",
			"IF", "THEN", "ELSE", "DO", "W", "WHILE", "ENDDO", "END", "CALL",
			"GOTO", "RETURN", "CONTINUE", "EXTERNAL", "INTRINSIC", "DOLLAR",
			"COMMA", "LPAREN", "RPAREN", "COLON", "ASSIGN", "MINUS", "PLUS",
			"DIV", "POWER", "STAR", "LNOT", "LAND", "LOR", "Q", "V", "EQV",
			"NEQV", "XOR", "EOR", "LT", "LE", "GT", "GE", "NE", "EQ", "TRUE",
			"FALSE", "ID", "INT_CONST", "HEX_DIGIT", "HEX_CONST", "EXPONENT",
			"FLOAT_CONST", "LINEFEED", "COMMENT", "NOTNL", "ESC_SEQ",
			"STRING_CONST", "UNICODE_ESC", "OCTAL_ESC", "J", "K", "Y", "Z" };
	public static final int LOR = 67;
	public static final int FUNCTION = 21;
	public static final int EXTERNAL = 52;
	public static final int LT = 74;
	public static final int EXPONENT = 86;
	public static final int STAR = 64;
	public static final int ALLOCATE = 38;
	public static final int WHILE = 45;
	public static final int OCTAL_ESC = 94;
	public static final int DO = 43;
	public static final int COMPLEX = 27;
	public static final int EOF = -1;
	public static final int DOUBLE_COMPLEX = 28;
	public static final int CHARACTER = 33;
	public static final int RPAREN = 57;
	public static final int LNOT = 65;
	public static final int HEX_CONST = 85;
	public static final int STRING_CONST = 92;
	public static final int EOR = 73;
	public static final int PARAMETER = 39;
	public static final int RETURN = 50;
	public static final int DOUBLE = 25;
	public static final int EQ = 79;
	public static final int GOTO = 49;
	public static final int COMMENT = 89;
	public static final int NE = 78;
	public static final int D = 30;
	public static final int E = 17;
	public static final int GE = 77;
	public static final int F = 19;
	public static final int G = 7;
	public static final int A = 8;
	public static final int B = 13;
	public static final int C = 20;
	public static final int L = 23;
	public static final int M = 9;
	public static final int N = 16;
	public static final int O = 6;
	public static final int H = 32;
	public static final int I = 15;
	public static final int J = 95;
	public static final int ELSE = 42;
	public static final int K = 96;
	public static final int U = 12;
	public static final int T = 14;
	public static final int W = 44;
	public static final int POWER = 63;
	public static final int V = 69;
	public static final int NEQV = 71;
	public static final int Q = 68;
	public static final int P = 4;
	public static final int S = 11;
	public static final int R = 5;
	public static final int Y = 97;
	public static final int X = 31;
	public static final int Z = 98;
	public static final int REAL = 24;
	public static final int WS = 26;
	public static final int NONE = 36;
	public static final int SUBROUTINE = 18;
	public static final int GT = 76;
	public static final int INTRINSIC = 53;
	public static final int CALL = 48;
	public static final int END = 47;
	public static final int FALSE = 81;
	public static final int LAND = 66;
	public static final int DOLLAR = 54;
	public static final int ID = 82;
	public static final int LPAREN = 56;
	public static final int FLOAT_CONST = 87;
	public static final int IF = 40;
	public static final int LINEFEED = 88;
	public static final int ESC_SEQ = 91;
	public static final int THEN = 41;
	public static final int CONTINUE = 51;
	public static final int COMMA = 55;
	public static final int DOUBLE_PRECISION = 29;
	public static final int PLUS = 61;
	public static final int ENDDO = 46;
	public static final int INTEGER = 22;
	public static final int XOR = 72;
	public static final int IMPLICIT = 35;
	public static final int UNICODE_ESC = 93;
	public static final int INT_CONST = 83;
	public static final int HEX_DIGIT = 84;
	public static final int MINUS = 60;
	public static final int TRUE = 80;
	public static final int NOTNL = 90;
	public static final int COLON = 58;
	public static final int ASSIGN = 59;
	public static final int EQV = 70;
	public static final int PROGRAM = 10;
	public static final int LOGICAL = 34;
	public static final int DIV = 62;
	public static final int LE = 75;
	public static final int DATA = 37;

	// delegates
	// delegators

	public FortranParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}

	public FortranParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);

	}

	public String[] getTokenNames() {
		return FortranParser.tokenNames;
	}

	public String getGrammarFileName() {
		return "/home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g";
	}

	private ParserHelper helper = null;

	public FortranParser(File file, TokenStream input) {
		this(input, new RecognizerSharedState());
		helper = new ParserHelper(file, this);
	}

	public ParserHelper getParserHelper() {
		return helper;
	}

	// $ANTLR start "file"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:29:1:
	// file : ( program_file | subroutine_file | (~ WS | WS )* );
	public final void file() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:30:3:
			// ( program_file | subroutine_file | (~ WS | WS )* )
			int alt2 = 3;
			alt2 = dfa2.predict(input);
			switch (alt2) {
			case 1:
				// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:31:3:
				// program_file
			{
				pushFollow(FOLLOW_program_file_in_file52);
				program_file();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 2:
				// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:33:3:
				// subroutine_file
			{
				pushFollow(FOLLOW_subroutine_file_in_file60);
				subroutine_file();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 3:
				// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:35:3:
				// (~ WS | WS )*
			{
				// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:35:3:
				// (~ WS | WS )*
				loop1: do {
					int alt1 = 2;
					int LA1_0 = input.LA(1);

					if (((LA1_0 >= P && LA1_0 <= Z))) {
						alt1 = 1;
					}

					switch (alt1) {
					case 1:
						// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:
					{
						if ((input.LA(1) >= P && input.LA(1) <= Z)) {
							input.consume();
							state.errorRecovery = false;
							state.failed = false;
						} else {
							if (state.backtracking > 0) {
								state.failed = true;
								return;
							}
							MismatchedSetException mse = new MismatchedSetException(
									null, input);
							throw mse;
						}

					}
						break;

					default:
						break loop1;
					}
				} while (true);

			}
				break;

			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "file"

	// $ANTLR start "program_file"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:41:1:
	// program_file : program id (~ WS | WS ) end ;
	public final void program_file() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:42:3:
			// ( program id (~ WS | WS ) end )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:43:3:
			// program id (~ WS | WS ) end
			{
				pushFollow(FOLLOW_program_in_program_file103);
				program();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_id_in_program_file105);
				id();

				state._fsp--;
				if (state.failed)
					return;
				if ((input.LA(1) >= P && input.LA(1) <= Z)) {
					input.consume();
					state.errorRecovery = false;
					state.failed = false;
				} else {
					if (state.backtracking > 0) {
						state.failed = true;
						return;
					}
					MismatchedSetException mse = new MismatchedSetException(
							null, input);
					throw mse;
				}

				pushFollow(FOLLOW_end_in_program_file132);
				end();

				state._fsp--;
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "program_file"

	// $ANTLR start "subroutine_file"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:51:1:
	// subroutine_file : subroutine id lparen ( id ( comma id )* )? rparen (~ WS
	// | WS ) end ;
	public final void subroutine_file() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:52:2:
			// ( subroutine id lparen ( id ( comma id )* )? rparen (~ WS | WS )
			// end )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:52:4:
			// subroutine id lparen ( id ( comma id )* )? rparen (~ WS | WS )
			// end
			{
				pushFollow(FOLLOW_subroutine_in_subroutine_file144);
				subroutine();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_id_in_subroutine_file146);
				id();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_lparen_in_subroutine_file148);
				lparen();

				state._fsp--;
				if (state.failed)
					return;
				// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:52:25:
				// ( id ( comma id )* )?
				int alt4 = 2;
				int LA4_0 = input.LA(1);

				if ((LA4_0 == ID)) {
					alt4 = 1;
				}
				switch (alt4) {
				case 1:
					// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:52:26:
					// id ( comma id )*
				{
					pushFollow(FOLLOW_id_in_subroutine_file151);
					id();

					state._fsp--;
					if (state.failed)
						return;
					// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:52:29:
					// ( comma id )*
					loop3: do {
						int alt3 = 2;
						int LA3_0 = input.LA(1);

						if ((LA3_0 == COMMA)) {
							alt3 = 1;
						}

						switch (alt3) {
						case 1:
							// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:52:30:
							// comma id
						{
							pushFollow(FOLLOW_comma_in_subroutine_file154);
							comma();

							state._fsp--;
							if (state.failed)
								return;
							pushFollow(FOLLOW_id_in_subroutine_file156);
							id();

							state._fsp--;
							if (state.failed)
								return;

						}
							break;

						default:
							break loop3;
						}
					} while (true);

				}
					break;

				}

				pushFollow(FOLLOW_rparen_in_subroutine_file162);
				rparen();

				state._fsp--;
				if (state.failed)
					return;
				if ((input.LA(1) >= P && input.LA(1) <= Z)) {
					input.consume();
					state.errorRecovery = false;
					state.failed = false;
				} else {
					if (state.backtracking > 0) {
						state.failed = true;
						return;
					}
					MismatchedSetException mse = new MismatchedSetException(
							null, input);
					throw mse;
				}

				pushFollow(FOLLOW_end_in_subroutine_file175);
				end();

				state._fsp--;
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "subroutine_file"

	// $ANTLR start "implicit_none"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:57:1:
	// implicit_none : implicit none ;
	public final void implicit_none() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:58:3:
			// ( implicit none )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:59:3:
			// implicit none
			{
				pushFollow(FOLLOW_implicit_in_implicit_none189);
				implicit();

				state._fsp--;
				if (state.failed)
					return;
				pushFollow(FOLLOW_none_in_implicit_none191);
				none();

				state._fsp--;
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "implicit_none"

	// $ANTLR start "logical_constants"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:62:1:
	// logical_constants : ( true_ | false_ );
	public final void logical_constants() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:63:3:
			// ( true_ | false_ )
			int alt5 = 2;
			int LA5_0 = input.LA(1);

			if ((LA5_0 == TRUE)) {
				alt5 = 1;
			} else if ((LA5_0 == FALSE)) {
				alt5 = 2;
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return;
				}
				NoViableAltException nvae = new NoViableAltException("", 5, 0,
						input);

				throw nvae;
			}
			switch (alt5) {
			case 1:
				// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:64:3:
				// true_
			{
				pushFollow(FOLLOW_true__in_logical_constants206);
				true_();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;
			case 2:
				// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:65:5:
				// false_
			{
				pushFollow(FOLLOW_false__in_logical_constants212);
				false_();

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
		}
		return;
	}

	// $ANTLR end "logical_constants"

	// $ANTLR start "program"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:72:1:
	// program : PROGRAM ;
	public final void program() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:73:3:
			// ( PROGRAM )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:74:3:
			// PROGRAM
			{
				match(input, PROGRAM, FOLLOW_PROGRAM_in_program231);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "program"

	// $ANTLR start "subroutine"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:77:1:
	// subroutine : SUBROUTINE ;
	public final void subroutine() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:78:3:
			// ( SUBROUTINE )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:79:3:
			// SUBROUTINE
			{
				match(input, SUBROUTINE, FOLLOW_SUBROUTINE_in_subroutine246);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "subroutine"

	// $ANTLR start "function"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:82:1:
	// function : FUNCTION ;
	public final void function() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:83:3:
			// ( FUNCTION )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:84:3:
			// FUNCTION
			{
				match(input, FUNCTION, FOLLOW_FUNCTION_in_function261);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "function"

	// $ANTLR start "integer"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:87:1:
	// integer : INTEGER ;
	public final void integer() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:88:3:
			// ( INTEGER )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:89:3:
			// INTEGER
			{
				match(input, INTEGER, FOLLOW_INTEGER_in_integer276);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "integer"

	// $ANTLR start "real"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:92:1:
	// real : REAL ;
	public final void real() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:93:3:
			// ( REAL )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:94:3:
			// REAL
			{
				match(input, REAL, FOLLOW_REAL_in_real291);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "real"

	// $ANTLR start "double_precision"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:97:1:
	// double_precision : DOUBLE_PRECISION ;
	public final void double_precision() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:98:3:
			// ( DOUBLE_PRECISION )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:99:3:
			// DOUBLE_PRECISION
			{
				match(input, DOUBLE_PRECISION,
						FOLLOW_DOUBLE_PRECISION_in_double_precision306);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "double_precision"

	// $ANTLR start "complex"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:102:1:
	// complex : COMPLEX ;
	public final void complex() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:103:3:
			// ( COMPLEX )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:104:3:
			// COMPLEX
			{
				match(input, COMPLEX, FOLLOW_COMPLEX_in_complex321);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "complex"

	// $ANTLR start "character"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:107:1:
	// character : CHARACTER ;
	public final void character() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:108:3:
			// ( CHARACTER )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:109:3:
			// CHARACTER
			{
				match(input, CHARACTER, FOLLOW_CHARACTER_in_character336);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "character"

	// $ANTLR start "logical"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:112:1:
	// logical : LOGICAL ;
	public final void logical() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:113:3:
			// ( LOGICAL )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:114:3:
			// LOGICAL
			{
				match(input, LOGICAL, FOLLOW_LOGICAL_in_logical351);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "logical"

	// $ANTLR start "implicit"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:117:1:
	// implicit : IMPLICIT ;
	public final void implicit() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:118:3:
			// ( IMPLICIT )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:119:3:
			// IMPLICIT
			{
				match(input, IMPLICIT, FOLLOW_IMPLICIT_in_implicit366);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "implicit"

	// $ANTLR start "none"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:122:1:
	// none : NONE ;
	public final void none() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:123:3:
			// ( NONE )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:124:3:
			// NONE
			{
				match(input, NONE, FOLLOW_NONE_in_none381);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "none"

	// $ANTLR start "data"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:127:1:
	// data : DATA ;
	public final void data() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:128:3:
			// ( DATA )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:129:3:
			// DATA
			{
				match(input, DATA, FOLLOW_DATA_in_data396);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "data"

	// $ANTLR start "allocate"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:132:1:
	// allocate : ALLOCATE ;
	public final void allocate() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:133:3:
			// ( ALLOCATE )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:134:3:
			// ALLOCATE
			{
				match(input, ALLOCATE, FOLLOW_ALLOCATE_in_allocate411);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "allocate"

	// $ANTLR start "parameter"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:137:1:
	// parameter : PARAMETER ;
	public final void parameter() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:138:3:
			// ( PARAMETER )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:139:3:
			// PARAMETER
			{
				match(input, PARAMETER, FOLLOW_PARAMETER_in_parameter426);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "parameter"

	// $ANTLR start "if_"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:142:1:
	// if_ : IF ;
	public final void if_() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:143:3:
			// ( IF )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:144:3:
			// IF
			{
				match(input, IF, FOLLOW_IF_in_if_441);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "if_"

	// $ANTLR start "then_"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:147:1:
	// then_ : THEN ;
	public final void then_() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:148:3:
			// ( THEN )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:149:3:
			// THEN
			{
				match(input, THEN, FOLLOW_THEN_in_then_456);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "then_"

	// $ANTLR start "else_"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:152:1:
	// else_ : ELSE ;
	public final void else_() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:153:3:
			// ( ELSE )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:154:3:
			// ELSE
			{
				match(input, ELSE, FOLLOW_ELSE_in_else_471);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "else_"

	// $ANTLR start "do_"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:157:1:
	// do_ : DO ;
	public final void do_() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:158:3:
			// ( DO )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:159:3:
			// DO
			{
				match(input, DO, FOLLOW_DO_in_do_486);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "do_"

	// $ANTLR start "while_"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:162:1:
	// while_ : WHILE ;
	public final void while_() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:163:3:
			// ( WHILE )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:164:3:
			// WHILE
			{
				match(input, WHILE, FOLLOW_WHILE_in_while_501);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "while_"

	// $ANTLR start "enddo"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:167:1:
	// enddo : ENDDO ;
	public final void enddo() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:168:3:
			// ( ENDDO )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:169:3:
			// ENDDO
			{
				match(input, ENDDO, FOLLOW_ENDDO_in_enddo516);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "enddo"

	// $ANTLR start "end"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:172:1:
	// end : END ;
	public final void end() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:173:3:
			// ( END )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:174:3:
			// END
			{
				match(input, END, FOLLOW_END_in_end531);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "end"

	// $ANTLR start "call_"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:177:1:
	// call_ : CALL ;
	public final void call_() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:178:3:
			// ( CALL )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:179:3:
			// CALL
			{
				match(input, CALL, FOLLOW_CALL_in_call_546);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "call_"

	// $ANTLR start "goto_"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:182:1:
	// goto_ : GOTO ;
	public final void goto_() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:183:3:
			// ( GOTO )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:184:3:
			// GOTO
			{
				match(input, GOTO, FOLLOW_GOTO_in_goto_561);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "goto_"

	// $ANTLR start "return_"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:187:1:
	// return_ : RETURN ;
	public final void return_() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:188:3:
			// ( RETURN )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:189:3:
			// RETURN
			{
				match(input, RETURN, FOLLOW_RETURN_in_return_576);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "return_"

	// $ANTLR start "continue_"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:192:1:
	// continue_ : CONTINUE ;
	public final void continue_() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:193:3:
			// ( CONTINUE )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:194:3:
			// CONTINUE
			{
				match(input, CONTINUE, FOLLOW_CONTINUE_in_continue_591);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "continue_"

	// $ANTLR start "external"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:197:1:
	// external : EXTERNAL ;
	public final void external() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:198:3:
			// ( EXTERNAL )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:199:3:
			// EXTERNAL
			{
				match(input, EXTERNAL, FOLLOW_EXTERNAL_in_external606);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "external"

	// $ANTLR start "intrincis"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:202:1:
	// intrincis : INTRINSIC ;
	public final void intrincis() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:203:3:
			// ( INTRINSIC )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:204:3:
			// INTRINSIC
			{
				match(input, INTRINSIC, FOLLOW_INTRINSIC_in_intrincis621);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "intrincis"

	// $ANTLR start "dollar"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:207:1:
	// dollar : DOLLAR ;
	public final void dollar() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:208:3:
			// ( DOLLAR )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:209:3:
			// DOLLAR
			{
				match(input, DOLLAR, FOLLOW_DOLLAR_in_dollar636);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "dollar"

	// $ANTLR start "comma"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:212:1:
	// comma : COMMA ;
	public final void comma() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:213:3:
			// ( COMMA )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:214:3:
			// COMMA
			{
				match(input, COMMA, FOLLOW_COMMA_in_comma651);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "comma"

	// $ANTLR start "lparen"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:217:1:
	// lparen : LPAREN ;
	public final void lparen() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:218:3:
			// ( LPAREN )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:219:3:
			// LPAREN
			{
				match(input, LPAREN, FOLLOW_LPAREN_in_lparen666);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "lparen"

	// $ANTLR start "rparen"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:222:1:
	// rparen : RPAREN ;
	public final void rparen() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:223:3:
			// ( RPAREN )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:224:3:
			// RPAREN
			{
				match(input, RPAREN, FOLLOW_RPAREN_in_rparen681);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "rparen"

	// $ANTLR start "colon"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:227:1:
	// colon : COLON ;
	public final void colon() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:228:3:
			// ( COLON )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:229:3:
			// COLON
			{
				match(input, COLON, FOLLOW_COLON_in_colon696);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "colon"

	// $ANTLR start "assign"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:232:1:
	// assign : ASSIGN ;
	public final void assign() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:233:3:
			// ( ASSIGN )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:234:3:
			// ASSIGN
			{
				match(input, ASSIGN, FOLLOW_ASSIGN_in_assign711);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "assign"

	// $ANTLR start "minus"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:237:1:
	// minus : MINUS ;
	public final void minus() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:238:3:
			// ( MINUS )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:239:3:
			// MINUS
			{
				match(input, MINUS, FOLLOW_MINUS_in_minus726);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "minus"

	// $ANTLR start "plus"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:242:1:
	// plus : PLUS ;
	public final void plus() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:243:3:
			// ( PLUS )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:244:3:
			// PLUS
			{
				match(input, PLUS, FOLLOW_PLUS_in_plus741);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "plus"

	// $ANTLR start "div"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:247:1:
	// div : DIV ;
	public final void div() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:248:3:
			// ( DIV )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:249:3:
			// DIV
			{
				match(input, DIV, FOLLOW_DIV_in_div756);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "div"

	// $ANTLR start "power"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:252:1:
	// power : POWER ;
	public final void power() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:253:3:
			// ( POWER )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:254:3:
			// POWER
			{
				match(input, POWER, FOLLOW_POWER_in_power771);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "power"

	// $ANTLR start "star"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:257:1:
	// star : STAR ;
	public final void star() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:258:3:
			// ( STAR )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:259:3:
			// STAR
			{
				match(input, STAR, FOLLOW_STAR_in_star786);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "star"

	// $ANTLR start "lnot"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:262:1:
	// lnot : LNOT ;
	public final void lnot() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:263:3:
			// ( LNOT )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:264:3:
			// LNOT
			{
				match(input, LNOT, FOLLOW_LNOT_in_lnot801);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "lnot"

	// $ANTLR start "land"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:267:1:
	// land : LAND ;
	public final void land() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:268:3:
			// ( LAND )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:269:3:
			// LAND
			{
				match(input, LAND, FOLLOW_LAND_in_land816);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "land"

	// $ANTLR start "lor"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:272:1:
	// lor : LOR ;
	public final void lor() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:273:3:
			// ( LOR )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:274:3:
			// LOR
			{
				match(input, LOR, FOLLOW_LOR_in_lor831);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "lor"

	// $ANTLR start "eqv"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:277:1:
	// eqv : EQV ;
	public final void eqv() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:278:3:
			// ( EQV )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:279:3:
			// EQV
			{
				match(input, EQV, FOLLOW_EQV_in_eqv846);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "eqv"

	// $ANTLR start "neqv"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:282:1:
	// neqv : NEQV ;
	public final void neqv() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:283:3:
			// ( NEQV )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:284:3:
			// NEQV
			{
				match(input, NEQV, FOLLOW_NEQV_in_neqv861);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "neqv"

	// $ANTLR start "xor"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:287:1:
	// xor : XOR ;
	public final void xor() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:288:3:
			// ( XOR )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:289:3:
			// XOR
			{
				match(input, XOR, FOLLOW_XOR_in_xor876);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "xor"

	// $ANTLR start "eor"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:292:1:
	// eor : EOR ;
	public final void eor() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:293:3:
			// ( EOR )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:294:3:
			// EOR
			{
				match(input, EOR, FOLLOW_EOR_in_eor891);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "eor"

	// $ANTLR start "lt"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:297:1:
	// lt : LT ;
	public final void lt() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:298:3:
			// ( LT )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:299:3:
			// LT
			{
				match(input, LT, FOLLOW_LT_in_lt906);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "lt"

	// $ANTLR start "le"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:302:1:
	// le : LE ;
	public final void le() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:303:3:
			// ( LE )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:304:3:
			// LE
			{
				match(input, LE, FOLLOW_LE_in_le921);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "le"

	// $ANTLR start "gt"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:307:1:
	// gt : GT ;
	public final void gt() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:308:3:
			// ( GT )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:309:3:
			// GT
			{
				match(input, GT, FOLLOW_GT_in_gt936);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "gt"

	// $ANTLR start "ge"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:312:1:
	// ge : GE ;
	public final void ge() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:313:3:
			// ( GE )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:314:3:
			// GE
			{
				match(input, GE, FOLLOW_GE_in_ge951);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "ge"

	// $ANTLR start "ne"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:317:1:
	// ne : NE ;
	public final void ne() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:318:3:
			// ( NE )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:319:3:
			// NE
			{
				match(input, NE, FOLLOW_NE_in_ne966);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "ne"

	// $ANTLR start "eq"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:322:1:
	// eq : EQ ;
	public final void eq() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:323:3:
			// ( EQ )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:324:3:
			// EQ
			{
				match(input, EQ, FOLLOW_EQ_in_eq981);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "eq"

	// $ANTLR start "true_"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:327:1:
	// true_ : TRUE ;
	public final void true_() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:328:3:
			// ( TRUE )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:329:3:
			// TRUE
			{
				match(input, TRUE, FOLLOW_TRUE_in_true_996);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "true_"

	// $ANTLR start "false_"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:332:1:
	// false_ : FALSE ;
	public final void false_() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:333:3:
			// ( FALSE )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:334:3:
			// FALSE
			{
				match(input, FALSE, FOLLOW_FALSE_in_false_1011);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "false_"

	// $ANTLR start "id"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:337:1:
	// id : ID ;
	public final void id() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:338:3:
			// ( ID )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:339:3:
			// ID
			{
				match(input, ID, FOLLOW_ID_in_id1026);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "id"

	// $ANTLR start "int_const"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:342:1:
	// int_const : INT_CONST ;
	public final void int_const() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:343:3:
			// ( INT_CONST )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:344:3:
			// INT_CONST
			{
				match(input, INT_CONST, FOLLOW_INT_CONST_in_int_const1041);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "int_const"

	// $ANTLR start "hex_const"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:347:1:
	// hex_const : HEX_CONST ;
	public final void hex_const() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:348:3:
			// ( HEX_CONST )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:349:3:
			// HEX_CONST
			{
				match(input, HEX_CONST, FOLLOW_HEX_CONST_in_hex_const1056);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "hex_const"

	// $ANTLR start "float_const"
	// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:352:1:
	// float_const : FLOAT_CONST ;
	public final void float_const() throws RecognitionException {
		try {
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:353:3:
			// ( FLOAT_CONST )
			// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:354:3:
			// FLOAT_CONST
			{
				match(input, FLOAT_CONST, FOLLOW_FLOAT_CONST_in_float_const1071);
				if (state.failed)
					return;

			}

		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
		} finally {
		}
		return;
	}

	// $ANTLR end "float_const"

	// $ANTLR start synpred1_FortranParser
	public final void synpred1_FortranParser_fragment()
			throws RecognitionException {
		// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:31:3:
		// ( program_file )
		// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:31:3:
		// program_file
		{
			pushFollow(FOLLOW_program_file_in_synpred1_FortranParser52);
			program_file();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred1_FortranParser

	// $ANTLR start synpred2_FortranParser
	public final void synpred2_FortranParser_fragment()
			throws RecognitionException {
		// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:33:3:
		// ( subroutine_file )
		// /home/ludwig/workspace/PureSolTechnologies_API/src/com/puresol/coding/fortran/antlr/FortranParser.g:33:3:
		// subroutine_file
		{
			pushFollow(FOLLOW_subroutine_file_in_synpred2_FortranParser60);
			subroutine_file();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred2_FortranParser

	// Delegated rules

	public final boolean synpred2_FortranParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred2_FortranParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred1_FortranParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred1_FortranParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	protected DFA2 dfa2 = new DFA2(this);
	static final String DFA2_eotS = "\21\uffff";
	static final String DFA2_eofS = "\3\3\1\uffff\4\3\1\uffff\2\3\1\uffff\3\3\2\uffff";
	static final String DFA2_minS = "\3\4\1\uffff\4\4\1\0\2\4\1\uffff\3\4\1\0\1\uffff";
	static final String DFA2_maxS = "\3\142\1\uffff\4\142\1\0\2\142\1\uffff\3\142\1\0\1\uffff";
	static final String DFA2_acceptS = "\3\uffff\1\3\7\uffff\1\1\4\uffff\1\2";
	static final String DFA2_specialS = "\10\uffff\1\1\6\uffff\1\0\1\uffff}>";
	static final String[] DFA2_transitionS = { "\6\3\1\1\7\3\1\2\120\3",
			"\116\3\1\4\20\3", "\116\3\1\5\20\3", "", "\137\6",
			"\64\3\1\7\52\3", "\53\3\1\10\63\3", "\65\3\1\12\30\3\1\11\20\3",
			"\1\uffff", "\63\3\1\14\1\3\1\12\51\3", "\137\15", "",
			"\116\3\1\16\20\3", "\53\3\1\17\63\3", "\63\3\1\14\1\3\1\12\51\3",
			"\1\uffff", "" };

	static final short[] DFA2_eot = DFA.unpackEncodedString(DFA2_eotS);
	static final short[] DFA2_eof = DFA.unpackEncodedString(DFA2_eofS);
	static final char[] DFA2_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA2_minS);
	static final char[] DFA2_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA2_maxS);
	static final short[] DFA2_accept = DFA.unpackEncodedString(DFA2_acceptS);
	static final short[] DFA2_special = DFA.unpackEncodedString(DFA2_specialS);
	static final short[][] DFA2_transition;

	static {
		int numStates = DFA2_transitionS.length;
		DFA2_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
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
			return "29:1: file : ( program_file | subroutine_file | (~ WS | WS )* );";
		}

		public int specialStateTransition(int s, IntStream _input)
				throws NoViableAltException {
			TokenStream input = (TokenStream) _input;
			int _s = s;
			switch (s) {
			case 0:
				int index2_15 = input.index();
				input.rewind();
				s = -1;
				if ((synpred2_FortranParser())) {
					s = 16;
				}

				else if ((true)) {
					s = 3;
				}

				input.seek(index2_15);
				if (s >= 0)
					return s;
				break;
			case 1:
				int index2_8 = input.index();
				input.rewind();
				s = -1;
				if ((synpred1_FortranParser())) {
					s = 11;
				}

				else if ((true)) {
					s = 3;
				}

				input.seek(index2_8);
				if (s >= 0)
					return s;
				break;
			}
			if (state.backtracking > 0) {
				state.failed = true;
				return -1;
			}
			NoViableAltException nvae = new NoViableAltException(
					getDescription(), 2, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	public static final BitSet FOLLOW_program_file_in_file52 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_subroutine_file_in_file60 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_set_in_file68 = new BitSet(new long[] {
			0xFFFFFFFFFFFFFFF2L, 0x00000007FFFFFFFFL });
	public static final BitSet FOLLOW_program_in_program_file103 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000040000L });
	public static final BitSet FOLLOW_id_in_program_file105 = new BitSet(
			new long[] { 0xFFFFFFFFFFFFFFF0L, 0x00000007FFFFFFFFL });
	public static final BitSet FOLLOW_set_in_program_file109 = new BitSet(
			new long[] { 0x0000800000000000L });
	public static final BitSet FOLLOW_end_in_program_file132 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_subroutine_in_subroutine_file144 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000040000L });
	public static final BitSet FOLLOW_id_in_subroutine_file146 = new BitSet(
			new long[] { 0x0100000000000000L });
	public static final BitSet FOLLOW_lparen_in_subroutine_file148 = new BitSet(
			new long[] { 0x0200000000000000L, 0x0000000000040000L });
	public static final BitSet FOLLOW_id_in_subroutine_file151 = new BitSet(
			new long[] { 0x0280000000000000L, 0x0000000000040000L });
	public static final BitSet FOLLOW_comma_in_subroutine_file154 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000040000L });
	public static final BitSet FOLLOW_id_in_subroutine_file156 = new BitSet(
			new long[] { 0x0280000000000000L, 0x0000000000040000L });
	public static final BitSet FOLLOW_rparen_in_subroutine_file162 = new BitSet(
			new long[] { 0xFFFFFFFFFFFFFFF0L, 0x00000007FFFFFFFFL });
	public static final BitSet FOLLOW_set_in_subroutine_file165 = new BitSet(
			new long[] { 0x0000800000000000L });
	public static final BitSet FOLLOW_end_in_subroutine_file175 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_implicit_in_implicit_none189 = new BitSet(
			new long[] { 0x0000001000000000L });
	public static final BitSet FOLLOW_none_in_implicit_none191 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_true__in_logical_constants206 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_false__in_logical_constants212 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_PROGRAM_in_program231 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_SUBROUTINE_in_subroutine246 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_FUNCTION_in_function261 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_INTEGER_in_integer276 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_REAL_in_real291 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_DOUBLE_PRECISION_in_double_precision306 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_COMPLEX_in_complex321 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_CHARACTER_in_character336 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_LOGICAL_in_logical351 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_IMPLICIT_in_implicit366 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_NONE_in_none381 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_DATA_in_data396 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ALLOCATE_in_allocate411 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_PARAMETER_in_parameter426 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_IF_in_if_441 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_THEN_in_then_456 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ELSE_in_else_471 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_DO_in_do_486 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_WHILE_in_while_501 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ENDDO_in_enddo516 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_END_in_end531 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_CALL_in_call_546 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_GOTO_in_goto_561 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_RETURN_in_return_576 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_CONTINUE_in_continue_591 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_EXTERNAL_in_external606 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_INTRINSIC_in_intrincis621 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_DOLLAR_in_dollar636 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_COMMA_in_comma651 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_LPAREN_in_lparen666 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_RPAREN_in_rparen681 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_COLON_in_colon696 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ASSIGN_in_assign711 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_MINUS_in_minus726 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_PLUS_in_plus741 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_DIV_in_div756 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_POWER_in_power771 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_STAR_in_star786 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_LNOT_in_lnot801 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_LAND_in_land816 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_LOR_in_lor831 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_EQV_in_eqv846 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_NEQV_in_neqv861 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_XOR_in_xor876 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_EOR_in_eor891 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_LT_in_lt906 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_LE_in_le921 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_GT_in_gt936 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_GE_in_ge951 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_NE_in_ne966 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_EQ_in_eq981 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_TRUE_in_true_996 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_FALSE_in_false_1011 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ID_in_id1026 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_INT_CONST_in_int_const1041 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_HEX_CONST_in_hex_const1056 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_FLOAT_CONST_in_float_const1071 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_program_file_in_synpred1_FortranParser52 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_subroutine_file_in_synpred2_FortranParser60 = new BitSet(
			new long[] { 0x0000000000000002L });

}