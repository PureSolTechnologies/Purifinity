package com.puresol.coding.lang.java;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.AbstractProgrammingLanguage;
import com.puresol.coding.analysis.Analyser;
import com.puresol.coding.lang.java.source.keywords.AbstractKeyword;
import com.puresol.coding.lang.java.source.keywords.AssertKeyword;
import com.puresol.coding.lang.java.source.keywords.BooleanKeyword;
import com.puresol.coding.lang.java.source.keywords.BreakKeyword;
import com.puresol.coding.lang.java.source.keywords.ByteKeyword;
import com.puresol.coding.lang.java.source.keywords.CaseKeyword;
import com.puresol.coding.lang.java.source.keywords.CatchKeyword;
import com.puresol.coding.lang.java.source.keywords.CharKeyword;
import com.puresol.coding.lang.java.source.keywords.ClassKeyword;
import com.puresol.coding.lang.java.source.keywords.ConstKeyword;
import com.puresol.coding.lang.java.source.keywords.ContinueKeyword;
import com.puresol.coding.lang.java.source.keywords.DefaultKeyword;
import com.puresol.coding.lang.java.source.keywords.DoKeyword;
import com.puresol.coding.lang.java.source.keywords.DoubleKeyword;
import com.puresol.coding.lang.java.source.keywords.ElseKeyword;
import com.puresol.coding.lang.java.source.keywords.EnumKeyword;
import com.puresol.coding.lang.java.source.keywords.ExtendsKeyword;
import com.puresol.coding.lang.java.source.keywords.FinalKeyword;
import com.puresol.coding.lang.java.source.keywords.FinallyKeyword;
import com.puresol.coding.lang.java.source.keywords.FloatKeyword;
import com.puresol.coding.lang.java.source.keywords.ForKeyword;
import com.puresol.coding.lang.java.source.keywords.GotoKeyword;
import com.puresol.coding.lang.java.source.keywords.IfKeyword;
import com.puresol.coding.lang.java.source.keywords.ImplementsKeyword;
import com.puresol.coding.lang.java.source.keywords.ImportKeyword;
import com.puresol.coding.lang.java.source.keywords.InstanceofKeyword;
import com.puresol.coding.lang.java.source.keywords.IntKeyword;
import com.puresol.coding.lang.java.source.keywords.InterfaceKeyword;
import com.puresol.coding.lang.java.source.keywords.LongKeyword;
import com.puresol.coding.lang.java.source.keywords.NativeKeyword;
import com.puresol.coding.lang.java.source.keywords.NewKeyword;
import com.puresol.coding.lang.java.source.keywords.PackageKeyword;
import com.puresol.coding.lang.java.source.keywords.PrivateKeyword;
import com.puresol.coding.lang.java.source.keywords.ProtectedKeyword;
import com.puresol.coding.lang.java.source.keywords.PublicKeyword;
import com.puresol.coding.lang.java.source.keywords.ReturnKeyword;
import com.puresol.coding.lang.java.source.keywords.ShortKeyword;
import com.puresol.coding.lang.java.source.keywords.StaticKeyword;
import com.puresol.coding.lang.java.source.keywords.StrictfpKeyword;
import com.puresol.coding.lang.java.source.keywords.SuperKeyword;
import com.puresol.coding.lang.java.source.keywords.SwitchKeyword;
import com.puresol.coding.lang.java.source.keywords.SynchronizedKeyword;
import com.puresol.coding.lang.java.source.keywords.ThisKeyword;
import com.puresol.coding.lang.java.source.keywords.ThrowKeyword;
import com.puresol.coding.lang.java.source.keywords.ThrowsKeyword;
import com.puresol.coding.lang.java.source.keywords.TransientKeyword;
import com.puresol.coding.lang.java.source.keywords.TryKeyword;
import com.puresol.coding.lang.java.source.keywords.VoidKeyword;
import com.puresol.coding.lang.java.source.keywords.VolatileKeyword;
import com.puresol.coding.lang.java.source.keywords.WhileKeyword;
import com.puresol.coding.lang.java.source.literals.BooleanLiteral;
import com.puresol.coding.lang.java.source.literals.CharacterLiteral;
import com.puresol.coding.lang.java.source.literals.FloatingPointLiteral;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.literals.IntegerLiteral;
import com.puresol.coding.lang.java.source.literals.NullLiteral;
import com.puresol.coding.lang.java.source.literals.StringLiteral;
import com.puresol.coding.lang.java.source.symbols.Ampersand;
import com.puresol.coding.lang.java.source.symbols.AmpersandAssign;
import com.puresol.coding.lang.java.source.symbols.Assign;
import com.puresol.coding.lang.java.source.symbols.At;
import com.puresol.coding.lang.java.source.symbols.BackSlash;
import com.puresol.coding.lang.java.source.symbols.BitOr;
import com.puresol.coding.lang.java.source.symbols.BitOrAssign;
import com.puresol.coding.lang.java.source.symbols.Caret;
import com.puresol.coding.lang.java.source.symbols.CaretAssign;
import com.puresol.coding.lang.java.source.symbols.Colon;
import com.puresol.coding.lang.java.source.symbols.Comma;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.coding.lang.java.source.symbols.DotDotDot;
import com.puresol.coding.lang.java.source.symbols.EndOfLineComment;
import com.puresol.coding.lang.java.source.symbols.Equal;
import com.puresol.coding.lang.java.source.symbols.ExclamationMark;
import com.puresol.coding.lang.java.source.symbols.GreaterEqual;
import com.puresol.coding.lang.java.source.symbols.GreaterThan;
import com.puresol.coding.lang.java.source.symbols.GreaterThanGreaterThanAssign;
import com.puresol.coding.lang.java.source.symbols.GreaterThanGreaterThanGreaterThanAssign;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.LRectBracket;
import com.puresol.coding.lang.java.source.symbols.LessEqual;
import com.puresol.coding.lang.java.source.symbols.LessThan;
import com.puresol.coding.lang.java.source.symbols.LessThanLessThanAssign;
import com.puresol.coding.lang.java.source.symbols.LineBreak;
import com.puresol.coding.lang.java.source.symbols.LogicalAnd;
import com.puresol.coding.lang.java.source.symbols.LogicalOr;
import com.puresol.coding.lang.java.source.symbols.Minus;
import com.puresol.coding.lang.java.source.symbols.MinusAssign;
import com.puresol.coding.lang.java.source.symbols.MinusMinus;
import com.puresol.coding.lang.java.source.symbols.Not;
import com.puresol.coding.lang.java.source.symbols.Percent;
import com.puresol.coding.lang.java.source.symbols.PercentAssign;
import com.puresol.coding.lang.java.source.symbols.Plus;
import com.puresol.coding.lang.java.source.symbols.PlusAssign;
import com.puresol.coding.lang.java.source.symbols.PlusPlus;
import com.puresol.coding.lang.java.source.symbols.QuestionMark;
import com.puresol.coding.lang.java.source.symbols.RCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.RParen;
import com.puresol.coding.lang.java.source.symbols.RRectBracket;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.coding.lang.java.source.symbols.Slash;
import com.puresol.coding.lang.java.source.symbols.SlashAssign;
import com.puresol.coding.lang.java.source.symbols.Star;
import com.puresol.coding.lang.java.source.symbols.StarAssign;
import com.puresol.coding.lang.java.source.symbols.Tilde;
import com.puresol.coding.lang.java.source.symbols.TraditionalComment;
import com.puresol.coding.lang.java.source.symbols.Unequal;
import com.puresol.coding.lang.java.source.symbols.WhiteSpace;
import com.puresol.parser.TokenDefinition;

