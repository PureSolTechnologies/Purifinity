package com.puresol.coding.lang.fortran;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.AbstractProgrammingLanguage;
import com.puresol.coding.analysis.Analyzer;
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
import com.puresol.coding.lang.fortran.source.literals.RealLiteral;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.literals.IntegerLiteral;
import com.puresol.coding.lang.fortran.source.literals.CharLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Ampersand;
import com.puresol.coding.lang.fortran.source.symbols.Apostrophe;
import com.puresol.coding.lang.fortran.source.symbols.Backslash;
import com.puresol.coding.lang.fortran.source.symbols.Blank;
import com.puresol.coding.lang.fortran.source.symbols.CircumflexAccent;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.CommercialAt;
import com.puresol.coding.lang.fortran.source.symbols.CurrencySymbol;
import com.puresol.coding.lang.fortran.source.symbols.DecimalPoint;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.coding.lang.fortran.source.symbols.GraveAccent;
import com.puresol.coding.lang.fortran.source.symbols.GreaterThan;
import com.puresol.coding.lang.fortran.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.LBracket;
import com.puresol.coding.lang.fortran.source.symbols.LessThan;
import com.puresol.coding.lang.fortran.source.symbols.LineTerminator;
import com.puresol.coding.lang.fortran.source.symbols.Minus;
import com.puresol.coding.lang.fortran.source.symbols.NumberSign;
import com.puresol.coding.lang.fortran.source.symbols.Percent;
import com.puresol.coding.lang.fortran.source.symbols.Plus;
import com.puresol.coding.lang.fortran.source.symbols.QuestionMark;
import com.puresol.coding.lang.fortran.source.symbols.RCurlyBracket;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.coding.lang.fortran.source.symbols.RBracket;
import com.puresol.coding.lang.fortran.source.symbols.Semicolon;
import com.puresol.coding.lang.fortran.source.symbols.Slash;
import com.puresol.coding.lang.fortran.source.symbols.Asterik;
import com.puresol.coding.lang.fortran.source.symbols.Tilde;
import com.puresol.coding.lang.fortran.source.symbols.VerticalLine;
import com.puresol.parser.TokenDefinition;
import com.puresol.utils.PersistenceException;

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
		LITERALS.add(CharLiteral.class);
		LITERALS.add(FloatingPointDoublePrecessionLiteral.class);
		LITERALS.add(RealLiteral.class);
		LITERALS.add(IntegerLiteral.class);
		LITERALS.add(NameLiteral.class); // is subset of FormatLiteral!
	}

	private static final List<Class<? extends TokenDefinition>> SYMBOLS = new ArrayList<Class<? extends TokenDefinition>>();
	static {
		SYMBOLS.add(Ampersand.class);
		SYMBOLS.add(Apostrophe.class);
		SYMBOLS.add(Asterik.class);
		SYMBOLS.add(Backslash.class);
		SYMBOLS.add(Blank.class);
		SYMBOLS.add(CircumflexAccent.class);
		SYMBOLS.add(Colon.class);
		SYMBOLS.add(Comma.class);
		SYMBOLS.add(CommercialAt.class);
		SYMBOLS.add(CurrencySymbol.class);
		SYMBOLS.add(DecimalPoint.class);
		SYMBOLS.add(Equals.class);
		SYMBOLS.add(GraveAccent.class);
		SYMBOLS.add(GreaterThan.class);
		SYMBOLS.add(LCurlyBracket.class);
		SYMBOLS.add(LessThan.class);
		SYMBOLS.add(LineTerminator.class);
		SYMBOLS.add(LParen.class);
		SYMBOLS.add(LBracket.class);
		SYMBOLS.add(Minus.class);
		SYMBOLS.add(NumberSign.class);
		SYMBOLS.add(Percent.class);
		SYMBOLS.add(Plus.class);
		SYMBOLS.add(QuestionMark.class);
		SYMBOLS.add(RCurlyBracket.class);
		SYMBOLS.add(RParen.class);
		SYMBOLS.add(RBracket.class);
		SYMBOLS.add(Semicolon.class);
		SYMBOLS.add(Slash.class);
		SYMBOLS.add(Tilde.class);
		SYMBOLS.add(VerticalLine.class);
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
	protected Class<? extends Analyzer> getAnalyserClass() {
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

	@Override
	public Analyzer restoreAnalyzer(File file) throws PersistenceException {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			Analyzer analyzer = (Analyzer) ois.readObject();
			ois.close();
			return analyzer;
		} catch (Throwable e) {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e1) {
				}
			}
			throw new PersistenceException(e);
		}
	}
}
