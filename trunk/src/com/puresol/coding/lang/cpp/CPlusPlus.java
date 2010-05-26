package com.puresol.coding.lang.cpp;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.AbstractProgrammingLanguage;
import com.puresol.coding.analysis.Analyser;
import com.puresol.coding.lang.cpp.source.keywords.BoolKeyword;
import com.puresol.coding.lang.cpp.source.keywords.BreakKeyword;
import com.puresol.coding.lang.cpp.source.keywords.CaseKeyword;
import com.puresol.coding.lang.cpp.source.keywords.CharKeyword;
import com.puresol.coding.lang.cpp.source.keywords.ClassKeyword;
import com.puresol.coding.lang.cpp.source.keywords.ConstKeyword;
import com.puresol.coding.lang.cpp.source.keywords.ContinueKeyword;
import com.puresol.coding.lang.cpp.source.keywords.DoKeyword;
import com.puresol.coding.lang.cpp.source.keywords.DoubleKeyword;
import com.puresol.coding.lang.cpp.source.keywords.ElseKeyword;
import com.puresol.coding.lang.cpp.source.keywords.FloatKeyword;
import com.puresol.coding.lang.cpp.source.keywords.ForKeyword;
import com.puresol.coding.lang.cpp.source.keywords.IfKeyword;
import com.puresol.coding.lang.cpp.source.keywords.IntKeyword;
import com.puresol.coding.lang.cpp.source.keywords.LongKeyword;
import com.puresol.coding.lang.cpp.source.keywords.NewKeyword;
import com.puresol.coding.lang.cpp.source.keywords.NullKeyword;
import com.puresol.coding.lang.cpp.source.keywords.PPDefineKeyword;
import com.puresol.coding.lang.cpp.source.keywords.PPElseKeyword;
import com.puresol.coding.lang.cpp.source.keywords.PPEndIfKeyword;
import com.puresol.coding.lang.cpp.source.keywords.PPIfDefKeyword;
import com.puresol.coding.lang.cpp.source.keywords.PPIncludeKeyword;
import com.puresol.coding.lang.cpp.source.keywords.PrivateKeyword;
import com.puresol.coding.lang.cpp.source.keywords.ProtectedKeyword;
import com.puresol.coding.lang.cpp.source.keywords.PublicKeyword;
import com.puresol.coding.lang.cpp.source.keywords.ReturnKeyword;
import com.puresol.coding.lang.cpp.source.keywords.StaticKeyword;
import com.puresol.coding.lang.cpp.source.keywords.SwitchKeyword;
import com.puresol.coding.lang.cpp.source.keywords.ThisKeyword;
import com.puresol.coding.lang.cpp.source.keywords.VoidKeyword;
import com.puresol.coding.lang.cpp.source.keywords.WhileKeyword;
import com.puresol.coding.lang.cpp.source.literals.BooleanLiteral;
import com.puresol.coding.lang.cpp.source.literals.CharacterLiteral;
import com.puresol.coding.lang.cpp.source.literals.FloatingPointLiteral;
import com.puresol.coding.lang.cpp.source.literals.IdLiteral;
import com.puresol.coding.lang.cpp.source.literals.IntegerLiteral;
import com.puresol.coding.lang.cpp.source.literals.StringLiteral;
import com.puresol.coding.lang.cpp.source.symbols.Assign;
import com.puresol.coding.lang.cpp.source.symbols.BackSlash;
import com.puresol.coding.lang.cpp.source.symbols.BitAnd;
import com.puresol.coding.lang.cpp.source.symbols.BitOr;
import com.puresol.coding.lang.cpp.source.symbols.Caret;
import com.puresol.coding.lang.cpp.source.symbols.Colon;
import com.puresol.coding.lang.cpp.source.symbols.Comma;
import com.puresol.coding.lang.cpp.source.symbols.Dot;
import com.puresol.coding.lang.cpp.source.symbols.Equal;
import com.puresol.coding.lang.cpp.source.symbols.GreaterEqual;
import com.puresol.coding.lang.cpp.source.symbols.GreaterThan;
import com.puresol.coding.lang.cpp.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.cpp.source.symbols.LParen;
import com.puresol.coding.lang.cpp.source.symbols.LRectBracket;
import com.puresol.coding.lang.cpp.source.symbols.LessEqual;
import com.puresol.coding.lang.cpp.source.symbols.LessThan;
import com.puresol.coding.lang.cpp.source.symbols.LineBreak;
import com.puresol.coding.lang.cpp.source.symbols.LineComment;
import com.puresol.coding.lang.cpp.source.symbols.LogicalAnd;
import com.puresol.coding.lang.cpp.source.symbols.LogicalOr;
import com.puresol.coding.lang.cpp.source.symbols.Minus;
import com.puresol.coding.lang.cpp.source.symbols.MinusAssign;
import com.puresol.coding.lang.cpp.source.symbols.MinusMinus;
import com.puresol.coding.lang.cpp.source.symbols.MultiLineComment;
import com.puresol.coding.lang.cpp.source.symbols.Not;
import com.puresol.coding.lang.cpp.source.symbols.Percent;
import com.puresol.coding.lang.cpp.source.symbols.Plus;
import com.puresol.coding.lang.cpp.source.symbols.PlusAssign;
import com.puresol.coding.lang.cpp.source.symbols.PlusPlus;
import com.puresol.coding.lang.cpp.source.symbols.QuestionMark;
import com.puresol.coding.lang.cpp.source.symbols.RCurlyBracket;
import com.puresol.coding.lang.cpp.source.symbols.RParen;
import com.puresol.coding.lang.cpp.source.symbols.RRectBracket;
import com.puresol.coding.lang.cpp.source.symbols.Semicolon;
import com.puresol.coding.lang.cpp.source.symbols.Slash;
import com.puresol.coding.lang.cpp.source.symbols.SlashAssign;
import com.puresol.coding.lang.cpp.source.symbols.Star;
import com.puresol.coding.lang.cpp.source.symbols.StarAssign;
import com.puresol.coding.lang.cpp.source.symbols.Tilde;
import com.puresol.coding.lang.cpp.source.symbols.Unequal;
import com.puresol.coding.lang.cpp.source.symbols.WhiteSpace;
import com.puresol.parser.TokenDefinition;