/**
 * This is the base class for Java Programming Language. The lexical and
 * syntactical information were taken out of "The Java™ Language Specification
 * -- Third Edition".
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Java extends AbstractProgrammingLanguage {

	private static final String[] FILE_SUFFIXES = { ".java" };

	private static final List<Class<? extends TokenDefinition>> KEYWORDS = new ArrayList<Class<? extends TokenDefinition>>();
	static {
		KEYWORDS.add(PackageKeyword.class);
		KEYWORDS.add(ImportKeyword.class);

		KEYWORDS.add(ClassKeyword.class);
		KEYWORDS.add(InterfaceKeyword.class);
		KEYWORDS.add(EnumKeyword.class);
		KEYWORDS.add(ExtendsKeyword.class);
		KEYWORDS.add(ImplementsKeyword.class);
		KEYWORDS.add(NewKeyword.class);

		KEYWORDS.add(PublicKeyword.class);
		KEYWORDS.add(ProtectedKeyword.class);
		KEYWORDS.add(PrivateKeyword.class);
		KEYWORDS.add(StaticKeyword.class);
		KEYWORDS.add(FinalKeyword.class);
		KEYWORDS.add(ConstKeyword.class);
		KEYWORDS.add(NativeKeyword.class);
		KEYWORDS.add(TransientKeyword.class);
		KEYWORDS.add(VolatileKeyword.class);
		KEYWORDS.add(AbstractKeyword.class);
		KEYWORDS.add(SynchronizedKeyword.class);
		KEYWORDS.add(StrictfpKeyword.class);

		KEYWORDS.add(ReturnKeyword.class);
		KEYWORDS.add(BreakKeyword.class);
		KEYWORDS.add(ContinueKeyword.class);

		KEYWORDS.add(IfKeyword.class);
		KEYWORDS.add(ElseKeyword.class);
		KEYWORDS.add(WhileKeyword.class);
		KEYWORDS.add(DoKeyword.class);
		KEYWORDS.add(ForKeyword.class);
		KEYWORDS.add(SwitchKeyword.class);
		KEYWORDS.add(CaseKeyword.class);
		KEYWORDS.add(DefaultKeyword.class);

		KEYWORDS.add(TryKeyword.class);
		KEYWORDS.add(CatchKeyword.class);
		KEYWORDS.add(FinallyKeyword.class);
		KEYWORDS.add(ThrowsKeyword.class);
		KEYWORDS.add(ThrowKeyword.class);

		KEYWORDS.add(InstanceofKeyword.class);
		KEYWORDS.add(SuperKeyword.class);
		KEYWORDS.add(ThisKeyword.class);

		KEYWORDS.add(AssertKeyword.class);
		KEYWORDS.add(GotoKeyword.class);

		KEYWORDS.add(VoidKeyword.class);
		KEYWORDS.add(BooleanKeyword.class);
		KEYWORDS.add(CharKeyword.class);
		KEYWORDS.add(ByteKeyword.class);
		KEYWORDS.add(ShortKeyword.class);
		KEYWORDS.add(IntKeyword.class);
		KEYWORDS.add(LongKeyword.class);
		KEYWORDS.add(FloatKeyword.class);
		KEYWORDS.add(DoubleKeyword.class);
	}

	private static final List<Class<? extends TokenDefinition>> LITERALS = new ArrayList<Class<? extends TokenDefinition>>();
	static {
		LITERALS.add(NullLiteral.class);
		LITERALS.add(FloatingPointLiteral.class);
		LITERALS.add(IntegerLiteral.class);
		LITERALS.add(CharacterLiteral.class);
		LITERALS.add(StringLiteral.class);
		LITERALS.add(BooleanLiteral.class);
		LITERALS.add(Identifier.class);
	}

	private static final List<Class<? extends TokenDefinition>> SYMBOLS = new ArrayList<Class<? extends TokenDefinition>>();
	static {
		SYMBOLS.add(TraditionalComment.class);
		SYMBOLS.add(EndOfLineComment.class);
		SYMBOLS.add(LineBreak.class);
		SYMBOLS.add(WhiteSpace.class);

		SYMBOLS.add(LParen.class);
		SYMBOLS.add(RParen.class);
		SYMBOLS.add(LCurlyBracket.class);
		SYMBOLS.add(RCurlyBracket.class);
		SYMBOLS.add(LRectBracket.class);
		SYMBOLS.add(RRectBracket.class);

		SYMBOLS.add(LessEqual.class);
		SYMBOLS.add(GreaterEqual.class);
		SYMBOLS.add(LessThan.class);
		SYMBOLS.add(GreaterThan.class);
		SYMBOLS.add(Equal.class);
		SYMBOLS.add(Unequal.class);

		SYMBOLS.add(PlusAssign.class);
		SYMBOLS.add(MinusAssign.class);
		SYMBOLS.add(LessThanLessThanAssign.class);
		SYMBOLS.add(GreaterThanGreaterThanAssign.class);
		SYMBOLS.add(GreaterThanGreaterThanGreaterThanAssign.class);
		SYMBOLS.add(AmpersandAssign.class);
		SYMBOLS.add(CaretAssign.class);
		SYMBOLS.add(BitOrAssign.class);

		SYMBOLS.add(SlashAssign.class);
		SYMBOLS.add(StarAssign.class);
		SYMBOLS.add(PercentAssign.class);
		SYMBOLS.add(Assign.class);

		SYMBOLS.add(At.class);
		SYMBOLS.add(Caret.class);

		SYMBOLS.add(PlusPlus.class);
		SYMBOLS.add(MinusMinus.class);

		SYMBOLS.add(Plus.class);
		SYMBOLS.add(Minus.class);
		SYMBOLS.add(Slash.class);
		SYMBOLS.add(Star.class);
		SYMBOLS.add(Percent.class);
		SYMBOLS.add(BackSlash.class);
		SYMBOLS.add(Tilde.class);

		SYMBOLS.add(LogicalAnd.class);
		SYMBOLS.add(LogicalOr.class);
		SYMBOLS.add(Ampersand.class);
		SYMBOLS.add(BitOr.class);
		SYMBOLS.add(Not.class);

		SYMBOLS.add(ExclamationMark.class);
		SYMBOLS.add(QuestionMark.class);

		SYMBOLS.add(Semicolon.class);
		SYMBOLS.add(Comma.class);
		SYMBOLS.add(Colon.class);
		SYMBOLS.add(DotDotDot.class);
		SYMBOLS.add(Dot.class);
	}

	private static Java instance = null;

	public static Java getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new Java();
		}
	}

	private Java() {
		super("Java");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isObjectOriented() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class<? extends Analyser> getAnalyserClass() {
		return JavaAnalyser.class;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] getValidFileSuffixes() {
		return FILE_SUFFIXES;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Class<? extends TokenDefinition>> getKeywords() {
		return KEYWORDS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Class<? extends TokenDefinition>> getLiterals() {
		return LITERALS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Class<? extends TokenDefinition>> getSymbols() {
		return SYMBOLS;
	}

}
