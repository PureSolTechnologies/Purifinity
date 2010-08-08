package com.puresol.coding.lang.fortran;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.AbstractProgrammingLanguage;
import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.lang.fortran.source.keywords.*;
import com.puresol.coding.lang.fortran.source.literals.*;
import com.puresol.coding.lang.fortran.source.symbols.*;
import com.puresol.parser.tokens.TokenDefinition;
import com.puresol.utils.PersistenceException;

public class Fortran extends AbstractProgrammingLanguage {

	private static final String[] FILE_SUFFIXES = { ".f", ".f77", ".f90",
			".f95", ".for" };

	private static final List<Class<? extends TokenDefinition>> KEYWORDS = new ArrayList<Class<? extends TokenDefinition>>();
	static {
		KEYWORDS.add(AbstractKeyword.class);
		KEYWORDS.add(AccessKeyword.class);
		KEYWORDS.add(AcquiredLockKeyword.class);
		KEYWORDS.add(ActionKeyword.class);
		KEYWORDS.add(AdvanceKeyword.class);
		KEYWORDS.add(AKeyword.class);
		KEYWORDS.add(AllocatableKeyword.class);
		KEYWORDS.add(AllocateKeyword.class);
		KEYWORDS.add(AssignmentKeyword.class);
		KEYWORDS.add(AssociateKeyword.class);
		KEYWORDS.add(AsynchronousKeyword.class);
		KEYWORDS.add(BackspaceKeyword.class);
		KEYWORDS.add(BindKeyword.class);
		KEYWORDS.add(BKeyword.class);
		KEYWORDS.add(BlankKeyword.class);
		KEYWORDS.add(BlockDataKeyword.class);
		KEYWORDS.add(BlockKeyword.class);
		KEYWORDS.add(BNKeyword.class);
		KEYWORDS.add(BZKeyword.class);
		KEYWORDS.add(CallKeyword.class);
		KEYWORDS.add(CaseDefaultKeyword.class);
		KEYWORDS.add(CaseKeyword.class);
		KEYWORDS.add(CharacterKeyword.class);
		KEYWORDS.add(CKeyword.class);
		KEYWORDS.add(ClassDefaultKeyword.class);
		KEYWORDS.add(ClassIsKeyword.class);
		KEYWORDS.add(ClassKeyword.class);
		KEYWORDS.add(CloseKeyword.class);
		KEYWORDS.add(CodimensionKeyword.class);
		KEYWORDS.add(CommonKeyword.class);
		KEYWORDS.add(ComplexKeyword.class);
		KEYWORDS.add(ConcurrentKeyword.class);
		KEYWORDS.add(ContainsKeyword.class);
		KEYWORDS.add(ContiguousKeyword.class);
		KEYWORDS.add(ContinueKeyword.class);
		KEYWORDS.add(CriticalKeyword.class);
		KEYWORDS.add(CycleKeyword.class);
		KEYWORDS.add(DataKeyword.class);
		KEYWORDS.add(DCKeyword.class);
		KEYWORDS.add(DeallocateKeyword.class);
		KEYWORDS.add(DecimalKeyword.class);
		KEYWORDS.add(DefaultKeyword.class);
		KEYWORDS.add(DeferredKeyword.class);
		KEYWORDS.add(DelimKeyword.class);
		KEYWORDS.add(DimensionKeyword.class);
		KEYWORDS.add(DirectKeyword.class);
		KEYWORDS.add(DKeyword.class);
		KEYWORDS.add(DoKeyword.class);
		KEYWORDS.add(DotANDDot.class);
		KEYWORDS.add(DotEQDot.class);
		KEYWORDS.add(DotFALSEDot.class);
		KEYWORDS.add(DotGEDot.class);
		KEYWORDS.add(DotGTDot.class);
		KEYWORDS.add(DotLEDot.class);
		KEYWORDS.add(DotLTDot.class);
		KEYWORDS.add(DotNEDot.class);
		KEYWORDS.add(DotNOTDot.class);
		KEYWORDS.add(DotORDot.class);
		KEYWORDS.add(DotTRUEDot.class);
		KEYWORDS.add(DoubleKeyword.class);
		KEYWORDS.add(DoublePrecisionKeyword.class);
		KEYWORDS.add(DPKeyword.class);
		KEYWORDS.add(DTKeyword.class);
		KEYWORDS.add(EKeyword.class);
		KEYWORDS.add(ElementalKeyword.class);
		KEYWORDS.add(ElseIfKeyword.class);
		KEYWORDS.add(ElseKeyword.class);
		KEYWORDS.add(ElseWhereKeyword.class);
		KEYWORDS.add(EncodingKeyword.class);
		KEYWORDS.add(EndAssociateKeyword.class);
		KEYWORDS.add(EndBlockDataKeyword.class);
		KEYWORDS.add(EndBlockKeyword.class);
		KEYWORDS.add(EndCriticalKeyword.class);
		KEYWORDS.add(EndDoKeyword.class);
		KEYWORDS.add(EndEnumKeyword.class);
		KEYWORDS.add(EndFileKeyword.class);
		KEYWORDS.add(EndForAllKeyword.class);
		KEYWORDS.add(EndFunctionKeyword.class);
		KEYWORDS.add(EndIfInterfaceKeyword.class);
		KEYWORDS.add(EndIfKeyword.class);
		KEYWORDS.add(EndInterfaceKeyword.class);
		KEYWORDS.add(EndKeyword.class);
		KEYWORDS.add(EndModuleKeyword.class);
		KEYWORDS.add(EndProcedureKeyword.class);
		KEYWORDS.add(EndProgramKeyword.class);
		KEYWORDS.add(EndSelectKeyword.class);
		KEYWORDS.add(EndSubmoduleKeyword.class);
		KEYWORDS.add(EndSubroutineKeyword.class);
		KEYWORDS.add(EndTypeKeyword.class);
		KEYWORDS.add(EndWhereKeyword.class);
		KEYWORDS.add(ENKeyword.class);
		KEYWORDS.add(EntryKeyword.class);
		KEYWORDS.add(EnumeratorKeyword.class);
		KEYWORDS.add(EnumKeyword.class);
		KEYWORDS.add(EorKeyword.class);
		KEYWORDS.add(EquivalenceKeyword.class);
		KEYWORDS.add(ErrKeyword.class);
		KEYWORDS.add(ErrMsgKeyword.class);
		KEYWORDS.add(ErrorKeyword.class);
		KEYWORDS.add(ESKeyword.class);
		KEYWORDS.add(ExistKeyword.class);
		KEYWORDS.add(ExitKeyword.class);
		KEYWORDS.add(ExtendsKeyword.class);
		KEYWORDS.add(ExternalKeyword.class);
		KEYWORDS.add(FileKeyword.class);
		KEYWORDS.add(FinalKeyword.class);
		KEYWORDS.add(FKeyword.class);
		KEYWORDS.add(FlushKeyword.class);
		KEYWORDS.add(FmtKeyword.class);
		KEYWORDS.add(ForAllKeyword.class);
		KEYWORDS.add(FormatKeyword.class);
		KEYWORDS.add(FormattedKeyword.class);
		KEYWORDS.add(FormKeyword.class);
		KEYWORDS.add(FunctionKeyword.class);
		KEYWORDS.add(GenericKeyword.class);
		KEYWORDS.add(GKeyword.class);
		KEYWORDS.add(GotoKeyword.class);
		KEYWORDS.add(IdKeyword.class);
		KEYWORDS.add(IfKeyword.class);
		KEYWORDS.add(IKeyword.class);
		KEYWORDS.add(ImKeyword.class);
		KEYWORDS.add(ImplicitKeyword.class);
		KEYWORDS.add(ImportKeyword.class);
		KEYWORDS.add(ImpureKeyword.class);
		KEYWORDS.add(IncludeKeyword.class);
		KEYWORDS.add(InKeyword.class);
		KEYWORDS.add(InOutKeyword.class);
		KEYWORDS.add(InquireKeyword.class);
		KEYWORDS.add(IntegerKeyword.class);
		KEYWORDS.add(IntentKeyword.class);
		KEYWORDS.add(InterfaceKeyword.class);
		KEYWORDS.add(IntrinsicKeyword.class);
		KEYWORDS.add(IoLengthKeyword.class);
		KEYWORDS.add(IoMsgKeyword.class);
		KEYWORDS.add(IoStatKeyword.class);
		KEYWORDS.add(KindKeyword.class);
		KEYWORDS.add(LenKeyword.class);
		KEYWORDS.add(LKeyword.class);
		KEYWORDS.add(LockKeyword.class);
		KEYWORDS.add(LogicalKeyword.class);
		KEYWORDS.add(ModuleKeyword.class);
		KEYWORDS.add(ModuleProcedureKeyword.class);
		KEYWORDS.add(MoldKeyword.class);
		KEYWORDS.add(NamedKeyword.class);
		KEYWORDS.add(NameKeyword.class);
		KEYWORDS.add(NamelistKeyword.class);
		KEYWORDS.add(NewUnitKeyword.class);
		KEYWORDS.add(NextRecKeyword.class);
		KEYWORDS.add(NmlKeyword.class);
		KEYWORDS.add(NoneKeyword.class);
		KEYWORDS.add(NonKeyword.class);
		KEYWORDS.add(NoPassKeyword.class);
		KEYWORDS.add(NullifyKeyword.class);
		KEYWORDS.add(NumberKeyword.class);
		KEYWORDS.add(OKeyword.class);
		KEYWORDS.add(OnlyKeyword.class);
		KEYWORDS.add(OpenedKeyword.class);
		KEYWORDS.add(OpenKeyword.class);
		KEYWORDS.add(OperatorKeyword.class);
		KEYWORDS.add(OptionalKeyword.class);
		KEYWORDS.add(OutKeyword.class);
		KEYWORDS.add(OverridableKeyword.class);
		KEYWORDS.add(PadKeyword.class);
		KEYWORDS.add(ParameterKeyword.class);
		KEYWORDS.add(PartIsKeyword.class);
		KEYWORDS.add(PassKeyword.class);
		KEYWORDS.add(PendingKeyword.class);
		KEYWORDS.add(PKeyword.class);
		KEYWORDS.add(PointerKeyword.class);
		KEYWORDS.add(PositionKeyword.class);
		KEYWORDS.add(PosKeyword.class);
		KEYWORDS.add(PrintKeyword.class);
		KEYWORDS.add(PrivateKeyword.class);
		KEYWORDS.add(ProcedureKeyword.class);
		KEYWORDS.add(ProgramKeyword.class);
		KEYWORDS.add(ProtectedKeyword.class);
		KEYWORDS.add(PublicKeyword.class);
		KEYWORDS.add(PureKeyword.class);
		KEYWORDS.add(RCKeyword.class);
		KEYWORDS.add(RDKeyword.class);
		KEYWORDS.add(ReadKeyword.class);
		KEYWORDS.add(ReadWriteKeyword.class);
		KEYWORDS.add(RealKeyword.class);
		KEYWORDS.add(RecKeyword.class);
		KEYWORDS.add(ReclKeyword.class);
		KEYWORDS.add(RecursiveKeyword.class);
		KEYWORDS.add(ReKeyword.class);
		KEYWORDS.add(ResultKeyword.class);
		KEYWORDS.add(ReturnKeyword.class);
		KEYWORDS.add(RewindKeyword.class);
		KEYWORDS.add(RNKeyword.class);
		KEYWORDS.add(RoundKeyword.class);
		KEYWORDS.add(RPKeyword.class);
		KEYWORDS.add(RUKeyword.class);
		KEYWORDS.add(RZKeyword.class);
		KEYWORDS.add(SaveKeyword.class);
		KEYWORDS.add(SelectCaseKeyword.class);
		KEYWORDS.add(SelectTypeKeyword.class);
		KEYWORDS.add(SequenceKeyword.class);
		KEYWORDS.add(SequentialKeyword.class);
		KEYWORDS.add(SignKeyword.class);
		KEYWORDS.add(SizeKeyword.class);
		KEYWORDS.add(SKeyword.class);
		KEYWORDS.add(SourceKeyword.class);
		KEYWORDS.add(SPKeyword.class);
		KEYWORDS.add(SSKeyword.class);
		KEYWORDS.add(StatKeyword.class);
		KEYWORDS.add(StatusKeyword.class);
		KEYWORDS.add(StopKeyword.class);
		KEYWORDS.add(StreamKeyword.class);
		KEYWORDS.add(SubmoduleKeyword.class);
		KEYWORDS.add(SubroutineKeyword.class);
		KEYWORDS.add(SwitchCaseKeyword.class);
		KEYWORDS.add(SyncAllKeyword.class);
		KEYWORDS.add(SyncImagesKeyword.class);
		KEYWORDS.add(SyncMemoryKeyword.class);
		KEYWORDS.add(TargetKeyword.class);
		KEYWORDS.add(ThenKeyword.class);
		KEYWORDS.add(TKeyword.class);
		KEYWORDS.add(TLKeyword.class);
		KEYWORDS.add(TRKeyword.class);
		KEYWORDS.add(TypeKeyword.class);
		KEYWORDS.add(UnformattedKeyword.class);
		KEYWORDS.add(UnitKeyword.class);
		KEYWORDS.add(UnlockKeyword.class);
		KEYWORDS.add(UseKeyword.class);
		KEYWORDS.add(ValueKeyword.class);
		KEYWORDS.add(VolatileKeyword.class);
		KEYWORDS.add(WaitKeyword.class);
		KEYWORDS.add(WhereKeyword.class);
		KEYWORDS.add(WhileKeyword.class);
		KEYWORDS.add(WriteKeyword.class);
		KEYWORDS.add(XKeyword.class);
		KEYWORDS.add(ZKeyword.class);
	}

	private static final List<Class<? extends TokenDefinition>> LITERALS = new ArrayList<Class<? extends TokenDefinition>>();
	static {
		LITERALS.add(ComplexLiteral.class);
		LITERALS.add(RealLiteral.class);
		LITERALS.add(IntegerLiteral.class);
		LITERALS.add(BozLiteral.class);
		LITERALS.add(LogicalLiteral.class);
		LITERALS.add(CharLiteral.class);
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
		SYMBOLS.add(LineComment.class);
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
		SYMBOLS.add(Star.class);
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