public class CPlusPlus extends AbstractProgrammingLanguage {

    private static final String[] FILE_SUFFIXES = { ".h", ".c", ".hpp", ".cpp",
	    ".hxx", ".cxx" };

    private static final List<Class<? extends TokenDefinition>> KEYWORDS = new ArrayList<Class<? extends TokenDefinition>>();
    static {
	KEYWORDS.add(PPIncludeKeyword.class);
	KEYWORDS.add(PPDefineKeyword.class);
	KEYWORDS.add(PPIfDefKeyword.class);
	KEYWORDS.add(PPEndIfKeyword.class);
	KEYWORDS.add(PPElseKeyword.class);

	KEYWORDS.add(ClassKeyword.class);
	KEYWORDS.add(NewKeyword.class);

	KEYWORDS.add(PublicKeyword.class);
	KEYWORDS.add(ProtectedKeyword.class);
	KEYWORDS.add(PrivateKeyword.class);
	KEYWORDS.add(StaticKeyword.class);

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

	KEYWORDS.add(ThisKeyword.class);
	KEYWORDS.add(NullKeyword.class);

	KEYWORDS.add(VoidKeyword.class);
	KEYWORDS.add(ConstKeyword.class);
	KEYWORDS.add(BoolKeyword.class);
	KEYWORDS.add(CharKeyword.class);
	KEYWORDS.add(IntKeyword.class);
	KEYWORDS.add(LongKeyword.class);
	KEYWORDS.add(FloatKeyword.class);
	KEYWORDS.add(DoubleKeyword.class);
    }

    private static final List<Class<? extends TokenDefinition>> LITERALS = new ArrayList<Class<? extends TokenDefinition>>();
    static {
	LITERALS.add(FloatingPointLiteral.class);
	LITERALS.add(IntegerLiteral.class);
	LITERALS.add(CharacterLiteral.class);
	LITERALS.add(StringLiteral.class);
	LITERALS.add(BooleanLiteral.class);

	LITERALS.add(IdLiteral.class);
    }

    private static final List<Class<? extends TokenDefinition>> SYMBOLS = new ArrayList<Class<? extends TokenDefinition>>();
    static {
	SYMBOLS.add(MultiLineComment.class);
	SYMBOLS.add(LineComment.class);
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
	SYMBOLS.add(SlashAssign.class);
	SYMBOLS.add(StarAssign.class);
	SYMBOLS.add(Assign.class);
	SYMBOLS.add(BitOr.class);
	SYMBOLS.add(Caret.class);

	SYMBOLS.add(PlusPlus.class);
	SYMBOLS.add(MinusMinus.class);

	SYMBOLS.add(Plus.class);
	SYMBOLS.add(Minus.class);
	SYMBOLS.add(Slash.class);
	SYMBOLS.add(Star.class);
	SYMBOLS.add(Percent.class);
	SYMBOLS.add(BackSlash.class);

	SYMBOLS.add(LogicalAnd.class);
	SYMBOLS.add(BitAnd.class);
	SYMBOLS.add(LogicalOr.class);
	SYMBOLS.add(BitOr.class);
	SYMBOLS.add(Not.class);

	SYMBOLS.add(Tilde.class);
	SYMBOLS.add(QuestionMark.class);

	SYMBOLS.add(Semicolon.class);
	SYMBOLS.add(Comma.class);
	SYMBOLS.add(Colon.class);
	SYMBOLS.add(Dot.class);
    }

    private static CPlusPlus instance = null;

    public static CPlusPlus getInstance() {
	if (instance == null) {
	    createInstance();
	}
	return instance;
    }

    private static synchronized void createInstance() {
	if (instance == null) {
	    instance = new CPlusPlus();
	}
    }

    private CPlusPlus() {
	super("C++");
    }

    @Override
    public boolean isObjectOriented() {
	return true;
    }

    @Override
    protected Class<? extends Analyser> getAnalyserClass() {
	return CPPAnalyser.class;
    }

    @Override
    protected String[] getValidFileSuffixes() {
	return FILE_SUFFIXES;
    }

    @Override
    public List<Class<? extends TokenDefinition>> getKeywords() {
	return KEYWORDS;
    }

    @Override
    public List<Class<? extends TokenDefinition>> getLiterals() {
	return LITERALS;
    }

    @Override
    public List<Class<? extends TokenDefinition>> getSymbols() {
	return SYMBOLS;
    }
}
