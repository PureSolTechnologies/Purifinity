// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g 2009-12-16 15:50:52

package com.puresol.coding.java.antlr.output;

import org.antlr.runtime.*;
import java.util.HashMap;

import org.antlr.runtime.tree.*;

/** A Java 1.5 grammar for ANTLR v3 derived from the spec
 *
 *  This is a very close representation of the spec; the changes
 *  are comestic (remove left recursion) and also fixes (the spec
 *  isn't exactly perfect).  I have run this on the 1.4.2 source
 *  and some nasty looking enums from 1.5, but have not really
 *  tested for 1.5 compatibility.
 *
 *  I built this with: java -Xmx100M org.antlr.Tool java.g 
 *  and got two errors that are ok (for now):
 *  java.g:691:9: Decision can match input such as
 *    "'0'..'9'{'E', 'e'}{'+', '-'}'0'..'9'{'D', 'F', 'd', 'f'}"
 *    using multiple alternatives: 3, 4
 *  As a result, alternative(s) 4 were disabled for that input
 *  java.g:734:35: Decision can match input such as "{'$', 'A'..'Z',
 *    '_', 'a'..'z', '\u00C0'..'\u00D6', '\u00D8'..'\u00F6',
 *    '\u00F8'..'\u1FFF', '\u3040'..'\u318F', '\u3300'..'\u337F',
 *    '\u3400'..'\u3D2D', '\u4E00'..'\u9FFF', '\uF900'..'\uFAFF'}"
 *    using multiple alternatives: 1, 2
 *  As a result, alternative(s) 2 were disabled for that input
 *
 *  You can turn enum on/off as a keyword :)
 *
 *  Version 1.0 -- initial release July 5, 2006 (requires 3.0b2 or higher)
 *
 *  Primary author: Terence Parr, July 2006
 *
 *  Version 1.0.1 -- corrections by Koen Vanderkimpen & Marko van Dooren,
 *      October 25, 2006;
 *      fixed normalInterfaceDeclaration: now uses typeParameters instead
 *          of typeParameter (according to JLS, 3rd edition)
 *      fixed castExpression: no longer allows expression next to type
 *          (according to semantics in JLS, in contrast with syntax in JLS)
 *
 *  Version 1.0.2 -- Terence Parr, Nov 27, 2006
 *      java spec I built this from had some bizarre for-loop control.
 *          Looked weird and so I looked elsewhere...Yep, it's messed up.
 *          simplified.
 *
 *  Version 1.0.3 -- Chris Hogue, Feb 26, 2007
 *      Factored out an annotationName rule and used it in the annotation rule.
 *          Not sure why, but typeName wasn't recognizing references to inner
 *          annotations (e.g. @InterfaceName.InnerAnnotation())
 *      Factored out the elementValue section of an annotation reference.  Created 
 *          elementValuePair and elementValuePairs rules, then used them in the 
 *          annotation rule.  Allows it to recognize annotation references with 
 *          multiple, comma separated attributes.
 *      Updated elementValueArrayInitializer so that it allows multiple elements.
 *          (It was only allowing 0 or 1 element).
 *      Updated localVariableDeclaration to allow annotations.  Interestingly the JLS
 *          doesn't appear to indicate this is legal, but it does work as of at least
 *          JDK 1.5.0_06.
 *      Moved the Identifier portion of annotationTypeElementRest to annotationMethodRest.
 *          Because annotationConstantRest already references variableDeclarator which 
 *          has the Identifier portion in it, the parser would fail on constants in 
 *          annotation definitions because it expected two identifiers.  
 *      Added optional trailing ';' to the alternatives in annotationTypeElementRest.
 *          Wouldn't handle an inner interface that has a trailing ';'.
 *      Swapped the expression and type rule reference order in castExpression to 
 *          make it check for genericized casts first.  It was failing to recognize a
 *          statement like  "Class<Byte> TYPE = (Class<Byte>)...;" because it was seeing
 *          'Class<Byte' in the cast expression as a less than expression, then failing 
 *          on the '>'.
 *      Changed createdName to use typeArguments instead of nonWildcardTypeArguments.
 *          Again, JLS doesn't seem to allow this, but java.lang.Class has an example of
 *          of this construct.
 *      Changed the 'this' alternative in primary to allow 'identifierSuffix' rather than
 *          just 'arguments'.  The case it couldn't handle was a call to an explicit
 *          generic method invocation (e.g. this.<E>doSomething()).  Using identifierSuffix
 *          may be overly aggressive--perhaps should create a more constrained thisSuffix rule?
 *      
 *  Version 1.0.4 -- Hiroaki Nakamura, May 3, 2007
 *
 *  Fixed formalParameterDecls, localVariableDeclaration, forInit,
 *  and forVarControl to use variableModifier* not 'final'? (annotation)?
 *
 *  Version 1.0.5 -- Terence, June 21, 2007
 *  --a[i].foo didn't work. Fixed unaryExpression
 *
 *  Version 1.0.6 -- John Ridgway, March 17, 2008
 *      Made "assert" a switchable keyword like "enum".
 *      Fixed compilationUnit to disallow "annotation importDeclaration ...".
 *      Changed "Identifier ('.' Identifier)*" to "qualifiedName" in more 
 *          places.
 *      Changed modifier* and/or variableModifier* to classOrInterfaceModifiers,
 *          modifiers or variableModifiers, as appropriate.
 *      Renamed "bound" to "typeBound" to better match language in the JLS.
 *      Added "memberDeclaration" which rewrites to methodDeclaration or 
 *      fieldDeclaration and pulled type into memberDeclaration.  So we parse 
 *          type and then move on to decide whether we're dealing with a field
 *          or a method.
 *      Modified "constructorDeclaration" to use "constructorBody" instead of
 *          "methodBody".  constructorBody starts with explicitConstructorInvocation,
 *          then goes on to blockStatement*.  Pulling explicitConstructorInvocation
 *          out of expressions allowed me to simplify "primary".
 *      Changed variableDeclarator to simplify it.
 *      Changed type to use classOrInterfaceType, thus simplifying it; of course
 *          I then had to add classOrInterfaceType, but it is used in several 
 *          places.
 *      Fixed annotations, old version allowed "@X(y,z)", which is illegal.
 *      Added optional comma to end of "elementValueArrayInitializer"; as per JLS.
 *      Changed annotationTypeElementRest to use normalClassDeclaration and 
 *          normalInterfaceDeclaration rather than classDeclaration and 
 *          interfaceDeclaration, thus getting rid of a couple of grammar ambiguities.
 *      Split localVariableDeclaration into localVariableDeclarationStatement
 *          (includes the terminating semi-colon) and localVariableDeclaration.  
 *          This allowed me to use localVariableDeclaration in "forInit" clauses,
 *           simplifying them.
 *      Changed switchBlockStatementGroup to use multiple labels.  This adds an
 *          ambiguity, but if one uses appropriately greedy parsing it yields the
 *           parse that is closest to the meaning of the switch statement.
 *      Renamed "forVarControl" to "enhancedForControl" -- JLS language.
 *      Added semantic predicates to test for shift operations rather than other
 *          things.  Thus, for instance, the string "< <" will never be treated
 *          as a left-shift operator.
 *      In "creator" we rule out "nonWildcardTypeArguments" on arrayCreation, 
 *          which are illegal.
 *      Moved "nonWildcardTypeArguments into innerCreator.
 *      Removed 'super' superSuffix from explicitGenericInvocation, since that
 *          is only used in explicitConstructorInvocation at the beginning of a
 *           constructorBody.  (This is part of the simplification of expressions
 *           mentioned earlier.)
 *      Simplified primary (got rid of those things that are only used in
 *          explicitConstructorInvocation).
 *      Lexer -- removed "Exponent?" from FloatingPointLiteral choice 4, since it
 *          led to an ambiguity.
 *
 *      This grammar successfully parses every .java file in the JDK 1.5 source 
 *          tree (excluding those whose file names include '-', which are not
 *          valid Java compilation units).
 *
 *  Known remaining problems:
 *      "Letter" and "JavaIDDigit" are wrong.  The actual specification of
 *      "Letter" should be "a character for which the method
 *      Character.isJavaIdentifierStart(int) returns true."  A "Java 
 *      letter-or-digit is a character for which the method 
 *      Character.isJavaIdentifierPart(int) returns true."
 */
public class JavaParser extends Parser {
	public static final String[] tokenNames = new String[] { "<invalid>",
			"<EOR>", "<DOWN>", "<UP>", "CODERANGE_CLASS", "CODERANGE_METHOD",
			"Identifier", "ENUM", "FloatingPointLiteral", "CharacterLiteral",
			"StringLiteral", "HexLiteral", "OctalLiteral", "DecimalLiteral",
			"ASSERT", "HexDigit", "IntegerTypeSuffix", "Exponent",
			"FloatTypeSuffix", "EscapeSequence", "UnicodeEscape",
			"OctalEscape", "Letter", "JavaIDDigit", "WS", "COMMENT",
			"LINE_COMMENT", "'package'", "';'", "'import'", "'static'", "'.'",
			"'*'", "'public'", "'protected'", "'private'", "'abstract'",
			"'final'", "'strictfp'", "'class'", "'extends'", "'implements'",
			"'<'", "','", "'>'", "'&'", "'{'", "'}'", "'interface'", "'void'",
			"'['", "']'", "'throws'", "'='", "'native'", "'synchronized'",
			"'transient'", "'volatile'", "'boolean'", "'char'", "'byte'",
			"'short'", "'int'", "'long'", "'float'", "'double'", "'?'",
			"'super'", "'('", "')'", "'...'", "'this'", "'null'", "'true'",
			"'false'", "'@'", "'default'", "':'", "'if'", "'else'", "'for'",
			"'while'", "'do'", "'try'", "'finally'", "'switch'", "'return'",
			"'throw'", "'break'", "'continue'", "'catch'", "'case'", "'+='",
			"'-='", "'*='", "'/='", "'&='", "'|='", "'^='", "'%='", "'||'",
			"'&&'", "'|'", "'^'", "'=='", "'!='", "'instanceof'", "'+'", "'-'",
			"'/'", "'%'", "'++'", "'--'", "'~'", "'!'", "'new'" };
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
	public static final int ASSERT = 14;
	public static final int T__87 = 87;
	public static final int T__86 = 86;
	public static final int T__89 = 89;
	public static final int T__88 = 88;
	public static final int WS = 24;
	public static final int T__71 = 71;
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
	public static final int ENUM = 7;
	public static final int T__34 = 34;
	public static final int T__35 = 35;
	public static final int T__36 = 36;
	public static final int T__37 = 37;
	public static final int T__38 = 38;
	public static final int T__39 = 39;
	public static final int UnicodeEscape = 20;
	public static final int OctalEscape = 21;

	// delegates
	// delegators

	public JavaParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}

	public JavaParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
		this.state.ruleMemo = new HashMap[407 + 1];

	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}

	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}

	public String[] getTokenNames() {
		return JavaParser.tokenNames;
	}

	public String getGrammarFileName() {
		return "/home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g";
	}

	public static class compilationUnit_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "compilationUnit"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:196:1:
	// compilationUnit : ( annotations ( packageDeclaration ( importDeclaration
	// )* ( typeDeclaration )* | classOrInterfaceDeclaration ( typeDeclaration
	// )* ) | ( packageDeclaration )? ( importDeclaration )* ( typeDeclaration
	// )* );
	public final JavaParser.compilationUnit_return compilationUnit()
			throws RecognitionException {
		JavaParser.compilationUnit_return retval = new JavaParser.compilationUnit_return();
		retval.start = input.LT(1);
		int compilationUnit_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.annotations_return annotations1 = null;

		JavaParser.packageDeclaration_return packageDeclaration2 = null;

		JavaParser.importDeclaration_return importDeclaration3 = null;

		JavaParser.typeDeclaration_return typeDeclaration4 = null;

		JavaParser.classOrInterfaceDeclaration_return classOrInterfaceDeclaration5 = null;

		JavaParser.typeDeclaration_return typeDeclaration6 = null;

		JavaParser.packageDeclaration_return packageDeclaration7 = null;

		JavaParser.importDeclaration_return importDeclaration8 = null;

		JavaParser.typeDeclaration_return typeDeclaration9 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 1)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:197:5:
			// ( annotations ( packageDeclaration ( importDeclaration )* (
			// typeDeclaration )* | classOrInterfaceDeclaration (
			// typeDeclaration )* ) | ( packageDeclaration )? (
			// importDeclaration )* ( typeDeclaration )* )
			int alt8 = 2;
			alt8 = dfa8.predict(input);
			switch (alt8) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:197:9:
				// annotations ( packageDeclaration ( importDeclaration )* (
				// typeDeclaration )* | classOrInterfaceDeclaration (
				// typeDeclaration )* )
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_annotations_in_compilationUnit89);
				annotations1 = annotations();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, annotations1.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:198:9:
				// ( packageDeclaration ( importDeclaration )* ( typeDeclaration
				// )* | classOrInterfaceDeclaration ( typeDeclaration )* )
				int alt4 = 2;
				int LA4_0 = input.LA(1);

				if ((LA4_0 == 27)) {
					alt4 = 1;
				} else if ((LA4_0 == ENUM || LA4_0 == 30
						|| (LA4_0 >= 33 && LA4_0 <= 39) || LA4_0 == 48 || LA4_0 == 75)) {
					alt4 = 2;
				} else {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					NoViableAltException nvae = new NoViableAltException("", 4,
							0, input);

					throw nvae;
				}
				switch (alt4) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:198:13:
					// packageDeclaration ( importDeclaration )* (
					// typeDeclaration )*
				{
					pushFollow(FOLLOW_packageDeclaration_in_compilationUnit103);
					packageDeclaration2 = packageDeclaration();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, packageDeclaration2.getTree());
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:198:32:
					// ( importDeclaration )*
					loop1: do {
						int alt1 = 2;
						int LA1_0 = input.LA(1);

						if ((LA1_0 == 29)) {
							alt1 = 1;
						}

						switch (alt1) {
						case 1:
							// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
							// importDeclaration
						{
							pushFollow(FOLLOW_importDeclaration_in_compilationUnit105);
							importDeclaration3 = importDeclaration();

							state._fsp--;
							if (state.failed)
								return retval;
							if (state.backtracking == 0)
								adaptor.addChild(root_0, importDeclaration3
										.getTree());

						}
							break;

						default:
							break loop1;
						}
					} while (true);

					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:198:51:
					// ( typeDeclaration )*
					loop2: do {
						int alt2 = 2;
						int LA2_0 = input.LA(1);

						if ((LA2_0 == ENUM || LA2_0 == 28 || LA2_0 == 30
								|| (LA2_0 >= 33 && LA2_0 <= 39) || LA2_0 == 48 || LA2_0 == 75)) {
							alt2 = 1;
						}

						switch (alt2) {
						case 1:
							// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
							// typeDeclaration
						{
							pushFollow(FOLLOW_typeDeclaration_in_compilationUnit108);
							typeDeclaration4 = typeDeclaration();

							state._fsp--;
							if (state.failed)
								return retval;
							if (state.backtracking == 0)
								adaptor.addChild(root_0, typeDeclaration4
										.getTree());

						}
							break;

						default:
							break loop2;
						}
					} while (true);

				}
					break;
				case 2:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:199:13:
					// classOrInterfaceDeclaration ( typeDeclaration )*
				{
					pushFollow(FOLLOW_classOrInterfaceDeclaration_in_compilationUnit123);
					classOrInterfaceDeclaration5 = classOrInterfaceDeclaration();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, classOrInterfaceDeclaration5
								.getTree());
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:199:41:
					// ( typeDeclaration )*
					loop3: do {
						int alt3 = 2;
						int LA3_0 = input.LA(1);

						if ((LA3_0 == ENUM || LA3_0 == 28 || LA3_0 == 30
								|| (LA3_0 >= 33 && LA3_0 <= 39) || LA3_0 == 48 || LA3_0 == 75)) {
							alt3 = 1;
						}

						switch (alt3) {
						case 1:
							// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
							// typeDeclaration
						{
							pushFollow(FOLLOW_typeDeclaration_in_compilationUnit125);
							typeDeclaration6 = typeDeclaration();

							state._fsp--;
							if (state.failed)
								return retval;
							if (state.backtracking == 0)
								adaptor.addChild(root_0, typeDeclaration6
										.getTree());

						}
							break;

						default:
							break loop3;
						}
					} while (true);

				}
					break;

				}

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:201:9:
				// ( packageDeclaration )? ( importDeclaration )* (
				// typeDeclaration )*
			{
				root_0 = (Object) adaptor.nil();

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:201:9:
				// ( packageDeclaration )?
				int alt5 = 2;
				int LA5_0 = input.LA(1);

				if ((LA5_0 == 27)) {
					alt5 = 1;
				}
				switch (alt5) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// packageDeclaration
				{
					pushFollow(FOLLOW_packageDeclaration_in_compilationUnit146);
					packageDeclaration7 = packageDeclaration();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, packageDeclaration7.getTree());

				}
					break;

				}

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:201:29:
				// ( importDeclaration )*
				loop6: do {
					int alt6 = 2;
					int LA6_0 = input.LA(1);

					if ((LA6_0 == 29)) {
						alt6 = 1;
					}

					switch (alt6) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
						// importDeclaration
					{
						pushFollow(FOLLOW_importDeclaration_in_compilationUnit149);
						importDeclaration8 = importDeclaration();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, importDeclaration8
									.getTree());

					}
						break;

					default:
						break loop6;
					}
				} while (true);

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:201:48:
				// ( typeDeclaration )*
				loop7: do {
					int alt7 = 2;
					int LA7_0 = input.LA(1);

					if ((LA7_0 == ENUM || LA7_0 == 28 || LA7_0 == 30
							|| (LA7_0 >= 33 && LA7_0 <= 39) || LA7_0 == 48 || LA7_0 == 75)) {
						alt7 = 1;
					}

					switch (alt7) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
						// typeDeclaration
					{
						pushFollow(FOLLOW_typeDeclaration_in_compilationUnit152);
						typeDeclaration9 = typeDeclaration();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor
									.addChild(root_0, typeDeclaration9
											.getTree());

					}
						break;

					default:
						break loop7;
					}
				} while (true);

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 1, compilationUnit_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "compilationUnit"

	public static class packageDeclaration_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "packageDeclaration"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:204:1:
	// packageDeclaration : 'package' qualifiedName ';' ;
	public final JavaParser.packageDeclaration_return packageDeclaration()
			throws RecognitionException {
		JavaParser.packageDeclaration_return retval = new JavaParser.packageDeclaration_return();
		retval.start = input.LT(1);
		int packageDeclaration_StartIndex = input.index();
		Object root_0 = null;

		Token string_literal10 = null;
		Token char_literal12 = null;
		JavaParser.qualifiedName_return qualifiedName11 = null;

		Object string_literal10_tree = null;
		Object char_literal12_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 2)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:205:5:
			// ( 'package' qualifiedName ';' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:205:9:
			// 'package' qualifiedName ';'
			{
				root_0 = (Object) adaptor.nil();

				string_literal10 = (Token) match(input, 27,
						FOLLOW_27_in_packageDeclaration172);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal10_tree = (Object) adaptor
							.create(string_literal10);
					adaptor.addChild(root_0, string_literal10_tree);
				}
				pushFollow(FOLLOW_qualifiedName_in_packageDeclaration174);
				qualifiedName11 = qualifiedName();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, qualifiedName11.getTree());
				char_literal12 = (Token) match(input, 28,
						FOLLOW_28_in_packageDeclaration176);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal12_tree = (Object) adaptor
							.create(char_literal12);
					adaptor.addChild(root_0, char_literal12_tree);
				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 2, packageDeclaration_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "packageDeclaration"

	public static class importDeclaration_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "importDeclaration"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:208:1:
	// importDeclaration : 'import' ( 'static' )? qualifiedName ( '.' '*' )? ';'
	// ;
	public final JavaParser.importDeclaration_return importDeclaration()
			throws RecognitionException {
		JavaParser.importDeclaration_return retval = new JavaParser.importDeclaration_return();
		retval.start = input.LT(1);
		int importDeclaration_StartIndex = input.index();
		Object root_0 = null;

		Token string_literal13 = null;
		Token string_literal14 = null;
		Token char_literal16 = null;
		Token char_literal17 = null;
		Token char_literal18 = null;
		JavaParser.qualifiedName_return qualifiedName15 = null;

		Object string_literal13_tree = null;
		Object string_literal14_tree = null;
		Object char_literal16_tree = null;
		Object char_literal17_tree = null;
		Object char_literal18_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 3)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:209:5:
			// ( 'import' ( 'static' )? qualifiedName ( '.' '*' )? ';' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:209:9:
			// 'import' ( 'static' )? qualifiedName ( '.' '*' )? ';'
			{
				root_0 = (Object) adaptor.nil();

				string_literal13 = (Token) match(input, 29,
						FOLLOW_29_in_importDeclaration199);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal13_tree = (Object) adaptor
							.create(string_literal13);
					adaptor.addChild(root_0, string_literal13_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:209:18:
				// ( 'static' )?
				int alt9 = 2;
				int LA9_0 = input.LA(1);

				if ((LA9_0 == 30)) {
					alt9 = 1;
				}
				switch (alt9) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// 'static'
				{
					string_literal14 = (Token) match(input, 30,
							FOLLOW_30_in_importDeclaration201);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						string_literal14_tree = (Object) adaptor
								.create(string_literal14);
						adaptor.addChild(root_0, string_literal14_tree);
					}

				}
					break;

				}

				pushFollow(FOLLOW_qualifiedName_in_importDeclaration204);
				qualifiedName15 = qualifiedName();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, qualifiedName15.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:209:42:
				// ( '.' '*' )?
				int alt10 = 2;
				int LA10_0 = input.LA(1);

				if ((LA10_0 == 31)) {
					alt10 = 1;
				}
				switch (alt10) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:209:43:
					// '.' '*'
				{
					char_literal16 = (Token) match(input, 31,
							FOLLOW_31_in_importDeclaration207);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						char_literal16_tree = (Object) adaptor
								.create(char_literal16);
						adaptor.addChild(root_0, char_literal16_tree);
					}
					char_literal17 = (Token) match(input, 32,
							FOLLOW_32_in_importDeclaration209);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						char_literal17_tree = (Object) adaptor
								.create(char_literal17);
						adaptor.addChild(root_0, char_literal17_tree);
					}

				}
					break;

				}

				char_literal18 = (Token) match(input, 28,
						FOLLOW_28_in_importDeclaration213);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal18_tree = (Object) adaptor
							.create(char_literal18);
					adaptor.addChild(root_0, char_literal18_tree);
				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 3, importDeclaration_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "importDeclaration"

	public static class typeDeclaration_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "typeDeclaration"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:212:1:
	// typeDeclaration : ( classOrInterfaceDeclaration | ';' );
	public final JavaParser.typeDeclaration_return typeDeclaration()
			throws RecognitionException {
		JavaParser.typeDeclaration_return retval = new JavaParser.typeDeclaration_return();
		retval.start = input.LT(1);
		int typeDeclaration_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal20 = null;
		JavaParser.classOrInterfaceDeclaration_return classOrInterfaceDeclaration19 = null;

		Object char_literal20_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 4)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:213:5:
			// ( classOrInterfaceDeclaration | ';' )
			int alt11 = 2;
			int LA11_0 = input.LA(1);

			if ((LA11_0 == ENUM || LA11_0 == 30
					|| (LA11_0 >= 33 && LA11_0 <= 39) || LA11_0 == 48 || LA11_0 == 75)) {
				alt11 = 1;
			} else if ((LA11_0 == 28)) {
				alt11 = 2;
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 11, 0,
						input);

				throw nvae;
			}
			switch (alt11) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:213:9:
				// classOrInterfaceDeclaration
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_classOrInterfaceDeclaration_in_typeDeclaration236);
				classOrInterfaceDeclaration19 = classOrInterfaceDeclaration();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, classOrInterfaceDeclaration19
							.getTree());

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:214:9:
				// ';'
			{
				root_0 = (Object) adaptor.nil();

				char_literal20 = (Token) match(input, 28,
						FOLLOW_28_in_typeDeclaration246);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal20_tree = (Object) adaptor
							.create(char_literal20);
					adaptor.addChild(root_0, char_literal20_tree);
				}

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 4, typeDeclaration_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "typeDeclaration"

	public static class classOrInterfaceModifiers_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "classOrInterfaceModifiers"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:218:1:
	// classOrInterfaceModifiers : ( classOrInterfaceModifier )* ;
	public final JavaParser.classOrInterfaceModifiers_return classOrInterfaceModifiers()
			throws RecognitionException {
		JavaParser.classOrInterfaceModifiers_return retval = new JavaParser.classOrInterfaceModifiers_return();
		retval.start = input.LT(1);
		int classOrInterfaceModifiers_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.classOrInterfaceModifier_return classOrInterfaceModifier21 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 5)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:219:5:
			// ( ( classOrInterfaceModifier )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:219:9:
			// ( classOrInterfaceModifier )*
			{
				root_0 = (Object) adaptor.nil();

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:219:9:
				// ( classOrInterfaceModifier )*
				loop12: do {
					int alt12 = 2;
					int LA12_0 = input.LA(1);

					if ((LA12_0 == 75)) {
						int LA12_2 = input.LA(2);

						if ((LA12_2 == Identifier)) {
							alt12 = 1;
						}

					} else if ((LA12_0 == 30 || (LA12_0 >= 33 && LA12_0 <= 38))) {
						alt12 = 1;
					}

					switch (alt12) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
						// classOrInterfaceModifier
					{
						pushFollow(FOLLOW_classOrInterfaceModifier_in_classOrInterfaceModifiers270);
						classOrInterfaceModifier21 = classOrInterfaceModifier();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, classOrInterfaceModifier21
									.getTree());

					}
						break;

					default:
						break loop12;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 5, classOrInterfaceModifiers_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "classOrInterfaceModifiers"

	public static class classOrInterfaceDeclaration_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "classOrInterfaceDeclaration"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:221:1:
	// classOrInterfaceDeclaration : ( classOrInterfaceModifiers
	// classDeclaration -> ^( CODERANGE_CLASS classOrInterfaceModifiers
	// classDeclaration ) | classOrInterfaceModifiers interfaceDeclaration -> ^(
	// CODERANGE_CLASS classOrInterfaceModifiers interfaceDeclaration ) );
	public final JavaParser.classOrInterfaceDeclaration_return classOrInterfaceDeclaration()
			throws RecognitionException {
		JavaParser.classOrInterfaceDeclaration_return retval = new JavaParser.classOrInterfaceDeclaration_return();
		retval.start = input.LT(1);
		int classOrInterfaceDeclaration_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.classOrInterfaceModifiers_return classOrInterfaceModifiers22 = null;

		JavaParser.classDeclaration_return classDeclaration23 = null;

		JavaParser.classOrInterfaceModifiers_return classOrInterfaceModifiers24 = null;

		JavaParser.interfaceDeclaration_return interfaceDeclaration25 = null;

		RewriteRuleSubtreeStream stream_interfaceDeclaration = new RewriteRuleSubtreeStream(
				adaptor, "rule interfaceDeclaration");
		RewriteRuleSubtreeStream stream_classDeclaration = new RewriteRuleSubtreeStream(
				adaptor, "rule classDeclaration");
		RewriteRuleSubtreeStream stream_classOrInterfaceModifiers = new RewriteRuleSubtreeStream(
				adaptor, "rule classOrInterfaceModifiers");
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 6)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:222:5:
			// ( classOrInterfaceModifiers classDeclaration -> ^(
			// CODERANGE_CLASS classOrInterfaceModifiers classDeclaration ) |
			// classOrInterfaceModifiers interfaceDeclaration -> ^(
			// CODERANGE_CLASS classOrInterfaceModifiers interfaceDeclaration )
			// )
			int alt13 = 2;
			alt13 = dfa13.predict(input);
			switch (alt13) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:222:9:
				// classOrInterfaceModifiers classDeclaration
			{
				pushFollow(FOLLOW_classOrInterfaceModifiers_in_classOrInterfaceDeclaration289);
				classOrInterfaceModifiers22 = classOrInterfaceModifiers();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					stream_classOrInterfaceModifiers
							.add(classOrInterfaceModifiers22.getTree());
				pushFollow(FOLLOW_classDeclaration_in_classOrInterfaceDeclaration291);
				classDeclaration23 = classDeclaration();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					stream_classDeclaration.add(classDeclaration23.getTree());

				// AST REWRITE
				// elements: classDeclaration, classOrInterfaceModifiers
				// token labels:
				// rule labels: retval
				// token list labels:
				// rule list labels:
				// wildcard labels:
				if (state.backtracking == 0) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
							adaptor, "rule retval",
							retval != null ? retval.tree : null);

					root_0 = (Object) adaptor.nil();
					// 222:52: -> ^( CODERANGE_CLASS classOrInterfaceModifiers
					// classDeclaration )
					{
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:222:55:
						// ^( CODERANGE_CLASS classOrInterfaceModifiers
						// classDeclaration )
						{
							Object root_1 = (Object) adaptor.nil();
							root_1 = (Object) adaptor.becomeRoot(
									(Object) adaptor.create(CODERANGE_CLASS,
											"CODERANGE_CLASS"), root_1);

							adaptor
									.addChild(root_1,
											stream_classOrInterfaceModifiers
													.nextTree());
							adaptor.addChild(root_1, stream_classDeclaration
									.nextTree());

							adaptor.addChild(root_0, root_1);
						}

					}

					retval.tree = root_0;
				}
			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:223:8:
				// classOrInterfaceModifiers interfaceDeclaration
			{
				pushFollow(FOLLOW_classOrInterfaceModifiers_in_classOrInterfaceDeclaration310);
				classOrInterfaceModifiers24 = classOrInterfaceModifiers();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					stream_classOrInterfaceModifiers
							.add(classOrInterfaceModifiers24.getTree());
				pushFollow(FOLLOW_interfaceDeclaration_in_classOrInterfaceDeclaration312);
				interfaceDeclaration25 = interfaceDeclaration();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					stream_interfaceDeclaration.add(interfaceDeclaration25
							.getTree());

				// AST REWRITE
				// elements: interfaceDeclaration, classOrInterfaceModifiers
				// token labels:
				// rule labels: retval
				// token list labels:
				// rule list labels:
				// wildcard labels:
				if (state.backtracking == 0) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
							adaptor, "rule retval",
							retval != null ? retval.tree : null);

					root_0 = (Object) adaptor.nil();
					// 223:55: -> ^( CODERANGE_CLASS classOrInterfaceModifiers
					// interfaceDeclaration )
					{
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:223:58:
						// ^( CODERANGE_CLASS classOrInterfaceModifiers
						// interfaceDeclaration )
						{
							Object root_1 = (Object) adaptor.nil();
							root_1 = (Object) adaptor.becomeRoot(
									(Object) adaptor.create(CODERANGE_CLASS,
											"CODERANGE_CLASS"), root_1);

							adaptor
									.addChild(root_1,
											stream_classOrInterfaceModifiers
													.nextTree());
							adaptor.addChild(root_1,
									stream_interfaceDeclaration.nextTree());

							adaptor.addChild(root_0, root_1);
						}

					}

					retval.tree = root_0;
				}
			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 6, classOrInterfaceDeclaration_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "classOrInterfaceDeclaration"

	public static class classOrInterfaceModifier_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "classOrInterfaceModifier"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:227:1:
	// classOrInterfaceModifier : ( annotation | 'public' | 'protected' |
	// 'private' | 'abstract' | 'static' | 'final' | 'strictfp' );
	public final JavaParser.classOrInterfaceModifier_return classOrInterfaceModifier()
			throws RecognitionException {
		JavaParser.classOrInterfaceModifier_return retval = new JavaParser.classOrInterfaceModifier_return();
		retval.start = input.LT(1);
		int classOrInterfaceModifier_StartIndex = input.index();
		Object root_0 = null;

		Token string_literal27 = null;
		Token string_literal28 = null;
		Token string_literal29 = null;
		Token string_literal30 = null;
		Token string_literal31 = null;
		Token string_literal32 = null;
		Token string_literal33 = null;
		JavaParser.annotation_return annotation26 = null;

		Object string_literal27_tree = null;
		Object string_literal28_tree = null;
		Object string_literal29_tree = null;
		Object string_literal30_tree = null;
		Object string_literal31_tree = null;
		Object string_literal32_tree = null;
		Object string_literal33_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 7)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:228:5:
			// ( annotation | 'public' | 'protected' | 'private' | 'abstract' |
			// 'static' | 'final' | 'strictfp' )
			int alt14 = 8;
			switch (input.LA(1)) {
			case 75: {
				alt14 = 1;
			}
				break;
			case 33: {
				alt14 = 2;
			}
				break;
			case 34: {
				alt14 = 3;
			}
				break;
			case 35: {
				alt14 = 4;
			}
				break;
			case 36: {
				alt14 = 5;
			}
				break;
			case 30: {
				alt14 = 6;
			}
				break;
			case 37: {
				alt14 = 7;
			}
				break;
			case 38: {
				alt14 = 8;
			}
				break;
			default:
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 14, 0,
						input);

				throw nvae;
			}

			switch (alt14) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:228:9:
				// annotation
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_annotation_in_classOrInterfaceModifier346);
				annotation26 = annotation();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, annotation26.getTree());

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:229:9:
				// 'public'
			{
				root_0 = (Object) adaptor.nil();

				string_literal27 = (Token) match(input, 33,
						FOLLOW_33_in_classOrInterfaceModifier359);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal27_tree = (Object) adaptor
							.create(string_literal27);
					adaptor.addChild(root_0, string_literal27_tree);
				}

			}
				break;
			case 3:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:230:9:
				// 'protected'
			{
				root_0 = (Object) adaptor.nil();

				string_literal28 = (Token) match(input, 34,
						FOLLOW_34_in_classOrInterfaceModifier374);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal28_tree = (Object) adaptor
							.create(string_literal28);
					adaptor.addChild(root_0, string_literal28_tree);
				}

			}
				break;
			case 4:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:231:9:
				// 'private'
			{
				root_0 = (Object) adaptor.nil();

				string_literal29 = (Token) match(input, 35,
						FOLLOW_35_in_classOrInterfaceModifier386);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal29_tree = (Object) adaptor
							.create(string_literal29);
					adaptor.addChild(root_0, string_literal29_tree);
				}

			}
				break;
			case 5:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:232:9:
				// 'abstract'
			{
				root_0 = (Object) adaptor.nil();

				string_literal30 = (Token) match(input, 36,
						FOLLOW_36_in_classOrInterfaceModifier400);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal30_tree = (Object) adaptor
							.create(string_literal30);
					adaptor.addChild(root_0, string_literal30_tree);
				}

			}
				break;
			case 6:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:233:9:
				// 'static'
			{
				root_0 = (Object) adaptor.nil();

				string_literal31 = (Token) match(input, 30,
						FOLLOW_30_in_classOrInterfaceModifier413);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal31_tree = (Object) adaptor
							.create(string_literal31);
					adaptor.addChild(root_0, string_literal31_tree);
				}

			}
				break;
			case 7:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:234:9:
				// 'final'
			{
				root_0 = (Object) adaptor.nil();

				string_literal32 = (Token) match(input, 37,
						FOLLOW_37_in_classOrInterfaceModifier428);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal32_tree = (Object) adaptor
							.create(string_literal32);
					adaptor.addChild(root_0, string_literal32_tree);
				}

			}
				break;
			case 8:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:235:9:
				// 'strictfp'
			{
				root_0 = (Object) adaptor.nil();

				string_literal33 = (Token) match(input, 38,
						FOLLOW_38_in_classOrInterfaceModifier444);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal33_tree = (Object) adaptor
							.create(string_literal33);
					adaptor.addChild(root_0, string_literal33_tree);
				}

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 7, classOrInterfaceModifier_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "classOrInterfaceModifier"

	public static class modifiers_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "modifiers"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:238:1:
	// modifiers : ( modifier )* ;
	public final JavaParser.modifiers_return modifiers()
			throws RecognitionException {
		JavaParser.modifiers_return retval = new JavaParser.modifiers_return();
		retval.start = input.LT(1);
		int modifiers_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.modifier_return modifier34 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 8)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:239:5:
			// ( ( modifier )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:239:9:
			// ( modifier )*
			{
				root_0 = (Object) adaptor.nil();

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:239:9:
				// ( modifier )*
				loop15: do {
					int alt15 = 2;
					int LA15_0 = input.LA(1);

					if ((LA15_0 == 75)) {
						int LA15_2 = input.LA(2);

						if ((LA15_2 == Identifier)) {
							alt15 = 1;
						}

					} else if ((LA15_0 == 30 || (LA15_0 >= 33 && LA15_0 <= 38) || (LA15_0 >= 54 && LA15_0 <= 57))) {
						alt15 = 1;
					}

					switch (alt15) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
						// modifier
					{
						pushFollow(FOLLOW_modifier_in_modifiers466);
						modifier34 = modifier();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, modifier34.getTree());

					}
						break;

					default:
						break loop15;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 8, modifiers_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "modifiers"

	public static class classDeclaration_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "classDeclaration"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:242:1:
	// classDeclaration : ( normalClassDeclaration | enumDeclaration );
	public final JavaParser.classDeclaration_return classDeclaration()
			throws RecognitionException {
		JavaParser.classDeclaration_return retval = new JavaParser.classDeclaration_return();
		retval.start = input.LT(1);
		int classDeclaration_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.normalClassDeclaration_return normalClassDeclaration35 = null;

		JavaParser.enumDeclaration_return enumDeclaration36 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 9)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:243:5:
			// ( normalClassDeclaration | enumDeclaration )
			int alt16 = 2;
			int LA16_0 = input.LA(1);

			if ((LA16_0 == 39)) {
				alt16 = 1;
			} else if ((LA16_0 == ENUM)) {
				alt16 = 2;
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 16, 0,
						input);

				throw nvae;
			}
			switch (alt16) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:243:9:
				// normalClassDeclaration
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_normalClassDeclaration_in_classDeclaration486);
				normalClassDeclaration35 = normalClassDeclaration();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor
							.addChild(root_0, normalClassDeclaration35
									.getTree());

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:244:9:
				// enumDeclaration
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_enumDeclaration_in_classDeclaration496);
				enumDeclaration36 = enumDeclaration();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, enumDeclaration36.getTree());

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 9, classDeclaration_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "classDeclaration"

	public static class normalClassDeclaration_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "normalClassDeclaration"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:247:1:
	// normalClassDeclaration : 'class' Identifier ( typeParameters )? (
	// 'extends' type )? ( 'implements' typeList )? classBody ;
	public final JavaParser.normalClassDeclaration_return normalClassDeclaration()
			throws RecognitionException {
		JavaParser.normalClassDeclaration_return retval = new JavaParser.normalClassDeclaration_return();
		retval.start = input.LT(1);
		int normalClassDeclaration_StartIndex = input.index();
		Object root_0 = null;

		Token string_literal37 = null;
		Token Identifier38 = null;
		Token string_literal40 = null;
		Token string_literal42 = null;
		JavaParser.typeParameters_return typeParameters39 = null;

		JavaParser.type_return type41 = null;

		JavaParser.typeList_return typeList43 = null;

		JavaParser.classBody_return classBody44 = null;

		Object string_literal37_tree = null;
		Object Identifier38_tree = null;
		Object string_literal40_tree = null;
		Object string_literal42_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 10)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:248:5:
			// ( 'class' Identifier ( typeParameters )? ( 'extends' type )? (
			// 'implements' typeList )? classBody )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:248:9:
			// 'class' Identifier ( typeParameters )? ( 'extends' type )? (
			// 'implements' typeList )? classBody
			{
				root_0 = (Object) adaptor.nil();

				string_literal37 = (Token) match(input, 39,
						FOLLOW_39_in_normalClassDeclaration519);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal37_tree = (Object) adaptor
							.create(string_literal37);
					adaptor.addChild(root_0, string_literal37_tree);
				}
				Identifier38 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_normalClassDeclaration521);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier38_tree = (Object) adaptor.create(Identifier38);
					adaptor.addChild(root_0, Identifier38_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:248:28:
				// ( typeParameters )?
				int alt17 = 2;
				int LA17_0 = input.LA(1);

				if ((LA17_0 == 42)) {
					alt17 = 1;
				}
				switch (alt17) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// typeParameters
				{
					pushFollow(FOLLOW_typeParameters_in_normalClassDeclaration523);
					typeParameters39 = typeParameters();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, typeParameters39.getTree());

				}
					break;

				}

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:249:9:
				// ( 'extends' type )?
				int alt18 = 2;
				int LA18_0 = input.LA(1);

				if ((LA18_0 == 40)) {
					alt18 = 1;
				}
				switch (alt18) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:249:10:
					// 'extends' type
				{
					string_literal40 = (Token) match(input, 40,
							FOLLOW_40_in_normalClassDeclaration535);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						string_literal40_tree = (Object) adaptor
								.create(string_literal40);
						adaptor.addChild(root_0, string_literal40_tree);
					}
					pushFollow(FOLLOW_type_in_normalClassDeclaration537);
					type41 = type();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, type41.getTree());

				}
					break;

				}

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:250:9:
				// ( 'implements' typeList )?
				int alt19 = 2;
				int LA19_0 = input.LA(1);

				if ((LA19_0 == 41)) {
					alt19 = 1;
				}
				switch (alt19) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:250:10:
					// 'implements' typeList
				{
					string_literal42 = (Token) match(input, 41,
							FOLLOW_41_in_normalClassDeclaration550);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						string_literal42_tree = (Object) adaptor
								.create(string_literal42);
						adaptor.addChild(root_0, string_literal42_tree);
					}
					pushFollow(FOLLOW_typeList_in_normalClassDeclaration552);
					typeList43 = typeList();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, typeList43.getTree());

				}
					break;

				}

				pushFollow(FOLLOW_classBody_in_normalClassDeclaration564);
				classBody44 = classBody();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, classBody44.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 10, normalClassDeclaration_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "normalClassDeclaration"

	public static class typeParameters_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "typeParameters"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:254:1:
	// typeParameters : '<' typeParameter ( ',' typeParameter )* '>' ;
	public final JavaParser.typeParameters_return typeParameters()
			throws RecognitionException {
		JavaParser.typeParameters_return retval = new JavaParser.typeParameters_return();
		retval.start = input.LT(1);
		int typeParameters_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal45 = null;
		Token char_literal47 = null;
		Token char_literal49 = null;
		JavaParser.typeParameter_return typeParameter46 = null;

		JavaParser.typeParameter_return typeParameter48 = null;

		Object char_literal45_tree = null;
		Object char_literal47_tree = null;
		Object char_literal49_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 11)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:255:5:
			// ( '<' typeParameter ( ',' typeParameter )* '>' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:255:9:
			// '<' typeParameter ( ',' typeParameter )* '>'
			{
				root_0 = (Object) adaptor.nil();

				char_literal45 = (Token) match(input, 42,
						FOLLOW_42_in_typeParameters587);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal45_tree = (Object) adaptor
							.create(char_literal45);
					adaptor.addChild(root_0, char_literal45_tree);
				}
				pushFollow(FOLLOW_typeParameter_in_typeParameters589);
				typeParameter46 = typeParameter();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, typeParameter46.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:255:27:
				// ( ',' typeParameter )*
				loop20: do {
					int alt20 = 2;
					int LA20_0 = input.LA(1);

					if ((LA20_0 == 43)) {
						alt20 = 1;
					}

					switch (alt20) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:255:28:
						// ',' typeParameter
					{
						char_literal47 = (Token) match(input, 43,
								FOLLOW_43_in_typeParameters592);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal47_tree = (Object) adaptor
									.create(char_literal47);
							adaptor.addChild(root_0, char_literal47_tree);
						}
						pushFollow(FOLLOW_typeParameter_in_typeParameters594);
						typeParameter48 = typeParameter();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, typeParameter48.getTree());

					}
						break;

					default:
						break loop20;
					}
				} while (true);

				char_literal49 = (Token) match(input, 44,
						FOLLOW_44_in_typeParameters598);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal49_tree = (Object) adaptor
							.create(char_literal49);
					adaptor.addChild(root_0, char_literal49_tree);
				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 11, typeParameters_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "typeParameters"

	public static class typeParameter_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "typeParameter"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:258:1:
	// typeParameter : Identifier ( 'extends' typeBound )? ;
	public final JavaParser.typeParameter_return typeParameter()
			throws RecognitionException {
		JavaParser.typeParameter_return retval = new JavaParser.typeParameter_return();
		retval.start = input.LT(1);
		int typeParameter_StartIndex = input.index();
		Object root_0 = null;

		Token Identifier50 = null;
		Token string_literal51 = null;
		JavaParser.typeBound_return typeBound52 = null;

		Object Identifier50_tree = null;
		Object string_literal51_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 12)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:259:5:
			// ( Identifier ( 'extends' typeBound )? )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:259:9:
			// Identifier ( 'extends' typeBound )?
			{
				root_0 = (Object) adaptor.nil();

				Identifier50 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_typeParameter617);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier50_tree = (Object) adaptor.create(Identifier50);
					adaptor.addChild(root_0, Identifier50_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:259:20:
				// ( 'extends' typeBound )?
				int alt21 = 2;
				int LA21_0 = input.LA(1);

				if ((LA21_0 == 40)) {
					alt21 = 1;
				}
				switch (alt21) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:259:21:
					// 'extends' typeBound
				{
					string_literal51 = (Token) match(input, 40,
							FOLLOW_40_in_typeParameter620);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						string_literal51_tree = (Object) adaptor
								.create(string_literal51);
						adaptor.addChild(root_0, string_literal51_tree);
					}
					pushFollow(FOLLOW_typeBound_in_typeParameter622);
					typeBound52 = typeBound();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, typeBound52.getTree());

				}
					break;

				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 12, typeParameter_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "typeParameter"

	public static class typeBound_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "typeBound"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:262:1:
	// typeBound : type ( '&' type )* ;
	public final JavaParser.typeBound_return typeBound()
			throws RecognitionException {
		JavaParser.typeBound_return retval = new JavaParser.typeBound_return();
		retval.start = input.LT(1);
		int typeBound_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal54 = null;
		JavaParser.type_return type53 = null;

		JavaParser.type_return type55 = null;

		Object char_literal54_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 13)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:263:5:
			// ( type ( '&' type )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:263:9:
			// type ( '&' type )*
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_type_in_typeBound651);
				type53 = type();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, type53.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:263:14:
				// ( '&' type )*
				loop22: do {
					int alt22 = 2;
					int LA22_0 = input.LA(1);

					if ((LA22_0 == 45)) {
						alt22 = 1;
					}

					switch (alt22) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:263:15:
						// '&' type
					{
						char_literal54 = (Token) match(input, 45,
								FOLLOW_45_in_typeBound654);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal54_tree = (Object) adaptor
									.create(char_literal54);
							adaptor.addChild(root_0, char_literal54_tree);
						}
						pushFollow(FOLLOW_type_in_typeBound656);
						type55 = type();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, type55.getTree());

					}
						break;

					default:
						break loop22;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 13, typeBound_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "typeBound"

	public static class enumDeclaration_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "enumDeclaration"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:266:1:
	// enumDeclaration : ENUM Identifier ( 'implements' typeList )? enumBody ;
	public final JavaParser.enumDeclaration_return enumDeclaration()
			throws RecognitionException {
		JavaParser.enumDeclaration_return retval = new JavaParser.enumDeclaration_return();
		retval.start = input.LT(1);
		int enumDeclaration_StartIndex = input.index();
		Object root_0 = null;

		Token ENUM56 = null;
		Token Identifier57 = null;
		Token string_literal58 = null;
		JavaParser.typeList_return typeList59 = null;

		JavaParser.enumBody_return enumBody60 = null;

		Object ENUM56_tree = null;
		Object Identifier57_tree = null;
		Object string_literal58_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 14)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:267:5:
			// ( ENUM Identifier ( 'implements' typeList )? enumBody )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:267:9:
			// ENUM Identifier ( 'implements' typeList )? enumBody
			{
				root_0 = (Object) adaptor.nil();

				ENUM56 = (Token) match(input, ENUM,
						FOLLOW_ENUM_in_enumDeclaration677);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					ENUM56_tree = (Object) adaptor.create(ENUM56);
					adaptor.addChild(root_0, ENUM56_tree);
				}
				Identifier57 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_enumDeclaration679);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier57_tree = (Object) adaptor.create(Identifier57);
					adaptor.addChild(root_0, Identifier57_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:267:25:
				// ( 'implements' typeList )?
				int alt23 = 2;
				int LA23_0 = input.LA(1);

				if ((LA23_0 == 41)) {
					alt23 = 1;
				}
				switch (alt23) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:267:26:
					// 'implements' typeList
				{
					string_literal58 = (Token) match(input, 41,
							FOLLOW_41_in_enumDeclaration682);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						string_literal58_tree = (Object) adaptor
								.create(string_literal58);
						adaptor.addChild(root_0, string_literal58_tree);
					}
					pushFollow(FOLLOW_typeList_in_enumDeclaration684);
					typeList59 = typeList();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, typeList59.getTree());

				}
					break;

				}

				pushFollow(FOLLOW_enumBody_in_enumDeclaration688);
				enumBody60 = enumBody();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, enumBody60.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 14, enumDeclaration_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "enumDeclaration"

	public static class enumBody_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "enumBody"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:270:1:
	// enumBody : '{' ( enumConstants )? ( ',' )? ( enumBodyDeclarations )? '}'
	// ;
	public final JavaParser.enumBody_return enumBody()
			throws RecognitionException {
		JavaParser.enumBody_return retval = new JavaParser.enumBody_return();
		retval.start = input.LT(1);
		int enumBody_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal61 = null;
		Token char_literal63 = null;
		Token char_literal65 = null;
		JavaParser.enumConstants_return enumConstants62 = null;

		JavaParser.enumBodyDeclarations_return enumBodyDeclarations64 = null;

		Object char_literal61_tree = null;
		Object char_literal63_tree = null;
		Object char_literal65_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 15)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:271:5:
			// ( '{' ( enumConstants )? ( ',' )? ( enumBodyDeclarations )? '}' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:271:9:
			// '{' ( enumConstants )? ( ',' )? ( enumBodyDeclarations )? '}'
			{
				root_0 = (Object) adaptor.nil();

				char_literal61 = (Token) match(input, 46,
						FOLLOW_46_in_enumBody707);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal61_tree = (Object) adaptor
							.create(char_literal61);
					adaptor.addChild(root_0, char_literal61_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:271:13:
				// ( enumConstants )?
				int alt24 = 2;
				int LA24_0 = input.LA(1);

				if ((LA24_0 == Identifier || LA24_0 == 75)) {
					alt24 = 1;
				}
				switch (alt24) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// enumConstants
				{
					pushFollow(FOLLOW_enumConstants_in_enumBody709);
					enumConstants62 = enumConstants();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, enumConstants62.getTree());

				}
					break;

				}

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:271:28:
				// ( ',' )?
				int alt25 = 2;
				int LA25_0 = input.LA(1);

				if ((LA25_0 == 43)) {
					alt25 = 1;
				}
				switch (alt25) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// ','
				{
					char_literal63 = (Token) match(input, 43,
							FOLLOW_43_in_enumBody712);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						char_literal63_tree = (Object) adaptor
								.create(char_literal63);
						adaptor.addChild(root_0, char_literal63_tree);
					}

				}
					break;

				}

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:271:33:
				// ( enumBodyDeclarations )?
				int alt26 = 2;
				int LA26_0 = input.LA(1);

				if ((LA26_0 == 28)) {
					alt26 = 1;
				}
				switch (alt26) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// enumBodyDeclarations
				{
					pushFollow(FOLLOW_enumBodyDeclarations_in_enumBody715);
					enumBodyDeclarations64 = enumBodyDeclarations();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, enumBodyDeclarations64
								.getTree());

				}
					break;

				}

				char_literal65 = (Token) match(input, 47,
						FOLLOW_47_in_enumBody718);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal65_tree = (Object) adaptor
							.create(char_literal65);
					adaptor.addChild(root_0, char_literal65_tree);
				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 15, enumBody_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "enumBody"

	public static class enumConstants_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "enumConstants"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:274:1:
	// enumConstants : enumConstant ( ',' enumConstant )* ;
	public final JavaParser.enumConstants_return enumConstants()
			throws RecognitionException {
		JavaParser.enumConstants_return retval = new JavaParser.enumConstants_return();
		retval.start = input.LT(1);
		int enumConstants_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal67 = null;
		JavaParser.enumConstant_return enumConstant66 = null;

		JavaParser.enumConstant_return enumConstant68 = null;

		Object char_literal67_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 16)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:275:5:
			// ( enumConstant ( ',' enumConstant )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:275:9:
			// enumConstant ( ',' enumConstant )*
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_enumConstant_in_enumConstants737);
				enumConstant66 = enumConstant();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, enumConstant66.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:275:22:
				// ( ',' enumConstant )*
				loop27: do {
					int alt27 = 2;
					int LA27_0 = input.LA(1);

					if ((LA27_0 == 43)) {
						int LA27_1 = input.LA(2);

						if ((LA27_1 == Identifier || LA27_1 == 75)) {
							alt27 = 1;
						}

					}

					switch (alt27) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:275:23:
						// ',' enumConstant
					{
						char_literal67 = (Token) match(input, 43,
								FOLLOW_43_in_enumConstants740);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal67_tree = (Object) adaptor
									.create(char_literal67);
							adaptor.addChild(root_0, char_literal67_tree);
						}
						pushFollow(FOLLOW_enumConstant_in_enumConstants742);
						enumConstant68 = enumConstant();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, enumConstant68.getTree());

					}
						break;

					default:
						break loop27;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 16, enumConstants_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "enumConstants"

	public static class enumConstant_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "enumConstant"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:278:1:
	// enumConstant : ( annotations )? Identifier ( arguments )? ( classBody )?
	// ;
	public final JavaParser.enumConstant_return enumConstant()
			throws RecognitionException {
		JavaParser.enumConstant_return retval = new JavaParser.enumConstant_return();
		retval.start = input.LT(1);
		int enumConstant_StartIndex = input.index();
		Object root_0 = null;

		Token Identifier70 = null;
		JavaParser.annotations_return annotations69 = null;

		JavaParser.arguments_return arguments71 = null;

		JavaParser.classBody_return classBody72 = null;

		Object Identifier70_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 17)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:279:5:
			// ( ( annotations )? Identifier ( arguments )? ( classBody )? )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:279:9:
			// ( annotations )? Identifier ( arguments )? ( classBody )?
			{
				root_0 = (Object) adaptor.nil();

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:279:9:
				// ( annotations )?
				int alt28 = 2;
				int LA28_0 = input.LA(1);

				if ((LA28_0 == 75)) {
					alt28 = 1;
				}
				switch (alt28) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// annotations
				{
					pushFollow(FOLLOW_annotations_in_enumConstant767);
					annotations69 = annotations();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, annotations69.getTree());

				}
					break;

				}

				Identifier70 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_enumConstant770);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier70_tree = (Object) adaptor.create(Identifier70);
					adaptor.addChild(root_0, Identifier70_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:279:33:
				// ( arguments )?
				int alt29 = 2;
				int LA29_0 = input.LA(1);

				if ((LA29_0 == 68)) {
					alt29 = 1;
				}
				switch (alt29) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// arguments
				{
					pushFollow(FOLLOW_arguments_in_enumConstant772);
					arguments71 = arguments();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, arguments71.getTree());

				}
					break;

				}

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:279:44:
				// ( classBody )?
				int alt30 = 2;
				int LA30_0 = input.LA(1);

				if ((LA30_0 == 46)) {
					alt30 = 1;
				}
				switch (alt30) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// classBody
				{
					pushFollow(FOLLOW_classBody_in_enumConstant775);
					classBody72 = classBody();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, classBody72.getTree());

				}
					break;

				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 17, enumConstant_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "enumConstant"

	public static class enumBodyDeclarations_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "enumBodyDeclarations"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:282:1:
	// enumBodyDeclarations : ';' ( classBodyDeclaration )* ;
	public final JavaParser.enumBodyDeclarations_return enumBodyDeclarations()
			throws RecognitionException {
		JavaParser.enumBodyDeclarations_return retval = new JavaParser.enumBodyDeclarations_return();
		retval.start = input.LT(1);
		int enumBodyDeclarations_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal73 = null;
		JavaParser.classBodyDeclaration_return classBodyDeclaration74 = null;

		Object char_literal73_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 18)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:283:5:
			// ( ';' ( classBodyDeclaration )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:283:9:
			// ';' ( classBodyDeclaration )*
			{
				root_0 = (Object) adaptor.nil();

				char_literal73 = (Token) match(input, 28,
						FOLLOW_28_in_enumBodyDeclarations799);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal73_tree = (Object) adaptor
							.create(char_literal73);
					adaptor.addChild(root_0, char_literal73_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:283:13:
				// ( classBodyDeclaration )*
				loop31: do {
					int alt31 = 2;
					int LA31_0 = input.LA(1);

					if (((LA31_0 >= Identifier && LA31_0 <= ENUM)
							|| LA31_0 == 28 || LA31_0 == 30
							|| (LA31_0 >= 33 && LA31_0 <= 39) || LA31_0 == 42
							|| LA31_0 == 46 || (LA31_0 >= 48 && LA31_0 <= 49)
							|| (LA31_0 >= 54 && LA31_0 <= 65) || LA31_0 == 75)) {
						alt31 = 1;
					}

					switch (alt31) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:283:14:
						// classBodyDeclaration
					{
						pushFollow(FOLLOW_classBodyDeclaration_in_enumBodyDeclarations802);
						classBodyDeclaration74 = classBodyDeclaration();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, classBodyDeclaration74
									.getTree());

					}
						break;

					default:
						break loop31;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 18, enumBodyDeclarations_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "enumBodyDeclarations"

	public static class interfaceDeclaration_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "interfaceDeclaration"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:286:1:
	// interfaceDeclaration : ( normalInterfaceDeclaration |
	// annotationTypeDeclaration );
	public final JavaParser.interfaceDeclaration_return interfaceDeclaration()
			throws RecognitionException {
		JavaParser.interfaceDeclaration_return retval = new JavaParser.interfaceDeclaration_return();
		retval.start = input.LT(1);
		int interfaceDeclaration_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.normalInterfaceDeclaration_return normalInterfaceDeclaration75 = null;

		JavaParser.annotationTypeDeclaration_return annotationTypeDeclaration76 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 19)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:287:5:
			// ( normalInterfaceDeclaration | annotationTypeDeclaration )
			int alt32 = 2;
			int LA32_0 = input.LA(1);

			if ((LA32_0 == 48)) {
				alt32 = 1;
			} else if ((LA32_0 == 75)) {
				alt32 = 2;
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 32, 0,
						input);

				throw nvae;
			}
			switch (alt32) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:287:9:
				// normalInterfaceDeclaration
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_normalInterfaceDeclaration_in_interfaceDeclaration827);
				normalInterfaceDeclaration75 = normalInterfaceDeclaration();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, normalInterfaceDeclaration75
							.getTree());

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:288:9:
				// annotationTypeDeclaration
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_annotationTypeDeclaration_in_interfaceDeclaration837);
				annotationTypeDeclaration76 = annotationTypeDeclaration();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, annotationTypeDeclaration76
							.getTree());

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 19, interfaceDeclaration_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "interfaceDeclaration"

	public static class normalInterfaceDeclaration_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "normalInterfaceDeclaration"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:291:1:
	// normalInterfaceDeclaration : 'interface' Identifier ( typeParameters )? (
	// 'extends' typeList )? interfaceBody ;
	public final JavaParser.normalInterfaceDeclaration_return normalInterfaceDeclaration()
			throws RecognitionException {
		JavaParser.normalInterfaceDeclaration_return retval = new JavaParser.normalInterfaceDeclaration_return();
		retval.start = input.LT(1);
		int normalInterfaceDeclaration_StartIndex = input.index();
		Object root_0 = null;

		Token string_literal77 = null;
		Token Identifier78 = null;
		Token string_literal80 = null;
		JavaParser.typeParameters_return typeParameters79 = null;

		JavaParser.typeList_return typeList81 = null;

		JavaParser.interfaceBody_return interfaceBody82 = null;

		Object string_literal77_tree = null;
		Object Identifier78_tree = null;
		Object string_literal80_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 20)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:292:5:
			// ( 'interface' Identifier ( typeParameters )? ( 'extends' typeList
			// )? interfaceBody )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:292:9:
			// 'interface' Identifier ( typeParameters )? ( 'extends' typeList
			// )? interfaceBody
			{
				root_0 = (Object) adaptor.nil();

				string_literal77 = (Token) match(input, 48,
						FOLLOW_48_in_normalInterfaceDeclaration860);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal77_tree = (Object) adaptor
							.create(string_literal77);
					adaptor.addChild(root_0, string_literal77_tree);
				}
				Identifier78 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_normalInterfaceDeclaration862);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier78_tree = (Object) adaptor.create(Identifier78);
					adaptor.addChild(root_0, Identifier78_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:292:32:
				// ( typeParameters )?
				int alt33 = 2;
				int LA33_0 = input.LA(1);

				if ((LA33_0 == 42)) {
					alt33 = 1;
				}
				switch (alt33) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// typeParameters
				{
					pushFollow(FOLLOW_typeParameters_in_normalInterfaceDeclaration864);
					typeParameters79 = typeParameters();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, typeParameters79.getTree());

				}
					break;

				}

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:292:48:
				// ( 'extends' typeList )?
				int alt34 = 2;
				int LA34_0 = input.LA(1);

				if ((LA34_0 == 40)) {
					alt34 = 1;
				}
				switch (alt34) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:292:49:
					// 'extends' typeList
				{
					string_literal80 = (Token) match(input, 40,
							FOLLOW_40_in_normalInterfaceDeclaration868);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						string_literal80_tree = (Object) adaptor
								.create(string_literal80);
						adaptor.addChild(root_0, string_literal80_tree);
					}
					pushFollow(FOLLOW_typeList_in_normalInterfaceDeclaration870);
					typeList81 = typeList();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, typeList81.getTree());

				}
					break;

				}

				pushFollow(FOLLOW_interfaceBody_in_normalInterfaceDeclaration874);
				interfaceBody82 = interfaceBody();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, interfaceBody82.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 20, normalInterfaceDeclaration_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "normalInterfaceDeclaration"

	public static class typeList_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "typeList"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:295:1:
	// typeList : type ( ',' type )* ;
	public final JavaParser.typeList_return typeList()
			throws RecognitionException {
		JavaParser.typeList_return retval = new JavaParser.typeList_return();
		retval.start = input.LT(1);
		int typeList_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal84 = null;
		JavaParser.type_return type83 = null;

		JavaParser.type_return type85 = null;

		Object char_literal84_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 21)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:296:5:
			// ( type ( ',' type )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:296:9:
			// type ( ',' type )*
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_type_in_typeList897);
				type83 = type();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, type83.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:296:14:
				// ( ',' type )*
				loop35: do {
					int alt35 = 2;
					int LA35_0 = input.LA(1);

					if ((LA35_0 == 43)) {
						alt35 = 1;
					}

					switch (alt35) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:296:15:
						// ',' type
					{
						char_literal84 = (Token) match(input, 43,
								FOLLOW_43_in_typeList900);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal84_tree = (Object) adaptor
									.create(char_literal84);
							adaptor.addChild(root_0, char_literal84_tree);
						}
						pushFollow(FOLLOW_type_in_typeList902);
						type85 = type();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, type85.getTree());

					}
						break;

					default:
						break loop35;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 21, typeList_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "typeList"

	public static class classBody_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "classBody"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:299:1:
	// classBody : '{' ( classBodyDeclaration )* '}' ;
	public final JavaParser.classBody_return classBody()
			throws RecognitionException {
		JavaParser.classBody_return retval = new JavaParser.classBody_return();
		retval.start = input.LT(1);
		int classBody_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal86 = null;
		Token char_literal88 = null;
		JavaParser.classBodyDeclaration_return classBodyDeclaration87 = null;

		Object char_literal86_tree = null;
		Object char_literal88_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 22)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:300:5:
			// ( '{' ( classBodyDeclaration )* '}' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:300:9:
			// '{' ( classBodyDeclaration )* '}'
			{
				root_0 = (Object) adaptor.nil();

				char_literal86 = (Token) match(input, 46,
						FOLLOW_46_in_classBody927);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal86_tree = (Object) adaptor
							.create(char_literal86);
					adaptor.addChild(root_0, char_literal86_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:300:13:
				// ( classBodyDeclaration )*
				loop36: do {
					int alt36 = 2;
					int LA36_0 = input.LA(1);

					if (((LA36_0 >= Identifier && LA36_0 <= ENUM)
							|| LA36_0 == 28 || LA36_0 == 30
							|| (LA36_0 >= 33 && LA36_0 <= 39) || LA36_0 == 42
							|| LA36_0 == 46 || (LA36_0 >= 48 && LA36_0 <= 49)
							|| (LA36_0 >= 54 && LA36_0 <= 65) || LA36_0 == 75)) {
						alt36 = 1;
					}

					switch (alt36) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
						// classBodyDeclaration
					{
						pushFollow(FOLLOW_classBodyDeclaration_in_classBody929);
						classBodyDeclaration87 = classBodyDeclaration();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, classBodyDeclaration87
									.getTree());

					}
						break;

					default:
						break loop36;
					}
				} while (true);

				char_literal88 = (Token) match(input, 47,
						FOLLOW_47_in_classBody932);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal88_tree = (Object) adaptor
							.create(char_literal88);
					adaptor.addChild(root_0, char_literal88_tree);
				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 22, classBody_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "classBody"

	public static class interfaceBody_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "interfaceBody"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:303:1:
	// interfaceBody : '{' ( interfaceBodyDeclaration )* '}' ;
	public final JavaParser.interfaceBody_return interfaceBody()
			throws RecognitionException {
		JavaParser.interfaceBody_return retval = new JavaParser.interfaceBody_return();
		retval.start = input.LT(1);
		int interfaceBody_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal89 = null;
		Token char_literal91 = null;
		JavaParser.interfaceBodyDeclaration_return interfaceBodyDeclaration90 = null;

		Object char_literal89_tree = null;
		Object char_literal91_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 23)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:304:5:
			// ( '{' ( interfaceBodyDeclaration )* '}' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:304:9:
			// '{' ( interfaceBodyDeclaration )* '}'
			{
				root_0 = (Object) adaptor.nil();

				char_literal89 = (Token) match(input, 46,
						FOLLOW_46_in_interfaceBody955);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal89_tree = (Object) adaptor
							.create(char_literal89);
					adaptor.addChild(root_0, char_literal89_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:304:13:
				// ( interfaceBodyDeclaration )*
				loop37: do {
					int alt37 = 2;
					int LA37_0 = input.LA(1);

					if (((LA37_0 >= Identifier && LA37_0 <= ENUM)
							|| LA37_0 == 28 || LA37_0 == 30
							|| (LA37_0 >= 33 && LA37_0 <= 39) || LA37_0 == 42
							|| (LA37_0 >= 48 && LA37_0 <= 49)
							|| (LA37_0 >= 54 && LA37_0 <= 65) || LA37_0 == 75)) {
						alt37 = 1;
					}

					switch (alt37) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
						// interfaceBodyDeclaration
					{
						pushFollow(FOLLOW_interfaceBodyDeclaration_in_interfaceBody957);
						interfaceBodyDeclaration90 = interfaceBodyDeclaration();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, interfaceBodyDeclaration90
									.getTree());

					}
						break;

					default:
						break loop37;
					}
				} while (true);

				char_literal91 = (Token) match(input, 47,
						FOLLOW_47_in_interfaceBody960);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal91_tree = (Object) adaptor
							.create(char_literal91);
					adaptor.addChild(root_0, char_literal91_tree);
				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 23, interfaceBody_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "interfaceBody"

	public static class classBodyDeclaration_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "classBodyDeclaration"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:307:1:
	// classBodyDeclaration : ( ';' | ( 'static' )? block -> ^( CODERANGE_METHOD
	// ( 'static' )? block ) | modifiers memberDecl -> ^( CODERANGE_METHOD
	// modifiers memberDecl ) );
	public final JavaParser.classBodyDeclaration_return classBodyDeclaration()
			throws RecognitionException {
		JavaParser.classBodyDeclaration_return retval = new JavaParser.classBodyDeclaration_return();
		retval.start = input.LT(1);
		int classBodyDeclaration_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal92 = null;
		Token string_literal93 = null;
		JavaParser.block_return block94 = null;

		JavaParser.modifiers_return modifiers95 = null;

		JavaParser.memberDecl_return memberDecl96 = null;

		Object char_literal92_tree = null;
		Object string_literal93_tree = null;
		RewriteRuleTokenStream stream_30 = new RewriteRuleTokenStream(adaptor,
				"token 30");
		RewriteRuleSubtreeStream stream_memberDecl = new RewriteRuleSubtreeStream(
				adaptor, "rule memberDecl");
		RewriteRuleSubtreeStream stream_block = new RewriteRuleSubtreeStream(
				adaptor, "rule block");
		RewriteRuleSubtreeStream stream_modifiers = new RewriteRuleSubtreeStream(
				adaptor, "rule modifiers");
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 24)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:308:5:
			// ( ';' | ( 'static' )? block -> ^( CODERANGE_METHOD ( 'static' )?
			// block ) | modifiers memberDecl -> ^( CODERANGE_METHOD modifiers
			// memberDecl ) )
			int alt39 = 3;
			switch (input.LA(1)) {
			case 28: {
				alt39 = 1;
			}
				break;
			case 30: {
				int LA39_2 = input.LA(2);

				if ((LA39_2 == 46)) {
					alt39 = 2;
				} else if (((LA39_2 >= Identifier && LA39_2 <= ENUM)
						|| LA39_2 == 30 || (LA39_2 >= 33 && LA39_2 <= 39)
						|| LA39_2 == 42 || (LA39_2 >= 48 && LA39_2 <= 49)
						|| (LA39_2 >= 54 && LA39_2 <= 65) || LA39_2 == 75)) {
					alt39 = 3;
				} else {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					NoViableAltException nvae = new NoViableAltException("",
							39, 2, input);

					throw nvae;
				}
			}
				break;
			case 46: {
				alt39 = 2;
			}
				break;
			case Identifier:
			case ENUM:
			case 33:
			case 34:
			case 35:
			case 36:
			case 37:
			case 38:
			case 39:
			case 42:
			case 48:
			case 49:
			case 54:
			case 55:
			case 56:
			case 57:
			case 58:
			case 59:
			case 60:
			case 61:
			case 62:
			case 63:
			case 64:
			case 65:
			case 75: {
				alt39 = 3;
			}
				break;
			default:
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 39, 0,
						input);

				throw nvae;
			}

			switch (alt39) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:308:9:
				// ';'
			{
				root_0 = (Object) adaptor.nil();

				char_literal92 = (Token) match(input, 28,
						FOLLOW_28_in_classBodyDeclaration979);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal92_tree = (Object) adaptor
							.create(char_literal92);
					adaptor.addChild(root_0, char_literal92_tree);
				}

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:309:9:
				// ( 'static' )? block
			{
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:309:9:
				// ( 'static' )?
				int alt38 = 2;
				int LA38_0 = input.LA(1);

				if ((LA38_0 == 30)) {
					alt38 = 1;
				}
				switch (alt38) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// 'static'
				{
					string_literal93 = (Token) match(input, 30,
							FOLLOW_30_in_classBodyDeclaration989);
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						stream_30.add(string_literal93);

				}
					break;

				}

				pushFollow(FOLLOW_block_in_classBodyDeclaration992);
				block94 = block();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					stream_block.add(block94.getTree());

				// AST REWRITE
				// elements: 30, block
				// token labels:
				// rule labels: retval
				// token list labels:
				// rule list labels:
				// wildcard labels:
				if (state.backtracking == 0) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
							adaptor, "rule retval",
							retval != null ? retval.tree : null);

					root_0 = (Object) adaptor.nil();
					// 309:25: -> ^( CODERANGE_METHOD ( 'static' )? block )
					{
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:309:28:
						// ^( CODERANGE_METHOD ( 'static' )? block )
						{
							Object root_1 = (Object) adaptor.nil();
							root_1 = (Object) adaptor.becomeRoot(
									(Object) adaptor.create(CODERANGE_METHOD,
											"CODERANGE_METHOD"), root_1);

							// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:309:47:
							// ( 'static' )?
							if (stream_30.hasNext()) {
								adaptor.addChild(root_1, stream_30.nextNode());

							}
							stream_30.reset();
							adaptor.addChild(root_1, stream_block.nextTree());

							adaptor.addChild(root_0, root_1);
						}

					}

					retval.tree = root_0;
				}
			}
				break;
			case 3:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:310:9:
				// modifiers memberDecl
			{
				pushFollow(FOLLOW_modifiers_in_classBodyDeclaration1013);
				modifiers95 = modifiers();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					stream_modifiers.add(modifiers95.getTree());
				pushFollow(FOLLOW_memberDecl_in_classBodyDeclaration1015);
				memberDecl96 = memberDecl();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					stream_memberDecl.add(memberDecl96.getTree());

				// AST REWRITE
				// elements: memberDecl, modifiers
				// token labels:
				// rule labels: retval
				// token list labels:
				// rule list labels:
				// wildcard labels:
				if (state.backtracking == 0) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
							adaptor, "rule retval",
							retval != null ? retval.tree : null);

					root_0 = (Object) adaptor.nil();
					// 310:30: -> ^( CODERANGE_METHOD modifiers memberDecl )
					{
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:310:33:
						// ^( CODERANGE_METHOD modifiers memberDecl )
						{
							Object root_1 = (Object) adaptor.nil();
							root_1 = (Object) adaptor.becomeRoot(
									(Object) adaptor.create(CODERANGE_METHOD,
											"CODERANGE_METHOD"), root_1);

							adaptor.addChild(root_1, stream_modifiers
									.nextTree());
							adaptor.addChild(root_1, stream_memberDecl
									.nextTree());

							adaptor.addChild(root_0, root_1);
						}

					}

					retval.tree = root_0;
				}
			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 24, classBodyDeclaration_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "classBodyDeclaration"

	public static class memberDecl_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "memberDecl"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:313:1:
	// memberDecl : ( genericMethodOrConstructorDecl | memberDeclaration |
	// 'void' Identifier voidMethodDeclaratorRest | Identifier
	// constructorDeclaratorRest | interfaceDeclaration | classDeclaration );
	public final JavaParser.memberDecl_return memberDecl()
			throws RecognitionException {
		JavaParser.memberDecl_return retval = new JavaParser.memberDecl_return();
		retval.start = input.LT(1);
		int memberDecl_StartIndex = input.index();
		Object root_0 = null;

		Token string_literal99 = null;
		Token Identifier100 = null;
		Token Identifier102 = null;
		JavaParser.genericMethodOrConstructorDecl_return genericMethodOrConstructorDecl97 = null;

		JavaParser.memberDeclaration_return memberDeclaration98 = null;

		JavaParser.voidMethodDeclaratorRest_return voidMethodDeclaratorRest101 = null;

		JavaParser.constructorDeclaratorRest_return constructorDeclaratorRest103 = null;

		JavaParser.interfaceDeclaration_return interfaceDeclaration104 = null;

		JavaParser.classDeclaration_return classDeclaration105 = null;

		Object string_literal99_tree = null;
		Object Identifier100_tree = null;
		Object Identifier102_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 25)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:314:5:
			// ( genericMethodOrConstructorDecl | memberDeclaration | 'void'
			// Identifier voidMethodDeclaratorRest | Identifier
			// constructorDeclaratorRest | interfaceDeclaration |
			// classDeclaration )
			int alt40 = 6;
			switch (input.LA(1)) {
			case 42: {
				alt40 = 1;
			}
				break;
			case Identifier: {
				int LA40_2 = input.LA(2);

				if ((LA40_2 == 68)) {
					alt40 = 4;
				} else if ((LA40_2 == Identifier || LA40_2 == 31
						|| LA40_2 == 42 || LA40_2 == 50)) {
					alt40 = 2;
				} else {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					NoViableAltException nvae = new NoViableAltException("",
							40, 2, input);

					throw nvae;
				}
			}
				break;
			case 58:
			case 59:
			case 60:
			case 61:
			case 62:
			case 63:
			case 64:
			case 65: {
				alt40 = 2;
			}
				break;
			case 49: {
				alt40 = 3;
			}
				break;
			case 48:
			case 75: {
				alt40 = 5;
			}
				break;
			case ENUM:
			case 39: {
				alt40 = 6;
			}
				break;
			default:
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 40, 0,
						input);

				throw nvae;
			}

			switch (alt40) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:314:9:
				// genericMethodOrConstructorDecl
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_genericMethodOrConstructorDecl_in_memberDecl1048);
				genericMethodOrConstructorDecl97 = genericMethodOrConstructorDecl();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, genericMethodOrConstructorDecl97
							.getTree());

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:315:9:
				// memberDeclaration
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_memberDeclaration_in_memberDecl1058);
				memberDeclaration98 = memberDeclaration();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, memberDeclaration98.getTree());

			}
				break;
			case 3:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:316:9:
				// 'void' Identifier voidMethodDeclaratorRest
			{
				root_0 = (Object) adaptor.nil();

				string_literal99 = (Token) match(input, 49,
						FOLLOW_49_in_memberDecl1068);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal99_tree = (Object) adaptor
							.create(string_literal99);
					adaptor.addChild(root_0, string_literal99_tree);
				}
				Identifier100 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_memberDecl1070);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier100_tree = (Object) adaptor.create(Identifier100);
					adaptor.addChild(root_0, Identifier100_tree);
				}
				pushFollow(FOLLOW_voidMethodDeclaratorRest_in_memberDecl1072);
				voidMethodDeclaratorRest101 = voidMethodDeclaratorRest();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, voidMethodDeclaratorRest101
							.getTree());

			}
				break;
			case 4:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:317:9:
				// Identifier constructorDeclaratorRest
			{
				root_0 = (Object) adaptor.nil();

				Identifier102 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_memberDecl1082);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier102_tree = (Object) adaptor.create(Identifier102);
					adaptor.addChild(root_0, Identifier102_tree);
				}
				pushFollow(FOLLOW_constructorDeclaratorRest_in_memberDecl1084);
				constructorDeclaratorRest103 = constructorDeclaratorRest();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, constructorDeclaratorRest103
							.getTree());

			}
				break;
			case 5:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:318:9:
				// interfaceDeclaration
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_interfaceDeclaration_in_memberDecl1094);
				interfaceDeclaration104 = interfaceDeclaration();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, interfaceDeclaration104.getTree());

			}
				break;
			case 6:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:319:9:
				// classDeclaration
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_classDeclaration_in_memberDecl1104);
				classDeclaration105 = classDeclaration();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, classDeclaration105.getTree());

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 25, memberDecl_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "memberDecl"

	public static class memberDeclaration_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "memberDeclaration"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:322:1:
	// memberDeclaration : type ( methodDeclaration | fieldDeclaration ) ;
	public final JavaParser.memberDeclaration_return memberDeclaration()
			throws RecognitionException {
		JavaParser.memberDeclaration_return retval = new JavaParser.memberDeclaration_return();
		retval.start = input.LT(1);
		int memberDeclaration_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.type_return type106 = null;

		JavaParser.methodDeclaration_return methodDeclaration107 = null;

		JavaParser.fieldDeclaration_return fieldDeclaration108 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 26)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:323:5:
			// ( type ( methodDeclaration | fieldDeclaration ) )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:323:9:
			// type ( methodDeclaration | fieldDeclaration )
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_type_in_memberDeclaration1127);
				type106 = type();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, type106.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:323:14:
				// ( methodDeclaration | fieldDeclaration )
				int alt41 = 2;
				int LA41_0 = input.LA(1);

				if ((LA41_0 == Identifier)) {
					int LA41_1 = input.LA(2);

					if ((LA41_1 == 68)) {
						alt41 = 1;
					} else if ((LA41_1 == 28 || LA41_1 == 43 || LA41_1 == 50 || LA41_1 == 53)) {
						alt41 = 2;
					} else {
						if (state.backtracking > 0) {
							state.failed = true;
							return retval;
						}
						NoViableAltException nvae = new NoViableAltException(
								"", 41, 1, input);

						throw nvae;
					}
				} else {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					NoViableAltException nvae = new NoViableAltException("",
							41, 0, input);

					throw nvae;
				}
				switch (alt41) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:323:15:
					// methodDeclaration
				{
					pushFollow(FOLLOW_methodDeclaration_in_memberDeclaration1130);
					methodDeclaration107 = methodDeclaration();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor
								.addChild(root_0, methodDeclaration107
										.getTree());

				}
					break;
				case 2:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:323:35:
					// fieldDeclaration
				{
					pushFollow(FOLLOW_fieldDeclaration_in_memberDeclaration1134);
					fieldDeclaration108 = fieldDeclaration();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, fieldDeclaration108.getTree());

				}
					break;

				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 26, memberDeclaration_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "memberDeclaration"

	public static class genericMethodOrConstructorDecl_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "genericMethodOrConstructorDecl"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:326:1:
	// genericMethodOrConstructorDecl : typeParameters
	// genericMethodOrConstructorRest ;
	public final JavaParser.genericMethodOrConstructorDecl_return genericMethodOrConstructorDecl()
			throws RecognitionException {
		JavaParser.genericMethodOrConstructorDecl_return retval = new JavaParser.genericMethodOrConstructorDecl_return();
		retval.start = input.LT(1);
		int genericMethodOrConstructorDecl_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.typeParameters_return typeParameters109 = null;

		JavaParser.genericMethodOrConstructorRest_return genericMethodOrConstructorRest110 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 27)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:327:5:
			// ( typeParameters genericMethodOrConstructorRest )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:327:9:
			// typeParameters genericMethodOrConstructorRest
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_typeParameters_in_genericMethodOrConstructorDecl1154);
				typeParameters109 = typeParameters();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, typeParameters109.getTree());
				pushFollow(FOLLOW_genericMethodOrConstructorRest_in_genericMethodOrConstructorDecl1156);
				genericMethodOrConstructorRest110 = genericMethodOrConstructorRest();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, genericMethodOrConstructorRest110
							.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 27, genericMethodOrConstructorDecl_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "genericMethodOrConstructorDecl"

	public static class genericMethodOrConstructorRest_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "genericMethodOrConstructorRest"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:330:1:
	// genericMethodOrConstructorRest : ( ( type | 'void' ) Identifier
	// methodDeclaratorRest | Identifier constructorDeclaratorRest );
	public final JavaParser.genericMethodOrConstructorRest_return genericMethodOrConstructorRest()
			throws RecognitionException {
		JavaParser.genericMethodOrConstructorRest_return retval = new JavaParser.genericMethodOrConstructorRest_return();
		retval.start = input.LT(1);
		int genericMethodOrConstructorRest_StartIndex = input.index();
		Object root_0 = null;

		Token string_literal112 = null;
		Token Identifier113 = null;
		Token Identifier115 = null;
		JavaParser.type_return type111 = null;

		JavaParser.methodDeclaratorRest_return methodDeclaratorRest114 = null;

		JavaParser.constructorDeclaratorRest_return constructorDeclaratorRest116 = null;

		Object string_literal112_tree = null;
		Object Identifier113_tree = null;
		Object Identifier115_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 28)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:331:5:
			// ( ( type | 'void' ) Identifier methodDeclaratorRest | Identifier
			// constructorDeclaratorRest )
			int alt43 = 2;
			int LA43_0 = input.LA(1);

			if ((LA43_0 == Identifier)) {
				int LA43_1 = input.LA(2);

				if ((LA43_1 == Identifier || LA43_1 == 31 || LA43_1 == 42 || LA43_1 == 50)) {
					alt43 = 1;
				} else if ((LA43_1 == 68)) {
					alt43 = 2;
				} else {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					NoViableAltException nvae = new NoViableAltException("",
							43, 1, input);

					throw nvae;
				}
			} else if ((LA43_0 == 49 || (LA43_0 >= 58 && LA43_0 <= 65))) {
				alt43 = 1;
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 43, 0,
						input);

				throw nvae;
			}
			switch (alt43) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:331:9:
				// ( type | 'void' ) Identifier methodDeclaratorRest
			{
				root_0 = (Object) adaptor.nil();

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:331:9:
				// ( type | 'void' )
				int alt42 = 2;
				int LA42_0 = input.LA(1);

				if ((LA42_0 == Identifier || (LA42_0 >= 58 && LA42_0 <= 65))) {
					alt42 = 1;
				} else if ((LA42_0 == 49)) {
					alt42 = 2;
				} else {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					NoViableAltException nvae = new NoViableAltException("",
							42, 0, input);

					throw nvae;
				}
				switch (alt42) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:331:10:
					// type
				{
					pushFollow(FOLLOW_type_in_genericMethodOrConstructorRest1180);
					type111 = type();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, type111.getTree());

				}
					break;
				case 2:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:331:17:
					// 'void'
				{
					string_literal112 = (Token) match(input, 49,
							FOLLOW_49_in_genericMethodOrConstructorRest1184);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						string_literal112_tree = (Object) adaptor
								.create(string_literal112);
						adaptor.addChild(root_0, string_literal112_tree);
					}

				}
					break;

				}

				Identifier113 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_genericMethodOrConstructorRest1187);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier113_tree = (Object) adaptor.create(Identifier113);
					adaptor.addChild(root_0, Identifier113_tree);
				}
				pushFollow(FOLLOW_methodDeclaratorRest_in_genericMethodOrConstructorRest1189);
				methodDeclaratorRest114 = methodDeclaratorRest();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, methodDeclaratorRest114.getTree());

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:332:9:
				// Identifier constructorDeclaratorRest
			{
				root_0 = (Object) adaptor.nil();

				Identifier115 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_genericMethodOrConstructorRest1199);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier115_tree = (Object) adaptor.create(Identifier115);
					adaptor.addChild(root_0, Identifier115_tree);
				}
				pushFollow(FOLLOW_constructorDeclaratorRest_in_genericMethodOrConstructorRest1201);
				constructorDeclaratorRest116 = constructorDeclaratorRest();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, constructorDeclaratorRest116
							.getTree());

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 28, genericMethodOrConstructorRest_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "genericMethodOrConstructorRest"

	public static class methodDeclaration_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "methodDeclaration"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:335:1:
	// methodDeclaration : Identifier methodDeclaratorRest ;
	public final JavaParser.methodDeclaration_return methodDeclaration()
			throws RecognitionException {
		JavaParser.methodDeclaration_return retval = new JavaParser.methodDeclaration_return();
		retval.start = input.LT(1);
		int methodDeclaration_StartIndex = input.index();
		Object root_0 = null;

		Token Identifier117 = null;
		JavaParser.methodDeclaratorRest_return methodDeclaratorRest118 = null;

		Object Identifier117_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 29)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:336:5:
			// ( Identifier methodDeclaratorRest )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:336:9:
			// Identifier methodDeclaratorRest
			{
				root_0 = (Object) adaptor.nil();

				Identifier117 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_methodDeclaration1220);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier117_tree = (Object) adaptor.create(Identifier117);
					adaptor.addChild(root_0, Identifier117_tree);
				}
				pushFollow(FOLLOW_methodDeclaratorRest_in_methodDeclaration1222);
				methodDeclaratorRest118 = methodDeclaratorRest();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, methodDeclaratorRest118.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 29, methodDeclaration_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "methodDeclaration"

	public static class fieldDeclaration_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "fieldDeclaration"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:339:1:
	// fieldDeclaration : variableDeclarators ';' ;
	public final JavaParser.fieldDeclaration_return fieldDeclaration()
			throws RecognitionException {
		JavaParser.fieldDeclaration_return retval = new JavaParser.fieldDeclaration_return();
		retval.start = input.LT(1);
		int fieldDeclaration_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal120 = null;
		JavaParser.variableDeclarators_return variableDeclarators119 = null;

		Object char_literal120_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 30)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:340:5:
			// ( variableDeclarators ';' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:340:9:
			// variableDeclarators ';'
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_variableDeclarators_in_fieldDeclaration1241);
				variableDeclarators119 = variableDeclarators();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, variableDeclarators119.getTree());
				char_literal120 = (Token) match(input, 28,
						FOLLOW_28_in_fieldDeclaration1243);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal120_tree = (Object) adaptor
							.create(char_literal120);
					adaptor.addChild(root_0, char_literal120_tree);
				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 30, fieldDeclaration_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "fieldDeclaration"

	public static class interfaceBodyDeclaration_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "interfaceBodyDeclaration"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:343:1:
	// interfaceBodyDeclaration : ( modifiers interfaceMemberDecl | ';' );
	public final JavaParser.interfaceBodyDeclaration_return interfaceBodyDeclaration()
			throws RecognitionException {
		JavaParser.interfaceBodyDeclaration_return retval = new JavaParser.interfaceBodyDeclaration_return();
		retval.start = input.LT(1);
		int interfaceBodyDeclaration_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal123 = null;
		JavaParser.modifiers_return modifiers121 = null;

		JavaParser.interfaceMemberDecl_return interfaceMemberDecl122 = null;

		Object char_literal123_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 31)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:344:5:
			// ( modifiers interfaceMemberDecl | ';' )
			int alt44 = 2;
			int LA44_0 = input.LA(1);

			if (((LA44_0 >= Identifier && LA44_0 <= ENUM) || LA44_0 == 30
					|| (LA44_0 >= 33 && LA44_0 <= 39) || LA44_0 == 42
					|| (LA44_0 >= 48 && LA44_0 <= 49)
					|| (LA44_0 >= 54 && LA44_0 <= 65) || LA44_0 == 75)) {
				alt44 = 1;
			} else if ((LA44_0 == 28)) {
				alt44 = 2;
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 44, 0,
						input);

				throw nvae;
			}
			switch (alt44) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:344:9:
				// modifiers interfaceMemberDecl
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_modifiers_in_interfaceBodyDeclaration1270);
				modifiers121 = modifiers();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, modifiers121.getTree());
				pushFollow(FOLLOW_interfaceMemberDecl_in_interfaceBodyDeclaration1272);
				interfaceMemberDecl122 = interfaceMemberDecl();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, interfaceMemberDecl122.getTree());

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:345:9:
				// ';'
			{
				root_0 = (Object) adaptor.nil();

				char_literal123 = (Token) match(input, 28,
						FOLLOW_28_in_interfaceBodyDeclaration1282);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal123_tree = (Object) adaptor
							.create(char_literal123);
					adaptor.addChild(root_0, char_literal123_tree);
				}

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 31, interfaceBodyDeclaration_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "interfaceBodyDeclaration"

	public static class interfaceMemberDecl_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "interfaceMemberDecl"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:348:1:
	// interfaceMemberDecl : ( interfaceMethodOrFieldDecl |
	// interfaceGenericMethodDecl | 'void' Identifier
	// voidInterfaceMethodDeclaratorRest | interfaceDeclaration |
	// classDeclaration );
	public final JavaParser.interfaceMemberDecl_return interfaceMemberDecl()
			throws RecognitionException {
		JavaParser.interfaceMemberDecl_return retval = new JavaParser.interfaceMemberDecl_return();
		retval.start = input.LT(1);
		int interfaceMemberDecl_StartIndex = input.index();
		Object root_0 = null;

		Token string_literal126 = null;
		Token Identifier127 = null;
		JavaParser.interfaceMethodOrFieldDecl_return interfaceMethodOrFieldDecl124 = null;

		JavaParser.interfaceGenericMethodDecl_return interfaceGenericMethodDecl125 = null;

		JavaParser.voidInterfaceMethodDeclaratorRest_return voidInterfaceMethodDeclaratorRest128 = null;

		JavaParser.interfaceDeclaration_return interfaceDeclaration129 = null;

		JavaParser.classDeclaration_return classDeclaration130 = null;

		Object string_literal126_tree = null;
		Object Identifier127_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 32)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:349:5:
			// ( interfaceMethodOrFieldDecl | interfaceGenericMethodDecl |
			// 'void' Identifier voidInterfaceMethodDeclaratorRest |
			// interfaceDeclaration | classDeclaration )
			int alt45 = 5;
			switch (input.LA(1)) {
			case Identifier:
			case 58:
			case 59:
			case 60:
			case 61:
			case 62:
			case 63:
			case 64:
			case 65: {
				alt45 = 1;
			}
				break;
			case 42: {
				alt45 = 2;
			}
				break;
			case 49: {
				alt45 = 3;
			}
				break;
			case 48:
			case 75: {
				alt45 = 4;
			}
				break;
			case ENUM:
			case 39: {
				alt45 = 5;
			}
				break;
			default:
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 45, 0,
						input);

				throw nvae;
			}

			switch (alt45) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:349:9:
				// interfaceMethodOrFieldDecl
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_interfaceMethodOrFieldDecl_in_interfaceMemberDecl1301);
				interfaceMethodOrFieldDecl124 = interfaceMethodOrFieldDecl();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, interfaceMethodOrFieldDecl124
							.getTree());

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:350:9:
				// interfaceGenericMethodDecl
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_interfaceGenericMethodDecl_in_interfaceMemberDecl1311);
				interfaceGenericMethodDecl125 = interfaceGenericMethodDecl();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, interfaceGenericMethodDecl125
							.getTree());

			}
				break;
			case 3:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:351:9:
				// 'void' Identifier voidInterfaceMethodDeclaratorRest
			{
				root_0 = (Object) adaptor.nil();

				string_literal126 = (Token) match(input, 49,
						FOLLOW_49_in_interfaceMemberDecl1321);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal126_tree = (Object) adaptor
							.create(string_literal126);
					adaptor.addChild(root_0, string_literal126_tree);
				}
				Identifier127 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_interfaceMemberDecl1323);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier127_tree = (Object) adaptor.create(Identifier127);
					adaptor.addChild(root_0, Identifier127_tree);
				}
				pushFollow(FOLLOW_voidInterfaceMethodDeclaratorRest_in_interfaceMemberDecl1325);
				voidInterfaceMethodDeclaratorRest128 = voidInterfaceMethodDeclaratorRest();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0,
							voidInterfaceMethodDeclaratorRest128.getTree());

			}
				break;
			case 4:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:352:9:
				// interfaceDeclaration
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_interfaceDeclaration_in_interfaceMemberDecl1335);
				interfaceDeclaration129 = interfaceDeclaration();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, interfaceDeclaration129.getTree());

			}
				break;
			case 5:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:353:9:
				// classDeclaration
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_classDeclaration_in_interfaceMemberDecl1345);
				classDeclaration130 = classDeclaration();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, classDeclaration130.getTree());

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 32, interfaceMemberDecl_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "interfaceMemberDecl"

	public static class interfaceMethodOrFieldDecl_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "interfaceMethodOrFieldDecl"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:356:1:
	// interfaceMethodOrFieldDecl : type Identifier interfaceMethodOrFieldRest ;
	public final JavaParser.interfaceMethodOrFieldDecl_return interfaceMethodOrFieldDecl()
			throws RecognitionException {
		JavaParser.interfaceMethodOrFieldDecl_return retval = new JavaParser.interfaceMethodOrFieldDecl_return();
		retval.start = input.LT(1);
		int interfaceMethodOrFieldDecl_StartIndex = input.index();
		Object root_0 = null;

		Token Identifier132 = null;
		JavaParser.type_return type131 = null;

		JavaParser.interfaceMethodOrFieldRest_return interfaceMethodOrFieldRest133 = null;

		Object Identifier132_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 33)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:357:5:
			// ( type Identifier interfaceMethodOrFieldRest )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:357:9:
			// type Identifier interfaceMethodOrFieldRest
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_type_in_interfaceMethodOrFieldDecl1368);
				type131 = type();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, type131.getTree());
				Identifier132 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_interfaceMethodOrFieldDecl1370);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier132_tree = (Object) adaptor.create(Identifier132);
					adaptor.addChild(root_0, Identifier132_tree);
				}
				pushFollow(FOLLOW_interfaceMethodOrFieldRest_in_interfaceMethodOrFieldDecl1372);
				interfaceMethodOrFieldRest133 = interfaceMethodOrFieldRest();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, interfaceMethodOrFieldRest133
							.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 33, interfaceMethodOrFieldDecl_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "interfaceMethodOrFieldDecl"

	public static class interfaceMethodOrFieldRest_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "interfaceMethodOrFieldRest"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:360:1:
	// interfaceMethodOrFieldRest : ( constantDeclaratorsRest ';' |
	// interfaceMethodDeclaratorRest );
	public final JavaParser.interfaceMethodOrFieldRest_return interfaceMethodOrFieldRest()
			throws RecognitionException {
		JavaParser.interfaceMethodOrFieldRest_return retval = new JavaParser.interfaceMethodOrFieldRest_return();
		retval.start = input.LT(1);
		int interfaceMethodOrFieldRest_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal135 = null;
		JavaParser.constantDeclaratorsRest_return constantDeclaratorsRest134 = null;

		JavaParser.interfaceMethodDeclaratorRest_return interfaceMethodDeclaratorRest136 = null;

		Object char_literal135_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 34)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:361:5:
			// ( constantDeclaratorsRest ';' | interfaceMethodDeclaratorRest )
			int alt46 = 2;
			int LA46_0 = input.LA(1);

			if ((LA46_0 == 50 || LA46_0 == 53)) {
				alt46 = 1;
			} else if ((LA46_0 == 68)) {
				alt46 = 2;
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 46, 0,
						input);

				throw nvae;
			}
			switch (alt46) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:361:9:
				// constantDeclaratorsRest ';'
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_constantDeclaratorsRest_in_interfaceMethodOrFieldRest1395);
				constantDeclaratorsRest134 = constantDeclaratorsRest();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, constantDeclaratorsRest134
							.getTree());
				char_literal135 = (Token) match(input, 28,
						FOLLOW_28_in_interfaceMethodOrFieldRest1397);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal135_tree = (Object) adaptor
							.create(char_literal135);
					adaptor.addChild(root_0, char_literal135_tree);
				}

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:362:9:
				// interfaceMethodDeclaratorRest
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_interfaceMethodDeclaratorRest_in_interfaceMethodOrFieldRest1407);
				interfaceMethodDeclaratorRest136 = interfaceMethodDeclaratorRest();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, interfaceMethodDeclaratorRest136
							.getTree());

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 34, interfaceMethodOrFieldRest_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "interfaceMethodOrFieldRest"

	public static class methodDeclaratorRest_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "methodDeclaratorRest"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:365:1:
	// methodDeclaratorRest : formalParameters ( '[' ']' )* ( 'throws'
	// qualifiedNameList )? ( methodBody | ';' ) ;
	public final JavaParser.methodDeclaratorRest_return methodDeclaratorRest()
			throws RecognitionException {
		JavaParser.methodDeclaratorRest_return retval = new JavaParser.methodDeclaratorRest_return();
		retval.start = input.LT(1);
		int methodDeclaratorRest_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal138 = null;
		Token char_literal139 = null;
		Token string_literal140 = null;
		Token char_literal143 = null;
		JavaParser.formalParameters_return formalParameters137 = null;

		JavaParser.qualifiedNameList_return qualifiedNameList141 = null;

		JavaParser.methodBody_return methodBody142 = null;

		Object char_literal138_tree = null;
		Object char_literal139_tree = null;
		Object string_literal140_tree = null;
		Object char_literal143_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 35)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:366:5:
			// ( formalParameters ( '[' ']' )* ( 'throws' qualifiedNameList )? (
			// methodBody | ';' ) )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:366:9:
			// formalParameters ( '[' ']' )* ( 'throws' qualifiedNameList )? (
			// methodBody | ';' )
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_formalParameters_in_methodDeclaratorRest1430);
				formalParameters137 = formalParameters();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, formalParameters137.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:366:26:
				// ( '[' ']' )*
				loop47: do {
					int alt47 = 2;
					int LA47_0 = input.LA(1);

					if ((LA47_0 == 50)) {
						alt47 = 1;
					}

					switch (alt47) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:366:27:
						// '[' ']'
					{
						char_literal138 = (Token) match(input, 50,
								FOLLOW_50_in_methodDeclaratorRest1433);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal138_tree = (Object) adaptor
									.create(char_literal138);
							adaptor.addChild(root_0, char_literal138_tree);
						}
						char_literal139 = (Token) match(input, 51,
								FOLLOW_51_in_methodDeclaratorRest1435);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal139_tree = (Object) adaptor
									.create(char_literal139);
							adaptor.addChild(root_0, char_literal139_tree);
						}

					}
						break;

					default:
						break loop47;
					}
				} while (true);

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:367:9:
				// ( 'throws' qualifiedNameList )?
				int alt48 = 2;
				int LA48_0 = input.LA(1);

				if ((LA48_0 == 52)) {
					alt48 = 1;
				}
				switch (alt48) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:367:10:
					// 'throws' qualifiedNameList
				{
					string_literal140 = (Token) match(input, 52,
							FOLLOW_52_in_methodDeclaratorRest1448);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						string_literal140_tree = (Object) adaptor
								.create(string_literal140);
						adaptor.addChild(root_0, string_literal140_tree);
					}
					pushFollow(FOLLOW_qualifiedNameList_in_methodDeclaratorRest1450);
					qualifiedNameList141 = qualifiedNameList();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor
								.addChild(root_0, qualifiedNameList141
										.getTree());

				}
					break;

				}

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:368:9:
				// ( methodBody | ';' )
				int alt49 = 2;
				int LA49_0 = input.LA(1);

				if ((LA49_0 == 46)) {
					alt49 = 1;
				} else if ((LA49_0 == 28)) {
					alt49 = 2;
				} else {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					NoViableAltException nvae = new NoViableAltException("",
							49, 0, input);

					throw nvae;
				}
				switch (alt49) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:368:13:
					// methodBody
				{
					pushFollow(FOLLOW_methodBody_in_methodDeclaratorRest1466);
					methodBody142 = methodBody();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, methodBody142.getTree());

				}
					break;
				case 2:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:369:13:
					// ';'
				{
					char_literal143 = (Token) match(input, 28,
							FOLLOW_28_in_methodDeclaratorRest1480);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						char_literal143_tree = (Object) adaptor
								.create(char_literal143);
						adaptor.addChild(root_0, char_literal143_tree);
					}

				}
					break;

				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 35, methodDeclaratorRest_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "methodDeclaratorRest"

	public static class voidMethodDeclaratorRest_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "voidMethodDeclaratorRest"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:373:1:
	// voidMethodDeclaratorRest : formalParameters ( 'throws' qualifiedNameList
	// )? ( methodBody | ';' ) ;
	public final JavaParser.voidMethodDeclaratorRest_return voidMethodDeclaratorRest()
			throws RecognitionException {
		JavaParser.voidMethodDeclaratorRest_return retval = new JavaParser.voidMethodDeclaratorRest_return();
		retval.start = input.LT(1);
		int voidMethodDeclaratorRest_StartIndex = input.index();
		Object root_0 = null;

		Token string_literal145 = null;
		Token char_literal148 = null;
		JavaParser.formalParameters_return formalParameters144 = null;

		JavaParser.qualifiedNameList_return qualifiedNameList146 = null;

		JavaParser.methodBody_return methodBody147 = null;

		Object string_literal145_tree = null;
		Object char_literal148_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 36)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:374:5:
			// ( formalParameters ( 'throws' qualifiedNameList )? ( methodBody |
			// ';' ) )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:374:9:
			// formalParameters ( 'throws' qualifiedNameList )? ( methodBody |
			// ';' )
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_formalParameters_in_voidMethodDeclaratorRest1513);
				formalParameters144 = formalParameters();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, formalParameters144.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:374:26:
				// ( 'throws' qualifiedNameList )?
				int alt50 = 2;
				int LA50_0 = input.LA(1);

				if ((LA50_0 == 52)) {
					alt50 = 1;
				}
				switch (alt50) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:374:27:
					// 'throws' qualifiedNameList
				{
					string_literal145 = (Token) match(input, 52,
							FOLLOW_52_in_voidMethodDeclaratorRest1516);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						string_literal145_tree = (Object) adaptor
								.create(string_literal145);
						adaptor.addChild(root_0, string_literal145_tree);
					}
					pushFollow(FOLLOW_qualifiedNameList_in_voidMethodDeclaratorRest1518);
					qualifiedNameList146 = qualifiedNameList();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor
								.addChild(root_0, qualifiedNameList146
										.getTree());

				}
					break;

				}

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:375:9:
				// ( methodBody | ';' )
				int alt51 = 2;
				int LA51_0 = input.LA(1);

				if ((LA51_0 == 46)) {
					alt51 = 1;
				} else if ((LA51_0 == 28)) {
					alt51 = 2;
				} else {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					NoViableAltException nvae = new NoViableAltException("",
							51, 0, input);

					throw nvae;
				}
				switch (alt51) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:375:13:
					// methodBody
				{
					pushFollow(FOLLOW_methodBody_in_voidMethodDeclaratorRest1534);
					methodBody147 = methodBody();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, methodBody147.getTree());

				}
					break;
				case 2:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:376:13:
					// ';'
				{
					char_literal148 = (Token) match(input, 28,
							FOLLOW_28_in_voidMethodDeclaratorRest1548);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						char_literal148_tree = (Object) adaptor
								.create(char_literal148);
						adaptor.addChild(root_0, char_literal148_tree);
					}

				}
					break;

				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 36, voidMethodDeclaratorRest_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "voidMethodDeclaratorRest"

	public static class interfaceMethodDeclaratorRest_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "interfaceMethodDeclaratorRest"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:380:1:
	// interfaceMethodDeclaratorRest : formalParameters ( '[' ']' )* ( 'throws'
	// qualifiedNameList )? ';' ;
	public final JavaParser.interfaceMethodDeclaratorRest_return interfaceMethodDeclaratorRest()
			throws RecognitionException {
		JavaParser.interfaceMethodDeclaratorRest_return retval = new JavaParser.interfaceMethodDeclaratorRest_return();
		retval.start = input.LT(1);
		int interfaceMethodDeclaratorRest_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal150 = null;
		Token char_literal151 = null;
		Token string_literal152 = null;
		Token char_literal154 = null;
		JavaParser.formalParameters_return formalParameters149 = null;

		JavaParser.qualifiedNameList_return qualifiedNameList153 = null;

		Object char_literal150_tree = null;
		Object char_literal151_tree = null;
		Object string_literal152_tree = null;
		Object char_literal154_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 37)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:381:5:
			// ( formalParameters ( '[' ']' )* ( 'throws' qualifiedNameList )?
			// ';' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:381:9:
			// formalParameters ( '[' ']' )* ( 'throws' qualifiedNameList )? ';'
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_formalParameters_in_interfaceMethodDeclaratorRest1581);
				formalParameters149 = formalParameters();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, formalParameters149.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:381:26:
				// ( '[' ']' )*
				loop52: do {
					int alt52 = 2;
					int LA52_0 = input.LA(1);

					if ((LA52_0 == 50)) {
						alt52 = 1;
					}

					switch (alt52) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:381:27:
						// '[' ']'
					{
						char_literal150 = (Token) match(input, 50,
								FOLLOW_50_in_interfaceMethodDeclaratorRest1584);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal150_tree = (Object) adaptor
									.create(char_literal150);
							adaptor.addChild(root_0, char_literal150_tree);
						}
						char_literal151 = (Token) match(input, 51,
								FOLLOW_51_in_interfaceMethodDeclaratorRest1586);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal151_tree = (Object) adaptor
									.create(char_literal151);
							adaptor.addChild(root_0, char_literal151_tree);
						}

					}
						break;

					default:
						break loop52;
					}
				} while (true);

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:381:37:
				// ( 'throws' qualifiedNameList )?
				int alt53 = 2;
				int LA53_0 = input.LA(1);

				if ((LA53_0 == 52)) {
					alt53 = 1;
				}
				switch (alt53) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:381:38:
					// 'throws' qualifiedNameList
				{
					string_literal152 = (Token) match(input, 52,
							FOLLOW_52_in_interfaceMethodDeclaratorRest1591);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						string_literal152_tree = (Object) adaptor
								.create(string_literal152);
						adaptor.addChild(root_0, string_literal152_tree);
					}
					pushFollow(FOLLOW_qualifiedNameList_in_interfaceMethodDeclaratorRest1593);
					qualifiedNameList153 = qualifiedNameList();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor
								.addChild(root_0, qualifiedNameList153
										.getTree());

				}
					break;

				}

				char_literal154 = (Token) match(input, 28,
						FOLLOW_28_in_interfaceMethodDeclaratorRest1597);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal154_tree = (Object) adaptor
							.create(char_literal154);
					adaptor.addChild(root_0, char_literal154_tree);
				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 37, interfaceMethodDeclaratorRest_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "interfaceMethodDeclaratorRest"

	public static class interfaceGenericMethodDecl_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "interfaceGenericMethodDecl"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:384:1:
	// interfaceGenericMethodDecl : typeParameters ( type | 'void' ) Identifier
	// interfaceMethodDeclaratorRest ;
	public final JavaParser.interfaceGenericMethodDecl_return interfaceGenericMethodDecl()
			throws RecognitionException {
		JavaParser.interfaceGenericMethodDecl_return retval = new JavaParser.interfaceGenericMethodDecl_return();
		retval.start = input.LT(1);
		int interfaceGenericMethodDecl_StartIndex = input.index();
		Object root_0 = null;

		Token string_literal157 = null;
		Token Identifier158 = null;
		JavaParser.typeParameters_return typeParameters155 = null;

		JavaParser.type_return type156 = null;

		JavaParser.interfaceMethodDeclaratorRest_return interfaceMethodDeclaratorRest159 = null;

		Object string_literal157_tree = null;
		Object Identifier158_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 38)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:385:5:
			// ( typeParameters ( type | 'void' ) Identifier
			// interfaceMethodDeclaratorRest )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:385:9:
			// typeParameters ( type | 'void' ) Identifier
			// interfaceMethodDeclaratorRest
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_typeParameters_in_interfaceGenericMethodDecl1620);
				typeParameters155 = typeParameters();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, typeParameters155.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:385:24:
				// ( type | 'void' )
				int alt54 = 2;
				int LA54_0 = input.LA(1);

				if ((LA54_0 == Identifier || (LA54_0 >= 58 && LA54_0 <= 65))) {
					alt54 = 1;
				} else if ((LA54_0 == 49)) {
					alt54 = 2;
				} else {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					NoViableAltException nvae = new NoViableAltException("",
							54, 0, input);

					throw nvae;
				}
				switch (alt54) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:385:25:
					// type
				{
					pushFollow(FOLLOW_type_in_interfaceGenericMethodDecl1623);
					type156 = type();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, type156.getTree());

				}
					break;
				case 2:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:385:32:
					// 'void'
				{
					string_literal157 = (Token) match(input, 49,
							FOLLOW_49_in_interfaceGenericMethodDecl1627);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						string_literal157_tree = (Object) adaptor
								.create(string_literal157);
						adaptor.addChild(root_0, string_literal157_tree);
					}

				}
					break;

				}

				Identifier158 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_interfaceGenericMethodDecl1630);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier158_tree = (Object) adaptor.create(Identifier158);
					adaptor.addChild(root_0, Identifier158_tree);
				}
				pushFollow(FOLLOW_interfaceMethodDeclaratorRest_in_interfaceGenericMethodDecl1640);
				interfaceMethodDeclaratorRest159 = interfaceMethodDeclaratorRest();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, interfaceMethodDeclaratorRest159
							.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 38, interfaceGenericMethodDecl_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "interfaceGenericMethodDecl"

	public static class voidInterfaceMethodDeclaratorRest_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "voidInterfaceMethodDeclaratorRest"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:389:1:
	// voidInterfaceMethodDeclaratorRest : formalParameters ( 'throws'
	// qualifiedNameList )? ';' ;
	public final JavaParser.voidInterfaceMethodDeclaratorRest_return voidInterfaceMethodDeclaratorRest()
			throws RecognitionException {
		JavaParser.voidInterfaceMethodDeclaratorRest_return retval = new JavaParser.voidInterfaceMethodDeclaratorRest_return();
		retval.start = input.LT(1);
		int voidInterfaceMethodDeclaratorRest_StartIndex = input.index();
		Object root_0 = null;

		Token string_literal161 = null;
		Token char_literal163 = null;
		JavaParser.formalParameters_return formalParameters160 = null;

		JavaParser.qualifiedNameList_return qualifiedNameList162 = null;

		Object string_literal161_tree = null;
		Object char_literal163_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 39)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:390:5:
			// ( formalParameters ( 'throws' qualifiedNameList )? ';' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:390:9:
			// formalParameters ( 'throws' qualifiedNameList )? ';'
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_formalParameters_in_voidInterfaceMethodDeclaratorRest1663);
				formalParameters160 = formalParameters();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, formalParameters160.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:390:26:
				// ( 'throws' qualifiedNameList )?
				int alt55 = 2;
				int LA55_0 = input.LA(1);

				if ((LA55_0 == 52)) {
					alt55 = 1;
				}
				switch (alt55) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:390:27:
					// 'throws' qualifiedNameList
				{
					string_literal161 = (Token) match(input, 52,
							FOLLOW_52_in_voidInterfaceMethodDeclaratorRest1666);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						string_literal161_tree = (Object) adaptor
								.create(string_literal161);
						adaptor.addChild(root_0, string_literal161_tree);
					}
					pushFollow(FOLLOW_qualifiedNameList_in_voidInterfaceMethodDeclaratorRest1668);
					qualifiedNameList162 = qualifiedNameList();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor
								.addChild(root_0, qualifiedNameList162
										.getTree());

				}
					break;

				}

				char_literal163 = (Token) match(input, 28,
						FOLLOW_28_in_voidInterfaceMethodDeclaratorRest1672);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal163_tree = (Object) adaptor
							.create(char_literal163);
					adaptor.addChild(root_0, char_literal163_tree);
				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 39, voidInterfaceMethodDeclaratorRest_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "voidInterfaceMethodDeclaratorRest"

	public static class constructorDeclaratorRest_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "constructorDeclaratorRest"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:393:1:
	// constructorDeclaratorRest : formalParameters ( 'throws' qualifiedNameList
	// )? constructorBody ;
	public final JavaParser.constructorDeclaratorRest_return constructorDeclaratorRest()
			throws RecognitionException {
		JavaParser.constructorDeclaratorRest_return retval = new JavaParser.constructorDeclaratorRest_return();
		retval.start = input.LT(1);
		int constructorDeclaratorRest_StartIndex = input.index();
		Object root_0 = null;

		Token string_literal165 = null;
		JavaParser.formalParameters_return formalParameters164 = null;

		JavaParser.qualifiedNameList_return qualifiedNameList166 = null;

		JavaParser.constructorBody_return constructorBody167 = null;

		Object string_literal165_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 40)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:394:5:
			// ( formalParameters ( 'throws' qualifiedNameList )?
			// constructorBody )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:394:9:
			// formalParameters ( 'throws' qualifiedNameList )? constructorBody
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_formalParameters_in_constructorDeclaratorRest1695);
				formalParameters164 = formalParameters();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, formalParameters164.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:394:26:
				// ( 'throws' qualifiedNameList )?
				int alt56 = 2;
				int LA56_0 = input.LA(1);

				if ((LA56_0 == 52)) {
					alt56 = 1;
				}
				switch (alt56) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:394:27:
					// 'throws' qualifiedNameList
				{
					string_literal165 = (Token) match(input, 52,
							FOLLOW_52_in_constructorDeclaratorRest1698);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						string_literal165_tree = (Object) adaptor
								.create(string_literal165);
						adaptor.addChild(root_0, string_literal165_tree);
					}
					pushFollow(FOLLOW_qualifiedNameList_in_constructorDeclaratorRest1700);
					qualifiedNameList166 = qualifiedNameList();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor
								.addChild(root_0, qualifiedNameList166
										.getTree());

				}
					break;

				}

				pushFollow(FOLLOW_constructorBody_in_constructorDeclaratorRest1704);
				constructorBody167 = constructorBody();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, constructorBody167.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 40, constructorDeclaratorRest_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "constructorDeclaratorRest"

	public static class constantDeclarator_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "constantDeclarator"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:397:1:
	// constantDeclarator : Identifier constantDeclaratorRest ;
	public final JavaParser.constantDeclarator_return constantDeclarator()
			throws RecognitionException {
		JavaParser.constantDeclarator_return retval = new JavaParser.constantDeclarator_return();
		retval.start = input.LT(1);
		int constantDeclarator_StartIndex = input.index();
		Object root_0 = null;

		Token Identifier168 = null;
		JavaParser.constantDeclaratorRest_return constantDeclaratorRest169 = null;

		Object Identifier168_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 41)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:398:5:
			// ( Identifier constantDeclaratorRest )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:398:9:
			// Identifier constantDeclaratorRest
			{
				root_0 = (Object) adaptor.nil();

				Identifier168 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_constantDeclarator1723);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier168_tree = (Object) adaptor.create(Identifier168);
					adaptor.addChild(root_0, Identifier168_tree);
				}
				pushFollow(FOLLOW_constantDeclaratorRest_in_constantDeclarator1725);
				constantDeclaratorRest169 = constantDeclaratorRest();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, constantDeclaratorRest169
							.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 41, constantDeclarator_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "constantDeclarator"

	public static class variableDeclarators_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "variableDeclarators"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:401:1:
	// variableDeclarators : variableDeclarator ( ',' variableDeclarator )* ;
	public final JavaParser.variableDeclarators_return variableDeclarators()
			throws RecognitionException {
		JavaParser.variableDeclarators_return retval = new JavaParser.variableDeclarators_return();
		retval.start = input.LT(1);
		int variableDeclarators_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal171 = null;
		JavaParser.variableDeclarator_return variableDeclarator170 = null;

		JavaParser.variableDeclarator_return variableDeclarator172 = null;

		Object char_literal171_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 42)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:402:5:
			// ( variableDeclarator ( ',' variableDeclarator )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:402:9:
			// variableDeclarator ( ',' variableDeclarator )*
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_variableDeclarator_in_variableDeclarators1748);
				variableDeclarator170 = variableDeclarator();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, variableDeclarator170.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:402:28:
				// ( ',' variableDeclarator )*
				loop57: do {
					int alt57 = 2;
					int LA57_0 = input.LA(1);

					if ((LA57_0 == 43)) {
						alt57 = 1;
					}

					switch (alt57) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:402:29:
						// ',' variableDeclarator
					{
						char_literal171 = (Token) match(input, 43,
								FOLLOW_43_in_variableDeclarators1751);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal171_tree = (Object) adaptor
									.create(char_literal171);
							adaptor.addChild(root_0, char_literal171_tree);
						}
						pushFollow(FOLLOW_variableDeclarator_in_variableDeclarators1753);
						variableDeclarator172 = variableDeclarator();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, variableDeclarator172
									.getTree());

					}
						break;

					default:
						break loop57;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 42, variableDeclarators_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "variableDeclarators"

	public static class variableDeclarator_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "variableDeclarator"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:405:1:
	// variableDeclarator : variableDeclaratorId ( '=' variableInitializer )? ;
	public final JavaParser.variableDeclarator_return variableDeclarator()
			throws RecognitionException {
		JavaParser.variableDeclarator_return retval = new JavaParser.variableDeclarator_return();
		retval.start = input.LT(1);
		int variableDeclarator_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal174 = null;
		JavaParser.variableDeclaratorId_return variableDeclaratorId173 = null;

		JavaParser.variableInitializer_return variableInitializer175 = null;

		Object char_literal174_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 43)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:406:5:
			// ( variableDeclaratorId ( '=' variableInitializer )? )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:406:9:
			// variableDeclaratorId ( '=' variableInitializer )?
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_variableDeclaratorId_in_variableDeclarator1774);
				variableDeclaratorId173 = variableDeclaratorId();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, variableDeclaratorId173.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:406:30:
				// ( '=' variableInitializer )?
				int alt58 = 2;
				int LA58_0 = input.LA(1);

				if ((LA58_0 == 53)) {
					alt58 = 1;
				}
				switch (alt58) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:406:31:
					// '=' variableInitializer
				{
					char_literal174 = (Token) match(input, 53,
							FOLLOW_53_in_variableDeclarator1777);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						char_literal174_tree = (Object) adaptor
								.create(char_literal174);
						adaptor.addChild(root_0, char_literal174_tree);
					}
					pushFollow(FOLLOW_variableInitializer_in_variableDeclarator1779);
					variableInitializer175 = variableInitializer();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, variableInitializer175
								.getTree());

				}
					break;

				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 43, variableDeclarator_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "variableDeclarator"

	public static class constantDeclaratorsRest_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "constantDeclaratorsRest"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:409:1:
	// constantDeclaratorsRest : constantDeclaratorRest ( ',' constantDeclarator
	// )* ;
	public final JavaParser.constantDeclaratorsRest_return constantDeclaratorsRest()
			throws RecognitionException {
		JavaParser.constantDeclaratorsRest_return retval = new JavaParser.constantDeclaratorsRest_return();
		retval.start = input.LT(1);
		int constantDeclaratorsRest_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal177 = null;
		JavaParser.constantDeclaratorRest_return constantDeclaratorRest176 = null;

		JavaParser.constantDeclarator_return constantDeclarator178 = null;

		Object char_literal177_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 44)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:410:5:
			// ( constantDeclaratorRest ( ',' constantDeclarator )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:410:9:
			// constantDeclaratorRest ( ',' constantDeclarator )*
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_constantDeclaratorRest_in_constantDeclaratorsRest1804);
				constantDeclaratorRest176 = constantDeclaratorRest();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, constantDeclaratorRest176
							.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:410:32:
				// ( ',' constantDeclarator )*
				loop59: do {
					int alt59 = 2;
					int LA59_0 = input.LA(1);

					if ((LA59_0 == 43)) {
						alt59 = 1;
					}

					switch (alt59) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:410:33:
						// ',' constantDeclarator
					{
						char_literal177 = (Token) match(input, 43,
								FOLLOW_43_in_constantDeclaratorsRest1807);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal177_tree = (Object) adaptor
									.create(char_literal177);
							adaptor.addChild(root_0, char_literal177_tree);
						}
						pushFollow(FOLLOW_constantDeclarator_in_constantDeclaratorsRest1809);
						constantDeclarator178 = constantDeclarator();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, constantDeclarator178
									.getTree());

					}
						break;

					default:
						break loop59;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 44, constantDeclaratorsRest_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "constantDeclaratorsRest"

	public static class constantDeclaratorRest_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "constantDeclaratorRest"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:413:1:
	// constantDeclaratorRest : ( '[' ']' )* '=' variableInitializer ;
	public final JavaParser.constantDeclaratorRest_return constantDeclaratorRest()
			throws RecognitionException {
		JavaParser.constantDeclaratorRest_return retval = new JavaParser.constantDeclaratorRest_return();
		retval.start = input.LT(1);
		int constantDeclaratorRest_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal179 = null;
		Token char_literal180 = null;
		Token char_literal181 = null;
		JavaParser.variableInitializer_return variableInitializer182 = null;

		Object char_literal179_tree = null;
		Object char_literal180_tree = null;
		Object char_literal181_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 45)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:414:5:
			// ( ( '[' ']' )* '=' variableInitializer )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:414:9:
			// ( '[' ']' )* '=' variableInitializer
			{
				root_0 = (Object) adaptor.nil();

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:414:9:
				// ( '[' ']' )*
				loop60: do {
					int alt60 = 2;
					int LA60_0 = input.LA(1);

					if ((LA60_0 == 50)) {
						alt60 = 1;
					}

					switch (alt60) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:414:10:
						// '[' ']'
					{
						char_literal179 = (Token) match(input, 50,
								FOLLOW_50_in_constantDeclaratorRest1831);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal179_tree = (Object) adaptor
									.create(char_literal179);
							adaptor.addChild(root_0, char_literal179_tree);
						}
						char_literal180 = (Token) match(input, 51,
								FOLLOW_51_in_constantDeclaratorRest1833);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal180_tree = (Object) adaptor
									.create(char_literal180);
							adaptor.addChild(root_0, char_literal180_tree);
						}

					}
						break;

					default:
						break loop60;
					}
				} while (true);

				char_literal181 = (Token) match(input, 53,
						FOLLOW_53_in_constantDeclaratorRest1837);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal181_tree = (Object) adaptor
							.create(char_literal181);
					adaptor.addChild(root_0, char_literal181_tree);
				}
				pushFollow(FOLLOW_variableInitializer_in_constantDeclaratorRest1839);
				variableInitializer182 = variableInitializer();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, variableInitializer182.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 45, constantDeclaratorRest_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "constantDeclaratorRest"

	public static class variableDeclaratorId_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "variableDeclaratorId"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:417:1:
	// variableDeclaratorId : Identifier ( '[' ']' )* ;
	public final JavaParser.variableDeclaratorId_return variableDeclaratorId()
			throws RecognitionException {
		JavaParser.variableDeclaratorId_return retval = new JavaParser.variableDeclaratorId_return();
		retval.start = input.LT(1);
		int variableDeclaratorId_StartIndex = input.index();
		Object root_0 = null;

		Token Identifier183 = null;
		Token char_literal184 = null;
		Token char_literal185 = null;

		Object Identifier183_tree = null;
		Object char_literal184_tree = null;
		Object char_literal185_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 46)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:418:5:
			// ( Identifier ( '[' ']' )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:418:9:
			// Identifier ( '[' ']' )*
			{
				root_0 = (Object) adaptor.nil();

				Identifier183 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_variableDeclaratorId1862);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier183_tree = (Object) adaptor.create(Identifier183);
					adaptor.addChild(root_0, Identifier183_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:418:20:
				// ( '[' ']' )*
				loop61: do {
					int alt61 = 2;
					int LA61_0 = input.LA(1);

					if ((LA61_0 == 50)) {
						alt61 = 1;
					}

					switch (alt61) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:418:21:
						// '[' ']'
					{
						char_literal184 = (Token) match(input, 50,
								FOLLOW_50_in_variableDeclaratorId1865);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal184_tree = (Object) adaptor
									.create(char_literal184);
							adaptor.addChild(root_0, char_literal184_tree);
						}
						char_literal185 = (Token) match(input, 51,
								FOLLOW_51_in_variableDeclaratorId1867);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal185_tree = (Object) adaptor
									.create(char_literal185);
							adaptor.addChild(root_0, char_literal185_tree);
						}

					}
						break;

					default:
						break loop61;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 46, variableDeclaratorId_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "variableDeclaratorId"

	public static class variableInitializer_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "variableInitializer"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:421:1:
	// variableInitializer : ( arrayInitializer | expression );
	public final JavaParser.variableInitializer_return variableInitializer()
			throws RecognitionException {
		JavaParser.variableInitializer_return retval = new JavaParser.variableInitializer_return();
		retval.start = input.LT(1);
		int variableInitializer_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.arrayInitializer_return arrayInitializer186 = null;

		JavaParser.expression_return expression187 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 47)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:422:5:
			// ( arrayInitializer | expression )
			int alt62 = 2;
			int LA62_0 = input.LA(1);

			if ((LA62_0 == 46)) {
				alt62 = 1;
			} else if ((LA62_0 == Identifier
					|| (LA62_0 >= FloatingPointLiteral && LA62_0 <= DecimalLiteral)
					|| LA62_0 == 49 || (LA62_0 >= 58 && LA62_0 <= 65)
					|| (LA62_0 >= 67 && LA62_0 <= 68)
					|| (LA62_0 >= 71 && LA62_0 <= 74)
					|| (LA62_0 >= 107 && LA62_0 <= 108) || (LA62_0 >= 111 && LA62_0 <= 115))) {
				alt62 = 2;
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 62, 0,
						input);

				throw nvae;
			}
			switch (alt62) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:422:9:
				// arrayInitializer
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_arrayInitializer_in_variableInitializer1888);
				arrayInitializer186 = arrayInitializer();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, arrayInitializer186.getTree());

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:423:9:
				// expression
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_expression_in_variableInitializer1898);
				expression187 = expression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, expression187.getTree());

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 47, variableInitializer_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "variableInitializer"

	public static class arrayInitializer_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "arrayInitializer"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:426:1:
	// arrayInitializer : '{' ( variableInitializer ( ',' variableInitializer )*
	// ( ',' )? )? '}' ;
	public final JavaParser.arrayInitializer_return arrayInitializer()
			throws RecognitionException {
		JavaParser.arrayInitializer_return retval = new JavaParser.arrayInitializer_return();
		retval.start = input.LT(1);
		int arrayInitializer_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal188 = null;
		Token char_literal190 = null;
		Token char_literal192 = null;
		Token char_literal193 = null;
		JavaParser.variableInitializer_return variableInitializer189 = null;

		JavaParser.variableInitializer_return variableInitializer191 = null;

		Object char_literal188_tree = null;
		Object char_literal190_tree = null;
		Object char_literal192_tree = null;
		Object char_literal193_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 48)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:427:5:
			// ( '{' ( variableInitializer ( ',' variableInitializer )* ( ',' )?
			// )? '}' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:427:9:
			// '{' ( variableInitializer ( ',' variableInitializer )* ( ',' )?
			// )? '}'
			{
				root_0 = (Object) adaptor.nil();

				char_literal188 = (Token) match(input, 46,
						FOLLOW_46_in_arrayInitializer1925);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal188_tree = (Object) adaptor
							.create(char_literal188);
					adaptor.addChild(root_0, char_literal188_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:427:13:
				// ( variableInitializer ( ',' variableInitializer )* ( ',' )?
				// )?
				int alt65 = 2;
				int LA65_0 = input.LA(1);

				if ((LA65_0 == Identifier
						|| (LA65_0 >= FloatingPointLiteral && LA65_0 <= DecimalLiteral)
						|| LA65_0 == 46 || LA65_0 == 49
						|| (LA65_0 >= 58 && LA65_0 <= 65)
						|| (LA65_0 >= 67 && LA65_0 <= 68)
						|| (LA65_0 >= 71 && LA65_0 <= 74)
						|| (LA65_0 >= 107 && LA65_0 <= 108) || (LA65_0 >= 111 && LA65_0 <= 115))) {
					alt65 = 1;
				}
				switch (alt65) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:427:14:
					// variableInitializer ( ',' variableInitializer )* ( ',' )?
				{
					pushFollow(FOLLOW_variableInitializer_in_arrayInitializer1928);
					variableInitializer189 = variableInitializer();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, variableInitializer189
								.getTree());
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:427:34:
					// ( ',' variableInitializer )*
					loop63: do {
						int alt63 = 2;
						int LA63_0 = input.LA(1);

						if ((LA63_0 == 43)) {
							int LA63_1 = input.LA(2);

							if ((LA63_1 == Identifier
									|| (LA63_1 >= FloatingPointLiteral && LA63_1 <= DecimalLiteral)
									|| LA63_1 == 46 || LA63_1 == 49
									|| (LA63_1 >= 58 && LA63_1 <= 65)
									|| (LA63_1 >= 67 && LA63_1 <= 68)
									|| (LA63_1 >= 71 && LA63_1 <= 74)
									|| (LA63_1 >= 107 && LA63_1 <= 108) || (LA63_1 >= 111 && LA63_1 <= 115))) {
								alt63 = 1;
							}

						}

						switch (alt63) {
						case 1:
							// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:427:35:
							// ',' variableInitializer
						{
							char_literal190 = (Token) match(input, 43,
									FOLLOW_43_in_arrayInitializer1931);
							if (state.failed)
								return retval;
							if (state.backtracking == 0) {
								char_literal190_tree = (Object) adaptor
										.create(char_literal190);
								adaptor.addChild(root_0, char_literal190_tree);
							}
							pushFollow(FOLLOW_variableInitializer_in_arrayInitializer1933);
							variableInitializer191 = variableInitializer();

							state._fsp--;
							if (state.failed)
								return retval;
							if (state.backtracking == 0)
								adaptor.addChild(root_0, variableInitializer191
										.getTree());

						}
							break;

						default:
							break loop63;
						}
					} while (true);

					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:427:61:
					// ( ',' )?
					int alt64 = 2;
					int LA64_0 = input.LA(1);

					if ((LA64_0 == 43)) {
						alt64 = 1;
					}
					switch (alt64) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:427:62:
						// ','
					{
						char_literal192 = (Token) match(input, 43,
								FOLLOW_43_in_arrayInitializer1938);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal192_tree = (Object) adaptor
									.create(char_literal192);
							adaptor.addChild(root_0, char_literal192_tree);
						}

					}
						break;

					}

				}
					break;

				}

				char_literal193 = (Token) match(input, 47,
						FOLLOW_47_in_arrayInitializer1945);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal193_tree = (Object) adaptor
							.create(char_literal193);
					adaptor.addChild(root_0, char_literal193_tree);
				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 48, arrayInitializer_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "arrayInitializer"

	public static class modifier_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "modifier"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:430:1:
	// modifier : ( annotation | 'public' | 'protected' | 'private' | 'static' |
	// 'abstract' | 'final' | 'native' | 'synchronized' | 'transient' |
	// 'volatile' | 'strictfp' );
	public final JavaParser.modifier_return modifier()
			throws RecognitionException {
		JavaParser.modifier_return retval = new JavaParser.modifier_return();
		retval.start = input.LT(1);
		int modifier_StartIndex = input.index();
		Object root_0 = null;

		Token string_literal195 = null;
		Token string_literal196 = null;
		Token string_literal197 = null;
		Token string_literal198 = null;
		Token string_literal199 = null;
		Token string_literal200 = null;
		Token string_literal201 = null;
		Token string_literal202 = null;
		Token string_literal203 = null;
		Token string_literal204 = null;
		Token string_literal205 = null;
		JavaParser.annotation_return annotation194 = null;

		Object string_literal195_tree = null;
		Object string_literal196_tree = null;
		Object string_literal197_tree = null;
		Object string_literal198_tree = null;
		Object string_literal199_tree = null;
		Object string_literal200_tree = null;
		Object string_literal201_tree = null;
		Object string_literal202_tree = null;
		Object string_literal203_tree = null;
		Object string_literal204_tree = null;
		Object string_literal205_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 49)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:431:5:
			// ( annotation | 'public' | 'protected' | 'private' | 'static' |
			// 'abstract' | 'final' | 'native' | 'synchronized' | 'transient' |
			// 'volatile' | 'strictfp' )
			int alt66 = 12;
			switch (input.LA(1)) {
			case 75: {
				alt66 = 1;
			}
				break;
			case 33: {
				alt66 = 2;
			}
				break;
			case 34: {
				alt66 = 3;
			}
				break;
			case 35: {
				alt66 = 4;
			}
				break;
			case 30: {
				alt66 = 5;
			}
				break;
			case 36: {
				alt66 = 6;
			}
				break;
			case 37: {
				alt66 = 7;
			}
				break;
			case 54: {
				alt66 = 8;
			}
				break;
			case 55: {
				alt66 = 9;
			}
				break;
			case 56: {
				alt66 = 10;
			}
				break;
			case 57: {
				alt66 = 11;
			}
				break;
			case 38: {
				alt66 = 12;
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
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:431:9:
				// annotation
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_annotation_in_modifier1964);
				annotation194 = annotation();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, annotation194.getTree());

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:432:9:
				// 'public'
			{
				root_0 = (Object) adaptor.nil();

				string_literal195 = (Token) match(input, 33,
						FOLLOW_33_in_modifier1974);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal195_tree = (Object) adaptor
							.create(string_literal195);
					adaptor.addChild(root_0, string_literal195_tree);
				}

			}
				break;
			case 3:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:433:9:
				// 'protected'
			{
				root_0 = (Object) adaptor.nil();

				string_literal196 = (Token) match(input, 34,
						FOLLOW_34_in_modifier1984);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal196_tree = (Object) adaptor
							.create(string_literal196);
					adaptor.addChild(root_0, string_literal196_tree);
				}

			}
				break;
			case 4:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:434:9:
				// 'private'
			{
				root_0 = (Object) adaptor.nil();

				string_literal197 = (Token) match(input, 35,
						FOLLOW_35_in_modifier1994);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal197_tree = (Object) adaptor
							.create(string_literal197);
					adaptor.addChild(root_0, string_literal197_tree);
				}

			}
				break;
			case 5:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:435:9:
				// 'static'
			{
				root_0 = (Object) adaptor.nil();

				string_literal198 = (Token) match(input, 30,
						FOLLOW_30_in_modifier2004);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal198_tree = (Object) adaptor
							.create(string_literal198);
					adaptor.addChild(root_0, string_literal198_tree);
				}

			}
				break;
			case 6:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:436:9:
				// 'abstract'
			{
				root_0 = (Object) adaptor.nil();

				string_literal199 = (Token) match(input, 36,
						FOLLOW_36_in_modifier2014);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal199_tree = (Object) adaptor
							.create(string_literal199);
					adaptor.addChild(root_0, string_literal199_tree);
				}

			}
				break;
			case 7:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:437:9:
				// 'final'
			{
				root_0 = (Object) adaptor.nil();

				string_literal200 = (Token) match(input, 37,
						FOLLOW_37_in_modifier2024);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal200_tree = (Object) adaptor
							.create(string_literal200);
					adaptor.addChild(root_0, string_literal200_tree);
				}

			}
				break;
			case 8:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:438:9:
				// 'native'
			{
				root_0 = (Object) adaptor.nil();

				string_literal201 = (Token) match(input, 54,
						FOLLOW_54_in_modifier2034);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal201_tree = (Object) adaptor
							.create(string_literal201);
					adaptor.addChild(root_0, string_literal201_tree);
				}

			}
				break;
			case 9:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:439:9:
				// 'synchronized'
			{
				root_0 = (Object) adaptor.nil();

				string_literal202 = (Token) match(input, 55,
						FOLLOW_55_in_modifier2044);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal202_tree = (Object) adaptor
							.create(string_literal202);
					adaptor.addChild(root_0, string_literal202_tree);
				}

			}
				break;
			case 10:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:440:9:
				// 'transient'
			{
				root_0 = (Object) adaptor.nil();

				string_literal203 = (Token) match(input, 56,
						FOLLOW_56_in_modifier2054);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal203_tree = (Object) adaptor
							.create(string_literal203);
					adaptor.addChild(root_0, string_literal203_tree);
				}

			}
				break;
			case 11:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:441:9:
				// 'volatile'
			{
				root_0 = (Object) adaptor.nil();

				string_literal204 = (Token) match(input, 57,
						FOLLOW_57_in_modifier2064);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal204_tree = (Object) adaptor
							.create(string_literal204);
					adaptor.addChild(root_0, string_literal204_tree);
				}

			}
				break;
			case 12:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:442:9:
				// 'strictfp'
			{
				root_0 = (Object) adaptor.nil();

				string_literal205 = (Token) match(input, 38,
						FOLLOW_38_in_modifier2074);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal205_tree = (Object) adaptor
							.create(string_literal205);
					adaptor.addChild(root_0, string_literal205_tree);
				}

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 49, modifier_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "modifier"

	public static class packageOrTypeName_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "packageOrTypeName"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:445:1:
	// packageOrTypeName : qualifiedName ;
	public final JavaParser.packageOrTypeName_return packageOrTypeName()
			throws RecognitionException {
		JavaParser.packageOrTypeName_return retval = new JavaParser.packageOrTypeName_return();
		retval.start = input.LT(1);
		int packageOrTypeName_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.qualifiedName_return qualifiedName206 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 50)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:446:5:
			// ( qualifiedName )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:446:9:
			// qualifiedName
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_qualifiedName_in_packageOrTypeName2093);
				qualifiedName206 = qualifiedName();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, qualifiedName206.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 50, packageOrTypeName_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "packageOrTypeName"

	public static class enumConstantName_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "enumConstantName"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:449:1:
	// enumConstantName : Identifier ;
	public final JavaParser.enumConstantName_return enumConstantName()
			throws RecognitionException {
		JavaParser.enumConstantName_return retval = new JavaParser.enumConstantName_return();
		retval.start = input.LT(1);
		int enumConstantName_StartIndex = input.index();
		Object root_0 = null;

		Token Identifier207 = null;

		Object Identifier207_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 51)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:450:5:
			// ( Identifier )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:450:9:
			// Identifier
			{
				root_0 = (Object) adaptor.nil();

				Identifier207 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_enumConstantName2112);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier207_tree = (Object) adaptor.create(Identifier207);
					adaptor.addChild(root_0, Identifier207_tree);
				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 51, enumConstantName_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "enumConstantName"

	public static class typeName_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "typeName"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:453:1:
	// typeName : qualifiedName ;
	public final JavaParser.typeName_return typeName()
			throws RecognitionException {
		JavaParser.typeName_return retval = new JavaParser.typeName_return();
		retval.start = input.LT(1);
		int typeName_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.qualifiedName_return qualifiedName208 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 52)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:454:5:
			// ( qualifiedName )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:454:9:
			// qualifiedName
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_qualifiedName_in_typeName2131);
				qualifiedName208 = qualifiedName();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, qualifiedName208.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 52, typeName_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "typeName"

	public static class type_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "type"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:457:1:
	// type : ( classOrInterfaceType ( '[' ']' )* | primitiveType ( '[' ']' )*
	// );
	public final JavaParser.type_return type() throws RecognitionException {
		JavaParser.type_return retval = new JavaParser.type_return();
		retval.start = input.LT(1);
		int type_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal210 = null;
		Token char_literal211 = null;
		Token char_literal213 = null;
		Token char_literal214 = null;
		JavaParser.classOrInterfaceType_return classOrInterfaceType209 = null;

		JavaParser.primitiveType_return primitiveType212 = null;

		Object char_literal210_tree = null;
		Object char_literal211_tree = null;
		Object char_literal213_tree = null;
		Object char_literal214_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 53)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:458:2:
			// ( classOrInterfaceType ( '[' ']' )* | primitiveType ( '[' ']' )*
			// )
			int alt69 = 2;
			int LA69_0 = input.LA(1);

			if ((LA69_0 == Identifier)) {
				alt69 = 1;
			} else if (((LA69_0 >= 58 && LA69_0 <= 65))) {
				alt69 = 2;
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 69, 0,
						input);

				throw nvae;
			}
			switch (alt69) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:458:4:
				// classOrInterfaceType ( '[' ']' )*
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_classOrInterfaceType_in_type2145);
				classOrInterfaceType209 = classOrInterfaceType();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, classOrInterfaceType209.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:458:25:
				// ( '[' ']' )*
				loop67: do {
					int alt67 = 2;
					int LA67_0 = input.LA(1);

					if ((LA67_0 == 50)) {
						alt67 = 1;
					}

					switch (alt67) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:458:26:
						// '[' ']'
					{
						char_literal210 = (Token) match(input, 50,
								FOLLOW_50_in_type2148);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal210_tree = (Object) adaptor
									.create(char_literal210);
							adaptor.addChild(root_0, char_literal210_tree);
						}
						char_literal211 = (Token) match(input, 51,
								FOLLOW_51_in_type2150);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal211_tree = (Object) adaptor
									.create(char_literal211);
							adaptor.addChild(root_0, char_literal211_tree);
						}

					}
						break;

					default:
						break loop67;
					}
				} while (true);

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:459:4:
				// primitiveType ( '[' ']' )*
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_primitiveType_in_type2157);
				primitiveType212 = primitiveType();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, primitiveType212.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:459:18:
				// ( '[' ']' )*
				loop68: do {
					int alt68 = 2;
					int LA68_0 = input.LA(1);

					if ((LA68_0 == 50)) {
						alt68 = 1;
					}

					switch (alt68) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:459:19:
						// '[' ']'
					{
						char_literal213 = (Token) match(input, 50,
								FOLLOW_50_in_type2160);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal213_tree = (Object) adaptor
									.create(char_literal213);
							adaptor.addChild(root_0, char_literal213_tree);
						}
						char_literal214 = (Token) match(input, 51,
								FOLLOW_51_in_type2162);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal214_tree = (Object) adaptor
									.create(char_literal214);
							adaptor.addChild(root_0, char_literal214_tree);
						}

					}
						break;

					default:
						break loop68;
					}
				} while (true);

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 53, type_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "type"

	public static class classOrInterfaceType_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "classOrInterfaceType"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:462:1:
	// classOrInterfaceType : Identifier ( typeArguments )? ( '.' Identifier (
	// typeArguments )? )* ;
	public final JavaParser.classOrInterfaceType_return classOrInterfaceType()
			throws RecognitionException {
		JavaParser.classOrInterfaceType_return retval = new JavaParser.classOrInterfaceType_return();
		retval.start = input.LT(1);
		int classOrInterfaceType_StartIndex = input.index();
		Object root_0 = null;

		Token Identifier215 = null;
		Token char_literal217 = null;
		Token Identifier218 = null;
		JavaParser.typeArguments_return typeArguments216 = null;

		JavaParser.typeArguments_return typeArguments219 = null;

		Object Identifier215_tree = null;
		Object char_literal217_tree = null;
		Object Identifier218_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 54)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:463:2:
			// ( Identifier ( typeArguments )? ( '.' Identifier ( typeArguments
			// )? )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:463:4:
			// Identifier ( typeArguments )? ( '.' Identifier ( typeArguments )?
			// )*
			{
				root_0 = (Object) adaptor.nil();

				Identifier215 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_classOrInterfaceType2175);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier215_tree = (Object) adaptor.create(Identifier215);
					adaptor.addChild(root_0, Identifier215_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:463:15:
				// ( typeArguments )?
				int alt70 = 2;
				int LA70_0 = input.LA(1);

				if ((LA70_0 == 42)) {
					int LA70_1 = input.LA(2);

					if ((LA70_1 == Identifier || (LA70_1 >= 58 && LA70_1 <= 66))) {
						alt70 = 1;
					}
				}
				switch (alt70) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// typeArguments
				{
					pushFollow(FOLLOW_typeArguments_in_classOrInterfaceType2177);
					typeArguments216 = typeArguments();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, typeArguments216.getTree());

				}
					break;

				}

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:463:30:
				// ( '.' Identifier ( typeArguments )? )*
				loop72: do {
					int alt72 = 2;
					int LA72_0 = input.LA(1);

					if ((LA72_0 == 31)) {
						alt72 = 1;
					}

					switch (alt72) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:463:31:
						// '.' Identifier ( typeArguments )?
					{
						char_literal217 = (Token) match(input, 31,
								FOLLOW_31_in_classOrInterfaceType2181);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal217_tree = (Object) adaptor
									.create(char_literal217);
							adaptor.addChild(root_0, char_literal217_tree);
						}
						Identifier218 = (Token) match(input, Identifier,
								FOLLOW_Identifier_in_classOrInterfaceType2183);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							Identifier218_tree = (Object) adaptor
									.create(Identifier218);
							adaptor.addChild(root_0, Identifier218_tree);
						}
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:463:46:
						// ( typeArguments )?
						int alt71 = 2;
						int LA71_0 = input.LA(1);

						if ((LA71_0 == 42)) {
							int LA71_1 = input.LA(2);

							if ((LA71_1 == Identifier || (LA71_1 >= 58 && LA71_1 <= 66))) {
								alt71 = 1;
							}
						}
						switch (alt71) {
						case 1:
							// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
							// typeArguments
						{
							pushFollow(FOLLOW_typeArguments_in_classOrInterfaceType2185);
							typeArguments219 = typeArguments();

							state._fsp--;
							if (state.failed)
								return retval;
							if (state.backtracking == 0)
								adaptor.addChild(root_0, typeArguments219
										.getTree());

						}
							break;

						}

					}
						break;

					default:
						break loop72;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 54, classOrInterfaceType_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "classOrInterfaceType"

	public static class primitiveType_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "primitiveType"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:466:1:
	// primitiveType : ( 'boolean' | 'char' | 'byte' | 'short' | 'int' | 'long'
	// | 'float' | 'double' );
	public final JavaParser.primitiveType_return primitiveType()
			throws RecognitionException {
		JavaParser.primitiveType_return retval = new JavaParser.primitiveType_return();
		retval.start = input.LT(1);
		int primitiveType_StartIndex = input.index();
		Object root_0 = null;

		Token set220 = null;

		Object set220_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 55)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:467:5:
			// ( 'boolean' | 'char' | 'byte' | 'short' | 'int' | 'long' |
			// 'float' | 'double' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:
			{
				root_0 = (Object) adaptor.nil();

				set220 = (Token) input.LT(1);
				if ((input.LA(1) >= 58 && input.LA(1) <= 65)) {
					input.consume();
					if (state.backtracking == 0)
						adaptor.addChild(root_0, (Object) adaptor
								.create(set220));
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

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 55, primitiveType_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "primitiveType"

	public static class variableModifier_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "variableModifier"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:477:1:
	// variableModifier : ( 'final' | annotation );
	public final JavaParser.variableModifier_return variableModifier()
			throws RecognitionException {
		JavaParser.variableModifier_return retval = new JavaParser.variableModifier_return();
		retval.start = input.LT(1);
		int variableModifier_StartIndex = input.index();
		Object root_0 = null;

		Token string_literal221 = null;
		JavaParser.annotation_return annotation222 = null;

		Object string_literal221_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 56)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:478:5:
			// ( 'final' | annotation )
			int alt73 = 2;
			int LA73_0 = input.LA(1);

			if ((LA73_0 == 37)) {
				alt73 = 1;
			} else if ((LA73_0 == 75)) {
				alt73 = 2;
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 73, 0,
						input);

				throw nvae;
			}
			switch (alt73) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:478:9:
				// 'final'
			{
				root_0 = (Object) adaptor.nil();

				string_literal221 = (Token) match(input, 37,
						FOLLOW_37_in_variableModifier2294);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal221_tree = (Object) adaptor
							.create(string_literal221);
					adaptor.addChild(root_0, string_literal221_tree);
				}

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:479:9:
				// annotation
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_annotation_in_variableModifier2304);
				annotation222 = annotation();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, annotation222.getTree());

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 56, variableModifier_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "variableModifier"

	public static class typeArguments_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "typeArguments"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:482:1:
	// typeArguments : '<' typeArgument ( ',' typeArgument )* '>' ;
	public final JavaParser.typeArguments_return typeArguments()
			throws RecognitionException {
		JavaParser.typeArguments_return retval = new JavaParser.typeArguments_return();
		retval.start = input.LT(1);
		int typeArguments_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal223 = null;
		Token char_literal225 = null;
		Token char_literal227 = null;
		JavaParser.typeArgument_return typeArgument224 = null;

		JavaParser.typeArgument_return typeArgument226 = null;

		Object char_literal223_tree = null;
		Object char_literal225_tree = null;
		Object char_literal227_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 57)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:483:5:
			// ( '<' typeArgument ( ',' typeArgument )* '>' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:483:9:
			// '<' typeArgument ( ',' typeArgument )* '>'
			{
				root_0 = (Object) adaptor.nil();

				char_literal223 = (Token) match(input, 42,
						FOLLOW_42_in_typeArguments2323);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal223_tree = (Object) adaptor
							.create(char_literal223);
					adaptor.addChild(root_0, char_literal223_tree);
				}
				pushFollow(FOLLOW_typeArgument_in_typeArguments2325);
				typeArgument224 = typeArgument();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, typeArgument224.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:483:26:
				// ( ',' typeArgument )*
				loop74: do {
					int alt74 = 2;
					int LA74_0 = input.LA(1);

					if ((LA74_0 == 43)) {
						alt74 = 1;
					}

					switch (alt74) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:483:27:
						// ',' typeArgument
					{
						char_literal225 = (Token) match(input, 43,
								FOLLOW_43_in_typeArguments2328);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal225_tree = (Object) adaptor
									.create(char_literal225);
							adaptor.addChild(root_0, char_literal225_tree);
						}
						pushFollow(FOLLOW_typeArgument_in_typeArguments2330);
						typeArgument226 = typeArgument();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, typeArgument226.getTree());

					}
						break;

					default:
						break loop74;
					}
				} while (true);

				char_literal227 = (Token) match(input, 44,
						FOLLOW_44_in_typeArguments2334);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal227_tree = (Object) adaptor
							.create(char_literal227);
					adaptor.addChild(root_0, char_literal227_tree);
				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 57, typeArguments_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "typeArguments"

	public static class typeArgument_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "typeArgument"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:486:1:
	// typeArgument : ( type | '?' ( ( 'extends' | 'super' ) type )? );
	public final JavaParser.typeArgument_return typeArgument()
			throws RecognitionException {
		JavaParser.typeArgument_return retval = new JavaParser.typeArgument_return();
		retval.start = input.LT(1);
		int typeArgument_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal229 = null;
		Token set230 = null;
		JavaParser.type_return type228 = null;

		JavaParser.type_return type231 = null;

		Object char_literal229_tree = null;
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 58)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:487:5:
			// ( type | '?' ( ( 'extends' | 'super' ) type )? )
			int alt76 = 2;
			int LA76_0 = input.LA(1);

			if ((LA76_0 == Identifier || (LA76_0 >= 58 && LA76_0 <= 65))) {
				alt76 = 1;
			} else if ((LA76_0 == 66)) {
				alt76 = 2;
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 76, 0,
						input);

				throw nvae;
			}
			switch (alt76) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:487:9:
				// type
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_type_in_typeArgument2357);
				type228 = type();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, type228.getTree());

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:488:9:
				// '?' ( ( 'extends' | 'super' ) type )?
			{
				root_0 = (Object) adaptor.nil();

				char_literal229 = (Token) match(input, 66,
						FOLLOW_66_in_typeArgument2367);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal229_tree = (Object) adaptor
							.create(char_literal229);
					adaptor.addChild(root_0, char_literal229_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:488:13:
				// ( ( 'extends' | 'super' ) type )?
				int alt75 = 2;
				int LA75_0 = input.LA(1);

				if ((LA75_0 == 40 || LA75_0 == 67)) {
					alt75 = 1;
				}
				switch (alt75) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:488:14:
					// ( 'extends' | 'super' ) type
				{
					set230 = (Token) input.LT(1);
					if (input.LA(1) == 40 || input.LA(1) == 67) {
						input.consume();
						if (state.backtracking == 0)
							adaptor.addChild(root_0, (Object) adaptor
									.create(set230));
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

					pushFollow(FOLLOW_type_in_typeArgument2378);
					type231 = type();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, type231.getTree());

				}
					break;

				}

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 58, typeArgument_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "typeArgument"

	public static class qualifiedNameList_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "qualifiedNameList"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:491:1:
	// qualifiedNameList : qualifiedName ( ',' qualifiedName )* ;
	public final JavaParser.qualifiedNameList_return qualifiedNameList()
			throws RecognitionException {
		JavaParser.qualifiedNameList_return retval = new JavaParser.qualifiedNameList_return();
		retval.start = input.LT(1);
		int qualifiedNameList_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal233 = null;
		JavaParser.qualifiedName_return qualifiedName232 = null;

		JavaParser.qualifiedName_return qualifiedName234 = null;

		Object char_literal233_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 59)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:492:5:
			// ( qualifiedName ( ',' qualifiedName )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:492:9:
			// qualifiedName ( ',' qualifiedName )*
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_qualifiedName_in_qualifiedNameList2403);
				qualifiedName232 = qualifiedName();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, qualifiedName232.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:492:23:
				// ( ',' qualifiedName )*
				loop77: do {
					int alt77 = 2;
					int LA77_0 = input.LA(1);

					if ((LA77_0 == 43)) {
						alt77 = 1;
					}

					switch (alt77) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:492:24:
						// ',' qualifiedName
					{
						char_literal233 = (Token) match(input, 43,
								FOLLOW_43_in_qualifiedNameList2406);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal233_tree = (Object) adaptor
									.create(char_literal233);
							adaptor.addChild(root_0, char_literal233_tree);
						}
						pushFollow(FOLLOW_qualifiedName_in_qualifiedNameList2408);
						qualifiedName234 = qualifiedName();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor
									.addChild(root_0, qualifiedName234
											.getTree());

					}
						break;

					default:
						break loop77;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 59, qualifiedNameList_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "qualifiedNameList"

	public static class formalParameters_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "formalParameters"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:495:1:
	// formalParameters : '(' ( formalParameterDecls )? ')' ;
	public final JavaParser.formalParameters_return formalParameters()
			throws RecognitionException {
		JavaParser.formalParameters_return retval = new JavaParser.formalParameters_return();
		retval.start = input.LT(1);
		int formalParameters_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal235 = null;
		Token char_literal237 = null;
		JavaParser.formalParameterDecls_return formalParameterDecls236 = null;

		Object char_literal235_tree = null;
		Object char_literal237_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 60)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:496:5:
			// ( '(' ( formalParameterDecls )? ')' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:496:9:
			// '(' ( formalParameterDecls )? ')'
			{
				root_0 = (Object) adaptor.nil();

				char_literal235 = (Token) match(input, 68,
						FOLLOW_68_in_formalParameters2429);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal235_tree = (Object) adaptor
							.create(char_literal235);
					adaptor.addChild(root_0, char_literal235_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:496:13:
				// ( formalParameterDecls )?
				int alt78 = 2;
				int LA78_0 = input.LA(1);

				if ((LA78_0 == Identifier || LA78_0 == 37
						|| (LA78_0 >= 58 && LA78_0 <= 65) || LA78_0 == 75)) {
					alt78 = 1;
				}
				switch (alt78) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// formalParameterDecls
				{
					pushFollow(FOLLOW_formalParameterDecls_in_formalParameters2431);
					formalParameterDecls236 = formalParameterDecls();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, formalParameterDecls236
								.getTree());

				}
					break;

				}

				char_literal237 = (Token) match(input, 69,
						FOLLOW_69_in_formalParameters2434);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal237_tree = (Object) adaptor
							.create(char_literal237);
					adaptor.addChild(root_0, char_literal237_tree);
				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 60, formalParameters_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "formalParameters"

	public static class formalParameterDecls_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "formalParameterDecls"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:499:1:
	// formalParameterDecls : variableModifiers type formalParameterDeclsRest ;
	public final JavaParser.formalParameterDecls_return formalParameterDecls()
			throws RecognitionException {
		JavaParser.formalParameterDecls_return retval = new JavaParser.formalParameterDecls_return();
		retval.start = input.LT(1);
		int formalParameterDecls_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.variableModifiers_return variableModifiers238 = null;

		JavaParser.type_return type239 = null;

		JavaParser.formalParameterDeclsRest_return formalParameterDeclsRest240 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 61)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:500:5:
			// ( variableModifiers type formalParameterDeclsRest )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:500:9:
			// variableModifiers type formalParameterDeclsRest
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_variableModifiers_in_formalParameterDecls2457);
				variableModifiers238 = variableModifiers();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, variableModifiers238.getTree());
				pushFollow(FOLLOW_type_in_formalParameterDecls2459);
				type239 = type();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, type239.getTree());
				pushFollow(FOLLOW_formalParameterDeclsRest_in_formalParameterDecls2461);
				formalParameterDeclsRest240 = formalParameterDeclsRest();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, formalParameterDeclsRest240
							.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 61, formalParameterDecls_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "formalParameterDecls"

	public static class formalParameterDeclsRest_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "formalParameterDeclsRest"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:503:1:
	// formalParameterDeclsRest : ( variableDeclaratorId ( ','
	// formalParameterDecls )? | '...' variableDeclaratorId );
	public final JavaParser.formalParameterDeclsRest_return formalParameterDeclsRest()
			throws RecognitionException {
		JavaParser.formalParameterDeclsRest_return retval = new JavaParser.formalParameterDeclsRest_return();
		retval.start = input.LT(1);
		int formalParameterDeclsRest_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal242 = null;
		Token string_literal244 = null;
		JavaParser.variableDeclaratorId_return variableDeclaratorId241 = null;

		JavaParser.formalParameterDecls_return formalParameterDecls243 = null;

		JavaParser.variableDeclaratorId_return variableDeclaratorId245 = null;

		Object char_literal242_tree = null;
		Object string_literal244_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 62)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:504:5:
			// ( variableDeclaratorId ( ',' formalParameterDecls )? | '...'
			// variableDeclaratorId )
			int alt80 = 2;
			int LA80_0 = input.LA(1);

			if ((LA80_0 == Identifier)) {
				alt80 = 1;
			} else if ((LA80_0 == 70)) {
				alt80 = 2;
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 80, 0,
						input);

				throw nvae;
			}
			switch (alt80) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:504:9:
				// variableDeclaratorId ( ',' formalParameterDecls )?
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_variableDeclaratorId_in_formalParameterDeclsRest2484);
				variableDeclaratorId241 = variableDeclaratorId();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, variableDeclaratorId241.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:504:30:
				// ( ',' formalParameterDecls )?
				int alt79 = 2;
				int LA79_0 = input.LA(1);

				if ((LA79_0 == 43)) {
					alt79 = 1;
				}
				switch (alt79) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:504:31:
					// ',' formalParameterDecls
				{
					char_literal242 = (Token) match(input, 43,
							FOLLOW_43_in_formalParameterDeclsRest2487);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						char_literal242_tree = (Object) adaptor
								.create(char_literal242);
						adaptor.addChild(root_0, char_literal242_tree);
					}
					pushFollow(FOLLOW_formalParameterDecls_in_formalParameterDeclsRest2489);
					formalParameterDecls243 = formalParameterDecls();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, formalParameterDecls243
								.getTree());

				}
					break;

				}

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:505:9:
				// '...' variableDeclaratorId
			{
				root_0 = (Object) adaptor.nil();

				string_literal244 = (Token) match(input, 70,
						FOLLOW_70_in_formalParameterDeclsRest2501);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal244_tree = (Object) adaptor
							.create(string_literal244);
					adaptor.addChild(root_0, string_literal244_tree);
				}
				pushFollow(FOLLOW_variableDeclaratorId_in_formalParameterDeclsRest2503);
				variableDeclaratorId245 = variableDeclaratorId();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, variableDeclaratorId245.getTree());

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 62, formalParameterDeclsRest_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "formalParameterDeclsRest"

	public static class methodBody_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "methodBody"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:508:1:
	// methodBody : block ;
	public final JavaParser.methodBody_return methodBody()
			throws RecognitionException {
		JavaParser.methodBody_return retval = new JavaParser.methodBody_return();
		retval.start = input.LT(1);
		int methodBody_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.block_return block246 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 63)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:509:5:
			// ( block )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:509:9:
			// block
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_block_in_methodBody2526);
				block246 = block();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, block246.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 63, methodBody_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "methodBody"

	public static class constructorBody_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "constructorBody"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:512:1:
	// constructorBody : '{' ( explicitConstructorInvocation )? ( blockStatement
	// )* '}' ;
	public final JavaParser.constructorBody_return constructorBody()
			throws RecognitionException {
		JavaParser.constructorBody_return retval = new JavaParser.constructorBody_return();
		retval.start = input.LT(1);
		int constructorBody_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal247 = null;
		Token char_literal250 = null;
		JavaParser.explicitConstructorInvocation_return explicitConstructorInvocation248 = null;

		JavaParser.blockStatement_return blockStatement249 = null;

		Object char_literal247_tree = null;
		Object char_literal250_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 64)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:513:5:
			// ( '{' ( explicitConstructorInvocation )? ( blockStatement )* '}'
			// )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:513:9:
			// '{' ( explicitConstructorInvocation )? ( blockStatement )* '}'
			{
				root_0 = (Object) adaptor.nil();

				char_literal247 = (Token) match(input, 46,
						FOLLOW_46_in_constructorBody2545);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal247_tree = (Object) adaptor
							.create(char_literal247);
					adaptor.addChild(root_0, char_literal247_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:513:13:
				// ( explicitConstructorInvocation )?
				int alt81 = 2;
				alt81 = dfa81.predict(input);
				switch (alt81) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// explicitConstructorInvocation
				{
					pushFollow(FOLLOW_explicitConstructorInvocation_in_constructorBody2547);
					explicitConstructorInvocation248 = explicitConstructorInvocation();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0,
								explicitConstructorInvocation248.getTree());

				}
					break;

				}

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:513:44:
				// ( blockStatement )*
				loop82: do {
					int alt82 = 2;
					int LA82_0 = input.LA(1);

					if (((LA82_0 >= Identifier && LA82_0 <= ASSERT)
							|| LA82_0 == 28 || LA82_0 == 30
							|| (LA82_0 >= 33 && LA82_0 <= 39) || LA82_0 == 46
							|| (LA82_0 >= 48 && LA82_0 <= 49) || LA82_0 == 55
							|| (LA82_0 >= 58 && LA82_0 <= 65)
							|| (LA82_0 >= 67 && LA82_0 <= 68)
							|| (LA82_0 >= 71 && LA82_0 <= 75) || LA82_0 == 78
							|| (LA82_0 >= 80 && LA82_0 <= 83)
							|| (LA82_0 >= 85 && LA82_0 <= 89)
							|| (LA82_0 >= 107 && LA82_0 <= 108) || (LA82_0 >= 111 && LA82_0 <= 115))) {
						alt82 = 1;
					}

					switch (alt82) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
						// blockStatement
					{
						pushFollow(FOLLOW_blockStatement_in_constructorBody2550);
						blockStatement249 = blockStatement();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, blockStatement249
									.getTree());

					}
						break;

					default:
						break loop82;
					}
				} while (true);

				char_literal250 = (Token) match(input, 47,
						FOLLOW_47_in_constructorBody2553);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal250_tree = (Object) adaptor
							.create(char_literal250);
					adaptor.addChild(root_0, char_literal250_tree);
				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 64, constructorBody_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "constructorBody"

	public static class explicitConstructorInvocation_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "explicitConstructorInvocation"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:516:1:
	// explicitConstructorInvocation : ( ( nonWildcardTypeArguments )? ( 'this'
	// | 'super' ) arguments ';' | primary '.' ( nonWildcardTypeArguments )?
	// 'super' arguments ';' );
	public final JavaParser.explicitConstructorInvocation_return explicitConstructorInvocation()
			throws RecognitionException {
		JavaParser.explicitConstructorInvocation_return retval = new JavaParser.explicitConstructorInvocation_return();
		retval.start = input.LT(1);
		int explicitConstructorInvocation_StartIndex = input.index();
		Object root_0 = null;

		Token set252 = null;
		Token char_literal254 = null;
		Token char_literal256 = null;
		Token string_literal258 = null;
		Token char_literal260 = null;
		JavaParser.nonWildcardTypeArguments_return nonWildcardTypeArguments251 = null;

		JavaParser.arguments_return arguments253 = null;

		JavaParser.primary_return primary255 = null;

		JavaParser.nonWildcardTypeArguments_return nonWildcardTypeArguments257 = null;

		JavaParser.arguments_return arguments259 = null;

		Object set252_tree = null;
		Object char_literal254_tree = null;
		Object char_literal256_tree = null;
		Object string_literal258_tree = null;
		Object char_literal260_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 65)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:517:5:
			// ( ( nonWildcardTypeArguments )? ( 'this' | 'super' ) arguments
			// ';' | primary '.' ( nonWildcardTypeArguments )? 'super' arguments
			// ';' )
			int alt85 = 2;
			alt85 = dfa85.predict(input);
			switch (alt85) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:517:9:
				// ( nonWildcardTypeArguments )? ( 'this' | 'super' ) arguments
				// ';'
			{
				root_0 = (Object) adaptor.nil();

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:517:9:
				// ( nonWildcardTypeArguments )?
				int alt83 = 2;
				int LA83_0 = input.LA(1);

				if ((LA83_0 == 42)) {
					alt83 = 1;
				}
				switch (alt83) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// nonWildcardTypeArguments
				{
					pushFollow(FOLLOW_nonWildcardTypeArguments_in_explicitConstructorInvocation2572);
					nonWildcardTypeArguments251 = nonWildcardTypeArguments();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, nonWildcardTypeArguments251
								.getTree());

				}
					break;

				}

				set252 = (Token) input.LT(1);
				if (input.LA(1) == 67 || input.LA(1) == 71) {
					input.consume();
					if (state.backtracking == 0)
						adaptor.addChild(root_0, (Object) adaptor
								.create(set252));
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

				pushFollow(FOLLOW_arguments_in_explicitConstructorInvocation2583);
				arguments253 = arguments();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, arguments253.getTree());
				char_literal254 = (Token) match(input, 28,
						FOLLOW_28_in_explicitConstructorInvocation2585);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal254_tree = (Object) adaptor
							.create(char_literal254);
					adaptor.addChild(root_0, char_literal254_tree);
				}

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:518:9:
				// primary '.' ( nonWildcardTypeArguments )? 'super' arguments
				// ';'
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_primary_in_explicitConstructorInvocation2595);
				primary255 = primary();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, primary255.getTree());
				char_literal256 = (Token) match(input, 31,
						FOLLOW_31_in_explicitConstructorInvocation2597);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal256_tree = (Object) adaptor
							.create(char_literal256);
					adaptor.addChild(root_0, char_literal256_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:518:21:
				// ( nonWildcardTypeArguments )?
				int alt84 = 2;
				int LA84_0 = input.LA(1);

				if ((LA84_0 == 42)) {
					alt84 = 1;
				}
				switch (alt84) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// nonWildcardTypeArguments
				{
					pushFollow(FOLLOW_nonWildcardTypeArguments_in_explicitConstructorInvocation2599);
					nonWildcardTypeArguments257 = nonWildcardTypeArguments();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, nonWildcardTypeArguments257
								.getTree());

				}
					break;

				}

				string_literal258 = (Token) match(input, 67,
						FOLLOW_67_in_explicitConstructorInvocation2602);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal258_tree = (Object) adaptor
							.create(string_literal258);
					adaptor.addChild(root_0, string_literal258_tree);
				}
				pushFollow(FOLLOW_arguments_in_explicitConstructorInvocation2604);
				arguments259 = arguments();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, arguments259.getTree());
				char_literal260 = (Token) match(input, 28,
						FOLLOW_28_in_explicitConstructorInvocation2606);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal260_tree = (Object) adaptor
							.create(char_literal260);
					adaptor.addChild(root_0, char_literal260_tree);
				}

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 65, explicitConstructorInvocation_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "explicitConstructorInvocation"

	public static class qualifiedName_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "qualifiedName"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:522:1:
	// qualifiedName : Identifier ( '.' Identifier )* ;
	public final JavaParser.qualifiedName_return qualifiedName()
			throws RecognitionException {
		JavaParser.qualifiedName_return retval = new JavaParser.qualifiedName_return();
		retval.start = input.LT(1);
		int qualifiedName_StartIndex = input.index();
		Object root_0 = null;

		Token Identifier261 = null;
		Token char_literal262 = null;
		Token Identifier263 = null;

		Object Identifier261_tree = null;
		Object char_literal262_tree = null;
		Object Identifier263_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 66)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:523:5:
			// ( Identifier ( '.' Identifier )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:523:9:
			// Identifier ( '.' Identifier )*
			{
				root_0 = (Object) adaptor.nil();

				Identifier261 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_qualifiedName2626);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier261_tree = (Object) adaptor.create(Identifier261);
					adaptor.addChild(root_0, Identifier261_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:523:20:
				// ( '.' Identifier )*
				loop86: do {
					int alt86 = 2;
					int LA86_0 = input.LA(1);

					if ((LA86_0 == 31)) {
						int LA86_2 = input.LA(2);

						if ((LA86_2 == Identifier)) {
							alt86 = 1;
						}

					}

					switch (alt86) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:523:21:
						// '.' Identifier
					{
						char_literal262 = (Token) match(input, 31,
								FOLLOW_31_in_qualifiedName2629);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal262_tree = (Object) adaptor
									.create(char_literal262);
							adaptor.addChild(root_0, char_literal262_tree);
						}
						Identifier263 = (Token) match(input, Identifier,
								FOLLOW_Identifier_in_qualifiedName2631);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							Identifier263_tree = (Object) adaptor
									.create(Identifier263);
							adaptor.addChild(root_0, Identifier263_tree);
						}

					}
						break;

					default:
						break loop86;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 66, qualifiedName_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "qualifiedName"

	public static class literal_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "literal"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:526:1:
	// literal : ( integerLiteral | FloatingPointLiteral | CharacterLiteral |
	// StringLiteral | booleanLiteral | 'null' );
	public final JavaParser.literal_return literal()
			throws RecognitionException {
		JavaParser.literal_return retval = new JavaParser.literal_return();
		retval.start = input.LT(1);
		int literal_StartIndex = input.index();
		Object root_0 = null;

		Token FloatingPointLiteral265 = null;
		Token CharacterLiteral266 = null;
		Token StringLiteral267 = null;
		Token string_literal269 = null;
		JavaParser.integerLiteral_return integerLiteral264 = null;

		JavaParser.booleanLiteral_return booleanLiteral268 = null;

		Object FloatingPointLiteral265_tree = null;
		Object CharacterLiteral266_tree = null;
		Object StringLiteral267_tree = null;
		Object string_literal269_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 67)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:527:5:
			// ( integerLiteral | FloatingPointLiteral | CharacterLiteral |
			// StringLiteral | booleanLiteral | 'null' )
			int alt87 = 6;
			switch (input.LA(1)) {
			case HexLiteral:
			case OctalLiteral:
			case DecimalLiteral: {
				alt87 = 1;
			}
				break;
			case FloatingPointLiteral: {
				alt87 = 2;
			}
				break;
			case CharacterLiteral: {
				alt87 = 3;
			}
				break;
			case StringLiteral: {
				alt87 = 4;
			}
				break;
			case 73:
			case 74: {
				alt87 = 5;
			}
				break;
			case 72: {
				alt87 = 6;
			}
				break;
			default:
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 87, 0,
						input);

				throw nvae;
			}

			switch (alt87) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:527:9:
				// integerLiteral
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_integerLiteral_in_literal2657);
				integerLiteral264 = integerLiteral();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, integerLiteral264.getTree());

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:528:9:
				// FloatingPointLiteral
			{
				root_0 = (Object) adaptor.nil();

				FloatingPointLiteral265 = (Token) match(input,
						FloatingPointLiteral,
						FOLLOW_FloatingPointLiteral_in_literal2667);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					FloatingPointLiteral265_tree = (Object) adaptor
							.create(FloatingPointLiteral265);
					adaptor.addChild(root_0, FloatingPointLiteral265_tree);
				}

			}
				break;
			case 3:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:529:9:
				// CharacterLiteral
			{
				root_0 = (Object) adaptor.nil();

				CharacterLiteral266 = (Token) match(input, CharacterLiteral,
						FOLLOW_CharacterLiteral_in_literal2677);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					CharacterLiteral266_tree = (Object) adaptor
							.create(CharacterLiteral266);
					adaptor.addChild(root_0, CharacterLiteral266_tree);
				}

			}
				break;
			case 4:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:530:9:
				// StringLiteral
			{
				root_0 = (Object) adaptor.nil();

				StringLiteral267 = (Token) match(input, StringLiteral,
						FOLLOW_StringLiteral_in_literal2687);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					StringLiteral267_tree = (Object) adaptor
							.create(StringLiteral267);
					adaptor.addChild(root_0, StringLiteral267_tree);
				}

			}
				break;
			case 5:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:531:9:
				// booleanLiteral
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_booleanLiteral_in_literal2697);
				booleanLiteral268 = booleanLiteral();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, booleanLiteral268.getTree());

			}
				break;
			case 6:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:532:9:
				// 'null'
			{
				root_0 = (Object) adaptor.nil();

				string_literal269 = (Token) match(input, 72,
						FOLLOW_72_in_literal2707);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal269_tree = (Object) adaptor
							.create(string_literal269);
					adaptor.addChild(root_0, string_literal269_tree);
				}

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 67, literal_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "literal"

	public static class integerLiteral_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "integerLiteral"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:535:1:
	// integerLiteral : ( HexLiteral | OctalLiteral | DecimalLiteral );
	public final JavaParser.integerLiteral_return integerLiteral()
			throws RecognitionException {
		JavaParser.integerLiteral_return retval = new JavaParser.integerLiteral_return();
		retval.start = input.LT(1);
		int integerLiteral_StartIndex = input.index();
		Object root_0 = null;

		Token set270 = null;

		Object set270_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 68)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:536:5:
			// ( HexLiteral | OctalLiteral | DecimalLiteral )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:
			{
				root_0 = (Object) adaptor.nil();

				set270 = (Token) input.LT(1);
				if ((input.LA(1) >= HexLiteral && input.LA(1) <= DecimalLiteral)) {
					input.consume();
					if (state.backtracking == 0)
						adaptor.addChild(root_0, (Object) adaptor
								.create(set270));
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

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 68, integerLiteral_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "integerLiteral"

	public static class booleanLiteral_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "booleanLiteral"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:541:1:
	// booleanLiteral : ( 'true' | 'false' );
	public final JavaParser.booleanLiteral_return booleanLiteral()
			throws RecognitionException {
		JavaParser.booleanLiteral_return retval = new JavaParser.booleanLiteral_return();
		retval.start = input.LT(1);
		int booleanLiteral_StartIndex = input.index();
		Object root_0 = null;

		Token set271 = null;

		Object set271_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 69)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:542:5:
			// ( 'true' | 'false' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:
			{
				root_0 = (Object) adaptor.nil();

				set271 = (Token) input.LT(1);
				if ((input.LA(1) >= 73 && input.LA(1) <= 74)) {
					input.consume();
					if (state.backtracking == 0)
						adaptor.addChild(root_0, (Object) adaptor
								.create(set271));
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

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 69, booleanLiteral_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "booleanLiteral"

	public static class annotations_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "annotations"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:548:1:
	// annotations : ( annotation )+ ;
	public final JavaParser.annotations_return annotations()
			throws RecognitionException {
		JavaParser.annotations_return retval = new JavaParser.annotations_return();
		retval.start = input.LT(1);
		int annotations_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.annotation_return annotation272 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 70)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:549:5:
			// ( ( annotation )+ )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:549:9:
			// ( annotation )+
			{
				root_0 = (Object) adaptor.nil();

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:549:9:
				// ( annotation )+
				int cnt88 = 0;
				loop88: do {
					int alt88 = 2;
					int LA88_0 = input.LA(1);

					if ((LA88_0 == 75)) {
						int LA88_2 = input.LA(2);

						if ((LA88_2 == Identifier)) {
							int LA88_3 = input.LA(3);

							if ((synpred128_Java())) {
								alt88 = 1;
							}

						}

					}

					switch (alt88) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
						// annotation
					{
						pushFollow(FOLLOW_annotation_in_annotations2796);
						annotation272 = annotation();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, annotation272.getTree());

					}
						break;

					default:
						if (cnt88 >= 1)
							break loop88;
						if (state.backtracking > 0) {
							state.failed = true;
							return retval;
						}
						EarlyExitException eee = new EarlyExitException(88,
								input);
						throw eee;
					}
					cnt88++;
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 70, annotations_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "annotations"

	public static class annotation_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "annotation"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:552:1:
	// annotation : '@' annotationName ( '(' ( elementValuePairs | elementValue
	// )? ')' )? ;
	public final JavaParser.annotation_return annotation()
			throws RecognitionException {
		JavaParser.annotation_return retval = new JavaParser.annotation_return();
		retval.start = input.LT(1);
		int annotation_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal273 = null;
		Token char_literal275 = null;
		Token char_literal278 = null;
		JavaParser.annotationName_return annotationName274 = null;

		JavaParser.elementValuePairs_return elementValuePairs276 = null;

		JavaParser.elementValue_return elementValue277 = null;

		Object char_literal273_tree = null;
		Object char_literal275_tree = null;
		Object char_literal278_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 71)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:553:5:
			// ( '@' annotationName ( '(' ( elementValuePairs | elementValue )?
			// ')' )? )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:553:9:
			// '@' annotationName ( '(' ( elementValuePairs | elementValue )?
			// ')' )?
			{
				root_0 = (Object) adaptor.nil();

				char_literal273 = (Token) match(input, 75,
						FOLLOW_75_in_annotation2816);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal273_tree = (Object) adaptor
							.create(char_literal273);
					adaptor.addChild(root_0, char_literal273_tree);
				}
				pushFollow(FOLLOW_annotationName_in_annotation2818);
				annotationName274 = annotationName();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, annotationName274.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:553:28:
				// ( '(' ( elementValuePairs | elementValue )? ')' )?
				int alt90 = 2;
				int LA90_0 = input.LA(1);

				if ((LA90_0 == 68)) {
					alt90 = 1;
				}
				switch (alt90) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:553:30:
					// '(' ( elementValuePairs | elementValue )? ')'
				{
					char_literal275 = (Token) match(input, 68,
							FOLLOW_68_in_annotation2822);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						char_literal275_tree = (Object) adaptor
								.create(char_literal275);
						adaptor.addChild(root_0, char_literal275_tree);
					}
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:553:34:
					// ( elementValuePairs | elementValue )?
					int alt89 = 3;
					int LA89_0 = input.LA(1);

					if ((LA89_0 == Identifier)) {
						int LA89_1 = input.LA(2);

						if ((LA89_1 == 53)) {
							alt89 = 1;
						} else if (((LA89_1 >= 31 && LA89_1 <= 32)
								|| LA89_1 == 42
								|| (LA89_1 >= 44 && LA89_1 <= 45)
								|| LA89_1 == 50 || LA89_1 == 66
								|| (LA89_1 >= 68 && LA89_1 <= 69) || (LA89_1 >= 100 && LA89_1 <= 112))) {
							alt89 = 2;
						}
					} else if (((LA89_0 >= FloatingPointLiteral && LA89_0 <= DecimalLiteral)
							|| LA89_0 == 46
							|| LA89_0 == 49
							|| (LA89_0 >= 58 && LA89_0 <= 65)
							|| (LA89_0 >= 67 && LA89_0 <= 68)
							|| (LA89_0 >= 71 && LA89_0 <= 75)
							|| (LA89_0 >= 107 && LA89_0 <= 108) || (LA89_0 >= 111 && LA89_0 <= 115))) {
						alt89 = 2;
					}
					switch (alt89) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:553:36:
						// elementValuePairs
					{
						pushFollow(FOLLOW_elementValuePairs_in_annotation2826);
						elementValuePairs276 = elementValuePairs();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, elementValuePairs276
									.getTree());

					}
						break;
					case 2:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:553:56:
						// elementValue
					{
						pushFollow(FOLLOW_elementValue_in_annotation2830);
						elementValue277 = elementValue();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, elementValue277.getTree());

					}
						break;

					}

					char_literal278 = (Token) match(input, 69,
							FOLLOW_69_in_annotation2835);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						char_literal278_tree = (Object) adaptor
								.create(char_literal278);
						adaptor.addChild(root_0, char_literal278_tree);
					}

				}
					break;

				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 71, annotation_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "annotation"

	public static class annotationName_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "annotationName"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:556:1:
	// annotationName : Identifier ( '.' Identifier )* ;
	public final JavaParser.annotationName_return annotationName()
			throws RecognitionException {
		JavaParser.annotationName_return retval = new JavaParser.annotationName_return();
		retval.start = input.LT(1);
		int annotationName_StartIndex = input.index();
		Object root_0 = null;

		Token Identifier279 = null;
		Token char_literal280 = null;
		Token Identifier281 = null;

		Object Identifier279_tree = null;
		Object char_literal280_tree = null;
		Object Identifier281_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 72)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:557:5:
			// ( Identifier ( '.' Identifier )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:557:7:
			// Identifier ( '.' Identifier )*
			{
				root_0 = (Object) adaptor.nil();

				Identifier279 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_annotationName2859);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier279_tree = (Object) adaptor.create(Identifier279);
					adaptor.addChild(root_0, Identifier279_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:557:18:
				// ( '.' Identifier )*
				loop91: do {
					int alt91 = 2;
					int LA91_0 = input.LA(1);

					if ((LA91_0 == 31)) {
						alt91 = 1;
					}

					switch (alt91) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:557:19:
						// '.' Identifier
					{
						char_literal280 = (Token) match(input, 31,
								FOLLOW_31_in_annotationName2862);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal280_tree = (Object) adaptor
									.create(char_literal280);
							adaptor.addChild(root_0, char_literal280_tree);
						}
						Identifier281 = (Token) match(input, Identifier,
								FOLLOW_Identifier_in_annotationName2864);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							Identifier281_tree = (Object) adaptor
									.create(Identifier281);
							adaptor.addChild(root_0, Identifier281_tree);
						}

					}
						break;

					default:
						break loop91;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 72, annotationName_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "annotationName"

	public static class elementValuePairs_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "elementValuePairs"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:560:1:
	// elementValuePairs : elementValuePair ( ',' elementValuePair )* ;
	public final JavaParser.elementValuePairs_return elementValuePairs()
			throws RecognitionException {
		JavaParser.elementValuePairs_return retval = new JavaParser.elementValuePairs_return();
		retval.start = input.LT(1);
		int elementValuePairs_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal283 = null;
		JavaParser.elementValuePair_return elementValuePair282 = null;

		JavaParser.elementValuePair_return elementValuePair284 = null;

		Object char_literal283_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 73)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:561:5:
			// ( elementValuePair ( ',' elementValuePair )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:561:9:
			// elementValuePair ( ',' elementValuePair )*
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_elementValuePair_in_elementValuePairs2885);
				elementValuePair282 = elementValuePair();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, elementValuePair282.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:561:26:
				// ( ',' elementValuePair )*
				loop92: do {
					int alt92 = 2;
					int LA92_0 = input.LA(1);

					if ((LA92_0 == 43)) {
						alt92 = 1;
					}

					switch (alt92) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:561:27:
						// ',' elementValuePair
					{
						char_literal283 = (Token) match(input, 43,
								FOLLOW_43_in_elementValuePairs2888);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal283_tree = (Object) adaptor
									.create(char_literal283);
							adaptor.addChild(root_0, char_literal283_tree);
						}
						pushFollow(FOLLOW_elementValuePair_in_elementValuePairs2890);
						elementValuePair284 = elementValuePair();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, elementValuePair284
									.getTree());

					}
						break;

					default:
						break loop92;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 73, elementValuePairs_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "elementValuePairs"

	public static class elementValuePair_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "elementValuePair"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:564:1:
	// elementValuePair : Identifier '=' elementValue ;
	public final JavaParser.elementValuePair_return elementValuePair()
			throws RecognitionException {
		JavaParser.elementValuePair_return retval = new JavaParser.elementValuePair_return();
		retval.start = input.LT(1);
		int elementValuePair_StartIndex = input.index();
		Object root_0 = null;

		Token Identifier285 = null;
		Token char_literal286 = null;
		JavaParser.elementValue_return elementValue287 = null;

		Object Identifier285_tree = null;
		Object char_literal286_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 74)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:565:5:
			// ( Identifier '=' elementValue )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:565:9:
			// Identifier '=' elementValue
			{
				root_0 = (Object) adaptor.nil();

				Identifier285 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_elementValuePair2911);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier285_tree = (Object) adaptor.create(Identifier285);
					adaptor.addChild(root_0, Identifier285_tree);
				}
				char_literal286 = (Token) match(input, 53,
						FOLLOW_53_in_elementValuePair2913);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal286_tree = (Object) adaptor
							.create(char_literal286);
					adaptor.addChild(root_0, char_literal286_tree);
				}
				pushFollow(FOLLOW_elementValue_in_elementValuePair2915);
				elementValue287 = elementValue();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, elementValue287.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 74, elementValuePair_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "elementValuePair"

	public static class elementValue_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "elementValue"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:568:1:
	// elementValue : ( conditionalExpression | annotation |
	// elementValueArrayInitializer );
	public final JavaParser.elementValue_return elementValue()
			throws RecognitionException {
		JavaParser.elementValue_return retval = new JavaParser.elementValue_return();
		retval.start = input.LT(1);
		int elementValue_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.conditionalExpression_return conditionalExpression288 = null;

		JavaParser.annotation_return annotation289 = null;

		JavaParser.elementValueArrayInitializer_return elementValueArrayInitializer290 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 75)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:569:5:
			// ( conditionalExpression | annotation |
			// elementValueArrayInitializer )
			int alt93 = 3;
			switch (input.LA(1)) {
			case Identifier:
			case FloatingPointLiteral:
			case CharacterLiteral:
			case StringLiteral:
			case HexLiteral:
			case OctalLiteral:
			case DecimalLiteral:
			case 49:
			case 58:
			case 59:
			case 60:
			case 61:
			case 62:
			case 63:
			case 64:
			case 65:
			case 67:
			case 68:
			case 71:
			case 72:
			case 73:
			case 74:
			case 107:
			case 108:
			case 111:
			case 112:
			case 113:
			case 114:
			case 115: {
				alt93 = 1;
			}
				break;
			case 75: {
				alt93 = 2;
			}
				break;
			case 46: {
				alt93 = 3;
			}
				break;
			default:
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 93, 0,
						input);

				throw nvae;
			}

			switch (alt93) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:569:9:
				// conditionalExpression
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_conditionalExpression_in_elementValue2938);
				conditionalExpression288 = conditionalExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor
							.addChild(root_0, conditionalExpression288
									.getTree());

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:570:9:
				// annotation
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_annotation_in_elementValue2948);
				annotation289 = annotation();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, annotation289.getTree());

			}
				break;
			case 3:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:571:9:
				// elementValueArrayInitializer
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_elementValueArrayInitializer_in_elementValue2958);
				elementValueArrayInitializer290 = elementValueArrayInitializer();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, elementValueArrayInitializer290
							.getTree());

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 75, elementValue_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "elementValue"

	public static class elementValueArrayInitializer_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "elementValueArrayInitializer"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:574:1:
	// elementValueArrayInitializer : '{' ( elementValue ( ',' elementValue )*
	// )? ( ',' )? '}' ;
	public final JavaParser.elementValueArrayInitializer_return elementValueArrayInitializer()
			throws RecognitionException {
		JavaParser.elementValueArrayInitializer_return retval = new JavaParser.elementValueArrayInitializer_return();
		retval.start = input.LT(1);
		int elementValueArrayInitializer_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal291 = null;
		Token char_literal293 = null;
		Token char_literal295 = null;
		Token char_literal296 = null;
		JavaParser.elementValue_return elementValue292 = null;

		JavaParser.elementValue_return elementValue294 = null;

		Object char_literal291_tree = null;
		Object char_literal293_tree = null;
		Object char_literal295_tree = null;
		Object char_literal296_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 76)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:575:5:
			// ( '{' ( elementValue ( ',' elementValue )* )? ( ',' )? '}' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:575:9:
			// '{' ( elementValue ( ',' elementValue )* )? ( ',' )? '}'
			{
				root_0 = (Object) adaptor.nil();

				char_literal291 = (Token) match(input, 46,
						FOLLOW_46_in_elementValueArrayInitializer2981);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal291_tree = (Object) adaptor
							.create(char_literal291);
					adaptor.addChild(root_0, char_literal291_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:575:13:
				// ( elementValue ( ',' elementValue )* )?
				int alt95 = 2;
				int LA95_0 = input.LA(1);

				if ((LA95_0 == Identifier
						|| (LA95_0 >= FloatingPointLiteral && LA95_0 <= DecimalLiteral)
						|| LA95_0 == 46 || LA95_0 == 49
						|| (LA95_0 >= 58 && LA95_0 <= 65)
						|| (LA95_0 >= 67 && LA95_0 <= 68)
						|| (LA95_0 >= 71 && LA95_0 <= 75)
						|| (LA95_0 >= 107 && LA95_0 <= 108) || (LA95_0 >= 111 && LA95_0 <= 115))) {
					alt95 = 1;
				}
				switch (alt95) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:575:14:
					// elementValue ( ',' elementValue )*
				{
					pushFollow(FOLLOW_elementValue_in_elementValueArrayInitializer2984);
					elementValue292 = elementValue();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, elementValue292.getTree());
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:575:27:
					// ( ',' elementValue )*
					loop94: do {
						int alt94 = 2;
						int LA94_0 = input.LA(1);

						if ((LA94_0 == 43)) {
							int LA94_1 = input.LA(2);

							if ((LA94_1 == Identifier
									|| (LA94_1 >= FloatingPointLiteral && LA94_1 <= DecimalLiteral)
									|| LA94_1 == 46 || LA94_1 == 49
									|| (LA94_1 >= 58 && LA94_1 <= 65)
									|| (LA94_1 >= 67 && LA94_1 <= 68)
									|| (LA94_1 >= 71 && LA94_1 <= 75)
									|| (LA94_1 >= 107 && LA94_1 <= 108) || (LA94_1 >= 111 && LA94_1 <= 115))) {
								alt94 = 1;
							}

						}

						switch (alt94) {
						case 1:
							// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:575:28:
							// ',' elementValue
						{
							char_literal293 = (Token) match(input, 43,
									FOLLOW_43_in_elementValueArrayInitializer2987);
							if (state.failed)
								return retval;
							if (state.backtracking == 0) {
								char_literal293_tree = (Object) adaptor
										.create(char_literal293);
								adaptor.addChild(root_0, char_literal293_tree);
							}
							pushFollow(FOLLOW_elementValue_in_elementValueArrayInitializer2989);
							elementValue294 = elementValue();

							state._fsp--;
							if (state.failed)
								return retval;
							if (state.backtracking == 0)
								adaptor.addChild(root_0, elementValue294
										.getTree());

						}
							break;

						default:
							break loop94;
						}
					} while (true);

				}
					break;

				}

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:575:49:
				// ( ',' )?
				int alt96 = 2;
				int LA96_0 = input.LA(1);

				if ((LA96_0 == 43)) {
					alt96 = 1;
				}
				switch (alt96) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:575:50:
					// ','
				{
					char_literal295 = (Token) match(input, 43,
							FOLLOW_43_in_elementValueArrayInitializer2996);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						char_literal295_tree = (Object) adaptor
								.create(char_literal295);
						adaptor.addChild(root_0, char_literal295_tree);
					}

				}
					break;

				}

				char_literal296 = (Token) match(input, 47,
						FOLLOW_47_in_elementValueArrayInitializer3000);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal296_tree = (Object) adaptor
							.create(char_literal296);
					adaptor.addChild(root_0, char_literal296_tree);
				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 76, elementValueArrayInitializer_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "elementValueArrayInitializer"

	public static class annotationTypeDeclaration_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "annotationTypeDeclaration"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:578:1:
	// annotationTypeDeclaration : '@' 'interface' Identifier annotationTypeBody
	// ;
	public final JavaParser.annotationTypeDeclaration_return annotationTypeDeclaration()
			throws RecognitionException {
		JavaParser.annotationTypeDeclaration_return retval = new JavaParser.annotationTypeDeclaration_return();
		retval.start = input.LT(1);
		int annotationTypeDeclaration_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal297 = null;
		Token string_literal298 = null;
		Token Identifier299 = null;
		JavaParser.annotationTypeBody_return annotationTypeBody300 = null;

		Object char_literal297_tree = null;
		Object string_literal298_tree = null;
		Object Identifier299_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 77)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:579:5:
			// ( '@' 'interface' Identifier annotationTypeBody )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:579:9:
			// '@' 'interface' Identifier annotationTypeBody
			{
				root_0 = (Object) adaptor.nil();

				char_literal297 = (Token) match(input, 75,
						FOLLOW_75_in_annotationTypeDeclaration3023);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal297_tree = (Object) adaptor
							.create(char_literal297);
					adaptor.addChild(root_0, char_literal297_tree);
				}
				string_literal298 = (Token) match(input, 48,
						FOLLOW_48_in_annotationTypeDeclaration3025);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal298_tree = (Object) adaptor
							.create(string_literal298);
					adaptor.addChild(root_0, string_literal298_tree);
				}
				Identifier299 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_annotationTypeDeclaration3027);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier299_tree = (Object) adaptor.create(Identifier299);
					adaptor.addChild(root_0, Identifier299_tree);
				}
				pushFollow(FOLLOW_annotationTypeBody_in_annotationTypeDeclaration3029);
				annotationTypeBody300 = annotationTypeBody();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, annotationTypeBody300.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 77, annotationTypeDeclaration_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "annotationTypeDeclaration"

	public static class annotationTypeBody_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "annotationTypeBody"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:582:1:
	// annotationTypeBody : '{' ( annotationTypeElementDeclaration )* '}' ;
	public final JavaParser.annotationTypeBody_return annotationTypeBody()
			throws RecognitionException {
		JavaParser.annotationTypeBody_return retval = new JavaParser.annotationTypeBody_return();
		retval.start = input.LT(1);
		int annotationTypeBody_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal301 = null;
		Token char_literal303 = null;
		JavaParser.annotationTypeElementDeclaration_return annotationTypeElementDeclaration302 = null;

		Object char_literal301_tree = null;
		Object char_literal303_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 78)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:583:5:
			// ( '{' ( annotationTypeElementDeclaration )* '}' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:583:9:
			// '{' ( annotationTypeElementDeclaration )* '}'
			{
				root_0 = (Object) adaptor.nil();

				char_literal301 = (Token) match(input, 46,
						FOLLOW_46_in_annotationTypeBody3052);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal301_tree = (Object) adaptor
							.create(char_literal301);
					adaptor.addChild(root_0, char_literal301_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:583:13:
				// ( annotationTypeElementDeclaration )*
				loop97: do {
					int alt97 = 2;
					int LA97_0 = input.LA(1);

					if (((LA97_0 >= Identifier && LA97_0 <= ENUM)
							|| LA97_0 == 30 || (LA97_0 >= 33 && LA97_0 <= 39)
							|| LA97_0 == 42 || (LA97_0 >= 48 && LA97_0 <= 49)
							|| (LA97_0 >= 54 && LA97_0 <= 65) || LA97_0 == 75)) {
						alt97 = 1;
					}

					switch (alt97) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:583:14:
						// annotationTypeElementDeclaration
					{
						pushFollow(FOLLOW_annotationTypeElementDeclaration_in_annotationTypeBody3055);
						annotationTypeElementDeclaration302 = annotationTypeElementDeclaration();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0,
									annotationTypeElementDeclaration302
											.getTree());

					}
						break;

					default:
						break loop97;
					}
				} while (true);

				char_literal303 = (Token) match(input, 47,
						FOLLOW_47_in_annotationTypeBody3059);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal303_tree = (Object) adaptor
							.create(char_literal303);
					adaptor.addChild(root_0, char_literal303_tree);
				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 78, annotationTypeBody_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "annotationTypeBody"

	public static class annotationTypeElementDeclaration_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "annotationTypeElementDeclaration"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:586:1:
	// annotationTypeElementDeclaration : modifiers annotationTypeElementRest ;
	public final JavaParser.annotationTypeElementDeclaration_return annotationTypeElementDeclaration()
			throws RecognitionException {
		JavaParser.annotationTypeElementDeclaration_return retval = new JavaParser.annotationTypeElementDeclaration_return();
		retval.start = input.LT(1);
		int annotationTypeElementDeclaration_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.modifiers_return modifiers304 = null;

		JavaParser.annotationTypeElementRest_return annotationTypeElementRest305 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 79)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:587:5:
			// ( modifiers annotationTypeElementRest )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:587:9:
			// modifiers annotationTypeElementRest
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_modifiers_in_annotationTypeElementDeclaration3082);
				modifiers304 = modifiers();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, modifiers304.getTree());
				pushFollow(FOLLOW_annotationTypeElementRest_in_annotationTypeElementDeclaration3084);
				annotationTypeElementRest305 = annotationTypeElementRest();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, annotationTypeElementRest305
							.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 79, annotationTypeElementDeclaration_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "annotationTypeElementDeclaration"

	public static class annotationTypeElementRest_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "annotationTypeElementRest"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:590:1:
	// annotationTypeElementRest : ( type annotationMethodOrConstantRest ';' |
	// normalClassDeclaration ( ';' )? | normalInterfaceDeclaration ( ';' )? |
	// enumDeclaration ( ';' )? | annotationTypeDeclaration ( ';' )? );
	public final JavaParser.annotationTypeElementRest_return annotationTypeElementRest()
			throws RecognitionException {
		JavaParser.annotationTypeElementRest_return retval = new JavaParser.annotationTypeElementRest_return();
		retval.start = input.LT(1);
		int annotationTypeElementRest_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal308 = null;
		Token char_literal310 = null;
		Token char_literal312 = null;
		Token char_literal314 = null;
		Token char_literal316 = null;
		JavaParser.type_return type306 = null;

		JavaParser.annotationMethodOrConstantRest_return annotationMethodOrConstantRest307 = null;

		JavaParser.normalClassDeclaration_return normalClassDeclaration309 = null;

		JavaParser.normalInterfaceDeclaration_return normalInterfaceDeclaration311 = null;

		JavaParser.enumDeclaration_return enumDeclaration313 = null;

		JavaParser.annotationTypeDeclaration_return annotationTypeDeclaration315 = null;

		Object char_literal308_tree = null;
		Object char_literal310_tree = null;
		Object char_literal312_tree = null;
		Object char_literal314_tree = null;
		Object char_literal316_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 80)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:591:5:
			// ( type annotationMethodOrConstantRest ';' |
			// normalClassDeclaration ( ';' )? | normalInterfaceDeclaration (
			// ';' )? | enumDeclaration ( ';' )? | annotationTypeDeclaration (
			// ';' )? )
			int alt102 = 5;
			switch (input.LA(1)) {
			case Identifier:
			case 58:
			case 59:
			case 60:
			case 61:
			case 62:
			case 63:
			case 64:
			case 65: {
				alt102 = 1;
			}
				break;
			case 39: {
				alt102 = 2;
			}
				break;
			case 48: {
				alt102 = 3;
			}
				break;
			case ENUM: {
				alt102 = 4;
			}
				break;
			case 75: {
				alt102 = 5;
			}
				break;
			default:
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 102,
						0, input);

				throw nvae;
			}

			switch (alt102) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:591:9:
				// type annotationMethodOrConstantRest ';'
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_type_in_annotationTypeElementRest3107);
				type306 = type();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, type306.getTree());
				pushFollow(FOLLOW_annotationMethodOrConstantRest_in_annotationTypeElementRest3109);
				annotationMethodOrConstantRest307 = annotationMethodOrConstantRest();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, annotationMethodOrConstantRest307
							.getTree());
				char_literal308 = (Token) match(input, 28,
						FOLLOW_28_in_annotationTypeElementRest3111);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal308_tree = (Object) adaptor
							.create(char_literal308);
					adaptor.addChild(root_0, char_literal308_tree);
				}

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:592:9:
				// normalClassDeclaration ( ';' )?
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_normalClassDeclaration_in_annotationTypeElementRest3121);
				normalClassDeclaration309 = normalClassDeclaration();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, normalClassDeclaration309
							.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:592:32:
				// ( ';' )?
				int alt98 = 2;
				int LA98_0 = input.LA(1);

				if ((LA98_0 == 28)) {
					alt98 = 1;
				}
				switch (alt98) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// ';'
				{
					char_literal310 = (Token) match(input, 28,
							FOLLOW_28_in_annotationTypeElementRest3123);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						char_literal310_tree = (Object) adaptor
								.create(char_literal310);
						adaptor.addChild(root_0, char_literal310_tree);
					}

				}
					break;

				}

			}
				break;
			case 3:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:593:9:
				// normalInterfaceDeclaration ( ';' )?
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_normalInterfaceDeclaration_in_annotationTypeElementRest3134);
				normalInterfaceDeclaration311 = normalInterfaceDeclaration();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, normalInterfaceDeclaration311
							.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:593:36:
				// ( ';' )?
				int alt99 = 2;
				int LA99_0 = input.LA(1);

				if ((LA99_0 == 28)) {
					alt99 = 1;
				}
				switch (alt99) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// ';'
				{
					char_literal312 = (Token) match(input, 28,
							FOLLOW_28_in_annotationTypeElementRest3136);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						char_literal312_tree = (Object) adaptor
								.create(char_literal312);
						adaptor.addChild(root_0, char_literal312_tree);
					}

				}
					break;

				}

			}
				break;
			case 4:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:594:9:
				// enumDeclaration ( ';' )?
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_enumDeclaration_in_annotationTypeElementRest3147);
				enumDeclaration313 = enumDeclaration();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, enumDeclaration313.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:594:25:
				// ( ';' )?
				int alt100 = 2;
				int LA100_0 = input.LA(1);

				if ((LA100_0 == 28)) {
					alt100 = 1;
				}
				switch (alt100) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// ';'
				{
					char_literal314 = (Token) match(input, 28,
							FOLLOW_28_in_annotationTypeElementRest3149);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						char_literal314_tree = (Object) adaptor
								.create(char_literal314);
						adaptor.addChild(root_0, char_literal314_tree);
					}

				}
					break;

				}

			}
				break;
			case 5:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:595:9:
				// annotationTypeDeclaration ( ';' )?
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_annotationTypeDeclaration_in_annotationTypeElementRest3160);
				annotationTypeDeclaration315 = annotationTypeDeclaration();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, annotationTypeDeclaration315
							.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:595:35:
				// ( ';' )?
				int alt101 = 2;
				int LA101_0 = input.LA(1);

				if ((LA101_0 == 28)) {
					alt101 = 1;
				}
				switch (alt101) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// ';'
				{
					char_literal316 = (Token) match(input, 28,
							FOLLOW_28_in_annotationTypeElementRest3162);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						char_literal316_tree = (Object) adaptor
								.create(char_literal316);
						adaptor.addChild(root_0, char_literal316_tree);
					}

				}
					break;

				}

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 80, annotationTypeElementRest_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "annotationTypeElementRest"

	public static class annotationMethodOrConstantRest_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "annotationMethodOrConstantRest"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:598:1:
	// annotationMethodOrConstantRest : ( annotationMethodRest |
	// annotationConstantRest );
	public final JavaParser.annotationMethodOrConstantRest_return annotationMethodOrConstantRest()
			throws RecognitionException {
		JavaParser.annotationMethodOrConstantRest_return retval = new JavaParser.annotationMethodOrConstantRest_return();
		retval.start = input.LT(1);
		int annotationMethodOrConstantRest_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.annotationMethodRest_return annotationMethodRest317 = null;

		JavaParser.annotationConstantRest_return annotationConstantRest318 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 81)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:599:5:
			// ( annotationMethodRest | annotationConstantRest )
			int alt103 = 2;
			int LA103_0 = input.LA(1);

			if ((LA103_0 == Identifier)) {
				int LA103_1 = input.LA(2);

				if ((LA103_1 == 68)) {
					alt103 = 1;
				} else if ((LA103_1 == 28 || LA103_1 == 43 || LA103_1 == 50 || LA103_1 == 53)) {
					alt103 = 2;
				} else {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					NoViableAltException nvae = new NoViableAltException("",
							103, 1, input);

					throw nvae;
				}
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 103,
						0, input);

				throw nvae;
			}
			switch (alt103) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:599:9:
				// annotationMethodRest
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_annotationMethodRest_in_annotationMethodOrConstantRest3186);
				annotationMethodRest317 = annotationMethodRest();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, annotationMethodRest317.getTree());

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:600:9:
				// annotationConstantRest
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_annotationConstantRest_in_annotationMethodOrConstantRest3196);
				annotationConstantRest318 = annotationConstantRest();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, annotationConstantRest318
							.getTree());

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 81, annotationMethodOrConstantRest_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "annotationMethodOrConstantRest"

	public static class annotationMethodRest_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "annotationMethodRest"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:603:1:
	// annotationMethodRest : Identifier '(' ')' ( defaultValue )? ;
	public final JavaParser.annotationMethodRest_return annotationMethodRest()
			throws RecognitionException {
		JavaParser.annotationMethodRest_return retval = new JavaParser.annotationMethodRest_return();
		retval.start = input.LT(1);
		int annotationMethodRest_StartIndex = input.index();
		Object root_0 = null;

		Token Identifier319 = null;
		Token char_literal320 = null;
		Token char_literal321 = null;
		JavaParser.defaultValue_return defaultValue322 = null;

		Object Identifier319_tree = null;
		Object char_literal320_tree = null;
		Object char_literal321_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 82)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:604:5:
			// ( Identifier '(' ')' ( defaultValue )? )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:604:9:
			// Identifier '(' ')' ( defaultValue )?
			{
				root_0 = (Object) adaptor.nil();

				Identifier319 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_annotationMethodRest3219);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier319_tree = (Object) adaptor.create(Identifier319);
					adaptor.addChild(root_0, Identifier319_tree);
				}
				char_literal320 = (Token) match(input, 68,
						FOLLOW_68_in_annotationMethodRest3221);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal320_tree = (Object) adaptor
							.create(char_literal320);
					adaptor.addChild(root_0, char_literal320_tree);
				}
				char_literal321 = (Token) match(input, 69,
						FOLLOW_69_in_annotationMethodRest3223);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal321_tree = (Object) adaptor
							.create(char_literal321);
					adaptor.addChild(root_0, char_literal321_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:604:28:
				// ( defaultValue )?
				int alt104 = 2;
				int LA104_0 = input.LA(1);

				if ((LA104_0 == 76)) {
					alt104 = 1;
				}
				switch (alt104) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// defaultValue
				{
					pushFollow(FOLLOW_defaultValue_in_annotationMethodRest3225);
					defaultValue322 = defaultValue();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, defaultValue322.getTree());

				}
					break;

				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 82, annotationMethodRest_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "annotationMethodRest"

	public static class annotationConstantRest_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "annotationConstantRest"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:607:1:
	// annotationConstantRest : variableDeclarators ;
	public final JavaParser.annotationConstantRest_return annotationConstantRest()
			throws RecognitionException {
		JavaParser.annotationConstantRest_return retval = new JavaParser.annotationConstantRest_return();
		retval.start = input.LT(1);
		int annotationConstantRest_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.variableDeclarators_return variableDeclarators323 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 83)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:608:5:
			// ( variableDeclarators )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:608:9:
			// variableDeclarators
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_variableDeclarators_in_annotationConstantRest3249);
				variableDeclarators323 = variableDeclarators();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, variableDeclarators323.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 83, annotationConstantRest_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "annotationConstantRest"

	public static class defaultValue_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "defaultValue"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:611:1:
	// defaultValue : 'default' elementValue ;
	public final JavaParser.defaultValue_return defaultValue()
			throws RecognitionException {
		JavaParser.defaultValue_return retval = new JavaParser.defaultValue_return();
		retval.start = input.LT(1);
		int defaultValue_StartIndex = input.index();
		Object root_0 = null;

		Token string_literal324 = null;
		JavaParser.elementValue_return elementValue325 = null;

		Object string_literal324_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 84)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:612:5:
			// ( 'default' elementValue )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:612:9:
			// 'default' elementValue
			{
				root_0 = (Object) adaptor.nil();

				string_literal324 = (Token) match(input, 76,
						FOLLOW_76_in_defaultValue3272);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal324_tree = (Object) adaptor
							.create(string_literal324);
					adaptor.addChild(root_0, string_literal324_tree);
				}
				pushFollow(FOLLOW_elementValue_in_defaultValue3274);
				elementValue325 = elementValue();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, elementValue325.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 84, defaultValue_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "defaultValue"

	public static class block_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "block"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:617:1:
	// block : '{' ( blockStatement )* '}' ;
	public final JavaParser.block_return block() throws RecognitionException {
		JavaParser.block_return retval = new JavaParser.block_return();
		retval.start = input.LT(1);
		int block_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal326 = null;
		Token char_literal328 = null;
		JavaParser.blockStatement_return blockStatement327 = null;

		Object char_literal326_tree = null;
		Object char_literal328_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 85)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:618:5:
			// ( '{' ( blockStatement )* '}' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:618:9:
			// '{' ( blockStatement )* '}'
			{
				root_0 = (Object) adaptor.nil();

				char_literal326 = (Token) match(input, 46,
						FOLLOW_46_in_block3295);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal326_tree = (Object) adaptor
							.create(char_literal326);
					adaptor.addChild(root_0, char_literal326_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:618:13:
				// ( blockStatement )*
				loop105: do {
					int alt105 = 2;
					int LA105_0 = input.LA(1);

					if (((LA105_0 >= Identifier && LA105_0 <= ASSERT)
							|| LA105_0 == 28 || LA105_0 == 30
							|| (LA105_0 >= 33 && LA105_0 <= 39)
							|| LA105_0 == 46
							|| (LA105_0 >= 48 && LA105_0 <= 49)
							|| LA105_0 == 55
							|| (LA105_0 >= 58 && LA105_0 <= 65)
							|| (LA105_0 >= 67 && LA105_0 <= 68)
							|| (LA105_0 >= 71 && LA105_0 <= 75)
							|| LA105_0 == 78
							|| (LA105_0 >= 80 && LA105_0 <= 83)
							|| (LA105_0 >= 85 && LA105_0 <= 89)
							|| (LA105_0 >= 107 && LA105_0 <= 108) || (LA105_0 >= 111 && LA105_0 <= 115))) {
						alt105 = 1;
					}

					switch (alt105) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
						// blockStatement
					{
						pushFollow(FOLLOW_blockStatement_in_block3297);
						blockStatement327 = blockStatement();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, blockStatement327
									.getTree());

					}
						break;

					default:
						break loop105;
					}
				} while (true);

				char_literal328 = (Token) match(input, 47,
						FOLLOW_47_in_block3300);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal328_tree = (Object) adaptor
							.create(char_literal328);
					adaptor.addChild(root_0, char_literal328_tree);
				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 85, block_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "block"

	public static class blockStatement_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "blockStatement"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:621:1:
	// blockStatement : ( localVariableDeclarationStatement |
	// classOrInterfaceDeclaration | statement );
	public final JavaParser.blockStatement_return blockStatement()
			throws RecognitionException {
		JavaParser.blockStatement_return retval = new JavaParser.blockStatement_return();
		retval.start = input.LT(1);
		int blockStatement_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.localVariableDeclarationStatement_return localVariableDeclarationStatement329 = null;

		JavaParser.classOrInterfaceDeclaration_return classOrInterfaceDeclaration330 = null;

		JavaParser.statement_return statement331 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 86)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:622:5:
			// ( localVariableDeclarationStatement | classOrInterfaceDeclaration
			// | statement )
			int alt106 = 3;
			alt106 = dfa106.predict(input);
			switch (alt106) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:622:9:
				// localVariableDeclarationStatement
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_localVariableDeclarationStatement_in_blockStatement3323);
				localVariableDeclarationStatement329 = localVariableDeclarationStatement();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0,
							localVariableDeclarationStatement329.getTree());

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:623:9:
				// classOrInterfaceDeclaration
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_classOrInterfaceDeclaration_in_blockStatement3333);
				classOrInterfaceDeclaration330 = classOrInterfaceDeclaration();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, classOrInterfaceDeclaration330
							.getTree());

			}
				break;
			case 3:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:624:9:
				// statement
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_statement_in_blockStatement3343);
				statement331 = statement();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, statement331.getTree());

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 86, blockStatement_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "blockStatement"

	public static class localVariableDeclarationStatement_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "localVariableDeclarationStatement"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:627:1:
	// localVariableDeclarationStatement : localVariableDeclaration ';' ;
	public final JavaParser.localVariableDeclarationStatement_return localVariableDeclarationStatement()
			throws RecognitionException {
		JavaParser.localVariableDeclarationStatement_return retval = new JavaParser.localVariableDeclarationStatement_return();
		retval.start = input.LT(1);
		int localVariableDeclarationStatement_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal333 = null;
		JavaParser.localVariableDeclaration_return localVariableDeclaration332 = null;

		Object char_literal333_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 87)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:628:5:
			// ( localVariableDeclaration ';' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:628:10:
			// localVariableDeclaration ';'
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_localVariableDeclaration_in_localVariableDeclarationStatement3367);
				localVariableDeclaration332 = localVariableDeclaration();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, localVariableDeclaration332
							.getTree());
				char_literal333 = (Token) match(input, 28,
						FOLLOW_28_in_localVariableDeclarationStatement3369);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal333_tree = (Object) adaptor
							.create(char_literal333);
					adaptor.addChild(root_0, char_literal333_tree);
				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 87, localVariableDeclarationStatement_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "localVariableDeclarationStatement"

	public static class localVariableDeclaration_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "localVariableDeclaration"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:631:1:
	// localVariableDeclaration : variableModifiers type variableDeclarators ;
	public final JavaParser.localVariableDeclaration_return localVariableDeclaration()
			throws RecognitionException {
		JavaParser.localVariableDeclaration_return retval = new JavaParser.localVariableDeclaration_return();
		retval.start = input.LT(1);
		int localVariableDeclaration_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.variableModifiers_return variableModifiers334 = null;

		JavaParser.type_return type335 = null;

		JavaParser.variableDeclarators_return variableDeclarators336 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 88)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:632:5:
			// ( variableModifiers type variableDeclarators )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:632:9:
			// variableModifiers type variableDeclarators
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_variableModifiers_in_localVariableDeclaration3388);
				variableModifiers334 = variableModifiers();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, variableModifiers334.getTree());
				pushFollow(FOLLOW_type_in_localVariableDeclaration3390);
				type335 = type();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, type335.getTree());
				pushFollow(FOLLOW_variableDeclarators_in_localVariableDeclaration3392);
				variableDeclarators336 = variableDeclarators();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, variableDeclarators336.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 88, localVariableDeclaration_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "localVariableDeclaration"

	public static class variableModifiers_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "variableModifiers"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:635:1:
	// variableModifiers : ( variableModifier )* ;
	public final JavaParser.variableModifiers_return variableModifiers()
			throws RecognitionException {
		JavaParser.variableModifiers_return retval = new JavaParser.variableModifiers_return();
		retval.start = input.LT(1);
		int variableModifiers_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.variableModifier_return variableModifier337 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 89)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:636:5:
			// ( ( variableModifier )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:636:9:
			// ( variableModifier )*
			{
				root_0 = (Object) adaptor.nil();

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:636:9:
				// ( variableModifier )*
				loop107: do {
					int alt107 = 2;
					int LA107_0 = input.LA(1);

					if ((LA107_0 == 37 || LA107_0 == 75)) {
						alt107 = 1;
					}

					switch (alt107) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
						// variableModifier
					{
						pushFollow(FOLLOW_variableModifier_in_variableModifiers3415);
						variableModifier337 = variableModifier();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, variableModifier337
									.getTree());

					}
						break;

					default:
						break loop107;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 89, variableModifiers_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "variableModifiers"

	public static class statement_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "statement"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:639:1:
	// statement : ( block | ASSERT expression ( ':' expression )? ';' | 'if'
	// parExpression statement ( options {k=1; } : 'else' statement )? | 'for'
	// '(' forControl ')' statement | 'while' parExpression statement | 'do'
	// statement 'while' parExpression ';' | 'try' block ( catches 'finally'
	// block | catches | 'finally' block ) | 'switch' parExpression '{'
	// switchBlockStatementGroups '}' | 'synchronized' parExpression block |
	// 'return' ( expression )? ';' | 'throw' expression ';' | 'break' (
	// Identifier )? ';' | 'continue' ( Identifier )? ';' | ';' |
	// statementExpression ';' | Identifier ':' statement );
	public final JavaParser.statement_return statement()
			throws RecognitionException {
		JavaParser.statement_return retval = new JavaParser.statement_return();
		retval.start = input.LT(1);
		int statement_StartIndex = input.index();
		Object root_0 = null;

		Token ASSERT339 = null;
		Token char_literal341 = null;
		Token char_literal343 = null;
		Token string_literal344 = null;
		Token string_literal347 = null;
		Token string_literal349 = null;
		Token char_literal350 = null;
		Token char_literal352 = null;
		Token string_literal354 = null;
		Token string_literal357 = null;
		Token string_literal359 = null;
		Token char_literal361 = null;
		Token string_literal362 = null;
		Token string_literal365 = null;
		Token string_literal368 = null;
		Token string_literal370 = null;
		Token char_literal372 = null;
		Token char_literal374 = null;
		Token string_literal375 = null;
		Token string_literal378 = null;
		Token char_literal380 = null;
		Token string_literal381 = null;
		Token char_literal383 = null;
		Token string_literal384 = null;
		Token Identifier385 = null;
		Token char_literal386 = null;
		Token string_literal387 = null;
		Token Identifier388 = null;
		Token char_literal389 = null;
		Token char_literal390 = null;
		Token char_literal392 = null;
		Token Identifier393 = null;
		Token char_literal394 = null;
		JavaParser.block_return block338 = null;

		JavaParser.expression_return expression340 = null;

		JavaParser.expression_return expression342 = null;

		JavaParser.parExpression_return parExpression345 = null;

		JavaParser.statement_return statement346 = null;

		JavaParser.statement_return statement348 = null;

		JavaParser.forControl_return forControl351 = null;

		JavaParser.statement_return statement353 = null;

		JavaParser.parExpression_return parExpression355 = null;

		JavaParser.statement_return statement356 = null;

		JavaParser.statement_return statement358 = null;

		JavaParser.parExpression_return parExpression360 = null;

		JavaParser.block_return block363 = null;

		JavaParser.catches_return catches364 = null;

		JavaParser.block_return block366 = null;

		JavaParser.catches_return catches367 = null;

		JavaParser.block_return block369 = null;

		JavaParser.parExpression_return parExpression371 = null;

		JavaParser.switchBlockStatementGroups_return switchBlockStatementGroups373 = null;

		JavaParser.parExpression_return parExpression376 = null;

		JavaParser.block_return block377 = null;

		JavaParser.expression_return expression379 = null;

		JavaParser.expression_return expression382 = null;

		JavaParser.statementExpression_return statementExpression391 = null;

		JavaParser.statement_return statement395 = null;

		Object ASSERT339_tree = null;
		Object char_literal341_tree = null;
		Object char_literal343_tree = null;
		Object string_literal344_tree = null;
		Object string_literal347_tree = null;
		Object string_literal349_tree = null;
		Object char_literal350_tree = null;
		Object char_literal352_tree = null;
		Object string_literal354_tree = null;
		Object string_literal357_tree = null;
		Object string_literal359_tree = null;
		Object char_literal361_tree = null;
		Object string_literal362_tree = null;
		Object string_literal365_tree = null;
		Object string_literal368_tree = null;
		Object string_literal370_tree = null;
		Object char_literal372_tree = null;
		Object char_literal374_tree = null;
		Object string_literal375_tree = null;
		Object string_literal378_tree = null;
		Object char_literal380_tree = null;
		Object string_literal381_tree = null;
		Object char_literal383_tree = null;
		Object string_literal384_tree = null;
		Object Identifier385_tree = null;
		Object char_literal386_tree = null;
		Object string_literal387_tree = null;
		Object Identifier388_tree = null;
		Object char_literal389_tree = null;
		Object char_literal390_tree = null;
		Object char_literal392_tree = null;
		Object Identifier393_tree = null;
		Object char_literal394_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 90)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:640:5:
			// ( block | ASSERT expression ( ':' expression )? ';' | 'if'
			// parExpression statement ( options {k=1; } : 'else' statement )? |
			// 'for' '(' forControl ')' statement | 'while' parExpression
			// statement | 'do' statement 'while' parExpression ';' | 'try'
			// block ( catches 'finally' block | catches | 'finally' block ) |
			// 'switch' parExpression '{' switchBlockStatementGroups '}' |
			// 'synchronized' parExpression block | 'return' ( expression )? ';'
			// | 'throw' expression ';' | 'break' ( Identifier )? ';' |
			// 'continue' ( Identifier )? ';' | ';' | statementExpression ';' |
			// Identifier ':' statement )
			int alt114 = 16;
			alt114 = dfa114.predict(input);
			switch (alt114) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:640:7:
				// block
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_block_in_statement3433);
				block338 = block();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, block338.getTree());

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:641:9:
				// ASSERT expression ( ':' expression )? ';'
			{
				root_0 = (Object) adaptor.nil();

				ASSERT339 = (Token) match(input, ASSERT,
						FOLLOW_ASSERT_in_statement3443);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					ASSERT339_tree = (Object) adaptor.create(ASSERT339);
					adaptor.addChild(root_0, ASSERT339_tree);
				}
				pushFollow(FOLLOW_expression_in_statement3445);
				expression340 = expression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, expression340.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:641:27:
				// ( ':' expression )?
				int alt108 = 2;
				int LA108_0 = input.LA(1);

				if ((LA108_0 == 77)) {
					alt108 = 1;
				}
				switch (alt108) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:641:28:
					// ':' expression
				{
					char_literal341 = (Token) match(input, 77,
							FOLLOW_77_in_statement3448);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						char_literal341_tree = (Object) adaptor
								.create(char_literal341);
						adaptor.addChild(root_0, char_literal341_tree);
					}
					pushFollow(FOLLOW_expression_in_statement3450);
					expression342 = expression();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, expression342.getTree());

				}
					break;

				}

				char_literal343 = (Token) match(input, 28,
						FOLLOW_28_in_statement3454);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal343_tree = (Object) adaptor
							.create(char_literal343);
					adaptor.addChild(root_0, char_literal343_tree);
				}

			}
				break;
			case 3:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:642:9:
				// 'if' parExpression statement ( options {k=1; } : 'else'
				// statement )?
			{
				root_0 = (Object) adaptor.nil();

				string_literal344 = (Token) match(input, 78,
						FOLLOW_78_in_statement3464);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal344_tree = (Object) adaptor
							.create(string_literal344);
					adaptor.addChild(root_0, string_literal344_tree);
				}
				pushFollow(FOLLOW_parExpression_in_statement3466);
				parExpression345 = parExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, parExpression345.getTree());
				pushFollow(FOLLOW_statement_in_statement3468);
				statement346 = statement();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, statement346.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:642:38:
				// ( options {k=1; } : 'else' statement )?
				int alt109 = 2;
				int LA109_0 = input.LA(1);

				if ((LA109_0 == 79)) {
					if ((synpred157_Java())) {
						alt109 = 1;
					}
				}
				switch (alt109) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:642:54:
					// 'else' statement
				{
					string_literal347 = (Token) match(input, 79,
							FOLLOW_79_in_statement3478);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						string_literal347_tree = (Object) adaptor
								.create(string_literal347);
						adaptor.addChild(root_0, string_literal347_tree);
					}
					pushFollow(FOLLOW_statement_in_statement3480);
					statement348 = statement();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, statement348.getTree());

				}
					break;

				}

			}
				break;
			case 4:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:643:9:
				// 'for' '(' forControl ')' statement
			{
				root_0 = (Object) adaptor.nil();

				string_literal349 = (Token) match(input, 80,
						FOLLOW_80_in_statement3492);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal349_tree = (Object) adaptor
							.create(string_literal349);
					adaptor.addChild(root_0, string_literal349_tree);
				}
				char_literal350 = (Token) match(input, 68,
						FOLLOW_68_in_statement3494);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal350_tree = (Object) adaptor
							.create(char_literal350);
					adaptor.addChild(root_0, char_literal350_tree);
				}
				pushFollow(FOLLOW_forControl_in_statement3496);
				forControl351 = forControl();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, forControl351.getTree());
				char_literal352 = (Token) match(input, 69,
						FOLLOW_69_in_statement3498);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal352_tree = (Object) adaptor
							.create(char_literal352);
					adaptor.addChild(root_0, char_literal352_tree);
				}
				pushFollow(FOLLOW_statement_in_statement3500);
				statement353 = statement();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, statement353.getTree());

			}
				break;
			case 5:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:644:9:
				// 'while' parExpression statement
			{
				root_0 = (Object) adaptor.nil();

				string_literal354 = (Token) match(input, 81,
						FOLLOW_81_in_statement3510);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal354_tree = (Object) adaptor
							.create(string_literal354);
					adaptor.addChild(root_0, string_literal354_tree);
				}
				pushFollow(FOLLOW_parExpression_in_statement3512);
				parExpression355 = parExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, parExpression355.getTree());
				pushFollow(FOLLOW_statement_in_statement3514);
				statement356 = statement();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, statement356.getTree());

			}
				break;
			case 6:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:645:9:
				// 'do' statement 'while' parExpression ';'
			{
				root_0 = (Object) adaptor.nil();

				string_literal357 = (Token) match(input, 82,
						FOLLOW_82_in_statement3524);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal357_tree = (Object) adaptor
							.create(string_literal357);
					adaptor.addChild(root_0, string_literal357_tree);
				}
				pushFollow(FOLLOW_statement_in_statement3526);
				statement358 = statement();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, statement358.getTree());
				string_literal359 = (Token) match(input, 81,
						FOLLOW_81_in_statement3528);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal359_tree = (Object) adaptor
							.create(string_literal359);
					adaptor.addChild(root_0, string_literal359_tree);
				}
				pushFollow(FOLLOW_parExpression_in_statement3530);
				parExpression360 = parExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, parExpression360.getTree());
				char_literal361 = (Token) match(input, 28,
						FOLLOW_28_in_statement3532);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal361_tree = (Object) adaptor
							.create(char_literal361);
					adaptor.addChild(root_0, char_literal361_tree);
				}

			}
				break;
			case 7:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:646:9:
				// 'try' block ( catches 'finally' block | catches | 'finally'
				// block )
			{
				root_0 = (Object) adaptor.nil();

				string_literal362 = (Token) match(input, 83,
						FOLLOW_83_in_statement3542);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal362_tree = (Object) adaptor
							.create(string_literal362);
					adaptor.addChild(root_0, string_literal362_tree);
				}
				pushFollow(FOLLOW_block_in_statement3544);
				block363 = block();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, block363.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:647:9:
				// ( catches 'finally' block | catches | 'finally' block )
				int alt110 = 3;
				int LA110_0 = input.LA(1);

				if ((LA110_0 == 90)) {
					if ((synpred162_Java())) {
						alt110 = 1;
					} else if ((synpred163_Java())) {
						alt110 = 2;
					} else {
						if (state.backtracking > 0) {
							state.failed = true;
							return retval;
						}
						NoViableAltException nvae = new NoViableAltException(
								"", 110, 1, input);

						throw nvae;
					}
				} else if ((LA110_0 == 84)) {
					alt110 = 3;
				} else {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					NoViableAltException nvae = new NoViableAltException("",
							110, 0, input);

					throw nvae;
				}
				switch (alt110) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:647:11:
					// catches 'finally' block
				{
					pushFollow(FOLLOW_catches_in_statement3556);
					catches364 = catches();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, catches364.getTree());
					string_literal365 = (Token) match(input, 84,
							FOLLOW_84_in_statement3558);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						string_literal365_tree = (Object) adaptor
								.create(string_literal365);
						adaptor.addChild(root_0, string_literal365_tree);
					}
					pushFollow(FOLLOW_block_in_statement3560);
					block366 = block();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, block366.getTree());

				}
					break;
				case 2:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:648:11:
					// catches
				{
					pushFollow(FOLLOW_catches_in_statement3572);
					catches367 = catches();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, catches367.getTree());

				}
					break;
				case 3:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:649:13:
					// 'finally' block
				{
					string_literal368 = (Token) match(input, 84,
							FOLLOW_84_in_statement3586);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						string_literal368_tree = (Object) adaptor
								.create(string_literal368);
						adaptor.addChild(root_0, string_literal368_tree);
					}
					pushFollow(FOLLOW_block_in_statement3588);
					block369 = block();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, block369.getTree());

				}
					break;

				}

			}
				break;
			case 8:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:651:9:
				// 'switch' parExpression '{' switchBlockStatementGroups '}'
			{
				root_0 = (Object) adaptor.nil();

				string_literal370 = (Token) match(input, 85,
						FOLLOW_85_in_statement3608);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal370_tree = (Object) adaptor
							.create(string_literal370);
					adaptor.addChild(root_0, string_literal370_tree);
				}
				pushFollow(FOLLOW_parExpression_in_statement3610);
				parExpression371 = parExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, parExpression371.getTree());
				char_literal372 = (Token) match(input, 46,
						FOLLOW_46_in_statement3612);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal372_tree = (Object) adaptor
							.create(char_literal372);
					adaptor.addChild(root_0, char_literal372_tree);
				}
				pushFollow(FOLLOW_switchBlockStatementGroups_in_statement3614);
				switchBlockStatementGroups373 = switchBlockStatementGroups();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, switchBlockStatementGroups373
							.getTree());
				char_literal374 = (Token) match(input, 47,
						FOLLOW_47_in_statement3616);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal374_tree = (Object) adaptor
							.create(char_literal374);
					adaptor.addChild(root_0, char_literal374_tree);
				}

			}
				break;
			case 9:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:652:9:
				// 'synchronized' parExpression block
			{
				root_0 = (Object) adaptor.nil();

				string_literal375 = (Token) match(input, 55,
						FOLLOW_55_in_statement3626);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal375_tree = (Object) adaptor
							.create(string_literal375);
					adaptor.addChild(root_0, string_literal375_tree);
				}
				pushFollow(FOLLOW_parExpression_in_statement3628);
				parExpression376 = parExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, parExpression376.getTree());
				pushFollow(FOLLOW_block_in_statement3630);
				block377 = block();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, block377.getTree());

			}
				break;
			case 10:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:653:9:
				// 'return' ( expression )? ';'
			{
				root_0 = (Object) adaptor.nil();

				string_literal378 = (Token) match(input, 86,
						FOLLOW_86_in_statement3640);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal378_tree = (Object) adaptor
							.create(string_literal378);
					adaptor.addChild(root_0, string_literal378_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:653:18:
				// ( expression )?
				int alt111 = 2;
				int LA111_0 = input.LA(1);

				if ((LA111_0 == Identifier
						|| (LA111_0 >= FloatingPointLiteral && LA111_0 <= DecimalLiteral)
						|| LA111_0 == 49 || (LA111_0 >= 58 && LA111_0 <= 65)
						|| (LA111_0 >= 67 && LA111_0 <= 68)
						|| (LA111_0 >= 71 && LA111_0 <= 74)
						|| (LA111_0 >= 107 && LA111_0 <= 108) || (LA111_0 >= 111 && LA111_0 <= 115))) {
					alt111 = 1;
				}
				switch (alt111) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// expression
				{
					pushFollow(FOLLOW_expression_in_statement3642);
					expression379 = expression();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, expression379.getTree());

				}
					break;

				}

				char_literal380 = (Token) match(input, 28,
						FOLLOW_28_in_statement3645);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal380_tree = (Object) adaptor
							.create(char_literal380);
					adaptor.addChild(root_0, char_literal380_tree);
				}

			}
				break;
			case 11:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:654:9:
				// 'throw' expression ';'
			{
				root_0 = (Object) adaptor.nil();

				string_literal381 = (Token) match(input, 87,
						FOLLOW_87_in_statement3655);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal381_tree = (Object) adaptor
							.create(string_literal381);
					adaptor.addChild(root_0, string_literal381_tree);
				}
				pushFollow(FOLLOW_expression_in_statement3657);
				expression382 = expression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, expression382.getTree());
				char_literal383 = (Token) match(input, 28,
						FOLLOW_28_in_statement3659);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal383_tree = (Object) adaptor
							.create(char_literal383);
					adaptor.addChild(root_0, char_literal383_tree);
				}

			}
				break;
			case 12:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:655:9:
				// 'break' ( Identifier )? ';'
			{
				root_0 = (Object) adaptor.nil();

				string_literal384 = (Token) match(input, 88,
						FOLLOW_88_in_statement3669);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal384_tree = (Object) adaptor
							.create(string_literal384);
					adaptor.addChild(root_0, string_literal384_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:655:17:
				// ( Identifier )?
				int alt112 = 2;
				int LA112_0 = input.LA(1);

				if ((LA112_0 == Identifier)) {
					alt112 = 1;
				}
				switch (alt112) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// Identifier
				{
					Identifier385 = (Token) match(input, Identifier,
							FOLLOW_Identifier_in_statement3671);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						Identifier385_tree = (Object) adaptor
								.create(Identifier385);
						adaptor.addChild(root_0, Identifier385_tree);
					}

				}
					break;

				}

				char_literal386 = (Token) match(input, 28,
						FOLLOW_28_in_statement3674);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal386_tree = (Object) adaptor
							.create(char_literal386);
					adaptor.addChild(root_0, char_literal386_tree);
				}

			}
				break;
			case 13:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:656:9:
				// 'continue' ( Identifier )? ';'
			{
				root_0 = (Object) adaptor.nil();

				string_literal387 = (Token) match(input, 89,
						FOLLOW_89_in_statement3684);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal387_tree = (Object) adaptor
							.create(string_literal387);
					adaptor.addChild(root_0, string_literal387_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:656:20:
				// ( Identifier )?
				int alt113 = 2;
				int LA113_0 = input.LA(1);

				if ((LA113_0 == Identifier)) {
					alt113 = 1;
				}
				switch (alt113) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// Identifier
				{
					Identifier388 = (Token) match(input, Identifier,
							FOLLOW_Identifier_in_statement3686);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						Identifier388_tree = (Object) adaptor
								.create(Identifier388);
						adaptor.addChild(root_0, Identifier388_tree);
					}

				}
					break;

				}

				char_literal389 = (Token) match(input, 28,
						FOLLOW_28_in_statement3689);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal389_tree = (Object) adaptor
							.create(char_literal389);
					adaptor.addChild(root_0, char_literal389_tree);
				}

			}
				break;
			case 14:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:657:9:
				// ';'
			{
				root_0 = (Object) adaptor.nil();

				char_literal390 = (Token) match(input, 28,
						FOLLOW_28_in_statement3699);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal390_tree = (Object) adaptor
							.create(char_literal390);
					adaptor.addChild(root_0, char_literal390_tree);
				}

			}
				break;
			case 15:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:658:9:
				// statementExpression ';'
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_statementExpression_in_statement3710);
				statementExpression391 = statementExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, statementExpression391.getTree());
				char_literal392 = (Token) match(input, 28,
						FOLLOW_28_in_statement3712);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal392_tree = (Object) adaptor
							.create(char_literal392);
					adaptor.addChild(root_0, char_literal392_tree);
				}

			}
				break;
			case 16:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:659:9:
				// Identifier ':' statement
			{
				root_0 = (Object) adaptor.nil();

				Identifier393 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_statement3722);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier393_tree = (Object) adaptor.create(Identifier393);
					adaptor.addChild(root_0, Identifier393_tree);
				}
				char_literal394 = (Token) match(input, 77,
						FOLLOW_77_in_statement3724);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal394_tree = (Object) adaptor
							.create(char_literal394);
					adaptor.addChild(root_0, char_literal394_tree);
				}
				pushFollow(FOLLOW_statement_in_statement3726);
				statement395 = statement();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, statement395.getTree());

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 90, statement_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "statement"

	public static class catches_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "catches"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:662:1:
	// catches : catchClause ( catchClause )* ;
	public final JavaParser.catches_return catches()
			throws RecognitionException {
		JavaParser.catches_return retval = new JavaParser.catches_return();
		retval.start = input.LT(1);
		int catches_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.catchClause_return catchClause396 = null;

		JavaParser.catchClause_return catchClause397 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 91)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:663:5:
			// ( catchClause ( catchClause )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:663:9:
			// catchClause ( catchClause )*
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_catchClause_in_catches3749);
				catchClause396 = catchClause();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, catchClause396.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:663:21:
				// ( catchClause )*
				loop115: do {
					int alt115 = 2;
					int LA115_0 = input.LA(1);

					if ((LA115_0 == 90)) {
						alt115 = 1;
					}

					switch (alt115) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:663:22:
						// catchClause
					{
						pushFollow(FOLLOW_catchClause_in_catches3752);
						catchClause397 = catchClause();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, catchClause397.getTree());

					}
						break;

					default:
						break loop115;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 91, catches_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "catches"

	public static class catchClause_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "catchClause"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:666:1:
	// catchClause : 'catch' '(' formalParameter ')' block ;
	public final JavaParser.catchClause_return catchClause()
			throws RecognitionException {
		JavaParser.catchClause_return retval = new JavaParser.catchClause_return();
		retval.start = input.LT(1);
		int catchClause_StartIndex = input.index();
		Object root_0 = null;

		Token string_literal398 = null;
		Token char_literal399 = null;
		Token char_literal401 = null;
		JavaParser.formalParameter_return formalParameter400 = null;

		JavaParser.block_return block402 = null;

		Object string_literal398_tree = null;
		Object char_literal399_tree = null;
		Object char_literal401_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 92)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:667:5:
			// ( 'catch' '(' formalParameter ')' block )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:667:9:
			// 'catch' '(' formalParameter ')' block
			{
				root_0 = (Object) adaptor.nil();

				string_literal398 = (Token) match(input, 90,
						FOLLOW_90_in_catchClause3777);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal398_tree = (Object) adaptor
							.create(string_literal398);
					adaptor.addChild(root_0, string_literal398_tree);
				}
				char_literal399 = (Token) match(input, 68,
						FOLLOW_68_in_catchClause3779);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal399_tree = (Object) adaptor
							.create(char_literal399);
					adaptor.addChild(root_0, char_literal399_tree);
				}
				pushFollow(FOLLOW_formalParameter_in_catchClause3781);
				formalParameter400 = formalParameter();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, formalParameter400.getTree());
				char_literal401 = (Token) match(input, 69,
						FOLLOW_69_in_catchClause3783);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal401_tree = (Object) adaptor
							.create(char_literal401);
					adaptor.addChild(root_0, char_literal401_tree);
				}
				pushFollow(FOLLOW_block_in_catchClause3785);
				block402 = block();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, block402.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 92, catchClause_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "catchClause"

	public static class formalParameter_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "formalParameter"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:670:1:
	// formalParameter : variableModifiers type variableDeclaratorId ;
	public final JavaParser.formalParameter_return formalParameter()
			throws RecognitionException {
		JavaParser.formalParameter_return retval = new JavaParser.formalParameter_return();
		retval.start = input.LT(1);
		int formalParameter_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.variableModifiers_return variableModifiers403 = null;

		JavaParser.type_return type404 = null;

		JavaParser.variableDeclaratorId_return variableDeclaratorId405 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 93)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:671:5:
			// ( variableModifiers type variableDeclaratorId )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:671:9:
			// variableModifiers type variableDeclaratorId
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_variableModifiers_in_formalParameter3804);
				variableModifiers403 = variableModifiers();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, variableModifiers403.getTree());
				pushFollow(FOLLOW_type_in_formalParameter3806);
				type404 = type();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, type404.getTree());
				pushFollow(FOLLOW_variableDeclaratorId_in_formalParameter3808);
				variableDeclaratorId405 = variableDeclaratorId();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, variableDeclaratorId405.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 93, formalParameter_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "formalParameter"

	public static class switchBlockStatementGroups_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "switchBlockStatementGroups"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:674:1:
	// switchBlockStatementGroups : ( switchBlockStatementGroup )* ;
	public final JavaParser.switchBlockStatementGroups_return switchBlockStatementGroups()
			throws RecognitionException {
		JavaParser.switchBlockStatementGroups_return retval = new JavaParser.switchBlockStatementGroups_return();
		retval.start = input.LT(1);
		int switchBlockStatementGroups_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.switchBlockStatementGroup_return switchBlockStatementGroup406 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 94)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:675:5:
			// ( ( switchBlockStatementGroup )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:675:9:
			// ( switchBlockStatementGroup )*
			{
				root_0 = (Object) adaptor.nil();

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:675:9:
				// ( switchBlockStatementGroup )*
				loop116: do {
					int alt116 = 2;
					int LA116_0 = input.LA(1);

					if ((LA116_0 == 76 || LA116_0 == 91)) {
						alt116 = 1;
					}

					switch (alt116) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:675:10:
						// switchBlockStatementGroup
					{
						pushFollow(FOLLOW_switchBlockStatementGroup_in_switchBlockStatementGroups3836);
						switchBlockStatementGroup406 = switchBlockStatementGroup();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0,
									switchBlockStatementGroup406.getTree());

					}
						break;

					default:
						break loop116;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 94, switchBlockStatementGroups_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "switchBlockStatementGroups"

	public static class switchBlockStatementGroup_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "switchBlockStatementGroup"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:682:1:
	// switchBlockStatementGroup : ( switchLabel )+ ( blockStatement )* ;
	public final JavaParser.switchBlockStatementGroup_return switchBlockStatementGroup()
			throws RecognitionException {
		JavaParser.switchBlockStatementGroup_return retval = new JavaParser.switchBlockStatementGroup_return();
		retval.start = input.LT(1);
		int switchBlockStatementGroup_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.switchLabel_return switchLabel407 = null;

		JavaParser.blockStatement_return blockStatement408 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 95)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:683:5:
			// ( ( switchLabel )+ ( blockStatement )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:683:9:
			// ( switchLabel )+ ( blockStatement )*
			{
				root_0 = (Object) adaptor.nil();

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:683:9:
				// ( switchLabel )+
				int cnt117 = 0;
				loop117: do {
					int alt117 = 2;
					int LA117_0 = input.LA(1);

					if ((LA117_0 == 91)) {
						if ((synpred178_Java())) {
							alt117 = 1;
						}

					} else if ((LA117_0 == 76)) {
						if ((synpred178_Java())) {
							alt117 = 1;
						}

					}

					switch (alt117) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
						// switchLabel
					{
						pushFollow(FOLLOW_switchLabel_in_switchBlockStatementGroup3863);
						switchLabel407 = switchLabel();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, switchLabel407.getTree());

					}
						break;

					default:
						if (cnt117 >= 1)
							break loop117;
						if (state.backtracking > 0) {
							state.failed = true;
							return retval;
						}
						EarlyExitException eee = new EarlyExitException(117,
								input);
						throw eee;
					}
					cnt117++;
				} while (true);

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:683:22:
				// ( blockStatement )*
				loop118: do {
					int alt118 = 2;
					int LA118_0 = input.LA(1);

					if (((LA118_0 >= Identifier && LA118_0 <= ASSERT)
							|| LA118_0 == 28 || LA118_0 == 30
							|| (LA118_0 >= 33 && LA118_0 <= 39)
							|| LA118_0 == 46
							|| (LA118_0 >= 48 && LA118_0 <= 49)
							|| LA118_0 == 55
							|| (LA118_0 >= 58 && LA118_0 <= 65)
							|| (LA118_0 >= 67 && LA118_0 <= 68)
							|| (LA118_0 >= 71 && LA118_0 <= 75)
							|| LA118_0 == 78
							|| (LA118_0 >= 80 && LA118_0 <= 83)
							|| (LA118_0 >= 85 && LA118_0 <= 89)
							|| (LA118_0 >= 107 && LA118_0 <= 108) || (LA118_0 >= 111 && LA118_0 <= 115))) {
						alt118 = 1;
					}

					switch (alt118) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
						// blockStatement
					{
						pushFollow(FOLLOW_blockStatement_in_switchBlockStatementGroup3866);
						blockStatement408 = blockStatement();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, blockStatement408
									.getTree());

					}
						break;

					default:
						break loop118;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 95, switchBlockStatementGroup_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "switchBlockStatementGroup"

	public static class switchLabel_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "switchLabel"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:686:1:
	// switchLabel : ( 'case' constantExpression ':' | 'case' enumConstantName
	// ':' | 'default' ':' );
	public final JavaParser.switchLabel_return switchLabel()
			throws RecognitionException {
		JavaParser.switchLabel_return retval = new JavaParser.switchLabel_return();
		retval.start = input.LT(1);
		int switchLabel_StartIndex = input.index();
		Object root_0 = null;

		Token string_literal409 = null;
		Token char_literal411 = null;
		Token string_literal412 = null;
		Token char_literal414 = null;
		Token string_literal415 = null;
		Token char_literal416 = null;
		JavaParser.constantExpression_return constantExpression410 = null;

		JavaParser.enumConstantName_return enumConstantName413 = null;

		Object string_literal409_tree = null;
		Object char_literal411_tree = null;
		Object string_literal412_tree = null;
		Object char_literal414_tree = null;
		Object string_literal415_tree = null;
		Object char_literal416_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 96)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:687:5:
			// ( 'case' constantExpression ':' | 'case' enumConstantName ':' |
			// 'default' ':' )
			int alt119 = 3;
			int LA119_0 = input.LA(1);

			if ((LA119_0 == 91)) {
				int LA119_1 = input.LA(2);

				if ((LA119_1 == Identifier)) {
					int LA119_3 = input.LA(3);

					if ((LA119_3 == 77)) {
						if ((synpred180_Java())) {
							alt119 = 1;
						} else if ((synpred181_Java())) {
							alt119 = 2;
						} else {
							if (state.backtracking > 0) {
								state.failed = true;
								return retval;
							}
							NoViableAltException nvae = new NoViableAltException(
									"", 119, 5, input);

							throw nvae;
						}
					} else if (((LA119_3 >= 31 && LA119_3 <= 32)
							|| LA119_3 == 42
							|| (LA119_3 >= 44 && LA119_3 <= 45)
							|| LA119_3 == 50 || LA119_3 == 53 || LA119_3 == 66
							|| LA119_3 == 68 || (LA119_3 >= 92 && LA119_3 <= 112))) {
						alt119 = 1;
					} else {
						if (state.backtracking > 0) {
							state.failed = true;
							return retval;
						}
						NoViableAltException nvae = new NoViableAltException(
								"", 119, 3, input);

						throw nvae;
					}
				} else if (((LA119_1 >= FloatingPointLiteral && LA119_1 <= DecimalLiteral)
						|| LA119_1 == 49
						|| (LA119_1 >= 58 && LA119_1 <= 65)
						|| (LA119_1 >= 67 && LA119_1 <= 68)
						|| (LA119_1 >= 71 && LA119_1 <= 74)
						|| (LA119_1 >= 107 && LA119_1 <= 108) || (LA119_1 >= 111 && LA119_1 <= 115))) {
					alt119 = 1;
				} else {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					NoViableAltException nvae = new NoViableAltException("",
							119, 1, input);

					throw nvae;
				}
			} else if ((LA119_0 == 76)) {
				alt119 = 3;
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 119,
						0, input);

				throw nvae;
			}
			switch (alt119) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:687:9:
				// 'case' constantExpression ':'
			{
				root_0 = (Object) adaptor.nil();

				string_literal409 = (Token) match(input, 91,
						FOLLOW_91_in_switchLabel3890);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal409_tree = (Object) adaptor
							.create(string_literal409);
					adaptor.addChild(root_0, string_literal409_tree);
				}
				pushFollow(FOLLOW_constantExpression_in_switchLabel3892);
				constantExpression410 = constantExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, constantExpression410.getTree());
				char_literal411 = (Token) match(input, 77,
						FOLLOW_77_in_switchLabel3894);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal411_tree = (Object) adaptor
							.create(char_literal411);
					adaptor.addChild(root_0, char_literal411_tree);
				}

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:688:9:
				// 'case' enumConstantName ':'
			{
				root_0 = (Object) adaptor.nil();

				string_literal412 = (Token) match(input, 91,
						FOLLOW_91_in_switchLabel3904);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal412_tree = (Object) adaptor
							.create(string_literal412);
					adaptor.addChild(root_0, string_literal412_tree);
				}
				pushFollow(FOLLOW_enumConstantName_in_switchLabel3906);
				enumConstantName413 = enumConstantName();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, enumConstantName413.getTree());
				char_literal414 = (Token) match(input, 77,
						FOLLOW_77_in_switchLabel3908);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal414_tree = (Object) adaptor
							.create(char_literal414);
					adaptor.addChild(root_0, char_literal414_tree);
				}

			}
				break;
			case 3:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:689:9:
				// 'default' ':'
			{
				root_0 = (Object) adaptor.nil();

				string_literal415 = (Token) match(input, 76,
						FOLLOW_76_in_switchLabel3918);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal415_tree = (Object) adaptor
							.create(string_literal415);
					adaptor.addChild(root_0, string_literal415_tree);
				}
				char_literal416 = (Token) match(input, 77,
						FOLLOW_77_in_switchLabel3920);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal416_tree = (Object) adaptor
							.create(char_literal416);
					adaptor.addChild(root_0, char_literal416_tree);
				}

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 96, switchLabel_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "switchLabel"

	public static class forControl_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "forControl"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:692:1:
	// forControl options {k=3; } : ( enhancedForControl | ( forInit )? ';' (
	// expression )? ';' ( forUpdate )? );
	public final JavaParser.forControl_return forControl()
			throws RecognitionException {
		JavaParser.forControl_return retval = new JavaParser.forControl_return();
		retval.start = input.LT(1);
		int forControl_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal419 = null;
		Token char_literal421 = null;
		JavaParser.enhancedForControl_return enhancedForControl417 = null;

		JavaParser.forInit_return forInit418 = null;

		JavaParser.expression_return expression420 = null;

		JavaParser.forUpdate_return forUpdate422 = null;

		Object char_literal419_tree = null;
		Object char_literal421_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 97)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:694:5:
			// ( enhancedForControl | ( forInit )? ';' ( expression )? ';' (
			// forUpdate )? )
			int alt123 = 2;
			alt123 = dfa123.predict(input);
			switch (alt123) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:694:9:
				// enhancedForControl
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_enhancedForControl_in_forControl3951);
				enhancedForControl417 = enhancedForControl();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, enhancedForControl417.getTree());

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:695:9:
				// ( forInit )? ';' ( expression )? ';' ( forUpdate )?
			{
				root_0 = (Object) adaptor.nil();

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:695:9:
				// ( forInit )?
				int alt120 = 2;
				int LA120_0 = input.LA(1);

				if ((LA120_0 == Identifier
						|| (LA120_0 >= FloatingPointLiteral && LA120_0 <= DecimalLiteral)
						|| LA120_0 == 37 || LA120_0 == 49
						|| (LA120_0 >= 58 && LA120_0 <= 65)
						|| (LA120_0 >= 67 && LA120_0 <= 68)
						|| (LA120_0 >= 71 && LA120_0 <= 75)
						|| (LA120_0 >= 107 && LA120_0 <= 108) || (LA120_0 >= 111 && LA120_0 <= 115))) {
					alt120 = 1;
				}
				switch (alt120) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// forInit
				{
					pushFollow(FOLLOW_forInit_in_forControl3961);
					forInit418 = forInit();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, forInit418.getTree());

				}
					break;

				}

				char_literal419 = (Token) match(input, 28,
						FOLLOW_28_in_forControl3964);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal419_tree = (Object) adaptor
							.create(char_literal419);
					adaptor.addChild(root_0, char_literal419_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:695:22:
				// ( expression )?
				int alt121 = 2;
				int LA121_0 = input.LA(1);

				if ((LA121_0 == Identifier
						|| (LA121_0 >= FloatingPointLiteral && LA121_0 <= DecimalLiteral)
						|| LA121_0 == 49 || (LA121_0 >= 58 && LA121_0 <= 65)
						|| (LA121_0 >= 67 && LA121_0 <= 68)
						|| (LA121_0 >= 71 && LA121_0 <= 74)
						|| (LA121_0 >= 107 && LA121_0 <= 108) || (LA121_0 >= 111 && LA121_0 <= 115))) {
					alt121 = 1;
				}
				switch (alt121) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// expression
				{
					pushFollow(FOLLOW_expression_in_forControl3966);
					expression420 = expression();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, expression420.getTree());

				}
					break;

				}

				char_literal421 = (Token) match(input, 28,
						FOLLOW_28_in_forControl3969);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal421_tree = (Object) adaptor
							.create(char_literal421);
					adaptor.addChild(root_0, char_literal421_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:695:38:
				// ( forUpdate )?
				int alt122 = 2;
				int LA122_0 = input.LA(1);

				if ((LA122_0 == Identifier
						|| (LA122_0 >= FloatingPointLiteral && LA122_0 <= DecimalLiteral)
						|| LA122_0 == 49 || (LA122_0 >= 58 && LA122_0 <= 65)
						|| (LA122_0 >= 67 && LA122_0 <= 68)
						|| (LA122_0 >= 71 && LA122_0 <= 74)
						|| (LA122_0 >= 107 && LA122_0 <= 108) || (LA122_0 >= 111 && LA122_0 <= 115))) {
					alt122 = 1;
				}
				switch (alt122) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// forUpdate
				{
					pushFollow(FOLLOW_forUpdate_in_forControl3971);
					forUpdate422 = forUpdate();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, forUpdate422.getTree());

				}
					break;

				}

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 97, forControl_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "forControl"

	public static class forInit_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "forInit"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:698:1:
	// forInit : ( localVariableDeclaration | expressionList );
	public final JavaParser.forInit_return forInit()
			throws RecognitionException {
		JavaParser.forInit_return retval = new JavaParser.forInit_return();
		retval.start = input.LT(1);
		int forInit_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.localVariableDeclaration_return localVariableDeclaration423 = null;

		JavaParser.expressionList_return expressionList424 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 98)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:699:5:
			// ( localVariableDeclaration | expressionList )
			int alt124 = 2;
			alt124 = dfa124.predict(input);
			switch (alt124) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:699:9:
				// localVariableDeclaration
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_localVariableDeclaration_in_forInit3991);
				localVariableDeclaration423 = localVariableDeclaration();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, localVariableDeclaration423
							.getTree());

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:700:9:
				// expressionList
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_expressionList_in_forInit4001);
				expressionList424 = expressionList();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, expressionList424.getTree());

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 98, forInit_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "forInit"

	public static class enhancedForControl_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "enhancedForControl"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:703:1:
	// enhancedForControl : variableModifiers type Identifier ':' expression ;
	public final JavaParser.enhancedForControl_return enhancedForControl()
			throws RecognitionException {
		JavaParser.enhancedForControl_return retval = new JavaParser.enhancedForControl_return();
		retval.start = input.LT(1);
		int enhancedForControl_StartIndex = input.index();
		Object root_0 = null;

		Token Identifier427 = null;
		Token char_literal428 = null;
		JavaParser.variableModifiers_return variableModifiers425 = null;

		JavaParser.type_return type426 = null;

		JavaParser.expression_return expression429 = null;

		Object Identifier427_tree = null;
		Object char_literal428_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 99)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:704:5:
			// ( variableModifiers type Identifier ':' expression )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:704:9:
			// variableModifiers type Identifier ':' expression
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_variableModifiers_in_enhancedForControl4024);
				variableModifiers425 = variableModifiers();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, variableModifiers425.getTree());
				pushFollow(FOLLOW_type_in_enhancedForControl4026);
				type426 = type();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, type426.getTree());
				Identifier427 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_enhancedForControl4028);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier427_tree = (Object) adaptor.create(Identifier427);
					adaptor.addChild(root_0, Identifier427_tree);
				}
				char_literal428 = (Token) match(input, 77,
						FOLLOW_77_in_enhancedForControl4030);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal428_tree = (Object) adaptor
							.create(char_literal428);
					adaptor.addChild(root_0, char_literal428_tree);
				}
				pushFollow(FOLLOW_expression_in_enhancedForControl4032);
				expression429 = expression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, expression429.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 99, enhancedForControl_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "enhancedForControl"

	public static class forUpdate_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "forUpdate"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:707:1:
	// forUpdate : expressionList ;
	public final JavaParser.forUpdate_return forUpdate()
			throws RecognitionException {
		JavaParser.forUpdate_return retval = new JavaParser.forUpdate_return();
		retval.start = input.LT(1);
		int forUpdate_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.expressionList_return expressionList430 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 100)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:708:5:
			// ( expressionList )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:708:9:
			// expressionList
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_expressionList_in_forUpdate4051);
				expressionList430 = expressionList();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, expressionList430.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 100, forUpdate_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "forUpdate"

	public static class parExpression_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "parExpression"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:713:1:
	// parExpression : '(' expression ')' ;
	public final JavaParser.parExpression_return parExpression()
			throws RecognitionException {
		JavaParser.parExpression_return retval = new JavaParser.parExpression_return();
		retval.start = input.LT(1);
		int parExpression_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal431 = null;
		Token char_literal433 = null;
		JavaParser.expression_return expression432 = null;

		Object char_literal431_tree = null;
		Object char_literal433_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 101)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:714:5:
			// ( '(' expression ')' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:714:9:
			// '(' expression ')'
			{
				root_0 = (Object) adaptor.nil();

				char_literal431 = (Token) match(input, 68,
						FOLLOW_68_in_parExpression4072);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal431_tree = (Object) adaptor
							.create(char_literal431);
					adaptor.addChild(root_0, char_literal431_tree);
				}
				pushFollow(FOLLOW_expression_in_parExpression4074);
				expression432 = expression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, expression432.getTree());
				char_literal433 = (Token) match(input, 69,
						FOLLOW_69_in_parExpression4076);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal433_tree = (Object) adaptor
							.create(char_literal433);
					adaptor.addChild(root_0, char_literal433_tree);
				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 101, parExpression_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "parExpression"

	public static class expressionList_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "expressionList"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:717:1:
	// expressionList : expression ( ',' expression )* ;
	public final JavaParser.expressionList_return expressionList()
			throws RecognitionException {
		JavaParser.expressionList_return retval = new JavaParser.expressionList_return();
		retval.start = input.LT(1);
		int expressionList_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal435 = null;
		JavaParser.expression_return expression434 = null;

		JavaParser.expression_return expression436 = null;

		Object char_literal435_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 102)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:718:5:
			// ( expression ( ',' expression )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:718:9:
			// expression ( ',' expression )*
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_expression_in_expressionList4099);
				expression434 = expression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, expression434.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:718:20:
				// ( ',' expression )*
				loop125: do {
					int alt125 = 2;
					int LA125_0 = input.LA(1);

					if ((LA125_0 == 43)) {
						alt125 = 1;
					}

					switch (alt125) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:718:21:
						// ',' expression
					{
						char_literal435 = (Token) match(input, 43,
								FOLLOW_43_in_expressionList4102);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal435_tree = (Object) adaptor
									.create(char_literal435);
							adaptor.addChild(root_0, char_literal435_tree);
						}
						pushFollow(FOLLOW_expression_in_expressionList4104);
						expression436 = expression();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, expression436.getTree());

					}
						break;

					default:
						break loop125;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 102, expressionList_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "expressionList"

	public static class statementExpression_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "statementExpression"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:721:1:
	// statementExpression : expression ;
	public final JavaParser.statementExpression_return statementExpression()
			throws RecognitionException {
		JavaParser.statementExpression_return retval = new JavaParser.statementExpression_return();
		retval.start = input.LT(1);
		int statementExpression_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.expression_return expression437 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 103)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:722:5:
			// ( expression )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:722:9:
			// expression
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_expression_in_statementExpression4125);
				expression437 = expression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, expression437.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 103, statementExpression_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "statementExpression"

	public static class constantExpression_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "constantExpression"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:725:1:
	// constantExpression : expression ;
	public final JavaParser.constantExpression_return constantExpression()
			throws RecognitionException {
		JavaParser.constantExpression_return retval = new JavaParser.constantExpression_return();
		retval.start = input.LT(1);
		int constantExpression_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.expression_return expression438 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 104)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:726:5:
			// ( expression )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:726:9:
			// expression
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_expression_in_constantExpression4148);
				expression438 = expression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, expression438.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 104, constantExpression_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "constantExpression"

	public static class expression_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "expression"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:729:1:
	// expression : conditionalExpression ( assignmentOperator expression )? ;
	public final JavaParser.expression_return expression()
			throws RecognitionException {
		JavaParser.expression_return retval = new JavaParser.expression_return();
		retval.start = input.LT(1);
		int expression_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.conditionalExpression_return conditionalExpression439 = null;

		JavaParser.assignmentOperator_return assignmentOperator440 = null;

		JavaParser.expression_return expression441 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 105)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:730:5:
			// ( conditionalExpression ( assignmentOperator expression )? )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:730:9:
			// conditionalExpression ( assignmentOperator expression )?
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_conditionalExpression_in_expression4171);
				conditionalExpression439 = conditionalExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor
							.addChild(root_0, conditionalExpression439
									.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:730:31:
				// ( assignmentOperator expression )?
				int alt126 = 2;
				alt126 = dfa126.predict(input);
				switch (alt126) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:730:32:
					// assignmentOperator expression
				{
					pushFollow(FOLLOW_assignmentOperator_in_expression4174);
					assignmentOperator440 = assignmentOperator();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, assignmentOperator440
								.getTree());
					pushFollow(FOLLOW_expression_in_expression4176);
					expression441 = expression();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, expression441.getTree());

				}
					break;

				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 105, expression_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "expression"

	public static class assignmentOperator_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "assignmentOperator"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:733:1:
	// assignmentOperator : ( '=' | '+=' | '-=' | '*=' | '/=' | '&=' | '|=' |
	// '^=' | '%=' | ( '<' '<' '=' )=>t1= '<' t2= '<' t3= '=' {...}? | ( '>' '>'
	// '>' '=' )=>t1= '>' t2= '>' t3= '>' t4= '=' {...}? | ( '>' '>' '=' )=>t1=
	// '>' t2= '>' t3= '=' {...}?);
	public final JavaParser.assignmentOperator_return assignmentOperator()
			throws RecognitionException {
		JavaParser.assignmentOperator_return retval = new JavaParser.assignmentOperator_return();
		retval.start = input.LT(1);
		int assignmentOperator_StartIndex = input.index();
		Object root_0 = null;

		Token t1 = null;
		Token t2 = null;
		Token t3 = null;
		Token t4 = null;
		Token char_literal442 = null;
		Token string_literal443 = null;
		Token string_literal444 = null;
		Token string_literal445 = null;
		Token string_literal446 = null;
		Token string_literal447 = null;
		Token string_literal448 = null;
		Token string_literal449 = null;
		Token string_literal450 = null;

		Object t1_tree = null;
		Object t2_tree = null;
		Object t3_tree = null;
		Object t4_tree = null;
		Object char_literal442_tree = null;
		Object string_literal443_tree = null;
		Object string_literal444_tree = null;
		Object string_literal445_tree = null;
		Object string_literal446_tree = null;
		Object string_literal447_tree = null;
		Object string_literal448_tree = null;
		Object string_literal449_tree = null;
		Object string_literal450_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 106)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:734:5:
			// ( '=' | '+=' | '-=' | '*=' | '/=' | '&=' | '|=' | '^=' | '%=' | (
			// '<' '<' '=' )=>t1= '<' t2= '<' t3= '=' {...}? | ( '>' '>' '>' '='
			// )=>t1= '>' t2= '>' t3= '>' t4= '=' {...}? | ( '>' '>' '=' )=>t1=
			// '>' t2= '>' t3= '=' {...}?)
			int alt127 = 12;
			alt127 = dfa127.predict(input);
			switch (alt127) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:734:9:
				// '='
			{
				root_0 = (Object) adaptor.nil();

				char_literal442 = (Token) match(input, 53,
						FOLLOW_53_in_assignmentOperator4201);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal442_tree = (Object) adaptor
							.create(char_literal442);
					adaptor.addChild(root_0, char_literal442_tree);
				}

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:735:9:
				// '+='
			{
				root_0 = (Object) adaptor.nil();

				string_literal443 = (Token) match(input, 92,
						FOLLOW_92_in_assignmentOperator4211);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal443_tree = (Object) adaptor
							.create(string_literal443);
					adaptor.addChild(root_0, string_literal443_tree);
				}

			}
				break;
			case 3:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:736:9:
				// '-='
			{
				root_0 = (Object) adaptor.nil();

				string_literal444 = (Token) match(input, 93,
						FOLLOW_93_in_assignmentOperator4221);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal444_tree = (Object) adaptor
							.create(string_literal444);
					adaptor.addChild(root_0, string_literal444_tree);
				}

			}
				break;
			case 4:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:737:9:
				// '*='
			{
				root_0 = (Object) adaptor.nil();

				string_literal445 = (Token) match(input, 94,
						FOLLOW_94_in_assignmentOperator4231);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal445_tree = (Object) adaptor
							.create(string_literal445);
					adaptor.addChild(root_0, string_literal445_tree);
				}

			}
				break;
			case 5:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:738:9:
				// '/='
			{
				root_0 = (Object) adaptor.nil();

				string_literal446 = (Token) match(input, 95,
						FOLLOW_95_in_assignmentOperator4241);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal446_tree = (Object) adaptor
							.create(string_literal446);
					adaptor.addChild(root_0, string_literal446_tree);
				}

			}
				break;
			case 6:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:739:9:
				// '&='
			{
				root_0 = (Object) adaptor.nil();

				string_literal447 = (Token) match(input, 96,
						FOLLOW_96_in_assignmentOperator4251);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal447_tree = (Object) adaptor
							.create(string_literal447);
					adaptor.addChild(root_0, string_literal447_tree);
				}

			}
				break;
			case 7:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:740:9:
				// '|='
			{
				root_0 = (Object) adaptor.nil();

				string_literal448 = (Token) match(input, 97,
						FOLLOW_97_in_assignmentOperator4261);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal448_tree = (Object) adaptor
							.create(string_literal448);
					adaptor.addChild(root_0, string_literal448_tree);
				}

			}
				break;
			case 8:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:741:9:
				// '^='
			{
				root_0 = (Object) adaptor.nil();

				string_literal449 = (Token) match(input, 98,
						FOLLOW_98_in_assignmentOperator4271);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal449_tree = (Object) adaptor
							.create(string_literal449);
					adaptor.addChild(root_0, string_literal449_tree);
				}

			}
				break;
			case 9:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:742:9:
				// '%='
			{
				root_0 = (Object) adaptor.nil();

				string_literal450 = (Token) match(input, 99,
						FOLLOW_99_in_assignmentOperator4281);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal450_tree = (Object) adaptor
							.create(string_literal450);
					adaptor.addChild(root_0, string_literal450_tree);
				}

			}
				break;
			case 10:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:743:9:
				// ( '<' '<' '=' )=>t1= '<' t2= '<' t3= '=' {...}?
			{
				root_0 = (Object) adaptor.nil();

				t1 = (Token) match(input, 42,
						FOLLOW_42_in_assignmentOperator4302);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					t1_tree = (Object) adaptor.create(t1);
					adaptor.addChild(root_0, t1_tree);
				}
				t2 = (Token) match(input, 42,
						FOLLOW_42_in_assignmentOperator4306);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					t2_tree = (Object) adaptor.create(t2);
					adaptor.addChild(root_0, t2_tree);
				}
				t3 = (Token) match(input, 53,
						FOLLOW_53_in_assignmentOperator4310);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					t3_tree = (Object) adaptor.create(t3);
					adaptor.addChild(root_0, t3_tree);
				}
				if (!((t1.getLine() == t2.getLine()
						&& t1.getCharPositionInLine() + 1 == t2
								.getCharPositionInLine()
						&& t2.getLine() == t3.getLine() && t2
						.getCharPositionInLine() + 1 == t3
						.getCharPositionInLine()))) {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					throw new FailedPredicateException(
							input,
							"assignmentOperator",
							" $t1.getLine() == $t2.getLine() &&\n          $t1.getCharPositionInLine() + 1 == $t2.getCharPositionInLine() && \n          $t2.getLine() == $t3.getLine() && \n          $t2.getCharPositionInLine() + 1 == $t3.getCharPositionInLine() ");
				}

			}
				break;
			case 11:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:748:9:
				// ( '>' '>' '>' '=' )=>t1= '>' t2= '>' t3= '>' t4= '=' {...}?
			{
				root_0 = (Object) adaptor.nil();

				t1 = (Token) match(input, 44,
						FOLLOW_44_in_assignmentOperator4344);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					t1_tree = (Object) adaptor.create(t1);
					adaptor.addChild(root_0, t1_tree);
				}
				t2 = (Token) match(input, 44,
						FOLLOW_44_in_assignmentOperator4348);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					t2_tree = (Object) adaptor.create(t2);
					adaptor.addChild(root_0, t2_tree);
				}
				t3 = (Token) match(input, 44,
						FOLLOW_44_in_assignmentOperator4352);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					t3_tree = (Object) adaptor.create(t3);
					adaptor.addChild(root_0, t3_tree);
				}
				t4 = (Token) match(input, 53,
						FOLLOW_53_in_assignmentOperator4356);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					t4_tree = (Object) adaptor.create(t4);
					adaptor.addChild(root_0, t4_tree);
				}
				if (!((t1.getLine() == t2.getLine()
						&& t1.getCharPositionInLine() + 1 == t2
								.getCharPositionInLine()
						&& t2.getLine() == t3.getLine()
						&& t2.getCharPositionInLine() + 1 == t3
								.getCharPositionInLine()
						&& t3.getLine() == t4.getLine() && t3
						.getCharPositionInLine() + 1 == t4
						.getCharPositionInLine()))) {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					throw new FailedPredicateException(
							input,
							"assignmentOperator",
							" $t1.getLine() == $t2.getLine() && \n          $t1.getCharPositionInLine() + 1 == $t2.getCharPositionInLine() &&\n          $t2.getLine() == $t3.getLine() && \n          $t2.getCharPositionInLine() + 1 == $t3.getCharPositionInLine() &&\n          $t3.getLine() == $t4.getLine() && \n          $t3.getCharPositionInLine() + 1 == $t4.getCharPositionInLine() ");
				}

			}
				break;
			case 12:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:755:9:
				// ( '>' '>' '=' )=>t1= '>' t2= '>' t3= '=' {...}?
			{
				root_0 = (Object) adaptor.nil();

				t1 = (Token) match(input, 44,
						FOLLOW_44_in_assignmentOperator4387);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					t1_tree = (Object) adaptor.create(t1);
					adaptor.addChild(root_0, t1_tree);
				}
				t2 = (Token) match(input, 44,
						FOLLOW_44_in_assignmentOperator4391);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					t2_tree = (Object) adaptor.create(t2);
					adaptor.addChild(root_0, t2_tree);
				}
				t3 = (Token) match(input, 53,
						FOLLOW_53_in_assignmentOperator4395);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					t3_tree = (Object) adaptor.create(t3);
					adaptor.addChild(root_0, t3_tree);
				}
				if (!((t1.getLine() == t2.getLine()
						&& t1.getCharPositionInLine() + 1 == t2
								.getCharPositionInLine()
						&& t2.getLine() == t3.getLine() && t2
						.getCharPositionInLine() + 1 == t3
						.getCharPositionInLine()))) {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					throw new FailedPredicateException(
							input,
							"assignmentOperator",
							" $t1.getLine() == $t2.getLine() && \n          $t1.getCharPositionInLine() + 1 == $t2.getCharPositionInLine() && \n          $t2.getLine() == $t3.getLine() && \n          $t2.getCharPositionInLine() + 1 == $t3.getCharPositionInLine() ");
				}

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 106, assignmentOperator_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "assignmentOperator"

	public static class conditionalExpression_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "conditionalExpression"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:762:1:
	// conditionalExpression : conditionalOrExpression ( '?' expression ':'
	// expression )? ;
	public final JavaParser.conditionalExpression_return conditionalExpression()
			throws RecognitionException {
		JavaParser.conditionalExpression_return retval = new JavaParser.conditionalExpression_return();
		retval.start = input.LT(1);
		int conditionalExpression_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal452 = null;
		Token char_literal454 = null;
		JavaParser.conditionalOrExpression_return conditionalOrExpression451 = null;

		JavaParser.expression_return expression453 = null;

		JavaParser.expression_return expression455 = null;

		Object char_literal452_tree = null;
		Object char_literal454_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 107)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:763:5:
			// ( conditionalOrExpression ( '?' expression ':' expression )? )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:763:9:
			// conditionalOrExpression ( '?' expression ':' expression )?
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_conditionalOrExpression_in_conditionalExpression4424);
				conditionalOrExpression451 = conditionalOrExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, conditionalOrExpression451
							.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:763:33:
				// ( '?' expression ':' expression )?
				int alt128 = 2;
				int LA128_0 = input.LA(1);

				if ((LA128_0 == 66)) {
					alt128 = 1;
				}
				switch (alt128) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:763:35:
					// '?' expression ':' expression
				{
					char_literal452 = (Token) match(input, 66,
							FOLLOW_66_in_conditionalExpression4428);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						char_literal452_tree = (Object) adaptor
								.create(char_literal452);
						adaptor.addChild(root_0, char_literal452_tree);
					}
					pushFollow(FOLLOW_expression_in_conditionalExpression4430);
					expression453 = expression();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, expression453.getTree());
					char_literal454 = (Token) match(input, 77,
							FOLLOW_77_in_conditionalExpression4432);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						char_literal454_tree = (Object) adaptor
								.create(char_literal454);
						adaptor.addChild(root_0, char_literal454_tree);
					}
					pushFollow(FOLLOW_expression_in_conditionalExpression4434);
					expression455 = expression();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, expression455.getTree());

				}
					break;

				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 107, conditionalExpression_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "conditionalExpression"

	public static class conditionalOrExpression_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "conditionalOrExpression"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:766:1:
	// conditionalOrExpression : conditionalAndExpression ( '||'
	// conditionalAndExpression )* ;
	public final JavaParser.conditionalOrExpression_return conditionalOrExpression()
			throws RecognitionException {
		JavaParser.conditionalOrExpression_return retval = new JavaParser.conditionalOrExpression_return();
		retval.start = input.LT(1);
		int conditionalOrExpression_StartIndex = input.index();
		Object root_0 = null;

		Token string_literal457 = null;
		JavaParser.conditionalAndExpression_return conditionalAndExpression456 = null;

		JavaParser.conditionalAndExpression_return conditionalAndExpression458 = null;

		Object string_literal457_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 108)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:767:5:
			// ( conditionalAndExpression ( '||' conditionalAndExpression )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:767:9:
			// conditionalAndExpression ( '||' conditionalAndExpression )*
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_conditionalAndExpression_in_conditionalOrExpression4456);
				conditionalAndExpression456 = conditionalAndExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, conditionalAndExpression456
							.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:767:34:
				// ( '||' conditionalAndExpression )*
				loop129: do {
					int alt129 = 2;
					int LA129_0 = input.LA(1);

					if ((LA129_0 == 100)) {
						alt129 = 1;
					}

					switch (alt129) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:767:36:
						// '||' conditionalAndExpression
					{
						string_literal457 = (Token) match(input, 100,
								FOLLOW_100_in_conditionalOrExpression4460);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							string_literal457_tree = (Object) adaptor
									.create(string_literal457);
							adaptor.addChild(root_0, string_literal457_tree);
						}
						pushFollow(FOLLOW_conditionalAndExpression_in_conditionalOrExpression4462);
						conditionalAndExpression458 = conditionalAndExpression();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0,
									conditionalAndExpression458.getTree());

					}
						break;

					default:
						break loop129;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 108, conditionalOrExpression_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "conditionalOrExpression"

	public static class conditionalAndExpression_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "conditionalAndExpression"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:770:1:
	// conditionalAndExpression : inclusiveOrExpression ( '&&'
	// inclusiveOrExpression )* ;
	public final JavaParser.conditionalAndExpression_return conditionalAndExpression()
			throws RecognitionException {
		JavaParser.conditionalAndExpression_return retval = new JavaParser.conditionalAndExpression_return();
		retval.start = input.LT(1);
		int conditionalAndExpression_StartIndex = input.index();
		Object root_0 = null;

		Token string_literal460 = null;
		JavaParser.inclusiveOrExpression_return inclusiveOrExpression459 = null;

		JavaParser.inclusiveOrExpression_return inclusiveOrExpression461 = null;

		Object string_literal460_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 109)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:771:5:
			// ( inclusiveOrExpression ( '&&' inclusiveOrExpression )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:771:9:
			// inclusiveOrExpression ( '&&' inclusiveOrExpression )*
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_inclusiveOrExpression_in_conditionalAndExpression4484);
				inclusiveOrExpression459 = inclusiveOrExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor
							.addChild(root_0, inclusiveOrExpression459
									.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:771:31:
				// ( '&&' inclusiveOrExpression )*
				loop130: do {
					int alt130 = 2;
					int LA130_0 = input.LA(1);

					if ((LA130_0 == 101)) {
						alt130 = 1;
					}

					switch (alt130) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:771:33:
						// '&&' inclusiveOrExpression
					{
						string_literal460 = (Token) match(input, 101,
								FOLLOW_101_in_conditionalAndExpression4488);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							string_literal460_tree = (Object) adaptor
									.create(string_literal460);
							adaptor.addChild(root_0, string_literal460_tree);
						}
						pushFollow(FOLLOW_inclusiveOrExpression_in_conditionalAndExpression4490);
						inclusiveOrExpression461 = inclusiveOrExpression();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, inclusiveOrExpression461
									.getTree());

					}
						break;

					default:
						break loop130;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 109, conditionalAndExpression_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "conditionalAndExpression"

	public static class inclusiveOrExpression_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "inclusiveOrExpression"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:774:1:
	// inclusiveOrExpression : exclusiveOrExpression ( '|' exclusiveOrExpression
	// )* ;
	public final JavaParser.inclusiveOrExpression_return inclusiveOrExpression()
			throws RecognitionException {
		JavaParser.inclusiveOrExpression_return retval = new JavaParser.inclusiveOrExpression_return();
		retval.start = input.LT(1);
		int inclusiveOrExpression_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal463 = null;
		JavaParser.exclusiveOrExpression_return exclusiveOrExpression462 = null;

		JavaParser.exclusiveOrExpression_return exclusiveOrExpression464 = null;

		Object char_literal463_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 110)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:775:5:
			// ( exclusiveOrExpression ( '|' exclusiveOrExpression )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:775:9:
			// exclusiveOrExpression ( '|' exclusiveOrExpression )*
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression4512);
				exclusiveOrExpression462 = exclusiveOrExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor
							.addChild(root_0, exclusiveOrExpression462
									.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:775:31:
				// ( '|' exclusiveOrExpression )*
				loop131: do {
					int alt131 = 2;
					int LA131_0 = input.LA(1);

					if ((LA131_0 == 102)) {
						alt131 = 1;
					}

					switch (alt131) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:775:33:
						// '|' exclusiveOrExpression
					{
						char_literal463 = (Token) match(input, 102,
								FOLLOW_102_in_inclusiveOrExpression4516);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal463_tree = (Object) adaptor
									.create(char_literal463);
							adaptor.addChild(root_0, char_literal463_tree);
						}
						pushFollow(FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression4518);
						exclusiveOrExpression464 = exclusiveOrExpression();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, exclusiveOrExpression464
									.getTree());

					}
						break;

					default:
						break loop131;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 110, inclusiveOrExpression_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "inclusiveOrExpression"

	public static class exclusiveOrExpression_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "exclusiveOrExpression"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:778:1:
	// exclusiveOrExpression : andExpression ( '^' andExpression )* ;
	public final JavaParser.exclusiveOrExpression_return exclusiveOrExpression()
			throws RecognitionException {
		JavaParser.exclusiveOrExpression_return retval = new JavaParser.exclusiveOrExpression_return();
		retval.start = input.LT(1);
		int exclusiveOrExpression_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal466 = null;
		JavaParser.andExpression_return andExpression465 = null;

		JavaParser.andExpression_return andExpression467 = null;

		Object char_literal466_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 111)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:779:5:
			// ( andExpression ( '^' andExpression )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:779:9:
			// andExpression ( '^' andExpression )*
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_andExpression_in_exclusiveOrExpression4540);
				andExpression465 = andExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, andExpression465.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:779:23:
				// ( '^' andExpression )*
				loop132: do {
					int alt132 = 2;
					int LA132_0 = input.LA(1);

					if ((LA132_0 == 103)) {
						alt132 = 1;
					}

					switch (alt132) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:779:25:
						// '^' andExpression
					{
						char_literal466 = (Token) match(input, 103,
								FOLLOW_103_in_exclusiveOrExpression4544);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal466_tree = (Object) adaptor
									.create(char_literal466);
							adaptor.addChild(root_0, char_literal466_tree);
						}
						pushFollow(FOLLOW_andExpression_in_exclusiveOrExpression4546);
						andExpression467 = andExpression();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor
									.addChild(root_0, andExpression467
											.getTree());

					}
						break;

					default:
						break loop132;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 111, exclusiveOrExpression_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "exclusiveOrExpression"

	public static class andExpression_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "andExpression"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:782:1:
	// andExpression : equalityExpression ( '&' equalityExpression )* ;
	public final JavaParser.andExpression_return andExpression()
			throws RecognitionException {
		JavaParser.andExpression_return retval = new JavaParser.andExpression_return();
		retval.start = input.LT(1);
		int andExpression_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal469 = null;
		JavaParser.equalityExpression_return equalityExpression468 = null;

		JavaParser.equalityExpression_return equalityExpression470 = null;

		Object char_literal469_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 112)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:783:5:
			// ( equalityExpression ( '&' equalityExpression )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:783:9:
			// equalityExpression ( '&' equalityExpression )*
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_equalityExpression_in_andExpression4568);
				equalityExpression468 = equalityExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, equalityExpression468.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:783:28:
				// ( '&' equalityExpression )*
				loop133: do {
					int alt133 = 2;
					int LA133_0 = input.LA(1);

					if ((LA133_0 == 45)) {
						alt133 = 1;
					}

					switch (alt133) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:783:30:
						// '&' equalityExpression
					{
						char_literal469 = (Token) match(input, 45,
								FOLLOW_45_in_andExpression4572);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal469_tree = (Object) adaptor
									.create(char_literal469);
							adaptor.addChild(root_0, char_literal469_tree);
						}
						pushFollow(FOLLOW_equalityExpression_in_andExpression4574);
						equalityExpression470 = equalityExpression();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, equalityExpression470
									.getTree());

					}
						break;

					default:
						break loop133;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 112, andExpression_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "andExpression"

	public static class equalityExpression_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "equalityExpression"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:786:1:
	// equalityExpression : instanceOfExpression ( ( '==' | '!=' )
	// instanceOfExpression )* ;
	public final JavaParser.equalityExpression_return equalityExpression()
			throws RecognitionException {
		JavaParser.equalityExpression_return retval = new JavaParser.equalityExpression_return();
		retval.start = input.LT(1);
		int equalityExpression_StartIndex = input.index();
		Object root_0 = null;

		Token set472 = null;
		JavaParser.instanceOfExpression_return instanceOfExpression471 = null;

		JavaParser.instanceOfExpression_return instanceOfExpression473 = null;

		Object set472_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 113)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:787:5:
			// ( instanceOfExpression ( ( '==' | '!=' ) instanceOfExpression )*
			// )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:787:9:
			// instanceOfExpression ( ( '==' | '!=' ) instanceOfExpression )*
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_instanceOfExpression_in_equalityExpression4596);
				instanceOfExpression471 = instanceOfExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, instanceOfExpression471.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:787:30:
				// ( ( '==' | '!=' ) instanceOfExpression )*
				loop134: do {
					int alt134 = 2;
					int LA134_0 = input.LA(1);

					if (((LA134_0 >= 104 && LA134_0 <= 105))) {
						alt134 = 1;
					}

					switch (alt134) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:787:32:
						// ( '==' | '!=' ) instanceOfExpression
					{
						set472 = (Token) input.LT(1);
						if ((input.LA(1) >= 104 && input.LA(1) <= 105)) {
							input.consume();
							if (state.backtracking == 0)
								adaptor.addChild(root_0, (Object) adaptor
										.create(set472));
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

						pushFollow(FOLLOW_instanceOfExpression_in_equalityExpression4608);
						instanceOfExpression473 = instanceOfExpression();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, instanceOfExpression473
									.getTree());

					}
						break;

					default:
						break loop134;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 113, equalityExpression_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "equalityExpression"

	public static class instanceOfExpression_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "instanceOfExpression"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:790:1:
	// instanceOfExpression : relationalExpression ( 'instanceof' type )? ;
	public final JavaParser.instanceOfExpression_return instanceOfExpression()
			throws RecognitionException {
		JavaParser.instanceOfExpression_return retval = new JavaParser.instanceOfExpression_return();
		retval.start = input.LT(1);
		int instanceOfExpression_StartIndex = input.index();
		Object root_0 = null;

		Token string_literal475 = null;
		JavaParser.relationalExpression_return relationalExpression474 = null;

		JavaParser.type_return type476 = null;

		Object string_literal475_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 114)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:791:5:
			// ( relationalExpression ( 'instanceof' type )? )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:791:9:
			// relationalExpression ( 'instanceof' type )?
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_relationalExpression_in_instanceOfExpression4630);
				relationalExpression474 = relationalExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, relationalExpression474.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:791:30:
				// ( 'instanceof' type )?
				int alt135 = 2;
				int LA135_0 = input.LA(1);

				if ((LA135_0 == 106)) {
					alt135 = 1;
				}
				switch (alt135) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:791:31:
					// 'instanceof' type
				{
					string_literal475 = (Token) match(input, 106,
							FOLLOW_106_in_instanceOfExpression4633);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						string_literal475_tree = (Object) adaptor
								.create(string_literal475);
						adaptor.addChild(root_0, string_literal475_tree);
					}
					pushFollow(FOLLOW_type_in_instanceOfExpression4635);
					type476 = type();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, type476.getTree());

				}
					break;

				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 114, instanceOfExpression_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "instanceOfExpression"

	public static class relationalExpression_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "relationalExpression"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:794:1:
	// relationalExpression : shiftExpression ( relationalOp shiftExpression )*
	// ;
	public final JavaParser.relationalExpression_return relationalExpression()
			throws RecognitionException {
		JavaParser.relationalExpression_return retval = new JavaParser.relationalExpression_return();
		retval.start = input.LT(1);
		int relationalExpression_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.shiftExpression_return shiftExpression477 = null;

		JavaParser.relationalOp_return relationalOp478 = null;

		JavaParser.shiftExpression_return shiftExpression479 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 115)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:795:5:
			// ( shiftExpression ( relationalOp shiftExpression )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:795:9:
			// shiftExpression ( relationalOp shiftExpression )*
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_shiftExpression_in_relationalExpression4656);
				shiftExpression477 = shiftExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, shiftExpression477.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:795:25:
				// ( relationalOp shiftExpression )*
				loop136: do {
					int alt136 = 2;
					int LA136_0 = input.LA(1);

					if ((LA136_0 == 42)) {
						int LA136_2 = input.LA(2);

						if ((LA136_2 == Identifier
								|| (LA136_2 >= FloatingPointLiteral && LA136_2 <= DecimalLiteral)
								|| LA136_2 == 49 || LA136_2 == 53
								|| (LA136_2 >= 58 && LA136_2 <= 65)
								|| (LA136_2 >= 67 && LA136_2 <= 68)
								|| (LA136_2 >= 71 && LA136_2 <= 74)
								|| (LA136_2 >= 107 && LA136_2 <= 108) || (LA136_2 >= 111 && LA136_2 <= 115))) {
							alt136 = 1;
						}

					} else if ((LA136_0 == 44)) {
						int LA136_3 = input.LA(2);

						if ((LA136_3 == Identifier
								|| (LA136_3 >= FloatingPointLiteral && LA136_3 <= DecimalLiteral)
								|| LA136_3 == 49 || LA136_3 == 53
								|| (LA136_3 >= 58 && LA136_3 <= 65)
								|| (LA136_3 >= 67 && LA136_3 <= 68)
								|| (LA136_3 >= 71 && LA136_3 <= 74)
								|| (LA136_3 >= 107 && LA136_3 <= 108) || (LA136_3 >= 111 && LA136_3 <= 115))) {
							alt136 = 1;
						}

					}

					switch (alt136) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:795:27:
						// relationalOp shiftExpression
					{
						pushFollow(FOLLOW_relationalOp_in_relationalExpression4660);
						relationalOp478 = relationalOp();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, relationalOp478.getTree());
						pushFollow(FOLLOW_shiftExpression_in_relationalExpression4662);
						shiftExpression479 = shiftExpression();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, shiftExpression479
									.getTree());

					}
						break;

					default:
						break loop136;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 115, relationalExpression_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "relationalExpression"

	public static class relationalOp_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "relationalOp"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:798:1:
	// relationalOp : ( ( '<' '=' )=>t1= '<' t2= '=' {...}? | ( '>' '=' )=>t1=
	// '>' t2= '=' {...}? | '<' | '>' );
	public final JavaParser.relationalOp_return relationalOp()
			throws RecognitionException {
		JavaParser.relationalOp_return retval = new JavaParser.relationalOp_return();
		retval.start = input.LT(1);
		int relationalOp_StartIndex = input.index();
		Object root_0 = null;

		Token t1 = null;
		Token t2 = null;
		Token char_literal480 = null;
		Token char_literal481 = null;

		Object t1_tree = null;
		Object t2_tree = null;
		Object char_literal480_tree = null;
		Object char_literal481_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 116)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:799:5:
			// ( ( '<' '=' )=>t1= '<' t2= '=' {...}? | ( '>' '=' )=>t1= '>' t2=
			// '=' {...}? | '<' | '>' )
			int alt137 = 4;
			int LA137_0 = input.LA(1);

			if ((LA137_0 == 42)) {
				int LA137_1 = input.LA(2);

				if ((LA137_1 == 53) && (synpred211_Java())) {
					alt137 = 1;
				} else if ((LA137_1 == Identifier
						|| (LA137_1 >= FloatingPointLiteral && LA137_1 <= DecimalLiteral)
						|| LA137_1 == 49 || (LA137_1 >= 58 && LA137_1 <= 65)
						|| (LA137_1 >= 67 && LA137_1 <= 68)
						|| (LA137_1 >= 71 && LA137_1 <= 74)
						|| (LA137_1 >= 107 && LA137_1 <= 108) || (LA137_1 >= 111 && LA137_1 <= 115))) {
					alt137 = 3;
				} else {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					NoViableAltException nvae = new NoViableAltException("",
							137, 1, input);

					throw nvae;
				}
			} else if ((LA137_0 == 44)) {
				int LA137_2 = input.LA(2);

				if ((LA137_2 == 53) && (synpred212_Java())) {
					alt137 = 2;
				} else if ((LA137_2 == Identifier
						|| (LA137_2 >= FloatingPointLiteral && LA137_2 <= DecimalLiteral)
						|| LA137_2 == 49 || (LA137_2 >= 58 && LA137_2 <= 65)
						|| (LA137_2 >= 67 && LA137_2 <= 68)
						|| (LA137_2 >= 71 && LA137_2 <= 74)
						|| (LA137_2 >= 107 && LA137_2 <= 108) || (LA137_2 >= 111 && LA137_2 <= 115))) {
					alt137 = 4;
				} else {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					NoViableAltException nvae = new NoViableAltException("",
							137, 2, input);

					throw nvae;
				}
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 137,
						0, input);

				throw nvae;
			}
			switch (alt137) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:799:9:
				// ( '<' '=' )=>t1= '<' t2= '=' {...}?
			{
				root_0 = (Object) adaptor.nil();

				t1 = (Token) match(input, 42, FOLLOW_42_in_relationalOp4697);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					t1_tree = (Object) adaptor.create(t1);
					adaptor.addChild(root_0, t1_tree);
				}
				t2 = (Token) match(input, 53, FOLLOW_53_in_relationalOp4701);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					t2_tree = (Object) adaptor.create(t2);
					adaptor.addChild(root_0, t2_tree);
				}
				if (!((t1.getLine() == t2.getLine() && t1
						.getCharPositionInLine() + 1 == t2
						.getCharPositionInLine()))) {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					throw new FailedPredicateException(
							input,
							"relationalOp",
							" $t1.getLine() == $t2.getLine() && \n          $t1.getCharPositionInLine() + 1 == $t2.getCharPositionInLine() ");
				}

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:802:9:
				// ( '>' '=' )=>t1= '>' t2= '=' {...}?
			{
				root_0 = (Object) adaptor.nil();

				t1 = (Token) match(input, 44, FOLLOW_44_in_relationalOp4731);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					t1_tree = (Object) adaptor.create(t1);
					adaptor.addChild(root_0, t1_tree);
				}
				t2 = (Token) match(input, 53, FOLLOW_53_in_relationalOp4735);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					t2_tree = (Object) adaptor.create(t2);
					adaptor.addChild(root_0, t2_tree);
				}
				if (!((t1.getLine() == t2.getLine() && t1
						.getCharPositionInLine() + 1 == t2
						.getCharPositionInLine()))) {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					throw new FailedPredicateException(
							input,
							"relationalOp",
							" $t1.getLine() == $t2.getLine() && \n          $t1.getCharPositionInLine() + 1 == $t2.getCharPositionInLine() ");
				}

			}
				break;
			case 3:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:805:9:
				// '<'
			{
				root_0 = (Object) adaptor.nil();

				char_literal480 = (Token) match(input, 42,
						FOLLOW_42_in_relationalOp4756);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal480_tree = (Object) adaptor
							.create(char_literal480);
					adaptor.addChild(root_0, char_literal480_tree);
				}

			}
				break;
			case 4:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:806:9:
				// '>'
			{
				root_0 = (Object) adaptor.nil();

				char_literal481 = (Token) match(input, 44,
						FOLLOW_44_in_relationalOp4767);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal481_tree = (Object) adaptor
							.create(char_literal481);
					adaptor.addChild(root_0, char_literal481_tree);
				}

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 116, relationalOp_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "relationalOp"

	public static class shiftExpression_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "shiftExpression"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:809:1:
	// shiftExpression : additiveExpression ( shiftOp additiveExpression )* ;
	public final JavaParser.shiftExpression_return shiftExpression()
			throws RecognitionException {
		JavaParser.shiftExpression_return retval = new JavaParser.shiftExpression_return();
		retval.start = input.LT(1);
		int shiftExpression_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.additiveExpression_return additiveExpression482 = null;

		JavaParser.shiftOp_return shiftOp483 = null;

		JavaParser.additiveExpression_return additiveExpression484 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 117)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:810:5:
			// ( additiveExpression ( shiftOp additiveExpression )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:810:9:
			// additiveExpression ( shiftOp additiveExpression )*
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_additiveExpression_in_shiftExpression4787);
				additiveExpression482 = additiveExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, additiveExpression482.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:810:28:
				// ( shiftOp additiveExpression )*
				loop138: do {
					int alt138 = 2;
					int LA138_0 = input.LA(1);

					if ((LA138_0 == 42)) {
						int LA138_1 = input.LA(2);

						if ((LA138_1 == 42)) {
							int LA138_4 = input.LA(3);

							if ((LA138_4 == Identifier
									|| (LA138_4 >= FloatingPointLiteral && LA138_4 <= DecimalLiteral)
									|| LA138_4 == 49
									|| (LA138_4 >= 58 && LA138_4 <= 65)
									|| (LA138_4 >= 67 && LA138_4 <= 68)
									|| (LA138_4 >= 71 && LA138_4 <= 74)
									|| (LA138_4 >= 107 && LA138_4 <= 108) || (LA138_4 >= 111 && LA138_4 <= 115))) {
								alt138 = 1;
							}

						}

					} else if ((LA138_0 == 44)) {
						int LA138_2 = input.LA(2);

						if ((LA138_2 == 44)) {
							int LA138_5 = input.LA(3);

							if ((LA138_5 == 44)) {
								int LA138_7 = input.LA(4);

								if ((LA138_7 == Identifier
										|| (LA138_7 >= FloatingPointLiteral && LA138_7 <= DecimalLiteral)
										|| LA138_7 == 49
										|| (LA138_7 >= 58 && LA138_7 <= 65)
										|| (LA138_7 >= 67 && LA138_7 <= 68)
										|| (LA138_7 >= 71 && LA138_7 <= 74)
										|| (LA138_7 >= 107 && LA138_7 <= 108) || (LA138_7 >= 111 && LA138_7 <= 115))) {
									alt138 = 1;
								}

							} else if ((LA138_5 == Identifier
									|| (LA138_5 >= FloatingPointLiteral && LA138_5 <= DecimalLiteral)
									|| LA138_5 == 49
									|| (LA138_5 >= 58 && LA138_5 <= 65)
									|| (LA138_5 >= 67 && LA138_5 <= 68)
									|| (LA138_5 >= 71 && LA138_5 <= 74)
									|| (LA138_5 >= 107 && LA138_5 <= 108) || (LA138_5 >= 111 && LA138_5 <= 115))) {
								alt138 = 1;
							}

						}

					}

					switch (alt138) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:810:30:
						// shiftOp additiveExpression
					{
						pushFollow(FOLLOW_shiftOp_in_shiftExpression4791);
						shiftOp483 = shiftOp();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, shiftOp483.getTree());
						pushFollow(FOLLOW_additiveExpression_in_shiftExpression4793);
						additiveExpression484 = additiveExpression();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, additiveExpression484
									.getTree());

					}
						break;

					default:
						break loop138;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 117, shiftExpression_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "shiftExpression"

	public static class shiftOp_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "shiftOp"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:813:1:
	// shiftOp : ( ( '<' '<' )=>t1= '<' t2= '<' {...}? | ( '>' '>' '>' )=>t1=
	// '>' t2= '>' t3= '>' {...}? | ( '>' '>' )=>t1= '>' t2= '>' {...}?);
	public final JavaParser.shiftOp_return shiftOp()
			throws RecognitionException {
		JavaParser.shiftOp_return retval = new JavaParser.shiftOp_return();
		retval.start = input.LT(1);
		int shiftOp_StartIndex = input.index();
		Object root_0 = null;

		Token t1 = null;
		Token t2 = null;
		Token t3 = null;

		Object t1_tree = null;
		Object t2_tree = null;
		Object t3_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 118)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:814:5:
			// ( ( '<' '<' )=>t1= '<' t2= '<' {...}? | ( '>' '>' '>' )=>t1= '>'
			// t2= '>' t3= '>' {...}? | ( '>' '>' )=>t1= '>' t2= '>' {...}?)
			int alt139 = 3;
			alt139 = dfa139.predict(input);
			switch (alt139) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:814:9:
				// ( '<' '<' )=>t1= '<' t2= '<' {...}?
			{
				root_0 = (Object) adaptor.nil();

				t1 = (Token) match(input, 42, FOLLOW_42_in_shiftOp4824);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					t1_tree = (Object) adaptor.create(t1);
					adaptor.addChild(root_0, t1_tree);
				}
				t2 = (Token) match(input, 42, FOLLOW_42_in_shiftOp4828);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					t2_tree = (Object) adaptor.create(t2);
					adaptor.addChild(root_0, t2_tree);
				}
				if (!((t1.getLine() == t2.getLine() && t1
						.getCharPositionInLine() + 1 == t2
						.getCharPositionInLine()))) {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					throw new FailedPredicateException(
							input,
							"shiftOp",
							" $t1.getLine() == $t2.getLine() && \n          $t1.getCharPositionInLine() + 1 == $t2.getCharPositionInLine() ");
				}

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:817:9:
				// ( '>' '>' '>' )=>t1= '>' t2= '>' t3= '>' {...}?
			{
				root_0 = (Object) adaptor.nil();

				t1 = (Token) match(input, 44, FOLLOW_44_in_shiftOp4860);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					t1_tree = (Object) adaptor.create(t1);
					adaptor.addChild(root_0, t1_tree);
				}
				t2 = (Token) match(input, 44, FOLLOW_44_in_shiftOp4864);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					t2_tree = (Object) adaptor.create(t2);
					adaptor.addChild(root_0, t2_tree);
				}
				t3 = (Token) match(input, 44, FOLLOW_44_in_shiftOp4868);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					t3_tree = (Object) adaptor.create(t3);
					adaptor.addChild(root_0, t3_tree);
				}
				if (!((t1.getLine() == t2.getLine()
						&& t1.getCharPositionInLine() + 1 == t2
								.getCharPositionInLine()
						&& t2.getLine() == t3.getLine() && t2
						.getCharPositionInLine() + 1 == t3
						.getCharPositionInLine()))) {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					throw new FailedPredicateException(
							input,
							"shiftOp",
							" $t1.getLine() == $t2.getLine() && \n          $t1.getCharPositionInLine() + 1 == $t2.getCharPositionInLine() &&\n          $t2.getLine() == $t3.getLine() && \n          $t2.getCharPositionInLine() + 1 == $t3.getCharPositionInLine() ");
				}

			}
				break;
			case 3:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:822:9:
				// ( '>' '>' )=>t1= '>' t2= '>' {...}?
			{
				root_0 = (Object) adaptor.nil();

				t1 = (Token) match(input, 44, FOLLOW_44_in_shiftOp4898);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					t1_tree = (Object) adaptor.create(t1);
					adaptor.addChild(root_0, t1_tree);
				}
				t2 = (Token) match(input, 44, FOLLOW_44_in_shiftOp4902);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					t2_tree = (Object) adaptor.create(t2);
					adaptor.addChild(root_0, t2_tree);
				}
				if (!((t1.getLine() == t2.getLine() && t1
						.getCharPositionInLine() + 1 == t2
						.getCharPositionInLine()))) {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					throw new FailedPredicateException(
							input,
							"shiftOp",
							" $t1.getLine() == $t2.getLine() && \n          $t1.getCharPositionInLine() + 1 == $t2.getCharPositionInLine() ");
				}

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 118, shiftOp_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "shiftOp"

	public static class additiveExpression_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "additiveExpression"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:828:1:
	// additiveExpression : multiplicativeExpression ( ( '+' | '-' )
	// multiplicativeExpression )* ;
	public final JavaParser.additiveExpression_return additiveExpression()
			throws RecognitionException {
		JavaParser.additiveExpression_return retval = new JavaParser.additiveExpression_return();
		retval.start = input.LT(1);
		int additiveExpression_StartIndex = input.index();
		Object root_0 = null;

		Token set486 = null;
		JavaParser.multiplicativeExpression_return multiplicativeExpression485 = null;

		JavaParser.multiplicativeExpression_return multiplicativeExpression487 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 119)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:829:5:
			// ( multiplicativeExpression ( ( '+' | '-' )
			// multiplicativeExpression )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:829:9:
			// multiplicativeExpression ( ( '+' | '-' ) multiplicativeExpression
			// )*
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression4932);
				multiplicativeExpression485 = multiplicativeExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, multiplicativeExpression485
							.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:829:34:
				// ( ( '+' | '-' ) multiplicativeExpression )*
				loop140: do {
					int alt140 = 2;
					int LA140_0 = input.LA(1);

					if (((LA140_0 >= 107 && LA140_0 <= 108))) {
						alt140 = 1;
					}

					switch (alt140) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:829:36:
						// ( '+' | '-' ) multiplicativeExpression
					{
						set486 = (Token) input.LT(1);
						if ((input.LA(1) >= 107 && input.LA(1) <= 108)) {
							input.consume();
							if (state.backtracking == 0)
								adaptor.addChild(root_0, (Object) adaptor
										.create(set486));
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

						pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression4944);
						multiplicativeExpression487 = multiplicativeExpression();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0,
									multiplicativeExpression487.getTree());

					}
						break;

					default:
						break loop140;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 119, additiveExpression_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "additiveExpression"

	public static class multiplicativeExpression_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "multiplicativeExpression"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:832:1:
	// multiplicativeExpression : unaryExpression ( ( '*' | '/' | '%' )
	// unaryExpression )* ;
	public final JavaParser.multiplicativeExpression_return multiplicativeExpression()
			throws RecognitionException {
		JavaParser.multiplicativeExpression_return retval = new JavaParser.multiplicativeExpression_return();
		retval.start = input.LT(1);
		int multiplicativeExpression_StartIndex = input.index();
		Object root_0 = null;

		Token set489 = null;
		JavaParser.unaryExpression_return unaryExpression488 = null;

		JavaParser.unaryExpression_return unaryExpression490 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 120)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:833:5:
			// ( unaryExpression ( ( '*' | '/' | '%' ) unaryExpression )* )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:833:9:
			// unaryExpression ( ( '*' | '/' | '%' ) unaryExpression )*
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_unaryExpression_in_multiplicativeExpression4966);
				unaryExpression488 = unaryExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, unaryExpression488.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:833:25:
				// ( ( '*' | '/' | '%' ) unaryExpression )*
				loop141: do {
					int alt141 = 2;
					int LA141_0 = input.LA(1);

					if ((LA141_0 == 32 || (LA141_0 >= 109 && LA141_0 <= 110))) {
						alt141 = 1;
					}

					switch (alt141) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:833:27:
						// ( '*' | '/' | '%' ) unaryExpression
					{
						set489 = (Token) input.LT(1);
						if (input.LA(1) == 32
								|| (input.LA(1) >= 109 && input.LA(1) <= 110)) {
							input.consume();
							if (state.backtracking == 0)
								adaptor.addChild(root_0, (Object) adaptor
										.create(set489));
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

						pushFollow(FOLLOW_unaryExpression_in_multiplicativeExpression4984);
						unaryExpression490 = unaryExpression();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, unaryExpression490
									.getTree());

					}
						break;

					default:
						break loop141;
					}
				} while (true);

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 120, multiplicativeExpression_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "multiplicativeExpression"

	public static class unaryExpression_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "unaryExpression"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:836:1:
	// unaryExpression : ( '+' unaryExpression | '-' unaryExpression | '++'
	// unaryExpression | '--' unaryExpression | unaryExpressionNotPlusMinus );
	public final JavaParser.unaryExpression_return unaryExpression()
			throws RecognitionException {
		JavaParser.unaryExpression_return retval = new JavaParser.unaryExpression_return();
		retval.start = input.LT(1);
		int unaryExpression_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal491 = null;
		Token char_literal493 = null;
		Token string_literal495 = null;
		Token string_literal497 = null;
		JavaParser.unaryExpression_return unaryExpression492 = null;

		JavaParser.unaryExpression_return unaryExpression494 = null;

		JavaParser.unaryExpression_return unaryExpression496 = null;

		JavaParser.unaryExpression_return unaryExpression498 = null;

		JavaParser.unaryExpressionNotPlusMinus_return unaryExpressionNotPlusMinus499 = null;

		Object char_literal491_tree = null;
		Object char_literal493_tree = null;
		Object string_literal495_tree = null;
		Object string_literal497_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 121)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:837:5:
			// ( '+' unaryExpression | '-' unaryExpression | '++'
			// unaryExpression | '--' unaryExpression |
			// unaryExpressionNotPlusMinus )
			int alt142 = 5;
			switch (input.LA(1)) {
			case 107: {
				alt142 = 1;
			}
				break;
			case 108: {
				alt142 = 2;
			}
				break;
			case 111: {
				alt142 = 3;
			}
				break;
			case 112: {
				alt142 = 4;
			}
				break;
			case Identifier:
			case FloatingPointLiteral:
			case CharacterLiteral:
			case StringLiteral:
			case HexLiteral:
			case OctalLiteral:
			case DecimalLiteral:
			case 49:
			case 58:
			case 59:
			case 60:
			case 61:
			case 62:
			case 63:
			case 64:
			case 65:
			case 67:
			case 68:
			case 71:
			case 72:
			case 73:
			case 74:
			case 113:
			case 114:
			case 115: {
				alt142 = 5;
			}
				break;
			default:
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 142,
						0, input);

				throw nvae;
			}

			switch (alt142) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:837:9:
				// '+' unaryExpression
			{
				root_0 = (Object) adaptor.nil();

				char_literal491 = (Token) match(input, 107,
						FOLLOW_107_in_unaryExpression5010);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal491_tree = (Object) adaptor
							.create(char_literal491);
					adaptor.addChild(root_0, char_literal491_tree);
				}
				pushFollow(FOLLOW_unaryExpression_in_unaryExpression5012);
				unaryExpression492 = unaryExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, unaryExpression492.getTree());

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:838:9:
				// '-' unaryExpression
			{
				root_0 = (Object) adaptor.nil();

				char_literal493 = (Token) match(input, 108,
						FOLLOW_108_in_unaryExpression5022);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal493_tree = (Object) adaptor
							.create(char_literal493);
					adaptor.addChild(root_0, char_literal493_tree);
				}
				pushFollow(FOLLOW_unaryExpression_in_unaryExpression5024);
				unaryExpression494 = unaryExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, unaryExpression494.getTree());

			}
				break;
			case 3:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:839:9:
				// '++' unaryExpression
			{
				root_0 = (Object) adaptor.nil();

				string_literal495 = (Token) match(input, 111,
						FOLLOW_111_in_unaryExpression5034);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal495_tree = (Object) adaptor
							.create(string_literal495);
					adaptor.addChild(root_0, string_literal495_tree);
				}
				pushFollow(FOLLOW_unaryExpression_in_unaryExpression5036);
				unaryExpression496 = unaryExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, unaryExpression496.getTree());

			}
				break;
			case 4:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:840:9:
				// '--' unaryExpression
			{
				root_0 = (Object) adaptor.nil();

				string_literal497 = (Token) match(input, 112,
						FOLLOW_112_in_unaryExpression5046);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal497_tree = (Object) adaptor
							.create(string_literal497);
					adaptor.addChild(root_0, string_literal497_tree);
				}
				pushFollow(FOLLOW_unaryExpression_in_unaryExpression5048);
				unaryExpression498 = unaryExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, unaryExpression498.getTree());

			}
				break;
			case 5:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:841:9:
				// unaryExpressionNotPlusMinus
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_unaryExpressionNotPlusMinus_in_unaryExpression5058);
				unaryExpressionNotPlusMinus499 = unaryExpressionNotPlusMinus();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, unaryExpressionNotPlusMinus499
							.getTree());

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 121, unaryExpression_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "unaryExpression"

	public static class unaryExpressionNotPlusMinus_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "unaryExpressionNotPlusMinus"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:844:1:
	// unaryExpressionNotPlusMinus : ( '~' unaryExpression | '!' unaryExpression
	// | castExpression | primary ( selector )* ( '++' | '--' )? );
	public final JavaParser.unaryExpressionNotPlusMinus_return unaryExpressionNotPlusMinus()
			throws RecognitionException {
		JavaParser.unaryExpressionNotPlusMinus_return retval = new JavaParser.unaryExpressionNotPlusMinus_return();
		retval.start = input.LT(1);
		int unaryExpressionNotPlusMinus_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal500 = null;
		Token char_literal502 = null;
		Token set507 = null;
		JavaParser.unaryExpression_return unaryExpression501 = null;

		JavaParser.unaryExpression_return unaryExpression503 = null;

		JavaParser.castExpression_return castExpression504 = null;

		JavaParser.primary_return primary505 = null;

		JavaParser.selector_return selector506 = null;

		Object char_literal500_tree = null;
		Object char_literal502_tree = null;
		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 122)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:845:5:
			// ( '~' unaryExpression | '!' unaryExpression | castExpression |
			// primary ( selector )* ( '++' | '--' )? )
			int alt145 = 4;
			alt145 = dfa145.predict(input);
			switch (alt145) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:845:9:
				// '~' unaryExpression
			{
				root_0 = (Object) adaptor.nil();

				char_literal500 = (Token) match(input, 113,
						FOLLOW_113_in_unaryExpressionNotPlusMinus5077);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal500_tree = (Object) adaptor
							.create(char_literal500);
					adaptor.addChild(root_0, char_literal500_tree);
				}
				pushFollow(FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus5079);
				unaryExpression501 = unaryExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, unaryExpression501.getTree());

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:846:9:
				// '!' unaryExpression
			{
				root_0 = (Object) adaptor.nil();

				char_literal502 = (Token) match(input, 114,
						FOLLOW_114_in_unaryExpressionNotPlusMinus5089);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal502_tree = (Object) adaptor
							.create(char_literal502);
					adaptor.addChild(root_0, char_literal502_tree);
				}
				pushFollow(FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus5091);
				unaryExpression503 = unaryExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, unaryExpression503.getTree());

			}
				break;
			case 3:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:847:9:
				// castExpression
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_castExpression_in_unaryExpressionNotPlusMinus5101);
				castExpression504 = castExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, castExpression504.getTree());

			}
				break;
			case 4:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:848:9:
				// primary ( selector )* ( '++' | '--' )?
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_primary_in_unaryExpressionNotPlusMinus5111);
				primary505 = primary();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, primary505.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:848:17:
				// ( selector )*
				loop143: do {
					int alt143 = 2;
					int LA143_0 = input.LA(1);

					if ((LA143_0 == 31 || LA143_0 == 50)) {
						alt143 = 1;
					}

					switch (alt143) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
						// selector
					{
						pushFollow(FOLLOW_selector_in_unaryExpressionNotPlusMinus5113);
						selector506 = selector();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, selector506.getTree());

					}
						break;

					default:
						break loop143;
					}
				} while (true);

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:848:27:
				// ( '++' | '--' )?
				int alt144 = 2;
				int LA144_0 = input.LA(1);

				if (((LA144_0 >= 111 && LA144_0 <= 112))) {
					alt144 = 1;
				}
				switch (alt144) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:
				{
					set507 = (Token) input.LT(1);
					if ((input.LA(1) >= 111 && input.LA(1) <= 112)) {
						input.consume();
						if (state.backtracking == 0)
							adaptor.addChild(root_0, (Object) adaptor
									.create(set507));
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

				}
					break;

				}

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 122, unaryExpressionNotPlusMinus_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "unaryExpressionNotPlusMinus"

	public static class castExpression_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "castExpression"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:851:1:
	// castExpression : ( '(' primitiveType ')' unaryExpression | '(' ( type |
	// expression ) ')' unaryExpressionNotPlusMinus );
	public final JavaParser.castExpression_return castExpression()
			throws RecognitionException {
		JavaParser.castExpression_return retval = new JavaParser.castExpression_return();
		retval.start = input.LT(1);
		int castExpression_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal508 = null;
		Token char_literal510 = null;
		Token char_literal512 = null;
		Token char_literal515 = null;
		JavaParser.primitiveType_return primitiveType509 = null;

		JavaParser.unaryExpression_return unaryExpression511 = null;

		JavaParser.type_return type513 = null;

		JavaParser.expression_return expression514 = null;

		JavaParser.unaryExpressionNotPlusMinus_return unaryExpressionNotPlusMinus516 = null;

		Object char_literal508_tree = null;
		Object char_literal510_tree = null;
		Object char_literal512_tree = null;
		Object char_literal515_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 123)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:852:5:
			// ( '(' primitiveType ')' unaryExpression | '(' ( type | expression
			// ) ')' unaryExpressionNotPlusMinus )
			int alt147 = 2;
			int LA147_0 = input.LA(1);

			if ((LA147_0 == 68)) {

				if ((synpred233_Java())) {
					alt147 = 1;
				} else if ((true)) {
					alt147 = 2;
				}
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 147,
						0, input);

				throw nvae;
			}
			switch (alt147) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:852:8:
				// '(' primitiveType ')' unaryExpression
			{
				root_0 = (Object) adaptor.nil();

				char_literal508 = (Token) match(input, 68,
						FOLLOW_68_in_castExpression5139);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal508_tree = (Object) adaptor
							.create(char_literal508);
					adaptor.addChild(root_0, char_literal508_tree);
				}
				pushFollow(FOLLOW_primitiveType_in_castExpression5141);
				primitiveType509 = primitiveType();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, primitiveType509.getTree());
				char_literal510 = (Token) match(input, 69,
						FOLLOW_69_in_castExpression5143);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal510_tree = (Object) adaptor
							.create(char_literal510);
					adaptor.addChild(root_0, char_literal510_tree);
				}
				pushFollow(FOLLOW_unaryExpression_in_castExpression5145);
				unaryExpression511 = unaryExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, unaryExpression511.getTree());

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:853:8:
				// '(' ( type | expression ) ')' unaryExpressionNotPlusMinus
			{
				root_0 = (Object) adaptor.nil();

				char_literal512 = (Token) match(input, 68,
						FOLLOW_68_in_castExpression5154);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal512_tree = (Object) adaptor
							.create(char_literal512);
					adaptor.addChild(root_0, char_literal512_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:853:12:
				// ( type | expression )
				int alt146 = 2;
				alt146 = dfa146.predict(input);
				switch (alt146) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:853:13:
					// type
				{
					pushFollow(FOLLOW_type_in_castExpression5157);
					type513 = type();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, type513.getTree());

				}
					break;
				case 2:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:853:20:
					// expression
				{
					pushFollow(FOLLOW_expression_in_castExpression5161);
					expression514 = expression();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, expression514.getTree());

				}
					break;

				}

				char_literal515 = (Token) match(input, 69,
						FOLLOW_69_in_castExpression5164);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal515_tree = (Object) adaptor
							.create(char_literal515);
					adaptor.addChild(root_0, char_literal515_tree);
				}
				pushFollow(FOLLOW_unaryExpressionNotPlusMinus_in_castExpression5166);
				unaryExpressionNotPlusMinus516 = unaryExpressionNotPlusMinus();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, unaryExpressionNotPlusMinus516
							.getTree());

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 123, castExpression_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "castExpression"

	public static class primary_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "primary"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:856:1:
	// primary : ( parExpression | 'this' ( '.' Identifier )* ( identifierSuffix
	// )? | 'super' superSuffix | literal | 'new' creator | Identifier ( '.'
	// Identifier )* ( identifierSuffix )? | primitiveType ( '[' ']' )* '.'
	// 'class' | 'void' '.' 'class' );
	public final JavaParser.primary_return primary()
			throws RecognitionException {
		JavaParser.primary_return retval = new JavaParser.primary_return();
		retval.start = input.LT(1);
		int primary_StartIndex = input.index();
		Object root_0 = null;

		Token string_literal518 = null;
		Token char_literal519 = null;
		Token Identifier520 = null;
		Token string_literal522 = null;
		Token string_literal525 = null;
		Token Identifier527 = null;
		Token char_literal528 = null;
		Token Identifier529 = null;
		Token char_literal532 = null;
		Token char_literal533 = null;
		Token char_literal534 = null;
		Token string_literal535 = null;
		Token string_literal536 = null;
		Token char_literal537 = null;
		Token string_literal538 = null;
		JavaParser.parExpression_return parExpression517 = null;

		JavaParser.identifierSuffix_return identifierSuffix521 = null;

		JavaParser.superSuffix_return superSuffix523 = null;

		JavaParser.literal_return literal524 = null;

		JavaParser.creator_return creator526 = null;

		JavaParser.identifierSuffix_return identifierSuffix530 = null;

		JavaParser.primitiveType_return primitiveType531 = null;

		Object string_literal518_tree = null;
		Object char_literal519_tree = null;
		Object Identifier520_tree = null;
		Object string_literal522_tree = null;
		Object string_literal525_tree = null;
		Object Identifier527_tree = null;
		Object char_literal528_tree = null;
		Object Identifier529_tree = null;
		Object char_literal532_tree = null;
		Object char_literal533_tree = null;
		Object char_literal534_tree = null;
		Object string_literal535_tree = null;
		Object string_literal536_tree = null;
		Object char_literal537_tree = null;
		Object string_literal538_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 124)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:857:5:
			// ( parExpression | 'this' ( '.' Identifier )* ( identifierSuffix
			// )? | 'super' superSuffix | literal | 'new' creator | Identifier (
			// '.' Identifier )* ( identifierSuffix )? | primitiveType ( '[' ']'
			// )* '.' 'class' | 'void' '.' 'class' )
			int alt153 = 8;
			switch (input.LA(1)) {
			case 68: {
				alt153 = 1;
			}
				break;
			case 71: {
				alt153 = 2;
			}
				break;
			case 67: {
				alt153 = 3;
			}
				break;
			case FloatingPointLiteral:
			case CharacterLiteral:
			case StringLiteral:
			case HexLiteral:
			case OctalLiteral:
			case DecimalLiteral:
			case 72:
			case 73:
			case 74: {
				alt153 = 4;
			}
				break;
			case 115: {
				alt153 = 5;
			}
				break;
			case Identifier: {
				alt153 = 6;
			}
				break;
			case 58:
			case 59:
			case 60:
			case 61:
			case 62:
			case 63:
			case 64:
			case 65: {
				alt153 = 7;
			}
				break;
			case 49: {
				alt153 = 8;
			}
				break;
			default:
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 153,
						0, input);

				throw nvae;
			}

			switch (alt153) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:857:9:
				// parExpression
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_parExpression_in_primary5185);
				parExpression517 = parExpression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, parExpression517.getTree());

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:858:9:
				// 'this' ( '.' Identifier )* ( identifierSuffix )?
			{
				root_0 = (Object) adaptor.nil();

				string_literal518 = (Token) match(input, 71,
						FOLLOW_71_in_primary5195);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal518_tree = (Object) adaptor
							.create(string_literal518);
					adaptor.addChild(root_0, string_literal518_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:858:16:
				// ( '.' Identifier )*
				loop148: do {
					int alt148 = 2;
					int LA148_0 = input.LA(1);

					if ((LA148_0 == 31)) {
						int LA148_2 = input.LA(2);

						if ((LA148_2 == Identifier)) {

							if ((synpred236_Java())) {
								alt148 = 1;
							}

						}

					}

					switch (alt148) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:858:17:
						// '.' Identifier
					{
						char_literal519 = (Token) match(input, 31,
								FOLLOW_31_in_primary5198);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal519_tree = (Object) adaptor
									.create(char_literal519);
							adaptor.addChild(root_0, char_literal519_tree);
						}
						Identifier520 = (Token) match(input, Identifier,
								FOLLOW_Identifier_in_primary5200);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							Identifier520_tree = (Object) adaptor
									.create(Identifier520);
							adaptor.addChild(root_0, Identifier520_tree);
						}

					}
						break;

					default:
						break loop148;
					}
				} while (true);

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:858:34:
				// ( identifierSuffix )?
				int alt149 = 2;
				alt149 = dfa149.predict(input);
				switch (alt149) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// identifierSuffix
				{
					pushFollow(FOLLOW_identifierSuffix_in_primary5204);
					identifierSuffix521 = identifierSuffix();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, identifierSuffix521.getTree());

				}
					break;

				}

			}
				break;
			case 3:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:859:9:
				// 'super' superSuffix
			{
				root_0 = (Object) adaptor.nil();

				string_literal522 = (Token) match(input, 67,
						FOLLOW_67_in_primary5215);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal522_tree = (Object) adaptor
							.create(string_literal522);
					adaptor.addChild(root_0, string_literal522_tree);
				}
				pushFollow(FOLLOW_superSuffix_in_primary5217);
				superSuffix523 = superSuffix();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, superSuffix523.getTree());

			}
				break;
			case 4:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:860:9:
				// literal
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_literal_in_primary5227);
				literal524 = literal();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, literal524.getTree());

			}
				break;
			case 5:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:861:9:
				// 'new' creator
			{
				root_0 = (Object) adaptor.nil();

				string_literal525 = (Token) match(input, 115,
						FOLLOW_115_in_primary5237);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal525_tree = (Object) adaptor
							.create(string_literal525);
					adaptor.addChild(root_0, string_literal525_tree);
				}
				pushFollow(FOLLOW_creator_in_primary5239);
				creator526 = creator();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, creator526.getTree());

			}
				break;
			case 6:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:862:9:
				// Identifier ( '.' Identifier )* ( identifierSuffix )?
			{
				root_0 = (Object) adaptor.nil();

				Identifier527 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_primary5249);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier527_tree = (Object) adaptor.create(Identifier527);
					adaptor.addChild(root_0, Identifier527_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:862:20:
				// ( '.' Identifier )*
				loop150: do {
					int alt150 = 2;
					int LA150_0 = input.LA(1);

					if ((LA150_0 == 31)) {
						int LA150_2 = input.LA(2);

						if ((LA150_2 == Identifier)) {
							if ((synpred242_Java())) {
								alt150 = 1;
							}

						}

					}

					switch (alt150) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:862:21:
						// '.' Identifier
					{
						char_literal528 = (Token) match(input, 31,
								FOLLOW_31_in_primary5252);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal528_tree = (Object) adaptor
									.create(char_literal528);
							adaptor.addChild(root_0, char_literal528_tree);
						}
						Identifier529 = (Token) match(input, Identifier,
								FOLLOW_Identifier_in_primary5254);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							Identifier529_tree = (Object) adaptor
									.create(Identifier529);
							adaptor.addChild(root_0, Identifier529_tree);
						}

					}
						break;

					default:
						break loop150;
					}
				} while (true);

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:862:38:
				// ( identifierSuffix )?
				int alt151 = 2;
				alt151 = dfa151.predict(input);
				switch (alt151) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// identifierSuffix
				{
					pushFollow(FOLLOW_identifierSuffix_in_primary5258);
					identifierSuffix530 = identifierSuffix();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, identifierSuffix530.getTree());

				}
					break;

				}

			}
				break;
			case 7:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:863:9:
				// primitiveType ( '[' ']' )* '.' 'class'
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_primitiveType_in_primary5269);
				primitiveType531 = primitiveType();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, primitiveType531.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:863:23:
				// ( '[' ']' )*
				loop152: do {
					int alt152 = 2;
					int LA152_0 = input.LA(1);

					if ((LA152_0 == 50)) {
						alt152 = 1;
					}

					switch (alt152) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:863:24:
						// '[' ']'
					{
						char_literal532 = (Token) match(input, 50,
								FOLLOW_50_in_primary5272);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal532_tree = (Object) adaptor
									.create(char_literal532);
							adaptor.addChild(root_0, char_literal532_tree);
						}
						char_literal533 = (Token) match(input, 51,
								FOLLOW_51_in_primary5274);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal533_tree = (Object) adaptor
									.create(char_literal533);
							adaptor.addChild(root_0, char_literal533_tree);
						}

					}
						break;

					default:
						break loop152;
					}
				} while (true);

				char_literal534 = (Token) match(input, 31,
						FOLLOW_31_in_primary5278);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal534_tree = (Object) adaptor
							.create(char_literal534);
					adaptor.addChild(root_0, char_literal534_tree);
				}
				string_literal535 = (Token) match(input, 39,
						FOLLOW_39_in_primary5280);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal535_tree = (Object) adaptor
							.create(string_literal535);
					adaptor.addChild(root_0, string_literal535_tree);
				}

			}
				break;
			case 8:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:864:9:
				// 'void' '.' 'class'
			{
				root_0 = (Object) adaptor.nil();

				string_literal536 = (Token) match(input, 49,
						FOLLOW_49_in_primary5290);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal536_tree = (Object) adaptor
							.create(string_literal536);
					adaptor.addChild(root_0, string_literal536_tree);
				}
				char_literal537 = (Token) match(input, 31,
						FOLLOW_31_in_primary5292);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal537_tree = (Object) adaptor
							.create(char_literal537);
					adaptor.addChild(root_0, char_literal537_tree);
				}
				string_literal538 = (Token) match(input, 39,
						FOLLOW_39_in_primary5294);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal538_tree = (Object) adaptor
							.create(string_literal538);
					adaptor.addChild(root_0, string_literal538_tree);
				}

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 124, primary_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "primary"

	public static class identifierSuffix_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "identifierSuffix"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:867:1:
	// identifierSuffix : ( ( '[' ']' )+ '.' 'class' | ( '[' expression ']' )+ |
	// arguments | '.' 'class' | '.' explicitGenericInvocation | '.' 'this' |
	// '.' 'super' arguments | '.' 'new' innerCreator );
	public final JavaParser.identifierSuffix_return identifierSuffix()
			throws RecognitionException {
		JavaParser.identifierSuffix_return retval = new JavaParser.identifierSuffix_return();
		retval.start = input.LT(1);
		int identifierSuffix_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal539 = null;
		Token char_literal540 = null;
		Token char_literal541 = null;
		Token string_literal542 = null;
		Token char_literal543 = null;
		Token char_literal545 = null;
		Token char_literal547 = null;
		Token string_literal548 = null;
		Token char_literal549 = null;
		Token char_literal551 = null;
		Token string_literal552 = null;
		Token char_literal553 = null;
		Token string_literal554 = null;
		Token char_literal556 = null;
		Token string_literal557 = null;
		JavaParser.expression_return expression544 = null;

		JavaParser.arguments_return arguments546 = null;

		JavaParser.explicitGenericInvocation_return explicitGenericInvocation550 = null;

		JavaParser.arguments_return arguments555 = null;

		JavaParser.innerCreator_return innerCreator558 = null;

		Object char_literal539_tree = null;
		Object char_literal540_tree = null;
		Object char_literal541_tree = null;
		Object string_literal542_tree = null;
		Object char_literal543_tree = null;
		Object char_literal545_tree = null;
		Object char_literal547_tree = null;
		Object string_literal548_tree = null;
		Object char_literal549_tree = null;
		Object char_literal551_tree = null;
		Object string_literal552_tree = null;
		Object char_literal553_tree = null;
		Object string_literal554_tree = null;
		Object char_literal556_tree = null;
		Object string_literal557_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 125)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:868:5:
			// ( ( '[' ']' )+ '.' 'class' | ( '[' expression ']' )+ | arguments
			// | '.' 'class' | '.' explicitGenericInvocation | '.' 'this' | '.'
			// 'super' arguments | '.' 'new' innerCreator )
			int alt156 = 8;
			alt156 = dfa156.predict(input);
			switch (alt156) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:868:9:
				// ( '[' ']' )+ '.' 'class'
			{
				root_0 = (Object) adaptor.nil();

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:868:9:
				// ( '[' ']' )+
				int cnt154 = 0;
				loop154: do {
					int alt154 = 2;
					int LA154_0 = input.LA(1);

					if ((LA154_0 == 50)) {
						alt154 = 1;
					}

					switch (alt154) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:868:10:
						// '[' ']'
					{
						char_literal539 = (Token) match(input, 50,
								FOLLOW_50_in_identifierSuffix5314);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal539_tree = (Object) adaptor
									.create(char_literal539);
							adaptor.addChild(root_0, char_literal539_tree);
						}
						char_literal540 = (Token) match(input, 51,
								FOLLOW_51_in_identifierSuffix5316);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal540_tree = (Object) adaptor
									.create(char_literal540);
							adaptor.addChild(root_0, char_literal540_tree);
						}

					}
						break;

					default:
						if (cnt154 >= 1)
							break loop154;
						if (state.backtracking > 0) {
							state.failed = true;
							return retval;
						}
						EarlyExitException eee = new EarlyExitException(154,
								input);
						throw eee;
					}
					cnt154++;
				} while (true);

				char_literal541 = (Token) match(input, 31,
						FOLLOW_31_in_identifierSuffix5320);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal541_tree = (Object) adaptor
							.create(char_literal541);
					adaptor.addChild(root_0, char_literal541_tree);
				}
				string_literal542 = (Token) match(input, 39,
						FOLLOW_39_in_identifierSuffix5322);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal542_tree = (Object) adaptor
							.create(string_literal542);
					adaptor.addChild(root_0, string_literal542_tree);
				}

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:869:9:
				// ( '[' expression ']' )+
			{
				root_0 = (Object) adaptor.nil();

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:869:9:
				// ( '[' expression ']' )+
				int cnt155 = 0;
				loop155: do {
					int alt155 = 2;
					alt155 = dfa155.predict(input);
					switch (alt155) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:869:10:
						// '[' expression ']'
					{
						char_literal543 = (Token) match(input, 50,
								FOLLOW_50_in_identifierSuffix5333);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal543_tree = (Object) adaptor
									.create(char_literal543);
							adaptor.addChild(root_0, char_literal543_tree);
						}
						pushFollow(FOLLOW_expression_in_identifierSuffix5335);
						expression544 = expression();

						state._fsp--;
						if (state.failed)
							return retval;
						if (state.backtracking == 0)
							adaptor.addChild(root_0, expression544.getTree());
						char_literal545 = (Token) match(input, 51,
								FOLLOW_51_in_identifierSuffix5337);
						if (state.failed)
							return retval;
						if (state.backtracking == 0) {
							char_literal545_tree = (Object) adaptor
									.create(char_literal545);
							adaptor.addChild(root_0, char_literal545_tree);
						}

					}
						break;

					default:
						if (cnt155 >= 1)
							break loop155;
						if (state.backtracking > 0) {
							state.failed = true;
							return retval;
						}
						EarlyExitException eee = new EarlyExitException(155,
								input);
						throw eee;
					}
					cnt155++;
				} while (true);

			}
				break;
			case 3:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:870:9:
				// arguments
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_arguments_in_identifierSuffix5350);
				arguments546 = arguments();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, arguments546.getTree());

			}
				break;
			case 4:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:871:9:
				// '.' 'class'
			{
				root_0 = (Object) adaptor.nil();

				char_literal547 = (Token) match(input, 31,
						FOLLOW_31_in_identifierSuffix5360);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal547_tree = (Object) adaptor
							.create(char_literal547);
					adaptor.addChild(root_0, char_literal547_tree);
				}
				string_literal548 = (Token) match(input, 39,
						FOLLOW_39_in_identifierSuffix5362);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal548_tree = (Object) adaptor
							.create(string_literal548);
					adaptor.addChild(root_0, string_literal548_tree);
				}

			}
				break;
			case 5:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:872:9:
				// '.' explicitGenericInvocation
			{
				root_0 = (Object) adaptor.nil();

				char_literal549 = (Token) match(input, 31,
						FOLLOW_31_in_identifierSuffix5372);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal549_tree = (Object) adaptor
							.create(char_literal549);
					adaptor.addChild(root_0, char_literal549_tree);
				}
				pushFollow(FOLLOW_explicitGenericInvocation_in_identifierSuffix5374);
				explicitGenericInvocation550 = explicitGenericInvocation();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, explicitGenericInvocation550
							.getTree());

			}
				break;
			case 6:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:873:9:
				// '.' 'this'
			{
				root_0 = (Object) adaptor.nil();

				char_literal551 = (Token) match(input, 31,
						FOLLOW_31_in_identifierSuffix5384);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal551_tree = (Object) adaptor
							.create(char_literal551);
					adaptor.addChild(root_0, char_literal551_tree);
				}
				string_literal552 = (Token) match(input, 71,
						FOLLOW_71_in_identifierSuffix5386);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal552_tree = (Object) adaptor
							.create(string_literal552);
					adaptor.addChild(root_0, string_literal552_tree);
				}

			}
				break;
			case 7:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:874:9:
				// '.' 'super' arguments
			{
				root_0 = (Object) adaptor.nil();

				char_literal553 = (Token) match(input, 31,
						FOLLOW_31_in_identifierSuffix5396);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal553_tree = (Object) adaptor
							.create(char_literal553);
					adaptor.addChild(root_0, char_literal553_tree);
				}
				string_literal554 = (Token) match(input, 67,
						FOLLOW_67_in_identifierSuffix5398);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal554_tree = (Object) adaptor
							.create(string_literal554);
					adaptor.addChild(root_0, string_literal554_tree);
				}
				pushFollow(FOLLOW_arguments_in_identifierSuffix5400);
				arguments555 = arguments();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, arguments555.getTree());

			}
				break;
			case 8:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:875:9:
				// '.' 'new' innerCreator
			{
				root_0 = (Object) adaptor.nil();

				char_literal556 = (Token) match(input, 31,
						FOLLOW_31_in_identifierSuffix5410);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal556_tree = (Object) adaptor
							.create(char_literal556);
					adaptor.addChild(root_0, char_literal556_tree);
				}
				string_literal557 = (Token) match(input, 115,
						FOLLOW_115_in_identifierSuffix5412);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal557_tree = (Object) adaptor
							.create(string_literal557);
					adaptor.addChild(root_0, string_literal557_tree);
				}
				pushFollow(FOLLOW_innerCreator_in_identifierSuffix5414);
				innerCreator558 = innerCreator();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, innerCreator558.getTree());

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 125, identifierSuffix_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "identifierSuffix"

	public static class creator_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "creator"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:878:1:
	// creator : ( nonWildcardTypeArguments createdName classCreatorRest |
	// createdName ( arrayCreatorRest | classCreatorRest ) );
	public final JavaParser.creator_return creator()
			throws RecognitionException {
		JavaParser.creator_return retval = new JavaParser.creator_return();
		retval.start = input.LT(1);
		int creator_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.nonWildcardTypeArguments_return nonWildcardTypeArguments559 = null;

		JavaParser.createdName_return createdName560 = null;

		JavaParser.classCreatorRest_return classCreatorRest561 = null;

		JavaParser.createdName_return createdName562 = null;

		JavaParser.arrayCreatorRest_return arrayCreatorRest563 = null;

		JavaParser.classCreatorRest_return classCreatorRest564 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 126)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:879:5:
			// ( nonWildcardTypeArguments createdName classCreatorRest |
			// createdName ( arrayCreatorRest | classCreatorRest ) )
			int alt158 = 2;
			int LA158_0 = input.LA(1);

			if ((LA158_0 == 42)) {
				alt158 = 1;
			} else if ((LA158_0 == Identifier || (LA158_0 >= 58 && LA158_0 <= 65))) {
				alt158 = 2;
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 158,
						0, input);

				throw nvae;
			}
			switch (alt158) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:879:9:
				// nonWildcardTypeArguments createdName classCreatorRest
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_nonWildcardTypeArguments_in_creator5433);
				nonWildcardTypeArguments559 = nonWildcardTypeArguments();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, nonWildcardTypeArguments559
							.getTree());
				pushFollow(FOLLOW_createdName_in_creator5435);
				createdName560 = createdName();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, createdName560.getTree());
				pushFollow(FOLLOW_classCreatorRest_in_creator5437);
				classCreatorRest561 = classCreatorRest();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, classCreatorRest561.getTree());

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:880:9:
				// createdName ( arrayCreatorRest | classCreatorRest )
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_createdName_in_creator5447);
				createdName562 = createdName();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, createdName562.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:880:21:
				// ( arrayCreatorRest | classCreatorRest )
				int alt157 = 2;
				int LA157_0 = input.LA(1);

				if ((LA157_0 == 50)) {
					alt157 = 1;
				} else if ((LA157_0 == 68)) {
					alt157 = 2;
				} else {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					NoViableAltException nvae = new NoViableAltException("",
							157, 0, input);

					throw nvae;
				}
				switch (alt157) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:880:22:
					// arrayCreatorRest
				{
					pushFollow(FOLLOW_arrayCreatorRest_in_creator5450);
					arrayCreatorRest563 = arrayCreatorRest();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, arrayCreatorRest563.getTree());

				}
					break;
				case 2:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:880:41:
					// classCreatorRest
				{
					pushFollow(FOLLOW_classCreatorRest_in_creator5454);
					classCreatorRest564 = classCreatorRest();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, classCreatorRest564.getTree());

				}
					break;

				}

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 126, creator_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "creator"

	public static class createdName_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "createdName"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:883:1:
	// createdName : ( classOrInterfaceType | primitiveType );
	public final JavaParser.createdName_return createdName()
			throws RecognitionException {
		JavaParser.createdName_return retval = new JavaParser.createdName_return();
		retval.start = input.LT(1);
		int createdName_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.classOrInterfaceType_return classOrInterfaceType565 = null;

		JavaParser.primitiveType_return primitiveType566 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 127)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:884:5:
			// ( classOrInterfaceType | primitiveType )
			int alt159 = 2;
			int LA159_0 = input.LA(1);

			if ((LA159_0 == Identifier)) {
				alt159 = 1;
			} else if (((LA159_0 >= 58 && LA159_0 <= 65))) {
				alt159 = 2;
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 159,
						0, input);

				throw nvae;
			}
			switch (alt159) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:884:9:
				// classOrInterfaceType
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_classOrInterfaceType_in_createdName5474);
				classOrInterfaceType565 = classOrInterfaceType();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, classOrInterfaceType565.getTree());

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:885:9:
				// primitiveType
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_primitiveType_in_createdName5484);
				primitiveType566 = primitiveType();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, primitiveType566.getTree());

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 127, createdName_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "createdName"

	public static class innerCreator_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "innerCreator"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:888:1:
	// innerCreator : ( nonWildcardTypeArguments )? Identifier classCreatorRest
	// ;
	public final JavaParser.innerCreator_return innerCreator()
			throws RecognitionException {
		JavaParser.innerCreator_return retval = new JavaParser.innerCreator_return();
		retval.start = input.LT(1);
		int innerCreator_StartIndex = input.index();
		Object root_0 = null;

		Token Identifier568 = null;
		JavaParser.nonWildcardTypeArguments_return nonWildcardTypeArguments567 = null;

		JavaParser.classCreatorRest_return classCreatorRest569 = null;

		Object Identifier568_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 128)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:889:5:
			// ( ( nonWildcardTypeArguments )? Identifier classCreatorRest )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:889:9:
			// ( nonWildcardTypeArguments )? Identifier classCreatorRest
			{
				root_0 = (Object) adaptor.nil();

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:889:9:
				// ( nonWildcardTypeArguments )?
				int alt160 = 2;
				int LA160_0 = input.LA(1);

				if ((LA160_0 == 42)) {
					alt160 = 1;
				}
				switch (alt160) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// nonWildcardTypeArguments
				{
					pushFollow(FOLLOW_nonWildcardTypeArguments_in_innerCreator5507);
					nonWildcardTypeArguments567 = nonWildcardTypeArguments();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, nonWildcardTypeArguments567
								.getTree());

				}
					break;

				}

				Identifier568 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_innerCreator5510);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier568_tree = (Object) adaptor.create(Identifier568);
					adaptor.addChild(root_0, Identifier568_tree);
				}
				pushFollow(FOLLOW_classCreatorRest_in_innerCreator5512);
				classCreatorRest569 = classCreatorRest();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, classCreatorRest569.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 128, innerCreator_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "innerCreator"

	public static class arrayCreatorRest_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "arrayCreatorRest"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:892:1:
	// arrayCreatorRest : '[' ( ']' ( '[' ']' )* arrayInitializer | expression
	// ']' ( '[' expression ']' )* ( '[' ']' )* ) ;
	public final JavaParser.arrayCreatorRest_return arrayCreatorRest()
			throws RecognitionException {
		JavaParser.arrayCreatorRest_return retval = new JavaParser.arrayCreatorRest_return();
		retval.start = input.LT(1);
		int arrayCreatorRest_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal570 = null;
		Token char_literal571 = null;
		Token char_literal572 = null;
		Token char_literal573 = null;
		Token char_literal576 = null;
		Token char_literal577 = null;
		Token char_literal579 = null;
		Token char_literal580 = null;
		Token char_literal581 = null;
		JavaParser.arrayInitializer_return arrayInitializer574 = null;

		JavaParser.expression_return expression575 = null;

		JavaParser.expression_return expression578 = null;

		Object char_literal570_tree = null;
		Object char_literal571_tree = null;
		Object char_literal572_tree = null;
		Object char_literal573_tree = null;
		Object char_literal576_tree = null;
		Object char_literal577_tree = null;
		Object char_literal579_tree = null;
		Object char_literal580_tree = null;
		Object char_literal581_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 129)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:893:5:
			// ( '[' ( ']' ( '[' ']' )* arrayInitializer | expression ']' ( '['
			// expression ']' )* ( '[' ']' )* ) )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:893:9:
			// '[' ( ']' ( '[' ']' )* arrayInitializer | expression ']' ( '['
			// expression ']' )* ( '[' ']' )* )
			{
				root_0 = (Object) adaptor.nil();

				char_literal570 = (Token) match(input, 50,
						FOLLOW_50_in_arrayCreatorRest5531);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal570_tree = (Object) adaptor
							.create(char_literal570);
					adaptor.addChild(root_0, char_literal570_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:894:9:
				// ( ']' ( '[' ']' )* arrayInitializer | expression ']' ( '['
				// expression ']' )* ( '[' ']' )* )
				int alt164 = 2;
				int LA164_0 = input.LA(1);

				if ((LA164_0 == 51)) {
					alt164 = 1;
				} else if ((LA164_0 == Identifier
						|| (LA164_0 >= FloatingPointLiteral && LA164_0 <= DecimalLiteral)
						|| LA164_0 == 49 || (LA164_0 >= 58 && LA164_0 <= 65)
						|| (LA164_0 >= 67 && LA164_0 <= 68)
						|| (LA164_0 >= 71 && LA164_0 <= 74)
						|| (LA164_0 >= 107 && LA164_0 <= 108) || (LA164_0 >= 111 && LA164_0 <= 115))) {
					alt164 = 2;
				} else {
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					NoViableAltException nvae = new NoViableAltException("",
							164, 0, input);

					throw nvae;
				}
				switch (alt164) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:894:13:
					// ']' ( '[' ']' )* arrayInitializer
				{
					char_literal571 = (Token) match(input, 51,
							FOLLOW_51_in_arrayCreatorRest5545);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						char_literal571_tree = (Object) adaptor
								.create(char_literal571);
						adaptor.addChild(root_0, char_literal571_tree);
					}
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:894:17:
					// ( '[' ']' )*
					loop161: do {
						int alt161 = 2;
						int LA161_0 = input.LA(1);

						if ((LA161_0 == 50)) {
							alt161 = 1;
						}

						switch (alt161) {
						case 1:
							// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:894:18:
							// '[' ']'
						{
							char_literal572 = (Token) match(input, 50,
									FOLLOW_50_in_arrayCreatorRest5548);
							if (state.failed)
								return retval;
							if (state.backtracking == 0) {
								char_literal572_tree = (Object) adaptor
										.create(char_literal572);
								adaptor.addChild(root_0, char_literal572_tree);
							}
							char_literal573 = (Token) match(input, 51,
									FOLLOW_51_in_arrayCreatorRest5550);
							if (state.failed)
								return retval;
							if (state.backtracking == 0) {
								char_literal573_tree = (Object) adaptor
										.create(char_literal573);
								adaptor.addChild(root_0, char_literal573_tree);
							}

						}
							break;

						default:
							break loop161;
						}
					} while (true);

					pushFollow(FOLLOW_arrayInitializer_in_arrayCreatorRest5554);
					arrayInitializer574 = arrayInitializer();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, arrayInitializer574.getTree());

				}
					break;
				case 2:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:895:13:
					// expression ']' ( '[' expression ']' )* ( '[' ']' )*
				{
					pushFollow(FOLLOW_expression_in_arrayCreatorRest5568);
					expression575 = expression();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, expression575.getTree());
					char_literal576 = (Token) match(input, 51,
							FOLLOW_51_in_arrayCreatorRest5570);
					if (state.failed)
						return retval;
					if (state.backtracking == 0) {
						char_literal576_tree = (Object) adaptor
								.create(char_literal576);
						adaptor.addChild(root_0, char_literal576_tree);
					}
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:895:28:
					// ( '[' expression ']' )*
					loop162: do {
						int alt162 = 2;
						alt162 = dfa162.predict(input);
						switch (alt162) {
						case 1:
							// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:895:29:
							// '[' expression ']'
						{
							char_literal577 = (Token) match(input, 50,
									FOLLOW_50_in_arrayCreatorRest5573);
							if (state.failed)
								return retval;
							if (state.backtracking == 0) {
								char_literal577_tree = (Object) adaptor
										.create(char_literal577);
								adaptor.addChild(root_0, char_literal577_tree);
							}
							pushFollow(FOLLOW_expression_in_arrayCreatorRest5575);
							expression578 = expression();

							state._fsp--;
							if (state.failed)
								return retval;
							if (state.backtracking == 0)
								adaptor.addChild(root_0, expression578
										.getTree());
							char_literal579 = (Token) match(input, 51,
									FOLLOW_51_in_arrayCreatorRest5577);
							if (state.failed)
								return retval;
							if (state.backtracking == 0) {
								char_literal579_tree = (Object) adaptor
										.create(char_literal579);
								adaptor.addChild(root_0, char_literal579_tree);
							}

						}
							break;

						default:
							break loop162;
						}
					} while (true);

					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:895:50:
					// ( '[' ']' )*
					loop163: do {
						int alt163 = 2;
						int LA163_0 = input.LA(1);

						if ((LA163_0 == 50)) {
							int LA163_2 = input.LA(2);

							if ((LA163_2 == 51)) {
								alt163 = 1;
							}

						}

						switch (alt163) {
						case 1:
							// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:895:51:
							// '[' ']'
						{
							char_literal580 = (Token) match(input, 50,
									FOLLOW_50_in_arrayCreatorRest5582);
							if (state.failed)
								return retval;
							if (state.backtracking == 0) {
								char_literal580_tree = (Object) adaptor
										.create(char_literal580);
								adaptor.addChild(root_0, char_literal580_tree);
							}
							char_literal581 = (Token) match(input, 51,
									FOLLOW_51_in_arrayCreatorRest5584);
							if (state.failed)
								return retval;
							if (state.backtracking == 0) {
								char_literal581_tree = (Object) adaptor
										.create(char_literal581);
								adaptor.addChild(root_0, char_literal581_tree);
							}

						}
							break;

						default:
							break loop163;
						}
					} while (true);

				}
					break;

				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 129, arrayCreatorRest_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "arrayCreatorRest"

	public static class classCreatorRest_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "classCreatorRest"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:899:1:
	// classCreatorRest : arguments ( classBody )? ;
	public final JavaParser.classCreatorRest_return classCreatorRest()
			throws RecognitionException {
		JavaParser.classCreatorRest_return retval = new JavaParser.classCreatorRest_return();
		retval.start = input.LT(1);
		int classCreatorRest_StartIndex = input.index();
		Object root_0 = null;

		JavaParser.arguments_return arguments582 = null;

		JavaParser.classBody_return classBody583 = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 130)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:900:5:
			// ( arguments ( classBody )? )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:900:9:
			// arguments ( classBody )?
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_arguments_in_classCreatorRest5615);
				arguments582 = arguments();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, arguments582.getTree());
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:900:19:
				// ( classBody )?
				int alt165 = 2;
				int LA165_0 = input.LA(1);

				if ((LA165_0 == 46)) {
					alt165 = 1;
				}
				switch (alt165) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// classBody
				{
					pushFollow(FOLLOW_classBody_in_classCreatorRest5617);
					classBody583 = classBody();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, classBody583.getTree());

				}
					break;

				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 130, classCreatorRest_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "classCreatorRest"

	public static class explicitGenericInvocation_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "explicitGenericInvocation"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:903:1:
	// explicitGenericInvocation : nonWildcardTypeArguments Identifier arguments
	// ;
	public final JavaParser.explicitGenericInvocation_return explicitGenericInvocation()
			throws RecognitionException {
		JavaParser.explicitGenericInvocation_return retval = new JavaParser.explicitGenericInvocation_return();
		retval.start = input.LT(1);
		int explicitGenericInvocation_StartIndex = input.index();
		Object root_0 = null;

		Token Identifier585 = null;
		JavaParser.nonWildcardTypeArguments_return nonWildcardTypeArguments584 = null;

		JavaParser.arguments_return arguments586 = null;

		Object Identifier585_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 131)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:904:5:
			// ( nonWildcardTypeArguments Identifier arguments )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:904:9:
			// nonWildcardTypeArguments Identifier arguments
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_nonWildcardTypeArguments_in_explicitGenericInvocation5641);
				nonWildcardTypeArguments584 = nonWildcardTypeArguments();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, nonWildcardTypeArguments584
							.getTree());
				Identifier585 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_explicitGenericInvocation5643);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier585_tree = (Object) adaptor.create(Identifier585);
					adaptor.addChild(root_0, Identifier585_tree);
				}
				pushFollow(FOLLOW_arguments_in_explicitGenericInvocation5645);
				arguments586 = arguments();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, arguments586.getTree());

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 131, explicitGenericInvocation_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "explicitGenericInvocation"

	public static class nonWildcardTypeArguments_return extends
			ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "nonWildcardTypeArguments"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:907:1:
	// nonWildcardTypeArguments : '<' typeList '>' ;
	public final JavaParser.nonWildcardTypeArguments_return nonWildcardTypeArguments()
			throws RecognitionException {
		JavaParser.nonWildcardTypeArguments_return retval = new JavaParser.nonWildcardTypeArguments_return();
		retval.start = input.LT(1);
		int nonWildcardTypeArguments_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal587 = null;
		Token char_literal589 = null;
		JavaParser.typeList_return typeList588 = null;

		Object char_literal587_tree = null;
		Object char_literal589_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 132)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:908:5:
			// ( '<' typeList '>' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:908:9:
			// '<' typeList '>'
			{
				root_0 = (Object) adaptor.nil();

				char_literal587 = (Token) match(input, 42,
						FOLLOW_42_in_nonWildcardTypeArguments5668);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal587_tree = (Object) adaptor
							.create(char_literal587);
					adaptor.addChild(root_0, char_literal587_tree);
				}
				pushFollow(FOLLOW_typeList_in_nonWildcardTypeArguments5670);
				typeList588 = typeList();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, typeList588.getTree());
				char_literal589 = (Token) match(input, 44,
						FOLLOW_44_in_nonWildcardTypeArguments5672);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal589_tree = (Object) adaptor
							.create(char_literal589);
					adaptor.addChild(root_0, char_literal589_tree);
				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 132, nonWildcardTypeArguments_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "nonWildcardTypeArguments"

	public static class selector_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "selector"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:911:1:
	// selector : ( '.' Identifier ( arguments )? | '.' 'this' | '.' 'super'
	// superSuffix | '.' 'new' innerCreator | '[' expression ']' );
	public final JavaParser.selector_return selector()
			throws RecognitionException {
		JavaParser.selector_return retval = new JavaParser.selector_return();
		retval.start = input.LT(1);
		int selector_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal590 = null;
		Token Identifier591 = null;
		Token char_literal593 = null;
		Token string_literal594 = null;
		Token char_literal595 = null;
		Token string_literal596 = null;
		Token char_literal598 = null;
		Token string_literal599 = null;
		Token char_literal601 = null;
		Token char_literal603 = null;
		JavaParser.arguments_return arguments592 = null;

		JavaParser.superSuffix_return superSuffix597 = null;

		JavaParser.innerCreator_return innerCreator600 = null;

		JavaParser.expression_return expression602 = null;

		Object char_literal590_tree = null;
		Object Identifier591_tree = null;
		Object char_literal593_tree = null;
		Object string_literal594_tree = null;
		Object char_literal595_tree = null;
		Object string_literal596_tree = null;
		Object char_literal598_tree = null;
		Object string_literal599_tree = null;
		Object char_literal601_tree = null;
		Object char_literal603_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 133)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:912:5:
			// ( '.' Identifier ( arguments )? | '.' 'this' | '.' 'super'
			// superSuffix | '.' 'new' innerCreator | '[' expression ']' )
			int alt167 = 5;
			int LA167_0 = input.LA(1);

			if ((LA167_0 == 31)) {
				switch (input.LA(2)) {
				case Identifier: {
					alt167 = 1;
				}
					break;
				case 71: {
					alt167 = 2;
				}
					break;
				case 67: {
					alt167 = 3;
				}
					break;
				case 115: {
					alt167 = 4;
				}
					break;
				default:
					if (state.backtracking > 0) {
						state.failed = true;
						return retval;
					}
					NoViableAltException nvae = new NoViableAltException("",
							167, 1, input);

					throw nvae;
				}

			} else if ((LA167_0 == 50)) {
				alt167 = 5;
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 167,
						0, input);

				throw nvae;
			}
			switch (alt167) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:912:9:
				// '.' Identifier ( arguments )?
			{
				root_0 = (Object) adaptor.nil();

				char_literal590 = (Token) match(input, 31,
						FOLLOW_31_in_selector5695);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal590_tree = (Object) adaptor
							.create(char_literal590);
					adaptor.addChild(root_0, char_literal590_tree);
				}
				Identifier591 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_selector5697);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier591_tree = (Object) adaptor.create(Identifier591);
					adaptor.addChild(root_0, Identifier591_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:912:24:
				// ( arguments )?
				int alt166 = 2;
				int LA166_0 = input.LA(1);

				if ((LA166_0 == 68)) {
					alt166 = 1;
				}
				switch (alt166) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// arguments
				{
					pushFollow(FOLLOW_arguments_in_selector5699);
					arguments592 = arguments();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, arguments592.getTree());

				}
					break;

				}

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:913:9:
				// '.' 'this'
			{
				root_0 = (Object) adaptor.nil();

				char_literal593 = (Token) match(input, 31,
						FOLLOW_31_in_selector5710);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal593_tree = (Object) adaptor
							.create(char_literal593);
					adaptor.addChild(root_0, char_literal593_tree);
				}
				string_literal594 = (Token) match(input, 71,
						FOLLOW_71_in_selector5712);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal594_tree = (Object) adaptor
							.create(string_literal594);
					adaptor.addChild(root_0, string_literal594_tree);
				}

			}
				break;
			case 3:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:914:9:
				// '.' 'super' superSuffix
			{
				root_0 = (Object) adaptor.nil();

				char_literal595 = (Token) match(input, 31,
						FOLLOW_31_in_selector5722);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal595_tree = (Object) adaptor
							.create(char_literal595);
					adaptor.addChild(root_0, char_literal595_tree);
				}
				string_literal596 = (Token) match(input, 67,
						FOLLOW_67_in_selector5724);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal596_tree = (Object) adaptor
							.create(string_literal596);
					adaptor.addChild(root_0, string_literal596_tree);
				}
				pushFollow(FOLLOW_superSuffix_in_selector5726);
				superSuffix597 = superSuffix();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, superSuffix597.getTree());

			}
				break;
			case 4:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:915:9:
				// '.' 'new' innerCreator
			{
				root_0 = (Object) adaptor.nil();

				char_literal598 = (Token) match(input, 31,
						FOLLOW_31_in_selector5736);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal598_tree = (Object) adaptor
							.create(char_literal598);
					adaptor.addChild(root_0, char_literal598_tree);
				}
				string_literal599 = (Token) match(input, 115,
						FOLLOW_115_in_selector5738);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					string_literal599_tree = (Object) adaptor
							.create(string_literal599);
					adaptor.addChild(root_0, string_literal599_tree);
				}
				pushFollow(FOLLOW_innerCreator_in_selector5740);
				innerCreator600 = innerCreator();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, innerCreator600.getTree());

			}
				break;
			case 5:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:916:9:
				// '[' expression ']'
			{
				root_0 = (Object) adaptor.nil();

				char_literal601 = (Token) match(input, 50,
						FOLLOW_50_in_selector5750);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal601_tree = (Object) adaptor
							.create(char_literal601);
					adaptor.addChild(root_0, char_literal601_tree);
				}
				pushFollow(FOLLOW_expression_in_selector5752);
				expression602 = expression();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, expression602.getTree());
				char_literal603 = (Token) match(input, 51,
						FOLLOW_51_in_selector5754);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal603_tree = (Object) adaptor
							.create(char_literal603);
					adaptor.addChild(root_0, char_literal603_tree);
				}

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 133, selector_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "selector"

	public static class superSuffix_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "superSuffix"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:919:1:
	// superSuffix : ( arguments | '.' Identifier ( arguments )? );
	public final JavaParser.superSuffix_return superSuffix()
			throws RecognitionException {
		JavaParser.superSuffix_return retval = new JavaParser.superSuffix_return();
		retval.start = input.LT(1);
		int superSuffix_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal605 = null;
		Token Identifier606 = null;
		JavaParser.arguments_return arguments604 = null;

		JavaParser.arguments_return arguments607 = null;

		Object char_literal605_tree = null;
		Object Identifier606_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 134)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:920:5:
			// ( arguments | '.' Identifier ( arguments )? )
			int alt169 = 2;
			int LA169_0 = input.LA(1);

			if ((LA169_0 == 68)) {
				alt169 = 1;
			} else if ((LA169_0 == 31)) {
				alt169 = 2;
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return retval;
				}
				NoViableAltException nvae = new NoViableAltException("", 169,
						0, input);

				throw nvae;
			}
			switch (alt169) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:920:9:
				// arguments
			{
				root_0 = (Object) adaptor.nil();

				pushFollow(FOLLOW_arguments_in_superSuffix5777);
				arguments604 = arguments();

				state._fsp--;
				if (state.failed)
					return retval;
				if (state.backtracking == 0)
					adaptor.addChild(root_0, arguments604.getTree());

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:921:9:
				// '.' Identifier ( arguments )?
			{
				root_0 = (Object) adaptor.nil();

				char_literal605 = (Token) match(input, 31,
						FOLLOW_31_in_superSuffix5787);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal605_tree = (Object) adaptor
							.create(char_literal605);
					adaptor.addChild(root_0, char_literal605_tree);
				}
				Identifier606 = (Token) match(input, Identifier,
						FOLLOW_Identifier_in_superSuffix5789);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					Identifier606_tree = (Object) adaptor.create(Identifier606);
					adaptor.addChild(root_0, Identifier606_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:921:24:
				// ( arguments )?
				int alt168 = 2;
				int LA168_0 = input.LA(1);

				if ((LA168_0 == 68)) {
					alt168 = 1;
				}
				switch (alt168) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// arguments
				{
					pushFollow(FOLLOW_arguments_in_superSuffix5791);
					arguments607 = arguments();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, arguments607.getTree());

				}
					break;

				}

			}
				break;

			}
			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 134, superSuffix_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "superSuffix"

	public static class arguments_return extends ParserRuleReturnScope {
		Object tree;

		public Object getTree() {
			return tree;
		}
	};

	// $ANTLR start "arguments"
	// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:924:1:
	// arguments : '(' ( expressionList )? ')' ;
	public final JavaParser.arguments_return arguments()
			throws RecognitionException {
		JavaParser.arguments_return retval = new JavaParser.arguments_return();
		retval.start = input.LT(1);
		int arguments_StartIndex = input.index();
		Object root_0 = null;

		Token char_literal608 = null;
		Token char_literal610 = null;
		JavaParser.expressionList_return expressionList609 = null;

		Object char_literal608_tree = null;
		Object char_literal610_tree = null;

		try {
			if (state.backtracking > 0 && alreadyParsedRule(input, 135)) {
				return retval;
			}
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:925:5:
			// ( '(' ( expressionList )? ')' )
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:925:9:
			// '(' ( expressionList )? ')'
			{
				root_0 = (Object) adaptor.nil();

				char_literal608 = (Token) match(input, 68,
						FOLLOW_68_in_arguments5811);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal608_tree = (Object) adaptor
							.create(char_literal608);
					adaptor.addChild(root_0, char_literal608_tree);
				}
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:925:13:
				// ( expressionList )?
				int alt170 = 2;
				int LA170_0 = input.LA(1);

				if ((LA170_0 == Identifier
						|| (LA170_0 >= FloatingPointLiteral && LA170_0 <= DecimalLiteral)
						|| LA170_0 == 49 || (LA170_0 >= 58 && LA170_0 <= 65)
						|| (LA170_0 >= 67 && LA170_0 <= 68)
						|| (LA170_0 >= 71 && LA170_0 <= 74)
						|| (LA170_0 >= 107 && LA170_0 <= 108) || (LA170_0 >= 111 && LA170_0 <= 115))) {
					alt170 = 1;
				}
				switch (alt170) {
				case 1:
					// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
					// expressionList
				{
					pushFollow(FOLLOW_expressionList_in_arguments5813);
					expressionList609 = expressionList();

					state._fsp--;
					if (state.failed)
						return retval;
					if (state.backtracking == 0)
						adaptor.addChild(root_0, expressionList609.getTree());

				}
					break;

				}

				char_literal610 = (Token) match(input, 69,
						FOLLOW_69_in_arguments5816);
				if (state.failed)
					return retval;
				if (state.backtracking == 0) {
					char_literal610_tree = (Object) adaptor
							.create(char_literal610);
					adaptor.addChild(root_0, char_literal610_tree);
				}

			}

			retval.stop = input.LT(-1);

			if (state.backtracking == 0) {

				retval.tree = (Object) adaptor.rulePostProcessing(root_0);
				adaptor.setTokenBoundaries(retval.tree, retval.start,
						retval.stop);
			}
		} catch (RecognitionException re) {
			reportError(re);
			recover(input, re);
			retval.tree = (Object) adaptor.errorNode(input, retval.start, input
					.LT(-1), re);

		} finally {
			if (state.backtracking > 0) {
				memoize(input, 135, arguments_StartIndex);
			}
		}
		return retval;
	}

	// $ANTLR end "arguments"

	// $ANTLR start synpred5_Java
	public final void synpred5_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:197:9:
		// ( annotations ( packageDeclaration ( importDeclaration )* (
		// typeDeclaration )* | classOrInterfaceDeclaration ( typeDeclaration )*
		// ) )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:197:9:
		// annotations ( packageDeclaration ( importDeclaration )* (
		// typeDeclaration )* | classOrInterfaceDeclaration ( typeDeclaration )*
		// )
		{
			pushFollow(FOLLOW_annotations_in_synpred5_Java89);
			annotations();

			state._fsp--;
			if (state.failed)
				return;
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:198:9:
			// ( packageDeclaration ( importDeclaration )* ( typeDeclaration )*
			// | classOrInterfaceDeclaration ( typeDeclaration )* )
			int alt176 = 2;
			int LA176_0 = input.LA(1);

			if ((LA176_0 == 27)) {
				alt176 = 1;
			} else if ((LA176_0 == ENUM || LA176_0 == 30
					|| (LA176_0 >= 33 && LA176_0 <= 39) || LA176_0 == 48 || LA176_0 == 75)) {
				alt176 = 2;
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return;
				}
				NoViableAltException nvae = new NoViableAltException("", 176,
						0, input);

				throw nvae;
			}
			switch (alt176) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:198:13:
				// packageDeclaration ( importDeclaration )* ( typeDeclaration
				// )*
			{
				pushFollow(FOLLOW_packageDeclaration_in_synpred5_Java103);
				packageDeclaration();

				state._fsp--;
				if (state.failed)
					return;
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:198:32:
				// ( importDeclaration )*
				loop173: do {
					int alt173 = 2;
					int LA173_0 = input.LA(1);

					if ((LA173_0 == 29)) {
						alt173 = 1;
					}

					switch (alt173) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
						// importDeclaration
					{
						pushFollow(FOLLOW_importDeclaration_in_synpred5_Java105);
						importDeclaration();

						state._fsp--;
						if (state.failed)
							return;

					}
						break;

					default:
						break loop173;
					}
				} while (true);

				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:198:51:
				// ( typeDeclaration )*
				loop174: do {
					int alt174 = 2;
					int LA174_0 = input.LA(1);

					if ((LA174_0 == ENUM || LA174_0 == 28 || LA174_0 == 30
							|| (LA174_0 >= 33 && LA174_0 <= 39)
							|| LA174_0 == 48 || LA174_0 == 75)) {
						alt174 = 1;
					}

					switch (alt174) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
						// typeDeclaration
					{
						pushFollow(FOLLOW_typeDeclaration_in_synpred5_Java108);
						typeDeclaration();

						state._fsp--;
						if (state.failed)
							return;

					}
						break;

					default:
						break loop174;
					}
				} while (true);

			}
				break;
			case 2:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:199:13:
				// classOrInterfaceDeclaration ( typeDeclaration )*
			{
				pushFollow(FOLLOW_classOrInterfaceDeclaration_in_synpred5_Java123);
				classOrInterfaceDeclaration();

				state._fsp--;
				if (state.failed)
					return;
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:199:41:
				// ( typeDeclaration )*
				loop175: do {
					int alt175 = 2;
					int LA175_0 = input.LA(1);

					if ((LA175_0 == ENUM || LA175_0 == 28 || LA175_0 == 30
							|| (LA175_0 >= 33 && LA175_0 <= 39)
							|| LA175_0 == 48 || LA175_0 == 75)) {
						alt175 = 1;
					}

					switch (alt175) {
					case 1:
						// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
						// typeDeclaration
					{
						pushFollow(FOLLOW_typeDeclaration_in_synpred5_Java125);
						typeDeclaration();

						state._fsp--;
						if (state.failed)
							return;

					}
						break;

					default:
						break loop175;
					}
				} while (true);

			}
				break;

			}

		}
	}

	// $ANTLR end synpred5_Java

	// $ANTLR start synpred13_Java
	public final void synpred13_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:222:9:
		// ( classOrInterfaceModifiers classDeclaration )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:222:9:
		// classOrInterfaceModifiers classDeclaration
		{
			pushFollow(FOLLOW_classOrInterfaceModifiers_in_synpred13_Java289);
			classOrInterfaceModifiers();

			state._fsp--;
			if (state.failed)
				return;
			pushFollow(FOLLOW_classDeclaration_in_synpred13_Java291);
			classDeclaration();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred13_Java

	// $ANTLR start synpred113_Java
	public final void synpred113_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:513:13:
		// ( explicitConstructorInvocation )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:513:13:
		// explicitConstructorInvocation
		{
			pushFollow(FOLLOW_explicitConstructorInvocation_in_synpred113_Java2547);
			explicitConstructorInvocation();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred113_Java

	// $ANTLR start synpred117_Java
	public final void synpred117_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:517:9:
		// ( ( nonWildcardTypeArguments )? ( 'this' | 'super' ) arguments ';' )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:517:9:
		// ( nonWildcardTypeArguments )? ( 'this' | 'super' ) arguments ';'
		{
			// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:517:9:
			// ( nonWildcardTypeArguments )?
			int alt184 = 2;
			int LA184_0 = input.LA(1);

			if ((LA184_0 == 42)) {
				alt184 = 1;
			}
			switch (alt184) {
			case 1:
				// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:0:0:
				// nonWildcardTypeArguments
			{
				pushFollow(FOLLOW_nonWildcardTypeArguments_in_synpred117_Java2572);
				nonWildcardTypeArguments();

				state._fsp--;
				if (state.failed)
					return;

			}
				break;

			}

			if (input.LA(1) == 67 || input.LA(1) == 71) {
				input.consume();
				state.errorRecovery = false;
				state.failed = false;
			} else {
				if (state.backtracking > 0) {
					state.failed = true;
					return;
				}
				MismatchedSetException mse = new MismatchedSetException(null,
						input);
				throw mse;
			}

			pushFollow(FOLLOW_arguments_in_synpred117_Java2583);
			arguments();

			state._fsp--;
			if (state.failed)
				return;
			match(input, 28, FOLLOW_28_in_synpred117_Java2585);
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred117_Java

	// $ANTLR start synpred128_Java
	public final void synpred128_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:549:9:
		// ( annotation )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:549:9:
		// annotation
		{
			pushFollow(FOLLOW_annotation_in_synpred128_Java2796);
			annotation();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred128_Java

	// $ANTLR start synpred151_Java
	public final void synpred151_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:622:9:
		// ( localVariableDeclarationStatement )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:622:9:
		// localVariableDeclarationStatement
		{
			pushFollow(FOLLOW_localVariableDeclarationStatement_in_synpred151_Java3323);
			localVariableDeclarationStatement();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred151_Java

	// $ANTLR start synpred152_Java
	public final void synpred152_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:623:9:
		// ( classOrInterfaceDeclaration )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:623:9:
		// classOrInterfaceDeclaration
		{
			pushFollow(FOLLOW_classOrInterfaceDeclaration_in_synpred152_Java3333);
			classOrInterfaceDeclaration();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred152_Java

	// $ANTLR start synpred157_Java
	public final void synpred157_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:642:54:
		// ( 'else' statement )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:642:54:
		// 'else' statement
		{
			match(input, 79, FOLLOW_79_in_synpred157_Java3478);
			if (state.failed)
				return;
			pushFollow(FOLLOW_statement_in_synpred157_Java3480);
			statement();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred157_Java

	// $ANTLR start synpred162_Java
	public final void synpred162_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:647:11:
		// ( catches 'finally' block )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:647:11:
		// catches 'finally' block
		{
			pushFollow(FOLLOW_catches_in_synpred162_Java3556);
			catches();

			state._fsp--;
			if (state.failed)
				return;
			match(input, 84, FOLLOW_84_in_synpred162_Java3558);
			if (state.failed)
				return;
			pushFollow(FOLLOW_block_in_synpred162_Java3560);
			block();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred162_Java

	// $ANTLR start synpred163_Java
	public final void synpred163_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:648:11:
		// ( catches )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:648:11:
		// catches
		{
			pushFollow(FOLLOW_catches_in_synpred163_Java3572);
			catches();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred163_Java

	// $ANTLR start synpred178_Java
	public final void synpred178_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:683:9:
		// ( switchLabel )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:683:9:
		// switchLabel
		{
			pushFollow(FOLLOW_switchLabel_in_synpred178_Java3863);
			switchLabel();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred178_Java

	// $ANTLR start synpred180_Java
	public final void synpred180_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:687:9:
		// ( 'case' constantExpression ':' )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:687:9:
		// 'case' constantExpression ':'
		{
			match(input, 91, FOLLOW_91_in_synpred180_Java3890);
			if (state.failed)
				return;
			pushFollow(FOLLOW_constantExpression_in_synpred180_Java3892);
			constantExpression();

			state._fsp--;
			if (state.failed)
				return;
			match(input, 77, FOLLOW_77_in_synpred180_Java3894);
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred180_Java

	// $ANTLR start synpred181_Java
	public final void synpred181_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:688:9:
		// ( 'case' enumConstantName ':' )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:688:9:
		// 'case' enumConstantName ':'
		{
			match(input, 91, FOLLOW_91_in_synpred181_Java3904);
			if (state.failed)
				return;
			pushFollow(FOLLOW_enumConstantName_in_synpred181_Java3906);
			enumConstantName();

			state._fsp--;
			if (state.failed)
				return;
			match(input, 77, FOLLOW_77_in_synpred181_Java3908);
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred181_Java

	// $ANTLR start synpred182_Java
	public final void synpred182_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:694:9:
		// ( enhancedForControl )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:694:9:
		// enhancedForControl
		{
			pushFollow(FOLLOW_enhancedForControl_in_synpred182_Java3951);
			enhancedForControl();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred182_Java

	// $ANTLR start synpred186_Java
	public final void synpred186_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:699:9:
		// ( localVariableDeclaration )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:699:9:
		// localVariableDeclaration
		{
			pushFollow(FOLLOW_localVariableDeclaration_in_synpred186_Java3991);
			localVariableDeclaration();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred186_Java

	// $ANTLR start synpred188_Java
	public final void synpred188_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:730:32:
		// ( assignmentOperator expression )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:730:32:
		// assignmentOperator expression
		{
			pushFollow(FOLLOW_assignmentOperator_in_synpred188_Java4174);
			assignmentOperator();

			state._fsp--;
			if (state.failed)
				return;
			pushFollow(FOLLOW_expression_in_synpred188_Java4176);
			expression();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred188_Java

	// $ANTLR start synpred198_Java
	public final void synpred198_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:743:9:
		// ( '<' '<' '=' )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:743:10:
		// '<' '<' '='
		{
			match(input, 42, FOLLOW_42_in_synpred198_Java4292);
			if (state.failed)
				return;
			match(input, 42, FOLLOW_42_in_synpred198_Java4294);
			if (state.failed)
				return;
			match(input, 53, FOLLOW_53_in_synpred198_Java4296);
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred198_Java

	// $ANTLR start synpred199_Java
	public final void synpred199_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:748:9:
		// ( '>' '>' '>' '=' )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:748:10:
		// '>' '>' '>' '='
		{
			match(input, 44, FOLLOW_44_in_synpred199_Java4332);
			if (state.failed)
				return;
			match(input, 44, FOLLOW_44_in_synpred199_Java4334);
			if (state.failed)
				return;
			match(input, 44, FOLLOW_44_in_synpred199_Java4336);
			if (state.failed)
				return;
			match(input, 53, FOLLOW_53_in_synpred199_Java4338);
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred199_Java

	// $ANTLR start synpred200_Java
	public final void synpred200_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:755:9:
		// ( '>' '>' '=' )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:755:10:
		// '>' '>' '='
		{
			match(input, 44, FOLLOW_44_in_synpred200_Java4377);
			if (state.failed)
				return;
			match(input, 44, FOLLOW_44_in_synpred200_Java4379);
			if (state.failed)
				return;
			match(input, 53, FOLLOW_53_in_synpred200_Java4381);
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred200_Java

	// $ANTLR start synpred211_Java
	public final void synpred211_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:799:9:
		// ( '<' '=' )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:799:10:
		// '<' '='
		{
			match(input, 42, FOLLOW_42_in_synpred211_Java4689);
			if (state.failed)
				return;
			match(input, 53, FOLLOW_53_in_synpred211_Java4691);
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred211_Java

	// $ANTLR start synpred212_Java
	public final void synpred212_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:802:9:
		// ( '>' '=' )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:802:10:
		// '>' '='
		{
			match(input, 44, FOLLOW_44_in_synpred212_Java4723);
			if (state.failed)
				return;
			match(input, 53, FOLLOW_53_in_synpred212_Java4725);
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred212_Java

	// $ANTLR start synpred215_Java
	public final void synpred215_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:814:9:
		// ( '<' '<' )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:814:10:
		// '<' '<'
		{
			match(input, 42, FOLLOW_42_in_synpred215_Java4816);
			if (state.failed)
				return;
			match(input, 42, FOLLOW_42_in_synpred215_Java4818);
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred215_Java

	// $ANTLR start synpred216_Java
	public final void synpred216_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:817:9:
		// ( '>' '>' '>' )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:817:10:
		// '>' '>' '>'
		{
			match(input, 44, FOLLOW_44_in_synpred216_Java4850);
			if (state.failed)
				return;
			match(input, 44, FOLLOW_44_in_synpred216_Java4852);
			if (state.failed)
				return;
			match(input, 44, FOLLOW_44_in_synpred216_Java4854);
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred216_Java

	// $ANTLR start synpred217_Java
	public final void synpred217_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:822:9:
		// ( '>' '>' )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:822:10:
		// '>' '>'
		{
			match(input, 44, FOLLOW_44_in_synpred217_Java4890);
			if (state.failed)
				return;
			match(input, 44, FOLLOW_44_in_synpred217_Java4892);
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred217_Java

	// $ANTLR start synpred229_Java
	public final void synpred229_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:847:9:
		// ( castExpression )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:847:9:
		// castExpression
		{
			pushFollow(FOLLOW_castExpression_in_synpred229_Java5101);
			castExpression();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred229_Java

	// $ANTLR start synpred233_Java
	public final void synpred233_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:852:8:
		// ( '(' primitiveType ')' unaryExpression )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:852:8:
		// '(' primitiveType ')' unaryExpression
		{
			match(input, 68, FOLLOW_68_in_synpred233_Java5139);
			if (state.failed)
				return;
			pushFollow(FOLLOW_primitiveType_in_synpred233_Java5141);
			primitiveType();

			state._fsp--;
			if (state.failed)
				return;
			match(input, 69, FOLLOW_69_in_synpred233_Java5143);
			if (state.failed)
				return;
			pushFollow(FOLLOW_unaryExpression_in_synpred233_Java5145);
			unaryExpression();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred233_Java

	// $ANTLR start synpred234_Java
	public final void synpred234_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:853:13:
		// ( type )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:853:13:
		// type
		{
			pushFollow(FOLLOW_type_in_synpred234_Java5157);
			type();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred234_Java

	// $ANTLR start synpred236_Java
	public final void synpred236_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:858:17:
		// ( '.' Identifier )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:858:17:
		// '.' Identifier
		{
			match(input, 31, FOLLOW_31_in_synpred236_Java5198);
			if (state.failed)
				return;
			match(input, Identifier, FOLLOW_Identifier_in_synpred236_Java5200);
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred236_Java

	// $ANTLR start synpred237_Java
	public final void synpred237_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:858:34:
		// ( identifierSuffix )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:858:34:
		// identifierSuffix
		{
			pushFollow(FOLLOW_identifierSuffix_in_synpred237_Java5204);
			identifierSuffix();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred237_Java

	// $ANTLR start synpred242_Java
	public final void synpred242_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:862:21:
		// ( '.' Identifier )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:862:21:
		// '.' Identifier
		{
			match(input, 31, FOLLOW_31_in_synpred242_Java5252);
			if (state.failed)
				return;
			match(input, Identifier, FOLLOW_Identifier_in_synpred242_Java5254);
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred242_Java

	// $ANTLR start synpred243_Java
	public final void synpred243_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:862:38:
		// ( identifierSuffix )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:862:38:
		// identifierSuffix
		{
			pushFollow(FOLLOW_identifierSuffix_in_synpred243_Java5258);
			identifierSuffix();

			state._fsp--;
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred243_Java

	// $ANTLR start synpred249_Java
	public final void synpred249_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:869:10:
		// ( '[' expression ']' )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:869:10:
		// '[' expression ']'
		{
			match(input, 50, FOLLOW_50_in_synpred249_Java5333);
			if (state.failed)
				return;
			pushFollow(FOLLOW_expression_in_synpred249_Java5335);
			expression();

			state._fsp--;
			if (state.failed)
				return;
			match(input, 51, FOLLOW_51_in_synpred249_Java5337);
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred249_Java

	// $ANTLR start synpred262_Java
	public final void synpred262_Java_fragment() throws RecognitionException {
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:895:29:
		// ( '[' expression ']' )
		// /home/ludwig/privat/PureSolTechnologiesAPI/src/com/puresol/coding/java/antlr/Java.g:895:29:
		// '[' expression ']'
		{
			match(input, 50, FOLLOW_50_in_synpred262_Java5573);
			if (state.failed)
				return;
			pushFollow(FOLLOW_expression_in_synpred262_Java5575);
			expression();

			state._fsp--;
			if (state.failed)
				return;
			match(input, 51, FOLLOW_51_in_synpred262_Java5577);
			if (state.failed)
				return;

		}
	}

	// $ANTLR end synpred262_Java

	// Delegated rules

	public final boolean synpred157_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred157_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred211_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred211_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred249_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred249_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred243_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred243_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred5_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred5_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred229_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred229_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred178_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred178_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred215_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred215_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred113_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred113_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred151_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred151_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred117_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred117_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred162_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred162_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred217_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred217_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred186_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred186_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred188_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred188_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred212_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred212_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred163_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred163_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred152_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred152_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred242_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred242_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred199_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred199_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred216_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred216_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred236_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred236_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred262_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred262_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred13_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred13_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred198_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred198_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred233_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred233_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred180_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred180_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred128_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred128_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred200_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred200_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred234_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred234_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred182_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred182_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred181_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred181_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	public final boolean synpred237_Java() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred237_Java_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	protected DFA8 dfa8 = new DFA8(this);
	protected DFA13 dfa13 = new DFA13(this);
	protected DFA81 dfa81 = new DFA81(this);
	protected DFA85 dfa85 = new DFA85(this);
	protected DFA106 dfa106 = new DFA106(this);
	protected DFA114 dfa114 = new DFA114(this);
	protected DFA123 dfa123 = new DFA123(this);
	protected DFA124 dfa124 = new DFA124(this);
	protected DFA126 dfa126 = new DFA126(this);
	protected DFA127 dfa127 = new DFA127(this);
	protected DFA139 dfa139 = new DFA139(this);
	protected DFA145 dfa145 = new DFA145(this);
	protected DFA146 dfa146 = new DFA146(this);
	protected DFA149 dfa149 = new DFA149(this);
	protected DFA151 dfa151 = new DFA151(this);
	protected DFA156 dfa156 = new DFA156(this);
	protected DFA155 dfa155 = new DFA155(this);
	protected DFA162 dfa162 = new DFA162(this);
	static final String DFA8_eotS = "\21\uffff";
	static final String DFA8_eofS = "\1\2\20\uffff";
	static final String DFA8_minS = "\1\7\1\0\17\uffff";
	static final String DFA8_maxS = "\1\113\1\0\17\uffff";
	static final String DFA8_acceptS = "\2\uffff\1\2\15\uffff\1\1";
	static final String DFA8_specialS = "\1\uffff\1\0\17\uffff}>";
	static final String[] DFA8_transitionS = {
			"\1\2\23\uffff\4\2\2\uffff\7\2\10\uffff\1\2\32\uffff\1\1",
			"\1\uffff", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
			"" };

	static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
	static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
	static final char[] DFA8_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA8_minS);
	static final char[] DFA8_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA8_maxS);
	static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
	static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
	static final short[][] DFA8_transition;

	static {
		int numStates = DFA8_transitionS.length;
		DFA8_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
		}
	}

	class DFA8 extends DFA {

		public DFA8(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 8;
			this.eot = DFA8_eot;
			this.eof = DFA8_eof;
			this.min = DFA8_min;
			this.max = DFA8_max;
			this.accept = DFA8_accept;
			this.special = DFA8_special;
			this.transition = DFA8_transition;
		}

		public String getDescription() {
			return "196:1: compilationUnit : ( annotations ( packageDeclaration ( importDeclaration )* ( typeDeclaration )* | classOrInterfaceDeclaration ( typeDeclaration )* ) | ( packageDeclaration )? ( importDeclaration )* ( typeDeclaration )* );";
		}

		public int specialStateTransition(int s, IntStream _input)
				throws NoViableAltException {
			TokenStream input = (TokenStream) _input;
			int _s = s;
			switch (s) {
			case 0:
				int LA8_1 = input.LA(1);

				int index8_1 = input.index();
				input.rewind();
				s = -1;
				if ((synpred5_Java())) {
					s = 16;
				}

				else if ((true)) {
					s = 2;
				}

				input.seek(index8_1);
				if (s >= 0)
					return s;
				break;
			}
			if (state.backtracking > 0) {
				state.failed = true;
				return -1;
			}
			NoViableAltException nvae = new NoViableAltException(
					getDescription(), 8, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	static final String DFA13_eotS = "\14\uffff";
	static final String DFA13_eofS = "\14\uffff";
	static final String DFA13_minS = "\1\7\10\0\3\uffff";
	static final String DFA13_maxS = "\1\113\10\0\3\uffff";
	static final String DFA13_acceptS = "\11\uffff\1\1\1\uffff\1\2";
	static final String DFA13_specialS = "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\3\uffff}>";
	static final String[] DFA13_transitionS = {
			"\1\11\26\uffff\1\6\2\uffff\1\2\1\3\1\4\1\5\1\7\1\10\1\11\10"
					+ "\uffff\1\13\32\uffff\1\1", "\1\uffff", "\1\uffff",
			"\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff",
			"\1\uffff", "", "", "" };

	static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
	static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
	static final char[] DFA13_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA13_minS);
	static final char[] DFA13_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA13_maxS);
	static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
	static final short[] DFA13_special = DFA
			.unpackEncodedString(DFA13_specialS);
	static final short[][] DFA13_transition;

	static {
		int numStates = DFA13_transitionS.length;
		DFA13_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
		}
	}

	class DFA13 extends DFA {

		public DFA13(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 13;
			this.eot = DFA13_eot;
			this.eof = DFA13_eof;
			this.min = DFA13_min;
			this.max = DFA13_max;
			this.accept = DFA13_accept;
			this.special = DFA13_special;
			this.transition = DFA13_transition;
		}

		public String getDescription() {
			return "221:1: classOrInterfaceDeclaration : ( classOrInterfaceModifiers classDeclaration -> ^( CODERANGE_CLASS classOrInterfaceModifiers classDeclaration ) | classOrInterfaceModifiers interfaceDeclaration -> ^( CODERANGE_CLASS classOrInterfaceModifiers interfaceDeclaration ) );";
		}

		public int specialStateTransition(int s, IntStream _input)
				throws NoViableAltException {
			TokenStream input = (TokenStream) _input;
			int _s = s;
			switch (s) {
			case 0:
				int index13_1 = input.index();
				input.rewind();
				s = -1;
				if ((synpred13_Java())) {
					s = 9;
				}

				else if ((true)) {
					s = 11;
				}

				input.seek(index13_1);
				if (s >= 0)
					return s;
				break;
			case 1:
				int index13_2 = input.index();
				input.rewind();
				s = -1;
				if ((synpred13_Java())) {
					s = 9;
				}

				else if ((true)) {
					s = 11;
				}

				input.seek(index13_2);
				if (s >= 0)
					return s;
				break;
			case 2:
				int index13_3 = input.index();
				input.rewind();
				s = -1;
				if ((synpred13_Java())) {
					s = 9;
				}

				else if ((true)) {
					s = 11;
				}

				input.seek(index13_3);
				if (s >= 0)
					return s;
				break;
			case 3:
				int index13_4 = input.index();
				input.rewind();
				s = -1;
				if ((synpred13_Java())) {
					s = 9;
				}

				else if ((true)) {
					s = 11;
				}

				input.seek(index13_4);
				if (s >= 0)
					return s;
				break;
			case 4:
				int LA13_5 = input.LA(1);

				int index13_5 = input.index();
				input.rewind();
				s = -1;
				if ((synpred13_Java())) {
					s = 9;
				}

				else if ((true)) {
					s = 11;
				}

				input.seek(index13_5);
				if (s >= 0)
					return s;
				break;
			case 5:
				int index13_6 = input.index();
				input.rewind();
				s = -1;
				if ((synpred13_Java())) {
					s = 9;
				}

				else if ((true)) {
					s = 11;
				}

				input.seek(index13_6);
				if (s >= 0)
					return s;
				break;
			case 6:
				int index13_7 = input.index();
				input.rewind();
				s = -1;
				if ((synpred13_Java())) {
					s = 9;
				}

				else if ((true)) {
					s = 11;
				}

				input.seek(index13_7);
				if (s >= 0)
					return s;
				break;
			case 7:
				int index13_8 = input.index();
				input.rewind();
				s = -1;
				if ((synpred13_Java())) {
					s = 9;
				}

				else if ((true)) {
					s = 11;
				}

				input.seek(index13_8);
				if (s >= 0)
					return s;
				break;
			}
			if (state.backtracking > 0) {
				state.failed = true;
				return -1;
			}
			NoViableAltException nvae = new NoViableAltException(
					getDescription(), 13, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	static final String DFA81_eotS = "\57\uffff";
	static final String DFA81_eofS = "\57\uffff";
	static final String DFA81_minS = "\1\6\1\uffff\15\0\40\uffff";
	static final String DFA81_maxS = "\1\163\1\uffff\15\0\40\uffff";
	static final String DFA81_acceptS = "\1\uffff\1\1\15\uffff\1\2\37\uffff";
	static final String DFA81_specialS = "\2\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14"
			+ "\40\uffff}>";
	static final String[] DFA81_transitionS = {
			"\1\14\1\17\1\6\1\7\1\10\3\5\1\17\15\uffff\1\17\1\uffff\1\17"
					+ "\2\uffff\7\17\2\uffff\1\1\3\uffff\3\17\1\16\5\uffff\1\17\2\uffff"
					+ "\10\15\1\uffff\1\4\1\3\2\uffff\1\2\1\12\2\11\1\17\2\uffff\1"
					+ "\17\1\uffff\4\17\1\uffff\5\17\21\uffff\2\17\2\uffff\4\17\1\13",
			"", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff",
			"\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff",
			"\1\uffff", "\1\uffff", "\1\uffff", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "" };

	static final short[] DFA81_eot = DFA.unpackEncodedString(DFA81_eotS);
	static final short[] DFA81_eof = DFA.unpackEncodedString(DFA81_eofS);
	static final char[] DFA81_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA81_minS);
	static final char[] DFA81_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA81_maxS);
	static final short[] DFA81_accept = DFA.unpackEncodedString(DFA81_acceptS);
	static final short[] DFA81_special = DFA
			.unpackEncodedString(DFA81_specialS);
	static final short[][] DFA81_transition;

	static {
		int numStates = DFA81_transitionS.length;
		DFA81_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA81_transition[i] = DFA.unpackEncodedString(DFA81_transitionS[i]);
		}
	}

	class DFA81 extends DFA {

		public DFA81(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 81;
			this.eot = DFA81_eot;
			this.eof = DFA81_eof;
			this.min = DFA81_min;
			this.max = DFA81_max;
			this.accept = DFA81_accept;
			this.special = DFA81_special;
			this.transition = DFA81_transition;
		}

		public String getDescription() {
			return "513:13: ( explicitConstructorInvocation )?";
		}

		public int specialStateTransition(int s, IntStream _input)
				throws NoViableAltException {
			TokenStream input = (TokenStream) _input;
			int _s = s;
			switch (s) {
			case 0:
				int LA81_2 = input.LA(1);

				int index81_2 = input.index();
				input.rewind();
				s = -1;
				if ((synpred113_Java())) {
					s = 1;
				}

				else if ((true)) {
					s = 15;
				}

				input.seek(index81_2);
				if (s >= 0)
					return s;
				break;
			case 1:
				int LA81_3 = input.LA(1);

				int index81_3 = input.index();
				input.rewind();
				s = -1;
				if ((synpred113_Java())) {
					s = 1;
				}

				else if ((true)) {
					s = 15;
				}

				input.seek(index81_3);
				if (s >= 0)
					return s;
				break;
			case 2:
				int LA81_4 = input.LA(1);

				int index81_4 = input.index();
				input.rewind();
				s = -1;
				if ((synpred113_Java())) {
					s = 1;
				}

				else if ((true)) {
					s = 15;
				}

				input.seek(index81_4);
				if (s >= 0)
					return s;
				break;
			case 3:
				int LA81_5 = input.LA(1);

				int index81_5 = input.index();
				input.rewind();
				s = -1;
				if ((synpred113_Java())) {
					s = 1;
				}

				else if ((true)) {
					s = 15;
				}

				input.seek(index81_5);
				if (s >= 0)
					return s;
				break;
			case 4:
				int LA81_6 = input.LA(1);

				int index81_6 = input.index();
				input.rewind();
				s = -1;
				if ((synpred113_Java())) {
					s = 1;
				}

				else if ((true)) {
					s = 15;
				}

				input.seek(index81_6);
				if (s >= 0)
					return s;
				break;
			case 5:
				int LA81_7 = input.LA(1);

				int index81_7 = input.index();
				input.rewind();
				s = -1;
				if ((synpred113_Java())) {
					s = 1;
				}

				else if ((true)) {
					s = 15;
				}

				input.seek(index81_7);
				if (s >= 0)
					return s;
				break;
			case 6:
				int LA81_8 = input.LA(1);

				int index81_8 = input.index();
				input.rewind();
				s = -1;
				if ((synpred113_Java())) {
					s = 1;
				}

				else if ((true)) {
					s = 15;
				}

				input.seek(index81_8);
				if (s >= 0)
					return s;
				break;
			case 7:
				int LA81_9 = input.LA(1);

				int index81_9 = input.index();
				input.rewind();
				s = -1;
				if ((synpred113_Java())) {
					s = 1;
				}

				else if ((true)) {
					s = 15;
				}

				input.seek(index81_9);
				if (s >= 0)
					return s;
				break;
			case 8:
				int LA81_10 = input.LA(1);

				int index81_10 = input.index();
				input.rewind();
				s = -1;
				if ((synpred113_Java())) {
					s = 1;
				}

				else if ((true)) {
					s = 15;
				}

				input.seek(index81_10);
				if (s >= 0)
					return s;
				break;
			case 9:
				int LA81_11 = input.LA(1);

				int index81_11 = input.index();
				input.rewind();
				s = -1;
				if ((synpred113_Java())) {
					s = 1;
				}

				else if ((true)) {
					s = 15;
				}

				input.seek(index81_11);
				if (s >= 0)
					return s;
				break;
			case 10:
				int LA81_12 = input.LA(1);

				int index81_12 = input.index();
				input.rewind();
				s = -1;
				if ((synpred113_Java())) {
					s = 1;
				}

				else if ((true)) {
					s = 15;
				}

				input.seek(index81_12);
				if (s >= 0)
					return s;
				break;
			case 11:
				int LA81_13 = input.LA(1);

				int index81_13 = input.index();
				input.rewind();
				s = -1;
				if ((synpred113_Java())) {
					s = 1;
				}

				else if ((true)) {
					s = 15;
				}

				input.seek(index81_13);
				if (s >= 0)
					return s;
				break;
			case 12:
				int LA81_14 = input.LA(1);

				int index81_14 = input.index();
				input.rewind();
				s = -1;
				if ((synpred113_Java())) {
					s = 1;
				}

				else if ((true)) {
					s = 15;
				}

				input.seek(index81_14);
				if (s >= 0)
					return s;
				break;
			}
			if (state.backtracking > 0) {
				state.failed = true;
				return -1;
			}
			NoViableAltException nvae = new NoViableAltException(
					getDescription(), 81, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	static final String DFA85_eotS = "\17\uffff";
	static final String DFA85_eofS = "\17\uffff";
	static final String DFA85_minS = "\1\6\1\uffff\1\0\1\uffff\1\0\12\uffff";
	static final String DFA85_maxS = "\1\163\1\uffff\1\0\1\uffff\1\0\12\uffff";
	static final String DFA85_acceptS = "\1\uffff\1\1\1\uffff\1\2\13\uffff";
	static final String DFA85_specialS = "\2\uffff\1\0\1\uffff\1\1\12\uffff}>";
	static final String[] DFA85_transitionS = {
			"\1\3\1\uffff\6\3\34\uffff\1\1\6\uffff\1\3\10\uffff\10\3\1\uffff"
					+ "\1\4\1\3\2\uffff\1\2\3\3\50\uffff\1\3", "", "\1\uffff",
			"", "\1\uffff", "", "", "", "", "", "", "", "", "", "" };

	static final short[] DFA85_eot = DFA.unpackEncodedString(DFA85_eotS);
	static final short[] DFA85_eof = DFA.unpackEncodedString(DFA85_eofS);
	static final char[] DFA85_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA85_minS);
	static final char[] DFA85_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA85_maxS);
	static final short[] DFA85_accept = DFA.unpackEncodedString(DFA85_acceptS);
	static final short[] DFA85_special = DFA
			.unpackEncodedString(DFA85_specialS);
	static final short[][] DFA85_transition;

	static {
		int numStates = DFA85_transitionS.length;
		DFA85_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA85_transition[i] = DFA.unpackEncodedString(DFA85_transitionS[i]);
		}
	}

	class DFA85 extends DFA {

		public DFA85(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 85;
			this.eot = DFA85_eot;
			this.eof = DFA85_eof;
			this.min = DFA85_min;
			this.max = DFA85_max;
			this.accept = DFA85_accept;
			this.special = DFA85_special;
			this.transition = DFA85_transition;
		}

		public String getDescription() {
			return "516:1: explicitConstructorInvocation : ( ( nonWildcardTypeArguments )? ( 'this' | 'super' ) arguments ';' | primary '.' ( nonWildcardTypeArguments )? 'super' arguments ';' );";
		}

		public int specialStateTransition(int s, IntStream _input)
				throws NoViableAltException {
			TokenStream input = (TokenStream) _input;
			int _s = s;
			switch (s) {
			case 0:
				int LA85_2 = input.LA(1);

				int index85_2 = input.index();
				input.rewind();
				s = -1;
				if ((synpred117_Java())) {
					s = 1;
				}

				else if ((true)) {
					s = 3;
				}

				input.seek(index85_2);
				if (s >= 0)
					return s;
				break;
			case 1:
				int LA85_4 = input.LA(1);

				int index85_4 = input.index();
				input.rewind();
				s = -1;
				if ((synpred117_Java())) {
					s = 1;
				}

				else if ((true)) {
					s = 3;
				}

				input.seek(index85_4);
				if (s >= 0)
					return s;
				break;
			}
			if (state.backtracking > 0) {
				state.failed = true;
				return -1;
			}
			NoViableAltException nvae = new NoViableAltException(
					getDescription(), 85, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	static final String DFA106_eotS = "\56\uffff";
	static final String DFA106_eofS = "\56\uffff";
	static final String DFA106_minS = "\1\6\4\0\51\uffff";
	static final String DFA106_maxS = "\1\163\4\0\51\uffff";
	static final String DFA106_acceptS = "\5\uffff\1\2\10\uffff\1\3\36\uffff\1\1";
	static final String DFA106_specialS = "\1\uffff\1\0\1\1\1\2\1\3\51\uffff}>";
	static final String[] DFA106_transitionS = {
			"\1\3\1\5\7\16\15\uffff\1\16\1\uffff\1\5\2\uffff\4\5\1\1\2\5"
					+ "\6\uffff\1\16\1\uffff\1\5\1\16\5\uffff\1\16\2\uffff\10\4\1\uffff"
					+ "\2\16\2\uffff\4\16\1\2\2\uffff\1\16\1\uffff\4\16\1\uffff\5\16"
					+ "\21\uffff\2\16\2\uffff\5\16", "\1\uffff", "\1\uffff",
			"\1\uffff", "\1\uffff", "", "", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "", "", "", "" };

	static final short[] DFA106_eot = DFA.unpackEncodedString(DFA106_eotS);
	static final short[] DFA106_eof = DFA.unpackEncodedString(DFA106_eofS);
	static final char[] DFA106_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA106_minS);
	static final char[] DFA106_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA106_maxS);
	static final short[] DFA106_accept = DFA
			.unpackEncodedString(DFA106_acceptS);
	static final short[] DFA106_special = DFA
			.unpackEncodedString(DFA106_specialS);
	static final short[][] DFA106_transition;

	static {
		int numStates = DFA106_transitionS.length;
		DFA106_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA106_transition[i] = DFA
					.unpackEncodedString(DFA106_transitionS[i]);
		}
	}

	class DFA106 extends DFA {

		public DFA106(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 106;
			this.eot = DFA106_eot;
			this.eof = DFA106_eof;
			this.min = DFA106_min;
			this.max = DFA106_max;
			this.accept = DFA106_accept;
			this.special = DFA106_special;
			this.transition = DFA106_transition;
		}

		public String getDescription() {
			return "621:1: blockStatement : ( localVariableDeclarationStatement | classOrInterfaceDeclaration | statement );";
		}

		public int specialStateTransition(int s, IntStream _input)
				throws NoViableAltException {
			TokenStream input = (TokenStream) _input;
			int _s = s;
			switch (s) {
			case 0:
				int index106_1 = input.index();
				input.rewind();
				s = -1;
				if ((synpred151_Java())) {
					s = 45;
				}

				else if ((synpred152_Java())) {
					s = 5;
				}

				input.seek(index106_1);
				if (s >= 0)
					return s;
				break;
			case 1:
				int index106_2 = input.index();
				input.rewind();
				s = -1;
				if ((synpred151_Java())) {
					s = 45;
				}

				else if ((synpred152_Java())) {
					s = 5;
				}

				input.seek(index106_2);
				if (s >= 0)
					return s;
				break;
			case 2:
				int index106_3 = input.index();
				input.rewind();
				s = -1;
				if ((synpred151_Java())) {
					s = 45;
				}

				else if ((true)) {
					s = 14;
				}

				input.seek(index106_3);
				if (s >= 0)
					return s;
				break;
			case 3:
				int index106_4 = input.index();
				input.rewind();
				s = -1;
				if ((synpred151_Java())) {
					s = 45;
				}

				else if ((true)) {
					s = 14;
				}

				input.seek(index106_4);
				if (s >= 0)
					return s;
				break;
			}
			if (state.backtracking > 0) {
				state.failed = true;
				return -1;
			}
			NoViableAltException nvae = new NoViableAltException(
					getDescription(), 106, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	static final String DFA114_eotS = "\22\uffff";
	static final String DFA114_eofS = "\22\uffff";
	static final String DFA114_minS = "\1\6\17\uffff\1\34\1\uffff";
	static final String DFA114_maxS = "\1\163\17\uffff\1\160\1\uffff";
	static final String DFA114_acceptS = "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"
			+ "\15\1\16\1\17\1\uffff\1\20";
	static final String DFA114_specialS = "\22\uffff}>";
	static final String[] DFA114_transitionS = {
			"\1\20\1\uffff\6\17\1\2\15\uffff\1\16\21\uffff\1\1\2\uffff\1"
					+ "\17\5\uffff\1\11\2\uffff\10\17\1\uffff\2\17\2\uffff\4\17\3\uffff"
					+ "\1\3\1\uffff\1\4\1\5\1\6\1\7\1\uffff\1\10\1\12\1\13\1\14\1\15"
					+ "\21\uffff\2\17\2\uffff\5\17",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\17\2\uffff\2\17\11\uffff\1\17\1\uffff\2\17\4\uffff\1\17"
					+ "\2\uffff\1\17\14\uffff\1\17\1\uffff\1\17\10\uffff\1\21\16\uffff"
					+ "\25\17", "" };

	static final short[] DFA114_eot = DFA.unpackEncodedString(DFA114_eotS);
	static final short[] DFA114_eof = DFA.unpackEncodedString(DFA114_eofS);
	static final char[] DFA114_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA114_minS);
	static final char[] DFA114_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA114_maxS);
	static final short[] DFA114_accept = DFA
			.unpackEncodedString(DFA114_acceptS);
	static final short[] DFA114_special = DFA
			.unpackEncodedString(DFA114_specialS);
	static final short[][] DFA114_transition;

	static {
		int numStates = DFA114_transitionS.length;
		DFA114_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA114_transition[i] = DFA
					.unpackEncodedString(DFA114_transitionS[i]);
		}
	}

	class DFA114 extends DFA {

		public DFA114(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 114;
			this.eot = DFA114_eot;
			this.eof = DFA114_eof;
			this.min = DFA114_min;
			this.max = DFA114_max;
			this.accept = DFA114_accept;
			this.special = DFA114_special;
			this.transition = DFA114_transition;
		}

		public String getDescription() {
			return "639:1: statement : ( block | ASSERT expression ( ':' expression )? ';' | 'if' parExpression statement ( options {k=1; } : 'else' statement )? | 'for' '(' forControl ')' statement | 'while' parExpression statement | 'do' statement 'while' parExpression ';' | 'try' block ( catches 'finally' block | catches | 'finally' block ) | 'switch' parExpression '{' switchBlockStatementGroups '}' | 'synchronized' parExpression block | 'return' ( expression )? ';' | 'throw' expression ';' | 'break' ( Identifier )? ';' | 'continue' ( Identifier )? ';' | ';' | statementExpression ';' | Identifier ':' statement );";
		}
	}

	static final String DFA123_eotS = "\u0087\uffff";
	static final String DFA123_eofS = "\u0087\uffff";
	static final String DFA123_minS = "\5\6\22\uffff\10\6\1\34\30\uffff\1\63\1\34\1\uffff\21\0\22\uffff"
			+ "\2\0\1\uffff\2\0\5\uffff\1\0\30\uffff\1\0\5\uffff";
	static final String DFA123_maxS = "\1\163\1\113\1\6\1\160\1\62\22\uffff\2\62\1\113\1\6\1\113\3\163"
			+ "\1\115\30\uffff\1\63\1\115\1\uffff\21\0\22\uffff\2\0\1\uffff\2\0"
			+ "\5\uffff\1\0\30\uffff\1\0\5\uffff";
	static final String DFA123_acceptS = "\5\uffff\1\2\166\uffff\1\1\12\uffff";
	static final String DFA123_specialS = "\73\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1"
			+ "\14\1\15\1\16\1\17\1\20\22\uffff\1\21\1\22\1\uffff\1\23\1\24\5\uffff"
			+ "\1\25\30\uffff\1\26\5\uffff}>";
	static final String[] DFA123_transitionS = {
			"\1\3\1\uffff\6\5\16\uffff\1\5\10\uffff\1\1\13\uffff\1\5\10\uffff"
					+ "\10\4\1\uffff\2\5\2\uffff\4\5\1\2\37\uffff\2\5\2\uffff\5\5",
			"\1\27\36\uffff\1\31\24\uffff\10\30\11\uffff\1\32",
			"\1\33",
			"\1\37\25\uffff\1\5\2\uffff\1\35\1\5\11\uffff\1\34\3\5\4\uffff"
					+ "\1\36\2\uffff\1\5\14\uffff\1\5\1\uffff\1\5\27\uffff\25\5",
			"\1\71\30\uffff\1\5\22\uffff\1\70",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\76\30\uffff\1\74\12\uffff\1\73\7\uffff\1\75",
			"\1\100\53\uffff\1\77",
			"\1\101\36\uffff\1\103\24\uffff\10\102\11\uffff\1\104",
			"\1\105",
			"\1\110\30\uffff\1\106\5\uffff\1\112\24\uffff\10\111\2\uffff"
					+ "\1\107\6\uffff\1\113",
			"\1\136\1\uffff\6\5\34\uffff\1\5\6\uffff\1\5\3\uffff\1\5\4\uffff"
					+ "\10\137\1\141\2\5\2\uffff\4\5\40\uffff\2\5\2\uffff\5\5",
			"\1\142\40\uffff\1\5\2\uffff\1\5\30\uffff\1\5\3\uffff\1\5\53"
					+ "\uffff\1\5",
			"\1\5\1\uffff\6\5\43\uffff\1\5\1\uffff\1\150\6\uffff\10\5\1"
					+ "\uffff\2\5\2\uffff\4\5\40\uffff\2\5\2\uffff\5\5",
			"\1\5\16\uffff\1\5\6\uffff\1\5\2\uffff\1\5\27\uffff\1\174", "", "",
			"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "\1\u0081",
			"\1\5\16\uffff\1\5\6\uffff\1\5\2\uffff\1\5\27\uffff\1\174", "",
			"\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff",
			"\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff",
			"\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff",
			"\1\uffff", "\1\uffff", "", "", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "\1\uffff", "\1\uffff", "", "\1\uffff",
			"\1\uffff", "", "", "", "", "", "\1\uffff", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
			"", "\1\uffff", "", "", "", "", "" };

	static final short[] DFA123_eot = DFA.unpackEncodedString(DFA123_eotS);
	static final short[] DFA123_eof = DFA.unpackEncodedString(DFA123_eofS);
	static final char[] DFA123_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA123_minS);
	static final char[] DFA123_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA123_maxS);
	static final short[] DFA123_accept = DFA
			.unpackEncodedString(DFA123_acceptS);
	static final short[] DFA123_special = DFA
			.unpackEncodedString(DFA123_specialS);
	static final short[][] DFA123_transition;

	static {
		int numStates = DFA123_transitionS.length;
		DFA123_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA123_transition[i] = DFA
					.unpackEncodedString(DFA123_transitionS[i]);
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
			return "692:1: forControl options {k=3; } : ( enhancedForControl | ( forInit )? ';' ( expression )? ';' ( forUpdate )? );";
		}

		public int specialStateTransition(int s, IntStream _input)
				throws NoViableAltException {
			TokenStream input = (TokenStream) _input;
			int _s = s;
			switch (s) {
			case 0:
				int index123_59 = input.index();
				input.rewind();
				s = -1;
				if ((synpred182_Java())) {
					s = 124;
				}

				else if ((true)) {
					s = 5;
				}

				input.seek(index123_59);
				if (s >= 0)
					return s;
				break;
			case 1:
				int index123_60 = input.index();
				input.rewind();
				s = -1;
				if ((synpred182_Java())) {
					s = 124;
				}

				else if ((true)) {
					s = 5;
				}

				input.seek(index123_60);
				if (s >= 0)
					return s;
				break;
			case 2:
				int index123_61 = input.index();
				input.rewind();
				s = -1;
				if ((synpred182_Java())) {
					s = 124;
				}

				else if ((true)) {
					s = 5;
				}

				input.seek(index123_61);
				if (s >= 0)
					return s;
				break;
			case 3:
				int index123_62 = input.index();
				input.rewind();
				s = -1;
				if ((synpred182_Java())) {
					s = 124;
				}

				else if ((true)) {
					s = 5;
				}

				input.seek(index123_62);
				if (s >= 0)
					return s;
				break;
			case 4:
				int index123_63 = input.index();
				input.rewind();
				s = -1;
				if ((synpred182_Java())) {
					s = 124;
				}

				else if ((true)) {
					s = 5;
				}

				input.seek(index123_63);
				if (s >= 0)
					return s;
				break;
			case 5:
				int index123_64 = input.index();
				input.rewind();
				s = -1;
				if ((synpred182_Java())) {
					s = 124;
				}

				else if ((true)) {
					s = 5;
				}

				input.seek(index123_64);
				if (s >= 0)
					return s;
				break;
			case 6:
				int index123_65 = input.index();
				input.rewind();
				s = -1;
				if ((synpred182_Java())) {
					s = 124;
				}

				else if ((true)) {
					s = 5;
				}

				input.seek(index123_65);
				if (s >= 0)
					return s;
				break;
			case 7:
				int index123_66 = input.index();
				input.rewind();
				s = -1;
				if ((synpred182_Java())) {
					s = 124;
				}

				else if ((true)) {
					s = 5;
				}

				input.seek(index123_66);
				if (s >= 0)
					return s;
				break;
			case 8:
				int index123_67 = input.index();
				input.rewind();
				s = -1;
				if ((synpred182_Java())) {
					s = 124;
				}

				else if ((true)) {
					s = 5;
				}

				input.seek(index123_67);
				if (s >= 0)
					return s;
				break;
			case 9:
				int index123_68 = input.index();
				input.rewind();
				s = -1;
				if ((synpred182_Java())) {
					s = 124;
				}

				else if ((true)) {
					s = 5;
				}

				input.seek(index123_68);
				if (s >= 0)
					return s;
				break;
			case 10:
				int index123_69 = input.index();
				input.rewind();
				s = -1;
				if ((synpred182_Java())) {
					s = 124;
				}

				else if ((true)) {
					s = 5;
				}

				input.seek(index123_69);
				if (s >= 0)
					return s;
				break;
			case 11:
				int index123_70 = input.index();
				input.rewind();
				s = -1;
				if ((synpred182_Java())) {
					s = 124;
				}

				else if ((true)) {
					s = 5;
				}

				input.seek(index123_70);
				if (s >= 0)
					return s;
				break;
			case 12:
				int index123_71 = input.index();
				input.rewind();
				s = -1;
				if ((synpred182_Java())) {
					s = 124;
				}

				else if ((true)) {
					s = 5;
				}

				input.seek(index123_71);
				if (s >= 0)
					return s;
				break;
			case 13:
				int index123_72 = input.index();
				input.rewind();
				s = -1;
				if ((synpred182_Java())) {
					s = 124;
				}

				else if ((true)) {
					s = 5;
				}

				input.seek(index123_72);
				if (s >= 0)
					return s;
				break;
			case 14:
				int index123_73 = input.index();
				input.rewind();
				s = -1;
				if ((synpred182_Java())) {
					s = 124;
				}

				else if ((true)) {
					s = 5;
				}

				input.seek(index123_73);
				if (s >= 0)
					return s;
				break;
			case 15:
				int index123_74 = input.index();
				input.rewind();
				s = -1;
				if ((synpred182_Java())) {
					s = 124;
				}

				else if ((true)) {
					s = 5;
				}

				input.seek(index123_74);
				if (s >= 0)
					return s;
				break;
			case 16:
				int index123_75 = input.index();
				input.rewind();
				s = -1;
				if ((synpred182_Java())) {
					s = 124;
				}

				else if ((true)) {
					s = 5;
				}

				input.seek(index123_75);
				if (s >= 0)
					return s;
				break;
			case 17:
				int index123_94 = input.index();
				input.rewind();
				s = -1;
				if ((synpred182_Java())) {
					s = 124;
				}

				else if ((true)) {
					s = 5;
				}

				input.seek(index123_94);
				if (s >= 0)
					return s;
				break;
			case 18:
				int index123_95 = input.index();
				input.rewind();
				s = -1;
				if ((synpred182_Java())) {
					s = 124;
				}

				else if ((true)) {
					s = 5;
				}

				input.seek(index123_95);
				if (s >= 0)
					return s;
				break;
			case 19:
				int index123_97 = input.index();
				input.rewind();
				s = -1;
				if ((synpred182_Java())) {
					s = 124;
				}

				else if ((true)) {
					s = 5;
				}

				input.seek(index123_97);
				if (s >= 0)
					return s;
				break;
			case 20:
				int index123_98 = input.index();
				input.rewind();
				s = -1;
				if ((synpred182_Java())) {
					s = 124;
				}

				else if ((true)) {
					s = 5;
				}

				input.seek(index123_98);
				if (s >= 0)
					return s;
				break;
			case 21:
				int index123_104 = input.index();
				input.rewind();
				s = -1;
				if ((synpred182_Java())) {
					s = 124;
				}

				else if ((true)) {
					s = 5;
				}

				input.seek(index123_104);
				if (s >= 0)
					return s;
				break;
			case 22:
				int index123_129 = input.index();
				input.rewind();
				s = -1;
				if ((synpred182_Java())) {
					s = 124;
				}

				else if ((true)) {
					s = 5;
				}

				input.seek(index123_129);
				if (s >= 0)
					return s;
				break;
			}
			if (state.backtracking > 0) {
				state.failed = true;
				return -1;
			}
			NoViableAltException nvae = new NoViableAltException(
					getDescription(), 123, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	static final String DFA124_eotS = "\26\uffff";
	static final String DFA124_eofS = "\26\uffff";
	static final String DFA124_minS = "\1\6\2\uffff\2\0\21\uffff";
	static final String DFA124_maxS = "\1\163\2\uffff\2\0\21\uffff";
	static final String DFA124_acceptS = "\1\uffff\1\1\3\uffff\1\2\20\uffff";
	static final String DFA124_specialS = "\3\uffff\1\0\1\1\21\uffff}>";
	static final String[] DFA124_transitionS = {
			"\1\3\1\uffff\6\5\27\uffff\1\1\13\uffff\1\5\10\uffff\10\4\1\uffff"
					+ "\2\5\2\uffff\4\5\1\1\37\uffff\2\5\2\uffff\5\5", "", "",
			"\1\uffff", "\1\uffff", "", "", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "" };

	static final short[] DFA124_eot = DFA.unpackEncodedString(DFA124_eotS);
	static final short[] DFA124_eof = DFA.unpackEncodedString(DFA124_eofS);
	static final char[] DFA124_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA124_minS);
	static final char[] DFA124_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA124_maxS);
	static final short[] DFA124_accept = DFA
			.unpackEncodedString(DFA124_acceptS);
	static final short[] DFA124_special = DFA
			.unpackEncodedString(DFA124_specialS);
	static final short[][] DFA124_transition;

	static {
		int numStates = DFA124_transitionS.length;
		DFA124_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA124_transition[i] = DFA
					.unpackEncodedString(DFA124_transitionS[i]);
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
			return "698:1: forInit : ( localVariableDeclaration | expressionList );";
		}

		public int specialStateTransition(int s, IntStream _input)
				throws NoViableAltException {
			TokenStream input = (TokenStream) _input;
			int _s = s;
			switch (s) {
			case 0:
				int index124_3 = input.index();
				input.rewind();
				s = -1;
				if ((synpred186_Java())) {
					s = 1;
				}

				else if ((true)) {
					s = 5;
				}

				input.seek(index124_3);
				if (s >= 0)
					return s;
				break;
			case 1:
				int index124_4 = input.index();
				input.rewind();
				s = -1;
				if ((synpred186_Java())) {
					s = 1;
				}

				else if ((true)) {
					s = 5;
				}

				input.seek(index124_4);
				if (s >= 0)
					return s;
				break;
			}
			if (state.backtracking > 0) {
				state.failed = true;
				return -1;
			}
			NoViableAltException nvae = new NoViableAltException(
					getDescription(), 124, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	static final String DFA126_eotS = "\16\uffff";
	static final String DFA126_eofS = "\1\14\15\uffff";
	static final String DFA126_minS = "\1\34\13\0\2\uffff";
	static final String DFA126_maxS = "\1\143\13\0\2\uffff";
	static final String DFA126_acceptS = "\14\uffff\1\2\1\1";
	static final String DFA126_specialS = "\1\uffff\1\11\1\6\1\2\1\0\1\10\1\5\1\3\1\1\1\12\1\7\1\4\2\uffff}>";
	static final String[] DFA126_transitionS = {
			"\1\14\15\uffff\1\12\1\14\1\13\2\uffff\1\14\3\uffff\1\14\1\uffff"
					+ "\1\1\17\uffff\1\14\7\uffff\1\14\16\uffff\1\2\1\3\1\4\1\5\1\6"
					+ "\1\7\1\10\1\11", "\1\uffff", "\1\uffff", "\1\uffff",
			"\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff",
			"\1\uffff", "\1\uffff", "\1\uffff", "", "" };

	static final short[] DFA126_eot = DFA.unpackEncodedString(DFA126_eotS);
	static final short[] DFA126_eof = DFA.unpackEncodedString(DFA126_eofS);
	static final char[] DFA126_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA126_minS);
	static final char[] DFA126_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA126_maxS);
	static final short[] DFA126_accept = DFA
			.unpackEncodedString(DFA126_acceptS);
	static final short[] DFA126_special = DFA
			.unpackEncodedString(DFA126_specialS);
	static final short[][] DFA126_transition;

	static {
		int numStates = DFA126_transitionS.length;
		DFA126_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA126_transition[i] = DFA
					.unpackEncodedString(DFA126_transitionS[i]);
		}
	}

	class DFA126 extends DFA {

		public DFA126(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 126;
			this.eot = DFA126_eot;
			this.eof = DFA126_eof;
			this.min = DFA126_min;
			this.max = DFA126_max;
			this.accept = DFA126_accept;
			this.special = DFA126_special;
			this.transition = DFA126_transition;
		}

		public String getDescription() {
			return "730:31: ( assignmentOperator expression )?";
		}

		public int specialStateTransition(int s, IntStream _input)
				throws NoViableAltException {
			TokenStream input = (TokenStream) _input;
			int _s = s;
			switch (s) {
			case 0:
				int index126_4 = input.index();
				input.rewind();
				s = -1;
				if ((synpred188_Java())) {
					s = 13;
				}

				else if ((true)) {
					s = 12;
				}

				input.seek(index126_4);
				if (s >= 0)
					return s;
				break;
			case 1:
				int index126_8 = input.index();
				input.rewind();
				s = -1;
				if ((synpred188_Java())) {
					s = 13;
				}

				else if ((true)) {
					s = 12;
				}

				input.seek(index126_8);
				if (s >= 0)
					return s;
				break;
			case 2:
				int index126_3 = input.index();
				input.rewind();
				s = -1;
				if ((synpred188_Java())) {
					s = 13;
				}

				else if ((true)) {
					s = 12;
				}

				input.seek(index126_3);
				if (s >= 0)
					return s;
				break;
			case 3:
				int index126_7 = input.index();
				input.rewind();
				s = -1;
				if ((synpred188_Java())) {
					s = 13;
				}

				else if ((true)) {
					s = 12;
				}

				input.seek(index126_7);
				if (s >= 0)
					return s;
				break;
			case 4:
				int index126_11 = input.index();
				input.rewind();
				s = -1;
				if ((synpred188_Java())) {
					s = 13;
				}

				else if ((true)) {
					s = 12;
				}

				input.seek(index126_11);
				if (s >= 0)
					return s;
				break;
			case 5:
				int index126_6 = input.index();
				input.rewind();
				s = -1;
				if ((synpred188_Java())) {
					s = 13;
				}

				else if ((true)) {
					s = 12;
				}

				input.seek(index126_6);
				if (s >= 0)
					return s;
				break;
			case 6:
				int index126_2 = input.index();
				input.rewind();
				s = -1;
				if ((synpred188_Java())) {
					s = 13;
				}

				else if ((true)) {
					s = 12;
				}

				input.seek(index126_2);
				if (s >= 0)
					return s;
				break;
			case 7:
				int index126_10 = input.index();
				input.rewind();
				s = -1;
				if ((synpred188_Java())) {
					s = 13;
				}

				else if ((true)) {
					s = 12;
				}

				input.seek(index126_10);
				if (s >= 0)
					return s;
				break;
			case 8:
				int index126_5 = input.index();
				input.rewind();
				s = -1;
				if ((synpred188_Java())) {
					s = 13;
				}

				else if ((true)) {
					s = 12;
				}

				input.seek(index126_5);
				if (s >= 0)
					return s;
				break;
			case 9:
				int index126_1 = input.index();
				input.rewind();
				s = -1;
				if ((synpred188_Java())) {
					s = 13;
				}

				else if ((true)) {
					s = 12;
				}

				input.seek(index126_1);
				if (s >= 0)
					return s;
				break;
			case 10:
				int index126_9 = input.index();
				input.rewind();
				s = -1;
				if ((synpred188_Java())) {
					s = 13;
				}

				else if ((true)) {
					s = 12;
				}

				input.seek(index126_9);
				if (s >= 0)
					return s;
				break;
			}
			if (state.backtracking > 0) {
				state.failed = true;
				return -1;
			}
			NoViableAltException nvae = new NoViableAltException(
					getDescription(), 126, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	static final String DFA127_eotS = "\17\uffff";
	static final String DFA127_eofS = "\17\uffff";
	static final String DFA127_minS = "\1\52\12\uffff\2\54\2\uffff";
	static final String DFA127_maxS = "\1\143\12\uffff\1\54\1\65\2\uffff";
	static final String DFA127_acceptS = "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\2\uffff\1\13"
			+ "\1\14";
	static final String DFA127_specialS = "\1\0\13\uffff\1\1\2\uffff}>";
	static final String[] DFA127_transitionS = {
			"\1\12\1\uffff\1\13\10\uffff\1\1\46\uffff\1\2\1\3\1\4\1\5\1\6"
					+ "\1\7\1\10\1\11", "", "", "", "", "", "", "", "", "", "",
			"\1\14", "\1\15\10\uffff\1\16", "", "" };

	static final short[] DFA127_eot = DFA.unpackEncodedString(DFA127_eotS);
	static final short[] DFA127_eof = DFA.unpackEncodedString(DFA127_eofS);
	static final char[] DFA127_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA127_minS);
	static final char[] DFA127_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA127_maxS);
	static final short[] DFA127_accept = DFA
			.unpackEncodedString(DFA127_acceptS);
	static final short[] DFA127_special = DFA
			.unpackEncodedString(DFA127_specialS);
	static final short[][] DFA127_transition;

	static {
		int numStates = DFA127_transitionS.length;
		DFA127_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA127_transition[i] = DFA
					.unpackEncodedString(DFA127_transitionS[i]);
		}
	}

	class DFA127 extends DFA {

		public DFA127(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 127;
			this.eot = DFA127_eot;
			this.eof = DFA127_eof;
			this.min = DFA127_min;
			this.max = DFA127_max;
			this.accept = DFA127_accept;
			this.special = DFA127_special;
			this.transition = DFA127_transition;
		}

		public String getDescription() {
			return "733:1: assignmentOperator : ( '=' | '+=' | '-=' | '*=' | '/=' | '&=' | '|=' | '^=' | '%=' | ( '<' '<' '=' )=>t1= '<' t2= '<' t3= '=' {...}? | ( '>' '>' '>' '=' )=>t1= '>' t2= '>' t3= '>' t4= '=' {...}? | ( '>' '>' '=' )=>t1= '>' t2= '>' t3= '=' {...}?);";
		}

		public int specialStateTransition(int s, IntStream _input)
				throws NoViableAltException {
			TokenStream input = (TokenStream) _input;
			int _s = s;
			switch (s) {
			case 0:
				int LA127_0 = input.LA(1);

				int index127_0 = input.index();
				input.rewind();
				s = -1;
				if ((LA127_0 == 53)) {
					s = 1;
				}

				else if ((LA127_0 == 92)) {
					s = 2;
				}

				else if ((LA127_0 == 93)) {
					s = 3;
				}

				else if ((LA127_0 == 94)) {
					s = 4;
				}

				else if ((LA127_0 == 95)) {
					s = 5;
				}

				else if ((LA127_0 == 96)) {
					s = 6;
				}

				else if ((LA127_0 == 97)) {
					s = 7;
				}

				else if ((LA127_0 == 98)) {
					s = 8;
				}

				else if ((LA127_0 == 99)) {
					s = 9;
				}

				else if ((LA127_0 == 42) && (synpred198_Java())) {
					s = 10;
				}

				else if ((LA127_0 == 44)) {
					s = 11;
				}

				input.seek(index127_0);
				if (s >= 0)
					return s;
				break;
			case 1:
				int LA127_12 = input.LA(1);

				int index127_12 = input.index();
				input.rewind();
				s = -1;
				if ((LA127_12 == 44) && (synpred199_Java())) {
					s = 13;
				}

				else if ((LA127_12 == 53) && (synpred200_Java())) {
					s = 14;
				}

				input.seek(index127_12);
				if (s >= 0)
					return s;
				break;
			}
			if (state.backtracking > 0) {
				state.failed = true;
				return -1;
			}
			NoViableAltException nvae = new NoViableAltException(
					getDescription(), 127, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	static final String DFA139_eotS = "\30\uffff";
	static final String DFA139_eofS = "\30\uffff";
	static final String DFA139_minS = "\1\52\1\uffff\1\54\1\6\24\uffff";
	static final String DFA139_maxS = "\1\54\1\uffff\1\54\1\163\24\uffff";
	static final String DFA139_acceptS = "\1\uffff\1\1\2\uffff\1\2\23\3";
	static final String DFA139_specialS = "\1\1\2\uffff\1\0\24\uffff}>";
	static final String[] DFA139_transitionS = {
			"\1\1\1\uffff\1\2",
			"",
			"\1\3",
			"\1\25\1\uffff\1\17\1\20\1\21\3\16\36\uffff\1\4\4\uffff\1\27"
					+ "\10\uffff\10\26\1\uffff\1\15\1\13\2\uffff\1\14\1\23\2\22\40"
					+ "\uffff\1\5\1\6\2\uffff\1\7\1\10\1\11\1\12\1\24", "", "",
			"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
			"" };

	static final short[] DFA139_eot = DFA.unpackEncodedString(DFA139_eotS);
	static final short[] DFA139_eof = DFA.unpackEncodedString(DFA139_eofS);
	static final char[] DFA139_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA139_minS);
	static final char[] DFA139_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA139_maxS);
	static final short[] DFA139_accept = DFA
			.unpackEncodedString(DFA139_acceptS);
	static final short[] DFA139_special = DFA
			.unpackEncodedString(DFA139_specialS);
	static final short[][] DFA139_transition;

	static {
		int numStates = DFA139_transitionS.length;
		DFA139_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA139_transition[i] = DFA
					.unpackEncodedString(DFA139_transitionS[i]);
		}
	}

	class DFA139 extends DFA {

		public DFA139(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 139;
			this.eot = DFA139_eot;
			this.eof = DFA139_eof;
			this.min = DFA139_min;
			this.max = DFA139_max;
			this.accept = DFA139_accept;
			this.special = DFA139_special;
			this.transition = DFA139_transition;
		}

		public String getDescription() {
			return "813:1: shiftOp : ( ( '<' '<' )=>t1= '<' t2= '<' {...}? | ( '>' '>' '>' )=>t1= '>' t2= '>' t3= '>' {...}? | ( '>' '>' )=>t1= '>' t2= '>' {...}?);";
		}

		public int specialStateTransition(int s, IntStream _input)
				throws NoViableAltException {
			TokenStream input = (TokenStream) _input;
			int _s = s;
			switch (s) {
			case 0:
				int LA139_3 = input.LA(1);

				int index139_3 = input.index();
				input.rewind();
				s = -1;
				if ((LA139_3 == 44) && (synpred216_Java())) {
					s = 4;
				}

				else if ((LA139_3 == 107) && (synpred217_Java())) {
					s = 5;
				}

				else if ((LA139_3 == 108) && (synpred217_Java())) {
					s = 6;
				}

				else if ((LA139_3 == 111) && (synpred217_Java())) {
					s = 7;
				}

				else if ((LA139_3 == 112) && (synpred217_Java())) {
					s = 8;
				}

				else if ((LA139_3 == 113) && (synpred217_Java())) {
					s = 9;
				}

				else if ((LA139_3 == 114) && (synpred217_Java())) {
					s = 10;
				}

				else if ((LA139_3 == 68) && (synpred217_Java())) {
					s = 11;
				}

				else if ((LA139_3 == 71) && (synpred217_Java())) {
					s = 12;
				}

				else if ((LA139_3 == 67) && (synpred217_Java())) {
					s = 13;
				}

				else if (((LA139_3 >= HexLiteral && LA139_3 <= DecimalLiteral))
						&& (synpred217_Java())) {
					s = 14;
				}

				else if ((LA139_3 == FloatingPointLiteral)
						&& (synpred217_Java())) {
					s = 15;
				}

				else if ((LA139_3 == CharacterLiteral) && (synpred217_Java())) {
					s = 16;
				}

				else if ((LA139_3 == StringLiteral) && (synpred217_Java())) {
					s = 17;
				}

				else if (((LA139_3 >= 73 && LA139_3 <= 74))
						&& (synpred217_Java())) {
					s = 18;
				}

				else if ((LA139_3 == 72) && (synpred217_Java())) {
					s = 19;
				}

				else if ((LA139_3 == 115) && (synpred217_Java())) {
					s = 20;
				}

				else if ((LA139_3 == Identifier) && (synpred217_Java())) {
					s = 21;
				}

				else if (((LA139_3 >= 58 && LA139_3 <= 65))
						&& (synpred217_Java())) {
					s = 22;
				}

				else if ((LA139_3 == 49) && (synpred217_Java())) {
					s = 23;
				}

				input.seek(index139_3);
				if (s >= 0)
					return s;
				break;
			case 1:
				int LA139_0 = input.LA(1);

				int index139_0 = input.index();
				input.rewind();
				s = -1;
				if ((LA139_0 == 42) && (synpred215_Java())) {
					s = 1;
				}

				else if ((LA139_0 == 44)) {
					s = 2;
				}

				input.seek(index139_0);
				if (s >= 0)
					return s;
				break;
			}
			if (state.backtracking > 0) {
				state.failed = true;
				return -1;
			}
			NoViableAltException nvae = new NoViableAltException(
					getDescription(), 139, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	static final String DFA145_eotS = "\21\uffff";
	static final String DFA145_eofS = "\21\uffff";
	static final String DFA145_minS = "\1\6\2\uffff\1\0\15\uffff";
	static final String DFA145_maxS = "\1\163\2\uffff\1\0\15\uffff";
	static final String DFA145_acceptS = "\1\uffff\1\1\1\2\1\uffff\1\4\13\uffff\1\3";
	static final String DFA145_specialS = "\3\uffff\1\0\15\uffff}>";
	static final String[] DFA145_transitionS = {
			"\1\4\1\uffff\6\4\43\uffff\1\4\10\uffff\10\4\1\uffff\1\4\1\3"
					+ "\2\uffff\4\4\46\uffff\1\1\1\2\1\4", "", "", "\1\uffff",
			"", "", "", "", "", "", "", "", "", "", "", "", "" };

	static final short[] DFA145_eot = DFA.unpackEncodedString(DFA145_eotS);
	static final short[] DFA145_eof = DFA.unpackEncodedString(DFA145_eofS);
	static final char[] DFA145_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA145_minS);
	static final char[] DFA145_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA145_maxS);
	static final short[] DFA145_accept = DFA
			.unpackEncodedString(DFA145_acceptS);
	static final short[] DFA145_special = DFA
			.unpackEncodedString(DFA145_specialS);
	static final short[][] DFA145_transition;

	static {
		int numStates = DFA145_transitionS.length;
		DFA145_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA145_transition[i] = DFA
					.unpackEncodedString(DFA145_transitionS[i]);
		}
	}

	class DFA145 extends DFA {

		public DFA145(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 145;
			this.eot = DFA145_eot;
			this.eof = DFA145_eof;
			this.min = DFA145_min;
			this.max = DFA145_max;
			this.accept = DFA145_accept;
			this.special = DFA145_special;
			this.transition = DFA145_transition;
		}

		public String getDescription() {
			return "844:1: unaryExpressionNotPlusMinus : ( '~' unaryExpression | '!' unaryExpression | castExpression | primary ( selector )* ( '++' | '--' )? );";
		}

		public int specialStateTransition(int s, IntStream _input)
				throws NoViableAltException {
			TokenStream input = (TokenStream) _input;
			int _s = s;
			switch (s) {
			case 0:
				int index145_3 = input.index();
				input.rewind();
				s = -1;
				if ((synpred229_Java())) {
					s = 16;
				}

				else if ((true)) {
					s = 4;
				}

				input.seek(index145_3);
				if (s >= 0)
					return s;
				break;
			}
			if (state.backtracking > 0) {
				state.failed = true;
				return -1;
			}
			NoViableAltException nvae = new NoViableAltException(
					getDescription(), 145, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	static final String DFA146_eotS = "\7\uffff";
	static final String DFA146_eofS = "\7\uffff";
	static final String DFA146_minS = "\1\6\1\0\1\37\2\uffff\1\63\1\37";
	static final String DFA146_maxS = "\1\163\1\0\1\105\2\uffff\1\63\1\105";
	static final String DFA146_acceptS = "\3\uffff\1\2\1\1\2\uffff";
	static final String DFA146_specialS = "\1\uffff\1\0\5\uffff}>";
	static final String[] DFA146_transitionS = {
			"\1\1\1\uffff\6\3\43\uffff\1\3\10\uffff\10\2\1\uffff\2\3\2\uffff"
					+ "\4\3\40\uffff\2\3\2\uffff\5\3", "\1\uffff",
			"\1\3\22\uffff\1\5\22\uffff\1\4", "", "", "\1\6",
			"\1\3\22\uffff\1\5\22\uffff\1\4" };

	static final short[] DFA146_eot = DFA.unpackEncodedString(DFA146_eotS);
	static final short[] DFA146_eof = DFA.unpackEncodedString(DFA146_eofS);
	static final char[] DFA146_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA146_minS);
	static final char[] DFA146_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA146_maxS);
	static final short[] DFA146_accept = DFA
			.unpackEncodedString(DFA146_acceptS);
	static final short[] DFA146_special = DFA
			.unpackEncodedString(DFA146_specialS);
	static final short[][] DFA146_transition;

	static {
		int numStates = DFA146_transitionS.length;
		DFA146_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA146_transition[i] = DFA
					.unpackEncodedString(DFA146_transitionS[i]);
		}
	}

	class DFA146 extends DFA {

		public DFA146(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 146;
			this.eot = DFA146_eot;
			this.eof = DFA146_eof;
			this.min = DFA146_min;
			this.max = DFA146_max;
			this.accept = DFA146_accept;
			this.special = DFA146_special;
			this.transition = DFA146_transition;
		}

		public String getDescription() {
			return "853:12: ( type | expression )";
		}

		public int specialStateTransition(int s, IntStream _input)
				throws NoViableAltException {
			TokenStream input = (TokenStream) _input;
			int _s = s;
			switch (s) {
			case 0:
				int index146_1 = input.index();
				input.rewind();
				s = -1;
				if ((synpred234_Java())) {
					s = 4;
				}

				else if ((true)) {
					s = 3;
				}

				input.seek(index146_1);
				if (s >= 0)
					return s;
				break;
			}
			if (state.backtracking > 0) {
				state.failed = true;
				return -1;
			}
			NoViableAltException nvae = new NoViableAltException(
					getDescription(), 146, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	static final String DFA149_eotS = "\41\uffff";
	static final String DFA149_eofS = "\1\4\40\uffff";
	static final String DFA149_minS = "\1\34\1\0\1\uffff\1\0\35\uffff";
	static final String DFA149_maxS = "\1\160\1\0\1\uffff\1\0\35\uffff";
	static final String DFA149_acceptS = "\2\uffff\1\1\1\uffff\1\2\34\uffff";
	static final String DFA149_specialS = "\1\uffff\1\0\1\uffff\1\1\35\uffff}>";
	static final String[] DFA149_transitionS = {
			"\1\4\2\uffff\1\3\1\4\11\uffff\4\4\1\uffff\1\4\2\uffff\1\1\1"
					+ "\4\1\uffff\1\4\14\uffff\1\4\1\uffff\1\2\1\4\7\uffff\1\4\16\uffff"
					+ "\25\4", "\1\uffff", "", "\1\uffff", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "" };

	static final short[] DFA149_eot = DFA.unpackEncodedString(DFA149_eotS);
	static final short[] DFA149_eof = DFA.unpackEncodedString(DFA149_eofS);
	static final char[] DFA149_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA149_minS);
	static final char[] DFA149_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA149_maxS);
	static final short[] DFA149_accept = DFA
			.unpackEncodedString(DFA149_acceptS);
	static final short[] DFA149_special = DFA
			.unpackEncodedString(DFA149_specialS);
	static final short[][] DFA149_transition;

	static {
		int numStates = DFA149_transitionS.length;
		DFA149_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA149_transition[i] = DFA
					.unpackEncodedString(DFA149_transitionS[i]);
		}
	}

	class DFA149 extends DFA {

		public DFA149(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 149;
			this.eot = DFA149_eot;
			this.eof = DFA149_eof;
			this.min = DFA149_min;
			this.max = DFA149_max;
			this.accept = DFA149_accept;
			this.special = DFA149_special;
			this.transition = DFA149_transition;
		}

		public String getDescription() {
			return "858:34: ( identifierSuffix )?";
		}

		public int specialStateTransition(int s, IntStream _input)
				throws NoViableAltException {
			TokenStream input = (TokenStream) _input;
			int _s = s;
			switch (s) {
			case 0:
				int index149_1 = input.index();
				input.rewind();
				s = -1;
				if ((synpred237_Java())) {
					s = 2;
				}

				else if ((true)) {
					s = 4;
				}

				input.seek(index149_1);
				if (s >= 0)
					return s;
				break;
			case 1:
				int index149_3 = input.index();
				input.rewind();
				s = -1;
				if ((synpred237_Java())) {
					s = 2;
				}

				else if ((true)) {
					s = 4;
				}

				input.seek(index149_3);
				if (s >= 0)
					return s;
				break;
			}
			if (state.backtracking > 0) {
				state.failed = true;
				return -1;
			}
			NoViableAltException nvae = new NoViableAltException(
					getDescription(), 149, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	static final String DFA151_eotS = "\41\uffff";
	static final String DFA151_eofS = "\1\4\40\uffff";
	static final String DFA151_minS = "\1\34\1\0\1\uffff\1\0\35\uffff";
	static final String DFA151_maxS = "\1\160\1\0\1\uffff\1\0\35\uffff";
	static final String DFA151_acceptS = "\2\uffff\1\1\1\uffff\1\2\34\uffff";
	static final String DFA151_specialS = "\1\uffff\1\0\1\uffff\1\1\35\uffff}>";
	static final String[] DFA151_transitionS = {
			"\1\4\2\uffff\1\3\1\4\11\uffff\4\4\1\uffff\1\4\2\uffff\1\1\1"
					+ "\4\1\uffff\1\4\14\uffff\1\4\1\uffff\1\2\1\4\7\uffff\1\4\16\uffff"
					+ "\25\4", "\1\uffff", "", "\1\uffff", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "" };

	static final short[] DFA151_eot = DFA.unpackEncodedString(DFA151_eotS);
	static final short[] DFA151_eof = DFA.unpackEncodedString(DFA151_eofS);
	static final char[] DFA151_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA151_minS);
	static final char[] DFA151_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA151_maxS);
	static final short[] DFA151_accept = DFA
			.unpackEncodedString(DFA151_acceptS);
	static final short[] DFA151_special = DFA
			.unpackEncodedString(DFA151_specialS);
	static final short[][] DFA151_transition;

	static {
		int numStates = DFA151_transitionS.length;
		DFA151_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA151_transition[i] = DFA
					.unpackEncodedString(DFA151_transitionS[i]);
		}
	}

	class DFA151 extends DFA {

		public DFA151(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 151;
			this.eot = DFA151_eot;
			this.eof = DFA151_eof;
			this.min = DFA151_min;
			this.max = DFA151_max;
			this.accept = DFA151_accept;
			this.special = DFA151_special;
			this.transition = DFA151_transition;
		}

		public String getDescription() {
			return "862:38: ( identifierSuffix )?";
		}

		public int specialStateTransition(int s, IntStream _input)
				throws NoViableAltException {
			TokenStream input = (TokenStream) _input;
			int _s = s;
			switch (s) {
			case 0:
				int index151_1 = input.index();
				input.rewind();
				s = -1;
				if ((synpred243_Java())) {
					s = 2;
				}

				else if ((true)) {
					s = 4;
				}

				input.seek(index151_1);
				if (s >= 0)
					return s;
				break;
			case 1:
				int index151_3 = input.index();
				input.rewind();
				s = -1;
				if ((synpred243_Java())) {
					s = 2;
				}

				else if ((true)) {
					s = 4;
				}

				input.seek(index151_3);
				if (s >= 0)
					return s;
				break;
			}
			if (state.backtracking > 0) {
				state.failed = true;
				return -1;
			}
			NoViableAltException nvae = new NoViableAltException(
					getDescription(), 151, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	static final String DFA156_eotS = "\13\uffff";
	static final String DFA156_eofS = "\13\uffff";
	static final String DFA156_minS = "\1\37\1\6\1\uffff\1\47\7\uffff";
	static final String DFA156_maxS = "\1\104\1\163\1\uffff\1\163\7\uffff";
	static final String DFA156_acceptS = "\2\uffff\1\3\1\uffff\1\1\1\2\1\4\1\6\1\7\1\10\1\5";
	static final String DFA156_specialS = "\13\uffff}>";
	static final String[] DFA156_transitionS = {
			"\1\3\22\uffff\1\1\21\uffff\1\2",
			"\1\5\1\uffff\6\5\43\uffff\1\5\1\uffff\1\4\6\uffff\10\5\1\uffff"
					+ "\2\5\2\uffff\4\5\40\uffff\2\5\2\uffff\5\5", "",
			"\1\6\2\uffff\1\12\30\uffff\1\10\3\uffff\1\7\53\uffff\1\11", "",
			"", "", "", "", "", "" };

	static final short[] DFA156_eot = DFA.unpackEncodedString(DFA156_eotS);
	static final short[] DFA156_eof = DFA.unpackEncodedString(DFA156_eofS);
	static final char[] DFA156_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA156_minS);
	static final char[] DFA156_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA156_maxS);
	static final short[] DFA156_accept = DFA
			.unpackEncodedString(DFA156_acceptS);
	static final short[] DFA156_special = DFA
			.unpackEncodedString(DFA156_specialS);
	static final short[][] DFA156_transition;

	static {
		int numStates = DFA156_transitionS.length;
		DFA156_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA156_transition[i] = DFA
					.unpackEncodedString(DFA156_transitionS[i]);
		}
	}

	class DFA156 extends DFA {

		public DFA156(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 156;
			this.eot = DFA156_eot;
			this.eof = DFA156_eof;
			this.min = DFA156_min;
			this.max = DFA156_max;
			this.accept = DFA156_accept;
			this.special = DFA156_special;
			this.transition = DFA156_transition;
		}

		public String getDescription() {
			return "867:1: identifierSuffix : ( ( '[' ']' )+ '.' 'class' | ( '[' expression ']' )+ | arguments | '.' 'class' | '.' explicitGenericInvocation | '.' 'this' | '.' 'super' arguments | '.' 'new' innerCreator );";
		}
	}

	static final String DFA155_eotS = "\41\uffff";
	static final String DFA155_eofS = "\1\1\40\uffff";
	static final String DFA155_minS = "\1\34\1\uffff\1\0\36\uffff";
	static final String DFA155_maxS = "\1\160\1\uffff\1\0\36\uffff";
	static final String DFA155_acceptS = "\1\uffff\1\2\36\uffff\1\1";
	static final String DFA155_specialS = "\2\uffff\1\0\36\uffff}>";
	static final String[] DFA155_transitionS = {
			"\1\1\2\uffff\2\1\11\uffff\4\1\1\uffff\1\1\2\uffff\1\2\1\1\1"
					+ "\uffff\1\1\14\uffff\1\1\2\uffff\1\1\7\uffff\1\1\16\uffff\25"
					+ "\1", "", "\1\uffff", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
			"", "", "", "" };

	static final short[] DFA155_eot = DFA.unpackEncodedString(DFA155_eotS);
	static final short[] DFA155_eof = DFA.unpackEncodedString(DFA155_eofS);
	static final char[] DFA155_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA155_minS);
	static final char[] DFA155_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA155_maxS);
	static final short[] DFA155_accept = DFA
			.unpackEncodedString(DFA155_acceptS);
	static final short[] DFA155_special = DFA
			.unpackEncodedString(DFA155_specialS);
	static final short[][] DFA155_transition;

	static {
		int numStates = DFA155_transitionS.length;
		DFA155_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA155_transition[i] = DFA
					.unpackEncodedString(DFA155_transitionS[i]);
		}
	}

	class DFA155 extends DFA {

		public DFA155(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 155;
			this.eot = DFA155_eot;
			this.eof = DFA155_eof;
			this.min = DFA155_min;
			this.max = DFA155_max;
			this.accept = DFA155_accept;
			this.special = DFA155_special;
			this.transition = DFA155_transition;
		}

		public String getDescription() {
			return "()+ loopback of 869:9: ( '[' expression ']' )+";
		}

		public int specialStateTransition(int s, IntStream _input)
				throws NoViableAltException {
			TokenStream input = (TokenStream) _input;
			int _s = s;
			switch (s) {
			case 0:
				int LA155_2 = input.LA(1);

				int index155_2 = input.index();
				input.rewind();
				s = -1;
				if ((synpred249_Java())) {
					s = 32;
				}

				else if ((true)) {
					s = 1;
				}

				input.seek(index155_2);
				if (s >= 0)
					return s;
				break;
			}
			if (state.backtracking > 0) {
				state.failed = true;
				return -1;
			}
			NoViableAltException nvae = new NoViableAltException(
					getDescription(), 155, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	static final String DFA162_eotS = "\41\uffff";
	static final String DFA162_eofS = "\1\2\40\uffff";
	static final String DFA162_minS = "\1\34\1\0\37\uffff";
	static final String DFA162_maxS = "\1\160\1\0\37\uffff";
	static final String DFA162_acceptS = "\2\uffff\1\2\35\uffff\1\1";
	static final String DFA162_specialS = "\1\uffff\1\0\37\uffff}>";
	static final String[] DFA162_transitionS = {
			"\1\2\2\uffff\2\2\11\uffff\4\2\1\uffff\1\2\2\uffff\1\1\1\2\1"
					+ "\uffff\1\2\14\uffff\1\2\2\uffff\1\2\7\uffff\1\2\16\uffff\25"
					+ "\2", "\1\uffff", "", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
			"", "", "", "" };

	static final short[] DFA162_eot = DFA.unpackEncodedString(DFA162_eotS);
	static final short[] DFA162_eof = DFA.unpackEncodedString(DFA162_eofS);
	static final char[] DFA162_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA162_minS);
	static final char[] DFA162_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA162_maxS);
	static final short[] DFA162_accept = DFA
			.unpackEncodedString(DFA162_acceptS);
	static final short[] DFA162_special = DFA
			.unpackEncodedString(DFA162_specialS);
	static final short[][] DFA162_transition;

	static {
		int numStates = DFA162_transitionS.length;
		DFA162_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA162_transition[i] = DFA
					.unpackEncodedString(DFA162_transitionS[i]);
		}
	}

	class DFA162 extends DFA {

		public DFA162(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 162;
			this.eot = DFA162_eot;
			this.eof = DFA162_eof;
			this.min = DFA162_min;
			this.max = DFA162_max;
			this.accept = DFA162_accept;
			this.special = DFA162_special;
			this.transition = DFA162_transition;
		}

		public String getDescription() {
			return "()* loopback of 895:28: ( '[' expression ']' )*";
		}

		public int specialStateTransition(int s, IntStream _input)
				throws NoViableAltException {
			TokenStream input = (TokenStream) _input;
			int _s = s;
			switch (s) {
			case 0:
				int LA162_1 = input.LA(1);

				int index162_1 = input.index();
				input.rewind();
				s = -1;
				if ((synpred262_Java())) {
					s = 32;
				}

				else if ((true)) {
					s = 2;
				}

				input.seek(index162_1);
				if (s >= 0)
					return s;
				break;
			}
			if (state.backtracking > 0) {
				state.failed = true;
				return -1;
			}
			NoViableAltException nvae = new NoViableAltException(
					getDescription(), 162, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	public static final BitSet FOLLOW_annotations_in_compilationUnit89 = new BitSet(
			new long[] { 0x000100FE48000080L, 0x0000000000000800L });
	public static final BitSet FOLLOW_packageDeclaration_in_compilationUnit103 = new BitSet(
			new long[] { 0x000100FE78000082L, 0x0000000000000800L });
	public static final BitSet FOLLOW_importDeclaration_in_compilationUnit105 = new BitSet(
			new long[] { 0x000100FE78000082L, 0x0000000000000800L });
	public static final BitSet FOLLOW_typeDeclaration_in_compilationUnit108 = new BitSet(
			new long[] { 0x000100FE58000082L, 0x0000000000000800L });
	public static final BitSet FOLLOW_classOrInterfaceDeclaration_in_compilationUnit123 = new BitSet(
			new long[] { 0x000100FE58000082L, 0x0000000000000800L });
	public static final BitSet FOLLOW_typeDeclaration_in_compilationUnit125 = new BitSet(
			new long[] { 0x000100FE58000082L, 0x0000000000000800L });
	public static final BitSet FOLLOW_packageDeclaration_in_compilationUnit146 = new BitSet(
			new long[] { 0x000100FE78000082L, 0x0000000000000800L });
	public static final BitSet FOLLOW_importDeclaration_in_compilationUnit149 = new BitSet(
			new long[] { 0x000100FE78000082L, 0x0000000000000800L });
	public static final BitSet FOLLOW_typeDeclaration_in_compilationUnit152 = new BitSet(
			new long[] { 0x000100FE58000082L, 0x0000000000000800L });
	public static final BitSet FOLLOW_27_in_packageDeclaration172 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_qualifiedName_in_packageDeclaration174 = new BitSet(
			new long[] { 0x0000000010000000L });
	public static final BitSet FOLLOW_28_in_packageDeclaration176 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_29_in_importDeclaration199 = new BitSet(
			new long[] { 0x0000000040000040L });
	public static final BitSet FOLLOW_30_in_importDeclaration201 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_qualifiedName_in_importDeclaration204 = new BitSet(
			new long[] { 0x0000000090000000L });
	public static final BitSet FOLLOW_31_in_importDeclaration207 = new BitSet(
			new long[] { 0x0000000100000000L });
	public static final BitSet FOLLOW_32_in_importDeclaration209 = new BitSet(
			new long[] { 0x0000000010000000L });
	public static final BitSet FOLLOW_28_in_importDeclaration213 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_classOrInterfaceDeclaration_in_typeDeclaration236 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_28_in_typeDeclaration246 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_classOrInterfaceModifier_in_classOrInterfaceModifiers270 = new BitSet(
			new long[] { 0x0000007E40000002L, 0x0000000000000800L });
	public static final BitSet FOLLOW_classOrInterfaceModifiers_in_classOrInterfaceDeclaration289 = new BitSet(
			new long[] { 0x000000FE40000080L, 0x0000000000000800L });
	public static final BitSet FOLLOW_classDeclaration_in_classOrInterfaceDeclaration291 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_classOrInterfaceModifiers_in_classOrInterfaceDeclaration310 = new BitSet(
			new long[] { 0x000100FE48000080L, 0x0000000000000800L });
	public static final BitSet FOLLOW_interfaceDeclaration_in_classOrInterfaceDeclaration312 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_annotation_in_classOrInterfaceModifier346 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_33_in_classOrInterfaceModifier359 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_34_in_classOrInterfaceModifier374 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_35_in_classOrInterfaceModifier386 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_36_in_classOrInterfaceModifier400 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_30_in_classOrInterfaceModifier413 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_37_in_classOrInterfaceModifier428 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_38_in_classOrInterfaceModifier444 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_modifier_in_modifiers466 = new BitSet(
			new long[] { 0x03C0007E40000002L, 0x0000000000000800L });
	public static final BitSet FOLLOW_normalClassDeclaration_in_classDeclaration486 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_enumDeclaration_in_classDeclaration496 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_39_in_normalClassDeclaration519 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_Identifier_in_normalClassDeclaration521 = new BitSet(
			new long[] { 0x0000470000000000L });
	public static final BitSet FOLLOW_typeParameters_in_normalClassDeclaration523 = new BitSet(
			new long[] { 0x0000470000000000L });
	public static final BitSet FOLLOW_40_in_normalClassDeclaration535 = new BitSet(
			new long[] { 0xFC00000000000040L, 0x0000000000000003L });
	public static final BitSet FOLLOW_type_in_normalClassDeclaration537 = new BitSet(
			new long[] { 0x0000470000000000L });
	public static final BitSet FOLLOW_41_in_normalClassDeclaration550 = new BitSet(
			new long[] { 0xFC00000000000040L, 0x0000000000000003L });
	public static final BitSet FOLLOW_typeList_in_normalClassDeclaration552 = new BitSet(
			new long[] { 0x0000470000000000L });
	public static final BitSet FOLLOW_classBody_in_normalClassDeclaration564 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_42_in_typeParameters587 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_typeParameter_in_typeParameters589 = new BitSet(
			new long[] { 0x0000180000000000L });
	public static final BitSet FOLLOW_43_in_typeParameters592 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_typeParameter_in_typeParameters594 = new BitSet(
			new long[] { 0x0000180000000000L });
	public static final BitSet FOLLOW_44_in_typeParameters598 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_Identifier_in_typeParameter617 = new BitSet(
			new long[] { 0x0000010000000002L });
	public static final BitSet FOLLOW_40_in_typeParameter620 = new BitSet(
			new long[] { 0xFC00000000000040L, 0x0000000000000003L });
	public static final BitSet FOLLOW_typeBound_in_typeParameter622 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_type_in_typeBound651 = new BitSet(
			new long[] { 0x0000200000000002L });
	public static final BitSet FOLLOW_45_in_typeBound654 = new BitSet(
			new long[] { 0xFC00000000000040L, 0x0000000000000003L });
	public static final BitSet FOLLOW_type_in_typeBound656 = new BitSet(
			new long[] { 0x0000200000000002L });
	public static final BitSet FOLLOW_ENUM_in_enumDeclaration677 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_Identifier_in_enumDeclaration679 = new BitSet(
			new long[] { 0x0000420000000000L });
	public static final BitSet FOLLOW_41_in_enumDeclaration682 = new BitSet(
			new long[] { 0xFC00000000000040L, 0x0000000000000003L });
	public static final BitSet FOLLOW_typeList_in_enumDeclaration684 = new BitSet(
			new long[] { 0x0000420000000000L });
	public static final BitSet FOLLOW_enumBody_in_enumDeclaration688 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_46_in_enumBody707 = new BitSet(
			new long[] { 0x0000880010000040L, 0x0000000000000800L });
	public static final BitSet FOLLOW_enumConstants_in_enumBody709 = new BitSet(
			new long[] { 0x0000880010000000L });
	public static final BitSet FOLLOW_43_in_enumBody712 = new BitSet(
			new long[] { 0x0000800010000000L });
	public static final BitSet FOLLOW_enumBodyDeclarations_in_enumBody715 = new BitSet(
			new long[] { 0x0000800000000000L });
	public static final BitSet FOLLOW_47_in_enumBody718 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_enumConstant_in_enumConstants737 = new BitSet(
			new long[] { 0x0000080000000002L });
	public static final BitSet FOLLOW_43_in_enumConstants740 = new BitSet(
			new long[] { 0x0000000000000040L, 0x0000000000000800L });
	public static final BitSet FOLLOW_enumConstant_in_enumConstants742 = new BitSet(
			new long[] { 0x0000080000000002L });
	public static final BitSet FOLLOW_annotations_in_enumConstant767 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_Identifier_in_enumConstant770 = new BitSet(
			new long[] { 0x0000470000000002L, 0x0000000000000010L });
	public static final BitSet FOLLOW_arguments_in_enumConstant772 = new BitSet(
			new long[] { 0x0000470000000002L });
	public static final BitSet FOLLOW_classBody_in_enumConstant775 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_28_in_enumBodyDeclarations799 = new BitSet(
			new long[] { 0x03C0407E50000002L, 0x0000000000000800L });
	public static final BitSet FOLLOW_classBodyDeclaration_in_enumBodyDeclarations802 = new BitSet(
			new long[] { 0x03C0407E50000002L, 0x0000000000000800L });
	public static final BitSet FOLLOW_normalInterfaceDeclaration_in_interfaceDeclaration827 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_annotationTypeDeclaration_in_interfaceDeclaration837 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_48_in_normalInterfaceDeclaration860 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_Identifier_in_normalInterfaceDeclaration862 = new BitSet(
			new long[] { 0x0000450000000000L });
	public static final BitSet FOLLOW_typeParameters_in_normalInterfaceDeclaration864 = new BitSet(
			new long[] { 0x0000450000000000L });
	public static final BitSet FOLLOW_40_in_normalInterfaceDeclaration868 = new BitSet(
			new long[] { 0xFC00000000000040L, 0x0000000000000003L });
	public static final BitSet FOLLOW_typeList_in_normalInterfaceDeclaration870 = new BitSet(
			new long[] { 0x0000450000000000L });
	public static final BitSet FOLLOW_interfaceBody_in_normalInterfaceDeclaration874 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_type_in_typeList897 = new BitSet(
			new long[] { 0x0000080000000002L });
	public static final BitSet FOLLOW_43_in_typeList900 = new BitSet(
			new long[] { 0xFC00000000000040L, 0x0000000000000003L });
	public static final BitSet FOLLOW_type_in_typeList902 = new BitSet(
			new long[] { 0x0000080000000002L });
	public static final BitSet FOLLOW_46_in_classBody927 = new BitSet(
			new long[] { 0x03C0C07E50000000L, 0x0000000000000800L });
	public static final BitSet FOLLOW_classBodyDeclaration_in_classBody929 = new BitSet(
			new long[] { 0x03C0C07E50000000L, 0x0000000000000800L });
	public static final BitSet FOLLOW_47_in_classBody932 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_46_in_interfaceBody955 = new BitSet(
			new long[] { 0x03C0C07E50000000L, 0x0000000000000800L });
	public static final BitSet FOLLOW_interfaceBodyDeclaration_in_interfaceBody957 = new BitSet(
			new long[] { 0x03C0C07E50000000L, 0x0000000000000800L });
	public static final BitSet FOLLOW_47_in_interfaceBody960 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_28_in_classBodyDeclaration979 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_30_in_classBodyDeclaration989 = new BitSet(
			new long[] { 0x0000400040000000L });
	public static final BitSet FOLLOW_block_in_classBodyDeclaration992 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_modifiers_in_classBodyDeclaration1013 = new BitSet(
			new long[] { 0xFC0304FE480000C0L, 0x0000000000000803L });
	public static final BitSet FOLLOW_memberDecl_in_classBodyDeclaration1015 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_genericMethodOrConstructorDecl_in_memberDecl1048 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_memberDeclaration_in_memberDecl1058 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_49_in_memberDecl1068 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_Identifier_in_memberDecl1070 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_voidMethodDeclaratorRest_in_memberDecl1072 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_Identifier_in_memberDecl1082 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_constructorDeclaratorRest_in_memberDecl1084 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_interfaceDeclaration_in_memberDecl1094 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_classDeclaration_in_memberDecl1104 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_type_in_memberDeclaration1127 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_methodDeclaration_in_memberDeclaration1130 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_fieldDeclaration_in_memberDeclaration1134 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_typeParameters_in_genericMethodOrConstructorDecl1154 = new BitSet(
			new long[] { 0xFC02000000000040L, 0x0000000000000003L });
	public static final BitSet FOLLOW_genericMethodOrConstructorRest_in_genericMethodOrConstructorDecl1156 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_type_in_genericMethodOrConstructorRest1180 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_49_in_genericMethodOrConstructorRest1184 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_Identifier_in_genericMethodOrConstructorRest1187 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_methodDeclaratorRest_in_genericMethodOrConstructorRest1189 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_Identifier_in_genericMethodOrConstructorRest1199 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_constructorDeclaratorRest_in_genericMethodOrConstructorRest1201 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_Identifier_in_methodDeclaration1220 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_methodDeclaratorRest_in_methodDeclaration1222 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_variableDeclarators_in_fieldDeclaration1241 = new BitSet(
			new long[] { 0x0000000010000000L });
	public static final BitSet FOLLOW_28_in_fieldDeclaration1243 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_modifiers_in_interfaceBodyDeclaration1270 = new BitSet(
			new long[] { 0xFC0304FE480000C0L, 0x0000000000000803L });
	public static final BitSet FOLLOW_interfaceMemberDecl_in_interfaceBodyDeclaration1272 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_28_in_interfaceBodyDeclaration1282 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_interfaceMethodOrFieldDecl_in_interfaceMemberDecl1301 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_interfaceGenericMethodDecl_in_interfaceMemberDecl1311 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_49_in_interfaceMemberDecl1321 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_Identifier_in_interfaceMemberDecl1323 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_voidInterfaceMethodDeclaratorRest_in_interfaceMemberDecl1325 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_interfaceDeclaration_in_interfaceMemberDecl1335 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_classDeclaration_in_interfaceMemberDecl1345 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_type_in_interfaceMethodOrFieldDecl1368 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_Identifier_in_interfaceMethodOrFieldDecl1370 = new BitSet(
			new long[] { 0x0024000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_interfaceMethodOrFieldRest_in_interfaceMethodOrFieldDecl1372 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_constantDeclaratorsRest_in_interfaceMethodOrFieldRest1395 = new BitSet(
			new long[] { 0x0000000010000000L });
	public static final BitSet FOLLOW_28_in_interfaceMethodOrFieldRest1397 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_interfaceMethodDeclaratorRest_in_interfaceMethodOrFieldRest1407 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_formalParameters_in_methodDeclaratorRest1430 = new BitSet(
			new long[] { 0x0014400050000000L });
	public static final BitSet FOLLOW_50_in_methodDeclaratorRest1433 = new BitSet(
			new long[] { 0x0008000000000000L });
	public static final BitSet FOLLOW_51_in_methodDeclaratorRest1435 = new BitSet(
			new long[] { 0x0014400050000000L });
	public static final BitSet FOLLOW_52_in_methodDeclaratorRest1448 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_qualifiedNameList_in_methodDeclaratorRest1450 = new BitSet(
			new long[] { 0x0000400050000000L });
	public static final BitSet FOLLOW_methodBody_in_methodDeclaratorRest1466 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_28_in_methodDeclaratorRest1480 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_formalParameters_in_voidMethodDeclaratorRest1513 = new BitSet(
			new long[] { 0x0010400050000000L });
	public static final BitSet FOLLOW_52_in_voidMethodDeclaratorRest1516 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_qualifiedNameList_in_voidMethodDeclaratorRest1518 = new BitSet(
			new long[] { 0x0000400050000000L });
	public static final BitSet FOLLOW_methodBody_in_voidMethodDeclaratorRest1534 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_28_in_voidMethodDeclaratorRest1548 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_formalParameters_in_interfaceMethodDeclaratorRest1581 = new BitSet(
			new long[] { 0x0014000010000000L });
	public static final BitSet FOLLOW_50_in_interfaceMethodDeclaratorRest1584 = new BitSet(
			new long[] { 0x0008000000000000L });
	public static final BitSet FOLLOW_51_in_interfaceMethodDeclaratorRest1586 = new BitSet(
			new long[] { 0x0014000010000000L });
	public static final BitSet FOLLOW_52_in_interfaceMethodDeclaratorRest1591 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_qualifiedNameList_in_interfaceMethodDeclaratorRest1593 = new BitSet(
			new long[] { 0x0000000010000000L });
	public static final BitSet FOLLOW_28_in_interfaceMethodDeclaratorRest1597 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_typeParameters_in_interfaceGenericMethodDecl1620 = new BitSet(
			new long[] { 0xFC02000000000040L, 0x0000000000000003L });
	public static final BitSet FOLLOW_type_in_interfaceGenericMethodDecl1623 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_49_in_interfaceGenericMethodDecl1627 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_Identifier_in_interfaceGenericMethodDecl1630 = new BitSet(
			new long[] { 0x0024000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_interfaceMethodDeclaratorRest_in_interfaceGenericMethodDecl1640 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_formalParameters_in_voidInterfaceMethodDeclaratorRest1663 = new BitSet(
			new long[] { 0x0010000010000000L });
	public static final BitSet FOLLOW_52_in_voidInterfaceMethodDeclaratorRest1666 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_qualifiedNameList_in_voidInterfaceMethodDeclaratorRest1668 = new BitSet(
			new long[] { 0x0000000010000000L });
	public static final BitSet FOLLOW_28_in_voidInterfaceMethodDeclaratorRest1672 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_formalParameters_in_constructorDeclaratorRest1695 = new BitSet(
			new long[] { 0x0010400000000000L });
	public static final BitSet FOLLOW_52_in_constructorDeclaratorRest1698 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_qualifiedNameList_in_constructorDeclaratorRest1700 = new BitSet(
			new long[] { 0x0010400000000000L });
	public static final BitSet FOLLOW_constructorBody_in_constructorDeclaratorRest1704 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_Identifier_in_constantDeclarator1723 = new BitSet(
			new long[] { 0x0024000000000000L });
	public static final BitSet FOLLOW_constantDeclaratorRest_in_constantDeclarator1725 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_variableDeclarator_in_variableDeclarators1748 = new BitSet(
			new long[] { 0x0000080000000002L });
	public static final BitSet FOLLOW_43_in_variableDeclarators1751 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_variableDeclarator_in_variableDeclarators1753 = new BitSet(
			new long[] { 0x0000080000000002L });
	public static final BitSet FOLLOW_variableDeclaratorId_in_variableDeclarator1774 = new BitSet(
			new long[] { 0x0020000000000002L });
	public static final BitSet FOLLOW_53_in_variableDeclarator1777 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_variableInitializer_in_variableDeclarator1779 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_constantDeclaratorRest_in_constantDeclaratorsRest1804 = new BitSet(
			new long[] { 0x0000080000000002L });
	public static final BitSet FOLLOW_43_in_constantDeclaratorsRest1807 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_constantDeclarator_in_constantDeclaratorsRest1809 = new BitSet(
			new long[] { 0x0000080000000002L });
	public static final BitSet FOLLOW_50_in_constantDeclaratorRest1831 = new BitSet(
			new long[] { 0x0008000000000000L });
	public static final BitSet FOLLOW_51_in_constantDeclaratorRest1833 = new BitSet(
			new long[] { 0x0024000000000000L });
	public static final BitSet FOLLOW_53_in_constantDeclaratorRest1837 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_variableInitializer_in_constantDeclaratorRest1839 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_Identifier_in_variableDeclaratorId1862 = new BitSet(
			new long[] { 0x0004000000000002L });
	public static final BitSet FOLLOW_50_in_variableDeclaratorId1865 = new BitSet(
			new long[] { 0x0008000000000000L });
	public static final BitSet FOLLOW_51_in_variableDeclaratorId1867 = new BitSet(
			new long[] { 0x0004000000000002L });
	public static final BitSet FOLLOW_arrayInitializer_in_variableInitializer1888 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_expression_in_variableInitializer1898 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_46_in_arrayInitializer1925 = new BitSet(
			new long[] { 0xFC02C00000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_variableInitializer_in_arrayInitializer1928 = new BitSet(
			new long[] { 0x0000880000000000L });
	public static final BitSet FOLLOW_43_in_arrayInitializer1931 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_variableInitializer_in_arrayInitializer1933 = new BitSet(
			new long[] { 0x0000880000000000L });
	public static final BitSet FOLLOW_43_in_arrayInitializer1938 = new BitSet(
			new long[] { 0x0000800000000000L });
	public static final BitSet FOLLOW_47_in_arrayInitializer1945 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_annotation_in_modifier1964 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_33_in_modifier1974 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_34_in_modifier1984 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_35_in_modifier1994 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_30_in_modifier2004 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_36_in_modifier2014 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_37_in_modifier2024 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_54_in_modifier2034 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_55_in_modifier2044 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_56_in_modifier2054 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_57_in_modifier2064 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_38_in_modifier2074 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_qualifiedName_in_packageOrTypeName2093 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_Identifier_in_enumConstantName2112 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_qualifiedName_in_typeName2131 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_classOrInterfaceType_in_type2145 = new BitSet(
			new long[] { 0x0004000000000002L });
	public static final BitSet FOLLOW_50_in_type2148 = new BitSet(
			new long[] { 0x0008000000000000L });
	public static final BitSet FOLLOW_51_in_type2150 = new BitSet(
			new long[] { 0x0004000000000002L });
	public static final BitSet FOLLOW_primitiveType_in_type2157 = new BitSet(
			new long[] { 0x0004000000000002L });
	public static final BitSet FOLLOW_50_in_type2160 = new BitSet(
			new long[] { 0x0008000000000000L });
	public static final BitSet FOLLOW_51_in_type2162 = new BitSet(
			new long[] { 0x0004000000000002L });
	public static final BitSet FOLLOW_Identifier_in_classOrInterfaceType2175 = new BitSet(
			new long[] { 0x0000040080000002L });
	public static final BitSet FOLLOW_typeArguments_in_classOrInterfaceType2177 = new BitSet(
			new long[] { 0x0000000080000002L });
	public static final BitSet FOLLOW_31_in_classOrInterfaceType2181 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_Identifier_in_classOrInterfaceType2183 = new BitSet(
			new long[] { 0x0000040080000002L });
	public static final BitSet FOLLOW_typeArguments_in_classOrInterfaceType2185 = new BitSet(
			new long[] { 0x0000000080000002L });
	public static final BitSet FOLLOW_set_in_primitiveType0 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_37_in_variableModifier2294 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_annotation_in_variableModifier2304 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_42_in_typeArguments2323 = new BitSet(
			new long[] { 0xFC00000000000040L, 0x0000000000000007L });
	public static final BitSet FOLLOW_typeArgument_in_typeArguments2325 = new BitSet(
			new long[] { 0x0000180000000000L });
	public static final BitSet FOLLOW_43_in_typeArguments2328 = new BitSet(
			new long[] { 0xFC00000000000040L, 0x0000000000000007L });
	public static final BitSet FOLLOW_typeArgument_in_typeArguments2330 = new BitSet(
			new long[] { 0x0000180000000000L });
	public static final BitSet FOLLOW_44_in_typeArguments2334 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_type_in_typeArgument2357 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_66_in_typeArgument2367 = new BitSet(
			new long[] { 0x0000010000000002L, 0x0000000000000008L });
	public static final BitSet FOLLOW_set_in_typeArgument2370 = new BitSet(
			new long[] { 0xFC00000000000040L, 0x0000000000000003L });
	public static final BitSet FOLLOW_type_in_typeArgument2378 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_qualifiedName_in_qualifiedNameList2403 = new BitSet(
			new long[] { 0x0000080000000002L });
	public static final BitSet FOLLOW_43_in_qualifiedNameList2406 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_qualifiedName_in_qualifiedNameList2408 = new BitSet(
			new long[] { 0x0000080000000002L });
	public static final BitSet FOLLOW_68_in_formalParameters2429 = new BitSet(
			new long[] { 0xFC00002000000040L, 0x0000000000000823L });
	public static final BitSet FOLLOW_formalParameterDecls_in_formalParameters2431 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000020L });
	public static final BitSet FOLLOW_69_in_formalParameters2434 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_variableModifiers_in_formalParameterDecls2457 = new BitSet(
			new long[] { 0xFC00000000000040L, 0x0000000000000003L });
	public static final BitSet FOLLOW_type_in_formalParameterDecls2459 = new BitSet(
			new long[] { 0x0000000000000040L, 0x0000000000000040L });
	public static final BitSet FOLLOW_formalParameterDeclsRest_in_formalParameterDecls2461 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_variableDeclaratorId_in_formalParameterDeclsRest2484 = new BitSet(
			new long[] { 0x0000080000000002L });
	public static final BitSet FOLLOW_43_in_formalParameterDeclsRest2487 = new BitSet(
			new long[] { 0xFC00002000000040L, 0x0000000000000803L });
	public static final BitSet FOLLOW_formalParameterDecls_in_formalParameterDeclsRest2489 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_70_in_formalParameterDeclsRest2501 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_variableDeclaratorId_in_formalParameterDeclsRest2503 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_block_in_methodBody2526 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_46_in_constructorBody2545 = new BitSet(
			new long[] { 0xFC83C4FE58007FC0L, 0x000F980003EF4F9BL });
	public static final BitSet FOLLOW_explicitConstructorInvocation_in_constructorBody2547 = new BitSet(
			new long[] { 0xFC83C0FE58007FC0L, 0x000F980003EF4F9BL });
	public static final BitSet FOLLOW_blockStatement_in_constructorBody2550 = new BitSet(
			new long[] { 0xFC83C0FE58007FC0L, 0x000F980003EF4F9BL });
	public static final BitSet FOLLOW_47_in_constructorBody2553 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_nonWildcardTypeArguments_in_explicitConstructorInvocation2572 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000088L });
	public static final BitSet FOLLOW_set_in_explicitConstructorInvocation2575 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_arguments_in_explicitConstructorInvocation2583 = new BitSet(
			new long[] { 0x0000000010000000L });
	public static final BitSet FOLLOW_28_in_explicitConstructorInvocation2585 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_primary_in_explicitConstructorInvocation2595 = new BitSet(
			new long[] { 0x0000000080000000L });
	public static final BitSet FOLLOW_31_in_explicitConstructorInvocation2597 = new BitSet(
			new long[] { 0x0000040000000000L, 0x0000000000000008L });
	public static final BitSet FOLLOW_nonWildcardTypeArguments_in_explicitConstructorInvocation2599 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000008L });
	public static final BitSet FOLLOW_67_in_explicitConstructorInvocation2602 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_arguments_in_explicitConstructorInvocation2604 = new BitSet(
			new long[] { 0x0000000010000000L });
	public static final BitSet FOLLOW_28_in_explicitConstructorInvocation2606 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_Identifier_in_qualifiedName2626 = new BitSet(
			new long[] { 0x0000000080000002L });
	public static final BitSet FOLLOW_31_in_qualifiedName2629 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_Identifier_in_qualifiedName2631 = new BitSet(
			new long[] { 0x0000000080000002L });
	public static final BitSet FOLLOW_integerLiteral_in_literal2657 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_FloatingPointLiteral_in_literal2667 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_CharacterLiteral_in_literal2677 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_StringLiteral_in_literal2687 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_booleanLiteral_in_literal2697 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_72_in_literal2707 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_set_in_integerLiteral0 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_set_in_booleanLiteral0 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_annotation_in_annotations2796 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000000000000800L });
	public static final BitSet FOLLOW_75_in_annotation2816 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_annotationName_in_annotation2818 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000000000000010L });
	public static final BitSet FOLLOW_68_in_annotation2822 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F980000000FBBL });
	public static final BitSet FOLLOW_elementValuePairs_in_annotation2826 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000020L });
	public static final BitSet FOLLOW_elementValue_in_annotation2830 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000020L });
	public static final BitSet FOLLOW_69_in_annotation2835 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_Identifier_in_annotationName2859 = new BitSet(
			new long[] { 0x0000000080000002L });
	public static final BitSet FOLLOW_31_in_annotationName2862 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_Identifier_in_annotationName2864 = new BitSet(
			new long[] { 0x0000000080000002L });
	public static final BitSet FOLLOW_elementValuePair_in_elementValuePairs2885 = new BitSet(
			new long[] { 0x0000080000000002L });
	public static final BitSet FOLLOW_43_in_elementValuePairs2888 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_elementValuePair_in_elementValuePairs2890 = new BitSet(
			new long[] { 0x0000080000000002L });
	public static final BitSet FOLLOW_Identifier_in_elementValuePair2911 = new BitSet(
			new long[] { 0x0020000000000000L });
	public static final BitSet FOLLOW_53_in_elementValuePair2913 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F980000000F9BL });
	public static final BitSet FOLLOW_elementValue_in_elementValuePair2915 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_conditionalExpression_in_elementValue2938 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_annotation_in_elementValue2948 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_elementValueArrayInitializer_in_elementValue2958 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_46_in_elementValueArrayInitializer2981 = new BitSet(
			new long[] { 0xFC02C80000003F40L, 0x000F980000000F9BL });
	public static final BitSet FOLLOW_elementValue_in_elementValueArrayInitializer2984 = new BitSet(
			new long[] { 0x0000880000000000L });
	public static final BitSet FOLLOW_43_in_elementValueArrayInitializer2987 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F980000000F9BL });
	public static final BitSet FOLLOW_elementValue_in_elementValueArrayInitializer2989 = new BitSet(
			new long[] { 0x0000880000000000L });
	public static final BitSet FOLLOW_43_in_elementValueArrayInitializer2996 = new BitSet(
			new long[] { 0x0000800000000000L });
	public static final BitSet FOLLOW_47_in_elementValueArrayInitializer3000 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_75_in_annotationTypeDeclaration3023 = new BitSet(
			new long[] { 0x0001000000000000L });
	public static final BitSet FOLLOW_48_in_annotationTypeDeclaration3025 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_Identifier_in_annotationTypeDeclaration3027 = new BitSet(
			new long[] { 0x0000400000000000L });
	public static final BitSet FOLLOW_annotationTypeBody_in_annotationTypeDeclaration3029 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_46_in_annotationTypeBody3052 = new BitSet(
			new long[] { 0x03C0C07E50000000L, 0x0000000000000800L });
	public static final BitSet FOLLOW_annotationTypeElementDeclaration_in_annotationTypeBody3055 = new BitSet(
			new long[] { 0x03C0C07E50000000L, 0x0000000000000800L });
	public static final BitSet FOLLOW_47_in_annotationTypeBody3059 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_modifiers_in_annotationTypeElementDeclaration3082 = new BitSet(
			new long[] { 0xFC0100FE480000C0L, 0x0000000000000803L });
	public static final BitSet FOLLOW_annotationTypeElementRest_in_annotationTypeElementDeclaration3084 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_type_in_annotationTypeElementRest3107 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_annotationMethodOrConstantRest_in_annotationTypeElementRest3109 = new BitSet(
			new long[] { 0x0000000010000000L });
	public static final BitSet FOLLOW_28_in_annotationTypeElementRest3111 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_normalClassDeclaration_in_annotationTypeElementRest3121 = new BitSet(
			new long[] { 0x0000000010000002L });
	public static final BitSet FOLLOW_28_in_annotationTypeElementRest3123 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_normalInterfaceDeclaration_in_annotationTypeElementRest3134 = new BitSet(
			new long[] { 0x0000000010000002L });
	public static final BitSet FOLLOW_28_in_annotationTypeElementRest3136 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_enumDeclaration_in_annotationTypeElementRest3147 = new BitSet(
			new long[] { 0x0000000010000002L });
	public static final BitSet FOLLOW_28_in_annotationTypeElementRest3149 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_annotationTypeDeclaration_in_annotationTypeElementRest3160 = new BitSet(
			new long[] { 0x0000000010000002L });
	public static final BitSet FOLLOW_28_in_annotationTypeElementRest3162 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_annotationMethodRest_in_annotationMethodOrConstantRest3186 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_annotationConstantRest_in_annotationMethodOrConstantRest3196 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_Identifier_in_annotationMethodRest3219 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_68_in_annotationMethodRest3221 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000020L });
	public static final BitSet FOLLOW_69_in_annotationMethodRest3223 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000000000001000L });
	public static final BitSet FOLLOW_defaultValue_in_annotationMethodRest3225 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_variableDeclarators_in_annotationConstantRest3249 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_76_in_defaultValue3272 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F980000000F9BL });
	public static final BitSet FOLLOW_elementValue_in_defaultValue3274 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_46_in_block3295 = new BitSet(new long[] {
			0xFC83C0FE58007FC0L, 0x000F980003EF4F9BL });
	public static final BitSet FOLLOW_blockStatement_in_block3297 = new BitSet(
			new long[] { 0xFC83C0FE58007FC0L, 0x000F980003EF4F9BL });
	public static final BitSet FOLLOW_47_in_block3300 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_localVariableDeclarationStatement_in_blockStatement3323 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_classOrInterfaceDeclaration_in_blockStatement3333 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_statement_in_blockStatement3343 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_localVariableDeclaration_in_localVariableDeclarationStatement3367 = new BitSet(
			new long[] { 0x0000000010000000L });
	public static final BitSet FOLLOW_28_in_localVariableDeclarationStatement3369 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_variableModifiers_in_localVariableDeclaration3388 = new BitSet(
			new long[] { 0xFC00000000000040L, 0x0000000000000003L });
	public static final BitSet FOLLOW_type_in_localVariableDeclaration3390 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_variableDeclarators_in_localVariableDeclaration3392 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_variableModifier_in_variableModifiers3415 = new BitSet(
			new long[] { 0x0000002000000002L, 0x0000000000000800L });
	public static final BitSet FOLLOW_block_in_statement3433 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ASSERT_in_statement3443 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_expression_in_statement3445 = new BitSet(
			new long[] { 0x0000000010000000L, 0x0000000000002000L });
	public static final BitSet FOLLOW_77_in_statement3448 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_expression_in_statement3450 = new BitSet(
			new long[] { 0x0000000010000000L });
	public static final BitSet FOLLOW_28_in_statement3454 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_78_in_statement3464 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_parExpression_in_statement3466 = new BitSet(
			new long[] { 0xFC8340FE58007FC0L, 0x000F980003EF4F9BL });
	public static final BitSet FOLLOW_statement_in_statement3468 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000000000008000L });
	public static final BitSet FOLLOW_79_in_statement3478 = new BitSet(
			new long[] { 0xFC8340FE58007FC0L, 0x000F980003EF4F9BL });
	public static final BitSet FOLLOW_statement_in_statement3480 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_80_in_statement3492 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_68_in_statement3494 = new BitSet(
			new long[] { 0xFC02402010003F40L, 0x000F980000000F9BL });
	public static final BitSet FOLLOW_forControl_in_statement3496 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000020L });
	public static final BitSet FOLLOW_69_in_statement3498 = new BitSet(
			new long[] { 0xFC8340FE58007FC0L, 0x000F980003EF4F9BL });
	public static final BitSet FOLLOW_statement_in_statement3500 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_81_in_statement3510 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_parExpression_in_statement3512 = new BitSet(
			new long[] { 0xFC8340FE58007FC0L, 0x000F980003EF4F9BL });
	public static final BitSet FOLLOW_statement_in_statement3514 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_82_in_statement3524 = new BitSet(
			new long[] { 0xFC8340FE58007FC0L, 0x000F980003EF4F9BL });
	public static final BitSet FOLLOW_statement_in_statement3526 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000020000L });
	public static final BitSet FOLLOW_81_in_statement3528 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_parExpression_in_statement3530 = new BitSet(
			new long[] { 0x0000000010000000L });
	public static final BitSet FOLLOW_28_in_statement3532 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_83_in_statement3542 = new BitSet(
			new long[] { 0x0000400040000000L });
	public static final BitSet FOLLOW_block_in_statement3544 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000004100000L });
	public static final BitSet FOLLOW_catches_in_statement3556 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000100000L });
	public static final BitSet FOLLOW_84_in_statement3558 = new BitSet(
			new long[] { 0x0000400040000000L });
	public static final BitSet FOLLOW_block_in_statement3560 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_catches_in_statement3572 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_84_in_statement3586 = new BitSet(
			new long[] { 0x0000400040000000L });
	public static final BitSet FOLLOW_block_in_statement3588 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_85_in_statement3608 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_parExpression_in_statement3610 = new BitSet(
			new long[] { 0x0000400000000000L });
	public static final BitSet FOLLOW_46_in_statement3612 = new BitSet(
			new long[] { 0x0000800000000000L, 0x0000000008001000L });
	public static final BitSet FOLLOW_switchBlockStatementGroups_in_statement3614 = new BitSet(
			new long[] { 0x0000800000000000L });
	public static final BitSet FOLLOW_47_in_statement3616 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_55_in_statement3626 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_parExpression_in_statement3628 = new BitSet(
			new long[] { 0x0000400040000000L });
	public static final BitSet FOLLOW_block_in_statement3630 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_86_in_statement3640 = new BitSet(
			new long[] { 0xFC02400010003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_expression_in_statement3642 = new BitSet(
			new long[] { 0x0000000010000000L });
	public static final BitSet FOLLOW_28_in_statement3645 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_87_in_statement3655 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_expression_in_statement3657 = new BitSet(
			new long[] { 0x0000000010000000L });
	public static final BitSet FOLLOW_28_in_statement3659 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_88_in_statement3669 = new BitSet(
			new long[] { 0x0000000010000040L });
	public static final BitSet FOLLOW_Identifier_in_statement3671 = new BitSet(
			new long[] { 0x0000000010000000L });
	public static final BitSet FOLLOW_28_in_statement3674 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_89_in_statement3684 = new BitSet(
			new long[] { 0x0000000010000040L });
	public static final BitSet FOLLOW_Identifier_in_statement3686 = new BitSet(
			new long[] { 0x0000000010000000L });
	public static final BitSet FOLLOW_28_in_statement3689 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_28_in_statement3699 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_statementExpression_in_statement3710 = new BitSet(
			new long[] { 0x0000000010000000L });
	public static final BitSet FOLLOW_28_in_statement3712 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_Identifier_in_statement3722 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000002000L });
	public static final BitSet FOLLOW_77_in_statement3724 = new BitSet(
			new long[] { 0xFC8340FE58007FC0L, 0x000F980003EF4F9BL });
	public static final BitSet FOLLOW_statement_in_statement3726 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_catchClause_in_catches3749 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000000004000000L });
	public static final BitSet FOLLOW_catchClause_in_catches3752 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000000004000000L });
	public static final BitSet FOLLOW_90_in_catchClause3777 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_68_in_catchClause3779 = new BitSet(
			new long[] { 0xFC00002000000040L, 0x0000000000000803L });
	public static final BitSet FOLLOW_formalParameter_in_catchClause3781 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000020L });
	public static final BitSet FOLLOW_69_in_catchClause3783 = new BitSet(
			new long[] { 0x0000400040000000L });
	public static final BitSet FOLLOW_block_in_catchClause3785 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_variableModifiers_in_formalParameter3804 = new BitSet(
			new long[] { 0xFC00000000000040L, 0x0000000000000003L });
	public static final BitSet FOLLOW_type_in_formalParameter3806 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_variableDeclaratorId_in_formalParameter3808 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_switchBlockStatementGroup_in_switchBlockStatementGroups3836 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000000008001000L });
	public static final BitSet FOLLOW_switchLabel_in_switchBlockStatementGroup3863 = new BitSet(
			new long[] { 0xFC8340FE58007FC2L, 0x000F98000BEF5F9BL });
	public static final BitSet FOLLOW_blockStatement_in_switchBlockStatementGroup3866 = new BitSet(
			new long[] { 0xFC8340FE58007FC2L, 0x000F980003EF4F9BL });
	public static final BitSet FOLLOW_91_in_switchLabel3890 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_constantExpression_in_switchLabel3892 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000002000L });
	public static final BitSet FOLLOW_77_in_switchLabel3894 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_91_in_switchLabel3904 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_enumConstantName_in_switchLabel3906 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000002000L });
	public static final BitSet FOLLOW_77_in_switchLabel3908 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_76_in_switchLabel3918 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000002000L });
	public static final BitSet FOLLOW_77_in_switchLabel3920 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_enhancedForControl_in_forControl3951 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_forInit_in_forControl3961 = new BitSet(
			new long[] { 0x0000000010000000L });
	public static final BitSet FOLLOW_28_in_forControl3964 = new BitSet(
			new long[] { 0xFC02400010003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_expression_in_forControl3966 = new BitSet(
			new long[] { 0x0000000010000000L });
	public static final BitSet FOLLOW_28_in_forControl3969 = new BitSet(
			new long[] { 0xFC02402000003F42L, 0x000F980000000F9BL });
	public static final BitSet FOLLOW_forUpdate_in_forControl3971 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_localVariableDeclaration_in_forInit3991 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_expressionList_in_forInit4001 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_variableModifiers_in_enhancedForControl4024 = new BitSet(
			new long[] { 0xFC00000000000040L, 0x0000000000000003L });
	public static final BitSet FOLLOW_type_in_enhancedForControl4026 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_Identifier_in_enhancedForControl4028 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000002000L });
	public static final BitSet FOLLOW_77_in_enhancedForControl4030 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_expression_in_enhancedForControl4032 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_expressionList_in_forUpdate4051 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_68_in_parExpression4072 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_expression_in_parExpression4074 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000020L });
	public static final BitSet FOLLOW_69_in_parExpression4076 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_expression_in_expressionList4099 = new BitSet(
			new long[] { 0x0000080000000002L });
	public static final BitSet FOLLOW_43_in_expressionList4102 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_expression_in_expressionList4104 = new BitSet(
			new long[] { 0x0000080000000002L });
	public static final BitSet FOLLOW_expression_in_statementExpression4125 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_expression_in_constantExpression4148 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_conditionalExpression_in_expression4171 = new BitSet(
			new long[] { 0x0020140000000002L, 0x0000000FF0000000L });
	public static final BitSet FOLLOW_assignmentOperator_in_expression4174 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_expression_in_expression4176 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_53_in_assignmentOperator4201 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_92_in_assignmentOperator4211 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_93_in_assignmentOperator4221 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_94_in_assignmentOperator4231 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_95_in_assignmentOperator4241 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_96_in_assignmentOperator4251 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_97_in_assignmentOperator4261 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_98_in_assignmentOperator4271 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_99_in_assignmentOperator4281 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_42_in_assignmentOperator4302 = new BitSet(
			new long[] { 0x0000040000000000L });
	public static final BitSet FOLLOW_42_in_assignmentOperator4306 = new BitSet(
			new long[] { 0x0020000000000000L });
	public static final BitSet FOLLOW_53_in_assignmentOperator4310 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_44_in_assignmentOperator4344 = new BitSet(
			new long[] { 0x0000100000000000L });
	public static final BitSet FOLLOW_44_in_assignmentOperator4348 = new BitSet(
			new long[] { 0x0000100000000000L });
	public static final BitSet FOLLOW_44_in_assignmentOperator4352 = new BitSet(
			new long[] { 0x0020000000000000L });
	public static final BitSet FOLLOW_53_in_assignmentOperator4356 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_44_in_assignmentOperator4387 = new BitSet(
			new long[] { 0x0000100000000000L });
	public static final BitSet FOLLOW_44_in_assignmentOperator4391 = new BitSet(
			new long[] { 0x0020000000000000L });
	public static final BitSet FOLLOW_53_in_assignmentOperator4395 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_conditionalOrExpression_in_conditionalExpression4424 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000000000000004L });
	public static final BitSet FOLLOW_66_in_conditionalExpression4428 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_expression_in_conditionalExpression4430 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000002000L });
	public static final BitSet FOLLOW_77_in_conditionalExpression4432 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_expression_in_conditionalExpression4434 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_conditionalAndExpression_in_conditionalOrExpression4456 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000001000000000L });
	public static final BitSet FOLLOW_100_in_conditionalOrExpression4460 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_conditionalAndExpression_in_conditionalOrExpression4462 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000001000000000L });
	public static final BitSet FOLLOW_inclusiveOrExpression_in_conditionalAndExpression4484 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000002000000000L });
	public static final BitSet FOLLOW_101_in_conditionalAndExpression4488 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_inclusiveOrExpression_in_conditionalAndExpression4490 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000002000000000L });
	public static final BitSet FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression4512 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000004000000000L });
	public static final BitSet FOLLOW_102_in_inclusiveOrExpression4516 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression4518 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000004000000000L });
	public static final BitSet FOLLOW_andExpression_in_exclusiveOrExpression4540 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000008000000000L });
	public static final BitSet FOLLOW_103_in_exclusiveOrExpression4544 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_andExpression_in_exclusiveOrExpression4546 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000008000000000L });
	public static final BitSet FOLLOW_equalityExpression_in_andExpression4568 = new BitSet(
			new long[] { 0x0000200000000002L });
	public static final BitSet FOLLOW_45_in_andExpression4572 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_equalityExpression_in_andExpression4574 = new BitSet(
			new long[] { 0x0000200000000002L });
	public static final BitSet FOLLOW_instanceOfExpression_in_equalityExpression4596 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000030000000000L });
	public static final BitSet FOLLOW_set_in_equalityExpression4600 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_instanceOfExpression_in_equalityExpression4608 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000030000000000L });
	public static final BitSet FOLLOW_relationalExpression_in_instanceOfExpression4630 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000040000000000L });
	public static final BitSet FOLLOW_106_in_instanceOfExpression4633 = new BitSet(
			new long[] { 0xFC00000000000040L, 0x0000000000000003L });
	public static final BitSet FOLLOW_type_in_instanceOfExpression4635 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_shiftExpression_in_relationalExpression4656 = new BitSet(
			new long[] { 0x0000140000000002L });
	public static final BitSet FOLLOW_relationalOp_in_relationalExpression4660 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_shiftExpression_in_relationalExpression4662 = new BitSet(
			new long[] { 0x0000140000000002L });
	public static final BitSet FOLLOW_42_in_relationalOp4697 = new BitSet(
			new long[] { 0x0020000000000000L });
	public static final BitSet FOLLOW_53_in_relationalOp4701 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_44_in_relationalOp4731 = new BitSet(
			new long[] { 0x0020000000000000L });
	public static final BitSet FOLLOW_53_in_relationalOp4735 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_42_in_relationalOp4756 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_44_in_relationalOp4767 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_additiveExpression_in_shiftExpression4787 = new BitSet(
			new long[] { 0x0000140000000002L });
	public static final BitSet FOLLOW_shiftOp_in_shiftExpression4791 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_additiveExpression_in_shiftExpression4793 = new BitSet(
			new long[] { 0x0000140000000002L });
	public static final BitSet FOLLOW_42_in_shiftOp4824 = new BitSet(
			new long[] { 0x0000040000000000L });
	public static final BitSet FOLLOW_42_in_shiftOp4828 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_44_in_shiftOp4860 = new BitSet(
			new long[] { 0x0000100000000000L });
	public static final BitSet FOLLOW_44_in_shiftOp4864 = new BitSet(
			new long[] { 0x0000100000000000L });
	public static final BitSet FOLLOW_44_in_shiftOp4868 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_44_in_shiftOp4898 = new BitSet(
			new long[] { 0x0000100000000000L });
	public static final BitSet FOLLOW_44_in_shiftOp4902 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression4932 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000180000000000L });
	public static final BitSet FOLLOW_set_in_additiveExpression4936 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression4944 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000180000000000L });
	public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression4966 = new BitSet(
			new long[] { 0x0000000100000002L, 0x0000600000000000L });
	public static final BitSet FOLLOW_set_in_multiplicativeExpression4970 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression4984 = new BitSet(
			new long[] { 0x0000000100000002L, 0x0000600000000000L });
	public static final BitSet FOLLOW_107_in_unaryExpression5010 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_unaryExpression_in_unaryExpression5012 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_108_in_unaryExpression5022 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_unaryExpression_in_unaryExpression5024 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_111_in_unaryExpression5034 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_unaryExpression_in_unaryExpression5036 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_112_in_unaryExpression5046 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_unaryExpression_in_unaryExpression5048 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_unaryExpressionNotPlusMinus_in_unaryExpression5058 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_113_in_unaryExpressionNotPlusMinus5077 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus5079 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_114_in_unaryExpressionNotPlusMinus5089 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus5091 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_castExpression_in_unaryExpressionNotPlusMinus5101 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_primary_in_unaryExpressionNotPlusMinus5111 = new BitSet(
			new long[] { 0x0004000080000002L, 0x0001800000000000L });
	public static final BitSet FOLLOW_selector_in_unaryExpressionNotPlusMinus5113 = new BitSet(
			new long[] { 0x0004000080000002L, 0x0001800000000000L });
	public static final BitSet FOLLOW_set_in_unaryExpressionNotPlusMinus5116 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_68_in_castExpression5139 = new BitSet(
			new long[] { 0xFC00000000000040L, 0x0000000000000003L });
	public static final BitSet FOLLOW_primitiveType_in_castExpression5141 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000020L });
	public static final BitSet FOLLOW_69_in_castExpression5143 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_unaryExpression_in_castExpression5145 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_68_in_castExpression5154 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_type_in_castExpression5157 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000020L });
	public static final BitSet FOLLOW_expression_in_castExpression5161 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000020L });
	public static final BitSet FOLLOW_69_in_castExpression5164 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_unaryExpressionNotPlusMinus_in_castExpression5166 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_parExpression_in_primary5185 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_71_in_primary5195 = new BitSet(
			new long[] { 0x0004000080000002L, 0x0000000000000010L });
	public static final BitSet FOLLOW_31_in_primary5198 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_Identifier_in_primary5200 = new BitSet(
			new long[] { 0x0004000080000002L, 0x0000000000000010L });
	public static final BitSet FOLLOW_identifierSuffix_in_primary5204 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_67_in_primary5215 = new BitSet(
			new long[] { 0x0000000080000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_superSuffix_in_primary5217 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_literal_in_primary5227 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_115_in_primary5237 = new BitSet(
			new long[] { 0xFC00040000000040L, 0x0000000000000003L });
	public static final BitSet FOLLOW_creator_in_primary5239 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_Identifier_in_primary5249 = new BitSet(
			new long[] { 0x0004000080000002L, 0x0000000000000010L });
	public static final BitSet FOLLOW_31_in_primary5252 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_Identifier_in_primary5254 = new BitSet(
			new long[] { 0x0004000080000002L, 0x0000000000000010L });
	public static final BitSet FOLLOW_identifierSuffix_in_primary5258 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_primitiveType_in_primary5269 = new BitSet(
			new long[] { 0x0004000080000000L });
	public static final BitSet FOLLOW_50_in_primary5272 = new BitSet(
			new long[] { 0x0008000000000000L });
	public static final BitSet FOLLOW_51_in_primary5274 = new BitSet(
			new long[] { 0x0004000080000000L });
	public static final BitSet FOLLOW_31_in_primary5278 = new BitSet(
			new long[] { 0x0000008000000000L });
	public static final BitSet FOLLOW_39_in_primary5280 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_49_in_primary5290 = new BitSet(
			new long[] { 0x0000000080000000L });
	public static final BitSet FOLLOW_31_in_primary5292 = new BitSet(
			new long[] { 0x0000008000000000L });
	public static final BitSet FOLLOW_39_in_primary5294 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_50_in_identifierSuffix5314 = new BitSet(
			new long[] { 0x0008000000000000L });
	public static final BitSet FOLLOW_51_in_identifierSuffix5316 = new BitSet(
			new long[] { 0x0004000080000000L });
	public static final BitSet FOLLOW_31_in_identifierSuffix5320 = new BitSet(
			new long[] { 0x0000008000000000L });
	public static final BitSet FOLLOW_39_in_identifierSuffix5322 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_50_in_identifierSuffix5333 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_expression_in_identifierSuffix5335 = new BitSet(
			new long[] { 0x0008000000000000L });
	public static final BitSet FOLLOW_51_in_identifierSuffix5337 = new BitSet(
			new long[] { 0x0004000000000002L });
	public static final BitSet FOLLOW_arguments_in_identifierSuffix5350 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_31_in_identifierSuffix5360 = new BitSet(
			new long[] { 0x0000008000000000L });
	public static final BitSet FOLLOW_39_in_identifierSuffix5362 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_31_in_identifierSuffix5372 = new BitSet(
			new long[] { 0x0000040000000000L });
	public static final BitSet FOLLOW_explicitGenericInvocation_in_identifierSuffix5374 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_31_in_identifierSuffix5384 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000080L });
	public static final BitSet FOLLOW_71_in_identifierSuffix5386 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_31_in_identifierSuffix5396 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000008L });
	public static final BitSet FOLLOW_67_in_identifierSuffix5398 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_arguments_in_identifierSuffix5400 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_31_in_identifierSuffix5410 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0008000000000000L });
	public static final BitSet FOLLOW_115_in_identifierSuffix5412 = new BitSet(
			new long[] { 0x0000040000000040L });
	public static final BitSet FOLLOW_innerCreator_in_identifierSuffix5414 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_nonWildcardTypeArguments_in_creator5433 = new BitSet(
			new long[] { 0xFC00040000000040L, 0x0000000000000003L });
	public static final BitSet FOLLOW_createdName_in_creator5435 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_classCreatorRest_in_creator5437 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_createdName_in_creator5447 = new BitSet(
			new long[] { 0x0004000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_arrayCreatorRest_in_creator5450 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_classCreatorRest_in_creator5454 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_classOrInterfaceType_in_createdName5474 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_primitiveType_in_createdName5484 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_nonWildcardTypeArguments_in_innerCreator5507 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_Identifier_in_innerCreator5510 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_classCreatorRest_in_innerCreator5512 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_50_in_arrayCreatorRest5531 = new BitSet(
			new long[] { 0xFC0A400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_51_in_arrayCreatorRest5545 = new BitSet(
			new long[] { 0x0004400000000000L });
	public static final BitSet FOLLOW_50_in_arrayCreatorRest5548 = new BitSet(
			new long[] { 0x0008000000000000L });
	public static final BitSet FOLLOW_51_in_arrayCreatorRest5550 = new BitSet(
			new long[] { 0x0004400000000000L });
	public static final BitSet FOLLOW_arrayInitializer_in_arrayCreatorRest5554 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_expression_in_arrayCreatorRest5568 = new BitSet(
			new long[] { 0x0008000000000000L });
	public static final BitSet FOLLOW_51_in_arrayCreatorRest5570 = new BitSet(
			new long[] { 0x0004000000000002L });
	public static final BitSet FOLLOW_50_in_arrayCreatorRest5573 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_expression_in_arrayCreatorRest5575 = new BitSet(
			new long[] { 0x0008000000000000L });
	public static final BitSet FOLLOW_51_in_arrayCreatorRest5577 = new BitSet(
			new long[] { 0x0004000000000002L });
	public static final BitSet FOLLOW_50_in_arrayCreatorRest5582 = new BitSet(
			new long[] { 0x0008000000000000L });
	public static final BitSet FOLLOW_51_in_arrayCreatorRest5584 = new BitSet(
			new long[] { 0x0004000000000002L });
	public static final BitSet FOLLOW_arguments_in_classCreatorRest5615 = new BitSet(
			new long[] { 0x0000470000000002L });
	public static final BitSet FOLLOW_classBody_in_classCreatorRest5617 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_nonWildcardTypeArguments_in_explicitGenericInvocation5641 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_Identifier_in_explicitGenericInvocation5643 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_arguments_in_explicitGenericInvocation5645 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_42_in_nonWildcardTypeArguments5668 = new BitSet(
			new long[] { 0xFC00000000000040L, 0x0000000000000003L });
	public static final BitSet FOLLOW_typeList_in_nonWildcardTypeArguments5670 = new BitSet(
			new long[] { 0x0000100000000000L });
	public static final BitSet FOLLOW_44_in_nonWildcardTypeArguments5672 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_31_in_selector5695 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_Identifier_in_selector5697 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000000000000010L });
	public static final BitSet FOLLOW_arguments_in_selector5699 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_31_in_selector5710 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000080L });
	public static final BitSet FOLLOW_71_in_selector5712 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_31_in_selector5722 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000008L });
	public static final BitSet FOLLOW_67_in_selector5724 = new BitSet(
			new long[] { 0x0000000080000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_superSuffix_in_selector5726 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_31_in_selector5736 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0008000000000000L });
	public static final BitSet FOLLOW_115_in_selector5738 = new BitSet(
			new long[] { 0x0000040000000040L });
	public static final BitSet FOLLOW_innerCreator_in_selector5740 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_50_in_selector5750 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_expression_in_selector5752 = new BitSet(
			new long[] { 0x0008000000000000L });
	public static final BitSet FOLLOW_51_in_selector5754 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_arguments_in_superSuffix5777 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_31_in_superSuffix5787 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_Identifier_in_superSuffix5789 = new BitSet(
			new long[] { 0x0000000000000002L, 0x0000000000000010L });
	public static final BitSet FOLLOW_arguments_in_superSuffix5791 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_68_in_arguments5811 = new BitSet(
			new long[] { 0xFC02402000003F40L, 0x000F980000000FBBL });
	public static final BitSet FOLLOW_expressionList_in_arguments5813 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000020L });
	public static final BitSet FOLLOW_69_in_arguments5816 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_annotations_in_synpred5_Java89 = new BitSet(
			new long[] { 0x000100FE48000080L, 0x0000000000000800L });
	public static final BitSet FOLLOW_packageDeclaration_in_synpred5_Java103 = new BitSet(
			new long[] { 0x000100FE78000082L, 0x0000000000000800L });
	public static final BitSet FOLLOW_importDeclaration_in_synpred5_Java105 = new BitSet(
			new long[] { 0x000100FE78000082L, 0x0000000000000800L });
	public static final BitSet FOLLOW_typeDeclaration_in_synpred5_Java108 = new BitSet(
			new long[] { 0x000100FE58000082L, 0x0000000000000800L });
	public static final BitSet FOLLOW_classOrInterfaceDeclaration_in_synpred5_Java123 = new BitSet(
			new long[] { 0x000100FE58000082L, 0x0000000000000800L });
	public static final BitSet FOLLOW_typeDeclaration_in_synpred5_Java125 = new BitSet(
			new long[] { 0x000100FE58000082L, 0x0000000000000800L });
	public static final BitSet FOLLOW_classOrInterfaceModifiers_in_synpred13_Java289 = new BitSet(
			new long[] { 0x000000FE40000080L, 0x0000000000000800L });
	public static final BitSet FOLLOW_classDeclaration_in_synpred13_Java291 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_explicitConstructorInvocation_in_synpred113_Java2547 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_nonWildcardTypeArguments_in_synpred117_Java2572 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000088L });
	public static final BitSet FOLLOW_set_in_synpred117_Java2575 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000010L });
	public static final BitSet FOLLOW_arguments_in_synpred117_Java2583 = new BitSet(
			new long[] { 0x0000000010000000L });
	public static final BitSet FOLLOW_28_in_synpred117_Java2585 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_annotation_in_synpred128_Java2796 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_localVariableDeclarationStatement_in_synpred151_Java3323 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_classOrInterfaceDeclaration_in_synpred152_Java3333 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_79_in_synpred157_Java3478 = new BitSet(
			new long[] { 0xFC8340FE58007FC0L, 0x000F980003EF4F9BL });
	public static final BitSet FOLLOW_statement_in_synpred157_Java3480 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_catches_in_synpred162_Java3556 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000100000L });
	public static final BitSet FOLLOW_84_in_synpred162_Java3558 = new BitSet(
			new long[] { 0x0000400040000000L });
	public static final BitSet FOLLOW_block_in_synpred162_Java3560 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_catches_in_synpred163_Java3572 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_switchLabel_in_synpred178_Java3863 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_91_in_synpred180_Java3890 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_constantExpression_in_synpred180_Java3892 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000002000L });
	public static final BitSet FOLLOW_77_in_synpred180_Java3894 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_91_in_synpred181_Java3904 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_enumConstantName_in_synpred181_Java3906 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000002000L });
	public static final BitSet FOLLOW_77_in_synpred181_Java3908 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_enhancedForControl_in_synpred182_Java3951 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_localVariableDeclaration_in_synpred186_Java3991 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_assignmentOperator_in_synpred188_Java4174 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_expression_in_synpred188_Java4176 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_42_in_synpred198_Java4292 = new BitSet(
			new long[] { 0x0000040000000000L });
	public static final BitSet FOLLOW_42_in_synpred198_Java4294 = new BitSet(
			new long[] { 0x0020000000000000L });
	public static final BitSet FOLLOW_53_in_synpred198_Java4296 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_44_in_synpred199_Java4332 = new BitSet(
			new long[] { 0x0000100000000000L });
	public static final BitSet FOLLOW_44_in_synpred199_Java4334 = new BitSet(
			new long[] { 0x0000100000000000L });
	public static final BitSet FOLLOW_44_in_synpred199_Java4336 = new BitSet(
			new long[] { 0x0020000000000000L });
	public static final BitSet FOLLOW_53_in_synpred199_Java4338 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_44_in_synpred200_Java4377 = new BitSet(
			new long[] { 0x0000100000000000L });
	public static final BitSet FOLLOW_44_in_synpred200_Java4379 = new BitSet(
			new long[] { 0x0020000000000000L });
	public static final BitSet FOLLOW_53_in_synpred200_Java4381 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_42_in_synpred211_Java4689 = new BitSet(
			new long[] { 0x0020000000000000L });
	public static final BitSet FOLLOW_53_in_synpred211_Java4691 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_44_in_synpred212_Java4723 = new BitSet(
			new long[] { 0x0020000000000000L });
	public static final BitSet FOLLOW_53_in_synpred212_Java4725 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_42_in_synpred215_Java4816 = new BitSet(
			new long[] { 0x0000040000000000L });
	public static final BitSet FOLLOW_42_in_synpred215_Java4818 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_44_in_synpred216_Java4850 = new BitSet(
			new long[] { 0x0000100000000000L });
	public static final BitSet FOLLOW_44_in_synpred216_Java4852 = new BitSet(
			new long[] { 0x0000100000000000L });
	public static final BitSet FOLLOW_44_in_synpred216_Java4854 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_44_in_synpred217_Java4890 = new BitSet(
			new long[] { 0x0000100000000000L });
	public static final BitSet FOLLOW_44_in_synpred217_Java4892 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_castExpression_in_synpred229_Java5101 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_68_in_synpred233_Java5139 = new BitSet(
			new long[] { 0xFC00000000000040L, 0x0000000000000003L });
	public static final BitSet FOLLOW_primitiveType_in_synpred233_Java5141 = new BitSet(
			new long[] { 0x0000000000000000L, 0x0000000000000020L });
	public static final BitSet FOLLOW_69_in_synpred233_Java5143 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_unaryExpression_in_synpred233_Java5145 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_type_in_synpred234_Java5157 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_31_in_synpred236_Java5198 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_Identifier_in_synpred236_Java5200 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_identifierSuffix_in_synpred237_Java5204 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_31_in_synpred242_Java5252 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_Identifier_in_synpred242_Java5254 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_identifierSuffix_in_synpred243_Java5258 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_50_in_synpred249_Java5333 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_expression_in_synpred249_Java5335 = new BitSet(
			new long[] { 0x0008000000000000L });
	public static final BitSet FOLLOW_51_in_synpred249_Java5337 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_50_in_synpred262_Java5573 = new BitSet(
			new long[] { 0xFC02400000003F40L, 0x000F98000000079BL });
	public static final BitSet FOLLOW_expression_in_synpred262_Java5575 = new BitSet(
			new long[] { 0x0008000000000000L });
	public static final BitSet FOLLOW_51_in_synpred262_Java5577 = new BitSet(
			new long[] { 0x0000000000000002L });

}