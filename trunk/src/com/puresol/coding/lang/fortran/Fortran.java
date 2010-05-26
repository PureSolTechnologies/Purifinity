package com.puresol.coding.lang.fortran;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.AbstractProgrammingLanguage;
import com.puresol.coding.analysis.Analyser;
import com.puresol.coding.lang.fortran.source.keywords.CallKeyword;
import com.puresol.coding.lang.fortran.source.keywords.CaseDefaultKeyword;
import com.puresol.coding.lang.fortran.source.keywords.CaseKeyword;
import com.puresol.coding.lang.fortran.source.keywords.CharacterKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ComplexKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ContinueKeyword;
import com.puresol.coding.lang.fortran.source.keywords.CycleKeyword;
import com.puresol.coding.lang.fortran.source.keywords.DimensionKeyword;
import com.puresol.coding.lang.fortran.source.keywords.DoKeyword;
import com.puresol.coding.lang.fortran.source.keywords.DoWhileKeyword;
import com.puresol.coding.lang.fortran.source.keywords.DotANDDot;
import com.puresol.coding.lang.fortran.source.keywords.DotEQDot;
import com.puresol.coding.lang.fortran.source.keywords.DotFALSEDot;
import com.puresol.coding.lang.fortran.source.keywords.DotGEDot;
import com.puresol.coding.lang.fortran.source.keywords.DotGTDot;
import com.puresol.coding.lang.fortran.source.keywords.DotLEDot;
import com.puresol.coding.lang.fortran.source.keywords.DotLTDot;
import com.puresol.coding.lang.fortran.source.keywords.DotNEDot;
import com.puresol.coding.lang.fortran.source.keywords.DotNOTDot;
import com.puresol.coding.lang.fortran.source.keywords.DotORDot;
import com.puresol.coding.lang.fortran.source.keywords.DotTRUEDot;
import com.puresol.coding.lang.fortran.source.keywords.DoubleKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ElseIfKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ElseKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ElseWhereKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndDoKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndForAllKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndFunctionKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndIfKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndModuleKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndProgramKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndSubroutineKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ExitKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ExternalKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ForAllKeyword;
import com.puresol.coding.lang.fortran.source.keywords.FunctionKeyword;
import com.puresol.coding.lang.fortran.source.keywords.GotoKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IfKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ImplicitKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IntegerKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IntrinsicKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ModuleKeyword;
import com.puresol.coding.lang.fortran.source.keywords.NoneKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ParameterKeyword;
import com.puresol.coding.lang.fortran.source.keywords.PointerKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ProgramKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ReadKeyword;
import com.puresol.coding.lang.fortran.source.keywords.RealKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ReturnKeyword;
import com.puresol.coding.lang.fortran.source.keywords.RewindKeyword;
import com.puresol.coding.lang.fortran.source.keywords.StopKeyword;
import com.puresol.coding.lang.fortran.source.keywords.SubroutineKeyword;
import com.puresol.coding.lang.fortran.source.keywords.SwitchCaseKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ThenKeyword;
import com.puresol.coding.lang.fortran.source.keywords.WhereKeyword;
import com.puresol.coding.lang.fortran.source.keywords.WriteKeyword;
import com.puresol.coding.lang.fortran.source.literals.FloatingPointDoublePrecessionLiteral;
import com.puresol.coding.lang.fortran.source.literals.FloatingPointLiteral;
import com.puresol.coding.lang.fortran.source.literals.FormatLiteral;
import com.puresol.coding.lang.fortran.source.literals.IdLiteral;
import com.puresol.coding.lang.fortran.source.literals.IntegerLiteral;
import com.puresol.coding.lang.fortran.source.literals.StringLiteral;
import com.puresol.coding.lang.fortran.source.literals.StringStartLiteral;
import com.puresol.coding.lang.fortran.source.literals.StringStopLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Ampersand;
import com.puresol.coding.lang.fortran.source.symbols.Assign;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.Equal;
import com.puresol.coding.lang.fortran.source.symbols.GreaterEqual;
import com.puresol.coding.lang.fortran.source.symbols.GreaterThan;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.LessEqual;
import com.puresol.coding.lang.fortran.source.symbols.LessThan;
import com.puresol.coding.lang.fortran.source.symbols.LineBreak;
import com.puresol.coding.lang.fortran.source.symbols.LineComment;
import com.puresol.coding.lang.fortran.source.symbols.Minus;
import com.puresol.coding.lang.fortran.source.symbols.Plus;
import com.puresol.coding.lang.fortran.source.symbols.PointerAssign;
import com.puresol.coding.lang.fortran.source.symbols.Power;
import com.puresol.coding.lang.fortran.source.symbols.Question;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.coding.lang.fortran.source.symbols.Semicolon;
import com.puresol.coding.lang.fortran.source.symbols.Slash;
import com.puresol.coding.lang.fortran.source.symbols.Star;
import com.puresol.coding.lang.fortran.source.symbols.Unequal;
import com.puresol.coding.lang.fortran.source.symbols.WhiteSpace;
import com.puresol.parser.TokenDefinition;

public class Fortran extends AbstractProgrammingLanguage {

    private static final String[] FILE_SUFFIXES = { ".f", ".f77", ".f90",
	    ".f95", ".for" };

