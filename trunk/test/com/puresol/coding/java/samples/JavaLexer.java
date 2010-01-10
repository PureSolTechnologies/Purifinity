/***************************************************************************
 *
 *   JavaLexer.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.java.samples;

import org.antlr.runtime.*;

public class JavaLexer extends Lexer {
	public static final int T__29 = 29;
	public static final int T__28 = 28;
	public static final int T__27 = 27;
	public static final int FloatTypeSuffix = 18;
	public static final int OctalLiteral = 12;
	public static final int CODERANGE_METHOD = 5;
	public static final int EOF = -1;
	public static final int Identifier = 6;
	public static final int T__93 = 93;
	public static final int T__94 = 94;
	public static final int T__91 = 91;
	public static final int T__92 = 92;
	public static final int T__90 = 90;
	public static final int COMMENT = 25;
	public static final int T__99 = 99;
	public static final int T__98 = 98;
	public static final int T__97 = 97;
	public static final int T__96 = 96;
	public static final int T__95 = 95;
	public static final int T__80 = 80;
	public static final int T__81 = 81;
	public static final int T__82 = 82;
	public static final int T__83 = 83;
	public static final int LINE_COMMENT = 26;
	public static final int IntegerTypeSuffix = 16;
	public static final int CODERANGE_CLASS = 4;
	public static final int T__85 = 85;
	public static final int T__84 = 84;
	public static final int T__87 = 87;
	public static final int ASSERT = 14;
	public static final int T__86 = 86;
	public static final int T__89 = 89;
	public static final int T__88 = 88;
	public static final int T__71 = 71;
	public static final int WS = 24;
	public static final int T__72 = 72;
	public static final int T__70 = 70;
	public static final int FloatingPointLiteral = 8;
	public static final int JavaIDDigit = 23;
	public static final int T__76 = 76;
	public static final int T__75 = 75;
	public static final int T__74 = 74;
	public static final int Letter = 22;
	public static final int EscapeSequence = 19;
	public static final int T__73 = 73;
	public static final int T__79 = 79;
	public static final int T__78 = 78;
	public static final int T__77 = 77;
	public static final int T__68 = 68;
	public static final int T__69 = 69;
	public static final int T__66 = 66;
	public static final int T__67 = 67;
	public static final int T__64 = 64;
	public static final int T__65 = 65;
	public static final int T__62 = 62;
	public static final int T__63 = 63;
	public static final int CharacterLiteral = 9;
	public static final int T__114 = 114;
	public static final int T__115 = 115;
	public static final int Exponent = 17;
	public static final int T__61 = 61;
	public static final int T__60 = 60;
	public static final int HexDigit = 15;
	public static final int T__55 = 55;
	public static final int T__56 = 56;
	public static final int T__57 = 57;
	public static final int T__58 = 58;
	public static final int T__51 = 51;
	public static final int T__52 = 52;
	public static final int T__53 = 53;
	public static final int T__54 = 54;
	public static final int T__107 = 107;
	public static final int T__108 = 108;
	public static final int T__109 = 109;
	public static final int T__59 = 59;
	public static final int T__103 = 103;
	public static final int T__104 = 104;
	public static final int T__105 = 105;
	public static final int T__106 = 106;
	public static final int T__111 = 111;
	public static final int T__110 = 110;
	public static final int T__113 = 113;
	public static final int T__112 = 112;
	public static final int T__50 = 50;
	public static final int T__42 = 42;
	public static final int HexLiteral = 11;
	public static final int T__43 = 43;
	public static final int T__40 = 40;
	public static final int T__41 = 41;
	public static final int T__46 = 46;
	public static final int T__47 = 47;
	public static final int T__44 = 44;
	public static final int T__45 = 45;
	public static final int T__48 = 48;
	public static final int T__49 = 49;
	public static final int T__102 = 102;
	public static final int T__101 = 101;
	public static final int T__100 = 100;
	public static final int DecimalLiteral = 13;
	public static final int StringLiteral = 10;
	public static final int T__30 = 30;
	public static final int T__31 = 31;
	public static final int T__32 = 32;
	public static final int T__33 = 33;
	public static final int T__34 = 34;
	public static final int ENUM = 7;
	public static final int T__35 = 35;
	public static final int T__36 = 36;
	public static final int T__37 = 37;
	public static final int T__38 = 38;
	public static final int T__39 = 39;
	public static final int UnicodeEscape = 20;
	public static final int OctalEscape = 21;

	protected boolean enumIsKeyword = true;
	protected boolean assertIsKeyword = true;

	// delegates
	// delegators

	public JavaLexer() {
		;
	}

	public JavaLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}

	public JavaLexer(CharStream input, RecognizerSharedState state) {
		super(input, state);

	}

	public String getGrammarFileName() {
		return "/home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g";
	}

	// $ANTLR start "T__27"
	public final void mT__27() throws RecognitionException {
		try {
			int _type = T__27;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:11:7:
			// ( 'package' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:11:9:
			// 'package'
			{
				match("package");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__27"

	// $ANTLR start "T__28"
	public final void mT__28() throws RecognitionException {
		try {
			int _type = T__28;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:12:7:
			// ( ';' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:12:9:
			// ';'
			{
				match(';');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__28"

	// $ANTLR start "T__29"
	public final void mT__29() throws RecognitionException {
		try {
			int _type = T__29;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:13:7:
			// ( 'import' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:13:9:
			// 'import'
			{
				match("import");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__29"

	// $ANTLR start "T__30"
	public final void mT__30() throws RecognitionException {
		try {
			int _type = T__30;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:14:7:
			// ( 'static' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:14:9:
			// 'static'
			{
				match("static");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__30"

	// $ANTLR start "T__31"
	public final void mT__31() throws RecognitionException {
		try {
			int _type = T__31;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:15:7:
			// ( '.' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:15:9:
			// '.'
			{
				match('.');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__31"

	// $ANTLR start "T__32"
	public final void mT__32() throws RecognitionException {
		try {
			int _type = T__32;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:16:7:
			// ( '*' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:16:9:
			// '*'
			{
				match('*');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__32"

	// $ANTLR start "T__33"
	public final void mT__33() throws RecognitionException {
		try {
			int _type = T__33;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:17:7:
			// ( 'public' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:17:9:
			// 'public'
			{
				match("public");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__33"

	// $ANTLR start "T__34"
	public final void mT__34() throws RecognitionException {
		try {
			int _type = T__34;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:18:7:
			// ( 'protected' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:18:9:
			// 'protected'
			{
				match("protected");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__34"

	// $ANTLR start "T__35"
	public final void mT__35() throws RecognitionException {
		try {
			int _type = T__35;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:19:7:
			// ( 'private' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:19:9:
			// 'private'
			{
				match("private");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__35"

	// $ANTLR start "T__36"
	public final void mT__36() throws RecognitionException {
		try {
			int _type = T__36;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:20:7:
			// ( 'abstract' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:20:9:
			// 'abstract'
			{
				match("abstract");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__36"

	// $ANTLR start "T__37"
	public final void mT__37() throws RecognitionException {
		try {
			int _type = T__37;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:21:7:
			// ( 'final' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:21:9:
			// 'final'
			{
				match("final");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__37"

	// $ANTLR start "T__38"
	public final void mT__38() throws RecognitionException {
		try {
			int _type = T__38;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:22:7:
			// ( 'strictfp' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:22:9:
			// 'strictfp'
			{
				match("strictfp");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__38"

	// $ANTLR start "T__39"
	public final void mT__39() throws RecognitionException {
		try {
			int _type = T__39;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:23:7:
			// ( 'class' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:23:9:
			// 'class'
			{
				match("class");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__39"

	// $ANTLR start "T__40"
	public final void mT__40() throws RecognitionException {
		try {
			int _type = T__40;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:24:7:
			// ( 'extends' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:24:9:
			// 'extends'
			{
				match("extends");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__40"

	// $ANTLR start "T__41"
	public final void mT__41() throws RecognitionException {
		try {
			int _type = T__41;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:25:7:
			// ( 'implements' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:25:9:
			// 'implements'
			{
				match("implements");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__41"

	// $ANTLR start "T__42"
	public final void mT__42() throws RecognitionException {
		try {
			int _type = T__42;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:26:7:
			// ( '<' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:26:9:
			// '<'
			{
				match('<');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__42"

	// $ANTLR start "T__43"
	public final void mT__43() throws RecognitionException {
		try {
			int _type = T__43;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:27:7:
			// ( ',' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:27:9:
			// ','
			{
				match(',');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__43"

	// $ANTLR start "T__44"
	public final void mT__44() throws RecognitionException {
		try {
			int _type = T__44;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:28:7:
			// ( '>' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:28:9:
			// '>'
			{
				match('>');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__44"

	// $ANTLR start "T__45"
	public final void mT__45() throws RecognitionException {
		try {
			int _type = T__45;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:29:7:
			// ( '&' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:29:9:
			// '&'
			{
				match('&');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__45"

	// $ANTLR start "T__46"
	public final void mT__46() throws RecognitionException {
		try {
			int _type = T__46;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:30:7:
			// ( '{' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:30:9:
			// '{'
			{
				match('{');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__46"

	// $ANTLR start "T__47"
	public final void mT__47() throws RecognitionException {
		try {
			int _type = T__47;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:31:7:
			// ( '}' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:31:9:
			// '}'
			{
				match('}');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__47"

	// $ANTLR start "T__48"
	public final void mT__48() throws RecognitionException {
		try {
			int _type = T__48;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:32:7:
			// ( 'interface' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:32:9:
			// 'interface'
			{
				match("interface");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__48"

	// $ANTLR start "T__49"
	public final void mT__49() throws RecognitionException {
		try {
			int _type = T__49;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:33:7:
			// ( 'void' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:33:9:
			// 'void'
			{
				match("void");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__49"

	// $ANTLR start "T__50"
	public final void mT__50() throws RecognitionException {
		try {
			int _type = T__50;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:34:7:
			// ( '[' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:34:9:
			// '['
			{
				match('[');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__50"

	// $ANTLR start "T__51"
	public final void mT__51() throws RecognitionException {
		try {
			int _type = T__51;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:35:7:
			// ( ']' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:35:9:
			// ']'
			{
				match(']');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__51"

	// $ANTLR start "T__52"
	public final void mT__52() throws RecognitionException {
		try {
			int _type = T__52;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:36:7:
			// ( 'throws' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:36:9:
			// 'throws'
			{
				match("throws");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__52"

	// $ANTLR start "T__53"
	public final void mT__53() throws RecognitionException {
		try {
			int _type = T__53;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:37:7:
			// ( '=' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:37:9:
			// '='
			{
				match('=');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__53"

	// $ANTLR start "T__54"
	public final void mT__54() throws RecognitionException {
		try {
			int _type = T__54;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:38:7:
			// ( 'native' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:38:9:
			// 'native'
			{
				match("native");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__54"

	// $ANTLR start "T__55"
	public final void mT__55() throws RecognitionException {
		try {
			int _type = T__55;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:39:7:
			// ( 'synchronized' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:39:9:
			// 'synchronized'
			{
				match("synchronized");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__55"

	// $ANTLR start "T__56"
	public final void mT__56() throws RecognitionException {
		try {
			int _type = T__56;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:40:7:
			// ( 'transient' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:40:9:
			// 'transient'
			{
				match("transient");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__56"

	// $ANTLR start "T__57"
	public final void mT__57() throws RecognitionException {
		try {
			int _type = T__57;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:41:7:
			// ( 'volatile' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:41:9:
			// 'volatile'
			{
				match("volatile");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__57"

	// $ANTLR start "T__58"
	public final void mT__58() throws RecognitionException {
		try {
			int _type = T__58;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:42:7:
			// ( 'boolean' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:42:9:
			// 'boolean'
			{
				match("boolean");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__58"

	// $ANTLR start "T__59"
	public final void mT__59() throws RecognitionException {
		try {
			int _type = T__59;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:43:7:
			// ( 'char' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:43:9:
			// 'char'
			{
				match("char");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__59"

	// $ANTLR start "T__60"
	public final void mT__60() throws RecognitionException {
		try {
			int _type = T__60;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:44:7:
			// ( 'byte' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:44:9:
			// 'byte'
			{
				match("byte");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__60"

	// $ANTLR start "T__61"
	public final void mT__61() throws RecognitionException {
		try {
			int _type = T__61;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:45:7:
			// ( 'short' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:45:9:
			// 'short'
			{
				match("short");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__61"

	// $ANTLR start "T__62"
	public final void mT__62() throws RecognitionException {
		try {
			int _type = T__62;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:46:7:
			// ( 'int' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:46:9:
			// 'int'
			{
				match("int");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__62"

	// $ANTLR start "T__63"
	public final void mT__63() throws RecognitionException {
		try {
			int _type = T__63;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:47:7:
			// ( 'long' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:47:9:
			// 'long'
			{
				match("long");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__63"

	// $ANTLR start "T__64"
	public final void mT__64() throws RecognitionException {
		try {
			int _type = T__64;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:48:7:
			// ( 'float' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:48:9:
			// 'float'
			{
				match("float");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__64"

	// $ANTLR start "T__65"
	public final void mT__65() throws RecognitionException {
		try {
			int _type = T__65;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:49:7:
			// ( 'double' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:49:9:
			// 'double'
			{
				match("double");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__65"

	// $ANTLR start "T__66"
	public final void mT__66() throws RecognitionException {
		try {
			int _type = T__66;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:50:7:
			// ( '?' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:50:9:
			// '?'
			{
				match('?');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__66"

	// $ANTLR start "T__67"
	public final void mT__67() throws RecognitionException {
		try {
			int _type = T__67;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:51:7:
			// ( 'super' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:51:9:
			// 'super'
			{
				match("super");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__67"

	// $ANTLR start "T__68"
	public final void mT__68() throws RecognitionException {
		try {
			int _type = T__68;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:52:7:
			// ( '(' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:52:9:
			// '('
			{
				match('(');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__68"

	// $ANTLR start "T__69"
	public final void mT__69() throws RecognitionException {
		try {
			int _type = T__69;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:53:7:
			// ( ')' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:53:9:
			// ')'
			{
				match(')');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__69"

	// $ANTLR start "T__70"
	public final void mT__70() throws RecognitionException {
		try {
			int _type = T__70;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:54:7:
			// ( '...' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:54:9:
			// '...'
			{
				match("...");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__70"

	// $ANTLR start "T__71"
	public final void mT__71() throws RecognitionException {
		try {
			int _type = T__71;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:55:7:
			// ( 'this' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:55:9:
			// 'this'
			{
				match("this");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__71"

	// $ANTLR start "T__72"
	public final void mT__72() throws RecognitionException {
		try {
			int _type = T__72;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:56:7:
			// ( 'null' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:56:9:
			// 'null'
			{
				match("null");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__72"

	// $ANTLR start "T__73"
	public final void mT__73() throws RecognitionException {
		try {
			int _type = T__73;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:57:7:
			// ( 'true' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:57:9:
			// 'true'
			{
				match("true");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__73"

	// $ANTLR start "T__74"
	public final void mT__74() throws RecognitionException {
		try {
			int _type = T__74;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:58:7:
			// ( 'false' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:58:9:
			// 'false'
			{
				match("false");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__74"

	// $ANTLR start "T__75"
	public final void mT__75() throws RecognitionException {
		try {
			int _type = T__75;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:59:7:
			// ( '@' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:59:9:
			// '@'
			{
				match('@');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__75"

	// $ANTLR start "T__76"
	public final void mT__76() throws RecognitionException {
		try {
			int _type = T__76;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:60:7:
			// ( 'default' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:60:9:
			// 'default'
			{
				match("default");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__76"

	// $ANTLR start "T__77"
	public final void mT__77() throws RecognitionException {
		try {
			int _type = T__77;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:61:7:
			// ( ':' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:61:9:
			// ':'
			{
				match(':');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__77"

	// $ANTLR start "T__78"
	public final void mT__78() throws RecognitionException {
		try {
			int _type = T__78;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:62:7:
			// ( 'if' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:62:9:
			// 'if'
			{
				match("if");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__78"

	// $ANTLR start "T__79"
	public final void mT__79() throws RecognitionException {
		try {
			int _type = T__79;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:63:7:
			// ( 'else' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:63:9:
			// 'else'
			{
				match("else");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__79"

	// $ANTLR start "T__80"
	public final void mT__80() throws RecognitionException {
		try {
			int _type = T__80;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:64:7:
			// ( 'for' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:64:9:
			// 'for'
			{
				match("for");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__80"

	// $ANTLR start "T__81"
	public final void mT__81() throws RecognitionException {
		try {
			int _type = T__81;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:65:7:
			// ( 'while' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:65:9:
			// 'while'
			{
				match("while");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__81"

	// $ANTLR start "T__82"
	public final void mT__82() throws RecognitionException {
		try {
			int _type = T__82;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:66:7:
			// ( 'do' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:66:9:
			// 'do'
			{
				match("do");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__82"

	// $ANTLR start "T__83"
	public final void mT__83() throws RecognitionException {
		try {
			int _type = T__83;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:67:7:
			// ( 'try' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:67:9:
			// 'try'
			{
				match("try");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__83"

	// $ANTLR start "T__84"
	public final void mT__84() throws RecognitionException {
		try {
			int _type = T__84;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:68:7:
			// ( 'finally' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:68:9:
			// 'finally'
			{
				match("finally");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__84"

	// $ANTLR start "T__85"
	public final void mT__85() throws RecognitionException {
		try {
			int _type = T__85;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:69:7:
			// ( 'switch' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:69:9:
			// 'switch'
			{
				match("switch");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__85"

	// $ANTLR start "T__86"
	public final void mT__86() throws RecognitionException {
		try {
			int _type = T__86;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:70:7:
			// ( 'return' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:70:9:
			// 'return'
			{
				match("return");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__86"

	// $ANTLR start "T__87"
	public final void mT__87() throws RecognitionException {
		try {
			int _type = T__87;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:71:7:
			// ( 'throw' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:71:9:
			// 'throw'
			{
				match("throw");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__87"

	// $ANTLR start "T__88"
	public final void mT__88() throws RecognitionException {
		try {
			int _type = T__88;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:72:7:
			// ( 'break' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:72:9:
			// 'break'
			{
				match("break");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__88"

	// $ANTLR start "T__89"
	public final void mT__89() throws RecognitionException {
		try {
			int _type = T__89;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:73:7:
			// ( 'continue' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:73:9:
			// 'continue'
			{
				match("continue");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__89"

	// $ANTLR start "T__90"
	public final void mT__90() throws RecognitionException {
		try {
			int _type = T__90;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:74:7:
			// ( 'catch' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:74:9:
			// 'catch'
			{
				match("catch");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__90"

	// $ANTLR start "T__91"
	public final void mT__91() throws RecognitionException {
		try {
			int _type = T__91;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:75:7:
			// ( 'case' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:75:9:
			// 'case'
			{
				match("case");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__91"

	// $ANTLR start "T__92"
	public final void mT__92() throws RecognitionException {
		try {
			int _type = T__92;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:76:7:
			// ( '+=' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:76:9:
			// '+='
			{
				match("+=");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__92"

	// $ANTLR start "T__93"
	public final void mT__93() throws RecognitionException {
		try {
			int _type = T__93;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:77:7:
			// ( '-=' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:77:9:
			// '-='
			{
				match("-=");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__93"

	// $ANTLR start "T__94"
	public final void mT__94() throws RecognitionException {
		try {
			int _type = T__94;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:78:7:
			// ( '*=' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:78:9:
			// '*='
			{
				match("*=");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__94"

	// $ANTLR start "T__95"
	public final void mT__95() throws RecognitionException {
		try {
			int _type = T__95;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:79:7:
			// ( '/=' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:79:9:
			// '/='
			{
				match("/=");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__95"

	// $ANTLR start "T__96"
	public final void mT__96() throws RecognitionException {
		try {
			int _type = T__96;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:80:7:
			// ( '&=' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:80:9:
			// '&='
			{
				match("&=");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__96"

	// $ANTLR start "T__97"
	public final void mT__97() throws RecognitionException {
		try {
			int _type = T__97;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:81:7:
			// ( '|=' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:81:9:
			// '|='
			{
				match("|=");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__97"

	// $ANTLR start "T__98"
	public final void mT__98() throws RecognitionException {
		try {
			int _type = T__98;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:82:7:
			// ( '^=' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:82:9:
			// '^='
			{
				match("^=");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__98"

	// $ANTLR start "T__99"
	public final void mT__99() throws RecognitionException {
		try {
			int _type = T__99;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:83:7:
			// ( '%=' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:83:9:
			// '%='
			{
				match("%=");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__99"

	// $ANTLR start "T__100"
	public final void mT__100() throws RecognitionException {
		try {
			int _type = T__100;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:84:8:
			// ( '||' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:84:10:
			// '||'
			{
				match("||");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__100"

	// $ANTLR start "T__101"
	public final void mT__101() throws RecognitionException {
		try {
			int _type = T__101;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:85:8:
			// ( '&&' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:85:10:
			// '&&'
			{
				match("&&");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__101"

	// $ANTLR start "T__102"
	public final void mT__102() throws RecognitionException {
		try {
			int _type = T__102;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:86:8:
			// ( '|' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:86:10:
			// '|'
			{
				match('|');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__102"

	// $ANTLR start "T__103"
	public final void mT__103() throws RecognitionException {
		try {
			int _type = T__103;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:87:8:
			// ( '^' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:87:10:
			// '^'
			{
				match('^');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__103"

	// $ANTLR start "T__104"
	public final void mT__104() throws RecognitionException {
		try {
			int _type = T__104;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:88:8:
			// ( '==' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:88:10:
			// '=='
			{
				match("==");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__104"

	// $ANTLR start "T__105"
	public final void mT__105() throws RecognitionException {
		try {
			int _type = T__105;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:89:8:
			// ( '!=' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:89:10:
			// '!='
			{
				match("!=");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__105"

	// $ANTLR start "T__106"
	public final void mT__106() throws RecognitionException {
		try {
			int _type = T__106;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:90:8:
			// ( 'instanceof' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:90:10:
			// 'instanceof'
			{
				match("instanceof");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__106"

	// $ANTLR start "T__107"
	public final void mT__107() throws RecognitionException {
		try {
			int _type = T__107;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:91:8:
			// ( '+' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:91:10:
			// '+'
			{
				match('+');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__107"

	// $ANTLR start "T__108"
	public final void mT__108() throws RecognitionException {
		try {
			int _type = T__108;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:92:8:
			// ( '-' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:92:10:
			// '-'
			{
				match('-');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__108"

	// $ANTLR start "T__109"
	public final void mT__109() throws RecognitionException {
		try {
			int _type = T__109;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:93:8:
			// ( '/' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:93:10:
			// '/'
			{
				match('/');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__109"

	// $ANTLR start "T__110"
	public final void mT__110() throws RecognitionException {
		try {
			int _type = T__110;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:94:8:
			// ( '%' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:94:10:
			// '%'
			{
				match('%');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__110"

	// $ANTLR start "T__111"
	public final void mT__111() throws RecognitionException {
		try {
			int _type = T__111;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:95:8:
			// ( '++' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:95:10:
			// '++'
			{
				match("++");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__111"

	// $ANTLR start "T__112"
	public final void mT__112() throws RecognitionException {
		try {
			int _type = T__112;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:96:8:
			// ( '--' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:96:10:
			// '--'
			{
				match("--");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__112"

	// $ANTLR start "T__113"
	public final void mT__113() throws RecognitionException {
		try {
			int _type = T__113;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:97:8:
			// ( '~' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:97:10:
			// '~'
			{
				match('~');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__113"

	// $ANTLR start "T__114"
	public final void mT__114() throws RecognitionException {
		try {
			int _type = T__114;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:98:8:
			// ( '!' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:98:10:
			// '!'
			{
				match('!');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__114"

	// $ANTLR start "T__115"
	public final void mT__115() throws RecognitionException {
		try {
			int _type = T__115;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:99:8:
			// ( 'new' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:99:10:
			// 'new'
			{
				match("new");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__115"

	// $ANTLR start "HexLiteral"
	public final void mHexLiteral() throws RecognitionException {
		try {
			int _type = HexLiteral;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:930:12:
			// ( '0' ( 'x' | 'X' ) ( HexDigit )+ ( IntegerTypeSuffix )? )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:930:14:
			// '0' ( 'x' | 'X' ) ( HexDigit )+ ( IntegerTypeSuffix )?
			{
				match('0');
				if (input.LA(1) == 'X' || input.LA(1) == 'x') {
					input.consume();

				} else {
					MismatchedSetException mse = new MismatchedSetException(
							null, input);
					recover(mse);
					throw mse;
				}

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:930:28:
				// ( HexDigit )+
				int cnt1 = 0;
				loop1: do {
					int alt1 = 2;
					int LA1_0 = input.LA(1);

					if (((LA1_0 >= '0' && LA1_0 <= '9')
							|| (LA1_0 >= 'A' && LA1_0 <= 'F') || (LA1_0 >= 'a' && LA1_0 <= 'f'))) {
						alt1 = 1;
					}

					switch (alt1) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:930:28:
						// HexDigit
					{
						mHexDigit();

					}
						break;

					default:
						if (cnt1 >= 1)
							break loop1;
						EarlyExitException eee = new EarlyExitException(1,
								input);
						throw eee;
					}
					cnt1++;
				} while (true);

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:930:38:
				// ( IntegerTypeSuffix )?
				int alt2 = 2;
				int LA2_0 = input.LA(1);

				if ((LA2_0 == 'L' || LA2_0 == 'l')) {
					alt2 = 1;
				}
				switch (alt2) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:930:38:
					// IntegerTypeSuffix
				{
					mIntegerTypeSuffix();

				}
					break;

				}

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "HexLiteral"

	// $ANTLR start "DecimalLiteral"
	public final void mDecimalLiteral() throws RecognitionException {
		try {
			int _type = DecimalLiteral;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:932:16:
			// ( ( '0' | '1' .. '9' ( '0' .. '9' )* ) ( IntegerTypeSuffix )? )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:932:18:
			// ( '0' | '1' .. '9' ( '0' .. '9' )* ) ( IntegerTypeSuffix )?
			{
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:932:18:
				// ( '0' | '1' .. '9' ( '0' .. '9' )* )
				int alt4 = 2;
				int LA4_0 = input.LA(1);

				if ((LA4_0 == '0')) {
					alt4 = 1;
				} else if (((LA4_0 >= '1' && LA4_0 <= '9'))) {
					alt4 = 2;
				} else {
					NoViableAltException nvae = new NoViableAltException("", 4,
							0, input);

					throw nvae;
				}
				switch (alt4) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:932:19:
					// '0'
				{
					match('0');

				}
					break;
				case 2:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:932:25:
					// '1' .. '9' ( '0' .. '9' )*
				{
					matchRange('1', '9');
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:932:34:
					// ( '0' .. '9' )*
					loop3: do {
						int alt3 = 2;
						int LA3_0 = input.LA(1);

						if (((LA3_0 >= '0' && LA3_0 <= '9'))) {
							alt3 = 1;
						}

						switch (alt3) {
						case 1:
							// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:932:34:
							// '0' .. '9'
						{
							matchRange('0', '9');

						}
							break;

						default:
							break loop3;
						}
					} while (true);

				}
					break;

				}

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:932:45:
				// ( IntegerTypeSuffix )?
				int alt5 = 2;
				int LA5_0 = input.LA(1);

				if ((LA5_0 == 'L' || LA5_0 == 'l')) {
					alt5 = 1;
				}
				switch (alt5) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:932:45:
					// IntegerTypeSuffix
				{
					mIntegerTypeSuffix();

				}
					break;

				}

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "DecimalLiteral"

	// $ANTLR start "OctalLiteral"
	public final void mOctalLiteral() throws RecognitionException {
		try {
			int _type = OctalLiteral;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:934:14:
			// ( '0' ( '0' .. '7' )+ ( IntegerTypeSuffix )? )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:934:16:
			// '0' ( '0' .. '7' )+ ( IntegerTypeSuffix )?
			{
				match('0');
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:934:20:
				// ( '0' .. '7' )+
				int cnt6 = 0;
				loop6: do {
					int alt6 = 2;
					int LA6_0 = input.LA(1);

					if (((LA6_0 >= '0' && LA6_0 <= '7'))) {
						alt6 = 1;
					}

					switch (alt6) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:934:21:
						// '0' .. '7'
					{
						matchRange('0', '7');

					}
						break;

					default:
						if (cnt6 >= 1)
							break loop6;
						EarlyExitException eee = new EarlyExitException(6,
								input);
						throw eee;
					}
					cnt6++;
				} while (true);

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:934:32:
				// ( IntegerTypeSuffix )?
				int alt7 = 2;
				int LA7_0 = input.LA(1);

				if ((LA7_0 == 'L' || LA7_0 == 'l')) {
					alt7 = 1;
				}
				switch (alt7) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:934:32:
					// IntegerTypeSuffix
				{
					mIntegerTypeSuffix();

				}
					break;

				}

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "OctalLiteral"

	// $ANTLR start "HexDigit"
	public final void mHexDigit() throws RecognitionException {
		try {
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:937:10:
			// ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:937:12:
			// ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
			{
				if ((input.LA(1) >= '0' && input.LA(1) <= '9')
						|| (input.LA(1) >= 'A' && input.LA(1) <= 'F')
						|| (input.LA(1) >= 'a' && input.LA(1) <= 'f')) {
					input.consume();

				} else {
					MismatchedSetException mse = new MismatchedSetException(
							null, input);
					recover(mse);
					throw mse;
				}

			}

		} finally {
		}
	}

	// $ANTLR end "HexDigit"

	// $ANTLR start "IntegerTypeSuffix"
	public final void mIntegerTypeSuffix() throws RecognitionException {
		try {
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:940:19:
			// ( ( 'l' | 'L' ) )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:940:21:
			// ( 'l' | 'L' )
			{
				if (input.LA(1) == 'L' || input.LA(1) == 'l') {
					input.consume();

				} else {
					MismatchedSetException mse = new MismatchedSetException(
							null, input);
					recover(mse);
					throw mse;
				}

			}

		} finally {
		}
	}

	// $ANTLR end "IntegerTypeSuffix"

	// $ANTLR start "FloatingPointLiteral"
	public final void mFloatingPointLiteral() throws RecognitionException {
		try {
			int _type = FloatingPointLiteral;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:943:5:
			// ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? (
			// FloatTypeSuffix )? | '.' ( '0' .. '9' )+ ( Exponent )? (
			// FloatTypeSuffix )? | ( '0' .. '9' )+ Exponent ( FloatTypeSuffix
			// )? | ( '0' .. '9' )+ FloatTypeSuffix )
			int alt18 = 4;
			alt18 = dfa18.predict(input);
			switch (alt18) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:943:9:
				// ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? (
				// FloatTypeSuffix )?
			{
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:943:9:
				// ( '0' .. '9' )+
				int cnt8 = 0;
				loop8: do {
					int alt8 = 2;
					int LA8_0 = input.LA(1);

					if (((LA8_0 >= '0' && LA8_0 <= '9'))) {
						alt8 = 1;
					}

					switch (alt8) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:943:10:
						// '0' .. '9'
					{
						matchRange('0', '9');

					}
						break;

					default:
						if (cnt8 >= 1)
							break loop8;
						EarlyExitException eee = new EarlyExitException(8,
								input);
						throw eee;
					}
					cnt8++;
				} while (true);

				match('.');
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:943:25:
				// ( '0' .. '9' )*
				loop9: do {
					int alt9 = 2;
					int LA9_0 = input.LA(1);

					if (((LA9_0 >= '0' && LA9_0 <= '9'))) {
						alt9 = 1;
					}

					switch (alt9) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:943:26:
						// '0' .. '9'
					{
						matchRange('0', '9');

					}
						break;

					default:
						break loop9;
					}
				} while (true);

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:943:37:
				// ( Exponent )?
				int alt10 = 2;
				int LA10_0 = input.LA(1);

				if ((LA10_0 == 'E' || LA10_0 == 'e')) {
					alt10 = 1;
				}
				switch (alt10) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:943:37:
					// Exponent
				{
					mExponent();

				}
					break;

				}

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:943:47:
				// ( FloatTypeSuffix )?
				int alt11 = 2;
				int LA11_0 = input.LA(1);

				if ((LA11_0 == 'D' || LA11_0 == 'F' || LA11_0 == 'd' || LA11_0 == 'f')) {
					alt11 = 1;
				}
				switch (alt11) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:943:47:
					// FloatTypeSuffix
				{
					mFloatTypeSuffix();

				}
					break;

				}

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:944:9:
				// '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )?
			{
				match('.');
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:944:13:
				// ( '0' .. '9' )+
				int cnt12 = 0;
				loop12: do {
					int alt12 = 2;
					int LA12_0 = input.LA(1);

					if (((LA12_0 >= '0' && LA12_0 <= '9'))) {
						alt12 = 1;
					}

					switch (alt12) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:944:14:
						// '0' .. '9'
					{
						matchRange('0', '9');

					}
						break;

					default:
						if (cnt12 >= 1)
							break loop12;
						EarlyExitException eee = new EarlyExitException(12,
								input);
						throw eee;
					}
					cnt12++;
				} while (true);

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:944:25:
				// ( Exponent )?
				int alt13 = 2;
				int LA13_0 = input.LA(1);

				if ((LA13_0 == 'E' || LA13_0 == 'e')) {
					alt13 = 1;
				}
				switch (alt13) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:944:25:
					// Exponent
				{
					mExponent();

				}
					break;

				}

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:944:35:
				// ( FloatTypeSuffix )?
				int alt14 = 2;
				int LA14_0 = input.LA(1);

				if ((LA14_0 == 'D' || LA14_0 == 'F' || LA14_0 == 'd' || LA14_0 == 'f')) {
					alt14 = 1;
				}
				switch (alt14) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:944:35:
					// FloatTypeSuffix
				{
					mFloatTypeSuffix();

				}
					break;

				}

			}
				break;
			case 3:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:945:9:
				// ( '0' .. '9' )+ Exponent ( FloatTypeSuffix )?
			{
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:945:9:
				// ( '0' .. '9' )+
				int cnt15 = 0;
				loop15: do {
					int alt15 = 2;
					int LA15_0 = input.LA(1);

					if (((LA15_0 >= '0' && LA15_0 <= '9'))) {
						alt15 = 1;
					}

					switch (alt15) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:945:10:
						// '0' .. '9'
					{
						matchRange('0', '9');

					}
						break;

					default:
						if (cnt15 >= 1)
							break loop15;
						EarlyExitException eee = new EarlyExitException(15,
								input);
						throw eee;
					}
					cnt15++;
				} while (true);

				mExponent();
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:945:30:
				// ( FloatTypeSuffix )?
				int alt16 = 2;
				int LA16_0 = input.LA(1);

				if ((LA16_0 == 'D' || LA16_0 == 'F' || LA16_0 == 'd' || LA16_0 == 'f')) {
					alt16 = 1;
				}
				switch (alt16) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:945:30:
					// FloatTypeSuffix
				{
					mFloatTypeSuffix();

				}
					break;

				}

			}
				break;
			case 4:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:946:9:
				// ( '0' .. '9' )+ FloatTypeSuffix
			{
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:946:9:
				// ( '0' .. '9' )+
				int cnt17 = 0;
				loop17: do {
					int alt17 = 2;
					int LA17_0 = input.LA(1);

					if (((LA17_0 >= '0' && LA17_0 <= '9'))) {
						alt17 = 1;
					}

					switch (alt17) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:946:10:
						// '0' .. '9'
					{
						matchRange('0', '9');

					}
						break;

					default:
						if (cnt17 >= 1)
							break loop17;
						EarlyExitException eee = new EarlyExitException(17,
								input);
						throw eee;
					}
					cnt17++;
				} while (true);

				mFloatTypeSuffix();

			}
				break;

			}
			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "FloatingPointLiteral"

	// $ANTLR start "Exponent"
	public final void mExponent() throws RecognitionException {
		try {
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:950:10:
			// ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:950:12:
			// ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
			{
				if (input.LA(1) == 'E' || input.LA(1) == 'e') {
					input.consume();

				} else {
					MismatchedSetException mse = new MismatchedSetException(
							null, input);
					recover(mse);
					throw mse;
				}

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:950:22:
				// ( '+' | '-' )?
				int alt19 = 2;
				int LA19_0 = input.LA(1);

				if ((LA19_0 == '+' || LA19_0 == '-')) {
					alt19 = 1;
				}
				switch (alt19) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:
				{
					if (input.LA(1) == '+' || input.LA(1) == '-') {
						input.consume();

					} else {
						MismatchedSetException mse = new MismatchedSetException(
								null, input);
						recover(mse);
						throw mse;
					}

				}
					break;

				}

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:950:33:
				// ( '0' .. '9' )+
				int cnt20 = 0;
				loop20: do {
					int alt20 = 2;
					int LA20_0 = input.LA(1);

					if (((LA20_0 >= '0' && LA20_0 <= '9'))) {
						alt20 = 1;
					}

					switch (alt20) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:950:34:
						// '0' .. '9'
					{
						matchRange('0', '9');

					}
						break;

					default:
						if (cnt20 >= 1)
							break loop20;
						EarlyExitException eee = new EarlyExitException(20,
								input);
						throw eee;
					}
					cnt20++;
				} while (true);

			}

		} finally {
		}
	}

	// $ANTLR end "Exponent"

	// $ANTLR start "FloatTypeSuffix"
	public final void mFloatTypeSuffix() throws RecognitionException {
		try {
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:953:17:
			// ( ( 'f' | 'F' | 'd' | 'D' ) )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:953:19:
			// ( 'f' | 'F' | 'd' | 'D' )
			{
				if (input.LA(1) == 'D' || input.LA(1) == 'F'
						|| input.LA(1) == 'd' || input.LA(1) == 'f') {
					input.consume();

				} else {
					MismatchedSetException mse = new MismatchedSetException(
							null, input);
					recover(mse);
					throw mse;
				}

			}

		} finally {
		}
	}

	// $ANTLR end "FloatTypeSuffix"

	// $ANTLR start "CharacterLiteral"
	public final void mCharacterLiteral() throws RecognitionException {
		try {
			int _type = CharacterLiteral;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:956:5:
			// ( '\\'' ( EscapeSequence | ~ ( '\\'' | '\\\\' ) ) '\\'' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:956:9:
			// '\\'' ( EscapeSequence | ~ ( '\\'' | '\\\\' ) ) '\\''
			{
				match('\'');
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:956:14:
				// ( EscapeSequence | ~ ( '\\'' | '\\\\' ) )
				int alt21 = 2;
				int LA21_0 = input.LA(1);

				if ((LA21_0 == '\\')) {
					alt21 = 1;
				} else if (((LA21_0 >= '\u0000' && LA21_0 <= '&')
						|| (LA21_0 >= '(' && LA21_0 <= '[') || (LA21_0 >= ']' && LA21_0 <= '\uFFFF'))) {
					alt21 = 2;
				} else {
					NoViableAltException nvae = new NoViableAltException("",
							21, 0, input);

					throw nvae;
				}
				switch (alt21) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:956:16:
					// EscapeSequence
				{
					mEscapeSequence();

				}
					break;
				case 2:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:956:33:
					// ~ ( '\\'' | '\\\\' )
				{
					if ((input.LA(1) >= '\u0000' && input.LA(1) <= '&')
							|| (input.LA(1) >= '(' && input.LA(1) <= '[')
							|| (input.LA(1) >= ']' && input.LA(1) <= '\uFFFF')) {
						input.consume();

					} else {
						MismatchedSetException mse = new MismatchedSetException(
								null, input);
						recover(mse);
						throw mse;
					}

				}
					break;

				}

				match('\'');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "CharacterLiteral"

	// $ANTLR start "StringLiteral"
	public final void mStringLiteral() throws RecognitionException {
		try {
			int _type = StringLiteral;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:960:5:
			// ( '\"' ( EscapeSequence | ~ ( '\\\\' | '\"' ) )* '\"' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:960:8:
			// '\"' ( EscapeSequence | ~ ( '\\\\' | '\"' ) )* '\"'
			{
				match('\"');
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:960:12:
				// ( EscapeSequence | ~ ( '\\\\' | '\"' ) )*
				loop22: do {
					int alt22 = 3;
					int LA22_0 = input.LA(1);

					if ((LA22_0 == '\\')) {
						alt22 = 1;
					} else if (((LA22_0 >= '\u0000' && LA22_0 <= '!')
							|| (LA22_0 >= '#' && LA22_0 <= '[') || (LA22_0 >= ']' && LA22_0 <= '\uFFFF'))) {
						alt22 = 2;
					}

					switch (alt22) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:960:14:
						// EscapeSequence
					{
						mEscapeSequence();

					}
						break;
					case 2:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:960:31:
						// ~ ( '\\\\' | '\"' )
					{
						if ((input.LA(1) >= '\u0000' && input.LA(1) <= '!')
								|| (input.LA(1) >= '#' && input.LA(1) <= '[')
								|| (input.LA(1) >= ']' && input.LA(1) <= '\uFFFF')) {
							input.consume();

						} else {
							MismatchedSetException mse = new MismatchedSetException(
									null, input);
							recover(mse);
							throw mse;
						}

					}
						break;

					default:
						break loop22;
					}
				} while (true);

				match('\"');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "StringLiteral"

	// $ANTLR start "EscapeSequence"
	public final void mEscapeSequence() throws RecognitionException {
		try {
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:965:5:
			// ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\'
			// ) | UnicodeEscape | OctalEscape )
			int alt23 = 3;
			int LA23_0 = input.LA(1);

			if ((LA23_0 == '\\')) {
				switch (input.LA(2)) {
				case '\"':
				case '\'':
				case '\\':
				case 'b':
				case 'f':
				case 'n':
				case 'r':
				case 't': {
					alt23 = 1;
				}
					break;
				case 'u': {
					alt23 = 2;
				}
					break;
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7': {
					alt23 = 3;
				}
					break;
				default:
					NoViableAltException nvae = new NoViableAltException("",
							23, 1, input);

					throw nvae;
				}

			} else {
				NoViableAltException nvae = new NoViableAltException("", 23, 0,
						input);

				throw nvae;
			}
			switch (alt23) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:965:9:
				// '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' |
				// '\\\\' )
			{
				match('\\');
				if (input.LA(1) == '\"' || input.LA(1) == '\''
						|| input.LA(1) == '\\' || input.LA(1) == 'b'
						|| input.LA(1) == 'f' || input.LA(1) == 'n'
						|| input.LA(1) == 'r' || input.LA(1) == 't') {
					input.consume();

				} else {
					MismatchedSetException mse = new MismatchedSetException(
							null, input);
					recover(mse);
					throw mse;
				}

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:966:9:
				// UnicodeEscape
			{
				mUnicodeEscape();

			}
				break;
			case 3:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:967:9:
				// OctalEscape
			{
				mOctalEscape();

			}
				break;

			}
		} finally {
		}
	}

	// $ANTLR end "EscapeSequence"

	// $ANTLR start "OctalEscape"
	public final void mOctalEscape() throws RecognitionException {
		try {
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:972:5:
			// ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' (
			// '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
			int alt24 = 3;
			int LA24_0 = input.LA(1);

			if ((LA24_0 == '\\')) {
				int LA24_1 = input.LA(2);

				if (((LA24_1 >= '0' && LA24_1 <= '3'))) {
					int LA24_2 = input.LA(3);

					if (((LA24_2 >= '0' && LA24_2 <= '7'))) {
						int LA24_5 = input.LA(4);

						if (((LA24_5 >= '0' && LA24_5 <= '7'))) {
							alt24 = 1;
						} else {
							alt24 = 2;
						}
					} else {
						alt24 = 3;
					}
				} else if (((LA24_1 >= '4' && LA24_1 <= '7'))) {
					int LA24_3 = input.LA(3);

					if (((LA24_3 >= '0' && LA24_3 <= '7'))) {
						alt24 = 2;
					} else {
						alt24 = 3;
					}
				} else {
					NoViableAltException nvae = new NoViableAltException("",
							24, 1, input);

					throw nvae;
				}
			} else {
				NoViableAltException nvae = new NoViableAltException("", 24, 0,
						input);

				throw nvae;
			}
			switch (alt24) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:972:9:
				// '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
			{
				match('\\');
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:972:14:
				// ( '0' .. '3' )
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:972:15:
				// '0' .. '3'
				{
					matchRange('0', '3');

				}

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:972:25:
				// ( '0' .. '7' )
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:972:26:
				// '0' .. '7'
				{
					matchRange('0', '7');

				}

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:972:36:
				// ( '0' .. '7' )
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:972:37:
				// '0' .. '7'
				{
					matchRange('0', '7');

				}

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:973:9:
				// '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
			{
				match('\\');
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:973:14:
				// ( '0' .. '7' )
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:973:15:
				// '0' .. '7'
				{
					matchRange('0', '7');

				}

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:973:25:
				// ( '0' .. '7' )
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:973:26:
				// '0' .. '7'
				{
					matchRange('0', '7');

				}

			}
				break;
			case 3:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:974:9:
				// '\\\\' ( '0' .. '7' )
			{
				match('\\');
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:974:14:
				// ( '0' .. '7' )
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:974:15:
				// '0' .. '7'
				{
					matchRange('0', '7');

				}

			}
				break;

			}
		} finally {
		}
	}

	// $ANTLR end "OctalEscape"

	// $ANTLR start "UnicodeEscape"
	public final void mUnicodeEscape() throws RecognitionException {
		try {
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:979:5:
			// ( '\\\\' 'u' HexDigit HexDigit HexDigit HexDigit )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:979:9:
			// '\\\\' 'u' HexDigit HexDigit HexDigit HexDigit
			{
				match('\\');
				match('u');
				mHexDigit();
				mHexDigit();
				mHexDigit();
				mHexDigit();

			}

		} finally {
		}
	}

	// $ANTLR end "UnicodeEscape"

	// $ANTLR start "ENUM"
	public final void mENUM() throws RecognitionException {
		try {
			int _type = ENUM;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:982:5:
			// ( 'enum' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:982:9:
			// 'enum'
			{
				match("enum");

				if (!enumIsKeyword)
					_type = Identifier;

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "ENUM"

	// $ANTLR start "ASSERT"
	public final void mASSERT() throws RecognitionException {
		try {
			int _type = ASSERT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:986:5:
			// ( 'assert' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:986:9:
			// 'assert'
			{
				match("assert");

				if (!assertIsKeyword)
					_type = Identifier;

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "ASSERT"

	// $ANTLR start "Identifier"
	public final void mIdentifier() throws RecognitionException {
		try {
			int _type = Identifier;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:990:5:
			// ( Letter ( Letter | JavaIDDigit )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:990:9:
			// Letter ( Letter | JavaIDDigit )*
			{
				mLetter();
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:990:16:
				// ( Letter | JavaIDDigit )*
				loop25: do {
					int alt25 = 2;
					int LA25_0 = input.LA(1);

					if ((LA25_0 == '$' || (LA25_0 >= '0' && LA25_0 <= '9')
							|| (LA25_0 >= 'A' && LA25_0 <= 'Z')
							|| LA25_0 == '_'
							|| (LA25_0 >= 'a' && LA25_0 <= 'z')
							|| (LA25_0 >= '\u00C0' && LA25_0 <= '\u00D6')
							|| (LA25_0 >= '\u00D8' && LA25_0 <= '\u00F6')
							|| (LA25_0 >= '\u00F8' && LA25_0 <= '\u1FFF')
							|| (LA25_0 >= '\u3040' && LA25_0 <= '\u318F')
							|| (LA25_0 >= '\u3300' && LA25_0 <= '\u337F')
							|| (LA25_0 >= '\u3400' && LA25_0 <= '\u3D2D')
							|| (LA25_0 >= '\u4E00' && LA25_0 <= '\u9FFF') || (LA25_0 >= '\uF900' && LA25_0 <= '\uFAFF'))) {
						alt25 = 1;
					}

					switch (alt25) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:
					{
						if (input.LA(1) == '$'
								|| (input.LA(1) >= '0' && input.LA(1) <= '9')
								|| (input.LA(1) >= 'A' && input.LA(1) <= 'Z')
								|| input.LA(1) == '_'
								|| (input.LA(1) >= 'a' && input.LA(1) <= 'z')
								|| (input.LA(1) >= '\u00C0' && input.LA(1) <= '\u00D6')
								|| (input.LA(1) >= '\u00D8' && input.LA(1) <= '\u00F6')
								|| (input.LA(1) >= '\u00F8' && input.LA(1) <= '\u1FFF')
								|| (input.LA(1) >= '\u3040' && input.LA(1) <= '\u318F')
								|| (input.LA(1) >= '\u3300' && input.LA(1) <= '\u337F')
								|| (input.LA(1) >= '\u3400' && input.LA(1) <= '\u3D2D')
								|| (input.LA(1) >= '\u4E00' && input.LA(1) <= '\u9FFF')
								|| (input.LA(1) >= '\uF900' && input.LA(1) <= '\uFAFF')) {
							input.consume();

						} else {
							MismatchedSetException mse = new MismatchedSetException(
									null, input);
							recover(mse);
							throw mse;
						}

					}
						break;

					default:
						break loop25;
					}
				} while (true);

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "Identifier"

	// $ANTLR start "Letter"
	public final void mLetter() throws RecognitionException {
		try {
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:998:5:
			// ( '\\u0024' | '\\u0041' .. '\\u005a' | '\\u005f' | '\\u0061' ..
			// '\\u007a' | '\\u00c0' .. '\\u00d6' | '\\u00d8' .. '\\u00f6' |
			// '\\u00f8' .. '\\u00ff' | '\\u0100' .. '\\u1fff' | '\\u3040' ..
			// '\\u318f' | '\\u3300' .. '\\u337f' | '\\u3400' .. '\\u3d2d' |
			// '\\u4e00' .. '\\u9fff' | '\\uf900' .. '\\ufaff' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:
			{
				if (input.LA(1) == '$'
						|| (input.LA(1) >= 'A' && input.LA(1) <= 'Z')
						|| input.LA(1) == '_'
						|| (input.LA(1) >= 'a' && input.LA(1) <= 'z')
						|| (input.LA(1) >= '\u00C0' && input.LA(1) <= '\u00D6')
						|| (input.LA(1) >= '\u00D8' && input.LA(1) <= '\u00F6')
						|| (input.LA(1) >= '\u00F8' && input.LA(1) <= '\u1FFF')
						|| (input.LA(1) >= '\u3040' && input.LA(1) <= '\u318F')
						|| (input.LA(1) >= '\u3300' && input.LA(1) <= '\u337F')
						|| (input.LA(1) >= '\u3400' && input.LA(1) <= '\u3D2D')
						|| (input.LA(1) >= '\u4E00' && input.LA(1) <= '\u9FFF')
						|| (input.LA(1) >= '\uF900' && input.LA(1) <= '\uFAFF')) {
					input.consume();

				} else {
					MismatchedSetException mse = new MismatchedSetException(
							null, input);
					recover(mse);
					throw mse;
				}

			}

		} finally {
		}
	}

	// $ANTLR end "Letter"

	// $ANTLR start "JavaIDDigit"
	public final void mJavaIDDigit() throws RecognitionException {
		try {
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1015:5:
			// ( '\\u0030' .. '\\u0039' | '\\u0660' .. '\\u0669' | '\\u06f0' ..
			// '\\u06f9' | '\\u0966' .. '\\u096f' | '\\u09e6' .. '\\u09ef' |
			// '\\u0a66' .. '\\u0a6f' | '\\u0ae6' .. '\\u0aef' | '\\u0b66' ..
			// '\\u0b6f' | '\\u0be7' .. '\\u0bef' | '\\u0c66' .. '\\u0c6f' |
			// '\\u0ce6' .. '\\u0cef' | '\\u0d66' .. '\\u0d6f' | '\\u0e50' ..
			// '\\u0e59' | '\\u0ed0' .. '\\u0ed9' | '\\u1040' .. '\\u1049' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:
			{
				if ((input.LA(1) >= '0' && input.LA(1) <= '9')
						|| (input.LA(1) >= '\u0660' && input.LA(1) <= '\u0669')
						|| (input.LA(1) >= '\u06F0' && input.LA(1) <= '\u06F9')
						|| (input.LA(1) >= '\u0966' && input.LA(1) <= '\u096F')
						|| (input.LA(1) >= '\u09E6' && input.LA(1) <= '\u09EF')
						|| (input.LA(1) >= '\u0A66' && input.LA(1) <= '\u0A6F')
						|| (input.LA(1) >= '\u0AE6' && input.LA(1) <= '\u0AEF')
						|| (input.LA(1) >= '\u0B66' && input.LA(1) <= '\u0B6F')
						|| (input.LA(1) >= '\u0BE7' && input.LA(1) <= '\u0BEF')
						|| (input.LA(1) >= '\u0C66' && input.LA(1) <= '\u0C6F')
						|| (input.LA(1) >= '\u0CE6' && input.LA(1) <= '\u0CEF')
						|| (input.LA(1) >= '\u0D66' && input.LA(1) <= '\u0D6F')
						|| (input.LA(1) >= '\u0E50' && input.LA(1) <= '\u0E59')
						|| (input.LA(1) >= '\u0ED0' && input.LA(1) <= '\u0ED9')
						|| (input.LA(1) >= '\u1040' && input.LA(1) <= '\u1049')) {
					input.consume();

				} else {
					MismatchedSetException mse = new MismatchedSetException(
							null, input);
					recover(mse);
					throw mse;
				}

			}

		} finally {
		}
	}

	// $ANTLR end "JavaIDDigit"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1032:5:
			// ( ( ' ' | '\\r' | '\\t' | '\ ' | '\\n' ) )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1032:8:
			// ( ' ' | '\\r' | '\\t' | '\ ' | '\\n' )
			{
				if ((input.LA(1) >= '\t' && input.LA(1) <= '\n')
						|| (input.LA(1) >= '\f' && input.LA(1) <= '\r')
						|| input.LA(1) == ' ') {
					input.consume();

				} else {
					MismatchedSetException mse = new MismatchedSetException(
							null, input);
					recover(mse);
					throw mse;
				}

				_channel = HIDDEN;

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "WS"

	// $ANTLR start "COMMENT"
	public final void mCOMMENT() throws RecognitionException {
		try {
			int _type = COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1036:5:
			// ( '/*' ( options {greedy=false; } : . )* '*/' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1036:9:
			// '/*' ( options {greedy=false; } : . )* '*/'
			{
				match("/*");

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1036:14:
				// ( options {greedy=false; } : . )*
				loop26: do {
					int alt26 = 2;
					int LA26_0 = input.LA(1);

					if ((LA26_0 == '*')) {
						int LA26_1 = input.LA(2);

						if ((LA26_1 == '/')) {
							alt26 = 2;
						} else if (((LA26_1 >= '\u0000' && LA26_1 <= '.') || (LA26_1 >= '0' && LA26_1 <= '\uFFFF'))) {
							alt26 = 1;
						}

					} else if (((LA26_0 >= '\u0000' && LA26_0 <= ')') || (LA26_0 >= '+' && LA26_0 <= '\uFFFF'))) {
						alt26 = 1;
					}

					switch (alt26) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1036:42:
						// .
					{
						matchAny();

					}
						break;

					default:
						break loop26;
					}
				} while (true);

				match("*/");

				_channel = HIDDEN;

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "COMMENT"

	// $ANTLR start "LINE_COMMENT"
	public final void mLINE_COMMENT() throws RecognitionException {
		try {
			int _type = LINE_COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1040:5:
			// ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1040:7:
			// '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
			{
				match("//");

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1040:12:
				// (~ ( '\\n' | '\\r' ) )*
				loop27: do {
					int alt27 = 2;
					int LA27_0 = input.LA(1);

					if (((LA27_0 >= '\u0000' && LA27_0 <= '\t')
							|| (LA27_0 >= '\u000B' && LA27_0 <= '\f') || (LA27_0 >= '\u000E' && LA27_0 <= '\uFFFF'))) {
						alt27 = 1;
					}

					switch (alt27) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1040:12:
						// ~ ( '\\n' | '\\r' )
					{
						if ((input.LA(1) >= '\u0000' && input.LA(1) <= '\t')
								|| (input.LA(1) >= '\u000B' && input.LA(1) <= '\f')
								|| (input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF')) {
							input.consume();

						} else {
							MismatchedSetException mse = new MismatchedSetException(
									null, input);
							recover(mse);
							throw mse;
						}

					}
						break;

					default:
						break loop27;
					}
				} while (true);

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1040:26:
				// ( '\\r' )?
				int alt28 = 2;
				int LA28_0 = input.LA(1);

				if ((LA28_0 == '\r')) {
					alt28 = 1;
				}
				switch (alt28) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1040:26:
					// '\\r'
				{
					match('\r');

				}
					break;

				}

				match('\n');
				_channel = HIDDEN;

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "LINE_COMMENT"

	public void mTokens() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:8:
		// ( T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 |
		// T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43
		// | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 |
		// T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60
		// | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 |
		// T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77
		// | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 |
		// T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94
		// | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 |
		// T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | T__110
		// | T__111 | T__112 | T__113 | T__114 | T__115 | HexLiteral |
		// DecimalLiteral | OctalLiteral | FloatingPointLiteral |
		// CharacterLiteral | StringLiteral | ENUM | ASSERT | Identifier | WS |
		// COMMENT | LINE_COMMENT )
		int alt29 = 101;
		alt29 = dfa29.predict(input);
		switch (alt29) {
		case 1:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:10:
			// T__27
		{
			mT__27();

		}
			break;
		case 2:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:16:
			// T__28
		{
			mT__28();

		}
			break;
		case 3:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:22:
			// T__29
		{
			mT__29();

		}
			break;
		case 4:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:28:
			// T__30
		{
			mT__30();

		}
			break;
		case 5:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:34:
			// T__31
		{
			mT__31();

		}
			break;
		case 6:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:40:
			// T__32
		{
			mT__32();

		}
			break;
		case 7:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:46:
			// T__33
		{
			mT__33();

		}
			break;
		case 8:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:52:
			// T__34
		{
			mT__34();

		}
			break;
		case 9:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:58:
			// T__35
		{
			mT__35();

		}
			break;
		case 10:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:64:
			// T__36
		{
			mT__36();

		}
			break;
		case 11:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:70:
			// T__37
		{
			mT__37();

		}
			break;
		case 12:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:76:
			// T__38
		{
			mT__38();

		}
			break;
		case 13:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:82:
			// T__39
		{
			mT__39();

		}
			break;
		case 14:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:88:
			// T__40
		{
			mT__40();

		}
			break;
		case 15:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:94:
			// T__41
		{
			mT__41();

		}
			break;
		case 16:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:100:
			// T__42
		{
			mT__42();

		}
			break;
		case 17:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:106:
			// T__43
		{
			mT__43();

		}
			break;
		case 18:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:112:
			// T__44
		{
			mT__44();

		}
			break;
		case 19:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:118:
			// T__45
		{
			mT__45();

		}
			break;
		case 20:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:124:
			// T__46
		{
			mT__46();

		}
			break;
		case 21:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:130:
			// T__47
		{
			mT__47();

		}
			break;
		case 22:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:136:
			// T__48
		{
			mT__48();

		}
			break;
		case 23:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:142:
			// T__49
		{
			mT__49();

		}
			break;
		case 24:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:148:
			// T__50
		{
			mT__50();

		}
			break;
		case 25:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:154:
			// T__51
		{
			mT__51();

		}
			break;
		case 26:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:160:
			// T__52
		{
			mT__52();

		}
			break;
		case 27:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:166:
			// T__53
		{
			mT__53();

		}
			break;
		case 28:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:172:
			// T__54
		{
			mT__54();

		}
			break;
		case 29:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:178:
			// T__55
		{
			mT__55();

		}
			break;
		case 30:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:184:
			// T__56
		{
			mT__56();

		}
			break;
		case 31:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:190:
			// T__57
		{
			mT__57();

		}
			break;
		case 32:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:196:
			// T__58
		{
			mT__58();

		}
			break;
		case 33:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:202:
			// T__59
		{
			mT__59();

		}
			break;
		case 34:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:208:
			// T__60
		{
			mT__60();

		}
			break;
		case 35:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:214:
			// T__61
		{
			mT__61();

		}
			break;
		case 36:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:220:
			// T__62
		{
			mT__62();

		}
			break;
		case 37:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:226:
			// T__63
		{
			mT__63();

		}
			break;
		case 38:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:232:
			// T__64
		{
			mT__64();

		}
			break;
		case 39:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:238:
			// T__65
		{
			mT__65();

		}
			break;
		case 40:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:244:
			// T__66
		{
			mT__66();

		}
			break;
		case 41:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:250:
			// T__67
		{
			mT__67();

		}
			break;
		case 42:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:256:
			// T__68
		{
			mT__68();

		}
			break;
		case 43:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:262:
			// T__69
		{
			mT__69();

		}
			break;
		case 44:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:268:
			// T__70
		{
			mT__70();

		}
			break;
		case 45:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:274:
			// T__71
		{
			mT__71();

		}
			break;
		case 46:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:280:
			// T__72
		{
			mT__72();

		}
			break;
		case 47:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:286:
			// T__73
		{
			mT__73();

		}
			break;
		case 48:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:292:
			// T__74
		{
			mT__74();

		}
			break;
		case 49:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:298:
			// T__75
		{
			mT__75();

		}
			break;
		case 50:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:304:
			// T__76
		{
			mT__76();

		}
			break;
		case 51:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:310:
			// T__77
		{
			mT__77();

		}
			break;
		case 52:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:316:
			// T__78
		{
			mT__78();

		}
			break;
		case 53:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:322:
			// T__79
		{
			mT__79();

		}
			break;
		case 54:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:328:
			// T__80
		{
			mT__80();

		}
			break;
		case 55:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:334:
			// T__81
		{
			mT__81();

		}
			break;
		case 56:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:340:
			// T__82
		{
			mT__82();

		}
			break;
		case 57:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:346:
			// T__83
		{
			mT__83();

		}
			break;
		case 58:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:352:
			// T__84
		{
			mT__84();

		}
			break;
		case 59:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:358:
			// T__85
		{
			mT__85();

		}
			break;
		case 60:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:364:
			// T__86
		{
			mT__86();

		}
			break;
		case 61:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:370:
			// T__87
		{
			mT__87();

		}
			break;
		case 62:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:376:
			// T__88
		{
			mT__88();

		}
			break;
		case 63:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:382:
			// T__89
		{
			mT__89();

		}
			break;
		case 64:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:388:
			// T__90
		{
			mT__90();

		}
			break;
		case 65:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:394:
			// T__91
		{
			mT__91();

		}
			break;
		case 66:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:400:
			// T__92
		{
			mT__92();

		}
			break;
		case 67:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:406:
			// T__93
		{
			mT__93();

		}
			break;
		case 68:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:412:
			// T__94
		{
			mT__94();

		}
			break;
		case 69:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:418:
			// T__95
		{
			mT__95();

		}
			break;
		case 70:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:424:
			// T__96
		{
			mT__96();

		}
			break;
		case 71:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:430:
			// T__97
		{
			mT__97();

		}
			break;
		case 72:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:436:
			// T__98
		{
			mT__98();

		}
			break;
		case 73:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:442:
			// T__99
		{
			mT__99();

		}
			break;
		case 74:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:448:
			// T__100
		{
			mT__100();

		}
			break;
		case 75:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:455:
			// T__101
		{
			mT__101();

		}
			break;
		case 76:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:462:
			// T__102
		{
			mT__102();

		}
			break;
		case 77:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:469:
			// T__103
		{
			mT__103();

		}
			break;
		case 78:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:476:
			// T__104
		{
			mT__104();

		}
			break;
		case 79:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:483:
			// T__105
		{
			mT__105();

		}
			break;
		case 80:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:490:
			// T__106
		{
			mT__106();

		}
			break;
		case 81:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:497:
			// T__107
		{
			mT__107();

		}
			break;
		case 82:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:504:
			// T__108
		{
			mT__108();

		}
			break;
		case 83:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:511:
			// T__109
		{
			mT__109();

		}
			break;
		case 84:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:518:
			// T__110
		{
			mT__110();

		}
			break;
		case 85:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:525:
			// T__111
		{
			mT__111();

		}
			break;
		case 86:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:532:
			// T__112
		{
			mT__112();

		}
			break;
		case 87:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:539:
			// T__113
		{
			mT__113();

		}
			break;
		case 88:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:546:
			// T__114
		{
			mT__114();

		}
			break;
		case 89:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:553:
			// T__115
		{
			mT__115();

		}
			break;
		case 90:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:560:
			// HexLiteral
		{
			mHexLiteral();

		}
			break;
		case 91:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:571:
			// DecimalLiteral
		{
			mDecimalLiteral();

		}
			break;
		case 92:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:586:
			// OctalLiteral
		{
			mOctalLiteral();

		}
			break;
		case 93:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:599:
			// FloatingPointLiteral
		{
			mFloatingPointLiteral();

		}
			break;
		case 94:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:620:
			// CharacterLiteral
		{
			mCharacterLiteral();

		}
			break;
		case 95:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:637:
			// StringLiteral
		{
			mStringLiteral();

		}
			break;
		case 96:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:651:
			// ENUM
		{
			mENUM();

		}
			break;
		case 97:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:656:
			// ASSERT
		{
			mASSERT();

		}
			break;
		case 98:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:663:
			// Identifier
		{
			mIdentifier();

		}
			break;
		case 99:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:674:
			// WS
		{
			mWS();

		}
			break;
		case 100:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:677:
			// COMMENT
		{
			mCOMMENT();

		}
			break;
		case 101:
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:1:685:
			// LINE_COMMENT
		{
			mLINE_COMMENT();

		}
			break;

		}

	}

	protected DFA18 dfa18 = new DFA18(this);
	protected DFA29 dfa29 = new DFA29(this);
	static final String DFA18_eotS = "\6\uffff";
	static final String DFA18_eofS = "\6\uffff";
	static final String DFA18_minS = "\2\56\4\uffff";
	static final String DFA18_maxS = "\1\71\1\146\4\uffff";
	static final String DFA18_acceptS = "\2\uffff\1\2\1\4\1\3\1\1";
	static final String DFA18_specialS = "\6\uffff}>";
	static final String[] DFA18_transitionS = { "\1\2\1\uffff\12\1",
			"\1\5\1\uffff\12\1\12\uffff\1\3\1\4\1\3\35\uffff\1\3\1\4\1\3", "",
			"", "", "" };

	static final short[] DFA18_eot = DFA.unpackEncodedString(DFA18_eotS);
	static final short[] DFA18_eof = DFA.unpackEncodedString(DFA18_eofS);
	static final char[] DFA18_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA18_minS);
	static final char[] DFA18_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA18_maxS);
	static final short[] DFA18_accept = DFA.unpackEncodedString(DFA18_acceptS);
	static final short[] DFA18_special = DFA
			.unpackEncodedString(DFA18_specialS);
	static final short[][] DFA18_transition;

	static {
		int numStates = DFA18_transitionS.length;
		DFA18_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA18_transition[i] = DFA.unpackEncodedString(DFA18_transitionS[i]);
		}
	}

	private class DFA18 extends DFA {

		public DFA18(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 18;
			this.eot = DFA18_eot;
			this.eof = DFA18_eof;
			this.min = DFA18_min;
			this.max = DFA18_max;
			this.accept = DFA18_accept;
			this.special = DFA18_special;
			this.transition = DFA18_transition;
		}

		public String getDescription() {
			return "942:1: FloatingPointLiteral : ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )? | '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )? | ( '0' .. '9' )+ Exponent ( FloatTypeSuffix )? | ( '0' .. '9' )+ FloatTypeSuffix );";
		}
	}

	static final String DFA29_eotS = "\1\uffff\1\55\1\uffff\2\55\1\73\1\76\4\55\3\uffff\1\116\2\uffff"
			+ "\1\55\2\uffff\1\55\1\123\4\55\5\uffff\2\55\1\141\1\144\1\150\1\153"
			+ "\1\155\1\157\1\161\1\uffff\2\164\4\uffff\5\55\1\175\5\55\5\uffff"
			+ "\15\55\3\uffff\3\55\2\uffff\7\55\1\u00a1\3\55\24\uffff\1\u00a5\1"
			+ "\uffff\1\164\5\55\1\u00ad\1\55\1\uffff\13\55\1\u00ba\16\55\1\u00c9"
			+ "\2\55\1\u00cc\5\55\1\uffff\3\55\1\uffff\7\55\1\uffff\14\55\1\uffff"
			+ "\1\55\1\u00e9\2\55\1\u00ec\1\55\1\u00ee\1\u00ef\1\u00f0\2\55\1\u00f3"
			+ "\1\55\1\u00f5\1\uffff\1\55\1\u00f7\1\uffff\1\55\1\u00f9\1\55\1\u00fb"
			+ "\17\55\1\u010b\1\u010c\3\55\1\u0111\1\u0112\1\u0113\1\u0114\1\uffff"
			+ "\1\55\1\u0116\1\uffff\1\55\3\uffff\1\55\1\u011a\1\uffff\1\55\1\uffff"
			+ "\1\55\1\uffff\1\55\1\uffff\1\u011e\1\uffff\2\55\1\u0121\2\55\1\u0124"
			+ "\2\55\1\u0127\3\55\1\u012b\2\55\2\uffff\1\u012e\1\55\1\u0130\1\55"
			+ "\4\uffff\1\55\1\uffff\2\55\1\u0135\1\uffff\1\55\1\u0137\1\55\1\uffff"
			+ "\1\u0139\1\55\1\uffff\1\u013b\1\u013c\1\uffff\1\55\1\u013e\1\uffff"
			+ "\3\55\1\uffff\2\55\1\uffff\1\55\1\uffff\1\u0145\1\55\1\u0147\1\55"
			+ "\1\uffff\1\55\1\uffff\1\u014a\1\uffff\1\u014b\2\uffff\1\55\1\uffff"
			+ "\3\55\1\u0150\1\55\1\u0152\1\uffff\1\u0153\1\uffff\1\u0154\1\55"
			+ "\2\uffff\1\u0156\1\55\1\u0158\1\55\1\uffff\1\55\3\uffff\1\u015b"
			+ "\1\uffff\1\u015c\1\uffff\1\u015d\1\55\3\uffff\1\55\1\u0160\1\uffff";
	static final String DFA29_eofS = "\u0161\uffff";
	static final String DFA29_minS = "\1\11\1\141\1\uffff\1\146\1\150\1\56\1\75\1\142\2\141\1\154\3\uffff"
			+ "\1\46\2\uffff\1\157\2\uffff\1\150\1\75\1\141\2\157\1\145\5\uffff"
			+ "\1\150\1\145\1\53\1\55\1\52\4\75\1\uffff\2\56\4\uffff\1\143\1\142"
			+ "\1\151\1\160\1\163\1\44\1\141\1\156\1\157\1\160\1\151\5\uffff\2"
			+ "\163\1\156\1\157\1\154\1\162\2\141\1\156\1\163\1\164\1\163\1\165"
			+ "\3\uffff\2\151\1\141\2\uffff\1\164\1\154\1\167\1\157\1\164\1\145"
			+ "\1\156\1\44\1\146\1\151\1\164\24\uffff\1\56\1\uffff\1\56\1\153\1"
			+ "\154\1\164\1\166\1\154\1\44\1\164\1\uffff\1\164\1\151\1\143\1\162"
			+ "\1\145\2\164\1\145\2\141\1\163\1\44\1\163\1\162\1\164\1\143\3\145"
			+ "\1\155\1\144\1\141\1\157\1\163\1\156\1\145\1\44\1\151\1\154\1\44"
			+ "\1\154\1\145\1\141\1\147\1\142\1\uffff\1\141\1\154\1\165\1\uffff"
			+ "\1\141\1\151\1\145\1\141\1\162\1\145\1\162\1\uffff\1\141\1\151\1"
			+ "\143\1\150\1\164\1\162\1\143\2\162\1\154\1\164\1\145\1\uffff\1\163"
			+ "\1\44\1\151\1\150\1\44\1\156\3\44\1\164\1\167\1\44\1\163\1\44\1"
			+ "\uffff\1\166\1\44\1\uffff\1\145\1\44\1\153\1\44\1\154\1\165\1\145"
			+ "\1\162\1\147\2\143\2\164\1\155\1\146\1\156\1\143\1\164\1\162\2\44"
			+ "\1\150\1\141\1\164\4\44\1\uffff\1\156\1\44\1\uffff\1\144\3\uffff"
			+ "\1\151\1\44\1\uffff\1\151\1\uffff\1\145\1\uffff\1\141\1\uffff\1"
			+ "\44\1\uffff\1\145\1\154\1\44\1\156\1\145\1\44\1\164\1\145\1\44\1"
			+ "\145\1\141\1\143\1\44\1\146\1\157\2\uffff\1\44\1\143\1\44\1\171"
			+ "\4\uffff\1\165\1\uffff\1\163\1\154\1\44\1\uffff\1\145\1\44\1\156"
			+ "\1\uffff\1\44\1\164\1\uffff\2\44\1\uffff\1\145\1\44\1\uffff\1\156"
			+ "\1\143\1\145\1\uffff\1\160\1\156\1\uffff\1\164\1\uffff\1\44\1\145"
			+ "\1\44\1\145\1\uffff\1\156\1\uffff\1\44\1\uffff\1\44\2\uffff\1\144"
			+ "\1\uffff\1\164\1\145\1\157\1\44\1\151\1\44\1\uffff\1\44\1\uffff"
			+ "\1\44\1\164\2\uffff\1\44\1\163\1\44\1\146\1\uffff\1\172\3\uffff"
			+ "\1\44\1\uffff\1\44\1\uffff\1\44\1\145\3\uffff\1\144\1\44\1\uffff";
	static final String DFA29_maxS = "\1\ufaff\1\165\1\uffff\1\156\1\171\1\71\1\75\1\163\2\157\1\170\3"
			+ "\uffff\1\75\2\uffff\1\157\2\uffff\1\162\1\75\1\165\1\171\2\157\5"
			+ "\uffff\1\150\1\145\3\75\1\174\3\75\1\uffff\1\170\1\146\4\uffff\1"
			+ "\143\1\142\1\157\1\160\1\164\1\ufaff\1\162\1\156\1\157\1\160\1\151"
			+ "\5\uffff\2\163\1\156\1\157\1\154\1\162\2\141\1\156\2\164\1\163\1"
			+ "\165\3\uffff\1\154\1\162\1\171\2\uffff\1\164\1\154\1\167\1\157\1"
			+ "\164\1\145\1\156\1\ufaff\1\146\1\151\1\164\24\uffff\1\146\1\uffff"
			+ "\1\146\1\153\1\154\1\164\1\166\1\157\1\ufaff\1\164\1\uffff\1\164"
			+ "\1\151\1\143\1\162\1\145\2\164\1\145\2\141\1\163\1\ufaff\1\163\1"
			+ "\162\1\164\1\143\3\145\1\155\1\144\1\141\1\157\1\163\1\156\1\145"
			+ "\1\ufaff\1\151\1\154\1\ufaff\1\154\1\145\1\141\1\147\1\142\1\uffff"
			+ "\1\141\1\154\1\165\1\uffff\1\141\1\151\1\145\1\141\1\162\1\145\1"
			+ "\162\1\uffff\1\141\1\151\1\143\1\150\1\164\1\162\1\143\2\162\1\154"
			+ "\1\164\1\145\1\uffff\1\163\1\ufaff\1\151\1\150\1\ufaff\1\156\3\ufaff"
			+ "\1\164\1\167\1\ufaff\1\163\1\ufaff\1\uffff\1\166\1\ufaff\1\uffff"
			+ "\1\145\1\ufaff\1\153\1\ufaff\1\154\1\165\1\145\1\162\1\147\2\143"
			+ "\2\164\1\155\1\146\1\156\1\143\1\164\1\162\2\ufaff\1\150\1\141\1"
			+ "\164\4\ufaff\1\uffff\1\156\1\ufaff\1\uffff\1\144\3\uffff\1\151\1"
			+ "\ufaff\1\uffff\1\151\1\uffff\1\145\1\uffff\1\141\1\uffff\1\ufaff"
			+ "\1\uffff\1\145\1\154\1\ufaff\1\156\1\145\1\ufaff\1\164\1\145\1\ufaff"
			+ "\1\145\1\141\1\143\1\ufaff\1\146\1\157\2\uffff\1\ufaff\1\143\1\ufaff"
			+ "\1\171\4\uffff\1\165\1\uffff\1\163\1\154\1\ufaff\1\uffff\1\145\1"
			+ "\ufaff\1\156\1\uffff\1\ufaff\1\164\1\uffff\2\ufaff\1\uffff\1\145"
			+ "\1\ufaff\1\uffff\1\156\1\143\1\145\1\uffff\1\160\1\156\1\uffff\1"
			+ "\164\1\uffff\1\ufaff\1\145\1\ufaff\1\145\1\uffff\1\156\1\uffff\1"
			+ "\ufaff\1\uffff\1\ufaff\2\uffff\1\144\1\uffff\1\164\1\145\1\157\1"
			+ "\ufaff\1\151\1\ufaff\1\uffff\1\ufaff\1\uffff\1\ufaff\1\164\2\uffff"
			+ "\1\ufaff\1\163\1\ufaff\1\146\1\uffff\1\172\3\uffff\1\ufaff\1\uffff"
			+ "\1\ufaff\1\uffff\1\ufaff\1\145\3\uffff\1\144\1\ufaff\1\uffff";
	static final String DFA29_acceptS = "\2\uffff\1\2\10\uffff\1\20\1\21\1\22\1\uffff\1\24\1\25\1\uffff\1"
			+ "\30\1\31\6\uffff\1\50\1\52\1\53\1\61\1\63\11\uffff\1\127\2\uffff"
			+ "\1\136\1\137\1\142\1\143\13\uffff\1\54\1\5\1\135\1\104\1\6\15\uffff"
			+ "\1\106\1\113\1\23\3\uffff\1\116\1\33\13\uffff\1\102\1\125\1\121"
			+ "\1\103\1\126\1\122\1\105\1\144\1\145\1\123\1\107\1\112\1\114\1\110"
			+ "\1\115\1\111\1\124\1\117\1\130\1\132\1\uffff\1\133\10\uffff\1\64"
			+ "\43\uffff\1\70\3\uffff\1\134\7\uffff\1\44\14\uffff\1\66\16\uffff"
			+ "\1\71\2\uffff\1\131\34\uffff\1\41\2\uffff\1\101\1\uffff\1\65\1\140"
			+ "\1\27\2\uffff\1\55\1\uffff\1\57\1\uffff\1\56\1\uffff\1\42\1\uffff"
			+ "\1\45\17\uffff\1\43\1\51\4\uffff\1\13\1\46\1\60\1\15\1\uffff\1\100"
			+ "\3\uffff\1\75\3\uffff\1\76\2\uffff\1\67\2\uffff\1\7\2\uffff\1\3"
			+ "\3\uffff\1\4\2\uffff\1\73\1\uffff\1\141\4\uffff\1\32\1\uffff\1\34"
			+ "\1\uffff\1\47\1\uffff\1\74\1\1\1\uffff\1\11\6\uffff\1\72\1\uffff"
			+ "\1\16\2\uffff\1\40\1\62\4\uffff\1\14\1\uffff\1\12\1\77\1\37\1\uffff"
			+ "\1\10\1\uffff\1\26\2\uffff\1\36\1\17\1\120\2\uffff\1\35";
	static final String DFA29_specialS = "\u0161\uffff}>";
	static final String[] DFA29_transitionS = {
			"\2\56\1\uffff\2\56\22\uffff\1\56\1\47\1\54\1\uffff\1\55\1\46"
					+ "\1\16\1\53\1\33\1\34\1\6\1\41\1\14\1\42\1\5\1\43\1\51\11\52"
					+ "\1\36\1\2\1\13\1\25\1\15\1\32\1\35\32\55\1\22\1\uffff\1\23\1"
					+ "\45\1\55\1\uffff\1\7\1\27\1\11\1\31\1\12\1\10\2\55\1\3\2\55"
					+ "\1\30\1\55\1\26\1\55\1\1\1\55\1\40\1\4\1\24\1\55\1\21\1\37\3"
					+ "\55\1\17\1\44\1\20\1\50\101\uffff\27\55\1\uffff\37\55\1\uffff"
					+ "\u1f08\55\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff"
					+ "\u092e\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
			"\1\57\20\uffff\1\61\2\uffff\1\60",
			"",
			"\1\64\6\uffff\1\62\1\63",
			"\1\67\13\uffff\1\65\1\70\1\uffff\1\71\1\uffff\1\66",
			"\1\72\1\uffff\12\74",
			"\1\75",
			"\1\77\20\uffff\1\100",
			"\1\103\7\uffff\1\101\2\uffff\1\102\2\uffff\1\104",
			"\1\110\6\uffff\1\106\3\uffff\1\105\2\uffff\1\107",
			"\1\112\1\uffff\1\113\11\uffff\1\111",
			"",
			"",
			"",
			"\1\115\26\uffff\1\114",
			"",
			"",
			"\1\117",
			"",
			"",
			"\1\120\11\uffff\1\121",
			"\1\122",
			"\1\124\3\uffff\1\126\17\uffff\1\125",
			"\1\127\2\uffff\1\131\6\uffff\1\130",
			"\1\132",
			"\1\134\11\uffff\1\133",
			"",
			"",
			"",
			"",
			"",
			"\1\135",
			"\1\136",
			"\1\140\21\uffff\1\137",
			"\1\143\17\uffff\1\142",
			"\1\146\4\uffff\1\147\15\uffff\1\145",
			"\1\151\76\uffff\1\152",
			"\1\154",
			"\1\156",
			"\1\160",
			"",
			"\1\74\1\uffff\10\163\2\74\12\uffff\3\74\21\uffff\1\162\13\uffff"
					+ "\3\74\21\uffff\1\162",
			"\1\74\1\uffff\12\165\12\uffff\3\74\35\uffff\3\74",
			"",
			"",
			"",
			"",
			"\1\166",
			"\1\167",
			"\1\171\5\uffff\1\170",
			"\1\172",
			"\1\174\1\173",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\176\20\uffff\1\177",
			"\1\u0080",
			"\1\u0081",
			"\1\u0082",
			"\1\u0083",
			"",
			"",
			"",
			"",
			"",
			"\1\u0084",
			"\1\u0085",
			"\1\u0086",
			"\1\u0087",
			"\1\u0088",
			"\1\u0089",
			"\1\u008a",
			"\1\u008b",
			"\1\u008c",
			"\1\u008e\1\u008d",
			"\1\u008f",
			"\1\u0090",
			"\1\u0091",
			"",
			"",
			"",
			"\1\u0092\2\uffff\1\u0093",
			"\1\u0095\10\uffff\1\u0094",
			"\1\u0096\23\uffff\1\u0097\3\uffff\1\u0098",
			"",
			"",
			"\1\u0099",
			"\1\u009a",
			"\1\u009b",
			"\1\u009c",
			"\1\u009d",
			"\1\u009e",
			"\1\u009f",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\24"
					+ "\55\1\u00a0\5\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08"
					+ "\55\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e"
					+ "\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
			"\1\u00a2",
			"\1\u00a3",
			"\1\u00a4",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\74\1\uffff\10\163\2\74\12\uffff\3\74\35\uffff\3\74",
			"",
			"\1\74\1\uffff\12\165\12\uffff\3\74\35\uffff\3\74",
			"\1\u00a6",
			"\1\u00a7",
			"\1\u00a8",
			"\1\u00a9",
			"\1\u00ab\2\uffff\1\u00aa",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\4\55"
					+ "\1\u00ac\25\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55"
					+ "\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e"
					+ "\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
			"\1\u00ae",
			"",
			"\1\u00af",
			"\1\u00b0",
			"\1\u00b1",
			"\1\u00b2",
			"\1\u00b3",
			"\1\u00b4",
			"\1\u00b5",
			"\1\u00b6",
			"\1\u00b7",
			"\1\u00b8",
			"\1\u00b9",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\u00bb",
			"\1\u00bc",
			"\1\u00bd",
			"\1\u00be",
			"\1\u00bf",
			"\1\u00c0",
			"\1\u00c1",
			"\1\u00c2",
			"\1\u00c3",
			"\1\u00c4",
			"\1\u00c5",
			"\1\u00c6",
			"\1\u00c7",
			"\1\u00c8",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\u00ca",
			"\1\u00cb",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\u00cd",
			"\1\u00ce",
			"\1\u00cf",
			"\1\u00d0",
			"\1\u00d1",
			"",
			"\1\u00d2",
			"\1\u00d3",
			"\1\u00d4",
			"",
			"\1\u00d5",
			"\1\u00d6",
			"\1\u00d7",
			"\1\u00d8",
			"\1\u00d9",
			"\1\u00da",
			"\1\u00db",
			"",
			"\1\u00dc",
			"\1\u00dd",
			"\1\u00de",
			"\1\u00df",
			"\1\u00e0",
			"\1\u00e1",
			"\1\u00e2",
			"\1\u00e3",
			"\1\u00e4",
			"\1\u00e5",
			"\1\u00e6",
			"\1\u00e7",
			"",
			"\1\u00e8",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\u00ea",
			"\1\u00eb",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\u00ed",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\u00f1",
			"\1\u00f2",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\u00f4",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"",
			"\1\u00f6",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"",
			"\1\u00f8",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\u00fa",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\u00fc",
			"\1\u00fd",
			"\1\u00fe",
			"\1\u00ff",
			"\1\u0100",
			"\1\u0101",
			"\1\u0102",
			"\1\u0103",
			"\1\u0104",
			"\1\u0105",
			"\1\u0106",
			"\1\u0107",
			"\1\u0108",
			"\1\u0109",
			"\1\u010a",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\u010d",
			"\1\u010e",
			"\1\u010f",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\13"
					+ "\55\1\u0110\16\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08"
					+ "\55\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e"
					+ "\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"",
			"\1\u0115",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"",
			"\1\u0117",
			"",
			"",
			"",
			"\1\u0118",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\22"
					+ "\55\1\u0119\7\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08"
					+ "\55\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e"
					+ "\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
			"",
			"\1\u011b",
			"",
			"\1\u011c",
			"",
			"\1\u011d",
			"",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"",
			"\1\u011f",
			"\1\u0120",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\u0122",
			"\1\u0123",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\u0125",
			"\1\u0126",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\u0128",
			"\1\u0129",
			"\1\u012a",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\u012c",
			"\1\u012d",
			"",
			"",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\u012f",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\u0131",
			"",
			"",
			"",
			"",
			"\1\u0132",
			"",
			"\1\u0133",
			"\1\u0134",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"",
			"\1\u0136",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\u0138",
			"",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\u013a",
			"",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"",
			"\1\u013d",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"",
			"\1\u013f",
			"\1\u0140",
			"\1\u0141",
			"",
			"\1\u0142",
			"\1\u0143",
			"",
			"\1\u0144",
			"",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\u0146",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\u0148",
			"",
			"\1\u0149",
			"",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"",
			"",
			"\1\u014c",
			"",
			"\1\u014d",
			"\1\u014e",
			"\1\u014f",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\u0151",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\u0155",
			"",
			"",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\u0157",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\u0159",
			"",
			"\1\u015a",
			"",
			"",
			"",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55",
			"\1\u015e",
			"",
			"",
			"",
			"\1\u015f",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"
					+ "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"
					+ "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"
					+ "\u5200\55\u5900\uffff\u0200\55", "" };

	static final short[] DFA29_eot = DFA.unpackEncodedString(DFA29_eotS);
	static final short[] DFA29_eof = DFA.unpackEncodedString(DFA29_eofS);
	static final char[] DFA29_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA29_minS);
	static final char[] DFA29_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA29_maxS);
	static final short[] DFA29_accept = DFA.unpackEncodedString(DFA29_acceptS);
	static final short[] DFA29_special = DFA
			.unpackEncodedString(DFA29_specialS);
	static final short[][] DFA29_transition;

	static {
		int numStates = DFA29_transitionS.length;
		DFA29_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA29_transition[i] = DFA.unpackEncodedString(DFA29_transitionS[i]);
		}
	}

	private class DFA29 extends DFA {

		public DFA29(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 29;
			this.eot = DFA29_eot;
			this.eof = DFA29_eof;
			this.min = DFA29_min;
			this.max = DFA29_max;
			this.accept = DFA29_accept;
			this.special = DFA29_special;
			this.transition = DFA29_transition;
		}

		public String getDescription() {
			return "1:1: Tokens : ( T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | T__110 | T__111 | T__112 | T__113 | T__114 | T__115 | HexLiteral | DecimalLiteral | OctalLiteral | FloatingPointLiteral | CharacterLiteral | StringLiteral | ENUM | ASSERT | Identifier | WS | COMMENT | LINE_COMMENT );";
		}
	}

}