    private static final List<Class<? extends TokenDefinition>> KEYWORDS = new ArrayList<Class<? extends TokenDefinition>>();
    static {
	KEYWORDS.add(ProgramKeyword.class);
	KEYWORDS.add(SubroutineKeyword.class);
	KEYWORDS.add(FunctionKeyword.class);
	KEYWORDS.add(ModuleKeyword.class);
	KEYWORDS.add(EndProgramKeyword.class);
	KEYWORDS.add(EndSubroutineKeyword.class);
	KEYWORDS.add(EndFunctionKeyword.class);
	KEYWORDS.add(EndModuleKeyword.class);

	KEYWORDS.add(ForAllKeyword.class);
	KEYWORDS.add(ContinueKeyword.class);
	KEYWORDS.add(DoWhileKeyword.class);
	KEYWORDS.add(DoKeyword.class);
	KEYWORDS.add(IfKeyword.class);
	KEYWORDS.add(ThenKeyword.class);
	KEYWORDS.add(EndDoKeyword.class);
	KEYWORDS.add(EndIfKeyword.class);
	KEYWORDS.add(EndForAllKeyword.class);
	KEYWORDS.add(EndKeyword.class);
	KEYWORDS.add(ElseIfKeyword.class);
	KEYWORDS.add(ElseKeyword.class);
	KEYWORDS.add(ExitKeyword.class);
	KEYWORDS.add(StopKeyword.class);
	KEYWORDS.add(CycleKeyword.class);
	KEYWORDS.add(GotoKeyword.class);

	KEYWORDS.add(CallKeyword.class);
	KEYWORDS.add(ReturnKeyword.class);

	KEYWORDS.add(ReadKeyword.class);
	KEYWORDS.add(WriteKeyword.class);
	KEYWORDS.add(RewindKeyword.class);

	KEYWORDS.add(SwitchCaseKeyword.class);
	KEYWORDS.add(CaseDefaultKeyword.class);
	KEYWORDS.add(CaseKeyword.class);
	KEYWORDS.add(WhereKeyword.class);
	KEYWORDS.add(ElseWhereKeyword.class);

	KEYWORDS.add(ParameterKeyword.class);
	KEYWORDS.add(ExternalKeyword.class);
	KEYWORDS.add(IntrinsicKeyword.class);
	KEYWORDS.add(ImplicitKeyword.class);
	KEYWORDS.add(NoneKeyword.class);

	KEYWORDS.add(PointerKeyword.class);
	KEYWORDS.add(DimensionKeyword.class);

	KEYWORDS.add(ComplexKeyword.class);
	KEYWORDS.add(CharacterKeyword.class);
	KEYWORDS.add(DoubleKeyword.class);
	KEYWORDS.add(RealKeyword.class);
	KEYWORDS.add(IntegerKeyword.class);

	KEYWORDS.add(DotEQDot.class);
	KEYWORDS.add(DotNEDot.class);
	KEYWORDS.add(DotLTDot.class);
	KEYWORDS.add(DotGTDot.class);
	KEYWORDS.add(DotLEDot.class);
	KEYWORDS.add(DotGEDot.class);

	KEYWORDS.add(DotORDot.class);
	KEYWORDS.add(DotANDDot.class);
	KEYWORDS.add(DotNOTDot.class);
	KEYWORDS.add(DotTRUEDot.class);
	KEYWORDS.add(DotFALSEDot.class);
    }

    private static final List<Class<? extends TokenDefinition>> LITERALS = new ArrayList<Class<? extends TokenDefinition>>();
    static {
	LITERALS.add(StringLiteral.class);
	LITERALS.add(StringStartLiteral.class);
	LITERALS.add(StringStopLiteral.class);
	LITERALS.add(FloatingPointDoublePrecessionLiteral.class);
	LITERALS.add(FloatingPointLiteral.class);
	LITERALS.add(IntegerLiteral.class);

	LITERALS.add(IdLiteral.class); // is subset of FormatLiteral!
	LITERALS.add(FormatLiteral.class);
    }

    private static final List<Class<? extends TokenDefinition>> SYMBOLS = new ArrayList<Class<? extends TokenDefinition>>();
    static {
	SYMBOLS.add(LineComment.class);

	SYMBOLS.add(LineBreak.class);
	SYMBOLS.add(WhiteSpace.class);
	SYMBOLS.add(LParen.class);
	SYMBOLS.add(RParen.class);

	SYMBOLS.add(Equal.class);
	SYMBOLS.add(Unequal.class);
	SYMBOLS.add(LessThan.class);
	SYMBOLS.add(GreaterThan.class);
	SYMBOLS.add(LessEqual.class);
	SYMBOLS.add(GreaterEqual.class);

	SYMBOLS.add(PointerAssign.class);

	SYMBOLS.add(Assign.class);
	SYMBOLS.add(Slash.class);

	SYMBOLS.add(Power.class);
	SYMBOLS.add(Star.class);
	SYMBOLS.add(Minus.class);
	SYMBOLS.add(Plus.class);

	SYMBOLS.add(Comma.class);
	SYMBOLS.add(Colon.class);
	SYMBOLS.add(Semicolon.class);
	SYMBOLS.add(Ampersand.class);
	SYMBOLS.add(Question.class);
    }

    private static Fortran instance = null;

    public static Fortran getInstance() {
	if (instance == null) {
	    createInstance();
	}
	return instance;
    }

    private static synchronized void createInstance() {
	if (instance == null) {
	    instance = new Fortran();
	}
    }

    private Fortran() {
	super("Fortran");
    }

    @Override
    public boolean isObjectOriented() {
	return false;
    }

    @Override
    protected Class<? extends Analyser> getAnalyserClass() {
	return FortranAnalyser.class;
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
